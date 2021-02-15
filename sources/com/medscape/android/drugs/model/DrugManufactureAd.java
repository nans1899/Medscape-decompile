package com.medscape.android.drugs.model;

import com.medscape.android.ads.NativeDFPAD;
import com.medscape.android.ads.sharethrough.SharethroughFallback;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B3\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\tHÆ\u0003J7\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\t2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/medscape/android/drugs/model/DrugManufactureAd;", "", "ad", "Lcom/medscape/android/ads/NativeDFPAD;", "dm", "Lcom/medscape/android/drugs/model/DrugManufacturer;", "fallBack", "Lcom/medscape/android/ads/sharethrough/SharethroughFallback;", "showProgress", "", "(Lcom/medscape/android/ads/NativeDFPAD;Lcom/medscape/android/drugs/model/DrugManufacturer;Lcom/medscape/android/ads/sharethrough/SharethroughFallback;Z)V", "getAd", "()Lcom/medscape/android/ads/NativeDFPAD;", "getDm", "()Lcom/medscape/android/drugs/model/DrugManufacturer;", "getFallBack", "()Lcom/medscape/android/ads/sharethrough/SharethroughFallback;", "getShowProgress", "()Z", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: DrugManufactureAd.kt */
public final class DrugManufactureAd {
    private final NativeDFPAD ad;
    private final DrugManufacturer dm;
    private final SharethroughFallback fallBack;
    private final boolean showProgress;

    public DrugManufactureAd() {
        this((NativeDFPAD) null, (DrugManufacturer) null, (SharethroughFallback) null, false, 15, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ DrugManufactureAd copy$default(DrugManufactureAd drugManufactureAd, NativeDFPAD nativeDFPAD, DrugManufacturer drugManufacturer, SharethroughFallback sharethroughFallback, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            nativeDFPAD = drugManufactureAd.ad;
        }
        if ((i & 2) != 0) {
            drugManufacturer = drugManufactureAd.dm;
        }
        if ((i & 4) != 0) {
            sharethroughFallback = drugManufactureAd.fallBack;
        }
        if ((i & 8) != 0) {
            z = drugManufactureAd.showProgress;
        }
        return drugManufactureAd.copy(nativeDFPAD, drugManufacturer, sharethroughFallback, z);
    }

    public final NativeDFPAD component1() {
        return this.ad;
    }

    public final DrugManufacturer component2() {
        return this.dm;
    }

    public final SharethroughFallback component3() {
        return this.fallBack;
    }

    public final boolean component4() {
        return this.showProgress;
    }

    public final DrugManufactureAd copy(NativeDFPAD nativeDFPAD, DrugManufacturer drugManufacturer, SharethroughFallback sharethroughFallback, boolean z) {
        return new DrugManufactureAd(nativeDFPAD, drugManufacturer, sharethroughFallback, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DrugManufactureAd)) {
            return false;
        }
        DrugManufactureAd drugManufactureAd = (DrugManufactureAd) obj;
        return Intrinsics.areEqual((Object) this.ad, (Object) drugManufactureAd.ad) && Intrinsics.areEqual((Object) this.dm, (Object) drugManufactureAd.dm) && Intrinsics.areEqual((Object) this.fallBack, (Object) drugManufactureAd.fallBack) && this.showProgress == drugManufactureAd.showProgress;
    }

    public int hashCode() {
        NativeDFPAD nativeDFPAD = this.ad;
        int i = 0;
        int hashCode = (nativeDFPAD != null ? nativeDFPAD.hashCode() : 0) * 31;
        DrugManufacturer drugManufacturer = this.dm;
        int hashCode2 = (hashCode + (drugManufacturer != null ? drugManufacturer.hashCode() : 0)) * 31;
        SharethroughFallback sharethroughFallback = this.fallBack;
        if (sharethroughFallback != null) {
            i = sharethroughFallback.hashCode();
        }
        int i2 = (hashCode2 + i) * 31;
        boolean z = this.showProgress;
        if (z) {
            z = true;
        }
        return i2 + (z ? 1 : 0);
    }

    public String toString() {
        return "DrugManufactureAd(ad=" + this.ad + ", dm=" + this.dm + ", fallBack=" + this.fallBack + ", showProgress=" + this.showProgress + ")";
    }

    public DrugManufactureAd(NativeDFPAD nativeDFPAD, DrugManufacturer drugManufacturer, SharethroughFallback sharethroughFallback, boolean z) {
        this.ad = nativeDFPAD;
        this.dm = drugManufacturer;
        this.fallBack = sharethroughFallback;
        this.showProgress = z;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DrugManufactureAd(NativeDFPAD nativeDFPAD, DrugManufacturer drugManufacturer, SharethroughFallback sharethroughFallback, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : nativeDFPAD, (i & 2) != 0 ? null : drugManufacturer, (i & 4) != 0 ? null : sharethroughFallback, (i & 8) != 0 ? true : z);
    }

    public final NativeDFPAD getAd() {
        return this.ad;
    }

    public final DrugManufacturer getDm() {
        return this.dm;
    }

    public final SharethroughFallback getFallBack() {
        return this.fallBack;
    }

    public final boolean getShowProgress() {
        return this.showProgress;
    }
}
