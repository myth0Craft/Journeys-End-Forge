package net.je.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.world.level.Level;

public class EclipseKeyItem extends Item {

	public EclipseKeyItem(Properties pProperties) {
		super(pProperties);
	}
	
	@Override
	public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
		super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
		if (!(pEntity instanceof LivingEntity living)) return;
		
		boolean isHeld = living.getMainHandItem() == pStack || living.getOffhandItem() == pStack;
		CustomModelData data = pStack.get(DataComponents.CUSTOM_MODEL_DATA);
		
		int current = data != null ? data.value() : 0;
		
		if (isHeld && current != 1) {
			pStack.set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(1));
		} else if (!isHeld && current != 0 ) {
			pStack.set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(0));
		}
		
    }
	
	

}
