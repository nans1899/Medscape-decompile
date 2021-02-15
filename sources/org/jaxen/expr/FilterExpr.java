package org.jaxen.expr;

import org.jaxen.Context;
import org.jaxen.JaxenException;

public interface FilterExpr extends Expr, Predicated {
    boolean asBoolean(Context context) throws JaxenException;

    Expr getExpr();
}
