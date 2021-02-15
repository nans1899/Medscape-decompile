package org.mockito.internal.debugging;

import java.util.List;
import org.mockito.internal.invocation.InvocationMatcher;
import org.mockito.invocation.Invocation;

public class WarningsPrinterImpl {
    private final WarningsFinder finder;
    private final boolean warnAboutUnstubbed;

    public WarningsPrinterImpl(List<Invocation> list, List<InvocationMatcher> list2, boolean z) {
        this(z, new WarningsFinder(list, list2));
    }

    WarningsPrinterImpl(boolean z, WarningsFinder warningsFinder) {
        this.warnAboutUnstubbed = z;
        this.finder = warningsFinder;
    }

    public String print() {
        LoggingListener loggingListener = new LoggingListener(this.warnAboutUnstubbed);
        this.finder.find(loggingListener);
        return loggingListener.getStubbingInfo();
    }
}
