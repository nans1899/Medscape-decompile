package net.media.android.bidder.base.models.internal;

public final class ActivityTrackerEvent {
    private String mActivityName;
    private String mEventType;

    private ActivityTrackerEvent() {
    }

    /* access modifiers changed from: private */
    public void setEventType(String str) {
        this.mEventType = str;
    }

    public String getActivityName() {
        return this.mActivityName;
    }

    /* access modifiers changed from: private */
    public void setActivityName(String str) {
        this.mActivityName = str;
    }

    public String getEventType() {
        return this.mEventType;
    }

    public static class Builder {
        private ActivityTrackerEvent mActivityTrackerEvent = new ActivityTrackerEvent();

        public Builder setEventType(String str) {
            this.mActivityTrackerEvent.setEventType(str);
            return this;
        }

        public Builder setActivityName(String str) {
            this.mActivityTrackerEvent.setActivityName(str);
            return this;
        }

        public ActivityTrackerEvent build() {
            return this.mActivityTrackerEvent;
        }
    }
}
