package com.google.common.util.concurrent;

import com.google.common.util.concurrent.ForwardingListenableFuture;
import java.lang.Exception;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Deprecated
public abstract class AbstractCheckedFuture<V, X extends Exception> extends ForwardingListenableFuture.SimpleForwardingListenableFuture<V> implements CheckedFuture<V, X> {
    /* access modifiers changed from: protected */
    public abstract X mapException(Exception exc);

    protected AbstractCheckedFuture(ListenableFuture<V> listenableFuture) {
        super(listenableFuture);
    }

    public V checkedGet() throws Exception {
        try {
            return get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw mapException(e);
        } catch (CancellationException | ExecutionException e2) {
            throw mapException(e2);
        }
    }

    public V checkedGet(long j, TimeUnit timeUnit) throws TimeoutException, Exception {
        try {
            return get(j, timeUnit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw mapException(e);
        } catch (CancellationException | ExecutionException e2) {
            throw mapException(e2);
        }
    }
}
