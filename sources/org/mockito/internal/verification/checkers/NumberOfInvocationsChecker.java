package org.mockito.internal.verification.checkers;

import java.util.Arrays;
import java.util.List;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.invocation.InvocationMarker;
import org.mockito.internal.invocation.InvocationsFinder;
import org.mockito.internal.reporting.Discrepancy;
import org.mockito.internal.verification.api.InOrderContext;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.Location;
import org.mockito.invocation.MatchableInvocation;

public class NumberOfInvocationsChecker {
    private NumberOfInvocationsChecker() {
    }

    public static void checkNumberOfInvocations(List<Invocation> list, MatchableInvocation matchableInvocation, int i) {
        List<Invocation> findInvocations = InvocationsFinder.findInvocations(list, matchableInvocation);
        int size = findInvocations.size();
        if (i > size) {
            throw Reporter.tooLittleActualInvocations(new Discrepancy(i, size), matchableInvocation, InvocationsFinder.getAllLocations(findInvocations));
        } else if (i == 0 && size > 0) {
            throw Reporter.neverWantedButInvoked(matchableInvocation, InvocationsFinder.getAllLocations(findInvocations));
        } else if (i >= size) {
            InvocationMarker.markVerified(findInvocations, matchableInvocation);
        } else {
            throw Reporter.tooManyActualInvocations(i, size, matchableInvocation, InvocationsFinder.getAllLocations(findInvocations));
        }
    }

    public static void checkNumberOfInvocations(List<Invocation> list, MatchableInvocation matchableInvocation, int i, InOrderContext inOrderContext) {
        List<Invocation> findMatchingChunk = InvocationsFinder.findMatchingChunk(list, matchableInvocation, i, inOrderContext);
        int size = findMatchingChunk.size();
        if (i > size) {
            throw Reporter.tooLittleActualInvocationsInOrder(new Discrepancy(i, size), matchableInvocation, InvocationsFinder.getAllLocations(findMatchingChunk));
        } else if (i >= size) {
            InvocationMarker.markVerifiedInOrder(findMatchingChunk, matchableInvocation, inOrderContext);
        } else {
            throw Reporter.tooManyActualInvocationsInOrder(i, size, matchableInvocation, InvocationsFinder.getAllLocations(findMatchingChunk));
        }
    }

    public static void checkNumberOfInvocationsNonGreedy(List<Invocation> list, MatchableInvocation matchableInvocation, int i, InOrderContext inOrderContext) {
        Location location = null;
        int i2 = 0;
        while (i2 < i) {
            Invocation findFirstMatchingUnverifiedInvocation = InvocationsFinder.findFirstMatchingUnverifiedInvocation(list, matchableInvocation, inOrderContext);
            if (findFirstMatchingUnverifiedInvocation != null) {
                InvocationMarker.markVerified(findFirstMatchingUnverifiedInvocation, matchableInvocation);
                inOrderContext.markVerified(findFirstMatchingUnverifiedInvocation);
                location = findFirstMatchingUnverifiedInvocation.getLocation();
                i2++;
            } else {
                throw Reporter.tooLittleActualInvocationsInOrder(new Discrepancy(i, i2), matchableInvocation, Arrays.asList(new Location[]{location}));
            }
        }
    }
}
