package net.je.screen.timeworn_journal.entry.renderer;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFW;

import net.je.screen.timeworn_journal.BaseTimewornJournalScreen;
import net.je.screen.timeworn_journal.entry.BaseTimewornJournalEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;

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

	public BaseTimewornJournalEntryScreen(BaseTimewornJournalEntry pEntry) {
		super();
		entry = pEntry;
	}

	@Override
	public void init() {
		super.init();
		loreX = super.getBgStartX() + (int) (Math.round(super.getBgWidth() - 50) * 0.3) + 90;
		loreY = super.getBgStartY() + 45;
		loreScrollbarX = loreX + 130;
	}

	@Override
	public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {

		if (pKeyCode == GLFW.GLFW_KEY_UP || pKeyCode == GLFW.GLFW_KEY_DOWN) {
			List<String> loreLines = wrapText(font, entry.getLore(), 125);
			loreScrollOffset = Mth.clamp(loreScrollOffset + (pKeyCode == GLFW.GLFW_KEY_UP ? -1 : 1), 0,
					Math.max(0, loreLines.size() - maxVisibleLoreLines));
			return true;
		} else if (pKeyCode == GLFW.GLFW_KEY_PAGE_UP || pKeyCode == GLFW.GLFW_KEY_PAGE_DOWN) {

			List<String> loreLines = wrapText(font, entry.getLore(), 125);
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
		List<String> loreLines = wrapText(font, entry.getLore(), 125);
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
		List<String> loreLines = wrapText(font, entry.getLore(), 125);
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
			List<String> loreLines = wrapText(font, entry.getLore(), 125);
			int loreHeight = 120;
			int thumbHeight = Math.max(10, (int) ((maxVisibleLoreLines / (float) loreLines.size()) * loreHeight));
			float deltaY = (int) mouseY - loreDragStartY;
			float progressDelta = deltaY / (float) (loreHeight - thumbHeight);

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
		List<String> loreLines = wrapText(font, entry.getLore(), 125);
		int maxScroll = Math.max(0, loreLines.size() - maxVisibleLoreLines);
		loreScrollOffset -= (int) pScrollY;
		loreScrollOffset = Mth.clamp(loreScrollOffset, 0, maxScroll);
		return true;
	}

	public static List<String> wrapText(Font font, String text, int maxWidth) {
		List<String> wrappedLines = new ArrayList<>();
		if (text == null || text.isEmpty())
			return wrappedLines;

		// Split into words
		String[] words = text.split("\\s+");
		StringBuilder currentLine = new StringBuilder();

		for (String word : words) {
			String testLine = currentLine.isEmpty() ? word : currentLine + " " + word;

			if (font.width(testLine) > maxWidth) {
				// Line too long, commit currentLine and start new
				wrappedLines.add(currentLine.toString());
				currentLine = new StringBuilder(word);
			} else {
				currentLine = new StringBuilder(testLine);
			}
		}

		// Add remaining text
		if (!currentLine.isEmpty()) {
			wrappedLines.add(currentLine.toString());
		}

		return wrappedLines;
	}
}