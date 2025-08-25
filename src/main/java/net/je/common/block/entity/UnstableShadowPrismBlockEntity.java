package net.je.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class UnstableShadowPrismBlockEntity extends ShadowPrismBlockEntity {

	public UnstableShadowPrismBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(ModBlockEntities.UNSTABLE_SHADOW_PRISM_BLOCK_ENTITY.get(), pPos, pBlockState);
	}

	public static void tick(Level level, BlockPos pos, BlockState state, UnstableShadowPrismBlockEntity be) {

	}

}
