package org.mockito.internal.debugging;

import java.util.LinkedList;
import java.util.List;
import org.mockito.internal.invocation.InvocationMatcher;
import org.mockito.internal.invocation.UnusedStubsFinder;
import org.mockito.internal.invocation.finder.AllInvocationsFinder;

@Deprecated
public class WarningsCollector {
    private final List<Object> createdMocks = new LinkedList();

    public String getWarnings() {
        return new WarningsPrinterImpl(new UnusedStubsFinder().find(this.createdMocks), InvocationMatcher.createFrom(AllInvocationsFinder.find(this.createdMocks)), false).print();
    }
}
