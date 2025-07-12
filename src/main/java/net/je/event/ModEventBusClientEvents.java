package net.je.event;

import com.mojang.blaze3d.systems.RenderSystem;

import net.je.JourneysEnd;
import net.je.effect.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JourneysEnd.MODID, value = Dist.CLIENT)
public class ModEventBusClientEvents {

	/*
	 * @SubscribeEvent public static void onCustomizeOverlay() { Minecraft mc =
	 * Minecraft.getInstance();
	 *
	 * if (mc.player == null || mc.level == null) return; //if
	 * (mc.player.hasEffect(ModEffects.VOID_STRIKE_EFFECT.get())) {
	 * RenderSystem.setShaderColor(60, 0, 97, 1); //} }
	 */

	@SubscribeEvent
	public static void customize(CustomizeGuiOverlayEvent pEvent) {
		Minecraft mc = Minecraft.getInstance();
		Holder<MobEffect> holder = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(ModEffects.VOID_STRIKE_EFFECT.get());
		if (mc.player == null || mc.level == null) {
			return;
		}

		boolean isActive = mc.player.hasEffect(holder);

		if (isActive) {
			if (!VoidStrikeOverlayState.wasActiveLastTick) {
				VoidStrikeOverlayState.fadeInTicks = 0;
				VoidStrikeOverlayState.fadeOutTicks = 0;

			}
			float alpha;
			if (VoidStrikeOverlayState.fadeInTicks < VoidStrikeOverlayState.MAX_FADE_TICKS) {
				alpha = VoidStrikeOverlayState.fadeInTicks / (float) VoidStrikeOverlayState.MAX_FADE_TICKS;
				VoidStrikeOverlayState.fadeInTicks++;
			} else {
				alpha = 1.0f;
			}
			renderVoidblightOverlay(pEvent.getGuiGraphics(), mc, alpha);
			VoidStrikeOverlayState.wasActiveLastTick = true;

		} else if (VoidStrikeOverlayState.wasActiveLastTick || VoidStrikeOverlayState.fadeOutTicks > 0) {
			VoidStrikeOverlayState.wasActiveLastTick = false;

			if (VoidStrikeOverlayState.fadeOutTicks == 0) {
				VoidStrikeOverlayState.fadeOutTicks = VoidStrikeOverlayState.MAX_FADE_TICKS;
			}

			float alpha = VoidStrikeOverlayState.fadeOutTicks / (float) VoidStrikeOverlayState.MAX_FADE_TICKS;
			renderVoidblightOverlay(pEvent.getGuiGraphics(), mc, alpha);
			VoidStrikeOverlayState.fadeOutTicks--;
		}
	}

	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
			"textures/misc/void_strike_overlay.png");

	private static void renderVoidblightOverlay(GuiGraphics pGraphics, Minecraft mc, float alpha) {
		int width = mc.getWindow().getGuiScaledWidth();
		int height = mc.getWindow().getGuiScaledHeight();

		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		RenderSystem.setShaderTexture(0, TEXTURE);
		RenderSystem.disableDepthTest();

		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, alpha);

		pGraphics.blit(TEXTURE, 0, 0, 0, 0, width, height, width, height);

		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

		RenderSystem.enableDepthTest();
		RenderSystem.disableBlend();

	}

	public class VoidStrikeOverlayState {
		public static int fadeOutTicks = 0;
		public static int fadeInTicks = 0;
		public static final int MAX_FADE_TICKS = 60;
		public static boolean wasActiveLastTick = false;
		public static boolean isFadingIn = false;
	}

	/*
	 * public static void renderCustomHearts(GuiGraphics graphics, Minecraft mc) {
	 * LocalPlayer player = mc.player; int screenWidth =
	 * mc.getWindow().getGuiScaledWidth(); int screenHeight =
	 * mc.getWindow().getGuiScaledHeight(); int health =
	 * Mth.ceil(player.getHealth()); int maxHealth =
	 * Mth.ceil(player.getMaxHealth()); int left = screenWidth / 2 - 91; int top =
	 * screenHeight - 39;
	 *
	 * for (int i = 0; i < maxHealth / 2; ++i) { int x = left + i * 8; boolean full
	 * = i * 2 + 1 < health; boolean half = i * 2 + 1 == health;
	 * graphics.blit(HEART_TEXTURE, x, top, 16, 0, 9, 9, 32, 9); if (full) {
	 * graphics.blit(HEART_TEXTURE, x, top, 16, 0, 9, 9, 32, 9); } } }
	 */

}
