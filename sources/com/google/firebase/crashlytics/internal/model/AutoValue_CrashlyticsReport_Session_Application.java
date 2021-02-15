package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

final class AutoValue_CrashlyticsReport_Session_Application extends CrashlyticsReport.Session.Application {
    private final String displayVersion;
    private final String identifier;
    private final String installationUuid;
    private final CrashlyticsReport.Session.Application.Organization organization;
    private final String version;

    private AutoValue_CrashlyticsReport_Session_Application(String str, String str2, String str3, CrashlyticsReport.Session.Application.Organization organization2, String str4) {
        this.identifier = str;
        this.version = str2;
        this.displayVersion = str3;
        this.organization = organization2;
        this.installationUuid = str4;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getVersion() {
        return this.version;
    }

    public String getDisplayVersion() {
        return this.displayVersion;
    }

    public CrashlyticsReport.Session.Application.Organization getOrganization() {
        return this.organization;
    }

    public String getInstallationUuid() {
        return this.installationUuid;
    }

    public String toString() {
        return "Application{identifier=" + this.identifier + ", version=" + this.version + ", displayVersion=" + this.displayVersion + ", organization=" + this.organization + ", installationUuid=" + this.installationUuid + "}";
    }

    public boolean equals(Object obj) {
        String str;
        CrashlyticsReport.Session.Application.Organization organization2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CrashlyticsReport.Session.Application)) {
            return false;
        }
        CrashlyticsReport.Session.Application application = (CrashlyticsReport.Session.Application) obj;
        if (this.identifier.equals(application.getIdentifier()) && this.version.equals(application.getVersion()) && ((str = this.displayVersion) != null ? str.equals(application.getDisplayVersion()) : application.getDisplayVersion() == null) && ((organization2 = this.organization) != null ? organization2.equals(application.getOrganization()) : application.getOrganization() == null)) {
            String str2 = this.installationUuid;
            if (str2 == null) {
                if (application.getInstallationUuid() == null) {
                    return true;
                }
            } else if (str2.equals(application.getInstallationUuid())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((this.identifier.hashCode() ^ 1000003) * 1000003) ^ this.version.hashCode()) * 1000003;
        String str = this.displayVersion;
        int i = 0;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        CrashlyticsReport.Session.Application.Organization organization2 = this.organization;
        int hashCode3 = (hashCode2 ^ (organization2 == null ? 0 : organization2.hashCode())) * 1000003;
        String str2 = this.installationUuid;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode3 ^ i;
    }

    /* access modifiers changed from: protected */
    public CrashlyticsReport.Session.Application.Builder toBuilder() {
        return new Builder(this);
    }

    static final class Builder extends CrashlyticsReport.Session.Application.Builder {
        private String displayVersion;
        private String identifier;
        private String installationUuid;
        private CrashlyticsReport.Session.Application.Organization organization;
        private String version;

        Builder() {
        }

        private Builder(CrashlyticsReport.Session.Application application) {
            this.identifier = application.getIdentifier();
            this.version = application.getVersion();
            this.displayVersion = application.getDisplayVersion();
            this.organization = application.getOrganization();
            this.installationUuid = application.getInstallationUuid();
        }

        public CrashlyticsReport.Session.Application.Builder setIdentifier(String str) {
            if (str != null) {
                this.identifier = str;
                return this;
            }
            throw new NullPointerException("Null identifier");
        }

        public CrashlyticsReport.Session.Application.Builder setVersion(String str) {
            if (str != null) {
                this.version = str;
                return this;
            }
            throw new NullPointerException("Null version");
        }

        public CrashlyticsReport.Session.Application.Builder setDisplayVersion(String str) {
            this.displayVersion = str;
            return this;
        }

        public CrashlyticsReport.Session.Application.Builder setOrganization(CrashlyticsReport.Session.Application.Organization organization2) {
            this.organization = organization2;
            return this;
        }

        public CrashlyticsReport.Session.Application.Builder setInstallationUuid(String str) {
            this.installationUuid = str;
            return this;
        }

        public CrashlyticsReport.Session.Application build() {
            String str = "";
            if (this.identifier == null) {
                str = str + " identifier";
            }
            if (this.version == null) {
                str = str + " version";
            }
            if (str.isEmpty()) {
                return new AutoValue_CrashlyticsReport_Session_Application(this.identifier, this.version, this.displayVersion, this.organization, this.installationUuid);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }
    }
}
