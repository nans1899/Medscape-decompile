package net.media.android.bidder.base.models.internal;

import mnetinternal.c;

public final class Log {
    @c(a = "message")
    private String message;
    @c(a = "tag")
    private String tag;

    public Log(String str, String str2) {
        this.tag = str;
        this.message = str2;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
