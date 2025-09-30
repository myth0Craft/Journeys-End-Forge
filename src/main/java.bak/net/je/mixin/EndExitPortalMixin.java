package net.je.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EndPortalBlock;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndPortalBlock.class)
public class EndExitPortalMixin {

	@Inject(method = "getPortalDestination", at = @At("HEAD"), cancellable = true)
	public void je$endPortalLogic(ServerLevel pLevel, Entity pEntity, BlockPos pPos, CallbackInfoReturnable<DimensionTransition> cir) {
		if (pLevel.dimension() == Level.END) {

			if (pEntity instanceof ServerPlayer player) {

				if (player.getRespawnDimension() == Level.END) {

					ServerLevel overworld = pLevel.getServer().getLevel(Level.OVERWORLD);

					if (overworld != null) {

						Vec3 vec3 = pEntity.adjustSpawnLocation(overworld,
								overworld.getSharedSpawnPos()).getBottomCenter();

						cir.setReturnValue(new DimensionTransition(
								overworld, vec3, pEntity.getDeltaMovement(), pEntity.getYRot(), pEntity.getXRot(),
								DimensionTransition.PLAY_PORTAL_SOUND.then(DimensionTransition.PLACE_PORTAL_TICKET)
						));
					}
				}
			}
		}
	}
}
