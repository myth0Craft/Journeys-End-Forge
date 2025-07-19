package net.je.item;

import java.util.List;

import net.je.JourneysEnd;
import net.je.entity.ModEntities;
import net.je.fluid.ModFluids;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			JourneysEnd.MODID);

	public static final RegistryObject<ForgeSpawnEggItem> ENDERSENT_SPAWN_EGG = ITEMS.register("endersent_spawn_egg",
			() -> new ForgeSpawnEggItem(ModEntities.ENDERSENT, 0, 12844, new Item.Properties()));

	public static final RegistryObject<ForgeSpawnEggItem> ENDERSENT_WITH_EYE_SPAWN_EGG = ITEMS.register(
			"endersent_with_eye_spawn_egg",
			() -> new ForgeSpawnEggItem(ModEntities.ENDERSENT_WITH_EYE, 0, 28249, new Item.Properties()));

	public static final RegistryObject<Item> VOIDBLIGHT_BUCKET = ITEMS.register("voidblight_bucket",
			() -> new VoidblightBucketItem(ModFluids.SOURCE_VOIDBLIGHT,
					new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

	public static final RegistryObject<Item> VOID_DUST = ITEMS.register("void_dust",
			() -> new Item(new Item.Properties()));

	public static final RegistryObject<Item> SMALL_VOID_DUST = ITEMS.register("small_void_dust",
			() -> new Item(new Item.Properties()));

	public static final RegistryObject<Item> RAW_VOIDMETAL = ITEMS.register("raw_voidmetal",
			() -> new Item(new Item.Properties()));

	public static final RegistryObject<Item> VOIDMETAL_INGOT = ITEMS.register("voidmetal_ingot",
			() -> new Item(new Item.Properties()));

	public static final RegistryObject<Item> VOIDMETAL_NUGGET = ITEMS.register("voidmetal_nugget",
			() -> new Item(new Item.Properties()));

	public static final RegistryObject<Item> VOIDMETAL_UPGRADE_SMITHING_TEMPLATE = ITEMS
			.register("voidmetal_upgrade_smithing_template", () -> new VoidmetalUpgradeTemplateItem());

	public static final RegistryObject<Item> TIMEWORN_JOURNAL_T0 = ITEMS.register("timeworn_journal_item_t0",
			() -> new TimewornJournalItem(new Item.Properties().stacksTo(1), 0));

	public static final RegistryObject<Item> TIMEWORN_JOURNAL_T1 = ITEMS.register("timeworn_journal_item_t1",
			() -> new TimewornJournalItem(new Item.Properties().stacksTo(1), 1));

	public static final RegistryObject<Item> VOIDMETAL_SWORD = ITEMS.register("voidmetal_sword",
			() -> new SwordItem(ModToolTiers.VOIDMETAL,
					new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.VOIDMETAL, 3, -2.4f))));
	public static final RegistryObject<Item> VOIDMETAL_PICKAXE = ITEMS.register("voidmetal_pickaxe",
			() -> new PickaxeItem(ModToolTiers.VOIDMETAL, new Item.Properties()
					.attributes(PickaxeItem.createAttributes(ModToolTiers.VOIDMETAL, 1.0f, -2.8f))));
	public static final RegistryObject<Item> VOIDMETAL_AXE = ITEMS.register("voidmetal_axe",
			() -> new AxeItem(ModToolTiers.VOIDMETAL,
					new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.VOIDMETAL, 5.0f, -3.0f))));
	public static final RegistryObject<Item> VOIDMETAL_SHOVEL = ITEMS.register("voidmetal_shovel", () -> new ShovelItem(
			ModToolTiers.VOIDMETAL,
			new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.VOIDMETAL, 1.5f, -3.0f))));
	public static final RegistryObject<Item> VOIDMETAL_HOE = ITEMS.register("voidmetal_hoe",
			() -> new HoeItem(ModToolTiers.VOIDMETAL,
					new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.VOIDMETAL, -5.0f, 0.0f))));

	public static final RegistryObject<Item> VOIDMETAL_HELMET = ITEMS.register("voidmetal_helmet",
			() -> new ArmorItem(ModArmorMaterials.VOIDMETAL_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
					new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(40))));
	public static final RegistryObject<Item> VOIDMETAL_CHESTPLATE = ITEMS.register("voidmetal_chestplate",
			() -> new ArmorItem(ModArmorMaterials.VOIDMETAL_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
					new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(40))));
	public static final RegistryObject<Item> VOIDMETAL_LEGGINGS = ITEMS.register("voidmetal_leggings",
			() -> new ArmorItem(ModArmorMaterials.VOIDMETAL_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
					new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(40))));
	public static final RegistryObject<Item> VOIDMETAL_BOOTS = ITEMS.register("voidmetal_boots",
			() -> new ArmorItem(ModArmorMaterials.VOIDMETAL_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
					new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(40))));

	public static final RegistryObject<Item> EYE_FRAGMENT = ITEMS.register("eye_fragment",
			() -> new Item(new Item.Properties()));

	public static final RegistryObject<Item> WARDBREAKER_PICKAXE = ITEMS.register("wardbreaker_pickaxe",
			() -> new PickaxeItem(ModToolTiers.VOIDMETAL, new Item.Properties()
					.attributes(PickaxeItem.createAttributes(ModToolTiers.VOIDMETAL, 1.0f, -2.8f))) {
				@Override
				public void appendHoverText(ItemStack pStack, TooltipContext pContext,
						List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
					pTooltipComponents.add(Component.translatable("tooltip.je.placeholder"));
					super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
				}
			});

	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}
