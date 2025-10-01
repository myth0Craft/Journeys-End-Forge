package net.je.common.entity.custom;

import net.je.common.entity.ai.EndersentAttackGoal;
import net.je.common.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class Endersent extends BaseEndersent {


	/*
	 * private static final EntityDataAccessor<Integer> VARIANT =
	 * SynchedEntityData.defineId(Endersent.class, EntityDataSerializers.INT);
	 */


	int teleportTime = 100;
	int invisibilityTime = -1;


	public Endersent(EntityType<? extends Monster> p_33002_, Level p_33003_) {
		super(p_33002_, p_33003_);
		this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
		this.xpReward = 50;

	}

	// anim states




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
		if (canAttack) {
			return SoundEvents.ENDERMAN_AMBIENT;
		} else {
			return null;
		}
	}

	public static AttributeSupplier.Builder createMonsterAttributes() {
		return Mob.createMobAttributes().add(Attributes.ATTACK_DAMAGE, 8f).add(Attributes.MAX_HEALTH, 100D)
				.add(Attributes.FOLLOW_RANGE, 64.0).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ARMOR, 2.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 20D)
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
		if (this.isInvulnerableTo(p_32494_)) {
			return false;
		} else {
			boolean flag = p_32494_.getDirectEntity() instanceof ThrownPotion;
			if (!p_32494_.is(DamageTypeTags.IS_PROJECTILE) && !flag) {
				boolean flag2 = super.hurt(p_32494_, p_32495_);
				if (!this.level().isClientSide() && !(p_32494_.getEntity() instanceof LivingEntity)
						&& this.random.nextInt(10) != 0) {
					this.teleport();
				}

				return flag2;
			} else {
				boolean flag1 = flag
						&& this.hurtWithCleanWater(p_32494_, (ThrownPotion) p_32494_.getDirectEntity(), p_32495_);

				for (int i = 0; i < 64; i++) {
					if (this.teleport()) {
						return true;
					}
				}

				return flag1;
			}
		}
	}

	private boolean hurtWithCleanWater(DamageSource pSource, ThrownPotion pPotion, float pAmount) {

		ItemStack itemstack = pPotion.getItem();
		Potion potion = PotionUtils.getPotion(itemstack);
		List<MobEffectInstance> list = PotionUtils.getMobEffects(itemstack);
		boolean flag = potion == Potions.WATER && list.isEmpty();
		return flag ? super.hurt(pSource, pAmount) : false;
	}

	@Override
	public void aiStep() {
		if (this.level().isClientSide && invisibilityTime < 0) {
			for (int i = 0; i < 2; i++) {
				this.level().addParticle(ParticleTypes.PORTAL, this.getRandomX(0.5), this.getRandomY() - 0.25,
						this.getRandomZ(0.5), (this.random.nextDouble() - 0.5) * 2.0, -this.random.nextDouble(),
						(this.random.nextDouble() - 0.5) * 2.0);
			}
		}

		super.aiStep();
	}

	@Override
	protected void customServerAiStep() {
		if (this.level().isDay() && this.getTarget() == null) {
			if (this.level().canSeeSky(this.blockPosition())) {
				this.setTarget(null);
				this.teleport();
			}
		}

		super.customServerAiStep();
	}

	@Override
	public boolean isSensitiveToWater() {
		return true;
	}

	protected boolean teleport() {
		if (!this.level().isClientSide() && this.isAlive()) {
			double d0 = this.getX() + (this.random.nextDouble() - 0.5) * 64.0;
			double d1 = this.getY() + (this.random.nextInt(64) - 32);
			double d2 = this.getZ() + (this.random.nextDouble() - 0.5) * 64.0;
			return this.teleport(d0, d1, d2);
		} else {
			return false;
		}
	}

	boolean teleportTowards(Entity p_32501_) {
		Vec3 vec3 = new Vec3(this.getX() - p_32501_.getX(), this.getY(0.5) - p_32501_.getEyeY(),
				this.getZ() - p_32501_.getZ());
		vec3 = vec3.normalize();
		double d1 = this.getX() + (this.random.nextDouble() - 0.5) * 8.0 - vec3.x * 16.0;
		double d2 = this.getY() + (this.random.nextInt(16) - 8) - vec3.y * 16.0;
		double d3 = this.getZ() + (this.random.nextDouble() - 0.5) * 8.0 - vec3.z * 16.0;
		return this.teleport(d1, d2, d3);
	}

	@SuppressWarnings("deprecation")
	private boolean teleport(double p_32544_, double p_32545_, double p_32546_) {
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(p_32544_, p_32545_, p_32546_);

		while (blockpos$mutableblockpos.getY() > this.level().getMinBuildHeight()
				&& !this.level().getBlockState(blockpos$mutableblockpos).blocksMotion()) {
			blockpos$mutableblockpos.move(Direction.DOWN);
		}

		BlockState blockstate = this.level().getBlockState(blockpos$mutableblockpos);

		boolean flag = blockstate.blocksMotion();
		boolean flag1 = blockstate.getFluidState().is(FluidTags.WATER);
		if (flag && !flag1) {
			net.minecraftforge.event.entity.EntityTeleportEvent.EnderEntity event = net.minecraftforge.event.ForgeEventFactory
					.onEnderTeleport(this, p_32544_, p_32545_, p_32546_);
			if (event.isCanceled()) {
				return false;
			}
			Vec3 vec3 = this.position();
			boolean flag2 = this.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true);
			if (flag2) {
				this.level().gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(this));
				System.out.println(p_32544_ + " " + p_32545_ + " " + p_32546_);
				teleportTime = (int) (100F + Math.random() * (200F - 100F));
				this.addTeleportEffect();
			}
			return flag2;
		} else {
			return false;
		}
	}

	private void addTeleportEffect() {
		invisibilityTime = ((random.nextInt(1, 10) / 4) + 3) * 20;
		this.setInvisible(true);

		this.setInvulnerable(true);
		canAttack = false;

	}

	private void removeTeleportEffect() {
		this.setInvisible(false);
		this.setInvulnerable(false);
		canAttack = true;
		this.playSound(SoundEvents.ENDERMAN_TELEPORT, 5.0F, 1.0F);

	}

	@Override
	public void tick() {
		super.defaultTick();

		if (this.level().isClientSide()) {
			super.setupAnimationStates();
		}

		if (teleportTime <= -60) {
			if (getTarget() != null) {

				this.teleportTowards(this.getTarget());

			} else if (teleportTime <= -200) {
				this.teleport();
			}
		}

		teleportTime--;
		largeAttackCooldown--;
		if (largeAttackCooldown <= 0) {
			this.goalSelector.addGoal(1, endersentLargeAttackGoal);
		}
		if (invisibilityTime > 0) {
			invisibilityTime--;
		} else if (invisibilityTime == 0) {
			this.removeTeleportEffect();
			invisibilityTime--;
		}
	}
}