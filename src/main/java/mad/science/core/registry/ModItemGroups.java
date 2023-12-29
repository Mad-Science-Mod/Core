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
                    .icon(() -> new ItemStack(ModItems.BIT)).entries((displayContext, entries) -> {
                        // Exotic Materials
                        entries.add(ModItems.FRACTAL);
                        entries.add(ModItems.VOID_DUST);
                        entries.add(ModItems.BIT);

                        // Tools


                        // Components
                        entries.add(ModItems.CLOCKWORK);
                        entries.add(ModItems.CLOCKWORK_HEART);
                        entries.add(ModItems.CLOCK_HAND);
                        entries.add(ModItems.CONTROL_CIRCUIT);
                        entries.add(ModItems.LAPIS_LACED_CIRCUIT);
                        entries.add(ModItems.PROJECTOR);

                        // Food Items
                        entries.add(ModItems.FOOD);

                        // Ore Items
                        entries.add(ModBlocks.TUBEROUS_DIRT);
                        entries.add(ModBlocks.FRACTAL_ORE);
                        entries.add(ModBlocks.SHADOWSILK_ORE);
                    }).build());

    public static void registerItemGroups() {

    }
}
