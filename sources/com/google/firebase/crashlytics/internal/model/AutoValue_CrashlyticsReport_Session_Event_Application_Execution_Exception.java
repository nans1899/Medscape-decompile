package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

final class AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Exception extends CrashlyticsReport.Session.Event.Application.Execution.Exception {
    private final CrashlyticsReport.Session.Event.Application.Execution.Exception causedBy;
    private final ImmutableList<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> frames;
    private final int overflowCount;
    private final String reason;
    private final String type;

    private AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Exception(String str, String str2, ImmutableList<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> immutableList, CrashlyticsReport.Session.Event.Application.Execution.Exception exception, int i) {
        this.type = str;
        this.reason = str2;
        this.frames = immutableList;
        this.causedBy = exception;
        this.overflowCount = i;
    }

    public String getType() {
        return this.type;
    }

    public String getReason() {
        return this.reason;
    }

    public ImmutableList<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> getFrames() {
        return this.frames;
    }

    public CrashlyticsReport.Session.Event.Application.Execution.Exception getCausedBy() {
        return this.causedBy;
    }

    public int getOverflowCount() {
        return this.overflowCount;
    }

    public String toString() {
        return "Exception{type=" + this.type + ", reason=" + this.reason + ", frames=" + this.frames + ", causedBy=" + this.causedBy + ", overflowCount=" + this.overflowCount + "}";
    }

    public boolean equals(Object obj) {
        String str;
        CrashlyticsReport.Session.Event.Application.Execution.Exception exception;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CrashlyticsReport.Session.Event.Application.Execution.Exception)) {
            return false;
        }
        CrashlyticsReport.Session.Event.Application.Execution.Exception exception2 = (CrashlyticsReport.Session.Event.Application.Execution.Exception) obj;
        if (!this.type.equals(exception2.getType()) || ((str = this.reason) != null ? !str.equals(exception2.getReason()) : exception2.getReason() != null) || !this.frames.equals(exception2.getFrames()) || ((exception = this.causedBy) != null ? !exception.equals(exception2.getCausedBy()) : exception2.getCausedBy() != null) || this.overflowCount != exception2.getOverflowCount()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = (this.type.hashCode() ^ 1000003) * 1000003;
        String str = this.reason;
        int i = 0;
        int hashCode2 = (((hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.frames.hashCode()) * 1000003;
        CrashlyticsReport.Session.Event.Application.Execution.Exception exception = this.causedBy;
        if (exception != null) {
            i = exception.hashCode();
        }
        return ((hashCode2 ^ i) * 1000003) ^ this.overflowCount;
    }

    static final class Builder extends CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder {
        private CrashlyticsReport.Session.Event.Application.Execution.Exception causedBy;
        private ImmutableList<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> frames;
        private Integer overflowCount;
        private String reason;
        private String type;

        Builder() {
        }

        public CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder setType(String str) {
            if (str != null) {
                this.type = str;
                return this;
            }
            throw new NullPointerException("Null type");
        }

        public CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder setReason(String str) {
            this.reason = str;
            return this;
        }

        public CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder setFrames(ImmutableList<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> immutableList) {
            if (immutableList != null) {
                this.frames = immutableList;
                return this;
            }
            throw new NullPointerException("Null frames");
        }

        public CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder setCausedBy(CrashlyticsReport.Session.Event.Application.Execution.Exception exception) {
            this.causedBy = exception;
            return this;
        }

        public CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder setOverflowCount(int i) {
            this.overflowCount = Integer.valueOf(i);
            return this;
        }

        public CrashlyticsReport.Session.Event.Application.Execution.Exception build() {
            String str = "";
            if (this.type == null) {
                str = str + " type";
            }
            if (this.frames == null) {
                str = str + " frames";
            }
            if (this.overflowCount == null) {
                str = str + " overflowCount";
            }
            if (str.isEmpty()) {
                return new AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Exception(this.type, this.reason, this.frames, this.causedBy, this.overflowCount.intValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }
    }
}
