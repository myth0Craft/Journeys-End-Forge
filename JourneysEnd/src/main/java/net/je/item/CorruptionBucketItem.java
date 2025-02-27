package net.je.item;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.je.recipe.EndStoneFurnaceRecipe;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.BlastingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.material.Fluid;

public class CorruptionBucketItem extends BucketItem {

	private int burnTime = 200;

	public CorruptionBucketItem(Supplier<? extends Fluid> supplier, Properties builder) {
		super(supplier, builder);
	}

	/*
	 * @Override public int getBurnTime(ItemStack itemStack, RecipeType<?>
	 * recipeType) {
	 * 
	 * return this.burnTime;
	 * 
	 * }
	 */

}
