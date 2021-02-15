package org.jaxen.expr;

public interface VariableReferenceExpr extends Expr {
    String getPrefix();

    String getVariableName();
}
