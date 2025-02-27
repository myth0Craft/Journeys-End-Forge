package net.je.util;

import net.je.JourneysEnd;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
	public static class Blocks {
        public static final TagKey<Block> NEEDS_VOIDMETAL_TOOL = createTag("needs_voidmetal_tool");
        public static final TagKey<Block> INCORRECT_FOR_VOIDMETAL_TOOL = createTag("incorrect_for_voidmetal_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, name));
        }
    }
}
