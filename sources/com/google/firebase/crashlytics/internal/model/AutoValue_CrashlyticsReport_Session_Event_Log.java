package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

final class AutoValue_CrashlyticsReport_Session_Event_Log extends CrashlyticsReport.Session.Event.Log {
    private final String content;

    private AutoValue_CrashlyticsReport_Session_Event_Log(String str) {
        this.content = str;
    }

    public String getContent() {
        return this.content;
    }

    public String toString() {
        return "Log{content=" + this.content + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CrashlyticsReport.Session.Event.Log) {
            return this.content.equals(((CrashlyticsReport.Session.Event.Log) obj).getContent());
        }
        return false;
    }

    public int hashCode() {
        return this.content.hashCode() ^ 1000003;
    }

    static final class Builder extends CrashlyticsReport.Session.Event.Log.Builder {
        private String content;

        Builder() {
        }

        public CrashlyticsReport.Session.Event.Log.Builder setContent(String str) {
            if (str != null) {
                this.content = str;
                return this;
            }
            throw new NullPointerException("Null content");
        }

        public CrashlyticsReport.Session.Event.Log build() {
            String str = "";
            if (this.content == null) {
                str = str + " content";
            }
            if (str.isEmpty()) {
                return new AutoValue_CrashlyticsReport_Session_Event_Log(this.content);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }
    }
}
