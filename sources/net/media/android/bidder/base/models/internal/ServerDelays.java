package net.media.android.bidder.base.models.internal;

import mnetinternal.c;

public final class ServerDelays {
    @c(a = "dp_processing")
    private long mDpDelay;
    @c(a = "rtb_exchange")
    private long mRtbDelay;

    public long getRtbDelay() {
        return this.mRtbDelay;
    }

    public long getDpDelay() {
        return this.mDpDelay;
    }
}
