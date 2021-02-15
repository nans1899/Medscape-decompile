package org.unbescape.properties;

public enum PropertiesKeyEscapeLevel {
    LEVEL_1_BASIC_ESCAPE_SET(1),
    LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET(2),
    LEVEL_3_ALL_NON_ALPHANUMERIC(3),
    LEVEL_4_ALL_CHARACTERS(4);
    
    private final int escapeLevel;

    public static PropertiesKeyEscapeLevel forLevel(int i) {
        if (i == 1) {
            return LEVEL_1_BASIC_ESCAPE_SET;
        }
        if (i == 2) {
            return LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET;
        }
        if (i == 3) {
            return LEVEL_3_ALL_NON_ALPHANUMERIC;
        }
        if (i == 4) {
            return LEVEL_4_ALL_CHARACTERS;
        }
        throw new IllegalArgumentException("No escape level enum constant defined for level: " + i);
    }

    private PropertiesKeyEscapeLevel(int i) {
        this.escapeLevel = i;
    }

    public int getEscapeLevel() {
        return this.escapeLevel;
    }
}
