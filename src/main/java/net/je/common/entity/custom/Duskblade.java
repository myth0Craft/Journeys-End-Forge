package net.je.common.entity.custom;

import net.je.common.entity.ai.ShadowMobNavigation;
import net.je.common.entity.animations.DuskbladeAnim;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class Duskblade extends Monster {

	public static final int ATTACK_1_DURATION = (int) (DuskbladeAnim.ATTACK_1.lengthInSeconds() * 20);
	public static final int ATTACK_2_DURATION = (int) (DuskbladeAnim.ATTACK_2.lengthInSeconds() * 20);
	public static final int ATTACK_3_DURATION = (int) (DuskbladeAnim.ATTACK_3.lengthInSeconds() * 20);

	public final AnimationState attackAnimationState = new AnimationState();

	public Duskblade(EntityType<? extends Monster> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 1.62f;
	}

	private int currentAttack = 0;
	private int attackTick = 0;
	private int attackDuration = 0;

	public void startAttack() {
		this.currentAttack = (this.currentAttack % 3) + 1; // cycle 1 → 2 → 3
		this.attackTick = 1; // start ticking at 1
		this.attackDuration = switch (currentAttack) {
			case 1 -> ATTACK_1_DURATION;
			case 2 -> ATTACK_2_DURATION;
			case 3 -> ATTACK_3_DURATION;
			default -> 0;
		};
	}

	@Override
	public void tick() {
		super.tick();

		if (level().isClientSide()) {
			setupAnimationStates();
		}

		if (attackTick > 0) {
			attackTick++;

			if (attackTick > attackDuration) {
				attackTick = 0;
				attackDuration = 0;
			}
		}
	}


	public int getCurrentAttack() {
		return currentAttack;
	}

	public int getAttackTick() {
		return attackTick;
	}

	@Override
	public boolean doHurtTarget(Entity pEntity) {
		this.startAttack();
		return super.doHurtTarget(pEntity);
	}

	public static AttributeSupplier.Builder createMonsterAttributes() {
		return Mob.createMobAttributes().add(Attributes.ATTACK_DAMAGE, 2f).add(Attributes.MAX_HEALTH, 20D)
				.add(Attributes.FOLLOW_RANGE, 10.0).add(Attributes.MOVEMENT_SPEED, 0.2D).add(Attributes.ARMOR, 0.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 1D);
	}

	@Override
	public void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.5, false));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0, 0.0F));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Mob.class, 8.0F));

		//this.targetSelector.addGoal(5, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true, false));
	}

	protected void setupAnimationStates() {
		if (attackTick > 0) {
			attackAnimationState.start(this.tickCount);
		}
		if (attackTick <= 0) {
			attackAnimationState.stop();
		}



	}

	@Override
	protected PathNavigation createNavigation(Level level) {
		return new ShadowMobNavigation(this, level);
	}
}
