package net.je.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.je.JourneysEnd;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraftforge.server.command.EnumArgument;

import java.util.function.Function;

public class ShadowTowerPiece extends TemplateStructurePiece {
	public static final Codec<ShadowTowerPiece> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			ResourceLocation.CODEC.fieldOf("template").forGetter(p -> ResourceLocation.parse(p.templateName)),
			BlockPos.CODEC.fieldOf("pos").forGetter(TemplateStructurePiece::templatePosition),
			Rotation.CODEC.fieldOf("rotation").forGetter(TemplateStructurePiece::getRotation)
	).apply(instance, (templateId, pos, rotation) -> new ShadowTowerPiece(
			ModStructurePieceTypes.SHADOW_TOWER_PIECE.get(),
			templateId,
			pos,
			rotation
	)));

	// Constructor for direct use during generation
	public ShadowTowerPiece(StructurePieceType type, ResourceLocation templateId, BlockPos pos, Rotation rotation, StructureTemplateManager templates) {
		super(
				type,
				0,
				templates,
				templateId,
				templateId.toString(),
				createSettings(rotation),
				pos
		);
	}

	// Constructor used for deserialization (Codec)
	public ShadowTowerPiece(StructurePieceType type, ResourceLocation templateId, BlockPos pos, Rotation rotation) {
		super(
				type,
				0,
				null, // StructureTemplateManager will be passed during loading
				templateId,
				templateId.toString(),
				createSettings(rotation),
				pos
		);
	}

	public ShadowTowerPiece(StructurePieceType type, CompoundTag tag, StructureTemplateManager templateManager) {
		super(
				type,
				tag,
				templateManager,
				id -> new StructurePlaceSettings()
						.setRotation(Rotation.NONE)
						.setMirror(Mirror.NONE)
						.addProcessor(BlockIgnoreProcessor.STRUCTURE_AND_AIR)
						.setIgnoreEntities(true)
		);
	}

	// Constructor used by the system when loading from NBT/Codec
	public ShadowTowerPiece(StructurePieceType type, CompoundTag tag, StructureTemplateManager templates,
							Function<ResourceLocation, StructurePlaceSettings> settingsFactory) {
		super(type, tag, templates, settingsFactory);
	}

	private static StructurePlaceSettings createSettings(Rotation rotation) {
		return new StructurePlaceSettings()
				.setRotation(rotation)
				.setMirror(Mirror.NONE)
				.setIgnoreEntities(true)
				.addProcessor(BlockIgnoreProcessor.STRUCTURE_AND_AIR);
	}

	@Override
	protected void handleDataMarker(String pName, BlockPos pPos, ServerLevelAccessor pLevel, RandomSource pRandom, BoundingBox pBox) {
	}

	@Override
	public void postProcess(
			WorldGenLevel pLevel,
			StructureManager pStructureManager,
			ChunkGenerator pGenerator,
			RandomSource pRandom,
			BoundingBox pBox,
			ChunkPos pChunkPos,
			BlockPos pPos
	) {
		carveInterior(pLevel);
		super.postProcess(pLevel, pStructureManager, pGenerator, pRandom, pBox, pChunkPos, pPos);
	}

	private void carveInterior(WorldGenLevel pLevel) {
		StructureTemplate template = this.template();

		StructurePlaceSettings settings = this.placeSettings;

		for (StructureTemplate.StructureBlockInfo blockInfo : template.filterBlocks(this.templatePosition, settings, Blocks.AIR)) {
			BlockPos worldPos = blockInfo.pos();

			BlockState state = blockInfo.state();

			if (isMarker(state)) {
				pLevel.setBlock(worldPos, Blocks.AIR.defaultBlockState(), 2);
			}
		}
	}

	private boolean isMarker(BlockState state) {
		return state.is(Blocks.BARRIER);
	}
}