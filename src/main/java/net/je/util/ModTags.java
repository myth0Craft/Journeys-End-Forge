package net.je.util;

import net.je.JourneysEnd;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.StructureTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.Structure;

public class ModTags {
	public static class Blocks {
        public static final TagKey<Block> NEEDS_VOIDMETAL_TOOL = createTag("needs_voidmetal_tool");
        public static final TagKey<Block> INCORRECT_FOR_VOIDMETAL_TOOL = createTag("incorrect_for_voidmetal_tool");
        public static final TagKey<Block> CORRUPTED_BLOCKS = createTag("corrupted_blocks");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, name));
        }
    }

	public static class Entities {
		public static final TagKey<EntityType<?>> END_MOBS = createTag("end_mobs");


		private static TagKey<EntityType<?>> createTag(String name) {
			return EntityTypeTags.create(ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, name));
		}
	}

	public static class Structures {
		//public static final TagKey<Structure> ENDERSENT_WELL = createTag("endersent_well");

		@SuppressWarnings("unused")
		private static TagKey<Structure> createTag(String name) {
			return StructureTags.create(ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, name));
		}
	}

}
