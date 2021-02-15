package org.simpleframework.xml.stream;

public class HyphenStyle implements Style {
    private final Builder builder;
    private final Style style;

    public HyphenStyle() {
        HyphenBuilder hyphenBuilder = new HyphenBuilder();
        this.style = hyphenBuilder;
        this.builder = new Builder(hyphenBuilder);
    }

    public String getAttribute(String str) {
        return this.builder.getAttribute(str);
    }

    public void setAttribute(String str, String str2) {
        this.builder.setAttribute(str, str2);
    }

    public String getElement(String str) {
        return this.builder.getElement(str);
    }

    public void setElement(String str, String str2) {
        this.builder.setElement(str, str2);
    }
}
