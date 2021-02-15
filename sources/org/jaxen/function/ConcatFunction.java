package org.jaxen.function;

import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;

public class ConcatFunction implements Function {
    public Object call(Context context, List list) throws FunctionCallException {
        if (list.size() >= 2) {
            return evaluate(list, context.getNavigator());
        }
        throw new FunctionCallException("concat() requires at least two arguments");
    }

    public static String evaluate(List list, Navigator navigator) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Object evaluate : list) {
            stringBuffer.append(StringFunction.evaluate(evaluate, navigator));
        }
        return stringBuffer.toString();
    }
}
