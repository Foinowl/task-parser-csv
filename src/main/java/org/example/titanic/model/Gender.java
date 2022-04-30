package org.example.titanic.model;

import java.util.Locale;


public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE");

    private String gender;

    Gender(final String value) {
        this.gender = value;
    }

    public static Gender of(final String value) {
        String val = value.trim().toLowerCase(Locale.ROOT);
        if (val.equals(MALE.toString().toLowerCase(Locale.ROOT))) {
            return MALE;
        } else if (val.equals(FEMALE.toString().toLowerCase(Locale.ROOT))) {
            return FEMALE;
        } else {
            return null;
        }
    }

    public boolean isGenderEquals(Gender gen) {
        return gender.equals(gen);
    }
}
