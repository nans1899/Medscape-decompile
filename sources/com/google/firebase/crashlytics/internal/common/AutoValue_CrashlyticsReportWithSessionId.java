package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

final class AutoValue_CrashlyticsReportWithSessionId extends CrashlyticsReportWithSessionId {
    private final CrashlyticsReport report;
    private final String sessionId;

    AutoValue_CrashlyticsReportWithSessionId(CrashlyticsReport crashlyticsReport, String str) {
        if (crashlyticsReport != null) {
            this.report = crashlyticsReport;
            if (str != null) {
                this.sessionId = str;
                return;
            }
            throw new NullPointerException("Null sessionId");
        }
        throw new NullPointerException("Null report");
    }

    public CrashlyticsReport getReport() {
        return this.report;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public String toString() {
        return "CrashlyticsReportWithSessionId{report=" + this.report + ", sessionId=" + this.sessionId + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CrashlyticsReportWithSessionId)) {
            return false;
        }
        CrashlyticsReportWithSessionId crashlyticsReportWithSessionId = (CrashlyticsReportWithSessionId) obj;
        if (!this.report.equals(crashlyticsReportWithSessionId.getReport()) || !this.sessionId.equals(crashlyticsReportWithSessionId.getSessionId())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((this.report.hashCode() ^ 1000003) * 1000003) ^ this.sessionId.hashCode();
    }
}
