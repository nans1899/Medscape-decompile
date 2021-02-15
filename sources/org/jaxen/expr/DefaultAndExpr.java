package org.jaxen.expr;

import org.jaxen.Context;
import org.jaxen.JaxenException;
import org.jaxen.Navigator;
import org.jaxen.function.BooleanFunction;

class DefaultAndExpr extends DefaultLogicalExpr {
    private static final long serialVersionUID = -5237984010263103742L;

    public String getOperator() {
        return "and";
    }

    DefaultAndExpr(Expr expr, Expr expr2) {
        super(expr, expr2);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[(DefaultAndExpr): ");
        stringBuffer.append(getLHS());
        stringBuffer.append(", ");
        stringBuffer.append(getRHS());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public Object evaluate(Context context) throws JaxenException {
        Navigator navigator = context.getNavigator();
        if (!BooleanFunction.evaluate(getLHS().evaluate(context), navigator).booleanValue()) {
            return Boolean.FALSE;
        }
        if (!BooleanFunction.evaluate(getRHS().evaluate(context), navigator).booleanValue()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
