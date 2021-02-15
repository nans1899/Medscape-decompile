package org.simpleframework.xml.core;

import java.lang.reflect.Constructor;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class InstanceFactory {
    private final Cache<Constructor> cache = new ConcurrentCache();

    public Instance getInstance(Value value) {
        return new ValueInstance(value);
    }

    public Instance getInstance(Class cls) {
        return new ClassInstance(cls);
    }

    /* access modifiers changed from: protected */
    public Object getObject(Class cls) throws Exception {
        Constructor fetch = this.cache.fetch(cls);
        if (fetch == null) {
            fetch = cls.getDeclaredConstructor(new Class[0]);
            if (!fetch.isAccessible()) {
                fetch.setAccessible(true);
            }
            this.cache.cache(cls, fetch);
        }
        return fetch.newInstance(new Object[0]);
    }

    private class ValueInstance implements Instance {
        private final Class type;
        private final Value value;

        public ValueInstance(Value value2) {
            this.type = value2.getType();
            this.value = value2;
        }

        public Object getInstance() throws Exception {
            if (this.value.isReference()) {
                return this.value.getValue();
            }
            Object object = InstanceFactory.this.getObject(this.type);
            Value value2 = this.value;
            if (value2 != null) {
                value2.setValue(object);
            }
            return object;
        }

        public Object setInstance(Object obj) {
            Value value2 = this.value;
            if (value2 != null) {
                value2.setValue(obj);
            }
            return obj;
        }

        public boolean isReference() {
            return this.value.isReference();
        }

        public Class getType() {
            return this.type;
        }
    }

    private class ClassInstance implements Instance {
        private Class type;
        private Object value;

        public boolean isReference() {
            return false;
        }

        public ClassInstance(Class cls) {
            this.type = cls;
        }

        public Object getInstance() throws Exception {
            if (this.value == null) {
                this.value = InstanceFactory.this.getObject(this.type);
            }
            return this.value;
        }

        public Object setInstance(Object obj) throws Exception {
            this.value = obj;
            return obj;
        }

        public Class getType() {
            return this.type;
        }
    }
}
