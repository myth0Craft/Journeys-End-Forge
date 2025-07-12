package net.je.worldgen;

import net.je.JourneysEnd;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifiers {

	public static final ResourceKey<BiomeModifier> SPAWN_ENDERSENT = registerKey("spawn_endersent");
	public static final ResourceKey<BiomeModifier> SPAWN_ENDERSENT_WITH_EYE = registerKey("spawn_endersent_with_eye");


	@SuppressWarnings("unused")
	public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeature = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);
	}

	private static ResourceKey<BiomeModifier> registerKey(String name) {
		return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS,
				ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, name));
	}
}