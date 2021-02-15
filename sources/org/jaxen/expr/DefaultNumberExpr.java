package org.jaxen.expr;

import org.jaxen.Context;

class DefaultNumberExpr extends DefaultExpr implements NumberExpr {
    private static final long serialVersionUID = -6021898973386269611L;
    private Double number;

    DefaultNumberExpr(Double d) {
        this.number = d;
    }

    public Number getNumber() {
        return this.number;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[(DefaultNumberExpr): ");
        stringBuffer.append(getNumber());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public String getText() {
        return getNumber().toString();
    }

    public Object evaluate(Context context) {
        return getNumber();
    }
}
