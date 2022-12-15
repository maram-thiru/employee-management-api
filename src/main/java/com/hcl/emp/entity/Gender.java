package com.hcl.emp.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
	MALE("M"),

	FEMALE("F");

	private String value;

	Gender(String value) {
		this.value = value;
	}

	@JsonValue
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static Gender fromValue(String value) {
		for (Gender gender : Gender.values()) {
			if (gender.value.equals(value)) {
				return gender;
			}
		}
		throw new IllegalArgumentException("Unexpected value '" + value + "'");
	}
}
