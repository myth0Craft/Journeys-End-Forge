package net.je.common.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class Duskblade extends Monster {
	public Duskblade(EntityType<? extends Monster> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
	}

	public static AttributeSupplier.Builder createMonsterAttributes() {
		return Mob.createMobAttributes().add(Attributes.ATTACK_DAMAGE, 2f).add(Attributes.MAX_HEALTH, 20D)
				.add(Attributes.FOLLOW_RANGE, 10.0).add(Attributes.MOVEMENT_SPEED, 0.2D).add(Attributes.ARMOR, 0.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 1D).add(Attributes.STEP_HEIGHT, 1.0);
	}
}
