package org.mockito.internal.invocation;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import org.mockito.internal.creation.DelegatingMethod;
import org.mockito.internal.debugging.LocationImpl;
import org.mockito.internal.invocation.RealMethod;
import org.mockito.internal.invocation.mockref.MockWeakReference;
import org.mockito.internal.progress.SequenceNumber;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.InvocationFactory;
import org.mockito.invocation.Location;
import org.mockito.mock.MockCreationSettings;

public class DefaultInvocationFactory implements InvocationFactory {
    public Invocation createInvocation(Object obj, MockCreationSettings mockCreationSettings, Method method, Callable callable, Object... objArr) {
        return createInvocation(obj, mockCreationSettings, method, (RealMethod) new RealMethod.FromCallable(callable), objArr);
    }

    public Invocation createInvocation(Object obj, MockCreationSettings mockCreationSettings, Method method, InvocationFactory.RealMethodBehavior realMethodBehavior, Object... objArr) {
        return createInvocation(obj, mockCreationSettings, method, (RealMethod) new RealMethod.FromBehavior(realMethodBehavior), objArr);
    }

    private Invocation createInvocation(Object obj, MockCreationSettings mockCreationSettings, Method method, RealMethod realMethod, Object[] objArr) {
        return createInvocation(obj, method, objArr, realMethod, mockCreationSettings);
    }

    public static InterceptedInvocation createInvocation(Object obj, Method method, Object[] objArr, RealMethod realMethod, MockCreationSettings mockCreationSettings, Location location) {
        return new InterceptedInvocation(new MockWeakReference(obj), createMockitoMethod(method, mockCreationSettings), objArr, realMethod, location, SequenceNumber.next());
    }

    private static InterceptedInvocation createInvocation(Object obj, Method method, Object[] objArr, RealMethod realMethod, MockCreationSettings mockCreationSettings) {
        return createInvocation(obj, method, objArr, realMethod, mockCreationSettings, new LocationImpl());
    }

    private static MockitoMethod createMockitoMethod(Method method, MockCreationSettings mockCreationSettings) {
        if (mockCreationSettings.isSerializable()) {
            return new SerializableMethod(method);
        }
        return new DelegatingMethod(method);
    }
}
