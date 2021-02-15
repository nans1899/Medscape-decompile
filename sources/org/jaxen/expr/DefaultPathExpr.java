package org.jaxen.expr;

import org.jaxen.Context;
import org.jaxen.JaxenException;

class DefaultPathExpr extends DefaultExpr implements PathExpr {
    private static final long serialVersionUID = -6593934674727004281L;
    private Expr filterExpr;
    private LocationPath locationPath;

    DefaultPathExpr(Expr expr, LocationPath locationPath2) {
        this.filterExpr = expr;
        this.locationPath = locationPath2;
    }

    public Expr getFilterExpr() {
        return this.filterExpr;
    }

    public void setFilterExpr(Expr expr) {
        this.filterExpr = expr;
    }

    public LocationPath getLocationPath() {
        return this.locationPath;
    }

    public String toString() {
        if (getLocationPath() != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("[(DefaultPathExpr): ");
            stringBuffer.append(getFilterExpr());
            stringBuffer.append(", ");
            stringBuffer.append(getLocationPath());
            stringBuffer.append("]");
            return stringBuffer.toString();
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("[(DefaultPathExpr): ");
        stringBuffer2.append(getFilterExpr());
        stringBuffer2.append("]");
        return stringBuffer2.toString();
    }

    public String getText() {
        StringBuffer stringBuffer = new StringBuffer();
        if (getFilterExpr() != null) {
            stringBuffer.append(getFilterExpr().getText());
        }
        if (getLocationPath() != null) {
            if (!getLocationPath().getSteps().isEmpty()) {
                stringBuffer.append("/");
            }
            stringBuffer.append(getLocationPath().getText());
        }
        return stringBuffer.toString();
    }

    public Expr simplify() {
        if (getFilterExpr() != null) {
            setFilterExpr(getFilterExpr().simplify());
        }
        if (getLocationPath() != null) {
            getLocationPath().simplify();
        }
        if (getFilterExpr() == null && getLocationPath() == null) {
            return null;
        }
        if (getLocationPath() == null) {
            return getFilterExpr();
        }
        return getFilterExpr() == null ? getLocationPath() : this;
    }

    public Object evaluate(Context context) throws JaxenException {
        Context context2;
        Object obj = null;
        if (getFilterExpr() != null) {
            obj = getFilterExpr().evaluate(context);
            context2 = new Context(context.getContextSupport());
            context2.setNodeSet(convertToList(obj));
        } else {
            context2 = null;
        }
        return getLocationPath() != null ? getLocationPath().evaluate(context2) : obj;
    }
}
