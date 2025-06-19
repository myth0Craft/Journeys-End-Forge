package net.je.screen.timeworn_journal.entry.renderer;

import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;

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
import net.je.screen.timeworn_journal.TimewornJournalScrollableScreen;
import net.je.screen.timeworn_journal.entry.BaseTimewornJournalEntry;
import net.je.screen.timeworn_journal.entry.EntityEntry;
import net.je.screen.timeworn_journal.entry.ImageEntry;
import net.je.screen.timeworn_journal.entry.ItemEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

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

		public TimewornJournalEntityEntryScreen(EntityEntry pEntry, TimewornJournalScrollableScreen listScreen) {
			super(pEntry);
			this.scrollOffset = 0;
			entry = pEntry;
			this.backScreen = listScreen;
			this.backScreen.setScrollOffset(scrollOffset);
			entity = (LivingEntity) entry.getEntity();
		}

		public TimewornJournalEntityEntryScreen(EntityEntry pEntry, int pScrollOffset,
				TimewornJournalScrollableScreen listScreen) {
			super(pEntry, pScrollOffset, listScreen);
			this.scrollOffset = pScrollOffset;
			entry = pEntry;
			this.backScreen = listScreen;
			this.backScreen.setScrollOffset(scrollOffset);
			entity = (LivingEntity) entry.getEntity();

		}

		@Override
		protected void renderBackButton(@Nullable Screen pBackScreen) {
			this.addRenderableWidget(new ImageButton(super.getBgStartX() + 18,
					super.getBgStartY() + super.getBgHeight() - 36, 20, 20, BACK_BUTTON_SPRITES, p_308203_ -> {
						this.minecraft.setScreen(backScreen);
					}));
		}

		public static void renderEntityInGui(PoseStack poseStack, int x, int y, float scale, float mouseX, float mouseY,
				LivingEntity entity) {
			Minecraft mc = Minecraft.getInstance();
			EntityRenderDispatcher dispatcher = mc.getEntityRenderDispatcher();
			MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();

			poseStack.pushPose();
			poseStack.translate(x, y, 50); // Position entity on screen
			poseStack.scale(-scale, scale, scale); // Flip for GUI

			float yaw = 210f;
			float pitch = 25f;

			// Save original rotations
			float bodyRot = entity.yBodyRot;
			float yRot = entity.getYRot();
			float xRot = entity.getXRot();

			entity.yBodyRot = yaw;
			entity.setYRot(yaw);
			entity.setXRot(-pitch); // Negative to tilt downward

			dispatcher.setRenderShadow(false);
			dispatcher.render(entity, 0, 0, 0, 0, 1.0f, poseStack, buffer, 15728880);
			buffer.endBatch();
			dispatcher.setRenderShadow(true);

			// Restore original rotations
			entity.yBodyRot = bodyRot;
			entity.setYRot(yRot);
			entity.setXRot(xRot);

			poseStack.popPose();
		}

		@Override
		public void init() {
			super.init();
			this.entity.yBodyRot = 210.0F;
			this.entity.setXRot(25.0F);
			this.entity.yHeadRot = this.entity.getYRot();
			this.entity.yHeadRotO = this.entity.getYRot();
		}

		@Override
		public void render(GuiGraphics pGuiGraphics, int mouseX, int mouseY, float partialTick) {
			super.render(pGuiGraphics, mouseX, mouseY, partialTick);

			InventoryScreen.renderEntityInInventory(pGuiGraphics, this.width / 2, this.height / 2, 25.0F,
					new Vector3f(), new Quaternionf().rotationXYZ(0.43633232F, 0.0F, (float) Math.PI), null,
					this.entity);
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
