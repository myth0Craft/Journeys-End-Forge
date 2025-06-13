package net.je.screen.timeworn_journal.entry.renderer;

import org.jetbrains.annotations.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;

import net.je.block.ModBlocks;
import net.je.screen.timeworn_journal.TimewornJournalListScreens;
import net.je.screen.timeworn_journal.TimewornJournalListScreens.TimewornJournalBiomesListScreen;
import net.je.screen.timeworn_journal.TimewornJournalListScreens.TimewornJournalBlocksListScreen;
import net.je.screen.timeworn_journal.TimewornJournalListScreens.TimewornJournalChapterListScreen;
import net.je.screen.timeworn_journal.TimewornJournalListScreens.TimewornJournalEntitiesListScreen;
import net.je.screen.timeworn_journal.TimewornJournalListScreens.TimewornJournalItemsListScreen;
import net.je.screen.timeworn_journal.TimewornJournalListScreens.TimewornJournalProgressionListScreen;
import net.je.screen.timeworn_journal.TimewornJournalListScreens.TimewornJournalStructuresListScreen;
import net.je.screen.timeworn_journal.entry.BaseTimewornJournalEntry;
import net.je.screen.timeworn_journal.entry.ItemEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

public class TimewornJournalEntryScreens {

	public static class TimewornJournalChapterEntryScreen extends BaseTimewornJournalEntryScreen {

		private int scrollOffset;

		public TimewornJournalChapterEntryScreen(BaseTimewornJournalEntry pEntry) {
			super(pEntry);
			this.scrollOffset = 0;
		}

		public TimewornJournalChapterEntryScreen(BaseTimewornJournalEntry pEntry, int pScrollOffset) {
			super(pEntry, pScrollOffset, new TimewornJournalListScreens.TimewornJournalChapterListScreen());
			this.scrollOffset = pScrollOffset;
		}

		@Override
		protected void renderBackButton(@Nullable Screen backScreen) {
			this.addRenderableWidget(new ImageButton(super.getBgStartX() + 18,
					super.getBgStartY() + super.getBgHeight() - 36, 20, 20, BACK_BUTTON_SPRITES, p_308203_ -> {
						this.minecraft.setScreen(new TimewornJournalChapterListScreen(scrollOffset));
					}));
		}

	}

	public static class TimewornJournalProgressionEntryScreen extends BaseTimewornJournalEntryScreen {

		private int scrollOffset;

		public TimewornJournalProgressionEntryScreen(BaseTimewornJournalEntry pEntry) {
			super(pEntry);
			this.scrollOffset = 0;
		}

		public TimewornJournalProgressionEntryScreen(BaseTimewornJournalEntry pEntry, int pScrollOffset) {
			super(pEntry, pScrollOffset, new TimewornJournalListScreens.TimewornJournalProgressionListScreen());
			this.scrollOffset = pScrollOffset;
		}

		@Override
		protected void renderBackButton(@Nullable Screen backScreen) {
			this.addRenderableWidget(new ImageButton(super.getBgStartX() + 18,
					super.getBgStartY() + super.getBgHeight() - 36, 20, 20, BACK_BUTTON_SPRITES, p_308203_ -> {
						this.minecraft.setScreen(new TimewornJournalProgressionListScreen(scrollOffset));
					}));
		}

	}

	public static class TimewornJournalBiomesEntryScreen extends BaseTimewornJournalEntryScreen {

		private int scrollOffset;

		public TimewornJournalBiomesEntryScreen(BaseTimewornJournalEntry pEntry) {
			super(pEntry);
			this.scrollOffset = 0;
		}

		public TimewornJournalBiomesEntryScreen(BaseTimewornJournalEntry pEntry, int pScrollOffset) {
			super(pEntry, pScrollOffset, new TimewornJournalListScreens.TimewornJournalBiomesListScreen());
			this.scrollOffset = pScrollOffset;
		}

		@Override
		protected void renderBackButton(@Nullable Screen backScreen) {
			this.addRenderableWidget(new ImageButton(super.getBgStartX() + 18,
					super.getBgStartY() + super.getBgHeight() - 36, 20, 20, BACK_BUTTON_SPRITES, p_308203_ -> {
						this.minecraft.setScreen(new TimewornJournalBiomesListScreen(scrollOffset));
					}));
		}

	}

	public static class TimewornJournalStructuresEntryScreen extends BaseTimewornJournalEntryScreen {

		private int scrollOffset;

		public TimewornJournalStructuresEntryScreen(BaseTimewornJournalEntry pEntry) {
			super(pEntry);
			this.scrollOffset = 0;
		}

		public TimewornJournalStructuresEntryScreen(BaseTimewornJournalEntry pEntry, int pScrollOffset) {
			super(pEntry, pScrollOffset, new TimewornJournalListScreens.TimewornJournalStructuresListScreen());
			this.scrollOffset = pScrollOffset;
		}

		@Override
		protected void renderBackButton(@Nullable Screen backScreen) {
			this.addRenderableWidget(new ImageButton(super.getBgStartX() + 18,
					super.getBgStartY() + super.getBgHeight() - 36, 20, 20, BACK_BUTTON_SPRITES, p_308203_ -> {
						this.minecraft.setScreen(new TimewornJournalStructuresListScreen(scrollOffset));
					}));
		}

	}

	public static class TimewornJournalEntitiesEntryScreen extends BaseTimewornJournalEntryScreen {

		private int scrollOffset;

		public TimewornJournalEntitiesEntryScreen(BaseTimewornJournalEntry pEntry) {
			super(pEntry);
			this.scrollOffset = 0;
		}

		public TimewornJournalEntitiesEntryScreen(BaseTimewornJournalEntry pEntry, int pScrollOffset) {
			super(pEntry, pScrollOffset, new TimewornJournalListScreens.TimewornJournalEntitiesListScreen());
			this.scrollOffset = pScrollOffset;
		}

		@Override
		protected void renderBackButton(@Nullable Screen backScreen) {
			this.addRenderableWidget(new ImageButton(super.getBgStartX() + 18,
					super.getBgStartY() + super.getBgHeight() - 36, 20, 20, BACK_BUTTON_SPRITES, p_308203_ -> {
						this.minecraft.setScreen(new TimewornJournalEntitiesListScreen(scrollOffset));
					}));
		}

	}

	public static class TimewornJournalItemsEntryScreen extends BaseTimewornJournalEntryScreen {

		private int scrollOffset;
		
		private ItemEntry entry;

		public TimewornJournalItemsEntryScreen(ItemEntry pEntry) {
			super(pEntry);
			this.scrollOffset = 0;
			entry = pEntry;
			
		}

		public TimewornJournalItemsEntryScreen(ItemEntry pEntry, int pScrollOffset) {
			super(pEntry, pScrollOffset, new TimewornJournalListScreens.TimewornJournalItemsListScreen());
			this.scrollOffset = pScrollOffset;
			entry = pEntry;
		}

		@Override
		protected void renderBackButton(@Nullable Screen backScreen) {
			this.addRenderableWidget(new ImageButton(super.getBgStartX() + 18,
					super.getBgStartY() + super.getBgHeight() - 36, 20, 20, BACK_BUTTON_SPRITES, p_308203_ -> {
						this.minecraft.setScreen(new TimewornJournalItemsListScreen(scrollOffset));
					}));
		}
		
		@Override
		public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
			super.render(guiGraphics, mouseX, mouseY, partialTick);
			if (entry != null) {

				 	ItemStack stack = entry.getItem();
				    super.renderItem(guiGraphics, stack);
			}
		}

	}

	public static class TimewornJournalBlocksEntryScreen extends BaseTimewornJournalEntryScreen {

		private int scrollOffset;

		private ItemEntry entry;

		public TimewornJournalBlocksEntryScreen(ItemEntry pEntry) {
			super(pEntry);
			this.scrollOffset = 0;
			entry = pEntry;
		}

		public TimewornJournalBlocksEntryScreen(ItemEntry pEntry, int pScrollOffset) {
			super(pEntry, pScrollOffset, new TimewornJournalListScreens.TimewornJournalBlocksListScreen());
			this.scrollOffset = pScrollOffset;
			entry = pEntry;
		}

		@Override
		protected void renderBackButton(@Nullable Screen backScreen) {
			this.addRenderableWidget(new ImageButton(super.getBgStartX() + 18,
					super.getBgStartY() + super.getBgHeight() - 36, 20, 20, BACK_BUTTON_SPRITES, p_308203_ -> {
						this.minecraft.setScreen(new TimewornJournalBlocksListScreen(scrollOffset));
					}));
		}

		@Override
		public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
			super.render(guiGraphics, mouseX, mouseY, partialTick);
			if (entry != null) {

				 	ItemStack stack = entry.getItem();
				    super.renderItem(guiGraphics, stack);
			}
		}
	}
}
