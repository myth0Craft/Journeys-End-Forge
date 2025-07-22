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
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JourneysEnd.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventBusClientEvents {
	@SubscribeEvent
    public static void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;

        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        
        if (mc.player != null && mc.level != null) {
            Vec3 eyePos = player.getEyePosition();
            BlockPos eyeBlock = BlockPos.containing(eyePos.x, eyePos.y - 0.05, eyePos.z);
            boolean insideShadow = mc.level.getBlockState(eyeBlock).is(ModBlocks.SHADOW_BLOCK.get());
            ClientModData.setPlayerInsideShadowBlock(insideShadow);
        }
    }
}
