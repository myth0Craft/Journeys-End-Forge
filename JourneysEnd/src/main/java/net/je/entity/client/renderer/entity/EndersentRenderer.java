package net.je.entity.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.je.JourneysEnd;
import net.je.entity.client.EndersentModel;
import net.je.entity.client.ModModelLayers;
import net.je.entity.client.renderer.entity.layers.EndersentEyesLayer;
import net.je.entity.custom.Endersent;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.WardenModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.layers.WardenEmissiveLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Giant;

public class EndersentRenderer extends MobRenderer<Endersent, EndersentModel<Endersent>> {
	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/entity/endersent/endersent.png");
	private static final ResourceLocation ENDERSENT_EYES = ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/entity/endersent/endersent_eyes.png");
	
	private final float scale;
	public EndersentRenderer(EntityRendererProvider.Context pContext) {
		super(pContext, new EndersentModel<>(pContext.bakeLayer(EndersentModel.ENDERSENT_LAYER)), 1f);
		this.scale = 1.2f;
		this.addLayer(new EndersentEyesLayer<>(this, ENDERSENT_EYES));

		
	}
	protected void scale(Endersent p_114775_, PoseStack p_114776_, float p_114777_) {
        p_114776_.scale(this.scale, this.scale, this.scale);
    }
	@Override
	public ResourceLocation getTextureLocation(Endersent pEntity) {
		// TODO Auto-generated method stub
		return TEXTURE;
	}
	
	
	
	
}