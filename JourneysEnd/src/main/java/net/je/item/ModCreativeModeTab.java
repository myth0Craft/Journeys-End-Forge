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

	public static final RegistryObject<CreativeModeTab> END_TAB = CREATIVE_MODE_TABS.register("je",
			() -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.VOIDMETAL_SWORD.get()))
			.title(Component.translatable("creativetab.je"))
			.displayItems((pParameters, pOutput) -> {
				
				pOutput.accept(ModItems.CORRUPTION_BUCKET.get());
				pOutput.accept(ModBlocks.SOLID_CORRUPTION.get());
				
				pOutput.accept(ModBlocks.COMPRESSED_END_STONE.get());
				pOutput.accept(ModBlocks.END_STONE_FURNACE.get());
				
				pOutput.accept(ModItems.SMALL_VOID_DUST.get());
				pOutput.accept(ModItems.VOID_DUST.get());
				pOutput.accept(ModItems.RAW_VOIDMETAL.get());
				pOutput.accept(ModItems.VOIDMETAL_INGOT.get());
				pOutput.accept(ModBlocks.VOIDMETAL_BLOCK.get());
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

			})
			.build());
	
	public static void register(IEventBus EventBus) {
		CREATIVE_MODE_TABS.register(EventBus);
	}
}
