package org.jaxen.expr;

import com.webmd.wbmdcmepulse.models.articles.HtmlObject;

class DefaultLessThanExpr extends DefaultRelationalExpr {
    private static final long serialVersionUID = 8423816025305001283L;

    public String getOperator() {
        return HtmlObject.HtmlMarkUp.OPEN_BRACKER;
    }

    DefaultLessThanExpr(Expr expr, Expr expr2) {
        super(expr, expr2);
    }

    /* access modifiers changed from: protected */
    public boolean evaluateDoubleDouble(Double d, Double d2) {
        return d.compareTo(d2) < 0;
    }
}
