package net.je.block.custom;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;

public class JEGrassBlock extends Block {

	public JEGrassBlock (BlockBehaviour.Properties p_55057_) {
        super(p_55057_);
    }

	public static final MapCodec<JEGrassBlock> CODEC = simpleCodec(JEGrassBlock::new);

    @Override
    public MapCodec<JEGrassBlock> codec() {
        return CODEC;
    }

    private static boolean canBeGrowing(BlockState pState, LevelReader pReader, BlockPos pPos) {
        BlockPos blockpos = pPos.above();
        BlockState blockstate = pReader.getBlockState(blockpos);
        int i = LightEngine.getLightBlockInto(pReader, pState, pPos, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(pReader, blockpos));
        return i < pReader.getMaxLightLevel();
    }

    @Override
    protected void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!canBeGrowing(pState, pLevel, pPos)) {
            pLevel.setBlockAndUpdate(pPos, Blocks.END_STONE.defaultBlockState());
        }
    }
}
