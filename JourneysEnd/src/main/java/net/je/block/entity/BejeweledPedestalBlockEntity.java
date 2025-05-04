package net.je.block.entity;

import net.je.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BejeweledPedestalBlockEntity extends BlockEntity {

	private static int endersentSpawnTime;
	
	public BejeweledPedestalBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(ModBlockEntities.BEJEWELED_PEDESTAL_BE.get(), pPos, pBlockState);
	}
	
	public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, BejeweledPedestalBlockEntity pBlockEntity) {
		if (endersentSpawnTime > 0) {
			((ServerLevel) pLevel).sendParticles(ModParticles.ENDERSENT_SPAWN_PARTICLES.get(),
					 pPos.getX() + 0.5, pPos.getY() + 1, pPos.getZ() + 0.5, 75,
		                0, 0, 0, 0.2);
			endersentSpawnTime--;
			if (endersentSpawnTime == 0) {
				((ServerLevel) pLevel).sendParticles(ParticleTypes.SONIC_BOOM,
						 pPos.getX() + 0.5, pPos.getY() + 1, pPos.getZ() + 0.5, 75,
			                0, 0, 0, 0.2);
			}
		}
	}
	
	public void spawnEndersent() {
		endersentSpawnTime = 100;
	}
	
}
