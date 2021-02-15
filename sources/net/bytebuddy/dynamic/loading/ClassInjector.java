package net.bytebuddy.dynamic.loading;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ReflectPermission;
import java.lang.reflect.Type;
import java.net.URL;
import java.security.AccessController;
import java.security.Permission;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.MemberRemoval;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.loading.PackageDefinitionStrategy;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;
import net.bytebuddy.utility.JavaType;
import net.bytebuddy.utility.RandomString;

public interface ClassInjector {
    public static final boolean ALLOW_EXISTING_TYPES = false;
    public static final Permission SUPPRESS_ACCESS_CHECKS = new ReflectPermission("suppressAccessChecks");

    Map<TypeDescription, Class<?>> inject(Map<? extends TypeDescription, byte[]> map);

    Map<String, Class<?>> injectRaw(Map<? extends String, byte[]> map);

    boolean isAlive();

    public static abstract class AbstractBase implements ClassInjector {
        public Map<TypeDescription, Class<?>> inject(Map<? extends TypeDescription, byte[]> map) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry next : map.entrySet()) {
                linkedHashMap.put(((TypeDescription) next.getKey()).getName(), next.getValue());
            }
            Map<String, Class<?>> injectRaw = injectRaw(linkedHashMap);
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            for (TypeDescription typeDescription : map.keySet()) {
                linkedHashMap2.put(typeDescription, injectRaw.get(typeDescription.getName()));
            }
            return linkedHashMap2;
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class UsingReflection extends AbstractBase {
        private static final Dispatcher.Initializable DISPATCHER = ((Dispatcher.Initializable) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE));
        private final ClassLoader classLoader;
        private final boolean forbidExisting;
        private final PackageDefinitionStrategy packageDefinitionStrategy;
        @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
        private final ProtectionDomain protectionDomain;

        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0036, code lost:
            if (r2 != null) goto L_0x0038;
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
                net.bytebuddy.dynamic.loading.ClassInjector$UsingReflection r5 = (net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection) r5
                boolean r3 = r5.forbidExisting
                if (r2 == r3) goto L_0x001c
                return r1
            L_0x001c:
                java.lang.ClassLoader r2 = r4.classLoader
                java.lang.ClassLoader r3 = r5.classLoader
                boolean r2 = r2.equals(r3)
                if (r2 != 0) goto L_0x0027
                return r1
            L_0x0027:
                java.security.ProtectionDomain r2 = r4.protectionDomain
                java.security.ProtectionDomain r3 = r5.protectionDomain
                if (r3 == 0) goto L_0x0036
                if (r2 == 0) goto L_0x0038
                boolean r2 = r2.equals(r3)
                if (r2 != 0) goto L_0x0039
                return r1
            L_0x0036:
                if (r2 == 0) goto L_0x0039
            L_0x0038:
                return r1
            L_0x0039:
                net.bytebuddy.dynamic.loading.PackageDefinitionStrategy r2 = r4.packageDefinitionStrategy
                net.bytebuddy.dynamic.loading.PackageDefinitionStrategy r5 = r5.packageDefinitionStrategy
                boolean r5 = r2.equals(r5)
                if (r5 != 0) goto L_0x0044
                return r1
            L_0x0044:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            int hashCode = (527 + this.classLoader.hashCode()) * 31;
            ProtectionDomain protectionDomain2 = this.protectionDomain;
            if (protectionDomain2 != null) {
                hashCode += protectionDomain2.hashCode();
            }
            return (((hashCode * 31) + this.packageDefinitionStrategy.hashCode()) * 31) + (this.forbidExisting ? 1 : 0);
        }

        public UsingReflection(ClassLoader classLoader2) {
            this(classLoader2, ClassLoadingStrategy.NO_PROTECTION_DOMAIN);
        }

        public UsingReflection(ClassLoader classLoader2, ProtectionDomain protectionDomain2) {
            this(classLoader2, protectionDomain2, PackageDefinitionStrategy.Trivial.INSTANCE, false);
        }

        public UsingReflection(ClassLoader classLoader2, ProtectionDomain protectionDomain2, PackageDefinitionStrategy packageDefinitionStrategy2, boolean z) {
            if (classLoader2 != null) {
                this.classLoader = classLoader2;
                this.protectionDomain = protectionDomain2;
                this.packageDefinitionStrategy = packageDefinitionStrategy2;
                this.forbidExisting = z;
                return;
            }
            throw new IllegalArgumentException("Cannot inject classes into the bootstrap class loader");
        }

        public boolean isAlive() {
            return isAvailable();
        }

        public Map<String, Class<?>> injectRaw(Map<? extends String, byte[]> map) {
            Dispatcher initialize = DISPATCHER.initialize();
            HashMap hashMap = new HashMap();
            for (Map.Entry next : map.entrySet()) {
                synchronized (initialize.getClassLoadingLock(this.classLoader, (String) next.getKey())) {
                    Class<?> findClass = initialize.findClass(this.classLoader, (String) next.getKey());
                    if (findClass == null) {
                        int lastIndexOf = ((String) next.getKey()).lastIndexOf(46);
                        if (lastIndexOf != -1) {
                            String substring = ((String) next.getKey()).substring(0, lastIndexOf);
                            PackageDefinitionStrategy.Definition define = this.packageDefinitionStrategy.define(this.classLoader, substring, (String) next.getKey());
                            if (define.isDefined()) {
                                Package packageR = initialize.getPackage(this.classLoader, substring);
                                if (packageR == null) {
                                    initialize.definePackage(this.classLoader, substring, define.getSpecificationTitle(), define.getSpecificationVersion(), define.getSpecificationVendor(), define.getImplementationTitle(), define.getImplementationVersion(), define.getImplementationVendor(), define.getSealBase());
                                } else if (!define.isCompatibleTo(packageR)) {
                                    throw new SecurityException("Sealing violation for package " + substring);
                                }
                            }
                        }
                        findClass = initialize.defineClass(this.classLoader, (String) next.getKey(), (byte[]) next.getValue(), this.protectionDomain);
                    } else if (this.forbidExisting) {
                        throw new IllegalStateException("Cannot inject already loaded type: " + findClass);
                    }
                    hashMap.put(next.getKey(), findClass);
                }
            }
            return hashMap;
        }

        public static boolean isAvailable() {
            return DISPATCHER.isAvailable();
        }

        public static ClassInjector ofSystemClassLoader() {
            return new UsingReflection(ClassLoader.getSystemClassLoader());
        }

        protected interface Dispatcher {
            public static final Class<?> UNDEFINED = null;

            Class<?> defineClass(ClassLoader classLoader, String str, byte[] bArr, ProtectionDomain protectionDomain);

            Package definePackage(ClassLoader classLoader, String str, String str2, String str3, String str4, String str5, String str6, String str7, URL url);

            Class<?> findClass(ClassLoader classLoader, String str);

            Object getClassLoadingLock(ClassLoader classLoader, String str);

            Package getPackage(ClassLoader classLoader, String str);

            public interface Initializable {
                Dispatcher initialize();

                boolean isAvailable();

                @HashCodeAndEqualsPlugin.Enhance
                public static class Unavailable implements Dispatcher, Initializable {
                    private final String message;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.message.equals(((Unavailable) obj).message);
                    }

                    public Object getClassLoadingLock(ClassLoader classLoader, String str) {
                        return classLoader;
                    }

                    public int hashCode() {
                        return 527 + this.message.hashCode();
                    }

                    public Dispatcher initialize() {
                        return this;
                    }

                    public boolean isAvailable() {
                        return false;
                    }

                    protected Unavailable(String str) {
                        this.message = str;
                    }

                    public Class<?> findClass(ClassLoader classLoader, String str) {
                        try {
                            return classLoader.loadClass(str);
                        } catch (ClassNotFoundException unused) {
                            return UNDEFINED;
                        }
                    }

                    public Class<?> defineClass(ClassLoader classLoader, String str, byte[] bArr, ProtectionDomain protectionDomain) {
                        throw new UnsupportedOperationException("Cannot define class using reflection: " + this.message);
                    }

                    public Package getPackage(ClassLoader classLoader, String str) {
                        throw new UnsupportedOperationException("Cannot get package using reflection: " + this.message);
                    }

                    public Package definePackage(ClassLoader classLoader, String str, String str2, String str3, String str4, String str5, String str6, String str7, URL url) {
                        throw new UnsupportedOperationException("Cannot define package using injection: " + this.message);
                    }
                }
            }

            public enum CreationAction implements PrivilegedAction<Initializable> {
                INSTANCE;

                public Initializable run() {
                    try {
                        if (!JavaModule.isSupported()) {
                            return Direct.make();
                        }
                        if (UsingUnsafe.isAvailable()) {
                            return UsingUnsafeInjection.make();
                        }
                        return UsingUnsafeOverride.make();
                    } catch (InvocationTargetException e) {
                        return new Initializable.Unavailable(e.getCause().getMessage());
                    } catch (Exception e2) {
                        return new Initializable.Unavailable(e2.getMessage());
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static abstract class Direct implements Dispatcher, Initializable {
                protected final Method defineClass;
                protected final Method definePackage;
                protected final Method findLoadedClass;
                protected final Method getPackage;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Direct direct = (Direct) obj;
                    return this.findLoadedClass.equals(direct.findLoadedClass) && this.defineClass.equals(direct.defineClass) && this.getPackage.equals(direct.getPackage) && this.definePackage.equals(direct.definePackage);
                }

                public int hashCode() {
                    return ((((((527 + this.findLoadedClass.hashCode()) * 31) + this.defineClass.hashCode()) * 31) + this.getPackage.hashCode()) * 31) + this.definePackage.hashCode();
                }

                public boolean isAvailable() {
                    return true;
                }

                protected Direct(Method method, Method method2, Method method3, Method method4) {
                    this.findLoadedClass = method;
                    this.defineClass = method2;
                    this.getPackage = method3;
                    this.definePackage = method4;
                }

                protected static Initializable make() throws Exception {
                    Method method;
                    if (JavaModule.isSupported()) {
                        try {
                            method = ClassLoader.class.getMethod("getDefinedPackage", new Class[]{String.class});
                        } catch (NoSuchMethodException unused) {
                            method = ClassLoader.class.getDeclaredMethod("getPackage", new Class[]{String.class});
                            method.setAccessible(true);
                        }
                    } else {
                        method = ClassLoader.class.getDeclaredMethod("getPackage", new Class[]{String.class});
                        method.setAccessible(true);
                    }
                    Method declaredMethod = ClassLoader.class.getDeclaredMethod("findLoadedClass", new Class[]{String.class});
                    declaredMethod.setAccessible(true);
                    Method declaredMethod2 = ClassLoader.class.getDeclaredMethod("defineClass", new Class[]{String.class, byte[].class, Integer.TYPE, Integer.TYPE, ProtectionDomain.class});
                    declaredMethod2.setAccessible(true);
                    Method declaredMethod3 = ClassLoader.class.getDeclaredMethod("definePackage", new Class[]{String.class, String.class, String.class, String.class, String.class, String.class, String.class, URL.class});
                    declaredMethod3.setAccessible(true);
                    try {
                        Method declaredMethod4 = ClassLoader.class.getDeclaredMethod("getClassLoadingLock", new Class[]{String.class});
                        declaredMethod4.setAccessible(true);
                        return new ForJava7CapableVm(declaredMethod, declaredMethod2, method, declaredMethod3, declaredMethod4);
                    } catch (NoSuchMethodException unused2) {
                        return new ForLegacyVm(declaredMethod, declaredMethod2, method, declaredMethod3);
                    }
                }

                public Dispatcher initialize() {
                    SecurityManager securityManager = System.getSecurityManager();
                    if (securityManager != null) {
                        try {
                            securityManager.checkPermission(ClassInjector.SUPPRESS_ACCESS_CHECKS);
                        } catch (Exception e) {
                            return new Unavailable(e.getMessage());
                        }
                    }
                    return this;
                }

                public Class<?> findClass(ClassLoader classLoader, String str) {
                    try {
                        return (Class) this.findLoadedClass.invoke(classLoader, new Object[]{str});
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Could not access java.lang.ClassLoader#findClass", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.ClassLoader#findClass", e2.getCause());
                    }
                }

                public Class<?> defineClass(ClassLoader classLoader, String str, byte[] bArr, ProtectionDomain protectionDomain) {
                    try {
                        return (Class) this.defineClass.invoke(classLoader, new Object[]{str, bArr, 0, Integer.valueOf(bArr.length), protectionDomain});
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Could not access java.lang.ClassLoader#defineClass", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.ClassLoader#defineClass", e2.getCause());
                    }
                }

                public Package getPackage(ClassLoader classLoader, String str) {
                    try {
                        return (Package) this.getPackage.invoke(classLoader, new Object[]{str});
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Could not access java.lang.ClassLoader#getPackage", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.ClassLoader#getPackage", e2.getCause());
                    }
                }

                public Package definePackage(ClassLoader classLoader, String str, String str2, String str3, String str4, String str5, String str6, String str7, URL url) {
                    try {
                        return (Package) this.definePackage.invoke(classLoader, new Object[]{str, str2, str3, str4, str5, str6, str7, url});
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Could not access java.lang.ClassLoader#definePackage", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.ClassLoader#definePackage", e2.getCause());
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                protected static class ForJava7CapableVm extends Direct {
                    private final Method getClassLoadingLock;

                    public boolean equals(Object obj) {
                        if (!super.equals(obj)) {
                            return false;
                        }
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.getClassLoadingLock.equals(((ForJava7CapableVm) obj).getClassLoadingLock);
                    }

                    public int hashCode() {
                        return (super.hashCode() * 31) + this.getClassLoadingLock.hashCode();
                    }

                    protected ForJava7CapableVm(Method method, Method method2, Method method3, Method method4, Method method5) {
                        super(method, method2, method3, method4);
                        this.getClassLoadingLock = method5;
                    }

                    public Object getClassLoadingLock(ClassLoader classLoader, String str) {
                        try {
                            return this.getClassLoadingLock.invoke(classLoader, new Object[]{str});
                        } catch (IllegalAccessException e) {
                            throw new IllegalStateException("Could not access java.lang.ClassLoader#getClassLoadingLock", e);
                        } catch (InvocationTargetException e2) {
                            throw new IllegalStateException("Error invoking java.lang.ClassLoader#getClassLoadingLock", e2.getCause());
                        }
                    }
                }

                protected static class ForLegacyVm extends Direct {
                    public Object getClassLoadingLock(ClassLoader classLoader, String str) {
                        return classLoader;
                    }

                    protected ForLegacyVm(Method method, Method method2, Method method3, Method method4) {
                        super(method, method2, method3, method4);
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class UsingUnsafeInjection implements Dispatcher, Initializable {
                private final Object accessor;
                private final Method defineClass;
                private final Method definePackage;
                private final Method findLoadedClass;
                private final Method getClassLoadingLock;
                private final Method getPackage;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    UsingUnsafeInjection usingUnsafeInjection = (UsingUnsafeInjection) obj;
                    return this.accessor.equals(usingUnsafeInjection.accessor) && this.findLoadedClass.equals(usingUnsafeInjection.findLoadedClass) && this.defineClass.equals(usingUnsafeInjection.defineClass) && this.getPackage.equals(usingUnsafeInjection.getPackage) && this.definePackage.equals(usingUnsafeInjection.definePackage) && this.getClassLoadingLock.equals(usingUnsafeInjection.getClassLoadingLock);
                }

                public int hashCode() {
                    return ((((((((((527 + this.accessor.hashCode()) * 31) + this.findLoadedClass.hashCode()) * 31) + this.defineClass.hashCode()) * 31) + this.getPackage.hashCode()) * 31) + this.definePackage.hashCode()) * 31) + this.getClassLoadingLock.hashCode();
                }

                public boolean isAvailable() {
                    return true;
                }

                protected UsingUnsafeInjection(Object obj, Method method, Method method2, Method method3, Method method4, Method method5) {
                    this.accessor = obj;
                    this.findLoadedClass = method;
                    this.defineClass = method2;
                    this.getPackage = method3;
                    this.definePackage = method4;
                    this.getClassLoadingLock = method5;
                }

                protected static Initializable make() throws Exception {
                    Method method;
                    DynamicType.Builder.MethodDefinition.ReceiverTypeDefinition<T> receiverTypeDefinition;
                    Class<byte[]> cls = byte[].class;
                    if (Boolean.getBoolean(UsingUnsafe.SAFE_PROPERTY)) {
                        return new Initializable.Unavailable("Use of Unsafe was disabled by system property");
                    }
                    Class<?> cls2 = Class.forName("sun.misc.Unsafe");
                    Field declaredField = cls2.getDeclaredField("theUnsafe");
                    declaredField.setAccessible(true);
                    Object obj = declaredField.get((Object) null);
                    if (JavaModule.isSupported()) {
                        try {
                            method = ClassLoader.class.getDeclaredMethod("getDefinedPackage", new Class[]{String.class});
                        } catch (NoSuchMethodException unused) {
                            method = ClassLoader.class.getDeclaredMethod("getPackage", new Class[]{String.class});
                        }
                    } else {
                        method = ClassLoader.class.getDeclaredMethod("getPackage", new Class[]{String.class});
                    }
                    method.setAccessible(true);
                    DynamicType.Builder<Object> subclass = new ByteBuddy().with(TypeValidation.DISABLED).subclass(Object.class, (ConstructorStrategy) ConstructorStrategy.Default.NO_CONSTRUCTORS);
                    ModifierContributor.ForMethod[] forMethodArr = {Visibility.PUBLIC};
                    DynamicType.Builder.MethodDefinition.ExceptionDefinition<T> withParameters = subclass.name(ClassLoader.class.getName() + "$ByteBuddyAccessor$" + RandomString.make()).defineMethod("findLoadedClass", (Type) Class.class, Visibility.PUBLIC).withParameters(ClassLoader.class, String.class).intercept(MethodCall.invoke(ClassLoader.class.getDeclaredMethod("findLoadedClass", new Class[]{String.class})).onArgument(0).withArgument(1)).defineMethod("defineClass", (Type) Class.class, forMethodArr).withParameters(ClassLoader.class, String.class, cls, Integer.TYPE, Integer.TYPE, ProtectionDomain.class);
                    Class[] clsArr = {String.class, cls, Integer.TYPE, Integer.TYPE, ProtectionDomain.class};
                    ModifierContributor.ForMethod[] forMethodArr2 = {Visibility.PUBLIC};
                    DynamicType.Builder.MethodDefinition.ReceiverTypeDefinition<T> intercept = withParameters.intercept(MethodCall.invoke(ClassLoader.class.getDeclaredMethod("defineClass", clsArr)).onArgument(0).withArgument(1, 2, 3, 4, 5)).defineMethod("getPackage", (Type) Package.class, Visibility.PUBLIC).withParameters(ClassLoader.class, String.class).intercept(MethodCall.invoke(method).onArgument(0).withArgument(1)).defineMethod("definePackage", (Type) Package.class, forMethodArr2).withParameters(ClassLoader.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, URL.class).intercept(MethodCall.invoke(ClassLoader.class.getDeclaredMethod("definePackage", new Class[]{String.class, String.class, String.class, String.class, String.class, String.class, String.class, URL.class})).onArgument(0).withArgument(1, 2, 3, 4, 5, 6, 7, 8));
                    Type type = Object.class;
                    try {
                        receiverTypeDefinition = intercept.defineMethod("getClassLoadingLock", type, Visibility.PUBLIC).withParameters(ClassLoader.class, String.class).intercept(MethodCall.invoke(ClassLoader.class.getDeclaredMethod("getClassLoadingLock", new Class[]{String.class})).onArgument(0).withArgument(1));
                    } catch (NoSuchMethodException unused2) {
                        receiverTypeDefinition = intercept.defineMethod("getClassLoadingLock", (Type) Object.class, Visibility.PUBLIC).withParameters(ClassLoader.class, String.class).intercept(FixedValue.argument(0));
                    }
                    Class<? extends T> loaded = receiverTypeDefinition.make().load(ClassLoadingStrategy.BOOTSTRAP_LOADER, new ClassLoadingStrategy.ForUnsafeInjection()).getLoaded();
                    return new UsingUnsafeInjection(cls2.getMethod("allocateInstance", new Class[]{Class.class}).invoke(obj, new Object[]{loaded}), loaded.getMethod("findLoadedClass", new Class[]{ClassLoader.class, String.class}), loaded.getMethod("defineClass", new Class[]{ClassLoader.class, String.class, cls, Integer.TYPE, Integer.TYPE, ProtectionDomain.class}), loaded.getMethod("getPackage", new Class[]{ClassLoader.class, String.class}), loaded.getMethod("definePackage", new Class[]{ClassLoader.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, URL.class}), loaded.getMethod("getClassLoadingLock", new Class[]{ClassLoader.class, String.class}));
                }

                public Dispatcher initialize() {
                    SecurityManager securityManager = System.getSecurityManager();
                    if (securityManager != null) {
                        try {
                            securityManager.checkPermission(ClassInjector.SUPPRESS_ACCESS_CHECKS);
                        } catch (Exception e) {
                            return new Unavailable(e.getMessage());
                        }
                    }
                    return this;
                }

                public Object getClassLoadingLock(ClassLoader classLoader, String str) {
                    try {
                        return this.getClassLoadingLock.invoke(this.accessor, new Object[]{classLoader, str});
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Could not access (accessor)::getClassLoadingLock", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking (accessor)::getClassLoadingLock", e2.getCause());
                    }
                }

                public Class<?> findClass(ClassLoader classLoader, String str) {
                    try {
                        return (Class) this.findLoadedClass.invoke(this.accessor, new Object[]{classLoader, str});
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Could not access (accessor)::findLoadedClass", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking (accessor)::findLoadedClass", e2.getCause());
                    }
                }

                public Class<?> defineClass(ClassLoader classLoader, String str, byte[] bArr, ProtectionDomain protectionDomain) {
                    try {
                        return (Class) this.defineClass.invoke(this.accessor, new Object[]{classLoader, str, bArr, 0, Integer.valueOf(bArr.length), protectionDomain});
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Could not access (accessor)::defineClass", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking (accessor)::defineClass", e2.getCause());
                    }
                }

                public Package getPackage(ClassLoader classLoader, String str) {
                    try {
                        return (Package) this.getPackage.invoke(this.accessor, new Object[]{classLoader, str});
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Could not access (accessor)::getPackage", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking (accessor)::getPackage", e2.getCause());
                    }
                }

                public Package definePackage(ClassLoader classLoader, String str, String str2, String str3, String str4, String str5, String str6, String str7, URL url) {
                    try {
                        return (Package) this.definePackage.invoke(this.accessor, new Object[]{classLoader, str, str2, str3, str4, str5, str6, str7, url});
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Could not access (accessor)::definePackage", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking (accessor)::definePackage", e2.getCause());
                    }
                }
            }

            public static abstract class UsingUnsafeOverride implements Dispatcher, Initializable {
                protected final Method defineClass;
                protected final Method definePackage;
                protected final Method findLoadedClass;
                protected final Method getPackage;

                public boolean isAvailable() {
                    return true;
                }

                protected UsingUnsafeOverride(Method method, Method method2, Method method3, Method method4) {
                    this.findLoadedClass = method;
                    this.defineClass = method2;
                    this.getPackage = method3;
                    this.definePackage = method4;
                }

                protected static Initializable make() throws Exception {
                    Field field;
                    Method method;
                    Method method2;
                    if (Boolean.getBoolean(UsingUnsafe.SAFE_PROPERTY)) {
                        return new Initializable.Unavailable("Use of Unsafe was disabled by system property");
                    }
                    Class<?> cls = Class.forName("sun.misc.Unsafe");
                    Field declaredField = cls.getDeclaredField("theUnsafe");
                    declaredField.setAccessible(true);
                    Object obj = declaredField.get((Object) null);
                    try {
                        field = AccessibleObject.class.getDeclaredField("override");
                    } catch (NoSuchFieldException unused) {
                        DynamicType.Builder<AccessibleObject> redefine = new ByteBuddy().redefine(AccessibleObject.class);
                        field = redefine.name("net.bytebuddy.mirror." + AccessibleObject.class.getSimpleName()).visit(new MemberRemoval().stripInvokables(ElementMatchers.any())).make().load(AccessibleObject.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER).getLoaded().getDeclaredField("override");
                    }
                    long longValue = ((Long) cls.getMethod("objectFieldOffset", new Class[]{Field.class}).invoke(obj, new Object[]{field})).longValue();
                    Method method3 = cls.getMethod("putBoolean", new Class[]{Object.class, Long.TYPE, Boolean.TYPE});
                    if (JavaModule.isSupported()) {
                        try {
                            method = ClassLoader.class.getMethod("getDefinedPackage", new Class[]{String.class});
                        } catch (NoSuchMethodException unused2) {
                            method = ClassLoader.class.getDeclaredMethod("getPackage", new Class[]{String.class});
                            method3.invoke(obj, new Object[]{method, Long.valueOf(longValue), true});
                        }
                    } else {
                        method = ClassLoader.class.getDeclaredMethod("getPackage", new Class[]{String.class});
                        method3.invoke(obj, new Object[]{method, Long.valueOf(longValue), true});
                    }
                    Method declaredMethod = ClassLoader.class.getDeclaredMethod("findLoadedClass", new Class[]{String.class});
                    Method declaredMethod2 = ClassLoader.class.getDeclaredMethod("defineClass", new Class[]{String.class, byte[].class, Integer.TYPE, Integer.TYPE, ProtectionDomain.class});
                    Method declaredMethod3 = ClassLoader.class.getDeclaredMethod("definePackage", new Class[]{String.class, String.class, String.class, String.class, String.class, String.class, String.class, URL.class});
                    method3.invoke(obj, new Object[]{declaredMethod2, Long.valueOf(longValue), true});
                    method3.invoke(obj, new Object[]{declaredMethod, Long.valueOf(longValue), true});
                    method3.invoke(obj, new Object[]{declaredMethod3, Long.valueOf(longValue), true});
                    try {
                        Method declaredMethod4 = ClassLoader.class.getDeclaredMethod("getClassLoadingLock", new Class[]{String.class});
                        method3.invoke(obj, new Object[]{declaredMethod4, Long.valueOf(longValue), true});
                        method2 = declaredMethod3;
                        try {
                            ForJava7CapableVm forJava7CapableVm = new ForJava7CapableVm(declaredMethod, declaredMethod2, method, declaredMethod3, declaredMethod4);
                            return forJava7CapableVm;
                        } catch (NoSuchMethodException unused3) {
                            return new ForLegacyVm(declaredMethod, declaredMethod2, method, method2);
                        }
                    } catch (NoSuchMethodException unused4) {
                        method2 = declaredMethod3;
                        return new ForLegacyVm(declaredMethod, declaredMethod2, method, method2);
                    }
                }

                public Dispatcher initialize() {
                    SecurityManager securityManager = System.getSecurityManager();
                    if (securityManager != null) {
                        try {
                            securityManager.checkPermission(ClassInjector.SUPPRESS_ACCESS_CHECKS);
                        } catch (Exception e) {
                            return new Unavailable(e.getMessage());
                        }
                    }
                    return this;
                }

                public Class<?> findClass(ClassLoader classLoader, String str) {
                    try {
                        return (Class) this.findLoadedClass.invoke(classLoader, new Object[]{str});
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Could not access java.lang.ClassLoader#findClass", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.ClassLoader#findClass", e2.getCause());
                    }
                }

                public Class<?> defineClass(ClassLoader classLoader, String str, byte[] bArr, ProtectionDomain protectionDomain) {
                    try {
                        return (Class) this.defineClass.invoke(classLoader, new Object[]{str, bArr, 0, Integer.valueOf(bArr.length), protectionDomain});
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Could not access java.lang.ClassLoader#defineClass", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.ClassLoader#defineClass", e2.getCause());
                    }
                }

                public Package getPackage(ClassLoader classLoader, String str) {
                    try {
                        return (Package) this.getPackage.invoke(classLoader, new Object[]{str});
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Could not access java.lang.ClassLoader#getPackage", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.ClassLoader#getPackage", e2.getCause());
                    }
                }

                public Package definePackage(ClassLoader classLoader, String str, String str2, String str3, String str4, String str5, String str6, String str7, URL url) {
                    try {
                        return (Package) this.definePackage.invoke(classLoader, new Object[]{str, str2, str3, str4, str5, str6, str7, url});
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Could not access java.lang.ClassLoader#definePackage", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.ClassLoader#definePackage", e2.getCause());
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                protected static class ForJava7CapableVm extends UsingUnsafeOverride {
                    private final Method getClassLoadingLock;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.getClassLoadingLock.equals(((ForJava7CapableVm) obj).getClassLoadingLock);
                    }

                    public int hashCode() {
                        return 527 + this.getClassLoadingLock.hashCode();
                    }

                    protected ForJava7CapableVm(Method method, Method method2, Method method3, Method method4, Method method5) {
                        super(method, method2, method3, method4);
                        this.getClassLoadingLock = method5;
                    }

                    public Object getClassLoadingLock(ClassLoader classLoader, String str) {
                        try {
                            return this.getClassLoadingLock.invoke(classLoader, new Object[]{str});
                        } catch (IllegalAccessException e) {
                            throw new IllegalStateException("Could not access java.lang.ClassLoader#getClassLoadingLock", e);
                        } catch (InvocationTargetException e2) {
                            throw new IllegalStateException("Error invoking java.lang.ClassLoader#getClassLoadingLock", e2.getCause());
                        }
                    }
                }

                protected static class ForLegacyVm extends UsingUnsafeOverride {
                    public Object getClassLoadingLock(ClassLoader classLoader, String str) {
                        return classLoader;
                    }

                    protected ForLegacyVm(Method method, Method method2, Method method3, Method method4) {
                        super(method, method2, method3, method4);
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Unavailable implements Dispatcher {
                private final String message;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.message.equals(((Unavailable) obj).message);
                }

                public Object getClassLoadingLock(ClassLoader classLoader, String str) {
                    return classLoader;
                }

                public int hashCode() {
                    return 527 + this.message.hashCode();
                }

                protected Unavailable(String str) {
                    this.message = str;
                }

                public Class<?> findClass(ClassLoader classLoader, String str) {
                    try {
                        return classLoader.loadClass(str);
                    } catch (ClassNotFoundException unused) {
                        return UNDEFINED;
                    }
                }

                public Class<?> defineClass(ClassLoader classLoader, String str, byte[] bArr, ProtectionDomain protectionDomain) {
                    throw new UnsupportedOperationException("Cannot define class using reflection: " + this.message);
                }

                public Package getPackage(ClassLoader classLoader, String str) {
                    throw new UnsupportedOperationException("Cannot get package using reflection: " + this.message);
                }

                public Package definePackage(ClassLoader classLoader, String str, String str2, String str3, String str4, String str5, String str6, String str7, URL url) {
                    throw new UnsupportedOperationException("Cannot define package using injection: " + this.message);
                }
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class UsingLookup extends AbstractBase {
        private static final Dispatcher DISPATCHER = ((Dispatcher) AccessController.doPrivileged(Dispatcher.Creator.INSTANCE));
        private static final int PACKAGE_LOOKUP = 8;
        private final Object lookup;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.lookup.equals(((UsingLookup) obj).lookup);
        }

        public int hashCode() {
            return 527 + this.lookup.hashCode();
        }

        protected UsingLookup(Object obj) {
            this.lookup = obj;
        }

        public static UsingLookup of(Object obj) {
            if (!DISPATCHER.isAlive()) {
                throw new IllegalStateException("The current VM does not support class definition via method handle lookups");
            } else if (!JavaType.METHOD_HANDLES_LOOKUP.isInstance(obj)) {
                throw new IllegalArgumentException("Not a method handle lookup: " + obj);
            } else if ((DISPATCHER.lookupModes(obj) & 8) != 0) {
                return new UsingLookup(DISPATCHER.dropLookupMode(obj, 2));
            } else {
                throw new IllegalArgumentException("Lookup does not imply package-access: " + obj);
            }
        }

        public Class<?> lookupType() {
            return DISPATCHER.lookupType(this.lookup);
        }

        public UsingLookup in(Class<?> cls) {
            return new UsingLookup(DISPATCHER.resolve(this.lookup, cls));
        }

        public boolean isAlive() {
            return isAvailable();
        }

        public Map<String, Class<?>> injectRaw(Map<? extends String, byte[]> map) {
            String str;
            String name = TypeDescription.ForLoadedType.of(lookupType()).getPackage().getName();
            HashMap hashMap = new HashMap();
            for (Map.Entry next : map.entrySet()) {
                int lastIndexOf = ((String) next.getKey()).lastIndexOf(46);
                if (lastIndexOf == -1) {
                    str = "";
                } else {
                    str = ((String) next.getKey()).substring(0, lastIndexOf);
                }
                if (name.equals(str)) {
                    hashMap.put(next.getKey(), DISPATCHER.defineClass(this.lookup, (byte[]) next.getValue()));
                } else {
                    throw new IllegalArgumentException(((String) next.getKey()) + " must be defined in the same package as " + this.lookup);
                }
            }
            return hashMap;
        }

        public static boolean isAvailable() {
            return DISPATCHER.isAlive();
        }

        protected interface Dispatcher {
            Class<?> defineClass(Object obj, byte[] bArr);

            Object dropLookupMode(Object obj, int i);

            boolean isAlive();

            int lookupModes(Object obj);

            Class<?> lookupType(Object obj);

            Object resolve(Object obj, Class<?> cls);

            public enum Creator implements PrivilegedAction<Dispatcher> {
                INSTANCE;

                public Dispatcher run() {
                    try {
                        Class<?> load = JavaType.METHOD_HANDLES_LOOKUP.load();
                        return new ForJava9CapableVm(JavaType.METHOD_HANDLES.load().getMethod("privateLookupIn", new Class[]{Class.class, load}), load.getMethod("lookupClass", new Class[0]), load.getMethod("lookupModes", new Class[0]), load.getMethod("dropLookupMode", new Class[]{Integer.TYPE}), load.getMethod("defineClass", new Class[]{byte[].class}));
                    } catch (Exception unused) {
                        return ForLegacyVm.INSTANCE;
                    }
                }
            }

            public enum ForLegacyVm implements Dispatcher {
                INSTANCE;

                public boolean isAlive() {
                    return false;
                }

                public Class<?> lookupType(Object obj) {
                    throw new IllegalStateException("Cannot dispatch method for java.lang.invoke.MethodHandles$Lookup");
                }

                public int lookupModes(Object obj) {
                    throw new IllegalStateException("Cannot dispatch method for java.lang.invoke.MethodHandles$Lookup");
                }

                public Object dropLookupMode(Object obj, int i) {
                    throw new IllegalStateException("Cannot dispatch method for java.lang.invoke.MethodHandles$Lookup");
                }

                public Object resolve(Object obj, Class<?> cls) {
                    throw new IllegalStateException("Cannot dispatch method for java.lang.invoke.MethodHandles");
                }

                public Class<?> defineClass(Object obj, byte[] bArr) {
                    throw new IllegalStateException("Cannot dispatch method for java.lang.invoke.MethodHandles$Lookup");
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForJava9CapableVm implements Dispatcher {
                private static final Object[] NO_ARGUMENTS = new Object[0];
                private final Method defineClass;
                private final Method dropLookupMode;
                private final Method lookupClass;
                private final Method lookupModes;
                private final Method privateLookupIn;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    ForJava9CapableVm forJava9CapableVm = (ForJava9CapableVm) obj;
                    return this.privateLookupIn.equals(forJava9CapableVm.privateLookupIn) && this.lookupClass.equals(forJava9CapableVm.lookupClass) && this.lookupModes.equals(forJava9CapableVm.lookupModes) && this.dropLookupMode.equals(forJava9CapableVm.dropLookupMode) && this.defineClass.equals(forJava9CapableVm.defineClass);
                }

                public int hashCode() {
                    return ((((((((527 + this.privateLookupIn.hashCode()) * 31) + this.lookupClass.hashCode()) * 31) + this.lookupModes.hashCode()) * 31) + this.dropLookupMode.hashCode()) * 31) + this.defineClass.hashCode();
                }

                public boolean isAlive() {
                    return true;
                }

                protected ForJava9CapableVm(Method method, Method method2, Method method3, Method method4, Method method5) {
                    this.privateLookupIn = method;
                    this.lookupClass = method2;
                    this.lookupModes = method3;
                    this.defineClass = method5;
                    this.dropLookupMode = method4;
                }

                public Class<?> lookupType(Object obj) {
                    try {
                        return (Class) this.lookupClass.invoke(obj, NO_ARGUMENTS);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodHandles$Lookup#lookupClass", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.invoke.MethodHandles$Lookup#lookupClass", e2.getCause());
                    }
                }

                public int lookupModes(Object obj) {
                    try {
                        return ((Integer) this.lookupModes.invoke(obj, NO_ARGUMENTS)).intValue();
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodHandles$Lookup#lookupModes", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.invoke.MethodHandles$Lookup#lookupModes", e2.getCause());
                    }
                }

                public Object dropLookupMode(Object obj, int i) {
                    try {
                        return this.dropLookupMode.invoke(obj, new Object[]{Integer.valueOf(i)});
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodHandles$Lookup#lookupModes", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.invoke.MethodHandles$Lookup#lookupModes", e2.getCause());
                    }
                }

                public Object resolve(Object obj, Class<?> cls) {
                    try {
                        return this.privateLookupIn.invoke((Object) null, new Object[]{cls, obj});
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodHandles#privateLookupIn", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.invoke.MethodHandles#privateLookupIn", e2.getCause());
                    }
                }

                public Class<?> defineClass(Object obj, byte[] bArr) {
                    try {
                        return (Class) this.defineClass.invoke(obj, new Object[]{bArr});
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodHandles$Lookup#defineClass", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.invoke.MethodHandles$Lookup#defineClass", e2.getCause());
                    }
                }
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class UsingUnsafe extends AbstractBase {
        private static final Object BOOTSTRAP_LOADER_LOCK = new Object();
        private static final Dispatcher.Initializable DISPATCHER = ((Dispatcher.Initializable) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE));
        public static final String SAFE_PROPERTY = "net.bytebuddy.safe";
        @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
        private final ClassLoader classLoader;
        @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
        private final ProtectionDomain protectionDomain;

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
                java.lang.ClassLoader r2 = r4.classLoader
                net.bytebuddy.dynamic.loading.ClassInjector$UsingUnsafe r5 = (net.bytebuddy.dynamic.loading.ClassInjector.UsingUnsafe) r5
                java.lang.ClassLoader r3 = r5.classLoader
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
                java.security.ProtectionDomain r2 = r4.protectionDomain
                java.security.ProtectionDomain r5 = r5.protectionDomain
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
            throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.dynamic.loading.ClassInjector.UsingUnsafe.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            ClassLoader classLoader2 = this.classLoader;
            int i = 527;
            if (classLoader2 != null) {
                i = 527 + classLoader2.hashCode();
            }
            int i2 = i * 31;
            ProtectionDomain protectionDomain2 = this.protectionDomain;
            return protectionDomain2 != null ? i2 + protectionDomain2.hashCode() : i2;
        }

        public UsingUnsafe(ClassLoader classLoader2) {
            this(classLoader2, ClassLoadingStrategy.NO_PROTECTION_DOMAIN);
        }

        public UsingUnsafe(ClassLoader classLoader2, ProtectionDomain protectionDomain2) {
            this.classLoader = classLoader2;
            this.protectionDomain = protectionDomain2;
        }

        public boolean isAlive() {
            return isAvailable();
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(8:8|9|10|11|12|21|18|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x001a, code lost:
            continue;
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003b */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.util.Map<java.lang.String, java.lang.Class<?>> injectRaw(java.util.Map<? extends java.lang.String, byte[]> r9) {
            /*
                r8 = this;
                net.bytebuddy.dynamic.loading.ClassInjector$UsingUnsafe$Dispatcher$Initializable r0 = DISPATCHER
                net.bytebuddy.dynamic.loading.ClassInjector$UsingUnsafe$Dispatcher r0 = r0.initialize()
                java.util.HashMap r1 = new java.util.HashMap
                r1.<init>()
                java.lang.ClassLoader r2 = r8.classLoader
                if (r2 != 0) goto L_0x0011
                java.lang.Object r2 = BOOTSTRAP_LOADER_LOCK
            L_0x0011:
                monitor-enter(r2)
                java.util.Set r9 = r9.entrySet()     // Catch:{ all -> 0x0059 }
                java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x0059 }
            L_0x001a:
                boolean r3 = r9.hasNext()     // Catch:{ all -> 0x0059 }
                if (r3 == 0) goto L_0x0057
                java.lang.Object r3 = r9.next()     // Catch:{ all -> 0x0059 }
                java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch:{ all -> 0x0059 }
                java.lang.Object r4 = r3.getKey()     // Catch:{ ClassNotFoundException -> 0x003b }
                java.lang.Object r5 = r3.getKey()     // Catch:{ ClassNotFoundException -> 0x003b }
                java.lang.String r5 = (java.lang.String) r5     // Catch:{ ClassNotFoundException -> 0x003b }
                r6 = 0
                java.lang.ClassLoader r7 = r8.classLoader     // Catch:{ ClassNotFoundException -> 0x003b }
                java.lang.Class r5 = java.lang.Class.forName(r5, r6, r7)     // Catch:{ ClassNotFoundException -> 0x003b }
                r1.put(r4, r5)     // Catch:{ ClassNotFoundException -> 0x003b }
                goto L_0x001a
            L_0x003b:
                java.lang.Object r4 = r3.getKey()     // Catch:{ all -> 0x0059 }
                java.lang.ClassLoader r5 = r8.classLoader     // Catch:{ all -> 0x0059 }
                java.lang.Object r6 = r3.getKey()     // Catch:{ all -> 0x0059 }
                java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x0059 }
                java.lang.Object r3 = r3.getValue()     // Catch:{ all -> 0x0059 }
                byte[] r3 = (byte[]) r3     // Catch:{ all -> 0x0059 }
                java.security.ProtectionDomain r7 = r8.protectionDomain     // Catch:{ all -> 0x0059 }
                java.lang.Class r3 = r0.defineClass(r5, r6, r3, r7)     // Catch:{ all -> 0x0059 }
                r1.put(r4, r3)     // Catch:{ all -> 0x0059 }
                goto L_0x001a
            L_0x0057:
                monitor-exit(r2)     // Catch:{ all -> 0x0059 }
                return r1
            L_0x0059:
                r9 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x0059 }
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.dynamic.loading.ClassInjector.UsingUnsafe.injectRaw(java.util.Map):java.util.Map");
        }

        public static boolean isAvailable() {
            return DISPATCHER.isAvailable();
        }

        public static ClassInjector ofSystemLoader() {
            return new UsingUnsafe(ClassLoader.getSystemClassLoader());
        }

        public static ClassInjector ofPlatformLoader() {
            return new UsingUnsafe(ClassLoader.getSystemClassLoader().getParent());
        }

        public static ClassInjector ofBootLoader() {
            return new UsingUnsafe(ClassLoadingStrategy.BOOTSTRAP_LOADER);
        }

        protected interface Dispatcher {

            public interface Initializable {
                Dispatcher initialize();

                boolean isAvailable();
            }

            Class<?> defineClass(ClassLoader classLoader, String str, byte[] bArr, ProtectionDomain protectionDomain);

            public enum CreationAction implements PrivilegedAction<Initializable> {
                INSTANCE;

                /* JADX WARNING: Can't wrap try/catch for region: R(2:15|16) */
                /* JADX WARNING: Can't wrap try/catch for region: R(2:19|20) */
                /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
                    r7 = new net.bytebuddy.ByteBuddy().redefine(java.lang.reflect.AccessibleObject.class);
                    r2 = r7.name("net.bytebuddy.mirror." + java.lang.reflect.AccessibleObject.class.getSimpleName()).visit(new net.bytebuddy.asm.MemberRemoval().stripInvokables(net.bytebuddy.matcher.ElementMatchers.any())).make().load(java.lang.reflect.AccessibleObject.class.getClassLoader(), net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Default.WRAPPER).getLoaded().getDeclaredField("override");
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
                    throw r0;
                 */
                /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x005e */
                /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0133 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public net.bytebuddy.dynamic.loading.ClassInjector.UsingUnsafe.Dispatcher.Initializable run() {
                    /*
                        r18 = this;
                        java.lang.Class<byte[]> r1 = byte[].class
                        java.lang.String r2 = "override"
                        java.lang.String r3 = "defineClass"
                        java.lang.String r4 = "theUnsafe"
                        java.lang.String r0 = "net.bytebuddy.safe"
                        boolean r0 = java.lang.Boolean.getBoolean(r0)
                        if (r0 == 0) goto L_0x0018
                        net.bytebuddy.dynamic.loading.ClassInjector$UsingUnsafe$Dispatcher$Unavailable r0 = new net.bytebuddy.dynamic.loading.ClassInjector$UsingUnsafe$Dispatcher$Unavailable
                        java.lang.String r1 = "Use of Unsafe was disabled by system property"
                        r0.<init>(r1)
                        return r0
                    L_0x0018:
                        java.lang.String r0 = "sun.misc.Unsafe"
                        java.lang.Class r5 = java.lang.Class.forName(r0)     // Catch:{ Exception -> 0x0134 }
                        java.lang.reflect.Field r0 = r5.getDeclaredField(r4)     // Catch:{ Exception -> 0x0134 }
                        r6 = 1
                        r0.setAccessible(r6)     // Catch:{ Exception -> 0x0134 }
                        r7 = 0
                        java.lang.Object r8 = r0.get(r7)     // Catch:{ Exception -> 0x0134 }
                        r9 = 5
                        r10 = 4
                        r11 = 6
                        r12 = 3
                        r13 = 2
                        r14 = 0
                        java.lang.Class[] r0 = new java.lang.Class[r11]     // Catch:{ Exception -> 0x0056 }
                        java.lang.Class<java.lang.String> r15 = java.lang.String.class
                        r0[r14] = r15     // Catch:{ Exception -> 0x0056 }
                        r0[r6] = r1     // Catch:{ Exception -> 0x0056 }
                        java.lang.Class r15 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0056 }
                        r0[r13] = r15     // Catch:{ Exception -> 0x0056 }
                        java.lang.Class r15 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0056 }
                        r0[r12] = r15     // Catch:{ Exception -> 0x0056 }
                        java.lang.Class<java.lang.ClassLoader> r15 = java.lang.ClassLoader.class
                        r0[r10] = r15     // Catch:{ Exception -> 0x0056 }
                        java.lang.Class<java.security.ProtectionDomain> r15 = java.security.ProtectionDomain.class
                        r0[r9] = r15     // Catch:{ Exception -> 0x0056 }
                        java.lang.reflect.Method r0 = r5.getMethod(r3, r0)     // Catch:{ Exception -> 0x0056 }
                        r0.setAccessible(r6)     // Catch:{ Exception -> 0x0056 }
                        net.bytebuddy.dynamic.loading.ClassInjector$UsingUnsafe$Dispatcher$Enabled r15 = new net.bytebuddy.dynamic.loading.ClassInjector$UsingUnsafe$Dispatcher$Enabled     // Catch:{ Exception -> 0x0056 }
                        r15.<init>(r8, r0)     // Catch:{ Exception -> 0x0056 }
                        return r15
                    L_0x0056:
                        r0 = move-exception
                        java.lang.Class<java.lang.reflect.AccessibleObject> r15 = java.lang.reflect.AccessibleObject.class
                        java.lang.reflect.Field r2 = r15.getDeclaredField(r2)     // Catch:{ NoSuchFieldException -> 0x005e }
                        goto L_0x00ad
                    L_0x005e:
                        net.bytebuddy.ByteBuddy r15 = new net.bytebuddy.ByteBuddy     // Catch:{ Exception -> 0x0133 }
                        r15.<init>()     // Catch:{ Exception -> 0x0133 }
                        java.lang.Class<java.lang.reflect.AccessibleObject> r7 = java.lang.reflect.AccessibleObject.class
                        net.bytebuddy.dynamic.DynamicType$Builder r7 = r15.redefine(r7)     // Catch:{ Exception -> 0x0133 }
                        java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0133 }
                        r15.<init>()     // Catch:{ Exception -> 0x0133 }
                        java.lang.String r9 = "net.bytebuddy.mirror."
                        r15.append(r9)     // Catch:{ Exception -> 0x0133 }
                        java.lang.Class<java.lang.reflect.AccessibleObject> r9 = java.lang.reflect.AccessibleObject.class
                        java.lang.String r9 = r9.getSimpleName()     // Catch:{ Exception -> 0x0133 }
                        r15.append(r9)     // Catch:{ Exception -> 0x0133 }
                        java.lang.String r9 = r15.toString()     // Catch:{ Exception -> 0x0133 }
                        net.bytebuddy.dynamic.DynamicType$Builder r7 = r7.name(r9)     // Catch:{ Exception -> 0x0133 }
                        net.bytebuddy.asm.MemberRemoval r9 = new net.bytebuddy.asm.MemberRemoval     // Catch:{ Exception -> 0x0133 }
                        r9.<init>()     // Catch:{ Exception -> 0x0133 }
                        net.bytebuddy.matcher.ElementMatcher$Junction r15 = net.bytebuddy.matcher.ElementMatchers.any()     // Catch:{ Exception -> 0x0133 }
                        net.bytebuddy.asm.MemberRemoval r9 = r9.stripInvokables(r15)     // Catch:{ Exception -> 0x0133 }
                        net.bytebuddy.dynamic.DynamicType$Builder r7 = r7.visit(r9)     // Catch:{ Exception -> 0x0133 }
                        net.bytebuddy.dynamic.DynamicType$Unloaded r7 = r7.make()     // Catch:{ Exception -> 0x0133 }
                        java.lang.Class<java.lang.reflect.AccessibleObject> r9 = java.lang.reflect.AccessibleObject.class
                        java.lang.ClassLoader r9 = r9.getClassLoader()     // Catch:{ Exception -> 0x0133 }
                        net.bytebuddy.dynamic.loading.ClassLoadingStrategy$Default r15 = net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Default.WRAPPER     // Catch:{ Exception -> 0x0133 }
                        net.bytebuddy.dynamic.DynamicType$Loaded r7 = r7.load(r9, r15)     // Catch:{ Exception -> 0x0133 }
                        java.lang.Class r7 = r7.getLoaded()     // Catch:{ Exception -> 0x0133 }
                        java.lang.reflect.Field r2 = r7.getDeclaredField(r2)     // Catch:{ Exception -> 0x0133 }
                    L_0x00ad:
                        java.lang.String r7 = "objectFieldOffset"
                        java.lang.Class[] r9 = new java.lang.Class[r6]     // Catch:{ Exception -> 0x0133 }
                        java.lang.Class<java.lang.reflect.Field> r15 = java.lang.reflect.Field.class
                        r9[r14] = r15     // Catch:{ Exception -> 0x0133 }
                        java.lang.reflect.Method r7 = r5.getMethod(r7, r9)     // Catch:{ Exception -> 0x0133 }
                        java.lang.Object[] r9 = new java.lang.Object[r6]     // Catch:{ Exception -> 0x0133 }
                        r9[r14] = r2     // Catch:{ Exception -> 0x0133 }
                        java.lang.Object r2 = r7.invoke(r8, r9)     // Catch:{ Exception -> 0x0133 }
                        java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ Exception -> 0x0133 }
                        long r16 = r2.longValue()     // Catch:{ Exception -> 0x0133 }
                        java.lang.String r2 = "putBoolean"
                        java.lang.Class[] r7 = new java.lang.Class[r12]     // Catch:{ Exception -> 0x0133 }
                        java.lang.Class<java.lang.Object> r9 = java.lang.Object.class
                        r7[r14] = r9     // Catch:{ Exception -> 0x0133 }
                        java.lang.Class r9 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0133 }
                        r7[r6] = r9     // Catch:{ Exception -> 0x0133 }
                        java.lang.Class r9 = java.lang.Boolean.TYPE     // Catch:{ Exception -> 0x0133 }
                        r7[r13] = r9     // Catch:{ Exception -> 0x0133 }
                        java.lang.reflect.Method r2 = r5.getMethod(r2, r7)     // Catch:{ Exception -> 0x0133 }
                        java.lang.String r5 = "jdk.internal.misc.Unsafe"
                        java.lang.Class r5 = java.lang.Class.forName(r5)     // Catch:{ Exception -> 0x0133 }
                        java.lang.reflect.Field r4 = r5.getDeclaredField(r4)     // Catch:{ Exception -> 0x0133 }
                        java.lang.Object[] r7 = new java.lang.Object[r12]     // Catch:{ Exception -> 0x0133 }
                        r7[r14] = r4     // Catch:{ Exception -> 0x0133 }
                        java.lang.Long r9 = java.lang.Long.valueOf(r16)     // Catch:{ Exception -> 0x0133 }
                        r7[r6] = r9     // Catch:{ Exception -> 0x0133 }
                        java.lang.Boolean r9 = java.lang.Boolean.valueOf(r6)     // Catch:{ Exception -> 0x0133 }
                        r7[r13] = r9     // Catch:{ Exception -> 0x0133 }
                        r2.invoke(r8, r7)     // Catch:{ Exception -> 0x0133 }
                        java.lang.Class[] r7 = new java.lang.Class[r11]     // Catch:{ Exception -> 0x0133 }
                        java.lang.Class<java.lang.String> r9 = java.lang.String.class
                        r7[r14] = r9     // Catch:{ Exception -> 0x0133 }
                        r7[r6] = r1     // Catch:{ Exception -> 0x0133 }
                        java.lang.Class r1 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0133 }
                        r7[r13] = r1     // Catch:{ Exception -> 0x0133 }
                        java.lang.Class r1 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0133 }
                        r7[r12] = r1     // Catch:{ Exception -> 0x0133 }
                        java.lang.Class<java.lang.ClassLoader> r1 = java.lang.ClassLoader.class
                        r7[r10] = r1     // Catch:{ Exception -> 0x0133 }
                        java.lang.Class<java.security.ProtectionDomain> r1 = java.security.ProtectionDomain.class
                        r9 = 5
                        r7[r9] = r1     // Catch:{ Exception -> 0x0133 }
                        java.lang.reflect.Method r1 = r5.getMethod(r3, r7)     // Catch:{ Exception -> 0x0133 }
                        java.lang.Object[] r3 = new java.lang.Object[r12]     // Catch:{ Exception -> 0x0133 }
                        r3[r14] = r1     // Catch:{ Exception -> 0x0133 }
                        java.lang.Long r5 = java.lang.Long.valueOf(r16)     // Catch:{ Exception -> 0x0133 }
                        r3[r6] = r5     // Catch:{ Exception -> 0x0133 }
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r6)     // Catch:{ Exception -> 0x0133 }
                        r3[r13] = r5     // Catch:{ Exception -> 0x0133 }
                        r2.invoke(r8, r3)     // Catch:{ Exception -> 0x0133 }
                        net.bytebuddy.dynamic.loading.ClassInjector$UsingUnsafe$Dispatcher$Enabled r2 = new net.bytebuddy.dynamic.loading.ClassInjector$UsingUnsafe$Dispatcher$Enabled     // Catch:{ Exception -> 0x0133 }
                        r3 = 0
                        java.lang.Object r3 = r4.get(r3)     // Catch:{ Exception -> 0x0133 }
                        r2.<init>(r3, r1)     // Catch:{ Exception -> 0x0133 }
                        return r2
                    L_0x0133:
                        throw r0     // Catch:{ Exception -> 0x0134 }
                    L_0x0134:
                        r0 = move-exception
                        net.bytebuddy.dynamic.loading.ClassInjector$UsingUnsafe$Dispatcher$Unavailable r1 = new net.bytebuddy.dynamic.loading.ClassInjector$UsingUnsafe$Dispatcher$Unavailable
                        java.lang.String r0 = r0.getMessage()
                        r1.<init>(r0)
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.dynamic.loading.ClassInjector.UsingUnsafe.Dispatcher.CreationAction.run():net.bytebuddy.dynamic.loading.ClassInjector$UsingUnsafe$Dispatcher$Initializable");
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Enabled implements Dispatcher, Initializable {
                private final Method defineClass;
                private final Object unsafe;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Enabled enabled = (Enabled) obj;
                    return this.unsafe.equals(enabled.unsafe) && this.defineClass.equals(enabled.defineClass);
                }

                public int hashCode() {
                    return ((527 + this.unsafe.hashCode()) * 31) + this.defineClass.hashCode();
                }

                public boolean isAvailable() {
                    return true;
                }

                protected Enabled(Object obj, Method method) {
                    this.unsafe = obj;
                    this.defineClass = method;
                }

                public Dispatcher initialize() {
                    SecurityManager securityManager = System.getSecurityManager();
                    if (securityManager != null) {
                        try {
                            securityManager.checkPermission(ClassInjector.SUPPRESS_ACCESS_CHECKS);
                        } catch (Exception e) {
                            return new Unavailable(e.getMessage());
                        }
                    }
                    return this;
                }

                public Class<?> defineClass(ClassLoader classLoader, String str, byte[] bArr, ProtectionDomain protectionDomain) {
                    try {
                        return (Class) this.defineClass.invoke(this.unsafe, new Object[]{str, bArr, 0, Integer.valueOf(bArr.length), classLoader, protectionDomain});
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Could not access Unsafe::defineClass", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking Unsafe::defineClass", e2.getCause());
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Unavailable implements Dispatcher, Initializable {
                private final String message;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.message.equals(((Unavailable) obj).message);
                }

                public int hashCode() {
                    return 527 + this.message.hashCode();
                }

                public boolean isAvailable() {
                    return false;
                }

                protected Unavailable(String str) {
                    this.message = str;
                }

                public Dispatcher initialize() {
                    throw new UnsupportedOperationException("Could not access Unsafe class: " + this.message);
                }

                public Class<?> defineClass(ClassLoader classLoader, String str, byte[] bArr, ProtectionDomain protectionDomain) {
                    throw new UnsupportedOperationException("Could not access Unsafe class: " + this.message);
                }
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class UsingInstrumentation extends AbstractBase {
        private static final String CLASS_FILE_EXTENSION = ".class";
        /* access modifiers changed from: private */
        public static final Dispatcher DISPATCHER = ((Dispatcher) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE));
        private static final String JAR = "jar";
        private final File folder;
        private final Instrumentation instrumentation;
        private final RandomString randomString;
        private final Target target;

        public enum Target {
            BOOTSTRAP {
                /* access modifiers changed from: protected */
                public void inject(Instrumentation instrumentation, JarFile jarFile) {
                    UsingInstrumentation.DISPATCHER.appendToBootstrapClassLoaderSearch(instrumentation, jarFile);
                }
            },
            SYSTEM {
                /* access modifiers changed from: protected */
                public void inject(Instrumentation instrumentation, JarFile jarFile) {
                    UsingInstrumentation.DISPATCHER.appendToSystemClassLoaderSearch(instrumentation, jarFile);
                }
            };

            /* access modifiers changed from: protected */
            public abstract void inject(Instrumentation instrumentation, JarFile jarFile);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            UsingInstrumentation usingInstrumentation = (UsingInstrumentation) obj;
            return this.target.equals(usingInstrumentation.target) && this.instrumentation.equals(usingInstrumentation.instrumentation) && this.folder.equals(usingInstrumentation.folder) && this.randomString.equals(usingInstrumentation.randomString);
        }

        public int hashCode() {
            return ((((((527 + this.instrumentation.hashCode()) * 31) + this.target.hashCode()) * 31) + this.folder.hashCode()) * 31) + this.randomString.hashCode();
        }

        protected UsingInstrumentation(File file, Target target2, Instrumentation instrumentation2, RandomString randomString2) {
            this.folder = file;
            this.target = target2;
            this.instrumentation = instrumentation2;
            this.randomString = randomString2;
        }

        public static ClassInjector of(File file, Target target2, Instrumentation instrumentation2) {
            return new UsingInstrumentation(file, target2, instrumentation2, new RandomString());
        }

        public boolean isAlive() {
            return isAvailable();
        }

        public Map<String, Class<?>> injectRaw(Map<? extends String, byte[]> map) {
            JarOutputStream jarOutputStream;
            File file = this.folder;
            File file2 = new File(file, JAR + this.randomString.nextString() + "." + JAR);
            try {
                if (file2.createNewFile()) {
                    jarOutputStream = new JarOutputStream(new FileOutputStream(file2));
                    for (Map.Entry next : map.entrySet()) {
                        jarOutputStream.putNextEntry(new JarEntry(((String) next.getKey()).replace('.', '/') + ".class"));
                        jarOutputStream.write((byte[]) next.getValue());
                    }
                    jarOutputStream.close();
                    this.target.inject(this.instrumentation, new JarFile(file2));
                    HashMap hashMap = new HashMap();
                    for (String str : map.keySet()) {
                        hashMap.put(str, Class.forName(str, false, ClassLoader.getSystemClassLoader()));
                    }
                    return hashMap;
                }
                throw new IllegalStateException("Cannot create file " + file2);
            } catch (IOException e) {
                throw new IllegalStateException("Cannot write jar file to disk", e);
            } catch (ClassNotFoundException e2) {
                throw new IllegalStateException("Cannot load injected class", e2);
            } catch (Throwable th) {
                jarOutputStream.close();
                throw th;
            }
        }

        public static boolean isAvailable() {
            return DISPATCHER.isAlive();
        }

        protected interface Dispatcher {
            void appendToBootstrapClassLoaderSearch(Instrumentation instrumentation, JarFile jarFile);

            void appendToSystemClassLoaderSearch(Instrumentation instrumentation, JarFile jarFile);

            boolean isAlive();

            public enum CreationAction implements PrivilegedAction<Dispatcher> {
                INSTANCE;

                public Dispatcher run() {
                    try {
                        return new ForJava6CapableVm(Instrumentation.class.getMethod("appendToBootstrapClassLoaderSearch", new Class[]{JarFile.class}), Instrumentation.class.getMethod("appendToSystemClassLoaderSearch", new Class[]{JarFile.class}));
                    } catch (NoSuchMethodException unused) {
                        return ForLegacyVm.INSTANCE;
                    }
                }
            }

            public enum ForLegacyVm implements Dispatcher {
                INSTANCE;

                public boolean isAlive() {
                    return false;
                }

                public void appendToBootstrapClassLoaderSearch(Instrumentation instrumentation, JarFile jarFile) {
                    throw new UnsupportedOperationException("The current JVM does not support appending to the bootstrap loader");
                }

                public void appendToSystemClassLoaderSearch(Instrumentation instrumentation, JarFile jarFile) {
                    throw new UnsupportedOperationException("The current JVM does not support appending to the system class loader");
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForJava6CapableVm implements Dispatcher {
                private final Method appendToBootstrapClassLoaderSearch;
                private final Method appendToSystemClassLoaderSearch;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    ForJava6CapableVm forJava6CapableVm = (ForJava6CapableVm) obj;
                    return this.appendToBootstrapClassLoaderSearch.equals(forJava6CapableVm.appendToBootstrapClassLoaderSearch) && this.appendToSystemClassLoaderSearch.equals(forJava6CapableVm.appendToSystemClassLoaderSearch);
                }

                public int hashCode() {
                    return ((527 + this.appendToBootstrapClassLoaderSearch.hashCode()) * 31) + this.appendToSystemClassLoaderSearch.hashCode();
                }

                public boolean isAlive() {
                    return true;
                }

                protected ForJava6CapableVm(Method method, Method method2) {
                    this.appendToBootstrapClassLoaderSearch = method;
                    this.appendToSystemClassLoaderSearch = method2;
                }

                public void appendToBootstrapClassLoaderSearch(Instrumentation instrumentation, JarFile jarFile) {
                    try {
                        this.appendToBootstrapClassLoaderSearch.invoke(instrumentation, new Object[]{jarFile});
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.instrument.Instrumentation#appendToBootstrapClassLoaderSearch", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.instrument.Instrumentation#appendToBootstrapClassLoaderSearch", e2.getCause());
                    }
                }

                public void appendToSystemClassLoaderSearch(Instrumentation instrumentation, JarFile jarFile) {
                    try {
                        this.appendToSystemClassLoaderSearch.invoke(instrumentation, new Object[]{jarFile});
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.instrument.Instrumentation#appendToSystemClassLoaderSearch", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.instrument.Instrumentation#appendToSystemClassLoaderSearch", e2.getCause());
                    }
                }
            }
        }
    }
}
