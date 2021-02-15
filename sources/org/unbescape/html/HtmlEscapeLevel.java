package org.unbescape.html;

public enum HtmlEscapeLevel {
    LEVEL_0_ONLY_MARKUP_SIGNIFICANT_EXCEPT_APOS(0),
    LEVEL_1_ONLY_MARKUP_SIGNIFICANT(1),
    LEVEL_2_ALL_NON_ASCII_PLUS_MARKUP_SIGNIFICANT(2),
    LEVEL_3_ALL_NON_ALPHANUMERIC(3),
    LEVEL_4_ALL_CHARACTERS(4);
    
    private final int escapeLevel;

    public static HtmlEscapeLevel forLevel(int i) {
        if (i == 0) {
            return LEVEL_0_ONLY_MARKUP_SIGNIFICANT_EXCEPT_APOS;
        }
        if (i == 1) {
            return LEVEL_1_ONLY_MARKUP_SIGNIFICANT;
        }
        if (i == 2) {
            return LEVEL_2_ALL_NON_ASCII_PLUS_MARKUP_SIGNIFICANT;
        }
        if (i == 3) {
            return LEVEL_3_ALL_NON_ALPHANUMERIC;
        }
        if (i == 4) {
            return LEVEL_4_ALL_CHARACTERS;
        }
        throw new IllegalArgumentException("No escape level enum constant defined for level: " + i);
    }

    private HtmlEscapeLevel(int i) {
        this.escapeLevel = i;
    }

    public int getEscapeLevel() {
        return this.escapeLevel;
    }
}
