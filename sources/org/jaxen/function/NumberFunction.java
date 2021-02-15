package org.jaxen.function;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Iterator;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;

public class NumberFunction implements Function {
    private static final Double NaN = new Double(Double.NaN);

    public Object call(Context context, List list) throws FunctionCallException {
        if (list.size() == 1) {
            return evaluate(list.get(0), context.getNavigator());
        }
        if (list.size() == 0) {
            return evaluate(context.getNodeSet(), context.getNavigator());
        }
        throw new FunctionCallException("number() takes at most one argument.");
    }

    public static Double evaluate(Object obj, Navigator navigator) {
        if (obj instanceof Double) {
            return (Double) obj;
        }
        if (obj instanceof String) {
            try {
                return new Double((String) obj);
            } catch (NumberFormatException unused) {
                return NaN;
            }
        } else if ((obj instanceof List) || (obj instanceof Iterator)) {
            return evaluate(StringFunction.evaluate(obj, navigator), navigator);
        } else {
            if (navigator.isElement(obj) || navigator.isAttribute(obj) || navigator.isText(obj) || navigator.isComment(obj) || navigator.isProcessingInstruction(obj) || navigator.isDocument(obj) || navigator.isNamespace(obj)) {
                return evaluate(StringFunction.evaluate(obj, navigator), navigator);
            }
            if (!(obj instanceof Boolean)) {
                return NaN;
            }
            if (obj == Boolean.TRUE) {
                return new Double(1.0d);
            }
            return new Double(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        }
    }

    public static boolean isNaN(double d) {
        return Double.isNaN(d);
    }

    public static boolean isNaN(Double d) {
        return d.equals(NaN);
    }
}
