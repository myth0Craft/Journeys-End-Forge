package net.je.common.block.custom;

import com.mojang.serialization.MapCodec;
import net.je.common.block.ModBlocks;
import net.je.common.block.entity.ModBlockEntities;
import net.je.common.block.entity.ShadowBeamEmitterBlockEntity;
import net.je.common.block.entity.UnstableShadowPrismBlockEntity;
import net.je.config.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
	public static final MapCodec<ShadowBeamEmitterBlock> CODEC = simpleCodec(ShadowBeamEmitterBlock::new);

	public boolean placedByPlayer;

	public ShadowBeamEmitterBlock(Properties pProperties) {
		super(pProperties);
	}

	@Override
	public MapCodec<ShadowBeamEmitterBlock> codec() {
		return CODEC;
	}

	@Override
	public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new ShadowBeamEmitterBlockEntity(pPos, pState);
	}

	@Override
	protected RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return createTickerHelper(type, ModBlockEntities.SHADOW_BEAM_EMITTER_BLOCK_ENTITY.get(), ShadowBeamEmitterBlockEntity::tick);
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
