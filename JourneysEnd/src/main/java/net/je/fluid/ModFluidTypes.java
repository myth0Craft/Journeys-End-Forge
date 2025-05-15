package net.je.fluid;

import org.joml.Vector3f;

import net.je.JourneysEnd;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluidTypes {

	public static final ResourceLocation VOIDBLIGHT_STILL = ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "block/voidblight_still");
	public static final ResourceLocation VOIDBLIGHT_FLOWING = ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "block/voidblight_flow");
	public static final ResourceLocation VOIDBLIGHT_OVERLAY = ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "block/voidblight_overlay");

	public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister
			.create(ForgeRegistries.Keys.FLUID_TYPES, JourneysEnd.MODID);

	public static final RegistryObject<FluidType> VOIDBLIGHT = register("voidblight",
			FluidType.Properties.create().lightLevel(4).density(1).viscosity(1).canSwim(false).canDrown(true).canExtinguish(false));

	private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
		return FLUID_TYPES.register(name, () -> new BaseFluid(VOIDBLIGHT_STILL, VOIDBLIGHT_FLOWING, VOIDBLIGHT_OVERLAY,
				0xA1E038D0, new Vector3f(224f / 255f, 56f / 255f, 208f / 255f), properties));
	}

	public static void register(IEventBus eventBus) {
		FLUID_TYPES.register(eventBus);
	}

}
