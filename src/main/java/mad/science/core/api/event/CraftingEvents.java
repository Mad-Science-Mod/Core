package mad.science.core.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class CraftingEvents {
    public static final Event<CraftingEvents.OnCraft> ON_CRAFT = EventFactory.createArrayBacked(CraftingEvents.OnCraft.class, callbacks -> (handler, inv, result, world, player, recipe) -> {
        for (CraftingEvents.OnCraft callback : callbacks) {
            ItemStack stack = callback.onCraft(handler, inv, result, world, player, recipe);
            if (!stack.isEmpty()) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    });

    @FunctionalInterface
    public interface OnCraft {
        // Called right before an item is crafted but after the recipe is confirmed valid
        // Return an itemstack to be used as the crafting result or return empty if result is unchanged
        ItemStack onCraft(ScreenHandler handler, RecipeInputInventory inputInventory, CraftingResultInventory resultInventory, World world, ServerPlayerEntity player, CraftingRecipe recipe);
    }
}
