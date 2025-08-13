package net.je.block.custom;

import com.mojang.serialization.MapCodec;
import net.je.block.ModBlocks;
import net.je.block.entity.GravityDistorterBlockEntity;
import net.je.block.entity.ModBlockEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.DebugPackets;
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

public class GravityDistorterBlock extends BaseEntityBlock {
	public static final MapCodec<GravityDistorterBlock> CODEC = simpleCodec(GravityDistorterBlock::new);

	public static final BooleanProperty BLOCK_ABOVE = BooleanProperty.create("block_above");

	public static final BooleanProperty BLOCK_BELOW = BooleanProperty.create("block_below");


	public GravityDistorterBlock(Properties p_49795_) {
		super(p_49795_);
		this.registerDefaultState(this.defaultBlockState().setValue(BLOCK_ABOVE, false).setValue(BLOCK_BELOW, false));
		//this.numBlocksStacked = 1;
	}

	@Override
	protected MapCodec<GravityDistorterBlock> codec() {
		return CODEC;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(BLOCK_ABOVE, BLOCK_BELOW);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		Level level = pContext.getLevel();
		BlockPos pos = pContext.getClickedPos();

		boolean above = level.getBlockState(pos.above()).is(this);
		boolean below = level.getBlockState(pos.below()).is(this);

		return this.defaultBlockState().setValue(BLOCK_ABOVE, above).setValue(BLOCK_BELOW, below);
	}

	@Override
	public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new GravityDistorterBlockEntity(pPos, pState);
	}

	@Override
	protected RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
		return createTickerHelper(pBlockEntityType, ModBlockEntities.GRAVITY_DISTORTER_BLOCK_ENTITY.get(),
				GravityDistorterBlockEntity::tick);
	}

	@Override
	protected void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pNeighborBlock, BlockPos pNeighborPos, boolean pMovedByPiston) {
		super.neighborChanged(pState, pLevel, pPos, pNeighborBlock, pNeighborPos, pMovedByPiston);

		if (!pLevel.isClientSide) {

			boolean above = pLevel.getBlockState(pPos.above()).is(this);
			boolean below = pLevel.getBlockState(pPos.below()).is(this);

			if (pState.getValue(BLOCK_ABOVE) != above || pState.getValue(BLOCK_BELOW) != below) {
				pLevel.setBlock(pPos, pState.setValue(BLOCK_ABOVE, above).setValue(BLOCK_BELOW, below), Block.UPDATE_CLIENTS);
			}

			if (pNeighborBlock == this) {
				updateStackHeight(pLevel, pPos);
			}
		}
			/*BlockState state = pLevel.getBlockState(pPos);

			Minecraft.getInstance().player.sendSystemMessage(Component.literal("At position\nX: " + pPos.getX() + "\nY: " + pPos.getY() + "\nZ: " + pPos.getZ() +
					",\nBlock above: " + state.getValue(BLOCK_ABOVE) + "\nBlock below: " + state.getValue(BLOCK_BELOW)));*/
	}

	private void updateStackHeight(Level level, BlockPos pos) {
		BlockPos top = pos;
		while(level.getBlockState(top.above()).is(this)) {
			top = top.above();
		}

		int height = 0;
		BlockPos current = top;
		while (level.getBlockState(current).is(this)) {
			BlockEntity be = level.getBlockEntity(current);
			if (be instanceof GravityDistorterBlockEntity gdbe) {
				gdbe.numBlocksStacked = height + 1;
				gdbe.updateState();
			}
			height++;
			current = current.below();

			if (current.getY() < level.getMinBuildHeight()) break;
		}
	}
}