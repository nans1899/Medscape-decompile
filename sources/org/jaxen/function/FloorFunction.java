package org.jaxen.function;

import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;

public class FloorFunction implements Function {
    public Object call(Context context, List list) throws FunctionCallException {
        if (list.size() == 1) {
            return evaluate(list.get(0), context.getNavigator());
        }
        throw new FunctionCallException("floor() requires one argument.");
    }

    public static Double evaluate(Object obj, Navigator navigator) {
        return new Double(Math.floor(NumberFunction.evaluate(obj, navigator).doubleValue()));
    }
}
