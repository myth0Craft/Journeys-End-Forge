package net.je.common.block.custom;

import com.mojang.serialization.MapCodec;
import net.je.common.block.entity.EnderVaultBlockEntity;
import net.je.common.block.entity.ShadowBeamEmitterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class EnderVaultBlock extends BaseEntityBlock {
	public static final MapCodec<EnderVaultBlock> CODEC = simpleCodec(EnderVaultBlock::new);

	public EnderVaultBlock(Properties pProperties) {
		super(pProperties);
	}

	@Override
	public MapCodec<EnderVaultBlock> codec() {
		return CODEC;
	}

	@Override
	public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new EnderVaultBlockEntity(pPos, pState);
	}

	@Override
	protected RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}
}
