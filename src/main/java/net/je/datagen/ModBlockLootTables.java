package net.je.datagen;

import java.util.Set;

import net.je.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockLootTables extends BlockLootSubProvider {
    protected ModBlockLootTables(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {

        dropSelf(ModBlocks.END_STONE_FURNACE.get());
        dropSelf(ModBlocks.VOIDMASS.get());
        dropSelf(ModBlocks.COMPRESSED_END_STONE.get());
        dropSelf(ModBlocks.VOIDMETAL_BLOCK.get());
        dropSelf(ModBlocks.END_STONE_PILLAR.get());
        dropSelf(ModBlocks.CHISELED_END_STONE.get());
        dropSelf(ModBlocks.END_STONE_TILES.get());
        dropSelf(ModBlocks.POLISHED_END_STONE.get());
        dropSelf(ModBlocks.POLISHED_END_STONE_STAIRS.get());
        this.add(ModBlocks.POLISHED_END_STONE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.POLISHED_END_STONE_SLAB.get()));
        dropSelf(ModBlocks.POLISHED_END_STONE_WALL.get());
        dropSelf(ModBlocks.VOID_STONE.get());
        dropSelf(ModBlocks.FADED_END_STONE.get());
        dropSelf(ModBlocks.LUSH_END_STONE.get());
        dropSelf(ModBlocks.CORRUPTED_DIRT.get());
        dropSelf(ModBlocks.LANTERN_OF_WARDING.get());
        dropSelf(ModBlocks.INTERDIMENSIONAL_ANCHOR.get());
        dropSelf(ModBlocks.SHADOW_STONE_BRICKS.get());
        dropSelf(ModBlocks.WARDED_SHADOW_STONE_BRICKS.get());

        LootTable.Builder loottable$builder = this.createSilkTouchOrShearsDispatchTable(
        		ModBlocks.VOIDBLOOM.get(),
                LootItem.lootTableItem(Blocks.AIR));
            this.add(ModBlocks.VOIDBLOOM.get(), loottable$builder);







    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                pBlock, this.applyExplosionDecay(
                        pBlock, LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}