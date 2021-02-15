package net.bytebuddy.agent;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.Instrumentation;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import net.bytebuddy.agent.VirtualMachine;

public class ByteBuddyAgent {
    private static final String AGENT_CLASS_PROPERTY = "Agent-Class";
    private static final String ATTACHER_FILE_NAME = "byteBuddyAttacher";
    private static final AttachmentTypeEvaluator ATTACHMENT_TYPE_EVALUATOR = ((AttachmentTypeEvaluator) AccessController.doPrivileged(AttachmentTypeEvaluator.InstallationAction.INSTANCE));
    /* access modifiers changed from: private */
    public static final ClassLoader BOOTSTRAP_CLASS_LOADER = null;
    private static final int BUFFER_SIZE = 1024;
    /* access modifiers changed from: private */
    public static final File CANNOT_SELF_RESOLVE = null;
    private static final String CAN_REDEFINE_CLASSES_PROPERTY = "Can-Redefine-Classes";
    private static final String CAN_RETRANSFORM_CLASSES_PROPERTY = "Can-Retransform-Classes";
    private static final String CAN_SET_NATIVE_METHOD_PREFIX = "Can-Set-Native-Method-Prefix";
    private static final String CLASS_FILE_EXTENSION = ".class";
    private static final String CLASS_PATH_ARGUMENT = "-cp";
    private static final int END_OF_FILE = -1;
    private static final String FILE_PROTOCOL = "file";
    private static final String INSTRUMENTATION_METHOD = "getInstrumentation";
    private static final String JAR_FILE_EXTENSION = ".jar";
    private static final String JAVA_HOME = "java.home";
    private static final String MANIFEST_VERSION_VALUE = "1.0";
    private static final String OS_NAME = "os.name";
    private static final int START_INDEX = 0;
    /* access modifiers changed from: private */
    public static final Object STATIC_MEMBER = null;
    private static final int SUCCESSFUL_ATTACH = 0;
    private static final Instrumentation UNAVAILABLE = null;
    private static final String WITHOUT_ARGUMENT = null;

    private ByteBuddyAgent() {
        throw new UnsupportedOperationException("This class is a utility class and not supposed to be instantiated");
    }

    public static Instrumentation getInstrumentation() {
        Instrumentation doGetInstrumentation = doGetInstrumentation();
        if (doGetInstrumentation != null) {
            return doGetInstrumentation;
        }
        throw new IllegalStateException("The Byte Buddy agent is not initialized");
    }

    public static void attach(File file, String str) {
        attach(file, str, WITHOUT_ARGUMENT);
    }

    public static void attach(File file, String str, String str2) {
        attach(file, str, str2, AttachmentProvider.DEFAULT);
    }

    public static void attach(File file, String str, AttachmentProvider attachmentProvider) {
        attach(file, str, WITHOUT_ARGUMENT, attachmentProvider);
    }

    public static void attach(File file, String str, String str2, AttachmentProvider attachmentProvider) {
        install(attachmentProvider, str, str2, new AgentProvider.ForExistingAgent(file));
    }

    public static void attach(File file, ProcessProvider processProvider) {
        attach(file, processProvider, WITHOUT_ARGUMENT);
    }

    public static void attach(File file, ProcessProvider processProvider, String str) {
        attach(file, processProvider, str, AttachmentProvider.DEFAULT);
    }

    public static void attach(File file, ProcessProvider processProvider, AttachmentProvider attachmentProvider) {
        attach(file, processProvider, WITHOUT_ARGUMENT, attachmentProvider);
    }

    public static void attach(File file, ProcessProvider processProvider, String str, AttachmentProvider attachmentProvider) {
        install(attachmentProvider, processProvider.resolve(), str, new AgentProvider.ForExistingAgent(file));
    }

    public static Instrumentation install() {
        return install(AttachmentProvider.DEFAULT);
    }

    public static Instrumentation install(AttachmentProvider attachmentProvider) {
        return install(attachmentProvider, ProcessProvider.ForCurrentVm.INSTANCE);
    }

    public static Instrumentation install(ProcessProvider processProvider) {
        return install(AttachmentProvider.DEFAULT, processProvider);
    }

    public static synchronized Instrumentation install(AttachmentProvider attachmentProvider, ProcessProvider processProvider) {
        synchronized (ByteBuddyAgent.class) {
            Instrumentation doGetInstrumentation = doGetInstrumentation();
            if (doGetInstrumentation != null) {
                return doGetInstrumentation;
            }
            install(attachmentProvider, processProvider.resolve(), WITHOUT_ARGUMENT, AgentProvider.ForByteBuddyAgent.INSTANCE);
            Instrumentation doGetInstrumentation2 = doGetInstrumentation();
            return doGetInstrumentation2;
        }
    }

    private static void install(AttachmentProvider attachmentProvider, String str, String str2, AgentProvider agentProvider) {
        AttachmentProvider.Accessor attempt = attachmentProvider.attempt();
        if (attempt.isAvailable()) {
            try {
                if (ATTACHMENT_TYPE_EVALUATOR.requiresExternalAttachment(str)) {
                    installExternal(attempt.getExternalAttachment(), str, agentProvider.resolve(), str2);
                } else {
                    Attacher.install(attempt.getVirtualMachineType(), str, agentProvider.resolve().getAbsolutePath(), str2);
                }
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                throw new IllegalStateException("Error during attachment using: " + attachmentProvider, e2);
            }
        } else {
            throw new IllegalStateException("No compatible attachment provider is available");
        }
    }

    private static void installExternal(AttachmentProvider.Accessor.ExternalAttachment externalAttachment, String str, File file, String str2) throws Exception {
        JarOutputStream jarOutputStream;
        String str3 = "";
        File trySelfResolve = trySelfResolve();
        File file2 = null;
        if (trySelfResolve == null) {
            try {
                InputStream resourceAsStream = Attacher.class.getResourceAsStream('/' + Attacher.class.getName().replace('.', '/') + ".class");
                if (resourceAsStream != null) {
                    try {
                        file2 = File.createTempFile(ATTACHER_FILE_NAME, JAR_FILE_EXTENSION);
                        jarOutputStream = new JarOutputStream(new FileOutputStream(file2));
                        jarOutputStream.putNextEntry(new JarEntry(Attacher.class.getName().replace('.', '/') + ".class"));
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = resourceAsStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            jarOutputStream.write(bArr, 0, read);
                        }
                        jarOutputStream.closeEntry();
                        jarOutputStream.close();
                        resourceAsStream.close();
                    } catch (Throwable th) {
                        resourceAsStream.close();
                        throw th;
                    }
                } else {
                    throw new IllegalStateException("Cannot locate class file for Byte Buddy installation process");
                }
            } catch (Throwable th2) {
                if (file2 != null && !file2.delete()) {
                    file2.deleteOnExit();
                }
                throw th2;
            }
        }
        StringBuilder sb = new StringBuilder();
        if (trySelfResolve == null) {
            trySelfResolve = file2;
        }
        sb.append(quote(trySelfResolve.getCanonicalPath()));
        for (File canonicalPath : externalAttachment.getClassPath()) {
            sb.append(File.pathSeparatorChar);
            sb.append(quote(canonicalPath.getCanonicalPath()));
        }
        String[] strArr = new String[8];
        StringBuilder sb2 = new StringBuilder();
        sb2.append(System.getProperty(JAVA_HOME));
        sb2.append(File.separatorChar);
        sb2.append("bin");
        sb2.append(File.separatorChar);
        sb2.append(System.getProperty(OS_NAME, str3).toLowerCase(Locale.US).contains("windows") ? "java.exe" : "java");
        strArr[0] = quote(sb2.toString());
        strArr[1] = CLASS_PATH_ARGUMENT;
        strArr[2] = sb.toString();
        strArr[3] = Attacher.class.getName();
        strArr[4] = externalAttachment.getVirtualMachineType();
        strArr[5] = str;
        strArr[6] = quote(file.getAbsolutePath());
        if (str2 != null) {
            str3 = "=" + str2;
        }
        strArr[7] = str3;
        if (new ProcessBuilder(strArr).start().waitFor() != 0) {
            throw new IllegalStateException("Could not self-attach to current VM using external process");
        } else if (file2 != null && !file2.delete()) {
            file2.deleteOnExit();
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:17|18|19) */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003a, code lost:
        return new java.io.File(r0.getPath());
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0031 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.io.File trySelfResolve() {
        /*
            java.lang.Class<net.bytebuddy.agent.Attacher> r0 = net.bytebuddy.agent.Attacher.class
            java.security.ProtectionDomain r0 = r0.getProtectionDomain()     // Catch:{ Exception -> 0x003b }
            if (r0 != 0) goto L_0x000b
            java.io.File r0 = CANNOT_SELF_RESOLVE     // Catch:{ Exception -> 0x003b }
            return r0
        L_0x000b:
            java.security.CodeSource r0 = r0.getCodeSource()     // Catch:{ Exception -> 0x003b }
            if (r0 != 0) goto L_0x0014
            java.io.File r0 = CANNOT_SELF_RESOLVE     // Catch:{ Exception -> 0x003b }
            return r0
        L_0x0014:
            java.net.URL r0 = r0.getLocation()     // Catch:{ Exception -> 0x003b }
            java.lang.String r1 = r0.getProtocol()     // Catch:{ Exception -> 0x003b }
            java.lang.String r2 = "file"
            boolean r1 = r1.equals(r2)     // Catch:{ Exception -> 0x003b }
            if (r1 != 0) goto L_0x0027
            java.io.File r0 = CANNOT_SELF_RESOLVE     // Catch:{ Exception -> 0x003b }
            return r0
        L_0x0027:
            java.io.File r1 = new java.io.File     // Catch:{ URISyntaxException -> 0x0031 }
            java.net.URI r2 = r0.toURI()     // Catch:{ URISyntaxException -> 0x0031 }
            r1.<init>(r2)     // Catch:{ URISyntaxException -> 0x0031 }
            return r1
        L_0x0031:
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x003b }
            java.lang.String r0 = r0.getPath()     // Catch:{ Exception -> 0x003b }
            r1.<init>(r0)     // Catch:{ Exception -> 0x003b }
            return r1
        L_0x003b:
            java.io.File r0 = CANNOT_SELF_RESOLVE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.agent.ByteBuddyAgent.trySelfResolve():java.io.File");
    }

    private static String quote(String str) {
        if (!str.contains(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)) {
            return str;
        }
        return '\"' + str + '\"';
    }

    private static Instrumentation doGetInstrumentation() {
        try {
            return (Instrumentation) ClassLoader.getSystemClassLoader().loadClass(Installer.class.getName()).getMethod(INSTRUMENTATION_METHOD, new Class[0]).invoke(STATIC_MEMBER, new Object[0]);
        } catch (Exception unused) {
            return UNAVAILABLE;
        }
    }

    public interface AttachmentProvider {
        public static final AttachmentProvider DEFAULT = new Compound(ForModularizedVm.INSTANCE, ForJ9Vm.INSTANCE, ForStandardToolsJarVm.JVM_ROOT, ForStandardToolsJarVm.JDK_ROOT, ForStandardToolsJarVm.MACINTOSH, ForUserDefinedToolsJar.INSTANCE, ForUnixHotSpotVm.INSTANCE);

        Accessor attempt();

        public interface Accessor {
            public static final String VIRTUAL_MACHINE_TYPE_NAME = "com.sun.tools.attach.VirtualMachine";
            public static final String VIRTUAL_MACHINE_TYPE_NAME_J9 = "com.ibm.tools.attach.VirtualMachine";

            ExternalAttachment getExternalAttachment();

            Class<?> getVirtualMachineType();

            boolean isAvailable();

            public enum Unavailable implements Accessor {
                INSTANCE;

                public boolean isAvailable() {
                    return false;
                }

                public Class<?> getVirtualMachineType() {
                    throw new IllegalStateException("Cannot read the virtual machine type for an unavailable accessor");
                }

                public ExternalAttachment getExternalAttachment() {
                    throw new IllegalStateException("Cannot read the virtual machine type for an unavailable accessor");
                }
            }

            public static class ExternalAttachment {
                private final List<File> classPath;
                private final String virtualMachineType;

                public ExternalAttachment(String str, List<File> list) {
                    this.virtualMachineType = str;
                    this.classPath = list;
                }

                public String getVirtualMachineType() {
                    return this.virtualMachineType;
                }

                public List<File> getClassPath() {
                    return this.classPath;
                }
            }

            public static abstract class Simple implements Accessor {
                protected final Class<?> virtualMachineType;

                public boolean isAvailable() {
                    return true;
                }

                protected Simple(Class<?> cls) {
                    this.virtualMachineType = cls;
                }

                public static Accessor of(ClassLoader classLoader, File... fileArr) {
                    try {
                        return new WithExternalAttachment(classLoader.loadClass(Accessor.VIRTUAL_MACHINE_TYPE_NAME), Arrays.asList(fileArr));
                    } catch (ClassNotFoundException unused) {
                        return Unavailable.INSTANCE;
                    }
                }

                public static Accessor ofJ9() {
                    try {
                        return new WithExternalAttachment(ClassLoader.getSystemClassLoader().loadClass(Accessor.VIRTUAL_MACHINE_TYPE_NAME_J9), Collections.emptyList());
                    } catch (ClassNotFoundException unused) {
                        return Unavailable.INSTANCE;
                    }
                }

                public Class<?> getVirtualMachineType() {
                    return this.virtualMachineType;
                }

                protected static class WithExternalAttachment extends Simple {
                    private final List<File> classPath;

                    public WithExternalAttachment(Class<?> cls, List<File> list) {
                        super(cls);
                        this.classPath = list;
                    }

                    public ExternalAttachment getExternalAttachment() {
                        return new ExternalAttachment(this.virtualMachineType.getName(), this.classPath);
                    }
                }

                protected static class WithoutExternalAttachment extends Simple {
                    public WithoutExternalAttachment(Class<?> cls) {
                        super(cls);
                    }

                    public ExternalAttachment getExternalAttachment() {
                        throw new IllegalStateException("Cannot read the virtual machine type for an unavailable accessor");
                    }
                }
            }
        }

        public enum ForModularizedVm implements AttachmentProvider {
            INSTANCE;

            public Accessor attempt() {
                return Accessor.Simple.of(ClassLoader.getSystemClassLoader(), new File[0]);
            }
        }

        public enum ForJ9Vm implements AttachmentProvider {
            INSTANCE;

            public Accessor attempt() {
                return Accessor.Simple.ofJ9();
            }
        }

        public enum ForStandardToolsJarVm implements AttachmentProvider {
            JVM_ROOT("../lib/tools.jar"),
            JDK_ROOT("lib/tools.jar"),
            MACINTOSH("../Classes/classes.jar");
            
            private static final String JAVA_HOME_PROPERTY = "java.home";
            private final String toolsJarPath;

            private ForStandardToolsJarVm(String str) {
                this.toolsJarPath = str;
            }

            public Accessor attempt() {
                File file = new File(System.getProperty(JAVA_HOME_PROPERTY), this.toolsJarPath);
                try {
                    if (!file.isFile() || !file.canRead()) {
                        return Accessor.Unavailable.INSTANCE;
                    }
                    return Accessor.Simple.of(new URLClassLoader(new URL[]{file.toURI().toURL()}, ByteBuddyAgent.BOOTSTRAP_CLASS_LOADER), file);
                } catch (MalformedURLException unused) {
                    throw new IllegalStateException("Could not represent " + file + " as URL");
                }
            }
        }

        public enum ForUserDefinedToolsJar implements AttachmentProvider {
            INSTANCE;
            
            public static final String PROPERTY = "net.bytebuddy.agent.toolsjar";

            public Accessor attempt() {
                String property = System.getProperty(PROPERTY);
                if (property == null) {
                    return Accessor.Unavailable.INSTANCE;
                }
                File file = new File(property);
                try {
                    return Accessor.Simple.of(new URLClassLoader(new URL[]{file.toURI().toURL()}, ByteBuddyAgent.BOOTSTRAP_CLASS_LOADER), file);
                } catch (MalformedURLException unused) {
                    throw new IllegalStateException("Could not represent " + file + " as URL");
                }
            }
        }

        public enum ForUnixHotSpotVm implements AttachmentProvider {
            INSTANCE;

            public Accessor attempt() {
                try {
                    return new Accessor.Simple.WithoutExternalAttachment(VirtualMachine.ForHotSpot.OnUnix.assertAvailability());
                } catch (Throwable unused) {
                    return Accessor.Unavailable.INSTANCE;
                }
            }
        }

        public static class Compound implements AttachmentProvider {
            private final List<AttachmentProvider> attachmentProviders;

            public Compound(AttachmentProvider... attachmentProviderArr) {
                this((List<? extends AttachmentProvider>) Arrays.asList(attachmentProviderArr));
            }

            public Compound(List<? extends AttachmentProvider> list) {
                this.attachmentProviders = new ArrayList();
                for (AttachmentProvider attachmentProvider : list) {
                    if (attachmentProvider instanceof Compound) {
                        this.attachmentProviders.addAll(((Compound) attachmentProvider).attachmentProviders);
                    } else {
                        this.attachmentProviders.add(attachmentProvider);
                    }
                }
            }

            public Accessor attempt() {
                for (AttachmentProvider attempt : this.attachmentProviders) {
                    Accessor attempt2 = attempt.attempt();
                    if (attempt2.isAvailable()) {
                        return attempt2;
                    }
                }
                return Accessor.Unavailable.INSTANCE;
            }
        }
    }

    public interface ProcessProvider {
        String resolve();

        public enum ForCurrentVm implements ProcessProvider {
            INSTANCE;
            
            private final ProcessProvider dispatcher;

            public String resolve() {
                return this.dispatcher.resolve();
            }

            protected enum ForLegacyVm implements ProcessProvider {
                INSTANCE;

                public String resolve() {
                    String name = ManagementFactory.getRuntimeMXBean().getName();
                    int indexOf = name.indexOf(64);
                    if (indexOf != -1) {
                        return name.substring(0, indexOf);
                    }
                    throw new IllegalStateException("Cannot extract process id from runtime management bean");
                }
            }

            protected static class ForJava9CapableVm implements ProcessProvider {
                private final Method current;
                private final Method pid;

                protected ForJava9CapableVm(Method method, Method method2) {
                    this.current = method;
                    this.pid = method2;
                }

                public static ProcessProvider make() {
                    try {
                        return new ForJava9CapableVm(Class.forName("java.lang.ProcessHandle").getMethod("current", new Class[0]), Class.forName("java.lang.ProcessHandle").getMethod("pid", new Class[0]));
                    } catch (Exception unused) {
                        return ForLegacyVm.INSTANCE;
                    }
                }

                public String resolve() {
                    try {
                        return this.pid.invoke(this.current.invoke(ByteBuddyAgent.STATIC_MEMBER, new Object[0]), new Object[0]).toString();
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access Java 9 process API", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error when accessing Java 9 process API", e2.getCause());
                    }
                }
            }
        }
    }

    protected interface AgentProvider {
        File resolve() throws IOException;

        public enum ForByteBuddyAgent implements AgentProvider {
            INSTANCE;
            
            private static final String AGENT_FILE_NAME = "byteBuddyAgent";

            private static File trySelfResolve() throws IOException {
                File file;
                ProtectionDomain protectionDomain = Installer.class.getProtectionDomain();
                if (protectionDomain == null) {
                    return ByteBuddyAgent.CANNOT_SELF_RESOLVE;
                }
                CodeSource codeSource = protectionDomain.getCodeSource();
                if (codeSource == null) {
                    return ByteBuddyAgent.CANNOT_SELF_RESOLVE;
                }
                URL location = codeSource.getLocation();
                if (!location.getProtocol().equals(ByteBuddyAgent.FILE_PROTOCOL)) {
                    return ByteBuddyAgent.CANNOT_SELF_RESOLVE;
                }
                try {
                    file = new File(location.toURI());
                } catch (URISyntaxException unused) {
                    file = new File(location.getPath());
                }
                if (!file.isFile() || !file.canRead()) {
                    return ByteBuddyAgent.CANNOT_SELF_RESOLVE;
                }
                JarInputStream jarInputStream = new JarInputStream(new FileInputStream(file));
                try {
                    Manifest manifest = jarInputStream.getManifest();
                    if (manifest == null) {
                        return ByteBuddyAgent.CANNOT_SELF_RESOLVE;
                    }
                    Attributes mainAttributes = manifest.getMainAttributes();
                    if (mainAttributes == null) {
                        File access$200 = ByteBuddyAgent.CANNOT_SELF_RESOLVE;
                        jarInputStream.close();
                        return access$200;
                    } else if (!Installer.class.getName().equals(mainAttributes.getValue(ByteBuddyAgent.AGENT_CLASS_PROPERTY)) || !Boolean.parseBoolean(mainAttributes.getValue(ByteBuddyAgent.CAN_REDEFINE_CLASSES_PROPERTY)) || !Boolean.parseBoolean(mainAttributes.getValue(ByteBuddyAgent.CAN_RETRANSFORM_CLASSES_PROPERTY)) || !Boolean.parseBoolean(mainAttributes.getValue(ByteBuddyAgent.CAN_SET_NATIVE_METHOD_PREFIX))) {
                        File access$2002 = ByteBuddyAgent.CANNOT_SELF_RESOLVE;
                        jarInputStream.close();
                        return access$2002;
                    } else {
                        jarInputStream.close();
                        return file;
                    }
                } finally {
                    jarInputStream.close();
                }
            }

            private static File createJarFile() throws IOException {
                JarOutputStream jarOutputStream;
                InputStream resourceAsStream = Installer.class.getResourceAsStream('/' + Installer.class.getName().replace('.', '/') + ".class");
                if (resourceAsStream != null) {
                    try {
                        File createTempFile = File.createTempFile(AGENT_FILE_NAME, ByteBuddyAgent.JAR_FILE_EXTENSION);
                        createTempFile.deleteOnExit();
                        Manifest manifest = new Manifest();
                        manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
                        manifest.getMainAttributes().put(new Attributes.Name(ByteBuddyAgent.AGENT_CLASS_PROPERTY), Installer.class.getName());
                        manifest.getMainAttributes().put(new Attributes.Name(ByteBuddyAgent.CAN_REDEFINE_CLASSES_PROPERTY), Boolean.TRUE.toString());
                        manifest.getMainAttributes().put(new Attributes.Name(ByteBuddyAgent.CAN_RETRANSFORM_CLASSES_PROPERTY), Boolean.TRUE.toString());
                        manifest.getMainAttributes().put(new Attributes.Name(ByteBuddyAgent.CAN_SET_NATIVE_METHOD_PREFIX), Boolean.TRUE.toString());
                        jarOutputStream = new JarOutputStream(new FileOutputStream(createTempFile), manifest);
                        jarOutputStream.putNextEntry(new JarEntry(Installer.class.getName().replace('.', '/') + ".class"));
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = resourceAsStream.read(bArr);
                            if (read != -1) {
                                jarOutputStream.write(bArr, 0, read);
                            } else {
                                jarOutputStream.closeEntry();
                                jarOutputStream.close();
                                resourceAsStream.close();
                                return createTempFile;
                            }
                        }
                    } catch (Throwable th) {
                        resourceAsStream.close();
                        throw th;
                    }
                } else {
                    throw new IllegalStateException("Cannot locate class file for Byte Buddy installer");
                }
            }

            public File resolve() throws IOException {
                try {
                    File trySelfResolve = trySelfResolve();
                    return trySelfResolve == null ? createJarFile() : trySelfResolve;
                } catch (Exception unused) {
                    return createJarFile();
                }
            }
        }

        public static class ForExistingAgent implements AgentProvider {
            private File agent;

            protected ForExistingAgent(File file) {
                this.agent = file;
            }

            public File resolve() {
                return this.agent;
            }
        }
    }

    protected interface AttachmentTypeEvaluator {

        public enum Disabled implements AttachmentTypeEvaluator {
            INSTANCE;

            public boolean requiresExternalAttachment(String str) {
                return false;
            }
        }

        boolean requiresExternalAttachment(String str);

        public enum InstallationAction implements PrivilegedAction<AttachmentTypeEvaluator> {
            INSTANCE;
            
            private static final String JDK_ALLOW_SELF_ATTACH = "jdk.attach.allowAttachSelf";

            public AttachmentTypeEvaluator run() {
                try {
                    if (Boolean.getBoolean(JDK_ALLOW_SELF_ATTACH)) {
                        return Disabled.INSTANCE;
                    }
                    return new ForJava9CapableVm(Class.forName("java.lang.ProcessHandle").getMethod("current", new Class[0]), Class.forName("java.lang.ProcessHandle").getMethod("pid", new Class[0]));
                } catch (Exception unused) {
                    return Disabled.INSTANCE;
                }
            }
        }

        public static class ForJava9CapableVm implements AttachmentTypeEvaluator {
            private final Method current;
            private final Method pid;

            protected ForJava9CapableVm(Method method, Method method2) {
                this.current = method;
                this.pid = method2;
            }

            public boolean requiresExternalAttachment(String str) {
                try {
                    return this.pid.invoke(this.current.invoke(ByteBuddyAgent.STATIC_MEMBER, new Object[0]), new Object[0]).toString().equals(str);
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access Java 9 process API", e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Error when accessing Java 9 process API", e2.getCause());
                }
            }
        }
    }
}
