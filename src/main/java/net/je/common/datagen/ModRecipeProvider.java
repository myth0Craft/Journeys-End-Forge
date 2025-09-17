package net.je.common.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import net.je.JourneysEnd;
import net.je.common.block.ModBlocks;
import net.je.common.conditions.ModConfigEnabledCondition;
import net.je.common.conditions.ModNotCondition;
import net.je.common.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.BlastingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
	public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
		super(pOutput, pRegistries);
	}

	@Override
	protected void buildRecipes(RecipeOutput recipeOutput) {

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COMPRESSED_END_STONE.get()).pattern("AAA")
				.pattern("AAA").pattern("AAA").define('A', Blocks.END_STONE)
				.unlockedBy("has_end_stone", has(Blocks.END_STONE)).save(recipeOutput);

		ConditionalRecipe.builder().condition(new ModConfigEnabledCondition("vanilla_eye_recipe_enabled"))
				.recipe(consumer -> ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.ENDER_EYE)
						.requires(Items.ENDER_PEARL).requires(Items.BLAZE_POWDER)
						.unlockedBy("has_ender_pearl", has(Items.ENDER_PEARL)).save(consumer))
				.save(recipeOutput, ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "mod_ender_eye"));

		ConditionalRecipe.builder().condition(new ModConfigEnabledCondition("eye_requires_echo_shards"))
				.recipe(consumer -> ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.ENDER_EYE)
						.requires(ModItems.EYE_FRAGMENT.get()).requires(Items.BLAZE_POWDER).requires(Items.ECHO_SHARD)
						.unlockedBy("has_eye_fragment", has(ModItems.EYE_FRAGMENT.get())).save(consumer))
				.save(recipeOutput,
						ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "je_ender_eye_with_echo_shard"));

		ConditionalRecipe.builder()
				.condition(new ModNotCondition(new ModConfigEnabledCondition("eye_requires_echo_shards")))
				.recipe(consumer -> ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.ENDER_EYE)
						.requires(ModItems.EYE_FRAGMENT.get()).requires(Items.BLAZE_POWDER)
						.unlockedBy("has_eye_fragment", has(ModItems.EYE_FRAGMENT.get())).save(consumer))
				.save(recipeOutput,
						ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "je_ender_eye_without_echo_shard"));

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.END_STONE_FURNACE.get()).pattern("AAA").pattern("ABA")
				.pattern("AAA").define('A', ModBlocks.COMPRESSED_END_STONE.get()).define('B', Blocks.BLAST_FURNACE)
				.unlockedBy("has_compressed_end_stone", has(ModBlocks.COMPRESSED_END_STONE.get())).save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.VOIDMETAL_BLOCK.get()).pattern("AAA")
				.pattern("AAA").pattern("AAA").define('A', ModItems.VOIDMETAL_INGOT.get())
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

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.VOIDMETAL_NUGGET.get(), 9)
				.requires(ModItems.VOIDMETAL_INGOT.get())
				.unlockedBy("has_voidmetal_ingot_for_nuggets", has(ModItems.VOIDMETAL_INGOT.get())).save(recipeOutput);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TIMEWORN_JOURNAL_T0.get(), 1)
				.requires(Items.ENDER_PEARL).requires(Items.BOOK).unlockedBy("has_cobblestone", has(Items.COBBLESTONE))
				.save(recipeOutput);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TIMEWORN_JOURNAL_T1.get(), 1)
				.requires(Blocks.END_STONE).requires(ModItems.TIMEWORN_JOURNAL_T0.get())
				.unlockedBy("has_end_stone", has(Blocks.END_STONE)).save(recipeOutput);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TIMEWORN_JOURNAL_T2.get(), 1)
				.requires(ModItems.VOIDMETAL_INGOT.get()).requires(ModItems.TIMEWORN_JOURNAL_T1.get())
				.unlockedBy("has_voidmetal_ingot", has(ModItems.VOIDMETAL_INGOT.get())).save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SHADOW_AMULET.get(), 1)
				.pattern("CVC").pattern("VNV").pattern(" V ").define('C', Blocks.CHAIN).define('V', ModItems.VOIDMETAL_INGOT.get())
				.define('N', Items.NETHER_STAR)
				.unlockedBy("has_voidmetal", has(ModItems.VOIDMETAL_INGOT.get()))
				.save(recipeOutput);

		//warded block conversion

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHADOW_STONE.get(), 1)
				.requires(ModBlocks.WARDED_SHADOW_STONE.get())
				.unlockedBy("has_warded_shadow_stone", has(ModBlocks.WARDED_SHADOW_STONE.get())).save(recipeOutput, "shadow_stone_from_warded");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHADOW_STONE_BRICKS.get(), 1)
				.requires(ModBlocks.WARDED_SHADOW_STONE_BRICKS.get())
				.unlockedBy("has_warded_shadow_stone_bricks", has(ModBlocks.WARDED_SHADOW_STONE_BRICKS.get())).save(recipeOutput, "shadow_stone_bricks_from_warded");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_SHADOW_STONE_BRICKS.get(), 1)
				.requires(ModBlocks.WARDED_CRACKED_SHADOW_STONE_BRICKS.get())
				.unlockedBy("has_warded_cracked_shadow_stone_bricks", has(ModBlocks.WARDED_CRACKED_SHADOW_STONE_BRICKS.get())).save(recipeOutput, "cracked_shadow_stone_bricks_from_warded");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SHADOW_STONE.get(), 1)
				.requires(ModBlocks.WARDED_POLISHED_SHADOW_STONE.get())
				.unlockedBy("has_warded_polished_shadow_stone", has(ModBlocks.WARDED_POLISHED_SHADOW_STONE.get())).save(recipeOutput, "polished_shadow_stone_from_warded");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CORRUPTED_SHADOW_STONE.get(), 1)
				.requires(ModBlocks.WARDED_CORRUPTED_SHADOW_STONE.get())
				.unlockedBy("has_warded_corrupted_shadow_stone", has(ModBlocks.WARDED_CORRUPTED_SHADOW_STONE.get())).save(recipeOutput, "corrupted_shadow_stone_from_warded");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.VOID_LANTERN.get(), 1)
				.requires(ModBlocks.WARDED_VOID_LANTERN.get())
				.unlockedBy("has_warded_void_lantern", has(ModBlocks.WARDED_VOID_LANTERN.get())).save(recipeOutput, "void_lantern_from_warded");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FADED_END_STONE_BRICKS.get(), 1)
				.requires(ModBlocks.WARDED_FADED_END_STONE_BRICKS.get())
				.unlockedBy("has_warded_faded_end_stone_bricks", has(ModBlocks.WARDED_FADED_END_STONE_BRICKS.get())).save(recipeOutput, "faded_end_stone_bricks_from_warded");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLIGHTED_SHADOW_STONE.get(), 1)
				.requires(ModBlocks.WARDED_BLIGHTED_SHADOW_STONE.get())
				.unlockedBy("has_warded_blighted_shadow_stone", has(ModBlocks.WARDED_BLIGHTED_SHADOW_STONE.get())).save(recipeOutput, "blighted_shadow_stone_from_warded");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_SHADOW_STONE.get(), 1)
				.requires(ModBlocks.WARDED_CHISELED_SHADOW_STONE.get())
				.unlockedBy("has_warded_chiseled_shadow_stone", has(ModBlocks.WARDED_CHISELED_SHADOW_STONE.get())).save(recipeOutput, "chiseled_shadow_stone_from_warded");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.VOIDGLASS.get(), 1)
				.requires(ModBlocks.WARDED_VOIDGLASS.get())
				.unlockedBy("has_warded_voidglass", has(ModBlocks.WARDED_VOIDGLASS.get())).save(recipeOutput, "voidglass_from_warded");

		// end stone block set

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_END_STONE_SLAB.get(), 6)
				.pattern("AAA").define('A', ModBlocks.POLISHED_END_STONE.get())
				.unlockedBy("has_polished_end_stone", has(ModBlocks.POLISHED_END_STONE.get())).save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.POLISHED_END_STONE_WALL.get(), 6)
				.pattern("AAA").pattern("AAA").define('A', ModBlocks.POLISHED_END_STONE.get())
				.unlockedBy("has_polished_end_stone", has(ModBlocks.POLISHED_END_STONE.get())).save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_END_STONE_STAIRS.get(), 4)
				.pattern("A  ").pattern("AA ").pattern("AAA").define('A', ModBlocks.POLISHED_END_STONE.get())
				.unlockedBy("has_polished_end_stone", has(ModBlocks.POLISHED_END_STONE.get())).save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.END_STONE_TILES.get(), 4).pattern("AA")
				.pattern("AA").define('A', Blocks.END_STONE_BRICKS)
				.unlockedBy("has_end_stone_bricks", has(Blocks.END_STONE_BRICKS)).save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_END_STONE.get(), 1).pattern("A")
				.pattern("A").define('A', Blocks.END_STONE_BRICK_SLAB)
				.unlockedBy("has_end_stone_bricks", has(Blocks.END_STONE_BRICKS))
				.save(recipeOutput, "chiseled_end_stone_from_end_brick_slab");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_END_STONE.get(), 1).pattern("A")
				.pattern("A").define('A', ModBlocks.POLISHED_END_STONE_SLAB.get())
				.unlockedBy("has_polished_end_stone", has(ModBlocks.POLISHED_END_STONE.get())).save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.END_STONE_BRICKS, 4).pattern("AA")
				.pattern("AA").define('A', ModBlocks.POLISHED_END_STONE.get())
				.unlockedBy("has_polished_end_stone", has(ModBlocks.POLISHED_END_STONE.get())).save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_END_STONE.get(), 4).pattern("AA")
				.pattern("AA").define('A', Blocks.END_STONE).unlockedBy("has_end_stone", has(Blocks.END_STONE))
				.save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.END_STONE_PILLAR.get(), 2).pattern("A")
				.pattern("A").define('A', ModBlocks.POLISHED_END_STONE.get())
				.unlockedBy("has_polished_end_stone", has(ModBlocks.POLISHED_END_STONE.get())).save(recipeOutput);

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_END_STONE.get(),
				Blocks.END_STONE);

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, Blocks.END_STONE_BRICKS,
				ModBlocks.POLISHED_END_STONE.get());

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.END_STONE_TILES.get(),
				Blocks.END_STONE);
		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.END_STONE_TILES.get(),
				ModBlocks.POLISHED_END_STONE.get());
		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.END_STONE_TILES.get(),
				Blocks.END_STONE_BRICKS);

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_END_STONE.get(),
				Blocks.END_STONE);
		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_END_STONE.get(),
				ModBlocks.POLISHED_END_STONE.get());

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.END_STONE_PILLAR.get(),
				Blocks.END_STONE);
		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.END_STONE_PILLAR.get(),
				ModBlocks.POLISHED_END_STONE.get());

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_END_STONE_SLAB.get(),
				Blocks.END_STONE, 2);
		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS,
				ModBlocks.POLISHED_END_STONE_STAIRS.get(), Blocks.END_STONE);
		stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS, ModBlocks.POLISHED_END_STONE_WALL.get(),
				Blocks.END_STONE);

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_END_STONE_SLAB.get(),
				ModBlocks.POLISHED_END_STONE.get(), 2);
		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS,
				ModBlocks.POLISHED_END_STONE_STAIRS.get(), ModBlocks.POLISHED_END_STONE.get());
		stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS, ModBlocks.POLISHED_END_STONE_WALL.get(),
				ModBlocks.POLISHED_END_STONE.get());

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, Blocks.END_STONE_BRICK_SLAB,
				ModBlocks.POLISHED_END_STONE.get(), 2);
		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, Blocks.END_STONE_BRICK_STAIRS,
				ModBlocks.POLISHED_END_STONE.get());
		stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS, Blocks.END_STONE_BRICK_WALL,
				ModBlocks.POLISHED_END_STONE.get());

		//shadow stone block set

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SHADOW_STONE.get(), 4).pattern("AA")
				.pattern("AA").define('A', ModBlocks.SHADOW_STONE.get()).unlockedBy("has_shadow_stone", has(ModBlocks.SHADOW_STONE.get()))
				.save(recipeOutput);

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SHADOW_STONE.get(),
				ModBlocks.SHADOW_STONE.get());

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHADOW_STONE_BRICKS.get(), 4).pattern("AA")
				.pattern("AA").define('A', ModBlocks.POLISHED_SHADOW_STONE.get())
				.unlockedBy("has_polished_shadow_stone", has(ModBlocks.POLISHED_SHADOW_STONE.get()))
				.save(recipeOutput);

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHADOW_STONE_BRICKS.get(),
				ModBlocks.POLISHED_SHADOW_STONE.get());

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHADOW_STONE_BRICKS.get(),
				ModBlocks.SHADOW_STONE.get());


		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SHADOW_STONE_SLAB.get(), 6)
				.pattern("AAA").define('A', ModBlocks.POLISHED_SHADOW_STONE.get())
				.unlockedBy("has_polished_shadow_stone", has(ModBlocks.POLISHED_SHADOW_STONE.get())).save(recipeOutput);

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SHADOW_STONE_SLAB.get(),
				ModBlocks.POLISHED_SHADOW_STONE.get());

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SHADOW_STONE_SLAB.get(),
				ModBlocks.SHADOW_STONE.get());

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.POLISHED_SHADOW_STONE_WALL.get(), 6)
				.pattern("AAA").pattern("AAA").define('A', ModBlocks.POLISHED_SHADOW_STONE.get())
				.unlockedBy("has_polished_shadow_stone", has(ModBlocks.POLISHED_SHADOW_STONE.get())).save(recipeOutput);

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SHADOW_STONE_WALL.get(),
				ModBlocks.POLISHED_SHADOW_STONE.get());

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SHADOW_STONE_WALL.get(),
				ModBlocks.SHADOW_STONE.get());

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SHADOW_STONE_STAIRS.get(), 4)
				.pattern("A  ").pattern("AA ").pattern("AAA").define('A', ModBlocks.POLISHED_SHADOW_STONE.get())
				.unlockedBy("has_polished_shadow_stone", has(ModBlocks.POLISHED_SHADOW_STONE.get())).save(recipeOutput);

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SHADOW_STONE_STAIRS.get(),
				ModBlocks.POLISHED_SHADOW_STONE.get());

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SHADOW_STONE_STAIRS.get(),
				ModBlocks.SHADOW_STONE.get());


		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHADOW_STONE_SLAB.get(), 6)
				.pattern("AAA").define('A', ModBlocks.SHADOW_STONE.get())
				.unlockedBy("has_shadow_stone", has(ModBlocks.SHADOW_STONE.get())).save(recipeOutput);

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHADOW_STONE_SLAB.get(),
				ModBlocks.SHADOW_STONE.get());

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.SHADOW_STONE_WALL.get(), 6)
				.pattern("AAA").pattern("AAA").define('A', ModBlocks.SHADOW_STONE.get())
				.unlockedBy("has_shadow_stone", has(ModBlocks.SHADOW_STONE.get())).save(recipeOutput);

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHADOW_STONE_WALL.get(),
				ModBlocks.SHADOW_STONE.get());

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHADOW_STONE_STAIRS.get(), 4)
				.pattern("A  ").pattern("AA ").pattern("AAA").define('A', ModBlocks.SHADOW_STONE.get())
				.unlockedBy("has_shadow_stone", has(ModBlocks.SHADOW_STONE.get())).save(recipeOutput);

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHADOW_STONE_STAIRS.get(),
				ModBlocks.SHADOW_STONE.get());


		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHADOW_STONE_BRICK_SLAB.get(), 6)
				.pattern("AAA").define('A', ModBlocks.SHADOW_STONE_BRICKS.get())
				.unlockedBy("has_shadow_stone_bricks", has(ModBlocks.SHADOW_STONE_BRICKS.get())).save(recipeOutput);

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHADOW_STONE_BRICK_SLAB.get(),
				ModBlocks.SHADOW_STONE.get());

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHADOW_STONE_BRICK_SLAB.get(),
				ModBlocks.SHADOW_STONE_BRICKS.get());

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHADOW_STONE_BRICK_SLAB.get(),
				ModBlocks.POLISHED_SHADOW_STONE.get());

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.SHADOW_STONE_BRICK_WALL.get(), 6)
				.pattern("AAA").pattern("AAA").define('A', ModBlocks.SHADOW_STONE_BRICKS.get())
				.unlockedBy("has_shadow_stone_bricks", has(ModBlocks.SHADOW_STONE_BRICKS.get())).save(recipeOutput);

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHADOW_STONE_BRICK_WALL.get(),
				ModBlocks.SHADOW_STONE.get());

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHADOW_STONE_BRICK_WALL.get(),
				ModBlocks.SHADOW_STONE_BRICKS.get());

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHADOW_STONE_BRICK_WALL.get(),
				ModBlocks.POLISHED_SHADOW_STONE.get());

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHADOW_STONE_BRICK_STAIRS.get(), 4)
				.pattern("A  ").pattern("AA ").pattern("AAA").define('A', ModBlocks.SHADOW_STONE_BRICKS.get())
				.unlockedBy("has_shadow_stone_bricks", has(ModBlocks.SHADOW_STONE_BRICKS.get())).save(recipeOutput);

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHADOW_STONE_BRICK_STAIRS.get(),
				ModBlocks.SHADOW_STONE.get());

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHADOW_STONE_BRICK_STAIRS.get(),
				ModBlocks.SHADOW_STONE_BRICKS.get());

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHADOW_STONE_BRICK_STAIRS.get(),
				ModBlocks.POLISHED_SHADOW_STONE.get());

		List<ItemLike> SHADOW_STONE_BRICKS_INGREDIENT = List.of(ModBlocks.SHADOW_STONE_BRICKS.get());
		oreSmelting(recipeOutput, SHADOW_STONE_BRICKS_INGREDIENT, RecipeCategory.MISC, ModBlocks.CRACKED_SHADOW_STONE_BRICKS.get(),
				0.25f, 200, "cracked_shadow_stone_bricks");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_SHADOW_STONE.get(), 1).pattern("A")
				.pattern("A").define('A', ModBlocks.SHADOW_STONE_BRICK_SLAB.get())
				.unlockedBy("has_shadow_stone_bricks", has(ModBlocks.SHADOW_STONE_BRICKS.get()))
				.save(recipeOutput, "chiseled_shadow_stone_from_shadow_stone_bricks");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_SHADOW_STONE.get(), 1).pattern("A")
				.pattern("A").define('A', ModBlocks.POLISHED_SHADOW_STONE_SLAB.get())
				.unlockedBy("has_polished_shadow_stone", has(ModBlocks.POLISHED_SHADOW_STONE.get())).save(recipeOutput);

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_SHADOW_STONE.get(),
				ModBlocks.POLISHED_SHADOW_STONE.get());

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_SHADOW_STONE.get(),
				ModBlocks.SHADOW_STONE_BRICKS.get());

		stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_SHADOW_STONE.get(),
				ModBlocks.SHADOW_STONE.get());

		// smithing

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