package net.je.item;

import net.je.screen.timeworn_journal.TimewornJournalHomeScreen;
import net.je.sound.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class TimewornJournalItem extends Item {

	public TimewornJournalItem(Properties pProperties) {
		super(pProperties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
		ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);


		if (pLevel.isClientSide()) {
			pLevel.playSound(pPlayer, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.BOOK_PAGE_TURN,
					SoundSource.PLAYERS, 1.0F, 1);
			Minecraft.getInstance().setScreen(new TimewornJournalHomeScreen());
		}

		return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());

	}

}
