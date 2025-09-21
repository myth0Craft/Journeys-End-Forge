package net.je.common.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.je.JourneysEnd;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.checkerframework.checker.units.qual.C;

public class ShadowLordModel<T extends LivingEntity> extends HumanoidModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "shadow_lord"), "main");

	public ShadowLordModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		CubeDeformation pCubeDeformation = new CubeDeformation(0, 0, 0);
		int pYOffset = 0;

		partdefinition.addOrReplaceChild(
				"head",
				CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, pCubeDeformation),
				PartPose.offset(0.0F, 0.0F + pYOffset, 0.0F)
		);
		partdefinition.addOrReplaceChild(
				"hat",
				CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, pCubeDeformation.extend(0.5F)),
				PartPose.offset(0.0F, 0.0F + pYOffset, 0.0F)
		);
		partdefinition.addOrReplaceChild(
				"body",
				CubeListBuilder.create().texOffs(16, 20).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, pCubeDeformation),
				PartPose.offset(0.0F, 0.0F + pYOffset, 0.0F)
		);
		partdefinition.addOrReplaceChild(
				"right_arm",
				CubeListBuilder.create().texOffs(40, 20).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, pCubeDeformation),
				PartPose.offset(-5.0F, 2.0F + pYOffset, 0.0F)
		);
		partdefinition.addOrReplaceChild(
				"left_arm",
				CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, pCubeDeformation),
				PartPose.offset(5.0F, 2.0F + pYOffset, 0.0F)
		);
		partdefinition.addOrReplaceChild(
				"right_leg",
				CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, pCubeDeformation),
				PartPose.offset(-1.9F, 12.0F + pYOffset, 0.0F)
		);
		partdefinition.addOrReplaceChild(
				"left_leg",
				CubeListBuilder.create().texOffs(16, 48).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, pCubeDeformation),
				PartPose.offset(1.9F, 12.0F + pYOffset, 0.0F)
		);

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}