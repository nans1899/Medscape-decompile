package com.tapstream.sdk.http;

import java.io.Closeable;
import java.io.IOException;

public interface HttpClient extends Closeable {
    HttpResponse sendRequest(HttpRequest httpRequest) throws IOException;
}
