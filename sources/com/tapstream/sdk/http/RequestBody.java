package com.tapstream.sdk.http;

public interface RequestBody {
    String contentType();

    byte[] toBytes();
}
