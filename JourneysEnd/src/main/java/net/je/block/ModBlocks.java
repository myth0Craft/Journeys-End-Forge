package net.je.block;

import java.util.List;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import net.je.JourneysEnd;
import net.je.block.custom.BejeweledPedestalBlock;
import net.je.block.custom.CorruptedGrowthBlock;
import net.je.block.custom.EndStoneFurnaceBlock;
import net.je.block.custom.InterdimensionalAnchorBlock;
//import net.je.block.custom.InterdimensionalAnchorBlock;
import net.je.block.custom.JEGrassBlock;
import net.je.block.custom.LushEndStoneBlock;
import net.je.fluid.ModFluids;
import net.je.item.ModItems;
import net.je.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
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

	public static final RegistryObject<LiquidBlock> CORRUPTION = registerBlock("source_corruption",
			() -> new LiquidBlock(ModFluids.SOURCE_CORRUPTION,
					BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_MAGENTA).replaceable().noCollission()
							.strength(100.0F).pushReaction(PushReaction.DESTROY).liquid().noLootTable()
							.lightLevel(p_50755_ -> 7).sound(SoundType.SCULK_SHRIEKER)
							.emissiveRendering(ModBlocks::always)));

	public static final RegistryObject<Block> SOLID_CORRUPTION = registerBlock("solid_corruption",
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
					.strength(3.0F, 9.0F).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()
					.sound(SoundType.NYLIUM).randomTicks()));

	public static final RegistryObject<Block> FADED_END_STONE = registerBlock("faded_end_stone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).strength(3.0F, 9.0F)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().sound(SoundType.CALCITE)));

	public static final RegistryObject<Block> CORRUPTED_DIRT = registerBlock("corrupted_dirt",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).strength(0.5F)
					.requiresCorrectToolForDrops().sound(SoundType.GRAVEL)) {
				@Override
				public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext,
						List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
					pTooltipComponents.add(Component.translatable("tooltip.je.corrupted_dirt"));
					super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
				}
			});
	
	public static final RegistryObject<Block> LANTERN_OF_WARDING = registerBlock("lantern_of_warding",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).strength(0.5F)
					.requiresCorrectToolForDrops().sound(SoundType.COPPER_BULB).lightLevel(p_50755_ -> 15).emissiveRendering(ModBlocks::always).hasPostProcess(ModBlocks::always)) {
				@Override
				public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext,
						List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
					pTooltipComponents.add(Component.translatable("tooltip.je.lantern_of_warding"));
					super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
				}
			});
	public static final RegistryObject<Block> INTERDIMENSIONAL_ANCHOR = registerBlock("interdimensional_anchor",
			() -> new InterdimensionalAnchorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE)
					.strength(3.0F, 9.0F).requiresCorrectToolForDrops()
					.sound(ModSounds.INTERDIMENSIONAL_ANCHOR_SOUNDS).randomTicks()));
	
	public static final RegistryObject<Block> ENDER_VAULT = registerBlock("ender_vault",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE)
					.strength(50.0F, 1200.0F).requiresCorrectToolForDrops()
					.sound(SoundType.ANVIL).noLootTable().lightLevel(p_50755_ -> 15)));
	
	public static final RegistryObject<Block> CORRUPTED_GROWTH = registerBlock("corrupted_growth",
			() -> new CorruptedGrowthBlock(BlockBehaviour.Properties.of()
					.noCollission()
	                .instabreak()
	                .sound(SoundType.SCULK_SHRIEKER)
	                .pushReaction(PushReaction.DESTROY)
	                .emissiveRendering(ModBlocks::always)
	                .lightLevel(p_50755_ -> 5)));
	
	public static final RegistryObject<Block> BEJEWELED_PEDESTAL = registerBlock("bejeweled_pedestal",
			() -> new BejeweledPedestalBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK)
					.strength(50.0F, 1200.0F).noLootTable()
					.sound(SoundType.STONE).noLootTable().pushReaction(PushReaction.IGNORE)));
	
	private static boolean always(BlockState p_50775_, BlockGetter p_50776_, BlockPos p_50777_) {
		return true;
	}

	private static boolean never(BlockState p_50806_, BlockGetter p_50807_, BlockPos p_50808_) {
		return false;
	}

	private static ToIntFunction<BlockState> litBlockEmission(int pLightValue) {
		return p_50763_ -> p_50763_.getValue(BlockStateProperties.LIT) ? pLightValue : 0;
	}

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
	
	public static final SoundType BAMBOO_SAPLING = new SoundType(
	        1.0F, 1.0F, SoundEvents.BAMBOO_SAPLING_BREAK, SoundEvents.BAMBOO_STEP, SoundEvents.BAMBOO_SAPLING_PLACE, SoundEvents.BAMBOO_SAPLING_HIT, SoundEvents.BAMBOO_FALL
	    );

}
