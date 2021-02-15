package org.mockito.internal.creation.bytebuddy;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Argument;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;
import net.bytebuddy.implementation.bind.annotation.FieldValue;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.StubValue;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.implementation.bind.annotation.This;
import org.mockito.internal.debugging.LocationImpl;
import org.mockito.internal.invocation.DefaultInvocationFactory;
import org.mockito.internal.invocation.RealMethod;
import org.mockito.invocation.Location;
import org.mockito.invocation.MockHandler;
import org.mockito.mock.MockCreationSettings;

public class MockMethodInterceptor implements Serializable {
    private static final long serialVersionUID = 7152947254057253027L;
    final MockHandler handler;
    private final MockCreationSettings mockCreationSettings;
    private final ByteBuddyCrossClassLoaderSerializationSupport serializationSupport = new ByteBuddyCrossClassLoaderSerializationSupport();

    public static class ForEquals {
        public static boolean doIdentityEquals(@This Object obj, @Argument(0) Object obj2) {
            return obj == obj2;
        }
    }

    public MockMethodInterceptor(MockHandler mockHandler, MockCreationSettings mockCreationSettings2) {
        this.handler = mockHandler;
        this.mockCreationSettings = mockCreationSettings2;
    }

    /* access modifiers changed from: package-private */
    public Object doIntercept(Object obj, Method method, Object[] objArr, RealMethod realMethod) throws Throwable {
        return doIntercept(obj, method, objArr, realMethod, new LocationImpl());
    }

    /* access modifiers changed from: package-private */
    public Object doIntercept(Object obj, Method method, Object[] objArr, RealMethod realMethod, Location location) throws Throwable {
        return this.handler.handle(DefaultInvocationFactory.createInvocation(obj, method, objArr, realMethod, this.mockCreationSettings, location));
    }

    public MockHandler getMockHandler() {
        return this.handler;
    }

    public ByteBuddyCrossClassLoaderSerializationSupport getSerializationSupport() {
        return this.serializationSupport;
    }

    public static class ForHashCode {
        public static int doIdentityHashCode(@This Object obj) {
            return System.identityHashCode(obj);
        }
    }

    public static class ForWriteReplace {
        public static Object doWriteReplace(@This MockAccess mockAccess) throws ObjectStreamException {
            return mockAccess.getMockitoInterceptor().getSerializationSupport().writeReplace(mockAccess);
        }
    }

    public static class DispatcherDefaultingToRealMethod {
        @BindingPriority(2)
        @RuntimeType
        public static Object interceptSuperCallable(@This Object obj, @FieldValue("mockitoInterceptor") MockMethodInterceptor mockMethodInterceptor, @Origin Method method, @AllArguments Object[] objArr, @SuperCall(serializableProxy = true) Callable<?> callable) throws Throwable {
            if (mockMethodInterceptor == null) {
                return callable.call();
            }
            return mockMethodInterceptor.doIntercept(obj, method, objArr, new RealMethod.FromCallable(callable));
        }

        @RuntimeType
        public static Object interceptAbstract(@This Object obj, @FieldValue("mockitoInterceptor") MockMethodInterceptor mockMethodInterceptor, @StubValue Object obj2, @Origin Method method, @AllArguments Object[] objArr) throws Throwable {
            return mockMethodInterceptor == null ? obj2 : mockMethodInterceptor.doIntercept(obj, method, objArr, RealMethod.IsIllegal.INSTANCE);
        }
    }
}
