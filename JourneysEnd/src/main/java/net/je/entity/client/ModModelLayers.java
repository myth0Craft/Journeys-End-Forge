package net.je.entity.client;

import java.util.Set;

import com.google.common.collect.Sets;

import net.je.JourneysEnd;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers extends ModelLayers{

	private static final Set<ModelLayerLocation> ALL_MOD_MODELS = Sets.newHashSet();

    @SuppressWarnings("unused")
	private static ModelLayerLocation register(String p_171294_) {
        return register(p_171294_, "main");
    }

    private static ModelLayerLocation register(String p_171296_, String p_171297_) {
        ModelLayerLocation modellayerlocation = createLocation(p_171296_, p_171297_);
        if (!ALL_MOD_MODELS.add(modellayerlocation)) {
            throw new IllegalStateException("Duplicate registration for " + modellayerlocation);
        } else {
            return modellayerlocation;
        }
    }

    private static ModelLayerLocation createLocation(String p_171301_, String p_171302_) {
        return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, p_171301_), p_171302_);
    }


}


