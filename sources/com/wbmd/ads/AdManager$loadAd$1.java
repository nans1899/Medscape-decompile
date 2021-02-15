package com.wbmd.ads;

import android.os.Bundle;
import android.util.Log;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "biddingDataBundle", "Landroid/os/Bundle;", "invoke"}, k = 3, mv = {1, 4, 0})
/* compiled from: AdManager.kt */
final class AdManager$loadAd$1 extends Lambda implements Function1<Bundle, Unit> {
    final /* synthetic */ IAdParams $adParams;
    final /* synthetic */ IAdListener $listener;
    final /* synthetic */ AdManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AdManager$loadAd$1(AdManager adManager, IAdParams iAdParams, IAdListener iAdListener) {
        super(1);
        this.this$0 = adManager;
        this.$adParams = iAdParams;
        this.$listener = iAdListener;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Bundle) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "biddingDataBundle");
        Bundle adExtrasBundle$wbmdadsdk_release = AdManager.Companion.getAdExtrasBundle$wbmdadsdk_release(this.$adParams);
        adExtrasBundle$wbmdadsdk_release.putAll(bundle);
        for (String str : adExtrasBundle$wbmdadsdk_release.keySet()) {
            Log.d("AdBundle", str + ": " + adExtrasBundle$wbmdadsdk_release.getString(str));
        }
        this.this$0.loadAd(this.$listener, this.$adParams, adExtrasBundle$wbmdadsdk_release);
    }
}
