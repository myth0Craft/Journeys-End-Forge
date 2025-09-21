package net.je.common.block.entity;

import net.je.common.block.ModBlocks;
import net.je.common.entity.custom.ShadowLord;
import net.je.config.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class UnstableShadowPrismBlockEntity extends ShadowPrismBlockEntity {

	public int activeTicks = 0;
	public int ticksUntilLaser = 0;
	private AABB laserDamageArea;
	public static final int BEAM_HEIGHT = 6;

	public UnstableShadowPrismBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(ModBlockEntities.UNSTABLE_SHADOW_PRISM_BLOCK_ENTITY.get(), pPos, pBlockState);
	}

	@Override
	public void onLoad() {
		ticksUntilLaser = 40;
		laserDamageArea = new AABB(worldPosition)
				.expandTowards(0, BEAM_HEIGHT, 0);
	}

	@Override
	public AABB getRenderBoundingBox() {
		return new AABB(worldPosition).expandTowards(0, BEAM_HEIGHT, 0);
	}

	public boolean isActive() {
		return activeTicks > 0;
	}

	public boolean laserIsCharging() {
		return ticksUntilLaser > 0;
	}

	public void startLaser() {
		activeTicks = 20;
	}

	public static void tick(Level level, BlockPos pos, BlockState state, UnstableShadowPrismBlockEntity be) {
		if (be.laserIsCharging()) {
			be.ticksUntilLaser -= 1;
			if (!be.laserIsCharging()) {
				be.startLaser();
			}
		}
		if (be.isActive()) {
			be.activeTicks -= 1;
			if (!level.isClientSide()) {
				if (level.getGameTime() % 2 == 0) {

					List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, be.laserDamageArea);

					for (LivingEntity entity : entities) {
						if (entity instanceof ServerPlayer player) {
							if (!player.isCreative()) {
								if (isAtBlock(player, be.getBlockPos())) {

									player.hurt(level.damageSources().magic(), 1);

									player.invulnerableTime = 1;

									player.hurtMarked = false;
								}
							}
						} else if (!(entity instanceof ShadowLord shadowLord)) {
							if (isAtBlock(entity, be.getBlockPos())) {

								entity.hurt(level.damageSources().magic(), 1);
								entity.invulnerableTime = 1;

								entity.hurtMarked = false;
							}
						}
					}
				}
			}

			if (!be.isActive()) {
				level.setBlockAndUpdate(pos, ModBlocks.FADED_END_STONE_BRICKS.get().defaultBlockState());
			}
		}
	}

	private static boolean isAtBlock(LivingEntity entity, BlockPos pos) {
		return Mth.floor(entity.getX()) == pos.getX() &&
				Mth.floor(entity.getZ()) == pos.getZ();
	}
}
