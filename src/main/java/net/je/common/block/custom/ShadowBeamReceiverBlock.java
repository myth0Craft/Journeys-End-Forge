package net.je.common.block.custom;

import net.je.common.block.ModBlocks;
import net.je.common.block.entity.ShadowBeamEmitterBlockEntity;
import net.je.common.particle.ModParticles;
import net.je.common.util.ModTags;
import net.je.config.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ShadowBeamReceiverBlock extends Block {

	public boolean placedByPlayer;

	public ShadowBeamReceiverBlock(Properties p_49795_) {
		super(p_49795_);
	}

	@Override
	public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
		notifyEmittersBelow(level, pos);
		super.onPlace(state, level, pos, oldState, isMoving);

	}

	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {

		if (!state.is(newState.getBlock())) {
			super.onRemove(state, level, pos, newState, isMoving);
			notifyEmittersBelow(level, pos); // do this after it’s truly gone
		} else {
			super.onRemove(state, level, pos, newState, isMoving);
		}


	}

	private void notifyEmittersBelow(Level level, BlockPos pos) {
		for (int i = 1; i <= 10; i++) {
			BlockEntity be = level.getBlockEntity(pos.below(i));
			if (be instanceof ShadowBeamEmitterBlockEntity emitter) {
				emitter.checkForReceiver();
				break;
			}
		}
	}

	@Override
	protected void attack(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
		if (CommonConfig.ALLOW_WARDED_BLOCKS.get()) {
			ItemStack item = pPlayer.getMainHandItem();
			if (!item.is(ModTags.Items.WARDBREAKER)) {
				/*
				 * pPlayer.sendSystemMessage(Component.translatable("message.je.warded_block")
				 * .withStyle(ChatFormatting.DARK_PURPLE, ChatFormatting.ITALIC));
				 */
				if (!pLevel.isClientSide) {
					ServerLevel server = (ServerLevel) pLevel;
					for (float i = 0; i <= 1; i += 0.2f) {
						server.sendParticles(ModParticles.WARDED_PARTICLES.get(), pPos.getX() - 0.1, pPos.getY() + 0.1, pPos.getZ() + i,
								1, 0, 0, 0, 0.1);
					}

					for (float i = 0; i <= 1; i += 0.2f) {
						server.sendParticles(ModParticles.WARDED_PARTICLES.get(), pPos.getX() + i, pPos.getY() + 0.1, pPos.getZ() - 0.1,
								1, 0, 0, 0, 0.1);
					}

					for (float i = 0; i <= 1; i += 0.2f) {
						server.sendParticles(ModParticles.WARDED_PARTICLES.get(), pPos.getX() + 1.1, pPos.getY() + 0.1, pPos.getZ() + i,
								1, 0, 0, 0, 0.1);
					}

					for (float i = 0; i <= 1; i += 0.2f) {
						server.sendParticles(ModParticles.WARDED_PARTICLES.get(), pPos.getX() + i, pPos.getY() + 0.1, pPos.getZ() + 1.1,
								1, 0, 0, 0, 0.1);
					}
				}
			}
		} else {
			super.attack(pState, pLevel, pPos, pPlayer);
		}
	}

	@Override
	protected float getDestroyProgress(BlockState pState, Player pPlayer, BlockGetter pLevel, BlockPos pPos) {
		if (CommonConfig.ALLOW_WARDED_BLOCKS.get()) {
			float f = pState.getDestroySpeed(pLevel, pPos);
			ItemStack item = pPlayer.getMainHandItem();

			if (!item.is(ModTags.Items.WARDBREAKER)) {
				return 0.0f;
			} else if (f == -1.0F) {
				return 0.0F;
			} else {
				int i = net.minecraftforge.common.ForgeHooks.isCorrectToolForDrops(pState, pPlayer) ? 30 : 100;
				return pPlayer.getDestroySpeed(pState, pPos) / f / i;
			}
		} else {
			return super.getDestroyProgress(pState, pPlayer, pLevel, pPos);
		}
	}

	/*@Override
	public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @javax.annotation.Nullable LivingEntity pPlacer, ItemStack pStack) {
		if (pPlacer instanceof Player) {
			placedByPlayer = true;
		} else {
			placedByPlayer = false;
		}
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
		if (pPlayer.getItemInHand(InteractionHand.MAIN_HAND).isEmpty() && placedByPlayer) {
			pLevel.setBlockAndUpdate(pPos, ModBlocks.SHADOW_BEAM_EMITTER.get().defaultBlockState());
			notifyEmittersBelow(pLevel, pPos);
			return InteractionResult.SUCCESS;
		} else {
			return super.useWithoutItem(pState, pLevel, pPos, pPlayer, pHitResult);
		}
	}*/
}
