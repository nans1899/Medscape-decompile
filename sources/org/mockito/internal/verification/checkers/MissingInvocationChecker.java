package org.mockito.internal.verification.checkers;

import java.util.List;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.invocation.InvocationsFinder;
import org.mockito.internal.reporting.SmartPrinter;
import org.mockito.internal.verification.api.InOrderContext;
import org.mockito.internal.verification.argumentmatching.ArgumentMatchingTool;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.MatchableInvocation;

public class MissingInvocationChecker {
    private MissingInvocationChecker() {
    }

    public static void checkMissingInvocation(List<Invocation> list, MatchableInvocation matchableInvocation) {
        if (InvocationsFinder.findInvocations(list, matchableInvocation).isEmpty()) {
            Invocation findSimilarInvocation = InvocationsFinder.findSimilarInvocation(list, matchableInvocation);
            if (findSimilarInvocation == null) {
                throw Reporter.wantedButNotInvoked(matchableInvocation, list);
            }
            SmartPrinter smartPrinter = new SmartPrinter(matchableInvocation, findSimilarInvocation, ArgumentMatchingTool.getSuspiciouslyNotMatchingArgsIndexes(matchableInvocation.getMatchers(), findSimilarInvocation.getArguments()));
            throw Reporter.argumentsAreDifferent(smartPrinter.getWanted(), smartPrinter.getActual(), findSimilarInvocation.getLocation());
        }
    }

    public static void checkMissingInvocation(List<Invocation> list, MatchableInvocation matchableInvocation, InOrderContext inOrderContext) {
        if (InvocationsFinder.findAllMatchingUnverifiedChunks(list, matchableInvocation, inOrderContext).isEmpty()) {
            Invocation findPreviousVerifiedInOrder = InvocationsFinder.findPreviousVerifiedInOrder(list, inOrderContext);
            if (findPreviousVerifiedInOrder == null) {
                checkMissingInvocation(list, matchableInvocation);
                return;
            }
            throw Reporter.wantedButNotInvokedInOrder(matchableInvocation, findPreviousVerifiedInOrder);
        }
    }
}
