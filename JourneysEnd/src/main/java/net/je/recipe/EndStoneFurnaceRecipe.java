package net.je.recipe;

import net.je.JourneysEnd;
import net.je.block.ModBlocks;
import net.je.recipe.ModRecipeSerializers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Blocks;

public class EndStoneFurnaceRecipe extends AbstractCookingRecipe {
	public final static String GROUP = "end_stone_furnace_recipe";
	
	public EndStoneFurnaceRecipe(String pGroup, CookingBookCategory pCategory, Ingredient pIngredient, ItemStack pResult, float pExperience, int pCookingTime) {
        super(ModRecipeSerializers.END_STONE_FURNACE_RECIPE_TYPE.get(), pGroup, pCategory, pIngredient, pResult, pExperience, pCookingTime);
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.END_STONE_FURNACE.get());
    }
    
    

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.END_STONE_FURNACE_RECIPE.get();
    }
}
