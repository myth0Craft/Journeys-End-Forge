package net.je.screen.timeworn_journal.entry.renderer;

import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import net.je.screen.timeworn_journal.TimewornJournalScrollableScreen;
import net.je.screen.timeworn_journal.entry.BaseTimewornJournalEntry;
import net.je.screen.timeworn_journal.entry.EntityEntry;
import net.je.screen.timeworn_journal.entry.ImageEntry;
import net.je.screen.timeworn_journal.entry.ItemEntry;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class TimewornJournalEntryScreens {

	public static class TimewornJournalEmptyEntryScreen extends BaseTimewornJournalEntryScreen {

		private int scrollOffset;

		private TimewornJournalScrollableScreen backScreen;

		public TimewornJournalEmptyEntryScreen(BaseTimewornJournalEntry pEntry,
				TimewornJournalScrollableScreen listScreen) {
			super(pEntry);
			this.scrollOffset = 0;
			this.backScreen = listScreen;
			this.backScreen.setScrollOffset(scrollOffset);
		}

		public TimewornJournalEmptyEntryScreen(BaseTimewornJournalEntry pEntry, int pScrollOffset,
				TimewornJournalScrollableScreen listScreen) {
			super(pEntry, pScrollOffset, listScreen);
			this.scrollOffset = pScrollOffset;
			this.backScreen = listScreen;
			this.backScreen.setScrollOffset(scrollOffset);
		}

		@Override
		protected void renderBackButton(@Nullable Screen pBackScreen) {
			this.addRenderableWidget(new ImageButton(super.getBgStartX() + 18,
					super.getBgStartY() + super.getBgHeight() - 36, 20, 20, BACK_BUTTON_SPRITES, p_308203_ -> {
						this.minecraft.setScreen(backScreen);
					}));
		}

	}

	public static class TimewornJournalImageEntryScreen extends BaseTimewornJournalEntryScreen {

		private int scrollOffset;

		private ImageEntry entry;

		private boolean shouldRenderFrame;

		private TimewornJournalScrollableScreen backScreen;

		public TimewornJournalImageEntryScreen(ImageEntry pEntry, boolean renderFrame,
				TimewornJournalScrollableScreen listScreen) {
			super(pEntry);
			this.scrollOffset = 0;
			entry = pEntry;
			shouldRenderFrame = renderFrame;
			this.backScreen = listScreen;
			this.backScreen.setScrollOffset(scrollOffset);
		}

		public TimewornJournalImageEntryScreen(ImageEntry pEntry, int pScrollOffset, boolean renderFrame,
				TimewornJournalScrollableScreen listScreen) {
			super(pEntry, pScrollOffset, listScreen);
			this.scrollOffset = pScrollOffset;
			entry = pEntry;
			shouldRenderFrame = renderFrame;
			this.backScreen = listScreen;
			this.backScreen.setScrollOffset(scrollOffset);
		}

		@Override
		protected void renderBackButton(@Nullable Screen pBackScreen) {
			this.addRenderableWidget(new ImageButton(super.getBgStartX() + 18,
					super.getBgStartY() + super.getBgHeight() - 36, 20, 20, BACK_BUTTON_SPRITES, p_308203_ -> {
						this.minecraft.setScreen(backScreen);
					}));
		}

		@Override
		public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
			super.render(guiGraphics, mouseX, mouseY, partialTick);
			if (entry != null) {

				ResourceLocation sprite = entry.getImage();
				if (shouldRenderFrame) {
					super.renderImageFrame(guiGraphics);
				}
				super.renderItem(guiGraphics, sprite);

			}
		}

	}

	public static class TimewornJournalEntityEntryScreen extends BaseTimewornJournalEntryScreen {

		private int scrollOffset;

		private LivingEntity entity;

		private EntityEntry entry;

		private TimewornJournalScrollableScreen backScreen;

		private float scale;

		public TimewornJournalEntityEntryScreen(EntityEntry pEntry, TimewornJournalScrollableScreen listScreen) {
			super(pEntry);
			this.scrollOffset = 0;
			entry = pEntry;
			this.backScreen = listScreen;
			this.backScreen.setScrollOffset(scrollOffset);
			entity = (LivingEntity) entry.getEntity();
			this.scale = entry.getScale();

		}

		public TimewornJournalEntityEntryScreen(EntityEntry pEntry, int pScrollOffset,
				TimewornJournalScrollableScreen listScreen) {
			super(pEntry, pScrollOffset, listScreen);
			this.scrollOffset = pScrollOffset;
			entry = pEntry;
			this.backScreen = listScreen;
			this.backScreen.setScrollOffset(scrollOffset);
			entity = (LivingEntity) entry.getEntity();
			this.scale = entry.getScale();

		}

		@Override
		protected void renderBackButton(@Nullable Screen pBackScreen) {
			this.addRenderableWidget(new ImageButton(super.getBgStartX() + 18,
					super.getBgStartY() + super.getBgHeight() - 36, 20, 20, BACK_BUTTON_SPRITES, p_308203_ -> {
						this.minecraft.setScreen(backScreen);
					}));
		}

		@Override
		public void init() {

			super.init();

			float bodyYaw = 210F;

			entity.yBodyRot = bodyYaw;
			entity.setYRot(bodyYaw);
			entity.setYHeadRot(bodyYaw);

		}

		@Override
		public void render(GuiGraphics pGuiGraphics, int mouseX, int mouseY, float partialTick) {
			super.render(pGuiGraphics, mouseX, mouseY, partialTick);


			int x = (int) ((this.width / 2) - super.getBgWidth() * 0.21);
			int y = (int)(super.getBgStartY() + super.getBgHeight() / 2 + entry.getYOffset() * super.getBgHeight());
			InventoryScreen.renderEntityInInventory(pGuiGraphics, x, y, this.scale,
					new Vector3f(), new Quaternionf().rotationXYZ(0.43633232F, 0.0F, (float) Math.PI), null, entity);
		}

	}

	public static class TimewornJournalBlockEntryScreen extends BaseTimewornJournalEntryScreen {

		private int scrollOffset;

		private ItemEntry entry;

		private ImageEntry imageEntry;

		private TimewornJournalScrollableScreen backScreen;

		public TimewornJournalBlockEntryScreen(ItemEntry pEntry, TimewornJournalScrollableScreen listScreen) {
			super(pEntry);
			this.scrollOffset = 0;
			entry = pEntry;
			this.backScreen = listScreen;
			this.backScreen.setScrollOffset(scrollOffset);
		}

		public TimewornJournalBlockEntryScreen(ItemEntry pEntry, int pScrollOffset,
				TimewornJournalScrollableScreen listScreen) {
			super(pEntry, pScrollOffset, listScreen);
			this.scrollOffset = pScrollOffset;
			entry = pEntry;
			this.backScreen = listScreen;
			this.backScreen.setScrollOffset(scrollOffset);
		}

		public TimewornJournalBlockEntryScreen(ImageEntry pEntry, TimewornJournalScrollableScreen listScreen) {
			super(pEntry);
			this.scrollOffset = 0;
			imageEntry = pEntry;
			this.backScreen = listScreen;
			this.backScreen.setScrollOffset(scrollOffset);
		}

		public TimewornJournalBlockEntryScreen(ImageEntry pEntry, int pScrollOffset,
				TimewornJournalScrollableScreen listScreen) {
			super(pEntry, pScrollOffset, listScreen);
			this.scrollOffset = pScrollOffset;
			imageEntry = pEntry;
			this.backScreen = listScreen;
			this.backScreen.setScrollOffset(scrollOffset);
		}

		@Override
		protected void renderBackButton(@Nullable Screen pBackScreen) {
			this.addRenderableWidget(new ImageButton(super.getBgStartX() + 18,
					super.getBgStartY() + super.getBgHeight() - 36, 20, 20, BACK_BUTTON_SPRITES, p_308203_ -> {
						this.minecraft.setScreen(backScreen);
					}));
		}

		@Override
		public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
			super.render(guiGraphics, mouseX, mouseY, partialTick);
			if (entry != null) {

				ItemStack stack = entry.getItem();
				super.renderBlock(guiGraphics, stack);
			} else if (imageEntry != null) {
				ResourceLocation sprite = imageEntry.getImage();
				super.renderItem(guiGraphics, sprite);
			}
		}
	}
}
