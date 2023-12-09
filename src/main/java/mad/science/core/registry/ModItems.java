package mad.science.core.registry;

import mad.science.core.MadScience;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item FOOD = registerItem("food",
            new Item((new Item.Settings().food(FoodComponents.COOKIE))));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(MadScience.MODID, name), item);
    }

    public static void registerModItems() {
        MadScience.LOGGER.info("Registering Mod Items for" + MadScience.MODID);
    }
}
