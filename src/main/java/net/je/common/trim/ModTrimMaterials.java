package net.je.common.trim;

import java.util.Map;

import io.netty.bootstrap.BootstrapConfig;
import net.je.JourneysEnd;
import net.je.common.item.ModItems;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.Bootstrap;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTrimMaterials {
	/*public static final ResourceKey<TrimMaterial> VOIDMETAL =
            ResourceKey.create(Registries.TRIM_MATERIAL, ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "voidmetal"));*/

    public static final DeferredRegister<TrimMaterial> TRIM_MATERIALS = DeferredRegister.create(Registries.TRIM_MATERIAL, JourneysEnd.MODID);

    /*public static void bootstrap(Bootstrap<TrimMaterial> context) {
        register(context, VOIDMETAL, ModItems.VOIDMETAL_INGOT.get(), Style.EMPTY.withColor(TextColor.parseColor("#4715bb").getOrThrow()), 1.0F);
    }

    private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> trimKey, Item item,
                                 Style style, float itemModelIndex) {
        TrimMaterial trimmaterial = TrimMaterial.create(trimKey.location().getPath(), item, itemModelIndex,
                Component.translatable(Util.makeDescriptionId("trim_material", trimKey.location())).withStyle(style), Map.of());
        context.register(trimKey, trimmaterial);
    }*/


    public static final RegistryObject<TrimMaterial> VOIDMETAL = TRIM_MATERIALS.register("voidmetal",
            () -> TrimMaterial.create("voidmetal",
                    ModItems.VOIDMETAL_INGOT.get(),
                    1.0F,
                    Component.translatable("trim_material.journeysend.voidmetal").withStyle(Style.EMPTY.withColor(0x4715bb)),
                    Map.of()
            ));
}
