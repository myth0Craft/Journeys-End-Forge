package net.je.common.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EclipseKeyItem extends Item {

	public EclipseKeyItem(Properties pProperties) {
		super(pProperties);
	}

	@Override
	public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
		super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
		if (!(pEntity instanceof LivingEntity living)) {
			return;
		}
		boolean isHeld = living.getMainHandItem() == pStack || living.getOffhandItem() == pStack;
		pStack.getOrCreateTag().putBoolean("ShadowAmuletHeld", isHeld);

	}
}
