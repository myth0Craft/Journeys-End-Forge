package net.je.block;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import net.je.JourneysEnd;
import net.je.block.custom.EndStoneFurnaceBlock;
import net.je.fluid.ModFluids;
import net.je.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
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
							.strength(100.0F).pushReaction(PushReaction.DESTROY).liquid().noLootTable().lightLevel(p_50755_ -> 7)
							.sound(SoundType.SCULK_SHRIEKER).emissiveRendering(ModBlocks::always)));

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
					.instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().sound(SoundType.METAL)));
	

	private static boolean always(BlockState p_50775_, BlockGetter p_50776_, BlockPos p_50777_) {
		return true;
	}

	private static boolean never(BlockState p_50806_, BlockGetter p_50807_, BlockPos p_50808_) {
		return false;
	}

	private static ToIntFunction<BlockState> litBlockEmission(int p_50760_) {
		return p_50763_ -> p_50763_.getValue(BlockStateProperties.LIT) ? p_50760_ : 0;
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

}
