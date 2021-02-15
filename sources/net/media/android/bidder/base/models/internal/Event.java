package net.media.android.bidder.base.models.internal;

import java.io.Serializable;
import java.util.Map;
import mnetinternal.c;

public final class Event implements Serializable {
    @c(a = "event_timestamp")
    private long mEventTimestamp;
    @c(a = "event_type")
    private String mEventType;
    @c(a = "ifa")
    private String mIfa;
    @c(a = "params")
    private Map<String, Object> mParams;
    private Map<String, Object> mUserProps;

    private Event() {
        this.mEventTimestamp = System.currentTimeMillis();
    }

    public String getType() {
        return this.mEventType;
    }

    public Map<String, Object> getParams() {
        return this.mParams;
    }

    public Map<String, Object> getUserProps() {
        return this.mUserProps;
    }

    public long getTimestamp() {
        return this.mEventTimestamp;
    }

    /* access modifiers changed from: private */
    public void setType(String str) {
        this.mEventType = str;
    }

    /* access modifiers changed from: private */
    public void setParams(Map<String, Object> map) {
        this.mParams = map;
    }

    /* access modifiers changed from: private */
    public void setUserProps(Map<String, Object> map) {
        this.mUserProps = map;
    }

    /* access modifiers changed from: private */
    public void setAdvertisingId(String str) {
        this.mIfa = str;
    }

    public String getAdvertisingId() {
        return this.mIfa;
    }

    /* access modifiers changed from: private */
    public void setTimestamp(long j) {
        this.mEventTimestamp = j;
    }

    public static class Builder {
        private Event mEvent = new Event();

        public Builder setAdvertisingId(String str) {
            this.mEvent.setAdvertisingId(str);
            return this;
        }

        public Builder setUserProperties(Map<String, Object> map) {
            this.mEvent.setUserProps(map);
            return this;
        }

        public Builder setType(String str) {
            this.mEvent.setType(str);
            return this;
        }

        public Builder setParams(Map<String, Object> map) {
            this.mEvent.setParams(map);
            return this;
        }

        public Builder setTimestamp(long j) {
            this.mEvent.setTimestamp(j);
            return this;
        }

        public Event build() {
            return this.mEvent;
        }
    }
}
