package org.mockito.internal.configuration.injection.filter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.mockito.internal.util.MockUtil;

public class NameBasedCandidateFilter implements MockCandidateFilter {
    private final MockCandidateFilter next;

    public NameBasedCandidateFilter(MockCandidateFilter mockCandidateFilter) {
        this.next = mockCandidateFilter;
    }

    public OngoingInjector filterCandidate(Collection<Object> collection, Field field, List<Field> list, Object obj) {
        if (collection.size() == 1 && anotherCandidateMatchesMockName(collection, field, list)) {
            return OngoingInjector.nop;
        }
        MockCandidateFilter mockCandidateFilter = this.next;
        boolean z = tooMany(collection);
        List<Object> list2 = collection;
        if (z) {
            list2 = selectMatchingName(collection, field);
        }
        return mockCandidateFilter.filterCandidate(list2, field, list, obj);
    }

    private boolean tooMany(Collection<Object> collection) {
        return collection.size() > 1;
    }

    private List<Object> selectMatchingName(Collection<Object> collection, Field field) {
        ArrayList arrayList = new ArrayList();
        for (Object next2 : collection) {
            if (field.getName().equals(MockUtil.getMockName(next2).toString())) {
                arrayList.add(next2);
            }
        }
        return arrayList;
    }

    private boolean anotherCandidateMatchesMockName(Collection<Object> collection, Field field, List<Field> list) {
        String mockName = MockUtil.getMockName(collection.iterator().next()).toString();
        for (Field next2 : list) {
            if (!next2.equals(field) && next2.getType().equals(field.getType()) && next2.getName().equals(mockName)) {
                return true;
            }
        }
        return false;
    }
}
