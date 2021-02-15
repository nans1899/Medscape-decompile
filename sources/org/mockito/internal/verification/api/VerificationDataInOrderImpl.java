package org.mockito.internal.verification.api;

import java.util.List;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.MatchableInvocation;

public class VerificationDataInOrderImpl implements VerificationDataInOrder {
    private final List<Invocation> allInvocations;
    private final InOrderContext inOrder;
    private final MatchableInvocation wanted;

    public VerificationDataInOrderImpl(InOrderContext inOrderContext, List<Invocation> list, MatchableInvocation matchableInvocation) {
        this.inOrder = inOrderContext;
        this.allInvocations = list;
        this.wanted = matchableInvocation;
    }

    public List<Invocation> getAllInvocations() {
        return this.allInvocations;
    }

    public InOrderContext getOrderingContext() {
        return this.inOrder;
    }

    public MatchableInvocation getWanted() {
        return this.wanted;
    }
}
