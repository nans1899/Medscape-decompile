package net.media.android.bidder.base.models.internal;

public final class TimeEvent {
    private String mName;
    private long mStartTime;
    private long mTimeTaken;

    public TimeEvent(String str) {
        this.mName = str;
        this.mStartTime = System.currentTimeMillis();
    }

    public TimeEvent(String str, long j) {
        this.mName = str;
        this.mTimeTaken = j;
    }

    public String getName() {
        return this.mName;
    }

    public void endTimeEvent() {
        this.mTimeTaken = System.currentTimeMillis() - this.mStartTime;
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public long getTimeTaken() {
        return this.mTimeTaken;
    }
}
