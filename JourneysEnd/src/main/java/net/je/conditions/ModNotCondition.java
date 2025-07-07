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

public class ModNotCondition implements ICondition {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "not");
    
    public static final MapCodec<ModNotCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> 
    instance.group(ICondition.CODEC.fieldOf("value").forGetter(c -> c.inner)
    		).apply(instance, ModNotCondition::new));
    
    private final ICondition inner;
    
    public ModNotCondition(ICondition pInner) {
    	this.inner = pInner;
    }
    

    // Hook into dynamic config (optional)
    @Override
    public boolean test(IContext context, com.mojang.serialization.DynamicOps<?> ops) {
        return !inner.test(context, ops); // Replace with ModConfig.ENABLE_MY_FEATURE.get() if needed
    }
    

    @Override
    public MapCodec<? extends ICondition> codec() {
        return CODEC;
    }
}