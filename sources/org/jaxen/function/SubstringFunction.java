package org.jaxen.function;

import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;

public class SubstringFunction implements Function {
    public Object call(Context context, List list) throws FunctionCallException {
        int intValue;
        int i;
        int size = list.size();
        if (size < 2 || size > 3) {
            throw new FunctionCallException("substring() requires two or three arguments.");
        }
        Navigator navigator = context.getNavigator();
        int i2 = 0;
        String evaluate = StringFunction.evaluate(list.get(0), navigator);
        if (evaluate == null || (intValue = StringLengthFunction.evaluate(list.get(0), navigator).intValue()) == 0) {
            return "";
        }
        Double evaluate2 = NumberFunction.evaluate(list.get(1), navigator);
        if (evaluate2.isNaN()) {
            return "";
        }
        int intValue2 = RoundFunction.evaluate(evaluate2, navigator).intValue() - 1;
        if (size == 3) {
            Double evaluate3 = NumberFunction.evaluate(list.get(2), navigator);
            i = !evaluate3.isNaN() ? RoundFunction.evaluate(evaluate3, navigator).intValue() : 0;
        } else {
            i = intValue;
        }
        if (i < 0) {
            return "";
        }
        int i3 = i + intValue2;
        if (size == 2) {
            i3 = intValue;
        }
        if (intValue2 >= 0) {
            if (intValue2 > intValue) {
                return "";
            }
            i2 = intValue2;
        }
        if (i3 > intValue) {
            i3 = intValue;
        } else if (i3 < i2) {
            return "";
        }
        if (intValue == evaluate.length()) {
            return evaluate.substring(i2, i3);
        }
        return unicodeSubstring(evaluate, i2, i3);
    }

    private static String unicodeSubstring(String str, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer(str.length());
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            char charAt = str.charAt(i3);
            if (i4 >= i) {
                stringBuffer.append(charAt);
            }
            if (charAt >= 55296) {
                i3++;
                if (i4 >= i) {
                    stringBuffer.append(str.charAt(i3));
                }
            }
            i3++;
        }
        return stringBuffer.toString();
    }
}
