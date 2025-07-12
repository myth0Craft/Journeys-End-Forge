package net.je.event;

import net.je.JourneysEnd;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JourneysEnd.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RecipeOverrideHandler {

	/*
	 * @SubscribeEvent public static void
	 * onServerAboutToStart(ServerAboutToStartEvent event) { RecipeManager manager =
	 * event.getServer().getRecipeManager(); modifyRecipeMap(manager); }
	 *
	 * private static void modifyRecipeMap(RecipeManager manager) {
	 * replaceByNameMap(manager, byName -> {
	 * byName.remove(ResourceLocation.withDefaultNamespace("ender_eye")); }); }
	 *
	 * @SuppressWarnings("unchecked") private static void
	 * replaceByNameMap(RecipeManager manager, Consumer<Map<ResourceLocation,
	 * RecipeHolder<?>>> modifier) { try { Field field =
	 * RecipeManager.class.getDeclaredField("byName"); field.setAccessible(true);
	 *
	 * Map<ResourceLocation, RecipeHolder<?>> original = (Map<ResourceLocation,
	 * RecipeHolder<?>>) field.get(manager); Map<ResourceLocation, RecipeHolder<?>>
	 * copy = new HashMap<>(original);
	 *
	 * modifier.accept(copy); // Apply your custom additions/removals
	 *
	 * field.set(manager, copy); // Replace the field with the mutable copy
	 *
	 * } catch (Exception e) { throw new
	 * RuntimeException("Failed to replace RecipeManager.byName", e); } }
	 *
	 * @SubscribeEvent public static void
	 * onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) { Player player =
	 * event.getEntity(); if (player instanceof ServerPlayer serverPlayer) {
	 * removeRecipeFromBook(serverPlayer,
	 * ResourceLocation.withDefaultNamespace("ender_eye")); } }
	 *
	 * private static void removeRecipeFromBook(ServerPlayer player,
	 * ResourceLocation recipeId) { try { Method removeMethod =
	 * RecipeBook.class.getDeclaredMethod("remove", ResourceLocation.class);
	 * removeMethod.setAccessible(true); removeMethod.invoke(player.getRecipeBook(),
	 * recipeId); } catch (Exception e) { throw new
	 * RuntimeException("Failed to remove recipe from player's recipe book", e); } }
	 */
}


