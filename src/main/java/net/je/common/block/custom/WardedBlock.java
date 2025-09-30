package net.je.common.block.custom;

import net.je.common.particle.ModParticles;
import net.je.common.util.ModTags;
import net.je.config.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class WardedBlock extends Block {


	public static final BooleanProperty PLACED = BooleanProperty.create("placed");
	/*public WardedBlock() {
		this(MapColor.COLOR_BLACK);
	}

	public WardedBlock(MapColor pColor) {
		this(Properties.of().strength(1.5f, 3600000.0F).mapColor(pColor)
				.instrument(NoteBlockInstrument.BASS).pushReaction(PushReaction.IGNORE));
	}*/

	public WardedBlock(Properties properties) {
		super(properties.pushReaction(PushReaction.IGNORE).explosionResistance(3600000.0F));
		this.registerDefaultState(this.stateDefinition.any().setValue(PLACED, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(PLACED);
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
				if (pState.getValue(PLACED)) {
					float speed = pPlayer.getDestroySpeed(pState);
					float hardness = 30000.0f;
					return speed / hardness;
				} else {
					return 0.0f;
				}
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

	protected float defaultDestroyProgress(BlockState pState, Player pPlayer, BlockGetter pLevel, BlockPos pPos) {
		return super.getDestroyProgress(pState, pPlayer, pLevel, pPos);
	}

	protected void defaultAttack(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
		super.attack(pState, pLevel, pPos, pPlayer);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		return this.defaultBlockState().setValue(PLACED, true);
	}


}
