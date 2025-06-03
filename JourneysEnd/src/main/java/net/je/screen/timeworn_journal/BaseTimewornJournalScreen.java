package net.je.screen.timeworn_journal;

import org.jetbrains.annotations.Nullable;

import net.je.JourneysEnd;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class BaseTimewornJournalScreen extends Screen {

	public static final ResourceLocation BG_LOCATION = ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
			"textures/gui/timeworn_journal_bg.png");

	private int bgStartX;
	private int bgStartY;
	
	private int bookWidth;
	private int bookHeight;
	
	private int centerX;
	private int centerY;

	private int xOffset;
	private int yOffset;
	
	public static final WidgetSprites BACK_BUTTON_SPRITES = new WidgetSprites(
			ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
					"timeworn_journal/timeworn_journal_back_button"),
			ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
					"timeworn_journal/timeworn_journal_back_button_highlighted"));

	public BaseTimewornJournalScreen() {
		super(Component.translatable("screen.je.timeworn_journal_title"));
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	@Override
	protected void init() {
		super.init();
		
		bookWidth = 400;
		bookHeight = 216;
		
		centerX = this.width / 2;
		centerY = this.height / 2;

		xOffset = Math.round(bookWidth / 11);
		yOffset = 10;

		bgStartX = centerX - ((bookWidth - xOffset) / 2);
		bgStartY = centerY - (bookHeight / 2) - yOffset;
		
		//this.renderBackButton(null);
	}

	@Override
	public void renderBackground(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
		this.renderTransparentBackground(pGuiGraphics);
		pGuiGraphics.blit(BG_LOCATION, bgStartX, bgStartY, 0, 0, 0, bookWidth, bookHeight, bookWidth, bookHeight);
	}
	
	protected void renderBackButton(@Nullable Screen backScreen) {
		this.addRenderableWidget(new ImageButton(bgStartX + 18, bgStartY + bookHeight - 36, 20, 20, BACK_BUTTON_SPRITES, p_308203_ -> {
			this.minecraft.setScreen(backScreen);
		}));
	}

	protected int getBgStartX() {
		return bgStartX;
	}

	protected int getBgStartY() {
		return bgStartY;
	}

	protected int getBgEndX() {
		return bgStartX + bookWidth;
	}

	protected int getBgEndY() {
		return bgStartY + bookHeight;
	}
	
	protected int getBgWidth() {
		return bookWidth;
	}
	
	protected int getBgHeight() {
		return bookHeight;
	}
}
