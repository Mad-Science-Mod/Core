package mad.science.core.util;

import mad.science.core.registry.ModComponents;
import net.minecraft.entity.player.PlayerEntity;

public class CoreUtil {

    // Returns the player's madness level
    public static long getMadness(PlayerEntity player) {
        return ModComponents.MADNESS.get(player).getMadness();
    }

    // A random percent chance - returns true if it occurs
    // Chance must be in decimal form (Ex. 10% -> 0.1)
    public static boolean chance(double percent) {
        return Math.random() <= percent;
    }
}
