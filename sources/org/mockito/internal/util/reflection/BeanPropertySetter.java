package org.mockito.internal.util.reflection;

import java.lang.reflect.Field;
import java.util.Locale;

public class BeanPropertySetter {
    private static final String SET_PREFIX = "set";
    private final Field field;
    private final boolean reportNoSetterFound;
    private final Object target;

    public BeanPropertySetter(Object obj, Field field2, boolean z) {
        this.field = field2;
        this.target = obj;
        this.reportNoSetterFound = z;
    }

    public BeanPropertySetter(Object obj, Field field2) {
        this(obj, field2, false);
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x003c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean set(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.String r0 = "'"
            org.mockito.internal.util.reflection.AccessibilityChanger r1 = new org.mockito.internal.util.reflection.AccessibilityChanger
            r1.<init>()
            r2 = 0
            r3 = 0
            java.lang.Object r4 = r9.target     // Catch:{ InvocationTargetException -> 0x0077, IllegalAccessException -> 0x0048, NoSuchMethodException -> 0x003c }
            java.lang.Class r4 = r4.getClass()     // Catch:{ InvocationTargetException -> 0x0077, IllegalAccessException -> 0x0048, NoSuchMethodException -> 0x003c }
            java.lang.reflect.Field r5 = r9.field     // Catch:{ InvocationTargetException -> 0x0077, IllegalAccessException -> 0x0048, NoSuchMethodException -> 0x003c }
            java.lang.String r5 = r5.getName()     // Catch:{ InvocationTargetException -> 0x0077, IllegalAccessException -> 0x0048, NoSuchMethodException -> 0x003c }
            java.lang.String r5 = r9.setterName(r5)     // Catch:{ InvocationTargetException -> 0x0077, IllegalAccessException -> 0x0048, NoSuchMethodException -> 0x003c }
            r6 = 1
            java.lang.Class[] r7 = new java.lang.Class[r6]     // Catch:{ InvocationTargetException -> 0x0077, IllegalAccessException -> 0x0048, NoSuchMethodException -> 0x003c }
            java.lang.reflect.Field r8 = r9.field     // Catch:{ InvocationTargetException -> 0x0077, IllegalAccessException -> 0x0048, NoSuchMethodException -> 0x003c }
            java.lang.Class r8 = r8.getType()     // Catch:{ InvocationTargetException -> 0x0077, IllegalAccessException -> 0x0048, NoSuchMethodException -> 0x003c }
            r7[r2] = r8     // Catch:{ InvocationTargetException -> 0x0077, IllegalAccessException -> 0x0048, NoSuchMethodException -> 0x003c }
            java.lang.reflect.Method r3 = r4.getMethod(r5, r7)     // Catch:{ InvocationTargetException -> 0x0077, IllegalAccessException -> 0x0048, NoSuchMethodException -> 0x003c }
            r1.enableAccess(r3)     // Catch:{ InvocationTargetException -> 0x0077, IllegalAccessException -> 0x0048, NoSuchMethodException -> 0x003c }
            java.lang.Object r4 = r9.target     // Catch:{ InvocationTargetException -> 0x0077, IllegalAccessException -> 0x0048, NoSuchMethodException -> 0x003c }
            java.lang.Object[] r5 = new java.lang.Object[r6]     // Catch:{ InvocationTargetException -> 0x0077, IllegalAccessException -> 0x0048, NoSuchMethodException -> 0x003c }
            r5[r2] = r10     // Catch:{ InvocationTargetException -> 0x0077, IllegalAccessException -> 0x0048, NoSuchMethodException -> 0x003c }
            r3.invoke(r4, r5)     // Catch:{ InvocationTargetException -> 0x0077, IllegalAccessException -> 0x0048, NoSuchMethodException -> 0x003c }
            if (r3 == 0) goto L_0x0039
            r1.safelyDisableAccess(r3)
        L_0x0039:
            return r6
        L_0x003a:
            r10 = move-exception
            goto L_0x00b0
        L_0x003c:
            r9.reportNoSetterFound()     // Catch:{ all -> 0x003a }
            if (r3 == 0) goto L_0x0044
            r1.safelyDisableAccess(r3)
        L_0x0044:
            r9.reportNoSetterFound()
            return r2
        L_0x0048:
            r2 = move-exception
            java.lang.RuntimeException r4 = new java.lang.RuntimeException     // Catch:{ all -> 0x003a }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x003a }
            r5.<init>()     // Catch:{ all -> 0x003a }
            java.lang.String r6 = "Access not authorized on field '"
            r5.append(r6)     // Catch:{ all -> 0x003a }
            java.lang.reflect.Field r6 = r9.field     // Catch:{ all -> 0x003a }
            r5.append(r6)     // Catch:{ all -> 0x003a }
            java.lang.String r6 = "' of object '"
            r5.append(r6)     // Catch:{ all -> 0x003a }
            java.lang.Object r6 = r9.target     // Catch:{ all -> 0x003a }
            r5.append(r6)     // Catch:{ all -> 0x003a }
            java.lang.String r6 = "' with value: '"
            r5.append(r6)     // Catch:{ all -> 0x003a }
            r5.append(r10)     // Catch:{ all -> 0x003a }
            r5.append(r0)     // Catch:{ all -> 0x003a }
            java.lang.String r10 = r5.toString()     // Catch:{ all -> 0x003a }
            r4.<init>(r10, r2)     // Catch:{ all -> 0x003a }
            throw r4     // Catch:{ all -> 0x003a }
        L_0x0077:
            r2 = move-exception
            java.lang.RuntimeException r4 = new java.lang.RuntimeException     // Catch:{ all -> 0x003a }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x003a }
            r5.<init>()     // Catch:{ all -> 0x003a }
            java.lang.String r6 = "Setter '"
            r5.append(r6)     // Catch:{ all -> 0x003a }
            r5.append(r3)     // Catch:{ all -> 0x003a }
            java.lang.String r6 = "' of '"
            r5.append(r6)     // Catch:{ all -> 0x003a }
            java.lang.Object r6 = r9.target     // Catch:{ all -> 0x003a }
            r5.append(r6)     // Catch:{ all -> 0x003a }
            java.lang.String r6 = "' with value '"
            r5.append(r6)     // Catch:{ all -> 0x003a }
            r5.append(r10)     // Catch:{ all -> 0x003a }
            java.lang.String r10 = "' threw exception : '"
            r5.append(r10)     // Catch:{ all -> 0x003a }
            java.lang.Throwable r10 = r2.getTargetException()     // Catch:{ all -> 0x003a }
            r5.append(r10)     // Catch:{ all -> 0x003a }
            r5.append(r0)     // Catch:{ all -> 0x003a }
            java.lang.String r10 = r5.toString()     // Catch:{ all -> 0x003a }
            r4.<init>(r10, r2)     // Catch:{ all -> 0x003a }
            throw r4     // Catch:{ all -> 0x003a }
        L_0x00b0:
            if (r3 == 0) goto L_0x00b5
            r1.safelyDisableAccess(r3)
        L_0x00b5:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mockito.internal.util.reflection.BeanPropertySetter.set(java.lang.Object):boolean");
    }

    private String setterName(String str) {
        return SET_PREFIX + str.substring(0, 1).toUpperCase(Locale.ENGLISH) + str.substring(1);
    }

    private void reportNoSetterFound() {
        if (this.reportNoSetterFound) {
            throw new RuntimeException("Problems setting value on object: [" + this.target + "] for property : [" + this.field.getName() + "], setter not found");
        }
    }
}
