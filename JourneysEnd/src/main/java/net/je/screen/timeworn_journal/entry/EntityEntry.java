package net.je.screen.timeworn_journal.entry;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;

public class EntityEntry extends BaseTimewornJournalEntry {

	
	private Entity entity;
	
	public EntityEntry(Component pName, Component pLore, Entity pEntity) {
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
