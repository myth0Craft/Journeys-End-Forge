package net.je.screen.timeworn_journal;

import java.util.ArrayList;
import java.util.List;

import net.je.screen.timeworn_journal.entry.BaseTimewornJournalEntry;
import net.je.screen.timeworn_journal.entry.EntityEntry;
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
					new TimewornJournalEntryScreens.TimewornJournalEmptyEntryScreen(pEntry, this.scrollOffset, this));
		}

	}

	public static class TimewornJournalProgressionListScreen extends TimewornJournalScrollableScreen {

		public TimewornJournalProgressionListScreen() {
			super(TimewornJournalEntries.PROGRESSION_ENTRIES);
			this.scrollOffset = 0;
		}

		public TimewornJournalProgressionListScreen(int pScrollOffset) {
			super(TimewornJournalEntries.PROGRESSION_ENTRIES);
			this.scrollOffset = pScrollOffset;
		}

		@Override
		protected void onButtonClicked(BaseTimewornJournalEntry pEntry) {
			ImageEntry item = (ImageEntry) pEntry;
			this.minecraft.setScreen(
					new TimewornJournalEntryScreens.TimewornJournalImageEntryScreen(item, this.scrollOffset, false, this));
		}

	}

	public static class TimewornJournalBiomesListScreen extends TimewornJournalScrollableScreen {

		public TimewornJournalBiomesListScreen() {
			super(TimewornJournalEntries.BIOME_ENTRIES);
			this.scrollOffset = 0;
		}

		public TimewornJournalBiomesListScreen(int pScrollOffset) {
			super(TimewornJournalEntries.BIOME_ENTRIES);
			this.scrollOffset = pScrollOffset;
		}

		@Override
		protected void onButtonClicked(BaseTimewornJournalEntry pEntry) {
			ImageEntry item = (ImageEntry) pEntry;
			this.minecraft.setScreen(
					new TimewornJournalEntryScreens.TimewornJournalImageEntryScreen(item, this.scrollOffset, true, this));
		}

	}

	public static class TimewornJournalStructuresListScreen extends TimewornJournalScrollableScreen {

		public TimewornJournalStructuresListScreen() {
			super(TimewornJournalEntries.STRUCTURE_ENTRIES);
			this.scrollOffset = 0;
		}

		public TimewornJournalStructuresListScreen(int pScrollOffset) {
			super(TimewornJournalEntries.STRUCTURE_ENTRIES);
			this.scrollOffset = pScrollOffset;
		}

		@Override
		protected void onButtonClicked(BaseTimewornJournalEntry pEntry) {
			ImageEntry item = (ImageEntry) pEntry;
			this.minecraft.setScreen(
					new TimewornJournalEntryScreens.TimewornJournalImageEntryScreen(item, this.scrollOffset, true, this));
		}

	}

	public static class TimewornJournalEntitiesListScreen extends TimewornJournalScrollableScreen {

		public TimewornJournalEntitiesListScreen() {
			super(TimewornJournalEntries.ENTITY_ENTRIES);
			this.scrollOffset = 0;
		}

		public TimewornJournalEntitiesListScreen(int pScrollOffset) {
			super(TimewornJournalEntries.ENTITY_ENTRIES);
			this.scrollOffset = pScrollOffset;
		}

		@Override
		protected void onButtonClicked(BaseTimewornJournalEntry pEntry) {
			EntityEntry entity = (EntityEntry) pEntry;
			this.minecraft.setScreen(
					new TimewornJournalEntryScreens.TimewornJournalEntityEntryScreen(entity, this.scrollOffset, this));
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
					new TimewornJournalEntryScreens.TimewornJournalImageEntryScreen(item, this.scrollOffset, false, this));
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
							new TimewornJournalEntryScreens.TimewornJournalBlockEntryScreen(item, this.scrollOffset, this));
				}
				
				
			} else if (pEntry.isImage()) {
				ImageEntry item = (ImageEntry) pEntry;
				
				if (item.getImage() != null) {
					this.minecraft.setScreen(
							new TimewornJournalEntryScreens.TimewornJournalImageEntryScreen(item, this.scrollOffset, false, this));
				}
			}
			
			
			
		}

	}

}
