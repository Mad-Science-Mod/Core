package mad.science.core.event;

import mad.science.core.api.event.CraftingEvents;
import mad.science.core.util.CoreUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class CommonEvents {

    public static void init() {
        CraftingEvents.ON_CRAFT.register(CommonEvents::madnessEnchant);
    }

    // Players with medium madness have a chance to gain an enchantment when crafting
    public static ItemStack madnessEnchant(ScreenHandler handler, RecipeInputInventory craftingInventory, CraftingResultInventory resultInventory, World world, ServerPlayerEntity player, CraftingRecipe recipe) {
        ItemStack result = recipe.craft(craftingInventory, world.getRegistryManager());
        if (result.isItemEnabled(world.getEnabledFeatures())) {
            if (CoreUtil.getMadness(player) >= 100 && result.isEnchantable() && !result.isOf(Items.BOOK) && !result.hasEnchantments()) {
                if (CoreUtil.chance(0.2)) {
                    EnchantmentHelper.enchant(player.getRandom(), result, player.getRandom().nextBetween(5, 7), false);
                    return result;
                }
            }
        }
        return ItemStack.EMPTY;
    }
}
