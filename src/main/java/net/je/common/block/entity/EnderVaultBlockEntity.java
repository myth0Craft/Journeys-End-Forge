package net.je.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class EnderVaultBlockEntity extends BlockEntity {
	public EnderVaultBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(ModBlockEntities.ENDER_VAULT_BLOCK_ENTITY.get(), pPos, pBlockState);
	}
}
