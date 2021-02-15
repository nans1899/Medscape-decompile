package org.mockito.internal.handler;

import org.mockito.internal.matchers.Equality;
import org.mockito.invocation.DescribedInvocation;
import org.mockito.invocation.Invocation;
import org.mockito.listeners.MethodInvocationReport;

public class NotifiedMethodInvocationReport implements MethodInvocationReport {
    private final Invocation invocation;
    private final Object returnedValue;
    private final Throwable throwable;

    public NotifiedMethodInvocationReport(Invocation invocation2, Object obj) {
        this.invocation = invocation2;
        this.returnedValue = obj;
        this.throwable = null;
    }

    public NotifiedMethodInvocationReport(Invocation invocation2, Throwable th) {
        this.invocation = invocation2;
        this.returnedValue = null;
        this.throwable = th;
    }

    public DescribedInvocation getInvocation() {
        return this.invocation;
    }

    public Object getReturnedValue() {
        return this.returnedValue;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public boolean threwException() {
        return this.throwable != null;
    }

    public String getLocationOfStubbing() {
        if (this.invocation.stubInfo() == null) {
            return null;
        }
        return this.invocation.stubInfo().stubbedAt().toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NotifiedMethodInvocationReport notifiedMethodInvocationReport = (NotifiedMethodInvocationReport) obj;
        if (!Equality.areEqual(this.invocation, notifiedMethodInvocationReport.invocation) || !Equality.areEqual(this.returnedValue, notifiedMethodInvocationReport.returnedValue) || !Equality.areEqual(this.throwable, notifiedMethodInvocationReport.throwable)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        Invocation invocation2 = this.invocation;
        int i = 0;
        int hashCode = (invocation2 != null ? invocation2.hashCode() : 0) * 31;
        Object obj = this.returnedValue;
        int hashCode2 = (hashCode + (obj != null ? obj.hashCode() : 0)) * 31;
        Throwable th = this.throwable;
        if (th != null) {
            i = th.hashCode();
        }
        return hashCode2 + i;
    }
}
