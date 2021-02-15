package net.bytebuddy.dynamic.loading;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.URLStreamHandler;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.loading.PackageDefinitionStrategy;
import net.bytebuddy.utility.JavaModule;

public class ByteArrayClassLoader extends InjectionClassLoader {
    private static final int FROM_BEGINNING = 0;
    /* access modifiers changed from: private */
    public static final URL NO_URL = null;
    /* access modifiers changed from: private */
    public static final PackageLookupStrategy PACKAGE_LOOKUP_STRATEGY = ((PackageLookupStrategy) AccessController.doPrivileged(PackageLookupStrategy.CreationAction.INSTANCE));
    protected static final SynchronizationStrategy.Initializable SYNCHRONIZATION_STRATEGY = ((SynchronizationStrategy.Initializable) AccessController.doPrivileged(SynchronizationStrategy.CreationAction.INSTANCE));
    private static final Class<?> UNLOADED_TYPE = null;
    public static final String URL_SCHEMA = "bytebuddy";
    protected final AccessControlContext accessControlContext;
    protected final ClassFileTransformer classFileTransformer;
    protected final PackageDefinitionStrategy packageDefinitionStrategy;
    protected final PersistenceHandler persistenceHandler;
    protected final ProtectionDomain protectionDomain;
    protected final ConcurrentMap<String, byte[]> typeDefinitions;

    public ByteArrayClassLoader(ClassLoader classLoader, Map<String, byte[]> map) {
        this(classLoader, true, map);
    }

    public ByteArrayClassLoader(ClassLoader classLoader, boolean z, Map<String, byte[]> map) {
        this(classLoader, z, map, PersistenceHandler.LATENT);
    }

    public ByteArrayClassLoader(ClassLoader classLoader, Map<String, byte[]> map, PersistenceHandler persistenceHandler2) {
        this(classLoader, true, map, persistenceHandler2);
    }

    public ByteArrayClassLoader(ClassLoader classLoader, boolean z, Map<String, byte[]> map, PersistenceHandler persistenceHandler2) {
        this(classLoader, z, map, ClassLoadingStrategy.NO_PROTECTION_DOMAIN, persistenceHandler2, (PackageDefinitionStrategy) PackageDefinitionStrategy.Trivial.INSTANCE);
    }

    public ByteArrayClassLoader(ClassLoader classLoader, Map<String, byte[]> map, ProtectionDomain protectionDomain2, PersistenceHandler persistenceHandler2, PackageDefinitionStrategy packageDefinitionStrategy2) {
        this(classLoader, true, map, protectionDomain2, persistenceHandler2, packageDefinitionStrategy2);
    }

    public ByteArrayClassLoader(ClassLoader classLoader, boolean z, Map<String, byte[]> map, ProtectionDomain protectionDomain2, PersistenceHandler persistenceHandler2, PackageDefinitionStrategy packageDefinitionStrategy2) {
        this(classLoader, z, map, protectionDomain2, persistenceHandler2, packageDefinitionStrategy2, NoOpClassFileTransformer.INSTANCE);
    }

    public ByteArrayClassLoader(ClassLoader classLoader, Map<String, byte[]> map, ProtectionDomain protectionDomain2, PersistenceHandler persistenceHandler2, PackageDefinitionStrategy packageDefinitionStrategy2, ClassFileTransformer classFileTransformer2) {
        this(classLoader, true, map, protectionDomain2, persistenceHandler2, packageDefinitionStrategy2, classFileTransformer2);
    }

    public ByteArrayClassLoader(ClassLoader classLoader, boolean z, Map<String, byte[]> map, ProtectionDomain protectionDomain2, PersistenceHandler persistenceHandler2, PackageDefinitionStrategy packageDefinitionStrategy2, ClassFileTransformer classFileTransformer2) {
        super(classLoader, z);
        this.typeDefinitions = new ConcurrentHashMap(map);
        this.protectionDomain = protectionDomain2;
        this.persistenceHandler = persistenceHandler2;
        this.packageDefinitionStrategy = packageDefinitionStrategy2;
        this.classFileTransformer = classFileTransformer2;
        this.accessControlContext = AccessController.getContext();
    }

    /* access modifiers changed from: private */
    public static Object methodHandle() throws Exception {
        return Class.forName("java.lang.invoke.MethodHandles").getMethod("lookup", new Class[0]).invoke((Object) null, new Object[0]);
    }

    public static Map<TypeDescription, Class<?>> load(ClassLoader classLoader, Map<TypeDescription, byte[]> map) {
        return load(classLoader, map, ClassLoadingStrategy.NO_PROTECTION_DOMAIN, PersistenceHandler.LATENT, PackageDefinitionStrategy.Trivial.INSTANCE, false, true);
    }

    public static Map<TypeDescription, Class<?>> load(ClassLoader classLoader, Map<TypeDescription, byte[]> map, ProtectionDomain protectionDomain2, PersistenceHandler persistenceHandler2, PackageDefinitionStrategy packageDefinitionStrategy2, boolean z, boolean z2) {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : map.entrySet()) {
            hashMap.put(((TypeDescription) next.getKey()).getName(), next.getValue());
        }
        ByteArrayClassLoader byteArrayClassLoader = new ByteArrayClassLoader(classLoader, z2, hashMap, protectionDomain2, persistenceHandler2, packageDefinitionStrategy2, NoOpClassFileTransformer.INSTANCE);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (TypeDescription next2 : map.keySet()) {
            try {
                Class<?> cls = Class.forName(next2.getName(), false, byteArrayClassLoader);
                if (z) {
                    if (cls.getClassLoader() != byteArrayClassLoader) {
                        throw new IllegalStateException("Class already loaded: " + cls);
                    }
                }
                linkedHashMap.put(next2, cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Cannot load class " + next2, e);
            }
        }
        return linkedHashMap;
    }

    /* access modifiers changed from: protected */
    public Map<String, Class<?>> doDefineClasses(Map<String, byte[]> map) throws ClassNotFoundException {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : map.entrySet()) {
            hashMap.put(next.getKey(), this.typeDefinitions.putIfAbsent(next.getKey(), next.getValue()));
        }
        try {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (String next2 : map.keySet()) {
                synchronized (SYNCHRONIZATION_STRATEGY.initialize().getClassLoadingLock(this, next2)) {
                    linkedHashMap.put(next2, loadClass(next2));
                }
            }
            for (Map.Entry entry : hashMap.entrySet()) {
                if (entry.getValue() == null) {
                    this.persistenceHandler.release((String) entry.getKey(), this.typeDefinitions);
                } else {
                    this.typeDefinitions.put(entry.getKey(), entry.getValue());
                }
            }
            return linkedHashMap;
        } catch (Throwable th) {
            for (Map.Entry entry2 : hashMap.entrySet()) {
                if (entry2.getValue() == null) {
                    this.persistenceHandler.release((String) entry2.getKey(), this.typeDefinitions);
                } else {
                    this.typeDefinitions.put(entry2.getKey(), entry2.getValue());
                }
            }
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public Class<?> findClass(String str) throws ClassNotFoundException {
        byte[] lookup = this.persistenceHandler.lookup(str, this.typeDefinitions);
        if (lookup != null) {
            try {
                byte[] transform = this.classFileTransformer.transform(this, str, UNLOADED_TYPE, this.protectionDomain, lookup);
                if (transform != null) {
                    lookup = transform;
                }
                return (Class) AccessController.doPrivileged(new ClassDefinitionAction(str, lookup), this.accessControlContext);
            } catch (IllegalClassFormatException e) {
                throw new IllegalStateException("The class file for " + str + " is not legal", e);
            }
        } else {
            throw new ClassNotFoundException(str);
        }
    }

    /* access modifiers changed from: protected */
    public URL findResource(String str) {
        return this.persistenceHandler.url(str, this.typeDefinitions);
    }

    /* access modifiers changed from: protected */
    public Enumeration<URL> findResources(String str) {
        URL url = this.persistenceHandler.url(str, this.typeDefinitions);
        return url == null ? EmptyEnumeration.INSTANCE : new SingletonEnumeration(url);
    }

    /* access modifiers changed from: private */
    public Package doGetPackage(String str) {
        return getPackage(str);
    }

    protected interface SynchronizationStrategy {

        public enum ForLegacyVm implements SynchronizationStrategy, Initializable {
            INSTANCE;

            public Object getClassLoadingLock(ByteArrayClassLoader byteArrayClassLoader, String str) {
                return byteArrayClassLoader;
            }

            public SynchronizationStrategy initialize() {
                return this;
            }
        }

        public interface Initializable {
            SynchronizationStrategy initialize();
        }

        Object getClassLoadingLock(ByteArrayClassLoader byteArrayClassLoader, String str);

        public enum CreationAction implements PrivilegedAction<Initializable> {
            INSTANCE;

            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: java.lang.Object[]} */
            /* JADX WARNING: Can't wrap try/catch for region: R(3:4|5|6) */
            /* JADX WARNING: Code restructure failed: missing block: B:6:0x008e, code lost:
                return new net.bytebuddy.dynamic.loading.ByteArrayClassLoader.SynchronizationStrategy.ForJava7CapableVm(java.lang.ClassLoader.class.getDeclaredMethod("getClassLoadingLock", new java.lang.Class[]{java.lang.String.class}));
             */
            /* JADX WARNING: Code restructure failed: missing block: B:9:0x0091, code lost:
                return net.bytebuddy.dynamic.loading.ByteArrayClassLoader.SynchronizationStrategy.ForLegacyVm.INSTANCE;
             */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x007d */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public net.bytebuddy.dynamic.loading.ByteArrayClassLoader.SynchronizationStrategy.Initializable run() {
                /*
                    r14 = this;
                    java.lang.String r0 = "getClassLoadingLock"
                    r1 = 0
                    r2 = 1
                    java.lang.String r3 = "java.lang.invoke.MethodType"
                    java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch:{ Exception -> 0x007d }
                    java.lang.String r4 = "java.lang.invoke.MethodHandle"
                    java.lang.Class r4 = java.lang.Class.forName(r4)     // Catch:{ Exception -> 0x007d }
                    net.bytebuddy.dynamic.loading.ByteArrayClassLoader$SynchronizationStrategy$ForJava8CapableVm r5 = new net.bytebuddy.dynamic.loading.ByteArrayClassLoader$SynchronizationStrategy$ForJava8CapableVm     // Catch:{ Exception -> 0x007d }
                    java.lang.String r6 = "java.lang.invoke.MethodHandles$Lookup"
                    java.lang.Class r6 = java.lang.Class.forName(r6)     // Catch:{ Exception -> 0x007d }
                    java.lang.String r7 = "findVirtual"
                    r8 = 3
                    java.lang.Class[] r9 = new java.lang.Class[r8]     // Catch:{ Exception -> 0x007d }
                    java.lang.Class<java.lang.Class> r10 = java.lang.Class.class
                    r9[r1] = r10     // Catch:{ Exception -> 0x007d }
                    java.lang.Class<java.lang.String> r10 = java.lang.String.class
                    r9[r2] = r10     // Catch:{ Exception -> 0x007d }
                    r10 = 2
                    r9[r10] = r3     // Catch:{ Exception -> 0x007d }
                    java.lang.reflect.Method r6 = r6.getMethod(r7, r9)     // Catch:{ Exception -> 0x007d }
                    java.lang.Object r7 = net.bytebuddy.dynamic.loading.ByteArrayClassLoader.methodHandle()     // Catch:{ Exception -> 0x007d }
                    java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ Exception -> 0x007d }
                    java.lang.Class<java.lang.ClassLoader> r9 = java.lang.ClassLoader.class
                    r8[r1] = r9     // Catch:{ Exception -> 0x007d }
                    r8[r2] = r0     // Catch:{ Exception -> 0x007d }
                    java.lang.String r9 = "methodType"
                    java.lang.Class[] r11 = new java.lang.Class[r10]     // Catch:{ Exception -> 0x007d }
                    java.lang.Class<java.lang.Class> r12 = java.lang.Class.class
                    r11[r1] = r12     // Catch:{ Exception -> 0x007d }
                    java.lang.Class<java.lang.Class[]> r12 = java.lang.Class[].class
                    r11[r2] = r12     // Catch:{ Exception -> 0x007d }
                    java.lang.reflect.Method r3 = r3.getMethod(r9, r11)     // Catch:{ Exception -> 0x007d }
                    r9 = 0
                    java.lang.Object[] r11 = new java.lang.Object[r10]     // Catch:{ Exception -> 0x007d }
                    java.lang.Class<java.lang.Object> r12 = java.lang.Object.class
                    r11[r1] = r12     // Catch:{ Exception -> 0x007d }
                    java.lang.Class[] r12 = new java.lang.Class[r2]     // Catch:{ Exception -> 0x007d }
                    java.lang.Class<java.lang.String> r13 = java.lang.String.class
                    r12[r1] = r13     // Catch:{ Exception -> 0x007d }
                    r11[r2] = r12     // Catch:{ Exception -> 0x007d }
                    java.lang.Object r3 = r3.invoke(r9, r11)     // Catch:{ Exception -> 0x007d }
                    r8[r10] = r3     // Catch:{ Exception -> 0x007d }
                    java.lang.Object r3 = r6.invoke(r7, r8)     // Catch:{ Exception -> 0x007d }
                    java.lang.String r6 = "bindTo"
                    java.lang.Class[] r7 = new java.lang.Class[r2]     // Catch:{ Exception -> 0x007d }
                    java.lang.Class<java.lang.Object> r8 = java.lang.Object.class
                    r7[r1] = r8     // Catch:{ Exception -> 0x007d }
                    java.lang.reflect.Method r6 = r4.getMethod(r6, r7)     // Catch:{ Exception -> 0x007d }
                    java.lang.String r7 = "invokeWithArguments"
                    java.lang.Class[] r8 = new java.lang.Class[r2]     // Catch:{ Exception -> 0x007d }
                    java.lang.Class<java.lang.Object[]> r9 = java.lang.Object[].class
                    r8[r1] = r9     // Catch:{ Exception -> 0x007d }
                    java.lang.reflect.Method r4 = r4.getMethod(r7, r8)     // Catch:{ Exception -> 0x007d }
                    r5.<init>(r3, r6, r4)     // Catch:{ Exception -> 0x007d }
                    return r5
                L_0x007d:
                    net.bytebuddy.dynamic.loading.ByteArrayClassLoader$SynchronizationStrategy$ForJava7CapableVm r3 = new net.bytebuddy.dynamic.loading.ByteArrayClassLoader$SynchronizationStrategy$ForJava7CapableVm     // Catch:{ Exception -> 0x008f }
                    java.lang.Class<java.lang.ClassLoader> r4 = java.lang.ClassLoader.class
                    java.lang.Class[] r2 = new java.lang.Class[r2]     // Catch:{ Exception -> 0x008f }
                    java.lang.Class<java.lang.String> r5 = java.lang.String.class
                    r2[r1] = r5     // Catch:{ Exception -> 0x008f }
                    java.lang.reflect.Method r0 = r4.getDeclaredMethod(r0, r2)     // Catch:{ Exception -> 0x008f }
                    r3.<init>(r0)     // Catch:{ Exception -> 0x008f }
                    return r3
                L_0x008f:
                    net.bytebuddy.dynamic.loading.ByteArrayClassLoader$SynchronizationStrategy$ForLegacyVm r0 = net.bytebuddy.dynamic.loading.ByteArrayClassLoader.SynchronizationStrategy.ForLegacyVm.INSTANCE
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.dynamic.loading.ByteArrayClassLoader.SynchronizationStrategy.CreationAction.run():net.bytebuddy.dynamic.loading.ByteArrayClassLoader$SynchronizationStrategy$Initializable");
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForJava7CapableVm implements SynchronizationStrategy, Initializable {
            private final Method method;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.method.equals(((ForJava7CapableVm) obj).method);
            }

            public int hashCode() {
                return 527 + this.method.hashCode();
            }

            protected ForJava7CapableVm(Method method2) {
                this.method = method2;
            }

            public Object getClassLoadingLock(ByteArrayClassLoader byteArrayClassLoader, String str) {
                try {
                    return this.method.invoke(byteArrayClassLoader, new Object[]{str});
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access class loading lock for " + str + " on " + byteArrayClassLoader, e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Error when getting " + str + " on " + byteArrayClassLoader, e2);
                }
            }

            public SynchronizationStrategy initialize() {
                try {
                    this.method.setAccessible(true);
                    return this;
                } catch (Exception unused) {
                    return ForLegacyVm.INSTANCE;
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForJava8CapableVm implements SynchronizationStrategy, Initializable {
            private final Method bindTo;
            private final Method invokeWithArguments;
            private final Object methodHandle;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForJava8CapableVm forJava8CapableVm = (ForJava8CapableVm) obj;
                return this.methodHandle.equals(forJava8CapableVm.methodHandle) && this.bindTo.equals(forJava8CapableVm.bindTo) && this.invokeWithArguments.equals(forJava8CapableVm.invokeWithArguments);
            }

            public int hashCode() {
                return ((((527 + this.methodHandle.hashCode()) * 31) + this.bindTo.hashCode()) * 31) + this.invokeWithArguments.hashCode();
            }

            public SynchronizationStrategy initialize() {
                return this;
            }

            protected ForJava8CapableVm(Object obj, Method method, Method method2) {
                this.methodHandle = obj;
                this.bindTo = method;
                this.invokeWithArguments = method2;
            }

            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.Object[]} */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Object getClassLoadingLock(net.bytebuddy.dynamic.loading.ByteArrayClassLoader r8, java.lang.String r9) {
                /*
                    r7 = this;
                    java.lang.String r0 = " on "
                    java.lang.reflect.Method r1 = r7.invokeWithArguments     // Catch:{ IllegalAccessException -> 0x003d, InvocationTargetException -> 0x001f }
                    java.lang.reflect.Method r2 = r7.bindTo     // Catch:{ IllegalAccessException -> 0x003d, InvocationTargetException -> 0x001f }
                    java.lang.Object r3 = r7.methodHandle     // Catch:{ IllegalAccessException -> 0x003d, InvocationTargetException -> 0x001f }
                    r4 = 1
                    java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ IllegalAccessException -> 0x003d, InvocationTargetException -> 0x001f }
                    r6 = 0
                    r5[r6] = r8     // Catch:{ IllegalAccessException -> 0x003d, InvocationTargetException -> 0x001f }
                    java.lang.Object r2 = r2.invoke(r3, r5)     // Catch:{ IllegalAccessException -> 0x003d, InvocationTargetException -> 0x001f }
                    java.lang.Object[] r3 = new java.lang.Object[r4]     // Catch:{ IllegalAccessException -> 0x003d, InvocationTargetException -> 0x001f }
                    java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ IllegalAccessException -> 0x003d, InvocationTargetException -> 0x001f }
                    r4[r6] = r9     // Catch:{ IllegalAccessException -> 0x003d, InvocationTargetException -> 0x001f }
                    r3[r6] = r4     // Catch:{ IllegalAccessException -> 0x003d, InvocationTargetException -> 0x001f }
                    java.lang.Object r8 = r1.invoke(r2, r3)     // Catch:{ IllegalAccessException -> 0x003d, InvocationTargetException -> 0x001f }
                    return r8
                L_0x001f:
                    r1 = move-exception
                    java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder
                    r3.<init>()
                    java.lang.String r4 = "Error when getting "
                    r3.append(r4)
                    r3.append(r9)
                    r3.append(r0)
                    r3.append(r8)
                    java.lang.String r8 = r3.toString()
                    r2.<init>(r8, r1)
                    throw r2
                L_0x003d:
                    r1 = move-exception
                    java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder
                    r3.<init>()
                    java.lang.String r4 = "Cannot access class loading lock for "
                    r3.append(r4)
                    r3.append(r9)
                    r3.append(r0)
                    r3.append(r8)
                    java.lang.String r8 = r3.toString()
                    r2.<init>(r8, r1)
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.dynamic.loading.ByteArrayClassLoader.SynchronizationStrategy.ForJava8CapableVm.getClassLoadingLock(net.bytebuddy.dynamic.loading.ByteArrayClassLoader, java.lang.String):java.lang.Object");
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
    protected class ClassDefinitionAction implements PrivilegedAction<Class<?>> {
        private final byte[] binaryRepresentation;
        private final String name;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ClassDefinitionAction classDefinitionAction = (ClassDefinitionAction) obj;
            return this.name.equals(classDefinitionAction.name) && Arrays.equals(this.binaryRepresentation, classDefinitionAction.binaryRepresentation) && ByteArrayClassLoader.this.equals(ByteArrayClassLoader.this);
        }

        public int hashCode() {
            return ((((527 + this.name.hashCode()) * 31) + Arrays.hashCode(this.binaryRepresentation)) * 31) + ByteArrayClassLoader.this.hashCode();
        }

        protected ClassDefinitionAction(String str, byte[] bArr) {
            this.name = str;
            this.binaryRepresentation = bArr;
        }

        public Class<?> run() {
            int lastIndexOf = this.name.lastIndexOf(46);
            if (lastIndexOf != -1) {
                String substring = this.name.substring(0, lastIndexOf);
                PackageDefinitionStrategy.Definition define = ByteArrayClassLoader.this.packageDefinitionStrategy.define(ByteArrayClassLoader.this, substring, this.name);
                if (define.isDefined()) {
                    Package apply = ByteArrayClassLoader.PACKAGE_LOOKUP_STRATEGY.apply(ByteArrayClassLoader.this, substring);
                    if (apply == null) {
                        Package unused = ByteArrayClassLoader.this.definePackage(substring, define.getSpecificationTitle(), define.getSpecificationVersion(), define.getSpecificationVendor(), define.getImplementationTitle(), define.getImplementationVersion(), define.getImplementationVendor(), define.getSealBase());
                    } else if (!define.isCompatibleTo(apply)) {
                        throw new SecurityException("Sealing violation for package " + substring);
                    }
                }
            }
            ByteArrayClassLoader byteArrayClassLoader = ByteArrayClassLoader.this;
            String str = this.name;
            byte[] bArr = this.binaryRepresentation;
            return byteArrayClassLoader.defineClass(str, bArr, 0, bArr.length, byteArrayClassLoader.protectionDomain);
        }
    }

    protected interface PackageLookupStrategy {
        Package apply(ByteArrayClassLoader byteArrayClassLoader, String str);

        public enum CreationAction implements PrivilegedAction<PackageLookupStrategy> {
            INSTANCE;

            public PackageLookupStrategy run() {
                if (!JavaModule.isSupported()) {
                    return ForLegacyVm.INSTANCE;
                }
                try {
                    return new ForJava9CapableVm(ClassLoader.class.getMethod("getDefinedPackage", new Class[]{String.class}));
                } catch (Exception unused) {
                    return ForLegacyVm.INSTANCE;
                }
            }
        }

        public enum ForLegacyVm implements PackageLookupStrategy {
            INSTANCE;

            public Package apply(ByteArrayClassLoader byteArrayClassLoader, String str) {
                return byteArrayClassLoader.doGetPackage(str);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForJava9CapableVm implements PackageLookupStrategy {
            private final Method getDefinedPackage;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.getDefinedPackage.equals(((ForJava9CapableVm) obj).getDefinedPackage);
            }

            public int hashCode() {
                return 527 + this.getDefinedPackage.hashCode();
            }

            protected ForJava9CapableVm(Method method) {
                this.getDefinedPackage = method;
            }

            public Package apply(ByteArrayClassLoader byteArrayClassLoader, String str) {
                try {
                    return (Package) this.getDefinedPackage.invoke(byteArrayClassLoader, new Object[]{str});
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access " + this.getDefinedPackage, e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Cannot invoke " + this.getDefinedPackage, e2.getCause());
                }
            }
        }
    }

    public enum PersistenceHandler {
        MANIFEST(true) {
            /* access modifiers changed from: protected */
            public void release(String str, ConcurrentMap<String, byte[]> concurrentMap) {
            }

            /* access modifiers changed from: protected */
            public byte[] lookup(String str, ConcurrentMap<String, byte[]> concurrentMap) {
                return (byte[]) concurrentMap.get(str);
            }

            /* access modifiers changed from: protected */
            public URL url(String str, ConcurrentMap<String, byte[]> concurrentMap) {
                if (!str.endsWith(".class")) {
                    return ByteArrayClassLoader.NO_URL;
                }
                if (str.startsWith("/")) {
                    str = str.substring(1);
                }
                byte[] bArr = (byte[]) concurrentMap.get(str.replace('/', '.').substring(0, str.length() - 6));
                if (bArr == null) {
                    return ByteArrayClassLoader.NO_URL;
                }
                return (URL) AccessController.doPrivileged(new UrlDefinitionAction(str, bArr));
            }
        },
        LATENT(false) {
            /* access modifiers changed from: protected */
            public byte[] lookup(String str, ConcurrentMap<String, byte[]> concurrentMap) {
                return (byte[]) concurrentMap.remove(str);
            }

            /* access modifiers changed from: protected */
            public URL url(String str, ConcurrentMap<String, byte[]> concurrentMap) {
                return ByteArrayClassLoader.NO_URL;
            }

            /* access modifiers changed from: protected */
            public void release(String str, ConcurrentMap<String, byte[]> concurrentMap) {
                concurrentMap.remove(str);
            }
        };
        
        private static final String CLASS_FILE_SUFFIX = ".class";
        private final boolean manifest;

        /* access modifiers changed from: protected */
        public abstract byte[] lookup(String str, ConcurrentMap<String, byte[]> concurrentMap);

        /* access modifiers changed from: protected */
        public abstract void release(String str, ConcurrentMap<String, byte[]> concurrentMap);

        /* access modifiers changed from: protected */
        public abstract URL url(String str, ConcurrentMap<String, byte[]> concurrentMap);

        private PersistenceHandler(boolean z) {
            this.manifest = z;
        }

        public boolean isManifest() {
            return this.manifest;
        }

        @HashCodeAndEqualsPlugin.Enhance
        protected static class UrlDefinitionAction implements PrivilegedAction<URL> {
            private static final String ENCODING = "UTF-8";
            private static final String NO_FILE = "";
            private static final int NO_PORT = -1;
            private final byte[] binaryRepresentation;
            private final String typeName;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                UrlDefinitionAction urlDefinitionAction = (UrlDefinitionAction) obj;
                return this.typeName.equals(urlDefinitionAction.typeName) && Arrays.equals(this.binaryRepresentation, urlDefinitionAction.binaryRepresentation);
            }

            public int hashCode() {
                return ((527 + this.typeName.hashCode()) * 31) + Arrays.hashCode(this.binaryRepresentation);
            }

            protected UrlDefinitionAction(String str, byte[] bArr) {
                this.typeName = str;
                this.binaryRepresentation = bArr;
            }

            public URL run() {
                try {
                    return new URL(ByteArrayClassLoader.URL_SCHEMA, URLEncoder.encode(this.typeName.replace('.', '/'), "UTF-8"), -1, "", new ByteArrayUrlStreamHandler(this.binaryRepresentation));
                } catch (MalformedURLException e) {
                    throw new IllegalStateException("Cannot create URL for " + this.typeName, e);
                } catch (UnsupportedEncodingException e2) {
                    throw new IllegalStateException("Could not find encoding: UTF-8", e2);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class ByteArrayUrlStreamHandler extends URLStreamHandler {
                private final byte[] binaryRepresentation;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && Arrays.equals(this.binaryRepresentation, ((ByteArrayUrlStreamHandler) obj).binaryRepresentation);
                }

                public int hashCode() {
                    return 527 + Arrays.hashCode(this.binaryRepresentation);
                }

                protected ByteArrayUrlStreamHandler(byte[] bArr) {
                    this.binaryRepresentation = bArr;
                }

                /* access modifiers changed from: protected */
                public URLConnection openConnection(URL url) {
                    return new ByteArrayUrlConnection(url, new ByteArrayInputStream(this.binaryRepresentation));
                }

                protected static class ByteArrayUrlConnection extends URLConnection {
                    private final InputStream inputStream;

                    protected ByteArrayUrlConnection(URL url, InputStream inputStream2) {
                        super(url);
                        this.inputStream = inputStream2;
                    }

                    public void connect() {
                        this.connected = true;
                    }

                    public InputStream getInputStream() {
                        connect();
                        return this.inputStream;
                    }
                }
            }
        }
    }

    public static class ChildFirst extends ByteArrayClassLoader {
        private static final String CLASS_FILE_SUFFIX = ".class";

        public ChildFirst(ClassLoader classLoader, Map<String, byte[]> map) {
            super(classLoader, map);
        }

        public ChildFirst(ClassLoader classLoader, boolean z, Map<String, byte[]> map) {
            super(classLoader, z, map);
        }

        public ChildFirst(ClassLoader classLoader, Map<String, byte[]> map, PersistenceHandler persistenceHandler) {
            super(classLoader, map, persistenceHandler);
        }

        public ChildFirst(ClassLoader classLoader, boolean z, Map<String, byte[]> map, PersistenceHandler persistenceHandler) {
            super(classLoader, z, map, persistenceHandler);
        }

        public ChildFirst(ClassLoader classLoader, Map<String, byte[]> map, ProtectionDomain protectionDomain, PersistenceHandler persistenceHandler, PackageDefinitionStrategy packageDefinitionStrategy) {
            super(classLoader, map, protectionDomain, persistenceHandler, packageDefinitionStrategy);
        }

        public ChildFirst(ClassLoader classLoader, boolean z, Map<String, byte[]> map, ProtectionDomain protectionDomain, PersistenceHandler persistenceHandler, PackageDefinitionStrategy packageDefinitionStrategy) {
            super(classLoader, z, map, protectionDomain, persistenceHandler, packageDefinitionStrategy);
        }

        public ChildFirst(ClassLoader classLoader, Map<String, byte[]> map, ProtectionDomain protectionDomain, PersistenceHandler persistenceHandler, PackageDefinitionStrategy packageDefinitionStrategy, ClassFileTransformer classFileTransformer) {
            super(classLoader, map, protectionDomain, persistenceHandler, packageDefinitionStrategy, classFileTransformer);
        }

        public ChildFirst(ClassLoader classLoader, boolean z, Map<String, byte[]> map, ProtectionDomain protectionDomain, PersistenceHandler persistenceHandler, PackageDefinitionStrategy packageDefinitionStrategy, ClassFileTransformer classFileTransformer) {
            super(classLoader, z, map, protectionDomain, persistenceHandler, packageDefinitionStrategy, classFileTransformer);
        }

        public static Map<TypeDescription, Class<?>> load(ClassLoader classLoader, Map<TypeDescription, byte[]> map) {
            return load(classLoader, map, ClassLoadingStrategy.NO_PROTECTION_DOMAIN, PersistenceHandler.LATENT, PackageDefinitionStrategy.Trivial.INSTANCE, false, true);
        }

        public static Map<TypeDescription, Class<?>> load(ClassLoader classLoader, Map<TypeDescription, byte[]> map, ProtectionDomain protectionDomain, PersistenceHandler persistenceHandler, PackageDefinitionStrategy packageDefinitionStrategy, boolean z, boolean z2) {
            HashMap hashMap = new HashMap();
            for (Map.Entry next : map.entrySet()) {
                hashMap.put(((TypeDescription) next.getKey()).getName(), next.getValue());
            }
            ChildFirst childFirst = new ChildFirst(classLoader, z2, hashMap, protectionDomain, persistenceHandler, packageDefinitionStrategy, NoOpClassFileTransformer.INSTANCE);
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (TypeDescription next2 : map.keySet()) {
                try {
                    Class<?> cls = Class.forName(next2.getName(), false, childFirst);
                    if (z) {
                        if (cls.getClassLoader() != childFirst) {
                            throw new IllegalStateException("Class already loaded: " + cls);
                        }
                    }
                    linkedHashMap.put(next2, cls);
                } catch (ClassNotFoundException e) {
                    throw new IllegalStateException("Cannot load class " + next2, e);
                }
            }
            return linkedHashMap;
        }

        /* access modifiers changed from: protected */
        public Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
            synchronized (SYNCHRONIZATION_STRATEGY.initialize().getClassLoadingLock(this, str)) {
                Class<?> findLoadedClass = findLoadedClass(str);
                if (findLoadedClass != null) {
                    return findLoadedClass;
                }
                try {
                    Class<?> findClass = findClass(str);
                    if (z) {
                        resolveClass(findClass);
                    }
                    return findClass;
                } catch (ClassNotFoundException unused) {
                    return ByteArrayClassLoader.super.loadClass(str, z);
                }
            }
        }

        public URL getResource(String str) {
            URL url = this.persistenceHandler.url(str, this.typeDefinitions);
            return (url != null || isShadowed(str)) ? url : ByteArrayClassLoader.super.getResource(str);
        }

        public Enumeration<URL> getResources(String str) throws IOException {
            URL url = this.persistenceHandler.url(str, this.typeDefinitions);
            if (url == null) {
                return ByteArrayClassLoader.super.getResources(str);
            }
            return new PrependingEnumeration(url, ByteArrayClassLoader.super.getResources(str));
        }

        /* JADX WARNING: Code restructure failed: missing block: B:17:0x003e, code lost:
            return r1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean isShadowed(java.lang.String r4) {
            /*
                r3 = this;
                net.bytebuddy.dynamic.loading.ByteArrayClassLoader$PersistenceHandler r0 = r3.persistenceHandler
                boolean r0 = r0.isManifest()
                r1 = 0
                if (r0 != 0) goto L_0x0042
                java.lang.String r0 = ".class"
                boolean r0 = r4.endsWith(r0)
                if (r0 != 0) goto L_0x0012
                goto L_0x0042
            L_0x0012:
                monitor-enter(r3)
                r0 = 47
                r2 = 46
                java.lang.String r0 = r4.replace(r0, r2)     // Catch:{ all -> 0x003f }
                int r4 = r4.length()     // Catch:{ all -> 0x003f }
                int r4 = r4 + -6
                java.lang.String r4 = r0.substring(r1, r4)     // Catch:{ all -> 0x003f }
                java.util.concurrent.ConcurrentMap r0 = r3.typeDefinitions     // Catch:{ all -> 0x003f }
                boolean r0 = r0.containsKey(r4)     // Catch:{ all -> 0x003f }
                r2 = 1
                if (r0 == 0) goto L_0x0030
                monitor-exit(r3)     // Catch:{ all -> 0x003f }
                return r2
            L_0x0030:
                java.lang.Class r4 = r3.findLoadedClass(r4)     // Catch:{ all -> 0x003f }
                if (r4 == 0) goto L_0x003d
                java.lang.ClassLoader r4 = r4.getClassLoader()     // Catch:{ all -> 0x003f }
                if (r4 != r3) goto L_0x003d
                r1 = 1
            L_0x003d:
                monitor-exit(r3)     // Catch:{ all -> 0x003f }
                return r1
            L_0x003f:
                r4 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x003f }
                throw r4
            L_0x0042:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.dynamic.loading.ByteArrayClassLoader.ChildFirst.isShadowed(java.lang.String):boolean");
        }

        protected static class PrependingEnumeration implements Enumeration<URL> {
            private final Enumeration<URL> enumeration;
            private URL nextElement;

            protected PrependingEnumeration(URL url, Enumeration<URL> enumeration2) {
                this.nextElement = url;
                this.enumeration = enumeration2;
            }

            public boolean hasMoreElements() {
                return this.nextElement != null && this.enumeration.hasMoreElements();
            }

            public URL nextElement() {
                if (this.nextElement == null || !this.enumeration.hasMoreElements()) {
                    throw new NoSuchElementException();
                }
                try {
                    return this.nextElement;
                } finally {
                    this.nextElement = this.enumeration.nextElement();
                }
            }
        }
    }

    protected enum EmptyEnumeration implements Enumeration<URL> {
        INSTANCE;

        public boolean hasMoreElements() {
            return false;
        }

        public URL nextElement() {
            throw new NoSuchElementException();
        }
    }

    protected static class SingletonEnumeration implements Enumeration<URL> {
        private URL element;

        protected SingletonEnumeration(URL url) {
            this.element = url;
        }

        public boolean hasMoreElements() {
            return this.element != null;
        }

        public URL nextElement() {
            URL url = this.element;
            if (url != null) {
                this.element = null;
                return url;
            }
            throw new NoSuchElementException();
        }
    }
}
