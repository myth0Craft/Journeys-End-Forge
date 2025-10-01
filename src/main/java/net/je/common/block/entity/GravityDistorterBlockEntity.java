package net.je.common.block.entity;

import net.je.common.block.custom.GravityDistorterBlock;
import net.je.config.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import java.util.*;

public class GravityDistorterBlockEntity extends BlockEntity {

	public int numBlocksStacked = 1;
	private boolean isTopBlockInStack;
	private boolean isCovered;
	private int levitationAmount;
	private AABB cachedArea;

	private final Set<UUID> levitatedEntities = new HashSet<>();

	public GravityDistorterBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.GRAVITY_DISTORTER_BLOCK_ENTITY.get(), pos, state);
	}

	@Override
	public void onLoad() {
		updateState();
	}

	public void updateState() {
		if (level == null) return;

		BlockState state = getBlockState();
		isTopBlockInStack = !state.getValue(GravityDistorterBlock.BLOCK_ABOVE);
		isCovered = !level.isEmptyBlock(worldPosition.above());

		levitationAmount = Math.min(numBlocksStacked, 10);
		updateLevitationArea();

		setChanged();
		if (!level.isClientSide) {
			BlockState bs = level.getBlockState(worldPosition);
			level.sendBlockUpdated(worldPosition, bs, bs, Block.UPDATE_CLIENTS);
		}
	}

	private void updateLevitationArea() {
		if (level == null) return;
		cachedArea = new AABB(worldPosition).expandTowards(0, 5 * levitationAmount, 0);
	}

	public AABB getLevitationArea() {
		return cachedArea;
	}

	public static void tick(Level level, BlockPos pos, BlockState state, GravityDistorterBlockEntity be) {
		if (level.isClientSide()) return;
		if (!be.isTopBlockInStack || be.isCovered) return;
		if (be.cachedArea == null) be.updateLevitationArea();

		List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, be.cachedArea);
		//Set<UUID> seenThisTick = new HashSet<>();

		for (LivingEntity entity : entities) {
			if (isAtBlock(entity, be.getBlockPos()) && be.hasClearPathToEntity(level, pos, entity)) {
				if (entity instanceof ServerPlayer player) {
					if (player.isShiftKeyDown()) {
						//player.setNoGravity(false);
						continue;
					}
					if (player.isCreative() && !CommonConfig.GRAVITY_DISTORTER_WORKS_IN_CREATIVE.get()) {
						continue;
					}
				}
				be.levitateEntity(entity, pos, level);
				//seenThisTick.add(entity.getUUID());
				if (entity instanceof ServerPlayer player) {
					player.hurtMarked = true;
				}
			}
		}

		/*Iterator<UUID> it = be.levitatedEntities.iterator();
		while (it.hasNext()) {
			UUID id = it.next();
			if (!seenThisTick.contains(id)) {
				Entity e = ((ServerLevel) level).getEntity(id);
				if (e instanceof LivingEntity le) {
					le.setNoGravity(false);
				}
				it.remove();
			}
		}

		be.levitatedEntities.clear();
		be.levitatedEntities.addAll(seenThisTick);*/
	}

	private boolean hasClearPathToEntity(Level level, BlockPos pos, LivingEntity entity) {
		int startY = pos.getY() + 1;
		int endY = Mth.floor(entity.getY());
		for (int y = startY; y <= endY; y++) {
			BlockPos checkPos = new BlockPos(pos.getX(), y, pos.getZ());
			if (!level.isEmptyBlock(checkPos)) return false;
		}
		return true;
	}

	private void levitateEntity(LivingEntity entity, BlockPos pos, Level pLevel) {
		//boolean blocksInTheWay = false;
		double topY = pos.getY() + 5 * levitationAmount;
		double currentY = entity.getY();

		/*for (int i = pos.getY() + 1; i <= currentY; i++) {
			if (!pLevel.isEmptyBlock(pos.above(i - pos.getY()))) {
				blocksInTheWay = true;
			}
		}*/

		//entity.setNoGravity(true);
		//if (!blocksInTheWay) {
			Vec3 motion = entity.getDeltaMovement();
			float friction = entity.level().getBlockState(entity.blockPosition()).getFriction(entity.level(), entity.blockPosition(), entity);
			Vec3 horizontal = entity.handleRelativeFrictionAndCalculateMovement(motion, friction);

			double targetY = 0.3;
			entity.setDeltaMovement(horizontal.x, targetY, horizontal.z);

			entity.fallDistance = 0;
			if (entity instanceof ServerPlayer player) {
				player.hurtMarked = true;
			}
		//}

		/*if (currentY > topY - 0.1) {
			double dy = topY - currentY;
			double lift = Math.min(0.3, dy * 0.2);
			Vec3 newMotion = new Vec3(horizontal.x * 2, lift, horizontal.z * 2);
			entity.setDeltaMovement(newMotion);

		} else if (currentY < topY) {
			Vec3 newMotion = new Vec3(horizontal.x, targetY, horizontal.z);
			entity.setDeltaMovement(newMotion);
		}*/

		/*if (currentY < topY) {
			Vec3 motion = entity.getDeltaMovement();
			entity.setDeltaMovement(motion.x(), 0.5, motion.y());
		} else if (currentY > topY) {
			entity.setPos(entity.getX(), topY, entity.getZ());
		}*/

		/*if (currentY < topY - 0.1) {
			double dy = topY - currentY;
			double lift = Math.min(0.3, dy * 0.2);
			Vec3 vec3 = new Vec3(motion.x(), lift, motion.z());
			Vec3 finalVec = new Vec3(entity.handleRelativeFrictionAndCalculateMovement(vec3, 0.1f).toVector3f());
			entity.setDeltaMovement(finalVec);
		} else {
			double newY = Math.min(motion.y(), 0);
			Vec3 vec3 = new Vec3(motion.x(), newY, motion.z());
			Vec3 finalVec = new Vec3(entity.handleRelativeFrictionAndCalculateMovement(vec3, 0.1f).toVector3f());
			entity.setDeltaMovement(finalVec);

			if (currentY > topY) {
				entity.setPos(entity.getX(), topY, entity.getZ());
			}
		}*/




	}

	private static boolean isAtBlock(LivingEntity entity, BlockPos pos) {
		return Mth.floor(entity.getX()) == pos.getX() &&
				Mth.floor(entity.getZ()) == pos.getZ();
	}

	@Override
	public void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		tag.putInt("NumBlocksStacked", numBlocksStacked);
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		numBlocksStacked = tag.getInt("NumBlocksStacked");
		updateState();
	}

	@Override
	public CompoundTag getUpdateTag() {
		CompoundTag tag = super.getUpdateTag();
		saveAdditional(tag);
		return tag;
	}

	@Override
	public void handleUpdateTag(CompoundTag tag) {
		load(tag);
		updateState();
	}
}