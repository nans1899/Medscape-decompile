package org.jaxen.expr;

import org.jaxen.Context;
import org.jaxen.JaxenException;
import org.jaxen.Navigator;
import org.jaxen.function.BooleanFunction;

class DefaultOrExpr extends DefaultLogicalExpr {
    private static final long serialVersionUID = 4894552680753026730L;

    public String getOperator() {
        return "or";
    }

    DefaultOrExpr(Expr expr, Expr expr2) {
        super(expr, expr2);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[(DefaultOrExpr): ");
        stringBuffer.append(getLHS());
        stringBuffer.append(", ");
        stringBuffer.append(getRHS());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public Object evaluate(Context context) throws JaxenException {
        Navigator navigator = context.getNavigator();
        if (BooleanFunction.evaluate(getLHS().evaluate(context), navigator).booleanValue()) {
            return Boolean.TRUE;
        }
        if (BooleanFunction.evaluate(getRHS().evaluate(context), navigator).booleanValue()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
