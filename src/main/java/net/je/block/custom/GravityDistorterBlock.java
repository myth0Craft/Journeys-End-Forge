package net.je.block.custom;

import com.mojang.serialization.MapCodec;
import net.je.block.entity.GravityDistorterBlockEntity;
import net.je.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
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
import org.jetbrains.annotations.Nullable;

public class GravityDistorterBlock extends BaseEntityBlock {
	public static final MapCodec<GravityDistorterBlock> CODEC = simpleCodec(GravityDistorterBlock::new);

	public static final BooleanProperty BLOCK_ABOVE = BooleanProperty.create("block_above");
	public static final BooleanProperty BLOCK_BELOW = BooleanProperty.create("block_below");

	public GravityDistorterBlock(Properties props) {
		super(props);
		this.registerDefaultState(this.defaultBlockState()
				.setValue(BLOCK_ABOVE, false)
				.setValue(BLOCK_BELOW, false));
	}

	@Override
	protected MapCodec<GravityDistorterBlock> codec() {
		return CODEC;
	}

	@Override
	protected RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(BLOCK_ABOVE, BLOCK_BELOW);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		Level level = ctx.getLevel();
		BlockPos pos = ctx.getClickedPos();

		boolean above = level.getBlockState(pos.above()).is(this);
		boolean below = level.getBlockState(pos.below()).is(this);

		return this.defaultBlockState()
				.setValue(BLOCK_ABOVE, above)
				.setValue(BLOCK_BELOW, below);
	}

	@Override
	public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new GravityDistorterBlockEntity(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return createTickerHelper(type, ModBlockEntities.GRAVITY_DISTORTER_BLOCK_ENTITY.get(), GravityDistorterBlockEntity::tick);
	}

	/** Recalculate stack height */
	public void updateStackHeight(Level level, BlockPos pos) {
		BlockPos top = pos;
		while (level.getBlockState(top.above()).is(this)) top = top.above();

		int height = 0;
		BlockPos current = top;

		// Count total stack height
		while (level.getBlockState(current).is(this)) {
			height++;
			current = current.below();
			if (current.getY() < level.getMinBuildHeight()) break;
		}

		// Update all blocks
		current = top;
		while (level.getBlockState(current).is(this)) {
			BlockEntity be = level.getBlockEntity(current);
			if (be instanceof GravityDistorterBlockEntity gdbe) {
				gdbe.numBlocksStacked = height;
				gdbe.updateState();
			}
			current = current.below();
			if (current.getY() < level.getMinBuildHeight()) break;
		}
	}

	/** Call updateStackHeight whenever neighbors change or blocks are placed/removed */
	@Override
	protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos neighborPos, boolean moved) {
		super.neighborChanged(state, level, pos, block, neighborPos, moved);
		if (!level.isClientSide) updateStackHeight(level, pos);
	}

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		super.setPlacedBy(level, pos, state, placer, stack);
		if (!level.isClientSide) updateStackHeight(level, pos);
	}

	@Override
	public void onRemove(BlockState oldState, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
		super.onRemove(oldState, level, pos, newState, isMoving);
		if (!level.isClientSide && oldState.getBlock() != newState.getBlock()) updateStackHeight(level, pos);
	}
}