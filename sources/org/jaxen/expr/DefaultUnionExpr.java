package org.jaxen.expr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.JaxenException;
import org.jaxen.XPathSyntaxException;

public class DefaultUnionExpr extends DefaultBinaryExpr implements UnionExpr {
    private static final long serialVersionUID = 7629142718276852707L;

    public String getOperator() {
        return "|";
    }

    public DefaultUnionExpr(Expr expr, Expr expr2) {
        super(expr, expr2);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[(DefaultUnionExpr): ");
        stringBuffer.append(getLHS());
        stringBuffer.append(", ");
        stringBuffer.append(getRHS());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public Object evaluate(Context context) throws JaxenException {
        ArrayList arrayList = new ArrayList();
        try {
            List list = (List) getLHS().evaluate(context);
            HashSet hashSet = new HashSet();
            arrayList.addAll(list);
            hashSet.addAll(list);
            for (Object next : (List) getRHS().evaluate(context)) {
                if (!hashSet.contains(next)) {
                    arrayList.add(next);
                    hashSet.add(next);
                }
            }
            Collections.sort(arrayList, new NodeComparator(context.getNavigator()));
            return arrayList;
        } catch (ClassCastException unused) {
            throw new XPathSyntaxException(getText(), context.getPosition(), "Unions are only allowed over node-sets");
        }
    }
}
