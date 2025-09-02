package net.je.common.entity.client.renderer.entity;

import net.je.JourneysEnd;
import net.je.common.entity.client.renderer.entity.layers.EchoEyesLayer;
import net.je.common.entity.client.renderer.entity.layers.EndersentEyesLayer;
import net.je.common.entity.custom.Echo;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class EchoRenderer extends MobRenderer<Echo, PlayerModel<Echo>> {

	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/entity/echo/echo.png");
	private static final ResourceLocation EYES = ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/entity/echo/echo_eyes.png");


	public EchoRenderer(EntityRendererProvider.Context context) {
		super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), false), 0.5f);
		this.addLayer(new EchoEyesLayer<>(this, EYES));
		this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
	}

	@Override
	public ResourceLocation getTextureLocation(Echo pEntity) {
		return TEXTURE;
	}
}
