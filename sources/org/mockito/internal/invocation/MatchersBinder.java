package org.mockito.internal.invocation;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import org.mockito.internal.matchers.LocalizedMatcher;
import org.mockito.internal.progress.ArgumentMatcherStorage;
import org.mockito.invocation.Invocation;

public class MatchersBinder implements Serializable {
    public InvocationMatcher bindMatchers(ArgumentMatcherStorage argumentMatcherStorage, Invocation invocation) {
        List<LocalizedMatcher> pullLocalizedMatchers = argumentMatcherStorage.pullLocalizedMatchers();
        validateMatchers(invocation, pullLocalizedMatchers);
        LinkedList linkedList = new LinkedList();
        for (LocalizedMatcher matcher : pullLocalizedMatchers) {
            linkedList.add(matcher.getMatcher());
        }
        return new InvocationMatcher(invocation, linkedList);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r3.size();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void validateMatchers(org.mockito.invocation.Invocation r2, java.util.List<org.mockito.internal.matchers.LocalizedMatcher> r3) {
        /*
            r1 = this;
            boolean r0 = r3.isEmpty()
            if (r0 != 0) goto L_0x0017
            int r0 = r3.size()
            java.lang.Object[] r2 = r2.getArguments()
            int r2 = r2.length
            if (r2 != r0) goto L_0x0012
            goto L_0x0017
        L_0x0012:
            org.mockito.exceptions.base.MockitoException r2 = org.mockito.internal.exceptions.Reporter.invalidUseOfMatchers(r2, r3)
            throw r2
        L_0x0017:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mockito.internal.invocation.MatchersBinder.validateMatchers(org.mockito.invocation.Invocation, java.util.List):void");
    }
}
