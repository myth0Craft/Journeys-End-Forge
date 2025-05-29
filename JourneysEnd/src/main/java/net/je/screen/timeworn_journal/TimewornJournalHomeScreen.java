package net.je.screen.timeworn_journal;

import java.util.List;

import javax.annotation.Nullable;

import org.joml.Quaternionf;
import org.joml.Vector3f;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.vertex.PoseStack;

import net.je.JourneysEnd;
import net.je.entity.ModEntities;
import net.je.entity.custom.Endersent;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.SpriteIconButton;
import net.minecraft.client.gui.components.SpriteIconButton.TextAndIcon;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.ArmorStand;

public class TimewornJournalHomeScreen extends BaseTimewornJournalScreen {
	private final List<Button> exitButtons = Lists.newArrayList();
	@Nullable
	private Button exitToTitleButton;

	private static final Vector3f ARMOR_STAND_TRANSLATION = new Vector3f();
	private static final Quaternionf ARMOR_STAND_ANGLE = new Quaternionf().rotationXYZ(0.43633232F, 0.0F,
			(float) Math.PI);
	private static final int ARMOR_STAND_SCALE = 25;
	private static final int ARMOR_STAND_OFFSET_Y = 75;
	private static final int ARMOR_STAND_OFFSET_X = 141;

	private Endersent endersentPreview;

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

	// private LocalPlayer player;

	public TimewornJournalHomeScreen() {

	}

	@Override
	protected void init() {
		// this.addRenderableWidget(ImageButton.builder(title, null -> {})).build();
		super.init();
		Component component = Component.translatable("screen.je.timeworn_journal_title");

		int x1 = (int) Math.round(super.getBgStartX() + (super.getBgWidth() / 2 * 0.2));
		int y1 = (int) Math.round(super.getBgStartY() + (super.getBgHeight() * 0.15));

		int x2 = (int) Math.round(super.getBgStartX() + (super.getBgWidth() / 2 * 0.5));
		int y2 = (int) Math.round(super.getBgStartY() + (super.getBgHeight() * 0.5));

		int x3 = (int) Math.round(super.getBgStartX() + (super.getBgWidth()/2) + super.getBgWidth()/2 * 0.05);
		
		int x4 = (int) Math.round(super.getBgStartX() + (super.getBgWidth()/2) + super.getBgWidth()/2 * 0.35);
		
		int x5 = (int) Math.round(super.getBgStartX() + (super.getBgWidth() / 2 * 0.2) + super.getBgWidth() / 2);

		this.addRenderableWidget(new ImageButton(x1, y1, 48, 48, STORY_SPRITES, p_308203_ -> {
			this.minecraft.setScreen(null);
		}));

		this.addRenderableWidget(new ImageButton(x2, y1, 48, 48, PROGRESSION_SPRITES, p_308203_ -> {
			this.minecraft.setScreen(null);
		}));

		this.addRenderableWidget(new ImageButton(x1, y2, 48, 48, BIOMES_SPRITES, p_308203_ -> {
			this.minecraft.setScreen(null);
		}));

		this.addRenderableWidget(new ImageButton(x2, y2, 48, 48, STRUCTURES_SPRITES, p_308203_ -> {
			this.minecraft.setScreen(null);
		}));

		this.addRenderableWidget(new ImageButton(x3, y1, 48, 48,
				ENTITIES_SPRITES, p_308203_ -> {
					this.minecraft.setScreen(null);
				}));
		
		this.addRenderableWidget(new ImageButton(x4, y1, 48, 48,
				ITEMS_SPRITES, p_308203_ -> {
					this.minecraft.setScreen(null);
				}));
		
		this.addRenderableWidget(new ImageButton(x5, y2, 48, 48,
				BLOCKS_SPRITES, p_308203_ -> {
					this.minecraft.setScreen(null);
				}));
		
		
		//this.font.drawInBatch(component, x1, y1, 0, true, null, null, null, x4, x5);

		/*
		 * this.addRenderableWidget(SpriteIconButton.builder((48, 48, component, 48, 48,
		 * ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
		 * "textures/gui/timeworn_journal_story_button_paper.png")), p_280794_ -> {
		 * this.minecraft.setScreen(null); p_280794_.active = true; }).bounds(this.width
		 * / 2 - 100, this.height / 4 + 72, 200, 20).build()));
		 */

		/*
		 * this.exitButtons.add(this.addRenderableWidget(Button.builder(component,
		 * p_280794_ -> { this.minecraft.setScreen(null); p_280794_.active = true;
		 * }).bounds(this.width / 2 - 100, this.height / 4 + 72, 200, 20).build()));
		 * this.exitButtons.add(this.exitToTitleButton);
		 */

		/*
		 * this.endersentPreview = new Endersent(ModEntities.ENDERSENT.get(),
		 * this.minecraft.level); this.endersentPreview.yBodyRot = 210.0F;
		 * this.endersentPreview.setXRot(25.0F); this.endersentPreview.yHeadRot =
		 * this.endersentPreview.getYRot(); this.endersentPreview.yHeadRotO =
		 * this.endersentPreview.getYRot();
		 */
	}

	@Override
	public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
		super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

		/*
		 * pGuiGraphics.blit( ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
		 * "textures/gui/timeworn_journal_story_button_paper.png"), super.bgStartX + 40,
		 * super.bgStartY + 35, 0, 0, 48, 48, 48, 48);
		 */

		/*
		 * pGuiGraphics.blit( ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
		 * "textures/gui/timeworn_journal_entities_button_paper.png"), widthCoord1,
		 * Math.round((this.height / 5) * 3) - 24, 0, 0, 0, 48, 48, 48, 48);
		 * pGuiGraphics.blit( ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
		 * "textures/gui/timeworn_journal_biomes_button_paper.png"), widthCoord1,
		 * Math.round((this.height / 5) * 4) - 24, 0, 0, 0, 48, 48, 48, 48);
		 * pGuiGraphics.blit( ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
		 * "textures/gui/timeworn_journal_progression_button_paper.png"), widthCoord2,
		 * Math.round((this.height / 4) * 2) - 24, 0, 0, 0, 48, 48, 48, 48);
		 * pGuiGraphics.blit( ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
		 * "textures/gui/timeworn_journal_story_button_paper.png"), widthCoord2,
		 * Math.round((this.height / 4) * 3) - 24, 0, 0, 0, 48, 48, 48, 48);
		 */

		// 1 2 3 4 5

		/*
		 * pGuiGraphics.pose().pushPose();
		 * 
		 * pGuiGraphics.pose().scale(5, 5, 5); pGuiGraphics.pose().translate(0, 0, 0);
		 * 
		 * pGuiGraphics.pose().popPose();
		 */

	}

}
