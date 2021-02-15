package net.bytebuddy.pool;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.GenericSignatureFormatError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import net.bytebuddy.build.CachedReturnPlugin;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.TypeVariableSource;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.type.PackageDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.ClassReader;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.FieldVisitor;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.jar.asm.TypePath;
import net.bytebuddy.jar.asm.TypeReference;
import net.bytebuddy.jar.asm.signature.SignatureReader;
import net.bytebuddy.jar.asm.signature.SignatureVisitor;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.OpenedClassReader;

public interface TypePool {
    void clear();

    Resolution describe(String str);

    public interface Resolution {
        boolean isResolved();

        TypeDescription resolve();

        @HashCodeAndEqualsPlugin.Enhance
        public static class Simple implements Resolution {
            private final TypeDescription typeDescription;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((Simple) obj).typeDescription);
            }

            public int hashCode() {
                return 527 + this.typeDescription.hashCode();
            }

            public boolean isResolved() {
                return true;
            }

            public Simple(TypeDescription typeDescription2) {
                this.typeDescription = typeDescription2;
            }

            public TypeDescription resolve() {
                return this.typeDescription;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Illegal implements Resolution {
            private final String name;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.name.equals(((Illegal) obj).name);
            }

            public int hashCode() {
                return 527 + this.name.hashCode();
            }

            public boolean isResolved() {
                return false;
            }

            public Illegal(String str) {
                this.name = str;
            }

            public TypeDescription resolve() {
                throw new IllegalStateException("Cannot resolve type description for " + this.name);
            }
        }
    }

    public interface CacheProvider {
        public static final Resolution UNRESOLVED = null;

        void clear();

        Resolution find(String str);

        Resolution register(String str, Resolution resolution);

        public enum NoOp implements CacheProvider {
            INSTANCE;

            public void clear() {
            }

            public Resolution register(String str, Resolution resolution) {
                return resolution;
            }

            public Resolution find(String str) {
                return UNRESOLVED;
            }
        }

        public static class Simple implements CacheProvider {
            private final ConcurrentMap<String, Resolution> storage;

            public Simple() {
                this(new ConcurrentHashMap());
            }

            public Simple(ConcurrentMap<String, Resolution> concurrentMap) {
                this.storage = concurrentMap;
            }

            public static CacheProvider withObjectType() {
                Simple simple = new Simple();
                simple.register(Object.class.getName(), new Resolution.Simple(TypeDescription.OBJECT));
                return simple;
            }

            public Resolution find(String str) {
                return (Resolution) this.storage.get(str);
            }

            public Resolution register(String str, Resolution resolution) {
                Resolution putIfAbsent = this.storage.putIfAbsent(str, resolution);
                return putIfAbsent == null ? resolution : putIfAbsent;
            }

            public void clear() {
                this.storage.clear();
            }

            public ConcurrentMap<String, Resolution> getStorage() {
                return this.storage;
            }
        }
    }

    public enum Empty implements TypePool {
        INSTANCE;

        public void clear() {
        }

        public Resolution describe(String str) {
            return new Resolution.Illegal(str);
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static abstract class AbstractBase implements TypePool {
        private static final String ARRAY_SYMBOL = "[";
        protected static final Map<String, String> PRIMITIVE_DESCRIPTORS;
        protected static final Map<String, TypeDescription> PRIMITIVE_TYPES;
        protected final CacheProvider cacheProvider;

        /* access modifiers changed from: protected */
        public abstract Resolution doDescribe(String str);

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.cacheProvider.equals(((AbstractBase) obj).cacheProvider);
        }

        public int hashCode() {
            return 527 + this.cacheProvider.hashCode();
        }

        static {
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            Class[] clsArr = {Boolean.TYPE, Byte.TYPE, Short.TYPE, Character.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Void.TYPE};
            for (int i = 0; i < 9; i++) {
                Class cls = clsArr[i];
                hashMap.put(cls.getName(), TypeDescription.ForLoadedType.of(cls));
                hashMap2.put(Type.getDescriptor(cls), cls.getName());
            }
            PRIMITIVE_TYPES = Collections.unmodifiableMap(hashMap);
            PRIMITIVE_DESCRIPTORS = Collections.unmodifiableMap(hashMap2);
        }

        protected AbstractBase(CacheProvider cacheProvider2) {
            this.cacheProvider = cacheProvider2;
        }

        public Resolution describe(String str) {
            if (!str.contains("/")) {
                int i = 0;
                while (str.startsWith(ARRAY_SYMBOL)) {
                    i++;
                    str = str.substring(1);
                }
                if (i > 0) {
                    String str2 = PRIMITIVE_DESCRIPTORS.get(str);
                    str = str2 == null ? str.substring(1, str.length() - 1) : str2;
                }
                TypeDescription typeDescription = PRIMITIVE_TYPES.get(str);
                Resolution find = typeDescription == null ? this.cacheProvider.find(str) : new Resolution.Simple(typeDescription);
                if (find == null) {
                    find = doCache(str, doDescribe(str));
                }
                return ArrayTypeResolution.of(find, i);
            }
            throw new IllegalArgumentException(str + " contains the illegal character '/'");
        }

        /* access modifiers changed from: protected */
        public Resolution doCache(String str, Resolution resolution) {
            return this.cacheProvider.register(str, resolution);
        }

        public void clear() {
            this.cacheProvider.clear();
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static abstract class Hierarchical extends AbstractBase {
            private final TypePool parent;

            public boolean equals(Object obj) {
                if (!super.equals(obj)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.parent.equals(((Hierarchical) obj).parent);
            }

            public int hashCode() {
                return (super.hashCode() * 31) + this.parent.hashCode();
            }

            protected Hierarchical(CacheProvider cacheProvider, TypePool typePool) {
                super(cacheProvider);
                this.parent = typePool;
            }

            public Resolution describe(String str) {
                Resolution describe = this.parent.describe(str);
                return describe.isResolved() ? describe : super.describe(str);
            }

            public void clear() {
                try {
                    this.parent.clear();
                } finally {
                    super.clear();
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        protected static class ArrayTypeResolution implements Resolution {
            private final int arity;
            private final Resolution resolution;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ArrayTypeResolution arrayTypeResolution = (ArrayTypeResolution) obj;
                return this.arity == arrayTypeResolution.arity && this.resolution.equals(arrayTypeResolution.resolution);
            }

            public int hashCode() {
                return ((527 + this.resolution.hashCode()) * 31) + this.arity;
            }

            protected ArrayTypeResolution(Resolution resolution2, int i) {
                this.resolution = resolution2;
                this.arity = i;
            }

            protected static Resolution of(Resolution resolution2, int i) {
                return i == 0 ? resolution2 : new ArrayTypeResolution(resolution2, i);
            }

            public boolean isResolved() {
                return this.resolution.isResolved();
            }

            public TypeDescription resolve() {
                return TypeDescription.ArrayProjection.of(this.resolution.resolve(), this.arity);
            }
        }

        protected static class RawAnnotationValue extends AnnotationValue.AbstractBase<AnnotationDescription, Annotation> {
            private final Default.LazyTypeDescription.AnnotationToken annotationToken;
            private final TypePool typePool;

            public RawAnnotationValue(TypePool typePool2, Default.LazyTypeDescription.AnnotationToken annotationToken2) {
                this.typePool = typePool2;
                this.annotationToken = annotationToken2;
            }

            public AnnotationDescription resolve() {
                return this.annotationToken.toAnnotationDescription(this.typePool).resolve();
            }

            public AnnotationValue.Loaded<Annotation> load(ClassLoader classLoader) throws ClassNotFoundException {
                Class<?> cls = Class.forName(this.annotationToken.getBinaryName(), false, classLoader);
                if (cls.isAnnotation()) {
                    return new AnnotationValue.ForAnnotationDescription.Loaded(AnnotationDescription.AnnotationInvocationHandler.of(classLoader, cls, this.annotationToken.getValues()));
                }
                return new AnnotationValue.ForAnnotationDescription.IncompatibleRuntimeType(cls);
            }

            public int hashCode() {
                return resolve().hashCode();
            }

            public boolean equals(Object obj) {
                return this == obj || ((obj instanceof AnnotationValue) && resolve().equals(((AnnotationValue) obj).resolve()));
            }

            public String toString() {
                return resolve().toString();
            }
        }

        protected static class RawEnumerationValue extends AnnotationValue.AbstractBase<EnumerationDescription, Enum<?>> {
            /* access modifiers changed from: private */
            public final String descriptor;
            /* access modifiers changed from: private */
            public final TypePool typePool;
            /* access modifiers changed from: private */
            public final String value;

            public RawEnumerationValue(TypePool typePool2, String str, String str2) {
                this.typePool = typePool2;
                this.descriptor = str;
                this.value = str2;
            }

            public EnumerationDescription resolve() {
                return new LazyEnumerationDescription();
            }

            public AnnotationValue.Loaded<Enum<?>> load(ClassLoader classLoader) throws ClassNotFoundException {
                String str = this.descriptor;
                Class cls = Class.forName(str.substring(1, str.length() - 1).replace('/', '.'), false, classLoader);
                try {
                    return cls.isEnum() ? new AnnotationValue.ForEnumerationDescription.Loaded(Enum.valueOf(cls, this.value)) : new AnnotationValue.ForEnumerationDescription.IncompatibleRuntimeType(cls);
                } catch (IllegalArgumentException unused) {
                    return new AnnotationValue.ForEnumerationDescription.UnknownRuntimeEnumeration(cls, this.value);
                }
            }

            public int hashCode() {
                return resolve().hashCode();
            }

            public boolean equals(Object obj) {
                return this == obj || ((obj instanceof AnnotationValue) && resolve().equals(((AnnotationValue) obj).resolve()));
            }

            public String toString() {
                return resolve().toString();
            }

            protected class LazyEnumerationDescription extends EnumerationDescription.AbstractBase {
                protected LazyEnumerationDescription() {
                }

                public String getValue() {
                    return RawEnumerationValue.this.value;
                }

                public TypeDescription getEnumerationType() {
                    return RawEnumerationValue.this.typePool.describe(RawEnumerationValue.this.descriptor.substring(1, RawEnumerationValue.this.descriptor.length() - 1).replace('/', '.')).resolve();
                }

                public <T extends Enum<T>> T load(Class<T> cls) {
                    return Enum.valueOf(cls, RawEnumerationValue.this.value);
                }
            }
        }

        protected static class RawTypeValue extends AnnotationValue.AbstractBase<TypeDescription, Class<?>> {
            private static final boolean NO_INITIALIZATION = false;
            private final String name;
            private final TypePool typePool;

            protected RawTypeValue(TypePool typePool2, Type type) {
                String str;
                this.typePool = typePool2;
                if (type.getSort() == 9) {
                    str = type.getInternalName().replace('/', '.');
                } else {
                    str = type.getClassName();
                }
                this.name = str;
            }

            public TypeDescription resolve() {
                return this.typePool.describe(this.name).resolve();
            }

            public AnnotationValue.Loaded<Class<?>> load(ClassLoader classLoader) throws ClassNotFoundException {
                return new Loaded(Class.forName(this.name, false, classLoader));
            }

            public int hashCode() {
                return resolve().hashCode();
            }

            public boolean equals(Object obj) {
                return this == obj || ((obj instanceof AnnotationValue) && resolve().equals(((AnnotationValue) obj).resolve()));
            }

            public String toString() {
                return AnnotationValue.RenderingDispatcher.CURRENT.toSourceString(resolve());
            }

            protected static class Loaded extends AnnotationValue.Loaded.AbstractBase<Class<?>> {
                private final Class<?> type;

                public Loaded(Class<?> cls) {
                    this.type = cls;
                }

                public AnnotationValue.Loaded.State getState() {
                    return AnnotationValue.Loaded.State.RESOLVED;
                }

                public Class<?> resolve() {
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
                    if (!(obj instanceof AnnotationValue.Loaded)) {
                        return false;
                    }
                    AnnotationValue.Loaded loaded = (AnnotationValue.Loaded) obj;
                    if (!loaded.getState().isResolved() || !this.type.equals(loaded.resolve())) {
                        return false;
                    }
                    return true;
                }

                public String toString() {
                    return AnnotationValue.RenderingDispatcher.CURRENT.toSourceString(TypeDescription.ForLoadedType.of(this.type));
                }
            }
        }

        protected static class RawDescriptionArray extends AnnotationValue.AbstractBase<Object[], Object[]> {
            private final ComponentTypeReference componentTypeReference;
            private final TypePool typePool;
            private List<AnnotationValue<?, ?>> values;

            public interface ComponentTypeReference {
                String lookup();
            }

            public RawDescriptionArray(TypePool typePool2, ComponentTypeReference componentTypeReference2, List<AnnotationValue<?, ?>> list) {
                this.typePool = typePool2;
                this.values = list;
                this.componentTypeReference = componentTypeReference2;
            }

            public Object[] resolve() {
                Class cls;
                TypeDescription resolve = this.typePool.describe(this.componentTypeReference.lookup()).resolve();
                if (resolve.represents(Class.class)) {
                    cls = TypeDescription.class;
                } else if (resolve.isAssignableTo((Class<?>) Enum.class)) {
                    cls = EnumerationDescription.class;
                } else if (resolve.isAssignableTo((Class<?>) Annotation.class)) {
                    cls = AnnotationDescription.class;
                } else if (resolve.represents(String.class)) {
                    cls = String.class;
                } else {
                    throw new IllegalStateException("Unexpected complex array component type " + resolve);
                }
                Object[] objArr = (Object[]) Array.newInstance(cls, this.values.size());
                int i = 0;
                for (AnnotationValue<?, ?> resolve2 : this.values) {
                    Array.set(objArr, i, resolve2.resolve());
                    i++;
                }
                return objArr;
            }

            public AnnotationValue.Loaded<Object[]> load(ClassLoader classLoader) throws ClassNotFoundException {
                ArrayList arrayList = new ArrayList(this.values.size());
                for (AnnotationValue<?, ?> load : this.values) {
                    arrayList.add(load.load(classLoader));
                }
                return new Loaded(Class.forName(this.componentTypeReference.lookup(), false, classLoader), arrayList);
            }

            public int hashCode() {
                return Arrays.hashCode(resolve());
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof AnnotationValue)) {
                    return false;
                }
                Object resolve = ((AnnotationValue) obj).resolve();
                if (!(resolve instanceof Object[]) || !Arrays.equals(resolve(), (Object[]) resolve)) {
                    return false;
                }
                return true;
            }

            public String toString() {
                return AnnotationValue.RenderingDispatcher.CURRENT.toSourceString((List<?>) this.values);
            }

            protected static class Loaded extends AnnotationValue.Loaded.AbstractBase<Object[]> {
                private final Class<?> componentType;
                private final List<AnnotationValue.Loaded<?>> values;

                public Loaded(Class<?> cls, List<AnnotationValue.Loaded<?>> list) {
                    this.componentType = cls;
                    this.values = list;
                }

                public AnnotationValue.Loaded.State getState() {
                    for (AnnotationValue.Loaded<?> state : this.values) {
                        if (!state.getState().isResolved()) {
                            return AnnotationValue.Loaded.State.UNRESOLVED;
                        }
                    }
                    return AnnotationValue.Loaded.State.RESOLVED;
                }

                public Object[] resolve() {
                    Object[] objArr = (Object[]) Array.newInstance(this.componentType, this.values.size());
                    int i = 0;
                    for (AnnotationValue.Loaded<?> resolve : this.values) {
                        Array.set(objArr, i, resolve.resolve());
                        i++;
                    }
                    return objArr;
                }

                public boolean represents(Object obj) {
                    if (!(obj instanceof Object[]) || obj.getClass().getComponentType() != this.componentType) {
                        return false;
                    }
                    Object[] objArr = (Object[]) obj;
                    if (this.values.size() != objArr.length) {
                        return false;
                    }
                    Iterator<AnnotationValue.Loaded<?>> it = this.values.iterator();
                    for (Object obj2 : objArr) {
                        AnnotationValue.Loaded next = it.next();
                        if (!next.getState().isResolved() || !next.represents(obj2)) {
                            return false;
                        }
                    }
                    return true;
                }

                public int hashCode() {
                    int i = 1;
                    for (AnnotationValue.Loaded<?> hashCode : this.values) {
                        i = (i * 31) + hashCode.hashCode();
                    }
                    return i;
                }

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof AnnotationValue.Loaded)) {
                        return false;
                    }
                    AnnotationValue.Loaded loaded = (AnnotationValue.Loaded) obj;
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
                    Iterator<AnnotationValue.Loaded<?>> it = this.values.iterator();
                    for (Object equals : objArr) {
                        if (!it.next().resolve().equals(equals)) {
                            return false;
                        }
                    }
                    return true;
                }

                public String toString() {
                    return AnnotationValue.RenderingDispatcher.CURRENT.toSourceString((List<?>) this.values);
                }
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class Default extends AbstractBase.Hierarchical {
        /* access modifiers changed from: private */
        public static final MethodVisitor IGNORE_METHOD = null;
        protected final ClassFileLocator classFileLocator;
        protected final ReaderMode readerMode;

        public boolean equals(Object obj) {
            if (!super.equals(obj)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Default defaultR = (Default) obj;
            return this.readerMode.equals(defaultR.readerMode) && this.classFileLocator.equals(defaultR.classFileLocator);
        }

        public int hashCode() {
            return (((super.hashCode() * 31) + this.classFileLocator.hashCode()) * 31) + this.readerMode.hashCode();
        }

        public Default(CacheProvider cacheProvider, ClassFileLocator classFileLocator2, ReaderMode readerMode2) {
            this(cacheProvider, classFileLocator2, readerMode2, Empty.INSTANCE);
        }

        public Default(CacheProvider cacheProvider, ClassFileLocator classFileLocator2, ReaderMode readerMode2, TypePool typePool) {
            super(cacheProvider, typePool);
            this.classFileLocator = classFileLocator2;
            this.readerMode = readerMode2;
        }

        public static TypePool ofSystemLoader() {
            return of(ClassFileLocator.ForClassLoader.ofSystemLoader());
        }

        public static TypePool ofPlatformLoader() {
            return of(ClassFileLocator.ForClassLoader.ofPlatformLoader());
        }

        public static TypePool ofBootLoader() {
            return of(ClassFileLocator.ForClassLoader.ofBootLoader());
        }

        public static TypePool of(ClassLoader classLoader) {
            return of(ClassFileLocator.ForClassLoader.of(classLoader));
        }

        public static TypePool of(ClassFileLocator classFileLocator2) {
            return new Default(new CacheProvider.Simple(), classFileLocator2, ReaderMode.FAST);
        }

        /* access modifiers changed from: protected */
        public Resolution doDescribe(String str) {
            try {
                ClassFileLocator.Resolution locate = this.classFileLocator.locate(str);
                return locate.isResolved() ? new Resolution.Simple(parse(locate.resolve())) : new Resolution.Illegal(str);
            } catch (IOException e) {
                throw new IllegalStateException("Error while reading class file", e);
            }
        }

        private TypeDescription parse(byte[] bArr) {
            ClassReader of = OpenedClassReader.of(bArr);
            TypeExtractor typeExtractor = new TypeExtractor();
            of.accept(typeExtractor, this.readerMode.getFlags());
            return typeExtractor.toTypeDescription();
        }

        public enum ReaderMode {
            EXTENDED(4),
            FAST(1);
            
            private final int flags;

            private ReaderMode(int i) {
                this.flags = i;
            }

            /* access modifiers changed from: protected */
            public int getFlags() {
                return this.flags;
            }

            public boolean isExtended() {
                return this == EXTENDED;
            }
        }

        public static class WithLazyResolution extends Default {
            /* access modifiers changed from: protected */
            public Resolution doCache(String str, Resolution resolution) {
                return resolution;
            }

            public WithLazyResolution(CacheProvider cacheProvider, ClassFileLocator classFileLocator, ReaderMode readerMode) {
                this(cacheProvider, classFileLocator, readerMode, Empty.INSTANCE);
            }

            public WithLazyResolution(CacheProvider cacheProvider, ClassFileLocator classFileLocator, ReaderMode readerMode, TypePool typePool) {
                super(cacheProvider, classFileLocator, readerMode, typePool);
            }

            public static TypePool ofSystemLoader() {
                return of(ClassFileLocator.ForClassLoader.ofSystemLoader());
            }

            public static TypePool ofPlatformLoader() {
                return of(ClassFileLocator.ForClassLoader.ofPlatformLoader());
            }

            public static TypePool ofBootLoader() {
                return of(ClassFileLocator.ForClassLoader.ofBootLoader());
            }

            public static TypePool of(ClassLoader classLoader) {
                return of(ClassFileLocator.ForClassLoader.of(classLoader));
            }

            public static TypePool of(ClassFileLocator classFileLocator) {
                return new WithLazyResolution(new CacheProvider.Simple(), classFileLocator, ReaderMode.FAST);
            }

            /* access modifiers changed from: protected */
            public Resolution doDescribe(String str) {
                return new LazyResolution(str);
            }

            /* access modifiers changed from: protected */
            public Resolution doResolve(String str) {
                Resolution find = this.cacheProvider.find(str);
                return find == null ? this.cacheProvider.register(str, super.doDescribe(str)) : find;
            }

            @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
            protected class LazyResolution implements Resolution {
                private final String name;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    LazyResolution lazyResolution = (LazyResolution) obj;
                    return this.name.equals(lazyResolution.name) && WithLazyResolution.this.equals(WithLazyResolution.this);
                }

                public int hashCode() {
                    return ((527 + this.name.hashCode()) * 31) + WithLazyResolution.this.hashCode();
                }

                protected LazyResolution(String str) {
                    this.name = str;
                }

                public boolean isResolved() {
                    return WithLazyResolution.this.doResolve(this.name).isResolved();
                }

                public TypeDescription resolve() {
                    return new LazyTypeDescription(this.name);
                }
            }

            protected class LazyTypeDescription extends TypeDescription.AbstractBase.OfSimpleType.WithDelegation {
                private transient /* synthetic */ TypeDescription delegate;
                private final String name;

                protected LazyTypeDescription(String str) {
                    this.name = str;
                }

                public String getName() {
                    return this.name;
                }

                /* access modifiers changed from: protected */
                @CachedReturnPlugin.Enhance("delegate")
                public TypeDescription delegate() {
                    TypeDescription resolve = this.delegate != null ? null : WithLazyResolution.this.doResolve(this.name).resolve();
                    if (resolve == null) {
                        return this.delegate;
                    }
                    this.delegate = resolve;
                    return resolve;
                }
            }
        }

        protected interface AnnotationRegistrant {
            void onComplete();

            void register(String str, AnnotationValue<?, ?> annotationValue);

            public static abstract class AbstractBase implements AnnotationRegistrant {
                private final String descriptor;
                private final Map<String, AnnotationValue<?, ?>> values = new HashMap();

                /* access modifiers changed from: protected */
                public abstract List<LazyTypeDescription.AnnotationToken> getTokens();

                protected AbstractBase(String str) {
                    this.descriptor = str;
                }

                public void register(String str, AnnotationValue<?, ?> annotationValue) {
                    this.values.put(str, annotationValue);
                }

                public void onComplete() {
                    getTokens().add(new LazyTypeDescription.AnnotationToken(this.descriptor, this.values));
                }

                protected static abstract class ForTypeVariable extends AbstractBase {
                    private final String typePath;

                    /* access modifiers changed from: protected */
                    public abstract Map<String, List<LazyTypeDescription.AnnotationToken>> getPathMap();

                    protected ForTypeVariable(String str, TypePath typePath2) {
                        super(str);
                        String str2;
                        if (typePath2 == null) {
                            str2 = "";
                        } else {
                            str2 = typePath2.toString();
                        }
                        this.typePath = str2;
                    }

                    /* access modifiers changed from: protected */
                    public List<LazyTypeDescription.AnnotationToken> getTokens() {
                        Map<String, List<LazyTypeDescription.AnnotationToken>> pathMap = getPathMap();
                        List<LazyTypeDescription.AnnotationToken> list = pathMap.get(this.typePath);
                        if (list != null) {
                            return list;
                        }
                        ArrayList arrayList = new ArrayList();
                        pathMap.put(this.typePath, arrayList);
                        return arrayList;
                    }

                    protected static abstract class WithIndex extends ForTypeVariable {
                        private final int index;

                        /* access modifiers changed from: protected */
                        public abstract Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> getIndexedPathMap();

                        protected WithIndex(String str, TypePath typePath, int i) {
                            super(str, typePath);
                            this.index = i;
                        }

                        /* access modifiers changed from: protected */
                        public Map<String, List<LazyTypeDescription.AnnotationToken>> getPathMap() {
                            Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> indexedPathMap = getIndexedPathMap();
                            Map<String, List<LazyTypeDescription.AnnotationToken>> map = indexedPathMap.get(Integer.valueOf(this.index));
                            if (map != null) {
                                return map;
                            }
                            HashMap hashMap = new HashMap();
                            indexedPathMap.put(Integer.valueOf(this.index), hashMap);
                            return hashMap;
                        }

                        protected static abstract class DoubleIndexed extends WithIndex {
                            private final int preIndex;

                            /* access modifiers changed from: protected */
                            public abstract Map<Integer, Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>>> getDoubleIndexedPathMap();

                            protected DoubleIndexed(String str, TypePath typePath, int i, int i2) {
                                super(str, typePath, i);
                                this.preIndex = i2;
                            }

                            /* access modifiers changed from: protected */
                            public Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> getIndexedPathMap() {
                                Map<Integer, Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>>> doubleIndexedPathMap = getDoubleIndexedPathMap();
                                Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> map = doubleIndexedPathMap.get(Integer.valueOf(this.preIndex));
                                if (map != null) {
                                    return map;
                                }
                                HashMap hashMap = new HashMap();
                                doubleIndexedPathMap.put(Integer.valueOf(this.preIndex), hashMap);
                                return hashMap;
                            }
                        }
                    }
                }
            }

            public static class ForByteCodeElement extends AbstractBase {
                private final List<LazyTypeDescription.AnnotationToken> annotationTokens;

                protected ForByteCodeElement(String str, List<LazyTypeDescription.AnnotationToken> list) {
                    super(str);
                    this.annotationTokens = list;
                }

                /* access modifiers changed from: protected */
                public List<LazyTypeDescription.AnnotationToken> getTokens() {
                    return this.annotationTokens;
                }

                public static class WithIndex extends AbstractBase {
                    private final Map<Integer, List<LazyTypeDescription.AnnotationToken>> annotationTokens;
                    private final int index;

                    protected WithIndex(String str, int i, Map<Integer, List<LazyTypeDescription.AnnotationToken>> map) {
                        super(str);
                        this.index = i;
                        this.annotationTokens = map;
                    }

                    /* access modifiers changed from: protected */
                    public List<LazyTypeDescription.AnnotationToken> getTokens() {
                        List<LazyTypeDescription.AnnotationToken> list = this.annotationTokens.get(Integer.valueOf(this.index));
                        if (list != null) {
                            return list;
                        }
                        ArrayList arrayList = new ArrayList();
                        this.annotationTokens.put(Integer.valueOf(this.index), arrayList);
                        return arrayList;
                    }
                }
            }

            public static class ForTypeVariable extends AbstractBase.ForTypeVariable {
                private final Map<String, List<LazyTypeDescription.AnnotationToken>> pathMap;

                protected ForTypeVariable(String str, TypePath typePath, Map<String, List<LazyTypeDescription.AnnotationToken>> map) {
                    super(str, typePath);
                    this.pathMap = map;
                }

                /* access modifiers changed from: protected */
                public Map<String, List<LazyTypeDescription.AnnotationToken>> getPathMap() {
                    return this.pathMap;
                }

                public static class WithIndex extends AbstractBase.ForTypeVariable.WithIndex {
                    private final Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> indexedPathMap;

                    protected WithIndex(String str, TypePath typePath, int i, Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> map) {
                        super(str, typePath, i);
                        this.indexedPathMap = map;
                    }

                    /* access modifiers changed from: protected */
                    public Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> getIndexedPathMap() {
                        return this.indexedPathMap;
                    }

                    public static class DoubleIndexed extends AbstractBase.ForTypeVariable.WithIndex.DoubleIndexed {
                        private final Map<Integer, Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>>> doubleIndexedPathMap;

                        protected DoubleIndexed(String str, TypePath typePath, int i, int i2, Map<Integer, Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>>> map) {
                            super(str, typePath, i, i2);
                            this.doubleIndexedPathMap = map;
                        }

                        /* access modifiers changed from: protected */
                        public Map<Integer, Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>>> getDoubleIndexedPathMap() {
                            return this.doubleIndexedPathMap;
                        }
                    }
                }
            }
        }

        protected interface ComponentTypeLocator {
            AbstractBase.RawDescriptionArray.ComponentTypeReference bind(String str);

            public enum Illegal implements ComponentTypeLocator {
                INSTANCE;

                public AbstractBase.RawDescriptionArray.ComponentTypeReference bind(String str) {
                    throw new IllegalStateException("Unexpected lookup of component type for " + str);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForAnnotationProperty implements ComponentTypeLocator {
                /* access modifiers changed from: private */
                public final String annotationName;
                /* access modifiers changed from: private */
                public final TypePool typePool;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    ForAnnotationProperty forAnnotationProperty = (ForAnnotationProperty) obj;
                    return this.annotationName.equals(forAnnotationProperty.annotationName) && this.typePool.equals(forAnnotationProperty.typePool);
                }

                public int hashCode() {
                    return ((527 + this.typePool.hashCode()) * 31) + this.annotationName.hashCode();
                }

                public ForAnnotationProperty(TypePool typePool2, String str) {
                    this.typePool = typePool2;
                    this.annotationName = str.substring(1, str.length() - 1).replace('/', '.');
                }

                public AbstractBase.RawDescriptionArray.ComponentTypeReference bind(String str) {
                    return new Bound(str);
                }

                @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                protected class Bound implements AbstractBase.RawDescriptionArray.ComponentTypeReference {
                    private final String name;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        Bound bound = (Bound) obj;
                        return this.name.equals(bound.name) && ForAnnotationProperty.this.equals(ForAnnotationProperty.this);
                    }

                    public int hashCode() {
                        return ((527 + this.name.hashCode()) * 31) + ForAnnotationProperty.this.hashCode();
                    }

                    protected Bound(String str) {
                        this.name = str;
                    }

                    public String lookup() {
                        return ((MethodDescription.InDefinedShape) ((MethodList) ForAnnotationProperty.this.typePool.describe(ForAnnotationProperty.this.annotationName).resolve().getDeclaredMethods().filter(ElementMatchers.named(this.name))).getOnly()).getReturnType().asErasure().getComponentType().getName();
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForArrayType implements ComponentTypeLocator, AbstractBase.RawDescriptionArray.ComponentTypeReference {
                private final String componentType;

                public AbstractBase.RawDescriptionArray.ComponentTypeReference bind(String str) {
                    return this;
                }

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.componentType.equals(((ForArrayType) obj).componentType);
                }

                public int hashCode() {
                    return 527 + this.componentType.hashCode();
                }

                public ForArrayType(String str) {
                    String className = Type.getMethodType(str).getReturnType().getClassName();
                    this.componentType = className.substring(0, className.length() - 2);
                }

                public String lookup() {
                    return this.componentType;
                }
            }
        }

        protected interface GenericTypeRegistrant {
            void register(LazyTypeDescription.GenericTypeToken genericTypeToken);

            public static class RejectingSignatureVisitor extends SignatureVisitor {
                private static final String MESSAGE = "Unexpected token in generic signature";

                public RejectingSignatureVisitor() {
                    super(OpenedClassReader.ASM_API);
                }

                public void visitFormalTypeParameter(String str) {
                    throw new IllegalStateException(MESSAGE);
                }

                public SignatureVisitor visitClassBound() {
                    throw new IllegalStateException(MESSAGE);
                }

                public SignatureVisitor visitInterfaceBound() {
                    throw new IllegalStateException(MESSAGE);
                }

                public SignatureVisitor visitSuperclass() {
                    throw new IllegalStateException(MESSAGE);
                }

                public SignatureVisitor visitInterface() {
                    throw new IllegalStateException(MESSAGE);
                }

                public SignatureVisitor visitParameterType() {
                    throw new IllegalStateException(MESSAGE);
                }

                public SignatureVisitor visitReturnType() {
                    throw new IllegalStateException(MESSAGE);
                }

                public SignatureVisitor visitExceptionType() {
                    throw new IllegalStateException(MESSAGE);
                }

                public void visitBaseType(char c) {
                    throw new IllegalStateException(MESSAGE);
                }

                public void visitTypeVariable(String str) {
                    throw new IllegalStateException(MESSAGE);
                }

                public SignatureVisitor visitArrayType() {
                    throw new IllegalStateException(MESSAGE);
                }

                public void visitClassType(String str) {
                    throw new IllegalStateException(MESSAGE);
                }

                public void visitInnerClassType(String str) {
                    throw new IllegalStateException(MESSAGE);
                }

                public void visitTypeArgument() {
                    throw new IllegalStateException(MESSAGE);
                }

                public SignatureVisitor visitTypeArgument(char c) {
                    throw new IllegalStateException(MESSAGE);
                }

                public void visitEnd() {
                    throw new IllegalStateException(MESSAGE);
                }
            }
        }

        protected static class ParameterBag {
            private final Map<Integer, String> parameterRegistry = new HashMap();
            private final Type[] parameterType;

            protected ParameterBag(Type[] typeArr) {
                this.parameterType = typeArr;
            }

            /* access modifiers changed from: protected */
            public void register(int i, String str) {
                this.parameterRegistry.put(Integer.valueOf(i), str);
            }

            /* access modifiers changed from: protected */
            public List<LazyTypeDescription.MethodToken.ParameterToken> resolve(boolean z) {
                int i;
                ArrayList arrayList = new ArrayList(this.parameterType.length);
                if (z) {
                    i = StackSize.ZERO.getSize();
                } else {
                    i = StackSize.SINGLE.getSize();
                }
                for (Type type : this.parameterType) {
                    String str = this.parameterRegistry.get(Integer.valueOf(i));
                    arrayList.add(str == null ? new LazyTypeDescription.MethodToken.ParameterToken() : new LazyTypeDescription.MethodToken.ParameterToken(str));
                    i += type.getSize();
                }
                return arrayList;
            }
        }

        protected static class GenericTypeExtractor extends GenericTypeRegistrant.RejectingSignatureVisitor implements GenericTypeRegistrant {
            private final GenericTypeRegistrant genericTypeRegistrant;
            private IncompleteToken incompleteToken;

            protected GenericTypeExtractor(GenericTypeRegistrant genericTypeRegistrant2) {
                this.genericTypeRegistrant = genericTypeRegistrant2;
            }

            public void visitBaseType(char c) {
                this.genericTypeRegistrant.register(LazyTypeDescription.GenericTypeToken.ForPrimitiveType.of(c));
            }

            public void visitTypeVariable(String str) {
                this.genericTypeRegistrant.register(new LazyTypeDescription.GenericTypeToken.ForTypeVariable(str));
            }

            public SignatureVisitor visitArrayType() {
                return new GenericTypeExtractor(this);
            }

            public void register(LazyTypeDescription.GenericTypeToken genericTypeToken) {
                this.genericTypeRegistrant.register(new LazyTypeDescription.GenericTypeToken.ForGenericArray(genericTypeToken));
            }

            public void visitClassType(String str) {
                this.incompleteToken = new IncompleteToken.ForTopLevelType(str);
            }

            public void visitInnerClassType(String str) {
                this.incompleteToken = new IncompleteToken.ForInnerClass(str, this.incompleteToken);
            }

            public void visitTypeArgument() {
                this.incompleteToken.appendPlaceholder();
            }

            public SignatureVisitor visitTypeArgument(char c) {
                if (c == '+') {
                    return this.incompleteToken.appendUpperBound();
                }
                if (c == '-') {
                    return this.incompleteToken.appendLowerBound();
                }
                if (c == '=') {
                    return this.incompleteToken.appendDirectBound();
                }
                throw new IllegalArgumentException("Unknown wildcard: " + c);
            }

            public void visitEnd() {
                this.genericTypeRegistrant.register(this.incompleteToken.toToken());
            }

            protected interface IncompleteToken {
                SignatureVisitor appendDirectBound();

                SignatureVisitor appendLowerBound();

                void appendPlaceholder();

                SignatureVisitor appendUpperBound();

                String getName();

                boolean isParameterized();

                LazyTypeDescription.GenericTypeToken toToken();

                public static abstract class AbstractBase implements IncompleteToken {
                    protected final List<LazyTypeDescription.GenericTypeToken> parameters = new ArrayList();

                    public SignatureVisitor appendDirectBound() {
                        return new GenericTypeExtractor(new ForDirectBound());
                    }

                    public SignatureVisitor appendUpperBound() {
                        return new GenericTypeExtractor(new ForUpperBound());
                    }

                    public SignatureVisitor appendLowerBound() {
                        return new GenericTypeExtractor(new ForLowerBound());
                    }

                    public void appendPlaceholder() {
                        this.parameters.add(LazyTypeDescription.GenericTypeToken.ForUnboundWildcard.INSTANCE);
                    }

                    protected class ForDirectBound implements GenericTypeRegistrant {
                        protected ForDirectBound() {
                        }

                        public void register(LazyTypeDescription.GenericTypeToken genericTypeToken) {
                            AbstractBase.this.parameters.add(genericTypeToken);
                        }
                    }

                    protected class ForUpperBound implements GenericTypeRegistrant {
                        protected ForUpperBound() {
                        }

                        public void register(LazyTypeDescription.GenericTypeToken genericTypeToken) {
                            AbstractBase.this.parameters.add(new LazyTypeDescription.GenericTypeToken.ForUpperBoundWildcard(genericTypeToken));
                        }
                    }

                    protected class ForLowerBound implements GenericTypeRegistrant {
                        protected ForLowerBound() {
                        }

                        public void register(LazyTypeDescription.GenericTypeToken genericTypeToken) {
                            AbstractBase.this.parameters.add(new LazyTypeDescription.GenericTypeToken.ForLowerBoundWildcard(genericTypeToken));
                        }
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class ForTopLevelType extends AbstractBase {
                    private final String internalName;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.internalName.equals(((ForTopLevelType) obj).internalName);
                    }

                    public int hashCode() {
                        return 527 + this.internalName.hashCode();
                    }

                    public ForTopLevelType(String str) {
                        this.internalName = str;
                    }

                    public LazyTypeDescription.GenericTypeToken toToken() {
                        if (isParameterized()) {
                            return new LazyTypeDescription.GenericTypeToken.ForParameterizedType(getName(), this.parameters);
                        }
                        return new LazyTypeDescription.GenericTypeToken.ForRawType(getName());
                    }

                    public boolean isParameterized() {
                        return !this.parameters.isEmpty();
                    }

                    public String getName() {
                        return this.internalName.replace('/', '.');
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class ForInnerClass extends AbstractBase {
                    private static final char INNER_CLASS_SEPARATOR = '$';
                    private final String internalName;
                    private final IncompleteToken outerTypeToken;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        ForInnerClass forInnerClass = (ForInnerClass) obj;
                        return this.internalName.equals(forInnerClass.internalName) && this.outerTypeToken.equals(forInnerClass.outerTypeToken);
                    }

                    public int hashCode() {
                        return ((527 + this.internalName.hashCode()) * 31) + this.outerTypeToken.hashCode();
                    }

                    public ForInnerClass(String str, IncompleteToken incompleteToken) {
                        this.internalName = str;
                        this.outerTypeToken = incompleteToken;
                    }

                    public LazyTypeDescription.GenericTypeToken toToken() {
                        if (isParameterized() || this.outerTypeToken.isParameterized()) {
                            return new LazyTypeDescription.GenericTypeToken.ForParameterizedType.Nested(getName(), this.parameters, this.outerTypeToken.toToken());
                        }
                        return new LazyTypeDescription.GenericTypeToken.ForRawType(getName());
                    }

                    public boolean isParameterized() {
                        return !this.parameters.isEmpty() || !this.outerTypeToken.isParameterized();
                    }

                    public String getName() {
                        return this.outerTypeToken.getName() + '$' + this.internalName.replace('/', '.');
                    }
                }
            }

            protected static abstract class ForSignature<T extends LazyTypeDescription.GenericTypeToken.Resolution> extends GenericTypeRegistrant.RejectingSignatureVisitor implements GenericTypeRegistrant {
                protected List<LazyTypeDescription.GenericTypeToken> currentBounds;
                protected String currentTypeParameter;
                protected final List<LazyTypeDescription.GenericTypeToken.OfFormalTypeVariable> typeVariableTokens = new ArrayList();

                public abstract T resolve();

                protected static <S extends LazyTypeDescription.GenericTypeToken.Resolution> S extract(String str, ForSignature<S> forSignature) {
                    new SignatureReader(str).accept(forSignature);
                    return forSignature.resolve();
                }

                public void visitFormalTypeParameter(String str) {
                    collectTypeParameter();
                    this.currentTypeParameter = str;
                    this.currentBounds = new ArrayList();
                }

                public SignatureVisitor visitClassBound() {
                    return new GenericTypeExtractor(this);
                }

                public SignatureVisitor visitInterfaceBound() {
                    return new GenericTypeExtractor(this);
                }

                public void register(LazyTypeDescription.GenericTypeToken genericTypeToken) {
                    List<LazyTypeDescription.GenericTypeToken> list = this.currentBounds;
                    if (list != null) {
                        list.add(genericTypeToken);
                        return;
                    }
                    throw new IllegalStateException("Did not expect " + genericTypeToken + " before finding formal parameter");
                }

                /* access modifiers changed from: protected */
                public void collectTypeParameter() {
                    String str = this.currentTypeParameter;
                    if (str != null) {
                        this.typeVariableTokens.add(new LazyTypeDescription.GenericTypeToken.ForTypeVariable.Formal(str, this.currentBounds));
                    }
                }

                protected static class OfType extends ForSignature<LazyTypeDescription.GenericTypeToken.Resolution.ForType> {
                    /* access modifiers changed from: private */
                    public final List<LazyTypeDescription.GenericTypeToken> interfaceTypeTokens = new ArrayList();
                    /* access modifiers changed from: private */
                    public LazyTypeDescription.GenericTypeToken superClassToken;

                    protected OfType() {
                    }

                    public static LazyTypeDescription.GenericTypeToken.Resolution.ForType extract(String str) {
                        if (str != null) {
                            return (LazyTypeDescription.GenericTypeToken.Resolution.ForType) ForSignature.extract(str, new OfType());
                        }
                        try {
                            return LazyTypeDescription.GenericTypeToken.Resolution.Raw.INSTANCE;
                        } catch (RuntimeException unused) {
                            return LazyTypeDescription.GenericTypeToken.Resolution.Malformed.INSTANCE;
                        }
                    }

                    public SignatureVisitor visitSuperclass() {
                        collectTypeParameter();
                        return new GenericTypeExtractor(new SuperClassRegistrant());
                    }

                    public SignatureVisitor visitInterface() {
                        return new GenericTypeExtractor(new InterfaceTypeRegistrant());
                    }

                    public LazyTypeDescription.GenericTypeToken.Resolution.ForType resolve() {
                        return new LazyTypeDescription.GenericTypeToken.Resolution.ForType.Tokenized(this.superClassToken, this.interfaceTypeTokens, this.typeVariableTokens);
                    }

                    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                    protected class SuperClassRegistrant implements GenericTypeRegistrant {
                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && OfType.this.equals(OfType.this);
                        }

                        public int hashCode() {
                            return 527 + OfType.this.hashCode();
                        }

                        protected SuperClassRegistrant() {
                        }

                        public void register(LazyTypeDescription.GenericTypeToken genericTypeToken) {
                            LazyTypeDescription.GenericTypeToken unused = OfType.this.superClassToken = genericTypeToken;
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                    protected class InterfaceTypeRegistrant implements GenericTypeRegistrant {
                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && OfType.this.equals(OfType.this);
                        }

                        public int hashCode() {
                            return 527 + OfType.this.hashCode();
                        }

                        protected InterfaceTypeRegistrant() {
                        }

                        public void register(LazyTypeDescription.GenericTypeToken genericTypeToken) {
                            OfType.this.interfaceTypeTokens.add(genericTypeToken);
                        }
                    }
                }

                protected static class OfMethod extends ForSignature<LazyTypeDescription.GenericTypeToken.Resolution.ForMethod> {
                    /* access modifiers changed from: private */
                    public final List<LazyTypeDescription.GenericTypeToken> exceptionTypeTokens = new ArrayList();
                    /* access modifiers changed from: private */
                    public final List<LazyTypeDescription.GenericTypeToken> parameterTypeTokens = new ArrayList();
                    /* access modifiers changed from: private */
                    public LazyTypeDescription.GenericTypeToken returnTypeToken;

                    public static LazyTypeDescription.GenericTypeToken.Resolution.ForMethod extract(String str) {
                        if (str != null) {
                            return (LazyTypeDescription.GenericTypeToken.Resolution.ForMethod) ForSignature.extract(str, new OfMethod());
                        }
                        try {
                            return LazyTypeDescription.GenericTypeToken.Resolution.Raw.INSTANCE;
                        } catch (RuntimeException unused) {
                            return LazyTypeDescription.GenericTypeToken.Resolution.Malformed.INSTANCE;
                        }
                    }

                    public SignatureVisitor visitParameterType() {
                        return new GenericTypeExtractor(new ParameterTypeRegistrant());
                    }

                    public SignatureVisitor visitReturnType() {
                        collectTypeParameter();
                        return new GenericTypeExtractor(new ReturnTypeTypeRegistrant());
                    }

                    public SignatureVisitor visitExceptionType() {
                        return new GenericTypeExtractor(new ExceptionTypeRegistrant());
                    }

                    public LazyTypeDescription.GenericTypeToken.Resolution.ForMethod resolve() {
                        return new LazyTypeDescription.GenericTypeToken.Resolution.ForMethod.Tokenized(this.returnTypeToken, this.parameterTypeTokens, this.exceptionTypeTokens, this.typeVariableTokens);
                    }

                    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                    protected class ParameterTypeRegistrant implements GenericTypeRegistrant {
                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && OfMethod.this.equals(OfMethod.this);
                        }

                        public int hashCode() {
                            return 527 + OfMethod.this.hashCode();
                        }

                        protected ParameterTypeRegistrant() {
                        }

                        public void register(LazyTypeDescription.GenericTypeToken genericTypeToken) {
                            OfMethod.this.parameterTypeTokens.add(genericTypeToken);
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                    protected class ReturnTypeTypeRegistrant implements GenericTypeRegistrant {
                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && OfMethod.this.equals(OfMethod.this);
                        }

                        public int hashCode() {
                            return 527 + OfMethod.this.hashCode();
                        }

                        protected ReturnTypeTypeRegistrant() {
                        }

                        public void register(LazyTypeDescription.GenericTypeToken genericTypeToken) {
                            LazyTypeDescription.GenericTypeToken unused = OfMethod.this.returnTypeToken = genericTypeToken;
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                    protected class ExceptionTypeRegistrant implements GenericTypeRegistrant {
                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && OfMethod.this.equals(OfMethod.this);
                        }

                        public int hashCode() {
                            return 527 + OfMethod.this.hashCode();
                        }

                        protected ExceptionTypeRegistrant() {
                        }

                        public void register(LazyTypeDescription.GenericTypeToken genericTypeToken) {
                            OfMethod.this.exceptionTypeTokens.add(genericTypeToken);
                        }
                    }
                }

                protected static class OfField implements GenericTypeRegistrant {
                    private LazyTypeDescription.GenericTypeToken fieldTypeToken;

                    protected OfField() {
                    }

                    public static LazyTypeDescription.GenericTypeToken.Resolution.ForField extract(String str) {
                        if (str == null) {
                            return LazyTypeDescription.GenericTypeToken.Resolution.Raw.INSTANCE;
                        }
                        SignatureReader signatureReader = new SignatureReader(str);
                        OfField ofField = new OfField();
                        try {
                            signatureReader.acceptType(new GenericTypeExtractor(ofField));
                            return ofField.resolve();
                        } catch (RuntimeException unused) {
                            return LazyTypeDescription.GenericTypeToken.Resolution.Malformed.INSTANCE;
                        }
                    }

                    public void register(LazyTypeDescription.GenericTypeToken genericTypeToken) {
                        this.fieldTypeToken = genericTypeToken;
                    }

                    /* access modifiers changed from: protected */
                    public LazyTypeDescription.GenericTypeToken.Resolution.ForField resolve() {
                        return new LazyTypeDescription.GenericTypeToken.Resolution.ForField.Tokenized(this.fieldTypeToken);
                    }
                }
            }
        }

        protected static class LazyTypeDescription extends TypeDescription.AbstractBase.OfSimpleType {
            private static final String NO_TYPE = null;
            private static final int SUPER_CLASS_INDEX = -1;
            private final int actualModifiers;
            private final List<AnnotationToken> annotationTokens;
            private final boolean anonymousType;
            private final List<String> declaredTypes;
            private final String declaringTypeName;
            /* access modifiers changed from: private */
            public final List<FieldToken> fieldTokens;
            private final String genericSignature;
            private final List<String> interfaceTypeDescriptors;
            /* access modifiers changed from: private */
            public final List<MethodToken> methodTokens;
            private final int modifiers;
            private final String name;
            private final String nestHost;
            private final List<String> nestMembers;
            private final GenericTypeToken.Resolution.ForType signatureResolution;
            private final String superClassDescriptor;
            private final Map<Integer, Map<String, List<AnnotationToken>>> superTypeAnnotationTokens;
            private final TypeContainment typeContainment;
            /* access modifiers changed from: private */
            public final TypePool typePool;
            private final Map<Integer, Map<String, List<AnnotationToken>>> typeVariableAnnotationTokens;
            private final Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> typeVariableBoundsAnnotationTokens;

            protected LazyTypeDescription(TypePool typePool2, int i, int i2, String str, String str2, String[] strArr, String str3, TypeContainment typeContainment2, String str4, List<String> list, boolean z, String str5, List<String> list2, Map<Integer, Map<String, List<AnnotationToken>>> map, Map<Integer, Map<String, List<AnnotationToken>>> map2, Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> map3, List<AnnotationToken> list3, List<FieldToken> list4, List<MethodToken> list5) {
                String str6;
                GenericTypeToken.Resolution.ForType forType;
                String str7;
                String str8;
                String[] strArr2 = strArr;
                String str9 = str4;
                this.typePool = typePool2;
                this.actualModifiers = i & -33;
                this.modifiers = -131105 & i2;
                this.name = Type.getObjectType(str).getClassName();
                if (str2 == null) {
                    str6 = NO_TYPE;
                } else {
                    str6 = Type.getObjectType(str2).getDescriptor();
                }
                this.superClassDescriptor = str6;
                this.genericSignature = str3;
                if (RAW_TYPES) {
                    forType = GenericTypeToken.Resolution.Raw.INSTANCE;
                } else {
                    forType = GenericTypeExtractor.ForSignature.OfType.extract(str3);
                }
                this.signatureResolution = forType;
                if (strArr2 == null) {
                    this.interfaceTypeDescriptors = Collections.emptyList();
                } else {
                    this.interfaceTypeDescriptors = new ArrayList(strArr2.length);
                    for (String objectType : strArr2) {
                        this.interfaceTypeDescriptors.add(Type.getObjectType(objectType).getDescriptor());
                    }
                }
                this.typeContainment = typeContainment2;
                if (str9 == null) {
                    str7 = NO_TYPE;
                } else {
                    str7 = str9.replace('/', '.');
                }
                this.declaringTypeName = str7;
                this.declaredTypes = list;
                this.anonymousType = z;
                if (str5 == null) {
                    str8 = NO_TYPE;
                } else {
                    str8 = Type.getObjectType(str5).getClassName();
                }
                this.nestHost = str8;
                this.nestMembers = new ArrayList(list2.size());
                for (String objectType2 : list2) {
                    this.nestMembers.add(Type.getObjectType(objectType2).getClassName());
                }
                this.superTypeAnnotationTokens = map;
                this.typeVariableAnnotationTokens = map2;
                this.typeVariableBoundsAnnotationTokens = map3;
                this.annotationTokens = list3;
                this.fieldTokens = list4;
                this.methodTokens = list5;
            }

            public TypeDescription.Generic getSuperClass() {
                if (this.superClassDescriptor == null || isInterface()) {
                    return TypeDescription.Generic.UNDEFINED;
                }
                return this.signatureResolution.resolveSuperClass(this.superClassDescriptor, this.typePool, this.superTypeAnnotationTokens.get(-1), this);
            }

            public TypeList.Generic getInterfaces() {
                return this.signatureResolution.resolveInterfaceTypes(this.interfaceTypeDescriptors, this.typePool, this.superTypeAnnotationTokens, this);
            }

            public MethodDescription.InDefinedShape getEnclosingMethod() {
                return this.typeContainment.getEnclosingMethod(this.typePool);
            }

            public TypeDescription getEnclosingType() {
                return this.typeContainment.getEnclosingType(this.typePool);
            }

            public TypeList getDeclaredTypes() {
                return new LazyTypeList(this.typePool, this.declaredTypes);
            }

            public boolean isAnonymousType() {
                return this.anonymousType;
            }

            public boolean isLocalType() {
                return !this.anonymousType && this.typeContainment.isLocalType();
            }

            public FieldList<FieldDescription.InDefinedShape> getDeclaredFields() {
                return new FieldTokenList();
            }

            public MethodList<MethodDescription.InDefinedShape> getDeclaredMethods() {
                return new MethodTokenList();
            }

            public PackageDescription getPackage() {
                String str;
                String name2 = getName();
                int lastIndexOf = name2.lastIndexOf(46);
                TypePool typePool2 = this.typePool;
                if (lastIndexOf == -1) {
                    str = "";
                } else {
                    str = name2.substring(0, lastIndexOf);
                }
                return new LazyPackageDescription(typePool2, str);
            }

            public String getName() {
                return this.name;
            }

            public TypeDescription getDeclaringType() {
                String str = this.declaringTypeName;
                if (str == null) {
                    return TypeDescription.UNDEFINED;
                }
                return this.typePool.describe(str).resolve();
            }

            public int getModifiers() {
                return this.modifiers;
            }

            public int getActualModifiers(boolean z) {
                return z ? this.actualModifiers | 32 : this.actualModifiers;
            }

            public TypeDescription getNestHost() {
                String str = this.nestHost;
                if (str == null) {
                    return this;
                }
                return this.typePool.describe(str).resolve();
            }

            public TypeList getNestMembers() {
                String str = this.nestHost;
                if (str == null) {
                    return new LazyNestMemberList(this, this.typePool, this.nestMembers);
                }
                return this.typePool.describe(str).resolve().getNestMembers();
            }

            public AnnotationList getDeclaredAnnotations() {
                return LazyAnnotationDescription.asList(this.typePool, this.annotationTokens);
            }

            public TypeList.Generic getTypeVariables() {
                return this.signatureResolution.resolveTypeVariables(this.typePool, this, this.typeVariableAnnotationTokens, this.typeVariableBoundsAnnotationTokens);
            }

            public String getGenericSignature() {
                return this.genericSignature;
            }

            protected class FieldTokenList extends FieldList.AbstractBase<FieldDescription.InDefinedShape> {
                protected FieldTokenList() {
                }

                public FieldDescription.InDefinedShape get(int i) {
                    return ((FieldToken) LazyTypeDescription.this.fieldTokens.get(i)).toFieldDescription(LazyTypeDescription.this);
                }

                public int size() {
                    return LazyTypeDescription.this.fieldTokens.size();
                }
            }

            protected class MethodTokenList extends MethodList.AbstractBase<MethodDescription.InDefinedShape> {
                protected MethodTokenList() {
                }

                public MethodDescription.InDefinedShape get(int i) {
                    return ((MethodToken) LazyTypeDescription.this.methodTokens.get(i)).toMethodDescription(LazyTypeDescription.this);
                }

                public int size() {
                    return LazyTypeDescription.this.methodTokens.size();
                }
            }

            protected interface TypeContainment {
                MethodDescription.InDefinedShape getEnclosingMethod(TypePool typePool);

                TypeDescription getEnclosingType(TypePool typePool);

                boolean isLocalType();

                boolean isSelfContained();

                public enum SelfContained implements TypeContainment {
                    INSTANCE;

                    public boolean isLocalType() {
                        return false;
                    }

                    public boolean isSelfContained() {
                        return true;
                    }

                    public MethodDescription.InDefinedShape getEnclosingMethod(TypePool typePool) {
                        return MethodDescription.UNDEFINED;
                    }

                    public TypeDescription getEnclosingType(TypePool typePool) {
                        return TypeDescription.UNDEFINED;
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class WithinType implements TypeContainment {
                    private final boolean localType;
                    private final String name;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        WithinType withinType = (WithinType) obj;
                        return this.localType == withinType.localType && this.name.equals(withinType.name);
                    }

                    public int hashCode() {
                        return ((527 + this.name.hashCode()) * 31) + (this.localType ? 1 : 0);
                    }

                    public boolean isSelfContained() {
                        return false;
                    }

                    protected WithinType(String str, boolean z) {
                        this.name = str.replace('/', '.');
                        this.localType = z;
                    }

                    public MethodDescription.InDefinedShape getEnclosingMethod(TypePool typePool) {
                        return MethodDescription.UNDEFINED;
                    }

                    public TypeDescription getEnclosingType(TypePool typePool) {
                        return typePool.describe(this.name).resolve();
                    }

                    public boolean isLocalType() {
                        return this.localType;
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class WithinMethod implements TypeContainment {
                    private final String methodDescriptor;
                    private final String methodName;
                    private final String name;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        WithinMethod withinMethod = (WithinMethod) obj;
                        return this.name.equals(withinMethod.name) && this.methodName.equals(withinMethod.methodName) && this.methodDescriptor.equals(withinMethod.methodDescriptor);
                    }

                    public int hashCode() {
                        return ((((527 + this.name.hashCode()) * 31) + this.methodName.hashCode()) * 31) + this.methodDescriptor.hashCode();
                    }

                    public boolean isLocalType() {
                        return true;
                    }

                    public boolean isSelfContained() {
                        return false;
                    }

                    protected WithinMethod(String str, String str2, String str3) {
                        this.name = str.replace('/', '.');
                        this.methodName = str2;
                        this.methodDescriptor = str3;
                    }

                    public MethodDescription.InDefinedShape getEnclosingMethod(TypePool typePool) {
                        TypeDescription enclosingType = getEnclosingType(typePool);
                        MethodList methodList = (MethodList) enclosingType.getDeclaredMethods().filter(ElementMatchers.hasMethodName(this.methodName).and(ElementMatchers.hasDescriptor(this.methodDescriptor)));
                        if (!methodList.isEmpty()) {
                            return (MethodDescription.InDefinedShape) methodList.getOnly();
                        }
                        throw new IllegalStateException(this.methodName + this.methodDescriptor + " not declared by " + enclosingType);
                    }

                    public TypeDescription getEnclosingType(TypePool typePool) {
                        return typePool.describe(this.name).resolve();
                    }
                }
            }

            protected interface GenericTypeToken {
                public static final char COMPONENT_TYPE_PATH = '[';
                public static final String EMPTY_TYPE_PATH = "";
                public static final char INDEXED_TYPE_DELIMITER = ';';
                public static final char INNER_CLASS_PATH = '.';
                public static final char WILDCARD_TYPE_PATH = '*';

                public interface OfFormalTypeVariable {
                    TypeDescription.Generic toGenericType(TypePool typePool, TypeVariableSource typeVariableSource, Map<String, List<AnnotationToken>> map, Map<Integer, Map<String, List<AnnotationToken>>> map2);
                }

                String getTypePathPrefix();

                boolean isPrimaryBound(TypePool typePool);

                TypeDescription.Generic toGenericType(TypePool typePool, TypeVariableSource typeVariableSource, String str, Map<String, List<AnnotationToken>> map);

                public enum ForPrimitiveType implements GenericTypeToken {
                    BOOLEAN(Boolean.TYPE),
                    BYTE(Byte.TYPE),
                    SHORT(Short.TYPE),
                    CHAR(Character.TYPE),
                    INTEGER(Integer.TYPE),
                    LONG(Long.TYPE),
                    FLOAT(Float.TYPE),
                    DOUBLE(Double.TYPE),
                    VOID(Void.TYPE);
                    
                    private final TypeDescription typeDescription;

                    private ForPrimitiveType(Class<?> cls) {
                        this.typeDescription = TypeDescription.ForLoadedType.of(cls);
                    }

                    public static GenericTypeToken of(char c) {
                        if (c == 'F') {
                            return FLOAT;
                        }
                        if (c == 'S') {
                            return SHORT;
                        }
                        if (c == 'V') {
                            return VOID;
                        }
                        if (c == 'Z') {
                            return BOOLEAN;
                        }
                        if (c == 'I') {
                            return INTEGER;
                        }
                        if (c == 'J') {
                            return LONG;
                        }
                        switch (c) {
                            case 'B':
                                return BYTE;
                            case 'C':
                                return CHAR;
                            case 'D':
                                return DOUBLE;
                            default:
                                throw new IllegalArgumentException("Not a valid primitive type descriptor: " + c);
                        }
                    }

                    public TypeDescription.Generic toGenericType(TypePool typePool, TypeVariableSource typeVariableSource, String str, Map<String, List<AnnotationToken>> map) {
                        if (map == null) {
                            map = Collections.emptyMap();
                        }
                        return new LazyPrimitiveType(typePool, str, map, this.typeDescription);
                    }

                    public boolean isPrimaryBound(TypePool typePool) {
                        throw new IllegalStateException("A primitive type cannot be a type variable bound: " + this);
                    }

                    public String getTypePathPrefix() {
                        throw new IllegalStateException("A primitive type cannot be the owner of a nested type: " + this);
                    }

                    protected static class LazyPrimitiveType extends TypeDescription.Generic.OfNonGenericType {
                        private final Map<String, List<AnnotationToken>> annotationTokens;
                        private final TypeDescription typeDescription;
                        private final String typePath;
                        private final TypePool typePool;

                        protected LazyPrimitiveType(TypePool typePool2, String str, Map<String, List<AnnotationToken>> map, TypeDescription typeDescription2) {
                            this.typePool = typePool2;
                            this.typePath = str;
                            this.annotationTokens = map;
                            this.typeDescription = typeDescription2;
                        }

                        public TypeDescription asErasure() {
                            return this.typeDescription;
                        }

                        public TypeDescription.Generic getOwnerType() {
                            return TypeDescription.Generic.UNDEFINED;
                        }

                        public TypeDescription.Generic getComponentType() {
                            return TypeDescription.Generic.UNDEFINED;
                        }

                        public AnnotationList getDeclaredAnnotations() {
                            return LazyAnnotationDescription.asListOfNullable(this.typePool, this.annotationTokens.get(this.typePath));
                        }
                    }
                }

                public enum ForUnboundWildcard implements GenericTypeToken {
                    INSTANCE;

                    public TypeDescription.Generic toGenericType(TypePool typePool, TypeVariableSource typeVariableSource, String str, Map<String, List<AnnotationToken>> map) {
                        if (map == null) {
                            map = Collections.emptyMap();
                        }
                        return new LazyUnboundWildcard(typePool, str, map);
                    }

                    public boolean isPrimaryBound(TypePool typePool) {
                        throw new IllegalStateException("A wildcard type cannot be a type variable bound: " + this);
                    }

                    public String getTypePathPrefix() {
                        throw new IllegalStateException("An unbound wildcard cannot be the owner of a nested type: " + this);
                    }

                    protected static class LazyUnboundWildcard extends TypeDescription.Generic.OfWildcardType {
                        private final Map<String, List<AnnotationToken>> annotationTokens;
                        private final String typePath;
                        private final TypePool typePool;

                        protected LazyUnboundWildcard(TypePool typePool2, String str, Map<String, List<AnnotationToken>> map) {
                            this.typePool = typePool2;
                            this.typePath = str;
                            this.annotationTokens = map;
                        }

                        public TypeList.Generic getUpperBounds() {
                            return new TypeList.Generic.Explicit(TypeDescription.Generic.OBJECT);
                        }

                        public TypeList.Generic getLowerBounds() {
                            return new TypeList.Generic.Empty();
                        }

                        public AnnotationList getDeclaredAnnotations() {
                            return LazyAnnotationDescription.asListOfNullable(this.typePool, this.annotationTokens.get(this.typePath));
                        }
                    }
                }

                public interface Resolution {
                    TypeList.Generic resolveTypeVariables(TypePool typePool, TypeVariableSource typeVariableSource, Map<Integer, Map<String, List<AnnotationToken>>> map, Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> map2);

                    public enum Raw implements ForType, ForMethod, ForField {
                        INSTANCE;

                        public TypeDescription.Generic resolveFieldType(String str, TypePool typePool, Map<String, List<AnnotationToken>> map, FieldDescription.InDefinedShape inDefinedShape) {
                            return RawAnnotatedType.of(typePool, map, str);
                        }

                        public TypeDescription.Generic resolveReturnType(String str, TypePool typePool, Map<String, List<AnnotationToken>> map, MethodDescription.InDefinedShape inDefinedShape) {
                            return RawAnnotatedType.of(typePool, map, str);
                        }

                        public TypeList.Generic resolveParameterTypes(List<String> list, TypePool typePool, Map<Integer, Map<String, List<AnnotationToken>>> map, MethodDescription.InDefinedShape inDefinedShape) {
                            return RawAnnotatedType.LazyRawAnnotatedTypeList.of(typePool, map, list);
                        }

                        public TypeList.Generic resolveExceptionTypes(List<String> list, TypePool typePool, Map<Integer, Map<String, List<AnnotationToken>>> map, MethodDescription.InDefinedShape inDefinedShape) {
                            return RawAnnotatedType.LazyRawAnnotatedTypeList.of(typePool, map, list);
                        }

                        public TypeDescription.Generic resolveSuperClass(String str, TypePool typePool, Map<String, List<AnnotationToken>> map, TypeDescription typeDescription) {
                            return RawAnnotatedType.of(typePool, map, str);
                        }

                        public TypeList.Generic resolveInterfaceTypes(List<String> list, TypePool typePool, Map<Integer, Map<String, List<AnnotationToken>>> map, TypeDescription typeDescription) {
                            return RawAnnotatedType.LazyRawAnnotatedTypeList.of(typePool, map, list);
                        }

                        public TypeList.Generic resolveTypeVariables(TypePool typePool, TypeVariableSource typeVariableSource, Map<Integer, Map<String, List<AnnotationToken>>> map, Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> map2) {
                            return new TypeList.Generic.Empty();
                        }

                        protected static class RawAnnotatedType extends TypeDescription.Generic.OfNonGenericType {
                            private final Map<String, List<AnnotationToken>> annotationTokens;
                            private final TypeDescription typeDescription;
                            private final String typePath;
                            private final TypePool typePool;

                            protected RawAnnotatedType(TypePool typePool2, String str, Map<String, List<AnnotationToken>> map, TypeDescription typeDescription2) {
                                this.typePool = typePool2;
                                this.typePath = str;
                                this.annotationTokens = map;
                                this.typeDescription = typeDescription2;
                            }

                            protected static TypeDescription.Generic of(TypePool typePool2, Map<String, List<AnnotationToken>> map, String str) {
                                if (map == null) {
                                    map = Collections.emptyMap();
                                }
                                return new RawAnnotatedType(typePool2, "", map, TokenizedGenericType.toErasure(typePool2, str));
                            }

                            public TypeDescription asErasure() {
                                return this.typeDescription;
                            }

                            public TypeDescription.Generic getOwnerType() {
                                TypeDescription declaringType = this.typeDescription.getDeclaringType();
                                return declaringType == null ? TypeDescription.Generic.UNDEFINED : new RawAnnotatedType(this.typePool, this.typePath, this.annotationTokens, declaringType);
                            }

                            public TypeDescription.Generic getComponentType() {
                                TypeDescription componentType = this.typeDescription.getComponentType();
                                if (componentType == null) {
                                    return TypeDescription.Generic.UNDEFINED;
                                }
                                TypePool typePool2 = this.typePool;
                                return new RawAnnotatedType(typePool2, this.typePath + GenericTypeToken.COMPONENT_TYPE_PATH, this.annotationTokens, componentType);
                            }

                            public AnnotationList getDeclaredAnnotations() {
                                StringBuilder sb = new StringBuilder(this.typePath);
                                for (int i = 0; i < this.typeDescription.getInnerClassCount(); i++) {
                                    sb.append('.');
                                }
                                return LazyAnnotationDescription.asListOfNullable(this.typePool, this.annotationTokens.get(sb.toString()));
                            }

                            protected static class LazyRawAnnotatedTypeList extends TypeList.Generic.AbstractBase {
                                private final Map<Integer, Map<String, List<AnnotationToken>>> annotationTokens;
                                private final List<String> descriptors;
                                private final TypePool typePool;

                                public TypeList.Generic asRawTypes() {
                                    return this;
                                }

                                protected LazyRawAnnotatedTypeList(TypePool typePool2, Map<Integer, Map<String, List<AnnotationToken>>> map, List<String> list) {
                                    this.typePool = typePool2;
                                    this.annotationTokens = map;
                                    this.descriptors = list;
                                }

                                protected static TypeList.Generic of(TypePool typePool2, Map<Integer, Map<String, List<AnnotationToken>>> map, List<String> list) {
                                    if (map == null) {
                                        map = Collections.emptyMap();
                                    }
                                    return new LazyRawAnnotatedTypeList(typePool2, map, list);
                                }

                                public TypeDescription.Generic get(int i) {
                                    return RawAnnotatedType.of(this.typePool, this.annotationTokens.get(Integer.valueOf(i)), this.descriptors.get(i));
                                }

                                public int size() {
                                    return this.descriptors.size();
                                }

                                public TypeList asErasures() {
                                    return new LazyTypeList(this.typePool, this.descriptors);
                                }

                                public int getStackSize() {
                                    int i = 0;
                                    for (String type : this.descriptors) {
                                        i += Type.getType(type).getSize();
                                    }
                                    return i;
                                }
                            }
                        }
                    }

                    public enum Malformed implements ForType, ForMethod, ForField {
                        INSTANCE;

                        public TypeDescription.Generic resolveFieldType(String str, TypePool typePool, Map<String, List<AnnotationToken>> map, FieldDescription.InDefinedShape inDefinedShape) {
                            return new TokenizedGenericType.Malformed(typePool, str);
                        }

                        public TypeDescription.Generic resolveReturnType(String str, TypePool typePool, Map<String, List<AnnotationToken>> map, MethodDescription.InDefinedShape inDefinedShape) {
                            return new TokenizedGenericType.Malformed(typePool, str);
                        }

                        public TypeList.Generic resolveParameterTypes(List<String> list, TypePool typePool, Map<Integer, Map<String, List<AnnotationToken>>> map, MethodDescription.InDefinedShape inDefinedShape) {
                            return new TokenizedGenericType.Malformed.TokenList(typePool, list);
                        }

                        public TypeList.Generic resolveExceptionTypes(List<String> list, TypePool typePool, Map<Integer, Map<String, List<AnnotationToken>>> map, MethodDescription.InDefinedShape inDefinedShape) {
                            return new TokenizedGenericType.Malformed.TokenList(typePool, list);
                        }

                        public TypeDescription.Generic resolveSuperClass(String str, TypePool typePool, Map<String, List<AnnotationToken>> map, TypeDescription typeDescription) {
                            return new TokenizedGenericType.Malformed(typePool, str);
                        }

                        public TypeList.Generic resolveInterfaceTypes(List<String> list, TypePool typePool, Map<Integer, Map<String, List<AnnotationToken>>> map, TypeDescription typeDescription) {
                            return new TokenizedGenericType.Malformed.TokenList(typePool, list);
                        }

                        public TypeList.Generic resolveTypeVariables(TypePool typePool, TypeVariableSource typeVariableSource, Map<Integer, Map<String, List<AnnotationToken>>> map, Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> map2) {
                            throw new GenericSignatureFormatError();
                        }
                    }

                    public interface ForType extends Resolution {
                        TypeList.Generic resolveInterfaceTypes(List<String> list, TypePool typePool, Map<Integer, Map<String, List<AnnotationToken>>> map, TypeDescription typeDescription);

                        TypeDescription.Generic resolveSuperClass(String str, TypePool typePool, Map<String, List<AnnotationToken>> map, TypeDescription typeDescription);

                        @HashCodeAndEqualsPlugin.Enhance
                        public static class Tokenized implements ForType {
                            private final List<GenericTypeToken> interfaceTypeTokens;
                            private final GenericTypeToken superClassToken;
                            private final List<OfFormalTypeVariable> typeVariableTokens;

                            public boolean equals(Object obj) {
                                if (this == obj) {
                                    return true;
                                }
                                if (obj == null || getClass() != obj.getClass()) {
                                    return false;
                                }
                                Tokenized tokenized = (Tokenized) obj;
                                return this.superClassToken.equals(tokenized.superClassToken) && this.interfaceTypeTokens.equals(tokenized.interfaceTypeTokens) && this.typeVariableTokens.equals(tokenized.typeVariableTokens);
                            }

                            public int hashCode() {
                                return ((((527 + this.superClassToken.hashCode()) * 31) + this.interfaceTypeTokens.hashCode()) * 31) + this.typeVariableTokens.hashCode();
                            }

                            protected Tokenized(GenericTypeToken genericTypeToken, List<GenericTypeToken> list, List<OfFormalTypeVariable> list2) {
                                this.superClassToken = genericTypeToken;
                                this.interfaceTypeTokens = list;
                                this.typeVariableTokens = list2;
                            }

                            public TypeDescription.Generic resolveSuperClass(String str, TypePool typePool, Map<String, List<AnnotationToken>> map, TypeDescription typeDescription) {
                                return TokenizedGenericType.of(typePool, this.superClassToken, str, map, typeDescription);
                            }

                            public TypeList.Generic resolveInterfaceTypes(List<String> list, TypePool typePool, Map<Integer, Map<String, List<AnnotationToken>>> map, TypeDescription typeDescription) {
                                return new TokenizedGenericType.TokenList(typePool, this.interfaceTypeTokens, map, list, typeDescription);
                            }

                            public TypeList.Generic resolveTypeVariables(TypePool typePool, TypeVariableSource typeVariableSource, Map<Integer, Map<String, List<AnnotationToken>>> map, Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> map2) {
                                return new TokenizedGenericType.TypeVariableList(typePool, this.typeVariableTokens, typeVariableSource, map, map2);
                            }
                        }
                    }

                    public interface ForMethod extends Resolution {
                        TypeList.Generic resolveExceptionTypes(List<String> list, TypePool typePool, Map<Integer, Map<String, List<AnnotationToken>>> map, MethodDescription.InDefinedShape inDefinedShape);

                        TypeList.Generic resolveParameterTypes(List<String> list, TypePool typePool, Map<Integer, Map<String, List<AnnotationToken>>> map, MethodDescription.InDefinedShape inDefinedShape);

                        TypeDescription.Generic resolveReturnType(String str, TypePool typePool, Map<String, List<AnnotationToken>> map, MethodDescription.InDefinedShape inDefinedShape);

                        @HashCodeAndEqualsPlugin.Enhance
                        public static class Tokenized implements ForMethod {
                            private final List<GenericTypeToken> exceptionTypeTokens;
                            private final List<GenericTypeToken> parameterTypeTokens;
                            private final GenericTypeToken returnTypeToken;
                            private final List<OfFormalTypeVariable> typeVariableTokens;

                            public boolean equals(Object obj) {
                                if (this == obj) {
                                    return true;
                                }
                                if (obj == null || getClass() != obj.getClass()) {
                                    return false;
                                }
                                Tokenized tokenized = (Tokenized) obj;
                                return this.returnTypeToken.equals(tokenized.returnTypeToken) && this.parameterTypeTokens.equals(tokenized.parameterTypeTokens) && this.exceptionTypeTokens.equals(tokenized.exceptionTypeTokens) && this.typeVariableTokens.equals(tokenized.typeVariableTokens);
                            }

                            public int hashCode() {
                                return ((((((527 + this.returnTypeToken.hashCode()) * 31) + this.parameterTypeTokens.hashCode()) * 31) + this.exceptionTypeTokens.hashCode()) * 31) + this.typeVariableTokens.hashCode();
                            }

                            protected Tokenized(GenericTypeToken genericTypeToken, List<GenericTypeToken> list, List<GenericTypeToken> list2, List<OfFormalTypeVariable> list3) {
                                this.returnTypeToken = genericTypeToken;
                                this.parameterTypeTokens = list;
                                this.exceptionTypeTokens = list2;
                                this.typeVariableTokens = list3;
                            }

                            public TypeDescription.Generic resolveReturnType(String str, TypePool typePool, Map<String, List<AnnotationToken>> map, MethodDescription.InDefinedShape inDefinedShape) {
                                return TokenizedGenericType.of(typePool, this.returnTypeToken, str, map, inDefinedShape);
                            }

                            public TypeList.Generic resolveParameterTypes(List<String> list, TypePool typePool, Map<Integer, Map<String, List<AnnotationToken>>> map, MethodDescription.InDefinedShape inDefinedShape) {
                                return new TokenizedGenericType.TokenList(typePool, this.parameterTypeTokens, map, list, inDefinedShape);
                            }

                            public TypeList.Generic resolveExceptionTypes(List<String> list, TypePool typePool, Map<Integer, Map<String, List<AnnotationToken>>> map, MethodDescription.InDefinedShape inDefinedShape) {
                                if (this.exceptionTypeTokens.isEmpty()) {
                                    return Raw.INSTANCE.resolveExceptionTypes(list, typePool, map, inDefinedShape);
                                }
                                return new TokenizedGenericType.TokenList(typePool, this.exceptionTypeTokens, map, list, inDefinedShape);
                            }

                            public TypeList.Generic resolveTypeVariables(TypePool typePool, TypeVariableSource typeVariableSource, Map<Integer, Map<String, List<AnnotationToken>>> map, Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> map2) {
                                return new TokenizedGenericType.TypeVariableList(typePool, this.typeVariableTokens, typeVariableSource, map, map2);
                            }
                        }
                    }

                    public interface ForField {
                        TypeDescription.Generic resolveFieldType(String str, TypePool typePool, Map<String, List<AnnotationToken>> map, FieldDescription.InDefinedShape inDefinedShape);

                        @HashCodeAndEqualsPlugin.Enhance
                        public static class Tokenized implements ForField {
                            private final GenericTypeToken fieldTypeToken;

                            public boolean equals(Object obj) {
                                if (this == obj) {
                                    return true;
                                }
                                return obj != null && getClass() == obj.getClass() && this.fieldTypeToken.equals(((Tokenized) obj).fieldTypeToken);
                            }

                            public int hashCode() {
                                return 527 + this.fieldTypeToken.hashCode();
                            }

                            protected Tokenized(GenericTypeToken genericTypeToken) {
                                this.fieldTypeToken = genericTypeToken;
                            }

                            public TypeDescription.Generic resolveFieldType(String str, TypePool typePool, Map<String, List<AnnotationToken>> map, FieldDescription.InDefinedShape inDefinedShape) {
                                return TokenizedGenericType.of(typePool, this.fieldTypeToken, str, map, inDefinedShape.getDeclaringType());
                            }
                        }
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class ForRawType implements GenericTypeToken {
                    private final String name;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.name.equals(((ForRawType) obj).name);
                    }

                    public int hashCode() {
                        return 527 + this.name.hashCode();
                    }

                    protected ForRawType(String str) {
                        this.name = str;
                    }

                    public TypeDescription.Generic toGenericType(TypePool typePool, TypeVariableSource typeVariableSource, String str, Map<String, List<AnnotationToken>> map) {
                        if (map == null) {
                            map = Collections.emptyMap();
                        }
                        return new Resolution.Raw.RawAnnotatedType(typePool, str, map, typePool.describe(this.name).resolve());
                    }

                    public boolean isPrimaryBound(TypePool typePool) {
                        return !typePool.describe(this.name).resolve().isInterface();
                    }

                    public String getTypePathPrefix() {
                        throw new IllegalStateException("A non-generic type cannot be the owner of a nested type: " + this);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class ForTypeVariable implements GenericTypeToken {
                    private final String symbol;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.symbol.equals(((ForTypeVariable) obj).symbol);
                    }

                    public int hashCode() {
                        return 527 + this.symbol.hashCode();
                    }

                    public boolean isPrimaryBound(TypePool typePool) {
                        return true;
                    }

                    protected ForTypeVariable(String str) {
                        this.symbol = str;
                    }

                    public TypeDescription.Generic toGenericType(TypePool typePool, TypeVariableSource typeVariableSource, String str, Map<String, List<AnnotationToken>> map) {
                        TypeDescription.Generic findVariable = typeVariableSource.findVariable(this.symbol);
                        if (findVariable == null) {
                            return new UnresolvedTypeVariable(typeVariableSource, typePool, this.symbol, map.get(str));
                        }
                        return new AnnotatedTypeVariable(typePool, map.get(str), findVariable);
                    }

                    public String getTypePathPrefix() {
                        throw new IllegalStateException("A type variable cannot be the owner of a nested type: " + this);
                    }

                    protected static class AnnotatedTypeVariable extends TypeDescription.Generic.OfTypeVariable {
                        private final List<AnnotationToken> annotationTokens;
                        private final TypePool typePool;
                        private final TypeDescription.Generic typeVariable;

                        protected AnnotatedTypeVariable(TypePool typePool2, List<AnnotationToken> list, TypeDescription.Generic generic) {
                            this.typePool = typePool2;
                            this.annotationTokens = list;
                            this.typeVariable = generic;
                        }

                        public TypeList.Generic getUpperBounds() {
                            return this.typeVariable.getUpperBounds();
                        }

                        public TypeVariableSource getTypeVariableSource() {
                            return this.typeVariable.getTypeVariableSource();
                        }

                        public String getSymbol() {
                            return this.typeVariable.getSymbol();
                        }

                        public AnnotationList getDeclaredAnnotations() {
                            return LazyAnnotationDescription.asListOfNullable(this.typePool, this.annotationTokens);
                        }
                    }

                    protected static class UnresolvedTypeVariable extends TypeDescription.Generic.OfTypeVariable {
                        private final List<AnnotationToken> annotationTokens;
                        private final String symbol;
                        private final TypePool typePool;
                        private final TypeVariableSource typeVariableSource;

                        protected UnresolvedTypeVariable(TypeVariableSource typeVariableSource2, TypePool typePool2, String str, List<AnnotationToken> list) {
                            this.typeVariableSource = typeVariableSource2;
                            this.typePool = typePool2;
                            this.symbol = str;
                            this.annotationTokens = list;
                        }

                        public TypeList.Generic getUpperBounds() {
                            throw new IllegalStateException("Cannot resolve bounds of unresolved type variable " + this + " by " + this.typeVariableSource);
                        }

                        public TypeVariableSource getTypeVariableSource() {
                            return this.typeVariableSource;
                        }

                        public String getSymbol() {
                            return this.symbol;
                        }

                        public AnnotationList getDeclaredAnnotations() {
                            return LazyAnnotationDescription.asListOfNullable(this.typePool, this.annotationTokens);
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance
                    protected static class Formal implements OfFormalTypeVariable {
                        private final List<GenericTypeToken> boundTypeTokens;
                        private final String symbol;

                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            if (obj == null || getClass() != obj.getClass()) {
                                return false;
                            }
                            Formal formal = (Formal) obj;
                            return this.symbol.equals(formal.symbol) && this.boundTypeTokens.equals(formal.boundTypeTokens);
                        }

                        public int hashCode() {
                            return ((527 + this.symbol.hashCode()) * 31) + this.boundTypeTokens.hashCode();
                        }

                        protected Formal(String str, List<GenericTypeToken> list) {
                            this.symbol = str;
                            this.boundTypeTokens = list;
                        }

                        public TypeDescription.Generic toGenericType(TypePool typePool, TypeVariableSource typeVariableSource, Map<String, List<AnnotationToken>> map, Map<Integer, Map<String, List<AnnotationToken>>> map2) {
                            if (map == null) {
                                map = Collections.emptyMap();
                            }
                            Map<String, List<AnnotationToken>> map3 = map;
                            if (map2 == null) {
                                map2 = Collections.emptyMap();
                            }
                            return new LazyTypeVariable(typePool, typeVariableSource, map3, map2, this.symbol, this.boundTypeTokens);
                        }

                        protected static class LazyTypeVariable extends TypeDescription.Generic.OfTypeVariable {
                            private final Map<String, List<AnnotationToken>> annotationTokens;
                            private final List<GenericTypeToken> boundTypeTokens;
                            private final Map<Integer, Map<String, List<AnnotationToken>>> boundaryAnnotationTokens;
                            private final String symbol;
                            private final TypePool typePool;
                            private final TypeVariableSource typeVariableSource;

                            protected LazyTypeVariable(TypePool typePool2, TypeVariableSource typeVariableSource2, Map<String, List<AnnotationToken>> map, Map<Integer, Map<String, List<AnnotationToken>>> map2, String str, List<GenericTypeToken> list) {
                                this.typePool = typePool2;
                                this.typeVariableSource = typeVariableSource2;
                                this.annotationTokens = map;
                                this.boundaryAnnotationTokens = map2;
                                this.symbol = str;
                                this.boundTypeTokens = list;
                            }

                            public TypeList.Generic getUpperBounds() {
                                return new LazyBoundTokenList(this.typePool, this.typeVariableSource, this.boundaryAnnotationTokens, this.boundTypeTokens);
                            }

                            public TypeVariableSource getTypeVariableSource() {
                                return this.typeVariableSource;
                            }

                            public String getSymbol() {
                                return this.symbol;
                            }

                            public AnnotationList getDeclaredAnnotations() {
                                return LazyAnnotationDescription.asListOfNullable(this.typePool, this.annotationTokens.get(""));
                            }

                            protected static class LazyBoundTokenList extends TypeList.Generic.AbstractBase {
                                private final Map<Integer, Map<String, List<AnnotationToken>>> annotationTokens;
                                private final List<GenericTypeToken> boundTypeTokens;
                                private final TypePool typePool;
                                private final TypeVariableSource typeVariableSource;

                                protected LazyBoundTokenList(TypePool typePool2, TypeVariableSource typeVariableSource2, Map<Integer, Map<String, List<AnnotationToken>>> map, List<GenericTypeToken> list) {
                                    this.typePool = typePool2;
                                    this.typeVariableSource = typeVariableSource2;
                                    this.annotationTokens = map;
                                    this.boundTypeTokens = list;
                                }

                                public TypeDescription.Generic get(int i) {
                                    Map map;
                                    if (this.annotationTokens.containsKey(Integer.valueOf(i)) || this.annotationTokens.containsKey(Integer.valueOf(i + 1))) {
                                        map = this.annotationTokens.get(Integer.valueOf((this.boundTypeTokens.get(0).isPrimaryBound(this.typePool) ^ true ? 1 : 0) + i));
                                    } else {
                                        map = Collections.emptyMap();
                                    }
                                    GenericTypeToken genericTypeToken = this.boundTypeTokens.get(i);
                                    TypePool typePool2 = this.typePool;
                                    TypeVariableSource typeVariableSource2 = this.typeVariableSource;
                                    if (map == null) {
                                        map = Collections.emptyMap();
                                    }
                                    return genericTypeToken.toGenericType(typePool2, typeVariableSource2, "", map);
                                }

                                public int size() {
                                    return this.boundTypeTokens.size();
                                }
                            }
                        }
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class ForGenericArray implements GenericTypeToken {
                    private final GenericTypeToken componentTypeToken;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.componentTypeToken.equals(((ForGenericArray) obj).componentTypeToken);
                    }

                    public int hashCode() {
                        return 527 + this.componentTypeToken.hashCode();
                    }

                    protected ForGenericArray(GenericTypeToken genericTypeToken) {
                        this.componentTypeToken = genericTypeToken;
                    }

                    public TypeDescription.Generic toGenericType(TypePool typePool, TypeVariableSource typeVariableSource, String str, Map<String, List<AnnotationToken>> map) {
                        return new LazyGenericArray(typePool, typeVariableSource, str, map, this.componentTypeToken);
                    }

                    public boolean isPrimaryBound(TypePool typePool) {
                        throw new IllegalStateException("A generic array type cannot be a type variable bound: " + this);
                    }

                    public String getTypePathPrefix() {
                        throw new IllegalStateException("A generic array type cannot be the owner of a nested type: " + this);
                    }

                    protected static class LazyGenericArray extends TypeDescription.Generic.OfGenericArray {
                        private final Map<String, List<AnnotationToken>> annotationTokens;
                        private final GenericTypeToken componentTypeToken;
                        private final String typePath;
                        private final TypePool typePool;
                        private final TypeVariableSource typeVariableSource;

                        protected LazyGenericArray(TypePool typePool2, TypeVariableSource typeVariableSource2, String str, Map<String, List<AnnotationToken>> map, GenericTypeToken genericTypeToken) {
                            this.typePool = typePool2;
                            this.typeVariableSource = typeVariableSource2;
                            this.typePath = str;
                            this.annotationTokens = map;
                            this.componentTypeToken = genericTypeToken;
                        }

                        public TypeDescription.Generic getComponentType() {
                            GenericTypeToken genericTypeToken = this.componentTypeToken;
                            TypePool typePool2 = this.typePool;
                            TypeVariableSource typeVariableSource2 = this.typeVariableSource;
                            return genericTypeToken.toGenericType(typePool2, typeVariableSource2, this.typePath + GenericTypeToken.COMPONENT_TYPE_PATH, this.annotationTokens);
                        }

                        public AnnotationList getDeclaredAnnotations() {
                            return LazyAnnotationDescription.asListOfNullable(this.typePool, this.annotationTokens.get(this.typePath));
                        }
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class ForLowerBoundWildcard implements GenericTypeToken {
                    private final GenericTypeToken boundTypeToken;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.boundTypeToken.equals(((ForLowerBoundWildcard) obj).boundTypeToken);
                    }

                    public int hashCode() {
                        return 527 + this.boundTypeToken.hashCode();
                    }

                    protected ForLowerBoundWildcard(GenericTypeToken genericTypeToken) {
                        this.boundTypeToken = genericTypeToken;
                    }

                    public TypeDescription.Generic toGenericType(TypePool typePool, TypeVariableSource typeVariableSource, String str, Map<String, List<AnnotationToken>> map) {
                        return new LazyLowerBoundWildcard(typePool, typeVariableSource, str, map, this.boundTypeToken);
                    }

                    public boolean isPrimaryBound(TypePool typePool) {
                        throw new IllegalStateException("A wildcard type cannot be a type variable bound: " + this);
                    }

                    public String getTypePathPrefix() {
                        throw new IllegalStateException("A lower bound wildcard cannot be the owner of a nested type: " + this);
                    }

                    protected static class LazyLowerBoundWildcard extends TypeDescription.Generic.OfWildcardType {
                        private final Map<String, List<AnnotationToken>> annotationTokens;
                        private final GenericTypeToken boundTypeToken;
                        private final String typePath;
                        private final TypePool typePool;
                        private final TypeVariableSource typeVariableSource;

                        protected LazyLowerBoundWildcard(TypePool typePool2, TypeVariableSource typeVariableSource2, String str, Map<String, List<AnnotationToken>> map, GenericTypeToken genericTypeToken) {
                            this.typePool = typePool2;
                            this.typeVariableSource = typeVariableSource2;
                            this.typePath = str;
                            this.annotationTokens = map;
                            this.boundTypeToken = genericTypeToken;
                        }

                        public TypeList.Generic getUpperBounds() {
                            return new TypeList.Generic.Explicit(TypeDescription.Generic.OBJECT);
                        }

                        public TypeList.Generic getLowerBounds() {
                            return new LazyTokenList.ForWildcardBound(this.typePool, this.typeVariableSource, this.typePath, this.annotationTokens, this.boundTypeToken);
                        }

                        public AnnotationList getDeclaredAnnotations() {
                            return LazyAnnotationDescription.asListOfNullable(this.typePool, this.annotationTokens.get(this.typePath));
                        }
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class ForUpperBoundWildcard implements GenericTypeToken {
                    private final GenericTypeToken boundTypeToken;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.boundTypeToken.equals(((ForUpperBoundWildcard) obj).boundTypeToken);
                    }

                    public int hashCode() {
                        return 527 + this.boundTypeToken.hashCode();
                    }

                    protected ForUpperBoundWildcard(GenericTypeToken genericTypeToken) {
                        this.boundTypeToken = genericTypeToken;
                    }

                    public TypeDescription.Generic toGenericType(TypePool typePool, TypeVariableSource typeVariableSource, String str, Map<String, List<AnnotationToken>> map) {
                        return new LazyUpperBoundWildcard(typePool, typeVariableSource, str, map, this.boundTypeToken);
                    }

                    public boolean isPrimaryBound(TypePool typePool) {
                        throw new IllegalStateException("A wildcard type cannot be a type variable bound: " + this);
                    }

                    public String getTypePathPrefix() {
                        throw new IllegalStateException("An upper bound wildcard cannot be the owner of a nested type: " + this);
                    }

                    protected static class LazyUpperBoundWildcard extends TypeDescription.Generic.OfWildcardType {
                        private final Map<String, List<AnnotationToken>> annotationTokens;
                        private final GenericTypeToken boundTypeToken;
                        private final String typePath;
                        private final TypePool typePool;
                        private final TypeVariableSource typeVariableSource;

                        protected LazyUpperBoundWildcard(TypePool typePool2, TypeVariableSource typeVariableSource2, String str, Map<String, List<AnnotationToken>> map, GenericTypeToken genericTypeToken) {
                            this.typePool = typePool2;
                            this.typeVariableSource = typeVariableSource2;
                            this.typePath = str;
                            this.annotationTokens = map;
                            this.boundTypeToken = genericTypeToken;
                        }

                        public TypeList.Generic getUpperBounds() {
                            return new LazyTokenList.ForWildcardBound(this.typePool, this.typeVariableSource, this.typePath, this.annotationTokens, this.boundTypeToken);
                        }

                        public TypeList.Generic getLowerBounds() {
                            return new TypeList.Generic.Empty();
                        }

                        public AnnotationList getDeclaredAnnotations() {
                            return LazyAnnotationDescription.asListOfNullable(this.typePool, this.annotationTokens.get(this.typePath));
                        }
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class ForParameterizedType implements GenericTypeToken {
                    private final String name;
                    private final List<GenericTypeToken> parameterTypeTokens;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        ForParameterizedType forParameterizedType = (ForParameterizedType) obj;
                        return this.name.equals(forParameterizedType.name) && this.parameterTypeTokens.equals(forParameterizedType.parameterTypeTokens);
                    }

                    public int hashCode() {
                        return ((527 + this.name.hashCode()) * 31) + this.parameterTypeTokens.hashCode();
                    }

                    protected ForParameterizedType(String str, List<GenericTypeToken> list) {
                        this.name = str;
                        this.parameterTypeTokens = list;
                    }

                    public TypeDescription.Generic toGenericType(TypePool typePool, TypeVariableSource typeVariableSource, String str, Map<String, List<AnnotationToken>> map) {
                        return new LazyParameterizedType(typePool, typeVariableSource, str, map, this.name, this.parameterTypeTokens);
                    }

                    public boolean isPrimaryBound(TypePool typePool) {
                        return !typePool.describe(this.name).resolve().isInterface();
                    }

                    public String getTypePathPrefix() {
                        return String.valueOf('.');
                    }

                    @HashCodeAndEqualsPlugin.Enhance
                    public static class Nested implements GenericTypeToken {
                        private final String name;
                        private final GenericTypeToken ownerTypeToken;
                        private final List<GenericTypeToken> parameterTypeTokens;

                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            if (obj == null || getClass() != obj.getClass()) {
                                return false;
                            }
                            Nested nested = (Nested) obj;
                            return this.name.equals(nested.name) && this.parameterTypeTokens.equals(nested.parameterTypeTokens) && this.ownerTypeToken.equals(nested.ownerTypeToken);
                        }

                        public int hashCode() {
                            return ((((527 + this.name.hashCode()) * 31) + this.parameterTypeTokens.hashCode()) * 31) + this.ownerTypeToken.hashCode();
                        }

                        protected Nested(String str, List<GenericTypeToken> list, GenericTypeToken genericTypeToken) {
                            this.name = str;
                            this.parameterTypeTokens = list;
                            this.ownerTypeToken = genericTypeToken;
                        }

                        public TypeDescription.Generic toGenericType(TypePool typePool, TypeVariableSource typeVariableSource, String str, Map<String, List<AnnotationToken>> map) {
                            return new LazyParameterizedType(typePool, typeVariableSource, str, map, this.name, this.parameterTypeTokens, this.ownerTypeToken);
                        }

                        public String getTypePathPrefix() {
                            return this.ownerTypeToken.getTypePathPrefix() + '.';
                        }

                        public boolean isPrimaryBound(TypePool typePool) {
                            return !typePool.describe(this.name).resolve().isInterface();
                        }

                        protected static class LazyParameterizedType extends TypeDescription.Generic.OfParameterizedType {
                            private final Map<String, List<AnnotationToken>> annotationTokens;
                            private final String name;
                            private final GenericTypeToken ownerTypeToken;
                            private final List<GenericTypeToken> parameterTypeTokens;
                            private final String typePath;
                            private final TypePool typePool;
                            private final TypeVariableSource typeVariableSource;

                            protected LazyParameterizedType(TypePool typePool2, TypeVariableSource typeVariableSource2, String str, Map<String, List<AnnotationToken>> map, String str2, List<GenericTypeToken> list, GenericTypeToken genericTypeToken) {
                                this.typePool = typePool2;
                                this.typeVariableSource = typeVariableSource2;
                                this.typePath = str;
                                this.annotationTokens = map;
                                this.name = str2;
                                this.parameterTypeTokens = list;
                                this.ownerTypeToken = genericTypeToken;
                            }

                            public TypeDescription asErasure() {
                                return this.typePool.describe(this.name).resolve();
                            }

                            public TypeList.Generic getTypeArguments() {
                                TypePool typePool2 = this.typePool;
                                TypeVariableSource typeVariableSource2 = this.typeVariableSource;
                                return new LazyTokenList(typePool2, typeVariableSource2, this.typePath + this.ownerTypeToken.getTypePathPrefix(), this.annotationTokens, this.parameterTypeTokens);
                            }

                            public TypeDescription.Generic getOwnerType() {
                                return this.ownerTypeToken.toGenericType(this.typePool, this.typeVariableSource, this.typePath, this.annotationTokens);
                            }

                            public AnnotationList getDeclaredAnnotations() {
                                TypePool typePool2 = this.typePool;
                                Map<String, List<AnnotationToken>> map = this.annotationTokens;
                                return LazyAnnotationDescription.asListOfNullable(typePool2, map.get(this.typePath + this.ownerTypeToken.getTypePathPrefix()));
                            }
                        }
                    }

                    protected static class LazyParameterizedType extends TypeDescription.Generic.OfParameterizedType {
                        private final Map<String, List<AnnotationToken>> annotationTokens;
                        private final String name;
                        private final List<GenericTypeToken> parameterTypeTokens;
                        private final String typePath;
                        private final TypePool typePool;
                        private final TypeVariableSource typeVariableSource;

                        protected LazyParameterizedType(TypePool typePool2, TypeVariableSource typeVariableSource2, String str, Map<String, List<AnnotationToken>> map, String str2, List<GenericTypeToken> list) {
                            this.typePool = typePool2;
                            this.typeVariableSource = typeVariableSource2;
                            this.typePath = str;
                            this.annotationTokens = map;
                            this.name = str2;
                            this.parameterTypeTokens = list;
                        }

                        public TypeDescription asErasure() {
                            return this.typePool.describe(this.name).resolve();
                        }

                        public TypeList.Generic getTypeArguments() {
                            return new LazyTokenList(this.typePool, this.typeVariableSource, this.typePath, this.annotationTokens, this.parameterTypeTokens);
                        }

                        public TypeDescription.Generic getOwnerType() {
                            TypeDescription enclosingType = this.typePool.describe(this.name).resolve().getEnclosingType();
                            if (enclosingType == null) {
                                return TypeDescription.Generic.UNDEFINED;
                            }
                            return enclosingType.asGenericType();
                        }

                        public AnnotationList getDeclaredAnnotations() {
                            return LazyAnnotationDescription.asListOfNullable(this.typePool, this.annotationTokens.get(this.typePath));
                        }
                    }
                }

                public static class LazyTokenList extends TypeList.Generic.AbstractBase {
                    private final Map<String, List<AnnotationToken>> annotationTokens;
                    private final List<GenericTypeToken> genericTypeTokens;
                    private final String typePath;
                    private final TypePool typePool;
                    private final TypeVariableSource typeVariableSource;

                    protected LazyTokenList(TypePool typePool2, TypeVariableSource typeVariableSource2, String str, Map<String, List<AnnotationToken>> map, List<GenericTypeToken> list) {
                        this.typePool = typePool2;
                        this.typeVariableSource = typeVariableSource2;
                        this.typePath = str;
                        this.annotationTokens = map;
                        this.genericTypeTokens = list;
                    }

                    public TypeDescription.Generic get(int i) {
                        TypePool typePool2 = this.typePool;
                        TypeVariableSource typeVariableSource2 = this.typeVariableSource;
                        return this.genericTypeTokens.get(i).toGenericType(typePool2, typeVariableSource2, this.typePath + i + ';', this.annotationTokens);
                    }

                    public int size() {
                        return this.genericTypeTokens.size();
                    }

                    protected static class ForWildcardBound extends TypeList.Generic.AbstractBase {
                        private final Map<String, List<AnnotationToken>> annotationTokens;
                        private final GenericTypeToken genericTypeToken;
                        private final String typePath;
                        private final TypePool typePool;
                        private final TypeVariableSource typeVariableSource;

                        public int size() {
                            return 1;
                        }

                        protected ForWildcardBound(TypePool typePool2, TypeVariableSource typeVariableSource2, String str, Map<String, List<AnnotationToken>> map, GenericTypeToken genericTypeToken2) {
                            this.typePool = typePool2;
                            this.typeVariableSource = typeVariableSource2;
                            this.typePath = str;
                            this.annotationTokens = map;
                            this.genericTypeToken = genericTypeToken2;
                        }

                        public TypeDescription.Generic get(int i) {
                            if (i == 0) {
                                GenericTypeToken genericTypeToken2 = this.genericTypeToken;
                                TypePool typePool2 = this.typePool;
                                TypeVariableSource typeVariableSource2 = this.typeVariableSource;
                                return genericTypeToken2.toGenericType(typePool2, typeVariableSource2, this.typePath + '*', this.annotationTokens);
                            }
                            throw new IndexOutOfBoundsException("index = " + i);
                        }
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class AnnotationToken {
                private final String descriptor;
                private final Map<String, AnnotationValue<?, ?>> values;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    AnnotationToken annotationToken = (AnnotationToken) obj;
                    return this.descriptor.equals(annotationToken.descriptor) && this.values.equals(annotationToken.values);
                }

                public int hashCode() {
                    return ((527 + this.descriptor.hashCode()) * 31) + this.values.hashCode();
                }

                protected AnnotationToken(String str, Map<String, AnnotationValue<?, ?>> map) {
                    this.descriptor = str;
                    this.values = map;
                }

                /* access modifiers changed from: protected */
                public Map<String, AnnotationValue<?, ?>> getValues() {
                    return this.values;
                }

                /* access modifiers changed from: protected */
                public String getBinaryName() {
                    String str = this.descriptor;
                    return str.substring(1, str.length() - 1).replace('/', '.');
                }

                /* access modifiers changed from: private */
                public Resolution toAnnotationDescription(TypePool typePool) {
                    Resolution describe = typePool.describe(getBinaryName());
                    if (describe.isResolved()) {
                        return new Resolution.Simple(new LazyAnnotationDescription(typePool, describe.resolve(), this.values));
                    }
                    return new Resolution.Illegal(getBinaryName());
                }

                protected interface Resolution {
                    boolean isResolved();

                    AnnotationDescription resolve();

                    @HashCodeAndEqualsPlugin.Enhance
                    public static class Simple implements Resolution {
                        private final AnnotationDescription annotationDescription;

                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && this.annotationDescription.equals(((Simple) obj).annotationDescription);
                        }

                        public int hashCode() {
                            return 527 + this.annotationDescription.hashCode();
                        }

                        public boolean isResolved() {
                            return true;
                        }

                        protected Simple(AnnotationDescription annotationDescription2) {
                            this.annotationDescription = annotationDescription2;
                        }

                        public AnnotationDescription resolve() {
                            return this.annotationDescription;
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance
                    public static class Illegal implements Resolution {
                        private final String annotationType;

                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && this.annotationType.equals(((Illegal) obj).annotationType);
                        }

                        public int hashCode() {
                            return 527 + this.annotationType.hashCode();
                        }

                        public boolean isResolved() {
                            return false;
                        }

                        public Illegal(String str) {
                            this.annotationType = str;
                        }

                        public AnnotationDescription resolve() {
                            throw new IllegalStateException("Annotation type is not available: " + this.annotationType);
                        }
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class FieldToken {
                private final List<AnnotationToken> annotationTokens;
                private final String descriptor;
                private final String genericSignature;
                private final int modifiers;
                private final String name;
                private final GenericTypeToken.Resolution.ForField signatureResolution;
                private final Map<String, List<AnnotationToken>> typeAnnotationTokens;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    FieldToken fieldToken = (FieldToken) obj;
                    return this.modifiers == fieldToken.modifiers && this.name.equals(fieldToken.name) && this.descriptor.equals(fieldToken.descriptor) && this.genericSignature.equals(fieldToken.genericSignature) && this.signatureResolution.equals(fieldToken.signatureResolution) && this.typeAnnotationTokens.equals(fieldToken.typeAnnotationTokens) && this.annotationTokens.equals(fieldToken.annotationTokens);
                }

                public int hashCode() {
                    return ((((((((((((527 + this.name.hashCode()) * 31) + this.modifiers) * 31) + this.descriptor.hashCode()) * 31) + this.genericSignature.hashCode()) * 31) + this.signatureResolution.hashCode()) * 31) + this.typeAnnotationTokens.hashCode()) * 31) + this.annotationTokens.hashCode();
                }

                protected FieldToken(String str, int i, String str2, String str3, Map<String, List<AnnotationToken>> map, List<AnnotationToken> list) {
                    GenericTypeToken.Resolution.ForField forField;
                    this.modifiers = i & -131073;
                    this.name = str;
                    this.descriptor = str2;
                    this.genericSignature = str3;
                    if (TypeDescription.AbstractBase.RAW_TYPES) {
                        forField = GenericTypeToken.Resolution.Raw.INSTANCE;
                    } else {
                        forField = GenericTypeExtractor.ForSignature.OfField.extract(str3);
                    }
                    this.signatureResolution = forField;
                    this.typeAnnotationTokens = map;
                    this.annotationTokens = list;
                }

                /* access modifiers changed from: private */
                public LazyFieldDescription toFieldDescription(LazyTypeDescription lazyTypeDescription) {
                    lazyTypeDescription.getClass();
                    return new LazyFieldDescription(this.name, this.modifiers, this.descriptor, this.genericSignature, this.signatureResolution, this.typeAnnotationTokens, this.annotationTokens);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class MethodToken {
                private final List<AnnotationToken> annotationTokens;
                private final AnnotationValue<?, ?> defaultValue;
                private final String descriptor;
                private final String[] exceptionName;
                private final Map<Integer, Map<String, List<AnnotationToken>>> exceptionTypeAnnotationTokens;
                private final String genericSignature;
                private final int modifiers;
                private final String name;
                private final Map<Integer, List<AnnotationToken>> parameterAnnotationTokens;
                private final List<ParameterToken> parameterTokens;
                private final Map<Integer, Map<String, List<AnnotationToken>>> parameterTypeAnnotationTokens;
                private final Map<String, List<AnnotationToken>> receiverTypeAnnotationTokens;
                private final Map<String, List<AnnotationToken>> returnTypeAnnotationTokens;
                private final GenericTypeToken.Resolution.ForMethod signatureResolution;
                private final Map<Integer, Map<String, List<AnnotationToken>>> typeVariableAnnotationTokens;
                private final Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> typeVariableBoundAnnotationTokens;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    MethodToken methodToken = (MethodToken) obj;
                    return this.modifiers == methodToken.modifiers && this.name.equals(methodToken.name) && this.descriptor.equals(methodToken.descriptor) && this.genericSignature.equals(methodToken.genericSignature) && this.signatureResolution.equals(methodToken.signatureResolution) && Arrays.equals(this.exceptionName, methodToken.exceptionName) && this.typeVariableAnnotationTokens.equals(methodToken.typeVariableAnnotationTokens) && this.typeVariableBoundAnnotationTokens.equals(methodToken.typeVariableBoundAnnotationTokens) && this.returnTypeAnnotationTokens.equals(methodToken.returnTypeAnnotationTokens) && this.parameterTypeAnnotationTokens.equals(methodToken.parameterTypeAnnotationTokens) && this.exceptionTypeAnnotationTokens.equals(methodToken.exceptionTypeAnnotationTokens) && this.receiverTypeAnnotationTokens.equals(methodToken.receiverTypeAnnotationTokens) && this.annotationTokens.equals(methodToken.annotationTokens) && this.parameterAnnotationTokens.equals(methodToken.parameterAnnotationTokens) && this.parameterTokens.equals(methodToken.parameterTokens) && this.defaultValue.equals(methodToken.defaultValue);
                }

                public int hashCode() {
                    return ((((((((((((((((((((((((((((((527 + this.name.hashCode()) * 31) + this.modifiers) * 31) + this.descriptor.hashCode()) * 31) + this.genericSignature.hashCode()) * 31) + this.signatureResolution.hashCode()) * 31) + Arrays.hashCode(this.exceptionName)) * 31) + this.typeVariableAnnotationTokens.hashCode()) * 31) + this.typeVariableBoundAnnotationTokens.hashCode()) * 31) + this.returnTypeAnnotationTokens.hashCode()) * 31) + this.parameterTypeAnnotationTokens.hashCode()) * 31) + this.exceptionTypeAnnotationTokens.hashCode()) * 31) + this.receiverTypeAnnotationTokens.hashCode()) * 31) + this.annotationTokens.hashCode()) * 31) + this.parameterAnnotationTokens.hashCode()) * 31) + this.parameterTokens.hashCode()) * 31) + this.defaultValue.hashCode();
                }

                protected MethodToken(String str, int i, String str2, String str3, String[] strArr, Map<Integer, Map<String, List<AnnotationToken>>> map, Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> map2, Map<String, List<AnnotationToken>> map3, Map<Integer, Map<String, List<AnnotationToken>>> map4, Map<Integer, Map<String, List<AnnotationToken>>> map5, Map<String, List<AnnotationToken>> map6, List<AnnotationToken> list, Map<Integer, List<AnnotationToken>> map7, List<ParameterToken> list2, AnnotationValue<?, ?> annotationValue) {
                    GenericTypeToken.Resolution.ForMethod forMethod;
                    this.modifiers = -131073 & i;
                    this.name = str;
                    this.descriptor = str2;
                    this.genericSignature = str3;
                    if (TypeDescription.AbstractBase.RAW_TYPES) {
                        forMethod = GenericTypeToken.Resolution.Raw.INSTANCE;
                    } else {
                        forMethod = GenericTypeExtractor.ForSignature.OfMethod.extract(str3);
                    }
                    this.signatureResolution = forMethod;
                    this.exceptionName = strArr;
                    this.typeVariableAnnotationTokens = map;
                    this.typeVariableBoundAnnotationTokens = map2;
                    this.returnTypeAnnotationTokens = map3;
                    this.parameterTypeAnnotationTokens = map4;
                    this.exceptionTypeAnnotationTokens = map5;
                    this.receiverTypeAnnotationTokens = map6;
                    this.annotationTokens = list;
                    this.parameterAnnotationTokens = map7;
                    this.parameterTokens = list2;
                    this.defaultValue = annotationValue;
                }

                /* access modifiers changed from: private */
                public MethodDescription.InDefinedShape toMethodDescription(LazyTypeDescription lazyTypeDescription) {
                    lazyTypeDescription.getClass();
                    return new LazyMethodDescription(this.name, this.modifiers, this.descriptor, this.genericSignature, this.signatureResolution, this.exceptionName, this.typeVariableAnnotationTokens, this.typeVariableBoundAnnotationTokens, this.returnTypeAnnotationTokens, this.parameterTypeAnnotationTokens, this.exceptionTypeAnnotationTokens, this.receiverTypeAnnotationTokens, this.annotationTokens, this.parameterAnnotationTokens, this.parameterTokens, this.defaultValue);
                }

                @HashCodeAndEqualsPlugin.Enhance
                protected static class ParameterToken {
                    protected static final Integer NO_MODIFIERS = null;
                    protected static final String NO_NAME = null;
                    @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
                    private final Integer modifiers;
                    @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
                    private final String name;

                    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0024, code lost:
                        if (r2 != null) goto L_0x0026;
                     */
                    /* JADX WARNING: Removed duplicated region for block: B:25:0x0039 A[RETURN] */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public boolean equals(java.lang.Object r5) {
                        /*
                            r4 = this;
                            r0 = 1
                            if (r4 != r5) goto L_0x0004
                            return r0
                        L_0x0004:
                            r1 = 0
                            if (r5 != 0) goto L_0x0008
                            return r1
                        L_0x0008:
                            java.lang.Class r2 = r4.getClass()
                            java.lang.Class r3 = r5.getClass()
                            if (r2 == r3) goto L_0x0013
                            return r1
                        L_0x0013:
                            java.lang.Integer r2 = r4.modifiers
                            net.bytebuddy.pool.TypePool$Default$LazyTypeDescription$MethodToken$ParameterToken r5 = (net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.MethodToken.ParameterToken) r5
                            java.lang.Integer r3 = r5.modifiers
                            if (r3 == 0) goto L_0x0024
                            if (r2 == 0) goto L_0x0026
                            boolean r2 = r2.equals(r3)
                            if (r2 != 0) goto L_0x0027
                            return r1
                        L_0x0024:
                            if (r2 == 0) goto L_0x0027
                        L_0x0026:
                            return r1
                        L_0x0027:
                            java.lang.String r2 = r4.name
                            java.lang.String r5 = r5.name
                            if (r5 == 0) goto L_0x0036
                            if (r2 == 0) goto L_0x0038
                            boolean r5 = r2.equals(r5)
                            if (r5 != 0) goto L_0x0039
                            return r1
                        L_0x0036:
                            if (r2 == 0) goto L_0x0039
                        L_0x0038:
                            return r1
                        L_0x0039:
                            return r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.MethodToken.ParameterToken.equals(java.lang.Object):boolean");
                    }

                    public int hashCode() {
                        String str = this.name;
                        int i = 527;
                        if (str != null) {
                            i = 527 + str.hashCode();
                        }
                        int i2 = i * 31;
                        Integer num = this.modifiers;
                        return num != null ? i2 + num.hashCode() : i2;
                    }

                    protected ParameterToken() {
                        this(NO_NAME);
                    }

                    protected ParameterToken(String str) {
                        this(str, NO_MODIFIERS);
                    }

                    protected ParameterToken(String str, Integer num) {
                        this.name = str;
                        this.modifiers = num;
                    }

                    /* access modifiers changed from: protected */
                    public String getName() {
                        return this.name;
                    }

                    /* access modifiers changed from: protected */
                    public Integer getModifiers() {
                        return this.modifiers;
                    }
                }
            }

            private static class LazyAnnotationDescription extends AnnotationDescription.AbstractBase {
                private final TypeDescription annotationType;
                protected final TypePool typePool;
                protected final Map<String, AnnotationValue<?, ?>> values;

                private LazyAnnotationDescription(TypePool typePool2, TypeDescription typeDescription, Map<String, AnnotationValue<?, ?>> map) {
                    this.typePool = typePool2;
                    this.annotationType = typeDescription;
                    this.values = map;
                }

                protected static AnnotationList asListOfNullable(TypePool typePool2, List<? extends AnnotationToken> list) {
                    if (list == null) {
                        return new AnnotationList.Empty();
                    }
                    return asList(typePool2, list);
                }

                protected static AnnotationList asList(TypePool typePool2, List<? extends AnnotationToken> list) {
                    ArrayList arrayList = new ArrayList(list.size());
                    for (AnnotationToken access$000 : list) {
                        AnnotationToken.Resolution access$0002 = access$000.toAnnotationDescription(typePool2);
                        if (access$0002.isResolved()) {
                            arrayList.add(access$0002.resolve());
                        }
                    }
                    return new AnnotationList.Explicit((List<? extends AnnotationDescription>) arrayList);
                }

                public AnnotationValue<?, ?> getValue(MethodDescription.InDefinedShape inDefinedShape) {
                    if (inDefinedShape.getDeclaringType().asErasure().equals(this.annotationType)) {
                        AnnotationValue<?, ?> annotationValue = this.values.get(inDefinedShape.getName());
                        if (annotationValue == null) {
                            annotationValue = ((MethodDescription.InDefinedShape) ((MethodList) getAnnotationType().getDeclaredMethods().filter(ElementMatchers.is(inDefinedShape))).getOnly()).getDefaultValue();
                        }
                        if (annotationValue != null) {
                            return annotationValue;
                        }
                        throw new IllegalStateException(inDefinedShape + " is not defined on annotation");
                    }
                    throw new IllegalArgumentException(inDefinedShape + " is not declared by " + getAnnotationType());
                }

                public TypeDescription getAnnotationType() {
                    return this.annotationType;
                }

                public <T extends Annotation> Loadable<T> prepare(Class<T> cls) {
                    if (this.annotationType.represents(cls)) {
                        return new Loadable<>(this.typePool, cls, this.values);
                    }
                    throw new IllegalArgumentException(cls + " does not represent " + this.annotationType);
                }

                private static class Loadable<S extends Annotation> extends LazyAnnotationDescription implements AnnotationDescription.Loadable<S> {
                    private final Class<S> annotationType;

                    public /* bridge */ /* synthetic */ AnnotationDescription.Loadable prepare(Class cls) {
                        return super.prepare(cls);
                    }

                    private Loadable(TypePool typePool, Class<S> cls, Map<String, AnnotationValue<?, ?>> map) {
                        super(typePool, TypeDescription.ForLoadedType.of(cls), map);
                        this.annotationType = cls;
                    }

                    public S load() throws ClassNotFoundException {
                        return AnnotationDescription.AnnotationInvocationHandler.of(this.annotationType.getClassLoader(), this.annotationType, this.values);
                    }

                    public S loadSilent() {
                        try {
                            return load();
                        } catch (ClassNotFoundException e) {
                            throw new IllegalStateException("Could not load annotation type or referenced type", e);
                        }
                    }
                }
            }

            private static class LazyPackageDescription extends PackageDescription.AbstractBase {
                private final String name;
                private final TypePool typePool;

                private LazyPackageDescription(TypePool typePool2, String str) {
                    this.typePool = typePool2;
                    this.name = str;
                }

                public AnnotationList getDeclaredAnnotations() {
                    TypePool typePool2 = this.typePool;
                    Resolution describe = typePool2.describe(this.name + "." + PackageDescription.PACKAGE_CLASS_NAME);
                    return describe.isResolved() ? describe.resolve().getDeclaredAnnotations() : new AnnotationList.Empty();
                }

                public String getName() {
                    return this.name;
                }
            }

            protected static class LazyTypeList extends TypeList.AbstractBase {
                private final List<String> descriptors;
                private final TypePool typePool;

                protected LazyTypeList(TypePool typePool2, List<String> list) {
                    this.typePool = typePool2;
                    this.descriptors = list;
                }

                public TypeDescription get(int i) {
                    return TokenizedGenericType.toErasure(this.typePool, this.descriptors.get(i));
                }

                public int size() {
                    return this.descriptors.size();
                }

                public String[] toInternalNames() {
                    int size = this.descriptors.size();
                    String[] strArr = new String[size];
                    int i = 0;
                    for (String type : this.descriptors) {
                        strArr[i] = Type.getType(type).getInternalName();
                        i++;
                    }
                    return size == 0 ? NO_INTERFACES : strArr;
                }

                public int getStackSize() {
                    int i = 0;
                    for (String type : this.descriptors) {
                        i += Type.getType(type).getSize();
                    }
                    return i;
                }
            }

            protected static class LazyNestMemberList extends TypeList.AbstractBase {
                private final List<String> nestMembers;
                private final TypeDescription typeDescription;
                private final TypePool typePool;

                protected LazyNestMemberList(TypeDescription typeDescription2, TypePool typePool2, List<String> list) {
                    this.typeDescription = typeDescription2;
                    this.typePool = typePool2;
                    this.nestMembers = list;
                }

                public TypeDescription get(int i) {
                    if (i == 0) {
                        return this.typeDescription;
                    }
                    return this.typePool.describe(this.nestMembers.get(i - 1)).resolve();
                }

                public int size() {
                    return this.nestMembers.size() + 1;
                }

                public String[] toInternalNames() {
                    int i = 1;
                    String[] strArr = new String[(this.nestMembers.size() + 1)];
                    strArr[0] = this.typeDescription.getInternalName();
                    for (String replace : this.nestMembers) {
                        strArr[i] = replace.replace('.', '/');
                        i++;
                    }
                    return strArr;
                }

                public int getStackSize() {
                    return this.nestMembers.size() + 1;
                }
            }

            protected static class TokenizedGenericType extends TypeDescription.Generic.LazyProjection.WithEagerNavigation {
                private final Map<String, List<AnnotationToken>> annotationTokens;
                private transient /* synthetic */ TypeDescription erasure;
                private final GenericTypeToken genericTypeToken;
                private final String rawTypeDescriptor;
                private transient /* synthetic */ TypeDescription.Generic resolved;
                private final TypePool typePool;
                private final TypeVariableSource typeVariableSource;

                protected TokenizedGenericType(TypePool typePool2, GenericTypeToken genericTypeToken2, String str, Map<String, List<AnnotationToken>> map, TypeVariableSource typeVariableSource2) {
                    this.typePool = typePool2;
                    this.genericTypeToken = genericTypeToken2;
                    this.rawTypeDescriptor = str;
                    this.annotationTokens = map;
                    this.typeVariableSource = typeVariableSource2;
                }

                protected static TypeDescription.Generic of(TypePool typePool2, GenericTypeToken genericTypeToken2, String str, Map<String, List<AnnotationToken>> map, TypeVariableSource typeVariableSource2) {
                    if (map == null) {
                        map = Collections.emptyMap();
                    }
                    return new TokenizedGenericType(typePool2, genericTypeToken2, str, map, typeVariableSource2);
                }

                protected static TypeDescription toErasure(TypePool typePool2, String str) {
                    String str2;
                    Type type = Type.getType(str);
                    if (type.getSort() == 9) {
                        str2 = type.getInternalName().replace('/', '.');
                    } else {
                        str2 = type.getClassName();
                    }
                    return typePool2.describe(str2).resolve();
                }

                /* access modifiers changed from: protected */
                @CachedReturnPlugin.Enhance("resolved")
                public TypeDescription.Generic resolve() {
                    TypeDescription.Generic genericType = this.resolved != null ? null : this.genericTypeToken.toGenericType(this.typePool, this.typeVariableSource, "", this.annotationTokens);
                    if (genericType == null) {
                        return this.resolved;
                    }
                    this.resolved = genericType;
                    return genericType;
                }

                @CachedReturnPlugin.Enhance("erasure")
                public TypeDescription asErasure() {
                    TypeDescription erasure2 = this.erasure != null ? null : toErasure(this.typePool, this.rawTypeDescriptor);
                    if (erasure2 == null) {
                        return this.erasure;
                    }
                    this.erasure = erasure2;
                    return erasure2;
                }

                public AnnotationList getDeclaredAnnotations() {
                    return resolve().getDeclaredAnnotations();
                }

                protected static class TokenList extends TypeList.Generic.AbstractBase {
                    private final Map<Integer, Map<String, List<AnnotationToken>>> annotationTokens;
                    private final List<GenericTypeToken> genericTypeTokens;
                    private final List<String> rawTypeDescriptors;
                    private final TypePool typePool;
                    private final TypeVariableSource typeVariableSource;

                    private TokenList(TypePool typePool2, List<GenericTypeToken> list, Map<Integer, Map<String, List<AnnotationToken>>> map, List<String> list2, TypeVariableSource typeVariableSource2) {
                        this.typePool = typePool2;
                        this.genericTypeTokens = list;
                        this.annotationTokens = map;
                        this.rawTypeDescriptors = list2;
                        this.typeVariableSource = typeVariableSource2;
                    }

                    public TypeDescription.Generic get(int i) {
                        if (this.rawTypeDescriptors.size() == this.genericTypeTokens.size()) {
                            return TokenizedGenericType.of(this.typePool, this.genericTypeTokens.get(i), this.rawTypeDescriptors.get(i), this.annotationTokens.get(Integer.valueOf(i)), this.typeVariableSource);
                        }
                        return TokenizedGenericType.toErasure(this.typePool, this.rawTypeDescriptors.get(i)).asGenericType();
                    }

                    public int size() {
                        return this.rawTypeDescriptors.size();
                    }

                    public TypeList asErasures() {
                        return new LazyTypeList(this.typePool, this.rawTypeDescriptors);
                    }
                }

                protected static class TypeVariableList extends TypeList.Generic.AbstractBase {
                    private final Map<Integer, Map<String, List<AnnotationToken>>> annotationTokens;
                    private final Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> boundAnnotationTokens;
                    private final TypePool typePool;
                    private final TypeVariableSource typeVariableSource;
                    private final List<GenericTypeToken.OfFormalTypeVariable> typeVariables;

                    protected TypeVariableList(TypePool typePool2, List<GenericTypeToken.OfFormalTypeVariable> list, TypeVariableSource typeVariableSource2, Map<Integer, Map<String, List<AnnotationToken>>> map, Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> map2) {
                        this.typePool = typePool2;
                        this.typeVariables = list;
                        this.typeVariableSource = typeVariableSource2;
                        this.annotationTokens = map;
                        this.boundAnnotationTokens = map2;
                    }

                    public TypeDescription.Generic get(int i) {
                        return this.typeVariables.get(i).toGenericType(this.typePool, this.typeVariableSource, this.annotationTokens.get(Integer.valueOf(i)), this.boundAnnotationTokens.get(Integer.valueOf(i)));
                    }

                    public int size() {
                        return this.typeVariables.size();
                    }
                }

                protected static class Malformed extends TypeDescription.Generic.LazyProjection.WithEagerNavigation {
                    private final String rawTypeDescriptor;
                    private final TypePool typePool;

                    protected Malformed(TypePool typePool2, String str) {
                        this.typePool = typePool2;
                        this.rawTypeDescriptor = str;
                    }

                    /* access modifiers changed from: protected */
                    public TypeDescription.Generic resolve() {
                        throw new GenericSignatureFormatError();
                    }

                    public TypeDescription asErasure() {
                        return TokenizedGenericType.toErasure(this.typePool, this.rawTypeDescriptor);
                    }

                    public AnnotationList getDeclaredAnnotations() {
                        throw new GenericSignatureFormatError();
                    }

                    protected static class TokenList extends TypeList.Generic.AbstractBase {
                        private final List<String> rawTypeDescriptors;
                        private final TypePool typePool;

                        protected TokenList(TypePool typePool2, List<String> list) {
                            this.typePool = typePool2;
                            this.rawTypeDescriptors = list;
                        }

                        public TypeDescription.Generic get(int i) {
                            return new Malformed(this.typePool, this.rawTypeDescriptors.get(i));
                        }

                        public int size() {
                            return this.rawTypeDescriptors.size();
                        }

                        public TypeList asErasures() {
                            return new LazyTypeList(this.typePool, this.rawTypeDescriptors);
                        }
                    }
                }
            }

            private class LazyFieldDescription extends FieldDescription.InDefinedShape.AbstractBase {
                private final List<AnnotationToken> annotationTokens;
                private final String descriptor;
                private final String genericSignature;
                private final int modifiers;
                private final String name;
                private final GenericTypeToken.Resolution.ForField signatureResolution;
                private final Map<String, List<AnnotationToken>> typeAnnotationTokens;

                private LazyFieldDescription(String str, int i, String str2, String str3, GenericTypeToken.Resolution.ForField forField, Map<String, List<AnnotationToken>> map, List<AnnotationToken> list) {
                    this.modifiers = i;
                    this.name = str;
                    this.descriptor = str2;
                    this.genericSignature = str3;
                    this.signatureResolution = forField;
                    this.typeAnnotationTokens = map;
                    this.annotationTokens = list;
                }

                public TypeDescription.Generic getType() {
                    return this.signatureResolution.resolveFieldType(this.descriptor, LazyTypeDescription.this.typePool, this.typeAnnotationTokens, this);
                }

                public AnnotationList getDeclaredAnnotations() {
                    return LazyAnnotationDescription.asListOfNullable(LazyTypeDescription.this.typePool, this.annotationTokens);
                }

                public String getName() {
                    return this.name;
                }

                public TypeDescription getDeclaringType() {
                    return LazyTypeDescription.this;
                }

                public int getModifiers() {
                    return this.modifiers;
                }

                public String getGenericSignature() {
                    return this.genericSignature;
                }
            }

            private class LazyMethodDescription extends MethodDescription.InDefinedShape.AbstractBase {
                private final List<AnnotationToken> annotationTokens;
                private final AnnotationValue<?, ?> defaultValue;
                private final Map<Integer, Map<String, List<AnnotationToken>>> exceptionTypeAnnotationTokens;
                private final List<String> exceptionTypeDescriptors;
                private final String genericSignature;
                private final String internalName;
                private final int modifiers;
                /* access modifiers changed from: private */
                public final Map<Integer, List<AnnotationToken>> parameterAnnotationTokens;
                /* access modifiers changed from: private */
                public final Integer[] parameterModifiers;
                /* access modifiers changed from: private */
                public final String[] parameterNames;
                /* access modifiers changed from: private */
                public final Map<Integer, Map<String, List<AnnotationToken>>> parameterTypeAnnotationTokens;
                /* access modifiers changed from: private */
                public final List<String> parameterTypeDescriptors;
                /* access modifiers changed from: private */
                public final Map<String, List<AnnotationToken>> receiverTypeAnnotationTokens;
                private final Map<String, List<AnnotationToken>> returnTypeAnnotationTokens;
                private final String returnTypeDescriptor;
                /* access modifiers changed from: private */
                public final GenericTypeToken.Resolution.ForMethod signatureResolution;
                final /* synthetic */ LazyTypeDescription this$0;
                private final Map<Integer, Map<String, List<AnnotationToken>>> typeVariableAnnotationTokens;
                private final Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> typeVariableBoundAnnotationTokens;

                private LazyMethodDescription(LazyTypeDescription lazyTypeDescription, String str, int i, String str2, String str3, GenericTypeToken.Resolution.ForMethod forMethod, String[] strArr, Map<Integer, Map<String, List<AnnotationToken>>> map, Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> map2, Map<String, List<AnnotationToken>> map3, Map<Integer, Map<String, List<AnnotationToken>>> map4, Map<Integer, Map<String, List<AnnotationToken>>> map5, Map<String, List<AnnotationToken>> map6, List<AnnotationToken> list, Map<Integer, List<AnnotationToken>> map7, List<MethodToken.ParameterToken> list2, AnnotationValue<?, ?> annotationValue) {
                    String[] strArr2 = strArr;
                    this.this$0 = lazyTypeDescription;
                    this.modifiers = i;
                    this.internalName = str;
                    Type methodType = Type.getMethodType(str2);
                    Type returnType = methodType.getReturnType();
                    Type[] argumentTypes = methodType.getArgumentTypes();
                    this.returnTypeDescriptor = returnType.getDescriptor();
                    this.parameterTypeDescriptors = new ArrayList(argumentTypes.length);
                    int i2 = 0;
                    for (Type descriptor : argumentTypes) {
                        this.parameterTypeDescriptors.add(descriptor.getDescriptor());
                    }
                    this.genericSignature = str3;
                    this.signatureResolution = forMethod;
                    if (strArr2 == null) {
                        this.exceptionTypeDescriptors = Collections.emptyList();
                    } else {
                        this.exceptionTypeDescriptors = new ArrayList(strArr2.length);
                        for (String objectType : strArr2) {
                            this.exceptionTypeDescriptors.add(Type.getObjectType(objectType).getDescriptor());
                        }
                    }
                    this.typeVariableAnnotationTokens = map;
                    this.typeVariableBoundAnnotationTokens = map2;
                    this.returnTypeAnnotationTokens = map3;
                    this.parameterTypeAnnotationTokens = map4;
                    this.exceptionTypeAnnotationTokens = map5;
                    this.receiverTypeAnnotationTokens = map6;
                    this.annotationTokens = list;
                    this.parameterAnnotationTokens = map7;
                    this.parameterNames = new String[argumentTypes.length];
                    this.parameterModifiers = new Integer[argumentTypes.length];
                    if (list2.size() == argumentTypes.length) {
                        for (MethodToken.ParameterToken next : list2) {
                            this.parameterNames[i2] = next.getName();
                            this.parameterModifiers[i2] = next.getModifiers();
                            i2++;
                        }
                    }
                    this.defaultValue = annotationValue;
                }

                public TypeDescription.Generic getReturnType() {
                    return this.signatureResolution.resolveReturnType(this.returnTypeDescriptor, this.this$0.typePool, this.returnTypeAnnotationTokens, this);
                }

                public TypeList.Generic getExceptionTypes() {
                    return this.signatureResolution.resolveExceptionTypes(this.exceptionTypeDescriptors, this.this$0.typePool, this.exceptionTypeAnnotationTokens, this);
                }

                public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
                    return new LazyParameterList();
                }

                public AnnotationList getDeclaredAnnotations() {
                    return LazyAnnotationDescription.asList(this.this$0.typePool, this.annotationTokens);
                }

                public String getInternalName() {
                    return this.internalName;
                }

                public TypeDescription getDeclaringType() {
                    return this.this$0;
                }

                public int getModifiers() {
                    return this.modifiers;
                }

                public TypeList.Generic getTypeVariables() {
                    return this.signatureResolution.resolveTypeVariables(this.this$0.typePool, this, this.typeVariableAnnotationTokens, this.typeVariableBoundAnnotationTokens);
                }

                public AnnotationValue<?, ?> getDefaultValue() {
                    return this.defaultValue;
                }

                public TypeDescription.Generic getReceiverType() {
                    if (isStatic()) {
                        return TypeDescription.Generic.UNDEFINED;
                    }
                    if (!isConstructor()) {
                        return this.this$0.isGenerified() ? new LazyParameterizedReceiverType(this) : new LazyNonGenericReceiverType(this);
                    }
                    TypeDescription declaringType = getDeclaringType();
                    TypeDescription enclosingType = declaringType.getEnclosingType();
                    return enclosingType == null ? declaringType.isGenerified() ? new LazyParameterizedReceiverType(declaringType) : new LazyNonGenericReceiverType(declaringType) : (declaringType.isStatic() || !declaringType.isGenerified()) ? new LazyNonGenericReceiverType(enclosingType) : new LazyParameterizedReceiverType(enclosingType);
                }

                public String getGenericSignature() {
                    return this.genericSignature;
                }

                private class LazyParameterList extends ParameterList.AbstractBase<ParameterDescription.InDefinedShape> {
                    private LazyParameterList() {
                    }

                    public ParameterDescription.InDefinedShape get(int i) {
                        return new LazyParameterDescription(i);
                    }

                    public boolean hasExplicitMetaData() {
                        for (int i = 0; i < size(); i++) {
                            if (LazyMethodDescription.this.parameterNames[i] == null || LazyMethodDescription.this.parameterModifiers[i] == null) {
                                return false;
                            }
                        }
                        return true;
                    }

                    public int size() {
                        return LazyMethodDescription.this.parameterTypeDescriptors.size();
                    }

                    public TypeList.Generic asTypeList() {
                        return LazyMethodDescription.this.signatureResolution.resolveParameterTypes(LazyMethodDescription.this.parameterTypeDescriptors, LazyMethodDescription.this.this$0.typePool, LazyMethodDescription.this.parameterTypeAnnotationTokens, LazyMethodDescription.this);
                    }
                }

                private class LazyParameterDescription extends ParameterDescription.InDefinedShape.AbstractBase {
                    private final int index;

                    protected LazyParameterDescription(int i) {
                        this.index = i;
                    }

                    public MethodDescription.InDefinedShape getDeclaringMethod() {
                        return LazyMethodDescription.this;
                    }

                    public int getIndex() {
                        return this.index;
                    }

                    public boolean isNamed() {
                        return LazyMethodDescription.this.parameterNames[this.index] != null;
                    }

                    public boolean hasModifiers() {
                        return LazyMethodDescription.this.parameterModifiers[this.index] != null;
                    }

                    public String getName() {
                        if (isNamed()) {
                            return LazyMethodDescription.this.parameterNames[this.index];
                        }
                        return super.getName();
                    }

                    public int getModifiers() {
                        if (hasModifiers()) {
                            return LazyMethodDescription.this.parameterModifiers[this.index].intValue();
                        }
                        return super.getModifiers();
                    }

                    public TypeDescription.Generic getType() {
                        return (TypeDescription.Generic) LazyMethodDescription.this.signatureResolution.resolveParameterTypes(LazyMethodDescription.this.parameterTypeDescriptors, LazyMethodDescription.this.this$0.typePool, LazyMethodDescription.this.parameterTypeAnnotationTokens, LazyMethodDescription.this).get(this.index);
                    }

                    public AnnotationList getDeclaredAnnotations() {
                        return LazyAnnotationDescription.asListOfNullable(LazyMethodDescription.this.this$0.typePool, (List) LazyMethodDescription.this.parameterAnnotationTokens.get(Integer.valueOf(this.index)));
                    }
                }

                private class LazyParameterizedReceiverType extends TypeDescription.Generic.OfParameterizedType {
                    private final TypeDescription typeDescription;

                    protected LazyParameterizedReceiverType(LazyMethodDescription lazyMethodDescription) {
                        this(lazyMethodDescription.this$0);
                    }

                    protected LazyParameterizedReceiverType(TypeDescription typeDescription2) {
                        this.typeDescription = typeDescription2;
                    }

                    public TypeList.Generic getTypeArguments() {
                        return new TypeArgumentList(this.typeDescription.getTypeVariables());
                    }

                    public TypeDescription.Generic getOwnerType() {
                        TypeDescription declaringType = this.typeDescription.getDeclaringType();
                        if (declaringType == null) {
                            return TypeDescription.Generic.UNDEFINED;
                        }
                        return (this.typeDescription.isStatic() || !declaringType.isGenerified()) ? new LazyNonGenericReceiverType(declaringType) : new LazyParameterizedReceiverType(declaringType);
                    }

                    public AnnotationList getDeclaredAnnotations() {
                        return LazyAnnotationDescription.asListOfNullable(LazyMethodDescription.this.this$0.typePool, (List) LazyMethodDescription.this.receiverTypeAnnotationTokens.get(getTypePath()));
                    }

                    /* access modifiers changed from: private */
                    public String getTypePath() {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < this.typeDescription.getInnerClassCount(); i++) {
                            sb.append('.');
                        }
                        return sb.toString();
                    }

                    public TypeDescription asErasure() {
                        return this.typeDescription;
                    }

                    protected class TypeArgumentList extends TypeList.Generic.AbstractBase {
                        private final List<? extends TypeDescription.Generic> typeVariables;

                        protected TypeArgumentList(List<? extends TypeDescription.Generic> list) {
                            this.typeVariables = list;
                        }

                        public TypeDescription.Generic get(int i) {
                            return new AnnotatedTypeVariable((TypeDescription.Generic) this.typeVariables.get(i), i);
                        }

                        public int size() {
                            return this.typeVariables.size();
                        }

                        protected class AnnotatedTypeVariable extends TypeDescription.Generic.OfTypeVariable {
                            private final int index;
                            private final TypeDescription.Generic typeVariable;

                            protected AnnotatedTypeVariable(TypeDescription.Generic generic, int i) {
                                this.typeVariable = generic;
                                this.index = i;
                            }

                            public TypeList.Generic getUpperBounds() {
                                return this.typeVariable.getUpperBounds();
                            }

                            public TypeVariableSource getTypeVariableSource() {
                                return this.typeVariable.getTypeVariableSource();
                            }

                            public String getSymbol() {
                                return this.typeVariable.getSymbol();
                            }

                            public AnnotationList getDeclaredAnnotations() {
                                TypePool access$2200 = LazyMethodDescription.this.this$0.typePool;
                                Map access$3000 = LazyMethodDescription.this.receiverTypeAnnotationTokens;
                                return LazyAnnotationDescription.asListOfNullable(access$2200, (List) access$3000.get(LazyParameterizedReceiverType.this.getTypePath() + this.index + ';'));
                            }
                        }
                    }
                }

                protected class LazyNonGenericReceiverType extends TypeDescription.Generic.OfNonGenericType {
                    private final TypeDescription typeDescription;

                    protected LazyNonGenericReceiverType(LazyMethodDescription lazyMethodDescription) {
                        this(lazyMethodDescription.this$0);
                    }

                    protected LazyNonGenericReceiverType(TypeDescription typeDescription2) {
                        this.typeDescription = typeDescription2;
                    }

                    public TypeDescription.Generic getOwnerType() {
                        TypeDescription declaringType = this.typeDescription.getDeclaringType();
                        return declaringType == null ? TypeDescription.Generic.UNDEFINED : new LazyNonGenericReceiverType(declaringType);
                    }

                    public TypeDescription.Generic getComponentType() {
                        return TypeDescription.Generic.UNDEFINED;
                    }

                    public AnnotationList getDeclaredAnnotations() {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < this.typeDescription.getInnerClassCount(); i++) {
                            sb.append('.');
                        }
                        return LazyAnnotationDescription.asListOfNullable(LazyMethodDescription.this.this$0.typePool, (List) LazyMethodDescription.this.receiverTypeAnnotationTokens.get(sb.toString()));
                    }

                    public TypeDescription asErasure() {
                        return this.typeDescription;
                    }
                }
            }
        }

        protected class TypeExtractor extends ClassVisitor {
            private static final int REAL_MODIFIER_MASK = 65535;
            private int actualModifiers;
            private final List<LazyTypeDescription.AnnotationToken> annotationTokens = new ArrayList();
            private boolean anonymousType = false;
            private final List<String> declaredTypes = new ArrayList();
            private String declaringTypeName;
            /* access modifiers changed from: private */
            public final List<LazyTypeDescription.FieldToken> fieldTokens = new ArrayList();
            private String genericSignature;
            private String[] interfaceName;
            private String internalName;
            /* access modifiers changed from: private */
            public final List<LazyTypeDescription.MethodToken> methodTokens = new ArrayList();
            private int modifiers;
            private String nestHost;
            private final List<String> nestMembers = new ArrayList();
            private String superClassName;
            private final Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> superTypeAnnotationTokens = new HashMap();
            private LazyTypeDescription.TypeContainment typeContainment = LazyTypeDescription.TypeContainment.SelfContained.INSTANCE;
            private final Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> typeVariableAnnotationTokens = new HashMap();
            private final Map<Integer, Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>>> typeVariableBoundsAnnotationTokens = new HashMap();

            protected TypeExtractor() {
                super(OpenedClassReader.ASM_API);
            }

            public void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
                this.modifiers = 65535 & i2;
                this.actualModifiers = i2;
                this.internalName = str;
                this.genericSignature = str2;
                this.superClassName = str3;
                this.interfaceName = strArr;
            }

            public void visitOuterClass(String str, String str2, String str3) {
                if (str2 != null) {
                    this.typeContainment = new LazyTypeDescription.TypeContainment.WithinMethod(str, str2, str3);
                } else if (str != null) {
                    this.typeContainment = new LazyTypeDescription.TypeContainment.WithinType(str, true);
                }
            }

            public void visitInnerClass(String str, String str2, String str3, int i) {
                if (str.equals(this.internalName)) {
                    if (str2 != null) {
                        this.declaringTypeName = str2;
                        if (this.typeContainment.isSelfContained()) {
                            this.typeContainment = new LazyTypeDescription.TypeContainment.WithinType(str2, false);
                        }
                    }
                    if (str3 == null && !this.typeContainment.isSelfContained()) {
                        this.anonymousType = true;
                    }
                    this.modifiers = 65535 & i;
                } else if (str2 != null && str3 != null && str2.equals(this.internalName)) {
                    List<String> list = this.declaredTypes;
                    list.add("L" + str + ";");
                }
            }

            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex$DoubleIndexed} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex$DoubleIndexed} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex$DoubleIndexed} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex$DoubleIndexed} */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public net.bytebuddy.jar.asm.AnnotationVisitor visitTypeAnnotation(int r8, net.bytebuddy.jar.asm.TypePath r9, java.lang.String r10, boolean r11) {
                /*
                    r7 = this;
                    net.bytebuddy.jar.asm.TypeReference r11 = new net.bytebuddy.jar.asm.TypeReference
                    r11.<init>(r8)
                    int r8 = r11.getSort()
                    if (r8 == 0) goto L_0x004d
                    r0 = 16
                    if (r8 == r0) goto L_0x0041
                    r0 = 17
                    if (r8 != r0) goto L_0x0026
                    net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex$DoubleIndexed r8 = new net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex$DoubleIndexed
                    int r4 = r11.getTypeParameterBoundIndex()
                    int r5 = r11.getTypeParameterIndex()
                    java.util.Map<java.lang.Integer, java.util.Map<java.lang.Integer, java.util.Map<java.lang.String, java.util.List<net.bytebuddy.pool.TypePool$Default$LazyTypeDescription$AnnotationToken>>>> r6 = r7.typeVariableBoundsAnnotationTokens
                    r1 = r8
                    r2 = r10
                    r3 = r9
                    r1.<init>(r2, r3, r4, r5, r6)
                    goto L_0x0058
                L_0x0026:
                    java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
                    java.lang.StringBuilder r9 = new java.lang.StringBuilder
                    r9.<init>()
                    java.lang.String r10 = "Unexpected type reference: "
                    r9.append(r10)
                    int r10 = r11.getSort()
                    r9.append(r10)
                    java.lang.String r9 = r9.toString()
                    r8.<init>(r9)
                    throw r8
                L_0x0041:
                    net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex r8 = new net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex
                    int r11 = r11.getSuperTypeIndex()
                    java.util.Map<java.lang.Integer, java.util.Map<java.lang.String, java.util.List<net.bytebuddy.pool.TypePool$Default$LazyTypeDescription$AnnotationToken>>> r0 = r7.superTypeAnnotationTokens
                    r8.<init>(r10, r9, r11, r0)
                    goto L_0x0058
                L_0x004d:
                    net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex r8 = new net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex
                    int r11 = r11.getTypeParameterIndex()
                    java.util.Map<java.lang.Integer, java.util.Map<java.lang.String, java.util.List<net.bytebuddy.pool.TypePool$Default$LazyTypeDescription$AnnotationToken>>> r0 = r7.typeVariableAnnotationTokens
                    r8.<init>(r10, r9, r11, r0)
                L_0x0058:
                    net.bytebuddy.pool.TypePool$Default$TypeExtractor$AnnotationExtractor r9 = new net.bytebuddy.pool.TypePool$Default$TypeExtractor$AnnotationExtractor
                    net.bytebuddy.pool.TypePool$Default$ComponentTypeLocator$ForAnnotationProperty r11 = new net.bytebuddy.pool.TypePool$Default$ComponentTypeLocator$ForAnnotationProperty
                    net.bytebuddy.pool.TypePool$Default r0 = net.bytebuddy.pool.TypePool.Default.this
                    r11.<init>(r0, r10)
                    r9.<init>(r8, r11)
                    return r9
                */
                throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.pool.TypePool.Default.TypeExtractor.visitTypeAnnotation(int, net.bytebuddy.jar.asm.TypePath, java.lang.String, boolean):net.bytebuddy.jar.asm.AnnotationVisitor");
            }

            public AnnotationVisitor visitAnnotation(String str, boolean z) {
                return new AnnotationExtractor(this, str, this.annotationTokens, new ComponentTypeLocator.ForAnnotationProperty(Default.this, str));
            }

            public FieldVisitor visitField(int i, String str, String str2, String str3, Object obj) {
                return new FieldExtractor(i & 65535, str, str2, str3);
            }

            public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
                if (str.equals(MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME)) {
                    return Default.IGNORE_METHOD;
                }
                return new MethodExtractor(i & 65535, str, str2, str3, strArr);
            }

            public void visitNestHost(String str) {
                this.nestHost = str;
            }

            public void visitNestMember(String str) {
                this.nestMembers.add(str);
            }

            /* access modifiers changed from: protected */
            public TypeDescription toTypeDescription() {
                return new LazyTypeDescription(Default.this, this.actualModifiers, this.modifiers, this.internalName, this.superClassName, this.interfaceName, this.genericSignature, this.typeContainment, this.declaringTypeName, this.declaredTypes, this.anonymousType, this.nestHost, this.nestMembers, this.superTypeAnnotationTokens, this.typeVariableAnnotationTokens, this.typeVariableBoundsAnnotationTokens, this.annotationTokens, this.fieldTokens, this.methodTokens);
            }

            protected class AnnotationExtractor extends AnnotationVisitor {
                /* access modifiers changed from: private */
                public final AnnotationRegistrant annotationRegistrant;
                private final ComponentTypeLocator componentTypeLocator;

                protected AnnotationExtractor(TypeExtractor typeExtractor, String str, List<LazyTypeDescription.AnnotationToken> list, ComponentTypeLocator componentTypeLocator2) {
                    this(new AnnotationRegistrant.ForByteCodeElement(str, list), componentTypeLocator2);
                }

                protected AnnotationExtractor(TypeExtractor typeExtractor, String str, int i, Map<Integer, List<LazyTypeDescription.AnnotationToken>> map, ComponentTypeLocator componentTypeLocator2) {
                    this(new AnnotationRegistrant.ForByteCodeElement.WithIndex(str, i, map), componentTypeLocator2);
                }

                protected AnnotationExtractor(AnnotationRegistrant annotationRegistrant2, ComponentTypeLocator componentTypeLocator2) {
                    super(OpenedClassReader.ASM_API);
                    this.annotationRegistrant = annotationRegistrant2;
                    this.componentTypeLocator = componentTypeLocator2;
                }

                public void visit(String str, Object obj) {
                    AnnotationValue annotationValue;
                    AnnotationRegistrant annotationRegistrant2 = this.annotationRegistrant;
                    if (obj instanceof Type) {
                        annotationValue = new AbstractBase.RawTypeValue(Default.this, (Type) obj);
                    } else {
                        annotationValue = AnnotationValue.ForConstant.of(obj);
                    }
                    annotationRegistrant2.register(str, annotationValue);
                }

                public void visitEnum(String str, String str2, String str3) {
                    this.annotationRegistrant.register(str, new AbstractBase.RawEnumerationValue(Default.this, str2, str3));
                }

                public AnnotationVisitor visitAnnotation(String str, String str2) {
                    return new AnnotationExtractor(new AnnotationLookup(str2, str), new ComponentTypeLocator.ForAnnotationProperty(Default.this, str2));
                }

                public AnnotationVisitor visitArray(String str) {
                    return new AnnotationExtractor(new ArrayLookup(str, this.componentTypeLocator.bind(str)), ComponentTypeLocator.Illegal.INSTANCE);
                }

                public void visitEnd() {
                    this.annotationRegistrant.onComplete();
                }

                protected class ArrayLookup implements AnnotationRegistrant {
                    private final AbstractBase.RawDescriptionArray.ComponentTypeReference componentTypeReference;
                    private final String name;
                    private final List<AnnotationValue<?, ?>> values = new ArrayList();

                    protected ArrayLookup(String str, AbstractBase.RawDescriptionArray.ComponentTypeReference componentTypeReference2) {
                        this.name = str;
                        this.componentTypeReference = componentTypeReference2;
                    }

                    public void register(String str, AnnotationValue<?, ?> annotationValue) {
                        this.values.add(annotationValue);
                    }

                    public void onComplete() {
                        AnnotationExtractor.this.annotationRegistrant.register(this.name, new AbstractBase.RawDescriptionArray(Default.this, this.componentTypeReference, this.values));
                    }
                }

                protected class AnnotationLookup implements AnnotationRegistrant {
                    private final String descriptor;
                    private final String name;
                    private final Map<String, AnnotationValue<?, ?>> values = new HashMap();

                    protected AnnotationLookup(String str, String str2) {
                        this.descriptor = str;
                        this.name = str2;
                    }

                    public void register(String str, AnnotationValue<?, ?> annotationValue) {
                        this.values.put(str, annotationValue);
                    }

                    public void onComplete() {
                        AnnotationExtractor.this.annotationRegistrant.register(this.name, new AbstractBase.RawAnnotationValue(Default.this, new LazyTypeDescription.AnnotationToken(this.descriptor, this.values)));
                    }
                }
            }

            protected class FieldExtractor extends FieldVisitor {
                private final List<LazyTypeDescription.AnnotationToken> annotationTokens = new ArrayList();
                private final String descriptor;
                private final String genericSignature;
                private final String internalName;
                private final int modifiers;
                private final Map<String, List<LazyTypeDescription.AnnotationToken>> typeAnnotationTokens = new HashMap();

                protected FieldExtractor(int i, String str, String str2, String str3) {
                    super(OpenedClassReader.ASM_API);
                    this.modifiers = i;
                    this.internalName = str;
                    this.descriptor = str2;
                    this.genericSignature = str3;
                }

                public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
                    TypeReference typeReference = new TypeReference(i);
                    if (typeReference.getSort() == 19) {
                        AnnotationRegistrant.ForTypeVariable forTypeVariable = new AnnotationRegistrant.ForTypeVariable(str, typePath, this.typeAnnotationTokens);
                        TypeExtractor typeExtractor = TypeExtractor.this;
                        return new AnnotationExtractor(forTypeVariable, new ComponentTypeLocator.ForAnnotationProperty(Default.this, str));
                    }
                    throw new IllegalStateException("Unexpected type reference on field: " + typeReference.getSort());
                }

                public AnnotationVisitor visitAnnotation(String str, boolean z) {
                    TypeExtractor typeExtractor = TypeExtractor.this;
                    return new AnnotationExtractor(typeExtractor, str, this.annotationTokens, new ComponentTypeLocator.ForAnnotationProperty(Default.this, str));
                }

                public void visitEnd() {
                    TypeExtractor.this.fieldTokens.add(new LazyTypeDescription.FieldToken(this.internalName, this.modifiers, this.descriptor, this.genericSignature, this.typeAnnotationTokens, this.annotationTokens));
                }
            }

            protected class MethodExtractor extends MethodVisitor implements AnnotationRegistrant {
                private final List<LazyTypeDescription.AnnotationToken> annotationTokens = new ArrayList();
                private AnnotationValue<?, ?> defaultValue;
                private final String descriptor;
                private final String[] exceptionName;
                private final Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> exceptionTypeAnnotationTokens = new HashMap();
                private Label firstLabel;
                private final String genericSignature;
                private final String internalName;
                private int invisibleParameterShift;
                private final ParameterBag legacyParameterBag;
                private final int modifiers;
                private final Map<Integer, List<LazyTypeDescription.AnnotationToken>> parameterAnnotationTokens = new HashMap();
                private final List<LazyTypeDescription.MethodToken.ParameterToken> parameterTokens = new ArrayList();
                private final Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> parameterTypeAnnotationTokens = new HashMap();
                private final Map<String, List<LazyTypeDescription.AnnotationToken>> receiverTypeAnnotationTokens = new HashMap();
                private final Map<String, List<LazyTypeDescription.AnnotationToken>> returnTypeAnnotationTokens = new HashMap();
                private final Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> typeVariableAnnotationTokens = new HashMap();
                private final Map<Integer, Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>>> typeVariableBoundAnnotationTokens = new HashMap();
                private int visibleParameterShift;

                public void onComplete() {
                }

                protected MethodExtractor(int i, String str, String str2, String str3, String[] strArr) {
                    super(OpenedClassReader.ASM_API);
                    this.modifiers = i;
                    this.internalName = str;
                    this.descriptor = str2;
                    this.genericSignature = str3;
                    this.exceptionName = strArr;
                    this.legacyParameterBag = new ParameterBag(Type.getMethodType(str2).getArgumentTypes());
                }

                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex$DoubleIndexed} */
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex} */
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable} */
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable} */
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v7, resolved type: net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex} */
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex} */
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v10, resolved type: net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex$DoubleIndexed} */
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex$DoubleIndexed} */
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v11, resolved type: net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex$DoubleIndexed} */
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v12, resolved type: net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex$DoubleIndexed} */
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v13, resolved type: net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex$DoubleIndexed} */
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v14, resolved type: net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex$DoubleIndexed} */
                /* JADX WARNING: Multi-variable type inference failed */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public net.bytebuddy.jar.asm.AnnotationVisitor visitTypeAnnotation(int r7, net.bytebuddy.jar.asm.TypePath r8, java.lang.String r9, boolean r10) {
                    /*
                        r6 = this;
                        net.bytebuddy.jar.asm.TypeReference r10 = new net.bytebuddy.jar.asm.TypeReference
                        r10.<init>(r7)
                        int r7 = r10.getSort()
                        r0 = 1
                        if (r7 == r0) goto L_0x0069
                        r0 = 18
                        if (r7 == r0) goto L_0x0056
                        switch(r7) {
                            case 20: goto L_0x004e;
                            case 21: goto L_0x0046;
                            case 22: goto L_0x003a;
                            case 23: goto L_0x002e;
                            default: goto L_0x0013;
                        }
                    L_0x0013:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.StringBuilder r8 = new java.lang.StringBuilder
                        r8.<init>()
                        java.lang.String r9 = "Unexpected type reference on method: "
                        r8.append(r9)
                        int r9 = r10.getSort()
                        r8.append(r9)
                        java.lang.String r8 = r8.toString()
                        r7.<init>(r8)
                        throw r7
                    L_0x002e:
                        net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex r7 = new net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex
                        int r10 = r10.getExceptionIndex()
                        java.util.Map<java.lang.Integer, java.util.Map<java.lang.String, java.util.List<net.bytebuddy.pool.TypePool$Default$LazyTypeDescription$AnnotationToken>>> r0 = r6.exceptionTypeAnnotationTokens
                        r7.<init>(r9, r8, r10, r0)
                        goto L_0x0074
                    L_0x003a:
                        net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex r7 = new net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex
                        int r10 = r10.getFormalParameterIndex()
                        java.util.Map<java.lang.Integer, java.util.Map<java.lang.String, java.util.List<net.bytebuddy.pool.TypePool$Default$LazyTypeDescription$AnnotationToken>>> r0 = r6.parameterTypeAnnotationTokens
                        r7.<init>(r9, r8, r10, r0)
                        goto L_0x0074
                    L_0x0046:
                        net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable r7 = new net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable
                        java.util.Map<java.lang.String, java.util.List<net.bytebuddy.pool.TypePool$Default$LazyTypeDescription$AnnotationToken>> r10 = r6.receiverTypeAnnotationTokens
                        r7.<init>(r9, r8, r10)
                        goto L_0x0074
                    L_0x004e:
                        net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable r7 = new net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable
                        java.util.Map<java.lang.String, java.util.List<net.bytebuddy.pool.TypePool$Default$LazyTypeDescription$AnnotationToken>> r10 = r6.returnTypeAnnotationTokens
                        r7.<init>(r9, r8, r10)
                        goto L_0x0074
                    L_0x0056:
                        net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex$DoubleIndexed r7 = new net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex$DoubleIndexed
                        int r3 = r10.getTypeParameterBoundIndex()
                        int r4 = r10.getTypeParameterIndex()
                        java.util.Map<java.lang.Integer, java.util.Map<java.lang.Integer, java.util.Map<java.lang.String, java.util.List<net.bytebuddy.pool.TypePool$Default$LazyTypeDescription$AnnotationToken>>>> r5 = r6.typeVariableBoundAnnotationTokens
                        r0 = r7
                        r1 = r9
                        r2 = r8
                        r0.<init>(r1, r2, r3, r4, r5)
                        goto L_0x0074
                    L_0x0069:
                        net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex r7 = new net.bytebuddy.pool.TypePool$Default$AnnotationRegistrant$ForTypeVariable$WithIndex
                        int r10 = r10.getTypeParameterIndex()
                        java.util.Map<java.lang.Integer, java.util.Map<java.lang.String, java.util.List<net.bytebuddy.pool.TypePool$Default$LazyTypeDescription$AnnotationToken>>> r0 = r6.typeVariableAnnotationTokens
                        r7.<init>(r9, r8, r10, r0)
                    L_0x0074:
                        net.bytebuddy.pool.TypePool$Default$TypeExtractor$AnnotationExtractor r8 = new net.bytebuddy.pool.TypePool$Default$TypeExtractor$AnnotationExtractor
                        net.bytebuddy.pool.TypePool$Default$TypeExtractor r10 = net.bytebuddy.pool.TypePool.Default.TypeExtractor.this
                        net.bytebuddy.pool.TypePool$Default$ComponentTypeLocator$ForAnnotationProperty r0 = new net.bytebuddy.pool.TypePool$Default$ComponentTypeLocator$ForAnnotationProperty
                        net.bytebuddy.pool.TypePool$Default r1 = net.bytebuddy.pool.TypePool.Default.this
                        r0.<init>(r1, r9)
                        r8.<init>(r7, r0)
                        return r8
                    */
                    throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.pool.TypePool.Default.TypeExtractor.MethodExtractor.visitTypeAnnotation(int, net.bytebuddy.jar.asm.TypePath, java.lang.String, boolean):net.bytebuddy.jar.asm.AnnotationVisitor");
                }

                public AnnotationVisitor visitAnnotation(String str, boolean z) {
                    TypeExtractor typeExtractor = TypeExtractor.this;
                    return new AnnotationExtractor(typeExtractor, str, this.annotationTokens, new ComponentTypeLocator.ForAnnotationProperty(Default.this, str));
                }

                public void visitAnnotableParameterCount(int i, boolean z) {
                    if (z) {
                        this.visibleParameterShift = Type.getMethodType(this.descriptor).getArgumentTypes().length - i;
                    } else {
                        this.invisibleParameterShift = Type.getMethodType(this.descriptor).getArgumentTypes().length - i;
                    }
                }

                public AnnotationVisitor visitParameterAnnotation(int i, String str, boolean z) {
                    return new AnnotationExtractor(TypeExtractor.this, str, i + (z ? this.visibleParameterShift : this.invisibleParameterShift), this.parameterAnnotationTokens, new ComponentTypeLocator.ForAnnotationProperty(Default.this, str));
                }

                public void visitLabel(Label label) {
                    if (Default.this.readerMode.isExtended() && this.firstLabel == null) {
                        this.firstLabel = label;
                    }
                }

                public void visitLocalVariable(String str, String str2, String str3, Label label, Label label2, int i) {
                    if (Default.this.readerMode.isExtended() && label == this.firstLabel) {
                        this.legacyParameterBag.register(i, str);
                    }
                }

                public void visitParameter(String str, int i) {
                    this.parameterTokens.add(new LazyTypeDescription.MethodToken.ParameterToken(str, Integer.valueOf(i)));
                }

                public AnnotationVisitor visitAnnotationDefault() {
                    return new AnnotationExtractor(this, new ComponentTypeLocator.ForArrayType(this.descriptor));
                }

                public void register(String str, AnnotationValue<?, ?> annotationValue) {
                    this.defaultValue = annotationValue;
                }

                public void visitEnd() {
                    List list;
                    List<LazyTypeDescription.MethodToken.ParameterToken> list2;
                    List access$3500 = TypeExtractor.this.methodTokens;
                    String str = this.internalName;
                    int i = this.modifiers;
                    String str2 = this.descriptor;
                    String str3 = this.genericSignature;
                    String[] strArr = this.exceptionName;
                    Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> map = this.typeVariableAnnotationTokens;
                    Map<Integer, Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>>> map2 = this.typeVariableBoundAnnotationTokens;
                    Map<String, List<LazyTypeDescription.AnnotationToken>> map3 = this.returnTypeAnnotationTokens;
                    Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> map4 = this.parameterTypeAnnotationTokens;
                    Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> map5 = this.exceptionTypeAnnotationTokens;
                    Map<String, List<LazyTypeDescription.AnnotationToken>> map6 = this.receiverTypeAnnotationTokens;
                    List<LazyTypeDescription.AnnotationToken> list3 = this.annotationTokens;
                    Map<Integer, List<LazyTypeDescription.AnnotationToken>> map7 = this.parameterAnnotationTokens;
                    if (this.parameterTokens.isEmpty()) {
                        list = access$3500;
                        list2 = this.legacyParameterBag.resolve((this.modifiers & 8) != 0);
                    } else {
                        list = access$3500;
                        list2 = this.parameterTokens;
                    }
                    LazyTypeDescription.MethodToken methodToken = r2;
                    LazyTypeDescription.MethodToken methodToken2 = new LazyTypeDescription.MethodToken(str, i, str2, str3, strArr, map, map2, map3, map4, map5, map6, list3, map7, list2, this.defaultValue);
                    list.add(methodToken);
                }
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class LazyFacade extends AbstractBase {
        private final TypePool typePool;

        public boolean equals(Object obj) {
            if (!super.equals(obj)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.typePool.equals(((LazyFacade) obj).typePool);
        }

        public int hashCode() {
            return (super.hashCode() * 31) + this.typePool.hashCode();
        }

        public LazyFacade(TypePool typePool2) {
            super(CacheProvider.NoOp.INSTANCE);
            this.typePool = typePool2;
        }

        /* access modifiers changed from: protected */
        public Resolution doDescribe(String str) {
            return new LazyResolution(this.typePool, str);
        }

        public void clear() {
            this.typePool.clear();
        }

        @HashCodeAndEqualsPlugin.Enhance
        protected static class LazyResolution implements Resolution {
            private final String name;
            private final TypePool typePool;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                LazyResolution lazyResolution = (LazyResolution) obj;
                return this.name.equals(lazyResolution.name) && this.typePool.equals(lazyResolution.typePool);
            }

            public int hashCode() {
                return ((527 + this.typePool.hashCode()) * 31) + this.name.hashCode();
            }

            protected LazyResolution(TypePool typePool2, String str) {
                this.typePool = typePool2;
                this.name = str;
            }

            public boolean isResolved() {
                return this.typePool.describe(this.name).isResolved();
            }

            public TypeDescription resolve() {
                return new LazyTypeDescription(this.typePool, this.name);
            }
        }

        protected static class LazyTypeDescription extends TypeDescription.AbstractBase.OfSimpleType.WithDelegation {
            private transient /* synthetic */ TypeDescription delegate;
            private final String name;
            private final TypePool typePool;

            protected LazyTypeDescription(TypePool typePool2, String str) {
                this.typePool = typePool2;
                this.name = str;
            }

            public String getName() {
                return this.name;
            }

            /* access modifiers changed from: protected */
            @CachedReturnPlugin.Enhance("delegate")
            public TypeDescription delegate() {
                TypeDescription resolve = this.delegate != null ? null : this.typePool.describe(this.name).resolve();
                if (resolve == null) {
                    return this.delegate;
                }
                this.delegate = resolve;
                return resolve;
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class ClassLoading extends AbstractBase.Hierarchical {
        private static final ClassLoader BOOTSTRAP_CLASS_LOADER = null;
        private final ClassLoader classLoader;

        public boolean equals(Object obj) {
            if (!super.equals(obj)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.classLoader.equals(((ClassLoading) obj).classLoader);
        }

        public int hashCode() {
            return (super.hashCode() * 31) + this.classLoader.hashCode();
        }

        public ClassLoading(CacheProvider cacheProvider, TypePool typePool, ClassLoader classLoader2) {
            super(cacheProvider, typePool);
            this.classLoader = classLoader2;
        }

        public static TypePool of(ClassLoader classLoader2) {
            return of(classLoader2, Empty.INSTANCE);
        }

        public static TypePool of(ClassLoader classLoader2, TypePool typePool) {
            return new ClassLoading(CacheProvider.NoOp.INSTANCE, typePool, classLoader2);
        }

        public static TypePool ofSystemLoader() {
            return of(ClassLoader.getSystemClassLoader());
        }

        public static TypePool ofPlatformLoader() {
            return of(ClassLoader.getSystemClassLoader().getParent());
        }

        public static TypePool ofBootLoader() {
            return of(BOOTSTRAP_CLASS_LOADER);
        }

        /* access modifiers changed from: protected */
        public Resolution doDescribe(String str) {
            try {
                return new Resolution.Simple(TypeDescription.ForLoadedType.of(Class.forName(str, false, this.classLoader)));
            } catch (ClassNotFoundException unused) {
                return new Resolution.Illegal(str);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class Explicit extends AbstractBase.Hierarchical {
        private final Map<String, TypeDescription> types;

        public boolean equals(Object obj) {
            if (!super.equals(obj)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.types.equals(((Explicit) obj).types);
        }

        public int hashCode() {
            return (super.hashCode() * 31) + this.types.hashCode();
        }

        public Explicit(Map<String, TypeDescription> map) {
            this(Empty.INSTANCE, map);
        }

        public Explicit(TypePool typePool, Map<String, TypeDescription> map) {
            super(CacheProvider.NoOp.INSTANCE, typePool);
            this.types = map;
        }

        /* access modifiers changed from: protected */
        public Resolution doDescribe(String str) {
            TypeDescription typeDescription = this.types.get(str);
            return typeDescription == null ? new Resolution.Illegal(str) : new Resolution.Simple(typeDescription);
        }
    }
}
