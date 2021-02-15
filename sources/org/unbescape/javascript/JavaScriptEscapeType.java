package org.unbescape.javascript;

public enum JavaScriptEscapeType {
    SINGLE_ESCAPE_CHARS_DEFAULT_TO_XHEXA_AND_UHEXA(true, true),
    SINGLE_ESCAPE_CHARS_DEFAULT_TO_UHEXA(true, false),
    XHEXA_DEFAULT_TO_UHEXA(false, true),
    UHEXA(false, false);
    
    private final boolean useSECs;
    private final boolean useXHexa;

    private JavaScriptEscapeType(boolean z, boolean z2) {
        this.useSECs = z;
        this.useXHexa = z2;
    }

    /* access modifiers changed from: package-private */
    public boolean getUseSECs() {
        return this.useSECs;
    }

    /* access modifiers changed from: package-private */
    public boolean getUseXHexa() {
        return this.useXHexa;
    }
}
