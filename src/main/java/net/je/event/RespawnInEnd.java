package net.je.event;

import net.je.common.block.custom.RespawnNexusBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RespawnInEnd {

	@SubscribeEvent
	public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
		if (!(event.getEntity() instanceof ServerPlayer player)) return;
		ServerLevel level = player.getServer().getLevel(player.getRespawnDimension());
		if (level == null) {
			System.out.println("no level");
			return;
		}
		BlockPos pos = player.getRespawnPosition();
		if (pos == null) {
			System.out.println("no pos");
			return;
		}

		BlockState blockstate = level.getBlockState(pos);
		Block block = blockstate.getBlock();
		if (block instanceof RespawnNexusBlock
				&& RespawnNexusBlock.canSetSpawn(level)) {
			Optional<Vec3> optional = RespawnNexusBlock.findStandUpPosition(EntityType.PLAYER, level, pos);
			level.setBlock(
					pos, blockstate.setValue(RespawnNexusBlock.CHARGED, false), 3
			);
			if (blockstate.getValue(RespawnNexusBlock.CHARGED)) {
				optional.ifPresent(vec -> {
					BlockPos spawnPos = new BlockPos((int) vec.x(), (int) vec.y(), (int) vec.z());

					player.teleportTo(level, spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), 0, 0);
					System.out.println("player teleported");

				});
			} else {
				System.out.println("block not charged");
			}


		} else {
			System.out.println("not a respawn nexus");
		}




	}

	/*public static final String JE_LAST_SPAWN = "je_last_spawn";

	public static void setEndSpawn(ServerPlayer player, BlockPos pos) {
		CompoundTag tag = player.getPersistentData();
		CompoundTag spawnTag = new CompoundTag();
		spawnTag.putLong("time", System.currentTimeMillis());
		spawnTag.putString("dim", Level.END.location().toString());
		spawnTag.put("pos", NbtUtils.writeBlockPos(pos));
		tag.put(JE_LAST_SPAWN, spawnTag);
	}




	@SubscribeEvent
	public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
		if (!(event.getEntity() instanceof ServerPlayer player)) {
			System.out.println("Entity not a player.");
			return;
		}

		CompoundTag tag = player.getPersistentData();
		Optional<BlockPos> opPos = NbtUtils.readBlockPos(tag, "je_end_spawn");
		System.out.println("Player NBT: " + tag);

		if (opPos.isEmpty()) {
				System.out.println("Block pos doesn't exist.");
				return;
		}
		BlockPos pos = opPos.get();

		String storedDim = tag.getString("je_end_spawn_dim");
		if (!Level.END.location().toString().equals(storedDim)) {
				System.out.println("Dimension doesn't equal the End.");
				return;
		}

		ServerLevel endLevel = player.getServer().getLevel(Level.END);

		if (endLevel != null) {
			player.teleportTo(endLevel, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, player.getYRot(), player.getXRot());
			System.out.println("Player teleported.");
		} else {
			System.out.println("Level is null.");
		}
	}

	@SubscribeEvent
	public static void onPlayerClone(PlayerEvent.Clone event) {
		if (event.isWasDeath()) {
			CompoundTag oldData = event.getOriginal().getPersistentData();
			CompoundTag newData = event.getEntity().getPersistentData();

			// Copy only your mod’s data
			if (oldData.contains("je_end_spawn")) {
				newData.put("je_end_spawn", oldData.get("je_end_spawn"));
			}
			if (oldData.contains("je_end_spawn_dim")) {
				newData.putString("je_end_spawn_dim", oldData.getString("je_end_spawn_dim"));
			}
		}
	}*/
}
