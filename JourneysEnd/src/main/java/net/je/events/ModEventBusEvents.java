package net.je.events;

import net.je.JourneysEnd;
import net.je.entity.ModEntities;
import net.je.entity.custom.Endersent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JourneysEnd.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.ENDERSENT.get(), Endersent.createMonsterAttributes().build());
    }
}
