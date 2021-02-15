package org.jaxen.expr;

import org.jaxen.Context;
import org.jaxen.JaxenException;
import org.jaxen.function.NumberFunction;

class DefaultMinusExpr extends DefaultAdditiveExpr {
    private static final long serialVersionUID = 6468563688098527800L;

    public String getOperator() {
        return "-";
    }

    DefaultMinusExpr(Expr expr, Expr expr2) {
        super(expr, expr2);
    }

    public Object evaluate(Context context) throws JaxenException {
        return new Double(NumberFunction.evaluate(getLHS().evaluate(context), context.getNavigator()).doubleValue() - NumberFunction.evaluate(getRHS().evaluate(context), context.getNavigator()).doubleValue());
    }
}
