package net.media.android.bidder.base.models.internal;

import net.media.android.bidder.base.error.MNetError;

public final class AdTrackerEvent {
    public static final String EVENT_ACTIVITY_CREATED = "event_activity_created";
    public static final String EVENT_ACTIVITY_DESTROYED = "event_activity_destroyed";
    public static final String EVENT_ACTIVITY_PAUSED = "event_activity_paused";
    public static final String EVENT_ACTIVITY_RESUMED = "event_activity_resumed";
    public static final String EVENT_APP_IN_BACKGROUND = "event_app_in_background";
    public static final String EVENT_APP_IN_FOREGROUND = "event_app_in_foreground";
    public static final String EVENT_CONFIG_FETCH_COMPLETE = "event_config_fetch_complete";
    public static final String EVENT_DONE_SDK_CONFIG_FETCH = "event_done_fetching_sdk_config";
    public static final String EVENT_NETWORK_CHANGED = "event_network_changed";
    private int mAdType;
    private Object mData;
    private MNetError mError;
    private String mEventType;
    private String mId;
    private String mUrl;

    private AdTrackerEvent() {
    }

    /* access modifiers changed from: private */
    public void setAdType(int i) {
        this.mAdType = i;
    }

    /* access modifiers changed from: private */
    public void setEventType(String str) {
        this.mEventType = str;
    }

    /* access modifiers changed from: private */
    public void setId(String str) {
        this.mId = str;
    }

    /* access modifiers changed from: private */
    public void setUrl(String str) {
        this.mUrl = str;
    }

    /* access modifiers changed from: private */
    public void setError(MNetError mNetError) {
        this.mError = mNetError;
    }

    /* access modifiers changed from: private */
    public void setData(Object obj) {
        this.mData = obj;
    }

    public int getAdType() {
        return this.mAdType;
    }

    public String getId() {
        return this.mId;
    }

    public String getEventType() {
        return this.mEventType;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public MNetError getError() {
        return this.mError;
    }

    public Object getData() {
        return this.mData;
    }

    public static class Builder {
        private AdTrackerEvent mAdTrackerEvent = new AdTrackerEvent();

        public Builder setAdType(int i) {
            this.mAdTrackerEvent.setAdType(i);
            return this;
        }

        public Builder setEventType(String str) {
            this.mAdTrackerEvent.setEventType(str);
            return this;
        }

        public Builder setId(String str) {
            this.mAdTrackerEvent.setId(str);
            return this;
        }

        public Builder setUrl(String str) {
            this.mAdTrackerEvent.setUrl(str);
            return this;
        }

        public Builder setError(MNetError mNetError) {
            this.mAdTrackerEvent.setError(mNetError);
            return this;
        }

        public Builder setData(Object obj) {
            this.mAdTrackerEvent.setData(obj);
            return this;
        }

        public AdTrackerEvent build() {
            return this.mAdTrackerEvent;
        }
    }
}
