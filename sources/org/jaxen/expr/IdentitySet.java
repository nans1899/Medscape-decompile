package org.jaxen.expr;

import java.util.HashSet;

final class IdentitySet {
    private HashSet contents = new HashSet();

    IdentitySet() {
    }

    /* access modifiers changed from: package-private */
    public void add(Object obj) {
        this.contents.add(new IdentityWrapper(obj));
    }

    public boolean contains(Object obj) {
        return this.contents.contains(new IdentityWrapper(obj));
    }

    private static class IdentityWrapper {
        private Object object;

        IdentityWrapper(Object obj) {
            this.object = obj;
        }

        public boolean equals(Object obj) {
            return this.object == ((IdentityWrapper) obj).object;
        }

        public int hashCode() {
            return System.identityHashCode(this.object);
        }
    }
}
