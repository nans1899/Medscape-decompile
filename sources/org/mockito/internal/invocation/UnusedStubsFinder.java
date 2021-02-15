package org.mockito.internal.invocation;

import java.util.LinkedList;
import java.util.List;
import org.mockito.internal.util.MockUtil;
import org.mockito.invocation.Invocation;
import org.mockito.stubbing.Stubbing;

@Deprecated
public class UnusedStubsFinder {
    public List<Invocation> find(List<?> list) {
        LinkedList linkedList = new LinkedList();
        for (Object invocationContainer : list) {
            for (Stubbing next : MockUtil.getInvocationContainer(invocationContainer).getStubbingsDescending()) {
                if (!next.wasUsed()) {
                    linkedList.add(next.getInvocation());
                }
            }
        }
        return linkedList;
    }
}
