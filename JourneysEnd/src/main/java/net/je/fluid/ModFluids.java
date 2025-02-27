package net.je.fluid;

import java.util.function.Supplier;

import net.je.JourneysEnd;
import net.je.block.ModBlocks;
import net.je.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.EmptyFluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
	 public static final DeferredRegister<Fluid> FLUIDS =
	            DeferredRegister.create(ForgeRegistries.FLUIDS, JourneysEnd.MODID);

	    public static final RegistryObject<FlowingFluid> SOURCE_CORRUPTION = FLUIDS.register("source_corruption",
	            () -> new ForgeFlowingFluid.Source(ModFluids.CORRUPTION_PROPERTIES));
	    public static final RegistryObject<FlowingFluid> FLOWING_CORRUPTION = FLUIDS.register("flowing_corruption",
	            () -> new ForgeFlowingFluid.Flowing(ModFluids.CORRUPTION_PROPERTIES));


	    public static final ForgeFlowingFluid.Properties CORRUPTION_PROPERTIES = new ForgeFlowingFluid.Properties(
	            ModFluidTypes.CORRUPTION, SOURCE_CORRUPTION, FLOWING_CORRUPTION)
	            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.CORRUPTION)
	            .bucket(ModItems.CORRUPTION_BUCKET)
	            .tickRate((int) 30);


	    public static void register(IEventBus eventBus) {
	        FLUIDS.register(eventBus);
	    }

}
