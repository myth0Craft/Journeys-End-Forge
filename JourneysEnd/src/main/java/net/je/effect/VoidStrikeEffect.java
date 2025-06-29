package net.je.effect;

import net.je.entity.custom.Endersent;
import net.je.util.ModTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class VoidStrikeEffect extends MobEffect {

	protected VoidStrikeEffect(MobEffectCategory p_19451_, int p_19452_) {
		super(p_19451_, p_19452_);
	}

	@Override
    public boolean applyEffectTick(LivingEntity pEntity, int p_298645_) {
		DamageSource voidStrike =
	            new ModDamageSources(pEntity.level().registryAccess()).voidStrike();
		if (!pEntity.getType().is(ModTags.Entities.END_MOBS)) {
			
			pEntity.hurt(voidStrike, 1.0F);
			
			
		} else {
			pEntity.heal(1.0F);
		}
		return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int p_299625_, int p_297396_) {
        int i = 40 >> p_297396_;
        return i > 0 ? p_299625_ % i == 0 : true;
        
    }

}
