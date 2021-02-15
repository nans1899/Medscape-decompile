package org.jaxen.expr;

import java.io.Serializable;
import org.jaxen.Context;
import org.jaxen.JaxenException;

public interface Predicate extends Serializable {
    Object evaluate(Context context) throws JaxenException;

    Expr getExpr();

    String getText();

    void setExpr(Expr expr);

    void simplify();
}
