package net.je.screen;

import net.je.JourneysEnd;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.BlastingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.BlastFurnaceMenu;

public class EndStoneFurnaceScreen extends AbstractFurnaceScreen<EndStoneFurnaceMenu> {
    //private static final ResourceLocation LIT_PROGRESS_SPRITE = ResourceLocation.withDefaultNamespace("container/blast_furnace/lit_progress");
	private static final ResourceLocation LIT_PROGRESS_SPRITE = ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "container/end_stone_furnace_flame");
    private static final ResourceLocation BURN_PROGRESS_SPRITE = ResourceLocation.withDefaultNamespace("container/blast_furnace/burn_progress");
    private static final ResourceLocation TEXTURE = ResourceLocation.withDefaultNamespace("textures/gui/container/blast_furnace.png");

    public EndStoneFurnaceScreen(EndStoneFurnaceMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, new EndStoneFurnaceRecipeBook(), pPlayerInventory, pTitle, TEXTURE, LIT_PROGRESS_SPRITE, BURN_PROGRESS_SPRITE);
    }
}