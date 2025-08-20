package net.je.common.item;

import net.je.common.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModToolTiers {
    public static final Tier VOIDMETAL = new ForgeTier(2500, 9.0f, 5f, 18,
            ModTags.Blocks.NEEDS_VOIDMETAL_TOOL, () -> Ingredient.of(ModItems.VOIDMETAL_INGOT.get()),
            ModTags.Blocks.INCORRECT_FOR_VOIDMETAL_TOOL);
}