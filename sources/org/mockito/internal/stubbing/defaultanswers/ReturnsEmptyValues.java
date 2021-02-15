package org.mockito.internal.stubbing.defaultanswers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import org.mockito.internal.util.JavaEightUtil;
import org.mockito.internal.util.MockUtil;
import org.mockito.internal.util.ObjectMethodsGuru;
import org.mockito.internal.util.Primitives;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.mock.MockName;
import org.mockito.stubbing.Answer;

public class ReturnsEmptyValues implements Answer<Object>, Serializable {
    private static final long serialVersionUID = 1998191268711234347L;

    public Object answer(InvocationOnMock invocationOnMock) {
        if (ObjectMethodsGuru.isToStringMethod(invocationOnMock.getMethod())) {
            Object mock = invocationOnMock.getMock();
            MockName mockName = MockUtil.getMockName(mock);
            if (!mockName.isDefault()) {
                return mockName.toString();
            }
            return "Mock for " + MockUtil.getMockSettings(mock).getTypeToMock().getSimpleName() + ", hashCode: " + mock.hashCode();
        } else if (!ObjectMethodsGuru.isCompareToMethod(invocationOnMock.getMethod())) {
            return returnValueFor(invocationOnMock.getMethod().getReturnType());
        } else {
            int i = 0;
            if (invocationOnMock.getMock() != invocationOnMock.getArgument(0)) {
                i = 1;
            }
            return Integer.valueOf(i);
        }
    }

    /* access modifiers changed from: package-private */
    public Object returnValueFor(Class<?> cls) {
        if (Primitives.isPrimitiveOrWrapper(cls)) {
            return Primitives.defaultValue(cls);
        }
        if (cls == Iterable.class) {
            return new ArrayList(0);
        }
        if (cls == Collection.class) {
            return new LinkedList();
        }
        if (cls == Set.class) {
            return new HashSet();
        }
        if (cls == HashSet.class) {
            return new HashSet();
        }
        if (cls == SortedSet.class) {
            return new TreeSet();
        }
        if (cls == TreeSet.class) {
            return new TreeSet();
        }
        if (cls == LinkedHashSet.class) {
            return new LinkedHashSet();
        }
        if (cls == List.class) {
            return new LinkedList();
        }
        if (cls == LinkedList.class) {
            return new LinkedList();
        }
        if (cls == ArrayList.class) {
            return new ArrayList();
        }
        if (cls == Map.class) {
            return new HashMap();
        }
        if (cls == HashMap.class) {
            return new HashMap();
        }
        if (cls == SortedMap.class) {
            return new TreeMap();
        }
        if (cls == TreeMap.class) {
            return new TreeMap();
        }
        if (cls == LinkedHashMap.class) {
            return new LinkedHashMap();
        }
        if ("java.util.Optional".equals(cls.getName())) {
            return JavaEightUtil.emptyOptional();
        }
        if ("java.util.OptionalDouble".equals(cls.getName())) {
            return JavaEightUtil.emptyOptionalDouble();
        }
        if ("java.util.OptionalInt".equals(cls.getName())) {
            return JavaEightUtil.emptyOptionalInt();
        }
        if ("java.util.OptionalLong".equals(cls.getName())) {
            return JavaEightUtil.emptyOptionalLong();
        }
        if ("java.util.stream.Stream".equals(cls.getName())) {
            return JavaEightUtil.emptyStream();
        }
        if ("java.util.stream.DoubleStream".equals(cls.getName())) {
            return JavaEightUtil.emptyDoubleStream();
        }
        if ("java.util.stream.IntStream".equals(cls.getName())) {
            return JavaEightUtil.emptyIntStream();
        }
        if ("java.util.stream.LongStream".equals(cls.getName())) {
            return JavaEightUtil.emptyLongStream();
        }
        return null;
    }
}
