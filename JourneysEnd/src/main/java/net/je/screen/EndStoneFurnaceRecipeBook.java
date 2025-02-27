package net.je.screen;

import java.util.Set;

import net.je.block.entity.EndStoneFurnaceBlockEntity;
import net.minecraft.client.gui.screens.recipebook.AbstractFurnaceRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;

public class EndStoneFurnaceRecipeBook extends AbstractFurnaceRecipeBookComponent {

	private static final Component FILTER_NAME = Component.translatable("gui.recipebook.toggleRecipes.end_stone_furnace");

    @Override
    protected Component getRecipeFilterName() {
        return FILTER_NAME;
    }

    @Override
    protected Set<Item> getFuelItems() {
        return EndStoneFurnaceBlockEntity.getFuel().keySet();
    }

}
