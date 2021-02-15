package org.dom4j.dtd;

public class InternalEntityDecl {
    private String name;
    private String value;

    public InternalEntityDecl() {
    }

    public InternalEntityDecl(String str, String str2) {
        this.name = str;
        this.value = str2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("<!ENTITY ");
        if (this.name.startsWith("%")) {
            stringBuffer.append("% ");
            stringBuffer.append(this.name.substring(1));
        } else {
            stringBuffer.append(this.name);
        }
        stringBuffer.append(" \"");
        stringBuffer.append(escapeEntityValue(this.value));
        stringBuffer.append("\">");
        return stringBuffer.toString();
    }

    private String escapeEntityValue(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '\"') {
                stringBuffer.append("&#34;");
            } else if (charAt == '<') {
                stringBuffer.append("&#38;#60;");
            } else if (charAt == '>') {
                stringBuffer.append("&#62;");
            } else if (charAt == '&') {
                stringBuffer.append("&#38;#38;");
            } else if (charAt == '\'') {
                stringBuffer.append("&#39;");
            } else if (charAt < ' ') {
                StringBuffer stringBuffer2 = new StringBuffer("&#");
                stringBuffer2.append(charAt);
                stringBuffer2.append(";");
                stringBuffer.append(stringBuffer2.toString());
            } else {
                stringBuffer.append(charAt);
            }
        }
        return stringBuffer.toString();
    }
}
