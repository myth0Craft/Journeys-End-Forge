package net.je.block.entity;

import net.je.JourneysEnd;
import net.je.block.ModBlocks;
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

	public static void register(IEventBus eventBus) {
		BLOCK_ENTITIES.register(eventBus);
	}
}