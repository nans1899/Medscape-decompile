package org.jaxen.expr;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;

abstract class DefaultBinaryExpr extends DefaultExpr implements BinaryExpr {
    private Expr lhs;
    private Expr rhs;

    public abstract String getOperator();

    DefaultBinaryExpr(Expr expr, Expr expr2) {
        this.lhs = expr;
        this.rhs = expr2;
    }

    public Expr getLHS() {
        return this.lhs;
    }

    public Expr getRHS() {
        return this.rhs;
    }

    public void setLHS(Expr expr) {
        this.lhs = expr;
    }

    public void setRHS(Expr expr) {
        this.rhs = expr;
    }

    public String getText() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("(");
        stringBuffer.append(getLHS().getText());
        stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        stringBuffer.append(getOperator());
        stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        stringBuffer.append(getRHS().getText());
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        stringBuffer.append(getClass().getName());
        stringBuffer.append(": ");
        stringBuffer.append(getLHS());
        stringBuffer.append(", ");
        stringBuffer.append(getRHS());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public Expr simplify() {
        setLHS(getLHS().simplify());
        setRHS(getRHS().simplify());
        return this;
    }
}
