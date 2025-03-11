package net.je.entity.custom;

import java.util.Arrays;
import java.util.Comparator;

public enum EndersentVariant {
	WITH_EYE(0), WITHOUT_EYE(1);

	private static final EndersentVariant[] BY_ID = Arrays.stream(values())
			.sorted(Comparator.comparingInt(EndersentVariant::getId)).toArray(EndersentVariant[]::new);
	private final int id;

	public int getId() {
		return this.id;
	}

	EndersentVariant(int id) {
		this.id = id;
	}

	public static EndersentVariant byId(int id) {
		return BY_ID[id % BY_ID.length];
	}
}
