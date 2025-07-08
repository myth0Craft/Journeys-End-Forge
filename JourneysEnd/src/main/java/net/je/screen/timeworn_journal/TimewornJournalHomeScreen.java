package net.je.screen.timeworn_journal;

import net.je.JourneysEnd;
import net.je.screen.timeworn_journal.TimewornJournalListScreens.TimewornJournalBiomesListScreen;
import net.je.screen.timeworn_journal.TimewornJournalListScreens.TimewornJournalBlocksListScreen;
import net.je.screen.timeworn_journal.TimewornJournalListScreens.TimewornJournalEntitiesListScreen;
import net.je.screen.timeworn_journal.TimewornJournalListScreens.TimewornJournalItemsListScreen;
import net.je.screen.timeworn_journal.TimewornJournalListScreens.TimewornJournalProgressionListScreen;
import net.je.screen.timeworn_journal.TimewornJournalListScreens.TimewornJournalStructuresListScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;

public class TimewornJournalHomeScreen extends BaseTimewornJournalScreen {
	private int x1;
	private int x2;
	private int x3;
	private int x4;
	private int x5;

	private int y1;
	private int y2;

	private int tier;

	public static final WidgetSprites STORY_SPRITES = new WidgetSprites(
			ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
					"timeworn_journal/timeworn_journal_story_button_paper"),
			ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
					"timeworn_journal/timeworn_journal_story_button_highlighted"));

	public static final WidgetSprites PROGRESSION_SPRITES = new WidgetSprites(
			ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
					"timeworn_journal/timeworn_journal_progression_button_paper"),
			ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
					"timeworn_journal/timeworn_journal_progression_button_highlighted"));

	public static final WidgetSprites BIOMES_SPRITES = new WidgetSprites(
			ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
					"timeworn_journal/timeworn_journal_biomes_button_paper"),
			ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
					"timeworn_journal/timeworn_journal_biomes_button_highlighted"));

	public static final WidgetSprites STRUCTURES_SPRITES = new WidgetSprites(
			ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
					"timeworn_journal/timeworn_journal_structures_button_paper"),
			ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
					"timeworn_journal/timeworn_journal_structures_button_highlighted"));

	public static final WidgetSprites ENTITIES_SPRITES = new WidgetSprites(
			ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
					"timeworn_journal/timeworn_journal_entities_button_paper"),
			ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
					"timeworn_journal/timeworn_journal_entities_button_highlighted"));

	public static final WidgetSprites ITEMS_SPRITES = new WidgetSprites(
			ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
					"timeworn_journal/timeworn_journal_items_button_paper"),
			ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
					"timeworn_journal/timeworn_journal_items_button_highlighted"));

	public static final WidgetSprites BLOCKS_SPRITES = new WidgetSprites(
			ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
					"timeworn_journal/timeworn_journal_blocks_button_paper"),
			ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
					"timeworn_journal/timeworn_journal_blocks_button_highlighted"));


	public TimewornJournalHomeScreen(int pTier) {
		this.tier = pTier;
	}

	@Override
	protected void init() {
		super.init();
		super.renderBackButton(null);

		x1 = (int) Math.round(super.getBgStartX() + (super.getBgWidth() / 2 * 0.2));
		y1 = (int) Math.round(super.getBgStartY() + (super.getBgHeight() * 0.15));

		x2 = (int) Math.round(super.getBgStartX() + (super.getBgWidth() / 2 * 0.5));
		y2 = (int) Math.round(super.getBgStartY() + (super.getBgHeight() * 0.5));

		x3 = (int) Math.round(super.getBgStartX() + (super.getBgWidth()/2) + super.getBgWidth()/2 * 0.05);

		x4 = (int) Math.round(super.getBgStartX() + (super.getBgWidth()/2) + super.getBgWidth()/2 * 0.35);

		x5 = (int) Math.round(super.getBgStartX() + (super.getBgWidth() / 2 * 0.2) + super.getBgWidth() / 2);

		this.addRenderableWidget(new ImageButton(x1, y1, 48, 48, STORY_SPRITES, p_308203_ -> {


			this.minecraft.setScreen(new TimewornJournalListScreens.TimewornJournalChapterListScreen(tier));
		}));

		this.addRenderableWidget(new ImageButton(x2, y1, 48, 48, PROGRESSION_SPRITES, p_308203_ -> {
			this.minecraft.setScreen(new TimewornJournalProgressionListScreen(tier));
		}));

		this.addRenderableWidget(new ImageButton(x1, y2, 48, 48, BIOMES_SPRITES, p_308203_ -> {
			this.minecraft.setScreen(new TimewornJournalBiomesListScreen(tier));
		}));

		this.addRenderableWidget(new ImageButton(x2, y2, 48, 48, STRUCTURES_SPRITES, p_308203_ -> {
			this.minecraft.setScreen(new TimewornJournalStructuresListScreen(tier));
		}));

		this.addRenderableWidget(new ImageButton(x3, y1, 48, 48,
				ENTITIES_SPRITES, p_308203_ -> {
					this.minecraft.setScreen(new TimewornJournalEntitiesListScreen(tier));
				}));

		this.addRenderableWidget(new ImageButton(x4, y1, 48, 48,
				ITEMS_SPRITES, p_308203_ -> {
					this.minecraft.setScreen(new TimewornJournalItemsListScreen(tier));
				}));

		this.addRenderableWidget(new ImageButton(x5, y2, 48, 48,
				BLOCKS_SPRITES, p_308203_ -> {
					this.minecraft.setScreen(new TimewornJournalBlocksListScreen(tier));
				}));
	}

	@Override
	public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
		super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

		Component story = Component.translatable("screen.je.timeworn_journal_home.story");
		Component progression = Component.translatable("screen.je.timeworn_journal_home.progression");
		Component biomes = Component.translatable("screen.je.timeworn_journal_home.biomes");
		Component structures = Component.translatable("screen.je.timeworn_journal_home.structures");
		Component entities = Component.translatable("screen.je.timeworn_journal_home.entities");
		Component items = Component.translatable("screen.je.timeworn_journal_home.items");
		Component blocks = Component.translatable("screen.je.timeworn_journal_home.blocks");

		this.writeHomeText(pGuiGraphics, story, x1 + 24, y1 + 50);
		this.writeHomeText(pGuiGraphics, progression, x2 + 24, y1 + 50);
		this.writeHomeText(pGuiGraphics, biomes, x1 + 24, y2 + 50);
		this.writeHomeText(pGuiGraphics, structures, x2 + 24, y2 + 50);
		this.writeHomeText(pGuiGraphics, entities, x3 + 24, y1 + 50);
		this.writeHomeText(pGuiGraphics, items, x4 + 24, y1 + 50);
		this.writeHomeText(pGuiGraphics, blocks, x5 + 24, y2 + 50);

	}

	protected void writeHomeText(GuiGraphics pGuiGraphics, Component pText, int pX, int pY) {
        FormattedCharSequence formattedcharsequence = pText.getVisualOrderText();
        pGuiGraphics.drawString(this.font, formattedcharsequence, pX - this.font.width(formattedcharsequence) / 2, pY, 12559971, false);
    }
}
