package org.jaxen.function;

import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;

public class SubstringAfterFunction implements Function {
    public Object call(Context context, List list) throws FunctionCallException {
        if (list.size() == 2) {
            return evaluate(list.get(0), list.get(1), context.getNavigator());
        }
        throw new FunctionCallException("substring-after() requires two arguments.");
    }

    public static String evaluate(Object obj, Object obj2, Navigator navigator) {
        String evaluate = StringFunction.evaluate(obj, navigator);
        String evaluate2 = StringFunction.evaluate(obj2, navigator);
        int indexOf = evaluate.indexOf(evaluate2);
        if (indexOf < 0) {
            return "";
        }
        return evaluate.substring(indexOf + evaluate2.length());
    }
}
