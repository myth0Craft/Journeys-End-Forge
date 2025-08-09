package net.je.worldgen;

import net.je.JourneysEnd;
import net.je.util.ModTags;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Map;

public class ModStructures {
	public static final DeferredRegister<Structure> STRUCTURES = DeferredRegister.create(Registries.STRUCTURE,
			JourneysEnd.MODID);

	/*
	 * public static final RegistryObject<Structure> ENDERSENT_WELL =
	 * STRUCTURES.register("endersent_well", () -> new JigsawStructure( new
	 * Structure.StructureSettings(HolderSet.empty(), Map.of(),
	 * GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.NONE),
	 * Holder.Reference < T > Optional.empty(), 1, ConstantHeight.ZERO, false));
	 */

	/*
	 * public static final ResourceKey<Structure> ENDERSENT_WELL =
	 * ResourceKey.create( Registries.STRUCTURE,
	 * ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "endersent_well") );
	 */

	/*public static final RegistryObject<Structure> SHADOW_TOWER = STRUCTURES.register("shadow_tower", () ->
			new ShadowTowerStructure(new Structure.StructureSettings(
					HolderSet.direct(

					),
					Map.of(),
					GenerationStep.Decoration.SURFACE_STRUCTURES,
					TerrainAdjustment.NONE
			))
	);*/

	public static void register(IEventBus eventBus) {
		STRUCTURES.register(eventBus);
	}



}