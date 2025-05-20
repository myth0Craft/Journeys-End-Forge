package net.je.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import net.je.JourneysEnd;
import net.je.block.ModBlocks;
import net.je.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.BlastingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
	public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
		super(pOutput, pRegistries);
	}

	@Override
	protected void buildRecipes(RecipeOutput recipeOutput) {

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COMPRESSED_END_STONE.get()).pattern("AAA")
				.pattern("AAA").pattern("AAA").define('A', Blocks.END_STONE)
				.unlockedBy("has_end_stone", has(Blocks.END_STONE)).save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.END_STONE_FURNACE.get()).pattern("AAA").pattern("ABA")
				.pattern("AAA").define('A', ModBlocks.COMPRESSED_END_STONE.get()).define('B', Blocks.BLAST_FURNACE)
				.unlockedBy("has_compressed_end_stone", has(ModBlocks.COMPRESSED_END_STONE.get())).save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.VOIDMETAL_BLOCK.get()).pattern("AAA").pattern("AAA")
				.pattern("AAA").define('A', ModItems.VOIDMETAL_INGOT.get())
				.unlockedBy("has_voidmetal", has(ModItems.VOIDMETAL_INGOT.get())).save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VOIDMETAL_UPGRADE_SMITHING_TEMPLATE.get(), 2)
				.pattern("ACA").pattern("ABA").pattern("AAA").define('A', Items.DIAMOND).define('B', Blocks.END_STONE)
				.define('C', ModItems.VOIDMETAL_UPGRADE_SMITHING_TEMPLATE.get())
				.unlockedBy("has_voidmetal_upgrade", has(ModItems.VOIDMETAL_UPGRADE_SMITHING_TEMPLATE.get()))
				.save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.INTERDIMENSIONAL_ANCHOR.get(), 1).pattern("ABA")
				.pattern("BCB").pattern("ABA").define('A', ModItems.VOIDMETAL_INGOT.get())
				.define('B', Items.NETHERITE_INGOT).define('C', Items.NETHER_STAR)
				.unlockedBy("has_voidmetal", has(ModItems.VOIDMETAL_INGOT.get())).save(recipeOutput);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.VOIDMETAL_INGOT.get(), 9)
				.requires(ModBlocks.VOIDMETAL_BLOCK.get())
				.unlockedBy("has_voidmetal_block", has(ModBlocks.VOIDMETAL_BLOCK.get())).save(recipeOutput);

		/*
		 * ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
		 * ModItems.VOIDMETAL_INGOT.get()) .pattern("AAA") .pattern("AAA")
		 * .pattern("AAA") .define('A', ModItems.VOIDMETAL_NUGGET.get())
		 * .unlockedBy("has_voidmetal_nugget",
		 * has(ModItems.VOIDMETAL_NUGGET.get())).save(recipeOutput);
		 */

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.VOIDMETAL_NUGGET.get(), 9)
				.requires(ModItems.VOIDMETAL_INGOT.get())
				.unlockedBy("has_voidmetal_ingot_for_nuggets", has(ModItems.VOIDMETAL_INGOT.get())).save(recipeOutput);

		SmithingTransformRecipeBuilder
				.smithing(Ingredient.of(ModItems.VOIDMETAL_UPGRADE_SMITHING_TEMPLATE.get()),
						Ingredient.of(Items.NETHERITE_SWORD), Ingredient.of(ModItems.VOIDMETAL_INGOT.get()),
						RecipeCategory.MISC, ModItems.VOIDMETAL_SWORD.get())
				.unlocks("has_voidmetal", has(ModItems.VOIDMETAL_INGOT.get()))
				.save(recipeOutput, "voidmetal_sword_smithing");

		SmithingTransformRecipeBuilder
				.smithing(Ingredient.of(ModItems.VOIDMETAL_UPGRADE_SMITHING_TEMPLATE.get()),
						Ingredient.of(Items.NETHERITE_PICKAXE), Ingredient.of(ModItems.VOIDMETAL_INGOT.get()),
						RecipeCategory.MISC, ModItems.VOIDMETAL_PICKAXE.get())
				.unlocks("has_voidmetal", has(ModItems.VOIDMETAL_INGOT.get()))
				.save(recipeOutput, "voidmetal_pickaxe_smithing");

		SmithingTransformRecipeBuilder
				.smithing(Ingredient.of(ModItems.VOIDMETAL_UPGRADE_SMITHING_TEMPLATE.get()),
						Ingredient.of(Items.NETHERITE_AXE), Ingredient.of(ModItems.VOIDMETAL_INGOT.get()),
						RecipeCategory.MISC, ModItems.VOIDMETAL_AXE.get())
				.unlocks("has_voidmetal", has(ModItems.VOIDMETAL_INGOT.get()))
				.save(recipeOutput, "voidmetal_axe_smithing");

		SmithingTransformRecipeBuilder
				.smithing(Ingredient.of(ModItems.VOIDMETAL_UPGRADE_SMITHING_TEMPLATE.get()),
						Ingredient.of(Items.NETHERITE_SHOVEL), Ingredient.of(ModItems.VOIDMETAL_INGOT.get()),
						RecipeCategory.MISC, ModItems.VOIDMETAL_SHOVEL.get())
				.unlocks("has_voidmetal", has(ModItems.VOIDMETAL_INGOT.get()))
				.save(recipeOutput, "voidmetal_shovel_smithing");

		SmithingTransformRecipeBuilder
				.smithing(Ingredient.of(ModItems.VOIDMETAL_UPGRADE_SMITHING_TEMPLATE.get()),
						Ingredient.of(Items.NETHERITE_HOE), Ingredient.of(ModItems.VOIDMETAL_INGOT.get()),
						RecipeCategory.MISC, ModItems.VOIDMETAL_HOE.get())
				.unlocks("has_voidmetal", has(ModItems.VOIDMETAL_INGOT.get()))
				.save(recipeOutput, "voidmetal_hoe_smithing");

		SmithingTransformRecipeBuilder
				.smithing(Ingredient.of(ModItems.VOIDMETAL_UPGRADE_SMITHING_TEMPLATE.get()),
						Ingredient.of(Items.NETHERITE_HELMET), Ingredient.of(ModItems.VOIDMETAL_INGOT.get()),
						RecipeCategory.MISC, ModItems.VOIDMETAL_HELMET.get())
				.unlocks("has_voidmetal", has(ModItems.VOIDMETAL_INGOT.get()))
				.save(recipeOutput, "voidmetal_helmet_smithing");

		SmithingTransformRecipeBuilder
				.smithing(Ingredient.of(ModItems.VOIDMETAL_UPGRADE_SMITHING_TEMPLATE.get()),
						Ingredient.of(Items.NETHERITE_CHESTPLATE), Ingredient.of(ModItems.VOIDMETAL_INGOT.get()),
						RecipeCategory.MISC, ModItems.VOIDMETAL_CHESTPLATE.get())
				.unlocks("has_voidmetal", has(ModItems.VOIDMETAL_INGOT.get()))
				.save(recipeOutput, "voidmetal_chestplate_smithing");

		SmithingTransformRecipeBuilder
				.smithing(Ingredient.of(ModItems.VOIDMETAL_UPGRADE_SMITHING_TEMPLATE.get()),
						Ingredient.of(Items.NETHERITE_LEGGINGS), Ingredient.of(ModItems.VOIDMETAL_INGOT.get()),
						RecipeCategory.MISC, ModItems.VOIDMETAL_LEGGINGS.get())
				.unlocks("has_voidmetal", has(ModItems.VOIDMETAL_INGOT.get()))
				.save(recipeOutput, "voidmetal_leggings_smithing");

		SmithingTransformRecipeBuilder
				.smithing(Ingredient.of(ModItems.VOIDMETAL_UPGRADE_SMITHING_TEMPLATE.get()),
						Ingredient.of(Items.NETHERITE_BOOTS), Ingredient.of(ModItems.VOIDMETAL_INGOT.get()),
						RecipeCategory.MISC, ModItems.VOIDMETAL_BOOTS.get())
				.unlocks("has_voidmetal", has(ModItems.VOIDMETAL_INGOT.get()))
				.save(recipeOutput, "voidmetal_boots_smithing");
	}

	protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory,
			ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
		oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory,
				pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
	}

	protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory,
			ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
		oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory,
				pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
	}

	protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput,
			RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
			List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience,
			int pCookingTime, String pGroup, String pRecipeName) {
		for (ItemLike itemlike : pIngredients) {
			SimpleCookingRecipeBuilder
					.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer,
							factory)
					.group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).save(recipeOutput,
							JourneysEnd.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
		}
	}
}