package net.je.util;

public class ClientModData {
	private static boolean playerInsideShadowBlock = false;

	public static boolean isPlayerInsideShadowBlock() {
		return playerInsideShadowBlock;
	}

	public static void setPlayerInsideShadowBlock(boolean inside) {
		playerInsideShadowBlock = inside;
	}

}
