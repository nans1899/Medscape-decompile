package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.encoders.annotations.Encodable;

final class AutoValue_CrashlyticsReport_Session_Event_Application_Execution_BinaryImage extends CrashlyticsReport.Session.Event.Application.Execution.BinaryImage {
    private final long baseAddress;
    private final String name;
    private final long size;
    private final String uuid;

    private AutoValue_CrashlyticsReport_Session_Event_Application_Execution_BinaryImage(long j, long j2, String str, String str2) {
        this.baseAddress = j;
        this.size = j2;
        this.name = str;
        this.uuid = str2;
    }

    public long getBaseAddress() {
        return this.baseAddress;
    }

    public long getSize() {
        return this.size;
    }

    public String getName() {
        return this.name;
    }

    @Encodable.Ignore
    public String getUuid() {
        return this.uuid;
    }

    public String toString() {
        return "BinaryImage{baseAddress=" + this.baseAddress + ", size=" + this.size + ", name=" + this.name + ", uuid=" + this.uuid + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CrashlyticsReport.Session.Event.Application.Execution.BinaryImage)) {
            return false;
        }
        CrashlyticsReport.Session.Event.Application.Execution.BinaryImage binaryImage = (CrashlyticsReport.Session.Event.Application.Execution.BinaryImage) obj;
        if (this.baseAddress == binaryImage.getBaseAddress() && this.size == binaryImage.getSize() && this.name.equals(binaryImage.getName())) {
            String str = this.uuid;
            if (str == null) {
                if (binaryImage.getUuid() == null) {
                    return true;
                }
            } else if (str.equals(binaryImage.getUuid())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        long j = this.baseAddress;
        long j2 = this.size;
        int hashCode = (((((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2))) * 1000003) ^ this.name.hashCode()) * 1000003;
        String str = this.uuid;
        return (str == null ? 0 : str.hashCode()) ^ hashCode;
    }

    static final class Builder extends CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder {
        private Long baseAddress;
        private String name;
        private Long size;
        private String uuid;

        Builder() {
        }

        public CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder setBaseAddress(long j) {
            this.baseAddress = Long.valueOf(j);
            return this;
        }

        public CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder setSize(long j) {
            this.size = Long.valueOf(j);
            return this;
        }

        public CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder setName(String str) {
            if (str != null) {
                this.name = str;
                return this;
            }
            throw new NullPointerException("Null name");
        }

        public CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder setUuid(String str) {
            this.uuid = str;
            return this;
        }

        public CrashlyticsReport.Session.Event.Application.Execution.BinaryImage build() {
            String str = "";
            if (this.baseAddress == null) {
                str = str + " baseAddress";
            }
            if (this.size == null) {
                str = str + " size";
            }
            if (this.name == null) {
                str = str + " name";
            }
            if (str.isEmpty()) {
                return new AutoValue_CrashlyticsReport_Session_Event_Application_Execution_BinaryImage(this.baseAddress.longValue(), this.size.longValue(), this.name, this.uuid);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }
    }
}
