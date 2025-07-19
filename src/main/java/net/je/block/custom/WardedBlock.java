package net.je.block.custom;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.je.JourneysEnd;
import net.je.particle.ModParticles;
import net.je.util.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.MapColor;

public class WardedBlock extends Block {

	public WardedBlock() {
		super(Properties.of().strength(1.5f, 3600000.0F).mapColor(MapColor.COLOR_BLACK)
				.instrument(NoteBlockInstrument.BASS));
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
				
				for (float i = 0; i < 1; i+=0.1f) {
					server.sendParticles(ModParticles.ENDERSENT_SPAWN_PARTICLES.get(), pPos.getX() + i, pPos.getY() + i,
							pPos.getZ() + i, 75, 0, 0, 0, 0.1);
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
			return pPlayer.getDestroySpeed(pState, pPos) / f / (float) i;
		}
	}
}
