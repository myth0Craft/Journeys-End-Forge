package net.je.screen.timeworn_journal;

public class TimewornJournalChapterListScreen extends BaseTimewornJournalScreen {
	
	public TimewornJournalChapterListScreen() {
		super();
	}
	
	@Override
	protected void init() {
		super.init();
		super.renderBackButton(new TimewornJournalHomeScreen());
	}
}
