package com.wbmd.ads.model;

import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B'\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0007HÆ\u0003J+\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/wbmd/ads/model/AdContainer;", "", "status", "Lcom/wbmd/ads/model/AdStatus;", "adView", "Lcom/google/android/gms/ads/doubleclick/PublisherAdView;", "nativeAd", "Lcom/google/android/gms/ads/formats/NativeCustomTemplateAd;", "(Lcom/wbmd/ads/model/AdStatus;Lcom/google/android/gms/ads/doubleclick/PublisherAdView;Lcom/google/android/gms/ads/formats/NativeCustomTemplateAd;)V", "getAdView", "()Lcom/google/android/gms/ads/doubleclick/PublisherAdView;", "getNativeAd", "()Lcom/google/android/gms/ads/formats/NativeCustomTemplateAd;", "getStatus", "()Lcom/wbmd/ads/model/AdStatus;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "wbmdadsdk_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: AdContainer.kt */
public final class AdContainer {
    private final PublisherAdView adView;
    private final NativeCustomTemplateAd nativeAd;
    private final AdStatus status;

    public AdContainer() {
        this((AdStatus) null, (PublisherAdView) null, (NativeCustomTemplateAd) null, 7, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ AdContainer copy$default(AdContainer adContainer, AdStatus adStatus, PublisherAdView publisherAdView, NativeCustomTemplateAd nativeCustomTemplateAd, int i, Object obj) {
        if ((i & 1) != 0) {
            adStatus = adContainer.status;
        }
        if ((i & 2) != 0) {
            publisherAdView = adContainer.adView;
        }
        if ((i & 4) != 0) {
            nativeCustomTemplateAd = adContainer.nativeAd;
        }
        return adContainer.copy(adStatus, publisherAdView, nativeCustomTemplateAd);
    }

    public final AdStatus component1() {
        return this.status;
    }

    public final PublisherAdView component2() {
        return this.adView;
    }

    public final NativeCustomTemplateAd component3() {
        return this.nativeAd;
    }

    public final AdContainer copy(AdStatus adStatus, PublisherAdView publisherAdView, NativeCustomTemplateAd nativeCustomTemplateAd) {
        Intrinsics.checkNotNullParameter(adStatus, "status");
        return new AdContainer(adStatus, publisherAdView, nativeCustomTemplateAd);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdContainer)) {
            return false;
        }
        AdContainer adContainer = (AdContainer) obj;
        return Intrinsics.areEqual((Object) this.status, (Object) adContainer.status) && Intrinsics.areEqual((Object) this.adView, (Object) adContainer.adView) && Intrinsics.areEqual((Object) this.nativeAd, (Object) adContainer.nativeAd);
    }

    public int hashCode() {
        AdStatus adStatus = this.status;
        int i = 0;
        int hashCode = (adStatus != null ? adStatus.hashCode() : 0) * 31;
        PublisherAdView publisherAdView = this.adView;
        int hashCode2 = (hashCode + (publisherAdView != null ? publisherAdView.hashCode() : 0)) * 31;
        NativeCustomTemplateAd nativeCustomTemplateAd = this.nativeAd;
        if (nativeCustomTemplateAd != null) {
            i = nativeCustomTemplateAd.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "AdContainer(status=" + this.status + ", adView=" + this.adView + ", nativeAd=" + this.nativeAd + ")";
    }

    public AdContainer(AdStatus adStatus, PublisherAdView publisherAdView, NativeCustomTemplateAd nativeCustomTemplateAd) {
        Intrinsics.checkNotNullParameter(adStatus, "status");
        this.status = adStatus;
        this.adView = publisherAdView;
        this.nativeAd = nativeCustomTemplateAd;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AdContainer(AdStatus adStatus, PublisherAdView publisherAdView, NativeCustomTemplateAd nativeCustomTemplateAd, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? AdStatus.none : adStatus, (i & 2) != 0 ? null : publisherAdView, (i & 4) != 0 ? null : nativeCustomTemplateAd);
    }

    public final AdStatus getStatus() {
        return this.status;
    }

    public final PublisherAdView getAdView() {
        return this.adView;
    }

    public final NativeCustomTemplateAd getNativeAd() {
        return this.nativeAd;
    }
}
