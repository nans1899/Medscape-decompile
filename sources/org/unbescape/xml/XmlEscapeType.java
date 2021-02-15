package org.unbescape.xml;

public enum XmlEscapeType {
    CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_DECIMAL(true, false),
    CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA(true, true),
    DECIMAL_REFERENCES(false, false),
    HEXADECIMAL_REFERENCES(false, true);
    
    private final boolean useCERs;
    private final boolean useHexa;

    private XmlEscapeType(boolean z, boolean z2) {
        this.useCERs = z;
        this.useHexa = z2;
    }

    /* access modifiers changed from: package-private */
    public boolean getUseCERs() {
        return this.useCERs;
    }

    /* access modifiers changed from: package-private */
    public boolean getUseHexa() {
        return this.useHexa;
    }
}
