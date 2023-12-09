package mad.science.core;

import mad.science.core.items.ModItemGroups;
import mad.science.core.items.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MadScience implements ModInitializer {
	public static final String MODID = "mad-science";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();

	}
}