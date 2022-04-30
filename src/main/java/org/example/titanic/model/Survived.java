package org.example.titanic.model;

public enum Survived {
    DROWN(false),
    NOT_DROWN(true);

    private final boolean survived;

    Survived(final boolean survived) {
        this.survived = survived;
    }

    public static Survived of(final String value) {
        Integer val = Integer.valueOf(value);
        if (val.equals(0)) {
            return DROWN;
        } else if (val.equals(1)) {
            return NOT_DROWN;
        } else {
            return null;
        }
    }

    public boolean isSurvived() {
        return survived;
    }

    @Override
    public String toString() {
        return survived ? "Survived" : "dead :(";
    }
}
