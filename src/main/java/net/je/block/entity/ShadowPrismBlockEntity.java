package net.je.block.entity;

import javax.annotation.Nullable;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ShadowPrismBlockEntity extends BlockEntity {

	
	
	public ShadowPrismBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(ModBlockEntities.SHADOW_PRISM_BLOCK_ENTITY.get(), pPos, pBlockState);
	}
	
	public boolean shouldRenderFace(Direction pFace) {
		return Block.shouldRenderFace(this.getBlockState(), this.level, this.getBlockPos(), pFace, this.getBlockPos().relative(pFace));
    }

	/*
	 * public int getParticleAmount() { int i = 0;
	 * 
	 * for (Direction direction : Direction.values()) { i +=
	 * this.shouldRenderFace(direction) ? 1 : 0; }
	 * 
	 * return i; }
	 * 
	 * public ClientboundBlockEntityDataPacket getUpdatePacket() { return
	 * ClientboundBlockEntityDataPacket.create(this); }
	 * 
	 * public float getSpawnPercent(float pPartialTicks) { return
	 * Mth.clamp(((float)this.age + pPartialTicks) / 200.0F, 0.0F, 1.0F); }
	 * 
	 * public boolean isSpawning() { return this.age < 200L; }
	 * 
	 * public static void portalTick(Level pLevel, BlockPos pPos, BlockState pState,
	 * TheEndGatewayBlockEntity pBlockEntity) { boolean flag =
	 * pBlockEntity.isSpawning(); boolean flag1 = pBlockEntity.isCoolingDown();
	 * pBlockEntity.age++; if (flag1) { pBlockEntity.teleportCooldown--; } else if
	 * (pBlockEntity.age % 2400L == 0L) { triggerCooldown(pLevel, pPos, pState,
	 * pBlockEntity); }
	 * 
	 * if (flag != pBlockEntity.isSpawning() || flag1 !=
	 * pBlockEntity.isCoolingDown()) { setChanged(pLevel, pPos, pState); } }
	 */

}
