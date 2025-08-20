package net.je.common.block.entity;

import net.je.common.block.custom.GravityDistorterBlock;
import net.je.config.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class GravityDistorterBlockEntity extends BlockEntity {

	public int numBlocksStacked = 1;
	private boolean isTopBlockInStack;
	private boolean isCovered;
	private int levitationAmount;
	private AABB cachedArea;

	public GravityDistorterBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(ModBlockEntities.GRAVITY_DISTORTER_BLOCK_ENTITY.get(), pPos, pBlockState);
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
		cachedArea = new AABB(worldPosition)
				.expandTowards(0, 5 * levitationAmount, 0);
	}

	public AABB getLevitationArea() {
		return cachedArea;
	}

	public static void tick(Level level, BlockPos pos, BlockState state, GravityDistorterBlockEntity be) {
		if (level.isClientSide()) return;
		if (!be.isTopBlockInStack || be.isCovered) return;
		if (be.cachedArea == null) be.updateLevitationArea();

		List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, be.cachedArea);

		for (LivingEntity entity : entities) {
			if (entity instanceof Player player) {
				if ((!player.isShiftKeyDown() && (!player.isCreative() || CommonConfig.GRAVITY_DISTORTER_WORKS_IN_CREATIVE.get()))) {
					double x = player.getDeltaMovement().x();
					double z = player.getDeltaMovement().z();

					player.setDeltaMovement(x, 0.5, z);
					player.hurtMarked = true;
					//player.fallDistance = 0;
					//player.setOnGround(false);



				}
			} else {
				//if (!level.isClientSide) {
					entity.setDeltaMovement(entity.getDeltaMovement().x(), 0.5, entity.getDeltaMovement().z());
					//entity.resetFallDistance();
				//}
			}
		}
	}

	@Override
	public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.saveAdditional(tag, registries);
		tag.putInt("NumBlocksStacked", numBlocksStacked);
	}

	@Override
	public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.loadAdditional(tag, registries);
		numBlocksStacked = tag.getInt("NumBlocksStacked");
		updateState(); // rebuild area and sync
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
		CompoundTag tag = super.getUpdateTag(registries);
		saveAdditional(tag, registries);
		return tag;
	}

	@Override
	public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider registries) {
		loadAdditional(tag, registries);
		updateState();
	}
}