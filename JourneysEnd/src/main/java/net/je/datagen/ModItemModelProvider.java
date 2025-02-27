package net.je.datagen;

import net.je.JourneysEnd;
import net.je.block.ModBlocks;
import net.je.item.ModItems;

import java.util.LinkedHashMap;

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
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, JourneysEnd.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.CORRUPTION_BUCKET.get());
        basicItem(ModItems.VOID_DUST.get());
        basicItem(ModItems.SMALL_VOID_DUST.get());
        basicItem(ModItems.RAW_VOIDMETAL.get());
        basicItem(ModItems.VOIDMETAL_INGOT.get());
        basicItem(ModItems.VOIDMETAL_NUGGET.get());
        
        handheldItem(ModItems.VOIDMETAL_PICKAXE);
        handheldItem(ModItems.VOIDMETAL_AXE);
        handheldItem(ModItems.VOIDMETAL_SHOVEL);
        handheldItem(ModItems.VOIDMETAL_HOE);
        handheldItem(ModItems.VOIDMETAL_SWORD);
    }
    
    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,"item/" + item.getId().getPath()));
    }
}