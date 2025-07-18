package net.je.block.custom;

import javax.annotation.Nullable;

import com.mojang.serialization.MapCodec;

import net.je.block.entity.ModBlockEntities;
import net.je.block.entity.ShadowPrismBlockEntity;
import net.je.config.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EndGatewayBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ShadowPrismBlock extends BaseEntityBlock {
	public static final MapCodec<ShadowPrismBlock> CODEC = simpleCodec(ShadowPrismBlock::new);

	@Override
	public MapCodec<ShadowPrismBlock> codec() {
		return CODEC;
	}

	public ShadowPrismBlock(Properties p_49795_) {
		super(p_49795_);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new ShadowPrismBlockEntity(pPos, pState);
	}

	@Override
	protected RenderShape getRenderShape(BlockState pState) {
		if (CommonConfig.ALLOW_FANCY_VISUALS.get()) {
			return RenderShape.INVISIBLE;
		} else {
			return RenderShape.MODEL;
		}
	}

	/*
	 * @Nullable
	 * 
	 * @Override public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level
	 * pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) { return
	 * createTickerHelper(pBlockEntityType,
	 * ModBlockEntities.SHADOW_PRISM_BLOCK_ENTITY.get(), pLevel.isClientSide ?
	 * ShadowPrismBlockEntity::beamAnimationTick :
	 * ShadowPrismBlockEntity::portalTick); }
	 */
}
