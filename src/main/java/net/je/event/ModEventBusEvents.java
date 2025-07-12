package net.je.event;

import java.lang.reflect.Field;

import net.je.JourneysEnd;
import net.je.config.CommonConfig;
import net.je.entity.ModEntities;
import net.je.entity.custom.Endersent;
import net.je.entity.custom.EndersentWithEye;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = JourneysEnd.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.ENDERSENT.get(), Endersent.createMonsterAttributes().build());
        event.put(ModEntities.ENDERSENT_WITH_EYE.get(), EndersentWithEye.createMonsterAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
    	event.enqueueWork(() -> {
    		ResourceLocation conditionalId = ResourceLocation.fromNamespaceAndPath("forge", "conditional");
    		RecipeSerializer<?> serializer = ForgeRegistries.RECIPE_SERIALIZERS.getValue(conditionalId);
    		if (serializer == null) {
    			System.err.println("[!!!] forge:conditional serializeris missing");
    		} else {
    			System.out.println("[OK] forge:conditional serializer is registerd: " + serializer);
    		}

        	System.out.println(CommonConfig.ENABLE_VANILLA_EYE_RECIPE.get());
    		System.out.println("========Recipe Manager Fields=========");
    		for (Field field : RecipeManager.class.getDeclaredFields()) {
    			field.setAccessible(true);
    			System.out.println(field.getName() + " -> " + field.getType().getTypeName());
    		}
    		System.out.println("=======================================");
    	});
    }


}
