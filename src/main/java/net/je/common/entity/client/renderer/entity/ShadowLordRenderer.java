package net.je.common.entity.client.renderer.entity;

import net.je.JourneysEnd;
import net.je.common.entity.client.ModLayerDefinitions;
import net.je.common.entity.client.ShadowLordModel;
import net.je.common.entity.client.renderer.entity.layers.EchoEyesLayer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class ShadowLordRenderer<T extends Mob> extends MobRenderer<T, ShadowLordModel<T>> {

	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/entity/shadow_lord/shadow_lord.png");
	private static final ResourceLocation EYES = ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/entity/echo/echo_eyes.png");

	public ShadowLordRenderer(EntityRendererProvider.Context context) {
		super(context, new ShadowLordModel<>(context.bakeLayer(ShadowLordModel.LAYER_LOCATION)), 0.5f);

		this.addLayer(new EchoEyesLayer<>(this, EYES));
		this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
	}

	@Override
	public ResourceLocation getTextureLocation(T entity) {
		return TEXTURE;
	}
}
