package net.je.entity.client;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModLayerDefinitions {

	public static Map<ModelLayerLocation, LayerDefinition> createRoots() {
		Builder<ModelLayerLocation, LayerDefinition> builder = ImmutableMap.builder();
		builder.put(EndersentModel.ENDERSENT_LAYER, EndersentModel.createBodyLayer());
		ImmutableMap<ModelLayerLocation, LayerDefinition> immutablemap = builder.build();
		List<ModelLayerLocation> list = ModelLayers.getKnownLocations()
				.filter(p_171117_ -> !immutablemap.containsKey(p_171117_)).collect(Collectors.toList());
		if (!list.isEmpty()) {
			throw new IllegalStateException("Missing layer definitions: " + list);
		} else {
			return immutablemap;
		}
	}

}
