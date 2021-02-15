package org.mockito.internal.invocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.mockito.ArgumentMatcher;
import org.mockito.internal.matchers.ArrayEquals;
import org.mockito.internal.matchers.Equals;

public class ArgumentsProcessor {
    public static Object[] expandArgs(MockitoMethod mockitoMethod, Object[] objArr) {
        int length = mockitoMethod.getParameterTypes().length;
        if (objArr != null && objArr.length > length) {
            objArr = Arrays.copyOf(objArr, length);
        }
        return expandVarArgs(mockitoMethod.isVarArgs(), objArr);
    }

    private static Object[] expandVarArgs(boolean z, Object[] objArr) {
        if (!z || isNullOrEmpty(objArr) || (objArr[objArr.length - 1] != null && !objArr[objArr.length - 1].getClass().isArray())) {
            return objArr == null ? new Object[0] : objArr;
        }
        int length = objArr.length - 1;
        Object[] createObjectArray = objArr[length] == null ? new Object[]{null} : ArrayEquals.createObjectArray(objArr[length]);
        int length2 = createObjectArray.length;
        Object[] objArr2 = new Object[(length + length2)];
        System.arraycopy(objArr, 0, objArr2, 0, length);
        System.arraycopy(createObjectArray, 0, objArr2, length, length2);
        return objArr2;
    }

    private static <T> boolean isNullOrEmpty(T[] tArr) {
        return tArr == null || tArr.length == 0;
    }

    public static List<ArgumentMatcher> argumentsToMatchers(Object[] objArr) {
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj : objArr) {
            if (obj == null || !obj.getClass().isArray()) {
                arrayList.add(new Equals(obj));
            } else {
                arrayList.add(new ArrayEquals(obj));
            }
        }
        return arrayList;
    }
}
