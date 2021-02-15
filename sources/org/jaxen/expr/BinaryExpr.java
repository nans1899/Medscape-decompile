package org.jaxen.expr;

public interface BinaryExpr extends Expr {
    Expr getLHS();

    String getOperator();

    Expr getRHS();
}
