package net.je.common.entity.ai;

import net.je.common.block.ModBlocks;
import net.je.common.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.PathfindingContext;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;


public class ShadowMobNodeEvaluator extends WalkNodeEvaluator {
	@Override
	public PathType getPathType(PathfindingContext ctx, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		BlockState state = ctx.level().getBlockState(pos);

		if (state.is(ModBlocks.SHADOW_BLOCK.get()) || state.is(ModBlocks.ECLIPSED_SHADOW_BLOCK.get())) {
			return PathType.OPEN;
		}
		return super.getPathType(ctx, x, y, z);
	}
}
