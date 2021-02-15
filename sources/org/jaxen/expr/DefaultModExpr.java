package org.jaxen.expr;

import org.jaxen.Context;
import org.jaxen.JaxenException;
import org.jaxen.function.NumberFunction;

class DefaultModExpr extends DefaultMultiplicativeExpr {
    private static final long serialVersionUID = -5554964716492040687L;

    public String getOperator() {
        return "mod";
    }

    DefaultModExpr(Expr expr, Expr expr2) {
        super(expr, expr2);
    }

    public Object evaluate(Context context) throws JaxenException {
        return new Double(NumberFunction.evaluate(getLHS().evaluate(context), context.getNavigator()).doubleValue() % NumberFunction.evaluate(getRHS().evaluate(context), context.getNavigator()).doubleValue());
    }
}
