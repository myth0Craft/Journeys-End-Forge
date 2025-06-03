package net.je.screen.timeworn_journal;

public class TimewornJournalBlocksScreen extends BaseTimewornJournalScreen {
	
	public TimewornJournalBlocksScreen() {
		super();
	}
	
	@Override
	protected void init() {
		super.init();
		super.renderBackButton(new TimewornJournalHomeScreen());
	}
}
