package org.jaxen.function;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;

public class SumFunction implements Function {
    public Object call(Context context, List list) throws FunctionCallException {
        if (list.size() == 1) {
            return evaluate(list.get(0), context.getNavigator());
        }
        throw new FunctionCallException("sum() requires one argument.");
    }

    public static Double evaluate(Object obj, Navigator navigator) throws FunctionCallException {
        if (obj instanceof List) {
            double d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            for (Object evaluate : (List) obj) {
                d += NumberFunction.evaluate(evaluate, navigator).doubleValue();
            }
            return new Double(d);
        }
        throw new FunctionCallException("The argument to the sum function must be a node-set");
    }
}
