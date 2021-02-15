package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.annotation.NoClass;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.medscape.android.settings.Settings;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class ClassUtil {
    /* access modifiers changed from: private */
    public static final Class<?> CLS_OBJECT = Object.class;
    private static final LRUMap<Class<?>, ClassMetadata> sCached = new LRUMap<>(48, 48);

    public static <T> Iterator<T> emptyIterator() {
        return Collections.emptyIterator();
    }

    public static List<JavaType> findSuperTypes(JavaType javaType, Class<?> cls, boolean z) {
        if (javaType == null || javaType.hasRawClass(cls) || javaType.hasRawClass(Object.class)) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(8);
        _addSuperTypes(javaType, cls, arrayList, z);
        return arrayList;
    }

    public static List<Class<?>> findRawSuperTypes(Class<?> cls, Class<?> cls2, boolean z) {
        if (cls == null || cls == cls2 || cls == Object.class) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(8);
        _addRawSuperTypes(cls, cls2, arrayList, z);
        return arrayList;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.lang.Class<?>, code=java.lang.Class, for r1v0, types: [java.lang.Class<?>, java.lang.Object] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<java.lang.Class<?>> findSuperClasses(java.lang.Class r1, java.lang.Class<?> r2, boolean r3) {
        /*
            java.util.LinkedList r0 = new java.util.LinkedList
            r0.<init>()
            if (r1 == 0) goto L_0x001b
            if (r1 == r2) goto L_0x001b
            if (r3 == 0) goto L_0x000e
            r0.add(r1)
        L_0x000e:
            java.lang.Class r1 = r1.getSuperclass()
            if (r1 == 0) goto L_0x001b
            if (r1 != r2) goto L_0x0017
            goto L_0x001b
        L_0x0017:
            r0.add(r1)
            goto L_0x000e
        L_0x001b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.util.ClassUtil.findSuperClasses(java.lang.Class, java.lang.Class, boolean):java.util.List");
    }

    @Deprecated
    public static List<Class<?>> findSuperTypes(Class<?> cls, Class<?> cls2) {
        return findSuperTypes(cls, cls2, (List<Class<?>>) new ArrayList(8));
    }

    @Deprecated
    public static List<Class<?>> findSuperTypes(Class<?> cls, Class<?> cls2, List<Class<?>> list) {
        _addRawSuperTypes(cls, cls2, list, false);
        return list;
    }

    private static void _addSuperTypes(JavaType javaType, Class<?> cls, Collection<JavaType> collection, boolean z) {
        Class<?> rawClass;
        if (javaType != null && (rawClass = javaType.getRawClass()) != cls && rawClass != Object.class) {
            if (z) {
                if (!collection.contains(javaType)) {
                    collection.add(javaType);
                } else {
                    return;
                }
            }
            for (JavaType _addSuperTypes : javaType.getInterfaces()) {
                _addSuperTypes(_addSuperTypes, cls, collection, true);
            }
            _addSuperTypes(javaType.getSuperClass(), cls, collection, true);
        }
    }

    private static void _addRawSuperTypes(Class<?> cls, Class<?> cls2, Collection<Class<?>> collection, boolean z) {
        if (cls != cls2 && cls != null && cls != Object.class) {
            if (z) {
                if (!collection.contains(cls)) {
                    collection.add(cls);
                } else {
                    return;
                }
            }
            for (Class _addRawSuperTypes : _interfaces(cls)) {
                _addRawSuperTypes(_addRawSuperTypes, cls2, collection, true);
            }
            _addRawSuperTypes(cls.getSuperclass(), cls2, collection, true);
        }
    }

    public static String canBeABeanType(Class<?> cls) {
        if (cls.isAnnotation()) {
            return "annotation";
        }
        if (cls.isArray()) {
            return "array";
        }
        if (cls.isEnum()) {
            return "enum";
        }
        if (cls.isPrimitive()) {
            return "primitive";
        }
        return null;
    }

    public static String isLocalType(Class<?> cls, boolean z) {
        try {
            if (hasEnclosingMethod(cls)) {
                return "local/anonymous";
            }
            if (z || Modifier.isStatic(cls.getModifiers()) || getEnclosingClass(cls) == null) {
                return null;
            }
            return "non-static member class";
        } catch (NullPointerException | SecurityException unused) {
            return null;
        }
    }

    public static Class<?> getOuterClass(Class<?> cls) {
        try {
            if (!hasEnclosingMethod(cls) && !Modifier.isStatic(cls.getModifiers())) {
                return getEnclosingClass(cls);
            }
        } catch (SecurityException unused) {
        }
        return null;
    }

    public static boolean isProxyType(Class<?> cls) {
        String name = cls.getName();
        return name.startsWith("net.sf.cglib.proxy.") || name.startsWith("org.hibernate.proxy.");
    }

    public static boolean isConcrete(Class<?> cls) {
        return (cls.getModifiers() & 1536) == 0;
    }

    public static boolean isConcrete(Member member) {
        return (member.getModifiers() & 1536) == 0;
    }

    public static boolean isCollectionMapOrArray(Class<?> cls) {
        if (!cls.isArray() && !Collection.class.isAssignableFrom(cls) && !Map.class.isAssignableFrom(cls)) {
            return false;
        }
        return true;
    }

    public static String getClassDescription(Object obj) {
        if (obj == null) {
            return "unknown";
        }
        return (obj instanceof Class ? (Class) obj : obj.getClass()).getName();
    }

    @Deprecated
    public static Class<?> findClass(String str) throws ClassNotFoundException {
        if (str.indexOf(46) < 0) {
            if ("int".equals(str)) {
                return Integer.TYPE;
            }
            if (Settings.LONGITUDE_KEY.equals(str)) {
                return Long.TYPE;
            }
            if ("float".equals(str)) {
                return Float.TYPE;
            }
            if ("double".equals(str)) {
                return Double.TYPE;
            }
            if ("boolean".equals(str)) {
                return Boolean.TYPE;
            }
            if ("byte".equals(str)) {
                return Byte.TYPE;
            }
            if ("char".equals(str)) {
                return Character.TYPE;
            }
            if ("short".equals(str)) {
                return Short.TYPE;
            }
            if ("void".equals(str)) {
                return Void.TYPE;
            }
        }
        Throwable th = null;
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader != null) {
            try {
                return Class.forName(str, true, contextClassLoader);
            } catch (Exception e) {
                th = getRootCause(e);
            }
        }
        try {
            return Class.forName(str);
        } catch (Exception e2) {
            if (th == null) {
                th = getRootCause(e2);
            }
            if (th instanceof RuntimeException) {
                throw ((RuntimeException) th);
            }
            throw new ClassNotFoundException(th.getMessage(), th);
        }
    }

    public static String getPackageName(Class<?> cls) {
        return _getMetadata(cls).getPackageName();
    }

    public static boolean hasEnclosingMethod(Class<?> cls) {
        return _getMetadata(cls).hasEnclosingMethod();
    }

    public static Field[] getDeclaredFields(Class<?> cls) {
        return _getMetadata(cls).getDeclaredFields();
    }

    public static Method[] getDeclaredMethods(Class<?> cls) {
        return _getMetadata(cls).getDeclaredMethods();
    }

    public static Annotation[] findClassAnnotations(Class<?> cls) {
        return _getMetadata(cls).getDeclaredAnnotations();
    }

    public static Ctor[] getConstructors(Class<?> cls) {
        return _getMetadata(cls).getConstructors();
    }

    public static Class<?> getDeclaringClass(Class<?> cls) {
        if (isObjectOrPrimitive(cls)) {
            return null;
        }
        return cls.getDeclaringClass();
    }

    public static Type getGenericSuperclass(Class<?> cls) {
        return cls.getGenericSuperclass();
    }

    public static Type[] getGenericInterfaces(Class<?> cls) {
        return _getMetadata(cls).getGenericInterfaces();
    }

    public static Class<?> getEnclosingClass(Class<?> cls) {
        if (isObjectOrPrimitive(cls)) {
            return null;
        }
        return cls.getEnclosingClass();
    }

    private static Class<?>[] _interfaces(Class<?> cls) {
        return _getMetadata(cls).getInterfaces();
    }

    private static ClassMetadata _getMetadata(Class<?> cls) {
        ClassMetadata classMetadata = sCached.get(cls);
        if (classMetadata != null) {
            return classMetadata;
        }
        ClassMetadata classMetadata2 = new ClassMetadata(cls);
        ClassMetadata putIfAbsent = sCached.putIfAbsent(cls, classMetadata2);
        return putIfAbsent != null ? putIfAbsent : classMetadata2;
    }

    @Deprecated
    public static boolean hasGetterSignature(Method method) {
        if (Modifier.isStatic(method.getModifiers())) {
            return false;
        }
        Class[] parameterTypes = method.getParameterTypes();
        if ((parameterTypes == null || parameterTypes.length == 0) && Void.TYPE != method.getReturnType()) {
            return true;
        }
        return false;
    }

    public static Throwable getRootCause(Throwable th) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        return th;
    }

    public static void throwRootCause(Throwable th) throws Exception {
        Throwable rootCause = getRootCause(th);
        if (rootCause instanceof Exception) {
            throw ((Exception) rootCause);
        }
        throw ((Error) rootCause);
    }

    public static void throwAsIAE(Throwable th) {
        throwAsIAE(th, th.getMessage());
    }

    public static void throwAsIAE(Throwable th, String str) {
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        } else if (th instanceof Error) {
            throw ((Error) th);
        } else {
            throw new IllegalArgumentException(str, th);
        }
    }

    public static void unwrapAndThrowAsIAE(Throwable th) {
        throwAsIAE(getRootCause(th));
    }

    public static void unwrapAndThrowAsIAE(Throwable th, String str) {
        throwAsIAE(getRootCause(th), str);
    }

    public static <T> T createInstance(Class<T> cls, boolean z) throws IllegalArgumentException {
        Constructor<T> findConstructor = findConstructor(cls, z);
        if (findConstructor != null) {
            try {
                return findConstructor.newInstance(new Object[0]);
            } catch (Exception e) {
                unwrapAndThrowAsIAE(e, "Failed to instantiate class " + cls.getName() + ", problem: " + e.getMessage());
                return null;
            }
        } else {
            throw new IllegalArgumentException("Class " + cls.getName() + " has no default (no arg) constructor");
        }
    }

    public static <T> Constructor<T> findConstructor(Class<T> cls, boolean z) throws IllegalArgumentException {
        try {
            Constructor<T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (z) {
                checkAndFixAccess(declaredConstructor);
            } else if (!Modifier.isPublic(declaredConstructor.getModifiers())) {
                throw new IllegalArgumentException("Default constructor for " + cls.getName() + " is not accessible (non-public?): not allowed to try modify access via Reflection: can not instantiate type");
            }
            return declaredConstructor;
        } catch (NoSuchMethodException unused) {
            return null;
        } catch (Exception e) {
            unwrapAndThrowAsIAE(e, "Failed to find default constructor of class " + cls.getName() + ", problem: " + e.getMessage());
            return null;
        }
    }

    public static Object defaultValue(Class<?> cls) {
        if (cls == Integer.TYPE) {
            return 0;
        }
        if (cls == Long.TYPE) {
            return 0L;
        }
        if (cls == Boolean.TYPE) {
            return Boolean.FALSE;
        }
        if (cls == Double.TYPE) {
            return Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        }
        if (cls == Float.TYPE) {
            return Float.valueOf(0.0f);
        }
        if (cls == Byte.TYPE) {
            return (byte) 0;
        }
        if (cls == Short.TYPE) {
            return (short) 0;
        }
        if (cls == Character.TYPE) {
            return 0;
        }
        throw new IllegalArgumentException("Class " + cls.getName() + " is not a primitive type");
    }

    public static Class<?> wrapperType(Class<?> cls) {
        if (cls == Integer.TYPE) {
            return Integer.class;
        }
        if (cls == Long.TYPE) {
            return Long.class;
        }
        if (cls == Boolean.TYPE) {
            return Boolean.class;
        }
        if (cls == Double.TYPE) {
            return Double.class;
        }
        if (cls == Float.TYPE) {
            return Float.class;
        }
        if (cls == Byte.TYPE) {
            return Byte.class;
        }
        if (cls == Short.TYPE) {
            return Short.class;
        }
        if (cls == Character.TYPE) {
            return Character.class;
        }
        throw new IllegalArgumentException("Class " + cls.getName() + " is not a primitive type");
    }

    public static Class<?> primitiveType(Class<?> cls) {
        if (cls.isPrimitive()) {
            return cls;
        }
        if (cls == Integer.class) {
            return Integer.TYPE;
        }
        if (cls == Long.class) {
            return Long.TYPE;
        }
        if (cls == Boolean.class) {
            return Boolean.TYPE;
        }
        if (cls == Double.class) {
            return Double.TYPE;
        }
        if (cls == Float.class) {
            return Float.TYPE;
        }
        if (cls == Byte.class) {
            return Byte.TYPE;
        }
        if (cls == Short.class) {
            return Short.TYPE;
        }
        if (cls == Character.class) {
            return Character.TYPE;
        }
        return null;
    }

    @Deprecated
    public static void checkAndFixAccess(Member member) {
        checkAndFixAccess(member, false);
    }

    public static void checkAndFixAccess(Member member, boolean z) {
        AccessibleObject accessibleObject = (AccessibleObject) member;
        if (!z) {
            try {
                if (Modifier.isPublic(member.getModifiers()) && Modifier.isPublic(member.getDeclaringClass().getModifiers())) {
                    return;
                }
            } catch (SecurityException e) {
                if (!accessibleObject.isAccessible()) {
                    Class<?> declaringClass = member.getDeclaringClass();
                    throw new IllegalArgumentException("Can not access " + member + " (from class " + declaringClass.getName() + "; failed to set access: " + e.getMessage());
                }
                return;
            }
        }
        accessibleObject.setAccessible(true);
    }

    public static Class<? extends Enum<?>> findEnumType(EnumSet<?> enumSet) {
        if (!enumSet.isEmpty()) {
            return findEnumType((Enum<?>) (Enum) enumSet.iterator().next());
        }
        return EnumTypeLocator.instance.enumTypeFor(enumSet);
    }

    public static Class<? extends Enum<?>> findEnumType(EnumMap<?, ?> enumMap) {
        if (!enumMap.isEmpty()) {
            return findEnumType((Enum<?>) (Enum) enumMap.keySet().iterator().next());
        }
        return EnumTypeLocator.instance.enumTypeFor(enumMap);
    }

    public static Class<? extends Enum<?>> findEnumType(Enum<?> enumR) {
        Class<?> cls = enumR.getClass();
        return cls.getSuperclass() != Enum.class ? cls.getSuperclass() : cls;
    }

    public static Class<? extends Enum<?>> findEnumType(Class<?> cls) {
        return cls.getSuperclass() != Enum.class ? cls.getSuperclass() : cls;
    }

    public static boolean isJacksonStdImpl(Object obj) {
        return obj != null && isJacksonStdImpl(obj.getClass());
    }

    public static boolean isJacksonStdImpl(Class<?> cls) {
        return cls.getAnnotation(JacksonStdImpl.class) != null;
    }

    public static boolean isBogusClass(Class<?> cls) {
        return cls == Void.class || cls == Void.TYPE || cls == NoClass.class;
    }

    public static boolean isNonStaticInnerClass(Class<?> cls) {
        return !Modifier.isStatic(cls.getModifiers()) && getEnclosingClass(cls) != null;
    }

    public static boolean isObjectOrPrimitive(Class<?> cls) {
        return cls == CLS_OBJECT || cls.isPrimitive();
    }

    private static class EnumTypeLocator {
        static final EnumTypeLocator instance = new EnumTypeLocator();
        private final Field enumMapTypeField = locateField(EnumMap.class, "elementType", Class.class);
        private final Field enumSetTypeField = locateField(EnumSet.class, "elementType", Class.class);

        private EnumTypeLocator() {
        }

        public Class<? extends Enum<?>> enumTypeFor(EnumSet<?> enumSet) {
            Field field = this.enumSetTypeField;
            if (field != null) {
                return (Class) get(enumSet, field);
            }
            throw new IllegalStateException("Can not figure out type for EnumSet (odd JDK platform?)");
        }

        public Class<? extends Enum<?>> enumTypeFor(EnumMap<?, ?> enumMap) {
            Field field = this.enumMapTypeField;
            if (field != null) {
                return (Class) get(enumMap, field);
            }
            throw new IllegalStateException("Can not figure out type for EnumMap (odd JDK platform?)");
        }

        private Object get(Object obj, Field field) {
            try {
                return field.get(obj);
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }

        private static Field locateField(Class<?> cls, String str, Class<?> cls2) {
            Field field;
            Field[] declaredFields = ClassUtil.getDeclaredFields(cls);
            int length = declaredFields.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    field = null;
                    break;
                }
                field = declaredFields[i];
                if (str.equals(field.getName()) && field.getType() == cls2) {
                    break;
                }
                i++;
            }
            if (field == null) {
                for (Field field2 : declaredFields) {
                    if (field2.getType() == cls2) {
                        if (field != null) {
                            return null;
                        }
                        field = field2;
                    }
                }
            }
            if (field != null) {
                try {
                    field.setAccessible(true);
                } catch (Throwable unused) {
                }
            }
            return field;
        }
    }

    private static final class ClassMetadata {
        private static final Annotation[] NO_ANNOTATIONS = new Annotation[0];
        private static final Ctor[] NO_CTORS = new Ctor[0];
        private Annotation[] _annotations;
        private Ctor[] _constructors;
        private Field[] _fields;
        private final Class<?> _forClass;
        private Type[] _genericInterfaces;
        private Boolean _hasEnclosingMethod;
        private Class<?>[] _interfaces;
        private Method[] _methods;
        private String _packageName;

        public ClassMetadata(Class<?> cls) {
            this._forClass = cls;
        }

        public String getPackageName() {
            String str = this._packageName;
            if (str == null) {
                Package packageR = this._forClass.getPackage();
                if (packageR == null) {
                    str = null;
                } else {
                    str = packageR.getName();
                }
                if (str == null) {
                    str = "";
                }
                this._packageName = str;
            }
            if (str == "") {
                return null;
            }
            return str;
        }

        public Class<?>[] getInterfaces() {
            Class<?>[] clsArr = this._interfaces;
            if (clsArr != null) {
                return clsArr;
            }
            Class<?>[] interfaces = this._forClass.getInterfaces();
            this._interfaces = interfaces;
            return interfaces;
        }

        public Type[] getGenericInterfaces() {
            Type[] typeArr = this._genericInterfaces;
            if (typeArr != null) {
                return typeArr;
            }
            Type[] genericInterfaces = this._forClass.getGenericInterfaces();
            this._genericInterfaces = genericInterfaces;
            return genericInterfaces;
        }

        public Annotation[] getDeclaredAnnotations() {
            Annotation[] annotationArr = this._annotations;
            if (annotationArr == null) {
                annotationArr = isObjectOrPrimitive() ? NO_ANNOTATIONS : this._forClass.getDeclaredAnnotations();
                this._annotations = annotationArr;
            }
            return annotationArr;
        }

        public Ctor[] getConstructors() {
            Ctor[] ctorArr = this._constructors;
            if (ctorArr == null) {
                if (this._forClass.isInterface() || isObjectOrPrimitive()) {
                    ctorArr = NO_CTORS;
                } else {
                    Constructor[] declaredConstructors = this._forClass.getDeclaredConstructors();
                    int length = declaredConstructors.length;
                    Ctor[] ctorArr2 = new Ctor[length];
                    for (int i = 0; i < length; i++) {
                        ctorArr2[i] = new Ctor(declaredConstructors[i]);
                    }
                    ctorArr = ctorArr2;
                }
                this._constructors = ctorArr;
            }
            return ctorArr;
        }

        public Field[] getDeclaredFields() {
            Field[] fieldArr = this._fields;
            if (fieldArr != null) {
                return fieldArr;
            }
            Field[] declaredFields = this._forClass.getDeclaredFields();
            this._fields = declaredFields;
            return declaredFields;
        }

        public Method[] getDeclaredMethods() {
            Method[] methodArr = this._methods;
            if (methodArr != null) {
                return methodArr;
            }
            Method[] declaredMethods = this._forClass.getDeclaredMethods();
            this._methods = declaredMethods;
            return declaredMethods;
        }

        public boolean hasEnclosingMethod() {
            Boolean bool = this._hasEnclosingMethod;
            if (bool == null) {
                if (isObjectOrPrimitive()) {
                    bool = Boolean.FALSE;
                } else {
                    bool = Boolean.valueOf(this._forClass.getEnclosingMethod() != null);
                }
                this._hasEnclosingMethod = bool;
            }
            return bool.booleanValue();
        }

        private boolean isObjectOrPrimitive() {
            return this._forClass == ClassUtil.CLS_OBJECT || this._forClass.isPrimitive();
        }
    }

    public static final class Ctor {
        private Annotation[] _annotations;
        public final Constructor<?> _ctor;
        private Annotation[][] _paramAnnotations;
        private int _paramCount = -1;

        public Ctor(Constructor<?> constructor) {
            this._ctor = constructor;
        }

        public Constructor<?> getConstructor() {
            return this._ctor;
        }

        public int getParamCount() {
            int i = this._paramCount;
            if (i >= 0) {
                return i;
            }
            int length = this._ctor.getParameterTypes().length;
            this._paramCount = length;
            return length;
        }

        public Class<?> getDeclaringClass() {
            return this._ctor.getDeclaringClass();
        }

        public Annotation[] getDeclaredAnnotations() {
            Annotation[] annotationArr = this._annotations;
            if (annotationArr != null) {
                return annotationArr;
            }
            Annotation[] declaredAnnotations = this._ctor.getDeclaredAnnotations();
            this._annotations = declaredAnnotations;
            return declaredAnnotations;
        }

        public Annotation[][] getParameterAnnotations() {
            Annotation[][] annotationArr = this._paramAnnotations;
            if (annotationArr != null) {
                return annotationArr;
            }
            Annotation[][] parameterAnnotations = this._ctor.getParameterAnnotations();
            this._paramAnnotations = parameterAnnotations;
            return parameterAnnotations;
        }
    }
}
