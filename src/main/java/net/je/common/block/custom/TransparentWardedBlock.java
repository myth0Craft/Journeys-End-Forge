package net.je.common.block.custom;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TransparentWardedBlock extends WardedBlock {

	public TransparentWardedBlock(Properties p_49795_) {
		super(p_49795_);
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.block();
	}

	@Override
	public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction pSide) {
		return (pAdjacentBlockState.is(this) || pAdjacentBlockState.getBlock() instanceof CustomTransparentBlock) ? true : super.skipRendering(pState, pAdjacentBlockState, pSide);
	}

}
