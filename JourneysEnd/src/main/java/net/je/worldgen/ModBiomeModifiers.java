package net.je.worldgen;

import java.util.List;

import net.je.JourneysEnd;
import net.je.entity.ModEntities;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> SPAWN_ENDERSENT = registerKey("spawn_endersent");
    public static final ResourceKey<BiomeModifier> SPAWN_ENDERSENT_WITH_EYE = registerKey("spawn_endersent_with_eye");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeature = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(SPAWN_ENDERSENT, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.END_HIGHLANDS), biomes.getOrThrow(Biomes.END_MIDLANDS)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.ENDERSENT.get(), 5, 1, 1))));
        
        context.register(SPAWN_ENDERSENT_WITH_EYE, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(
						/*
						 * biomes.getOrThrow(Biomes.BADLANDS), biomes.getOrThrow(Biomes.BAMBOO_JUNGLE),
						 * biomes.getOrThrow(Biomes.BEACH), biomes.getOrThrow(Biomes.BIRCH_FOREST),
						 * biomes.getOrThrow(Biomes.CHERRY_GROVE),
						 * biomes.getOrThrow(Biomes.DARK_FOREST), biomes.getOrThrow(Biomes.DESERT),
						 * biomes.getOrThrow(Biomes.ERODED_BADLANDS),
						 * biomes.getOrThrow(Biomes.FLOWER_FOREST), biomes.getOrThrow(Biomes.FOREST),
						 * biomes.getOrThrow(Biomes.FROZEN_PEAKS), biomes.getOrThrow(Biomes.GROVE),
						 * biomes.getOrThrow(Biomes.ICE_SPIKES), biomes.getOrThrow(Biomes.JAGGED_PEAKS),
						 * biomes.getOrThrow(Biomes.JUNGLE), biomes.getOrThrow(Biomes.MANGROVE_SWAMP),
						 * biomes.getOrThrow(Biomes.MEADOW),
						 * biomes.getOrThrow(Biomes.OLD_GROWTH_BIRCH_FOREST),
						 * biomes.getOrThrow(Biomes.OLD_GROWTH_PINE_TAIGA),
						 * biomes.getOrThrow(Biomes.OLD_GROWTH_SPRUCE_TAIGA),
						 */
                		biomes.getOrThrow(Biomes.PLAINS)),
                		/*biomes.getOrThrow(Biomes.SAVANNA),
                		biomes.getOrThrow(Biomes.SAVANNA_PLATEAU),
                		biomes.getOrThrow(Biomes.SNOWY_BEACH),
                		biomes.getOrThrow(Biomes.SNOWY_PLAINS),
                		biomes.getOrThrow(Biomes.SNOWY_SLOPES),
                		biomes.getOrThrow(Biomes.SNOWY_TAIGA),
                		biomes.getOrThrow(Biomes.SPARSE_JUNGLE),
                		biomes.getOrThrow(Biomes.STONY_PEAKS),
                		biomes.getOrThrow(Biomes.STONY_SHORE),
                		biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS),
                		biomes.getOrThrow(Biomes.SWAMP),
                		biomes.getOrThrow(Biomes.TAIGA),
                		biomes.getOrThrow(Biomes.WINDSWEPT_FOREST),
                		biomes.getOrThrow(Biomes.WINDSWEPT_GRAVELLY_HILLS),
                		biomes.getOrThrow(Biomes.WINDSWEPT_HILLS),
                		biomes.getOrThrow(Biomes.WINDSWEPT_SAVANNA),
                		biomes.getOrThrow(Biomes.WOODED_BADLANDS)),*/
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.ENDERSENT_WITH_EYE.get(), 25, 1, 1))));

    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, name));
    }
}