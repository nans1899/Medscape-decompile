package io.grpc.stub;

import javax.annotation.Nullable;

public abstract class ClientCallStreamObserver<V> extends CallStreamObserver<V> {
    public abstract void cancel(@Nullable String str, @Nullable Throwable th);
}
