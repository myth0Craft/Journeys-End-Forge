package net.je.common.entity.ai;

import net.je.common.block.ModBlocks;
import net.je.common.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;


public class ShadowMobNodeEvaluator extends WalkNodeEvaluator {
	@Override
	public BlockPathTypes getBlockPathType(BlockGetter pLevel, int pX, int pY, int pZ, Mob pMob) {
		BlockPos pos = new BlockPos(pX, pY, pZ);
		BlockState state = pLevel.getBlockState(pos);

		if (state.is(ModBlocks.SHADOW_BLOCK.get()) || state.is(ModBlocks.ECLIPSED_SHADOW_BLOCK.get())) {
			return BlockPathTypes.OPEN;
		}
		return super.getBlockPathType(pLevel, pX, pY, pZ);
	}
}
