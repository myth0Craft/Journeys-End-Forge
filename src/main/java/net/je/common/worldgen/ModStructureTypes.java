package net.je.common.worldgen;

import net.je.JourneysEnd;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModStructureTypes {
	public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES =
			DeferredRegister.create(Registries.STRUCTURE_TYPE, JourneysEnd.MODID);

	public static final RegistryObject<StructureType<ShadowTowerStructure>> SHADOW_TOWER =
			STRUCTURE_TYPES.register("shadow_tower", () -> () -> ShadowTowerStructure.CODEC);


	public static void register(IEventBus eventBus) {
		STRUCTURE_TYPES.register(eventBus);
	}
}
