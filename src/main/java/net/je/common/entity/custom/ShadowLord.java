package net.je.common.entity.custom;

import net.je.common.block.ModBlocks;
import net.je.common.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class ShadowLord extends Monster {

	private static final EntityDataAccessor<Boolean> AWAKE =
			SynchedEntityData.defineId(ShadowLord.class, EntityDataSerializers.BOOLEAN);

	public boolean isAwake = false;


	public ShadowLord(EntityType<? extends Monster> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
		this.setNoAi(true);
	}

	public static AttributeSupplier.Builder createMonsterAttributes() {
		return Mob.createMobAttributes().add(Attributes.ATTACK_DAMAGE, 2f).add(Attributes.MAX_HEALTH, 400D)
				.add(Attributes.FOLLOW_RANGE, 15.0).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ARMOR, 0.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 1D).add(Attributes.STEP_HEIGHT, 1.0);
	}

	@Override
	public void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.5, true));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Mob.class, 8.0F));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true, false));
	}

	/*@Override
	protected void populateDefaultEquipmentSlots(RandomSource pRandom, DifficultyInstance pDifficulty) {

		this.setDropChance(EquipmentSlot.MAINHAND, 1.0F);
		super.populateDefaultEquipmentSlots(random, pDifficulty);

	}*/

	@Override
	public void setTarget(@Nullable LivingEntity target) {
		if (this.isAwake) {
			super.setTarget(target);
			this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.SHADOW_STEEL_SWORD.get()));
		}
	}

	@Override
	public void tick() {
		super.tick();
		if (!(this.level() == null) && !(this.level().isClientSide())) {
			Level level = this.level();
			if (level.getGameTime() % 10 == 0 && this.getTarget() != null) {
				BlockPos pos = this.getOnPos();
				int x = random.nextInt(2 - (-2) + 1) + (-2);
				int z = random.nextInt(2 - (-2) + 1) + (-2);
				BlockPos laserPos = pos.offset(x, 0, z);
				if (!(level.getBlockState(laserPos).is(ModBlocks.UNSTABLE_SHADOW_PRISM.get())) && level.getBlockState(laserPos).is(ModBlocks.FADED_END_STONE_BRICKS.get()) && level.isEmptyBlock(laserPos.above())) {
					level.setBlockAndUpdate(laserPos, ModBlocks.UNSTABLE_SHADOW_PRISM.get().defaultBlockState());
				}
			}
		}
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		// Only matter when it is sleeping
		if (!this.level().isClientSide && !this.isAwake) {
			// Optionally require an item: if (player.getItemInHand(hand).is(Items.SOME_ITEM)) { ... }
			this.isAwake = true;

			this.setNoAi(false);
			// Immediately target the clicking player
			if (player instanceof ServerPlayer) {
				this.setTarget(player);
			} else {
				this.setTarget(player);
			}



			((ServerLevel)this.level()).sendParticles(ParticleTypes.SMOKE, this.getX(), this.getY() + 1.0, this.getZ(), 20, 0.5, 0.5, 0.5, 0.02);

			return InteractionResult.CONSUME;
		}

		return super.mobInteract(player, hand);
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		if (!this.isAwake) {
			return true;
		}
		return super.isInvulnerableTo(source);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.putBoolean("Awake", this.isAwake);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag nbt) {
		super.readAdditionalSaveData(nbt);
		boolean w = nbt.getBoolean("Awake");
		this.isAwake = w;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder p_333664_) {
		super.defineSynchedData(p_333664_);
		p_333664_.define(AWAKE, false);

	}
}
