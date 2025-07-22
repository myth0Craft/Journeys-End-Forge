package net.je.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class WardedParticleProvider implements ParticleProvider<SimpleParticleType> {

	private final SpriteSet sprites;

	private final float red, green, blue;


	public WardedParticleProvider (SpriteSet pSprites) {
		sprites = pSprites;
		red = 0.85f;
		green = 0.0f;
		blue = 1.0f;
	}


	@Override
	public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ,
			double pXSpeed, double pYSpeed, double pZSpeed) {
		WardedParticleType particle = new WardedParticleType(pLevel, pX, pY, pZ, red, green, blue);
		particle.setSprite(sprites.get(RandomSource.create()));
		return particle;
	}

}
