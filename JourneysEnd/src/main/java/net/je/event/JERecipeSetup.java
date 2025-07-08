package net.je.event;

import net.je.JourneysEnd;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JourneysEnd.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JERecipeSetup {
	/*
	 * @SubscribeEvent public static void registerRecipes(RegisterEvent event) {
	 * event.register(ForgeRegistries.Keys.RECIPE_SERIALIZERS, helper -> {
	 * helper.register(ResourceLocation.fromNamespaceAndPath("forge",
	 * "conditional"), ConditionalRecipe.SERIALZIER); }); }
	 */
}
