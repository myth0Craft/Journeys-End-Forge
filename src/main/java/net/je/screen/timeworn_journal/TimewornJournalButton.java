package net.je.screen.timeworn_journal;

import com.mojang.blaze3d.systems.RenderSystem;

import net.je.JourneysEnd;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class TimewornJournalButton extends Button {

	protected static final WidgetSprites TIMEWORN_JOURNAL_SPRITES = new WidgetSprites(
	        ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "timeworn_journal/timeworn_journal_button"),
	        ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "timeworn_journal/timeworn_journal_button_highlighted"),
	        ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "timeworn_journal/timeworn_journal_button_highlighted")
	    );

    public TimewornJournalButton(int x, int y, int width, int height, Component message, OnPress onPress) {
        super(x, y, width, height, message, onPress, DEFAULT_NARRATION);
    }

    @Override
    public void renderWidget(GuiGraphics pGuiGraphics, int mouseX, int mouseY, float partialTick) {
    	Minecraft minecraft = Minecraft.getInstance();
        pGuiGraphics.setColor(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        pGuiGraphics.blitSprite(TIMEWORN_JOURNAL_SPRITES.get(this.active, this.isHoveredOrFocused()), this.getX(), this.getY(), this.getWidth(), this.getHeight());
        pGuiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);

        Font font = Minecraft.getInstance().font;
        int textWidth = font.width(this.getMessage());
        int textX = this.getX() + (this.width - textWidth) / 2;
        int textY = this.getY() + (this.height - 8) / 2;

        MultiBufferSource.BufferSource bufferSource = minecraft.renderBuffers().bufferSource();

        font.drawInBatch(
            this.getMessage(), textX, textY,
            0xffa38b48,
            false,
            pGuiGraphics.pose().last().pose(),
            bufferSource,
            Font.DisplayMode.NORMAL,
            0,
            15728880
        );

        bufferSource.endBatch();
    }
}
