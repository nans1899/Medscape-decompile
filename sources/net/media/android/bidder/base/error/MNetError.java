package net.media.android.bidder.base.error;

public class MNetError extends Throwable {
    public static final int INSUFFICIENT_AD_SPACE = 4;
    public static final int INTERNAL_ERROR = 5;
    public static final int INVALID_REQUEST = 1;
    public static final int NETWORK_ERROR = 2;
    public static final int NO_AD = 3;
    private int mErrorCode;

    public MNetError(String str) {
        this(str, (Throwable) null);
    }

    public MNetError(String str, int i) {
        this(str, i, (Throwable) null);
    }

    public MNetError(String str, Throwable th) {
        this(str, 5, th);
    }

    public MNetError(String str, int i, Throwable th) {
        super(str, th);
        this.mErrorCode = 5;
        this.mErrorCode = i;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }
}
