package net.je.screen.timeworn_journal.entry;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.network.chat.Component;

public class BaseTimewornJournalEntry {

	protected Component name;

	protected Component lore;
	
	private Component empty = Component.translatable("screen.je.timeworn_journal.empty");

	public BaseTimewornJournalEntry(Component pName, Component pLore) {
		this.name = pName;
		this.lore = pLore;
	}

	public Component getName() {
		if (this.name != null) {
			return this.name;
		} else {
			return empty;
		}
	}

	public Component getLore() {
		if (this.lore != null) {
			return this.lore;
		} else {
			return empty;
		}
	}
	
	public Boolean isEntity() {
		return false;
	}
	
	public Boolean isImage() {
		return false;
	}
	
	public Boolean isItem() {
		return false;
	}
}
