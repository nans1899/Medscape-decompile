package org.jaxen.expr;

import org.jaxen.function.NumberFunction;

class DefaultNotEqualsExpr extends DefaultEqualityExpr {
    private static final long serialVersionUID = -8001267398136979152L;

    public String getOperator() {
        return "!=";
    }

    DefaultNotEqualsExpr(Expr expr, Expr expr2) {
        super(expr, expr2);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[(DefaultNotEqualsExpr): ");
        stringBuffer.append(getLHS());
        stringBuffer.append(", ");
        stringBuffer.append(getRHS());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    /* access modifiers changed from: protected */
    public boolean evaluateObjectObject(Object obj, Object obj2) {
        if (!eitherIsNumber(obj, obj2) || (!NumberFunction.isNaN((Double) obj) && !NumberFunction.isNaN((Double) obj2))) {
            return !obj.equals(obj2);
        }
        return true;
    }
}
