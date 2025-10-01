package net.je.common.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class TopWardedBlock extends WardedBlock {
	private final Direction minableDirection;

	public TopWardedBlock(Properties p_49795_, Direction pDirection) {
		super(p_49795_);
		this.minableDirection = pDirection;
	}



	@Override
	public void attack(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
		BlockHitResult hit = (BlockHitResult) pPlayer.pick(20, 0, false);
		if (hit.getBlockPos().equals(pPos) && hit.getDirection() == this.minableDirection) {
			super.defaultAttack(pState, pLevel, pPos, pPlayer);
		} else {
			super.attack(pState, pLevel, pPos, pPlayer);
		}
	}

	@Override
	public float getDestroyProgress(BlockState pState, Player pPlayer, BlockGetter pLevel, BlockPos pPos) {
		BlockHitResult hit = (BlockHitResult) pPlayer.pick(20, 0, false);
		if (hit.getBlockPos().equals(pPos) && hit.getDirection() == this.minableDirection) {
			return super.defaultDestroyProgress(pState, pPlayer, pLevel, pPos);
		} else {
			return super.getDestroyProgress(pState, pPlayer, pLevel, pPos);
		}
	}
}
