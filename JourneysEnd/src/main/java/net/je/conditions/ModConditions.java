package net.je.conditions;

import java.util.Map;

import com.mojang.serialization.MapCodec;

import net.je.JourneysEnd;
import net.je.config.CommonConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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
