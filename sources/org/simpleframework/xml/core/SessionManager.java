package org.simpleframework.xml.core;

class SessionManager {
    private ThreadLocal<Reference> local = new ThreadLocal<>();

    public Session open() throws Exception {
        return open(true);
    }

    public Session open(boolean z) throws Exception {
        Reference reference = this.local.get();
        if (reference != null) {
            return reference.get();
        }
        return create(z);
    }

    private Session create(boolean z) throws Exception {
        Reference reference = new Reference(z);
        this.local.set(reference);
        return reference.get();
    }

    public void close() throws Exception {
        Reference reference = this.local.get();
        if (reference == null) {
            throw new PersistenceException("Session does not exist", new Object[0]);
        } else if (reference.clear() == 0) {
            this.local.remove();
        }
    }

    private static class Reference {
        private int count;
        private Session session;

        public Reference(boolean z) {
            this.session = new Session(z);
        }

        public Session get() {
            int i = this.count;
            if (i >= 0) {
                this.count = i + 1;
            }
            return this.session;
        }

        public int clear() {
            int i = this.count - 1;
            this.count = i;
            return i;
        }
    }
}
