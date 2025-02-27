package net.je.entity.client.renderer.entity.layers;

import java.util.List;
import java.util.function.BiFunction;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;

import net.je.JourneysEnd;
import net.je.entity.client.EndersentModel;
import net.je.entity.custom.Endersent;
import net.minecraft.Util;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.layers.WardenEmissiveLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EndersentEyesLayer<T extends Endersent, M extends EndersentModel<T>> extends RenderLayer<T, M>{
    
    
    private final ResourceLocation texture;


    public EndersentEyesLayer(
        RenderLayerParent<T, M> p_234885_,
        ResourceLocation p_234886_

    ) {
        super(p_234885_);
        this.texture = p_234886_;
    }

    public void render(
        PoseStack p_234902_,
        MultiBufferSource p_234903_,
        int p_234904_,
        T p_234905_,
        float p_234906_,
        float p_234907_,
        float p_234908_,
        float p_234909_,
        float p_234910_,
        float p_234911_
    ) {
        if (!p_234905_.isInvisible()) {
            VertexConsumer vertexconsumer = p_234903_.getBuffer(RenderType.eyes(this.texture));
            int i = FastColor.ARGB32.color(255, 255, 255);
            this.getParentModel().renderToBuffer(p_234902_, vertexconsumer, p_234904_, LivingEntityRenderer.getOverlayCoords(p_234905_, 0.0F), i);
        }
    }
	
	

	

}
