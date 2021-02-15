package net.media.android.bidder.base;

public enum ConsentStatus {
    UNKNOWN(2),
    GIVEN(1),
    REVOKED(0);
    
    private final int value;

    private ConsentStatus(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public static ConsentStatus fromValue(int i) {
        if (i == 0) {
            return UNKNOWN;
        }
        if (i == 1) {
            return GIVEN;
        }
        if (i != 2) {
            return null;
        }
        return REVOKED;
    }
}
