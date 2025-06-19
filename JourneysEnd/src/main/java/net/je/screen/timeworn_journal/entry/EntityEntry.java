package net.je.screen.timeworn_journal.entry;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class EntityEntry extends BaseTimewornJournalEntry {

	
	private LivingEntity entity;
	
	public EntityEntry(Component pName, Component pLore, LivingEntity pEntity) {
		super(pName, pLore);
		entity = pEntity;
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	@Override
	public Boolean isEntity() {
		return true;
	}

}
