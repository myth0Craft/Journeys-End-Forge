package net.je;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.je.block.ModBlocks;
import net.je.block.entity.ModBlockEntities;
import net.je.conditions.ModConditions;
import net.je.config.CommonConfig;
import net.je.effect.ModEffects;
import net.je.entity.ModEntities;
import net.je.entity.client.EndersentModel;
import net.je.entity.client.renderer.entity.EndersentRenderer;
import net.je.entity.client.renderer.entity.EndersentWithEyeRenderer;
import net.je.fluid.ModFluidTypes;
import net.je.fluid.ModFluids;
import net.je.item.ModCreativeModeTab;
import net.je.item.ModItems;
import net.je.loot.ModLootModifiers;
import net.je.particle.ModParticles;
import net.je.recipe.ModRecipeSerializers;
import net.je.screen.EndStoneFurnaceScreen;
import net.je.screen.ModMenuTypes;
import net.je.sound.ModSounds;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.particle.DragonBreathParticle;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(JourneysEnd.MODID)
public class JourneysEnd {

	public static final String MODID = "je";

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogUtils.getLogger();

	public JourneysEnd() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		MinecraftForge.EVENT_BUS.register(this);

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC);

		ModCreativeModeTab.register(modEventBus);

		ModEntities.register(modEventBus);

		ModSounds.register(modEventBus);

		ModEffects.register(modEventBus);

		modEventBus.addListener(this::commonSetup);

		modEventBus.addListener(this::addCreative);

		ModItems.register(modEventBus);

		ModBlocks.register(modEventBus);

		ModFluids.register(modEventBus);
	    ModFluidTypes.register(modEventBus);
	    ModBlockEntities.register(modEventBus);
	    ModMenuTypes.register(modEventBus);

	    ModRecipeSerializers.register(modEventBus);

	    ModLootModifiers.register(modEventBus);

	    ModParticles.register(modEventBus);

	    ModConditions.register(modEventBus);

	}

	private void commonSetup(final FMLCommonSetupEvent event) {

	}

	private void addCreative(BuildCreativeModeTabContentsEvent event) {

	}

	@SubscribeEvent
	public void onServerStarting(ServerStartingEvent event) {

	}


	@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientModEvents {
		@SubscribeEvent
		public static void onClientSetup(FMLClientSetupEvent event) {
			MenuScreens.register(ModMenuTypes.END_STONE_FURNACE_MENU.get(), EndStoneFurnaceScreen::new);
		}
		@SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            // Entities
            event.registerEntityRenderer(ModEntities.ENDERSENT.get(), EndersentRenderer::new);
            event.registerEntityRenderer(ModEntities.ENDERSENT_WITH_EYE.get(), EndersentWithEyeRenderer::new);

        }
        @SubscribeEvent
    	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
    	    event.registerLayerDefinition(EndersentModel.ENDERSENT_LAYER, EndersentModel::createBodyLayer);
    	}
        @SubscribeEvent
        public static void registerParticleProvider(RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(ModParticles.ENDERSENT_SPAWN_PARTICLES.get(), DragonBreathParticle.Provider::new);
        }
	}

}