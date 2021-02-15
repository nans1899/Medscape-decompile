package net.bytebuddy.description.annotation;

import com.dd.plist.ASCIIPropertyListParser;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.pool.TypePool;

public interface AnnotationValue<T, S> {
    public static final AnnotationValue<?, ?> UNDEFINED = null;

    Loaded<S> load(ClassLoader classLoader) throws ClassNotFoundException;

    Loaded<S> loadSilent(ClassLoader classLoader);

    T resolve();

    <W> W resolve(Class<? extends W> cls);

    public enum RenderingDispatcher {
        LEGACY_VM(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH, ']') {
            public String toSourceString(String str) {
                return str;
            }

            public String toSourceString(char c) {
                return Character.toString(c);
            }

            public String toSourceString(long j) {
                return Long.toString(j);
            }

            public String toSourceString(float f) {
                return Float.toString(f);
            }

            public String toSourceString(double d) {
                return Double.toString(d);
            }

            public String toSourceString(TypeDescription typeDescription) {
                return typeDescription.toString();
            }
        },
        JAVA_9_CAPABLE_VM(ASCIIPropertyListParser.DICTIONARY_BEGIN_TOKEN, ASCIIPropertyListParser.DICTIONARY_END_TOKEN) {
            public String toSourceString(char c) {
                StringBuilder sb = new StringBuilder();
                sb.append('\'');
                if (c == '\'') {
                    sb.append("\\'");
                } else {
                    sb.append(c);
                }
                sb.append('\'');
                return sb.toString();
            }

            public String toSourceString(long j) {
                if (Math.abs(j) <= 2147483647L) {
                    return String.valueOf(j);
                }
                return j + "L";
            }

            public String toSourceString(float f) {
                if (Math.abs(f) <= Float.MAX_VALUE) {
                    return Float.toString(f) + "f";
                } else if (Float.isInfinite(f)) {
                    return f < 0.0f ? "-1.0f/0.0f" : "1.0f/0.0f";
                } else {
                    return "0.0f/0.0f";
                }
            }

            public String toSourceString(double d) {
                if (Math.abs(d) <= Double.MAX_VALUE) {
                    return Double.toString(d);
                }
                if (Double.isInfinite(d)) {
                    return d < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? "-1.0/0.0" : "1.0/0.0";
                }
                return "0.0/0.0";
            }

            public String toSourceString(String str) {
                StringBuilder sb = new StringBuilder();
                sb.append("\"");
                if (str.indexOf(34) != -1) {
                    str = str.replace("\"", "\\\"");
                }
                sb.append(str);
                sb.append("\"");
                return sb.toString();
            }

            public String toSourceString(TypeDescription typeDescription) {
                return typeDescription.getActualName() + ".class";
            }
        };
        
        public static final RenderingDispatcher CURRENT = null;
        private final char closingBrace;
        private final char openingBrace;

        public abstract String toSourceString(char c);

        public abstract String toSourceString(double d);

        public abstract String toSourceString(float f);

        public abstract String toSourceString(long j);

        public abstract String toSourceString(String str);

        public abstract String toSourceString(TypeDescription typeDescription);

        private RenderingDispatcher(char c, char c2) {
            this.openingBrace = c;
            this.closingBrace = c2;
        }

        public String toSourceString(boolean z) {
            return Boolean.toString(z);
        }

        public String toSourceString(byte b) {
            return Byte.toString(b);
        }

        public String toSourceString(short s) {
            return Short.toString(s);
        }

        public String toSourceString(int i) {
            return Integer.toString(i);
        }

        public String toSourceString(List<?> list) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.openingBrace);
            boolean z = true;
            for (Object next : list) {
                if (z) {
                    z = false;
                } else {
                    sb.append(", ");
                }
                sb.append(next);
            }
            sb.append(this.closingBrace);
            return sb.toString();
        }
    }

    public interface Loaded<U> {
        State getState();

        boolean represents(Object obj);

        U resolve();

        <V> V resolve(Class<? extends V> cls);

        public enum State {
            UNDEFINED,
            UNRESOLVED,
            RESOLVED;

            public boolean isDefined() {
                return this != UNDEFINED;
            }

            public boolean isResolved() {
                return this == RESOLVED;
            }
        }

        public static abstract class AbstractBase<W> implements Loaded<W> {
            public <X> X resolve(Class<? extends X> cls) {
                return cls.cast(resolve());
            }
        }
    }

    public static abstract class AbstractBase<U, V> implements AnnotationValue<U, V> {
        public <W> W resolve(Class<? extends W> cls) {
            return cls.cast(resolve());
        }

        public Loaded<V> loadSilent(ClassLoader classLoader) {
            try {
                return load(classLoader);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Cannot load " + this, e);
            }
        }
    }

    public static class ForConstant<U> extends AbstractBase<U, U> {
        private final PropertyDelegate propertyDelegate;
        private final U value;

        protected ForConstant(U u, PropertyDelegate propertyDelegate2) {
            this.value = u;
            this.propertyDelegate = propertyDelegate2;
        }

        public static AnnotationValue<Boolean, Boolean> of(boolean z) {
            return new ForConstant(Boolean.valueOf(z), PropertyDelegate.ForNonArrayType.BOOLEAN);
        }

        public static AnnotationValue<Byte, Byte> of(byte b) {
            return new ForConstant(Byte.valueOf(b), PropertyDelegate.ForNonArrayType.BYTE);
        }

        public static AnnotationValue<Short, Short> of(short s) {
            return new ForConstant(Short.valueOf(s), PropertyDelegate.ForNonArrayType.SHORT);
        }

        public static AnnotationValue<Character, Character> of(char c) {
            return new ForConstant(Character.valueOf(c), PropertyDelegate.ForNonArrayType.CHARACTER);
        }

        public static AnnotationValue<Integer, Integer> of(int i) {
            return new ForConstant(Integer.valueOf(i), PropertyDelegate.ForNonArrayType.INTEGER);
        }

        public static AnnotationValue<Long, Long> of(long j) {
            return new ForConstant(Long.valueOf(j), PropertyDelegate.ForNonArrayType.LONG);
        }

        public static AnnotationValue<Float, Float> of(float f) {
            return new ForConstant(Float.valueOf(f), PropertyDelegate.ForNonArrayType.FLOAT);
        }

        public static AnnotationValue<Double, Double> of(double d) {
            return new ForConstant(Double.valueOf(d), PropertyDelegate.ForNonArrayType.DOUBLE);
        }

        public static AnnotationValue<String, String> of(String str) {
            return new ForConstant(str, PropertyDelegate.ForNonArrayType.STRING);
        }

        public static AnnotationValue<boolean[], boolean[]> of(boolean... zArr) {
            return new ForConstant(zArr, PropertyDelegate.ForArrayType.BOOLEAN);
        }

        public static AnnotationValue<byte[], byte[]> of(byte... bArr) {
            return new ForConstant(bArr, PropertyDelegate.ForArrayType.BYTE);
        }

        public static AnnotationValue<short[], short[]> of(short... sArr) {
            return new ForConstant(sArr, PropertyDelegate.ForArrayType.SHORT);
        }

        public static AnnotationValue<char[], char[]> of(char... cArr) {
            return new ForConstant(cArr, PropertyDelegate.ForArrayType.CHARACTER);
        }

        public static AnnotationValue<int[], int[]> of(int... iArr) {
            return new ForConstant(iArr, PropertyDelegate.ForArrayType.INTEGER);
        }

        public static AnnotationValue<long[], long[]> of(long... jArr) {
            return new ForConstant(jArr, PropertyDelegate.ForArrayType.LONG);
        }

        public static AnnotationValue<float[], float[]> of(float... fArr) {
            return new ForConstant(fArr, PropertyDelegate.ForArrayType.FLOAT);
        }

        public static AnnotationValue<double[], double[]> of(double... dArr) {
            return new ForConstant(dArr, PropertyDelegate.ForArrayType.DOUBLE);
        }

        public static AnnotationValue<String[], String[]> of(String... strArr) {
            return new ForConstant(strArr, PropertyDelegate.ForArrayType.STRING);
        }

        public static AnnotationValue<?, ?> of(Object obj) {
            if (obj instanceof Boolean) {
                return of(((Boolean) obj).booleanValue());
            }
            if (obj instanceof Byte) {
                return of(((Byte) obj).byteValue());
            }
            if (obj instanceof Short) {
                return of(((Short) obj).shortValue());
            }
            if (obj instanceof Character) {
                return of(((Character) obj).charValue());
            }
            if (obj instanceof Integer) {
                return of(((Integer) obj).intValue());
            }
            if (obj instanceof Long) {
                return of(((Long) obj).longValue());
            }
            if (obj instanceof Float) {
                return of(((Float) obj).floatValue());
            }
            if (obj instanceof Double) {
                return of(((Double) obj).doubleValue());
            }
            if (obj instanceof String) {
                return of((String) obj);
            }
            if (obj instanceof boolean[]) {
                return of((boolean[]) obj);
            }
            if (obj instanceof byte[]) {
                return of((byte[]) obj);
            }
            if (obj instanceof short[]) {
                return of((short[]) obj);
            }
            if (obj instanceof char[]) {
                return of((char[]) obj);
            }
            if (obj instanceof int[]) {
                return of((int[]) obj);
            }
            if (obj instanceof long[]) {
                return of((long[]) obj);
            }
            if (obj instanceof float[]) {
                return of((float[]) obj);
            }
            if (obj instanceof double[]) {
                return of((double[]) obj);
            }
            if (obj instanceof String[]) {
                return of((String[]) obj);
            }
            throw new IllegalArgumentException("Not a constant annotation value: " + obj);
        }

        public U resolve() {
            return this.value;
        }

        public Loaded<U> load(ClassLoader classLoader) {
            return new Loaded(this.value, this.propertyDelegate);
        }

        public int hashCode() {
            return this.propertyDelegate.hashCode(this.value);
        }

        public boolean equals(Object obj) {
            return this == obj || ((obj instanceof AnnotationValue) && this.propertyDelegate.equals(this.value, ((AnnotationValue) obj).resolve()));
        }

        public String toString() {
            return this.propertyDelegate.toString(this.value);
        }

        protected interface PropertyDelegate {
            <S> S copy(S s);

            boolean equals(Object obj, Object obj2);

            int hashCode(Object obj);

            String toString(Object obj);

            public enum ForNonArrayType implements PropertyDelegate {
                BOOLEAN {
                    public String toString(Object obj) {
                        return RenderingDispatcher.CURRENT.toSourceString(((Boolean) obj).booleanValue());
                    }
                },
                BYTE {
                    public String toString(Object obj) {
                        return RenderingDispatcher.CURRENT.toSourceString(((Byte) obj).byteValue());
                    }
                },
                SHORT {
                    public String toString(Object obj) {
                        return RenderingDispatcher.CURRENT.toSourceString(((Short) obj).shortValue());
                    }
                },
                CHARACTER {
                    public String toString(Object obj) {
                        return RenderingDispatcher.CURRENT.toSourceString(((Character) obj).charValue());
                    }
                },
                INTEGER {
                    public String toString(Object obj) {
                        return RenderingDispatcher.CURRENT.toSourceString(((Integer) obj).intValue());
                    }
                },
                LONG {
                    public String toString(Object obj) {
                        return RenderingDispatcher.CURRENT.toSourceString(((Long) obj).longValue());
                    }
                },
                FLOAT {
                    public String toString(Object obj) {
                        return RenderingDispatcher.CURRENT.toSourceString(((Float) obj).floatValue());
                    }
                },
                DOUBLE {
                    public String toString(Object obj) {
                        return RenderingDispatcher.CURRENT.toSourceString(((Double) obj).doubleValue());
                    }
                },
                STRING {
                    public String toString(Object obj) {
                        return RenderingDispatcher.CURRENT.toSourceString((String) obj);
                    }
                };

                public <S> S copy(S s) {
                    return s;
                }

                public int hashCode(Object obj) {
                    return obj.hashCode();
                }

                public boolean equals(Object obj, Object obj2) {
                    return obj.equals(obj2);
                }
            }

            public enum ForArrayType implements PropertyDelegate {
                BOOLEAN {
                    /* access modifiers changed from: protected */
                    public Object doCopy(Object obj) {
                        return ((boolean[]) obj).clone();
                    }

                    public int hashCode(Object obj) {
                        return Arrays.hashCode((boolean[]) obj);
                    }

                    public boolean equals(Object obj, Object obj2) {
                        return (obj2 instanceof boolean[]) && Arrays.equals((boolean[]) obj, (boolean[]) obj2);
                    }

                    /* access modifiers changed from: protected */
                    public String toString(Object obj, int i) {
                        return ForNonArrayType.BOOLEAN.toString(Boolean.valueOf(Array.getBoolean(obj, i)));
                    }
                },
                BYTE {
                    /* access modifiers changed from: protected */
                    public Object doCopy(Object obj) {
                        return ((byte[]) obj).clone();
                    }

                    public int hashCode(Object obj) {
                        return Arrays.hashCode((byte[]) obj);
                    }

                    public boolean equals(Object obj, Object obj2) {
                        return (obj2 instanceof byte[]) && Arrays.equals((byte[]) obj, (byte[]) obj2);
                    }

                    /* access modifiers changed from: protected */
                    public String toString(Object obj, int i) {
                        return ForNonArrayType.BYTE.toString(Byte.valueOf(Array.getByte(obj, i)));
                    }
                },
                SHORT {
                    /* access modifiers changed from: protected */
                    public Object doCopy(Object obj) {
                        return ((short[]) obj).clone();
                    }

                    public int hashCode(Object obj) {
                        return Arrays.hashCode((short[]) obj);
                    }

                    public boolean equals(Object obj, Object obj2) {
                        return (obj2 instanceof short[]) && Arrays.equals((short[]) obj, (short[]) obj2);
                    }

                    /* access modifiers changed from: protected */
                    public String toString(Object obj, int i) {
                        return ForNonArrayType.SHORT.toString(Short.valueOf(Array.getShort(obj, i)));
                    }
                },
                CHARACTER {
                    /* access modifiers changed from: protected */
                    public Object doCopy(Object obj) {
                        return ((char[]) obj).clone();
                    }

                    public int hashCode(Object obj) {
                        return Arrays.hashCode((char[]) obj);
                    }

                    public boolean equals(Object obj, Object obj2) {
                        return (obj2 instanceof char[]) && Arrays.equals((char[]) obj, (char[]) obj2);
                    }

                    /* access modifiers changed from: protected */
                    public String toString(Object obj, int i) {
                        return ForNonArrayType.CHARACTER.toString(Character.valueOf(Array.getChar(obj, i)));
                    }
                },
                INTEGER {
                    /* access modifiers changed from: protected */
                    public Object doCopy(Object obj) {
                        return ((int[]) obj).clone();
                    }

                    public int hashCode(Object obj) {
                        return Arrays.hashCode((int[]) obj);
                    }

                    public boolean equals(Object obj, Object obj2) {
                        return (obj2 instanceof int[]) && Arrays.equals((int[]) obj, (int[]) obj2);
                    }

                    /* access modifiers changed from: protected */
                    public String toString(Object obj, int i) {
                        return ForNonArrayType.INTEGER.toString(Integer.valueOf(Array.getInt(obj, i)));
                    }
                },
                LONG {
                    /* access modifiers changed from: protected */
                    public Object doCopy(Object obj) {
                        return ((long[]) obj).clone();
                    }

                    public int hashCode(Object obj) {
                        return Arrays.hashCode((long[]) obj);
                    }

                    public boolean equals(Object obj, Object obj2) {
                        return (obj2 instanceof long[]) && Arrays.equals((long[]) obj, (long[]) obj2);
                    }

                    /* access modifiers changed from: protected */
                    public String toString(Object obj, int i) {
                        return ForNonArrayType.LONG.toString(Long.valueOf(Array.getLong(obj, i)));
                    }
                },
                FLOAT {
                    /* access modifiers changed from: protected */
                    public Object doCopy(Object obj) {
                        return ((float[]) obj).clone();
                    }

                    public int hashCode(Object obj) {
                        return Arrays.hashCode((float[]) obj);
                    }

                    public boolean equals(Object obj, Object obj2) {
                        return (obj2 instanceof float[]) && Arrays.equals((float[]) obj, (float[]) obj2);
                    }

                    /* access modifiers changed from: protected */
                    public String toString(Object obj, int i) {
                        return ForNonArrayType.FLOAT.toString(Float.valueOf(Array.getFloat(obj, i)));
                    }
                },
                DOUBLE {
                    /* access modifiers changed from: protected */
                    public Object doCopy(Object obj) {
                        return ((double[]) obj).clone();
                    }

                    public int hashCode(Object obj) {
                        return Arrays.hashCode((double[]) obj);
                    }

                    public boolean equals(Object obj, Object obj2) {
                        return (obj2 instanceof double[]) && Arrays.equals((double[]) obj, (double[]) obj2);
                    }

                    /* access modifiers changed from: protected */
                    public String toString(Object obj, int i) {
                        return ForNonArrayType.DOUBLE.toString(Double.valueOf(Array.getDouble(obj, i)));
                    }
                },
                STRING {
                    /* access modifiers changed from: protected */
                    public Object doCopy(Object obj) {
                        return ((String[]) obj).clone();
                    }

                    public int hashCode(Object obj) {
                        return Arrays.hashCode((String[]) obj);
                    }

                    public boolean equals(Object obj, Object obj2) {
                        return (obj2 instanceof String[]) && Arrays.equals((String[]) obj, (String[]) obj2);
                    }

                    /* access modifiers changed from: protected */
                    public String toString(Object obj, int i) {
                        return ForNonArrayType.STRING.toString(Array.get(obj, i));
                    }
                };

                /* access modifiers changed from: protected */
                public abstract Object doCopy(Object obj);

                /* access modifiers changed from: protected */
                public abstract String toString(Object obj, int i);

                public <S> S copy(S s) {
                    return doCopy(s);
                }

                public String toString(Object obj) {
                    ArrayList arrayList = new ArrayList(Array.getLength(obj));
                    for (int i = 0; i < Array.getLength(obj); i++) {
                        arrayList.add(toString(obj, i));
                    }
                    return RenderingDispatcher.CURRENT.toSourceString((List<?>) arrayList);
                }
            }
        }

        protected static class Loaded<V> extends Loaded.AbstractBase<V> {
            private final PropertyDelegate propertyDelegate;
            private final V value;

            protected Loaded(V v, PropertyDelegate propertyDelegate2) {
                this.value = v;
                this.propertyDelegate = propertyDelegate2;
            }

            public Loaded.State getState() {
                return Loaded.State.RESOLVED;
            }

            public V resolve() {
                return this.propertyDelegate.copy(this.value);
            }

            public boolean represents(Object obj) {
                return this.propertyDelegate.equals(this.value, obj);
            }

            public int hashCode() {
                return this.propertyDelegate.hashCode(this.value);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Loaded)) {
                    return false;
                }
                Loaded loaded = (Loaded) obj;
                if (!loaded.getState().isResolved() || !this.propertyDelegate.equals(this.value, loaded.resolve())) {
                    return false;
                }
                return true;
            }

            public String toString() {
                return this.propertyDelegate.toString(this.value);
            }
        }
    }

    public static class ForAnnotationDescription<U extends Annotation> extends AbstractBase<AnnotationDescription, U> {
        private final AnnotationDescription annotationDescription;

        public ForAnnotationDescription(AnnotationDescription annotationDescription2) {
            this.annotationDescription = annotationDescription2;
        }

        public static <V extends Annotation> AnnotationValue<AnnotationDescription, V> of(TypeDescription typeDescription, Map<String, ? extends AnnotationValue<?, ?>> map) {
            return new ForAnnotationDescription(new AnnotationDescription.Latent(typeDescription, map));
        }

        public AnnotationDescription resolve() {
            return this.annotationDescription;
        }

        public Loaded<U> load(ClassLoader classLoader) throws ClassNotFoundException {
            return new Loaded(this.annotationDescription.prepare(Class.forName(this.annotationDescription.getAnnotationType().getName(), false, classLoader)).load());
        }

        public int hashCode() {
            return this.annotationDescription.hashCode();
        }

        public boolean equals(Object obj) {
            return this == obj || ((obj instanceof AnnotationValue) && this.annotationDescription.equals(((AnnotationValue) obj).resolve()));
        }

        public String toString() {
            return this.annotationDescription.toString();
        }

        public static class Loaded<V extends Annotation> extends Loaded.AbstractBase<V> {
            private final V annotation;

            public Loaded(V v) {
                this.annotation = v;
            }

            public Loaded.State getState() {
                return Loaded.State.RESOLVED;
            }

            public V resolve() {
                return this.annotation;
            }

            public boolean represents(Object obj) {
                return this.annotation.equals(obj);
            }

            public int hashCode() {
                return this.annotation.hashCode();
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Loaded)) {
                    return false;
                }
                Loaded loaded = (Loaded) obj;
                if (!loaded.getState().isResolved() || !this.annotation.equals(loaded.resolve())) {
                    return false;
                }
                return true;
            }

            public String toString() {
                return this.annotation.toString();
            }
        }

        public static class IncompatibleRuntimeType extends Loaded.AbstractBase<Annotation> {
            private final Class<?> incompatibleType;

            public boolean represents(Object obj) {
                return false;
            }

            public IncompatibleRuntimeType(Class<?> cls) {
                this.incompatibleType = cls;
            }

            public Loaded.State getState() {
                return Loaded.State.UNRESOLVED;
            }

            public Annotation resolve() {
                throw new IncompatibleClassChangeError("Not an annotation type: " + this.incompatibleType.toString());
            }
        }
    }

    public static class ForEnumerationDescription<U extends Enum<U>> extends AbstractBase<EnumerationDescription, U> {
        private final EnumerationDescription enumerationDescription;

        protected ForEnumerationDescription(EnumerationDescription enumerationDescription2) {
            this.enumerationDescription = enumerationDescription2;
        }

        public static <V extends Enum<V>> AnnotationValue<EnumerationDescription, V> of(EnumerationDescription enumerationDescription2) {
            return new ForEnumerationDescription(enumerationDescription2);
        }

        public EnumerationDescription resolve() {
            return this.enumerationDescription;
        }

        public Loaded<U> load(ClassLoader classLoader) throws ClassNotFoundException {
            return new Loaded(this.enumerationDescription.load(Class.forName(this.enumerationDescription.getEnumerationType().getName(), false, classLoader)));
        }

        public int hashCode() {
            return this.enumerationDescription.hashCode();
        }

        public boolean equals(Object obj) {
            return this == obj || ((obj instanceof AnnotationValue) && this.enumerationDescription.equals(((AnnotationValue) obj).resolve()));
        }

        public String toString() {
            return this.enumerationDescription.toString();
        }

        public static class Loaded<V extends Enum<V>> extends Loaded.AbstractBase<V> {
            private final V enumeration;

            public Loaded(V v) {
                this.enumeration = v;
            }

            public Loaded.State getState() {
                return Loaded.State.RESOLVED;
            }

            public V resolve() {
                return this.enumeration;
            }

            public boolean represents(Object obj) {
                return this.enumeration.equals(obj);
            }

            public int hashCode() {
                return this.enumeration.hashCode();
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Loaded)) {
                    return false;
                }
                Loaded loaded = (Loaded) obj;
                if (!loaded.getState().isResolved() || !this.enumeration.equals(loaded.resolve())) {
                    return false;
                }
                return true;
            }

            public String toString() {
                return this.enumeration.toString();
            }
        }

        public static class UnknownRuntimeEnumeration extends Loaded.AbstractBase<Enum<?>> {
            private final Class<? extends Enum<?>> enumType;
            private final String value;

            public boolean represents(Object obj) {
                return false;
            }

            public UnknownRuntimeEnumeration(Class<? extends Enum<?>> cls, String str) {
                this.enumType = cls;
                this.value = str;
            }

            public Loaded.State getState() {
                return Loaded.State.UNRESOLVED;
            }

            public Enum<?> resolve() {
                throw new EnumConstantNotPresentException(this.enumType, this.value);
            }
        }

        public static class IncompatibleRuntimeType extends Loaded.AbstractBase<Enum<?>> {
            private final Class<?> type;

            public boolean represents(Object obj) {
                return false;
            }

            public IncompatibleRuntimeType(Class<?> cls) {
                this.type = cls;
            }

            public Loaded.State getState() {
                return Loaded.State.UNRESOLVED;
            }

            public Enum<?> resolve() {
                throw new IncompatibleClassChangeError("Not an enumeration type: " + this.type.toString());
            }
        }
    }

    public static class ForTypeDescription<U extends Class<U>> extends AbstractBase<TypeDescription, U> {
        private static final boolean NO_INITIALIZATION = false;
        private final TypeDescription typeDescription;

        protected ForTypeDescription(TypeDescription typeDescription2) {
            this.typeDescription = typeDescription2;
        }

        public static <V extends Class<V>> AnnotationValue<TypeDescription, V> of(TypeDescription typeDescription2) {
            return new ForTypeDescription(typeDescription2);
        }

        public TypeDescription resolve() {
            return this.typeDescription;
        }

        public Loaded<U> load(ClassLoader classLoader) throws ClassNotFoundException {
            return new Loaded(Class.forName(this.typeDescription.getName(), false, classLoader));
        }

        public int hashCode() {
            return this.typeDescription.hashCode();
        }

        public boolean equals(Object obj) {
            return this == obj || ((obj instanceof AnnotationValue) && this.typeDescription.equals(((AnnotationValue) obj).resolve()));
        }

        public String toString() {
            return RenderingDispatcher.CURRENT.toSourceString(this.typeDescription);
        }

        protected static class Loaded<U extends Class<U>> extends Loaded.AbstractBase<U> {
            private final U type;

            public Loaded(U u) {
                this.type = u;
            }

            public Loaded.State getState() {
                return Loaded.State.RESOLVED;
            }

            public U resolve() {
                return this.type;
            }

            public boolean represents(Object obj) {
                return this.type.equals(obj);
            }

            public int hashCode() {
                return this.type.hashCode();
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Loaded)) {
                    return false;
                }
                Loaded loaded = (Loaded) obj;
                if (!loaded.getState().isResolved() || !this.type.equals(loaded.resolve())) {
                    return false;
                }
                return true;
            }

            public String toString() {
                return RenderingDispatcher.CURRENT.toSourceString(TypeDescription.ForLoadedType.of(this.type));
            }
        }
    }

    public static class ForDescriptionArray<U, V> extends AbstractBase<U[], V[]> {
        private final TypeDescription componentType;
        private final Class<?> unloadedComponentType;
        private final List<? extends AnnotationValue<?, ?>> values;

        protected ForDescriptionArray(Class<?> cls, TypeDescription typeDescription, List<? extends AnnotationValue<?, ?>> list) {
            this.unloadedComponentType = cls;
            this.componentType = typeDescription;
            this.values = list;
        }

        public static <W extends Enum<W>> AnnotationValue<EnumerationDescription[], W[]> of(TypeDescription typeDescription, EnumerationDescription[] enumerationDescriptionArr) {
            ArrayList arrayList = new ArrayList(enumerationDescriptionArr.length);
            int length = enumerationDescriptionArr.length;
            int i = 0;
            while (i < length) {
                EnumerationDescription enumerationDescription = enumerationDescriptionArr[i];
                if (enumerationDescription.getEnumerationType().equals(typeDescription)) {
                    arrayList.add(ForEnumerationDescription.of(enumerationDescription));
                    i++;
                } else {
                    throw new IllegalArgumentException(enumerationDescription + " is not of " + typeDescription);
                }
            }
            return new ForDescriptionArray(EnumerationDescription.class, typeDescription, arrayList);
        }

        public static <W extends Annotation> AnnotationValue<AnnotationDescription[], W[]> of(TypeDescription typeDescription, AnnotationDescription[] annotationDescriptionArr) {
            ArrayList arrayList = new ArrayList(annotationDescriptionArr.length);
            int length = annotationDescriptionArr.length;
            int i = 0;
            while (i < length) {
                AnnotationDescription annotationDescription = annotationDescriptionArr[i];
                if (annotationDescription.getAnnotationType().equals(typeDescription)) {
                    arrayList.add(new ForAnnotationDescription(annotationDescription));
                    i++;
                } else {
                    throw new IllegalArgumentException(annotationDescription + " is not of " + typeDescription);
                }
            }
            return new ForDescriptionArray(AnnotationDescription.class, typeDescription, arrayList);
        }

        public static AnnotationValue<TypeDescription[], Class<?>[]> of(TypeDescription[] typeDescriptionArr) {
            ArrayList arrayList = new ArrayList(typeDescriptionArr.length);
            for (TypeDescription of : typeDescriptionArr) {
                arrayList.add(ForTypeDescription.of(of));
            }
            return new ForDescriptionArray(TypeDescription.class, TypeDescription.CLASS, arrayList);
        }

        public U[] resolve() {
            U[] uArr = (Object[]) Array.newInstance(this.unloadedComponentType, this.values.size());
            int i = 0;
            for (AnnotationValue resolve : this.values) {
                Array.set(uArr, i, resolve.resolve());
                i++;
            }
            return uArr;
        }

        public Loaded<V[]> load(ClassLoader classLoader) throws ClassNotFoundException {
            ArrayList arrayList = new ArrayList(this.values.size());
            for (AnnotationValue load : this.values) {
                arrayList.add(load.load(classLoader));
            }
            return new Loaded(Class.forName(this.componentType.getName(), false, classLoader), arrayList);
        }

        public int hashCode() {
            int i = 1;
            for (AnnotationValue hashCode : this.values) {
                i = (i * 31) + hashCode.hashCode();
            }
            return i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AnnotationValue)) {
                return false;
            }
            Object resolve = ((AnnotationValue) obj).resolve();
            if (!(resolve instanceof Object[])) {
                return false;
            }
            Object[] objArr = (Object[]) resolve;
            if (this.values.size() != objArr.length) {
                return false;
            }
            Iterator<? extends AnnotationValue<?, ?>> it = this.values.iterator();
            for (Object equals : objArr) {
                if (!((AnnotationValue) it.next()).resolve().equals(equals)) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            return RenderingDispatcher.CURRENT.toSourceString((List<?>) this.values);
        }

        protected static class Loaded<W> extends Loaded.AbstractBase<W[]> {
            private final Class<W> componentType;
            private final List<Loaded<?>> values;

            protected Loaded(Class<W> cls, List<Loaded<?>> list) {
                this.componentType = cls;
                this.values = list;
            }

            public Loaded.State getState() {
                for (Loaded<?> state : this.values) {
                    if (!state.getState().isResolved()) {
                        return Loaded.State.UNRESOLVED;
                    }
                }
                return Loaded.State.RESOLVED;
            }

            public W[] resolve() {
                W[] wArr = (Object[]) Array.newInstance(this.componentType, this.values.size());
                int i = 0;
                for (Loaded<?> resolve : this.values) {
                    Array.set(wArr, i, resolve.resolve());
                    i++;
                }
                return wArr;
            }

            public boolean represents(Object obj) {
                if (!(obj instanceof Object[]) || obj.getClass().getComponentType() != this.componentType) {
                    return false;
                }
                Object[] objArr = (Object[]) obj;
                if (this.values.size() != objArr.length) {
                    return false;
                }
                Iterator<Loaded<?>> it = this.values.iterator();
                for (Object represents : objArr) {
                    if (!it.next().represents(represents)) {
                        return false;
                    }
                }
                return true;
            }

            public int hashCode() {
                int i = 1;
                for (Loaded<?> hashCode : this.values) {
                    i = (i * 31) + hashCode.hashCode();
                }
                return i;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Loaded)) {
                    return false;
                }
                Loaded loaded = (Loaded) obj;
                if (!loaded.getState().isResolved()) {
                    return false;
                }
                Object resolve = loaded.resolve();
                if (!(resolve instanceof Object[])) {
                    return false;
                }
                Object[] objArr = (Object[]) resolve;
                if (this.values.size() != objArr.length) {
                    return false;
                }
                Iterator<Loaded<?>> it = this.values.iterator();
                for (Object obj2 : objArr) {
                    Loaded next = it.next();
                    if (!next.getState().isResolved() || !next.resolve().equals(obj2)) {
                        return false;
                    }
                }
                return true;
            }

            public String toString() {
                return RenderingDispatcher.CURRENT.toSourceString((List<?>) this.values);
            }
        }
    }
}
