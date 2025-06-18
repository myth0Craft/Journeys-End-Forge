package net.je.screen.timeworn_journal;

import java.util.ArrayList;
import java.util.List;

import net.je.screen.timeworn_journal.entry.BaseTimewornJournalEntry;
import net.je.screen.timeworn_journal.entry.ImageEntry;
import net.je.screen.timeworn_journal.entry.ItemEntry;
import net.je.screen.timeworn_journal.entry.TimewornJournalEntries;
import net.je.screen.timeworn_journal.entry.renderer.BaseTimewornJournalEntryScreen;
import net.je.screen.timeworn_journal.entry.renderer.TimewornJournalEntryScreens;

public class TimewornJournalListScreens {

	public static class TimewornJournalChapterListScreen extends TimewornJournalScrollableScreen {

		public TimewornJournalChapterListScreen() {
			super(TimewornJournalEntries.CHAPTER_ENTRIES);
			this.scrollOffset = 0;
		}

		public TimewornJournalChapterListScreen(int pScrollOffset) {
			super(TimewornJournalEntries.CHAPTER_ENTRIES);
			this.scrollOffset = pScrollOffset;
		}

		@Override
		protected void onButtonClicked(BaseTimewornJournalEntry pEntry) {
			this.minecraft.setScreen(
					new TimewornJournalEntryScreens.TimewornJournalChapterEntryScreen(pEntry, this.scrollOffset));
		}

	}

	public static class TimewornJournalProgressionListScreen extends TimewornJournalScrollableScreen {

		public TimewornJournalProgressionListScreen() {
			super(TimewornJournalEntries.CHAPTER_ENTRIES);
			this.scrollOffset = 0;
		}

		public TimewornJournalProgressionListScreen(int pScrollOffset) {
			super(TimewornJournalEntries.CHAPTER_ENTRIES);
			this.scrollOffset = pScrollOffset;
		}

		@Override
		protected void onButtonClicked(BaseTimewornJournalEntry pEntry) {
			this.minecraft.setScreen(
					new TimewornJournalEntryScreens.TimewornJournalProgressionEntryScreen(pEntry, this.scrollOffset));
		}

	}

	public static class TimewornJournalBiomesListScreen extends TimewornJournalScrollableScreen {

		public TimewornJournalBiomesListScreen() {
			super(TimewornJournalEntries.CHAPTER_ENTRIES);
			this.scrollOffset = 0;
		}

		public TimewornJournalBiomesListScreen(int pScrollOffset) {
			super(TimewornJournalEntries.CHAPTER_ENTRIES);
			this.scrollOffset = pScrollOffset;
		}

		@Override
		protected void onButtonClicked(BaseTimewornJournalEntry pEntry) {
			this.minecraft.setScreen(
					new TimewornJournalEntryScreens.TimewornJournalBiomesEntryScreen(pEntry, this.scrollOffset));
		}

	}

	public static class TimewornJournalStructuresListScreen extends TimewornJournalScrollableScreen {

		public TimewornJournalStructuresListScreen() {
			super(TimewornJournalEntries.CHAPTER_ENTRIES);
			this.scrollOffset = 0;
		}

		public TimewornJournalStructuresListScreen(int pScrollOffset) {
			super(TimewornJournalEntries.CHAPTER_ENTRIES);
			this.scrollOffset = pScrollOffset;
		}

		@Override
		protected void onButtonClicked(BaseTimewornJournalEntry pEntry) {
			this.minecraft.setScreen(
					new TimewornJournalEntryScreens.TimewornJournalStructuresEntryScreen(pEntry, this.scrollOffset));
		}

	}

	public static class TimewornJournalEntitiesListScreen extends TimewornJournalScrollableScreen {

		public TimewornJournalEntitiesListScreen() {
			super(TimewornJournalEntries.CHAPTER_ENTRIES);
			this.scrollOffset = 0;
		}

		public TimewornJournalEntitiesListScreen(int pScrollOffset) {
			super(TimewornJournalEntries.CHAPTER_ENTRIES);
			this.scrollOffset = pScrollOffset;
		}

		@Override
		protected void onButtonClicked(BaseTimewornJournalEntry pEntry) {
			this.minecraft.setScreen(
					new TimewornJournalEntryScreens.TimewornJournalEntitiesEntryScreen(pEntry, this.scrollOffset));
		}

	}

	public static class TimewornJournalItemsListScreen extends TimewornJournalScrollableScreen {

		public TimewornJournalItemsListScreen() {
			super(TimewornJournalEntries.ITEM_ENTRIES);
			this.scrollOffset = 0;
		}

		public TimewornJournalItemsListScreen(int pScrollOffset) {
			super(TimewornJournalEntries.ITEM_ENTRIES);
			this.scrollOffset = pScrollOffset;
		}

		@Override
		protected void onButtonClicked(BaseTimewornJournalEntry pEntry) {
			ImageEntry item = (ImageEntry) pEntry;
			this.minecraft.setScreen(
					new TimewornJournalEntryScreens.TimewornJournalItemsEntryScreen(item, this.scrollOffset));
		}

	}

	public static class TimewornJournalBlocksListScreen extends TimewornJournalScrollableScreen {

		
		
		public TimewornJournalBlocksListScreen() {
			super(TimewornJournalEntries.BLOCK_ENTRIES);
			this.scrollOffset = 0;
		}

		public TimewornJournalBlocksListScreen(int pScrollOffset) {
			super(TimewornJournalEntries.BLOCK_ENTRIES);
			this.scrollOffset = pScrollOffset;
		}

		@Override
		protected void onButtonClicked(BaseTimewornJournalEntry pEntry) {
			
			if (pEntry.isItem()) {
				ItemEntry item = (ItemEntry) pEntry;
				
				if (item.getItem() != null) {
					this.minecraft.setScreen(
							new TimewornJournalEntryScreens.TimewornJournalBlocksEntryScreen(item, this.scrollOffset));
				}
				
				
			} else if (pEntry.isImage()) {
				ImageEntry item = (ImageEntry) pEntry;
				
				if (item.getImage() != null) {
					this.minecraft.setScreen(
							new TimewornJournalEntryScreens.TimewornJournalBlocksEntryScreen(item, this.scrollOffset));
				}
			}
			
			
			
		}

	}

}
