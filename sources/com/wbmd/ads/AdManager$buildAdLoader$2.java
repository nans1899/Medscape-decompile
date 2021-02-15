package com.wbmd.ads;

import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "name", "", "kotlin.jvm.PlatformType", "info", "onAppEvent"}, k = 3, mv = {1, 4, 0})
/* compiled from: AdManager.kt */
final class AdManager$buildAdLoader$2 implements AppEventListener {
    final /* synthetic */ IAdListener $listener;
    final /* synthetic */ AdManager this$0;

    AdManager$buildAdLoader$2(AdManager adManager, IAdListener iAdListener) {
        this.this$0 = adManager;
        this.$listener = iAdListener;
    }

    public final void onAppEvent(String str, String str2) {
        IAppEventListener access$getAppEventListener$p = this.this$0.appEventListener;
        if (access$getAppEventListener$p != null) {
            Intrinsics.checkNotNullExpressionValue(str, "name");
            Intrinsics.checkNotNullExpressionValue(str2, OmnitureConstants.OMNITURE_LINK_EVENT_INFO);
            access$getAppEventListener$p.onAppEvent(str, str2, this.$listener);
        }
    }
}
