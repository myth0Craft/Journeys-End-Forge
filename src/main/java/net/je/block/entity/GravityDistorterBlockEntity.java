package net.je.block.entity;

import net.je.block.ModBlocks;
import net.je.block.custom.GravityDistorterBlock;
import net.je.entity.ModEntities;
import net.je.entity.custom.EndersentWithEye;
import net.je.particle.ModParticles;
import net.je.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class GravityDistorterBlockEntity extends BlockEntity {

	public int numBlocksStacked;

	private boolean isTopBlockInStack;

	private boolean isCovered;

	public GravityDistorterBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(ModBlockEntities.GRAVITY_DISTORTER_BLOCK_ENTITY.get(), pPos, pBlockState);
	}

	public void updateState() {
		if (level == null) return;
		BlockState state = getBlockState();
		isTopBlockInStack = !state.getValue(GravityDistorterBlock.BLOCK_ABOVE);

		isCovered = !level.isEmptyBlock(worldPosition.above());
	}

	@Override
	public void onLoad() {
		updateState();
	}

	public static void tick(Level pLevel, BlockPos pPos, BlockState pState, GravityDistorterBlockEntity pBlockEntity) {
		if (pLevel.isClientSide()) return;

		pBlockEntity.updateState();

		if (!pBlockEntity.isTopBlockInStack || pBlockEntity.isCovered) return;

		System.out.println("Top block in stack at " + pPos + ", stack height: " + pBlockEntity.numBlocksStacked);

		/*while (state.is(ModBlocks.GRAVITY_DISTORTER.get())) {
			numBlocksInStack++;
			topPos = topPos.below();
			state = pLevel.getBlockState(topPos);

			if (topPos.getY() == pLevel.getMinBuildHeight()) {
				break;
			}

		}*/

		/*if (pLevel.getGameTime() % 10 == 0) {
			System.out.println("Top block in stack at position:\nX: " + pPos.getX() + "\nY: " + pPos.getY() + "\nZ: " + pPos.getZ());
		}*/





		/*AABB area = new AABB(pPos).inflate(0.5, 1 * 10, 0.5);
		List<Player> players = pLevel.getEntitiesOfClass(Player.class, area);
		for (Player player : players) {
			player.setDeltaMovement(player.getDeltaMovement().x, 0.5, player.getDeltaMovement().y);
			player.fallDistance = 0;
			//System.out.println(player.getDisplayName() + " levitated by gravity block");
		}*/



	}
}
