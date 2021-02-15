package io.grpc.internal;

import com.google.common.base.Preconditions;
import com.medscape.android.slideshow.SlideshowPageFragment;

public final class FixedObjectPool<T> implements ObjectPool<T> {
    private final T object;

    public T returnObject(Object obj) {
        return null;
    }

    public FixedObjectPool(T t) {
        this.object = Preconditions.checkNotNull(t, SlideshowPageFragment.ARG_OBJECT);
    }

    public T getObject() {
        return this.object;
    }
}
