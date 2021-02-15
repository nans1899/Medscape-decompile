package org.jaxen.expr;

abstract class DefaultMultiplicativeExpr extends DefaultArithExpr implements MultiplicativeExpr {
    DefaultMultiplicativeExpr(Expr expr, Expr expr2) {
        super(expr, expr2);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[(DefaultMultiplicativeExpr): ");
        stringBuffer.append(getLHS());
        stringBuffer.append(", ");
        stringBuffer.append(getRHS());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
