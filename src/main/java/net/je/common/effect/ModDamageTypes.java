package net.je.common.effect;

import net.je.JourneysEnd;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;


public interface ModDamageTypes {
	ResourceKey<DamageType> VOID_STRIKE = register("void_strike");
	//ResourceKey<DamageType> SHADOW_BEAM = register("shadow_beam");

	private static ResourceKey<DamageType> register(String name) {
		return ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, name));
	}
}


