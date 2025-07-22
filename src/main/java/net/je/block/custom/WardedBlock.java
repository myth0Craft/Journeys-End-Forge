package net.je.block.custom;

import net.je.particle.ModParticles;
import net.je.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class WardedBlock extends Block {

	public WardedBlock() {
		this(MapColor.COLOR_BLACK);
	}

	public WardedBlock(MapColor pColor) {
		super(Properties.of().strength(1.5f, 3600000.0F).mapColor(pColor)
				.instrument(NoteBlockInstrument.BASS).pushReaction(PushReaction.IGNORE));
	}

	@Override
	protected void attack(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {

		ItemStack item = pPlayer.getMainHandItem();
		if (!item.is(ModTags.Items.WARDBREAKER)) {
			/*
			 * pPlayer.sendSystemMessage(Component.translatable("message.je.warded_block")
			 * .withStyle(ChatFormatting.DARK_PURPLE, ChatFormatting.ITALIC));
			 */
			if (!pLevel.isClientSide) {
				ServerLevel server = (ServerLevel) pLevel;
				for (float i = 0; i <= 1; i += 0.2) {
					server.sendParticles(ModParticles.WARDED_PARTICLES.get(), pPos.getX()-0.1, pPos.getY() + 0.1, pPos.getZ() + i,
							1, 0, 0, 0, 0.1);
				}

				for (float i = 0; i <= 1; i += 0.2) {
					server.sendParticles(ModParticles.WARDED_PARTICLES.get(), pPos.getX() + i, pPos.getY() + 0.1, pPos.getZ()-0.1,
							1, 0, 0, 0, 0.1);
				}

				for (float i = 0; i <= 1; i += 0.2) {
					server.sendParticles(ModParticles.WARDED_PARTICLES.get(), pPos.getX() + 1.1, pPos.getY() + 0.1, pPos.getZ() + i,
							1, 0, 0, 0, 0.1);
				}

				for (float i = 0; i <= 1; i += 0.2) {
					server.sendParticles(ModParticles.WARDED_PARTICLES.get(), pPos.getX() + i, pPos.getY() + 0.1, pPos.getZ() + 1.1,
							1, 0, 0, 0, 0.1);
				}


			}
		}
	}

	@Override
	protected float getDestroyProgress(BlockState pState, Player pPlayer, BlockGetter pLevel, BlockPos pPos) {
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
	}
}
