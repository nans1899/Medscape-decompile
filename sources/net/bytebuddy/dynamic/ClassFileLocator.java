package net.bytebuddy.dynamic;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.jar.JarFile;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;
import net.bytebuddy.utility.JavaType;
import net.bytebuddy.utility.StreamDrainer;

public interface ClassFileLocator extends Closeable {
    public static final String CLASS_FILE_EXTENSION = ".class";

    Resolution locate(String str) throws IOException;

    public interface Resolution {
        boolean isResolved();

        byte[] resolve();

        @HashCodeAndEqualsPlugin.Enhance
        public static class Illegal implements Resolution {
            private final String typeName;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.typeName.equals(((Illegal) obj).typeName);
            }

            public int hashCode() {
                return 527 + this.typeName.hashCode();
            }

            public boolean isResolved() {
                return false;
            }

            public Illegal(String str) {
                this.typeName = str;
            }

            public byte[] resolve() {
                throw new IllegalStateException("Could not locate class file for " + this.typeName);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Explicit implements Resolution {
            private final byte[] binaryRepresentation;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && Arrays.equals(this.binaryRepresentation, ((Explicit) obj).binaryRepresentation);
            }

            public int hashCode() {
                return 527 + Arrays.hashCode(this.binaryRepresentation);
            }

            public boolean isResolved() {
                return true;
            }

            public Explicit(byte[] bArr) {
                this.binaryRepresentation = bArr;
            }

            public byte[] resolve() {
                return this.binaryRepresentation;
            }
        }
    }

    public enum NoOp implements ClassFileLocator {
        INSTANCE;

        public void close() {
        }

        public Resolution locate(String str) {
            return new Resolution.Illegal(str);
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class Simple implements ClassFileLocator {
        private final Map<String, byte[]> classFiles;

        public void close() {
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.classFiles.equals(((Simple) obj).classFiles);
        }

        public int hashCode() {
            return 527 + this.classFiles.hashCode();
        }

        public Simple(Map<String, byte[]> map) {
            this.classFiles = map;
        }

        public static ClassFileLocator of(String str, byte[] bArr) {
            return new Simple(Collections.singletonMap(str, bArr));
        }

        public static ClassFileLocator of(DynamicType dynamicType) {
            return of(dynamicType.getAllTypes());
        }

        public static ClassFileLocator of(Map<TypeDescription, byte[]> map) {
            HashMap hashMap = new HashMap();
            for (Map.Entry next : map.entrySet()) {
                hashMap.put(((TypeDescription) next.getKey()).getName(), next.getValue());
            }
            return new Simple(hashMap);
        }

        public static ClassFileLocator ofResources(Map<String, byte[]> map) {
            HashMap hashMap = new HashMap();
            for (Map.Entry next : map.entrySet()) {
                if (((String) next.getKey()).endsWith(".class")) {
                    hashMap.put(((String) next.getKey()).substring(0, ((String) next.getKey()).length() - 6).replace('/', '.'), next.getValue());
                }
            }
            return new Simple(hashMap);
        }

        public Resolution locate(String str) {
            byte[] bArr = this.classFiles.get(str);
            return bArr == null ? new Resolution.Illegal(str) : new Resolution.Explicit(bArr);
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class ForClassLoader implements ClassFileLocator {
        private static final ClassLoader BOOT_LOADER_PROXY = ((ClassLoader) AccessController.doPrivileged(BootLoaderProxyCreationAction.INSTANCE));
        private final ClassLoader classLoader;

        public void close() {
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.classLoader.equals(((ForClassLoader) obj).classLoader);
        }

        public int hashCode() {
            return 527 + this.classLoader.hashCode();
        }

        protected ForClassLoader(ClassLoader classLoader2) {
            this.classLoader = classLoader2;
        }

        public static ClassFileLocator ofSystemLoader() {
            return new ForClassLoader(ClassLoader.getSystemClassLoader());
        }

        public static ClassFileLocator ofPlatformLoader() {
            return of(ClassLoader.getSystemClassLoader().getParent());
        }

        public static ClassFileLocator ofBootLoader() {
            return new ForClassLoader(BOOT_LOADER_PROXY);
        }

        public static ClassFileLocator of(ClassLoader classLoader2) {
            if (classLoader2 == null) {
                classLoader2 = BOOT_LOADER_PROXY;
            }
            return new ForClassLoader(classLoader2);
        }

        public static byte[] read(Class<?> cls) {
            try {
                ClassLoader classLoader2 = cls.getClassLoader();
                if (classLoader2 == null) {
                    classLoader2 = ClassLoader.getSystemClassLoader();
                }
                return locate(classLoader2, TypeDescription.ForLoadedType.getName(cls)).resolve();
            } catch (IOException e) {
                throw new IllegalStateException("Cannot read class file for " + cls, e);
            }
        }

        public static Map<Class<?>, byte[]> read(Class<?>... clsArr) {
            return read((Collection<? extends Class<?>>) Arrays.asList(clsArr));
        }

        public static Map<Class<?>, byte[]> read(Collection<? extends Class<?>> collection) {
            HashMap hashMap = new HashMap();
            for (Class cls : collection) {
                hashMap.put(cls, read((Class<?>) cls));
            }
            return hashMap;
        }

        public static Map<String, byte[]> readToNames(Class<?>... clsArr) {
            return readToNames((Collection<? extends Class<?>>) Arrays.asList(clsArr));
        }

        public static Map<String, byte[]> readToNames(Collection<? extends Class<?>> collection) {
            HashMap hashMap = new HashMap();
            for (Class cls : collection) {
                hashMap.put(cls.getName(), read((Class<?>) cls));
            }
            return hashMap;
        }

        public Resolution locate(String str) throws IOException {
            return locate(this.classLoader, str);
        }

        protected static Resolution locate(ClassLoader classLoader2, String str) throws IOException {
            InputStream resourceAsStream = classLoader2.getResourceAsStream(str.replace('.', '/') + ".class");
            if (resourceAsStream == null) {
                return new Resolution.Illegal(str);
            }
            try {
                return new Resolution.Explicit(StreamDrainer.DEFAULT.drain(resourceAsStream));
            } finally {
                resourceAsStream.close();
            }
        }

        protected enum BootLoaderProxyCreationAction implements PrivilegedAction<ClassLoader> {
            INSTANCE;

            public ClassLoader run() {
                return new URLClassLoader(new URL[0], ClassLoadingStrategy.BOOTSTRAP_LOADER);
            }
        }

        public static class WeaklyReferenced extends WeakReference<ClassLoader> implements ClassFileLocator {
            private final int hashCode;

            public void close() {
            }

            protected WeaklyReferenced(ClassLoader classLoader) {
                super(classLoader);
                this.hashCode = System.identityHashCode(classLoader);
            }

            public static ClassFileLocator of(ClassLoader classLoader) {
                return (classLoader == null || classLoader == ClassLoader.getSystemClassLoader() || classLoader == ClassLoader.getSystemClassLoader().getParent()) ? ForClassLoader.of(classLoader) : new WeaklyReferenced(classLoader);
            }

            public Resolution locate(String str) throws IOException {
                ClassLoader classLoader = (ClassLoader) get();
                if (classLoader == null) {
                    return new Resolution.Illegal(str);
                }
                return ForClassLoader.locate(classLoader, str);
            }

            public int hashCode() {
                return this.hashCode;
            }

            public boolean equals(Object obj) {
                ClassLoader classLoader;
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass() || (classLoader = (ClassLoader) ((WeaklyReferenced) obj).get()) == null || get() != classLoader) {
                    return false;
                }
                return true;
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class ForModule implements ClassFileLocator {
        private static final Object[] NO_ARGUMENTS = new Object[0];
        private final JavaModule module;

        public void close() {
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.module.equals(((ForModule) obj).module);
        }

        public int hashCode() {
            return 527 + this.module.hashCode();
        }

        protected ForModule(JavaModule javaModule) {
            this.module = javaModule;
        }

        public static ClassFileLocator ofBootLayer() {
            try {
                HashMap hashMap = new HashMap();
                Class<?> cls = Class.forName("java.lang.ModuleLayer");
                Method method = JavaType.MODULE.load().getMethod("getPackages", new Class[0]);
                for (Object next : (Set) cls.getMethod("modules", new Class[0]).invoke(cls.getMethod("boot", new Class[0]).invoke((Object) null, new Object[0]), new Object[0])) {
                    ClassFileLocator of = of(JavaModule.of(next));
                    for (String put : (Set) method.invoke(next, NO_ARGUMENTS)) {
                        hashMap.put(put, of);
                    }
                }
                return new PackageDiscriminating(hashMap);
            } catch (Exception e) {
                throw new IllegalStateException("Cannot process boot layer", e);
            }
        }

        public static ClassFileLocator of(JavaModule javaModule) {
            if (javaModule.isNamed()) {
                return new ForModule(javaModule);
            }
            return ForClassLoader.of(javaModule.getClassLoader());
        }

        public Resolution locate(String str) throws IOException {
            return locate(this.module, str);
        }

        protected static Resolution locate(JavaModule javaModule, String str) throws IOException {
            InputStream resourceAsStream = javaModule.getResourceAsStream(str.replace('.', '/') + ".class");
            if (resourceAsStream == null) {
                return new Resolution.Illegal(str);
            }
            try {
                return new Resolution.Explicit(StreamDrainer.DEFAULT.drain(resourceAsStream));
            } finally {
                resourceAsStream.close();
            }
        }

        public static class WeaklyReferenced extends WeakReference<Object> implements ClassFileLocator {
            private final int hashCode;

            public void close() {
            }

            protected WeaklyReferenced(Object obj) {
                super(obj);
                this.hashCode = System.identityHashCode(obj);
            }

            public static ClassFileLocator of(JavaModule javaModule) {
                if (!javaModule.isNamed()) {
                    return ForClassLoader.WeaklyReferenced.of(javaModule.getClassLoader());
                }
                if (javaModule.getClassLoader() == null || javaModule.getClassLoader() == ClassLoader.getSystemClassLoader() || javaModule.getClassLoader() == ClassLoader.getSystemClassLoader().getParent()) {
                    return new ForModule(javaModule);
                }
                return new WeaklyReferenced(javaModule.unwrap());
            }

            public Resolution locate(String str) throws IOException {
                Object obj = get();
                if (obj == null) {
                    return new Resolution.Illegal(str);
                }
                return ForModule.locate(JavaModule.of(obj), str);
            }

            public int hashCode() {
                return this.hashCode;
            }

            public boolean equals(Object obj) {
                Object obj2;
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass() || (obj2 = ((WeaklyReferenced) obj).get()) == null || get() != obj2) {
                    return false;
                }
                return true;
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class ForJarFile implements ClassFileLocator {
        private static final List<String> RUNTIME_LOCATIONS = Arrays.asList(new String[]{"lib/rt.jar", "../lib/rt.jar", "../Classes/classes.jar"});
        private final JarFile jarFile;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.jarFile.equals(((ForJarFile) obj).jarFile);
        }

        public int hashCode() {
            return 527 + this.jarFile.hashCode();
        }

        public ForJarFile(JarFile jarFile2) {
            this.jarFile = jarFile2;
        }

        public static ClassFileLocator of(File file) throws IOException {
            return new ForJarFile(new JarFile(file));
        }

        public static ClassFileLocator ofClassPath() throws IOException {
            return ofClassPath(System.getProperty("java.class.path"));
        }

        public static ClassFileLocator ofClassPath(String str) throws IOException {
            ArrayList arrayList = new ArrayList();
            for (String file : Pattern.compile(System.getProperty("path.separator"), 16).split(str)) {
                File file2 = new File(file);
                if (file2.isDirectory()) {
                    arrayList.add(new ForFolder(file2));
                } else if (file2.isFile()) {
                    arrayList.add(of(file2));
                }
            }
            return new Compound((List<? extends ClassFileLocator>) arrayList);
        }

        public static ClassFileLocator ofRuntimeJar() throws IOException {
            File file;
            String replace = System.getProperty("java.home").replace('\\', '/');
            Iterator<String> it = RUNTIME_LOCATIONS.iterator();
            while (true) {
                if (!it.hasNext()) {
                    file = null;
                    break;
                }
                file = new File(replace, it.next());
                if (file.isFile()) {
                    break;
                }
            }
            if (file != null) {
                return of(file);
            }
            throw new IllegalStateException("Runtime jar does not exist in " + replace + " for any of " + RUNTIME_LOCATIONS);
        }

        public Resolution locate(String str) throws IOException {
            JarFile jarFile2 = this.jarFile;
            ZipEntry entry = jarFile2.getEntry(str.replace('.', '/') + ".class");
            if (entry == null) {
                return new Resolution.Illegal(str);
            }
            InputStream inputStream = this.jarFile.getInputStream(entry);
            try {
                return new Resolution.Explicit(StreamDrainer.DEFAULT.drain(inputStream));
            } finally {
                inputStream.close();
            }
        }

        public void close() throws IOException {
            this.jarFile.close();
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class ForModuleFile implements ClassFileLocator {
        private static final List<String> BOOT_LOCATIONS = Arrays.asList(new String[]{"jmods", "../jmods"});
        private static final String JMOD_FILE_EXTENSION = ".jmod";
        private final ZipFile zipFile;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.zipFile.equals(((ForModuleFile) obj).zipFile);
        }

        public int hashCode() {
            return 527 + this.zipFile.hashCode();
        }

        public ForModuleFile(ZipFile zipFile2) {
            this.zipFile = zipFile2;
        }

        public static ClassFileLocator ofBootPath() throws IOException {
            File file;
            String replace = System.getProperty("java.home").replace('\\', '/');
            Iterator<String> it = BOOT_LOCATIONS.iterator();
            while (true) {
                if (!it.hasNext()) {
                    file = null;
                    break;
                }
                file = new File(replace, it.next());
                if (file.isDirectory()) {
                    break;
                }
            }
            if (file != null) {
                return ofBootPath(file);
            }
            throw new IllegalStateException("Boot modules do not exist in " + replace + " for any of " + BOOT_LOCATIONS);
        }

        public static ClassFileLocator ofBootPath(File file) throws IOException {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                return NoOp.INSTANCE;
            }
            ArrayList arrayList = new ArrayList(listFiles.length);
            for (File file2 : listFiles) {
                if (file2.isFile()) {
                    arrayList.add(of(file2));
                }
            }
            return new Compound((List<? extends ClassFileLocator>) arrayList);
        }

        public static ClassFileLocator ofModulePath() throws IOException {
            String property = System.getProperty("jdk.module.path");
            if (property == null) {
                return NoOp.INSTANCE;
            }
            return ofModulePath(property);
        }

        public static ClassFileLocator ofModulePath(String str) throws IOException {
            return ofModulePath(str, System.getProperty("user.dir"));
        }

        public static ClassFileLocator ofModulePath(String str, String str2) throws IOException {
            ClassFileLocator classFileLocator;
            ClassFileLocator classFileLocator2;
            ArrayList arrayList = new ArrayList();
            for (String file : Pattern.compile(System.getProperty("path.separator"), 16).split(str)) {
                File file2 = new File(str2, file);
                if (file2.isDirectory()) {
                    File[] listFiles = file2.listFiles();
                    if (listFiles != null) {
                        for (File file3 : listFiles) {
                            if (file3.isDirectory()) {
                                arrayList.add(new ForFolder(file3));
                            } else if (file3.isFile()) {
                                if (file3.getName().endsWith(JMOD_FILE_EXTENSION)) {
                                    classFileLocator2 = of(file3);
                                } else {
                                    classFileLocator2 = ForJarFile.of(file3);
                                }
                                arrayList.add(classFileLocator2);
                            }
                        }
                    }
                } else if (file2.isFile()) {
                    if (file2.getName().endsWith(JMOD_FILE_EXTENSION)) {
                        classFileLocator = of(file2);
                    } else {
                        classFileLocator = ForJarFile.of(file2);
                    }
                    arrayList.add(classFileLocator);
                }
            }
            return new Compound((List<? extends ClassFileLocator>) arrayList);
        }

        public static ClassFileLocator of(File file) throws IOException {
            return new ForModuleFile(new ZipFile(file));
        }

        public Resolution locate(String str) throws IOException {
            ZipFile zipFile2 = this.zipFile;
            ZipEntry entry = zipFile2.getEntry("classes/" + str.replace('.', '/') + ".class");
            if (entry == null) {
                return new Resolution.Illegal(str);
            }
            InputStream inputStream = this.zipFile.getInputStream(entry);
            try {
                return new Resolution.Explicit(StreamDrainer.DEFAULT.drain(inputStream));
            } finally {
                inputStream.close();
            }
        }

        public void close() throws IOException {
            this.zipFile.close();
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class ForFolder implements ClassFileLocator {
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

        public Resolution locate(String str) throws IOException {
            File file = this.folder;
            File file2 = new File(file, str.replace('.', File.separatorChar) + ".class");
            if (!file2.exists()) {
                return new Resolution.Illegal(str);
            }
            FileInputStream fileInputStream = new FileInputStream(file2);
            try {
                return new Resolution.Explicit(StreamDrainer.DEFAULT.drain(fileInputStream));
            } finally {
                fileInputStream.close();
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class ForUrl implements ClassFileLocator {
        private final ClassLoader classLoader;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.classLoader.equals(((ForUrl) obj).classLoader);
        }

        public int hashCode() {
            return 527 + this.classLoader.hashCode();
        }

        public ForUrl(URL... urlArr) {
            this.classLoader = (ClassLoader) AccessController.doPrivileged(new ClassLoaderCreationAction(urlArr));
        }

        public ForUrl(Collection<? extends URL> collection) {
            this((URL[]) collection.toArray(new URL[collection.size()]));
        }

        public Resolution locate(String str) throws IOException {
            return ForClassLoader.locate(this.classLoader, str);
        }

        public void close() throws IOException {
            ClassLoader classLoader2 = this.classLoader;
            if (classLoader2 instanceof Closeable) {
                ((Closeable) classLoader2).close();
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        protected static class ClassLoaderCreationAction implements PrivilegedAction<ClassLoader> {
            private final URL[] url;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && Arrays.equals(this.url, ((ClassLoaderCreationAction) obj).url);
            }

            public int hashCode() {
                return 527 + Arrays.hashCode(this.url);
            }

            protected ClassLoaderCreationAction(URL[] urlArr) {
                this.url = urlArr;
            }

            public ClassLoader run() {
                return new URLClassLoader(this.url, ClassLoadingStrategy.BOOTSTRAP_LOADER);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class AgentBased implements ClassFileLocator {
        private static final Dispatcher DISPATCHER = ((Dispatcher) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE));
        private static final String INSTALLER_TYPE = "net.bytebuddy.agent.Installer";
        private static final String INSTRUMENTATION_GETTER = "getInstrumentation";
        private static final Object STATIC_MEMBER = null;
        private final ClassLoadingDelegate classLoadingDelegate;
        private final Instrumentation instrumentation;

        public void close() {
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            AgentBased agentBased = (AgentBased) obj;
            return this.instrumentation.equals(agentBased.instrumentation) && this.classLoadingDelegate.equals(agentBased.classLoadingDelegate);
        }

        public int hashCode() {
            return ((527 + this.instrumentation.hashCode()) * 31) + this.classLoadingDelegate.hashCode();
        }

        public AgentBased(Instrumentation instrumentation2, ClassLoader classLoader) {
            this(instrumentation2, ClassLoadingDelegate.Default.of(classLoader));
        }

        public AgentBased(Instrumentation instrumentation2, ClassLoadingDelegate classLoadingDelegate2) {
            if (DISPATCHER.isRetransformClassesSupported(instrumentation2)) {
                this.instrumentation = instrumentation2;
                this.classLoadingDelegate = classLoadingDelegate2;
                return;
            }
            throw new IllegalArgumentException(instrumentation2 + " does not support retransformation");
        }

        public static ClassFileLocator fromInstalledAgent(ClassLoader classLoader) {
            try {
                return new AgentBased((Instrumentation) ClassLoader.getSystemClassLoader().loadClass(INSTALLER_TYPE).getMethod(INSTRUMENTATION_GETTER, new Class[0]).invoke(STATIC_MEMBER, new Object[0]), classLoader);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                throw new IllegalStateException("The Byte Buddy agent is not installed or not accessible", e2);
            }
        }

        public static ClassFileLocator of(Instrumentation instrumentation2, Class<?> cls) {
            return new AgentBased(instrumentation2, ClassLoadingDelegate.Explicit.of(cls));
        }

        public Resolution locate(String str) {
            ExtractionClassFileTransformer extractionClassFileTransformer;
            try {
                extractionClassFileTransformer = new ExtractionClassFileTransformer(this.classLoadingDelegate.getClassLoader(), str);
                DISPATCHER.addTransformer(this.instrumentation, extractionClassFileTransformer, true);
                DISPATCHER.retransformClasses(this.instrumentation, new Class[]{this.classLoadingDelegate.locate(str)});
                byte[] binaryRepresentation = extractionClassFileTransformer.getBinaryRepresentation();
                Resolution illegal = binaryRepresentation == null ? new Resolution.Illegal(str) : new Resolution.Explicit(binaryRepresentation);
                this.instrumentation.removeTransformer(extractionClassFileTransformer);
                return illegal;
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
                return new Resolution.Illegal(str);
            } catch (Throwable th) {
                this.instrumentation.removeTransformer(extractionClassFileTransformer);
                throw th;
            }
        }

        protected interface Dispatcher {
            void addTransformer(Instrumentation instrumentation, ClassFileTransformer classFileTransformer, boolean z);

            boolean isRetransformClassesSupported(Instrumentation instrumentation);

            void retransformClasses(Instrumentation instrumentation, Class<?>[] clsArr) throws UnmodifiableClassException;

            public enum CreationAction implements PrivilegedAction<Dispatcher> {
                INSTANCE;

                public Dispatcher run() {
                    try {
                        return new ForJava6CapableVm(Instrumentation.class.getMethod("isRetransformClassesSupported", new Class[0]), Instrumentation.class.getMethod("addTransformer", new Class[]{ClassFileTransformer.class, Boolean.TYPE}), Instrumentation.class.getMethod("retransformClasses", new Class[]{Class[].class}));
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

                public void addTransformer(Instrumentation instrumentation, ClassFileTransformer classFileTransformer, boolean z) {
                    throw new IllegalStateException("The current VM does not support class retransformation");
                }

                public void retransformClasses(Instrumentation instrumentation, Class<?>[] clsArr) {
                    throw new IllegalStateException("The current VM does not support class retransformation");
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForJava6CapableVm implements Dispatcher {
                private final Method addTransformer;
                private final Method isRetransformClassesSupported;
                private final Method retransformClasses;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    ForJava6CapableVm forJava6CapableVm = (ForJava6CapableVm) obj;
                    return this.isRetransformClassesSupported.equals(forJava6CapableVm.isRetransformClassesSupported) && this.addTransformer.equals(forJava6CapableVm.addTransformer) && this.retransformClasses.equals(forJava6CapableVm.retransformClasses);
                }

                public int hashCode() {
                    return ((((527 + this.isRetransformClassesSupported.hashCode()) * 31) + this.addTransformer.hashCode()) * 31) + this.retransformClasses.hashCode();
                }

                protected ForJava6CapableVm(Method method, Method method2, Method method3) {
                    this.isRetransformClassesSupported = method;
                    this.addTransformer = method2;
                    this.retransformClasses = method3;
                }

                public boolean isRetransformClassesSupported(Instrumentation instrumentation) {
                    try {
                        return ((Boolean) this.isRetransformClassesSupported.invoke(instrumentation, new Object[0])).booleanValue();
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.instrument.Instrumentation#isRetransformClassesSupported", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.instrument.Instrumentation#isRetransformClassesSupported", e2.getCause());
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

        public interface ClassLoadingDelegate {
            ClassLoader getClassLoader();

            Class<?> locate(String str) throws ClassNotFoundException;

            @HashCodeAndEqualsPlugin.Enhance
            public static class Default implements ClassLoadingDelegate {
                protected final ClassLoader classLoader;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.classLoader.equals(((Default) obj).classLoader);
                }

                public int hashCode() {
                    return 527 + this.classLoader.hashCode();
                }

                protected Default(ClassLoader classLoader2) {
                    this.classLoader = classLoader2;
                }

                public static ClassLoadingDelegate of(ClassLoader classLoader2) {
                    if (ForDelegatingClassLoader.isDelegating(classLoader2)) {
                        return new ForDelegatingClassLoader(classLoader2);
                    }
                    if (classLoader2 == null) {
                        classLoader2 = ClassLoader.getSystemClassLoader();
                    }
                    return new Default(classLoader2);
                }

                public Class<?> locate(String str) throws ClassNotFoundException {
                    return this.classLoader.loadClass(str);
                }

                public ClassLoader getClassLoader() {
                    return this.classLoader;
                }
            }

            public static class ForDelegatingClassLoader extends Default {
                private static final String DELEGATING_CLASS_LOADER_NAME = "sun.reflect.DelegatingClassLoader";
                private static final Dispatcher.Initializable DISPATCHER = ((Dispatcher.Initializable) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE));
                private static final int ONLY = 0;

                protected ForDelegatingClassLoader(ClassLoader classLoader) {
                    super(classLoader);
                }

                protected static boolean isDelegating(ClassLoader classLoader) {
                    return classLoader != null && classLoader.getClass().getName().equals(DELEGATING_CLASS_LOADER_NAME);
                }

                public Class<?> locate(String str) throws ClassNotFoundException {
                    try {
                        Vector<Class<?>> extract = DISPATCHER.initialize().extract(this.classLoader);
                        if (extract.size() != 1) {
                            return super.locate(str);
                        }
                        Class<?> cls = extract.get(0);
                        if (TypeDescription.ForLoadedType.getName(cls).equals(str)) {
                            return cls;
                        }
                        return super.locate(str);
                    } catch (RuntimeException unused) {
                        return super.locate(str);
                    }
                }

                protected interface Dispatcher {

                    public interface Initializable {
                        Dispatcher initialize();
                    }

                    Vector<Class<?>> extract(ClassLoader classLoader);

                    public enum CreationAction implements PrivilegedAction<Initializable> {
                        INSTANCE;

                        public Initializable run() {
                            try {
                                return new Resolved(ClassLoader.class.getDeclaredField("classes"));
                            } catch (Exception e) {
                                return new Unresolved(e.getMessage());
                            }
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance
                    public static class Resolved implements Dispatcher, Initializable, PrivilegedAction<Dispatcher> {
                        private final Field field;

                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && this.field.equals(((Resolved) obj).field);
                        }

                        public int hashCode() {
                            return 527 + this.field.hashCode();
                        }

                        public Resolved(Field field2) {
                            this.field = field2;
                        }

                        public Dispatcher initialize() {
                            return (Dispatcher) AccessController.doPrivileged(this);
                        }

                        public Vector<Class<?>> extract(ClassLoader classLoader) {
                            try {
                                return (Vector) this.field.get(classLoader);
                            } catch (IllegalAccessException e) {
                                throw new IllegalStateException("Cannot access field", e);
                            }
                        }

                        public Dispatcher run() {
                            this.field.setAccessible(true);
                            return this;
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance
                    public static class Unresolved implements Initializable {
                        private final String message;

                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && this.message.equals(((Unresolved) obj).message);
                        }

                        public int hashCode() {
                            return 527 + this.message.hashCode();
                        }

                        public Unresolved(String str) {
                            this.message = str;
                        }

                        public Dispatcher initialize() {
                            throw new UnsupportedOperationException("Could not locate classes vector: " + this.message);
                        }
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Explicit implements ClassLoadingDelegate {
                private final ClassLoadingDelegate fallbackDelegate;
                private final Map<String, Class<?>> types;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Explicit explicit = (Explicit) obj;
                    return this.fallbackDelegate.equals(explicit.fallbackDelegate) && this.types.equals(explicit.types);
                }

                public int hashCode() {
                    return ((527 + this.fallbackDelegate.hashCode()) * 31) + this.types.hashCode();
                }

                public Explicit(ClassLoader classLoader, Collection<? extends Class<?>> collection) {
                    this(Default.of(classLoader), collection);
                }

                public Explicit(ClassLoadingDelegate classLoadingDelegate, Collection<? extends Class<?>> collection) {
                    this.fallbackDelegate = classLoadingDelegate;
                    this.types = new HashMap();
                    for (Class cls : collection) {
                        this.types.put(TypeDescription.ForLoadedType.getName(cls), cls);
                    }
                }

                public static ClassLoadingDelegate of(Class<?> cls) {
                    return new Explicit(cls.getClassLoader(), (Collection<? extends Class<?>>) Collections.singleton(cls));
                }

                public Class<?> locate(String str) throws ClassNotFoundException {
                    Class<?> cls = this.types.get(str);
                    return cls == null ? this.fallbackDelegate.locate(str) : cls;
                }

                public ClassLoader getClassLoader() {
                    return this.fallbackDelegate.getClassLoader();
                }
            }
        }

        protected static class ExtractionClassFileTransformer implements ClassFileTransformer {
            private static final byte[] DO_NOT_TRANSFORM = null;
            private volatile byte[] binaryRepresentation;
            private final ClassLoader classLoader;
            private final String typeName;

            protected ExtractionClassFileTransformer(ClassLoader classLoader2, String str) {
                this.classLoader = classLoader2;
                this.typeName = str;
            }

            public byte[] transform(ClassLoader classLoader2, String str, Class<?> cls, ProtectionDomain protectionDomain, byte[] bArr) {
                if (str != null && ElementMatchers.isChildOf(this.classLoader).matches(classLoader2) && this.typeName.equals(str.replace('/', '.'))) {
                    this.binaryRepresentation = (byte[]) bArr.clone();
                }
                return DO_NOT_TRANSFORM;
            }

            /* access modifiers changed from: protected */
            public byte[] getBinaryRepresentation() {
                return this.binaryRepresentation;
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class PackageDiscriminating implements ClassFileLocator {
        private final Map<String, ClassFileLocator> classFileLocators;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.classFileLocators.equals(((PackageDiscriminating) obj).classFileLocators);
        }

        public int hashCode() {
            return 527 + this.classFileLocators.hashCode();
        }

        public PackageDiscriminating(Map<String, ClassFileLocator> map) {
            this.classFileLocators = map;
        }

        public Resolution locate(String str) throws IOException {
            String str2;
            int lastIndexOf = str.lastIndexOf(46);
            Map<String, ClassFileLocator> map = this.classFileLocators;
            if (lastIndexOf == -1) {
                str2 = "";
            } else {
                str2 = str.substring(0, lastIndexOf);
            }
            ClassFileLocator classFileLocator = map.get(str2);
            if (classFileLocator == null) {
                return new Resolution.Illegal(str);
            }
            return classFileLocator.locate(str);
        }

        public void close() throws IOException {
            for (ClassFileLocator close : this.classFileLocators.values()) {
                close.close();
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class Compound implements ClassFileLocator, Closeable {
        private final List<ClassFileLocator> classFileLocators;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.classFileLocators.equals(((Compound) obj).classFileLocators);
        }

        public int hashCode() {
            return 527 + this.classFileLocators.hashCode();
        }

        public Compound(ClassFileLocator... classFileLocatorArr) {
            this((List<? extends ClassFileLocator>) Arrays.asList(classFileLocatorArr));
        }

        public Compound(List<? extends ClassFileLocator> list) {
            this.classFileLocators = new ArrayList();
            for (ClassFileLocator classFileLocator : list) {
                if (classFileLocator instanceof Compound) {
                    this.classFileLocators.addAll(((Compound) classFileLocator).classFileLocators);
                } else if (!(classFileLocator instanceof NoOp)) {
                    this.classFileLocators.add(classFileLocator);
                }
            }
        }

        public Resolution locate(String str) throws IOException {
            for (ClassFileLocator locate : this.classFileLocators) {
                Resolution locate2 = locate.locate(str);
                if (locate2.isResolved()) {
                    return locate2;
                }
            }
            return new Resolution.Illegal(str);
        }

        public void close() throws IOException {
            for (ClassFileLocator close : this.classFileLocators) {
                close.close();
            }
        }
    }
}
