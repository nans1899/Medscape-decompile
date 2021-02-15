package com.tapstream.sdk.http;

import com.tapstream.sdk.ApiFuture;
import com.tapstream.sdk.ApiResponse;
import com.tapstream.sdk.Retry;
import com.tapstream.sdk.SettableApiFuture;
import com.tapstream.sdk.http.AsyncHttpRequest;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;

public class AsyncHttpClient implements Closeable {
    private final ScheduledExecutorService executor;
    private final HttpClient httpClient;

    public AsyncHttpClient(HttpClient httpClient2, ScheduledExecutorService scheduledExecutorService) {
        this.httpClient = httpClient2;
        this.executor = scheduledExecutorService;
    }

    public <T extends ApiResponse> ApiFuture<T> sendRequest(HttpRequest httpRequest, Retry.Strategy strategy, AsyncHttpRequest.Handler<T> handler, SettableApiFuture<T> settableApiFuture) {
        try {
            settableApiFuture.propagateCancellationTo(this.executor.submit(new AsyncHttpRequest(settableApiFuture, new Retry.Retryable(httpRequest, strategy), handler, this.executor, this.httpClient)));
        } catch (RuntimeException e) {
            settableApiFuture.setException(e);
            handler.onError(e);
        }
        return settableApiFuture;
    }

    public void close() throws IOException {
        this.httpClient.close();
    }
}
