package net.bytebuddy.description.annotation;

import com.dd.plist.ASCIIPropertyListParser;
import java.lang.annotation.Annotation;
import java.lang.annotation.AnnotationTypeMismatchException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.IncompleteAnnotationException;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.security.AccessController;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.privilege.SetAccessibleAction;

public interface AnnotationDescription {
    public static final Loadable<?> UNDEFINED = null;

    public interface Loadable<S extends Annotation> extends AnnotationDescription {
        S load() throws ClassNotFoundException;

        S loadSilent();
    }

    TypeDescription getAnnotationType();

    Set<ElementType> getElementTypes();

    RetentionPolicy getRetention();

    AnnotationValue<?, ?> getValue(MethodDescription.InDefinedShape inDefinedShape);

    boolean isDocumented();

    boolean isInherited();

    <T extends Annotation> Loadable<T> prepare(Class<T> cls);

    public static class AnnotationInvocationHandler<T extends Annotation> implements InvocationHandler {
        private static final String EQUALS = "equals";
        private static final String HASH_CODE = "hashCode";
        private static final Object[] NO_ARGUMENTS = new Object[0];
        private static final String TO_STRING = "toString";
        private final Class<? extends Annotation> annotationType;
        private final LinkedHashMap<Method, AnnotationValue.Loaded<?>> values;

        protected AnnotationInvocationHandler(Class<T> cls, LinkedHashMap<Method, AnnotationValue.Loaded<?>> linkedHashMap) {
            this.annotationType = cls;
            this.values = linkedHashMap;
        }

        public static <S extends Annotation> S of(ClassLoader classLoader, Class<S> cls, Map<String, ? extends AnnotationValue<?, ?>> map) throws ClassNotFoundException {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Method method : cls.getDeclaredMethods()) {
                AnnotationValue<?, ?> annotationValue = (AnnotationValue) map.get(method.getName());
                if (annotationValue == null) {
                    annotationValue = defaultValueOf(method);
                }
                linkedHashMap.put(method, annotationValue.load(classLoader));
            }
            return (Annotation) Proxy.newProxyInstance(classLoader, new Class[]{cls}, new AnnotationInvocationHandler(cls, linkedHashMap));
        }

        private static AnnotationValue<?, ?> defaultValueOf(Method method) {
            Object defaultValue = method.getDefaultValue();
            if (defaultValue == null) {
                return MissingValue.of(method);
            }
            return ForLoadedAnnotation.asValue(defaultValue, method.getReturnType());
        }

        private static Class<?> asWrapper(Class<?> cls) {
            if (!cls.isPrimitive()) {
                return cls;
            }
            if (cls == Boolean.TYPE) {
                return Boolean.class;
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
            if (cls == Integer.TYPE) {
                return Integer.class;
            }
            if (cls == Long.TYPE) {
                return Long.class;
            }
            if (cls == Float.TYPE) {
                return Float.class;
            }
            return cls == Double.TYPE ? Double.class : cls;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) {
            if (method.getDeclaringClass() == this.annotationType) {
                Object resolve = this.values.get(method).resolve();
                if (asWrapper(method.getReturnType()).isAssignableFrom(resolve.getClass())) {
                    return resolve;
                }
                throw new AnnotationTypeMismatchException(method, resolve.getClass().toString());
            } else if (method.getName().equals(HASH_CODE)) {
                return Integer.valueOf(hashCodeRepresentation());
            } else {
                if (method.getName().equals(EQUALS) && method.getParameterTypes().length == 1) {
                    return Boolean.valueOf(equalsRepresentation(obj, objArr[0]));
                }
                if (method.getName().equals(TO_STRING)) {
                    return toStringRepresentation();
                }
                return this.annotationType;
            }
        }

        /* access modifiers changed from: protected */
        public String toStringRepresentation() {
            StringBuilder sb = new StringBuilder();
            sb.append('@');
            sb.append(this.annotationType.getName());
            sb.append(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN);
            boolean z = true;
            for (Map.Entry next : this.values.entrySet()) {
                if (((AnnotationValue.Loaded) next.getValue()).getState().isDefined()) {
                    if (z) {
                        z = false;
                    } else {
                        sb.append(", ");
                    }
                    sb.append(((Method) next.getKey()).getName());
                    sb.append('=');
                    sb.append(((AnnotationValue.Loaded) next.getValue()).toString());
                }
            }
            sb.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
            return sb.toString();
        }

        private int hashCodeRepresentation() {
            int i = 0;
            for (Map.Entry next : this.values.entrySet()) {
                if (((AnnotationValue.Loaded) next.getValue()).getState().isDefined()) {
                    i += ((AnnotationValue.Loaded) next.getValue()).hashCode() ^ (((Method) next.getKey()).getName().hashCode() * 127);
                }
            }
            return i;
        }

        private boolean equalsRepresentation(Object obj, Object obj2) {
            if (obj == obj2) {
                return true;
            }
            if (!this.annotationType.isInstance(obj2)) {
                return false;
            }
            if (Proxy.isProxyClass(obj2.getClass())) {
                InvocationHandler invocationHandler = Proxy.getInvocationHandler(obj2);
                if (invocationHandler instanceof AnnotationInvocationHandler) {
                    return invocationHandler.equals(this);
                }
            }
            try {
                for (Map.Entry next : this.values.entrySet()) {
                    try {
                        if (!((AnnotationValue.Loaded) next.getValue()).represents(((Method) next.getKey()).invoke(obj2, NO_ARGUMENTS))) {
                            return false;
                        }
                    } catch (RuntimeException unused) {
                    }
                }
                return true;
            } catch (InvocationTargetException unused2) {
                return false;
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Could not access annotation property", e);
            }
        }

        public int hashCode() {
            int hashCode = (this.annotationType.hashCode() * 31) + this.values.hashCode();
            for (Map.Entry<Method, AnnotationValue.Loaded<?>> value : this.values.entrySet()) {
                hashCode = (hashCode * 31) + value.getValue().hashCode();
            }
            return hashCode;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AnnotationInvocationHandler)) {
                return false;
            }
            AnnotationInvocationHandler annotationInvocationHandler = (AnnotationInvocationHandler) obj;
            if (!this.annotationType.equals(annotationInvocationHandler.annotationType)) {
                return false;
            }
            for (Map.Entry next : this.values.entrySet()) {
                if (!((AnnotationValue.Loaded) next.getValue()).equals(annotationInvocationHandler.values.get(next.getKey()))) {
                    return false;
                }
            }
            return true;
        }

        protected static class MissingValue extends AnnotationValue.Loaded.AbstractBase<Void> implements AnnotationValue<Void, Void> {
            private final Class<? extends Annotation> annotationType;
            private final String property;

            public AnnotationValue.Loaded<Void> load(ClassLoader classLoader) {
                return this;
            }

            public AnnotationValue.Loaded<Void> loadSilent(ClassLoader classLoader) {
                return this;
            }

            public boolean represents(Object obj) {
                return false;
            }

            protected MissingValue(Class<? extends Annotation> cls, String str) {
                this.annotationType = cls;
                this.property = str;
            }

            protected static AnnotationValue<?, ?> of(Method method) {
                return new MissingValue(method.getDeclaringClass(), method.getName());
            }

            public AnnotationValue.Loaded.State getState() {
                return AnnotationValue.Loaded.State.UNDEFINED;
            }

            public Void resolve() {
                throw new IncompleteAnnotationException(this.annotationType, this.property);
            }
        }
    }

    public static abstract class AbstractBase implements AnnotationDescription {
        private static final ElementType[] DEFAULT_TARGET = {ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.METHOD, ElementType.PACKAGE, ElementType.PARAMETER, ElementType.TYPE};

        public RetentionPolicy getRetention() {
            Loadable<Retention> ofType = getAnnotationType().getDeclaredAnnotations().ofType(Retention.class);
            if (ofType == null) {
                return RetentionPolicy.CLASS;
            }
            return ofType.loadSilent().value();
        }

        public Set<ElementType> getElementTypes() {
            ElementType[] elementTypeArr;
            Loadable<Target> ofType = getAnnotationType().getDeclaredAnnotations().ofType(Target.class);
            if (ofType == null) {
                elementTypeArr = DEFAULT_TARGET;
            } else {
                elementTypeArr = ofType.loadSilent().value();
            }
            return new HashSet(Arrays.asList(elementTypeArr));
        }

        public boolean isInherited() {
            return getAnnotationType().getDeclaredAnnotations().isAnnotationPresent((Class<? extends Annotation>) Inherited.class);
        }

        public boolean isDocumented() {
            return getAnnotationType().getDeclaredAnnotations().isAnnotationPresent((Class<? extends Annotation>) Documented.class);
        }

        public int hashCode() {
            int i = 0;
            for (MethodDescription.InDefinedShape value : getAnnotationType().getDeclaredMethods()) {
                i += getValue(value).hashCode() * 31;
            }
            return i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AnnotationDescription)) {
                return false;
            }
            AnnotationDescription annotationDescription = (AnnotationDescription) obj;
            TypeDescription annotationType = getAnnotationType();
            if (!annotationDescription.getAnnotationType().equals(annotationType)) {
                return false;
            }
            for (MethodDescription.InDefinedShape inDefinedShape : annotationType.getDeclaredMethods()) {
                if (!getValue(inDefinedShape).equals(annotationDescription.getValue(inDefinedShape))) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            TypeDescription annotationType = getAnnotationType();
            StringBuilder sb = new StringBuilder();
            sb.append('@');
            sb.append(annotationType.getName());
            sb.append(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN);
            boolean z = true;
            for (MethodDescription.InDefinedShape inDefinedShape : annotationType.getDeclaredMethods()) {
                if (z) {
                    z = false;
                } else {
                    sb.append(", ");
                }
                sb.append(inDefinedShape.getName());
                sb.append('=');
                sb.append(getValue(inDefinedShape));
            }
            sb.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
            return sb.toString();
        }

        public static abstract class ForPrepared<S extends Annotation> extends AbstractBase implements Loadable<S> {
            public S loadSilent() {
                try {
                    return load();
                } catch (ClassNotFoundException e) {
                    throw new IllegalStateException("Could not load annotation type or referenced type", e);
                }
            }
        }
    }

    public static class ForLoadedAnnotation<S extends Annotation> extends AbstractBase.ForPrepared<S> {
        private final S annotation;
        private final Class<S> annotationType;

        protected ForLoadedAnnotation(S s) {
            this(s, s.annotationType());
        }

        private ForLoadedAnnotation(S s, Class<S> cls) {
            this.annotation = s;
            this.annotationType = cls;
        }

        public static <U extends Annotation> Loadable<U> of(U u) {
            return new ForLoadedAnnotation(u);
        }

        public S load() throws ClassNotFoundException {
            if (this.annotationType == this.annotation.annotationType()) {
                return this.annotation;
            }
            return AnnotationInvocationHandler.of(this.annotationType.getClassLoader(), this.annotationType, asValue(this.annotation));
        }

        private static Map<String, AnnotationValue<?, ?>> asValue(Annotation annotation2) {
            HashMap hashMap = new HashMap();
            Method[] declaredMethods = annotation2.annotationType().getDeclaredMethods();
            int length = declaredMethods.length;
            int i = 0;
            while (i < length) {
                Method method = declaredMethods[i];
                try {
                    hashMap.put(method.getName(), asValue(method.invoke(annotation2, new Object[0]), method.getReturnType()));
                    i++;
                } catch (InvocationTargetException e) {
                    throw new IllegalStateException("Cannot read " + method, e.getCause());
                } catch (IllegalAccessException e2) {
                    throw new IllegalStateException("Cannot access " + method, e2);
                }
            }
            return hashMap;
        }

        public static AnnotationValue<?, ?> asValue(Object obj, Class<?> cls) {
            if (Enum.class.isAssignableFrom(cls)) {
                return AnnotationValue.ForEnumerationDescription.of(new EnumerationDescription.ForLoadedEnumeration((Enum) obj));
            }
            int i = 0;
            if (Enum[].class.isAssignableFrom(cls)) {
                Enum[] enumArr = (Enum[]) obj;
                EnumerationDescription[] enumerationDescriptionArr = new EnumerationDescription[enumArr.length];
                int length = enumArr.length;
                int i2 = 0;
                while (i < length) {
                    enumerationDescriptionArr[i2] = new EnumerationDescription.ForLoadedEnumeration(enumArr[i]);
                    i++;
                    i2++;
                }
                return AnnotationValue.ForDescriptionArray.of(TypeDescription.ForLoadedType.of(cls.getComponentType()), enumerationDescriptionArr);
            } else if (Annotation.class.isAssignableFrom(cls)) {
                return AnnotationValue.ForAnnotationDescription.of(TypeDescription.ForLoadedType.of(cls), asValue((Annotation) obj));
            } else {
                if (Annotation[].class.isAssignableFrom(cls)) {
                    Annotation[] annotationArr = (Annotation[]) obj;
                    AnnotationDescription[] annotationDescriptionArr = new AnnotationDescription[annotationArr.length];
                    int length2 = annotationArr.length;
                    int i3 = 0;
                    while (i < length2) {
                        annotationDescriptionArr[i3] = new Latent(TypeDescription.ForLoadedType.of(cls.getComponentType()), asValue(annotationArr[i]));
                        i++;
                        i3++;
                    }
                    return AnnotationValue.ForDescriptionArray.of(TypeDescription.ForLoadedType.of(cls.getComponentType()), annotationDescriptionArr);
                } else if (Class.class.isAssignableFrom(cls)) {
                    return AnnotationValue.ForTypeDescription.of(TypeDescription.ForLoadedType.of((Class) obj));
                } else {
                    if (!Class[].class.isAssignableFrom(cls)) {
                        return AnnotationValue.ForConstant.of(obj);
                    }
                    Class[] clsArr = (Class[]) obj;
                    TypeDescription[] typeDescriptionArr = new TypeDescription[clsArr.length];
                    int length3 = clsArr.length;
                    int i4 = 0;
                    while (i < length3) {
                        typeDescriptionArr[i4] = TypeDescription.ForLoadedType.of(clsArr[i]);
                        i++;
                        i4++;
                    }
                    return AnnotationValue.ForDescriptionArray.of(typeDescriptionArr);
                }
            }
        }

        public AnnotationValue<?, ?> getValue(MethodDescription.InDefinedShape inDefinedShape) {
            if (inDefinedShape.getDeclaringType().represents(this.annotation.annotationType())) {
                try {
                    boolean isPublic = inDefinedShape.getDeclaringType().isPublic();
                    Method loadedMethod = inDefinedShape instanceof MethodDescription.ForLoadedMethod ? ((MethodDescription.ForLoadedMethod) inDefinedShape).getLoadedMethod() : null;
                    if (loadedMethod == null || loadedMethod.getDeclaringClass() != this.annotation.annotationType() || (!isPublic && !loadedMethod.isAccessible())) {
                        loadedMethod = this.annotation.annotationType().getMethod(inDefinedShape.getName(), new Class[0]);
                        if (!isPublic) {
                            AccessController.doPrivileged(new SetAccessibleAction(loadedMethod));
                        }
                    }
                    return asValue(loadedMethod.invoke(this.annotation, new Object[0]), loadedMethod.getReturnType());
                } catch (InvocationTargetException e) {
                    throw new IllegalStateException("Error reading annotation property " + inDefinedShape, e.getCause());
                } catch (Exception e2) {
                    throw new IllegalStateException("Cannot access annotation property " + inDefinedShape, e2);
                }
            } else {
                throw new IllegalArgumentException(inDefinedShape + " does not represent " + this.annotation.annotationType());
            }
        }

        public <T extends Annotation> Loadable<T> prepare(Class<T> cls) {
            if (this.annotation.annotationType().getName().equals(cls.getName())) {
                return cls == this.annotation.annotationType() ? this : new ForLoadedAnnotation(this.annotation, cls);
            }
            throw new IllegalArgumentException(cls + " does not represent " + this.annotation.annotationType());
        }

        public TypeDescription getAnnotationType() {
            return TypeDescription.ForLoadedType.of(this.annotation.annotationType());
        }
    }

    public static class Latent extends AbstractBase {
        private final TypeDescription annotationType;
        /* access modifiers changed from: private */
        public final Map<String, ? extends AnnotationValue<?, ?>> annotationValues;

        protected Latent(TypeDescription typeDescription, Map<String, ? extends AnnotationValue<?, ?>> map) {
            this.annotationType = typeDescription;
            this.annotationValues = map;
        }

        public AnnotationValue<?, ?> getValue(MethodDescription.InDefinedShape inDefinedShape) {
            AnnotationValue<?, ?> annotationValue = (AnnotationValue) this.annotationValues.get(inDefinedShape.getName());
            if (annotationValue != null) {
                return annotationValue;
            }
            AnnotationValue<?, ?> defaultValue = inDefinedShape.getDefaultValue();
            if (defaultValue != null) {
                return defaultValue;
            }
            throw new IllegalArgumentException("No value defined for: " + inDefinedShape);
        }

        public TypeDescription getAnnotationType() {
            return this.annotationType;
        }

        public <T extends Annotation> Loadable<T> prepare(Class<T> cls) {
            if (this.annotationType.represents(cls)) {
                return new Loadable<>(cls);
            }
            throw new IllegalArgumentException(cls + " does not represent " + this.annotationType);
        }

        protected class Loadable<S extends Annotation> extends AbstractBase.ForPrepared<S> {
            private final Class<S> annotationType;

            protected Loadable(Class<S> cls) {
                this.annotationType = cls;
            }

            public S load() throws ClassNotFoundException {
                return AnnotationInvocationHandler.of(this.annotationType.getClassLoader(), this.annotationType, Latent.this.annotationValues);
            }

            public AnnotationValue<?, ?> getValue(MethodDescription.InDefinedShape inDefinedShape) {
                return Latent.this.getValue(inDefinedShape);
            }

            public TypeDescription getAnnotationType() {
                return TypeDescription.ForLoadedType.of(this.annotationType);
            }

            public <T extends Annotation> Loadable<T> prepare(Class<T> cls) {
                return Latent.this.prepare(cls);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class Builder {
        private final TypeDescription annotationType;
        private final Map<String, AnnotationValue<?, ?>> annotationValues;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Builder builder = (Builder) obj;
            return this.annotationType.equals(builder.annotationType) && this.annotationValues.equals(builder.annotationValues);
        }

        public int hashCode() {
            return ((527 + this.annotationType.hashCode()) * 31) + this.annotationValues.hashCode();
        }

        protected Builder(TypeDescription typeDescription, Map<String, AnnotationValue<?, ?>> map) {
            this.annotationType = typeDescription;
            this.annotationValues = map;
        }

        public static Builder ofType(Class<? extends Annotation> cls) {
            return ofType(TypeDescription.ForLoadedType.of(cls));
        }

        public static Builder ofType(TypeDescription typeDescription) {
            if (typeDescription.isAnnotation()) {
                return new Builder(typeDescription, Collections.emptyMap());
            }
            throw new IllegalArgumentException("Not an annotation type: " + typeDescription);
        }

        public Builder define(String str, AnnotationValue<?, ?> annotationValue) {
            MethodList methodList = (MethodList) this.annotationType.getDeclaredMethods().filter(ElementMatchers.named(str));
            if (methodList.isEmpty()) {
                throw new IllegalArgumentException(this.annotationType + " does not define a property named " + str);
            } else if (((MethodDescription) methodList.getOnly()).getReturnType().asErasure().isAnnotationValue(annotationValue.resolve())) {
                HashMap hashMap = new HashMap();
                hashMap.putAll(this.annotationValues);
                if (hashMap.put(((MethodDescription) methodList.getOnly()).getName(), annotationValue) == null) {
                    return new Builder(this.annotationType, hashMap);
                }
                throw new IllegalArgumentException("Property already defined: " + str);
            } else {
                throw new IllegalArgumentException(annotationValue + " cannot be assigned to " + str);
            }
        }

        public Builder define(String str, Enum<?> enumR) {
            return define(str, (EnumerationDescription) new EnumerationDescription.ForLoadedEnumeration(enumR));
        }

        public Builder define(String str, TypeDescription typeDescription, String str2) {
            return define(str, (EnumerationDescription) new EnumerationDescription.Latent(typeDescription, str2));
        }

        public Builder define(String str, EnumerationDescription enumerationDescription) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForEnumerationDescription.of(enumerationDescription));
        }

        public Builder define(String str, Annotation annotation) {
            return define(str, (AnnotationDescription) new ForLoadedAnnotation(annotation));
        }

        public Builder define(String str, AnnotationDescription annotationDescription) {
            return define(str, (AnnotationValue<?, ?>) new AnnotationValue.ForAnnotationDescription(annotationDescription));
        }

        public Builder define(String str, Class<?> cls) {
            return define(str, TypeDescription.ForLoadedType.of(cls));
        }

        public Builder define(String str, TypeDescription typeDescription) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForTypeDescription.of(typeDescription));
        }

        public <T extends Enum<?>> Builder defineEnumerationArray(String str, Class<T> cls, T... tArr) {
            EnumerationDescription[] enumerationDescriptionArr = new EnumerationDescription[tArr.length];
            int length = tArr.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                enumerationDescriptionArr[i2] = new EnumerationDescription.ForLoadedEnumeration(tArr[i]);
                i++;
                i2++;
            }
            return defineEnumerationArray(str, TypeDescription.ForLoadedType.of(cls), enumerationDescriptionArr);
        }

        public Builder defineEnumerationArray(String str, TypeDescription typeDescription, String... strArr) {
            if (typeDescription.isEnum()) {
                EnumerationDescription[] enumerationDescriptionArr = new EnumerationDescription[strArr.length];
                for (int i = 0; i < strArr.length; i++) {
                    enumerationDescriptionArr[i] = new EnumerationDescription.Latent(typeDescription, strArr[i]);
                }
                return defineEnumerationArray(str, typeDescription, enumerationDescriptionArr);
            }
            throw new IllegalArgumentException("Not an enumeration type: " + typeDescription);
        }

        public Builder defineEnumerationArray(String str, TypeDescription typeDescription, EnumerationDescription... enumerationDescriptionArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForDescriptionArray.of(typeDescription, enumerationDescriptionArr));
        }

        public <T extends Annotation> Builder defineAnnotationArray(String str, Class<T> cls, T... tArr) {
            return defineAnnotationArray(str, TypeDescription.ForLoadedType.of(cls), (AnnotationDescription[]) new AnnotationList.ForLoadedAnnotations((Annotation[]) tArr).toArray(new AnnotationDescription[tArr.length]));
        }

        public Builder defineAnnotationArray(String str, TypeDescription typeDescription, AnnotationDescription... annotationDescriptionArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForDescriptionArray.of(typeDescription, annotationDescriptionArr));
        }

        public Builder defineTypeArray(String str, Class<?>... clsArr) {
            return defineTypeArray(str, (TypeDescription[]) new TypeList.ForLoadedTypes(clsArr).toArray(new TypeDescription[clsArr.length]));
        }

        public Builder defineTypeArray(String str, TypeDescription... typeDescriptionArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForDescriptionArray.of(typeDescriptionArr));
        }

        public Builder define(String str, boolean z) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(z));
        }

        public Builder define(String str, byte b) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(b));
        }

        public Builder define(String str, char c) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(c));
        }

        public Builder define(String str, short s) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(s));
        }

        public Builder define(String str, int i) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(i));
        }

        public Builder define(String str, long j) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(j));
        }

        public Builder define(String str, float f) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(f));
        }

        public Builder define(String str, double d) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(d));
        }

        public Builder define(String str, String str2) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(str2));
        }

        public Builder defineArray(String str, boolean... zArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(zArr));
        }

        public Builder defineArray(String str, byte... bArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(bArr));
        }

        public Builder defineArray(String str, char... cArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(cArr));
        }

        public Builder defineArray(String str, short... sArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(sArr));
        }

        public Builder defineArray(String str, int... iArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(iArr));
        }

        public Builder defineArray(String str, long... jArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(jArr));
        }

        public Builder defineArray(String str, float... fArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(fArr));
        }

        public Builder defineArray(String str, double... dArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(dArr));
        }

        public Builder defineArray(String str, String... strArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(strArr));
        }

        public AnnotationDescription build() {
            for (MethodDescription methodDescription : this.annotationType.getDeclaredMethods()) {
                if (this.annotationValues.get(methodDescription.getName()) == null && methodDescription.getDefaultValue() == null) {
                    throw new IllegalStateException("No value or default value defined for " + methodDescription.getName());
                }
            }
            return new Latent(this.annotationType, this.annotationValues);
        }
    }
}
