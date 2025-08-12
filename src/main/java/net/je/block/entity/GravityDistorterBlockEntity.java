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
	public GravityDistorterBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(ModBlockEntities.GRAVITY_DISTORTER_BLOCK_ENTITY.get(), pPos, pBlockState);
	}

	public static void tick(Level pLevel, BlockPos pPos, BlockState pState, GravityDistorterBlockEntity pBlockEntity) {
		if (pLevel.isClientSide()) return;
		//BlockState state = pLevel.getBlockState(pPos);

		GravityDistorterBlock block = (GravityDistorterBlock) pLevel.getBlockState(pPos).getBlock();
		int numBlocksStacked = block.numBlocksStacked;

		/*if (pLevel.getGameTime() % 5 == 0) {
			System.out.println(numBlocksStacked);
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
