package net.bytebuddy.dynamic.loading;

import java.io.File;
import java.lang.ClassLoader;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.Map;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.loading.ByteArrayClassLoader;
import net.bytebuddy.dynamic.loading.ClassInjector;
import net.bytebuddy.dynamic.loading.PackageDefinitionStrategy;

public interface ClassLoadingStrategy<T extends ClassLoader> {
    public static final ClassLoader BOOTSTRAP_LOADER = null;
    public static final ProtectionDomain NO_PROTECTION_DOMAIN = null;

    public interface Configurable<S extends ClassLoader> extends ClassLoadingStrategy<S> {
        Configurable<S> allowExistingTypes();

        Configurable<S> opened();

        Configurable<S> with(ProtectionDomain protectionDomain);

        Configurable<S> with(PackageDefinitionStrategy packageDefinitionStrategy);
    }

    Map<TypeDescription, Class<?>> load(T t, Map<TypeDescription, byte[]> map);

    public enum Default implements Configurable<ClassLoader> {
        WRAPPER(new WrappingDispatcher(ByteArrayClassLoader.PersistenceHandler.LATENT, false)),
        WRAPPER_PERSISTENT(new WrappingDispatcher(ByteArrayClassLoader.PersistenceHandler.MANIFEST, false)),
        CHILD_FIRST(new WrappingDispatcher(ByteArrayClassLoader.PersistenceHandler.LATENT, true)),
        CHILD_FIRST_PERSISTENT(new WrappingDispatcher(ByteArrayClassLoader.PersistenceHandler.MANIFEST, true)),
        INJECTION(new InjectionDispatcher());
        
        private static final boolean DEFAULT_FORBID_EXISTING = true;
        private final Configurable<ClassLoader> dispatcher;

        private Default(Configurable<ClassLoader> configurable) {
            this.dispatcher = configurable;
        }

        public Map<TypeDescription, Class<?>> load(ClassLoader classLoader, Map<TypeDescription, byte[]> map) {
            return this.dispatcher.load(classLoader, map);
        }

        public Configurable<ClassLoader> with(ProtectionDomain protectionDomain) {
            return this.dispatcher.with(protectionDomain);
        }

        public Configurable<ClassLoader> with(PackageDefinitionStrategy packageDefinitionStrategy) {
            return this.dispatcher.with(packageDefinitionStrategy);
        }

        public Configurable<ClassLoader> allowExistingTypes() {
            return this.dispatcher.allowExistingTypes();
        }

        public Configurable<ClassLoader> opened() {
            return this.dispatcher.opened();
        }

        @HashCodeAndEqualsPlugin.Enhance
        protected static class InjectionDispatcher implements Configurable<ClassLoader> {
            private final boolean forbidExisting;
            private final PackageDefinitionStrategy packageDefinitionStrategy;
            @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
            private final ProtectionDomain protectionDomain;

            /* JADX WARNING: Code restructure failed: missing block: B:18:0x002b, code lost:
                if (r2 != null) goto L_0x002d;
             */
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
                    boolean r2 = r4.forbidExisting
                    net.bytebuddy.dynamic.loading.ClassLoadingStrategy$Default$InjectionDispatcher r5 = (net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Default.InjectionDispatcher) r5
                    boolean r3 = r5.forbidExisting
                    if (r2 == r3) goto L_0x001c
                    return r1
                L_0x001c:
                    java.security.ProtectionDomain r2 = r4.protectionDomain
                    java.security.ProtectionDomain r3 = r5.protectionDomain
                    if (r3 == 0) goto L_0x002b
                    if (r2 == 0) goto L_0x002d
                    boolean r2 = r2.equals(r3)
                    if (r2 != 0) goto L_0x002e
                    return r1
                L_0x002b:
                    if (r2 == 0) goto L_0x002e
                L_0x002d:
                    return r1
                L_0x002e:
                    net.bytebuddy.dynamic.loading.PackageDefinitionStrategy r2 = r4.packageDefinitionStrategy
                    net.bytebuddy.dynamic.loading.PackageDefinitionStrategy r5 = r5.packageDefinitionStrategy
                    boolean r5 = r2.equals(r5)
                    if (r5 != 0) goto L_0x0039
                    return r1
                L_0x0039:
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Default.InjectionDispatcher.equals(java.lang.Object):boolean");
            }

            public int hashCode() {
                ProtectionDomain protectionDomain2 = this.protectionDomain;
                int i = 527;
                if (protectionDomain2 != null) {
                    i = 527 + protectionDomain2.hashCode();
                }
                return (((i * 31) + this.packageDefinitionStrategy.hashCode()) * 31) + (this.forbidExisting ? 1 : 0);
            }

            public Configurable<ClassLoader> opened() {
                return this;
            }

            protected InjectionDispatcher() {
                this(NO_PROTECTION_DOMAIN, PackageDefinitionStrategy.NoOp.INSTANCE, true);
            }

            private InjectionDispatcher(ProtectionDomain protectionDomain2, PackageDefinitionStrategy packageDefinitionStrategy2, boolean z) {
                this.protectionDomain = protectionDomain2;
                this.packageDefinitionStrategy = packageDefinitionStrategy2;
                this.forbidExisting = z;
            }

            public Map<TypeDescription, Class<?>> load(ClassLoader classLoader, Map<TypeDescription, byte[]> map) {
                return new ClassInjector.UsingReflection(classLoader, this.protectionDomain, this.packageDefinitionStrategy, this.forbidExisting).inject(map);
            }

            public Configurable<ClassLoader> with(ProtectionDomain protectionDomain2) {
                return new InjectionDispatcher(protectionDomain2, this.packageDefinitionStrategy, this.forbidExisting);
            }

            public Configurable<ClassLoader> with(PackageDefinitionStrategy packageDefinitionStrategy2) {
                return new InjectionDispatcher(this.protectionDomain, packageDefinitionStrategy2, this.forbidExisting);
            }

            public Configurable<ClassLoader> allowExistingTypes() {
                return new InjectionDispatcher(this.protectionDomain, this.packageDefinitionStrategy, false);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        protected static class WrappingDispatcher implements Configurable<ClassLoader> {
            private static final boolean CHILD_FIRST = true;
            private static final boolean PARENT_FIRST = false;
            private final boolean childFirst;
            private final boolean forbidExisting;
            private final PackageDefinitionStrategy packageDefinitionStrategy;
            private final ByteArrayClassLoader.PersistenceHandler persistenceHandler;
            @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
            private final ProtectionDomain protectionDomain;
            private final boolean sealed;

            /* JADX WARNING: Code restructure failed: missing block: B:27:0x0044, code lost:
                if (r2 != null) goto L_0x0046;
             */
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
                    boolean r2 = r4.childFirst
                    net.bytebuddy.dynamic.loading.ClassLoadingStrategy$Default$WrappingDispatcher r5 = (net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Default.WrappingDispatcher) r5
                    boolean r3 = r5.childFirst
                    if (r2 == r3) goto L_0x001c
                    return r1
                L_0x001c:
                    boolean r2 = r4.forbidExisting
                    boolean r3 = r5.forbidExisting
                    if (r2 == r3) goto L_0x0023
                    return r1
                L_0x0023:
                    boolean r2 = r4.sealed
                    boolean r3 = r5.sealed
                    if (r2 == r3) goto L_0x002a
                    return r1
                L_0x002a:
                    net.bytebuddy.dynamic.loading.ByteArrayClassLoader$PersistenceHandler r2 = r4.persistenceHandler
                    net.bytebuddy.dynamic.loading.ByteArrayClassLoader$PersistenceHandler r3 = r5.persistenceHandler
                    boolean r2 = r2.equals(r3)
                    if (r2 != 0) goto L_0x0035
                    return r1
                L_0x0035:
                    java.security.ProtectionDomain r2 = r4.protectionDomain
                    java.security.ProtectionDomain r3 = r5.protectionDomain
                    if (r3 == 0) goto L_0x0044
                    if (r2 == 0) goto L_0x0046
                    boolean r2 = r2.equals(r3)
                    if (r2 != 0) goto L_0x0047
                    return r1
                L_0x0044:
                    if (r2 == 0) goto L_0x0047
                L_0x0046:
                    return r1
                L_0x0047:
                    net.bytebuddy.dynamic.loading.PackageDefinitionStrategy r2 = r4.packageDefinitionStrategy
                    net.bytebuddy.dynamic.loading.PackageDefinitionStrategy r5 = r5.packageDefinitionStrategy
                    boolean r5 = r2.equals(r5)
                    if (r5 != 0) goto L_0x0052
                    return r1
                L_0x0052:
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Default.WrappingDispatcher.equals(java.lang.Object):boolean");
            }

            public int hashCode() {
                ProtectionDomain protectionDomain2 = this.protectionDomain;
                int i = 527;
                if (protectionDomain2 != null) {
                    i = 527 + protectionDomain2.hashCode();
                }
                return (((((((((i * 31) + this.persistenceHandler.hashCode()) * 31) + this.packageDefinitionStrategy.hashCode()) * 31) + (this.childFirst ? 1 : 0)) * 31) + (this.forbidExisting ? 1 : 0)) * 31) + (this.sealed ? 1 : 0);
            }

            protected WrappingDispatcher(ByteArrayClassLoader.PersistenceHandler persistenceHandler2, boolean z) {
                this(NO_PROTECTION_DOMAIN, PackageDefinitionStrategy.Trivial.INSTANCE, persistenceHandler2, z, true, true);
            }

            private WrappingDispatcher(ProtectionDomain protectionDomain2, PackageDefinitionStrategy packageDefinitionStrategy2, ByteArrayClassLoader.PersistenceHandler persistenceHandler2, boolean z, boolean z2, boolean z3) {
                this.protectionDomain = protectionDomain2;
                this.packageDefinitionStrategy = packageDefinitionStrategy2;
                this.persistenceHandler = persistenceHandler2;
                this.childFirst = z;
                this.forbidExisting = z2;
                this.sealed = z3;
            }

            public Map<TypeDescription, Class<?>> load(ClassLoader classLoader, Map<TypeDescription, byte[]> map) {
                if (this.childFirst) {
                    return ByteArrayClassLoader.ChildFirst.load(classLoader, map, this.protectionDomain, this.persistenceHandler, this.packageDefinitionStrategy, this.forbidExisting, this.sealed);
                }
                return ByteArrayClassLoader.load(classLoader, map, this.protectionDomain, this.persistenceHandler, this.packageDefinitionStrategy, this.forbidExisting, this.sealed);
            }

            public Configurable<ClassLoader> with(ProtectionDomain protectionDomain2) {
                return new WrappingDispatcher(protectionDomain2, this.packageDefinitionStrategy, this.persistenceHandler, this.childFirst, this.forbidExisting, this.sealed);
            }

            public Configurable<ClassLoader> with(PackageDefinitionStrategy packageDefinitionStrategy2) {
                return new WrappingDispatcher(this.protectionDomain, packageDefinitionStrategy2, this.persistenceHandler, this.childFirst, this.forbidExisting, this.sealed);
            }

            public Configurable<ClassLoader> allowExistingTypes() {
                return new WrappingDispatcher(this.protectionDomain, this.packageDefinitionStrategy, this.persistenceHandler, this.childFirst, false, this.sealed);
            }

            public Configurable<ClassLoader> opened() {
                return new WrappingDispatcher(this.protectionDomain, this.packageDefinitionStrategy, this.persistenceHandler, this.childFirst, this.forbidExisting, false);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class UsingLookup implements ClassLoadingStrategy<ClassLoader> {
        private final ClassInjector classInjector;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.classInjector.equals(((UsingLookup) obj).classInjector);
        }

        public int hashCode() {
            return 527 + this.classInjector.hashCode();
        }

        protected UsingLookup(ClassInjector classInjector2) {
            this.classInjector = classInjector2;
        }

        public static ClassLoadingStrategy<ClassLoader> of(Object obj) {
            return new UsingLookup(ClassInjector.UsingLookup.of(obj));
        }

        public Map<TypeDescription, Class<?>> load(ClassLoader classLoader, Map<TypeDescription, byte[]> map) {
            return this.classInjector.inject(map);
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class ForBootstrapInjection implements ClassLoadingStrategy<ClassLoader> {
        private final File folder;
        private final Instrumentation instrumentation;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ForBootstrapInjection forBootstrapInjection = (ForBootstrapInjection) obj;
            return this.instrumentation.equals(forBootstrapInjection.instrumentation) && this.folder.equals(forBootstrapInjection.folder);
        }

        public int hashCode() {
            return ((527 + this.instrumentation.hashCode()) * 31) + this.folder.hashCode();
        }

        public ForBootstrapInjection(Instrumentation instrumentation2, File file) {
            this.instrumentation = instrumentation2;
            this.folder = file;
        }

        public Map<TypeDescription, Class<?>> load(ClassLoader classLoader, Map<TypeDescription, byte[]> map) {
            return (classLoader == null ? ClassInjector.UsingInstrumentation.of(this.folder, ClassInjector.UsingInstrumentation.Target.BOOTSTRAP, this.instrumentation) : new ClassInjector.UsingReflection(classLoader)).inject(map);
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class ForUnsafeInjection implements ClassLoadingStrategy<ClassLoader> {
        @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
        private final ProtectionDomain protectionDomain;

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
                java.security.ProtectionDomain r2 = r4.protectionDomain
                net.bytebuddy.dynamic.loading.ClassLoadingStrategy$ForUnsafeInjection r5 = (net.bytebuddy.dynamic.loading.ClassLoadingStrategy.ForUnsafeInjection) r5
                java.security.ProtectionDomain r5 = r5.protectionDomain
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
            throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.dynamic.loading.ClassLoadingStrategy.ForUnsafeInjection.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            ProtectionDomain protectionDomain2 = this.protectionDomain;
            if (protectionDomain2 != null) {
                return 527 + protectionDomain2.hashCode();
            }
            return 527;
        }

        public ForUnsafeInjection() {
            this(NO_PROTECTION_DOMAIN);
        }

        public ForUnsafeInjection(ProtectionDomain protectionDomain2) {
            this.protectionDomain = protectionDomain2;
        }

        public Map<TypeDescription, Class<?>> load(ClassLoader classLoader, Map<TypeDescription, byte[]> map) {
            return new ClassInjector.UsingUnsafe(classLoader, this.protectionDomain).inject(map);
        }
    }
}
