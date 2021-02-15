package org.jaxen.expr;

abstract class DefaultAdditiveExpr extends DefaultArithExpr implements AdditiveExpr {
    DefaultAdditiveExpr(Expr expr, Expr expr2) {
        super(expr, expr2);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[(");
        stringBuffer.append(getClass().getName());
        stringBuffer.append("): ");
        stringBuffer.append(getLHS());
        stringBuffer.append(", ");
        stringBuffer.append(getRHS());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
