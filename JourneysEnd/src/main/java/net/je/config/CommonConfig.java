package net.je.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	
	public static ForgeConfigSpec.BooleanValue ENABLE_VANILLA_EYE_RECIPE;
	public static ForgeConfigSpec.BooleanValue EYE_REQUIRES_ECHO_SHARDS;
	
	static {
		BUILDER.push("General");
		ENABLE_VANILLA_EYE_RECIPE = BUILDER.comment("Enable the Vanilla Eye of Ender recipe").define("enableVanillaEyeRecipe", false);
		EYE_REQUIRES_ECHO_SHARDS = BUILDER.comment("Makes the custom Eye of Ender recipe require Echo Shards").define("eyeRequiresEchoShards", true);
		
		BUILDER.pop();
		SPEC = BUILDER.build();
	}
}
