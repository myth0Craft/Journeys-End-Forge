package net.je.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;

import net.je.JourneysEnd;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderStateShard.ShaderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.resources.ResourceLocation;

public class ModRenderTypes {
	public static RenderType SHADOW_PRISM;

	public static void registerShaders() {
		SHADOW_PRISM = RenderType.create("shadow_prism", DefaultVertexFormat.POSITION, VertexFormat.Mode.QUADS, 1536,
				false, false,
				RenderType.CompositeState.builder()
						.setShaderState(new ShaderStateShard(
								() -> JourneysEnd.ClientModEvents.SHADER))
						.setTextureState(RenderStateShard.MultiTextureStateShard.builder()
								.add(ShadowPrismRenderer.END_SKY_LOCATION, false, false)
								.add(ShadowPrismRenderer.END_PORTAL_LOCATION, false, false).build())
						.setTransparencyState(
								new RenderStateShard.TransparencyStateShard("translucent_transparency", () -> {
									RenderSystem.enableBlend();
									RenderSystem.defaultBlendFunc();
								}, () -> {
									RenderSystem.disableBlend();
								}))
						.createCompositeState(false));

	}
}
