package net.je.worldgen;

import com.mojang.serialization.MapCodec;
import net.je.JourneysEnd;
import net.je.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.Optional;

public class ShadowTowerStructure extends Structure {
	public static final MapCodec<ShadowTowerStructure> CODEC = simpleCodec(ShadowTowerStructure::new);

	protected ShadowTowerStructure(Structure.StructureSettings pSettings) {
		super(pSettings);
	}

	@Override
	protected Optional<GenerationStub> findGenerationPoint(GenerationContext pContext) {
		BlockPos chunkCenter = pContext.chunkPos().getMiddleBlockPosition(0);
		StructureTemplateManager templateManager = pContext.structureTemplateManager();
		ResourceLocation floor1Id = ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "shadow_tower/shadow_tower_2");
		StructureTemplate floor1Template = templateManager.getOrCreate(floor1Id);

		int width = floor1Template.getSize().getX();
		int length = floor1Template.getSize().getZ();

		int surfaceY = pContext.chunkGenerator().getBaseHeight(chunkCenter.getX(), chunkCenter.getZ(), Heightmap.Types.WORLD_SURFACE_WG,
				pContext.heightAccessor(), pContext.randomState());

		BlockPos startPos = new BlockPos(chunkCenter.getX(), surfaceY, chunkCenter.getZ());

		int startY = surfaceY - 15;

		//int minY = Integer.MAX_VALUE;
		for (int x = 0; x < width; x+=4) {
			for (int z = 0; z < length; z+=4) {
				int worldX = chunkCenter.getX() + x;
				int worldZ = chunkCenter.getZ() + z;

				int colHeight = pContext.chunkGenerator()
						.getFirstOccupiedHeight(worldX, worldZ, Heightmap.Types.WORLD_SURFACE_WG, pContext.heightAccessor(), pContext.randomState());
				if (colHeight < startY) {
					return Optional.empty();
				}

			}
		}




		return Optional.of(new GenerationStub(startPos, (builder) -> {


			int yOffset = 5;

			for (int i = 1; i < 12; i++) {
				ResourceLocation floorId = ResourceLocation.fromNamespaceAndPath(
						JourneysEnd.MODID,
						"shadow_tower/shadow_tower_" + (i + 1)
				);

				StructureTemplate template = templateManager.getOrCreate(floorId);
				BlockPos placementPos = startPos.above(yOffset);

				builder.addPiece(new ShadowTowerPiece(
						ModStructurePieceTypes.SHADOW_TOWER_PIECE.get(),
						floorId,
						placementPos,
						Rotation.NONE,
						templateManager
				));

				// increment by actual height of this floor
				yOffset += template.getSize().getY();
			}
		}));
	}
	@SuppressWarnings("deprecation")
	@Override
	public void afterPlace(
			WorldGenLevel pLevel,
			StructureManager pStructureManager,
			ChunkGenerator pChunkGenerator,
			RandomSource pRandom,
			BoundingBox pBoundingBox,
			ChunkPos pChunkPos,
			PiecesContainer pPieces
	) {
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
		int i = 31;
		BoundingBox boundingbox = pPieces.calculateBoundingBox();
		int j = boundingbox.minY();

		for (int k = pBoundingBox.minX(); k <= pBoundingBox.maxX(); k++) {
			for (int l = pBoundingBox.minZ(); l <= pBoundingBox.maxZ(); l++) {
				blockpos$mutableblockpos.set(k, j, l);
				if (!pLevel.isEmptyBlock(blockpos$mutableblockpos)
						&& boundingbox.isInside(blockpos$mutableblockpos)
						&& pPieces.isInsidePiece(blockpos$mutableblockpos)) {
					for (int i1 = j - 1; i1 > i; i1--) {
						blockpos$mutableblockpos.setY(i1);
						if (!pLevel.isEmptyBlock(blockpos$mutableblockpos) && !pLevel.getBlockState(blockpos$mutableblockpos).liquid()) {
							break;
						}

						pLevel.setBlock(blockpos$mutableblockpos, ModBlocks.SHADOW_STONE_BRICKS.get().defaultBlockState(), 2);
					}
				}
			}
		}
	}

	@Override
	public StructureType<?> type() {
		return ModStructureTypes.SHADOW_TOWER.get();
	}
}
