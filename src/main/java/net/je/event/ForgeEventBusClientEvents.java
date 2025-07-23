package net.je.event;

import net.je.JourneysEnd;
import net.je.block.ModBlocks;
import net.je.util.ClientModData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JourneysEnd.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventBusClientEvents {
	@SubscribeEvent
    public static void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {
			return;
		}

        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;

        if (mc.player != null && mc.level != null) {
            Vec3 eyePos = player.getEyePosition();
            BlockPos eyeBlock = BlockPos.containing(eyePos.x, eyePos.y - 0.05, eyePos.z);
            boolean insideShadow = mc.level.getBlockState(eyeBlock).is(ModBlocks.SHADOW_BLOCK.get());
            ClientModData.setPlayerInsideShadowBlock(insideShadow);
        }
    }

	/*
	 * @SubscribeEvent public static void onRenderOverlay(RenderLevelStageEvent
	 * event) { if (event.getStage() !=
	 * RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS) return;
	 *
	 * Minecraft mc = Minecraft.getInstance();
	 *
	 * if (!ClientModData.isPlayerInsideShadowBlock()) return; if
	 * (mc.options.getCameraType() != CameraType.FIRST_PERSON) return;
	 *
	 * int width = mc.getWindow().getGuiScaledWidth(); int height =
	 * mc.getWindow().getGuiScaledHeight();
	 *
	 * RenderSystem.disableDepthTest(); RenderSystem.enableBlend();
	 * RenderSystem.defaultBlendFunc();
	 * RenderSystem.setShader(GameRenderer::getPositionColorShader);
	 *
	 * Tesselator tesselator = Tesselator.getInstance(); BufferBuilder buffer =
	 * tesselator.begin(VertexFormat.Mode.QUADS,
	 * DefaultVertexFormat.POSITION_COLOR);
	 *
	 * Matrix4f matrix = new Matrix4f(); matrix.identity();
	 *
	 * // ARGB: translucent purple buffer.addVertex(matrix, 0, height,
	 * 0).setColor(136, 0, 136, 136); // Bottom-left buffer.addVertex(matrix, width,
	 * height, 0).setColor(136, 0, 136, 136); // Bottom-right
	 * buffer.addVertex(matrix, width, 0, 0).setColor(136, 0, 136, 136); //
	 * Top-right buffer.addVertex(matrix, 0, 0, 0).setColor(136, 0, 136, 136); //
	 * Top-left MeshData mesh = buffer.buildOrThrow();
	 * BufferUploader.drawWithShader(mesh); RenderSystem.disableBlend();
	 * RenderSystem.enableDepthTest(); }
	 */



}