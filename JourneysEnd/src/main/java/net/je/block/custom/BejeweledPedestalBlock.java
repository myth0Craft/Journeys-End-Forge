package net.je.block.custom;

import com.mojang.serialization.MapCodec;

import net.je.block.entity.BejeweledPedestalBlockEntity;
import net.je.block.entity.EndStoneFurnaceBlockEntity;
import net.je.block.entity.ModBlockEntities;
import net.je.entity.ModEntities;
import net.je.particle.ModParticles;
import net.je.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.EnchantingTableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BejeweledPedestalBlock extends BaseEntityBlock {
	public static final MapCodec<BejeweledPedestalBlock> CODEC = simpleCodec(BejeweledPedestalBlock::new);
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty HAS_EYE = BooleanProperty.create("eye");
	protected static final VoxelShape BASE_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 13.0, 16.0);
	protected static final VoxelShape EYE_SHAPE = Block.box(4.0, 13.0, 4.0, 12.0, 16.0, 12.0);
	protected static final VoxelShape FULL_SHAPE = Shapes.or(BASE_SHAPE, EYE_SHAPE);

	public BejeweledPedestalBlock(Properties pProperties) {
		super(pProperties);
		this.registerDefaultState(this.defaultBlockState().setValue(HAS_EYE, true));
	}

	@Override
	protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return pState.getValue(HAS_EYE) ? FULL_SHAPE : BASE_SHAPE;
	}

	@Override
	protected RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}

	@Override
	protected MapCodec<BejeweledPedestalBlock> codec() {
		return CODEC;
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection()).setValue(HAS_EYE,
				Boolean.valueOf(true));
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer,
			BlockHitResult pHitResult) {

		if (pLevel.getBlockEntity(pPos) instanceof BejeweledPedestalBlockEntity be) {
			if (be.getBlockState().getValue(HAS_EYE)) {
				if (pLevel.isClientSide) {
					float pitch = (float) ((Math.random() * 0.1) + 0.6);
					pLevel.playLocalSound(pPos.getX(), pPos.above().getY(), pPos.getZ(),
							ModSounds.ENDERSENT_SUMMON.get(), SoundSource.BLOCKS, 1.0F, pitch, false);
					return InteractionResult.SUCCESS;

				} else {
					be.spawnEndersent();
					Direction facing = pLevel.getBlockState(pPos).getValue(FACING);
					pLevel.setBlockAndUpdate(pPos,
							this.stateDefinition.any().setValue(HAS_EYE, false).setValue(FACING, facing));
					return InteractionResult.CONSUME;
				}
			} else {
				return InteractionResult.FAIL;
			}

		} else {
			return InteractionResult.FAIL;
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(FACING, HAS_EYE);
	}

	@Override
	protected boolean hasAnalogOutputSignal(BlockState pState) {
		return true;
	}

	@Override
	protected int getAnalogOutputSignal(BlockState pBlockState, Level pLevel, BlockPos pPos) {
		return pBlockState.getValue(HAS_EYE) ? 15 : 0;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new BejeweledPedestalBlockEntity(pPos, pState);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState,
			BlockEntityType<T> pBlockEntityType) {
		return createTickerHelper(pBlockEntityType, ModBlockEntities.BEJEWELED_PEDESTAL_BE.get(),
				BejeweledPedestalBlockEntity::tick);
	}
}