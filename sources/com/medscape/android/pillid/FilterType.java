package com.medscape.android.pillid;

public enum FilterType {
    IMPRINT("Imprint"),
    SHAPE("Shape"),
    COLOR("Color"),
    FORM("Form"),
    SCORING("Scoring"),
    NONE((String) null);
    
    private final String name;

    private FilterType(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public static FilterType get(String str) {
        if (IMPRINT.name.equalsIgnoreCase(str)) {
            return IMPRINT;
        }
        if (SHAPE.name.equalsIgnoreCase(str)) {
            return SHAPE;
        }
        if (COLOR.name.equalsIgnoreCase(str)) {
            return COLOR;
        }
        if (FORM.name.equalsIgnoreCase(str)) {
            return FORM;
        }
        if (SCORING.name.equalsIgnoreCase(str)) {
            return SCORING;
        }
        return NONE;
    }
}
