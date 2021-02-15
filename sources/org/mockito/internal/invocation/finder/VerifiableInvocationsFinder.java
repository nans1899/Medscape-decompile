package org.mockito.internal.invocation.finder;

import java.util.List;
import org.mockito.internal.util.collections.ListUtil;
import org.mockito.invocation.Invocation;

public class VerifiableInvocationsFinder {
    private VerifiableInvocationsFinder() {
    }

    public static List<Invocation> find(List<?> list) {
        return ListUtil.filter(AllInvocationsFinder.find(list), new RemoveIgnoredForVerification());
    }

    private static class RemoveIgnoredForVerification implements ListUtil.Filter<Invocation> {
        private RemoveIgnoredForVerification() {
        }

        public boolean isOut(Invocation invocation) {
            return invocation.isIgnoredForVerification();
        }
    }
}
