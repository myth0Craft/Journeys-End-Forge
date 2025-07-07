package net.je.conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.je.JourneysEnd;
import net.je.config.CommonConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

public class ModConfigEnabledCondition implements ICondition {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "config_enabled");
    
    public static final MapCodec<ModConfigEnabledCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> 
    instance.group(Codec.STRING.fieldOf("key").forGetter(cond -> cond.key)
    		).apply(instance, ModConfigEnabledCondition::new));
    
    private final String key;
    
    public ModConfigEnabledCondition(String pkey) {
    	this.key = pkey;
    }
    

    // Hook into dynamic config (optional)
    @Override
    public boolean test(IContext context, com.mojang.serialization.DynamicOps<?> ops) {
        return getConfigValue(key); // Replace with ModConfig.ENABLE_MY_FEATURE.get() if needed
    }
    

    @Override
    public MapCodec<? extends ICondition> codec() {
        return CODEC;
    }
    
    private boolean getConfigValue(String pkey) {
    	boolean value = switch (pkey) {
    	case "vanilla_eye_recipe_enabled" -> CommonConfig.ENABLE_VANILLA_EYE_RECIPE.get();
    	case "eye_requires_echo_shards" -> CommonConfig.EYE_REQUIRES_ECHO_SHARDS.get();
    	default -> false;
    	
    	};
    	return value;
    }
}