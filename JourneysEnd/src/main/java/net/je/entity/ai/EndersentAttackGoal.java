package net.je.entity.ai;

import net.je.entity.custom.Endersent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class EndersentAttackGoal extends MeleeAttackGoal {
	private final int ATTACK_RANGE = 4;
	private final Endersent entity;
	private int attackDelay = 10;
	private int ticksUntilNextAttack = 40;
	private boolean shouldCountTillNextAttack = false;

	public EndersentAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
		super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
		entity = ((Endersent) pMob);
	}

	@Override
	public void start() {
		super.start();
		attackDelay = 10;
		ticksUntilNextAttack = 40;
	}

	@Override
	protected void checkAndPerformAttack(LivingEntity pEnemy) {
		if (!entity.isLargeAttacking()) {
			if (entity.canAttack == true) {
				if (isEnemyWithinAttackDistance(pEnemy)) {
					shouldCountTillNextAttack = true;

					if (isTimeToStartAttackAnimation()) {
						entity.setAttacking(true);
					}

					if (isTimeToAttack()) {
						this.mob.getLookControl().setLookAt(pEnemy.getX(), pEnemy.getEyeY(), pEnemy.getZ());
						performAttack(pEnemy);
					}
				} else {
					resetAttackCooldown();
					shouldCountTillNextAttack = false;
					entity.setAttacking(false);
					entity.attackAnimationTimeout = 0;
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

	protected void resetAttackCooldown() {
		this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay);
	}

	protected boolean isTimeToAttack() {
		return this.ticksUntilNextAttack <= 0;
	}

	protected boolean isTimeToStartAttackAnimation() {
		return this.ticksUntilNextAttack <= attackDelay;
	}

	protected int getTicksUntilNextAttack() {
		return this.ticksUntilNextAttack;
	}

	protected void performAttack(LivingEntity pEnemy) {
		this.resetAttackCooldown();
		this.mob.swing(InteractionHand.MAIN_HAND);
		this.mob.doHurtTarget(pEnemy);
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
		entity.setAttacking(false);
		super.stop();
	}

}
