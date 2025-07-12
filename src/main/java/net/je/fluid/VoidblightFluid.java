package net.je.fluid;

import java.util.List;

import net.je.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class VoidblightFluid extends ForgeFlowingFluid {

	public static class Flowing extends VoidblightFluid {
		public Flowing() {
			super();
			registerDefaultState(getStateDefinition().any().setValue(LEVEL, 7));
		}

		@Override
		protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
			super.createFluidStateDefinition(builder);
			builder.add(LEVEL);
		}

		@Override
		public int getAmount(FluidState state) {
			return state.getValue(LEVEL);
		}

		@Override
		public boolean isSource(FluidState state) {
			return false;
		}
	}

	public static class Source extends VoidblightFluid {
		public Source() {
			super();
		}

		@Override
		public int getAmount(FluidState state) {
			return 8;
		}

		@Override
		public boolean isSource(FluidState state) {
			return true;
		}
	}

	private VoidblightFluid() {
		super(ModFluids.VOIDBLIGHT_PROPERTIES);
	}

	@Override
	public void tick(Level pLevel, BlockPos pPos, FluidState pState) {
		super.tick(pLevel, pPos, pState);
		AABB box = new AABB(pPos);
		List<LivingEntity> entities = pLevel.getEntitiesOfClass(LivingEntity.class, box);

		Holder<MobEffect> holder = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(ModEffects.VOID_STRIKE_EFFECT.get());
		for (LivingEntity entity : entities) {
			entity.addEffect(new MobEffectInstance(holder, 60, 1));
		}

		pLevel.scheduleTick(pPos, this, 10);
	}
}
