package org.mockito.internal.configuration.injection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.configuration.injection.filter.MockCandidateFilter;
import org.mockito.internal.configuration.injection.filter.NameBasedCandidateFilter;
import org.mockito.internal.configuration.injection.filter.TerminalMockCandidateFilter;
import org.mockito.internal.configuration.injection.filter.TypeBasedCandidateFilter;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.util.collections.ListUtil;
import org.mockito.internal.util.collections.Sets;
import org.mockito.internal.util.reflection.FieldInitializationReport;
import org.mockito.internal.util.reflection.FieldInitializer;
import org.mockito.internal.util.reflection.SuperTypesLastSorter;

public class PropertyAndSetterInjection extends MockInjectionStrategy {
    private final MockCandidateFilter mockCandidateFilter = new TypeBasedCandidateFilter(new NameBasedCandidateFilter(new TerminalMockCandidateFilter()));
    private final ListUtil.Filter<Field> notFinalOrStatic = new ListUtil.Filter<Field>() {
        public boolean isOut(Field field) {
            return Modifier.isFinal(field.getModifiers()) || Modifier.isStatic(field.getModifiers());
        }
    };

    public boolean processInjection(Field field, Object obj, Set<Object> set) {
        FieldInitializationReport initializeInjectMocksField = initializeInjectMocksField(field, obj);
        Object fieldInstance = initializeInjectMocksField.fieldInstance();
        boolean z = false;
        for (Class fieldClass = initializeInjectMocksField.fieldClass(); fieldClass != Object.class; fieldClass = fieldClass.getSuperclass()) {
            z |= injectMockCandidates(fieldClass, fieldInstance, Sets.newMockSafeHashSet((Iterable<Object>) set));
        }
        return z;
    }

    private FieldInitializationReport initializeInjectMocksField(Field field, Object obj) {
        try {
            return new FieldInitializer(obj, field).initialize();
        } catch (MockitoException e) {
            if (e.getCause() instanceof InvocationTargetException) {
                throw Reporter.fieldInitialisationThrewException(field, e.getCause().getCause());
            }
            throw Reporter.cannotInitializeForInjectMocksAnnotation(field.getName(), e.getMessage());
        }
    }

    private boolean injectMockCandidates(Class<?> cls, Object obj, Set<Object> set) {
        List<Field> orderedInstanceFieldsFrom = orderedInstanceFieldsFrom(cls);
        boolean injectMockCandidatesOnFields = injectMockCandidatesOnFields(set, obj, false, orderedInstanceFieldsFrom);
        return injectMockCandidatesOnFields(set, obj, injectMockCandidatesOnFields, orderedInstanceFieldsFrom) | injectMockCandidatesOnFields;
    }

    private boolean injectMockCandidatesOnFields(Set<Object> set, Object obj, boolean z, List<Field> list) {
        Iterator<Field> it = list.iterator();
        while (it.hasNext()) {
            Object thenInject = this.mockCandidateFilter.filterCandidate(set, it.next(), list, obj).thenInject();
            if (thenInject != null) {
                z |= true;
                set.remove(thenInject);
                it.remove();
            }
        }
        return z;
    }

    private List<Field> orderedInstanceFieldsFrom(Class<?> cls) {
        return SuperTypesLastSorter.sortSuperTypesLast(ListUtil.filter(Arrays.asList(cls.getDeclaredFields()), this.notFinalOrStatic));
    }
}
