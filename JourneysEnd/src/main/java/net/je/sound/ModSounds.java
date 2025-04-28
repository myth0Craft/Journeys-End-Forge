package net.je.sound;

import java.util.function.Supplier;

import net.je.JourneysEnd;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {

	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister
			.create(ForgeRegistries.SOUND_EVENTS, JourneysEnd.MODID);

	public static final RegistryObject<SoundEvent> ENDERSENT_STEP = registerSoundEvents("endersent.endersent_step");
	public static final RegistryObject<SoundEvent> ENDERSENT_DEATH = registerSoundEvents("endersent.endersent_death");

	public static final RegistryObject<SoundEvent> INTERDIMENSIONAL_ANCHOR_BREAK = registerSoundEvents(
			"block.interdimensional_anchor");

	public static final ForgeSoundType INTERDIMENSIONAL_ANCHOR_SOUNDS = new ForgeSoundType(1f, 1f,
			ModSounds.INTERDIMENSIONAL_ANCHOR_BREAK, 
			()-> SoundEvents.METAL_STEP, 
			()-> SoundEvents.RESPAWN_ANCHOR_CHARGE, 
			()-> SoundEvents.METAL_HIT,
			()-> SoundEvents.METAL_FALL);

	private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
		return SOUND_EVENTS.register(name, () -> SoundEvent
				.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, name)));
	}

	public static void register(IEventBus eventBus) {
		SOUND_EVENTS.register(eventBus);
	}

}
