package mad.science.core.registry;

import mad.science.core.MadScience;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item FOOD = registerItem("food",
            new Item((new Item.Settings().food(FoodComponents.COOKIE))));

    public static final Item VOID_DUST = registerItem("void_dust",
            new Item(new FabricItemSettings()));
    public static final Item FRACTAL = registerItem("fractal",
            new Item(new FabricItemSettings()));
    public static final Item BIT = registerItem("bit",
            new Item(new FabricItemSettings()));
    public static final Item CLOCKWORK = registerItem("clockwork",
            new Item(new FabricItemSettings()));
    public static final Item CLOCKWORK_HEART = registerItem("clockwork_heart",
            new Item(new FabricItemSettings()));
    public static final Item CLOCK_HAND = registerItem("clock_hand",
            new Item(new FabricItemSettings()));
    public static final Item CONTROL_CIRCUIT = registerItem("control_circuit",
            new Item(new FabricItemSettings()));
    public static final Item LAPIS_LACED_CIRCUIT = registerItem("lapis_laced_circuit",
            new Item(new FabricItemSettings()));
    public static final Item PROJECTOR = registerItem("projector",
            new Item(new FabricItemSettings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(MadScience.MODID, name), item);
    }

    public static void registerModItems() {
        MadScience.LOGGER.info("Registering Mod Items for" + MadScience.MODID);
    }
}
