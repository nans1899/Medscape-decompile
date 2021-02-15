package org.jaxen.expr;

import java.util.Iterator;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.JaxenException;
import org.jaxen.Navigator;
import org.jaxen.function.BooleanFunction;
import org.jaxen.function.NumberFunction;
import org.jaxen.function.StringFunction;

abstract class DefaultEqualityExpr extends DefaultTruthExpr implements EqualityExpr {
    /* access modifiers changed from: protected */
    public abstract boolean evaluateObjectObject(Object obj, Object obj2);

    DefaultEqualityExpr(Expr expr, Expr expr2) {
        super(expr, expr2);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[(DefaultEqualityExpr): ");
        stringBuffer.append(getLHS());
        stringBuffer.append(", ");
        stringBuffer.append(getRHS());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public Object evaluate(Context context) throws JaxenException {
        Object evaluate = getLHS().evaluate(context);
        Object evaluate2 = getRHS().evaluate(context);
        if (evaluate == null || evaluate2 == null) {
            return Boolean.FALSE;
        }
        Navigator navigator = context.getNavigator();
        if (bothAreSets(evaluate, evaluate2)) {
            return evaluateSetSet((List) evaluate, (List) evaluate2, navigator);
        }
        if (isSet(evaluate) && isBoolean(evaluate2)) {
            return Boolean.valueOf(evaluateObjectObject(((List) evaluate).isEmpty() ? Boolean.FALSE : Boolean.TRUE, (Boolean) evaluate2, navigator));
        } else if (isBoolean(evaluate) && isSet(evaluate2)) {
            return Boolean.valueOf(evaluateObjectObject((Boolean) evaluate, ((List) evaluate2).isEmpty() ? Boolean.FALSE : Boolean.TRUE, navigator));
        } else if (!eitherIsSet(evaluate, evaluate2)) {
            return Boolean.valueOf(evaluateObjectObject(evaluate, evaluate2, navigator));
        } else {
            if (isSet(evaluate)) {
                return evaluateSetSet((List) evaluate, convertToList(evaluate2), navigator);
            }
            return evaluateSetSet(convertToList(evaluate), (List) evaluate2, navigator);
        }
    }

    private Boolean evaluateSetSet(List list, List list2, Navigator navigator) {
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
        if (eitherIsBoolean(obj, obj2)) {
            return evaluateObjectObject(BooleanFunction.evaluate(obj, navigator), BooleanFunction.evaluate(obj2, navigator));
        }
        if (eitherIsNumber(obj, obj2)) {
            return evaluateObjectObject(NumberFunction.evaluate(obj, navigator), NumberFunction.evaluate(obj2, navigator));
        }
        return evaluateObjectObject(StringFunction.evaluate(obj, navigator), StringFunction.evaluate(obj2, navigator));
    }
}
