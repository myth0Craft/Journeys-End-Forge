package net.je.common.block;

import java.util.List;
import java.util.function.Supplier;

import net.je.JourneysEnd;
import net.je.common.block.custom.*;
import net.je.common.fluid.ModFluids;
import net.je.common.item.ModItems;
import net.je.common.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			JourneysEnd.MODID);

	public static final RegistryObject<LiquidBlock> VOIDBLIGHT = registerBlock("source_voidblight",
			() -> new LiquidBlock(ModFluids.SOURCE_VOIDBLIGHT,
					BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_MAGENTA).replaceable().noCollission()
							.strength(100.0F).pushReaction(PushReaction.DESTROY).liquid().noLootTable()
							.lightLevel(p_50755_ -> 7).sound(SoundType.SCULK_SHRIEKER)
							.emissiveRendering(ModBlocks::always)));

	public static final RegistryObject<Block> VOIDMASS = registerBlock("voidmass",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_MAGENTA).strength(1.5F)
					.lightLevel(p_50755_ -> 7).emissiveRendering(ModBlocks::always).sound(SoundType.SCULK_SHRIEKER)));

	public static final RegistryObject<Block> END_STONE_FURNACE = registerBlock("end_stone_furnace",
			() -> new EndStoneFurnaceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
					.strength(50F, 1200F).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> COMPRESSED_END_STONE = registerBlock("compressed_end_stone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).strength(50F, 1200F)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> VOIDMETAL_BLOCK = registerBlock("voidmetal_block",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(5.0F, 6.0F)
					.instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops()
					.sound(SoundType.METAL)));

	public static final RegistryObject<Block> END_STONE_PILLAR = registerBlock("end_stone_pillar",
			() -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(3.0F, 9.0F)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> CHISELED_END_STONE = registerBlock("chiseled_end_stone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(3.0F, 9.0F)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> END_STONE_TILES = registerBlock("end_stone_tiles",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(3.0F, 9.0F)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> POLISHED_END_STONE = registerBlock("polished_end_stone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(3.0F, 9.0F)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> POLISHED_END_STONE_STAIRS = registerBlock("polished_end_stone_stairs",
			() -> new StairBlock(ModBlocks.POLISHED_END_STONE.get().defaultBlockState(),
					BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(3.0F, 9.0F)
							.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_END_STONE_SLAB = registerBlock("polished_end_stone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(3.0F, 9.0F)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_END_STONE_WALL = registerBlock("polished_end_stone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(3.0F, 9.0F)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> VOID_STONE = registerBlock("void_stone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(1.5F, 6.0F)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> LUSH_END_STONE = registerBlock("lush_end_stone",
			() -> new LushEndStoneBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE)
					.strength(3.0F, 9.0F).requiresCorrectToolForDrops().sound(SoundType.NYLIUM).randomTicks()) {
				@Override
				public void appendHoverText(ItemStack pStack, TooltipContext pContext,
						List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
					pTooltipComponents.add(Component.translatable("tooltip.je.unobtainable"));
					super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
				}
			});

	public static final RegistryObject<Block> FADED_END_STONE = registerBlock("faded_end_stone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).strength(3.0F, 9.0F)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().sound(SoundType.CALCITE)));

	public static final RegistryObject<Block> FADED_END_STONE_BRICKS = registerBlock("faded_end_stone_bricks",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).strength(3.0F, 9.0F)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().sound(SoundType.CALCITE)));

	public static final RegistryObject<Block> CORRUPTED_DIRT = registerBlock("corrupted_dirt", () -> new Block(
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).strength(0.5F).sound(SoundType.GRAVEL)));

	public static final RegistryObject<Block> LANTERN_OF_WARDING = registerBlock("lantern_of_warding",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).strength(0.5F)
					.requiresCorrectToolForDrops().sound(SoundType.COPPER_BULB).lightLevel(p_50755_ -> 15)
					.emissiveRendering(ModBlocks::always).hasPostProcess(ModBlocks::always)) {
				@Override
				public void appendHoverText(ItemStack pStack, TooltipContext pContext,
						List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
					pTooltipComponents.add(Component.translatable("tooltip.je.unobtainable"));
					super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
				}
			});

	public static final RegistryObject<Block> INTERDIMENSIONAL_ANCHOR = registerBlock("interdimensional_anchor",
			() -> new InterdimensionalAnchorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE)
					.strength(3.0F, 9.0F).requiresCorrectToolForDrops().sound(ModSounds.INTERDIMENSIONAL_ANCHOR_SOUNDS)
					.randomTicks()));

	public static final RegistryObject<Block> ENDER_VAULT = registerBlock("ender_vault",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).strength(50.0F, 1200.0F)
					.requiresCorrectToolForDrops().sound(SoundType.ANVIL).noLootTable().lightLevel(p_50755_ -> 15)) {
				@Override
				public void appendHoverText(ItemStack pStack, TooltipContext pContext,
						List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
					pTooltipComponents.add(Component.translatable("tooltip.je.unobtainable"));
					super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
				}
			});

	public static final RegistryObject<Block> VOIDBLOOM = registerBlock("voidbloom",
			() -> new VoidbloomBlock(BlockBehaviour.Properties.of().noCollission().instabreak()
					.sound(SoundType.SCULK_SHRIEKER).pushReaction(PushReaction.DESTROY)
					.emissiveRendering(ModBlocks::always).lightLevel(p_50755_ -> 5)));

	public static final RegistryObject<Block> BEJEWELED_PEDESTAL = registerBlock("bejeweled_pedestal",
			() -> new BejeweledPedestalBlock(
					BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(50.0F, 1200.0F).noLootTable()
							.sound(SoundType.STONE).noLootTable().pushReaction(PushReaction.IGNORE)));
	
	public static final RegistryObject<Block> SHADOW_STONE = registerBlock("shadow_stone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(3.0F, 9.0F)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> CORRUPTED_SHADOW_STONE = registerBlock("corrupted_shadow_stone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(3.0F, 9.0F)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> BLIGHTED_SHADOW_STONE = registerBlock("blighted_shadow_stone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(1.5F, 4.5F)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().sound(SoundType.SCULK_SHRIEKER)));

	public static final RegistryObject<Block> SHADOW_STONE_STAIRS = registerBlock("shadow_stone_stairs",
			() -> new StairBlock(ModBlocks.SHADOW_STONE.get().defaultBlockState(),
					BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(3.0F, 9.0F)
							.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> SHADOW_STONE_SLAB = registerBlock("shadow_stone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(3.0F, 9.0F)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> SHADOW_STONE_WALL = registerBlock("shadow_stone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(3.0F, 9.0F)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> CHISELED_SHADOW_STONE = registerBlock("chiseled_shadow_stone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(3.0F, 9.0F)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> SHADOW_STONE_BRICKS = registerBlock("shadow_stone_bricks",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(3.0F, 9.0F)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> CRACKED_SHADOW_STONE_BRICKS = registerBlock("cracked_shadow_stone_bricks",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(3.0F, 9.0F)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> SHADOW_STONE_BRICK_STAIRS = registerBlock("shadow_stone_brick_stairs",
			() -> new StairBlock(ModBlocks.SHADOW_STONE.get().defaultBlockState(),
					BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(3.0F, 9.0F)
							.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> SHADOW_STONE_BRICK_SLAB = registerBlock("shadow_stone_brick_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(3.0F, 9.0F)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> SHADOW_STONE_BRICK_WALL = registerBlock("shadow_stone_brick_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(3.0F, 9.0F)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> POLISHED_SHADOW_STONE = registerBlock("polished_shadow_stone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(3.0F, 9.0F)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> POLISHED_SHADOW_STONE_STAIRS = registerBlock("polished_shadow_stone_stairs",
			() -> new StairBlock(ModBlocks.SHADOW_STONE.get().defaultBlockState(),
					BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(3.0F, 9.0F)
							.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_SHADOW_STONE_SLAB = registerBlock("polished_shadow_stone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(3.0F, 9.0F)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_SHADOW_STONE_WALL = registerBlock("polished_shadow_stone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(3.0F, 9.0F)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> SHADOW_BLOCK = registerBlock("shadow_block",
			() -> new ShadowBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(-1.0F, 3600000.0F)
					.noLootTable().noOcclusion()
					.isRedstoneConductor(ModBlocks::never).isSuffocating(ModBlocks::never).isViewBlocking((s, l, p) -> false)
					.pushReaction(PushReaction.IGNORE)));

	public static final RegistryObject<Block> ECLIPSED_SHADOW_BLOCK = registerBlock("eclipsed_shadow_block",
			() -> new InvertedShadowBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(-1.0F, 3600000.0F)
					.noLootTable().noOcclusion()
					.isRedstoneConductor(ModBlocks::never).isSuffocating(ModBlocks::never).isViewBlocking((s, l, p) -> false)
					.pushReaction(PushReaction.IGNORE)));


	public static final RegistryObject<Block> SHADOW_PRISM = registerBlock("shadow_prism",
			() -> new ShadowPrismBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(-1.0F, 3600000.0F)
					.noLootTable().noOcclusion().lightLevel(p_50755_ -> 15)
					.isRedstoneConductor(ModBlocks::never).isSuffocating(ModBlocks::never).pushReaction(PushReaction.IGNORE)));

	public static final RegistryObject<Block> WARDED_SHADOW_STONE = registerBlock("warded_shadow_stone",
			WardedBlock::new);

	public static final RegistryObject<Block> WARDED_SHADOW_STONE_BRICKS = registerBlock("warded_shadow_stone_bricks",
			WardedBlock::new);

	public static final RegistryObject<Block> WARDED_CRACKED_SHADOW_STONE_BRICKS = registerBlock("warded_cracked_shadow_stone_bricks",
			WardedBlock::new);

	public static final RegistryObject<Block> WARDED_POLISHED_SHADOW_STONE = registerBlock("warded_polished_shadow_stone",
			WardedBlock::new);

	public static final RegistryObject<Block> WARDED_CORRUPTED_SHADOW_STONE = registerBlock("warded_corrupted_shadow_stone",
			WardedBlock::new);

	public static final RegistryObject<Block> WARDED_FADED_END_STONE_BRICKS = registerBlock("warded_faded_end_stone_bricks",
			() -> new WardedBlock(BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).sound(SoundType.CALCITE)));

	public static final RegistryObject<Block> WARDED_BLIGHTED_SHADOW_STONE = registerBlock("warded_blighted_shadow_stone",
			() -> new WardedBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().sound(SoundType.SCULK).lightLevel(p -> 4)));

	public static final RegistryObject<Block> WARDED_CHISELED_SHADOW_STONE = registerBlock("warded_chiseled_shadow_stone",
			WardedBlock::new);

	public static final RegistryObject<Block> VOID_LANTERN = registerBlock(
			"void_lantern",
			() -> new Block(
					BlockBehaviour.Properties.of()
							.instrument(NoteBlockInstrument.HAT)
							.strength(0.3F)
							.sound(SoundType.GLASS)
							.noOcclusion()
							.isValidSpawn(ModBlocks::never)
							.isRedstoneConductor(ModBlocks::never)
							.isSuffocating(ModBlocks::never)
							.isViewBlocking(ModBlocks::never)
							.lightLevel(l -> 15)
			)
	);

	public static final RegistryObject<Block> VOIDGLASS = registerBlock(
			"voidglass",
			() -> new CustomTransparentBlock(
					BlockBehaviour.Properties.of()
							.instrument(NoteBlockInstrument.HAT)
							.strength(0.3F)
							.sound(SoundType.GLASS)
							.noOcclusion()
							.isValidSpawn(ModBlocks::never)
							.isRedstoneConductor(ModBlocks::never)
							.isSuffocating(ModBlocks::never)
							.isViewBlocking(ModBlocks::never)
			)
	);

	public static final RegistryObject<Block> WARDED_VOIDGLASS = registerBlock(
			"warded_voidglass",
			() -> new TransparentWardedBlock(
					BlockBehaviour.Properties.of()
							.instrument(NoteBlockInstrument.HAT)
							.strength(0.3F)
							.sound(SoundType.GLASS)
							.noOcclusion()
							.isValidSpawn(ModBlocks::never)
							.isRedstoneConductor(ModBlocks::never)
							.isSuffocating(ModBlocks::never)
							.isViewBlocking(ModBlocks::never)
							.mapColor(MapColor.COLOR_LIGHT_GRAY)
			)
	);


	public static final RegistryObject<Block> GRAVITY_DISTORTER = registerBlock("gravity_distorter",
			() -> new GravityDistorterBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(1.5F, 6.0F)
					.instrument(NoteBlockInstrument.PLING).requiresCorrectToolForDrops().sound(ModSounds.INTERDIMENSIONAL_ANCHOR_SOUNDS)));

	public static final RegistryObject<Block> RESPAWN_NEXUS = registerBlock("respawn_nexus",
			() -> new RespawnNexusBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK)
					.instrument(NoteBlockInstrument.PLING).requiresCorrectToolForDrops()
					.strength(50.0F, 1200.0F)));





	private static boolean always(BlockState p_50775_, BlockGetter p_50776_, BlockPos p_50777_) {
		return true;
	}

	private static boolean never(BlockState p_50806_, BlockGetter p_50807_, BlockPos p_50808_) {
		return false;
	}

	private static Boolean never(BlockState p_50779_, BlockGetter p_50780_, BlockPos p_50781_, EntityType<?> p_50782_) {
		return false;
	}

	/*
	 * private static ToIntFunction<BlockState> litBlockEmission(int pLightValue) {
	 * return p_50763_ -> p_50763_.getValue(BlockStateProperties.LIT) ? pLightValue
	 * : 0; }
	 */

	private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
		RegistryObject<T> toReturn = BLOCKS.register(name, block);
		registerBlockItem(name, toReturn);
		return toReturn;
	}

	private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
		return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
	}

	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
	}
}
