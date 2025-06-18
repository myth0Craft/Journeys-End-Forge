package net.je.screen.timeworn_journal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.je.screen.timeworn_journal.entry.BaseTimewornJournalEntry;
import net.je.screen.timeworn_journal.entry.renderer.BaseTimewornJournalEntryScreen;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

public class TimewornJournalScrollableScreen extends BaseTimewornJournalScreen {
	private final List<Button> allButtons = new ArrayList<>();
	private List<Button> filteredButtons = new ArrayList<>();

	private List<BaseTimewornJournalEntry> allEntries = new ArrayList<>();
	// private BaseTimewornJournalEntry selectedEntry = null;

	private final Map<Button, BaseTimewornJournalEntry> buttonEntryMap = new HashMap<>();

	private EditBox searchField;
	protected int scrollOffset = 0;
	private boolean draggingScrollbar = false;
	// private int lastMouseY = 0;

	private int listTop;
	private int listHeight;
	private int listLeft;
	private int listRight;
	private int listWidth;
	private final int buttonHeight = 18;
	private final int maxVisibleButtons = 8;
	private int dragStartY = 0;
	private float initialThumbProgress = 0f;

	private int searchBoxY;
	private int searchWidth;
	private int searchHeight = 16;

	private String oldSearchValue = "";

	// private int buttonCount;

	public TimewornJournalScrollableScreen(List<BaseTimewornJournalEntry> pList) {
		super();
		allEntries = pList;
		// this.buttonCount = allEntries.size();

	}

	@Override
	protected void init() {
		super.init();
		listTop = super.getBgStartY() + 40;
		listLeft = super.getBgStartX() + 40;
		searchBoxY = listTop - 20;

		listWidth = (int) (Math.round(super.getBgWidth() - 50) * 0.3);
		searchWidth = listWidth + 10;

		listRight = listLeft + listWidth;
		listHeight = buttonHeight * maxVisibleButtons;

		this.searchField = new TimewornJournalEditBox(font, listLeft - 5, searchBoxY, searchWidth, searchHeight,
				Component.literal("Search"));
		this.searchField.setResponder(s -> updateFilteredButtons());
		this.searchField.setHint(Component.literal("Search").withStyle(ChatFormatting.ITALIC));
		this.addRenderableWidget(searchField);

		/*
		 * for (int i = 0; i < buttonCount; i++) { int index = i; int buttonDisplayNum =
		 * i + 1; allButtons.add(new TimewornJournalButton(listLeft, 0, listWidth,
		 * buttonHeight, Component.literal(entryList.get(i).getName()), btn ->
		 * this.onButtonClicked(index))); }
		 */

		for (BaseTimewornJournalEntry entry : allEntries) {

			TimewornJournalButton button = new TimewornJournalButton(listLeft, 0, listWidth, buttonHeight,
					entry.getName(), b -> this.onButtonClicked(entry)

			// {this.minecraft.setScreen(new BaseTimewornJournalEntryScreen(entry));}
			);

			allButtons.add(button);
			buttonEntryMap.put(button, entry);

		}

		updateFilteredButtons();

		super.renderBackButton(new TimewornJournalHomeScreen());
		// this.renderBackButton(new TimewornJournalHomeScreen());
	}

	protected void onButtonClicked(BaseTimewornJournalEntry pEntry) {
		// this.minecraft.setScreen(new BaseTimewornJournalEntryScreen(pEntry));
	}

	private void updateFilteredButtons() {
		String search = searchField.getValue().toLowerCase();

		filteredButtons = allButtons.stream().filter(btn -> btn.getMessage().getString().toLowerCase().contains(search))
				.collect(Collectors.toList());

		// scrollOffset = 0;

		draggingScrollbar = false;

		if (search != oldSearchValue) {
			scrollOffset = 0;
			oldSearchValue = search;
		}
	}

	@Override
	public boolean mouseScrolled(double pMouseX, double pMouseY, double pScrollX, double pScrollY) {
		int maxScroll = Math.max(0, filteredButtons.size() - maxVisibleButtons);
		scrollOffset -= (int) pScrollY;
		scrollOffset = Mth.clamp(scrollOffset, 0, maxScroll);
		return true;
	}

	@Override
	public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {

		if (pKeyCode == GLFW.GLFW_KEY_UP || pKeyCode == GLFW.GLFW_KEY_DOWN) {

			scrollOffset = Mth.clamp(scrollOffset + (pKeyCode == GLFW.GLFW_KEY_UP ? -1 : 1), 0,
					Math.max(0, filteredButtons.size() - maxVisibleButtons));
			return true;
		} else if (pKeyCode == GLFW.GLFW_KEY_PAGE_UP || pKeyCode == GLFW.GLFW_KEY_PAGE_DOWN) {

			scrollOffset = Mth.clamp(scrollOffset + (pKeyCode == GLFW.GLFW_KEY_PAGE_UP ? -1 : 1) * maxVisibleButtons, 0,
					Math.max(0, filteredButtons.size() - maxVisibleButtons));
			return true;

		}

		return super.keyPressed(pKeyCode, pScanCode, pModifiers);
	}

	@Override
	public void render(GuiGraphics pGuiGraphics, int mouseX, int mouseY, float partialTick) {
		super.render(pGuiGraphics, mouseX, mouseY, partialTick);
		searchField.render(pGuiGraphics, mouseX, mouseY, partialTick);

		int visibleStart = scrollOffset;
		int visibleEnd = Math.min(scrollOffset + maxVisibleButtons, filteredButtons.size());

		for (int i = visibleStart; i < visibleEnd; i++) {
			Button btn = filteredButtons.get(i);
			btn.setX(listLeft);
			btn.setY(listTop + (i - visibleStart) * (buttonHeight + 1));
			btn.render(pGuiGraphics, mouseX, mouseY, partialTick);
		}

		// Scrollbar
		int scrollbarX = listRight + 4;
		int scrollbarY = listTop;
		int scrollbarHeight = listHeight;
		int totalButtons = filteredButtons.size();
		if (totalButtons > maxVisibleButtons) {
			float ratio = maxVisibleButtons / (float) totalButtons;
			int thumbHeight = Math.max(10, (int) (ratio * scrollbarHeight));
			float thumbProgress = scrollOffset / (float) (totalButtons - maxVisibleButtons);
			int thumbY = scrollbarY + (int) (thumbProgress * (scrollbarHeight - thumbHeight));

			boolean mouseOverThumb = mouseX >= scrollbarX && mouseX < scrollbarX + 6 && mouseY >= thumbY
					&& mouseY < thumbY + thumbHeight;
			int thumbColor = draggingScrollbar || mouseOverThumb ? 0xffa38b48 : 0xffb5a070;

			pGuiGraphics.fill(scrollbarX, scrollbarY, scrollbarX + 6, scrollbarY + scrollbarHeight, 0xffd1c196);
			pGuiGraphics.fill(scrollbarX, thumbY, scrollbarX + 6, thumbY + thumbHeight, thumbColor);
		}
		
		
		
		
		
		/*
		 * ItemStack item = new ItemStack(Blocks.DIAMOND_BLOCK);
		 * 
		 * pGuiGraphics.renderItem(item, super.getBgStartX(), super.getBgStartY());
		 * pGuiGraphics.renderItemDecorations(Minecraft.getInstance().font, item,
		 * super.getBgStartX(), super.getBgStartY());
		 */

	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		if (button == 0) {
			if (!(mouseX >= listLeft && mouseX < listLeft + searchWidth && mouseY >= searchBoxY
					&& mouseY < searchBoxY + searchHeight)) {
				searchField.setFocused(false);
			}
			int scrollbarX = listRight + 4;
			int scrollbarY = listTop;
			int scrollbarHeight = listHeight;
			int totalButtons = filteredButtons.size();
			if (totalButtons > maxVisibleButtons) {
				int thumbHeight = Math.max(10, (int) ((maxVisibleButtons / (float) totalButtons) * scrollbarHeight));
				float thumbProgress = scrollOffset / (float) (totalButtons - maxVisibleButtons);
				int thumbY = scrollbarY + (int) (thumbProgress * (scrollbarHeight - thumbHeight));

				if (mouseX >= scrollbarX && mouseX < scrollbarX + 6 && mouseY >= thumbY
						&& mouseY < thumbY + thumbHeight) {
					draggingScrollbar = true;
					dragStartY = (int) mouseY;
					initialThumbProgress = thumbProgress;
					return true;
				}
			}

			int visibleStart = scrollOffset;
			int visibleEnd = Math.min(scrollOffset + maxVisibleButtons, filteredButtons.size());

			for (int i = visibleStart; i < visibleEnd; i++) {
				Button btn = filteredButtons.get(i);
				if (btn.mouseClicked(mouseX, mouseY, button)) {
					BaseTimewornJournalEntry entry = buttonEntryMap.get(btn);
					this.onButtonClicked(entry);
					return true;
				}
			}

		}

		return super.mouseClicked(mouseX, mouseY, button);

	}

	@Override
	public boolean mouseDragged(double mouseX, double mouseY, int button, double dx, double dy) {
		if (draggingScrollbar) {
			int totalButtons = filteredButtons.size();
			if (totalButtons <= maxVisibleButtons)
				return true;

			int scrollbarHeight = listHeight;
			int thumbHeight = Math.max(10, (int) ((maxVisibleButtons / (float) totalButtons) * scrollbarHeight));
			float deltaY = (int) mouseY - dragStartY;
			float progressDelta = deltaY / (float) (scrollbarHeight - thumbHeight);

			float newThumbProgress = initialThumbProgress + progressDelta;
			newThumbProgress = Mth.clamp(newThumbProgress, 0f, 1f);

			scrollOffset = (int) (newThumbProgress * (totalButtons - maxVisibleButtons));
			return true;
		}

		return super.mouseDragged(mouseX, mouseY, button, dx, dy);
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		draggingScrollbar = false;
		return super.mouseReleased(mouseX, mouseY, button);
	}
}