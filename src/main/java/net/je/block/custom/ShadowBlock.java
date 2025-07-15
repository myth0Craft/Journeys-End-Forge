package net.je.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ShadowBlock extends TransparentBlock {

	public ShadowBlock(Properties p_312723_) {
		super(p_312723_);
	}
	
	@Override
    protected VoxelShape getVisualShape(BlockState p_312193_, BlockGetter p_310654_, BlockPos p_310658_, CollisionContext p_311129_) {
        return Shapes.block();
    }

}
