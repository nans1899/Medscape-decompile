package org.jaxen.expr;

public interface PathExpr extends Expr {
    Expr getFilterExpr();

    LocationPath getLocationPath();

    void setFilterExpr(Expr expr);
}
