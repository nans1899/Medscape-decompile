package com.comscore;

import com.comscore.ClientConfiguration;

public class PublisherConfiguration extends ClientConfiguration {
    private PublisherUniqueDeviceIdListener d;

    public static class Builder extends ClientConfiguration.Builder<Builder, PublisherConfiguration> {
        private PublisherUniqueDeviceIdListener a;

        public Builder() {
        }

        private Builder(Builder builder) {
            super(builder);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public Builder a() {
            return new Builder(this);
        }

        public PublisherConfiguration build() {
            return new PublisherConfiguration(this, this.a);
        }

        public Builder publisherId(String str) {
            setClientId(str);
            return this;
        }

        public Builder publisherUniqueDeviceIdListener(PublisherUniqueDeviceIdListener publisherUniqueDeviceIdListener) {
            this.a = publisherUniqueDeviceIdListener;
            return this;
        }

        /* access modifiers changed from: protected */
        public Builder self() {
            return this;
        }
    }

    PublisherConfiguration(long j) {
        this.b = j;
    }

    private PublisherConfiguration(Builder builder, PublisherUniqueDeviceIdListener publisherUniqueDeviceIdListener) {
        super(builder);
        this.d = publisherUniqueDeviceIdListener;
        try {
            this.b = newCppInstanceNative(builder, publisherUniqueDeviceIdListener);
        } catch (UnsatisfiedLinkError e) {
            printException(e);
        }
    }

    private static native void destroyCppInstanceNative(long j, PublisherUniqueDeviceIdListener publisherUniqueDeviceIdListener);

    private static native String getPublisherIdNative(long j);

    private static native long newCppInstanceNative(Builder builder, PublisherUniqueDeviceIdListener publisherUniqueDeviceIdListener);

    /* access modifiers changed from: protected */
    public void destroyCppObject() {
        destroyCppInstanceNative(this.b, this.d);
    }

    public String getPublisherId() {
        try {
            return getPublisherIdNative(this.b);
        } catch (UnsatisfiedLinkError e) {
            printException(e);
            return null;
        }
    }
}
