package net.je.screen.timeworn_journal.entry;

import java.util.ArrayList;
import java.util.List;

import net.je.JourneysEnd;
import net.je.block.ModBlocks;
import net.je.entity.ModEntities;
import net.je.entity.custom.Endersent;
import net.je.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class TimewornJournalEntries {

	public static final List<BaseTimewornJournalEntry> CHAPTER_ENTRIES = List.of(
			new BaseTimewornJournalEntry(name("chapter_1"), lore("chapter_1")),
			new BaseTimewornJournalEntry(name("chapter_2"), lore("chapter_2"))
			);
	
	public static final List<BaseTimewornJournalEntry> PROGRESSION_ENTRIES = List.of(
			new ImageEntry(name("progression_1"), lore("progression_1"), ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/item/eye_fragment.png")),
			new ImageEntry(name("progression_2"), lore("progression_2"), ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/item/voidmetal_ingot.png"))
			);

	public static final List<BaseTimewornJournalEntry> BLOCK_ENTRIES = List.of(
			new ItemEntry(name("corrupted_dirt"), lore("corrupted_dirt"), stack(ModBlocks.CORRUPTED_DIRT.get())),
			new ItemEntry(name("void_stone"), lore("void_stone"), stack(ModBlocks.VOID_STONE.get())),
			new ItemEntry(name("voidmass"), lore("voidmass"), stack(ModBlocks.VOIDMASS.get())),
			new ImageEntry(name("voidbloom"), lore("voidbloom"), ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/item/voidbloom_item.png")),
			new ImageEntry(name("voidblight"), lore("voidblight"), ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/block/voidblight_icon.png")),
			new ItemEntry(name("end_stone"), lore("end_stone"), stack(Blocks.END_STONE)),
			new ItemEntry(name("faded_end_stone"), lore("faded_end_stone"), stack(ModBlocks.FADED_END_STONE.get())),
			new ItemEntry(name("lush_end_stone"), lore("lush_end_stone"), stack(ModBlocks.LUSH_END_STONE.get())),
			new ItemEntry(name("compressed_end_stone"), lore("compressed_end_stone"), stack(ModBlocks.COMPRESSED_END_STONE.get())),
			new ItemEntry(name("end_stone_furnace"), lore("end_stone_furnace"), stack(ModBlocks.END_STONE_FURNACE.get())),
			new ItemEntry(name("interdimensional_anchor"), lore("interdimensional_anchor"), stack(ModBlocks.INTERDIMENSIONAL_ANCHOR.get())),
			new ItemEntry(name("bejeweled_pedestal"), lore("bejeweled_pedestal"), stack(ModBlocks.BEJEWELED_PEDESTAL.get())),
			new ItemEntry(name("lantern_of_warding"), lore("lantern_of_warding"), stack(ModBlocks.LANTERN_OF_WARDING.get())),
			new ItemEntry(name("ender_vault"), lore("ender_vault"), stack(ModBlocks.ENDER_VAULT.get())),
			new ItemEntry(name("voidmetal_block"), lore("voidmetal_block"), stack(ModBlocks.VOIDMETAL_BLOCK.get()))
			
			);
	
	public static final List<BaseTimewornJournalEntry> ITEM_ENTRIES = List.of(
			new ImageEntry(name("timeworn_journal"), lore("timeworn_journal"), ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/item/timeworn_journal.png")),
			new ImageEntry(name("voidblight_bucket"), lore("voidblight_bucket"), ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/item/voidblight_bucket.png")),
			new ImageEntry(name("eye_fragment"), lore("eye_fragment"), ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/item/eye_fragment.png")),
			new ImageEntry(name("small_void_dust"), lore("small_void_dust"), ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/item/small_void_dust.png")),
			new ImageEntry(name("void_dust"), lore("void_dust"), ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/item/void_dust.png")),
			new ImageEntry(name("raw_voidmetal"), lore("raw_voidmetal"), ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/item/raw_voidmetal.png")),
			new ImageEntry(name("voidmetal_ingot"), lore("voidmetal_ingot"), ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/item/voidmetal_ingot.png")),
			new ImageEntry(name("voidmetal_nugget"), lore("voidmetal_nugget"), ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/item/voidmetal_nugget.png")),
			new ImageEntry(name("voidmetal_upgrade_smithing_template"), lore("voidmetal_upgrade_smithing_template"), ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/item/voidmetal_upgrade_smithing_template.png"))
			);
	
	public static final List<BaseTimewornJournalEntry> STRUCTURE_ENTRIES = List.of(
			new ImageEntry(name("endersent_well"), lore("endersent_well"), ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/gui/journal_icons/endersent_well.png"))
			);
	
	public static final List<BaseTimewornJournalEntry> BIOME_ENTRIES = List.of(
			new ImageEntry(name("main_island"), lore("main_island"), ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/gui/journal_icons/main_island.png")),
			new ImageEntry(name("outer_islands"), lore("outer_islands"), ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/gui/journal_icons/outer_islands.png"))
			);
	
	public static final List<BaseTimewornJournalEntry> ENTITY_ENTRIES = List.of(
			new EntityEntry(name("endersent"), lore("endersent"), new Endersent(ModEntities.ENDERSENT.get(), Minecraft.getInstance().level))
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
}
