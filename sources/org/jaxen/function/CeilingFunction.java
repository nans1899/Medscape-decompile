package org.jaxen.function;

import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;

public class CeilingFunction implements Function {
    public Object call(Context context, List list) throws FunctionCallException {
        if (list.size() == 1) {
            return evaluate(list.get(0), context.getNavigator());
        }
        throw new FunctionCallException("ceiling() requires one argument.");
    }

    public static Double evaluate(Object obj, Navigator navigator) {
        return new Double(Math.ceil(NumberFunction.evaluate(obj, navigator).doubleValue()));
    }
}
