package net.je.screen.timeworn_journal.entry;

import net.minecraft.world.entity.Entity;

public class EntityEntry extends BaseTimewornJournalEntry {

	
	private Entity entity;
	
	public EntityEntry(String pName, String pLore, Entity pEntity) {
		super(pName, pLore);
		entity = pEntity;
	}
	
	public Entity getEntity() {
		return entity;
	}

}
