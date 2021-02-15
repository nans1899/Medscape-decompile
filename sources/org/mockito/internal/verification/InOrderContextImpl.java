package org.mockito.internal.verification;

import org.mockito.internal.util.collections.IdentitySet;
import org.mockito.internal.verification.api.InOrderContext;
import org.mockito.invocation.Invocation;

public class InOrderContextImpl implements InOrderContext {
    final IdentitySet verified = new IdentitySet();

    public boolean isVerified(Invocation invocation) {
        return this.verified.contains(invocation);
    }

    public void markVerified(Invocation invocation) {
        this.verified.add(invocation);
    }
}
