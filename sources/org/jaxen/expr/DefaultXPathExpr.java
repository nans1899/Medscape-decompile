package org.jaxen.expr;

import java.util.List;
import org.jaxen.Context;
import org.jaxen.JaxenException;

public class DefaultXPathExpr implements XPathExpr {
    private static final long serialVersionUID = 3007613096320896040L;
    private Expr rootExpr;

    public DefaultXPathExpr(Expr expr) {
        this.rootExpr = expr;
    }

    public Expr getRootExpr() {
        return this.rootExpr;
    }

    public void setRootExpr(Expr expr) {
        this.rootExpr = expr;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[(DefaultXPath): ");
        stringBuffer.append(getRootExpr());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public String getText() {
        return getRootExpr().getText();
    }

    public void simplify() {
        setRootExpr(getRootExpr().simplify());
    }

    public List asList(Context context) throws JaxenException {
        return DefaultExpr.convertToList(getRootExpr().evaluate(context));
    }
}
