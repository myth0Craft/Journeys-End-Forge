package net.je.entity.custom;

import net.je.entity.ai.EndersentAttackGoal;
import net.je.entity.ai.EndersentLargeAttackGoal;
import net.je.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.projectile.windcharge.WindCharge;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;

public class EndersentWithEye extends BaseEndersent {


	/*
	 * private static final EntityDataAccessor<Integer> VARIANT =
	 * SynchedEntityData.defineId(Endersent.class, EntityDataSerializers.INT);
	 */

	public EndersentWithEye(EntityType<? extends Monster> p_33002_, Level p_33003_) {
		super(p_33002_, p_33003_);
		this.setPathfindingMalus(PathType.WATER, -1.0F);
		this.xpReward = 50;

	}


	/*
	 * private int getTypeVariant() { return this.entityData.get(VARIANT); }
	 * 
	 * public EndersentVariant getVariant() { return
	 * EndersentVariant.byId(this.getTypeVariant() & 255); }
	 * 
	 * private void setVariant(EndersentVariant variant) {
	 * this.entityData.set(VARIANT, variant.getId() & 255); }
	 * 
	 * @Override public void addAdditionalSaveData(CompoundTag pCompound) {
	 * super.addAdditionalSaveData(pCompound); pCompound.putInt("Variant",
	 * this.getTypeVariant()); }
	 * 
	 * @Override public void readAdditionalSaveData(CompoundTag pCompound) {
	 * super.readAdditionalSaveData(pCompound); this.entityData.set(VARIANT,
	 * pCompound.getInt("Variant")); }
	 * 
	 * @Override public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel,
	 * DifficultyInstance pDifficulty, MobSpawnType pSpawnType, @Nullable
	 * SpawnGroupData pSpawnGroupData) {
	 * 
	 * 
	 * if (this.level().dimension() == Level.END || this.level().dimension() ==
	 * Level.NETHER) { this.setVariant(EndersentVariant.WITHOUT_EYE); } else {
	 * EndersentVariant variant = Util.getRandom(EndersentVariant.values(),
	 * this.random); this.setVariant(variant);
	 * 
	 * } return super.finalizeSpawn(pLevel, pDifficulty, pSpawnType,
	 * pSpawnGroupData); }
	 */

	@Override
	protected void updateWalkAnimation(float pPartialTick) {
		float f;
		if (this.getPose() == Pose.STANDING) {
			f = Math.min(pPartialTick * 6F, 1f);
		} else {
			f = 0f;
		}

		this.walkAnimation.update(f, 0.2f);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource p_33034_) {
		return SoundEvents.ENDERMAN_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.ENDERSENT_DEATH.get();
	}

	@Override
	protected SoundEvent getAmbientSound() {
		if (canAttack == true) {
			return SoundEvents.ENDERMAN_AMBIENT;
		} else {
			return null;
		}
	}


	public static AttributeSupplier.Builder createMonsterAttributes() {
		return Endersent.createMobAttributes().add(Attributes.ATTACK_DAMAGE, 8f).add(Attributes.MAX_HEALTH, 100D)
				.add(Attributes.FOLLOW_RANGE, 64.0).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ARMOR, 2.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 20D).add(Attributes.STEP_HEIGHT, 1.0)
				.add(Attributes.KNOCKBACK_RESISTANCE, 100.0D);

	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(2, new EndersentAttackGoal(this, 1.0D, true));

		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0, 0.0F));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Mob.class, 8.0F));

		this.targetSelector.addGoal(5, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true, false));
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Endermite.class, true, false));

	}

	@Override
	public boolean hurt(DamageSource p_32494_, float p_32495_) {
		Entity entity = p_32494_.getDirectEntity();
		if (this.isInvulnerableTo(p_32494_)) {
			return false;
		} else if (entity instanceof AbstractArrow || entity instanceof WindCharge) {
			return false;
		} else {

			boolean flag = p_32494_.getDirectEntity() instanceof ThrownPotion;
			if (!p_32494_.is(DamageTypeTags.IS_PROJECTILE) && !flag) {
				boolean flag2 = super.hurt(p_32494_, p_32495_);
				if (!this.level().isClientSide() && !(p_32494_.getEntity() instanceof LivingEntity)
						&& this.random.nextInt(10) != 0) {
				}

				return flag2;
			} else {
				boolean flag1 = flag
						&& this.hurtWithCleanWater(p_32494_, (ThrownPotion) p_32494_.getDirectEntity(), p_32495_);

				return flag1;
			}
		}
	}

	@Override
	public void checkDespawn() {
		if (this.level().getDifficulty() == Difficulty.PEACEFUL && this.shouldDespawnInPeaceful()) {
			this.discard();
		} else {
			this.noActionTime = 0;
		}
	}

	private boolean hurtWithCleanWater(DamageSource p_186273_, ThrownPotion p_186274_, float p_186275_) {
		ItemStack itemstack = p_186274_.getItem();
		PotionContents potioncontents = itemstack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
		return potioncontents.is(Potions.WATER) ? super.hurt(p_186273_, p_186275_) : false;
	}

	@SuppressWarnings("resource")
	@Override
	public void aiStep() {
		if (this.level().isClientSide) {
			for (int i = 0; i < 2; i++) {
				this.level().addParticle(ParticleTypes.PORTAL, this.getRandomX(0.5), this.getRandomY() - 0.25,
						this.getRandomZ(0.5), (this.random.nextDouble() - 0.5) * 2.0, -this.random.nextDouble(),
						(this.random.nextDouble() - 0.5) * 2.0);
			}
		}

		super.aiStep();
	}


	@Override
	public void tick() {
		super.tick();
	}
}
