package org.mockito.internal.verification;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import org.mockito.internal.util.ObjectMethodsGuru;
import org.mockito.internal.util.collections.ListUtil;
import org.mockito.invocation.Invocation;

public class DefaultRegisteredInvocations implements RegisteredInvocations, Serializable {
    private static final long serialVersionUID = -2674402327380736290L;
    private final LinkedList<Invocation> invocations = new LinkedList<>();

    public void add(Invocation invocation) {
        synchronized (this.invocations) {
            this.invocations.add(invocation);
        }
    }

    public void removeLast() {
        synchronized (this.invocations) {
            if (!this.invocations.isEmpty()) {
                this.invocations.removeLast();
            }
        }
    }

    public List<Invocation> getAll() {
        LinkedList linkedList;
        synchronized (this.invocations) {
            linkedList = new LinkedList(this.invocations);
        }
        return ListUtil.filter(linkedList, new RemoveToString());
    }

    public void clear() {
        synchronized (this.invocations) {
            this.invocations.clear();
        }
    }

    public boolean isEmpty() {
        boolean isEmpty;
        synchronized (this.invocations) {
            isEmpty = this.invocations.isEmpty();
        }
        return isEmpty;
    }

    private static class RemoveToString implements ListUtil.Filter<Invocation> {
        private RemoveToString() {
        }

        public boolean isOut(Invocation invocation) {
            return ObjectMethodsGuru.isToStringMethod(invocation.getMethod());
        }
    }
}
