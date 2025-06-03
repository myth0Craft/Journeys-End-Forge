package net.je.screen.timeworn_journal;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.je.JourneysEnd;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.gui.components.PlainTextButton;
import net.minecraft.client.gui.components.SpriteIconButton;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class TimewornJournalScrollableScreen extends BaseTimewornJournalScreen {

	private final List<Button> allButtons = new ArrayList<>();
	private int scrollOffset = 0;
	private int visibleButtonCount = 10;
	private int buttonHeight = 17;
	private int listTop;
	private int listLeft;
	private int listWidth;
	private int listHeight;
	private boolean isDraggingScrollbar = false;
	private int dragStartY = 0;
	private int initialScrollOffset = 0;

	public TimewornJournalScrollableScreen() {
		super();
	}

	@Override
	protected void init() {
		super.init();
		listTop = super.getBgStartY() + 20;
		listLeft = super.getBgStartX() + 40;

		listWidth = (int) (Math.round(super.getBgWidth() - 50) * 0.3);
		listHeight = buttonHeight * visibleButtonCount;

		allButtons.clear();

		for (int i = 0; i < 200; i++) {
			int finalI = i;
			allButtons.add(new TimewornJournalButton(listLeft, 0, listWidth, buttonHeight,
					Component.literal("Button " + i), btn -> onButtonClicked(finalI)));
		}
	}

	private void onButtonClicked(Integer buttonNum) {
		this.minecraft.setScreen(null);
	}

	@Override
	public boolean mouseScrolled(double pMouseX, double pMouseY, double pScrollX, double pScrollY) {
		int maxScroll = Math.max(0, allButtons.size() - visibleButtonCount);
		scrollOffset -= (int) pScrollY;
		scrollOffset = Mth.clamp(scrollOffset, 0, maxScroll);
		return true;
	}

	@Override
	public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
		int maxScroll = Math.max(0, allButtons.size() - visibleButtonCount);

		if (pKeyCode == GLFW.GLFW_KEY_UP) {
			scrollOffset = Mth.clamp(scrollOffset - 1, 0, maxScroll);
			return true;
		} else if (pKeyCode == GLFW.GLFW_KEY_DOWN) {
			scrollOffset = Mth.clamp(scrollOffset + 1, 0, maxScroll);
			return true;
		} else if (pKeyCode == GLFW.GLFW_KEY_PAGE_UP) {
			scrollOffset = Mth.clamp(scrollOffset - visibleButtonCount, 0, maxScroll);
			return true;
		} else if (pKeyCode == GLFW.GLFW_KEY_PAGE_DOWN) {
			scrollOffset = Mth.clamp(scrollOffset + visibleButtonCount, 0, maxScroll);
			return true;
		}
		return super.keyPressed(pKeyCode, pScanCode, pModifiers);
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {

		if (button == 0) {
			int scrollbarX = listLeft + listWidth + 2;
			int scrollbarY = getScrollbarY();
			int scrollbarWidth = 6;
			int thumbHeight = Math.max(10, (visibleButtonCount * listHeight) / allButtons.size());

			if (mouseX >= scrollbarX && mouseX <= scrollbarX + scrollbarWidth && mouseY >= scrollbarY
					&& mouseY <= scrollbarY + thumbHeight) {
				isDraggingScrollbar = true;
				dragStartY = (int) mouseY;
				initialScrollOffset = scrollOffset;
				return true;
			}
		}

		int start = scrollOffset;
		int end = Math.min(start + visibleButtonCount, allButtons.size());

		for (int i = start; i < end; i++) {
			if (allButtons.get(i).mouseClicked(mouseX, mouseY, button)) {
				return true;
			}
		}
		return super.mouseClicked(mouseX, mouseY, button);
	}

	@Override
	public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
		if (isDraggingScrollbar) {
			int trackHeight = listHeight;
			int thumbHeight = Math.max(10, (visibleButtonCount * trackHeight) / allButtons.size());
			int scrollableHeight = trackHeight - thumbHeight;

			int deltaY = (int) pMouseY - dragStartY;
			int maxScroll = Math.max(1, allButtons.size() - visibleButtonCount);

			int newOffset = initialScrollOffset + (deltaY * maxScroll) / scrollableHeight;
			scrollOffset = Mth.clamp(newOffset, 0, maxScroll);
			return true;
		}

		return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
	}

	@Override
	public boolean mouseReleased(double pMouseX, double pMouseY, int pButton) {
		if (pButton == 0) {
			isDraggingScrollbar = false;
		}

		int start = scrollOffset;
		int end = Math.min(start + visibleButtonCount, allButtons.size());
		for (int i = start; i < end; i++) {
			if (allButtons.get(i).mouseReleased(pMouseX, pMouseY, pButton)) {
				return true;
			}
		}

		return super.mouseReleased(pMouseX, pMouseY, pButton);
	}

	@Override
	public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
		super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

		int start = scrollOffset;
		int end = Math.min(start + visibleButtonCount, allButtons.size());

		for (int i = start; i < end; i++) {
			Button button = allButtons.get(i);
			button.setY(listTop + (i - start) * buttonHeight);
			button.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
		}

		this.renderScrollbar(pGuiGraphics, pMouseX, pMouseY);
	}

	private void renderScrollbar(GuiGraphics pGuiGraphics, int mouseX, int mouseY) {
		int scrollbarX = listLeft + listWidth + 2;
		int scrollbarWidth = 6;

		int thumbHeight = Math.max(10, (visibleButtonCount * listHeight) / allButtons.size());
		int scrollbarY = getScrollbarY();

		boolean mouseOverThumb = mouseX >= scrollbarX && mouseX < (scrollbarX + scrollbarWidth) && mouseY >= scrollbarY
				&& mouseY < (scrollbarY + thumbHeight);

		int thumbColor = mouseOverThumb ? 0xffa38b48 : 0xffb5a070; // lighter on hover


		// Draw track
		pGuiGraphics.fill(scrollbarX, listTop, scrollbarX + scrollbarWidth, listTop + listHeight, 0xffd1c196);

		// Draw thumb
		pGuiGraphics.fill(scrollbarX, scrollbarY, scrollbarX + scrollbarWidth, scrollbarY + thumbHeight, thumbColor);
	}

	private int getScrollbarY() {
		int trackHeight = listHeight;
		int thumbHeight = Math.max(10, (visibleButtonCount * trackHeight) / allButtons.size());
		int maxScroll = Math.max(1, allButtons.size() - visibleButtonCount);
		int scrollableHeight = trackHeight - thumbHeight;

		return listTop + (scrollOffset * scrollableHeight) / maxScroll;
	}
}