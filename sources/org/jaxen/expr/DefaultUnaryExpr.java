package org.jaxen.expr;

import org.jaxen.Context;
import org.jaxen.JaxenException;
import org.jaxen.function.NumberFunction;

class DefaultUnaryExpr extends DefaultExpr implements UnaryExpr {
    private static final long serialVersionUID = 2303714238683092334L;
    private Expr expr;

    DefaultUnaryExpr(Expr expr2) {
        this.expr = expr2;
    }

    public Expr getExpr() {
        return this.expr;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[(DefaultUnaryExpr): ");
        stringBuffer.append(getExpr());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public String getText() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("-(");
        stringBuffer.append(getExpr().getText());
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    public Expr simplify() {
        this.expr = this.expr.simplify();
        return this;
    }

    public Object evaluate(Context context) throws JaxenException {
        return new Double(NumberFunction.evaluate(getExpr().evaluate(context), context.getNavigator()).doubleValue() * -1.0d);
    }
}
