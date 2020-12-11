package org.codejudge.sb.util;

public enum LocationType {

	Country(0, "Country"),

	City(1, "City"),

	Zip(2, "Zip");

	/**
	 * @param code
	 * @return {@link LocationType} for the given enum code.
	 */
	public static LocationType valueOf(int code) {
		for (LocationType type : values()) {
			if (type.value == code) {
				return type;
			}
		}
		throw new IllegalArgumentException("No matching status for [" + code + "]");
	}

	private final int value;

	private final String description;

	private LocationType(int value, String description) {
		this.value = value;
		this.description = description;
	}

	/**
	 * @return Description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return value
	 */
	public int getValue() {
		return value;
	}
}
