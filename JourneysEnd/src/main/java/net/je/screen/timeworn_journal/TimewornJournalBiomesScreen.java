package net.je.screen.timeworn_journal;

public class TimewornJournalBiomesScreen extends BaseTimewornJournalScreen {
	
	public TimewornJournalBiomesScreen() {
		super();
	}
	
	@Override
	protected void init() {
		super.init();
		super.renderBackButton(new TimewornJournalHomeScreen());
	}
}
