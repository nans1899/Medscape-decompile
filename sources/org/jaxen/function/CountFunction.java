package org.jaxen.function;

import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;

public class CountFunction implements Function {
    public Object call(Context context, List list) throws FunctionCallException {
        if (list.size() == 1) {
            return evaluate(list.get(0));
        }
        throw new FunctionCallException("count() requires one argument.");
    }

    public static Double evaluate(Object obj) throws FunctionCallException {
        if (obj instanceof List) {
            return new Double((double) ((List) obj).size());
        }
        throw new FunctionCallException("count() function can only be used for node-sets");
    }
}
