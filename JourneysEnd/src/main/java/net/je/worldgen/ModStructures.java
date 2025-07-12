package net.je.worldgen;

import net.je.JourneysEnd;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.registries.DeferredRegister;

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



}