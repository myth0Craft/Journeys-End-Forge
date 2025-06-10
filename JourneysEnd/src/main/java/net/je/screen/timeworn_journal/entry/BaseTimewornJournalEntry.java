package net.je.screen.timeworn_journal.entry;

import java.util.ArrayList;
import java.util.List;

public class BaseTimewornJournalEntry {

	protected String name;

	protected String lore;
	
	private String empty = "Empty";

	public BaseTimewornJournalEntry(String pName, String pLore) {
		this.name = pName;
		this.lore = pLore;
	}

	public String getName() {
		if (this.name != null) {
			return this.name;
		} else {
			return empty;
		}
	}

	public String getLore() {
		if (this.lore != null) {
			return this.lore;
		} else {
			return empty;
		}
	}
}
