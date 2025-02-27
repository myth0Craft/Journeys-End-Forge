package net.je.datagen;

import java.util.concurrent.CompletableFuture;

import net.je.JourneysEnd;
import net.je.item.ModItems;
import net.je.loot.AddItemModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifierProviders extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProviders(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, JourneysEnd.MODID, registries);
    }

    @Override
    protected void start(HolderLookup.Provider registries) {
		/*
		 * this.add("kohlrabi_seeds_from_short_grass", new AddItemModifier(new
		 * LootItemCondition[] {
		 * LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.
		 * SHORT_GRASS).build(),
		 * LootItemRandomChanceCondition.randomChance(0.25f).build() },
		 * ModItems.VOIDMETAL_SWORD.get())); this.add("kohlrabi_seeds_to_tall_grass",
		 * new AddItemModifier(new LootItemCondition[] {
		 * LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.TALL_GRASS
		 * ).build(), LootItemRandomChanceCondition.randomChance(0.25f).build() },
		 * ModItems.KOHLRABI_SEEDS.get()));
		 * 
		 * this.add("chisel_from_jungle_temple", new AddItemModifier(new
		 * LootItemCondition[] { new
		 * LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace(
		 * "chests/jungle_temple")).build() }, ModItems.CHISEL.get()));
		 */

		/*
		 * add("void_dust_from_enderman", new AddItemModifier(new LootItemCondition[] {
		 * new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace(
		 * "entities/enderman"))
		 * .and(LootItemRandomChanceCondition.randomChance(0.5f)).build() },
		 * ModItems.VOID_DUST.get()));
		 */

    }
}