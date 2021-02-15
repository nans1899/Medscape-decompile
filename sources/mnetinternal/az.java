package mnetinternal;

import net.media.android.bidder.base.logging.Logger;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;
import okio.Sink;

class az implements Interceptor {
    az() {
    }

    public Response intercept(Interceptor.Chain chain) {
        Request request = chain.request();
        if (request.method().equalsIgnoreCase("GET") || request.method().equalsIgnoreCase("HEAD")) {
            return chain.proceed(request);
        }
        if (request.body() == null) {
            Request build = request.newBuilder().header("Content-Encoding", "gzip").build();
            Logger.debug("##GzipRequestInterceptor##", "GzipRequestInterceptor　newRequest.toString():" + build.toString());
            return chain.proceed(build);
        } else if (request.header("Content-Encoding") != null) {
            return chain.proceed(request);
        } else {
            return chain.proceed(request.newBuilder().header("Content-Encoding", "gzip").method(request.method(), a(b(request.body()))).build());
        }
    }

    private RequestBody a(final RequestBody requestBody) {
        final Buffer buffer = new Buffer();
        requestBody.writeTo(buffer);
        return new RequestBody() {
            public MediaType contentType() {
                return requestBody.contentType();
            }

            public long contentLength() {
                return buffer.size();
            }

            public void writeTo(BufferedSink bufferedSink) {
                bufferedSink.write(buffer.snapshot());
            }
        };
    }

    private RequestBody b(final RequestBody requestBody) {
        return new RequestBody() {
            public long contentLength() {
                return -1;
            }

            public MediaType contentType() {
                return requestBody.contentType();
            }

            public void writeTo(BufferedSink bufferedSink) {
                BufferedSink buffer = Okio.buffer((Sink) new GzipSink(bufferedSink));
                requestBody.writeTo(buffer);
                buffer.close();
            }
        };
    }
}
