package org.mozilla.javascript.commonjs.module.provider;

import com.medscape.android.Constants;
import java.io.Serializable;
import java.net.URLConnection;

public class DefaultUrlConnectionExpiryCalculator implements UrlConnectionExpiryCalculator, Serializable {
    private static final long serialVersionUID = 1;
    private final long relativeExpiry;

    public DefaultUrlConnectionExpiryCalculator() {
        this(Constants.MINUTE_IN_MILLIS);
    }

    public DefaultUrlConnectionExpiryCalculator(long j) {
        if (j >= 0) {
            this.relativeExpiry = j;
            return;
        }
        throw new IllegalArgumentException("relativeExpiry < 0");
    }

    public long calculateExpiry(URLConnection uRLConnection) {
        return System.currentTimeMillis() + this.relativeExpiry;
    }
}
