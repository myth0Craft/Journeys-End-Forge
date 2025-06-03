package net.je.screen.timeworn_journal;

import net.minecraft.client.gui.components.AbstractSelectionList;

public class TimewornJournalProgressionScreen extends BaseTimewornJournalScreen {
	
	public TimewornJournalProgressionScreen() {
		super();
	}
	
	@Override
	protected void init() {
		super.init();
		super.renderBackButton(new TimewornJournalHomeScreen());
	}
}
