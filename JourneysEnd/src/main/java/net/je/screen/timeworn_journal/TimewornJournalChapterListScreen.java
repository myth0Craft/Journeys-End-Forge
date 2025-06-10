package net.je.screen.timeworn_journal;

import java.util.List;

import net.je.screen.timeworn_journal.entry.BaseTimewornJournalEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

public class TimewornJournalChapterListScreen extends TimewornJournalScrollableScreen {
	
	private List<BaseTimewornJournalEntry> chapters;
	
	
	public TimewornJournalChapterListScreen(List<BaseTimewornJournalEntry> pChapters) {
		
		super(pChapters);
		chapters = pChapters;
	}
	
	@Override
	protected void init() {
		super.init();
		super.renderBackButton(new TimewornJournalHomeScreen());
	}
}
