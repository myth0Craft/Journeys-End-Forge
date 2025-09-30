package net.je.common.block.entity;

import net.je.common.block.ModBlocks;
import net.je.common.block.custom.EnderVaultBlock;
import net.je.common.entity.ModEntities;
import net.je.common.entity.custom.EndersentWithEye;
import net.je.common.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class EnderVaultBlockEntity extends BlockEntity {
	private int delayBetweenWaves = 0;
	private static final int DELAY_AMOUNT = 120;
	public boolean finished = false;

	private static final int SPAWN_RANGE = 7;
	private static final float PLAYER_DETECTION_RANGE = 5f;

	public boolean shouldSpawnKey = false;


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
						if (pState.getValue(EnderVaultBlock.SHOULD_SPAWN_KEY) || pBlockEntity.shouldSpawnKey) {
							ItemStack key = new ItemStack(ModItems.SHADOW_KEY.get());
							DefaultDispenseItemBehavior.spawnItem(pLevel, key, 2, Direction.UP, Vec3.atBottomCenterOf(pPos).relative(Direction.UP, 1.2));
						}
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
			Player player = pLevel.getNearestPlayer(pPos.getX(), pPos.getY(), pPos.getZ(), PLAYER_DETECTION_RANGE, false);
			if (player == null || player.getY() < pPos.getY()) return false;
			//System.out.println("Player nearby at: " + player.getX() + ", " + player.getY() + ", " + player.getZ());
			return true;
		} else {
			return false;
		}
	}

	public int getCurrentWave() {
		return this.getBlockState().getValue(EnderVaultBlock.WAVES_COMPLETE);
	}

	public void setCurrentWave(int num) {
		if (this.level != null) {
			level.setBlockAndUpdate(worldPosition, ModBlocks.ENDER_VAULT.get().defaultBlockState()
					.setValue(EnderVaultBlock.ACTIVE, this.getBlockState().getValue(EnderVaultBlock.ACTIVE))
					.setValue(EnderVaultBlock.WAVES_COMPLETE, Math.clamp(num, 0, 4))
					.setValue(EnderVaultBlock.SHOULD_SPAWN_KEY, this.getBlockState().getValue(EnderVaultBlock.SHOULD_SPAWN_KEY)));
		}
	}

	public void wave1() {
		setCurrentWave(1);
		updateWavesComplete();
		delayBetweenWaves = DELAY_AMOUNT;
		if (level != null && canSpawnInLevel(level)) {
			spawnMobs(level, worldPosition, ModEntities.ECHO.get(), 3);
		}
	}

	public void wave2() {
		setCurrentWave(2);
		updateWavesComplete();
		delayBetweenWaves = DELAY_AMOUNT;
		if (level != null && canSpawnInLevel(level)) {
			spawnMobs(level, worldPosition, ModEntities.ECHO.get(), 3);
		}
	}

	public void wave3() {
		setCurrentWave(3);
		updateWavesComplete();
		delayBetweenWaves = DELAY_AMOUNT;
		if (level != null && canSpawnInLevel(level)) {
			spawnMobs(level, worldPosition, ModEntities.ECHO.get(), 3);
		}
	}

	public void wave4() {
		setCurrentWave(4);
		updateWavesComplete();
		delayBetweenWaves = DELAY_AMOUNT;
		if (level != null && canSpawnInLevel(level)) {
			spawnMobs(level, worldPosition, ModEntities.ECHO.get(), 3);
		}
	}

	public void spawnMobs(Level pLevel, BlockPos pPos, EntityType<?> type, int count) {
		for (int i = 0; i < count; i++) {
			BlockPos pos = findSpawnPos(pPos, pLevel, type);

			if (pos == null) {
				for (int attempt = 0; attempt < 3 && pos == null; attempt++) {
					pos = findSpawnPos(pPos, pLevel, type);
				}
			}
			if (pos == null) continue;

			Mob mob = (Mob) type.create(pLevel);
			if (mob != null) {
				mob.moveTo(
						pos.getX() + 0.5D,
						pos.getY(),
						pos.getZ() + 0.5D,
						pLevel.random.nextFloat() * 360F,
						0.0F
				);
				pLevel.addFreshEntity(mob);
			}
		}
	}

	private BlockPos findSpawnPos(BlockPos pPos, Level pLevel, EntityType<?> type) {
		RandomSource randomsource = pLevel.getRandom();
		int d0 = (int) (pPos.getX() + (randomsource.nextDouble() - randomsource.nextDouble()) * (double) SPAWN_RANGE + 0.5);
		int d1 = (pPos.getY() + randomsource.nextInt(3) - 1);
		int d2 = (int) (pPos.getZ() + (randomsource.nextDouble() - randomsource.nextDouble()) * (double) SPAWN_RANGE + 0.5);
		BlockPos pos = new BlockPos(d0, d1, d2);
		for (int i = 0; i < 5; i++) {
			BlockPos checkPos = pos.above(i);

			if (!pLevel.getBlockState(checkPos.below()).isSolid()) continue;

			if (!pLevel.isEmptyBlock(checkPos) || !pLevel.isEmptyBlock(checkPos.above())) continue;

			AABB box = type.getDimensions().makeBoundingBox(
					checkPos.getX() + 0.5D,
					checkPos.getY(),
					checkPos.getZ() + 0.5D
			);


			if (pLevel.noCollision(box)) {
				return checkPos;
			}
		}

		return null;
	}

	private boolean canSpawnInLevel(Level pLevel) {
		return pLevel.getDifficulty() == Difficulty.PEACEFUL ? false : pLevel.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING);
	}

	private void updateWavesComplete() {
		if (this.level != null) {
			level.setBlockAndUpdate(worldPosition, ModBlocks.ENDER_VAULT.get().defaultBlockState()
					.setValue(EnderVaultBlock.ACTIVE, this.getBlockState().getValue(EnderVaultBlock.ACTIVE))
					.setValue(EnderVaultBlock.WAVES_COMPLETE, getCurrentWave())
					.setValue(EnderVaultBlock.SHOULD_SPAWN_KEY, this.getBlockState().getValue(EnderVaultBlock.SHOULD_SPAWN_KEY)));
		}
	}

	private void updateActive(Level pLevel, BlockPos pPos) {
		boolean playerNearby = checkForPlayers(pLevel, pPos);
		pLevel.setBlockAndUpdate(pPos, ModBlocks.ENDER_VAULT.get().defaultBlockState()
				.setValue(EnderVaultBlock.ACTIVE, playerNearby && !finished)
				.setValue(EnderVaultBlock.WAVES_COMPLETE, this.getBlockState().getValue(EnderVaultBlock.WAVES_COMPLETE))
				.setValue(EnderVaultBlock.SHOULD_SPAWN_KEY, this.getBlockState().getValue(EnderVaultBlock.SHOULD_SPAWN_KEY)));
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
		tag.putInt("delayBetweenWaves", this.delayBetweenWaves);
	}

	@Override
	public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.loadAdditional(tag, registries);
		this.finished = tag.getBoolean("Finished");
		this.delayBetweenWaves = tag.getInt("delayBetweenWaves");
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
		CompoundTag tag = super.getUpdateTag(registries);
		tag.putBoolean("Finished", this.finished);
		tag.putInt("delayBetweenWaves", this.delayBetweenWaves);
		return tag;
	}
}
