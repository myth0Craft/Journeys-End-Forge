package net.je.common.block.custom;

import com.mojang.serialization.MapCodec;
import net.je.common.block.entity.BejeweledPedestalBlockEntity;
import net.je.common.block.entity.EnderVaultBlockEntity;
import net.je.common.block.entity.ModBlockEntities;
import net.je.common.block.entity.ShadowBeamEmitterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.Nullable;

public class EnderVaultBlock extends BaseEntityBlock {
	public static final IntegerProperty WAVES_COMPLETE = IntegerProperty.create("waves", 0, 4);
	public static final BooleanProperty ACTIVE = BooleanProperty.create("active");


	public static final MapCodec<EnderVaultBlock> CODEC = simpleCodec(EnderVaultBlock::new);

	public EnderVaultBlock(Properties pProperties) {

		super(pProperties);
		this.registerDefaultState(this.defaultBlockState().setValue(ACTIVE, false).setValue(WAVES_COMPLETE, 0));
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		return this.defaultBlockState().setValue(WAVES_COMPLETE, 0).setValue(ACTIVE, false);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(WAVES_COMPLETE, ACTIVE);
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

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState,
																  BlockEntityType<T> pBlockEntityType) {
		return createTickerHelper(pBlockEntityType, ModBlockEntities.ENDER_VAULT_BLOCK_ENTITY.get(),
				EnderVaultBlockEntity::tick);
	}
}
