package org.jaxen.expr;

import org.jaxen.Context;
import org.jaxen.JaxenException;
import org.jaxen.function.NumberFunction;

class DefaultDivExpr extends DefaultMultiplicativeExpr {
    private static final long serialVersionUID = 6318739386201615441L;

    public String getOperator() {
        return "div";
    }

    DefaultDivExpr(Expr expr, Expr expr2) {
        super(expr, expr2);
    }

    public Object evaluate(Context context) throws JaxenException {
        return new Double(NumberFunction.evaluate(getLHS().evaluate(context), context.getNavigator()).doubleValue() / NumberFunction.evaluate(getRHS().evaluate(context), context.getNavigator()).doubleValue());
    }
}
