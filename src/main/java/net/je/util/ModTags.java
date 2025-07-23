package net.je.util;

import net.je.JourneysEnd;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

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


		/*
		 * private static TagKey<Structure> createTag(String name) { return
		 * StructureTags.create(ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
		 * name)); }
		 */
	}

	public static class Items {
		public static final TagKey<Item> WARDBREAKER = createTag("wardbreaker");

		public static final TagKey<Item> CAN_PASS_THROUGH_SHADOW_BLOCKS = createTag("can_pass_through_shadow_blocks");

		private static TagKey<Item> createTag(String name) {
			return ItemTags.create(ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, name));
		}
	}

}
