package com.comscore.streaming;

import com.comscore.util.ArrayUtils;
import com.comscore.util.cpp.CppJavaBinder;
import java.util.Map;

public class AdvertisementMetadata extends AssetMetadata {

    public static class Builder extends CppJavaBinder {
        long b;

        public Builder() {
            try {
                this.b = AdvertisementMetadata.newCppInstanceBuilderNative();
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
        }

        public Builder breakNumber(int i) {
            try {
                AdvertisementMetadata.breakNumberNative(this.b, i);
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
            return this;
        }

        public AdvertisementMetadata build() {
            try {
                return new AdvertisementMetadata(AdvertisementMetadata.buildNative(this.b));
            } catch (UnsatisfiedLinkError e) {
                printException(e);
                return new AdvertisementMetadata(0);
            }
        }

        public Builder callToActionUrl(String str) {
            if (str == null) {
                return this;
            }
            try {
                AdvertisementMetadata.callToActionUrlNative(this.b, str);
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
            return this;
        }

        public Builder classifyAsAudioStream(boolean z) {
            try {
                AdvertisementMetadata.classifyAsAudioStreamNative(this.b, z);
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
            return this;
        }

        public Builder clipUrl(String str) {
            if (str == null) {
                return this;
            }
            try {
                AdvertisementMetadata.clipUrlNative(this.b, str);
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
            return this;
        }

        public Builder customLabels(Map<String, String> map) {
            try {
                AdvertisementMetadata.customLabelsNative(this.b, map);
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
            return this;
        }

        public Builder deliveryType(int i) {
            if (!ArrayUtils.contains(AdvertisementDeliveryType.ALLOWED_VALUES, i)) {
                return this;
            }
            try {
                AdvertisementMetadata.deliveryTypeNative(this.b, i);
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
            return this;
        }

        /* access modifiers changed from: protected */
        public void destroyCppObject() {
            try {
                AdvertisementMetadata.destroyCppInstanceBuilderNative(this.b);
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
        }

        public Builder fee(int i) {
            try {
                AdvertisementMetadata.feeNative(this.b, i);
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
            return this;
        }

        public Builder length(long j) {
            try {
                AdvertisementMetadata.lengthNative(this.b, j);
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
            return this;
        }

        public Builder mediaType(int i) {
            if (!ArrayUtils.contains(AdvertisementType.ALLOWED_VALUES, i)) {
                return this;
            }
            try {
                AdvertisementMetadata.mediaTypeNative(this.b, i);
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
            return this;
        }

        public Builder numberInBreak(int i) {
            try {
                AdvertisementMetadata.numberInBreakNative(this.b, i);
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
            return this;
        }

        public Builder owner(int i) {
            if (!ArrayUtils.contains(AdvertisementOwner.ALLOWED_VALUES, i)) {
                return this;
            }
            try {
                AdvertisementMetadata.ownerNative(this.b, i);
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
                AdvertisementMetadata.placementIdNative(this.b, str);
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
            return this;
        }

        public Builder relatedContentMetadata(ContentMetadata contentMetadata) {
            if (contentMetadata == null) {
                return this;
            }
            try {
                AdvertisementMetadata.relatedContentMetadataNative(this.b, contentMetadata.a());
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
            return this;
        }

        public Builder server(String str) {
            if (str == null) {
                return this;
            }
            try {
                AdvertisementMetadata.serverNative(this.b, str);
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
                AdvertisementMetadata.serverCampaignIdNative(this.b, str);
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
            return this;
        }

        public Builder setStack(String str, StackedAdvertisementMetadata stackedAdvertisementMetadata) {
            try {
                AdvertisementMetadata.setStackNative(this.b, str, stackedAdvertisementMetadata.b());
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
                AdvertisementMetadata.siteIdNative(this.b, str);
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
                AdvertisementMetadata.titleNative(this.b, str);
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
            return this;
        }

        public Builder totalBreaks(int i) {
            try {
                AdvertisementMetadata.totalBreaksNative(this.b, i);
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
            return this;
        }

        public Builder totalInBreak(int i) {
            try {
                AdvertisementMetadata.totalInBreakNative(this.b, i);
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
                AdvertisementMetadata.uniqueIdNative(this.b, str);
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
            return this;
        }

        public Builder videoDimensions(int i, int i2) {
            try {
                AdvertisementMetadata.videoDimensionsNative(this.b, i, i2);
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
            return this;
        }
    }

    AdvertisementMetadata(long j) {
        super(j);
    }

    /* access modifiers changed from: private */
    public static native void breakNumberNative(long j, int i);

    /* access modifiers changed from: private */
    public static native long buildNative(long j);

    /* access modifiers changed from: private */
    public static native void callToActionUrlNative(long j, String str);

    /* access modifiers changed from: private */
    public static native void classifyAsAudioStreamNative(long j, boolean z);

    /* access modifiers changed from: private */
    public static native void clipUrlNative(long j, String str);

    /* access modifiers changed from: private */
    public static native void customLabelsNative(long j, Map<String, String> map);

    /* access modifiers changed from: private */
    public static native void deliveryTypeNative(long j, int i);

    /* access modifiers changed from: private */
    public static native void destroyCppInstanceBuilderNative(long j);

    private native void destroyCppInstanceNative(long j);

    /* access modifiers changed from: private */
    public static native void feeNative(long j, int i);

    /* access modifiers changed from: private */
    public static native void lengthNative(long j, long j2);

    /* access modifiers changed from: private */
    public static native void mediaTypeNative(long j, int i);

    /* access modifiers changed from: private */
    public static native long newCppInstanceBuilderNative();

    /* access modifiers changed from: private */
    public static native void numberInBreakNative(long j, int i);

    /* access modifiers changed from: private */
    public static native void ownerNative(long j, int i);

    /* access modifiers changed from: private */
    public static native void placementIdNative(long j, String str);

    /* access modifiers changed from: private */
    public static native void relatedContentMetadataNative(long j, long j2);

    /* access modifiers changed from: private */
    public static native void serverCampaignIdNative(long j, String str);

    /* access modifiers changed from: private */
    public static native void serverNative(long j, String str);

    /* access modifiers changed from: private */
    public static native void setStackNative(long j, String str, long j2);

    /* access modifiers changed from: private */
    public static native void siteIdNative(long j, String str);

    /* access modifiers changed from: private */
    public static native void titleNative(long j, String str);

    /* access modifiers changed from: private */
    public static native void totalBreaksNative(long j, int i);

    /* access modifiers changed from: private */
    public static native void totalInBreakNative(long j, int i);

    /* access modifiers changed from: private */
    public static native void uniqueIdNative(long j, String str);

    /* access modifiers changed from: private */
    public static native void videoDimensionsNative(long j, int i, int i2);

    /* access modifiers changed from: protected */
    public void destroyCppObject() {
        destroyCppInstanceNative(a());
    }
}
