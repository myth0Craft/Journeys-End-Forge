package net.je.common.entity;

import net.je.JourneysEnd;
import net.je.common.entity.custom.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModEntities {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
			DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, JourneysEnd.MODID);

	public static final RegistryObject<EntityType<Endersent>> ENDERSENT =
			ENTITY_TYPES.register("endersent", () -> EntityType.Builder.of(Endersent::new, MobCategory.MONSTER)
					.sized(1f, 6.5f).build(ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "endersent").toString()));

	public static final RegistryObject<EntityType<EndersentWithEye>> ENDERSENT_WITH_EYE =
			ENTITY_TYPES.register("endersent_with_eye", () -> EntityType.Builder.of(EndersentWithEye::new, MobCategory.MONSTER)
					.sized(1f, 6.5f).build(ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "endersent_with_eye").toString()));

	public static final RegistryObject<EntityType<Echo>> ECHO =
			ENTITY_TYPES.register("echo", () -> EntityType.Builder.of(Echo::new, MobCategory.MONSTER)
					.sized(0.6F, 1.8F)
					.fireImmune()
					.build(ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "echo").toString()));

	public static final RegistryObject<EntityType<Duskblade>> DUSKBLADE =
			ENTITY_TYPES.register("duskblade", () -> EntityType.Builder.of(Duskblade::new, MobCategory.MONSTER)
					.sized(0.6F, 1.8F)
					.fireImmune()
					.build(ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "duskblade").toString()));

	public static final RegistryObject<EntityType<ShadowLord>> SHADOW_LORD =
			ENTITY_TYPES.register("shadow_lord", () -> EntityType.Builder.of(ShadowLord::new, MobCategory.MONSTER)
					.sized(0.6F, 1.8F)
					.fireImmune()
					.build(ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "shadow_lord").toString()));

	public static void register(IEventBus eventBus) {
		ENTITY_TYPES.register(eventBus);
	}
}