package org.simpleframework.xml.stream;

public class Format {
    private final int indent;
    private final String prolog;
    private final Style style;
    private final Verbosity verbosity;

    public Format() {
        this(3);
    }

    public Format(int i) {
        this(i, (String) null, (Style) new IdentityStyle());
    }

    public Format(String str) {
        this(3, str);
    }

    public Format(int i, String str) {
        this(i, str, (Style) new IdentityStyle());
    }

    public Format(Verbosity verbosity2) {
        this(3, verbosity2);
    }

    public Format(int i, Verbosity verbosity2) {
        this(i, (Style) new IdentityStyle(), verbosity2);
    }

    public Format(Style style2) {
        this(3, style2);
    }

    public Format(Style style2, Verbosity verbosity2) {
        this(3, style2, verbosity2);
    }

    public Format(int i, Style style2) {
        this(i, (String) null, style2);
    }

    public Format(int i, Style style2, Verbosity verbosity2) {
        this(i, (String) null, style2, verbosity2);
    }

    public Format(int i, String str, Style style2) {
        this(i, str, style2, Verbosity.HIGH);
    }

    public Format(int i, String str, Style style2, Verbosity verbosity2) {
        this.verbosity = verbosity2;
        this.prolog = str;
        this.indent = i;
        this.style = style2;
    }

    public int getIndent() {
        return this.indent;
    }

    public String getProlog() {
        return this.prolog;
    }

    public Style getStyle() {
        return this.style;
    }

    public Verbosity getVerbosity() {
        return this.verbosity;
    }
}
