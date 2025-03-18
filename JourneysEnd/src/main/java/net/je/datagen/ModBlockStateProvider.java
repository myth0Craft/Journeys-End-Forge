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
        blockWithItem(ModBlocks.SOLID_CORRUPTION);
        blockWithItem(ModBlocks.COMPRESSED_END_STONE);
        blockWithItem(ModBlocks.VOIDMETAL_BLOCK);
        blockWithItem(ModBlocks.CHISELED_END_STONE);
        blockWithItem(ModBlocks.END_STONE_TILES);
        blockWithItem(ModBlocks.POLISHED_END_STONE);
        blockWithItem(ModBlocks.VOID_STONE);
        blockWithItem(ModBlocks.FADED_END_STONE);
        blockWithItem(ModBlocks.CORRUPTED_DIRT);
        //blockWithItem(ModBlocks.LANTERN_OF_WARDING);
        
        blockItem(ModBlocks.END_STONE_PILLAR);
        blockItem(ModBlocks.POLISHED_END_STONE_SLAB);
        blockItem(ModBlocks.POLISHED_END_STONE_STAIRS);
        blockItem(ModBlocks.LUSH_END_STONE);
        //blockItem(ModBlocks.POLISHED_END_STONE_WALL);
        
        axisBlock((RotatedPillarBlock) ModBlocks.END_STONE_PILLAR.get());
        
        stairsBlock(((StairBlock) ModBlocks.POLISHED_END_STONE_STAIRS.get()), blockTexture(ModBlocks.POLISHED_END_STONE.get()));
        slabBlock(((SlabBlock) ModBlocks.POLISHED_END_STONE_SLAB.get()), blockTexture(ModBlocks.POLISHED_END_STONE.get()), blockTexture(ModBlocks.POLISHED_END_STONE.get()));
        wallBlock(((WallBlock) ModBlocks.POLISHED_END_STONE_WALL.get()), blockTexture(ModBlocks.POLISHED_END_STONE.get()));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("je:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }
    
    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}