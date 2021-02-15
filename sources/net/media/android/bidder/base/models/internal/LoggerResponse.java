package net.media.android.bidder.base.models.internal;

import mnetinternal.c;

public final class LoggerResponse {
    @c(a = "message")
    private String message;
    @c(a = "success")
    private boolean success;

    public String getMessage() {
        return this.message;
    }

    public boolean isSuccess() {
        return this.success;
    }
}
