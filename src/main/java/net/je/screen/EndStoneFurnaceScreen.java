package net.je.screen;

import net.je.JourneysEnd;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;

public class EndStoneFurnaceScreen extends AbstractContainerScreen<EndStoneFurnaceMenu> {
	// private static final ResourceLocation LIT_PROGRESS_SPRITE =
	// ResourceLocation.withDefaultNamespace("container/blast_furnace/lit_progress");

	private static final ResourceLocation LIT_PROGRESS_SPRITE = ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
			"container/end_stone_furnace_flame");
	private static final ResourceLocation BURN_PROGRESS_SPRITE = ResourceLocation
			.withDefaultNamespace("container/blast_furnace/burn_progress");
	private static final ResourceLocation TEXTURE = ResourceLocation
			.withDefaultNamespace("textures/gui/container/blast_furnace.png");

	public EndStoneFurnaceScreen(EndStoneFurnaceMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
		super(pMenu, pPlayerInventory, pTitle);

	}

	@Override
	public void init() {
		super.init();
		this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
	}

	@Override
	public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {

		super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

		this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
	}

	@Override
	protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
		int i = this.leftPos;
		int j = this.topPos;
		pGuiGraphics.blit(TEXTURE, i, j, 0, 0, this.imageWidth, this.imageHeight);
		if (this.menu.isLit()) {
			int l = Mth.ceil(this.menu.getLitProgress() * 13.0F) + 1;
			pGuiGraphics.blitSprite(LIT_PROGRESS_SPRITE, 14, 14, 0, 14 - l, i + 56, j + 36 + 14 - l, 14, l);
		}

		int j1 = Mth.ceil(this.menu.getBurnProgress() * 24.0F);
		pGuiGraphics.blitSprite(BURN_PROGRESS_SPRITE, 24, 16, 0, 0, i + 79, j + 34, j1, 16);
	}


	@Override
	protected void slotClicked(Slot pSlot, int pSlotId, int pMouseButton, ClickType pType) {
		super.slotClicked(pSlot, pSlotId, pMouseButton, pType);
	}

	@Override
	protected boolean hasClickedOutside(double pMouseX, double pMouseY, int pGuiLeft, int pGuiTop, int pMouseButton) {
		boolean flag = pMouseX < pGuiLeft || pMouseY < pGuiTop
				|| pMouseX >= pGuiLeft + this.imageWidth || pMouseY >= pGuiTop + this.imageHeight;
		return flag;
	}
}