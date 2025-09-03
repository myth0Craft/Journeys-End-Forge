package net.je.event;

import java.lang.reflect.Field;

import net.je.JourneysEnd;
import net.je.common.entity.custom.Duskblade;
import net.je.common.entity.custom.Echo;
import net.je.config.CommonConfig;
import net.je.common.entity.ModEntities;
import net.je.common.entity.custom.Endersent;
import net.je.common.entity.custom.EndersentWithEye;
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
		event.put(ModEntities.ECHO.get(), Echo.createMonsterAttributes().build());
        event.put(ModEntities.DUSKBLADE.get(), Duskblade.createMonsterAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
    }
}
