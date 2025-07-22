package net.je.block.custom;

import java.util.Random;

import net.je.block.ModBlocks;
import net.je.particle.ModParticles;
import net.je.util.ClientModData;
import net.je.util.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ShadowBlock extends Block {

	public ShadowBlock(Properties p_312723_) {
		super(p_312723_);
	}

	@Override
	protected boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction pSide) {
		return pAdjacentBlockState.is(this) ? true : super.skipRendering(pState, pAdjacentBlockState, pSide);
	}
	
	@Override
	protected VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		if (pContext instanceof EntityCollisionContext entityContext) {
			Entity entity = entityContext.getEntity();
			if (entity instanceof LivingEntity living) {
				if (living.getMainHandItem().is(ModTags.Items.CAN_PASS_THROUGH_SHADOW_BLOCKS) || living.getOffhandItem().is(ModTags.Items.CAN_PASS_THROUGH_SHADOW_BLOCKS) || 
						living.getInBlockState().is(ModBlocks.SHADOW_BLOCK.get())) {
					return Shapes.empty();
				}
			}
		}
		
		return super.getCollisionShape(pState, pLevel, pPos, pContext);
		
    }
	
	@Override
	protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		if (pContext instanceof EntityCollisionContext entityContext) {
			Entity entity = entityContext.getEntity();
			if (entity instanceof LivingEntity living) {
				if (living.getMainHandItem().is(ModTags.Items.CAN_PASS_THROUGH_SHADOW_BLOCKS) || living.getOffhandItem().is(ModTags.Items.CAN_PASS_THROUGH_SHADOW_BLOCKS) || 
						living.getInBlockState().is(ModBlocks.SHADOW_BLOCK.get())) {
					return Shapes.empty();
				}
			}
		}
		return super.getShape(pState, pLevel, pPos, pContext);
    }
	
	/*
	 * @Override public VoxelShape getVisualShape(BlockState state, BlockGetter
	 * level, BlockPos pos, CollisionContext context) {
	 * 
	 * return Block.box(0, 0, 0, 16, 15.9, 16); }
	 */
	
	
	@Override
	protected void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
		super.entityInside(pState, pLevel, pPos, pEntity);
		/*
		 * if (!pLevel.isClientSide) {
		 * 
		 * ServerLevel server = (ServerLevel) pLevel; float min = -0.5f; float max =
		 * 0.25f; server.sendParticles(ParticleTypes.SMOKE, pPos.getX() + (Math.random()
		 * * ((max - min) + 1)), pPos.getY() + (Math.random() * ((max - min) + 1)),
		 * pPos.getZ() + (Math.random() * ((max - min) + 1)), 1, 0, 0, 0, 0); }
		 */
		
		/*
		 * if (pLevel.isClientSide && pEntity instanceof LocalPlayer player) { BlockPos
		 * eyeBlockPos = player.blockPosition().above();
		 * 
		 * if (eyeBlockPos == pPos) { ClientModData.setPlayerInsideShadowBlock(true); }
		 * }
		 */
    }
}
