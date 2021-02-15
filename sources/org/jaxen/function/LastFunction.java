package org.jaxen.function;

import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;

public class LastFunction implements Function {
    public Object call(Context context, List list) throws FunctionCallException {
        if (list.size() == 0) {
            return evaluate(context);
        }
        throw new FunctionCallException("last() requires no arguments.");
    }

    public static Double evaluate(Context context) {
        return new Double((double) context.getSize());
    }
}
