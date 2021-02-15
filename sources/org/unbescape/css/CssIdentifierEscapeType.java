package org.unbescape.css;

public enum CssIdentifierEscapeType {
    BACKSLASH_ESCAPES_DEFAULT_TO_COMPACT_HEXA(true, true),
    BACKSLASH_ESCAPES_DEFAULT_TO_SIX_DIGIT_HEXA(true, false),
    COMPACT_HEXA(false, true),
    SIX_DIGIT_HEXA(false, false);
    
    private final boolean useBackslashEscapes;
    private final boolean useCompactHexa;

    private CssIdentifierEscapeType(boolean z, boolean z2) {
        this.useBackslashEscapes = z;
        this.useCompactHexa = z2;
    }

    public boolean getUseBackslashEscapes() {
        return this.useBackslashEscapes;
    }

    public boolean getUseCompactHexa() {
        return this.useCompactHexa;
    }
}
