package net.je.common.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Map;

public class WardedBlockProcessor extends StructureProcessor {
	public static final MapCodec<WardedBlockProcessor> CODEC = RecordCodecBuilder.mapCodec(instance ->
			instance.group(
					Codec.unboundedMap(
							ForgeRegistries.BLOCKS.getCodec(),
							ForgeRegistries.BLOCKS.getCodec()
					).fieldOf("replacements").forGetter(p -> p.replacementMap)
			).apply(instance, WardedBlockProcessor::new)
	);

	/*@Override
	public MapCodec<WardedBlockProcessor> codec() {
		return CODEC;
	}*/

	private final Map<Block, Block> replacementMap;

	public WardedBlockProcessor(Map<Block, Block> replacementMap) {
		this.replacementMap = replacementMap;
	}

	@Override
	public StructureTemplate.StructureBlockInfo process(
			LevelReader level,
			BlockPos pos,
			BlockPos templatePos,
			StructureTemplate.StructureBlockInfo original,
			StructureTemplate.StructureBlockInfo current,
			StructurePlaceSettings settings,
			@Nullable StructureTemplate template
	) {
		Block replacement = replacementMap.get(current.state().getBlock());
		if (replacement != null) {
			BlockState newState = replacement.defaultBlockState();
			/*for (var entry : current.state().getValues().entrySet()) {
				if (newState.hasProperty(entry.getKey())) {
					newState = newState.setValue(entry.getKey(), entry.getValue());
				}
			}*/
			System.out.println("processed");
			return new StructureTemplate.StructureBlockInfo(current.pos(), newState, current.nbt());

		} else {
			System.out.println("processed");
			return current;
		}
	}

	@Override
	protected StructureProcessorType<?> getType() {
		return ModStructureProcessors.WARDED_BLOCK_PROCESSOR.get();
	}


}