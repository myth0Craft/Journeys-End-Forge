package net.je.screen.timeworn_journal;

import net.je.JourneysEnd;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class TimewornJournalEditBox extends EditBox {

	public TimewornJournalEditBox(Font pfont, int x, int y, int width, int height, Component message) {
		super(pfont, x, y, width, height, message);
	}

	@Override
	public void renderWidget(GuiGraphics pGuiGraphics, int mouseX, int mouseY, float partialTick) {
		super.renderWidget(pGuiGraphics, mouseX, mouseY, partialTick);
	    this.setTextColor(0xffa38b48);

	    pGuiGraphics.blit(ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/gui/sprites/timeworn_journal/timeworn_journal_edit_box.png"),
	                  this.getX(), this.getY(), 0, 0,
	                  this.width, this.height,
	                  this.width, this.height);


	}
}