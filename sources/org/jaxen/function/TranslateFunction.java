package org.jaxen.function;

import java.util.HashMap;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;

public class TranslateFunction implements Function {
    private static boolean isHighSurrogate(char c) {
        return c >= 55296 && c <= 56319;
    }

    private static boolean isLowSurrogate(char c) {
        return c >= 56320 && c <= 57343;
    }

    public Object call(Context context, List list) throws FunctionCallException {
        if (list.size() == 3) {
            return evaluate(list.get(0), list.get(1), list.get(2), context.getNavigator());
        }
        throw new FunctionCallException("translate() requires three arguments.");
    }

    public static String evaluate(Object obj, Object obj2, Object obj3, Navigator navigator) throws FunctionCallException {
        String evaluate = StringFunction.evaluate(obj, navigator);
        String evaluate2 = StringFunction.evaluate(obj2, navigator);
        String evaluate3 = StringFunction.evaluate(obj3, navigator);
        HashMap hashMap = new HashMap();
        String[] unicodeCharacters = toUnicodeCharacters(evaluate2);
        String[] unicodeCharacters2 = toUnicodeCharacters(evaluate3);
        int length = unicodeCharacters.length;
        int length2 = unicodeCharacters2.length;
        for (int i = 0; i < length; i++) {
            String str = unicodeCharacters[i];
            if (!hashMap.containsKey(str)) {
                if (i < length2) {
                    hashMap.put(str, unicodeCharacters2[i]);
                } else {
                    hashMap.put(str, (Object) null);
                }
            }
        }
        StringBuffer stringBuffer = new StringBuffer(evaluate.length());
        for (String str2 : toUnicodeCharacters(evaluate)) {
            if (hashMap.containsKey(str2)) {
                String str3 = (String) hashMap.get(str2);
                if (str3 != null) {
                    stringBuffer.append(str3);
                }
            } else {
                stringBuffer.append(str2);
            }
        }
        return stringBuffer.toString();
    }

    private static String[] toUnicodeCharacters(String str) throws FunctionCallException {
        int length = str.length();
        String[] strArr = new String[length];
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (isHighSurrogate(charAt)) {
                i++;
                try {
                    char charAt2 = str.charAt(i);
                    if (isLowSurrogate(charAt2)) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append(charAt);
                        stringBuffer.append("");
                        stringBuffer.append(charAt2);
                        strArr[i2] = stringBuffer.toString().intern();
                    } else {
                        throw new FunctionCallException("Mismatched surrogate pair in translate function");
                    }
                } catch (StringIndexOutOfBoundsException unused) {
                    throw new FunctionCallException("High surrogate without low surrogate at end of string passed to translate function");
                }
            } else {
                strArr[i2] = String.valueOf(charAt).intern();
            }
            i2++;
            i++;
        }
        if (i2 == length) {
            return strArr;
        }
        String[] strArr2 = new String[i2];
        System.arraycopy(strArr, 0, strArr2, 0, i2);
        return strArr2;
    }
}
