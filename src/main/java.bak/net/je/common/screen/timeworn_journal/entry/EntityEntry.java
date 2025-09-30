package net.je.common.screen.timeworn_journal.entry;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class EntityEntry extends BaseTimewornJournalEntry {


	private final LivingEntity entity;

	private final float scale;

	private final float yOffset;

	public EntityEntry(Component pName, Component pLore, LivingEntity pEntity) {
		super(pName, pLore);
		entity = pEntity;
		scale = 25f;
		yOffset = 0;
	}

	public EntityEntry(Component pName, Component pLore, LivingEntity pEntity, float pScale) {
		super(pName, pLore);
		entity = pEntity;
		scale = pScale;
		yOffset = 0;
	}

	public EntityEntry(Component pName, Component pLore, LivingEntity pEntity, float pScale, float pYOffset) {
		super(pName, pLore);
		entity = pEntity;
		scale = pScale;
		yOffset = pYOffset;
	}

	public Entity getEntity() {
		return entity;
	}

	@Override
	public Boolean isEntity() {
		return true;
	}

	public Float getScale() {
		return this.scale;
	}

	public float getYOffset() {
		return this.yOffset;
	}

}
