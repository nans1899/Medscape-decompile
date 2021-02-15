package org.mockito.internal.configuration.injection.filter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TypeBasedCandidateFilter implements MockCandidateFilter {
    private final MockCandidateFilter next;

    public TypeBasedCandidateFilter(MockCandidateFilter mockCandidateFilter) {
        this.next = mockCandidateFilter;
    }

    public OngoingInjector filterCandidate(Collection<Object> collection, Field field, List<Field> list, Object obj) {
        ArrayList arrayList = new ArrayList();
        for (Object next2 : collection) {
            if (field.getType().isAssignableFrom(next2.getClass())) {
                arrayList.add(next2);
            }
        }
        return this.next.filterCandidate(arrayList, field, list, obj);
    }
}
