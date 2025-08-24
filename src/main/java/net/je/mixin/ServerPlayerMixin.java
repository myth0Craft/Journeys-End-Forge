package net.je.mixin;

import net.je.common.block.custom.RespawnNexusBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
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

	@Inject(
			method = "findRespawnPositionAndUseSpawnBlock",
			at = @At("HEAD"),
			cancellable = true
	)
	private void injectRespawn(
			boolean pKeepInventory, DimensionTransition.PostDimensionTransition pPostDimensionTransition, CallbackInfoReturnable<DimensionTransition> cir
	) {

		/*ResourceKey<Level> respawnDim = this.getRespawnDimension();
		ResourceKey<Level> current = this.serverLevel().dimension();
		if (!respawnDim.equals(current)) return;d*/

		ServerLevel level = this.serverLevel().getServer().getLevel(this.getRespawnDimension());
		BlockPos pos = this.getRespawnPosition();
		boolean isInExit = ((ServerPlayer)(Object)this).getPersistentData().getBoolean("je:in_end_exit");
		if (level != null && pos != null && !isInExit) {

			BlockState blockstate = level.getBlockState(this.getRespawnPosition());
			if (blockstate != null) {
				Block block = blockstate.getBlock();

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

					if (optional.isPresent()) {
						cir.setReturnValue(new DimensionTransition(
								level, optional.get(), Vec3.ZERO, this.getRespawnAngle(), 0.0F, pPostDimensionTransition
						));
					}
				}
			}
		}
	}
}
