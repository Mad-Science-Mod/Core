package mad.science.core.util;

import mad.science.core.api.power.PowerStorage;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class PowerUtil {
    public static final String POWER_NBT = "Power";

    public static boolean match(PowerStorage storage1, PowerStorage storage2) {
        return storage1.getType() == storage2.getType();
    }

    public static long getPowerIn(ItemStack stack) {
        return getPowerIn(stack.getNbt());
    }

    public static long getPowerIn(NbtCompound nbt) {
        return nbt == null ? 0 : nbt.getLong(POWER_NBT);
    }

    public static void setPowerFor(ItemStack stack, long amount) {
        if (amount == 0) {
            stack.removeSubNbt(POWER_NBT);
        } else {
            stack.getOrCreateNbt().putLong(POWER_NBT, amount);
        }
    }
}
