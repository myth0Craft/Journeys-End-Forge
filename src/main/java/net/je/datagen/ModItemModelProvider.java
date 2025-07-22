package net.je.datagen;

import java.util.LinkedHashMap;

import net.je.JourneysEnd;
import net.je.block.ModBlocks;
import net.je.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
	private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

	public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, JourneysEnd.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.VOIDBLIGHT_BUCKET.get());
        basicItem(ModItems.VOID_DUST.get());
        basicItem(ModItems.SMALL_VOID_DUST.get());
        basicItem(ModItems.RAW_VOIDMETAL.get());
        basicItem(ModItems.VOIDMETAL_INGOT.get());
        basicItem(ModItems.VOIDMETAL_NUGGET.get());
        basicItem(ModItems.VOIDMETAL_UPGRADE_SMITHING_TEMPLATE.get());
        basicItem(ModItems.EYE_FRAGMENT.get());
        basicItem(ModItems.TIMEWORN_JOURNAL_T0.get());
        basicItem(ModItems.TIMEWORN_JOURNAL_T1.get());
        //basicItem(ModItems.ECLIPSE_KEY.get());


        handheldItem(ModItems.VOIDMETAL_PICKAXE);
        handheldItem(ModItems.VOIDMETAL_AXE);
        handheldItem(ModItems.VOIDMETAL_SHOVEL);
        handheldItem(ModItems.VOIDMETAL_HOE);
        handheldItem(ModItems.VOIDMETAL_SWORD);

        withExistingParent(ModItems.WARDBREAKER_PICKAXE.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,"item/" + ModItems.VOIDMETAL_PICKAXE.getId().getPath()));

        trimmedArmorItem(ModItems.VOIDMETAL_HELMET);
        trimmedArmorItem(ModItems.VOIDMETAL_CHESTPLATE);
        trimmedArmorItem(ModItems.VOIDMETAL_LEGGINGS);
        trimmedArmorItem(ModItems.VOIDMETAL_BOOTS);

        wallItem(ModBlocks.POLISHED_END_STONE_WALL, ModBlocks.POLISHED_END_STONE);

        blockItem(ModBlocks.SHADOW_BLOCK.get());
    }

    private ItemModelBuilder blockItem(Block block) {
    	return withExistingParent(ForgeRegistries.BLOCKS.getKey(block).getPath(),
            	modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block).getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,"item/" + item.getId().getPath()));
    }

    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = JourneysEnd.MODID;

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {
                float trimValue = value;

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = armorItem.toString();
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.parse(armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.parse(trimPath);
                ResourceLocation trimNameResLoc = ResourceLocation.parse(currentTrimName);

                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc.getNamespace() + ":item/" + armorItemResLoc.getPath())
                        .texture("layer1", trimResLoc);

                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace()  + ":item/" + trimNameResLoc.getPath()))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
}