package net.je.conditions;

import com.mojang.serialization.MapCodec;

import net.je.JourneysEnd;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = JourneysEnd.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModConditions {
	public static final DeferredRegister<MapCodec<? extends ICondition>> CONDITION_SERIALIZERS = DeferredRegister
			.create(ForgeRegistries.Keys.CONDITION_SERIALIZERS, JourneysEnd.MODID);

	public static final RegistryObject<MapCodec<? extends ICondition>> VANILLA_EYE_ENABLED = CONDITION_SERIALIZERS
			.register("config_enabled", () -> ModConfigEnabledCondition.CODEC);

	public static final RegistryObject<MapCodec<? extends ICondition>> NOT = CONDITION_SERIALIZERS
			.register("not", () -> ModNotCondition.CODEC);



	public static void register(IEventBus bus) {
		CONDITION_SERIALIZERS.register(bus);
	}

}
