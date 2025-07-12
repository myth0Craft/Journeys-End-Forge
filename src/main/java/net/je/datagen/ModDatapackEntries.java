package net.je.datagen;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import net.je.JourneysEnd;
import net.je.trim.ModTrimMaterials;
import net.je.worldgen.ModBiomeModifiers;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

public class ModDatapackEntries extends DatapackBuiltinEntriesProvider {
	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.TRIM_MATERIAL, ModTrimMaterials::bootstrap)
			.add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap);

	public ModDatapackEntries(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries, BUILDER, Set.of(JourneysEnd.MODID));
	}
}