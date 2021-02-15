package com.tapstream.sdk;

public interface Callback<T> {
    void error(Throwable th);

    void success(T t);
}
