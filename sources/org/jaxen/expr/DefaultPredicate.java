package org.jaxen.expr;

import org.jaxen.Context;
import org.jaxen.JaxenException;

class DefaultPredicate implements Predicate {
    private static final long serialVersionUID = -4140068594075364971L;
    private Expr expr;

    DefaultPredicate(Expr expr2) {
        setExpr(expr2);
    }

    public Expr getExpr() {
        return this.expr;
    }

    public void setExpr(Expr expr2) {
        this.expr = expr2;
    }

    public String getText() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        stringBuffer.append(getExpr().getText());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[(DefaultPredicate): ");
        stringBuffer.append(getExpr());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public void simplify() {
        setExpr(getExpr().simplify());
    }

    public Object evaluate(Context context) throws JaxenException {
        return getExpr().evaluate(context);
    }
}
