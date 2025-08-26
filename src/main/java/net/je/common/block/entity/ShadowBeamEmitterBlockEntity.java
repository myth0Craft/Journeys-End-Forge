package net.je.common.block.entity;

import net.je.JourneysEnd;
import net.je.common.block.ModBlocks;
import net.je.common.effect.ModDamageSources;
import net.je.common.effect.ModDamageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
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
	boolean laserActive = false;

	//private DamageSource shadowBeamSource;

	public ShadowBeamEmitterBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(ModBlockEntities.SHADOW_BEAM_EMITTER_BLOCK_ENTITY.get(), pPos, pBlockState);
	}

	public void checkForReceiver() {
			if (level == null) return;
			boolean found = false;
			int height = 0;
			for (int i = 1; i <= 10; i++) {
				BlockPos abovePos = worldPosition.above(i);
				BlockState state = level.getBlockState(abovePos);
				if (state.is(ModBlocks.SHADOW_BEAM_EMITTER.get())) {
					break;
				}
				if (state.is(ModBlocks.SHADOW_BEAM_RECEIVER.get())) {
					if (i > 1) {
						found = true;
						height = i - 1;

						laserDamageArea = new AABB(worldPosition)
								.expandTowards(0, height, 0);

						break;
					}
				}
			}
			isActive = found;
			beamHeight = height;
			setChanged();
			level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);

	}

	@Override
	public void onLoad() {
		checkForReceiver();
		//shadowBeamSource = new ModDamageSources(level.registryAccess()).shadowBeam();
	}

	public static void tick(Level level, BlockPos pos, BlockState state, ShadowBeamEmitterBlockEntity be) {
		if (!level.isClientSide()) {
			if (level.getGameTime() % 20 == 0) {
				be.checkForReceiver();
			}
			be.laserActive = true;
			if (be.isActive()) {
				boolean previousLaserActive = be.laserActive;
				if (be.laserTicks > 0) {
					be.laserActive = true;
					be.damageEntitiesInBeam(be);
					be.laserTicks--;

				} else if (be.laserCooldownTicks > 0) {
					be.laserActive = false;
					be.laserCooldownTicks--;

				} else {
					be.laserTicks = 40;
					be.laserCooldownTicks = 20;
					be.laserActive = true;
					be.setChanged();
					level.sendBlockUpdated(be.worldPosition, be.getBlockState(), be.getBlockState(), Block.UPDATE_ALL);
					be.damageEntitiesInBeam(be);

				}
				if (previousLaserActive != be.laserActive) {
					be.setChanged();
					level.sendBlockUpdated(be.worldPosition, be.getBlockState(), be.getBlockState(), Block.UPDATE_ALL);
				}
			} else {
				be.laserTicks = 0;
				be.laserCooldownTicks = 0;
				be.laserActive = false;

			}
		}
	}

	public void damageEntitiesInBeam(ShadowBeamEmitterBlockEntity be) {
		/*if (level.getGameTime() % 2 == 0 && be.laserDamageArea != null) {

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
		}*/

		if (level == null || laserDamageArea == null || !isActive()) return;
		if (level.getGameTime() % 2 != 0) return;

		List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, laserDamageArea);

		for (LivingEntity entity : entities) {
			if (entity instanceof ServerPlayer player && player.isCreative()) continue;
			if (laserTicks > 0 && laserActive) {
				entity.hurt(level.damageSources().magic(), 1.0F);
				entity.invulnerableTime = 1;
				entity.hurtMarked = false;
			}
		}
	}

	/*private DamageSource shadowBeamSource() {
		//if (level == null) return level.damageSources().magic();

		Holder<DamageType> typeHolder = level.registryAccess()
				.registryOrThrow(Registries.DAMAGE_TYPE)
				.getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE,
						ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "shadow_beam")));

		return new DamageSource(typeHolder);
	}*/


	public boolean isActive() {
		return isActive;
	}

	public int getBeamHeight() {
		return beamHeight;
	}

	public boolean laserActive() {
		return laserActive;
	}

	@Override
	public AABB getRenderBoundingBox() {
		return new AABB(worldPosition).expandTowards(0, beamHeight + 1, 0);
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
		tag.putBoolean("LaserActive", this.laserActive);
	}

	@Override
	public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.loadAdditional(tag, registries);
		this.isActive = tag.getBoolean("Active");
		this.beamHeight = tag.getInt("BeamHeight");
		this.laserActive = tag.getBoolean("LaserActive");

	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
		CompoundTag tag = super.getUpdateTag(registries);
		tag.putBoolean("Active", this.isActive);
		tag.putInt("BeamHeight", this.beamHeight);
		tag.putBoolean("LaserActive", this.laserActive);
		return tag;
	}
}

