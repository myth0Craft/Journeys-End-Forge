package net.je.mixin;

import net.je.common.block.custom.RespawnNexusBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.Optional;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {


	@Shadow public abstract ServerLevel serverLevel();

	@Shadow @Nullable public abstract BlockPos getRespawnPosition();

	@Shadow public abstract ResourceKey<Level> getRespawnDimension();

	@Shadow public abstract float getRespawnAngle();

	@Shadow private float respawnAngle;

	@Inject(
			method = "findRespawnPositionAndUseSpawnBlock",
			at = @At("HEAD"),
			cancellable = true
	)
	private void injectRespawn(
			boolean pKeepInventory, DimensionTransition.PostDimensionTransition pPostDimensionTransition, CallbackInfoReturnable<DimensionTransition> cir
	) {
		Level level = this.serverLevel().getServer().getLevel(this.getRespawnDimension());
		ServerLevel server = (ServerLevel) level;
		BlockPos pos = this.getRespawnPosition();
		BlockState blockstate = level.getBlockState(this.getRespawnPosition());
		Block block = blockstate.getBlock();

		// Handle your custom block
		if (block instanceof RespawnNexusBlock
				&& blockstate.getValue(RespawnNexusBlock.CHARGED)
				&& RespawnNexusBlock.canSetSpawn(level)) {

			Optional<Vec3> optional = RespawnNexusBlock.findStandUpPosition(EntityType.PLAYER, level, pos);

			if (!pKeepInventory && optional.isPresent()) {
				level.setBlock(pos, blockstate.setValue(
						RespawnNexusBlock.CHARGED,
						false
				), 3);
			}

			cir.setReturnValue(new DimensionTransition(
					server, optional.get(), Vec3.ZERO, this.respawnAngle, 0.0F, pPostDimensionTransition
			));
		}
	}

	/*@Inject(
			method = "findRespawnAndUseSpawnBlock",
			at = @At("HEAD"),
			cancellable = true
	)
	private static void injectRespawn(
			ServerLevel pLevel,
			BlockPos pPos,
			float pAngle,
			boolean pForced,
			boolean pKeepInventory,
			CallbackInfoReturnable<Optional<?>> cir
	) {
		System.out.println("Ran findRespawnAndUseSpawnBlock");
		BlockState blockstate = pLevel.getBlockState(pPos);
		Block block = blockstate.getBlock();

		// Handle your custom block
		if (block instanceof RespawnNexusBlock
				&& (pForced || blockstate.getValue(RespawnNexusBlock.CHARGED))
				&& RespawnNexusBlock.canSetSpawn(pLevel)) {

			Optional<Vec3> optional = RespawnNexusBlock.findStandUpPosition(EntityType.PLAYER, pLevel, pPos);

			if (!pForced && !pKeepInventory && optional.isPresent()) {
				pLevel.setBlock(pPos, blockstate.setValue(
						RespawnNexusBlock.CHARGED,
						false
				), 3);
			}

			Object respawn = RespawnPosAngleAccessor.invokeOf(optional.get(), pPos);
			cir.setReturnValue(Optional.of(respawn));
		}
	}*/
}
