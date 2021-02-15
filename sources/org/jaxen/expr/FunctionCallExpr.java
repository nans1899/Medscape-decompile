package org.jaxen.expr;

import java.util.List;

public interface FunctionCallExpr extends Expr {
    void addParameter(Expr expr);

    String getFunctionName();

    List getParameters();

    String getPrefix();
}
