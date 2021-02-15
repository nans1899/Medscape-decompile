package org.jaxen.function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;

public class IdFunction implements Function {
    public Object call(Context context, List list) throws FunctionCallException {
        if (list.size() == 1) {
            return evaluate(context.getNodeSet(), list.get(0), context.getNavigator());
        }
        throw new FunctionCallException("id() requires one argument");
    }

    public static List evaluate(List list, Object obj, Navigator navigator) {
        if (list.size() == 0) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList();
        Object obj2 = list.get(0);
        if (obj instanceof List) {
            for (Object evaluate : (List) obj) {
                arrayList.addAll(evaluate(list, StringFunction.evaluate(evaluate, navigator), navigator));
            }
        } else {
            StringTokenizer stringTokenizer = new StringTokenizer(StringFunction.evaluate(obj, navigator), " \t\n\r");
            while (stringTokenizer.hasMoreTokens()) {
                Object elementById = navigator.getElementById(obj2, stringTokenizer.nextToken());
                if (elementById != null) {
                    arrayList.add(elementById);
                }
            }
        }
        return arrayList;
    }
}
