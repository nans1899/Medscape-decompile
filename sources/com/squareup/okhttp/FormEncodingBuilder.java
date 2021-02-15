package com.squareup.okhttp;

import okhttp3.HttpUrl;
import okio.Buffer;
import org.jsoup.helper.HttpConnection;

public final class FormEncodingBuilder {
    private static final MediaType CONTENT_TYPE = MediaType.parse(HttpConnection.FORM_URL_ENCODED);
    private final Buffer content = new Buffer();

    public FormEncodingBuilder add(String str, String str2) {
        if (this.content.size() > 0) {
            this.content.writeByte(38);
        }
        HttpUrl.canonicalize(this.content, str, 0, str.length(), HttpUrl.FORM_ENCODE_SET, false, true, true);
        this.content.writeByte(61);
        HttpUrl.canonicalize(this.content, str2, 0, str2.length(), HttpUrl.FORM_ENCODE_SET, false, true, true);
        return this;
    }

    public FormEncodingBuilder addEncoded(String str, String str2) {
        if (this.content.size() > 0) {
            this.content.writeByte(38);
        }
        HttpUrl.canonicalize(this.content, str, 0, str.length(), HttpUrl.FORM_ENCODE_SET, true, true, true);
        this.content.writeByte(61);
        HttpUrl.canonicalize(this.content, str2, 0, str2.length(), HttpUrl.FORM_ENCODE_SET, true, true, true);
        return this;
    }

    public RequestBody build() {
        return RequestBody.create(CONTENT_TYPE, this.content.snapshot());
    }
}
