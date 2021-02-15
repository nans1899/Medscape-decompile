package org.jaxen.expr;

import org.jaxen.function.NumberFunction;

class DefaultEqualsExpr extends DefaultEqualityExpr {
    private static final long serialVersionUID = -8327599812627931648L;

    public String getOperator() {
        return "=";
    }

    DefaultEqualsExpr(Expr expr, Expr expr2) {
        super(expr, expr2);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[(DefaultEqualsExpr): ");
        stringBuffer.append(getLHS());
        stringBuffer.append(", ");
        stringBuffer.append(getRHS());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    /* access modifiers changed from: protected */
    public boolean evaluateObjectObject(Object obj, Object obj2) {
        if (!eitherIsNumber(obj, obj2) || (!NumberFunction.isNaN((Double) obj) && !NumberFunction.isNaN((Double) obj2))) {
            return obj.equals(obj2);
        }
        return false;
    }
}
