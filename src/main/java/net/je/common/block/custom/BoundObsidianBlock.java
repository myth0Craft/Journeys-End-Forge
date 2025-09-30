package net.je.common.block.custom;

import net.je.common.item.ModItems;
import net.je.common.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;

public class BoundObsidianBlock extends WardedBlock {
	public BoundObsidianBlock() {
		super(Properties.of().strength(50f, 3600000.0F).mapColor(MapColor.COLOR_BLACK)
				.instrument(NoteBlockInstrument.BASS).pushReaction(PushReaction.IGNORE).sound(SoundType.CHAIN).requiresCorrectToolForDrops());
	}

	@Override
	protected void attack(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {

		/*ItemStack item = pPlayer.getMainHandItem();
		if (!item.is(ModTags.Items.WARDBREAKER)) {
			*//*
		 * pPlayer.sendSystemMessage(Component.translatable("message.je.warded_block")
		 * .withStyle(ChatFormatting.DARK_PURPLE, ChatFormatting.ITALIC));
		 *//*
			if (!pLevel.isClientSide) {
				ServerLevel server = (ServerLevel) pLevel;
				server.playLocalSound(pPos, SoundType.CHAIN.getPlaceSound(), SoundSource.BLOCKS, 2.0f, 2.0f, false);
			}
		}*/
	}


	@Override
	public InteractionResult use(
			BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit
	) {
		ItemStack pStack = pPlayer.getItemInHand(pHand);
		if (!pLevel.isClientSide() && pStack.is(ModItems.SHADOW_KEY.get())) {
			pStack.shrink(1);
			pLevel.playSound(null, pPos.getX(), pPos.getY(), pPos.getZ(), SoundType.CHAIN.getPlaceSound(), SoundSource.BLOCKS, 1.0f, 1.0f);
			this.unlock(pLevel, pPos);
			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}

	public void spawnParticles(Level pLevel, BlockPos pPos) {
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

	public void unlock(Level pLevel, BlockPos pPos) {

		pLevel.removeBlock(pPos, false);
		spawnParticles(pLevel, pPos);
		if (pLevel.getBlockState(pPos.above()).is(this)) {
			BoundObsidianBlock block = (BoundObsidianBlock) pLevel.getBlockState(pPos.above()).getBlock();
			block.unlock(pLevel, pPos.above());
		}
		if (pLevel.getBlockState(pPos.below()).is(this)) {
			BoundObsidianBlock block = (BoundObsidianBlock) pLevel.getBlockState(pPos.below()).getBlock();
			block.unlock(pLevel, pPos.below());
		}
		if (pLevel.getBlockState(pPos.east()).is(this)) {
			BoundObsidianBlock block = (BoundObsidianBlock) pLevel.getBlockState(pPos.east()).getBlock();
			block.unlock(pLevel, pPos.east());
		}
		if (pLevel.getBlockState(pPos.west()).is(this)) {
			BoundObsidianBlock block = (BoundObsidianBlock) pLevel.getBlockState(pPos.west()).getBlock();
			block.unlock(pLevel, pPos.west());
		}
		if (pLevel.getBlockState(pPos.north()).is(this)) {
			BoundObsidianBlock block = (BoundObsidianBlock) pLevel.getBlockState(pPos.north()).getBlock();
			block.unlock(pLevel, pPos.north());
		}
		if (pLevel.getBlockState(pPos.south()).is(this)) {
			BoundObsidianBlock block = (BoundObsidianBlock) pLevel.getBlockState(pPos.south()).getBlock();
			block.unlock(pLevel, pPos.south());
		}
	}
}
