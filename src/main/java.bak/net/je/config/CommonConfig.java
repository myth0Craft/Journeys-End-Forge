package net.je.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;

	public static ForgeConfigSpec.BooleanValue ENABLE_VANILLA_EYE_RECIPE;
	public static ForgeConfigSpec.BooleanValue EYE_REQUIRES_ECHO_SHARDS;
	public static ForgeConfigSpec.BooleanValue GRAVITY_DISTORTER_WORKS_IN_CREATIVE;
	public static ForgeConfigSpec.BooleanValue ALLOW_FANCY_VISUALS;
	public static ForgeConfigSpec.BooleanValue ALLOW_WARDED_BLOCKS;

	static {
		BUILDER.push("General");
		ENABLE_VANILLA_EYE_RECIPE = BUILDER.comment("Enable the Vanilla Eye of Ender recipe").define("enableVanillaEyeRecipe", false);
		EYE_REQUIRES_ECHO_SHARDS = BUILDER.comment("Makes the custom Eye of Ender recipe require Echo Shards").define("eyeRequiresEchoShards", true);
		GRAVITY_DISTORTER_WORKS_IN_CREATIVE = BUILDER.comment("The Gravity Distorter block levitates players in Creative Mode").define("gravity_distorter_works_in_creative", false);
		ALLOW_FANCY_VISUALS = BUILDER.comment("Enable fancy modded visual effects. May be incompatible with shaders or other graphics mods.").define("allowFancyVisuals", true);
		ALLOW_WARDED_BLOCKS = BUILDER.comment("Allows warded blocks to be unminable except with certain pickaxes. Turning this off will make certain dungeons much easier.").define("allowWardedBlocks", true);

		BUILDER.pop();
		SPEC = BUILDER.build();
	}
}
