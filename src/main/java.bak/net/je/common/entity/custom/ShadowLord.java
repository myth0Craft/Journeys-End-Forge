package net.je.common.entity.custom;

import net.je.common.block.ModBlocks;
import net.je.common.block.custom.WardedBlock;
import net.je.common.entity.ModEntities;
import net.je.common.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
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
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class ShadowLord extends Monster {

	//private BlockPos centerArenaPos;


	private final ServerBossEvent bossEvent = new ServerBossEvent(
			this.getDisplayName(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS
	);

	private static final EntityDataAccessor<Boolean> AWAKE =
			SynchedEntityData.defineId(ShadowLord.class, EntityDataSerializers.BOOLEAN);

	private static final EntityDataAccessor<BlockPos> ARENA_CENTER =
			SynchedEntityData.defineId(ShadowLord.class, EntityDataSerializers.BLOCK_POS);



	public ShadowLord(EntityType<? extends Monster> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
		this.setNoAi(true);
		this.setPersistenceRequired();
		this.setRemainingFireTicks(0);
		this.fireImmune();
	}

	public static AttributeSupplier.Builder createMonsterAttributes() {
		return Mob.createMobAttributes().add(Attributes.ATTACK_DAMAGE, 0.5f).add(Attributes.MAX_HEALTH, 400D)
				.add(Attributes.FOLLOW_RANGE, 15.0).add(Attributes.MOVEMENT_SPEED, 0.2D).add(Attributes.ARMOR, 0.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 1D).add(Attributes.STEP_HEIGHT, 1.0).add(Attributes.KNOCKBACK_RESISTANCE, 0.0);
	}

	@Override
	public void die(DamageSource pDamageSource) {
		super.die(pDamageSource);
		if (!this.level().isClientSide()) {
			AABB area = this.getBoundingBox().inflate(10);

			for (Echo echo : this.level().getEntitiesOfClass(Echo.class, area)) {
				if (this.distanceToSqr(echo) <= 100) {
					echo.kill();
				}
			}
		}
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	public void checkDespawn() {
	}

	@Override
	public boolean shouldDespawnInPeaceful() {
		return false;
	}

	@Override
	public boolean isPushable() {
		return this.isAwake();
	}

	@Override
	public boolean fireImmune() {
		return true;
	}

	public boolean isAwake() {
		return this.entityData.get(AWAKE);
	}

	@Override
	public void setCustomName(@javax.annotation.Nullable Component pName) {
		super.setCustomName(pName);
		if (this.isAwake()) {
			this.bossEvent.setName(this.getDisplayName());
		}
	}

	@Override
	public void startSeenByPlayer(ServerPlayer pPlayer) {
		super.startSeenByPlayer(pPlayer);
		if (this.isAwake()) {
			this.bossEvent.addPlayer(pPlayer);
		}
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer pPlayer) {
		super.stopSeenByPlayer(pPlayer);
		this.bossEvent.removePlayer(pPlayer);
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();
		if (this.isAwake()) {
			this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
		}
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
		if (this.isAwake()) {
			super.setTarget(target);
			this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.SHADOW_STEEL_SWORD.get()));
		}
	}

	@Override
	public void tick() {
		super.tick();
		if (!(this.level() == null) && !(this.level().isClientSide())) {
			Level level = this.level();

			if (level.getGameTime() % 60 == 0 && this.getTarget() != null) {
				this.tryTeleport();
			}

			if (level.getGameTime() % 10 == 0 && this.getTarget() != null) {
				if (this.getHealth()/this.getMaxHealth() > 0.66) {
					addLaser(findLaserPos(), level);
				} else if (this.getHealth()/this.getMaxHealth() > 0.33) {
					addLaser(findLaserPos(), level);
					addLaser(findLaserPos(), level);
				} else {
					addLaser(findLaserPos(), level);
					addLaser(findLaserPos(), level);
					addLaser(findLaserPos(), level);
					addLaser(findLaserPos(), level);
					if (level.getGameTime() % 100 == 0) {
						Entity entity = ModEntities.ECHO.get().create(level);
						entity.moveTo(this.position());
						level.addFreshEntity(entity);
					}
				}
			}
			if (this.getArenaCenter() != null) {
				if (this.getY() < this.getArenaCenter().getY() - 3) {
					/*this.teleportTo(centerArenaPos.getX() + 0.5, centerArenaPos.getY() + 1, centerArenaPos.getZ() + 0.5);
					((ServerLevel) this.level()).playSound(null, this.blockPosition(),
							SoundEvents.ENDERMAN_TELEPORT, SoundSource.HOSTILE, 1.0F, 1.0F);
					((ServerLevel) this.level()).sendParticles(ParticleTypes.PORTAL, this.getX(), this.getY() + 1.0, this.getZ(), 20, 0.5, 0.5, 0.5, 0.02);
					*/
					this.tryTeleport();
				}
			}
		}
	}

	public BlockPos findLaserPos() {
		BlockPos pos = this.getOnPos();
		int x = random.nextInt(2 - (-2) + 1) + (-2);
		int z = random.nextInt(2 - (-2) + 1) + (-2);
		BlockPos laserPos = pos.offset(x, 0, z);
		return laserPos;

	}

	public void addLaser(BlockPos laserPos, Level level) {
		if (!(level.getBlockState(laserPos).is(ModBlocks.UNSTABLE_SHADOW_PRISM.get())) && level.getBlockState(laserPos).is(ModBlocks.WARDED_FADED_END_STONE_BRICKS.get()) && level.isEmptyBlock(laserPos.above())) {
			level.setBlockAndUpdate(laserPos, ModBlocks.UNSTABLE_SHADOW_PRISM.get().defaultBlockState());
		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		boolean result = super.hurt(source, amount);

		if (!this.level().isClientSide && this.isAwake() && this.horizontalCollision) {
			this.tryTeleport();
		}

		return result;
	}

	private void tryTeleport() {
		ServerLevel serverLevel = (ServerLevel)this.level();

		for (int i = 0; i < 10; i++) {
			double dx = this.getArenaCenter().getX() + (this.random.nextInt(16) - 8);
			double dy = this.getArenaCenter().getY();
			double dz = this.getArenaCenter().getZ() + (this.random.nextInt(16) - 8);

			BlockPos pos = BlockPos.containing(dx, dy, dz);
			if (serverLevel.getBlockState(pos.below()).is(ModBlocks.WARDED_FADED_END_STONE_BRICKS.get())) {
				if (this.randomTeleport(dx, dy, dz, true)) {
					serverLevel.playSound(null, this.blockPosition(),
							SoundEvents.ENDERMAN_TELEPORT, SoundSource.HOSTILE, 1.0F, 1.0F);
					((ServerLevel) this.level()).sendParticles(ParticleTypes.PORTAL, this.getX(), this.getY() + 1.0, this.getZ(), 20, 0.5, 0.5, 0.5, 0.02);
					break;
				}
			}
		}
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		if (!this.level().isClientSide && !this.isAwake()) {
			this.entityData.set(AWAKE, true);

			setArenaCenter(this.blockPosition());

			replaceShadowPrismCircle((ServerLevel) this.level(), this.blockPosition().below(), 3);

			this.setNoAi(false);
			if (!player.isCreative()) {
				if (player instanceof ServerPlayer) {
					this.setTarget(player);
				} else {
					this.setTarget(player);
				}
			}
			for (ServerPlayer pPlayer : ((ServerLevel) this.level()).players()) {
				if (this.distanceToSqr(pPlayer) < 16) {
					this.bossEvent.addPlayer(pPlayer);
				}
			}


			((ServerLevel)this.level()).sendParticles(ParticleTypes.SMOKE, this.getX(), this.getY() + 1.0, this.getZ(), 20, 0.5, 0.5, 0.5, 0.02);

			return InteractionResult.CONSUME;
		}

		return super.mobInteract(player, hand);
	}

	private void replaceShadowPrismCircle(ServerLevel level, BlockPos center, int radius) {
		int rSq = radius * radius;

		for (int dx = -radius; dx <= radius; dx++) {
			for (int dz = -radius; dz <= radius; dz++) {
				if (dx * dx + dz * dz <= rSq) { // inside circle
					BlockPos pos = center.offset(dx, 0, dz);
					if (level.getBlockState(pos).is(ModBlocks.SHADOW_PRISM.get())) {
						level.setBlock(pos, ModBlocks.WARDED_FADED_END_STONE_BRICKS.get().defaultBlockState().setValue(WardedBlock.PLACED, false), 3);
					}
				}
			}
		}
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		if (!this.isAwake()) {
			return true;
		}
		return super.isInvulnerableTo(source);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.putBoolean("Awake", this.isAwake());

		BlockPos pos = getArenaCenter();
		nbt.putInt("ArenaX", pos.getX());
		nbt.putInt("ArenaY", pos.getY());
		nbt.putInt("ArenaZ", pos.getZ());

	}

	@Override
	public void readAdditionalSaveData(CompoundTag nbt) {
		super.readAdditionalSaveData(nbt);
		boolean w = nbt.getBoolean("Awake");
		this.entityData.set(AWAKE, w);
		this.setNoAi(!w);


		if (nbt.contains("ArenaX")) {
			setArenaCenter(new BlockPos(nbt.getInt("ArenaX"), nbt.getInt("ArenaY"), nbt.getInt("ArenaZ")));
		}
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder p_333664_) {
		super.defineSynchedData(p_333664_);
		p_333664_.define(AWAKE, false);
		p_333664_.define(ARENA_CENTER, BlockPos.ZERO);

	}

	public void setArenaCenter(BlockPos pos) {
		this.entityData.set(ARENA_CENTER, pos);
	}

	@Nullable
	public BlockPos getArenaCenter() {
		return this.entityData.get(ARENA_CENTER);
	}
}
