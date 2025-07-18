package net.je.render;

import org.joml.Matrix4f;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.je.JourneysEnd;
import net.je.block.entity.ShadowPrismBlockEntity;
import net.je.config.CommonConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.TheEndPortalBlockEntity;

public class ShadowPrismRenderer implements BlockEntityRenderer<ShadowPrismBlockEntity> {
	public static final ResourceLocation END_SKY_LOCATION = ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
			"textures/block/shadow_prism_bg.png");
	public static final ResourceLocation END_PORTAL_LOCATION = ResourceLocation
			.withDefaultNamespace("textures/entity/end_portal.png");

	public ShadowPrismRenderer(BlockEntityRendererProvider.Context pContext) {
	}

	public void render(ShadowPrismBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
			MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
		
		
			Matrix4f matrix4f = pPoseStack.last().pose();
			this.renderCube(pBlockEntity, matrix4f, pBufferSource.getBuffer(this.renderType()));
		
	}

	private void renderCube(ShadowPrismBlockEntity pBlockEntity, Matrix4f pPose, VertexConsumer pConsumer) {
		float f = this.getOffsetDown();
		float f1 = this.getOffsetUp();
		this.renderFace(pBlockEntity, pPose, pConsumer, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F,
				Direction.SOUTH);
		this.renderFace(pBlockEntity, pPose, pConsumer, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F,
				Direction.NORTH);
		this.renderFace(pBlockEntity, pPose, pConsumer, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.EAST);
		this.renderFace(pBlockEntity, pPose, pConsumer, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.WEST);
		this.renderFace(pBlockEntity, pPose, pConsumer, 0.0F, 1.0F, f, f, 0.0F, 0.0F, 1.0F, 1.0F, Direction.DOWN);
		this.renderFace(pBlockEntity, pPose, pConsumer, 0.0F, 1.0F, f1, f1, 1.0F, 1.0F, 0.0F, 0.0F, Direction.UP);
	}

	private void renderFace(ShadowPrismBlockEntity pBlockEntity, Matrix4f pPose, VertexConsumer pConsumer, float pX0,
			float pX1, float pY0, float pY1, float pZ0, float pZ1, float pZ2, float pZ3, Direction pDirection) {
		if (pBlockEntity.shouldRenderFace(pDirection)) {
			pConsumer.addVertex(pPose, pX0, pY0, pZ0);
			pConsumer.addVertex(pPose, pX1, pY0, pZ1);
			pConsumer.addVertex(pPose, pX1, pY1, pZ2);
			pConsumer.addVertex(pPose, pX0, pY1, pZ3);
		}
	}

	protected float getOffsetUp() {
		return 1.0F;
	}

	protected float getOffsetDown() {
		return 0.0F;
	}

	protected RenderType renderType() {
		return ModRenderTypes.SHADOW_PRISM;
	}
}
