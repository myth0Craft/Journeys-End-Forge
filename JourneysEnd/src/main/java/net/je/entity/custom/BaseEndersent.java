package net.je.entity.custom;

import net.je.entity.ai.EndersentLargeAttackGoal;
import net.je.sound.ModSounds;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class BaseEndersent extends Monster {
	private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(BaseEndersent.class,
			EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> LARGE_ATTACKING = SynchedEntityData.defineId(BaseEndersent.class,
			EntityDataSerializers.BOOLEAN);
	
	public final AnimationState attackAnimationState = new AnimationState();

	public final AnimationState largeAttackAnimationState = new AnimationState();

	public int attackAnimationTimeout = 0;

	public int largeAttackAnimationTimeout = 0;
	
	public Goal endersentLargeAttackGoal = new EndersentLargeAttackGoal(this, 1.0D, true);
	
	public boolean canAttack = true;
	public int largeAttackCooldown = 140;
	
	public BaseEndersent(EntityType<? extends Monster> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
	}
	
	@Override
	protected void defineSynchedData(SynchedEntityData.Builder p_333664_) {
		super.defineSynchedData(p_333664_);
		p_333664_.define(ATTACKING, false);
		p_333664_.define(LARGE_ATTACKING, false);
		//p_333664_.define(VARIANT, 0);

	}
	
	// small attack
		public void setAttacking(boolean attacking) {
			this.entityData.set(ATTACKING, attacking);
		}

		public boolean isAttacking() {
			return this.entityData.get(ATTACKING);
		}

		// large attack
		public void setLargeAttacking(boolean attacking) {
			this.entityData.set(LARGE_ATTACKING, attacking);
		}

		public boolean isLargeAttacking() {
			return this.entityData.get(LARGE_ATTACKING);
		}
		
		protected void setupAnimationStates() {
			if (this.isAttacking() && attackAnimationTimeout <= 0 && !this.isLargeAttacking()) {

				attackAnimationTimeout = 10;
				attackAnimationState.start(this.tickCount);

			} else {
				--this.attackAnimationTimeout;
			}

			if (this.isLargeAttacking() && largeAttackAnimationTimeout <= 0 && !this.isAttacking()) {
				largeAttackAnimationTimeout = 20;
				largeAttackAnimationState.start(this.tickCount);
			} else {
				--this.largeAttackAnimationTimeout;
			}

			if (!this.isAttacking()) {
				attackAnimationState.stop();
			}

			if (!this.isLargeAttacking()) {
				largeAttackAnimationState.stop();
			}

		}
		
		protected void defaultTick() {
			super.tick();
		}
		
		@Override
		public void playAttackSound() {
			this.playSound(ModSounds.ENDERSENT_STEP.get(), 5.0F, 1.0F);
		}
		
		@Override
		public void tick() {
			defaultTick();

			if (this.level().isClientSide()) {
				this.setupAnimationStates();
			}

			largeAttackCooldown--;
			if (largeAttackCooldown <= 0) {
				this.goalSelector.addGoal(1, endersentLargeAttackGoal);
			}
		}
	
	
}
