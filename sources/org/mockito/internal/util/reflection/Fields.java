package org.mockito.internal.util.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.mockito.internal.util.Checks;
import org.mockito.internal.util.collections.ListUtil;

public abstract class Fields {
    public static InstanceFields allDeclaredFieldsOf(Object obj) {
        ArrayList arrayList = new ArrayList();
        for (Class cls = obj.getClass(); cls != Object.class; cls = cls.getSuperclass()) {
            arrayList.addAll(instanceFieldsIn(obj, cls.getDeclaredFields()));
        }
        return new InstanceFields(obj, arrayList);
    }

    public static InstanceFields declaredFieldsOf(Object obj) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(instanceFieldsIn(obj, obj.getClass().getDeclaredFields()));
        return new InstanceFields(obj, arrayList);
    }

    private static List<InstanceField> instanceFieldsIn(Object obj, Field[] fieldArr) {
        ArrayList arrayList = new ArrayList();
        for (Field instanceField : fieldArr) {
            arrayList.add(new InstanceField(instanceField, obj));
        }
        return arrayList;
    }

    public static ListUtil.Filter<InstanceField> annotatedBy(final Class<? extends Annotation>... clsArr) {
        return new ListUtil.Filter<InstanceField>() {
            public boolean isOut(InstanceField instanceField) {
                Checks.checkNotNull(clsArr, "Provide at least one annotation class");
                for (Class isAnnotatedBy : clsArr) {
                    if (instanceField.isAnnotatedBy(isAnnotatedBy)) {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    /* access modifiers changed from: private */
    public static ListUtil.Filter<InstanceField> nullField() {
        return new ListUtil.Filter<InstanceField>() {
            public boolean isOut(InstanceField instanceField) {
                return instanceField.isNull();
            }
        };
    }

    public static ListUtil.Filter<InstanceField> syntheticField() {
        return new ListUtil.Filter<InstanceField>() {
            public boolean isOut(InstanceField instanceField) {
                return instanceField.isSynthetic();
            }
        };
    }

    public static class InstanceFields {
        private final Object instance;
        private final List<InstanceField> instanceFields;

        public InstanceFields(Object obj, List<InstanceField> list) {
            this.instance = obj;
            this.instanceFields = list;
        }

        public InstanceFields filter(ListUtil.Filter<InstanceField> filter) {
            return new InstanceFields(this.instance, ListUtil.filter(this.instanceFields, filter));
        }

        public InstanceFields notNull() {
            return filter(Fields.nullField());
        }

        public List<InstanceField> instanceFields() {
            return new ArrayList(this.instanceFields);
        }

        public List<Object> assignedValues() {
            ArrayList arrayList = new ArrayList(this.instanceFields.size());
            for (InstanceField read : this.instanceFields) {
                arrayList.add(read.read());
            }
            return arrayList;
        }

        public List<String> names() {
            ArrayList arrayList = new ArrayList(this.instanceFields.size());
            for (InstanceField name : this.instanceFields) {
                arrayList.add(name.name());
            }
            return arrayList;
        }
    }
}
