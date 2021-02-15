package com.tapstream.sdk.http;

public class ByteArrayRequestBody implements RequestBody {
    private final byte[] body;

    public String contentType() {
        return "byte array";
    }

    public ByteArrayRequestBody() {
        this(new byte[0]);
    }

    public ByteArrayRequestBody(byte[] bArr) {
        this.body = bArr;
    }

    public byte[] toBytes() {
        return this.body;
    }
}
