package com.medscape.android.ads;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "nativeAD", "Lcom/google/android/gms/ads/formats/NativeCustomTemplateAd;", "kotlin.jvm.PlatformType", "onCustomTemplateAdLoaded", "com/medscape/android/ads/NativeAdAction$prepADWithCombinedRequests$2$1"}, k = 3, mv = {1, 4, 0})
/* compiled from: NativeAdAction.kt */
final class NativeAdAction$prepADWithCombinedRequests$$inlined$let$lambda$1 implements NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener {
    final /* synthetic */ INativeDFPAdLoadListener $nativeDFPAdLoadListener$inlined;
    final /* synthetic */ String[] $nativeTemplates$inlined;
    final /* synthetic */ NativeAdAction this$0;

    NativeAdAction$prepADWithCombinedRequests$$inlined$let$lambda$1(NativeAdAction nativeAdAction, String[] strArr, INativeDFPAdLoadListener iNativeDFPAdLoadListener) {
        this.this$0 = nativeAdAction;
        this.$nativeTemplates$inlined = strArr;
        this.$nativeDFPAdLoadListener$inlined = iNativeDFPAdLoadListener;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0043, code lost:
        if (r1 != null) goto L_0x0050;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onCustomTemplateAdLoaded(com.google.android.gms.ads.formats.NativeCustomTemplateAd r12) {
        /*
            r11 = this;
            com.medscape.android.ads.INativeDFPAdLoadListener r0 = r11.$nativeDFPAdLoadListener$inlined
            com.medscape.android.ads.NativeDFPAD r10 = new com.medscape.android.ads.NativeDFPAD
            com.medscape.android.ads.NativeAdAction r1 = r11.this$0
            java.lang.String r5 = r1.getPosValue()
            com.medscape.android.ads.NativeAdAction r1 = r11.this$0
            int r4 = r1.getPgValue()
            com.medscape.android.ads.NativeAdAction$Companion r1 = com.medscape.android.ads.NativeAdAction.Companion
            com.medscape.android.ads.NativeAdAction r2 = r11.this$0
            android.content.Context r2 = r2.context
            java.lang.String r3 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            com.medscape.android.ads.NativeAdAction r3 = r11.this$0
            java.lang.String r3 = r3.getPosValue()
            java.lang.String r6 = "nativeAD"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r6)
            com.medscape.android.ads.NativeStyleAd r6 = r1.pickRandomNativeStyle(r2, r3, r12)
            java.lang.String r1 = "StyleConfig"
            java.lang.CharSequence r1 = r12.getText(r1)
            if (r1 == 0) goto L_0x004e
            java.lang.String r1 = r1.toString()
            if (r1 == 0) goto L_0x004e
            if (r1 == 0) goto L_0x0046
            java.lang.String r1 = r1.toLowerCase()
            java.lang.String r2 = "(this as java.lang.String).toLowerCase()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            if (r1 == 0) goto L_0x004e
            goto L_0x0050
        L_0x0046:
            java.lang.NullPointerException r12 = new java.lang.NullPointerException
            java.lang.String r0 = "null cannot be cast to non-null type java.lang.String"
            r12.<init>(r0)
            throw r12
        L_0x004e:
            java.lang.String r1 = ""
        L_0x0050:
            r7 = r1
            r8 = 1
            r9 = 0
            r2 = 0
            r1 = r10
            r3 = r12
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            r0.onAdLoaded(r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.ads.NativeAdAction$prepADWithCombinedRequests$$inlined$let$lambda$1.onCustomTemplateAdLoaded(com.google.android.gms.ads.formats.NativeCustomTemplateAd):void");
    }
}
