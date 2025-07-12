package net.je.entity.ai;

import net.je.entity.custom.BaseEndersent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class EndersentLargeAttackGoal extends MeleeAttackGoal {
	private final int ATTACK_RANGE = 7;
	private final BaseEndersent entity;
	private int attackDelay = 20;
	private int ticksUntilNextAttack = 100;
	private boolean shouldCountTillNextAttack = false;

	public EndersentLargeAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
		super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
		entity = ((BaseEndersent) pMob);
	}

	@Override
	public void start() {
		super.start();
		attackDelay = 20;
		ticksUntilNextAttack = 100;
	}

	@Override
	protected void checkAndPerformAttack(LivingEntity pEnemy) {
		if (entity.canAttack) {
			if (!entity.isAttacking()) {
				if (entity.largeAttackCooldown <= 0) {
					if (isEnemyWithinAttackDistance(pEnemy)) {
						shouldCountTillNextAttack = true;

						if (isTimeToStartAttackAnimation()) {
							entity.setLargeAttacking(true);
						}

						if (isTimeToAttack()) {
							this.mob.getLookControl().setLookAt(pEnemy.getX(), pEnemy.getEyeY(), pEnemy.getZ());
							performAttack(pEnemy);
						}
					} else {
						resetAttackCooldown();
						shouldCountTillNextAttack = false;
						entity.setLargeAttacking(false);
						entity.largeAttackAnimationTimeout = 0;
					}
				}
			}
		}
	}

	private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy) {
		int diffX = Math.abs(pEnemy.getBlockX() - entity.getBlockX());
		int diffY = Math.abs(pEnemy.getBlockY() - entity.getBlockY());

		int diffZ = Math.abs(pEnemy.getBlockZ() - entity.getBlockZ());


		return diffX + diffY + diffZ <= this.ATTACK_RANGE;
	}

	@Override
	protected void resetAttackCooldown() {
		this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay);
	}

	@Override
	protected boolean isTimeToAttack() {
		return this.ticksUntilNextAttack <= 0;
	}

	protected boolean isTimeToStartAttackAnimation() {
		return this.ticksUntilNextAttack <= attackDelay;
	}

	@Override
	protected int getTicksUntilNextAttack() {
		return this.ticksUntilNextAttack;
	}

	protected void performAttack(LivingEntity pEnemy) {
		this.resetAttackCooldown();
		this.mob.swing(InteractionHand.MAIN_HAND);
		this.endersentHurtTarget(pEnemy);
		entity.largeAttackCooldown = 140;

		entity.goalSelector.removeGoal(entity.endersentLargeAttackGoal);
	}

    public boolean endersentHurtTarget(Entity p_21372_) {
        DamageSource damagesource = entity.damageSources().mobAttack(entity);
        boolean flag = p_21372_.hurt(damagesource, 15);
        if (flag) {
            float f1 = 30;
            if (f1 > 0.0F && p_21372_ instanceof LivingEntity livingentity) {
                livingentity.knockback(
                    f1 * 0.5F,
                    Mth.sin(entity.getYRot() * (float) (Math.PI / 180.0)),
                    (-Mth.cos(entity.getYRot() * (float) (Math.PI / 180.0)))
                );
                entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.6, 1.0, 0.6));
            }


            entity.setLastHurtMob(p_21372_);
            entity.playAttackSound();
        }

        return flag;
    }

	@Override
	public void tick() {
		super.tick();
		if (shouldCountTillNextAttack) {
			this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
		}
	}

	@Override
	public void stop() {
		entity.setLargeAttacking(false);
		super.stop();
	}

}
