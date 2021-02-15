package com.fasterxml.jackson.annotation;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import java.util.UUID;

public class ObjectIdGenerators {

    public static abstract class None extends ObjectIdGenerator<Object> {
    }

    private static abstract class Base<T> extends ObjectIdGenerator<T> {
        protected final Class<?> _scope;

        public abstract T generateId(Object obj);

        protected Base(Class<?> cls) {
            this._scope = cls;
        }

        public final Class<?> getScope() {
            return this._scope;
        }

        public boolean canUseFor(ObjectIdGenerator<?> objectIdGenerator) {
            return objectIdGenerator.getClass() == getClass() && objectIdGenerator.getScope() == this._scope;
        }
    }

    public static abstract class PropertyGenerator extends Base<Object> {
        private static final long serialVersionUID = 1;

        public /* bridge */ /* synthetic */ boolean canUseFor(ObjectIdGenerator objectIdGenerator) {
            return super.canUseFor(objectIdGenerator);
        }

        protected PropertyGenerator(Class<?> cls) {
            super(cls);
        }
    }

    public static final class IntSequenceGenerator extends Base<Integer> {
        private static final long serialVersionUID = 1;
        protected transient int _nextValue;

        /* access modifiers changed from: protected */
        public int initialValue() {
            return 1;
        }

        public /* bridge */ /* synthetic */ boolean canUseFor(ObjectIdGenerator objectIdGenerator) {
            return super.canUseFor(objectIdGenerator);
        }

        public IntSequenceGenerator() {
            this(Object.class, -1);
        }

        public IntSequenceGenerator(Class<?> cls, int i) {
            super(cls);
            this._nextValue = i;
        }

        public ObjectIdGenerator<Integer> forScope(Class<?> cls) {
            return this._scope == cls ? this : new IntSequenceGenerator(cls, this._nextValue);
        }

        public ObjectIdGenerator<Integer> newForSerialization(Object obj) {
            return new IntSequenceGenerator(this._scope, initialValue());
        }

        public ObjectIdGenerator.IdKey key(Object obj) {
            if (obj == null) {
                return null;
            }
            return new ObjectIdGenerator.IdKey(getClass(), this._scope, obj);
        }

        public Integer generateId(Object obj) {
            if (obj == null) {
                return null;
            }
            int i = this._nextValue;
            this._nextValue = i + 1;
            return Integer.valueOf(i);
        }
    }

    public static final class UUIDGenerator extends Base<UUID> {
        private static final long serialVersionUID = 1;

        public ObjectIdGenerator<UUID> forScope(Class<?> cls) {
            return this;
        }

        public ObjectIdGenerator<UUID> newForSerialization(Object obj) {
            return this;
        }

        public UUIDGenerator() {
            this(Object.class);
        }

        private UUIDGenerator(Class<?> cls) {
            super(Object.class);
        }

        public UUID generateId(Object obj) {
            return UUID.randomUUID();
        }

        public ObjectIdGenerator.IdKey key(Object obj) {
            if (obj == null) {
                return null;
            }
            return new ObjectIdGenerator.IdKey(getClass(), (Class<?>) null, obj);
        }

        public boolean canUseFor(ObjectIdGenerator<?> objectIdGenerator) {
            return objectIdGenerator.getClass() == getClass();
        }
    }

    public static final class StringIdGenerator extends Base<String> {
        private static final long serialVersionUID = 1;

        public ObjectIdGenerator<String> forScope(Class<?> cls) {
            return this;
        }

        public ObjectIdGenerator<String> newForSerialization(Object obj) {
            return this;
        }

        public StringIdGenerator() {
            this(Object.class);
        }

        private StringIdGenerator(Class<?> cls) {
            super(Object.class);
        }

        public String generateId(Object obj) {
            return UUID.randomUUID().toString();
        }

        public ObjectIdGenerator.IdKey key(Object obj) {
            if (obj == null) {
                return null;
            }
            return new ObjectIdGenerator.IdKey(getClass(), (Class<?>) null, obj);
        }

        public boolean canUseFor(ObjectIdGenerator<?> objectIdGenerator) {
            return objectIdGenerator instanceof StringIdGenerator;
        }
    }
}
