package net.media.android.bidder.base;

public enum SubjectToGDPR {
    DISABLED(0),
    ENABLED(1),
    UNKNOWN(2);
    
    private final int value;

    private SubjectToGDPR(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public static SubjectToGDPR fromValue(int i) {
        if (i == 0) {
            return DISABLED;
        }
        if (i == 1) {
            return ENABLED;
        }
        if (i != 2) {
            return null;
        }
        return UNKNOWN;
    }
}
