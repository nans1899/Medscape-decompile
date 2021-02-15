package org.jaxen.expr;

public class DefaultRelativeLocationPath extends DefaultLocationPath {
    private static final long serialVersionUID = -1006862529366150615L;

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[(DefaultRelativeLocationPath): ");
        stringBuffer.append(super.toString());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
