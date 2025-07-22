package net.je.event;

import com.mojang.blaze3d.systems.RenderSystem;

import net.je.JourneysEnd;
import net.je.effect.ModEffects;
import net.je.util.ClientModData;
import net.minecraft.client.CameraType;
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

		if (mc.player == null || mc.level == null) {
			return;
		}

		if (ClientModData.isPlayerInsideShadowBlock()) {
			if (mc.options.getCameraType() == CameraType.FIRST_PERSON) {
				int width = mc.getWindow().getGuiScaledWidth();
				int height = mc.getWindow().getGuiScaledHeight();

				int alpha = 200; // 0-255
				int red = 11;
				int green = 9;
				int blue = 12;

				int argb = (alpha << 24) | (red << 16) | (green << 8) | blue;

				pEvent.getGuiGraphics().fill(0, 0, width, height, argb);
			}
			
		}

		voidblightOverlayInit(pEvent.getGuiGraphics(), mc);

	}

	private static void voidblightOverlayInit(GuiGraphics pGraphics, Minecraft mc) {
		Holder<MobEffect> holder = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(ModEffects.VOID_STRIKE_EFFECT.get());
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
			renderVoidblightOverlay(pGraphics, mc, alpha);
			VoidStrikeOverlayState.wasActiveLastTick = true;

		} else if (VoidStrikeOverlayState.wasActiveLastTick || VoidStrikeOverlayState.fadeOutTicks > 0) {
			VoidStrikeOverlayState.wasActiveLastTick = false;

			if (VoidStrikeOverlayState.fadeOutTicks == 0) {
				VoidStrikeOverlayState.fadeOutTicks = VoidStrikeOverlayState.MAX_FADE_TICKS;
			}

			float alpha = VoidStrikeOverlayState.fadeOutTicks / (float) VoidStrikeOverlayState.MAX_FADE_TICKS;
			renderVoidblightOverlay(pGraphics, mc, alpha);
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

	public static void renderShadowBlockOverlay(GuiGraphics pGraphics, Minecraft mc, float alpha) {

	}
}
