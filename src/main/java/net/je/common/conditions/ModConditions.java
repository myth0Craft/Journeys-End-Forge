package net.je.common.conditions;

import com.mojang.serialization.MapCodec;

import net.je.JourneysEnd;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = JourneysEnd.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModConditions {
/*	public static final DeferredRegister<MapCodec<? extends ICondition>> CONDITION_SERIALIZERS = DeferredRegister.create(RegistryKey.ofRegistry(
			ResourceLocation.fromNamespaceAndPath("minecraft", "condition")), JourneysEnd.MODID);

	public static final RegistryObject<MapCodec<? extends ICondition>> VANILLA_EYE_ENABLED = CONDITION_SERIALIZERS
			.register("config_enabled", () -> ModConfigEnabledCondition.CODEC);

	public static final RegistryObject<MapCodec<? extends ICondition>> NOT = CONDITION_SERIALIZERS
			.register("not", () -> ModNotCondition.CODEC);



	public static void register(IEventBus bus) {
		CONDITION_SERIALIZERS.register(bus);
	}*/

	//public static final DeferredRegister<ICondition> CONDITIONS = DeferredRegister.create(ForgeRegistries, "yourmodid");

}
