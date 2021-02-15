package org.jaxen.expr;

import org.jaxen.Context;

class DefaultLiteralExpr extends DefaultExpr implements LiteralExpr {
    private static final long serialVersionUID = -953829179036273338L;
    private String literal;

    DefaultLiteralExpr(String str) {
        this.literal = str;
    }

    public String getLiteral() {
        return this.literal;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[(DefaultLiteralExpr): ");
        stringBuffer.append(getLiteral());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public String getText() {
        if (this.literal.indexOf(34) == -1) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("\"");
            stringBuffer.append(getLiteral());
            stringBuffer.append("\"");
            return stringBuffer.toString();
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("'");
        stringBuffer2.append(getLiteral());
        stringBuffer2.append("'");
        return stringBuffer2.toString();
    }

    public Object evaluate(Context context) {
        return getLiteral();
    }
}
