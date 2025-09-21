package net.je.common.entity.custom;

import net.je.common.block.ModBlocks;
import net.je.common.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class ShadowLord extends Monster {
	public ShadowLord(EntityType<? extends Monster> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
	}

	public static AttributeSupplier.Builder createMonsterAttributes() {
		return Mob.createMobAttributes().add(Attributes.ATTACK_DAMAGE, 2f).add(Attributes.MAX_HEALTH, 400D)
				.add(Attributes.FOLLOW_RANGE, 15.0).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ARMOR, 0.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 1D).add(Attributes.STEP_HEIGHT, 1.0);
	}

	@Override
	public void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.5, true));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Mob.class, 8.0F));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true, false));
	}

	/*@Override
	protected void populateDefaultEquipmentSlots(RandomSource pRandom, DifficultyInstance pDifficulty) {

		this.setDropChance(EquipmentSlot.MAINHAND, 1.0F);
		super.populateDefaultEquipmentSlots(random, pDifficulty);

	}*/

	@Override
	public void setTarget(@Nullable LivingEntity target) {
		super.setTarget(target);
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.SHADOW_STEEL_SWORD.get()));
	}

	@Override
	public void tick() {
		super.tick();
		if (!(this.level() == null) && !(this.level().isClientSide())) {
			Level level = this.level();
			if (level.getGameTime() % 10 == 0 && this.getTarget() != null) {
				BlockPos pos = this.getOnPos();
				int x = random.nextInt(2 - (-2) + 1) + (-2);
				int z = random.nextInt(2 - (-2) + 1) + (-2);
				BlockPos laserPos = pos.offset(x, 0, z);
				if (!(level.getBlockState(laserPos).is(ModBlocks.UNSTABLE_SHADOW_PRISM.get())) && !level.getBlockState(laserPos).is(ModBlocks.FADED_END_STONE_BRICKS.get()) && level.isEmptyBlock(laserPos.above())) {
					level.setBlockAndUpdate(laserPos, ModBlocks.UNSTABLE_SHADOW_PRISM.get().defaultBlockState());
				}
			}
		}
	}
}
