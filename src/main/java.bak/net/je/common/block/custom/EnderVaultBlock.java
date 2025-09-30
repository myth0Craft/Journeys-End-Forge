package net.je.common.block.custom;

import com.mojang.serialization.MapCodec;
import net.je.common.block.entity.BejeweledPedestalBlockEntity;
import net.je.common.block.entity.EnderVaultBlockEntity;
import net.je.common.block.entity.ModBlockEntities;
import net.je.common.block.entity.ShadowBeamEmitterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
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
import net.minecraftforge.unsafe.UnsafeFieldAccess;
import org.jetbrains.annotations.Nullable;

public class EnderVaultBlock extends BaseEntityBlock {
	public static final IntegerProperty WAVES_COMPLETE = IntegerProperty.create("waves", 0, 4);
	public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
	public static final BooleanProperty SHOULD_SPAWN_KEY = BooleanProperty.create("should_spawn_key");


	public static final MapCodec<EnderVaultBlock> CODEC = simpleCodec(EnderVaultBlock::new);

	public EnderVaultBlock(Properties pProperties) {

		super(pProperties);
		this.registerDefaultState(this.defaultBlockState().setValue(ACTIVE, false).setValue(WAVES_COMPLETE, 0).setValue(SHOULD_SPAWN_KEY, false));
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		return this.defaultBlockState().setValue(WAVES_COMPLETE, 0).setValue(ACTIVE, false).setValue(SHOULD_SPAWN_KEY, false);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(WAVES_COMPLETE, ACTIVE, SHOULD_SPAWN_KEY);
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

	@Override
	public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
		if (pLevel.getBlockEntity(pPos) instanceof EnderVaultBlockEntity evbe && evbe.finished) {
			double d0 = (double) pPos.getX() + 0.5;
			double d1 = (double) pPos.getY() + 1.0;
			double d2 = (double) pPos.getZ() + 0.5;
			pLevel.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0, 0.0, 0.0);
		}
	}
}
