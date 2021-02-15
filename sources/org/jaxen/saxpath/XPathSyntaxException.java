package org.jaxen.saxpath;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;

public class XPathSyntaxException extends SAXPathException {
    private static final String lineSeparator = System.getProperty("line.separator");
    private static final long serialVersionUID = 3567675610742422397L;
    private int position;
    private String xpath;

    public XPathSyntaxException(String str, int i, String str2) {
        super(str2);
        this.position = i;
        this.xpath = str;
    }

    public int getPosition() {
        return this.position;
    }

    public String getXPath() {
        return this.xpath;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getClass());
        stringBuffer.append(": ");
        stringBuffer.append(getXPath());
        stringBuffer.append(": ");
        stringBuffer.append(getPosition());
        stringBuffer.append(": ");
        stringBuffer.append(getMessage());
        return stringBuffer.toString();
    }

    private String getPositionMarker() {
        int position2 = getPosition();
        StringBuffer stringBuffer = new StringBuffer(position2 + 1);
        for (int i = 0; i < position2; i++) {
            stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        }
        stringBuffer.append("^");
        return stringBuffer.toString();
    }

    public String getMultilineMessage() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getMessage());
        stringBuffer.append(lineSeparator);
        stringBuffer.append(getXPath());
        stringBuffer.append(lineSeparator);
        stringBuffer.append(getPositionMarker());
        return stringBuffer.toString();
    }
}
