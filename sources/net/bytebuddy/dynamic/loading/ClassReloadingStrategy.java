package net.bytebuddy.dynamic.loading;

import java.io.File;
import java.io.IOException;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ClassInjector;

@HashCodeAndEqualsPlugin.Enhance
public class ClassReloadingStrategy implements ClassLoadingStrategy<ClassLoader> {
    protected static final Dispatcher DISPATCHER = ((Dispatcher) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE));
    private static final String INSTALLER_TYPE = "net.bytebuddy.agent.Installer";
    private static final String INSTRUMENTATION_GETTER = "getInstrumentation";
    private static final Object STATIC_MEMBER = null;
    private final BootstrapInjection bootstrapInjection;
    private final Instrumentation instrumentation;
    private final Map<String, Class<?>> preregisteredTypes;
    private final Strategy strategy;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ClassReloadingStrategy classReloadingStrategy = (ClassReloadingStrategy) obj;
        return this.strategy.equals(classReloadingStrategy.strategy) && this.instrumentation.equals(classReloadingStrategy.instrumentation) && this.bootstrapInjection.equals(classReloadingStrategy.bootstrapInjection) && this.preregisteredTypes.equals(classReloadingStrategy.preregisteredTypes);
    }

    public int hashCode() {
        return ((((((527 + this.instrumentation.hashCode()) * 31) + this.strategy.hashCode()) * 31) + this.bootstrapInjection.hashCode()) * 31) + this.preregisteredTypes.hashCode();
    }

    public ClassReloadingStrategy(Instrumentation instrumentation2, Strategy strategy2) {
        this(instrumentation2, strategy2, BootstrapInjection.Disabled.INSTANCE, Collections.emptyMap());
    }

    protected ClassReloadingStrategy(Instrumentation instrumentation2, Strategy strategy2, BootstrapInjection bootstrapInjection2, Map<String, Class<?>> map) {
        this.instrumentation = instrumentation2;
        this.strategy = strategy2.validate(instrumentation2);
        this.bootstrapInjection = bootstrapInjection2;
        this.preregisteredTypes = map;
    }

    public static ClassReloadingStrategy of(Instrumentation instrumentation2) {
        if (DISPATCHER.isRetransformClassesSupported(instrumentation2)) {
            return new ClassReloadingStrategy(instrumentation2, Strategy.RETRANSFORMATION);
        }
        if (instrumentation2.isRedefineClassesSupported()) {
            return new ClassReloadingStrategy(instrumentation2, Strategy.REDEFINITION);
        }
        throw new IllegalArgumentException("Instrumentation does not support reloading of classes: " + instrumentation2);
    }

    public static ClassReloadingStrategy fromInstalledAgent() {
        try {
            return of((Instrumentation) ClassLoader.getSystemClassLoader().loadClass(INSTALLER_TYPE).getMethod(INSTRUMENTATION_GETTER, new Class[0]).invoke(STATIC_MEMBER, new Object[0]));
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new IllegalStateException("The Byte Buddy agent is not installed or not accessible", e2);
        }
    }

    public static ClassReloadingStrategy fromInstalledAgent(Strategy strategy2) {
        try {
            return new ClassReloadingStrategy((Instrumentation) ClassLoader.getSystemClassLoader().loadClass(INSTALLER_TYPE).getMethod(INSTRUMENTATION_GETTER, new Class[0]).invoke(STATIC_MEMBER, new Object[0]), strategy2);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new IllegalStateException("The Byte Buddy agent is not installed or not accessible", e2);
        }
    }

    public Map<TypeDescription, Class<?>> load(ClassLoader classLoader, Map<TypeDescription, byte[]> map) {
        HashMap hashMap = new HashMap(this.preregisteredTypes);
        for (Class cls : this.instrumentation.getInitiatedClasses(classLoader)) {
            hashMap.put(TypeDescription.ForLoadedType.getName(cls), cls);
        }
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        HashMap hashMap2 = new HashMap();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry next : map.entrySet()) {
            Class cls2 = (Class) hashMap.get(((TypeDescription) next.getKey()).getName());
            if (cls2 != null) {
                concurrentHashMap.put(cls2, new ClassDefinition(cls2, (byte[]) next.getValue()));
                hashMap2.put(next.getKey(), cls2);
            } else {
                linkedHashMap.put(next.getKey(), next.getValue());
            }
        }
        try {
            this.strategy.apply(this.instrumentation, concurrentHashMap);
            if (!linkedHashMap.isEmpty()) {
                hashMap2.putAll((classLoader == null ? this.bootstrapInjection.make(this.instrumentation) : new ClassInjector.UsingReflection(classLoader)).inject(linkedHashMap));
            }
            return hashMap2;
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Could not locate classes for redefinition", e);
        } catch (UnmodifiableClassException e2) {
            throw new IllegalStateException("Cannot redefine specified class", e2);
        }
    }

    public ClassReloadingStrategy reset(Class<?>... clsArr) throws IOException {
        if (clsArr.length == 0) {
            return this;
        }
        return reset(ClassFileLocator.ForClassLoader.of(clsArr[0].getClassLoader()), clsArr);
    }

    public ClassReloadingStrategy reset(ClassFileLocator classFileLocator, Class<?>... clsArr) throws IOException {
        if (clsArr.length > 0) {
            try {
                this.strategy.reset(this.instrumentation, classFileLocator, Arrays.asList(clsArr));
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException("Cannot locate types " + Arrays.toString(clsArr), e);
            } catch (UnmodifiableClassException e2) {
                throw new IllegalStateException("Cannot reset types " + Arrays.toString(clsArr), e2);
            }
        }
        return this;
    }

    public ClassReloadingStrategy enableBootstrapInjection(File file) {
        return new ClassReloadingStrategy(this.instrumentation, this.strategy, new BootstrapInjection.Enabled(file), this.preregisteredTypes);
    }

    public ClassReloadingStrategy preregistered(Class<?>... clsArr) {
        HashMap hashMap = new HashMap(this.preregisteredTypes);
        for (Class<?> cls : clsArr) {
            hashMap.put(TypeDescription.ForLoadedType.getName(cls), cls);
        }
        return new ClassReloadingStrategy(this.instrumentation, this.strategy, this.bootstrapInjection, hashMap);
    }

    protected interface Dispatcher {
        void addTransformer(Instrumentation instrumentation, ClassFileTransformer classFileTransformer, boolean z);

        boolean isModifiableClass(Instrumentation instrumentation, Class<?> cls);

        boolean isRetransformClassesSupported(Instrumentation instrumentation);

        void retransformClasses(Instrumentation instrumentation, Class<?>[] clsArr) throws UnmodifiableClassException;

        public enum CreationAction implements PrivilegedAction<Dispatcher> {
            INSTANCE;

            public Dispatcher run() {
                try {
                    return new ForJava6CapableVm(Instrumentation.class.getMethod("isModifiableClass", new Class[]{Class.class}), Instrumentation.class.getMethod("isRetransformClassesSupported", new Class[0]), Instrumentation.class.getMethod("addTransformer", new Class[]{ClassFileTransformer.class, Boolean.TYPE}), Instrumentation.class.getMethod("retransformClasses", new Class[]{Class[].class}));
                } catch (NoSuchMethodException unused) {
                    return ForLegacyVm.INSTANCE;
                }
            }
        }

        public enum ForLegacyVm implements Dispatcher {
            INSTANCE;

            public boolean isRetransformClassesSupported(Instrumentation instrumentation) {
                return false;
            }

            public boolean isModifiableClass(Instrumentation instrumentation, Class<?> cls) {
                return !cls.isArray() && !cls.isPrimitive();
            }

            public void addTransformer(Instrumentation instrumentation, ClassFileTransformer classFileTransformer, boolean z) {
                if (!z) {
                    instrumentation.addTransformer(classFileTransformer);
                    return;
                }
                throw new UnsupportedOperationException("Cannot apply retransformation on legacy VM");
            }

            public void retransformClasses(Instrumentation instrumentation, Class<?>[] clsArr) {
                throw new IllegalStateException();
            }
        }

        public static class ForJava6CapableVm implements Dispatcher {
            private final Method addTransformer;
            private final Method isModifiableClass;
            private final Method isRetransformClassesSupported;
            private final Method retransformClasses;

            protected ForJava6CapableVm(Method method, Method method2, Method method3, Method method4) {
                this.isModifiableClass = method;
                this.isRetransformClassesSupported = method2;
                this.addTransformer = method3;
                this.retransformClasses = method4;
            }

            public boolean isModifiableClass(Instrumentation instrumentation, Class<?> cls) {
                try {
                    return ((Boolean) this.isModifiableClass.invoke(instrumentation, new Object[]{cls})).booleanValue();
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access java.lang.instrument.Instrumentation#isModifiableClass", e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Error invoking java.lang.instrument.Instrumentation#isModifiableClass", e2.getCause());
                }
            }

            public boolean isRetransformClassesSupported(Instrumentation instrumentation) {
                try {
                    return ((Boolean) this.isRetransformClassesSupported.invoke(instrumentation, new Object[0])).booleanValue();
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access java.lang.instrument.Instrumentation#isModifiableClass", e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Error invoking java.lang.instrument.Instrumentation#isModifiableClass", e2.getCause());
                }
            }

            public void addTransformer(Instrumentation instrumentation, ClassFileTransformer classFileTransformer, boolean z) {
                try {
                    this.addTransformer.invoke(instrumentation, new Object[]{classFileTransformer, Boolean.valueOf(z)});
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access java.lang.instrument.Instrumentation#addTransformer", e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Error invoking java.lang.instrument.Instrumentation#addTransformer", e2.getCause());
                }
            }

            public void retransformClasses(Instrumentation instrumentation, Class<?>[] clsArr) throws UnmodifiableClassException {
                try {
                    this.retransformClasses.invoke(instrumentation, new Object[]{clsArr});
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access java.lang.instrument.Instrumentation#retransformClasses", e);
                } catch (InvocationTargetException e2) {
                    UnmodifiableClassException cause = e2.getCause();
                    if (cause instanceof UnmodifiableClassException) {
                        throw cause;
                    }
                    throw new IllegalStateException("Error invoking java.lang.instrument.Instrumentation#retransformClasses", cause);
                }
            }
        }
    }

    public enum Strategy {
        REDEFINITION(true) {
            /* access modifiers changed from: protected */
            public void apply(Instrumentation instrumentation, Map<Class<?>, ClassDefinition> map) throws UnmodifiableClassException, ClassNotFoundException {
                instrumentation.redefineClasses((ClassDefinition[]) map.values().toArray(new ClassDefinition[map.size()]));
            }

            /* access modifiers changed from: protected */
            public Strategy validate(Instrumentation instrumentation) {
                if (instrumentation.isRedefineClassesSupported()) {
                    return this;
                }
                throw new IllegalArgumentException("Does not support redefinition: " + instrumentation);
            }

            public void reset(Instrumentation instrumentation, ClassFileLocator classFileLocator, List<Class<?>> list) throws IOException, UnmodifiableClassException, ClassNotFoundException {
                HashMap hashMap = new HashMap(list.size());
                for (Class next : list) {
                    hashMap.put(next, new ClassDefinition(next, classFileLocator.locate(TypeDescription.ForLoadedType.getName(next)).resolve()));
                }
                apply(instrumentation, hashMap);
            }
        },
        RETRANSFORMATION(false) {
            /* access modifiers changed from: protected */
            public void apply(Instrumentation instrumentation, Map<Class<?>, ClassDefinition> map) throws UnmodifiableClassException {
                ClassRedefinitionTransformer classRedefinitionTransformer = new ClassRedefinitionTransformer(map);
                synchronized (this) {
                    ClassReloadingStrategy.DISPATCHER.addTransformer(instrumentation, classRedefinitionTransformer, true);
                    try {
                        ClassReloadingStrategy.DISPATCHER.retransformClasses(instrumentation, (Class[]) map.keySet().toArray(new Class[map.size()]));
                    } finally {
                        instrumentation.removeTransformer(classRedefinitionTransformer);
                    }
                }
                classRedefinitionTransformer.assertTransformation();
            }

            /* access modifiers changed from: protected */
            public Strategy validate(Instrumentation instrumentation) {
                if (ClassReloadingStrategy.DISPATCHER.isRetransformClassesSupported(instrumentation)) {
                    return this;
                }
                throw new IllegalArgumentException("Does not support retransformation: " + instrumentation);
            }

            public void reset(Instrumentation instrumentation, ClassFileLocator classFileLocator, List<Class<?>> list) throws UnmodifiableClassException, ClassNotFoundException {
                for (Class next : list) {
                    if (!ClassReloadingStrategy.DISPATCHER.isModifiableClass(instrumentation, next)) {
                        throw new IllegalArgumentException("Cannot modify type: " + next);
                    }
                }
                ClassReloadingStrategy.DISPATCHER.addTransformer(instrumentation, ClassResettingTransformer.INSTANCE, true);
                try {
                    ClassReloadingStrategy.DISPATCHER.retransformClasses(instrumentation, (Class[]) list.toArray(new Class[list.size()]));
                } finally {
                    instrumentation.removeTransformer(ClassResettingTransformer.INSTANCE);
                }
            }
        };
        
        /* access modifiers changed from: private */
        public static final byte[] NO_REDEFINITION = null;
        private static final boolean REDEFINE_CLASSES = true;
        private final boolean redefinition;

        /* access modifiers changed from: protected */
        public abstract void apply(Instrumentation instrumentation, Map<Class<?>, ClassDefinition> map) throws UnmodifiableClassException, ClassNotFoundException;

        public abstract void reset(Instrumentation instrumentation, ClassFileLocator classFileLocator, List<Class<?>> list) throws IOException, UnmodifiableClassException, ClassNotFoundException;

        /* access modifiers changed from: protected */
        public abstract Strategy validate(Instrumentation instrumentation);

        static {
            NO_REDEFINITION = null;
        }

        private Strategy(boolean z) {
            this.redefinition = z;
        }

        public boolean isRedefinition() {
            return this.redefinition;
        }

        protected static class ClassRedefinitionTransformer implements ClassFileTransformer {
            private final Map<Class<?>, ClassDefinition> redefinedClasses;

            protected ClassRedefinitionTransformer(Map<Class<?>, ClassDefinition> map) {
                this.redefinedClasses = map;
            }

            public byte[] transform(ClassLoader classLoader, String str, Class<?> cls, ProtectionDomain protectionDomain, byte[] bArr) {
                if (str == null) {
                    return Strategy.NO_REDEFINITION;
                }
                ClassDefinition remove = this.redefinedClasses.remove(cls);
                if (remove == null) {
                    return Strategy.NO_REDEFINITION;
                }
                return remove.getDefinitionClassFile();
            }

            public void assertTransformation() {
                if (!this.redefinedClasses.isEmpty()) {
                    throw new IllegalStateException("Could not transform: " + this.redefinedClasses.keySet());
                }
            }
        }

        protected enum ClassResettingTransformer implements ClassFileTransformer {
            INSTANCE;

            public byte[] transform(ClassLoader classLoader, String str, Class<?> cls, ProtectionDomain protectionDomain, byte[] bArr) {
                return Strategy.NO_REDEFINITION;
            }
        }
    }

    protected interface BootstrapInjection {
        ClassInjector make(Instrumentation instrumentation);

        public enum Disabled implements BootstrapInjection {
            INSTANCE;

            public ClassInjector make(Instrumentation instrumentation) {
                throw new IllegalStateException("Bootstrap injection is not enabled");
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Enabled implements BootstrapInjection {
            private final File folder;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.folder.equals(((Enabled) obj).folder);
            }

            public int hashCode() {
                return 527 + this.folder.hashCode();
            }

            protected Enabled(File file) {
                this.folder = file;
            }

            public ClassInjector make(Instrumentation instrumentation) {
                return ClassInjector.UsingInstrumentation.of(this.folder, ClassInjector.UsingInstrumentation.Target.BOOTSTRAP, instrumentation);
            }
        }
    }
}
