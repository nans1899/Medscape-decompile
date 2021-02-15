package org.jaxen.expr;

abstract class DefaultLogicalExpr extends DefaultTruthExpr implements LogicalExpr {
    DefaultLogicalExpr(Expr expr, Expr expr2) {
        super(expr, expr2);
    }
}
