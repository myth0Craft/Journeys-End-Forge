package net.je.screen;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class EndStoneFurnaceFuelSlot extends Slot {
    private final EndStoneFurnaceMenu menu;

    public EndStoneFurnaceFuelSlot(EndStoneFurnaceMenu pEndStoneFurnaceMenu, Container pEndStoneFurnaceContainer, int pSlot, int pXPosition, int pYPosition) {
        super(pEndStoneFurnaceContainer, pSlot, pXPosition, pYPosition);
        this.menu = pEndStoneFurnaceMenu;
    }

    @Override
    public boolean mayPlace(ItemStack pStack) {
        return this.menu.isFuel(pStack) || isBucket(pStack);
    }

    @Override
    public int getMaxStackSize(ItemStack pStack) {
        return isBucket(pStack) ? 1 : super.getMaxStackSize(pStack);
    }

    public static boolean isBucket(ItemStack pStack) {
        return pStack.is(Items.BUCKET);
    }
}