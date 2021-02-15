package net.bytebuddy.build;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.scaffold.inline.MethodNameTransformer;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.CompoundList;

public interface Plugin extends ElementMatcher<TypeDescription>, Closeable {
    DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassFileLocator classFileLocator);

    public interface Factory {
        Plugin make();

        @HashCodeAndEqualsPlugin.Enhance
        public static class Simple implements Factory {
            private final Plugin plugin;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.plugin.equals(((Simple) obj).plugin);
            }

            public int hashCode() {
                return 527 + this.plugin.hashCode();
            }

            public Simple(Plugin plugin2) {
                this.plugin = plugin2;
            }

            public Plugin make() {
                return this.plugin;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class UsingReflection implements Factory {
            private final List<ArgumentResolver> argumentResolvers;
            private final Class<? extends Plugin> type;

            @Documented
            @Target({ElementType.CONSTRUCTOR})
            @Retention(RetentionPolicy.RUNTIME)
            public @interface Priority {
                public static final int DEFAULT = 0;

                int value();
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                UsingReflection usingReflection = (UsingReflection) obj;
                return this.type.equals(usingReflection.type) && this.argumentResolvers.equals(usingReflection.argumentResolvers);
            }

            public int hashCode() {
                return ((527 + this.type.hashCode()) * 31) + this.argumentResolvers.hashCode();
            }

            public UsingReflection(Class<? extends Plugin> cls) {
                this(cls, Collections.emptyList());
            }

            protected UsingReflection(Class<? extends Plugin> cls, List<ArgumentResolver> list) {
                this.type = cls;
                this.argumentResolvers = list;
            }

            public UsingReflection with(ArgumentResolver... argumentResolverArr) {
                return with((List<? extends ArgumentResolver>) Arrays.asList(argumentResolverArr));
            }

            public UsingReflection with(List<? extends ArgumentResolver> list) {
                return new UsingReflection(this.type, CompoundList.of(list, (List<? extends ArgumentResolver>) this.argumentResolvers));
            }

            public Plugin make() {
                boolean z;
                Instantiator unresolved = new Instantiator.Unresolved(this.type);
                loop0:
                for (Constructor constructor : this.type.getConstructors()) {
                    if (!constructor.isSynthetic()) {
                        ArrayList arrayList = new ArrayList(constructor.getParameterTypes().length);
                        int i = 0;
                        for (Class cls : constructor.getParameterTypes()) {
                            Iterator<ArgumentResolver> it = this.argumentResolvers.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    z = false;
                                    break;
                                }
                                ArgumentResolver.Resolution resolve = it.next().resolve(i, cls);
                                if (resolve.isResolved()) {
                                    arrayList.add(resolve.getArgument());
                                    z = true;
                                    break;
                                }
                            }
                            if (!z) {
                                break loop0;
                            }
                            i++;
                        }
                        unresolved = unresolved.replaceBy(new Instantiator.Resolved(constructor, arrayList));
                    }
                }
                return unresolved.instantiate();
            }

            protected interface Instantiator {
                Plugin instantiate();

                Instantiator replaceBy(Resolved resolved);

                @HashCodeAndEqualsPlugin.Enhance
                public static class Unresolved implements Instantiator {
                    private final Class<? extends Plugin> type;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.type.equals(((Unresolved) obj).type);
                    }

                    public int hashCode() {
                        return 527 + this.type.hashCode();
                    }

                    public Instantiator replaceBy(Resolved resolved) {
                        return resolved;
                    }

                    protected Unresolved(Class<? extends Plugin> cls) {
                        this.type = cls;
                    }

                    public Plugin instantiate() {
                        throw new IllegalStateException("No constructor available for " + this.type);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class Resolved implements Instantiator {
                    private final List<?> arguments;
                    private final Constructor<? extends Plugin> constructor;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        Resolved resolved = (Resolved) obj;
                        return this.constructor.equals(resolved.constructor) && this.arguments.equals(resolved.arguments);
                    }

                    public int hashCode() {
                        return ((527 + this.constructor.hashCode()) * 31) + this.arguments.hashCode();
                    }

                    protected Resolved(Constructor<? extends Plugin> constructor2, List<?> list) {
                        this.constructor = constructor2;
                        this.arguments = list;
                    }

                    public Instantiator replaceBy(Resolved resolved) {
                        int i;
                        Priority priority = (Priority) this.constructor.getAnnotation(Priority.class);
                        Priority priority2 = (Priority) resolved.constructor.getAnnotation(Priority.class);
                        int i2 = 0;
                        if (priority == null) {
                            i = 0;
                        } else {
                            i = priority.value();
                        }
                        if (priority2 != null) {
                            i2 = priority2.value();
                        }
                        if (i > i2) {
                            return this;
                        }
                        if (i < i2) {
                            return resolved;
                        }
                        throw new IllegalStateException("Ambiguous constructors " + this.constructor + " and " + resolved.constructor);
                    }

                    public Plugin instantiate() {
                        try {
                            return (Plugin) this.constructor.newInstance(this.arguments.toArray(new Object[this.arguments.size()]));
                        } catch (InstantiationException e) {
                            throw new IllegalStateException("Failed to instantiate plugin via " + this.constructor, e);
                        } catch (IllegalAccessException e2) {
                            throw new IllegalStateException("Failed to access " + this.constructor, e2);
                        } catch (InvocationTargetException e3) {
                            throw new IllegalStateException("Error during construction of" + this.constructor, e3.getCause());
                        }
                    }
                }
            }

            public interface ArgumentResolver {
                Resolution resolve(int i, Class<?> cls);

                public interface Resolution {
                    Object getArgument();

                    boolean isResolved();

                    public enum Unresolved implements Resolution {
                        INSTANCE;

                        public boolean isResolved() {
                            return false;
                        }

                        public Object getArgument() {
                            throw new IllegalStateException("Cannot get the argument for an unresolved parameter");
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance
                    public static class Resolved implements Resolution {
                        @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
                        private final Object argument;

                        /* JADX WARNING: Removed duplicated region for block: B:17:0x0027 A[RETURN] */
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
                                java.lang.Object r2 = r4.argument
                                net.bytebuddy.build.Plugin$Factory$UsingReflection$ArgumentResolver$Resolution$Resolved r5 = (net.bytebuddy.build.Plugin.Factory.UsingReflection.ArgumentResolver.Resolution.Resolved) r5
                                java.lang.Object r5 = r5.argument
                                if (r5 == 0) goto L_0x0024
                                if (r2 == 0) goto L_0x0026
                                boolean r5 = r2.equals(r5)
                                if (r5 != 0) goto L_0x0027
                                return r1
                            L_0x0024:
                                if (r2 == 0) goto L_0x0027
                            L_0x0026:
                                return r1
                            L_0x0027:
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.build.Plugin.Factory.UsingReflection.ArgumentResolver.Resolution.Resolved.equals(java.lang.Object):boolean");
                        }

                        public int hashCode() {
                            Object obj = this.argument;
                            if (obj != null) {
                                return 527 + obj.hashCode();
                            }
                            return 527;
                        }

                        public boolean isResolved() {
                            return true;
                        }

                        public Resolved(Object obj) {
                            this.argument = obj;
                        }

                        public Object getArgument() {
                            return this.argument;
                        }
                    }
                }

                public enum NoOp implements ArgumentResolver {
                    INSTANCE;

                    public Resolution resolve(int i, Class<?> cls) {
                        return Resolution.Unresolved.INSTANCE;
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class ForType<T> implements ArgumentResolver {
                    private final Class<? extends T> type;
                    private final T value;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        ForType forType = (ForType) obj;
                        return this.type.equals(forType.type) && this.value.equals(forType.value);
                    }

                    public int hashCode() {
                        return ((527 + this.type.hashCode()) * 31) + this.value.hashCode();
                    }

                    protected ForType(Class<? extends T> cls, T t) {
                        this.type = cls;
                        this.value = t;
                    }

                    public static <S> ArgumentResolver of(Class<? extends S> cls, S s) {
                        return new ForType(cls, s);
                    }

                    public Resolution resolve(int i, Class<?> cls) {
                        return cls == this.type ? new Resolution.Resolved(this.value) : Resolution.Unresolved.INSTANCE;
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class ForIndex implements ArgumentResolver {
                    /* access modifiers changed from: private */
                    public static final Map<Class<?>, Class<?>> WRAPPER_TYPES;
                    private final int index;
                    private final Object value;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        ForIndex forIndex = (ForIndex) obj;
                        return this.index == forIndex.index && this.value.equals(forIndex.value);
                    }

                    public int hashCode() {
                        return ((527 + this.index) * 31) + this.value.hashCode();
                    }

                    static {
                        HashMap hashMap = new HashMap();
                        WRAPPER_TYPES = hashMap;
                        hashMap.put(Boolean.TYPE, Boolean.class);
                        WRAPPER_TYPES.put(Byte.TYPE, Byte.class);
                        WRAPPER_TYPES.put(Short.TYPE, Short.class);
                        WRAPPER_TYPES.put(Character.TYPE, Character.class);
                        WRAPPER_TYPES.put(Integer.TYPE, Integer.class);
                        WRAPPER_TYPES.put(Long.TYPE, Long.class);
                        WRAPPER_TYPES.put(Float.TYPE, Float.class);
                        WRAPPER_TYPES.put(Double.TYPE, Double.class);
                    }

                    public ForIndex(int i, Object obj) {
                        this.index = i;
                        this.value = obj;
                    }

                    public Resolution resolve(int i, Class<?> cls) {
                        if (this.index != i) {
                            return Resolution.Unresolved.INSTANCE;
                        }
                        if (cls.isPrimitive()) {
                            return WRAPPER_TYPES.get(cls).isInstance(this.value) ? new Resolution.Resolved(this.value) : Resolution.Unresolved.INSTANCE;
                        }
                        Object obj = this.value;
                        return (obj == null || cls.isInstance(obj)) ? new Resolution.Resolved(this.value) : Resolution.Unresolved.INSTANCE;
                    }

                    @HashCodeAndEqualsPlugin.Enhance
                    public static class WithDynamicType implements ArgumentResolver {
                        private final int index;
                        private final String value;

                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            if (obj == null || getClass() != obj.getClass()) {
                                return false;
                            }
                            WithDynamicType withDynamicType = (WithDynamicType) obj;
                            return this.index == withDynamicType.index && this.value.equals(withDynamicType.value);
                        }

                        public int hashCode() {
                            return ((527 + this.index) * 31) + this.value.hashCode();
                        }

                        public WithDynamicType(int i, String str) {
                            this.index = i;
                            this.value = str;
                        }

                        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v17, resolved type: java.lang.Object} */
                        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v10, resolved type: java.lang.Class<?>} */
                        /* JADX WARNING: Multi-variable type inference failed */
                        /* Code decompiled incorrectly, please refer to instructions dump. */
                        public net.bytebuddy.build.Plugin.Factory.UsingReflection.ArgumentResolver.Resolution resolve(int r5, java.lang.Class<?> r6) {
                            /*
                                r4 = this;
                                int r0 = r4.index
                                if (r0 == r5) goto L_0x0007
                                net.bytebuddy.build.Plugin$Factory$UsingReflection$ArgumentResolver$Resolution$Unresolved r5 = net.bytebuddy.build.Plugin.Factory.UsingReflection.ArgumentResolver.Resolution.Unresolved.INSTANCE
                                return r5
                            L_0x0007:
                                java.lang.Class r5 = java.lang.Character.TYPE
                                r0 = 0
                                r1 = 1
                                if (r6 == r5) goto L_0x0078
                                java.lang.Class<java.lang.Character> r5 = java.lang.Character.class
                                if (r6 != r5) goto L_0x0012
                                goto L_0x0078
                            L_0x0012:
                                java.lang.Class<java.lang.String> r5 = java.lang.String.class
                                if (r6 != r5) goto L_0x001e
                                net.bytebuddy.build.Plugin$Factory$UsingReflection$ArgumentResolver$Resolution$Resolved r5 = new net.bytebuddy.build.Plugin$Factory$UsingReflection$ArgumentResolver$Resolution$Resolved
                                java.lang.String r6 = r4.value
                                r5.<init>(r6)
                                return r5
                            L_0x001e:
                                boolean r5 = r6.isPrimitive()
                                if (r5 == 0) goto L_0x002f
                                java.util.Map r5 = net.bytebuddy.build.Plugin.Factory.UsingReflection.ArgumentResolver.ForIndex.WRAPPER_TYPES
                                java.lang.Object r5 = r5.get(r6)
                                r6 = r5
                                java.lang.Class r6 = (java.lang.Class) r6
                            L_0x002f:
                                java.lang.String r5 = "valueOf"
                                java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ IllegalAccessException -> 0x0071, InvocationTargetException -> 0x0066, NoSuchMethodException -> 0x0063 }
                                java.lang.Class<java.lang.String> r3 = java.lang.String.class
                                r2[r0] = r3     // Catch:{ IllegalAccessException -> 0x0071, InvocationTargetException -> 0x0066, NoSuchMethodException -> 0x0063 }
                                java.lang.reflect.Method r5 = r6.getMethod(r5, r2)     // Catch:{ IllegalAccessException -> 0x0071, InvocationTargetException -> 0x0066, NoSuchMethodException -> 0x0063 }
                                int r2 = r5.getModifiers()     // Catch:{ IllegalAccessException -> 0x0071, InvocationTargetException -> 0x0066, NoSuchMethodException -> 0x0063 }
                                boolean r2 = java.lang.reflect.Modifier.isStatic(r2)     // Catch:{ IllegalAccessException -> 0x0071, InvocationTargetException -> 0x0066, NoSuchMethodException -> 0x0063 }
                                if (r2 == 0) goto L_0x0060
                                java.lang.Class r2 = r5.getReturnType()     // Catch:{ IllegalAccessException -> 0x0071, InvocationTargetException -> 0x0066, NoSuchMethodException -> 0x0063 }
                                boolean r6 = r6.isAssignableFrom(r2)     // Catch:{ IllegalAccessException -> 0x0071, InvocationTargetException -> 0x0066, NoSuchMethodException -> 0x0063 }
                                if (r6 == 0) goto L_0x0060
                                net.bytebuddy.build.Plugin$Factory$UsingReflection$ArgumentResolver$Resolution$Resolved r6 = new net.bytebuddy.build.Plugin$Factory$UsingReflection$ArgumentResolver$Resolution$Resolved     // Catch:{ IllegalAccessException -> 0x0071, InvocationTargetException -> 0x0066, NoSuchMethodException -> 0x0063 }
                                r2 = 0
                                java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ IllegalAccessException -> 0x0071, InvocationTargetException -> 0x0066, NoSuchMethodException -> 0x0063 }
                                java.lang.String r3 = r4.value     // Catch:{ IllegalAccessException -> 0x0071, InvocationTargetException -> 0x0066, NoSuchMethodException -> 0x0063 }
                                r1[r0] = r3     // Catch:{ IllegalAccessException -> 0x0071, InvocationTargetException -> 0x0066, NoSuchMethodException -> 0x0063 }
                                java.lang.Object r5 = r5.invoke(r2, r1)     // Catch:{ IllegalAccessException -> 0x0071, InvocationTargetException -> 0x0066, NoSuchMethodException -> 0x0063 }
                                r6.<init>(r5)     // Catch:{ IllegalAccessException -> 0x0071, InvocationTargetException -> 0x0066, NoSuchMethodException -> 0x0063 }
                                goto L_0x0062
                            L_0x0060:
                                net.bytebuddy.build.Plugin$Factory$UsingReflection$ArgumentResolver$Resolution$Unresolved r6 = net.bytebuddy.build.Plugin.Factory.UsingReflection.ArgumentResolver.Resolution.Unresolved.INSTANCE     // Catch:{ IllegalAccessException -> 0x0071, InvocationTargetException -> 0x0066, NoSuchMethodException -> 0x0063 }
                            L_0x0062:
                                return r6
                            L_0x0063:
                                net.bytebuddy.build.Plugin$Factory$UsingReflection$ArgumentResolver$Resolution$Unresolved r5 = net.bytebuddy.build.Plugin.Factory.UsingReflection.ArgumentResolver.Resolution.Unresolved.INSTANCE
                                return r5
                            L_0x0066:
                                r5 = move-exception
                                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                                java.lang.Throwable r5 = r5.getCause()
                                r6.<init>(r5)
                                throw r6
                            L_0x0071:
                                r5 = move-exception
                                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                                r6.<init>(r5)
                                throw r6
                            L_0x0078:
                                java.lang.String r5 = r4.value
                                int r5 = r5.length()
                                if (r5 != r1) goto L_0x0090
                                net.bytebuddy.build.Plugin$Factory$UsingReflection$ArgumentResolver$Resolution$Resolved r5 = new net.bytebuddy.build.Plugin$Factory$UsingReflection$ArgumentResolver$Resolution$Resolved
                                java.lang.String r6 = r4.value
                                char r6 = r6.charAt(r0)
                                java.lang.Character r6 = java.lang.Character.valueOf(r6)
                                r5.<init>(r6)
                                goto L_0x0092
                            L_0x0090:
                                net.bytebuddy.build.Plugin$Factory$UsingReflection$ArgumentResolver$Resolution$Unresolved r5 = net.bytebuddy.build.Plugin.Factory.UsingReflection.ArgumentResolver.Resolution.Unresolved.INSTANCE
                            L_0x0092:
                                return r5
                            */
                            throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.build.Plugin.Factory.UsingReflection.ArgumentResolver.ForIndex.WithDynamicType.resolve(int, java.lang.Class):net.bytebuddy.build.Plugin$Factory$UsingReflection$ArgumentResolver$Resolution");
                        }
                    }
                }
            }
        }
    }

    public interface Engine {
        public static final String CLASS_FILE_EXTENSION = ".class";

        Summary apply(File file, File file2, List<? extends Factory> list) throws IOException;

        Summary apply(File file, File file2, Factory... factoryArr) throws IOException;

        Summary apply(Source source, Target target, List<? extends Factory> list) throws IOException;

        Summary apply(Source source, Target target, Factory... factoryArr) throws IOException;

        Engine ignore(ElementMatcher<? super TypeDescription> elementMatcher);

        Engine with(ByteBuddy byteBuddy);

        Engine with(Listener listener);

        Engine with(PoolStrategy poolStrategy);

        Engine with(TypeStrategy typeStrategy);

        Engine with(ClassFileLocator classFileLocator);

        Engine withErrorHandlers(List<? extends ErrorHandler> list);

        Engine withErrorHandlers(ErrorHandler... errorHandlerArr);

        Engine withoutErrorHandlers();

        public interface TypeStrategy {

            public enum Default implements TypeStrategy {
                REDEFINE {
                    public DynamicType.Builder<?> builder(ByteBuddy byteBuddy, TypeDescription typeDescription, ClassFileLocator classFileLocator) {
                        return byteBuddy.redefine(typeDescription, classFileLocator);
                    }
                },
                REBASE {
                    public DynamicType.Builder<?> builder(ByteBuddy byteBuddy, TypeDescription typeDescription, ClassFileLocator classFileLocator) {
                        return byteBuddy.rebase(typeDescription, classFileLocator);
                    }
                },
                DECORATE {
                    public DynamicType.Builder<?> builder(ByteBuddy byteBuddy, TypeDescription typeDescription, ClassFileLocator classFileLocator) {
                        return byteBuddy.decorate(typeDescription, classFileLocator);
                    }
                }
            }

            DynamicType.Builder<?> builder(ByteBuddy byteBuddy, TypeDescription typeDescription, ClassFileLocator classFileLocator);

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForEntryPoint implements TypeStrategy {
                private final EntryPoint entryPoint;
                private final MethodNameTransformer methodNameTransformer;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    ForEntryPoint forEntryPoint = (ForEntryPoint) obj;
                    return this.entryPoint.equals(forEntryPoint.entryPoint) && this.methodNameTransformer.equals(forEntryPoint.methodNameTransformer);
                }

                public int hashCode() {
                    return ((527 + this.entryPoint.hashCode()) * 31) + this.methodNameTransformer.hashCode();
                }

                public ForEntryPoint(EntryPoint entryPoint2, MethodNameTransformer methodNameTransformer2) {
                    this.entryPoint = entryPoint2;
                    this.methodNameTransformer = methodNameTransformer2;
                }

                public DynamicType.Builder<?> builder(ByteBuddy byteBuddy, TypeDescription typeDescription, ClassFileLocator classFileLocator) {
                    return this.entryPoint.transform(typeDescription, byteBuddy, classFileLocator, this.methodNameTransformer);
                }
            }
        }

        public interface PoolStrategy {
            TypePool typePool(ClassFileLocator classFileLocator);

            public enum Default implements PoolStrategy {
                FAST(TypePool.Default.ReaderMode.FAST),
                EXTENDED(TypePool.Default.ReaderMode.EXTENDED);
                
                private final TypePool.Default.ReaderMode readerMode;

                private Default(TypePool.Default.ReaderMode readerMode2) {
                    this.readerMode = readerMode2;
                }

                public TypePool typePool(ClassFileLocator classFileLocator) {
                    return new TypePool.Default.WithLazyResolution(new TypePool.CacheProvider.Simple(), classFileLocator, this.readerMode, TypePool.ClassLoading.ofPlatformLoader());
                }
            }

            public enum Eager implements PoolStrategy {
                FAST(TypePool.Default.ReaderMode.FAST),
                EXTENDED(TypePool.Default.ReaderMode.EXTENDED);
                
                private final TypePool.Default.ReaderMode readerMode;

                private Eager(TypePool.Default.ReaderMode readerMode2) {
                    this.readerMode = readerMode2;
                }

                public TypePool typePool(ClassFileLocator classFileLocator) {
                    return new TypePool.Default(new TypePool.CacheProvider.Simple(), classFileLocator, this.readerMode, TypePool.ClassLoading.ofPlatformLoader());
                }
            }
        }

        public interface ErrorHandler {

            public enum Enforcing implements ErrorHandler {
                ALL_TYPES_RESOLVED {
                    public void onUnresolved(String str) {
                        throw new IllegalStateException("Failed to resolve type description for " + str);
                    }
                },
                NO_LIVE_INITIALIZERS {
                    public void onLiveInitializer(TypeDescription typeDescription, TypeDescription typeDescription2) {
                        throw new IllegalStateException("Failed to instrument " + typeDescription + " due to live initializer for " + typeDescription2);
                    }
                },
                CLASS_FILES_ONLY {
                    public void onResource(String str) {
                        throw new IllegalStateException("Discovered a resource when only class files were allowed: " + str);
                    }
                },
                MANIFEST_REQUIRED {
                    public void onManifest(Manifest manifest) {
                        if (manifest == null) {
                            throw new IllegalStateException("Required a manifest but no manifest was found");
                        }
                    }
                };

                public void onError(Map<TypeDescription, List<Throwable>> map) {
                }

                public void onError(Plugin plugin, Throwable th) {
                }

                public void onError(TypeDescription typeDescription, List<Throwable> list) {
                }

                public void onError(TypeDescription typeDescription, Plugin plugin, Throwable th) {
                }

                public void onLiveInitializer(TypeDescription typeDescription, TypeDescription typeDescription2) {
                }

                public void onManifest(Manifest manifest) {
                }

                public void onResource(String str) {
                }

                public void onUnresolved(String str) {
                }
            }

            void onError(Map<TypeDescription, List<Throwable>> map);

            void onError(Plugin plugin, Throwable th);

            void onError(TypeDescription typeDescription, List<Throwable> list);

            void onError(TypeDescription typeDescription, Plugin plugin, Throwable th);

            void onLiveInitializer(TypeDescription typeDescription, TypeDescription typeDescription2);

            void onManifest(Manifest manifest);

            void onResource(String str);

            void onUnresolved(String str);

            public enum Failing implements ErrorHandler {
                FAIL_FAST {
                    public void onError(TypeDescription typeDescription, Plugin plugin, Throwable th) {
                        throw new IllegalStateException("Failed to transform " + typeDescription + " using " + plugin, th);
                    }

                    public void onError(TypeDescription typeDescription, List<Throwable> list) {
                        throw new UnsupportedOperationException("onError");
                    }

                    public void onError(Map<TypeDescription, List<Throwable>> map) {
                        throw new UnsupportedOperationException("onError");
                    }
                },
                FAIL_AFTER_TYPE {
                    public void onError(TypeDescription typeDescription, Plugin plugin, Throwable th) {
                    }

                    public void onError(TypeDescription typeDescription, List<Throwable> list) {
                        throw new IllegalStateException("Failed to transform " + typeDescription + ": " + list);
                    }

                    public void onError(Map<TypeDescription, List<Throwable>> map) {
                        throw new UnsupportedOperationException("onError");
                    }
                },
                FAIL_LAST {
                    public void onError(TypeDescription typeDescription, List<Throwable> list) {
                    }

                    public void onError(TypeDescription typeDescription, Plugin plugin, Throwable th) {
                    }

                    public void onError(Map<TypeDescription, List<Throwable>> map) {
                        throw new IllegalStateException("Failed to transform at least one type: " + map);
                    }
                };

                public void onLiveInitializer(TypeDescription typeDescription, TypeDescription typeDescription2) {
                }

                public void onManifest(Manifest manifest) {
                }

                public void onResource(String str) {
                }

                public void onUnresolved(String str) {
                }

                public void onError(Plugin plugin, Throwable th) {
                    throw new IllegalStateException("Failed to close plugin " + plugin, th);
                }
            }

            public static class Compound implements ErrorHandler {
                private final List<ErrorHandler> errorHandlers;

                public Compound(ErrorHandler... errorHandlerArr) {
                    this((List<? extends ErrorHandler>) Arrays.asList(errorHandlerArr));
                }

                public Compound(List<? extends ErrorHandler> list) {
                    this.errorHandlers = new ArrayList();
                    for (ErrorHandler errorHandler : list) {
                        if (errorHandler instanceof Compound) {
                            this.errorHandlers.addAll(((Compound) errorHandler).errorHandlers);
                        } else if (!(errorHandler instanceof Listener.NoOp)) {
                            this.errorHandlers.add(errorHandler);
                        }
                    }
                }

                public void onError(TypeDescription typeDescription, Plugin plugin, Throwable th) {
                    for (ErrorHandler onError : this.errorHandlers) {
                        onError.onError(typeDescription, plugin, th);
                    }
                }

                public void onError(TypeDescription typeDescription, List<Throwable> list) {
                    for (ErrorHandler onError : this.errorHandlers) {
                        onError.onError(typeDescription, list);
                    }
                }

                public void onError(Map<TypeDescription, List<Throwable>> map) {
                    for (ErrorHandler onError : this.errorHandlers) {
                        onError.onError(map);
                    }
                }

                public void onError(Plugin plugin, Throwable th) {
                    for (ErrorHandler onError : this.errorHandlers) {
                        onError.onError(plugin, th);
                    }
                }

                public void onLiveInitializer(TypeDescription typeDescription, TypeDescription typeDescription2) {
                    for (ErrorHandler onLiveInitializer : this.errorHandlers) {
                        onLiveInitializer.onLiveInitializer(typeDescription, typeDescription2);
                    }
                }

                public void onUnresolved(String str) {
                    for (ErrorHandler onUnresolved : this.errorHandlers) {
                        onUnresolved.onUnresolved(str);
                    }
                }

                public void onManifest(Manifest manifest) {
                    for (ErrorHandler onManifest : this.errorHandlers) {
                        onManifest.onManifest(manifest);
                    }
                }

                public void onResource(String str) {
                    for (ErrorHandler onResource : this.errorHandlers) {
                        onResource.onResource(str);
                    }
                }
            }
        }

        public interface Listener extends ErrorHandler {

            public static abstract class Adapter implements Listener {
                public void onComplete(TypeDescription typeDescription) {
                }

                public void onDiscovery(String str) {
                }

                public void onError(Map<TypeDescription, List<Throwable>> map) {
                }

                public void onError(Plugin plugin, Throwable th) {
                }

                public void onError(TypeDescription typeDescription, List<Throwable> list) {
                }

                public void onError(TypeDescription typeDescription, Plugin plugin, Throwable th) {
                }

                public void onIgnored(TypeDescription typeDescription, List<Plugin> list) {
                }

                public void onIgnored(TypeDescription typeDescription, Plugin plugin) {
                }

                public void onLiveInitializer(TypeDescription typeDescription, TypeDescription typeDescription2) {
                }

                public void onManifest(Manifest manifest) {
                }

                public void onResource(String str) {
                }

                public void onTransformation(TypeDescription typeDescription, List<Plugin> list) {
                }

                public void onTransformation(TypeDescription typeDescription, Plugin plugin) {
                }

                public void onUnresolved(String str) {
                }
            }

            public enum NoOp implements Listener {
                INSTANCE;

                public void onComplete(TypeDescription typeDescription) {
                }

                public void onDiscovery(String str) {
                }

                public void onError(Map<TypeDescription, List<Throwable>> map) {
                }

                public void onError(Plugin plugin, Throwable th) {
                }

                public void onError(TypeDescription typeDescription, List<Throwable> list) {
                }

                public void onError(TypeDescription typeDescription, Plugin plugin, Throwable th) {
                }

                public void onIgnored(TypeDescription typeDescription, List<Plugin> list) {
                }

                public void onIgnored(TypeDescription typeDescription, Plugin plugin) {
                }

                public void onLiveInitializer(TypeDescription typeDescription, TypeDescription typeDescription2) {
                }

                public void onManifest(Manifest manifest) {
                }

                public void onResource(String str) {
                }

                public void onTransformation(TypeDescription typeDescription, List<Plugin> list) {
                }

                public void onTransformation(TypeDescription typeDescription, Plugin plugin) {
                }

                public void onUnresolved(String str) {
                }
            }

            void onComplete(TypeDescription typeDescription);

            void onDiscovery(String str);

            void onIgnored(TypeDescription typeDescription, List<Plugin> list);

            void onIgnored(TypeDescription typeDescription, Plugin plugin);

            void onTransformation(TypeDescription typeDescription, List<Plugin> list);

            void onTransformation(TypeDescription typeDescription, Plugin plugin);

            @HashCodeAndEqualsPlugin.Enhance
            public static class StreamWriting extends Adapter {
                protected static final String PREFIX = "[Byte Buddy]";
                private final PrintStream printStream;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.printStream.equals(((StreamWriting) obj).printStream);
                }

                public int hashCode() {
                    return 527 + this.printStream.hashCode();
                }

                public StreamWriting(PrintStream printStream2) {
                    this.printStream = printStream2;
                }

                public static StreamWriting toSystemOut() {
                    return new StreamWriting(System.out);
                }

                public static StreamWriting toSystemError() {
                    return new StreamWriting(System.err);
                }

                public Listener withTransformationsOnly() {
                    return new WithTransformationsOnly(this);
                }

                public Listener withErrorsOnly() {
                    return new WithErrorsOnly(this);
                }

                public void onDiscovery(String str) {
                    this.printStream.printf("[Byte Buddy] DISCOVERY %s", new Object[]{str});
                }

                public void onTransformation(TypeDescription typeDescription, Plugin plugin) {
                    this.printStream.printf("[Byte Buddy] TRANSFORM %s for %s", new Object[]{typeDescription, plugin});
                }

                public void onIgnored(TypeDescription typeDescription, Plugin plugin) {
                    this.printStream.printf("[Byte Buddy] IGNORE %s for %s", new Object[]{typeDescription, plugin});
                }

                public void onError(TypeDescription typeDescription, Plugin plugin, Throwable th) {
                    synchronized (this.printStream) {
                        this.printStream.printf("[Byte Buddy] ERROR %s for %s", new Object[]{typeDescription, plugin});
                        th.printStackTrace(this.printStream);
                    }
                }

                public void onError(Plugin plugin, Throwable th) {
                    synchronized (this.printStream) {
                        this.printStream.printf("[Byte Buddy] ERROR %s", new Object[]{plugin});
                        th.printStackTrace(this.printStream);
                    }
                }

                public void onUnresolved(String str) {
                    this.printStream.printf("[Byte Buddy] UNRESOLVED %s", new Object[]{str});
                }

                public void onLiveInitializer(TypeDescription typeDescription, TypeDescription typeDescription2) {
                    this.printStream.printf("[Byte Buddy] LIVE %s on %s", new Object[]{typeDescription, typeDescription2});
                }

                public void onComplete(TypeDescription typeDescription) {
                    this.printStream.printf("[Byte Buddy] COMPLETE %s", new Object[]{typeDescription});
                }

                public void onManifest(Manifest manifest) {
                    PrintStream printStream2 = this.printStream;
                    boolean z = true;
                    Object[] objArr = new Object[1];
                    if (manifest == null) {
                        z = false;
                    }
                    objArr[0] = Boolean.valueOf(z);
                    printStream2.printf("[Byte Buddy] MANIFEST %b", objArr);
                }

                public void onResource(String str) {
                    this.printStream.printf("[Byte Buddy] RESOURCE %s", new Object[]{str});
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class WithTransformationsOnly extends Adapter {
                private final Listener delegate;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.delegate.equals(((WithTransformationsOnly) obj).delegate);
                }

                public int hashCode() {
                    return 527 + this.delegate.hashCode();
                }

                public WithTransformationsOnly(Listener listener) {
                    this.delegate = listener;
                }

                public void onTransformation(TypeDescription typeDescription, Plugin plugin) {
                    this.delegate.onTransformation(typeDescription, plugin);
                }

                public void onTransformation(TypeDescription typeDescription, List<Plugin> list) {
                    this.delegate.onTransformation(typeDescription, list);
                }

                public void onError(TypeDescription typeDescription, Plugin plugin, Throwable th) {
                    this.delegate.onError(typeDescription, plugin, th);
                }

                public void onError(TypeDescription typeDescription, List<Throwable> list) {
                    this.delegate.onError(typeDescription, list);
                }

                public void onError(Map<TypeDescription, List<Throwable>> map) {
                    this.delegate.onError(map);
                }

                public void onError(Plugin plugin, Throwable th) {
                    this.delegate.onError(plugin, th);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class WithErrorsOnly extends Adapter {
                private final Listener delegate;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.delegate.equals(((WithErrorsOnly) obj).delegate);
                }

                public int hashCode() {
                    return 527 + this.delegate.hashCode();
                }

                public WithErrorsOnly(Listener listener) {
                    this.delegate = listener;
                }

                public void onError(TypeDescription typeDescription, Plugin plugin, Throwable th) {
                    this.delegate.onError(typeDescription, plugin, th);
                }

                public void onError(TypeDescription typeDescription, List<Throwable> list) {
                    this.delegate.onError(typeDescription, list);
                }

                public void onError(Map<TypeDescription, List<Throwable>> map) {
                    this.delegate.onError(map);
                }

                public void onError(Plugin plugin, Throwable th) {
                    this.delegate.onError(plugin, th);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForErrorHandler extends Adapter {
                private final ErrorHandler errorHandler;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.errorHandler.equals(((ForErrorHandler) obj).errorHandler);
                }

                public int hashCode() {
                    return 527 + this.errorHandler.hashCode();
                }

                public ForErrorHandler(ErrorHandler errorHandler2) {
                    this.errorHandler = errorHandler2;
                }

                public void onError(TypeDescription typeDescription, Plugin plugin, Throwable th) {
                    this.errorHandler.onError(typeDescription, plugin, th);
                }

                public void onError(TypeDescription typeDescription, List<Throwable> list) {
                    this.errorHandler.onError(typeDescription, list);
                }

                public void onError(Map<TypeDescription, List<Throwable>> map) {
                    this.errorHandler.onError(map);
                }

                public void onError(Plugin plugin, Throwable th) {
                    this.errorHandler.onError(plugin, th);
                }

                public void onLiveInitializer(TypeDescription typeDescription, TypeDescription typeDescription2) {
                    this.errorHandler.onLiveInitializer(typeDescription, typeDescription2);
                }

                public void onUnresolved(String str) {
                    this.errorHandler.onUnresolved(str);
                }

                public void onManifest(Manifest manifest) {
                    this.errorHandler.onManifest(manifest);
                }

                public void onResource(String str) {
                    this.errorHandler.onResource(str);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Compound implements Listener {
                private final List<Listener> listeners;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.listeners.equals(((Compound) obj).listeners);
                }

                public int hashCode() {
                    return 527 + this.listeners.hashCode();
                }

                public Compound(Listener... listenerArr) {
                    this((List<? extends Listener>) Arrays.asList(listenerArr));
                }

                public Compound(List<? extends Listener> list) {
                    this.listeners = new ArrayList();
                    for (Listener listener : list) {
                        if (listener instanceof Compound) {
                            this.listeners.addAll(((Compound) listener).listeners);
                        } else if (!(listener instanceof NoOp)) {
                            this.listeners.add(listener);
                        }
                    }
                }

                public void onDiscovery(String str) {
                    for (Listener onDiscovery : this.listeners) {
                        onDiscovery.onDiscovery(str);
                    }
                }

                public void onTransformation(TypeDescription typeDescription, Plugin plugin) {
                    for (Listener onTransformation : this.listeners) {
                        onTransformation.onTransformation(typeDescription, plugin);
                    }
                }

                public void onTransformation(TypeDescription typeDescription, List<Plugin> list) {
                    for (Listener onTransformation : this.listeners) {
                        onTransformation.onTransformation(typeDescription, list);
                    }
                }

                public void onIgnored(TypeDescription typeDescription, Plugin plugin) {
                    for (Listener onIgnored : this.listeners) {
                        onIgnored.onIgnored(typeDescription, plugin);
                    }
                }

                public void onIgnored(TypeDescription typeDescription, List<Plugin> list) {
                    for (Listener onIgnored : this.listeners) {
                        onIgnored.onIgnored(typeDescription, list);
                    }
                }

                public void onError(TypeDescription typeDescription, Plugin plugin, Throwable th) {
                    for (Listener onError : this.listeners) {
                        onError.onError(typeDescription, plugin, th);
                    }
                }

                public void onError(TypeDescription typeDescription, List<Throwable> list) {
                    for (Listener onError : this.listeners) {
                        onError.onError(typeDescription, list);
                    }
                }

                public void onError(Map<TypeDescription, List<Throwable>> map) {
                    for (Listener onError : this.listeners) {
                        onError.onError(map);
                    }
                }

                public void onError(Plugin plugin, Throwable th) {
                    for (Listener onError : this.listeners) {
                        onError.onError(plugin, th);
                    }
                }

                public void onLiveInitializer(TypeDescription typeDescription, TypeDescription typeDescription2) {
                    for (Listener onLiveInitializer : this.listeners) {
                        onLiveInitializer.onLiveInitializer(typeDescription, typeDescription2);
                    }
                }

                public void onComplete(TypeDescription typeDescription) {
                    for (Listener onComplete : this.listeners) {
                        onComplete.onComplete(typeDescription);
                    }
                }

                public void onUnresolved(String str) {
                    for (Listener onUnresolved : this.listeners) {
                        onUnresolved.onUnresolved(str);
                    }
                }

                public void onManifest(Manifest manifest) {
                    for (Listener onManifest : this.listeners) {
                        onManifest.onManifest(manifest);
                    }
                }

                public void onResource(String str) {
                    for (Listener onResource : this.listeners) {
                        onResource.onResource(str);
                    }
                }
            }
        }

        public interface Source {
            Origin read() throws IOException;

            public interface Origin extends Iterable<Element>, Closeable {
                public static final Manifest NO_MANIFEST = null;

                ClassFileLocator getClassFileLocator();

                Manifest getManifest() throws IOException;

                public static class ForJarFile implements Origin {
                    /* access modifiers changed from: private */
                    public final JarFile file;

                    public ForJarFile(JarFile jarFile) {
                        this.file = jarFile;
                    }

                    public Manifest getManifest() throws IOException {
                        return this.file.getManifest();
                    }

                    public ClassFileLocator getClassFileLocator() {
                        return new ClassFileLocator.ForJarFile(this.file);
                    }

                    public void close() throws IOException {
                        this.file.close();
                    }

                    public Iterator<Element> iterator() {
                        return new JarFileIterator(this.file.entries());
                    }

                    protected class JarFileIterator implements Iterator<Element> {
                        private final Enumeration<JarEntry> enumeration;

                        protected JarFileIterator(Enumeration<JarEntry> enumeration2) {
                            this.enumeration = enumeration2;
                        }

                        public boolean hasNext() {
                            return this.enumeration.hasMoreElements();
                        }

                        public Element next() {
                            return new Element.ForJarEntry(ForJarFile.this.file, this.enumeration.nextElement());
                        }

                        public void remove() {
                            throw new UnsupportedOperationException("remove");
                        }
                    }
                }
            }

            public interface Element {
                InputStream getInputStream() throws IOException;

                String getName();

                <T> T resolveAs(Class<T> cls);

                @HashCodeAndEqualsPlugin.Enhance
                public static class ForByteArray implements Element {
                    private final byte[] binaryRepresentation;
                    private final String name;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        ForByteArray forByteArray = (ForByteArray) obj;
                        return this.name.equals(forByteArray.name) && Arrays.equals(this.binaryRepresentation, forByteArray.binaryRepresentation);
                    }

                    public int hashCode() {
                        return ((527 + this.name.hashCode()) * 31) + Arrays.hashCode(this.binaryRepresentation);
                    }

                    public <T> T resolveAs(Class<T> cls) {
                        return null;
                    }

                    public ForByteArray(String str, byte[] bArr) {
                        this.name = str;
                        this.binaryRepresentation = bArr;
                    }

                    public String getName() {
                        return this.name;
                    }

                    public InputStream getInputStream() {
                        return new ByteArrayInputStream(this.binaryRepresentation);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class ForFile implements Element {
                    private final File file;
                    private final File root;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        ForFile forFile = (ForFile) obj;
                        return this.root.equals(forFile.root) && this.file.equals(forFile.file);
                    }

                    public int hashCode() {
                        return ((527 + this.root.hashCode()) * 31) + this.file.hashCode();
                    }

                    public ForFile(File file2, File file3) {
                        this.root = file2;
                        this.file = file3;
                    }

                    public String getName() {
                        return this.root.getAbsoluteFile().toURI().relativize(this.file.getAbsoluteFile().toURI()).getPath();
                    }

                    public InputStream getInputStream() throws IOException {
                        return new FileInputStream(this.file);
                    }

                    public <T> T resolveAs(Class<T> cls) {
                        if (File.class.isAssignableFrom(cls)) {
                            return this.file;
                        }
                        return null;
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class ForJarEntry implements Element {
                    private final JarEntry entry;
                    private final JarFile file;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        ForJarEntry forJarEntry = (ForJarEntry) obj;
                        return this.file.equals(forJarEntry.file) && this.entry.equals(forJarEntry.entry);
                    }

                    public int hashCode() {
                        return ((527 + this.file.hashCode()) * 31) + this.entry.hashCode();
                    }

                    public ForJarEntry(JarFile jarFile, JarEntry jarEntry) {
                        this.file = jarFile;
                        this.entry = jarEntry;
                    }

                    public String getName() {
                        return this.entry.getName();
                    }

                    public InputStream getInputStream() throws IOException {
                        return this.file.getInputStream(this.entry);
                    }

                    public <T> T resolveAs(Class<T> cls) {
                        if (JarEntry.class.isAssignableFrom(cls)) {
                            return this.entry;
                        }
                        return null;
                    }
                }
            }

            public enum Empty implements Source, Origin {
                INSTANCE;

                public void close() {
                }

                public Origin read() {
                    return this;
                }

                public ClassFileLocator getClassFileLocator() {
                    return ClassFileLocator.NoOp.INSTANCE;
                }

                public Manifest getManifest() {
                    return NO_MANIFEST;
                }

                public Iterator<Element> iterator() {
                    return Collections.emptySet().iterator();
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class InMemory implements Source, Origin {
                private final Map<String, byte[]> storage;

                public void close() {
                }

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.storage.equals(((InMemory) obj).storage);
                }

                public int hashCode() {
                    return 527 + this.storage.hashCode();
                }

                public Origin read() {
                    return this;
                }

                public InMemory(Map<String, byte[]> map) {
                    this.storage = map;
                }

                public static Source ofTypes(Class<?>... clsArr) {
                    return ofTypes((Collection<? extends Class<?>>) Arrays.asList(clsArr));
                }

                public static Source ofTypes(Collection<? extends Class<?>> collection) {
                    HashMap hashMap = new HashMap();
                    for (Class cls : collection) {
                        hashMap.put(TypeDescription.ForLoadedType.of(cls), ClassFileLocator.ForClassLoader.read((Class<?>) cls));
                    }
                    return ofTypes((Map<TypeDescription, byte[]>) hashMap);
                }

                public static Source ofTypes(Map<TypeDescription, byte[]> map) {
                    HashMap hashMap = new HashMap();
                    for (Map.Entry next : map.entrySet()) {
                        hashMap.put(((TypeDescription) next.getKey()).getInternalName() + ".class", next.getValue());
                    }
                    return new InMemory(hashMap);
                }

                public ClassFileLocator getClassFileLocator() {
                    return ClassFileLocator.Simple.ofResources(this.storage);
                }

                public Manifest getManifest() throws IOException {
                    byte[] bArr = this.storage.get("META-INF/MANIFEST.MF");
                    if (bArr == null) {
                        return NO_MANIFEST;
                    }
                    return new Manifest(new ByteArrayInputStream(bArr));
                }

                public Iterator<Element> iterator() {
                    return new MapEntryIterator(this.storage.entrySet().iterator());
                }

                protected static class MapEntryIterator implements Iterator<Element> {
                    private final Iterator<Map.Entry<String, byte[]>> iterator;

                    protected MapEntryIterator(Iterator<Map.Entry<String, byte[]>> it) {
                        this.iterator = it;
                    }

                    public boolean hasNext() {
                        return this.iterator.hasNext();
                    }

                    public Element next() {
                        Map.Entry next = this.iterator.next();
                        return new Element.ForByteArray((String) next.getKey(), (byte[]) next.getValue());
                    }

                    public void remove() {
                        throw new UnsupportedOperationException("remove");
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForFolder implements Source, Origin {
                /* access modifiers changed from: private */
                public final File folder;

                public void close() {
                }

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.folder.equals(((ForFolder) obj).folder);
                }

                public int hashCode() {
                    return 527 + this.folder.hashCode();
                }

                public Origin read() {
                    return this;
                }

                public ForFolder(File file) {
                    this.folder = file;
                }

                public ClassFileLocator getClassFileLocator() {
                    return new ClassFileLocator.ForFolder(this.folder);
                }

                public Manifest getManifest() throws IOException {
                    File file = new File(this.folder, "META-INF/MANIFEST.MF");
                    if (!file.exists()) {
                        return NO_MANIFEST;
                    }
                    FileInputStream fileInputStream = new FileInputStream(file);
                    try {
                        return new Manifest(fileInputStream);
                    } finally {
                        fileInputStream.close();
                    }
                }

                public Iterator<Element> iterator() {
                    return new FolderIterator(this.folder);
                }

                protected class FolderIterator implements Iterator<Element> {
                    private final LinkedList<File> files;

                    protected FolderIterator(File file) {
                        this.files = new LinkedList<>(Collections.singleton(file));
                        while (true) {
                            File[] listFiles = this.files.removeFirst().listFiles();
                            if (listFiles != null) {
                                this.files.addAll(0, Arrays.asList(listFiles));
                            }
                            if (this.files.isEmpty()) {
                                return;
                            }
                            if (!this.files.peek().isDirectory() && !this.files.peek().equals(new File(file, "META-INF/MANIFEST.MF"))) {
                                return;
                            }
                        }
                    }

                    public boolean hasNext() {
                        return !this.files.isEmpty();
                    }

                    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
                        jadx.core.utils.exceptions.JadxOverflowException: 
                        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
                        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
                        */
                    public net.bytebuddy.build.Plugin.Engine.Source.Element next() {
                        /*
                            r6 = this;
                            java.lang.String r0 = "META-INF/MANIFEST.MF"
                            r1 = 0
                            net.bytebuddy.build.Plugin$Engine$Source$Element$ForFile r2 = new net.bytebuddy.build.Plugin$Engine$Source$Element$ForFile     // Catch:{ all -> 0x005e }
                            net.bytebuddy.build.Plugin$Engine$Source$ForFolder r3 = net.bytebuddy.build.Plugin.Engine.Source.ForFolder.this     // Catch:{ all -> 0x005e }
                            java.io.File r3 = r3.folder     // Catch:{ all -> 0x005e }
                            java.util.LinkedList<java.io.File> r4 = r6.files     // Catch:{ all -> 0x005e }
                            java.lang.Object r4 = r4.removeFirst()     // Catch:{ all -> 0x005e }
                            java.io.File r4 = (java.io.File) r4     // Catch:{ all -> 0x005e }
                            r2.<init>(r3, r4)     // Catch:{ all -> 0x005e }
                        L_0x0016:
                            java.util.LinkedList<java.io.File> r3 = r6.files
                            boolean r3 = r3.isEmpty()
                            if (r3 != 0) goto L_0x005d
                            java.util.LinkedList<java.io.File> r3 = r6.files
                            java.lang.Object r3 = r3.peek()
                            java.io.File r3 = (java.io.File) r3
                            boolean r3 = r3.isDirectory()
                            if (r3 != 0) goto L_0x0045
                            java.util.LinkedList<java.io.File> r3 = r6.files
                            java.lang.Object r3 = r3.peek()
                            java.io.File r3 = (java.io.File) r3
                            java.io.File r4 = new java.io.File
                            net.bytebuddy.build.Plugin$Engine$Source$ForFolder r5 = net.bytebuddy.build.Plugin.Engine.Source.ForFolder.this
                            java.io.File r5 = r5.folder
                            r4.<init>(r5, r0)
                            boolean r3 = r3.equals(r4)
                            if (r3 == 0) goto L_0x005d
                        L_0x0045:
                            java.util.LinkedList<java.io.File> r3 = r6.files
                            java.lang.Object r3 = r3.removeFirst()
                            java.io.File r3 = (java.io.File) r3
                            java.io.File[] r3 = r3.listFiles()
                            if (r3 == 0) goto L_0x0016
                            java.util.LinkedList<java.io.File> r4 = r6.files
                            java.util.List r3 = java.util.Arrays.asList(r3)
                            r4.addAll(r1, r3)
                            goto L_0x0016
                        L_0x005d:
                            return r2
                        L_0x005e:
                            r2 = move-exception
                        L_0x005f:
                            java.util.LinkedList<java.io.File> r3 = r6.files
                            boolean r3 = r3.isEmpty()
                            if (r3 != 0) goto L_0x00a6
                            java.util.LinkedList<java.io.File> r3 = r6.files
                            java.lang.Object r3 = r3.peek()
                            java.io.File r3 = (java.io.File) r3
                            boolean r3 = r3.isDirectory()
                            if (r3 != 0) goto L_0x008e
                            java.util.LinkedList<java.io.File> r3 = r6.files
                            java.lang.Object r3 = r3.peek()
                            java.io.File r3 = (java.io.File) r3
                            java.io.File r4 = new java.io.File
                            net.bytebuddy.build.Plugin$Engine$Source$ForFolder r5 = net.bytebuddy.build.Plugin.Engine.Source.ForFolder.this
                            java.io.File r5 = r5.folder
                            r4.<init>(r5, r0)
                            boolean r3 = r3.equals(r4)
                            if (r3 == 0) goto L_0x00a6
                        L_0x008e:
                            java.util.LinkedList<java.io.File> r3 = r6.files
                            java.lang.Object r3 = r3.removeFirst()
                            java.io.File r3 = (java.io.File) r3
                            java.io.File[] r3 = r3.listFiles()
                            if (r3 == 0) goto L_0x005f
                            java.util.LinkedList<java.io.File> r4 = r6.files
                            java.util.List r3 = java.util.Arrays.asList(r3)
                            r4.addAll(r1, r3)
                            goto L_0x005f
                        L_0x00a6:
                            throw r2
                        */
                        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.build.Plugin.Engine.Source.ForFolder.FolderIterator.next():net.bytebuddy.build.Plugin$Engine$Source$Element");
                    }

                    public void remove() {
                        throw new UnsupportedOperationException("remove");
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForJarFile implements Source {
                private final File file;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.file.equals(((ForJarFile) obj).file);
                }

                public int hashCode() {
                    return 527 + this.file.hashCode();
                }

                public ForJarFile(File file2) {
                    this.file = file2;
                }

                public Origin read() throws IOException {
                    return new Origin.ForJarFile(new JarFile(this.file));
                }
            }
        }

        public interface Target {

            public enum Discarding implements Target, Sink {
                INSTANCE;

                public void close() {
                }

                public void retain(Source.Element element) {
                }

                public void store(Map<TypeDescription, byte[]> map) {
                }

                public Sink write(Manifest manifest) {
                    return this;
                }
            }

            Sink write(Manifest manifest) throws IOException;

            public interface Sink extends Closeable {
                void retain(Source.Element element) throws IOException;

                void store(Map<TypeDescription, byte[]> map) throws IOException;

                public static class ForJarOutputStream implements Sink {
                    private final JarOutputStream outputStream;

                    public ForJarOutputStream(JarOutputStream jarOutputStream) {
                        this.outputStream = jarOutputStream;
                    }

                    public void store(Map<TypeDescription, byte[]> map) throws IOException {
                        for (Map.Entry next : map.entrySet()) {
                            JarOutputStream jarOutputStream = this.outputStream;
                            jarOutputStream.putNextEntry(new JarEntry(((TypeDescription) next.getKey()).getInternalName() + ".class"));
                            this.outputStream.write((byte[]) next.getValue());
                            this.outputStream.closeEntry();
                        }
                    }

                    /* JADX INFO: finally extract failed */
                    public void retain(Source.Element element) throws IOException {
                        JarEntry jarEntry = (JarEntry) element.resolveAs(JarEntry.class);
                        JarOutputStream jarOutputStream = this.outputStream;
                        if (jarEntry == null) {
                            jarEntry = new JarEntry(element.getName());
                        }
                        jarOutputStream.putNextEntry(jarEntry);
                        InputStream inputStream = element.getInputStream();
                        try {
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int read = inputStream.read(bArr);
                                if (read != -1) {
                                    this.outputStream.write(bArr, 0, read);
                                } else {
                                    inputStream.close();
                                    this.outputStream.closeEntry();
                                    return;
                                }
                            }
                        } catch (Throwable th) {
                            inputStream.close();
                            throw th;
                        }
                    }

                    public void close() throws IOException {
                        this.outputStream.close();
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class InMemory implements Target, Sink {
                private final Map<String, byte[]> storage;

                public void close() {
                }

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.storage.equals(((InMemory) obj).storage);
                }

                public int hashCode() {
                    return 527 + this.storage.hashCode();
                }

                public InMemory() {
                    this(new HashMap());
                }

                public InMemory(Map<String, byte[]> map) {
                    this.storage = map;
                }

                /* JADX INFO: finally extract failed */
                public Sink write(Manifest manifest) throws IOException {
                    if (manifest != null) {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        try {
                            manifest.write(byteArrayOutputStream);
                            byteArrayOutputStream.close();
                            this.storage.put("META-INF/MANIFEST.MF", byteArrayOutputStream.toByteArray());
                        } catch (Throwable th) {
                            byteArrayOutputStream.close();
                            throw th;
                        }
                    }
                    return this;
                }

                public void store(Map<TypeDescription, byte[]> map) {
                    for (Map.Entry next : map.entrySet()) {
                        Map<String, byte[]> map2 = this.storage;
                        map2.put(((TypeDescription) next.getKey()).getInternalName() + ".class", next.getValue());
                    }
                }

                public void retain(Source.Element element) throws IOException {
                    InputStream inputStream;
                    if (!element.getName().endsWith("/")) {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        try {
                            inputStream = element.getInputStream();
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int read = inputStream.read(bArr);
                                if (read != -1) {
                                    byteArrayOutputStream.write(bArr, 0, read);
                                } else {
                                    inputStream.close();
                                    byteArrayOutputStream.close();
                                    this.storage.put(element.getName(), byteArrayOutputStream.toByteArray());
                                    return;
                                }
                            }
                        } catch (Throwable th) {
                            byteArrayOutputStream.close();
                            throw th;
                        }
                    }
                }

                public Map<String, byte[]> getStorage() {
                    return this.storage;
                }

                public Map<String, byte[]> toTypeMap() {
                    HashMap hashMap = new HashMap();
                    for (Map.Entry next : this.storage.entrySet()) {
                        if (((String) next.getKey()).endsWith(".class")) {
                            hashMap.put(((String) next.getKey()).substring(0, ((String) next.getKey()).length() - 6).replace('/', '.'), next.getValue());
                        }
                    }
                    return hashMap;
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForFolder implements Target, Sink {
                protected static final Dispatcher DISPATCHER = ((Dispatcher) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE));
                private final File folder;

                public void close() {
                }

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.folder.equals(((ForFolder) obj).folder);
                }

                public int hashCode() {
                    return 527 + this.folder.hashCode();
                }

                public ForFolder(File file) {
                    this.folder = file;
                }

                public Sink write(Manifest manifest) throws IOException {
                    if (manifest != null) {
                        File file = new File(this.folder, "META-INF/MANIFEST.MF");
                        if (file.getParentFile().isDirectory() || file.getParentFile().mkdirs()) {
                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            try {
                                manifest.write(fileOutputStream);
                            } finally {
                                fileOutputStream.close();
                            }
                        } else {
                            throw new IOException("Could not create directory: " + file.getParent());
                        }
                    }
                    return this;
                }

                public void store(Map<TypeDescription, byte[]> map) throws IOException {
                    for (Map.Entry next : map.entrySet()) {
                        File file = this.folder;
                        File file2 = new File(file, ((TypeDescription) next.getKey()).getInternalName() + ".class");
                        if (file2.getParentFile().isDirectory() || file2.getParentFile().mkdirs()) {
                            FileOutputStream fileOutputStream = new FileOutputStream(file2);
                            try {
                                fileOutputStream.write((byte[]) next.getValue());
                            } finally {
                                fileOutputStream.close();
                            }
                        } else {
                            throw new IOException("Could not create directory: " + file2.getParent());
                        }
                    }
                }

                public void retain(Source.Element element) throws IOException {
                    FileOutputStream fileOutputStream;
                    String name = element.getName();
                    if (!name.endsWith("/")) {
                        File file = new File(this.folder, name);
                        File file2 = (File) element.resolveAs(File.class);
                        if (!file.getCanonicalPath().startsWith(this.folder.getCanonicalPath())) {
                            throw new IllegalArgumentException(file + " is not a subdirectory of " + this.folder);
                        } else if (!file.getParentFile().isDirectory() && !file.getParentFile().mkdirs()) {
                            throw new IOException("Could not create directory: " + file.getParent());
                        } else if (DISPATCHER.isAlive() && file2 != null && !file2.equals(file)) {
                            DISPATCHER.copy(file2, file);
                        } else if (!file.equals(file2)) {
                            InputStream inputStream = element.getInputStream();
                            try {
                                fileOutputStream = new FileOutputStream(file);
                                byte[] bArr = new byte[1024];
                                while (true) {
                                    int read = inputStream.read(bArr);
                                    if (read != -1) {
                                        fileOutputStream.write(bArr, 0, read);
                                    } else {
                                        fileOutputStream.close();
                                        inputStream.close();
                                        return;
                                    }
                                }
                            } catch (Throwable th) {
                                inputStream.close();
                                throw th;
                            }
                        }
                    }
                }

                protected interface Dispatcher {
                    void copy(File file, File file2) throws IOException;

                    boolean isAlive();

                    public enum CreationAction implements PrivilegedAction<Dispatcher> {
                        INSTANCE;

                        public Dispatcher run() {
                            try {
                                Class<?> cls = Class.forName("java.nio.file.Path");
                                Object[] objArr = (Object[]) Array.newInstance(Class.forName("java.nio.file.CopyOption"), 1);
                                objArr[0] = Enum.valueOf(Class.forName("java.nio.file.StandardCopyOption"), "REPLACE_EXISTING");
                                return new ForJava7CapableVm(File.class.getMethod("toPath", new Class[0]), Class.forName("java.nio.file.Files").getMethod("copy", new Class[]{cls, cls, objArr.getClass()}), objArr);
                            } catch (Throwable unused) {
                                return ForLegacyVm.INSTANCE;
                            }
                        }
                    }

                    public enum ForLegacyVm implements Dispatcher {
                        INSTANCE;

                        public boolean isAlive() {
                            return false;
                        }

                        public void copy(File file, File file2) {
                            throw new UnsupportedOperationException("Cannot use NIO2 copy on current VM");
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance
                    public static class ForJava7CapableVm implements Dispatcher {
                        private final Method copy;
                        private final Object[] copyOptions;
                        private final Method toPath;

                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            if (obj == null || getClass() != obj.getClass()) {
                                return false;
                            }
                            ForJava7CapableVm forJava7CapableVm = (ForJava7CapableVm) obj;
                            return this.toPath.equals(forJava7CapableVm.toPath) && this.copy.equals(forJava7CapableVm.copy) && Arrays.equals(this.copyOptions, forJava7CapableVm.copyOptions);
                        }

                        public int hashCode() {
                            return ((((527 + this.toPath.hashCode()) * 31) + this.copy.hashCode()) * 31) + Arrays.hashCode(this.copyOptions);
                        }

                        public boolean isAlive() {
                            return true;
                        }

                        protected ForJava7CapableVm(Method method, Method method2, Object[] objArr) {
                            this.toPath = method;
                            this.copy = method2;
                            this.copyOptions = objArr;
                        }

                        public void copy(File file, File file2) throws IOException {
                            try {
                                this.copy.invoke((Object) null, new Object[]{this.toPath.invoke(file, new Object[0]), this.toPath.invoke(file2, new Object[0]), this.copyOptions});
                            } catch (IllegalAccessException e) {
                                throw new IllegalStateException("Cannot access NIO file copy", e);
                            } catch (InvocationTargetException e2) {
                                Throwable cause = e2.getCause();
                                if (cause instanceof IOException) {
                                    throw ((IOException) cause);
                                }
                                throw new IllegalStateException("Cannot execute NIO file copy", cause);
                            }
                        }
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForJarFile implements Target {
                private final File file;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.file.equals(((ForJarFile) obj).file);
                }

                public int hashCode() {
                    return 527 + this.file.hashCode();
                }

                public ForJarFile(File file2) {
                    this.file = file2;
                }

                public Sink write(Manifest manifest) throws IOException {
                    return manifest == null ? new Sink.ForJarOutputStream(new JarOutputStream(new FileOutputStream(this.file))) : new Sink.ForJarOutputStream(new JarOutputStream(new FileOutputStream(this.file), manifest));
                }
            }
        }

        public static class Summary {
            private final Map<TypeDescription, List<Throwable>> failed;
            private final List<TypeDescription> transformed;
            private final List<String> unresolved;

            public Summary(List<TypeDescription> list, Map<TypeDescription, List<Throwable>> map, List<String> list2) {
                this.transformed = list;
                this.failed = map;
                this.unresolved = list2;
            }

            public List<TypeDescription> getTransformed() {
                return this.transformed;
            }

            public Map<TypeDescription, List<Throwable>> getFailed() {
                return this.failed;
            }

            public List<String> getUnresolved() {
                return this.unresolved;
            }

            public int hashCode() {
                return (((this.transformed.hashCode() * 31) + this.failed.hashCode()) * 31) + this.unresolved.hashCode();
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Summary summary = (Summary) obj;
                if (!this.transformed.equals(summary.transformed) || !this.failed.equals(summary.failed) || !this.unresolved.equals(summary.unresolved)) {
                    return false;
                }
                return true;
            }
        }

        public static abstract class AbstractBase implements Engine {
            public Engine withErrorHandlers(ErrorHandler... errorHandlerArr) {
                return withErrorHandlers((List<? extends ErrorHandler>) Arrays.asList(errorHandlerArr));
            }

            public Summary apply(File file, File file2, Factory... factoryArr) throws IOException {
                return apply(file, file2, (List<? extends Factory>) Arrays.asList(factoryArr));
            }

            public Summary apply(File file, File file2, List<? extends Factory> list) throws IOException {
                return apply(file.isDirectory() ? new Source.ForFolder(file) : new Source.ForJarFile(file), file2.isDirectory() ? new Target.ForFolder(file2) : new Target.ForJarFile(file2), list);
            }

            public Summary apply(Source source, Target target, Factory... factoryArr) throws IOException {
                return apply(source, target, (List<? extends Factory>) Arrays.asList(factoryArr));
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Default extends AbstractBase {
            private final ByteBuddy byteBuddy;
            private final ClassFileLocator classFileLocator;
            private final ErrorHandler errorHandler;
            private final ElementMatcher.Junction<? super TypeDescription> ignoredTypeMatcher;
            private final Listener listener;
            private final PoolStrategy poolStrategy;
            private final TypeStrategy typeStrategy;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Default defaultR = (Default) obj;
                return this.byteBuddy.equals(defaultR.byteBuddy) && this.typeStrategy.equals(defaultR.typeStrategy) && this.poolStrategy.equals(defaultR.poolStrategy) && this.classFileLocator.equals(defaultR.classFileLocator) && this.listener.equals(defaultR.listener) && this.errorHandler.equals(defaultR.errorHandler) && this.ignoredTypeMatcher.equals(defaultR.ignoredTypeMatcher);
            }

            public int hashCode() {
                return ((((((((((((527 + this.byteBuddy.hashCode()) * 31) + this.typeStrategy.hashCode()) * 31) + this.poolStrategy.hashCode()) * 31) + this.classFileLocator.hashCode()) * 31) + this.listener.hashCode()) * 31) + this.errorHandler.hashCode()) * 31) + this.ignoredTypeMatcher.hashCode();
            }

            public Default() {
                this(new ByteBuddy());
            }

            public Default(ByteBuddy byteBuddy2) {
                this(byteBuddy2, TypeStrategy.Default.REBASE);
            }

            /* JADX WARNING: Illegal instructions before constructor call */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            protected Default(net.bytebuddy.ByteBuddy r9, net.bytebuddy.build.Plugin.Engine.TypeStrategy r10) {
                /*
                    r8 = this;
                    net.bytebuddy.build.Plugin$Engine$PoolStrategy$Default r3 = net.bytebuddy.build.Plugin.Engine.PoolStrategy.Default.FAST
                    net.bytebuddy.dynamic.ClassFileLocator$NoOp r4 = net.bytebuddy.dynamic.ClassFileLocator.NoOp.INSTANCE
                    net.bytebuddy.build.Plugin$Engine$Listener$NoOp r5 = net.bytebuddy.build.Plugin.Engine.Listener.NoOp.INSTANCE
                    net.bytebuddy.build.Plugin$Engine$ErrorHandler$Compound r6 = new net.bytebuddy.build.Plugin$Engine$ErrorHandler$Compound
                    r0 = 3
                    net.bytebuddy.build.Plugin$Engine$ErrorHandler[] r0 = new net.bytebuddy.build.Plugin.Engine.ErrorHandler[r0]
                    net.bytebuddy.build.Plugin$Engine$ErrorHandler$Failing r1 = net.bytebuddy.build.Plugin.Engine.ErrorHandler.Failing.FAIL_FAST
                    r2 = 0
                    r0[r2] = r1
                    net.bytebuddy.build.Plugin$Engine$ErrorHandler$Enforcing r1 = net.bytebuddy.build.Plugin.Engine.ErrorHandler.Enforcing.ALL_TYPES_RESOLVED
                    r2 = 1
                    r0[r2] = r1
                    net.bytebuddy.build.Plugin$Engine$ErrorHandler$Enforcing r1 = net.bytebuddy.build.Plugin.Engine.ErrorHandler.Enforcing.NO_LIVE_INITIALIZERS
                    r2 = 2
                    r0[r2] = r1
                    r6.<init>((net.bytebuddy.build.Plugin.Engine.ErrorHandler[]) r0)
                    net.bytebuddy.matcher.ElementMatcher$Junction r7 = net.bytebuddy.matcher.ElementMatchers.none()
                    r0 = r8
                    r1 = r9
                    r2 = r10
                    r0.<init>(r1, r2, r3, r4, r5, r6, r7)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.build.Plugin.Engine.Default.<init>(net.bytebuddy.ByteBuddy, net.bytebuddy.build.Plugin$Engine$TypeStrategy):void");
            }

            protected Default(ByteBuddy byteBuddy2, TypeStrategy typeStrategy2, PoolStrategy poolStrategy2, ClassFileLocator classFileLocator2, Listener listener2, ErrorHandler errorHandler2, ElementMatcher.Junction<? super TypeDescription> junction) {
                this.byteBuddy = byteBuddy2;
                this.typeStrategy = typeStrategy2;
                this.poolStrategy = poolStrategy2;
                this.classFileLocator = classFileLocator2;
                this.listener = listener2;
                this.errorHandler = errorHandler2;
                this.ignoredTypeMatcher = junction;
            }

            public static Engine of(EntryPoint entryPoint, ClassFileVersion classFileVersion, MethodNameTransformer methodNameTransformer) {
                return new Default(entryPoint.byteBuddy(classFileVersion), new TypeStrategy.ForEntryPoint(entryPoint, methodNameTransformer));
            }

            public static void main(String... strArr) throws ClassNotFoundException, IOException {
                if (strArr.length >= 2) {
                    ArrayList arrayList = new ArrayList(strArr.length - 2);
                    for (String cls : Arrays.asList(strArr).subList(2, strArr.length)) {
                        arrayList.add(new Factory.UsingReflection(Class.forName(cls)));
                    }
                    new Default().apply(new File(strArr[0]), new File(strArr[1]), (List<? extends Factory>) arrayList);
                    return;
                }
                throw new IllegalArgumentException("Expected arguments: <source> <target> [<plugin>, ...]");
            }

            public Engine with(ByteBuddy byteBuddy2) {
                return new Default(byteBuddy2, this.typeStrategy, this.poolStrategy, this.classFileLocator, this.listener, this.errorHandler, this.ignoredTypeMatcher);
            }

            public Engine with(TypeStrategy typeStrategy2) {
                return new Default(this.byteBuddy, typeStrategy2, this.poolStrategy, this.classFileLocator, this.listener, this.errorHandler, this.ignoredTypeMatcher);
            }

            public Engine with(PoolStrategy poolStrategy2) {
                return new Default(this.byteBuddy, this.typeStrategy, poolStrategy2, this.classFileLocator, this.listener, this.errorHandler, this.ignoredTypeMatcher);
            }

            public Engine with(ClassFileLocator classFileLocator2) {
                return new Default(this.byteBuddy, this.typeStrategy, this.poolStrategy, new ClassFileLocator.Compound(this.classFileLocator, classFileLocator2), this.listener, this.errorHandler, this.ignoredTypeMatcher);
            }

            public Engine with(Listener listener2) {
                return new Default(this.byteBuddy, this.typeStrategy, this.poolStrategy, this.classFileLocator, new Listener.Compound(this.listener, listener2), this.errorHandler, this.ignoredTypeMatcher);
            }

            public Engine withoutErrorHandlers() {
                return new Default(this.byteBuddy, this.typeStrategy, this.poolStrategy, this.classFileLocator, this.listener, Listener.NoOp.INSTANCE, this.ignoredTypeMatcher);
            }

            public Engine withErrorHandlers(List<? extends ErrorHandler> list) {
                return new Default(this.byteBuddy, this.typeStrategy, this.poolStrategy, this.classFileLocator, this.listener, new ErrorHandler.Compound(list), this.ignoredTypeMatcher);
            }

            public Engine ignore(ElementMatcher<? super TypeDescription> elementMatcher) {
                return new Default(this.byteBuddy, this.typeStrategy, this.poolStrategy, this.classFileLocator, this.listener, this.errorHandler, this.ignoredTypeMatcher.or(elementMatcher));
            }

            public Summary apply(Source source, Target target, List<? extends Factory> list) throws IOException {
                Iterator it;
                TypePool typePool;
                TypeDescription resolve;
                ArrayList arrayList;
                Plugin plugin;
                Default defaultR = this;
                int i = 0;
                int i2 = 1;
                Listener.Compound compound = new Listener.Compound(defaultR.listener, new Listener.ForErrorHandler(defaultR.errorHandler));
                ArrayList arrayList2 = new ArrayList();
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                ArrayList arrayList3 = new ArrayList();
                ArrayList<Plugin> arrayList4 = new ArrayList<>(list.size());
                try {
                    for (Factory make : list) {
                        arrayList4.add(make.make());
                    }
                    Source.Origin read = source.read();
                    try {
                        ClassFileLocator.Compound compound2 = new ClassFileLocator.Compound(read.getClassFileLocator(), defaultR.classFileLocator);
                        TypePool typePool2 = defaultR.poolStrategy.typePool(compound2);
                        Manifest manifest = read.getManifest();
                        compound.onManifest(manifest);
                        Target.Sink write = target.write(manifest);
                        try {
                            Iterator it2 = read.iterator();
                            while (it2.hasNext()) {
                                Source.Element element = (Source.Element) it2.next();
                                String name = element.getName();
                                while (name.startsWith("/")) {
                                    name = name.substring(i2);
                                }
                                if (name.endsWith(".class")) {
                                    String replace = name.substring(i, name.length() - 6).replace('/', '.');
                                    compound.onDiscovery(replace);
                                    TypePool.Resolution describe = typePool2.describe(replace);
                                    if (describe.isResolved()) {
                                        resolve = describe.resolve();
                                        if (!defaultR.ignoredTypeMatcher.matches(resolve)) {
                                            ArrayList arrayList5 = new ArrayList();
                                            ArrayList arrayList6 = new ArrayList();
                                            typePool = typePool2;
                                            arrayList = new ArrayList();
                                            it = it2;
                                            DynamicType.Builder<?> builder = defaultR.typeStrategy.builder(defaultR.byteBuddy, resolve, compound2);
                                            Iterator it3 = arrayList4.iterator();
                                            DynamicType.Builder<?> builder2 = builder;
                                            while (it3.hasNext()) {
                                                Iterator it4 = it3;
                                                plugin = (Plugin) it3.next();
                                                if (plugin.matches(resolve)) {
                                                    builder2 = plugin.apply(builder2, resolve, compound2);
                                                    compound.onTransformation(resolve, plugin);
                                                    arrayList5.add(plugin);
                                                } else {
                                                    compound.onIgnored(resolve, plugin);
                                                    arrayList6.add(plugin);
                                                }
                                                it3 = it4;
                                            }
                                            if (!arrayList.isEmpty()) {
                                                compound.onError(resolve, (List<Throwable>) arrayList);
                                                write.retain(element);
                                                linkedHashMap.put(resolve, arrayList);
                                            } else if (!arrayList5.isEmpty()) {
                                                DynamicType.Unloaded<?> make2 = builder2.make();
                                                compound.onTransformation(resolve, (List<Plugin>) arrayList5);
                                                for (Map.Entry next : make2.getLoadedTypeInitializers().entrySet()) {
                                                    if (((LoadedTypeInitializer) next.getValue()).isAlive()) {
                                                        compound.onLiveInitializer(resolve, (TypeDescription) next.getKey());
                                                    }
                                                }
                                                write.store(make2.getAllTypes());
                                                arrayList2.add(make2.getTypeDescription());
                                            } else {
                                                compound.onIgnored(resolve, (List<Plugin>) arrayList6);
                                                write.retain(element);
                                            }
                                        } else {
                                            typePool = typePool2;
                                            it = it2;
                                            compound.onIgnored(resolve, (List<Plugin>) arrayList4);
                                            write.retain(element);
                                        }
                                        compound.onComplete(resolve);
                                    } else {
                                        typePool = typePool2;
                                        it = it2;
                                        compound.onUnresolved(replace);
                                        write.retain(element);
                                        arrayList3.add(replace);
                                    }
                                } else {
                                    typePool = typePool2;
                                    it = it2;
                                    if (!name.equals("META-INF/MANIFEST.MF")) {
                                        compound.onResource(name);
                                        write.retain(element);
                                    }
                                }
                                i = 0;
                                i2 = 1;
                                defaultR = this;
                                typePool2 = typePool;
                                it2 = it;
                            }
                            if (!linkedHashMap.isEmpty()) {
                                compound.onError(linkedHashMap);
                            }
                            write.close();
                            RuntimeException runtimeException = null;
                            for (Plugin plugin2 : arrayList4) {
                                try {
                                    plugin2.close();
                                } catch (Throwable th) {
                                    try {
                                        compound.onError(plugin2, th);
                                    } catch (RuntimeException e) {
                                        if (runtimeException == null) {
                                            runtimeException = e;
                                        }
                                    }
                                }
                            }
                            if (runtimeException == null) {
                                return new Summary(arrayList2, linkedHashMap, arrayList3);
                            }
                            throw runtimeException;
                        } catch (Throwable th2) {
                            write.close();
                            throw th2;
                        }
                    } finally {
                        read.close();
                    }
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    RuntimeException runtimeException2 = null;
                    for (Plugin plugin3 : arrayList4) {
                        try {
                            plugin3.close();
                        } catch (Throwable th5) {
                            try {
                                compound.onError(plugin3, th5);
                            } catch (RuntimeException e2) {
                                if (runtimeException2 == null) {
                                    runtimeException2 = e2;
                                }
                            }
                        }
                    }
                    throw th4;
                }
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class NoOp implements Plugin, Factory {
        public void close() {
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass();
        }

        public int hashCode() {
            return 17;
        }

        public Plugin make() {
            return this;
        }

        public boolean matches(TypeDescription typeDescription) {
            return false;
        }

        public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassFileLocator classFileLocator) {
            throw new IllegalStateException("Cannot apply non-operational plugin");
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static abstract class ForElementMatcher implements Plugin {
        private final ElementMatcher<? super TypeDescription> matcher;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.matcher.equals(((ForElementMatcher) obj).matcher);
        }

        public int hashCode() {
            return 527 + this.matcher.hashCode();
        }

        protected ForElementMatcher(ElementMatcher<? super TypeDescription> elementMatcher) {
            this.matcher = elementMatcher;
        }

        public boolean matches(TypeDescription typeDescription) {
            return this.matcher.matches(typeDescription);
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class Compound implements Plugin {
        private final List<Plugin> plugins;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.plugins.equals(((Compound) obj).plugins);
        }

        public int hashCode() {
            return 527 + this.plugins.hashCode();
        }

        public Compound(Plugin... pluginArr) {
            this((List<? extends Plugin>) Arrays.asList(pluginArr));
        }

        public Compound(List<? extends Plugin> list) {
            this.plugins = new ArrayList();
            for (Plugin plugin : list) {
                if (plugin instanceof Compound) {
                    this.plugins.addAll(((Compound) plugin).plugins);
                } else if (!(plugin instanceof NoOp)) {
                    this.plugins.add(plugin);
                }
            }
        }

        public boolean matches(TypeDescription typeDescription) {
            for (Plugin matches : this.plugins) {
                if (matches.matches(typeDescription)) {
                    return true;
                }
            }
            return false;
        }

        public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassFileLocator classFileLocator) {
            for (Plugin next : this.plugins) {
                if (next.matches(typeDescription)) {
                    builder = next.apply(builder, typeDescription, classFileLocator);
                }
            }
            return builder;
        }

        public void close() throws IOException {
            for (Plugin close : this.plugins) {
                close.close();
            }
        }
    }
}
