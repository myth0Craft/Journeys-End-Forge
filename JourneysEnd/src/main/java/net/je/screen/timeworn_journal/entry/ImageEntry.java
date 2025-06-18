package net.je.screen.timeworn_journal.entry;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ImageEntry extends BaseTimewornJournalEntry {

	private ResourceLocation image;
	
	
	public ImageEntry(Component pName, Component pLore, ResourceLocation pImage) {
		super(pName, pLore);
		this.image = pImage;
	}
	
	public ResourceLocation getImage() {
		return this.image;
	}
	
	@Override
	public Boolean isImage() {
		return true;
	}

}
