package org.jaxen.expr;

import java.util.Iterator;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.JaxenException;
import org.jaxen.Navigator;
import org.jaxen.function.NumberFunction;

abstract class DefaultRelationalExpr extends DefaultTruthExpr implements RelationalExpr {
    /* access modifiers changed from: protected */
    public abstract boolean evaluateDoubleDouble(Double d, Double d2);

    DefaultRelationalExpr(Expr expr, Expr expr2) {
        super(expr, expr2);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[(DefaultRelationalExpr): ");
        stringBuffer.append(getLHS());
        stringBuffer.append(", ");
        stringBuffer.append(getRHS());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public Object evaluate(Context context) throws JaxenException {
        Object evaluate = getLHS().evaluate(context);
        Object evaluate2 = getRHS().evaluate(context);
        Navigator navigator = context.getNavigator();
        if (bothAreSets(evaluate, evaluate2)) {
            return evaluateSetSet((List) evaluate, (List) evaluate2, navigator);
        }
        if (!eitherIsSet(evaluate, evaluate2)) {
            return evaluateObjectObject(evaluate, evaluate2, navigator) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (isSet(evaluate)) {
            return evaluateSetSet((List) evaluate, convertToList(evaluate2), navigator);
        }
        return evaluateSetSet(convertToList(evaluate), (List) evaluate2, navigator);
    }

    private Object evaluateSetSet(List list, List list2, Navigator navigator) {
        if (setIsEmpty(list) || setIsEmpty(list2)) {
            return Boolean.FALSE;
        }
        for (Object next : list) {
            Iterator it = list2.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (evaluateObjectObject(next, it.next(), navigator)) {
                        return Boolean.TRUE;
                    }
                }
            }
        }
        return Boolean.FALSE;
    }

    private boolean evaluateObjectObject(Object obj, Object obj2, Navigator navigator) {
        if (!(obj == null || obj2 == null)) {
            Double evaluate = NumberFunction.evaluate(obj, navigator);
            Double evaluate2 = NumberFunction.evaluate(obj2, navigator);
            if (!NumberFunction.isNaN(evaluate) && !NumberFunction.isNaN(evaluate2)) {
                return evaluateDoubleDouble(evaluate, evaluate2);
            }
        }
        return false;
    }
}
