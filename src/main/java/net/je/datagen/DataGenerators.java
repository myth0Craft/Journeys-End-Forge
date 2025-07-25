package net.je.datagen;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import net.je.JourneysEnd;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JourneysEnd.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput, lookupProvider));

		generator.addProvider(event.includeServer(),
				new LootTableProvider(packOutput, Collections.emptySet(), List.of(
						new LootTableProvider.SubProviderEntry(ModBlockLootTables::new, LootContextParamSets.BLOCK)),
						lookupProvider));

		generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));
		generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, existingFileHelper));
		BlockTagsProvider blockTagsProvider = new ModBlockTagProvider(packOutput, lookupProvider, existingFileHelper);
		generator.addProvider(event.includeServer(), blockTagsProvider);
		generator.addProvider(event.includeServer(), new ModItemTagProvider(packOutput, lookupProvider,
				blockTagsProvider.contentsGetter(), existingFileHelper));
		generator.addProvider(event.includeServer(), new ModDatapackEntries(packOutput, lookupProvider));

		generator.addProvider(event.includeServer(), new ModAdvancementProvider(packOutput, lookupProvider));

	}
}