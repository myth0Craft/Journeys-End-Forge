package net.je.effect;

import net.je.JourneysEnd;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Items;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {

	 public static final DeferredRegister<MobEffect> MOB_EFFECTS =
	            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, JourneysEnd.MODID);

	    public static final RegistryObject<MobEffect> VOID_STRIKE_EFFECT = MOB_EFFECTS.register("void_strike",
	            () -> new VoidStrikeEffect(MobEffectCategory.HARMFUL, 0x5a0083).withSoundOnAdded(SoundEvents.SCULK_SHRIEKER_BREAK));


	    public static void register(IEventBus eventBus) {
	        MOB_EFFECTS.register(eventBus);
	    }

}
