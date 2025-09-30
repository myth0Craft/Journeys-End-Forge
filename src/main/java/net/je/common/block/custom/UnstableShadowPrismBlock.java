package net.je.common.block.custom;

import com.mojang.serialization.MapCodec;
import net.je.common.block.entity.GravityDistorterBlockEntity;
import net.je.common.block.entity.ModBlockEntities;
import net.je.common.block.entity.UnstableShadowPrismBlockEntity;
import net.je.common.block.entity.UnstableShadowPrismBlockEntity;
import net.je.config.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class UnstableShadowPrismBlock extends BaseEntityBlock {

	public UnstableShadowPrismBlock(Properties p_49795_) {
		super(p_49795_);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new UnstableShadowPrismBlockEntity(pPos, pState);
	}

	@Override
	public RenderShape getRenderShape(BlockState pState) {
		if (CommonConfig.ALLOW_FANCY_VISUALS.get()) {
			return RenderShape.INVISIBLE;
		} else {
			return RenderShape.MODEL;
		}
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return createTickerHelper(type, ModBlockEntities.UNSTABLE_SHADOW_PRISM_BLOCK_ENTITY.get(), UnstableShadowPrismBlockEntity::tick);
	}
}
