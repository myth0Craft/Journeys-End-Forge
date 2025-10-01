package net.je.common.entity.client.renderer.entity;

import net.je.JourneysEnd;
import net.je.common.entity.client.renderer.entity.layers.EchoEyesLayer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class DuskbladeRenderer<T extends Mob> extends MobRenderer<T, PlayerModel<T>> {

	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/entity/echo/echo.png");
	private static final ResourceLocation EYES = ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "textures/entity/echo/echo_eyes.png");

	public DuskbladeRenderer(EntityRendererProvider.Context context) {
		super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), false), 0.5f);

		this.addLayer(new EchoEyesLayer<>(this, EYES));
		this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
	}

	@Override
	public ResourceLocation getTextureLocation(T entity) {
		return TEXTURE;
	}
}