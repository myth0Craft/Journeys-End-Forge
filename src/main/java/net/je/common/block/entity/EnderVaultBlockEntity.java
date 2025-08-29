package net.je.common.block.entity;

import net.je.common.block.ModBlocks;
import net.je.common.block.custom.EnderVaultBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class EnderVaultBlockEntity extends BlockEntity {
	public EnderVaultBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(ModBlockEntities.ENDER_VAULT_BLOCK_ENTITY.get(), pPos, pBlockState);
	}



	public static void tick(Level pLevel, BlockPos pPos, BlockState pState, EnderVaultBlockEntity pBlockEntity) {
		if (!pLevel.isClientSide()) {
			boolean playerNearby = checkForPlayers(pLevel, pPos);
			if (playerNearby) {
				pBlockEntity.wave1();
			}
			pLevel.setBlockAndUpdate(pPos, ModBlocks.ENDER_VAULT.get().defaultBlockState().setValue(EnderVaultBlock.ACTIVE, playerNearby)
					.setValue(EnderVaultBlock.WAVES_COMPLETE, pBlockEntity.getBlockState().getValue(EnderVaultBlock.WAVES_COMPLETE)));
		}

	}

	public static boolean checkForPlayers(Level pLevel, BlockPos pPos) {
		if (!pLevel.isClientSide()) {
			if (pLevel.hasNearbyAlivePlayer(pPos.getX(), pPos.getY(), pPos.getZ(), 5f)) {
				Player player = pLevel.getNearestPlayer(pPos.getX(), pPos.getY(), pPos.getZ(), 5f, false);
				if (player == null || player.getY() < pPos.getY()) return false;
				//System.out.println("Player nearby at: " + player.getX() + ", " + player.getY() + ", " + player.getZ());
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void spawnMob(LivingEntity pEntity) {

	}

	public void wave1() {

	}

	public void wave2() {

	}

	public void wave3() {

	}
}
