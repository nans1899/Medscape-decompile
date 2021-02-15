package org.jaxen.expr;

import java.io.Serializable;
import org.jaxen.Context;
import org.jaxen.JaxenException;

public interface Expr extends Serializable {
    Object evaluate(Context context) throws JaxenException;

    String getText();

    Expr simplify();
}
