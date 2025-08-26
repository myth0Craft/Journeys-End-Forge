package net.je.common.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.je.common.block.custom.RespawnNexusBlock;
import net.je.common.block.entity.RespawnNexusBlockEntity;
import net.je.common.block.entity.ShadowBeamEmitterBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import org.joml.Matrix4f;

public class ShadowBeamEmitterRenderer implements BlockEntityRenderer<ShadowBeamEmitterBlockEntity> {
	public static final ResourceLocation BEAM_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/beacon_beam.png");

	public ShadowBeamEmitterRenderer(BlockEntityRendererProvider.Context pContext) {
	}

	@Override
	public void render(ShadowBeamEmitterBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
					   MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {

		int j = DyeColor.WHITE.getTextureDiffuseColor();
		long k = pBlockEntity.getLevel().getGameTime();
		int height = pBlockEntity.getBeamHeight();
		if (pBlockEntity.isActive()) {
			UnstableShadowPrismRenderer.renderBeaconBeam(pPoseStack, pBufferSource, BEAM_LOCATION, pPartialTick, 1, k, 1, height, j, 0.15F, 0.175F);
		}
	}
}
