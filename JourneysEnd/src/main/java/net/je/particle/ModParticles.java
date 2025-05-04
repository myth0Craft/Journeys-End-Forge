package net.je.particle;

import net.je.JourneysEnd;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister
			.create(ForgeRegistries.PARTICLE_TYPES, JourneysEnd.MODID);

	public static final RegistryObject<SimpleParticleType> ENDERSENT_SPAWN_PARTICLES = PARTICLE_TYPES
			.register("endersent_spawn_particles", () -> new SimpleParticleType(true));

	public static void register(IEventBus eventBus) {
		PARTICLE_TYPES.register(eventBus);
	}
}
