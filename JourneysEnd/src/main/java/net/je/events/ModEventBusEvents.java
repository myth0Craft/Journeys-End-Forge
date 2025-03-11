package net.je.events;

import net.je.JourneysEnd;
import net.je.entity.ModEntities;
import net.je.entity.custom.Endersent;
import net.je.entity.custom.EndersentWithEye;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.level.levelgen.Heightmap;

@Mod.EventBusSubscriber(modid = JourneysEnd.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.ENDERSENT.get(), Endersent.createMonsterAttributes().build());
        event.put(ModEntities.ENDERSENT_WITH_EYE.get(), EndersentWithEye.createMonsterAttributes().build());
    }
    
    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(ModEntities.ENDERSENT.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.WORLD_SURFACE,
                Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        
        event.register(ModEntities.ENDERSENT_WITH_EYE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
}
