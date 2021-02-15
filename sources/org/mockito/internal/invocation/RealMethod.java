package org.mockito.internal.invocation;

import java.io.Serializable;
import java.util.concurrent.Callable;
import org.mockito.internal.exceptions.stacktrace.ConditionalStackTraceFilter;
import org.mockito.invocation.InvocationFactory;

public interface RealMethod extends Serializable {
    Object invoke() throws Throwable;

    boolean isInvokable();

    public enum IsIllegal implements RealMethod {
        INSTANCE;

        public boolean isInvokable() {
            return false;
        }

        public Object invoke() {
            throw new IllegalStateException();
        }
    }

    public static class FromCallable extends FromBehavior implements RealMethod {
        public FromCallable(final Callable<?> callable) {
            super(new InvocationFactory.RealMethodBehavior() {
                public Object call() throws Throwable {
                    return callable.call();
                }
            });
        }
    }

    public static class FromBehavior implements RealMethod {
        private final InvocationFactory.RealMethodBehavior<?> behavior;

        public boolean isInvokable() {
            return true;
        }

        FromBehavior(InvocationFactory.RealMethodBehavior<?> realMethodBehavior) {
            this.behavior = realMethodBehavior;
        }

        public Object invoke() throws Throwable {
            try {
                return this.behavior.call();
            } catch (Throwable th) {
                new ConditionalStackTraceFilter().filter(th);
                throw th;
            }
        }
    }
}
