package net.je.block.entity;

import net.je.block.ModBlocks;
import net.je.block.custom.GravityDistorterBlock;
import net.je.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class GravityDistorterBlockEntity extends BlockEntity {

	public int numBlocksStacked = 1;

	private boolean isTopBlockInStack;

	private boolean isCovered;

	private int levitationAmount;

	public GravityDistorterBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(ModBlockEntities.GRAVITY_DISTORTER_BLOCK_ENTITY.get(), pPos, pBlockState);
	}

	public void updateState() {
		if (level == null) return;
		BlockState state = getBlockState();
		isTopBlockInStack = !state.getValue(GravityDistorterBlock.BLOCK_ABOVE);

		isCovered = !level.isEmptyBlock(worldPosition.above());

		levitationAmount = Math.min(numBlocksStacked, 10);
	}

	@Override
	public void onLoad() {
		super.onLoad();
		updateState();
		if (level != null) {
			if (!level.isClientSide()) {
				BlockState state = level.getBlockState(worldPosition);
				if (state.getBlock() instanceof GravityDistorterBlock block) {
					block.updateStackHeight(level, worldPosition);
				}
			}
		}

	}

	public static void tick(Level pLevel, BlockPos pPos, BlockState pState, GravityDistorterBlockEntity pBlockEntity) {
		if (pLevel.isClientSide()) return;

		if (!pBlockEntity.isTopBlockInStack || pBlockEntity.isCovered) return;

		//System.out.println("Top block in stack at " + pPos + ", stack height: " + pBlockEntity.numBlocksStacked);

		AABB area = new AABB(pPos).inflate(0, 10 * pBlockEntity.levitationAmount, 0).setMinY(pPos.getY());
		List<LivingEntity> entities = pLevel.getEntitiesOfClass(LivingEntity.class, area);

		//Holder<MobEffect> holder = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(MobEffects.LEVITATION.get());
		for (LivingEntity entity : entities) {
			if (entity instanceof Player player) {
				if (!player.isShiftKeyDown()) {
					player.setDeltaMovement(player.getDeltaMovement().x(), 0.5, player.getDeltaMovement().z());
					player.resetFallDistance();
				}
			} else {
				entity.setDeltaMovement(entity.getDeltaMovement().x(), 0.5, entity.getDeltaMovement().z());
				entity.resetFallDistance();
			}
			//entity.addEffect(new MobEffectInstance(holder, 10, 1));
			System.out.println(entity.toString() + "levitated");
		}
	}
}
