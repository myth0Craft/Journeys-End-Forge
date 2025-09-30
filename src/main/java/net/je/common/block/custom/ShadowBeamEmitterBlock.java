package net.je.common.block.custom;

import com.mojang.serialization.MapCodec;
import net.je.common.block.ModBlocks;
import net.je.common.block.entity.ModBlockEntities;
import net.je.common.block.entity.ShadowBeamEmitterBlockEntity;
import net.je.common.block.entity.UnstableShadowPrismBlockEntity;
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
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class ShadowBeamEmitterBlock extends BaseEntityBlock {

	public boolean placedByPlayer;

	public ShadowBeamEmitterBlock(Properties pProperties) {
		super(pProperties);
	}


	@Override
	public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new ShadowBeamEmitterBlockEntity(pPos, pState);
	}

	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return createTickerHelper(type, ModBlockEntities.SHADOW_BEAM_EMITTER_BLOCK_ENTITY.get(), ShadowBeamEmitterBlockEntity::tick);
	}

	@Override
	public void attack(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
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
	public float getDestroyProgress(BlockState pState, Player pPlayer, BlockGetter pLevel, BlockPos pPos) {
		if (CommonConfig.ALLOW_WARDED_BLOCKS.get()) {
			float f = pState.getDestroySpeed(pLevel, pPos);
			ItemStack item = pPlayer.getMainHandItem();

			if (!item.is(ModTags.Items.WARDBREAKER)) {
				return 0.0f;
			} else if (f == -1.0F) {
				return 0.0F;
			} else {
				int i = net.minecraftforge.common.ForgeHooks.isCorrectToolForDrops(pState, pPlayer) ? 30 : 100;
				return pPlayer.getDestroySpeed(pState) / f / i;
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
			pLevel.setBlockAndUpdate(pPos, ModBlocks.SHADOW_BEAM_RECEIVER.get().defaultBlockState());
			return InteractionResult.SUCCESS;
		} else {
			return super.useWithoutItem(pState, pLevel, pPos, pPlayer, pHitResult);
		}
	}*/
}
