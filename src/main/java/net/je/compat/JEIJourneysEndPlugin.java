
package net.je.compat;

import java.util.List;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.je.JourneysEnd;
import net.je.block.ModBlocks;
import net.je.recipe.EndStoneFurnaceRecipe;
import net.je.recipe.ModRecipeSerializers;
import net.je.screen.EndStoneFurnaceScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

@JeiPlugin
public class JEIJourneysEndPlugin implements IModPlugin {

	@Override
	public ResourceLocation getPluginUid() {
		return ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "jei_plugin");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		registration.addRecipeCategories(new EndStoneFurnaceRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

		List<EndStoneFurnaceRecipe> growthChamberRecipes = recipeManager
				.getAllRecipesFor(ModRecipeSerializers.END_STONE_FURNACE_RECIPE_TYPE.get()).stream().map(RecipeHolder::value).toList();
		registration.addRecipes(EndStoneFurnaceRecipeCategory.END_STONE_FURNACE_RECIPE_TYPE, growthChamberRecipes);
	}

	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration) {
		registration.addRecipeClickArea(EndStoneFurnaceScreen.class, 70, 30, 25, 20,
				EndStoneFurnaceRecipeCategory.END_STONE_FURNACE_RECIPE_TYPE);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(ModBlocks.END_STONE_FURNACE.get().asItem()),
				EndStoneFurnaceRecipeCategory.END_STONE_FURNACE_RECIPE_TYPE);
	}
}
