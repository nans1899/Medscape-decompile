package org.jaxen.function;

import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;

public class ContainsFunction implements Function {
    public Object call(Context context, List list) throws FunctionCallException {
        if (list.size() == 2) {
            return evaluate(list.get(0), list.get(1), context.getNavigator());
        }
        throw new FunctionCallException("contains() requires two arguments.");
    }

    public static Boolean evaluate(Object obj, Object obj2, Navigator navigator) {
        return StringFunction.evaluate(obj, navigator).indexOf(StringFunction.evaluate(obj2, navigator)) >= 0 ? Boolean.TRUE : Boolean.FALSE;
    }
}
