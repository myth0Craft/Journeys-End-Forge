package net.je.trim;

import java.util.Map;

import net.je.JourneysEnd;
import net.je.item.ModItems;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;

public class ModTrimMaterials {
	public static final ResourceKey<TrimMaterial> VOIDMETAL =
            ResourceKey.create(Registries.TRIM_MATERIAL, ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "voidmetal"));

    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        register(context, VOIDMETAL, ModItems.VOIDMETAL_INGOT.get(), Style.EMPTY.withColor(TextColor.parseColor("#4715bb").getOrThrow()), 1.0F);
    }

    private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> trimKey, Item item,
                                 Style style, float itemModelIndex) {
        TrimMaterial trimmaterial = TrimMaterial.create(trimKey.location().getPath(), item, itemModelIndex,
                Component.translatable(Util.makeDescriptionId("trim_material", trimKey.location())).withStyle(style), Map.of());
        context.register(trimKey, trimmaterial);
    }
}
