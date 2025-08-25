package net.je.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ShadowPrismBlockEntity extends BlockEntity {



	protected ShadowPrismBlockEntity(BlockEntityType<?> type, BlockPos pPos, BlockState pBlockState) {
		super(type, pPos, pBlockState);
	}

	public ShadowPrismBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(ModBlockEntities.SHADOW_PRISM_BLOCK_ENTITY.get(), pPos, pBlockState);
	}

	public boolean shouldRenderFace(Direction pFace) {
		return Block.shouldRenderFace(this.getBlockState(), this.level, this.getBlockPos(), pFace, this.getBlockPos().relative(pFace));
    }
}
