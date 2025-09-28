package net.je.common.worldgen;

import net.je.JourneysEnd;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModStructureProcessors {

	public static final DeferredRegister<StructureProcessorType<?>> STRUCTURE_PROCESSORS =
			DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, JourneysEnd.MODID);


	public static final RegistryObject<StructureProcessorType<WardedBlockProcessor>> WARDED_BLOCK_PROCESSOR =
			STRUCTURE_PROCESSORS.register("warded_block", () -> () -> WardedBlockProcessor.CODEC);


	public static void register(IEventBus eventBus) {
		STRUCTURE_PROCESSORS.register(eventBus);
	}
}
