package net.je.datagen;

import net.je.JourneysEnd;
import net.je.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, JourneysEnd.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.VOIDMASS);
        blockWithItem(ModBlocks.COMPRESSED_END_STONE);
        blockWithItem(ModBlocks.VOIDMETAL_BLOCK);
        blockWithItem(ModBlocks.CHISELED_END_STONE);
        blockWithItem(ModBlocks.END_STONE_TILES);
        blockWithItem(ModBlocks.POLISHED_END_STONE);
        blockWithItem(ModBlocks.VOID_STONE);
        blockWithItem(ModBlocks.FADED_END_STONE);
        blockWithItem(ModBlocks.CORRUPTED_DIRT);
        blockWithItem(ModBlocks.LANTERN_OF_WARDING);

        blockWithItem(ModBlocks.SHADOW_STONE);
        blockWithItem(ModBlocks.SHADOW_STONE_BRICKS);
        blockWithItem(ModBlocks.POLISHED_SHADOW_STONE);
        blockWithItem(ModBlocks.WARDED_SHADOW_STONE, "shadow_stone");
        blockWithItem(ModBlocks.WARDED_SHADOW_STONE_BRICKS, "shadow_stone_bricks");
        blockWithItem(ModBlocks.WARDED_POLISHED_SHADOW_STONE, "polished_shadow_stone");
        blockWithItem(ModBlocks.CORRUPTED_SHADOW_STONE);
        blockWithItem(ModBlocks.WARDED_CORRUPTED_SHADOW_STONE, "corrupted_shadow_stone");
        blockWithItem(ModBlocks.BLIGHTED_SHADOW_STONE);
        blockWithItem(ModBlocks.WARDED_BLIGHTED_SHADOW_STONE, "blighted_shadow_stone");
        blockWithItem(ModBlocks.CRACKED_SHADOW_STONE_BRICKS);
        blockWithItem(ModBlocks.WARDED_CRACKED_SHADOW_STONE_BRICKS, "cracked_shadow_stone_bricks");
        blockWithItem(ModBlocks.CHISELED_SHADOW_STONE);
        blockWithItem(ModBlocks.WARDED_CHISELED_SHADOW_STONE, "chiseled_shadow_stone");

        blockWithItem(ModBlocks.VOID_LANTERN);

        blockWithItem(ModBlocks.FADED_END_STONE_BRICKS);
        blockWithItem(ModBlocks.WARDED_FADED_END_STONE_BRICKS, "faded_end_stone_bricks");

        //blockWithItem(ModBlocks.SHADOW_PRISM);
        //blockWithItem(ModBlocks.SHADOW_BLOCK);

        blockItem(ModBlocks.END_STONE_PILLAR);
        blockItem(ModBlocks.POLISHED_END_STONE_SLAB);
        blockItem(ModBlocks.POLISHED_END_STONE_STAIRS);
        blockItem(ModBlocks.LUSH_END_STONE);

        blockItem(ModBlocks.SHADOW_STONE_STAIRS);
        blockItem(ModBlocks.SHADOW_STONE_SLAB);

        blockItem(ModBlocks.POLISHED_SHADOW_STONE_STAIRS);
        blockItem(ModBlocks.POLISHED_SHADOW_STONE_SLAB);

        blockItem(ModBlocks.SHADOW_STONE_BRICK_STAIRS);
        blockItem(ModBlocks.SHADOW_STONE_BRICK_SLAB);

        blockItem(ModBlocks.VOIDGLASS);
        blockItem(ModBlocks.WARDED_VOIDGLASS, "voidglass");

        blockItem(ModBlocks.GRAVITY_DISTORTER);
        //blockItem(ModBlocks.RESPAWN_NEXUS);
        //blockItem(ModBlocks.ENDER_VAULT);
        //blockItem(ModBlocks.INTERDIMENSIONAL_ANCHOR);
        //blockItem(ModBlocks.POLISHED_END_STONE_WALL);

        axisBlock((RotatedPillarBlock) ModBlocks.END_STONE_PILLAR.get());

        stairsBlock(((StairBlock) ModBlocks.POLISHED_END_STONE_STAIRS.get()), blockTexture(ModBlocks.POLISHED_END_STONE.get()));
        slabBlock(((SlabBlock) ModBlocks.POLISHED_END_STONE_SLAB.get()), blockTexture(ModBlocks.POLISHED_END_STONE.get()), blockTexture(ModBlocks.POLISHED_END_STONE.get()));
        wallBlock(((WallBlock) ModBlocks.POLISHED_END_STONE_WALL.get()), blockTexture(ModBlocks.POLISHED_END_STONE.get()));

        stairsBlock(((StairBlock) ModBlocks.SHADOW_STONE_STAIRS.get()), blockTexture(ModBlocks.SHADOW_STONE.get()));
        slabBlock(((SlabBlock) ModBlocks.SHADOW_STONE_SLAB.get()), blockTexture(ModBlocks.SHADOW_STONE.get()), blockTexture(ModBlocks.SHADOW_STONE.get()));
        wallBlock(((WallBlock) ModBlocks.SHADOW_STONE_WALL.get()), blockTexture(ModBlocks.SHADOW_STONE.get()));

        stairsBlock(((StairBlock) ModBlocks.POLISHED_SHADOW_STONE_STAIRS.get()), blockTexture(ModBlocks.POLISHED_SHADOW_STONE.get()));
        slabBlock(((SlabBlock) ModBlocks.POLISHED_SHADOW_STONE_SLAB.get()), blockTexture(ModBlocks.POLISHED_SHADOW_STONE.get()), blockTexture(ModBlocks.POLISHED_SHADOW_STONE.get()));
        wallBlock(((WallBlock) ModBlocks.POLISHED_SHADOW_STONE_WALL.get()), blockTexture(ModBlocks.POLISHED_SHADOW_STONE.get()));

        stairsBlock(((StairBlock) ModBlocks.SHADOW_STONE_BRICK_STAIRS.get()), blockTexture(ModBlocks.SHADOW_STONE_BRICKS.get()));
        slabBlock(((SlabBlock) ModBlocks.SHADOW_STONE_BRICK_SLAB.get()), blockTexture(ModBlocks.SHADOW_STONE_BRICKS.get()), blockTexture(ModBlocks.SHADOW_STONE_BRICKS.get()));
        wallBlock(((WallBlock) ModBlocks.SHADOW_STONE_BRICK_WALL.get()), blockTexture(ModBlocks.SHADOW_STONE_BRICKS.get()));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("je:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject, String path) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).withPath(p -> "block/" + path)));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject, String path) {
        simpleBlockWithItem(blockRegistryObject.get(), models().cubeAll(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
        		ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).withPath(p -> "block/" + path)));
    }
}