package org.unbescape.html;

public enum HtmlEscapeType {
    HTML4_NAMED_REFERENCES_DEFAULT_TO_DECIMAL(true, false, false),
    HTML4_NAMED_REFERENCES_DEFAULT_TO_HEXA(true, true, false),
    HTML5_NAMED_REFERENCES_DEFAULT_TO_DECIMAL(true, false, true),
    HTML5_NAMED_REFERENCES_DEFAULT_TO_HEXA(true, true, true),
    DECIMAL_REFERENCES(false, false, false),
    HEXADECIMAL_REFERENCES(false, true, false);
    
    private final boolean useHexa;
    private final boolean useHtml5;
    private final boolean useNCRs;

    private HtmlEscapeType(boolean z, boolean z2, boolean z3) {
        this.useNCRs = z;
        this.useHexa = z2;
        this.useHtml5 = z3;
    }

    /* access modifiers changed from: package-private */
    public boolean getUseNCRs() {
        return this.useNCRs;
    }

    /* access modifiers changed from: package-private */
    public boolean getUseHexa() {
        return this.useHexa;
    }

    /* access modifiers changed from: package-private */
    public boolean getUseHtml5() {
        return this.useHtml5;
    }
}
