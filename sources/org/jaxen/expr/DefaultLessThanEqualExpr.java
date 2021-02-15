package org.jaxen.expr;

class DefaultLessThanEqualExpr extends DefaultRelationalExpr {
    private static final long serialVersionUID = 7980276649555334242L;

    public String getOperator() {
        return "<=";
    }

    DefaultLessThanEqualExpr(Expr expr, Expr expr2) {
        super(expr, expr2);
    }

    /* access modifiers changed from: protected */
    public boolean evaluateDoubleDouble(Double d, Double d2) {
        return d.compareTo(d2) <= 0;
    }
}
