package net.je.common.conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.je.JourneysEnd;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;

public class ModNotCondition implements ICondition {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(JourneysEnd.MODID, "not");

    /*public static final MapCodec<ModNotCondition> CODEC = RecordCodecBuilder.create(instance ->
    instance.group(Codec.STRING.fieldOf("value").forGetter(c -> c.inner)
    		).apply(instance, ModNotCondition::new));*/

    private final ICondition inner;

    public ModNotCondition(ICondition pInner) {
    	this.inner = pInner;
    }


    @Override
    public ResourceLocation getID() {
        return ID;
    }

    // Hook into dynamic config (optional)
    @Override
    public boolean test(IContext context) {
        return !inner.test(context); // Replace with ModConfig.ENABLE_MY_FEATURE.get() if needed
    }
}