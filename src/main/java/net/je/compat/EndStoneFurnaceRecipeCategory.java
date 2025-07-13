package net.je.compat;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.je.JourneysEnd;
import net.je.block.ModBlocks;
import net.je.item.ModItems;
import net.je.recipe.EndStoneFurnaceRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class EndStoneFurnaceRecipeCategory implements IRecipeCategory<EndStoneFurnaceRecipe> {
	public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
			"end_stone_furnace");
	public static final ResourceLocation TEXTURE = ResourceLocation
			.withDefaultNamespace("textures/gui/container/furnace.png");

	public static final RecipeType<EndStoneFurnaceRecipe> END_STONE_FURNACE_RECIPE_TYPE = new RecipeType<>(UID,
			EndStoneFurnaceRecipe.class);

	private final IDrawable background;
	private final IDrawable icon;
	/*
	 * private final IDrawableStatic flame; private final IDrawable animatedFlame;
	 * private final IDrawableStatic arrow; private final IDrawable animatedArrow;
	 */

	public EndStoneFurnaceRecipeCategory(IGuiHelper helper) {
		this.background = helper.createDrawable(TEXTURE, 5, 5, 166, 75);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
				new ItemStack(ModBlocks.END_STONE_FURNACE.get()));

		/*
		 * this.flame = helper.createDrawable(TEXTURE, 176, 0, 14, 14);
		 * this.animatedFlame = helper.createAnimatedDrawable(flame, 300,
		 * StartDirection.TOP, true); this.arrow = helper.createDrawable(TEXTURE, 176,
		 * 14, 24, 17); this.animatedArrow = helper.createAnimatedDrawable(arrow, 200,
		 * StartDirection.LEFT, false);
		 */
	}

	@Override
	public RecipeType<EndStoneFurnaceRecipe> getRecipeType() {
		return END_STONE_FURNACE_RECIPE_TYPE;
	}

	@Override
	public Component getTitle() {
		return Component.translatable("block.je.end_stone_furnace");
	}

	@Override
	public @Nullable IDrawable getIcon() {
		return icon;
	}



	@Nullable
    @Override
    public IDrawable getBackground() {
        return background;
    }

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, EndStoneFurnaceRecipe recipe, IFocusGroup focuses) {
		ItemStack voidblight = new ItemStack(ModItems.VOIDBLIGHT_BUCKET.get());
		ItemStack voidmass = new ItemStack(ModBlocks.VOIDMASS.get());
		ItemStack voidbloom = new ItemStack(ModBlocks.VOIDBLOOM.get());

		List<ItemStack> fuels = new ArrayList<>();

		fuels.add(voidbloom);
		fuels.add(voidblight);
		fuels.add(voidmass);

		builder.addSlot(RecipeIngredientRole.INPUT, 51, 12).addIngredients(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.INPUT, 51, 48).addItemStacks(fuels);

		builder.addSlot(RecipeIngredientRole.OUTPUT, 111, 30).addItemStack(recipe.getResultItem(null));
	}
}