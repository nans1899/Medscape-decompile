package org.jaxen.function.ext;

import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;
import org.jaxen.function.StringFunction;

public class EndsWithFunction implements Function {
    public Object call(Context context, List list) throws FunctionCallException {
        if (list.size() == 2) {
            return evaluate(list.get(0), list.get(1), context.getNavigator());
        }
        throw new FunctionCallException("ends-with() requires two arguments.");
    }

    public static Boolean evaluate(Object obj, Object obj2, Navigator navigator) {
        return StringFunction.evaluate(obj, navigator).endsWith(StringFunction.evaluate(obj2, navigator)) ? Boolean.TRUE : Boolean.FALSE;
    }
}
