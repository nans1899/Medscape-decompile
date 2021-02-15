package org.mockito.invocation;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import org.mockito.Incubating;
import org.mockito.mock.MockCreationSettings;

@Incubating
public interface InvocationFactory {

    public interface RealMethodBehavior<R> extends Serializable {
        R call() throws Throwable;
    }

    @Deprecated
    Invocation createInvocation(Object obj, MockCreationSettings mockCreationSettings, Method method, Callable callable, Object... objArr);

    @Incubating
    Invocation createInvocation(Object obj, MockCreationSettings mockCreationSettings, Method method, RealMethodBehavior realMethodBehavior, Object... objArr);
}
