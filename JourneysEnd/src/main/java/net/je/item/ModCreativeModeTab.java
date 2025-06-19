package net.je.item;

import net.je.JourneysEnd;
import net.je.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public class ModCreativeModeTab {
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
			DeferredRegister.create(Registries.CREATIVE_MODE_TAB, JourneysEnd.MODID);

	public static final RegistryObject<CreativeModeTab> BLOCKS_TAB = CREATIVE_MODE_TABS.register("je_blocks_tab",
			() -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.VOIDMETAL_BLOCK.get()))
			.title(Component.translatable("creativetab.je.blocks"))
			.displayItems((pParameters, pOutput) -> {
				
				pOutput.accept(ModBlocks.CORRUPTED_DIRT.get());
				pOutput.accept(ModBlocks.VOID_STONE.get());
				pOutput.accept(ModBlocks.VOIDMASS.get());
				pOutput.accept(ModBlocks.VOIDBLOOM.get());
				
				pOutput.accept(ModBlocks.END_STONE_PILLAR.get());
				pOutput.accept(ModBlocks.CHISELED_END_STONE.get());
				pOutput.accept(ModBlocks.END_STONE_TILES.get());
				pOutput.accept(ModBlocks.POLISHED_END_STONE.get());
				pOutput.accept(ModBlocks.POLISHED_END_STONE_STAIRS.get());
				pOutput.accept(ModBlocks.POLISHED_END_STONE_SLAB.get());
				pOutput.accept(ModBlocks.POLISHED_END_STONE_WALL.get());
				pOutput.accept(ModBlocks.FADED_END_STONE.get());
				pOutput.accept(ModBlocks.LUSH_END_STONE.get());
				pOutput.accept(ModBlocks.COMPRESSED_END_STONE.get());
				
				pOutput.accept(ModBlocks.END_STONE_FURNACE.get());
				pOutput.accept(ModBlocks.INTERDIMENSIONAL_ANCHOR.get());
				pOutput.accept(ModBlocks.BEJEWELED_PEDESTAL.get());
				pOutput.accept(ModBlocks.LANTERN_OF_WARDING.get());
				pOutput.accept(ModBlocks.ENDER_VAULT.get());

				pOutput.accept(ModBlocks.VOIDMETAL_BLOCK.get());

			})
			.build());
	
	public static final RegistryObject<CreativeModeTab> ITEMS_TAB = CREATIVE_MODE_TABS.register("aje_items_tab",
			() -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.VOIDMETAL_SWORD.get()))
			.title(Component.translatable("creativetab.je.items"))
			.displayItems((pParameters, pOutput) -> {
				
				pOutput.accept(ModItems.TIMEWORN_JOURNAL.get());
				
				pOutput.accept(ModItems.VOIDBLIGHT_BUCKET.get());
				
				pOutput.accept(ModItems.EYE_FRAGMENT.get());
				
				pOutput.accept(ModItems.SMALL_VOID_DUST.get());
				pOutput.accept(ModItems.VOID_DUST.get());
				pOutput.accept(ModItems.RAW_VOIDMETAL.get());
				pOutput.accept(ModItems.VOIDMETAL_INGOT.get());
				pOutput.accept(ModItems.VOIDMETAL_NUGGET.get());
				pOutput.accept(ModItems.VOIDMETAL_SWORD.get());
				pOutput.accept(ModItems.VOIDMETAL_PICKAXE.get());
				pOutput.accept(ModItems.VOIDMETAL_AXE.get());
				pOutput.accept(ModItems.VOIDMETAL_SHOVEL.get());
				pOutput.accept(ModItems.VOIDMETAL_HOE.get());
				pOutput.accept(ModItems.VOIDMETAL_HELMET.get());
				pOutput.accept(ModItems.VOIDMETAL_CHESTPLATE.get());
				pOutput.accept(ModItems.VOIDMETAL_LEGGINGS.get());
				pOutput.accept(ModItems.VOIDMETAL_BOOTS.get());
				pOutput.accept(ModItems.VOIDMETAL_UPGRADE_SMITHING_TEMPLATE.get());
				
				pOutput.accept(ModItems.ENDERSENT_SPAWN_EGG.get());
				pOutput.accept(ModItems.ENDERSENT_WITH_EYE_SPAWN_EGG.get());

			})
			.build());
	
	public static void register(IEventBus EventBus) {
		CREATIVE_MODE_TABS.register(EventBus);
	}
}
