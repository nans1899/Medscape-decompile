package org.jaxen;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import org.apache.commons.io.IOUtils;

public class XPathSyntaxException extends JaxenException {
    private static final long serialVersionUID = 1980601567207604059L;
    private int position;
    private String xpath;

    public XPathSyntaxException(org.jaxen.saxpath.XPathSyntaxException xPathSyntaxException) {
        super((Throwable) xPathSyntaxException);
        this.xpath = xPathSyntaxException.getXPath();
        this.position = xPathSyntaxException.getPosition();
    }

    public XPathSyntaxException(String str, int i, String str2) {
        super(str2);
        this.xpath = str;
        this.position = i;
    }

    public int getPosition() {
        return this.position;
    }

    public String getXPath() {
        return this.xpath;
    }

    public String getPositionMarker() {
        StringBuffer stringBuffer = new StringBuffer();
        int position2 = getPosition();
        for (int i = 0; i < position2; i++) {
            stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        }
        stringBuffer.append("^");
        return stringBuffer.toString();
    }

    public String getMultilineMessage() {
        StringBuffer stringBuffer = new StringBuffer(getMessage());
        stringBuffer.append(IOUtils.LINE_SEPARATOR_UNIX);
        stringBuffer.append(getXPath());
        stringBuffer.append(IOUtils.LINE_SEPARATOR_UNIX);
        stringBuffer.append(getPositionMarker());
        return stringBuffer.toString();
    }
}
