package net.je.entity.client;

import net.je.JourneysEnd;
import net.je.entity.animations.EndersentAnim;
import net.je.entity.animations.EndersentAttackAnim;
import net.je.entity.custom.BaseEndersent;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings("unused")
@OnlyIn(Dist.CLIENT)
public class EndersentModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation ENDERSENT_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "endersent"), "main");
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart leftArm;
	private final ModelPart leftArm2;
	private final ModelPart rightArm;
	private final ModelPart rightArm2;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;

	public EndersentModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.head = this.root.getChild("head");
		this.leftArm = this.root.getChild("leftArm");
		this.leftArm2 = this.leftArm.getChild("leftArm2");
		this.rightArm = this.root.getChild("rightArm");
		this.rightArm2 = this.rightArm.getChild("rightArm2");
		this.rightLeg = this.root.getChild("rightLeg");
		this.leftLeg = this.root.getChild("leftLeg");
	}


	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, -20.0F, 0.0F));

		//PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, -19.0F, -7.0F, 24.0F, 28.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, -19.0F, -7.0F, 24.0F, 28.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(0, 120).addBox(-6.5F, -10.0F, -7.01F, 13.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, 0.0F));

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(48, 70).addBox(-8.0F, -13.0F, -8.0F, 16.0F, 18.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -26.0F, -5.0F));

		PartDefinition leftArm = root.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(15, 0).addBox(0.0F, -2.0F, -3.0F, 6.0F, 34.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(12.0F, -22.0F, 0.0F));

		PartDefinition leftArm2 = leftArm.addOrReplaceChild("leftArm2", CubeListBuilder.create().texOffs(15, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 34.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 13).addBox(-4.0F, 22.0F, -3.0F, 8.0F, 12.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 32.0F, 0.0F));

		PartDefinition rightArm = root.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(15, 0).addBox(-6.0F, -2.0F, -3.0F, 6.0F, 34.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-12.0F, -22.0F, 0.0F));

		PartDefinition rightArm2 = rightArm.addOrReplaceChild("rightArm2", CubeListBuilder.create().texOffs(15, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 34.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 13).addBox(-4.0F, 22.0F, -3.0F, 8.0F, 12.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 32.0F, 0.0F));

		PartDefinition rightLeg = root.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(24, 70).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 44.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 0.0F, 0.0F));

		PartDefinition leftLeg = root.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(0, 70).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 44.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	public void setupAnim(T p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_,
			float p_102623_) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.head.xRot = p_102623_* (float) (Math.PI/180.0);
        this.head.yRot = p_102622_* (float) (Math.PI/180.0);

        this.animateWalk(EndersentAnim.WALK, p_102619_, p_102620_, 2f, 2.5f);
        this.animate(((BaseEndersent) p_102618_).attackAnimationState, EndersentAttackAnim.ATTACK, p_102621_, 1f);
        this.animate(((BaseEndersent) p_102618_).largeAttackAnimationState, EndersentAttackAnim.LARGE_ATTACK, p_102621_, 1f);

	}
}