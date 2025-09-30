package net.je.common.entity.client;

import net.je.common.entity.animations.DuskbladeAnim;
import net.je.common.entity.animations.EndersentAttackAnim;
import net.je.common.entity.custom.BaseEndersent;
import net.je.common.entity.custom.Duskblade;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.HumanoidArm;
import org.joml.Vector3f;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DuskbladeModel extends PlayerModel<Duskblade> {
	//private static final Vector3f ANIMATION_VECTOR_CACHE = new Vector3f();

	private boolean shouldUseRightArm = true;

	public DuskbladeModel(ModelPart pRoot, boolean pSlim) {
		super(pRoot, pSlim);
	}

	@Override
	protected void setupAttackAnimation(Duskblade pLivingEntity, float pAgeInTicks) {
		if (!(this.attackTime <= 0.0F)) {
			HumanoidArm humanoidarm;
			if (shouldUseRightArm) {
				humanoidarm = HumanoidArm.RIGHT;
				shouldUseRightArm = !shouldUseRightArm;
			} else {
				humanoidarm = HumanoidArm.LEFT;
				shouldUseRightArm = !shouldUseRightArm;
			}

			ModelPart modelpart = this.getArm(humanoidarm);
			float f = this.attackTime;
			this.body.yRot = Mth.sin(Mth.sqrt(f) * (float) (Math.PI * 2)) * 0.2F;
			if (humanoidarm == HumanoidArm.LEFT) {
				this.body.yRot *= -1.0F;
			}

			this.rightArm.z = Mth.sin(this.body.yRot) * 5.0F;
			this.rightArm.x = -Mth.cos(this.body.yRot) * 5.0F;
			this.leftArm.z = -Mth.sin(this.body.yRot) * 5.0F;
			this.leftArm.x = Mth.cos(this.body.yRot) * 5.0F;
			this.rightArm.yRot = this.rightArm.yRot + this.body.yRot;
			this.leftArm.yRot = this.leftArm.yRot + this.body.yRot;
			this.leftArm.xRot = this.leftArm.xRot + this.body.yRot;
			f = 1.0F - this.attackTime;
			f *= f;
			f *= f;
			f = 1.0F - f;
			float f1 = Mth.sin(f * (float) Math.PI);
			float f2 = Mth.sin(this.attackTime * (float) Math.PI) * -(this.head.xRot - 0.7F) * 0.75F;
			modelpart.xRot -= f1 * 1.2F + f2;
			modelpart.yRot = modelpart.yRot + this.body.yRot * 2.0F;
			modelpart.zRot = modelpart.zRot + Mth.sin(this.attackTime * (float) Math.PI) * -0.4F;
		}
	}
}
