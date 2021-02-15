package org.mockito.internal.util.collections;

import java.util.Collection;
import java.util.LinkedList;

public class ListUtil {

    public interface Converter<From, To> {
        To convert(From from);
    }

    public interface Filter<T> {
        boolean isOut(T t);
    }

    public static <T> LinkedList<T> filter(Collection<T> collection, Filter<T> filter) {
        LinkedList<T> linkedList = new LinkedList<>();
        for (T next : collection) {
            if (!filter.isOut(next)) {
                linkedList.add(next);
            }
        }
        return linkedList;
    }

    public static <From, To> LinkedList<To> convert(Collection<From> collection, Converter<From, To> converter) {
        LinkedList<To> linkedList = new LinkedList<>();
        for (From convert : collection) {
            linkedList.add(converter.convert(convert));
        }
        return linkedList;
    }
}
