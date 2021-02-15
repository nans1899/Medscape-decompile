package com.comscore.streaming;

import com.comscore.util.cpp.CppJavaBinder;
import java.util.Map;

public class StackedAdvertisementMetadata extends CppJavaBinder {
    private long b;

    public static class Builder extends CppJavaBinder {
        long b;

        public Builder() {
            try {
                this.b = StackedAdvertisementMetadata.newCppInstanceBuilderNative();
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
        }

        public StackedAdvertisementMetadata build() {
            try {
                return new StackedAdvertisementMetadata(StackedAdvertisementMetadata.buildNative(this.b));
            } catch (UnsatisfiedLinkError e) {
                printException(e);
                return new StackedAdvertisementMetadata(0);
            }
        }

        public Builder customLabels(Map<String, String> map) {
            try {
                StackedAdvertisementMetadata.customLabelsNative(this.b, map);
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
            return this;
        }

        /* access modifiers changed from: protected */
        public void destroyCppObject() {
            try {
                StackedAdvertisementMetadata.destroyCppInstanceBuilderNative(this.b);
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
        }

        public Builder fee(int i) {
            try {
                StackedAdvertisementMetadata.feeNative(this.b, i);
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
            return this;
        }

        public Builder placementId(String str) {
            if (str == null) {
                return this;
            }
            try {
                StackedAdvertisementMetadata.placementIdNative(this.b, str);
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
            return this;
        }

        public Builder serverCampaignId(String str) {
            if (str == null) {
                return this;
            }
            try {
                StackedAdvertisementMetadata.serverCampaignIdNative(this.b, str);
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
            return this;
        }

        public Builder siteId(String str) {
            if (str == null) {
                return this;
            }
            try {
                StackedAdvertisementMetadata.siteIdNative(this.b, str);
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
            return this;
        }

        public Builder title(String str) {
            if (str == null) {
                return this;
            }
            try {
                StackedAdvertisementMetadata.titleNative(this.b, str);
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
            return this;
        }

        public Builder uniqueId(String str) {
            if (str == null) {
                return this;
            }
            try {
                StackedAdvertisementMetadata.uniqueIdNative(this.b, str);
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
            return this;
        }
    }

    StackedAdvertisementMetadata(long j) {
        this.b = j;
    }

    /* access modifiers changed from: private */
    public static native long buildNative(long j);

    /* access modifiers changed from: private */
    public static native void customLabelsNative(long j, Map<String, String> map);

    /* access modifiers changed from: private */
    public static native void destroyCppInstanceBuilderNative(long j);

    private native void destroyCppInstanceNative(long j);

    /* access modifiers changed from: private */
    public static native void feeNative(long j, int i);

    /* access modifiers changed from: private */
    public static native long newCppInstanceBuilderNative();

    /* access modifiers changed from: private */
    public static native void placementIdNative(long j, String str);

    /* access modifiers changed from: private */
    public static native void serverCampaignIdNative(long j, String str);

    /* access modifiers changed from: private */
    public static native void siteIdNative(long j, String str);

    /* access modifiers changed from: private */
    public static native void titleNative(long j, String str);

    /* access modifiers changed from: private */
    public static native void uniqueIdNative(long j, String str);

    /* access modifiers changed from: package-private */
    public long b() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public void destroyCppObject() {
        destroyCppInstanceNative(b());
    }
}
