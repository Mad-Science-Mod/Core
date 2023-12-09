package mad.science.core;

import mad.science.core.event.CommonEvents;
import mad.science.core.registry.ModCommands;
import mad.science.core.registry.ModItemGroups;
import mad.science.core.registry.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MadScience implements ModInitializer {
	public static final String MODID = "mad-science";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
		// Registries
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModCommands.init();

		// Events
		CommonEvents.init();
	}
}