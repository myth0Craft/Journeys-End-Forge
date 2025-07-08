package net.je.item;

import java.util.List;

import net.je.screen.timeworn_journal.TimewornJournalHomeScreen;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class TimewornJournalItem extends Item {


	private int tier;

	public TimewornJournalItem(Properties pProperties, int pTier) {
		super(pProperties);
		this.tier = pTier;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
		ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);


		if (pLevel.isClientSide()) {
			pLevel.playSound(pPlayer, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.BOOK_PAGE_TURN,
					SoundSource.PLAYERS, 1.0F, 1);
			Minecraft.getInstance().setScreen(new TimewornJournalHomeScreen(tier));
		}

		return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());

	}
	
	@Override
	public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
		String tooltip = Component.translatable("tooltip.je.tier").getString() + tier;
		pTooltipComponents.add(Component.literal(tooltip).withStyle(ChatFormatting.DARK_PURPLE));
    }

}
