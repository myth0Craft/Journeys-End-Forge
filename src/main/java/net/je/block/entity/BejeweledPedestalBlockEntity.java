package net.je.block.entity;

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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BejeweledPedestalBlockEntity extends BlockEntity {

	private int endersentSpawnTime;

	public BejeweledPedestalBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(ModBlockEntities.BEJEWELED_PEDESTAL_BE.get(), pPos, pBlockState);
		this.endersentSpawnTime = 0;
	}

	public static void tick(Level pLevel, BlockPos pPos, BlockState pState, BejeweledPedestalBlockEntity pBlockEntity) {
		if (pBlockEntity.endersentSpawnTime > 0) {
			if (!pLevel.isClientSide) {
				ServerLevel server = (ServerLevel) pLevel;

				if (!(server.getDifficulty() == Difficulty.PEACEFUL)) {
					server.sendParticles(ModParticles.ENDERSENT_SPAWN_PARTICLES.get(), pPos.getX() + 0.5,
							pPos.getY() + 1, pPos.getZ() + 0.5, 75, 0, 0, 0, 0.2);
					pBlockEntity.endersentSpawnTime--;
					if (pBlockEntity.endersentSpawnTime == 0) {

						pBlockEntity.endersentSpawnTime = -1;
						EntityType<EndersentWithEye> entity = ModEntities.ENDERSENT_WITH_EYE.get();

						entity.spawn(server, pPos.above(), MobSpawnType.MOB_SUMMONED);

						pLevel.playSound(null, pPos.getX(), pPos.above().getY(), pPos.getZ(), ModSounds.ENDERSENT_SPAWN.get(),
								SoundSource.BLOCKS, 1.0F, 1);

					}
				}
			}
		}
	}

	public void spawnEndersent() {
		this.endersentSpawnTime = 140;
	}
}
