package net.je.event;

import net.je.JourneysEnd;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RecipesUpdatedEvent;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = JourneysEnd.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JERecipeSetup {
	/*
	 * @SubscribeEvent public static void registerRecipes(RegisterEvent event) {
	 * event.register(ForgeRegistries.Keys.RECIPE_SERIALIZERS, helper -> {
	 * helper.register(ResourceLocation.fromNamespaceAndPath("forge",
	 * "conditional"), ConditionalRecipe.SERIALZIER); }); }
	 */
}
