package mad.science.core.registry;

import mad.science.core.MadScience;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static ItemGroup MAD_SCIENCE = Registry.register(Registries.ITEM_GROUP, new Identifier(MadScience.MODID, "mad_science"),
            FabricItemGroup.builder().displayName(Text.translatable("Mad Science"))
                    .icon(() -> new ItemStack(ModBlocks.TUBEROUS_DIRT)).entries((displayContext, entries) -> {

                        // Food Items
                        entries.add(ModItems.FOOD);

                        // Ore Items
                        entries.add(ModBlocks.TUBEROUS_DIRT);
                    }).build());

    public static void registerItemGroups() {

    }
}
