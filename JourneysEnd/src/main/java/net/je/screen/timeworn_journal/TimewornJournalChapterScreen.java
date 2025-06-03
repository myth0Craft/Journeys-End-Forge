package net.je.screen.timeworn_journal;

import net.minecraft.network.chat.Component;

public class TimewornJournalChapterScreen extends BaseTimewornJournalScreen {
	
	public TimewornJournalChapterScreen(Integer chapterNumber, Boolean shouldHaveBackButton) {
		super();
	}
	@Override
	protected void init() {
		super.init();
		//super.renderBackButton(new TimewornJournalHomeScreen());
	}
}
