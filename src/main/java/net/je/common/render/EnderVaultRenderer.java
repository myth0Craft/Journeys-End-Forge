package net.je.common.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.je.common.block.ModBlocks;
import net.je.common.block.custom.EnderVaultBlock;
import net.je.common.block.custom.RespawnNexusBlock;
import net.je.common.block.entity.EnderVaultBlockEntity;
import net.je.common.block.entity.RespawnNexusBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import org.joml.Matrix4f;

public class EnderVaultRenderer implements BlockEntityRenderer<EnderVaultBlockEntity> {
	/*public static final ResourceLocation END_SKY_LOCATION = ResourceLocation.withDefaultNamespace("textures/environment/end_sky.png");
	public static final ResourceLocation END_PORTAL_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/end_portal.png");*/

	public EnderVaultRenderer(BlockEntityRendererProvider.Context pContext) {
	}

	@Override
	public void render(EnderVaultBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
					   MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {

			if (!pBlockEntity.getBlockState().is(ModBlocks.ENDER_VAULT.get())) return;

			if (pBlockEntity.finished) return;

			pPoseStack.pushPose();

			Matrix4f matrix4f = pPoseStack.last().pose();
			VertexConsumer consumer = pBufferSource.getBuffer(this.renderType());

			this.renderTopFace(matrix4f, consumer, 6f / 16f, 10f / 16f, 6f / 16f, 10f / 16f);

			pPoseStack.popPose();
	}

	private void renderTopFace(Matrix4f pPose, VertexConsumer pConsumer,
							   float pX0, float pX1, float pZ0, float pZ1) {
		float y = getOffsetUp(); // top of the block
		pConsumer.addVertex(pPose, pX0, y, pZ1);
		pConsumer.addVertex(pPose, pX1, y, pZ1);
		pConsumer.addVertex(pPose, pX1, y, pZ0);
		pConsumer.addVertex(pPose, pX0, y, pZ0);



	}

	protected float getOffsetUp() {
		return 1.0001F;
	}

	protected float getOffsetDown() {
		return 0.0F;
	}

	protected RenderType renderType() {
		return RenderType.endPortal();
	}
}
