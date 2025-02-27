package net.je.screen;

import net.je.JourneysEnd;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {

	public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU,
			JourneysEnd.MODID);

	//public static final RegistryObject<MenuType<EndStoneFurnaceMenu>> END_STONE_FURNACE_MENU = registerMenuType(
			//"end_stone_furnace_menu", EndStoneFurnaceMenu::new);
	
	public static final RegistryObject<MenuType<EndStoneFurnaceMenu>> END_STONE_FURNACE_MENU = MENUS.register("end_stone_furnace_menu", () -> IForgeMenuType.create((windowId, inv, data) -> {
		return new EndStoneFurnaceMenu(windowId, inv);
    }));
 
	/*
	 * private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>>
	 * registerMenuType(String name, IContainerFactory<T> factory) { return
	 * MENUS.register(name, () -> IForgeMenuType.create(factory)); }
	 */

	public static void register(IEventBus eventBus) {
		MENUS.register(eventBus);
	}

}