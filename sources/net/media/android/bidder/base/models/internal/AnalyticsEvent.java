package net.media.android.bidder.base.models.internal;

import java.util.HashMap;
import java.util.Map;

public final class AnalyticsEvent {
    public static final int ADDRESS = 8;
    public static final int DEVICE = 2;
    public static final int EVENT = 1;
    public static final int LOCATION = 5;
    public static final int NETWORK = 4;
    public static final int PUBLISHER = 9;
    public static final int TIMEZONE = 7;
    public static final int USER_AGENT = 6;
    private String mName;
    private Map<String, Object> mProperties;
    private int mType;

    private AnalyticsEvent(String str) {
        this.mType = 1;
        this.mName = str;
        this.mProperties = new HashMap();
    }

    private AnalyticsEvent(String str, int i) {
        this(str);
        this.mType = i;
    }

    public AnalyticsEvent addProperty(String str, Object obj) {
        this.mProperties.put(str, obj);
        return this;
    }

    public AnalyticsEvent addProperties(Map<String, Object> map) {
        this.mProperties.putAll(map);
        return this;
    }

    public String getName() {
        return this.mName;
    }

    public Map<String, Object> getProperties() {
        return this.mProperties;
    }

    public int getType() {
        return this.mType;
    }

    public static final class Events {
        private Events() {
        }

        public static AnalyticsEvent newEvent(String str) {
            return new AnalyticsEvent(str);
        }

        public static AnalyticsEvent newSdkEvent(String str, int i) {
            return new AnalyticsEvent(str, i);
        }
    }

    public String toString() {
        return "name: " + this.mName + " properties: " + this.mProperties.toString();
    }
}
