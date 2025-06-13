package net.je.screen.timeworn_journal.entry;

import net.minecraft.world.item.ItemStack;

public class ItemEntry extends BaseTimewornJournalEntry {

	private ItemStack item;
	
	public ItemEntry(String pName, String pLore, ItemStack pItem) {
		super(pName, pLore);
		item = pItem;
	}
	
	public ItemStack getItem() {
		return item;
	}

}
