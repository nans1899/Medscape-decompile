package org.mockito.internal.reporting;

import org.apache.commons.io.IOUtils;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.MatchableInvocation;

public class SmartPrinter {
    private final String actual;
    private final String wanted;

    public SmartPrinter(MatchableInvocation matchableInvocation, Invocation invocation, Integer... numArr) {
        PrintSettings printSettings = new PrintSettings();
        printSettings.setMultiline(matchableInvocation.toString().contains(IOUtils.LINE_SEPARATOR_UNIX) || invocation.toString().contains(IOUtils.LINE_SEPARATOR_UNIX));
        printSettings.setMatchersToBeDescribedWithExtraTypeInfo(numArr);
        this.wanted = printSettings.print(matchableInvocation);
        this.actual = printSettings.print(invocation);
    }

    public String getWanted() {
        return this.wanted;
    }

    public String getActual() {
        return this.actual;
    }
}
