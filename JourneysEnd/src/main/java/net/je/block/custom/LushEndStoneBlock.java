package net.je.block.custom;

import net.je.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class LushEndStoneBlock extends JEGrassBlock {

	public LushEndStoneBlock(BlockBehaviour.Properties p_55057_) {
		super(p_55057_);
	}

	@Override
	protected void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
		super.randomTick(pState, pLevel, pPos, pRandom);
		if (!pLevel.isAreaLoaded(pPos, 3))
			return;
		BlockState blockstate = this.defaultBlockState();
		for (int i = 0; i < 4; i++) {
			BlockPos blockpos = pPos.offset(pRandom.nextInt(3) - 1, pRandom.nextInt(5) - 3, pRandom.nextInt(3) - 1);
			if (pLevel.getBlockState(blockpos).is(Blocks.END_STONE) && pLevel.getBlockState(blockpos.above()).is(Blocks.AIR)) {
				pLevel.setBlockAndUpdate(blockpos, blockstate);
			}
		}
	}
}
