package net.je.block.custom;

import org.joml.Random;

import com.mojang.serialization.MapCodec;

import net.je.block.ModBlocks;
import net.je.block.entity.EndStoneFurnaceBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class InterdimensionalAnchorBlock extends Block {
	
	Level blockLevel;
	
	public static final IntegerProperty DIMENSION = IntegerProperty.create("dim", 0, 2);

	public static final MapCodec<InterdimensionalAnchorBlock> CODEC = simpleCodec(InterdimensionalAnchorBlock::new);

	@Override
	public MapCodec<InterdimensionalAnchorBlock> codec() {
		return CODEC;
	}

	@Override
	protected RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}

	public InterdimensionalAnchorBlock(Properties p_49795_) {
		super(p_49795_);
		this.registerDefaultState(this.defaultBlockState().setValue(DIMENSION, 0));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(DIMENSION);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		Level level = pContext.getLevel();
		blockLevel = level;

		if (level.dimension() == Level.OVERWORLD) {
			return this.stateDefinition.any().setValue(DIMENSION, 1);
		} else if (level.dimension() == Level.END) {
			return this.stateDefinition.any().setValue(DIMENSION, 2);
		} else {
			return this.stateDefinition.any().setValue(DIMENSION, 0);
		}
	}

	@Override
	protected void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
		super.randomTick(pState, pLevel, pPos, pRandom);
		if (blockLevel != null) {
			
			RandomSource random = RandomSource.create();
			
			if (random.nextIntBetweenInclusive(1, 4) == 1) {
				
				if (blockLevel.dimension() == Level.OVERWORLD) {
					
					spreadBlock(Blocks.OBSIDIAN, Blocks.CRYING_OBSIDIAN, pLevel, pPos, pRandom);

					spreadBlock(ModBlocks.CORRUPTION.get(), Blocks.AIR, pLevel, pPos, pRandom);

					spreadBlock(ModBlocks.SOLID_CORRUPTION.get(), ModBlocks.CORRUPTION.get(), pLevel, pPos, pRandom);

					spreadBlock(ModBlocks.VOID_STONE.get(), ModBlocks.SOLID_CORRUPTION.get(), pLevel, pPos, pRandom);
					spreadBlock(ModBlocks.FADED_END_STONE.get(), ModBlocks.SOLID_CORRUPTION.get(), pLevel, pPos, pRandom);

					spreadBlock(ModBlocks.CORRUPTED_DIRT.get(), ModBlocks.VOID_STONE.get(), pLevel, pPos, pRandom);
					spreadBlock(Blocks.END_STONE, ModBlocks.FADED_END_STONE.get(), pLevel, pPos, pRandom);

					spreadBlock(Blocks.GRASS_BLOCK, ModBlocks.CORRUPTED_DIRT.get(), pLevel, pPos, pRandom);
					spreadBlock(Blocks.DIRT, ModBlocks.CORRUPTED_DIRT.get(), pLevel, pPos, pRandom);
					spreadBlock(Blocks.STONE, Blocks.END_STONE, pLevel, pPos, pRandom);
					
				} else if (blockLevel.dimension() == Level.END) {
					
					spreadBlock(Blocks.CRYING_OBSIDIAN, Blocks.OBSIDIAN, pLevel, pPos, pRandom);

					spreadBlock(ModBlocks.CORRUPTED_DIRT.get(), Blocks.GRASS_BLOCK, pLevel, pPos, pRandom);

					spreadBlock(ModBlocks.CORRUPTION.get(), ModBlocks.SOLID_CORRUPTION.get(), pLevel, pPos, pRandom);

					spreadBlock(ModBlocks.SOLID_CORRUPTION.get(), ModBlocks.VOID_STONE.get(), pLevel, pPos, pRandom);

					spreadBlock(ModBlocks.VOID_STONE.get(), ModBlocks.CORRUPTED_DIRT.get(), pLevel, pPos, pRandom);

					spreadBlock(ModBlocks.FADED_END_STONE.get(), Blocks.END_STONE, pLevel, pPos, pRandom);

					spreadBlock(Blocks.END_STONE, ModBlocks.CORRUPTED_DIRT.get(), pLevel, pPos, pRandom);
				}
			}
		}
	}

	protected void spreadBlock(Block blockToReplace, Block blockToSpawn, ServerLevel pLevel, BlockPos pPos,
			RandomSource pRandom) {
		// RandomSource pRandom = RandomSource.create();
		if (!pLevel.isAreaLoaded(pPos, 3))
			return;
		for (int i = 0; i < 4; i++) {
			BlockPos blockpos = pPos.offset(pRandom.nextInt(3) - 1, pRandom.nextInt(5) - 3, pRandom.nextInt(3) - 1);
			if (pLevel.getBlockState(blockpos).is(blockToReplace)) {
				pLevel.setBlockAndUpdate(blockpos, blockToSpawn.defaultBlockState());
			}
		}
	}
}
