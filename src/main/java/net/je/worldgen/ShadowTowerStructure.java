package net.je.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.je.JourneysEnd;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.*;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.structures.EndCityStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

public class ShadowTowerStructure extends Structure {
	public static final MapCodec<ShadowTowerStructure> CODEC = simpleCodec(ShadowTowerStructure::new);

	protected ShadowTowerStructure(Structure.StructureSettings pSettings) {
		super(pSettings);
	}

	@Override
	protected Optional<GenerationStub> findGenerationPoint(GenerationContext pContext) {
		BlockPos chunkCenter = pContext.chunkPos().getMiddleBlockPosition(0);
		int baseY = 50;
		BlockPos currentPos = new BlockPos(chunkCenter.getX(), baseY, chunkCenter.getZ());

		return Optional.of(new GenerationStub(currentPos, (builder) -> {
			StructureTemplateManager templateManager = pContext.structureTemplateManager();

			int yOffset = 0;

			for (int i = 0; i < 12; i++) {
				ResourceLocation floorId = ResourceLocation.fromNamespaceAndPath(
						JourneysEnd.MODID,
						"shadow_tower/shadow_tower_" + (i + 1)
				);

				StructureTemplate template = templateManager.getOrCreate(floorId);
				BlockPos placementPos = currentPos.above(yOffset);

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

	@Override
	public StructureType<?> type() {
		return ModStructureTypes.SHADOW_TOWER.get();
	}
}
