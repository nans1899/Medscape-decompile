package org.mockito.internal.invocation;

import java.util.ArrayList;
import java.util.List;
import org.mockito.ArgumentMatcher;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import org.mockito.internal.matchers.VarargMatcher;
import org.mockito.invocation.Invocation;

public class MatcherApplicationStrategy {
    private final Invocation invocation;
    private final List<ArgumentMatcher<?>> matchers;
    private final MatcherApplicationType matchingType;

    enum MatcherApplicationType {
        ONE_MATCHER_PER_ARGUMENT,
        MATCH_EACH_VARARGS_WITH_LAST_MATCHER,
        ERROR_UNSUPPORTED_NUMBER_OF_MATCHERS
    }

    private MatcherApplicationStrategy(Invocation invocation2, List<ArgumentMatcher<?>> list, MatcherApplicationType matcherApplicationType) {
        this.invocation = invocation2;
        if (matcherApplicationType == MatcherApplicationType.MATCH_EACH_VARARGS_WITH_LAST_MATCHER) {
            this.matchers = appendLastMatcherNTimes(list, varargLength(invocation2));
        } else {
            this.matchers = list;
        }
        this.matchingType = matcherApplicationType;
    }

    public static MatcherApplicationStrategy getMatcherApplicationStrategyFor(Invocation invocation2, List<ArgumentMatcher<?>> list) {
        return new MatcherApplicationStrategy(invocation2, list, getMatcherApplicationType(invocation2, list));
    }

    public boolean forEachMatcherAndArgument(ArgumentMatcherAction argumentMatcherAction) {
        if (this.matchingType == MatcherApplicationType.ERROR_UNSUPPORTED_NUMBER_OF_MATCHERS) {
            return false;
        }
        Object[] arguments = this.invocation.getArguments();
        for (int i = 0; i < arguments.length; i++) {
            if (!argumentMatcherAction.apply(this.matchers.get(i), arguments[i])) {
                return false;
            }
        }
        return true;
    }

    private static MatcherApplicationType getMatcherApplicationType(Invocation invocation2, List<ArgumentMatcher<?>> list) {
        int length = invocation2.getRawArguments().length;
        int length2 = invocation2.getArguments().length;
        int size = list.size();
        if (length2 == size) {
            return MatcherApplicationType.ONE_MATCHER_PER_ARGUMENT;
        }
        if (length != size || !isLastMatcherVarargMatcher(list)) {
            return MatcherApplicationType.ERROR_UNSUPPORTED_NUMBER_OF_MATCHERS;
        }
        return MatcherApplicationType.MATCH_EACH_VARARGS_WITH_LAST_MATCHER;
    }

    private static boolean isLastMatcherVarargMatcher(List<ArgumentMatcher<?>> list) {
        ArgumentMatcher<?> lastMatcher = lastMatcher(list);
        if (lastMatcher instanceof HamcrestArgumentMatcher) {
            return ((HamcrestArgumentMatcher) lastMatcher).isVarargMatcher();
        }
        return lastMatcher instanceof VarargMatcher;
    }

    private static List<ArgumentMatcher<?>> appendLastMatcherNTimes(List<ArgumentMatcher<?>> list, int i) {
        ArgumentMatcher<?> lastMatcher = lastMatcher(list);
        ArrayList arrayList = new ArrayList(list);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(lastMatcher);
        }
        return arrayList;
    }

    private static int varargLength(Invocation invocation2) {
        return invocation2.getArguments().length - invocation2.getRawArguments().length;
    }

    private static ArgumentMatcher<?> lastMatcher(List<ArgumentMatcher<?>> list) {
        return list.get(list.size() - 1);
    }
}
