package org.mockito.internal.debugging;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.LinkedList;
import java.util.List;
import org.mockito.internal.invocation.InvocationMatcher;
import org.mockito.internal.util.StringUtil;
import org.mockito.invocation.Invocation;

public class LoggingListener implements FindingsListener {
    private final List<String> argMismatchStubs = new LinkedList();
    private final List<String> unstubbedCalls = new LinkedList();
    private final List<String> unusedStubs = new LinkedList();
    private final boolean warnAboutUnstubbed;

    public LoggingListener(boolean z) {
        this.warnAboutUnstubbed = z;
    }

    public void foundStubCalledWithDifferentArgs(Invocation invocation, InvocationMatcher invocationMatcher) {
        String num = Integer.toString(indexOfNextPair(this.argMismatchStubs.size()));
        String replaceAll = num.replaceAll("\\d", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        List<String> list = this.argMismatchStubs;
        list.add(num + ". Stubbed " + invocation.getLocation());
        List<String> list2 = this.argMismatchStubs;
        list2.add(replaceAll + "  Invoked " + invocationMatcher.getInvocation().getLocation());
    }

    static int indexOfNextPair(int i) {
        return (i / 2) + 1;
    }

    public void foundUnusedStub(Invocation invocation) {
        List<String> list = this.unusedStubs;
        list.add((this.unusedStubs.size() + 1) + ". " + invocation.getLocation());
    }

    public void foundUnstubbed(InvocationMatcher invocationMatcher) {
        if (this.warnAboutUnstubbed) {
            List<String> list = this.unstubbedCalls;
            list.add((this.unstubbedCalls.size() + 1) + ". " + invocationMatcher.getInvocation().getLocation());
        }
    }

    public String getStubbingInfo() {
        if (this.argMismatchStubs.isEmpty() && this.unusedStubs.isEmpty() && this.unstubbedCalls.isEmpty()) {
            return "";
        }
        LinkedList linkedList = new LinkedList();
        linkedList.add("[Mockito] Additional stubbing information (see javadoc for StubbingInfo class):");
        if (!this.argMismatchStubs.isEmpty()) {
            linkedList.add("[Mockito]");
            linkedList.add("[Mockito] Argument mismatch between stubbing and actual invocation (is stubbing correct in the test?):");
            linkedList.add("[Mockito]");
            addOrderedList(linkedList, this.argMismatchStubs);
        }
        if (!this.unusedStubs.isEmpty()) {
            linkedList.add("[Mockito]");
            linkedList.add("[Mockito] Unused stubbing (perhaps can be removed from the test?):");
            linkedList.add("[Mockito]");
            addOrderedList(linkedList, this.unusedStubs);
        }
        if (!this.unstubbedCalls.isEmpty()) {
            linkedList.add("[Mockito]");
            linkedList.add("[Mockito] Unstubbed method invocations (perhaps missing stubbing in the test?):");
            linkedList.add("[Mockito]");
            addOrderedList(linkedList, this.unstubbedCalls);
        }
        return StringUtil.join("", linkedList);
    }

    private void addOrderedList(List<String> list, List<String> list2) {
        for (String str : list2) {
            list.add("[Mockito] " + str);
        }
    }
}
