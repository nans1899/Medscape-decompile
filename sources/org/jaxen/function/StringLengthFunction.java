package org.jaxen.function;

import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;

public class StringLengthFunction implements Function {
    public Object call(Context context, List list) throws FunctionCallException {
        if (list.size() == 0) {
            return evaluate(context.getNodeSet(), context.getNavigator());
        }
        if (list.size() == 1) {
            return evaluate(list.get(0), context.getNavigator());
        }
        throw new FunctionCallException("string-length() requires one argument.");
    }

    public static Double evaluate(Object obj, Navigator navigator) throws FunctionCallException {
        String evaluate = StringFunction.evaluate(obj, navigator);
        char[] charArray = evaluate.toCharArray();
        int i = 0;
        int i2 = 0;
        while (i < charArray.length) {
            char c = charArray[i];
            i2++;
            if (c >= 55296 && c <= 57343) {
                i++;
                try {
                    char c2 = charArray[i];
                    if (c2 < 56320 || c2 > 57343) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Bad surrogate pair in string ");
                        stringBuffer.append(evaluate);
                        throw new FunctionCallException(stringBuffer.toString());
                    }
                } catch (ArrayIndexOutOfBoundsException unused) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Bad surrogate pair in string ");
                    stringBuffer2.append(evaluate);
                    throw new FunctionCallException(stringBuffer2.toString());
                }
            }
            i++;
        }
        return new Double((double) i2);
    }
}
