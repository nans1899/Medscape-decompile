package org.mockito.internal.invocation.finder;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.mockito.internal.invocation.InvocationComparator;
import org.mockito.internal.stubbing.StubbingComparator;
import org.mockito.internal.util.DefaultMockingDetails;
import org.mockito.invocation.Invocation;
import org.mockito.stubbing.Stubbing;

public class AllInvocationsFinder {
    private AllInvocationsFinder() {
    }

    public static List<Invocation> find(Iterable<?> iterable) {
        TreeSet treeSet = new TreeSet(new InvocationComparator());
        for (Object defaultMockingDetails : iterable) {
            treeSet.addAll(new DefaultMockingDetails(defaultMockingDetails).getInvocations());
        }
        return new LinkedList(treeSet);
    }

    public static Set<Stubbing> findStubbings(Iterable<?> iterable) {
        TreeSet treeSet = new TreeSet(new StubbingComparator());
        for (Object defaultMockingDetails : iterable) {
            treeSet.addAll(new DefaultMockingDetails(defaultMockingDetails).getStubbings());
        }
        return treeSet;
    }
}
