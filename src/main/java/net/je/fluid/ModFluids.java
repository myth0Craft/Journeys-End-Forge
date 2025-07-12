package net.je.fluid;

import net.je.JourneysEnd;
import net.je.block.ModBlocks;
import net.je.item.ModItems;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
	 public static final DeferredRegister<Fluid> FLUIDS =
	            DeferredRegister.create(ForgeRegistries.FLUIDS, JourneysEnd.MODID);

	    public static final RegistryObject<FlowingFluid> SOURCE_VOIDBLIGHT = FLUIDS.register("source_voidblight",
	            () -> new VoidblightFluid.Source());
	    public static final RegistryObject<FlowingFluid> FLOWING_VOIDBLIGHT = FLUIDS.register("flowing_voidblight",
	            () -> new VoidblightFluid.Flowing());


	    public static final ForgeFlowingFluid.Properties VOIDBLIGHT_PROPERTIES = new ForgeFlowingFluid.Properties(
	            ModFluidTypes.VOIDBLIGHT, SOURCE_VOIDBLIGHT, FLOWING_VOIDBLIGHT)
	            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.VOIDBLIGHT)
	            .bucket(ModItems.VOIDBLIGHT_BUCKET)
	            .tickRate(30);


	    public static void register(IEventBus eventBus) {
	        FLUIDS.register(eventBus);
	    }

}
