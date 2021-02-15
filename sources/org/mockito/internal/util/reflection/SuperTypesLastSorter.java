package org.mockito.internal.util.reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SuperTypesLastSorter {
    private static final Comparator<Field> compareFieldsByName = new Comparator<Field>() {
        public int compare(Field field, Field field2) {
            return field.getName().compareTo(field2.getName());
        }
    };

    private SuperTypesLastSorter() {
    }

    public static List<Field> sortSuperTypesLast(Collection<? extends Field> collection) {
        ArrayList arrayList = new ArrayList(collection);
        Collections.sort(arrayList, compareFieldsByName);
        int i = 0;
        while (i < arrayList.size() - 1) {
            Field field = (Field) arrayList.get(i);
            Class<?> type = field.getType();
            int i2 = i + 1;
            int i3 = i;
            for (int i4 = i2; i4 < arrayList.size(); i4++) {
                Class<?> type2 = ((Field) arrayList.get(i4)).getType();
                if (type != type2 && type.isAssignableFrom(type2)) {
                    i3 = i4;
                }
            }
            if (i3 == i) {
                i = i2;
            } else {
                arrayList.remove(i);
                arrayList.add(i3, field);
            }
        }
        return arrayList;
    }
}
