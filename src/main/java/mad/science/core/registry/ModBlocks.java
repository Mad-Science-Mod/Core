package mad.science.core.registry;

import mad.science.core.MadScience;
import mad.science.core.blocks.SulphireBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {

    public static final Block SULPHIRE = registerBlock("sulphire",
            new SulphireBlock(FabricBlockSettings.create().mapColor(MapColor.DARK_AQUA).replaceable().noCollision().breakInstantly().luminance((state) -> {
                return 10;
            }).sounds(BlockSoundGroup.WOOL).pistonBehavior(PistonBehavior.DESTROY)));
    // Ores
    public static final Block TUBEROUS_DIRT = registerBlock("tuberous_dirt",
            new Block(FabricBlockSettings.copyOf(Blocks.DIRT).strength(0.7f)
            ));
    public static final Block SHADOWSILK_ORE = registerBlock("shadowsilk_ore",
            new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_IRON_ORE).strength(4.5F, 3.0F).requiresTool()
            ));
    public static final Block FRACTAL_ORE = registerBlock("fractal_ore",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_ORE).requiresTool().strength(3.0F, 3.0F)
            ));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(MadScience.MODID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        Item item = Registry.register(Registries.ITEM, new Identifier(MadScience.MODID, name),
                new BlockItem(block, new FabricItemSettings()));
        return item;
    }

    public static void registerModBlocks() {
        MadScience.LOGGER.info("Registering ModBlocks for " + MadScience.MODID);
    }
}
