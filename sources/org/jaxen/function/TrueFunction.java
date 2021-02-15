package org.jaxen.function;

import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;

public class TrueFunction implements Function {
    public Object call(Context context, List list) throws FunctionCallException {
        if (list.size() == 0) {
            return evaluate();
        }
        throw new FunctionCallException("true() requires no arguments.");
    }

    public static Boolean evaluate() {
        return Boolean.TRUE;
    }
}
