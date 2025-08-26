package net.je.common.block.entity;

import net.je.JourneysEnd;
import net.je.common.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
			.create(ForgeRegistries.BLOCK_ENTITY_TYPES, JourneysEnd.MODID);

	public static final RegistryObject<BlockEntityType<EndStoneFurnaceBlockEntity>> END_STONE_FURNACE_BE = BLOCK_ENTITIES
			.register("end_stone_furnace_be", () -> BlockEntityType.Builder
					.of(EndStoneFurnaceBlockEntity::new, ModBlocks.END_STONE_FURNACE.get()).build(null));

	public static final RegistryObject<BlockEntityType<BejeweledPedestalBlockEntity>> BEJEWELED_PEDESTAL_BE = BLOCK_ENTITIES
			.register("bejeweled_pedestal_be", () -> BlockEntityType.Builder
					.of(BejeweledPedestalBlockEntity::new, ModBlocks.BEJEWELED_PEDESTAL.get()).build(null));

	public static final RegistryObject<BlockEntityType<ShadowPrismBlockEntity>> SHADOW_PRISM_BLOCK_ENTITY = BLOCK_ENTITIES
			.register("shadow_prism_block_entity", () -> BlockEntityType.Builder
					.of(ShadowPrismBlockEntity::new, ModBlocks.SHADOW_PRISM.get()).build(null));

	public static final RegistryObject<BlockEntityType<UnstableShadowPrismBlockEntity>> UNSTABLE_SHADOW_PRISM_BLOCK_ENTITY = BLOCK_ENTITIES
			.register("unstable_shadow_prism_block_entity", () -> BlockEntityType.Builder
					.of(UnstableShadowPrismBlockEntity::new, ModBlocks.UNSTABLE_SHADOW_PRISM.get()).build(null));

	public static final RegistryObject<BlockEntityType<GravityDistorterBlockEntity>> GRAVITY_DISTORTER_BLOCK_ENTITY = BLOCK_ENTITIES
			.register("gravity_distorter_block_entity", () -> BlockEntityType.Builder
					.of(GravityDistorterBlockEntity::new, ModBlocks.GRAVITY_DISTORTER.get()).build(null));

	public static final RegistryObject<BlockEntityType<RespawnNexusBlockEntity>> RESPAWN_NEXUS_BLOCK_ENTITY = BLOCK_ENTITIES
			.register("respawn_nexus_block_entity", () -> BlockEntityType.Builder
					.of(RespawnNexusBlockEntity::new, ModBlocks.RESPAWN_NEXUS.get()).build(null));

	public static final RegistryObject<BlockEntityType<ShadowBeamEmitterBlockEntity>> SHADOW_BEAM_EMITTER_BLOCK_ENTITY = BLOCK_ENTITIES
			.register("shadow_beam_emitter_block_entity", () -> BlockEntityType.Builder
					.of(ShadowBeamEmitterBlockEntity::new, ModBlocks.SHADOW_BEAM_EMITTER.get()).build(null));

	public static void register(IEventBus eventBus) {
		BLOCK_ENTITIES.register(eventBus);
	}
}