package mad.science.core.api.power;

import mad.science.core.MadScience;
import net.fabricmc.fabric.api.lookup.v1.block.BlockApiLookup;
import net.fabricmc.fabric.api.lookup.v1.item.ItemApiLookup;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.storage.StoragePreconditions;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.fabricmc.fabric.api.transfer.v1.transaction.base.SnapshotParticipant;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("ALL")
// A simple power storage class modeled by Team Reborn's Energy API
// Can be used for all power types
public class PowerStorage extends SnapshotParticipant<Long> {
    public static final BlockApiLookup<PowerStorage, @Nullable Direction> SIDED = BlockApiLookup.get(new Identifier(MadScience.MODID, "sided_power"), PowerStorage.class, Direction.class);
    public static final ItemApiLookup<PowerStorage, ContainerItemContext> ITEM = ItemApiLookup.get(new Identifier(MadScience.MODID, "item_power"), PowerStorage.class, ContainerItemContext.class);

    public long amount = 0;
    public final long capacity;
    public final long maxInsert;
    public final long maxExtract;
    public final PowerType type;

    public PowerStorage(PowerType type, long capacity, long maxInsert, long maxExtract) {
        StoragePreconditions.notNegative(capacity);
        StoragePreconditions.notNegative(maxInsert);
        StoragePreconditions.notNegative(maxExtract);

        this.capacity = capacity;
        this.maxInsert = maxInsert;
        this.maxExtract = maxExtract;
        this.type = type;
    }

    public PowerType getType() {
        return this.type;
    }

    @Override
    protected Long createSnapshot() {
        return amount;
    }

    @Override
    protected void readSnapshot(Long snapshot) {
        amount = snapshot;
    }

    public boolean canInsert() {
        return maxInsert > 0;
    }

    public long insert(long maxAmount, TransactionContext transaction) {
        StoragePreconditions.notNegative(maxAmount);

        long inserted = Math.min(maxInsert, Math.min(maxAmount, capacity - amount));

        if (inserted > 0) {
            updateSnapshots(transaction);
            amount += inserted;
            return inserted;
        }

        return 0;
    }

    public boolean canExtract() {
        return maxExtract > 0;
    }

    public long extract(long maxAmount, TransactionContext transaction) {
        StoragePreconditions.notNegative(maxAmount);

        long extracted = Math.min(maxExtract, Math.min(maxAmount, amount));

        if (extracted > 0) {
            updateSnapshots(transaction);
            amount -= extracted;
            return extracted;
        }

        return 0;
    }

    public long getPower() {
        return amount;
    }

    public long getCapacity() {
        return capacity;
    }
}
