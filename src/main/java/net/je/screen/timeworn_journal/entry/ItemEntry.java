package net.je.screen.timeworn_journal.entry;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class ItemEntry extends BaseTimewornJournalEntry {

	private ItemStack item;

	public ItemEntry(Component pName, Component pLore, ItemStack pItem) {
		super(pName, pLore);
		item = pItem;
	}

	public ItemStack getItem() {
		return item;
	}

	@Override
	public Boolean isItem() {
		return true;
	}

}
