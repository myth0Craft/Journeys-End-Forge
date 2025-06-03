package net.je.screen.timeworn_journal;

public class TimewornJournalItemsScreen extends BaseTimewornJournalScreen {
	
	public TimewornJournalItemsScreen() {
		super();
	}
	
	@Override
	protected void init() {
		super.init();
		super.renderBackButton(new TimewornJournalHomeScreen());
	}
}