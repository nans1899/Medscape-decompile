package org.mockito.internal.verification.checkers;

import java.util.List;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.invocation.InvocationMarker;
import org.mockito.internal.invocation.InvocationsFinder;
import org.mockito.internal.verification.api.InOrderContext;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.MatchableInvocation;

public class AtLeastXNumberOfInvocationsChecker {
    public static void checkAtLeastNumberOfInvocations(List<Invocation> list, MatchableInvocation matchableInvocation, int i) {
        List<Invocation> findInvocations = InvocationsFinder.findInvocations(list, matchableInvocation);
        int size = findInvocations.size();
        if (i <= size) {
            InvocationMarker.markVerified(findInvocations, matchableInvocation);
        } else {
            throw Reporter.tooLittleActualInvocations(new AtLeastDiscrepancy(i, size), matchableInvocation, InvocationsFinder.getAllLocations(findInvocations));
        }
    }

    public static void checkAtLeastNumberOfInvocations(List<Invocation> list, MatchableInvocation matchableInvocation, int i, InOrderContext inOrderContext) {
        List<Invocation> findAllMatchingUnverifiedChunks = InvocationsFinder.findAllMatchingUnverifiedChunks(list, matchableInvocation, inOrderContext);
        int size = findAllMatchingUnverifiedChunks.size();
        if (i <= size) {
            InvocationMarker.markVerifiedInOrder(findAllMatchingUnverifiedChunks, matchableInvocation, inOrderContext);
        } else {
            throw Reporter.tooLittleActualInvocationsInOrder(new AtLeastDiscrepancy(i, size), matchableInvocation, InvocationsFinder.getAllLocations(findAllMatchingUnverifiedChunks));
        }
    }
}
