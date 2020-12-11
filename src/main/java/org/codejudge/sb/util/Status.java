package org.codejudge.sb.util;

public enum Status {

	Created(0, "Created"),

	Contacted(1, "Contacted");

	/**
	 * @param code
	 * @return {@link Status} for the given enum code.
	 */
	public static Status valueOf(int code) {
		for (Status type : values()) {
			if (type.value == code) {
				return type;
			}
		}
		throw new IllegalArgumentException("No matching status for [" + code + "]");
	}

	private final int value;

	private final String description;

	private Status(int value, String description) {
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
