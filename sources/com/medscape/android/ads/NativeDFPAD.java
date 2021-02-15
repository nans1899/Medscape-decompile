package com.medscape.android.ads;

import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001BG\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\t¢\u0006\u0002\u0010\rJ\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010&\u001a\u00020\u0007HÆ\u0003J\t\u0010'\u001a\u00020\tHÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\t\u0010)\u001a\u00020\tHÆ\u0003JK\u0010*\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\tHÆ\u0001J\u0013\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010.\u001a\u00020\u0007HÖ\u0001J\t\u0010/\u001a\u00020\tHÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\f\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001f\"\u0004\b#\u0010!¨\u00060"}, d2 = {"Lcom/medscape/android/ads/NativeDFPAD;", "", "dfpAD", "Lcom/google/android/gms/ads/doubleclick/PublisherAdView;", "nativeAD", "Lcom/google/android/gms/ads/formats/NativeCustomTemplateAd;", "pgValue", "", "posValue", "", "nativeStyle", "Lcom/medscape/android/ads/NativeStyleAd;", "styleConfig", "(Lcom/google/android/gms/ads/doubleclick/PublisherAdView;Lcom/google/android/gms/ads/formats/NativeCustomTemplateAd;ILjava/lang/String;Lcom/medscape/android/ads/NativeStyleAd;Ljava/lang/String;)V", "getDfpAD", "()Lcom/google/android/gms/ads/doubleclick/PublisherAdView;", "setDfpAD", "(Lcom/google/android/gms/ads/doubleclick/PublisherAdView;)V", "getNativeAD", "()Lcom/google/android/gms/ads/formats/NativeCustomTemplateAd;", "setNativeAD", "(Lcom/google/android/gms/ads/formats/NativeCustomTemplateAd;)V", "getNativeStyle", "()Lcom/medscape/android/ads/NativeStyleAd;", "setNativeStyle", "(Lcom/medscape/android/ads/NativeStyleAd;)V", "getPgValue", "()I", "setPgValue", "(I)V", "getPosValue", "()Ljava/lang/String;", "setPosValue", "(Ljava/lang/String;)V", "getStyleConfig", "setStyleConfig", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: NativeDFPAD.kt */
public final class NativeDFPAD {
    private PublisherAdView dfpAD;
    private NativeCustomTemplateAd nativeAD;
    private NativeStyleAd nativeStyle;
    private int pgValue;
    private String posValue;
    private String styleConfig;

    public NativeDFPAD() {
        this((PublisherAdView) null, (NativeCustomTemplateAd) null, 0, (String) null, (NativeStyleAd) null, (String) null, 63, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ NativeDFPAD copy$default(NativeDFPAD nativeDFPAD, PublisherAdView publisherAdView, NativeCustomTemplateAd nativeCustomTemplateAd, int i, String str, NativeStyleAd nativeStyleAd, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            publisherAdView = nativeDFPAD.dfpAD;
        }
        if ((i2 & 2) != 0) {
            nativeCustomTemplateAd = nativeDFPAD.nativeAD;
        }
        NativeCustomTemplateAd nativeCustomTemplateAd2 = nativeCustomTemplateAd;
        if ((i2 & 4) != 0) {
            i = nativeDFPAD.pgValue;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            str = nativeDFPAD.posValue;
        }
        String str3 = str;
        if ((i2 & 16) != 0) {
            nativeStyleAd = nativeDFPAD.nativeStyle;
        }
        NativeStyleAd nativeStyleAd2 = nativeStyleAd;
        if ((i2 & 32) != 0) {
            str2 = nativeDFPAD.styleConfig;
        }
        return nativeDFPAD.copy(publisherAdView, nativeCustomTemplateAd2, i3, str3, nativeStyleAd2, str2);
    }

    public final PublisherAdView component1() {
        return this.dfpAD;
    }

    public final NativeCustomTemplateAd component2() {
        return this.nativeAD;
    }

    public final int component3() {
        return this.pgValue;
    }

    public final String component4() {
        return this.posValue;
    }

    public final NativeStyleAd component5() {
        return this.nativeStyle;
    }

    public final String component6() {
        return this.styleConfig;
    }

    public final NativeDFPAD copy(PublisherAdView publisherAdView, NativeCustomTemplateAd nativeCustomTemplateAd, int i, String str, NativeStyleAd nativeStyleAd, String str2) {
        Intrinsics.checkNotNullParameter(str, "posValue");
        Intrinsics.checkNotNullParameter(str2, "styleConfig");
        return new NativeDFPAD(publisherAdView, nativeCustomTemplateAd, i, str, nativeStyleAd, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NativeDFPAD)) {
            return false;
        }
        NativeDFPAD nativeDFPAD = (NativeDFPAD) obj;
        return Intrinsics.areEqual((Object) this.dfpAD, (Object) nativeDFPAD.dfpAD) && Intrinsics.areEqual((Object) this.nativeAD, (Object) nativeDFPAD.nativeAD) && this.pgValue == nativeDFPAD.pgValue && Intrinsics.areEqual((Object) this.posValue, (Object) nativeDFPAD.posValue) && Intrinsics.areEqual((Object) this.nativeStyle, (Object) nativeDFPAD.nativeStyle) && Intrinsics.areEqual((Object) this.styleConfig, (Object) nativeDFPAD.styleConfig);
    }

    public int hashCode() {
        PublisherAdView publisherAdView = this.dfpAD;
        int i = 0;
        int hashCode = (publisherAdView != null ? publisherAdView.hashCode() : 0) * 31;
        NativeCustomTemplateAd nativeCustomTemplateAd = this.nativeAD;
        int hashCode2 = (((hashCode + (nativeCustomTemplateAd != null ? nativeCustomTemplateAd.hashCode() : 0)) * 31) + this.pgValue) * 31;
        String str = this.posValue;
        int hashCode3 = (hashCode2 + (str != null ? str.hashCode() : 0)) * 31;
        NativeStyleAd nativeStyleAd = this.nativeStyle;
        int hashCode4 = (hashCode3 + (nativeStyleAd != null ? nativeStyleAd.hashCode() : 0)) * 31;
        String str2 = this.styleConfig;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode4 + i;
    }

    public String toString() {
        return "NativeDFPAD(dfpAD=" + this.dfpAD + ", nativeAD=" + this.nativeAD + ", pgValue=" + this.pgValue + ", posValue=" + this.posValue + ", nativeStyle=" + this.nativeStyle + ", styleConfig=" + this.styleConfig + ")";
    }

    public NativeDFPAD(PublisherAdView publisherAdView, NativeCustomTemplateAd nativeCustomTemplateAd, int i, String str, NativeStyleAd nativeStyleAd, String str2) {
        Intrinsics.checkNotNullParameter(str, "posValue");
        Intrinsics.checkNotNullParameter(str2, "styleConfig");
        this.dfpAD = publisherAdView;
        this.nativeAD = nativeCustomTemplateAd;
        this.pgValue = i;
        this.posValue = str;
        this.nativeStyle = nativeStyleAd;
        this.styleConfig = str2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ NativeDFPAD(com.google.android.gms.ads.doubleclick.PublisherAdView r5, com.google.android.gms.ads.formats.NativeCustomTemplateAd r6, int r7, java.lang.String r8, com.medscape.android.ads.NativeStyleAd r9, java.lang.String r10, int r11, kotlin.jvm.internal.DefaultConstructorMarker r12) {
        /*
            r4 = this;
            r12 = r11 & 1
            r0 = 0
            if (r12 == 0) goto L_0x0008
            r5 = r0
            com.google.android.gms.ads.doubleclick.PublisherAdView r5 = (com.google.android.gms.ads.doubleclick.PublisherAdView) r5
        L_0x0008:
            r12 = r11 & 2
            if (r12 == 0) goto L_0x000f
            r6 = r0
            com.google.android.gms.ads.formats.NativeCustomTemplateAd r6 = (com.google.android.gms.ads.formats.NativeCustomTemplateAd) r6
        L_0x000f:
            r12 = r6
            r6 = r11 & 4
            if (r6 == 0) goto L_0x0017
            r7 = 0
            r1 = 0
            goto L_0x0018
        L_0x0017:
            r1 = r7
        L_0x0018:
            r6 = r11 & 8
            java.lang.String r7 = ""
            if (r6 == 0) goto L_0x0020
            r2 = r7
            goto L_0x0021
        L_0x0020:
            r2 = r8
        L_0x0021:
            r6 = r11 & 16
            if (r6 == 0) goto L_0x0028
            r9 = r0
            com.medscape.android.ads.NativeStyleAd r9 = (com.medscape.android.ads.NativeStyleAd) r9
        L_0x0028:
            r0 = r9
            r6 = r11 & 32
            if (r6 == 0) goto L_0x002f
            r3 = r7
            goto L_0x0030
        L_0x002f:
            r3 = r10
        L_0x0030:
            r6 = r4
            r7 = r5
            r8 = r12
            r9 = r1
            r10 = r2
            r11 = r0
            r12 = r3
            r6.<init>(r7, r8, r9, r10, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.ads.NativeDFPAD.<init>(com.google.android.gms.ads.doubleclick.PublisherAdView, com.google.android.gms.ads.formats.NativeCustomTemplateAd, int, java.lang.String, com.medscape.android.ads.NativeStyleAd, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final PublisherAdView getDfpAD() {
        return this.dfpAD;
    }

    public final void setDfpAD(PublisherAdView publisherAdView) {
        this.dfpAD = publisherAdView;
    }

    public final NativeCustomTemplateAd getNativeAD() {
        return this.nativeAD;
    }

    public final void setNativeAD(NativeCustomTemplateAd nativeCustomTemplateAd) {
        this.nativeAD = nativeCustomTemplateAd;
    }

    public final int getPgValue() {
        return this.pgValue;
    }

    public final void setPgValue(int i) {
        this.pgValue = i;
    }

    public final String getPosValue() {
        return this.posValue;
    }

    public final void setPosValue(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.posValue = str;
    }

    public final NativeStyleAd getNativeStyle() {
        return this.nativeStyle;
    }

    public final void setNativeStyle(NativeStyleAd nativeStyleAd) {
        this.nativeStyle = nativeStyleAd;
    }

    public final String getStyleConfig() {
        return this.styleConfig;
    }

    public final void setStyleConfig(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.styleConfig = str;
    }
}
