package net.je.screen.timeworn_journal;

public class TimewornJournalStructuresScreen extends BaseTimewornJournalScreen {
	
	public TimewornJournalStructuresScreen() {
		super();
	}
	
	@Override
	protected void init() {
		super.init();
		super.renderBackButton(new TimewornJournalHomeScreen());
	}
}
