package org.mockito.internal.creation.bytebuddy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.SoftReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.implementation.bind.annotation.Argument;
import net.bytebuddy.implementation.bind.annotation.This;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.creation.bytebuddy.inject.MockMethodDispatcher;
import org.mockito.internal.exceptions.stacktrace.ConditionalStackTraceFilter;
import org.mockito.internal.invocation.RealMethod;
import org.mockito.internal.invocation.SerializableMethod;
import org.mockito.internal.invocation.mockref.MockReference;
import org.mockito.internal.invocation.mockref.MockWeakReference;
import org.mockito.internal.util.concurrent.WeakConcurrentMap;

public class MockMethodAdvice extends MockMethodDispatcher {
    private final MethodGraph.Compiler compiler = MethodGraph.Compiler.Default.forJavaHierarchy();
    private final WeakConcurrentMap<Class<?>, SoftReference<MethodGraph>> graphs = new WeakConcurrentMap.WithInlinedExpunction();
    private final String identifier;
    /* access modifiers changed from: private */
    public final WeakConcurrentMap<Object, MockMethodInterceptor> interceptors;
    /* access modifiers changed from: private */
    public final SelfCallInfo selfCallInfo = new SelfCallInfo();

    @Retention(RetentionPolicy.RUNTIME)
    @interface Identifier {
    }

    public MockMethodAdvice(WeakConcurrentMap<Object, MockMethodInterceptor> weakConcurrentMap, String str) {
        this.interceptors = weakConcurrentMap;
        this.identifier = str;
    }

    @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
    private static Callable<?> enter(@Identifier String str, @Advice.This Object obj, @Advice.Origin Method method, @Advice.AllArguments Object[] objArr) throws Throwable {
        MockMethodDispatcher mockMethodDispatcher = MockMethodDispatcher.get(str, obj);
        if (mockMethodDispatcher == null || !mockMethodDispatcher.isMocked(obj) || mockMethodDispatcher.isOverridden(obj, method)) {
            return null;
        }
        return mockMethodDispatcher.handle(obj, method, objArr);
    }

    @Advice.OnMethodExit
    private static void exit(@Advice.Return(readOnly = false, typing = Assigner.Typing.DYNAMIC) Object obj, @Advice.Enter Callable<?> callable) throws Throwable {
        if (callable != null) {
            callable.call();
        }
    }

    static Throwable hideRecursiveCall(Throwable th, int i, Class<?> cls) {
        try {
            StackTraceElement[] stackTrace = th.getStackTrace();
            int i2 = 0;
            do {
                i2++;
            } while (!stackTrace[(stackTrace.length - i) - i2].getClassName().equals(cls.getName()));
            int length = (stackTrace.length - i) - i2;
            StackTraceElement[] stackTraceElementArr = new StackTraceElement[(stackTrace.length - i2)];
            System.arraycopy(stackTrace, 0, stackTraceElementArr, 0, length);
            System.arraycopy(stackTrace, i2 + length, stackTraceElementArr, length, i);
            th.setStackTrace(stackTraceElementArr);
        } catch (RuntimeException unused) {
        }
        return th;
    }

    /* JADX WARNING: type inference failed for: r8v0 */
    /* JADX WARNING: type inference failed for: r0v8, types: [org.mockito.internal.creation.bytebuddy.MockMethodAdvice$RealMethodCall] */
    /* JADX WARNING: type inference failed for: r0v9, types: [org.mockito.internal.creation.bytebuddy.MockMethodAdvice$SerializableRealMethodCall] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.concurrent.Callable<?> handle(java.lang.Object r10, java.lang.reflect.Method r11, java.lang.Object[] r12) throws java.lang.Throwable {
        /*
            r9 = this;
            org.mockito.internal.util.concurrent.WeakConcurrentMap<java.lang.Object, org.mockito.internal.creation.bytebuddy.MockMethodInterceptor> r0 = r9.interceptors
            java.lang.Object r0 = r0.get(r10)
            r6 = r0
            org.mockito.internal.creation.bytebuddy.MockMethodInterceptor r6 = (org.mockito.internal.creation.bytebuddy.MockMethodInterceptor) r6
            r7 = 0
            if (r6 != 0) goto L_0x000d
            return r7
        L_0x000d:
            boolean r0 = r10 instanceof java.io.Serializable
            if (r0 == 0) goto L_0x001e
            org.mockito.internal.creation.bytebuddy.MockMethodAdvice$SerializableRealMethodCall r8 = new org.mockito.internal.creation.bytebuddy.MockMethodAdvice$SerializableRealMethodCall
            java.lang.String r1 = r9.identifier
            r5 = 0
            r0 = r8
            r2 = r11
            r3 = r10
            r4 = r12
            r0.<init>(r1, r2, r3, r4)
            goto L_0x002a
        L_0x001e:
            org.mockito.internal.creation.bytebuddy.MockMethodAdvice$RealMethodCall r8 = new org.mockito.internal.creation.bytebuddy.MockMethodAdvice$RealMethodCall
            org.mockito.internal.creation.bytebuddy.MockMethodAdvice$SelfCallInfo r1 = r9.selfCallInfo
            r5 = 0
            r0 = r8
            r2 = r11
            r3 = r10
            r4 = r12
            r0.<init>(r1, r2, r3, r4)
        L_0x002a:
            r4 = r8
            java.lang.Throwable r0 = new java.lang.Throwable
            r0.<init>()
            java.lang.StackTraceElement[] r1 = r0.getStackTrace()
            java.lang.StackTraceElement[] r1 = skipInlineMethodElement(r1)
            r0.setStackTrace(r1)
            org.mockito.internal.creation.bytebuddy.MockMethodAdvice$ReturnValueWrapper r8 = new org.mockito.internal.creation.bytebuddy.MockMethodAdvice$ReturnValueWrapper
            org.mockito.internal.debugging.LocationImpl r5 = new org.mockito.internal.debugging.LocationImpl
            r5.<init>((java.lang.Throwable) r0)
            r0 = r6
            r1 = r10
            r2 = r11
            r3 = r12
            java.lang.Object r0 = r0.doIntercept(r1, r2, r3, r4, r5)
            r8.<init>(r0)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mockito.internal.creation.bytebuddy.MockMethodAdvice.handle(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.util.concurrent.Callable");
    }

    public boolean isMock(Object obj) {
        return obj != this.interceptors.target && this.interceptors.containsKey(obj);
    }

    public boolean isMocked(Object obj) {
        return this.selfCallInfo.checkSuperCall(obj) && isMock(obj);
    }

    public boolean isOverridden(Object obj, Method method) {
        MethodGraph methodGraph;
        SoftReference softReference = this.graphs.get(obj.getClass());
        if (softReference == null) {
            methodGraph = null;
        } else {
            methodGraph = (MethodGraph) softReference.get();
        }
        if (methodGraph == null) {
            methodGraph = this.compiler.compile(new TypeDescription.ForLoadedType(obj.getClass()));
            this.graphs.put(obj.getClass(), new SoftReference(methodGraph));
        }
        MethodGraph.Node locate = methodGraph.locate(new MethodDescription.ForLoadedMethod(method).asSignatureToken());
        return !locate.getSort().isResolved() || !((MethodDescription.InDefinedShape) locate.getRepresentative().asDefined()).getDeclaringType().represents(method.getDeclaringClass());
    }

    private static class RealMethodCall implements RealMethod {
        private final Object[] arguments;
        private final MockWeakReference<Object> instanceRef;
        private final Method origin;
        private final SelfCallInfo selfCallInfo;

        public boolean isInvokable() {
            return true;
        }

        private RealMethodCall(SelfCallInfo selfCallInfo2, Method method, Object obj, Object[] objArr) {
            this.selfCallInfo = selfCallInfo2;
            this.origin = method;
            this.instanceRef = new MockWeakReference<>(obj);
            this.arguments = objArr;
        }

        public Object invoke() throws Throwable {
            if (!Modifier.isPublic(this.origin.getDeclaringClass().getModifiers() & this.origin.getModifiers())) {
                this.origin.setAccessible(true);
            }
            this.selfCallInfo.set(this.instanceRef.get());
            return MockMethodAdvice.tryInvoke(this.origin, this.instanceRef.get(), this.arguments);
        }
    }

    private static class SerializableRealMethodCall implements RealMethod {
        private final Object[] arguments;
        private final String identifier;
        private final MockReference<Object> instanceRef;
        private final SerializableMethod origin;

        public boolean isInvokable() {
            return true;
        }

        private SerializableRealMethodCall(String str, Method method, Object obj, Object[] objArr) {
            this.origin = new SerializableMethod(method);
            this.identifier = str;
            this.instanceRef = new MockWeakReference(obj);
            this.arguments = objArr;
        }

        public Object invoke() throws Throwable {
            Method javaMethod = this.origin.getJavaMethod();
            if (!Modifier.isPublic(javaMethod.getDeclaringClass().getModifiers() & javaMethod.getModifiers())) {
                javaMethod.setAccessible(true);
            }
            MockMethodAdvice mockMethodAdvice = MockMethodDispatcher.get(this.identifier, this.instanceRef.get());
            if (mockMethodAdvice instanceof MockMethodAdvice) {
                MockMethodAdvice mockMethodAdvice2 = mockMethodAdvice;
                Object replace = mockMethodAdvice2.selfCallInfo.replace(this.instanceRef.get());
                try {
                    return MockMethodAdvice.tryInvoke(javaMethod, this.instanceRef.get(), this.arguments);
                } finally {
                    mockMethodAdvice2.selfCallInfo.set(replace);
                }
            } else {
                throw new MockitoException("Unexpected dispatcher for advice-based super call");
            }
        }
    }

    /* access modifiers changed from: private */
    public static Object tryInvoke(Method method, Object obj, Object[] objArr) throws Throwable {
        try {
            return method.invoke(obj, objArr);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            new ConditionalStackTraceFilter().filter(hideRecursiveCall(cause, new Throwable().getStackTrace().length, method.getDeclaringClass()));
            throw cause;
        }
    }

    private static StackTraceElement[] skipInlineMethodElement(StackTraceElement[] stackTraceElementArr) {
        ArrayList arrayList = new ArrayList(stackTraceElementArr.length);
        int i = 0;
        while (i < stackTraceElementArr.length) {
            StackTraceElement stackTraceElement = stackTraceElementArr[i];
            arrayList.add(stackTraceElement);
            if (stackTraceElement.getClassName().equals(MockMethodAdvice.class.getName()) && stackTraceElement.getMethodName().equals("handle")) {
                i++;
            }
            i++;
        }
        return (StackTraceElement[]) arrayList.toArray(new StackTraceElement[arrayList.size()]);
    }

    private static class ReturnValueWrapper implements Callable<Object> {
        private final Object returned;

        private ReturnValueWrapper(Object obj) {
            this.returned = obj;
        }

        public Object call() {
            return this.returned;
        }
    }

    private static class SelfCallInfo extends ThreadLocal<Object> {
        private SelfCallInfo() {
        }

        /* access modifiers changed from: package-private */
        public Object replace(Object obj) {
            Object obj2 = get();
            set(obj);
            return obj2;
        }

        /* access modifiers changed from: package-private */
        public boolean checkSuperCall(Object obj) {
            if (obj != get()) {
                return true;
            }
            set((Object) null);
            return false;
        }
    }

    static class ForHashCode {
        ForHashCode() {
        }

        @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
        private static boolean enter(@Identifier String str, @Advice.This Object obj) {
            MockMethodDispatcher mockMethodDispatcher = MockMethodDispatcher.get(str, obj);
            return mockMethodDispatcher != null && mockMethodDispatcher.isMock(obj);
        }

        @Advice.OnMethodExit
        private static void enter(@Advice.This Object obj, @Advice.Return(readOnly = false) int i, @Advice.Enter boolean z) {
            if (z) {
                System.identityHashCode(obj);
            }
        }
    }

    static class ForEquals {
        @Advice.OnMethodExit
        private static void enter(@Advice.This Object obj, @Advice.Argument(0) Object obj2, @Advice.Return(readOnly = false) boolean z, @Advice.Enter boolean z2) {
        }

        ForEquals() {
        }

        @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
        private static boolean enter(@Identifier String str, @Advice.This Object obj) {
            MockMethodDispatcher mockMethodDispatcher = MockMethodDispatcher.get(str, obj);
            return mockMethodDispatcher != null && mockMethodDispatcher.isMock(obj);
        }
    }

    public static class ForReadObject {
        public static void doReadObject(@Identifier String str, @This MockAccess mockAccess, @Argument(0) ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            MockMethodAdvice mockMethodAdvice = MockMethodDispatcher.get(str, mockAccess);
            if (mockMethodAdvice != null) {
                mockMethodAdvice.interceptors.put(mockAccess, mockAccess.getMockitoInterceptor());
            }
        }
    }
}
