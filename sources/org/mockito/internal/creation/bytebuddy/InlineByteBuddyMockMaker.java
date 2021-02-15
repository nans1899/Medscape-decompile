package org.mockito.internal.creation.bytebuddy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Modifier;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import javax.tools.ToolProvider;
import net.bytebuddy.agent.ByteBuddyAgent;
import org.mockito.Incubating;
import org.mockito.creation.instance.InstantiationException;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.exceptions.base.MockitoInitializationException;
import org.mockito.internal.configuration.plugins.Plugins;
import org.mockito.internal.util.Platform;
import org.mockito.internal.util.StringUtil;
import org.mockito.internal.util.concurrent.WeakConcurrentMap;
import org.mockito.invocation.MockHandler;
import org.mockito.mock.MockCreationSettings;
import org.mockito.plugins.InlineMockMaker;
import org.mockito.plugins.MockMaker;

@Incubating
public class InlineByteBuddyMockMaker implements ClassCreatingMockMaker, InlineMockMaker {
    private static final Throwable INITIALIZATION_ERROR;
    /* access modifiers changed from: private */
    public static final Instrumentation INSTRUMENTATION;
    private final BytecodeGenerator bytecodeGenerator;
    private final WeakConcurrentMap<Object, MockMethodInterceptor> mocks = new WeakConcurrentMap.WithInlinedExpunction();

    static {
        InputStream resourceAsStream;
        Class<InlineByteBuddyMockMaker> cls = InlineByteBuddyMockMaker.class;
        Instrumentation instrumentation = null;
        try {
            Instrumentation install = ByteBuddyAgent.install();
            if (install.isRetransformClassesSupported()) {
                File createTempFile = File.createTempFile("mockitoboot", ".jar");
                createTempFile.deleteOnExit();
                JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(createTempFile));
                try {
                    resourceAsStream = cls.getClassLoader().getResourceAsStream("org/mockito/internal/creation/bytebuddy/inject/MockMethodDispatcher" + ".raw");
                    if (resourceAsStream != null) {
                        jarOutputStream.putNextEntry(new JarEntry("org/mockito/internal/creation/bytebuddy/inject/MockMethodDispatcher" + ".class"));
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = resourceAsStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            jarOutputStream.write(bArr, 0, read);
                        }
                        resourceAsStream.close();
                        jarOutputStream.closeEntry();
                        jarOutputStream.close();
                        install.appendToBootstrapClassLoaderSearch(new JarFile(createTempFile));
                        Class.forName("org.mockito.internal.creation.bytebuddy.inject.MockMethodDispatcher", false, (ClassLoader) null);
                        th = null;
                        instrumentation = install;
                        INSTRUMENTATION = instrumentation;
                        INITIALIZATION_ERROR = th;
                        return;
                    }
                    throw new IllegalStateException(StringUtil.join("The MockMethodDispatcher class file is not locatable: " + "org/mockito/internal/creation/bytebuddy/inject/MockMethodDispatcher" + ".raw", "", "The class loader responsible for looking up the resource: " + cls.getClassLoader()));
                } catch (Throwable th) {
                    jarOutputStream.close();
                    throw th;
                }
            } else {
                throw new IllegalStateException(StringUtil.join("Byte Buddy requires retransformation for creating inline mocks. This feature is unavailable on the current VM.", "", "You cannot use this mock maker on this VM"));
            }
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(StringUtil.join("Mockito failed to inject the MockMethodDispatcher class into the bootstrap class loader", "", "It seems like your current VM does not support the instrumentation API correctly."), e);
        } catch (IOException e2) {
            try {
                throw new IllegalStateException(StringUtil.join("Mockito could not self-attach a Java agent to the current VM. This feature is required for inline mocking.", "This error occured due to an I/O error during the creation of this agent: " + e2, "", "Potentially, the current VM does not support the instrumentation API correctly"), e2);
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    public InlineByteBuddyMockMaker() {
        if (INITIALIZATION_ERROR != null) {
            Object[] objArr = new Object[3];
            objArr[0] = "Could not initialize inline Byte Buddy mock maker. (This mock maker is not supported on Android.)";
            objArr[1] = ToolProvider.getSystemJavaCompiler() == null ? "Are you running a JRE instead of a JDK? The inline mock maker needs to be run on a JDK.\n" : "";
            objArr[2] = Platform.describe();
            throw new MockitoInitializationException(StringUtil.join(objArr), INITIALIZATION_ERROR);
        }
        this.bytecodeGenerator = new TypeCachingBytecodeGenerator(new InlineBytecodeGenerator(INSTRUMENTATION, this.mocks), true);
    }

    public <T> T createMock(MockCreationSettings<T> mockCreationSettings, MockHandler mockHandler) {
        Class<? extends T> createMockType = createMockType(mockCreationSettings);
        try {
            T newInstance = Plugins.getInstantiatorProvider().getInstantiator(mockCreationSettings).newInstance(createMockType);
            MockMethodInterceptor mockMethodInterceptor = new MockMethodInterceptor(mockHandler, mockCreationSettings);
            this.mocks.put(newInstance, mockMethodInterceptor);
            if (newInstance instanceof MockAccess) {
                ((MockAccess) newInstance).setMockitoInterceptor(mockMethodInterceptor);
            }
            return newInstance;
        } catch (InstantiationException e) {
            throw new MockitoException("Unable to create mock instance of type '" + createMockType.getSimpleName() + "'", e);
        }
    }

    public <T> Class<? extends T> createMockType(MockCreationSettings<T> mockCreationSettings) {
        try {
            return this.bytecodeGenerator.mockClass(MockFeatures.withMockFeatures(mockCreationSettings.getTypeToMock(), mockCreationSettings.getExtraInterfaces(), mockCreationSettings.getSerializableMode(), mockCreationSettings.isStripAnnotations()));
        } catch (Exception e) {
            throw prettifyFailure(mockCreationSettings, e);
        }
    }

    private <T> RuntimeException prettifyFailure(MockCreationSettings<T> mockCreationSettings, Exception exc) {
        String str;
        Exception exc2 = exc;
        if (mockCreationSettings.getTypeToMock().isArray()) {
            throw new MockitoException(StringUtil.join("Arrays cannot be mocked: " + mockCreationSettings.getTypeToMock() + ".", ""), exc2);
        } else if (Modifier.isFinal(mockCreationSettings.getTypeToMock().getModifiers())) {
            throw new MockitoException(StringUtil.join("Mockito cannot mock this class: " + mockCreationSettings.getTypeToMock() + ".", "Can not mock final classes with the following settings :", " - explicit serialization (e.g. withSettings().serializable())", " - extra interfaces (e.g. withSettings().extraInterfaces(...))", "", "You are seeing this disclaimer because Mockito is configured to create inlined mocks.", "You can learn about inline mocks and their limitations under item #39 of the Mockito class javadoc.", "", "Underlying exception : " + exc2), exc2);
        } else if (!Modifier.isPrivate(mockCreationSettings.getTypeToMock().getModifiers())) {
            Object[] objArr = new Object[11];
            objArr[0] = "Mockito cannot mock this class: " + mockCreationSettings.getTypeToMock() + ".";
            objArr[1] = "";
            objArr[2] = "If you're not sure why you're getting this error, please report to the mailing list.";
            objArr[3] = "";
            if (Platform.isJava8BelowUpdate45()) {
                str = "Java 8 early builds have bugs that were addressed in Java 1.8.0_45, please update your JDK!\n";
            } else {
                str = "";
            }
            objArr[4] = Platform.warnForVM("IBM J9 VM", "Early IBM virtual machine are known to have issues with Mockito, please upgrade to an up-to-date version.\n", "Hotspot", str);
            objArr[5] = Platform.describe();
            objArr[6] = "";
            objArr[7] = "You are seeing this disclaimer because Mockito is configured to create inlined mocks.";
            objArr[8] = "You can learn about inline mocks and their limitations under item #39 of the Mockito class javadoc.";
            objArr[9] = "";
            objArr[10] = "Underlying exception : " + exc2;
            throw new MockitoException(StringUtil.join(objArr), exc2);
        } else {
            throw new MockitoException(StringUtil.join("Mockito cannot mock this class: " + mockCreationSettings.getTypeToMock() + ".", "Most likely it is a private class that is not visible by Mockito", "", "You are seeing this disclaimer because Mockito is configured to create inlined mocks.", "You can learn about inline mocks and their limitations under item #39 of the Mockito class javadoc.", ""), exc2);
        }
    }

    public MockHandler getHandler(Object obj) {
        MockMethodInterceptor mockMethodInterceptor = this.mocks.get(obj);
        if (mockMethodInterceptor == null) {
            return null;
        }
        return mockMethodInterceptor.handler;
    }

    public void resetMock(Object obj, MockHandler mockHandler, MockCreationSettings mockCreationSettings) {
        MockMethodInterceptor mockMethodInterceptor = new MockMethodInterceptor(mockHandler, mockCreationSettings);
        this.mocks.put(obj, mockMethodInterceptor);
        if (obj instanceof MockAccess) {
            ((MockAccess) obj).setMockitoInterceptor(mockMethodInterceptor);
        }
    }

    public void clearMock(Object obj) {
        this.mocks.remove(obj);
    }

    public void clearAllMocks() {
        this.mocks.clear();
    }

    public MockMaker.TypeMockability isTypeMockable(final Class<?> cls) {
        return new MockMaker.TypeMockability() {
            public boolean mockable() {
                return InlineByteBuddyMockMaker.INSTRUMENTATION.isModifiableClass(cls) && !InlineBytecodeGenerator.EXCLUDES.contains(cls);
            }

            public String nonMockableReason() {
                if (mockable()) {
                    return "";
                }
                if (cls.isPrimitive()) {
                    return "primitive type";
                }
                return InlineBytecodeGenerator.EXCLUDES.contains(cls) ? "Cannot mock wrapper types, String.class or Class.class" : "VM does not not support modification of given type";
            }
        };
    }
}
