package org.mockito.internal.reporting;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.mockito.ArgumentMatcher;
import org.mockito.internal.invocation.ArgumentsProcessor;
import org.mockito.internal.matchers.text.MatchersPrinter;
import org.mockito.internal.util.MockUtil;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.MatchableInvocation;

public class PrintSettings {
    public static final int MAX_LINE_LENGTH = 45;
    private boolean multiline;
    private List<Integer> withTypeInfo = new LinkedList();

    public void setMultiline(boolean z) {
        this.multiline = z;
    }

    public boolean isMultiline() {
        return this.multiline;
    }

    public static PrintSettings verboseMatchers(Integer... numArr) {
        PrintSettings printSettings = new PrintSettings();
        printSettings.setMatchersToBeDescribedWithExtraTypeInfo(numArr);
        return printSettings;
    }

    public boolean extraTypeInfoFor(int i) {
        return this.withTypeInfo.contains(Integer.valueOf(i));
    }

    public void setMatchersToBeDescribedWithExtraTypeInfo(Integer[] numArr) {
        this.withTypeInfo = Arrays.asList(numArr);
    }

    public String print(List<ArgumentMatcher> list, Invocation invocation) {
        MatchersPrinter matchersPrinter = new MatchersPrinter();
        String str = MockUtil.getMockName(invocation.getMock()) + "." + invocation.getMethod().getName();
        String str2 = str + matchersPrinter.getArgumentsLine(list, this);
        if (!isMultiline() && (list.isEmpty() || str2.length() <= 45)) {
            return str2;
        }
        return str + matchersPrinter.getArgumentsBlock(list, this);
    }

    public String print(Invocation invocation) {
        return print(ArgumentsProcessor.argumentsToMatchers(invocation.getArguments()), invocation);
    }

    public String print(MatchableInvocation matchableInvocation) {
        return print(matchableInvocation.getMatchers(), matchableInvocation.getInvocation());
    }
}
