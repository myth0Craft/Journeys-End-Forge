package net.je.recipe;

import net.je.JourneysEnd;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeSerializers {
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister
			.create(ForgeRegistries.RECIPE_SERIALIZERS, JourneysEnd.MODID);

	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister
			.create(ForgeRegistries.RECIPE_TYPES, JourneysEnd.MODID);

	public static final RegistryObject<RecipeSerializer<?>> END_STONE_FURNACE_RECIPE = RECIPE_SERIALIZERS
			.register("end_stone_furnace_recipe", () -> new SimpleCookingSerializer<>(EndStoneFurnaceRecipe::new, 100));

	public static final RegistryObject<RecipeType<EndStoneFurnaceRecipe>> END_STONE_FURNACE_RECIPE_TYPE = RECIPE_TYPES
			.register("end_stone_furnace_recipe", () -> new RecipeType<EndStoneFurnaceRecipe>() {
				@Override
				public String toString() {
					return "end_stone_furnace_recipe";
				}
			});

	public static void register(IEventBus eventBus) {
		RECIPE_SERIALIZERS.register(eventBus);
		RECIPE_TYPES.register(eventBus);
	}

	/*
	 * public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(String
	 * type) { ResourceLocation recipeTypeId =
	 * ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, type); return
	 * Registry.register(BuiltInRegistries.RECIPE_TYPE, recipeTypeId, new
	 * RecipeType<T>() {
	 *
	 * @Override public String toString() { return type; } }); }
	 */

}
