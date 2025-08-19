package net.je.event;

import com.google.common.eventbus.Subscribe;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RespawnInEnd {

	@SubscribeEvent
	public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
		if (!(event.getEntity() instanceof ServerPlayer player)) return;
		CompoundTag tag = player.getPersistentData();
		Optional<BlockPos> opPos = NbtUtils.readBlockPos(tag, "je_end_spawn");

		if (opPos.isEmpty()) return;
		BlockPos pos = opPos.get();

		String storedDim = tag.getString("je_end_spawn_dim");
		if (!Level.END.location().toString().equals(storedDim)) return;

		ServerLevel endLevel = player.getServer().getLevel(Level.END);

		if (endLevel != null) {
			player.teleportTo(endLevel, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, player.getYRot(), player.getXRot());
		}
	}
}
