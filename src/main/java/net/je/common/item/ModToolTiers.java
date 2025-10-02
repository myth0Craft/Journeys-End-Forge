package net.je.common.item;

import net.je.JourneysEnd;
import net.je.common.block.ModBlocks;
import net.je.common.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier VOIDMETAL = TierSortingRegistry.registerTier(new ForgeTier(5,2500, 9.0f, 5f, 18,
            ModTags.Blocks.NEEDS_VOIDMETAL_TOOL, () -> Ingredient.of(ModItems.VOIDMETAL_INGOT.get())),
            ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "voidmetal"), List.of(Tiers.NETHERITE), List.of());

    public static final Tier SHADOW_STEEL = TierSortingRegistry.registerTier(new ForgeTier(6,3000, 9.0f, 5f, 20,
            ModTags.Blocks.NEEDS_VOIDMETAL_TOOL, () -> Ingredient.of(ModBlocks.SHADOW_BLOCK.get())),
            ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "voidmetal"), List.of(Tiers.NETHERITE), List.of());
}