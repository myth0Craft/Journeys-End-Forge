package net.je.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

public class WardedParticleType extends TextureSheetParticle {

	private final float initialSize;


	public WardedParticleType(ClientLevel pLevel, double pX, double pY, double pZ, float r, float g, float b) {
		super(pLevel, pX, pY, pZ);
		this.setColor(r, g, b);
		this.setAlpha(1.0f);
		this.initialSize = 0.1f;
		this.lifetime = 20 + this.random.nextInt(10);
		this.quadSize = initialSize;
		this.setParticleSpeed(0, 0.1, 0);
	}

	@Override
	public void tick() {
		super.tick();
		float ageRatio = (float) age / lifetime;
		//this.quadSize = initialSize * (1.0f - ageRatio);
		this.alpha = 1.0f - ageRatio;
	}


	@Override
	public void setSprite(TextureAtlasSprite pSprite) {
		this.sprite = pSprite;
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}
}
