package org.jaxen.expr;

import com.webmd.wbmdcmepulse.models.articles.HtmlObject;

class DefaultGreaterThanExpr extends DefaultRelationalExpr {
    private static final long serialVersionUID = 6379252220540222867L;

    public String getOperator() {
        return HtmlObject.HtmlMarkUp.CLOSE_BRACKER;
    }

    DefaultGreaterThanExpr(Expr expr, Expr expr2) {
        super(expr, expr2);
    }

    /* access modifiers changed from: protected */
    public boolean evaluateDoubleDouble(Double d, Double d2) {
        return d.compareTo(d2) > 0;
    }
}
