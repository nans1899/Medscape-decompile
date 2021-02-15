package com.tapstream.sdk;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SettableApiFuture<T> implements ApiFuture<T> {
    private static final int STATE_CANCELLED = 1;
    private static final int STATE_DONE = 2;
    private static final int STATE_ERROR = 3;
    private static final int STATE_INITIAL = 0;
    Callback<T> callback;
    Throwable error;
    T obj;
    Future<?> propagateCancellationTo;
    int state = 0;

    public synchronized boolean cancel(boolean z) {
        if (this.state != 0) {
            return false;
        }
        this.state = 1;
        if (this.propagateCancellationTo != null) {
            this.propagateCancellationTo.cancel(z);
        }
        notifyAll();
        return true;
    }

    public synchronized boolean isError() {
        return this.state == 3;
    }

    public synchronized boolean isCancelled() {
        boolean z;
        z = true;
        if (this.state != 1) {
            z = false;
        }
        return z;
    }

    public synchronized boolean isDone() {
        boolean z;
        z = true;
        if (!(this.state == 1 || this.state == 2 || this.state == 3)) {
            z = false;
        }
        return z;
    }

    public synchronized boolean set(T t) {
        if (this.state != 0) {
            return false;
        }
        this.state = 2;
        this.obj = t;
        notifyAll();
        safeCallbackSuccess(this.callback, t);
        return true;
    }

    public synchronized boolean setException(Throwable th) {
        if (this.state != 0) {
            return false;
        }
        this.state = 3;
        this.error = th;
        notifyAll();
        safeCallbackError(this.callback, th);
        return true;
    }

    public synchronized void propagateCancellationTo(Future<?> future) {
        this.propagateCancellationTo = future;
    }

    private static <T> void safeCallbackSuccess(Callback<T> callback2, T t) {
        if (callback2 != null) {
            try {
                callback2.success(t);
            } catch (Exception e) {
                Logging.log(5, "Failed to execute callback success: " + e.toString(), new Object[0]);
            }
        }
    }

    private static <T> void safeCallbackError(Callback<T> callback2, Throwable th) {
        if (callback2 != null) {
            try {
                callback2.error(th);
            } catch (Exception e) {
                Logging.log(5, "Failed to execute callback error: " + e.toString(), new Object[0]);
            }
        }
    }

    public synchronized void setCallback(Callback<T> callback2) {
        int i = this.state;
        if (i == 0) {
            this.callback = callback2;
        } else if (i == 1) {
            safeCallbackError(callback2, new CancellationException());
        } else if (i == 2) {
            safeCallbackSuccess(callback2, this.obj);
        } else if (i == 3) {
            safeCallbackError(callback2, this.error);
        }
    }

    public synchronized T get() throws InterruptedException, ExecutionException {
        while (true) {
            int i = this.state;
            if (i == 1) {
                throw new CancellationException();
            } else if (i == 2) {
            } else if (i != 3) {
                wait();
            } else {
                throw new ExecutionException(this.error);
            }
        }
        return this.obj;
    }

    public synchronized T get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        long currentTimeMillis = System.currentTimeMillis();
        long millis = timeUnit.toMillis(j);
        while (true) {
            int i = this.state;
            if (i == 1) {
                throw new CancellationException();
            } else if (i == 2) {
            } else if (i != 3) {
                long currentTimeMillis2 = millis - (System.currentTimeMillis() - currentTimeMillis);
                if (currentTimeMillis2 > 0) {
                    wait(currentTimeMillis2);
                } else {
                    throw new TimeoutException();
                }
            } else {
                throw new ExecutionException(this.error);
            }
        }
        return this.obj;
    }
}
