package net.je.block.custom;

import com.mojang.serialization.MapCodec;
import net.je.block.ModBlocks;
import net.je.block.entity.GravityDistorterBlockEntity;
import net.je.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class GravityDistorterBlock extends BaseEntityBlock {
	public static final MapCodec<GravityDistorterBlock> CODEC = simpleCodec(GravityDistorterBlock::new);

	public int numBlocksStacked = 0;

	private boolean blockAbove = false;

	private boolean blockBelow = false;

	public GravityDistorterBlock(Properties p_49795_) {
		super(p_49795_);
		this.numBlocksStacked = 1;
	}

	@Override
	protected MapCodec<GravityDistorterBlock> codec() {
		return CODEC;
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
		//if (pNeighborBlock.defaultBlockState().is(ModBlocks.GRAVITY_DISTORTER.get())) {
			if (pNeighborPos.equals(pPos.above())) {
				if (pLevel.getBlockState(pPos.above()).is(ModBlocks.GRAVITY_DISTORTER.get())) {
					//System.out.println("Gravity distorter placed above");

					GravityDistorterBlock block = (GravityDistorterBlock) pLevel.getBlockState(pPos.above()).getBlock();

					this.blockAbove = true;

					block.blockBelow = true;

				} else {
					this.blockAbove = false;
				}
			} else if (pNeighborPos.equals(pPos.below())) {
				if (pLevel.getBlockState(pPos.below()).is(ModBlocks.GRAVITY_DISTORTER.get())) {
					//System.out.println("Gravity distorter placed below");
					GravityDistorterBlock block = (GravityDistorterBlock) pLevel.getBlockState(pPos.below()).getBlock();

					this.blockBelow = true;

					block.blockAbove = true;
				} else {
					this.blockBelow = false;
				}



			}
		//}

		System.out.println("At position\nX: " + pPos.getX() + "\nY: " + pPos.getY() + "\nZ: " + pPos.getZ() +
				",\nBlock above: " + this.blockAbove + "\nBlock below: " + this.blockBelow);
	}
}
