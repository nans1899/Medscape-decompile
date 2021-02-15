package org.mockito.internal.verification.argumentmatching;

import java.util.LinkedList;
import java.util.List;
import org.mockito.ArgumentMatcher;
import org.mockito.internal.matchers.ContainsExtraTypeInfo;

public class ArgumentMatchingTool {
    private ArgumentMatchingTool() {
    }

    public static Integer[] getSuspiciouslyNotMatchingArgsIndexes(List<ArgumentMatcher> list, Object[] objArr) {
        if (list.size() != objArr.length) {
            return new Integer[0];
        }
        LinkedList linkedList = new LinkedList();
        int i = 0;
        for (ArgumentMatcher next : list) {
            if ((next instanceof ContainsExtraTypeInfo) && !safelyMatches(next, objArr[i]) && toStringEquals(next, objArr[i]) && !((ContainsExtraTypeInfo) next).typeMatches(objArr[i])) {
                linkedList.add(Integer.valueOf(i));
            }
            i++;
        }
        return (Integer[]) linkedList.toArray(new Integer[0]);
    }

    private static boolean safelyMatches(ArgumentMatcher argumentMatcher, Object obj) {
        try {
            return argumentMatcher.matches(obj);
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean toStringEquals(ArgumentMatcher argumentMatcher, Object obj) {
        return argumentMatcher.toString().equals(obj == null ? "null" : obj.toString());
    }
}
