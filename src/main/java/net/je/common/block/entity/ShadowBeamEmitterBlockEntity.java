package net.je.common.block.entity;

import net.je.common.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class ShadowBeamEmitterBlockEntity extends BlockEntity {

	private boolean isActive = false;
	private int beamHeight = 0;
	public int laserTicks = 0;
	public int laserCooldownTicks = 0;
	private AABB laserDamageArea;

	public ShadowBeamEmitterBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(ModBlockEntities.SHADOW_BEAM_EMITTER_BLOCK_ENTITY.get(), pPos, pBlockState);
	}

	public void checkForReceiver() {
		boolean activeTest = false;
		for (int i = 1; i <= 10; i++) {
			BlockState state = level.getBlockState(worldPosition.above(i));
			if (state.is(ModBlocks.SHADOW_BEAM_RECEIVER.get())) {
				if (i == 1) {
					break;
				}
				activeTest = true;
				beamHeight = i - 1;
				laserDamageArea = new AABB(worldPosition)
						.expandTowards(0, i - 1, 0);
				break;
			}
			if (state.is(ModBlocks.SHADOW_BEAM_EMITTER.get())) {
				break;
			}
		}
		isActive = activeTest;
		setChanged();
		level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
	}

	@Override
	public void onLoad() {
		checkForReceiver();
	}

	public static void tick(Level level, BlockPos pos, BlockState state, ShadowBeamEmitterBlockEntity be) {
		if (!level.isClientSide()) {
			be.checkForReceiver();
			if (be.isActive()) {
				if (be.laserCooldownTicks == 0) {
					be.laserTicks = 40;
					be.laserCooldownTicks = 20;
				} else if (!(be.laserTicks > 0)) {
					be.laserCooldownTicks--;
				}
				if (be.laserTicks > 0) {
					be.damageEntitiesInBeam(be);
					be.laserTicks--;
				}
			} else {
				be.laserTicks = 0;
				be.laserCooldownTicks = 0;
			}
		}
	}

	public void damageEntitiesInBeam(ShadowBeamEmitterBlockEntity be) {
		if (level.getGameTime() % 2 == 0) {

			List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, be.laserDamageArea);

			for (LivingEntity entity : entities) {
				if (entity instanceof ServerPlayer player) {
					if (!player.isCreative()) {
						Vec3 movement = player.getDeltaMovement();

						player.hurt(level.damageSources().magic(), 1);

						player.invulnerableTime = 0;

						player.hurtMarked = false;

						player.setDeltaMovement(movement);
					}
				} else {
					Vec3 movement = entity.getDeltaMovement();

					entity.hurt(level.damageSources().magic(), 1);
					entity.invulnerableTime = 0;

					entity.hurtMarked = false;

					entity.setDeltaMovement(movement);
				}
			}
		}
	}


	public boolean isActive() {
		return isActive;
	}

	public int getBeamHeight() {
		return beamHeight;
	}

	@Override
	public Packet<ClientGamePacketListener> getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.saveAdditional(tag, registries);
		tag.putBoolean("Active", this.isActive);
		tag.putInt("BeamHeight", this.beamHeight);
	}

	@Override
	public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.loadAdditional(tag, registries);
		this.isActive = tag.getBoolean("Active");
		this.beamHeight = tag.getInt("BeamHeight");

	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
		CompoundTag tag = super.getUpdateTag(registries);
		tag.putBoolean("Active", this.isActive);
		tag.putInt("BeamHeight", this.beamHeight);
		return tag;
	}
}

