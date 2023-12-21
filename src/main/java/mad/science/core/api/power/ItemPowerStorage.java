package mad.science.core.api.power;

import mad.science.core.util.PowerUtil;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.StoragePreconditions;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.item.ItemStack;

public class ItemPowerStorage extends PowerStorage {
    private final ContainerItemContext ctx;

    private ItemPowerStorage(ContainerItemContext ctx, PowerType type, long capacity, long maxInsert, long maxExtract) {
        super(type, capacity, maxInsert, maxExtract);
        this.ctx = ctx;
    }

    public static ItemPowerStorage createSimple(ContainerItemContext ctx, PowerType type, long capacity, long maxInsert, long maxExtract) {
        StoragePreconditions.notNegative(capacity);
        StoragePreconditions.notNegative(maxInsert);
        StoragePreconditions.notNegative(maxExtract);
        return new ItemPowerStorage(ctx, type, capacity, maxInsert, maxExtract);
    }

    // Attempts to set the stack's power, returns true if successful
    private boolean setPower(long energyAmountPerCount, long count, TransactionContext transaction) {
        ItemStack newStack = ctx.getItemVariant().toStack();
        PowerUtil.setPowerFor(newStack, energyAmountPerCount);
        ItemVariant newVariant = ItemVariant.of(newStack);

        try (Transaction nested = transaction.openNested()) {
            if (ctx.extract(ctx.getItemVariant(), count, nested) == count && ctx.insert(newVariant, count, nested) == count) {
                nested.commit();
                return true;
            }
        }

        return false;
    }

    @Override
    public long insert(long maxAmount, TransactionContext transaction) {
        long count = ctx.getAmount();

        long maxAmountPerCount = maxAmount / count;
        long currentAmountPerCount = this.getPower() / count;
        long insertedPerCount = Math.min(maxInsert, Math.min(maxAmountPerCount, capacity - currentAmountPerCount));

        if (insertedPerCount > 0) {
            if (this.setPower(currentAmountPerCount + insertedPerCount, count, transaction)) {
                return insertedPerCount * count;
            }
        }

        return 0;
    }

    @Override
    public long extract(long maxAmount, TransactionContext transaction) {
        long count = ctx.getAmount();

        long maxAmountPerCount = maxAmount / count;
        long currentAmountPerCount = this.getPower() / count;
        long extractedPerCount = Math.min(maxExtract, Math.min(maxAmountPerCount, currentAmountPerCount));

        if (extractedPerCount > 0) {
            if (this.setPower(currentAmountPerCount - extractedPerCount, count, transaction)) {
                return extractedPerCount * count;
            }
        }

        return 0;
    }

    @Override
    public long getPower() {
        return ctx.getAmount() * PowerUtil.getPowerIn(ctx.getItemVariant().getNbt());
    }

    @Override
    public long getCapacity() {
        return ctx.getAmount() * capacity;
    }
}
