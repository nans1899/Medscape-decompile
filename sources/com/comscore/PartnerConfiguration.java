package com.comscore;

import com.comscore.ClientConfiguration;

public class PartnerConfiguration extends ClientConfiguration {

    public static class Builder extends ClientConfiguration.Builder<Builder, PartnerConfiguration> {
        protected String externalClientId;

        public Builder() {
        }

        private Builder(Builder builder) {
            super(builder);
            this.externalClientId = builder.externalClientId;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public Builder a() {
            return new Builder(this);
        }

        public PartnerConfiguration build() {
            return new PartnerConfiguration(this);
        }

        public Builder externalClientId(String str) {
            this.externalClientId = str;
            return this;
        }

        public Builder partnerId(String str) {
            setClientId(str);
            return this;
        }

        /* access modifiers changed from: protected */
        public Builder self() {
            return this;
        }
    }

    PartnerConfiguration(long j) {
        this.b = j;
    }

    private PartnerConfiguration(Builder builder) {
        super(builder);
        try {
            this.b = newCppInstanceNative(builder);
        } catch (UnsatisfiedLinkError e) {
            printException(e);
        }
    }

    private static native void destroyCppInstanceNative(long j);

    private static native String getExternalClientIdNative(long j);

    private static native String getPartnerIdNative(long j);

    private static native long newCppInstanceNative(Builder builder);

    /* access modifiers changed from: protected */
    public void destroyCppObject() {
        destroyCppInstanceNative(this.b);
    }

    public String getExternalClientId() {
        try {
            return getExternalClientIdNative(this.b);
        } catch (UnsatisfiedLinkError e) {
            printException(e);
            return null;
        }
    }

    public String getPartnerId() {
        try {
            return getPartnerIdNative(this.b);
        } catch (UnsatisfiedLinkError e) {
            printException(e);
            return null;
        }
    }
}
