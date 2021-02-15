package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

final class AutoValue_CrashlyticsReport_Session_OperatingSystem extends CrashlyticsReport.Session.OperatingSystem {
    private final String buildVersion;
    private final boolean jailbroken;
    private final int platform;
    private final String version;

    private AutoValue_CrashlyticsReport_Session_OperatingSystem(int i, String str, String str2, boolean z) {
        this.platform = i;
        this.version = str;
        this.buildVersion = str2;
        this.jailbroken = z;
    }

    public int getPlatform() {
        return this.platform;
    }

    public String getVersion() {
        return this.version;
    }

    public String getBuildVersion() {
        return this.buildVersion;
    }

    public boolean isJailbroken() {
        return this.jailbroken;
    }

    public String toString() {
        return "OperatingSystem{platform=" + this.platform + ", version=" + this.version + ", buildVersion=" + this.buildVersion + ", jailbroken=" + this.jailbroken + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CrashlyticsReport.Session.OperatingSystem)) {
            return false;
        }
        CrashlyticsReport.Session.OperatingSystem operatingSystem = (CrashlyticsReport.Session.OperatingSystem) obj;
        if (this.platform != operatingSystem.getPlatform() || !this.version.equals(operatingSystem.getVersion()) || !this.buildVersion.equals(operatingSystem.getBuildVersion()) || this.jailbroken != operatingSystem.isJailbroken()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((this.platform ^ 1000003) * 1000003) ^ this.version.hashCode()) * 1000003) ^ this.buildVersion.hashCode()) * 1000003) ^ (this.jailbroken ? 1231 : 1237);
    }

    static final class Builder extends CrashlyticsReport.Session.OperatingSystem.Builder {
        private String buildVersion;
        private Boolean jailbroken;
        private Integer platform;
        private String version;

        Builder() {
        }

        public CrashlyticsReport.Session.OperatingSystem.Builder setPlatform(int i) {
            this.platform = Integer.valueOf(i);
            return this;
        }

        public CrashlyticsReport.Session.OperatingSystem.Builder setVersion(String str) {
            if (str != null) {
                this.version = str;
                return this;
            }
            throw new NullPointerException("Null version");
        }

        public CrashlyticsReport.Session.OperatingSystem.Builder setBuildVersion(String str) {
            if (str != null) {
                this.buildVersion = str;
                return this;
            }
            throw new NullPointerException("Null buildVersion");
        }

        public CrashlyticsReport.Session.OperatingSystem.Builder setJailbroken(boolean z) {
            this.jailbroken = Boolean.valueOf(z);
            return this;
        }

        public CrashlyticsReport.Session.OperatingSystem build() {
            String str = "";
            if (this.platform == null) {
                str = str + " platform";
            }
            if (this.version == null) {
                str = str + " version";
            }
            if (this.buildVersion == null) {
                str = str + " buildVersion";
            }
            if (this.jailbroken == null) {
                str = str + " jailbroken";
            }
            if (str.isEmpty()) {
                return new AutoValue_CrashlyticsReport_Session_OperatingSystem(this.platform.intValue(), this.version, this.buildVersion, this.jailbroken.booleanValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }
    }
}
