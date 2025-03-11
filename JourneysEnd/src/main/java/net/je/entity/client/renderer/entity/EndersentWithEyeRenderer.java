package net.je.entity.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.je.JourneysEnd;
import net.je.entity.client.EndersentModel;
import net.je.entity.client.renderer.entity.layers.EndersentEyesLayer;
import net.je.entity.custom.Endersent;
import net.je.entity.custom.EndersentWithEye;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class EndersentWithEyeRenderer extends MobRenderer<EndersentWithEye, EndersentModel<EndersentWithEye>> {
	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/entity/endersent/endersent_with_eye.png");
	private static final ResourceLocation ENDERSENT_EYES = ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/entity/endersent/endersent_eyes.png");
	
	/*
	 * private static final Map<EndersentVariant, ResourceLocation>
	 * LOCATION_BY_VARIANT = Util.make(Maps.newEnumMap(EndersentVariant.class), map
	 * -> { map.put(EndersentVariant.WITH_EYE,
	 * ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
	 * "textures/entity/endersent/endersent_with_eye.png"));
	 * map.put(EndersentVariant.WITHOUT_EYE,
	 * ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID,
	 * "textures/entity/endersent/endersent.png")); });
	 */
	
	private final float scale;
	public EndersentWithEyeRenderer(EntityRendererProvider.Context pContext) {
		super(pContext, new EndersentModel<>(pContext.bakeLayer(EndersentModel.ENDERSENT_LAYER)), 1f);
		this.scale = 1.2f;
		this.addLayer(new EndersentEyesLayer<>(this, ENDERSENT_EYES));

		
	}
	protected void scale(EndersentWithEye p_114775_, PoseStack p_114776_, float p_114777_) {
        p_114776_.scale(this.scale, this.scale, this.scale);
    }
	@Override
	public ResourceLocation getTextureLocation(EndersentWithEye pEntity) {
		//return LOCATION_BY_VARIANT.get(pEntity.getVariant());
		return TEXTURE;
	}
	
	
	
	
}