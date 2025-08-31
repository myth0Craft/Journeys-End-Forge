package net.je.common.block.entity;

import net.je.common.block.ModBlocks;
import net.je.common.block.custom.EnderVaultBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class EnderVaultBlockEntity extends BlockEntity {
	private int delayBetweenWaves = 0;
	private final int DELAY_AMOUNT = 20;
	public boolean finished = false;


	public EnderVaultBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(ModBlockEntities.ENDER_VAULT_BLOCK_ENTITY.get(), pPos, pBlockState);
	}


	public static void tick(Level pLevel, BlockPos pPos, BlockState pState, EnderVaultBlockEntity pBlockEntity) {
		if (!pLevel.isClientSide()) {
			if (pBlockEntity.delayBetweenWaves <= 0) {
				pBlockEntity.updateActive(pLevel, pPos);
				if (pBlockEntity.isActive()) {
						if (pBlockEntity.getCurrentWave() == 4) {
							pBlockEntity.finished = true;
							pBlockEntity.updateActive(pLevel, pPos);
							pBlockEntity.setChanged();
							pLevel.sendBlockUpdated(pBlockEntity.worldPosition, pBlockEntity.getBlockState(), pBlockEntity.getBlockState(), Block.UPDATE_ALL);
						} else if (pBlockEntity.getCurrentWave() == 3) {
							pBlockEntity.wave4();
						} else if (pBlockEntity.getCurrentWave() == 2) {
							pBlockEntity.wave3();
						} else if (pBlockEntity.getCurrentWave() == 1) {
							pBlockEntity.wave2();
						} else {
							pBlockEntity.wave1();
					}
				}
			} else {
				pBlockEntity.delayBetweenWaves--;
			}
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

	public int getCurrentWave() {
		return this.getBlockState().getValue(EnderVaultBlock.WAVES_COMPLETE);
	}

	public void setCurrentWave(int num) {
		if (this.level != null) {
			level.setBlockAndUpdate(worldPosition, ModBlocks.ENDER_VAULT.get().defaultBlockState()
					.setValue(EnderVaultBlock.ACTIVE, this.getBlockState().getValue(EnderVaultBlock.ACTIVE))
					.setValue(EnderVaultBlock.WAVES_COMPLETE, Math.clamp(num, 0, 4)));
		}
	}

	public void wave1() {
		//wave1Complete = true;
		setCurrentWave(1);
		updateWavesComplete();
		delayBetweenWaves = DELAY_AMOUNT;
	}

	public void wave2() {
		setCurrentWave(2);
		updateWavesComplete();
		delayBetweenWaves = DELAY_AMOUNT;
	}

	public void wave3() {
		setCurrentWave(3);
		updateWavesComplete();
		delayBetweenWaves = DELAY_AMOUNT;
	}

	public void wave4() {
		setCurrentWave(4);
		updateWavesComplete();
		delayBetweenWaves = DELAY_AMOUNT;
	}

	private void updateWavesComplete() {
		if (this.level != null) {
			level.setBlockAndUpdate(worldPosition, ModBlocks.ENDER_VAULT.get().defaultBlockState()
					.setValue(EnderVaultBlock.ACTIVE, this.getBlockState().getValue(EnderVaultBlock.ACTIVE))
					.setValue(EnderVaultBlock.WAVES_COMPLETE, getCurrentWave()));
		}
	}

	private void updateActive(Level pLevel, BlockPos pPos) {
		boolean playerNearby = checkForPlayers(pLevel, pPos);
		pLevel.setBlockAndUpdate(pPos, ModBlocks.ENDER_VAULT.get().defaultBlockState()
				.setValue(EnderVaultBlock.ACTIVE, playerNearby && !finished)
				.setValue(EnderVaultBlock.WAVES_COMPLETE, this.getBlockState().getValue(EnderVaultBlock.WAVES_COMPLETE)));
	}

	private boolean isActive() {
		return this.getBlockState().getValue(EnderVaultBlock.ACTIVE);
	}

	@Override
	public Packet<ClientGamePacketListener> getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.saveAdditional(tag, registries);
		tag.putBoolean("Finished", this.finished);
	}

	@Override
	public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.loadAdditional(tag, registries);
		this.finished = tag.getBoolean("Finished");
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
		CompoundTag tag = super.getUpdateTag(registries);
		tag.putBoolean("Finished", this.finished);
		return tag;
	}
}
