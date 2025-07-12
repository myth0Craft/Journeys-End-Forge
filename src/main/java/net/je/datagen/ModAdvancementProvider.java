package net.je.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import net.je.JourneysEnd;
import net.je.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ModAdvancementProvider implements DataProvider {

	private final PackOutput output;
	private final CompletableFuture<HolderLookup.Provider> lookupProvider;

	public ModAdvancementProvider(PackOutput poutput, CompletableFuture<HolderLookup.Provider> plookupProvider) {
		this.output = poutput;
		this.lookupProvider = plookupProvider;
	}

	@SuppressWarnings({ "deprecation", "unused" })
	@Override
	public CompletableFuture<?> run(CachedOutput pOutput) {

		AdvancementSubProvider subProvider = (lookup, consumer) -> {

			AdvancementHolder rootAdvancement = Advancement.Builder.advancement()
					.display(ModItems.TIMEWORN_JOURNAL_T0.get(),
							Component.translatable("advancements.je.timeworn_journal_t0.title"),
							Component.translatable("advancements.je.timeworn_journal_t0.desc"),
							ResourceLocation.withDefaultNamespace("textures/gui/advancements/backgrounds/end.png"),
							AdvancementType.TASK, true, true, false)
					.addCriterion("has_timeworn_journal_t0",
							InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIMEWORN_JOURNAL_T0.get()))
					.save(consumer, ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "timeworn_journal_t0"));

			//Holder<Structure> endersentWell = Holder.Reference.createStandAlone(lookup.lookupOrThrow(Registries.STRUCTURE), ModStructures.ENDERSENT_WELL);

			//HolderOwner<Structure> owner = lookup.lookupOrThrow(Registries.STRUCTURE);

			//HolderLookup.RegistryLookup<Structure> structures = lookup.lookupOrThrow(Registries.STRUCTURE);

			//TagKey<Structure> tag = TagKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "endersent_well"));


			/*
			 * ResourceKey<Structure> endersentWell = ResourceKey.create(
			 * Registries.STRUCTURE,
			 * ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "endersent_well"));
			 *
			 * Holder<Structure> holder = Holder.Reference.createStandAlone(owner,
			 * endersentWell);
			 *
			 * //HolderSet<Structure> holderSet = HolderSet.direct(holder);
			 *
			 * AdvancementHolder endersentWellAdvancement =
			 * Advancement.Builder.advancement() .display(Blocks.STONE_BRICKS,
			 * Component.translatable("advancements.je.endersent_well.title"),
			 * Component.translatable("advancements.je.endersent_well.desc"), null,
			 * AdvancementType.TASK, true, true, false)
			 * .addCriterion("entered_endersent_well",
			 * PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(
			 * holder))) .save(consumer,
			 * ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "endersent_well"));
			 */



			@SuppressWarnings("removal")
			AdvancementHolder eyeFragAdvancement = Advancement.Builder.advancement()
					.parent(ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "endersent_well"))
					.display(ModItems.EYE_FRAGMENT.get(), Component.translatable("advancements.je.eye_fragment.title"),
							Component.translatable("advancements.je.eye_fragment.desc"), null, AdvancementType.TASK, true,
							true, false)
					.addCriterion("has_eye_fragment", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EYE_FRAGMENT.get()))
					.save(consumer, ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "eye_fragment"));

		};

		AdvancementProvider provider = new AdvancementProvider(output, lookupProvider, List.of(subProvider));

		return provider.run(pOutput);
	}

	@Override
	public String getName() {
		return "Journey's End";
	}

}
