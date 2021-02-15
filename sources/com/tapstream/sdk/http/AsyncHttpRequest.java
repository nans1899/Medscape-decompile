package com.tapstream.sdk.http;

import com.tapstream.sdk.ApiResponse;
import com.tapstream.sdk.Logging;
import com.tapstream.sdk.Retry;
import com.tapstream.sdk.SettableApiFuture;
import com.tapstream.sdk.errors.ApiException;
import com.tapstream.sdk.errors.RetriesExhaustedException;
import com.tapstream.sdk.errors.UnrecoverableApiException;
import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AsyncHttpRequest<T extends ApiResponse> implements Runnable {
    final HttpClient client;
    final ScheduledExecutorService executor;
    final Handler<T> handler;
    final SettableApiFuture<T> responseFuture;
    final Retry.Retryable<HttpRequest> retryable;

    public static abstract class Handler<T> {
        /* access modifiers changed from: protected */
        public abstract T checkedRun(HttpResponse httpResponse) throws IOException, ApiException;

        /* access modifiers changed from: protected */
        public void onFailure() {
        }

        /* access modifiers changed from: protected */
        public void onRetry() {
        }

        /* access modifiers changed from: protected */
        public void onError(Throwable th) {
            onFailure();
        }

        /* access modifiers changed from: protected */
        public void onFailure(UnrecoverableApiException unrecoverableApiException, HttpResponse httpResponse) {
            Logging.log(6, "Unrecoverable API exception. Check that your API secret and account name are correct (cause: %s)", unrecoverableApiException.toString());
            onFailure();
        }
    }

    public AsyncHttpRequest(SettableApiFuture<T> settableApiFuture, Retry.Retryable<HttpRequest> retryable2, Handler<T> handler2, ScheduledExecutorService scheduledExecutorService, HttpClient httpClient) {
        this.responseFuture = settableApiFuture;
        this.retryable = retryable2;
        this.handler = handler2;
        this.executor = scheduledExecutorService;
        this.client = httpClient;
    }

    private void fail(Throwable th) {
        this.handler.onError(th);
        this.responseFuture.setException(th);
    }

    private void fail(UnrecoverableApiException unrecoverableApiException, HttpResponse httpResponse) {
        this.handler.onFailure(unrecoverableApiException, httpResponse);
        this.responseFuture.setException(unrecoverableApiException);
    }

    private void retryRequest(Exception exc) {
        if (this.responseFuture.isCancelled()) {
            Logging.log(4, "API request cancelled", new Object[0]);
        } else if (this.retryable.shouldRetry()) {
            Logging.log(6, "Failure during request, retrying (cause: %s)", exc.toString());
            this.retryable.incrementAttempt();
            this.handler.onRetry();
            this.responseFuture.propagateCancellationTo(this.executor.schedule(this, (long) this.retryable.getDelayMs(), TimeUnit.MILLISECONDS));
        } else {
            Logging.log(6, "No more retries, failing permanently (cause: %s).", exc.toString());
            fail(new RetriesExhaustedException());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0023, code lost:
        com.tapstream.sdk.Logging.log(6, "Unhandled exception during request (cause: %s)", r0.toString());
        fail(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003d, code lost:
        retryRequest(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0041, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0042, code lost:
        retryRequest(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001d, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001e, code lost:
        fail(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0021, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0022, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003c A[ExcHandler: IOException (r0v1 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:0:0x0000] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0041 A[ExcHandler: RecoverableApiException (r0v0 'e' com.tapstream.sdk.errors.RecoverableApiException A[CUSTOM_DECLARE]), Splitter:B:0:0x0000] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0022 A[ExcHandler: Exception (r0v3 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:0:0x0000] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r5 = this;
            com.tapstream.sdk.http.HttpClient r0 = r5.client     // Catch:{ RecoverableApiException -> 0x0041, IOException -> 0x003c, UnrecoverableApiException -> 0x0037, Exception -> 0x0022 }
            com.tapstream.sdk.Retry$Retryable<com.tapstream.sdk.http.HttpRequest> r1 = r5.retryable     // Catch:{ RecoverableApiException -> 0x0041, IOException -> 0x003c, UnrecoverableApiException -> 0x0037, Exception -> 0x0022 }
            java.lang.Object r1 = r1.get()     // Catch:{ RecoverableApiException -> 0x0041, IOException -> 0x003c, UnrecoverableApiException -> 0x0037, Exception -> 0x0022 }
            com.tapstream.sdk.http.HttpRequest r1 = (com.tapstream.sdk.http.HttpRequest) r1     // Catch:{ RecoverableApiException -> 0x0041, IOException -> 0x003c, UnrecoverableApiException -> 0x0037, Exception -> 0x0022 }
            com.tapstream.sdk.http.HttpResponse r0 = r0.sendRequest(r1)     // Catch:{ RecoverableApiException -> 0x0041, IOException -> 0x003c, UnrecoverableApiException -> 0x0037, Exception -> 0x0022 }
            r0.throwOnError()     // Catch:{ UnrecoverableApiException -> 0x001d, RecoverableApiException -> 0x0041, IOException -> 0x003c, Exception -> 0x0022 }
            com.tapstream.sdk.SettableApiFuture<T> r1 = r5.responseFuture     // Catch:{ RecoverableApiException -> 0x0041, IOException -> 0x003c, UnrecoverableApiException -> 0x0037, Exception -> 0x0022 }
            com.tapstream.sdk.http.AsyncHttpRequest$Handler<T> r2 = r5.handler     // Catch:{ RecoverableApiException -> 0x0041, IOException -> 0x003c, UnrecoverableApiException -> 0x0037, Exception -> 0x0022 }
            java.lang.Object r0 = r2.checkedRun(r0)     // Catch:{ RecoverableApiException -> 0x0041, IOException -> 0x003c, UnrecoverableApiException -> 0x0037, Exception -> 0x0022 }
            r1.set(r0)     // Catch:{ RecoverableApiException -> 0x0041, IOException -> 0x003c, UnrecoverableApiException -> 0x0037, Exception -> 0x0022 }
            goto L_0x0045
        L_0x001d:
            r1 = move-exception
            r5.fail(r1, r0)     // Catch:{ RecoverableApiException -> 0x0041, IOException -> 0x003c, UnrecoverableApiException -> 0x0037, Exception -> 0x0022 }
            return
        L_0x0022:
            r0 = move-exception
            r1 = 6
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            java.lang.String r4 = r0.toString()
            r2[r3] = r4
            java.lang.String r3 = "Unhandled exception during request (cause: %s)"
            com.tapstream.sdk.Logging.log(r1, r3, r2)
            r5.fail(r0)
            goto L_0x0045
        L_0x0037:
            r0 = move-exception
            r5.fail(r0)
            goto L_0x0045
        L_0x003c:
            r0 = move-exception
            r5.retryRequest(r0)
            goto L_0x0045
        L_0x0041:
            r0 = move-exception
            r5.retryRequest(r0)
        L_0x0045:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapstream.sdk.http.AsyncHttpRequest.run():void");
    }
}
