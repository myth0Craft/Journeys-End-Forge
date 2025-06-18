package net.je.screen.timeworn_journal.entry;

import java.util.ArrayList;
import java.util.List;

import net.je.JourneysEnd;
import net.je.block.ModBlocks;
import net.je.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class TimewornJournalEntries {

	public static final List<BaseTimewornJournalEntry> CHAPTER_ENTRIES = List.of(
			new BaseTimewornJournalEntry(name("Chapter 1"), null),
			new BaseTimewornJournalEntry(name("Chapter 2"),
					lore("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
							+ "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate"
							+ " velit esse cillum dolore eu fugiat nulla pariatur. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore"
							+ " magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit"
							+ " in voluptate velit esse cillum dolore eu fugiat nulla pariatur.")));

	public static final List<BaseTimewornJournalEntry> BLOCK_ENTRIES = List.of(
			new ItemEntry(name("Interdimensional Anchor"), lore("Lore -- its pretty cool man"), stack(ModBlocks.INTERDIMENSIONAL_ANCHOR.get())),
			new ItemEntry(name("End Stone Furnace"), lore("Cook"), stack(ModBlocks.END_STONE_FURNACE.get())),
			new ItemEntry(name("Bejeweled Pedestal"), lore("Eye"), stack(ModBlocks.BEJEWELED_PEDESTAL.get())),
			new ImageEntry(name("Voidbloom"), lore("Plant"), ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/item/voidbloom_item.png"))
			);
	
	public static final List<BaseTimewornJournalEntry> ITEM_ENTRIES = List.of(
			new ImageEntry(name("voidmetal_ingot"), lore("voidmetal_ingot"), ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/item/voidmetal_sword.png"))
			);
	
	
	private static ItemStack stack(Item pItem) {
		return new ItemStack(pItem);
	}
	
	private static ItemStack stack(Block pBlock) {
		return new ItemStack(pBlock);
	}
	
	private static Component name(String path) {
		String start = "screen.je.title.";
		return Component.translatable(start + path);
	}
	
	private static Component lore(String path) {
		String start = "screen.je.lore.";
		return Component.translatable(start + path);
	}
	

			/*
			 * private ItemStack stack(Item) { return new ItemStack(Item); }
			 */
}
