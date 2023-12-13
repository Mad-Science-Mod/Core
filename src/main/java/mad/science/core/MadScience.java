package mad.science.core;

import mad.science.core.event.CommonEvents;
import mad.science.core.registry.*;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MadScience implements ModInitializer {
	public static final String MODID = "mad_science";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	public static final RegistryKey<PlacedFeature> TUBEROUS_DIRT_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(MODID ,"tuberous_dirt"));
	public static final RegistryKey<PlacedFeature> SHADOWSILK_ORE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(MODID ,"shadowsilk_ore"));
	public static final RegistryKey<PlacedFeature> FRACTAL_ORE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(MODID ,"fractal_ore"));

	@Override
	public void onInitialize() {
		// Registries
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModCommands.init();
		ModStatusEffects.init();

		// Events
		CommonEvents.init();

		// WorldGen
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, TUBEROUS_DIRT_PLACED_KEY);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, SHADOWSILK_ORE_PLACED_KEY);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, FRACTAL_ORE_PLACED_KEY);
	}
}