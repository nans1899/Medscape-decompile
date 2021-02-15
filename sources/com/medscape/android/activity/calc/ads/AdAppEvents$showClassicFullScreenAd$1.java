package com.medscape.android.activity.calc.ads;

import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.medscape.android.ads.DFPAd;
import com.medscape.android.ads.GetAdContentListener;
import com.medscape.android.util.StringUtil;
import com.wbmd.ads.IAdListener;
import com.wbmd.ads.model.AdContainer;
import com.wbmd.ads.model.AdStatus;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "result", "", "kotlin.jvm.PlatformType", "onContentsDownloaded"}, k = 3, mv = {1, 4, 0})
/* compiled from: AdAppEvents.kt */
final class AdAppEvents$showClassicFullScreenAd$1 implements GetAdContentListener {
    final /* synthetic */ IAdListener $adLoadListener;
    final /* synthetic */ DFPAd $dfpAd;
    final /* synthetic */ AdAppEvents this$0;

    AdAppEvents$showClassicFullScreenAd$1(AdAppEvents adAppEvents, IAdListener iAdListener, DFPAd dFPAd) {
        this.this$0 = adAppEvents;
        this.$adLoadListener = iAdListener;
        this.$dfpAd = dFPAd;
    }

    public final void onContentsDownloaded(String str) {
        try {
            if (StringUtil.isNullOrEmpty(str)) {
                IAdListener iAdListener = this.$adLoadListener;
                if (iAdListener != null) {
                    iAdListener.onAdFailed(new AdContainer(AdStatus.failed, (PublisherAdView) null, (NativeCustomTemplateAd) null, 6, (DefaultConstructorMarker) null), -999);
                    return;
                }
                return;
            }
            this.this$0.showClassicFullScreenViewer(this.$dfpAd, str);
        } catch (Exception e) {
            e.printStackTrace();
            IAdListener iAdListener2 = this.$adLoadListener;
            if (iAdListener2 != null) {
                iAdListener2.onAdFailed(new AdContainer(AdStatus.failed, (PublisherAdView) null, (NativeCustomTemplateAd) null, 6, (DefaultConstructorMarker) null), -999);
            }
        }
    }
}
