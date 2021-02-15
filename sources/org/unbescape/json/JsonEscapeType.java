package org.unbescape.json;

public enum JsonEscapeType {
    SINGLE_ESCAPE_CHARS_DEFAULT_TO_UHEXA(true),
    UHEXA(false);
    
    private final boolean useSECs;

    private JsonEscapeType(boolean z) {
        this.useSECs = z;
    }

    /* access modifiers changed from: package-private */
    public boolean getUseSECs() {
        return this.useSECs;
    }
}
