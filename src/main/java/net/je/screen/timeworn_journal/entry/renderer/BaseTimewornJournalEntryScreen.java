package net.je.screen.timeworn_journal.entry.renderer;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.vertex.PoseStack;

import net.je.JourneysEnd;
import net.je.screen.timeworn_journal.BaseTimewornJournalScreen;
import net.je.screen.timeworn_journal.entry.BaseTimewornJournalEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class BaseTimewornJournalEntryScreen extends BaseTimewornJournalScreen {

	private int loreX;
	private int loreY;

	private int loreScrollOffset = 0;
	private final int loreLineHeight = 10;
	private final int maxVisibleLoreLines = 14;
	private boolean draggingLoreScrollbar = false;
	private int loreDragStartY = 0;
	private float initialLoreThumbProgress = 0f;

	private int loreScrollbarX;

	protected BaseTimewornJournalEntry entry;

	@SuppressWarnings("unused")
	private int scrollOffset;

	private Screen backScreen;

	public BaseTimewornJournalEntryScreen(BaseTimewornJournalEntry pEntry) {
		super();
		this.scrollOffset = 0;
		entry = pEntry;
	}

	public BaseTimewornJournalEntryScreen(BaseTimewornJournalEntry pEntry, int pScrollOffset,
			@Nullable Screen pBackScreen) {
		super();
		this.scrollOffset = pScrollOffset;
		entry = pEntry;
	}

	@Override
	public void init() {
		super.init();
		loreX = super.getBgStartX() + (int) (Math.round(super.getBgWidth() - 50) * 0.3) + 90;
		loreY = super.getBgStartY() + 45;
		loreScrollbarX = loreX + 130;
		this.renderBackButton(backScreen);

	}

	@Override
	public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {

		if (pKeyCode == GLFW.GLFW_KEY_UP || pKeyCode == GLFW.GLFW_KEY_DOWN) {
			List<String> loreLines = wrapText(font, entry.getLore().getString(), 125);
			loreScrollOffset = Mth.clamp(loreScrollOffset + (pKeyCode == GLFW.GLFW_KEY_UP ? -1 : 1), 0,
					Math.max(0, loreLines.size() - maxVisibleLoreLines));
			return true;
		} else if (pKeyCode == GLFW.GLFW_KEY_PAGE_UP || pKeyCode == GLFW.GLFW_KEY_PAGE_DOWN) {

			List<String> loreLines = wrapText(font, entry.getLore().getString(), 125);
			loreScrollOffset = Mth.clamp(
					loreScrollOffset + (pKeyCode == GLFW.GLFW_KEY_PAGE_UP ? -1 : 1) * maxVisibleLoreLines, 0,
					Math.max(0, loreLines.size() - maxVisibleLoreLines));
			return true;
		}

		return super.keyPressed(pKeyCode, pScanCode, pModifiers);
	}

	@Override
	public void render(GuiGraphics pGuiGraphics, int mouseX, int mouseY, float partialTick) {
		super.render(pGuiGraphics, mouseX, mouseY, partialTick);
		List<String> loreLines = wrapText(font, entry.getLore().getString(), 125);
		int loreHeight = 120;

		int visibleStartLore = loreScrollOffset;
		int visibleEndLore = Math.min(loreLines.size(), loreScrollOffset + maxVisibleLoreLines);

		pGuiGraphics.drawString(font, entry.getName(), loreX, loreY - 20, 0x000000, false);

		for (int i = visibleStartLore; i < visibleEndLore; i++) {
			pGuiGraphics.drawString(font, loreLines.get(i), loreX, loreY + (i - visibleStartLore) * loreLineHeight,
					0x000000, false);
		}

		// Draw scrollbar
		if (loreLines.size() > maxVisibleLoreLines) {
			float ratio = maxVisibleLoreLines / (float) loreLines.size();
			int thumbHeight = Math.max(10, (int) (ratio * loreHeight));
			float thumbProgress = loreScrollOffset / (float) (loreLines.size() - maxVisibleLoreLines);
			int thumbY = loreY + (int) (thumbProgress * (loreHeight - thumbHeight));
			boolean mouseOverThumb = mouseX >= loreScrollbarX && mouseX < loreScrollbarX + 6 && mouseY >= thumbY
					&& mouseY < thumbY + thumbHeight;
			int scrollbarColor = draggingLoreScrollbar || mouseOverThumb ? 0xffa38b48 : 0xffb5a070;

			pGuiGraphics.fill(loreScrollbarX, loreY, loreScrollbarX + 6, loreY + loreHeight, 0xffd1c196);
			pGuiGraphics.fill(loreScrollbarX, thumbY, loreScrollbarX + 6, thumbY + thumbHeight, scrollbarColor);
		}
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		List<String> loreLines = wrapText(font, entry.getLore().getString(), 125);
		int loreHeight = 120;

		int thumbHeight = Math.max(10, (int) ((maxVisibleLoreLines / (float) loreLines.size()) * loreHeight));
		float thumbProgress = loreScrollOffset / (float) (loreLines.size() - maxVisibleLoreLines);
		int thumbY = loreY + (int) (thumbProgress * (loreHeight - thumbHeight));

		if (mouseX >= loreScrollbarX && mouseX < loreScrollbarX + 6 && mouseY >= thumbY
				&& mouseY < thumbY + thumbHeight) {
			draggingLoreScrollbar = true;
			loreDragStartY = (int) mouseY;
			initialLoreThumbProgress = thumbProgress;
			return true;
		}

		return super.mouseClicked(mouseX, mouseY, button);
	}

	@Override
	public boolean mouseDragged(double mouseX, double mouseY, int button, double dx, double dy) {
		if (draggingLoreScrollbar) {
			List<String> loreLines = wrapText(font, entry.getLore().getString(), 125);
			int loreHeight = 120;
			int thumbHeight = Math.max(10, (int) ((maxVisibleLoreLines / (float) loreLines.size()) * loreHeight));
			float deltaY = (int) mouseY - loreDragStartY;
			float progressDelta = deltaY / (loreHeight - thumbHeight);

			float newThumbProgress = initialLoreThumbProgress + progressDelta;
			newThumbProgress = Mth.clamp(newThumbProgress, 0f, 1f);

			loreScrollOffset = (int) (newThumbProgress * (loreLines.size() - maxVisibleLoreLines));
			return true;
		}
		return super.mouseDragged(mouseX, mouseY, button, dx, dy);
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		draggingLoreScrollbar = false;
		return super.mouseReleased(mouseX, mouseY, button);
	}

	@Override
	public boolean mouseScrolled(double pMouseX, double pMouseY, double pScrollX, double pScrollY) {
		List<String> loreLines = wrapText(font, entry.getLore().getString(), 125);
		int maxScroll = Math.max(0, loreLines.size() - maxVisibleLoreLines);
		loreScrollOffset -= (int) pScrollY;
		loreScrollOffset = Mth.clamp(loreScrollOffset, 0, maxScroll);
		return true;
	}

	public static List<String> wrapText(Font font, String text, int maxWidth) {
		List<String> wrappedLines = new ArrayList<>();
		if (text == null || text.isEmpty()) {
			return wrappedLines;
		}

		String[] words = text.split("\\s+");
		StringBuilder currentLine = new StringBuilder();

		for (String word : words) {
			String testLine = currentLine.isEmpty() ? word : currentLine + " " + word;

			if (font.width(testLine) > maxWidth) {
				wrappedLines.add(currentLine.toString());
				currentLine = new StringBuilder(word);
			} else {
				currentLine = new StringBuilder(testLine);
			}
		}

		if (!currentLine.isEmpty()) {
			wrappedLines.add(currentLine.toString());
		}

		return wrappedLines;
	}

	public void renderBlock(GuiGraphics guiGraphics, ItemStack stack) {
		float scale = 120.0f;

		int x = (int) ((this.width / 2) - super.getBgWidth() * 0.21);
		int y = (int) ((this.height / 2) - super.getBgHeight() * 0.05);

		guiGraphics.flush();

		PoseStack poseStack = guiGraphics.pose();
		poseStack.pushPose();

		poseStack.translate(x, y, 200);

		poseStack.scale(scale, -scale, scale);


		Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.GUI, 15728880,
				OverlayTexture.NO_OVERLAY, poseStack, guiGraphics.bufferSource(), null, 0);

		guiGraphics.flush();
		poseStack.popPose();
	}

	public void renderItem(GuiGraphics guiGraphics, ResourceLocation pTexture) {
		float scale = 7;
		int x = (int) ((this.width / 2) - super.getBgWidth() * 0.21 - (int) scale * 8);
		int y = (int) ((this.height / 2) - super.getBgHeight() * 0.05 - (int) scale * 8);
		guiGraphics.blit(pTexture, x, y, 0, 0, 0, (int) scale * 16, (int) scale * 16, (int) scale * 16,
				(int) scale * 16);

	}

	public void renderImageFrame(GuiGraphics guiGraphics) {
		float scale = 9;
		int x = (int) ((this.width / 2) - super.getBgWidth() * 0.21 - (int) scale * 8);
		int y = (int) ((this.height / 2) - super.getBgHeight() * 0.05 - (int) scale * 8);
		guiGraphics.blit(ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/gui/timeworn_journal_button_frame.png"), x, y, 0, 0, 0, (int) scale * 16, (int) scale * 16, (int) scale * 16,
				(int) scale * 16);
	}
}