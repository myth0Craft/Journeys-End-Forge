package net.je.common.entity.custom;

import net.je.common.entity.ModEntities;
import net.je.common.entity.ai.EndersentAttackGoal;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.Nullable;

public class Echo extends Monster {

	public Echo(Level pLevel) {
		super(ModEntities.ECHO.get(), pLevel);
	}

	public Echo(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}

	public static AttributeSupplier.Builder createMonsterAttributes() {
		return Mob.createMobAttributes().add(Attributes.ATTACK_DAMAGE, 2f).add(Attributes.MAX_HEALTH, 20D)
				.add(Attributes.FOLLOW_RANGE, 10.0).add(Attributes.MOVEMENT_SPEED, 0.2D).add(Attributes.ARMOR, 0.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 1D).add(Attributes.STEP_HEIGHT, 1.0);
	}

	@Override
	public void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		//this.goalSelector.addGoal(2, new EndersentAttackGoal(this, 1.0D, true));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.5, false));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0, 0.0F));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Mob.class, 8.0F));

		this.targetSelector.addGoal(5, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true, false));
	}

	public static final TagKey<Item> SWORDS_TAG = ItemTags.create(ResourceLocation.withDefaultNamespace("swords"));

	@Override
	public void setTarget(@Nullable LivingEntity target) {
		super.setTarget(target);

		if (target instanceof Player player) {
			ItemStack mainHand = player.getItemBySlot(EquipmentSlot.MAINHAND);
			if (!mainHand.isEmpty() && mainHand.is(SWORDS_TAG)) {
				ItemStack copy = new ItemStack(mainHand.getItem(), 1);
				this.setItemSlot(EquipmentSlot.MAINHAND, copy);
			}
		}
	}

	@Override
	public ItemStack getMainHandItem() {
		return super.getMainHandItem();
	}
}
