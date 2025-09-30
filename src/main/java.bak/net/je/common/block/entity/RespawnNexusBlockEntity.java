package net.je.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class RespawnNexusBlockEntity extends BlockEntity {
	public RespawnNexusBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(ModBlockEntities.RESPAWN_NEXUS_BLOCK_ENTITY.get(), pPos, pBlockState);
	}

	public boolean shouldRenderFace(Direction pFace) {
		return Block.shouldRenderFace(this.getBlockState(), this.level, this.getBlockPos(), pFace, this.getBlockPos().relative(pFace));
	}
}
