package org.jaxen.expr;

class DefaultGreaterThanEqualExpr extends DefaultRelationalExpr {
    private static final long serialVersionUID = -7848747981787197470L;

    public String getOperator() {
        return ">=";
    }

    DefaultGreaterThanEqualExpr(Expr expr, Expr expr2) {
        super(expr, expr2);
    }

    /* access modifiers changed from: protected */
    public boolean evaluateDoubleDouble(Double d, Double d2) {
        return d.compareTo(d2) >= 0;
    }
}
