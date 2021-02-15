package org.jaxen.saxpath.base;

class Token {
    private String parseText;
    private int tokenBegin;
    private int tokenEnd;
    private int tokenType;

    Token(int i, String str, int i2, int i3) {
        setTokenType(i);
        setParseText(str);
        setTokenBegin(i2);
        setTokenEnd(i3);
    }

    private void setTokenType(int i) {
        this.tokenType = i;
    }

    /* access modifiers changed from: package-private */
    public int getTokenType() {
        return this.tokenType;
    }

    private void setParseText(String str) {
        this.parseText = str;
    }

    /* access modifiers changed from: package-private */
    public String getTokenText() {
        return this.parseText.substring(getTokenBegin(), getTokenEnd());
    }

    private void setTokenBegin(int i) {
        this.tokenBegin = i;
    }

    /* access modifiers changed from: package-private */
    public int getTokenBegin() {
        return this.tokenBegin;
    }

    private void setTokenEnd(int i) {
        this.tokenEnd = i;
    }

    /* access modifiers changed from: package-private */
    public int getTokenEnd() {
        return this.tokenEnd;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[ (");
        stringBuffer.append(this.tokenType);
        stringBuffer.append(") (");
        stringBuffer.append(getTokenText());
        stringBuffer.append(")");
        return stringBuffer.toString();
    }
}
