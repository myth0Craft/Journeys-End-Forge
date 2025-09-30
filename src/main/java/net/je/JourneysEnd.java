package net.je;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.logging.LogUtils;
import net.je.config.CommonConfig;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.particle.DragonBreathParticle;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.client.event.RegisterShadersEvent;
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
import org.slf4j.Logger;

import java.io.IOException;

@Mod(JourneysEnd.MODID)
public class JourneysEnd {

	public static final String MODID = "je";

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogUtils.getLogger();

	@SuppressWarnings("removal")
	public JourneysEnd() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		MinecraftForge.EVENT_BUS.register(this);

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC);

		//ModCreativeModeTab.register(modEventBus);

		//ModEntities.register(modEventBus);

		//ModSounds.register(modEventBus);

		//ModEffects.register(modEventBus);

		modEventBus.addListener(this::commonSetup);

		modEventBus.addListener(this::addCreative);

		//ModItems.register(modEventBus);

		//ModBlocks.register(modEventBus);

		//ModFluids.register(modEventBus);
		//ModFluidTypes.register(modEventBus);
		//ModBlockEntities.register(modEventBus);
		//ModMenuTypes.register(modEventBus);

		//ModRecipeSerializers.register(modEventBus);

		//ModLootModifiers.register(modEventBus);

		//ModParticles.register(modEventBus);

		//ModConditions.register(modEventBus);

		//ModStructureProcessors.register(modEventBus);

		//ModStructurePieceTypes.register(modEventBus);

		//ModStructureTypes.register(modEventBus);
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
			/*MenuScreens.register(ModMenuTypes.END_STONE_FURNACE_MENU.get(), EndStoneFurnaceScreen::new);

			if (CommonConfig.ALLOW_FANCY_VISUALS.get()) {
				ModRenderTypes.registerRenderTypes();
				BlockEntityRenderers.register(ModBlockEntities.SHADOW_PRISM_BLOCK_ENTITY.get(), ShadowPrismRenderer::new);
				BlockEntityRenderers.register(ModBlockEntities.UNSTABLE_SHADOW_PRISM_BLOCK_ENTITY.get(), UnstableShadowPrismRenderer::new);
			}
			BlockEntityRenderers.register(ModBlockEntities.RESPAWN_NEXUS_BLOCK_ENTITY.get(), RespawnNexusRenderer::new);
			BlockEntityRenderers.register(ModBlockEntities.SHADOW_BEAM_EMITTER_BLOCK_ENTITY.get(), ShadowBeamEmitterRenderer::new);
			BlockEntityRenderers.register(ModBlockEntities.ENDER_VAULT_BLOCK_ENTITY.get(), EnderVaultRenderer::new);*/

			//ItemBlockRenderTypes.setRenderLayer(ModBlocks.SHADOW_BLOCK.get(), RenderType.translucent());

			/*
			 * event.registerBlockEntityRenderer(ModBlockEntities.SHADOW_PRISM_BLOCK_ENTITY.
			 * get(), ShadowPrismRenderer::new);
			 */

			// BlockRenderLayerMap.put(RenderType.translucent(),
			// ModBlocks.SHADOW_BLOCK.get());
			// ItemBlockRenderTypes.setRenderLayer(ModBlocks.SHADOW_PRISM.get(),
			// ModRenderTypes.SHADOW_PRISM);

		}

		public static ShaderInstance SHADER;

		@SubscribeEvent
		public static void registerShaders(RegisterShadersEvent event) throws IOException {
			/*if (CommonConfig.ALLOW_FANCY_VISUALS.get()) {
				event.registerShader(new ShaderInstance(event.getResourceProvider(),
						ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "shadow_prism"),
						DefaultVertexFormat.POSITION), shaderInstance -> {
							SHADER = shaderInstance;
						});
			}*/
		}

		@SubscribeEvent
		public static void registerParticles(RegisterParticleProvidersEvent event) {
			//event.registerSpriteSet(ModParticles.WARDED_PARTICLES.get(), WardedParticleProvider::new);
		}

		@SubscribeEvent
		public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
			// Entities
			/*event.registerEntityRenderer(ModEntities.ENDERSENT.get(), EndersentRenderer::new);
			event.registerEntityRenderer(ModEntities.ENDERSENT_WITH_EYE.get(), EndersentWithEyeRenderer::new);
			event.registerEntityRenderer(ModEntities.ECHO.get(), ShadowMobRenderer::new);
			event.registerEntityRenderer(ModEntities.DUSKBLADE.get(), ShadowMobRenderer::new);
			event.registerEntityRenderer(ModEntities.SHADOW_LORD.get(), ShadowLordRenderer::new);*/
		}

		@SubscribeEvent
		public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
			/*event.registerLayerDefinition(EndersentModel.ENDERSENT_LAYER, EndersentModel::createBodyLayer);
			event.registerLayerDefinition(ShadowLordModel.LAYER_LOCATION, ShadowLordModel::createBodyLayer);*/
		}

		@SubscribeEvent
		public static void registerParticleProvider(RegisterParticleProvidersEvent event) {
			//event.registerSpriteSet(ModParticles.ENDERSENT_SPAWN_PARTICLES.get(), DragonBreathParticle.Provider::new);
		}
	}

}