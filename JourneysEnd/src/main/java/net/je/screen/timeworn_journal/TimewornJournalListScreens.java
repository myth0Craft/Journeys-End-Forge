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

		int tier;
		
		public TimewornJournalChapterListScreen(int pTier) {
			super(TimewornJournalEntries.getChapterList(pTier));
			this.scrollOffset = 0;
			tier = pTier;
		}

		public TimewornJournalChapterListScreen(int pTier, int pScrollOffset) {
			super(TimewornJournalEntries.getChapterList(pTier));
			this.scrollOffset = pScrollOffset;
			tier = pTier;
		}

		@Override
		protected void onButtonClicked(BaseTimewornJournalEntry pEntry) {
			this.minecraft.setScreen(
					new TimewornJournalEntryScreens.TimewornJournalEmptyEntryScreen(pEntry, this.scrollOffset, this));
		}
		
		@Override
		public void init() {
			super.init();
			super.renderBackButton(new TimewornJournalHomeScreen(tier));
		}

	}

	public static class TimewornJournalProgressionListScreen extends TimewornJournalScrollableScreen {

		
		int tier;
		public TimewornJournalProgressionListScreen(int pTier) {
			super(TimewornJournalEntries.getProgressionList(pTier));
			this.scrollOffset = 0;
			tier = pTier;
		}

		public TimewornJournalProgressionListScreen(int pTier, int pScrollOffset) {
			super(TimewornJournalEntries.getProgressionList(pTier));
			this.scrollOffset = pScrollOffset;
			tier = pTier;
		}

		@Override
		protected void onButtonClicked(BaseTimewornJournalEntry pEntry) {
			ImageEntry item = (ImageEntry) pEntry;
			this.minecraft.setScreen(new TimewornJournalEntryScreens.TimewornJournalImageEntryScreen(item,
					this.scrollOffset, false, this));
		}
		
		@Override
		public void init() {
			super.init();
			super.renderBackButton(new TimewornJournalHomeScreen(tier));
		}

	}

	public static class TimewornJournalBiomesListScreen extends TimewornJournalScrollableScreen {

		int tier;
		public TimewornJournalBiomesListScreen(int pTier) {
			super(TimewornJournalEntries.getBiomeList(pTier));
			this.scrollOffset = 0;
			tier = pTier;
		}

		public TimewornJournalBiomesListScreen(int pTier, int pScrollOffset) {
			super(TimewornJournalEntries.getBiomeList(pTier));
			this.scrollOffset = pScrollOffset;
			tier = pTier;
		}

		@Override
		protected void onButtonClicked(BaseTimewornJournalEntry pEntry) {
			ImageEntry item = (ImageEntry) pEntry;
			this.minecraft.setScreen(new TimewornJournalEntryScreens.TimewornJournalImageEntryScreen(item,
					this.scrollOffset, true, this));
		}
		
		@Override
		public void init() {
			super.init();
			super.renderBackButton(new TimewornJournalHomeScreen(tier));
		}

	}

	public static class TimewornJournalStructuresListScreen extends TimewornJournalScrollableScreen {

		int tier;
		public TimewornJournalStructuresListScreen(int pTier) {
			super(TimewornJournalEntries.getStructureList(pTier));
			this.scrollOffset = 0;
			tier = pTier;
		}

		public TimewornJournalStructuresListScreen(int pTier, int pScrollOffset) {
			super(TimewornJournalEntries.getStructureList(pTier));
			this.scrollOffset = pScrollOffset;
			tier = pTier;
		}

		@Override
		protected void onButtonClicked(BaseTimewornJournalEntry pEntry) {
			ImageEntry item = (ImageEntry) pEntry;
			this.minecraft.setScreen(new TimewornJournalEntryScreens.TimewornJournalImageEntryScreen(item,
					this.scrollOffset, true, this));
		}
		
		@Override
		public void init() {
			super.init();
			super.renderBackButton(new TimewornJournalHomeScreen(tier));
		}

	}

	public static class TimewornJournalEntitiesListScreen extends TimewornJournalScrollableScreen {

		
		int tier;
		public TimewornJournalEntitiesListScreen(int pTier) {
			super(TimewornJournalEntries.getEntityList(pTier));
			this.scrollOffset = 0;
			tier = pTier;
		}

		public TimewornJournalEntitiesListScreen(int pTier, int pScrollOffset) {
			super(TimewornJournalEntries.getEntityList(pTier));
			this.scrollOffset = pScrollOffset;
			tier = pTier;
		}

		@Override
		protected void onButtonClicked(BaseTimewornJournalEntry pEntry) {
			if (pEntry.isEntity()) {
				EntityEntry entity = (EntityEntry) pEntry;
				if (entity.getEntity() != null) {
					this.minecraft.setScreen(new TimewornJournalEntryScreens.TimewornJournalEntityEntryScreen(entity,
							this.scrollOffset, this));
				}

			} else if (pEntry.isImage()) {
				ImageEntry entity = (ImageEntry) pEntry;
				if (entity.getImage() != null) {
					this.minecraft.setScreen(new TimewornJournalEntryScreens.TimewornJournalImageEntryScreen(entity,
							this.scrollOffset, true, this));
				}
			}
		}
		
		@Override
		public void init() {
			super.init();
			super.renderBackButton(new TimewornJournalHomeScreen(tier));
		}

	}

	public static class TimewornJournalItemsListScreen extends TimewornJournalScrollableScreen {

		
		int tier;
		public TimewornJournalItemsListScreen(int pTier) {
			super(TimewornJournalEntries.getItemList(pTier));
			this.scrollOffset = 0;
			tier = pTier;
		}

		public TimewornJournalItemsListScreen(int pTier, int pScrollOffset) {
			super(TimewornJournalEntries.getItemList(pTier));
			this.scrollOffset = pScrollOffset;
			tier = pTier;
		}

		@Override
		protected void onButtonClicked(BaseTimewornJournalEntry pEntry) {
			ImageEntry item = (ImageEntry) pEntry;
			this.minecraft.setScreen(new TimewornJournalEntryScreens.TimewornJournalImageEntryScreen(item,
					this.scrollOffset, false, this));
		}
		
		@Override
		public void init() {
			super.init();
			super.renderBackButton(new TimewornJournalHomeScreen(tier));
		}

	}

	public static class TimewornJournalBlocksListScreen extends TimewornJournalScrollableScreen {

		
		int tier;
		public TimewornJournalBlocksListScreen(int pTier) {
			super(TimewornJournalEntries.getBlockList(pTier));
			this.scrollOffset = 0;
			tier = pTier;
		}

		public TimewornJournalBlocksListScreen(int pTier, int pScrollOffset) {
			super(TimewornJournalEntries.getBlockList(pTier));
			this.scrollOffset = pScrollOffset;
			tier = pTier;
		}

		@Override
		protected void onButtonClicked(BaseTimewornJournalEntry pEntry) {

			if (pEntry.isItem()) {
				ItemEntry item = (ItemEntry) pEntry;

				if (item.getItem() != null) {
					this.minecraft.setScreen(new TimewornJournalEntryScreens.TimewornJournalBlockEntryScreen(item,
							this.scrollOffset, this));
				}

			} else if (pEntry.isImage()) {
				ImageEntry item = (ImageEntry) pEntry;

				if (item.getImage() != null) {
					this.minecraft.setScreen(new TimewornJournalEntryScreens.TimewornJournalImageEntryScreen(item,
							this.scrollOffset, false, this));
				}
			}

		}
		
		@Override
		public void init() {
			super.init();
			super.renderBackButton(new TimewornJournalHomeScreen(tier));
		}

	}

}
