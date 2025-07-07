package net.je.event;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import net.je.JourneysEnd;
import net.je.config.CommonConfig;
import net.je.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.RecipeBook;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.VillagerDataHolder;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.client.event.RecipesUpdatedEvent;
import net.minecraftforge.common.util.LogicalSidedProvider;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
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


