package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

public class BeanUtil {
    public static String okNameForGetter(AnnotatedMethod annotatedMethod, boolean z) {
        String name = annotatedMethod.getName();
        String okNameForIsGetter = okNameForIsGetter(annotatedMethod, name, z);
        return okNameForIsGetter == null ? okNameForRegularGetter(annotatedMethod, name, z) : okNameForIsGetter;
    }

    public static String okNameForRegularGetter(AnnotatedMethod annotatedMethod, String str, boolean z) {
        if (!str.startsWith("get")) {
            return null;
        }
        if ("getCallbacks".equals(str)) {
            if (isCglibGetCallbacks(annotatedMethod)) {
                return null;
            }
        } else if ("getMetaClass".equals(str) && isGroovyMetaClassGetter(annotatedMethod)) {
            return null;
        }
        return z ? stdManglePropertyName(str, 3) : legacyManglePropertyName(str, 3);
    }

    public static String okNameForIsGetter(AnnotatedMethod annotatedMethod, String str, boolean z) {
        if (!str.startsWith("is")) {
            return null;
        }
        Class<?> rawType = annotatedMethod.getRawType();
        if (rawType == Boolean.class || rawType == Boolean.TYPE) {
            return z ? stdManglePropertyName(str, 2) : legacyManglePropertyName(str, 2);
        }
        return null;
    }

    public static String okNameForSetter(AnnotatedMethod annotatedMethod, boolean z) {
        String okNameForMutator = okNameForMutator(annotatedMethod, "set", z);
        if (okNameForMutator == null) {
            return null;
        }
        if (!"metaClass".equals(okNameForMutator) || !isGroovyMetaClassSetter(annotatedMethod)) {
            return okNameForMutator;
        }
        return null;
    }

    public static String okNameForMutator(AnnotatedMethod annotatedMethod, String str, boolean z) {
        String name = annotatedMethod.getName();
        if (!name.startsWith(str)) {
            return null;
        }
        int length = str.length();
        return z ? stdManglePropertyName(name, length) : legacyManglePropertyName(name, length);
    }

    @Deprecated
    public static String okNameForGetter(AnnotatedMethod annotatedMethod) {
        return okNameForGetter(annotatedMethod, false);
    }

    @Deprecated
    public static String okNameForRegularGetter(AnnotatedMethod annotatedMethod, String str) {
        return okNameForRegularGetter(annotatedMethod, str, false);
    }

    @Deprecated
    public static String okNameForIsGetter(AnnotatedMethod annotatedMethod, String str) {
        return okNameForIsGetter(annotatedMethod, str, false);
    }

    @Deprecated
    public static String okNameForSetter(AnnotatedMethod annotatedMethod) {
        return okNameForSetter(annotatedMethod, false);
    }

    @Deprecated
    public static String okNameForMutator(AnnotatedMethod annotatedMethod, String str) {
        return okNameForMutator(annotatedMethod, str, false);
    }

    protected static boolean isCglibGetCallbacks(AnnotatedMethod annotatedMethod) {
        String packageName;
        Class<?> rawType = annotatedMethod.getRawType();
        if (rawType == null || !rawType.isArray() || (packageName = ClassUtil.getPackageName(rawType.getComponentType())) == null || !packageName.contains(".cglib") || (!packageName.startsWith("net.sf.cglib") && !packageName.startsWith("org.hibernate.repackage.cglib") && !packageName.startsWith("org.springframework.cglib"))) {
            return false;
        }
        return true;
    }

    protected static boolean isGroovyMetaClassSetter(AnnotatedMethod annotatedMethod) {
        String packageName = ClassUtil.getPackageName(annotatedMethod.getRawParameterType(0));
        if (packageName == null || !packageName.startsWith("groovy.lang")) {
            return false;
        }
        return true;
    }

    protected static boolean isGroovyMetaClassGetter(AnnotatedMethod annotatedMethod) {
        String packageName;
        Class<?> rawType = annotatedMethod.getRawType();
        if (rawType == null || rawType.isArray() || (packageName = ClassUtil.getPackageName(rawType)) == null || !packageName.startsWith("groovy.lang")) {
            return false;
        }
        return true;
    }

    protected static String legacyManglePropertyName(String str, int i) {
        int length = str.length();
        if (length == i) {
            return null;
        }
        char charAt = str.charAt(i);
        char lowerCase = Character.toLowerCase(charAt);
        if (charAt == lowerCase) {
            return str.substring(i);
        }
        StringBuilder sb = new StringBuilder(length - i);
        sb.append(lowerCase);
        while (true) {
            i++;
            if (i >= length) {
                break;
            }
            char charAt2 = str.charAt(i);
            char lowerCase2 = Character.toLowerCase(charAt2);
            if (charAt2 == lowerCase2) {
                sb.append(str, i, length);
                break;
            }
            sb.append(lowerCase2);
        }
        return sb.toString();
    }

    protected static String stdManglePropertyName(String str, int i) {
        int length = str.length();
        if (length == i) {
            return null;
        }
        char charAt = str.charAt(i);
        char lowerCase = Character.toLowerCase(charAt);
        if (charAt == lowerCase) {
            return str.substring(i);
        }
        int i2 = i + 1;
        if (i2 < length && Character.isUpperCase(str.charAt(i2))) {
            return str.substring(i);
        }
        StringBuilder sb = new StringBuilder(length - i);
        sb.append(lowerCase);
        sb.append(str, i2, length);
        return sb.toString();
    }
}
