package org.mockito.internal.junit;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import org.mockito.internal.invocation.finder.AllInvocationsFinder;
import org.mockito.internal.stubbing.UnusedStubbingReporting;
import org.mockito.internal.util.collections.ListUtil;
import org.mockito.invocation.Invocation;
import org.mockito.stubbing.Stubbing;

public class UnusedStubbingsFinder {
    public UnusedStubbings getUnusedStubbings(Iterable<Object> iterable) {
        return new UnusedStubbings(ListUtil.filter(AllInvocationsFinder.findStubbings(iterable), new ListUtil.Filter<Stubbing>() {
            public boolean isOut(Stubbing stubbing) {
                return !UnusedStubbingReporting.shouldBeReported(stubbing);
            }
        }));
    }

    public Collection<Invocation> getUnusedStubbingsByLocation(Iterable<Object> iterable) {
        Set<Stubbing> findStubbings = AllInvocationsFinder.findStubbings(iterable);
        HashSet hashSet = new HashSet();
        for (Stubbing next : findStubbings) {
            if (!UnusedStubbingReporting.shouldBeReported(next)) {
                hashSet.add(next.getInvocation().getLocation().toString());
            }
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Stubbing next2 : findStubbings) {
            String location = next2.getInvocation().getLocation().toString();
            if (!hashSet.contains(location)) {
                linkedHashMap.put(location, next2.getInvocation());
            }
        }
        return linkedHashMap.values();
    }
}
