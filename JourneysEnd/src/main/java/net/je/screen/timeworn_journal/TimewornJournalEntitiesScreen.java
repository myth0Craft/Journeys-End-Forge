package net.je.screen.timeworn_journal;

public class TimewornJournalEntitiesScreen extends BaseTimewornJournalScreen {
	
	public TimewornJournalEntitiesScreen() {
		super();
	}
	
	@Override
	protected void init() {
		super.init();
		super.renderBackButton(new TimewornJournalHomeScreen());
	}
}
