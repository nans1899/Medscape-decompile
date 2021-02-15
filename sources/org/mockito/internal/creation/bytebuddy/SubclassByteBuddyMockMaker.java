package org.mockito.internal.creation.bytebuddy;

import java.lang.reflect.Modifier;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.util.Platform;
import org.mockito.internal.util.StringUtil;
import org.mockito.invocation.MockHandler;
import org.mockito.mock.MockCreationSettings;
import org.mockito.plugins.MockMaker;

public class SubclassByteBuddyMockMaker implements ClassCreatingMockMaker {
    private final BytecodeGenerator cachingMockBytecodeGenerator;

    public SubclassByteBuddyMockMaker() {
        this(new SubclassInjectionLoader());
    }

    public SubclassByteBuddyMockMaker(SubclassLoader subclassLoader) {
        this.cachingMockBytecodeGenerator = new TypeCachingBytecodeGenerator(new SubclassBytecodeGenerator(subclassLoader), false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0046, code lost:
        throw new org.mockito.exceptions.base.MockitoException("Unable to create mock instance of type '" + r0.getSuperclass().getSimpleName() + "'", r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0020, code lost:
        r10 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0022, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0022 A[ExcHandler: InstantiationException (r9v11 'e' org.mockito.creation.instance.InstantiationException A[CUSTOM_DECLARE]), Splitter:B:1:0x000c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T createMock(org.mockito.mock.MockCreationSettings<T> r9, org.mockito.invocation.MockHandler r10) {
        /*
            r8 = this;
            java.lang.Class r0 = r8.createMockType(r9)
            org.mockito.plugins.InstantiatorProvider2 r1 = org.mockito.internal.configuration.plugins.Plugins.getInstantiatorProvider()
            org.mockito.creation.instance.Instantiator r1 = r1.getInstantiator(r9)
            java.lang.Object r2 = r1.newInstance(r0)     // Catch:{ ClassCastException -> 0x0047, InstantiationException -> 0x0022 }
            r3 = r2
            org.mockito.internal.creation.bytebuddy.MockAccess r3 = (org.mockito.internal.creation.bytebuddy.MockAccess) r3     // Catch:{ ClassCastException -> 0x0020, InstantiationException -> 0x0022 }
            org.mockito.internal.creation.bytebuddy.MockMethodInterceptor r4 = new org.mockito.internal.creation.bytebuddy.MockMethodInterceptor     // Catch:{ ClassCastException -> 0x0020, InstantiationException -> 0x0022 }
            r4.<init>(r10, r9)     // Catch:{ ClassCastException -> 0x0020, InstantiationException -> 0x0022 }
            r3.setMockitoInterceptor(r4)     // Catch:{ ClassCastException -> 0x0020, InstantiationException -> 0x0022 }
            java.lang.Object r9 = ensureMockIsAssignableToMockedType(r9, r2)     // Catch:{ ClassCastException -> 0x0020, InstantiationException -> 0x0022 }
            return r9
        L_0x0020:
            r10 = move-exception
            goto L_0x0049
        L_0x0022:
            r9 = move-exception
            org.mockito.exceptions.base.MockitoException r10 = new org.mockito.exceptions.base.MockitoException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unable to create mock instance of type '"
            r1.append(r2)
            java.lang.Class r0 = r0.getSuperclass()
            java.lang.String r0 = r0.getSimpleName()
            r1.append(r0)
            java.lang.String r0 = "'"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r10.<init>(r0, r9)
            throw r10
        L_0x0047:
            r10 = move-exception
            r2 = 0
        L_0x0049:
            org.mockito.exceptions.base.MockitoException r3 = new org.mockito.exceptions.base.MockitoException
            r4 = 8
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r5 = 0
            java.lang.String r6 = "ClassCastException occurred while creating the mockito mock :"
            r4[r5] = r6
            r5 = 1
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "  class to mock : "
            r6.append(r7)
            java.lang.Class r9 = r9.getTypeToMock()
            java.lang.String r9 = describeClass((java.lang.Class<?>) r9)
            r6.append(r9)
            java.lang.String r9 = r6.toString()
            r4[r5] = r9
            r9 = 2
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "  created class : "
            r5.append(r6)
            java.lang.String r0 = describeClass((java.lang.Class<?>) r0)
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            r4[r9] = r0
            r9 = 3
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r5 = "  proxy instance class : "
            r0.append(r5)
            java.lang.String r2 = describeClass((java.lang.Object) r2)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r4[r9] = r0
            r9 = 4
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "  instance creation by : "
            r0.append(r2)
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getSimpleName()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r4[r9] = r0
            r9 = 5
            java.lang.String r0 = ""
            r4[r9] = r0
            r9 = 6
            java.lang.String r1 = "You might experience classloading issues, please ask the mockito mailing-list."
            r4[r9] = r1
            r9 = 7
            r4[r9] = r0
            java.lang.String r9 = org.mockito.internal.util.StringUtil.join(r4)
            r3.<init>(r9, r10)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mockito.internal.creation.bytebuddy.SubclassByteBuddyMockMaker.createMock(org.mockito.mock.MockCreationSettings, org.mockito.invocation.MockHandler):java.lang.Object");
    }

    public <T> Class<? extends T> createMockType(MockCreationSettings<T> mockCreationSettings) {
        try {
            return this.cachingMockBytecodeGenerator.mockClass(MockFeatures.withMockFeatures(mockCreationSettings.getTypeToMock(), mockCreationSettings.getExtraInterfaces(), mockCreationSettings.getSerializableMode(), mockCreationSettings.isStripAnnotations()));
        } catch (Exception e) {
            throw prettifyFailure(mockCreationSettings, e);
        }
    }

    private static <T> T ensureMockIsAssignableToMockedType(MockCreationSettings<T> mockCreationSettings, T t) {
        return mockCreationSettings.getTypeToMock().cast(t);
    }

    private <T> RuntimeException prettifyFailure(MockCreationSettings<T> mockCreationSettings, Exception exc) {
        String str;
        if (mockCreationSettings.getTypeToMock().isArray()) {
            throw new MockitoException(StringUtil.join("Mockito cannot mock arrays: " + mockCreationSettings.getTypeToMock() + ".", ""), exc);
        } else if (!Modifier.isPrivate(mockCreationSettings.getTypeToMock().getModifiers())) {
            Object[] objArr = new Object[9];
            objArr[0] = "Mockito cannot mock this class: " + mockCreationSettings.getTypeToMock() + ".";
            objArr[1] = "";
            objArr[2] = "Mockito can only mock non-private & non-final classes.";
            objArr[3] = "If you're not sure why you're getting this error, please report to the mailing list.";
            objArr[4] = "";
            if (Platform.isJava8BelowUpdate45()) {
                str = "Java 8 early builds have bugs that were addressed in Java 1.8.0_45, please update your JDK!\n";
            } else {
                str = "";
            }
            objArr[5] = Platform.warnForVM("IBM J9 VM", "Early IBM virtual machine are known to have issues with Mockito, please upgrade to an up-to-date version.\n", "Hotspot", str);
            objArr[6] = Platform.describe();
            objArr[7] = "";
            objArr[8] = "Underlying exception : " + exc;
            throw new MockitoException(StringUtil.join(objArr), exc);
        } else {
            throw new MockitoException(StringUtil.join("Mockito cannot mock this class: " + mockCreationSettings.getTypeToMock() + ".", "Most likely it is due to mocking a private class that is not visible to Mockito", ""), exc);
        }
    }

    private static String describeClass(Class<?> cls) {
        if (cls == null) {
            return "null";
        }
        return "'" + cls.getCanonicalName() + "', loaded by classloader : '" + cls.getClassLoader() + "'";
    }

    private static String describeClass(Object obj) {
        return obj == null ? "null" : describeClass(obj.getClass());
    }

    public MockHandler getHandler(Object obj) {
        if (!(obj instanceof MockAccess)) {
            return null;
        }
        return ((MockAccess) obj).getMockitoInterceptor().getMockHandler();
    }

    public void resetMock(Object obj, MockHandler mockHandler, MockCreationSettings mockCreationSettings) {
        ((MockAccess) obj).setMockitoInterceptor(new MockMethodInterceptor(mockHandler, mockCreationSettings));
    }

    public MockMaker.TypeMockability isTypeMockable(final Class<?> cls) {
        return new MockMaker.TypeMockability() {
            public boolean mockable() {
                return !cls.isPrimitive() && !Modifier.isFinal(cls.getModifiers());
            }

            public String nonMockableReason() {
                if (mockable()) {
                    return "";
                }
                if (cls.isPrimitive()) {
                    return "primitive type";
                }
                if (Modifier.isFinal(cls.getModifiers())) {
                    return "final class";
                }
                return StringUtil.join("not handled type");
            }
        };
    }
}
