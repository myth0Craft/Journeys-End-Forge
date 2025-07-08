package net.je.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import net.je.JourneysEnd;
import net.je.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture,
                              CompletableFuture<TagLookup<Block>> lookupCompletableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, completableFuture, lookupCompletableFuture, JourneysEnd.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.VOIDMETAL_HELMET.get())
                .add(ModItems.VOIDMETAL_CHESTPLATE.get())
                .add(ModItems.VOIDMETAL_LEGGINGS.get())
                .add(ModItems.VOIDMETAL_BOOTS.get());

        tag(ItemTags.TRIM_MATERIALS)
        .add(ModItems.VOIDMETAL_INGOT.get());

        tag(ItemTags.FOOT_ARMOR)
        .add(ModItems.VOIDMETAL_BOOTS.get());

        tag(ItemTags.LEG_ARMOR)
        .add(ModItems.VOIDMETAL_LEGGINGS.get());

        tag(ItemTags.CHEST_ARMOR)
        .add(ModItems.VOIDMETAL_CHESTPLATE.get());

        tag(ItemTags.HEAD_ARMOR)
        .add(ModItems.VOIDMETAL_HELMET.get());
    }
}