package io.grpc.internal;

public interface ObjectPool<T> {
    T getObject();

    T returnObject(Object obj);
}
