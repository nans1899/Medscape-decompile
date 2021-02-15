package com.medscape.android.ads;

import android.content.Context;
import android.view.View;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.ads.sharethrough.SharethroughFallback;
import com.medscape.android.util.RedirectHandler;
import com.medscape.android.util.constants.DeepLinkConstants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: ShareThroughNativeADViewHolder.kt */
final class ShareThroughNativeADViewHolder$bindFallback$1 implements View.OnClickListener {
    final /* synthetic */ Context $context;
    final /* synthetic */ SharethroughFallback $fallback;

    ShareThroughNativeADViewHolder$bindFallback$1(Context context, SharethroughFallback sharethroughFallback) {
        this.$context = context;
        this.$fallback = sharethroughFallback;
    }

    public final void onClick(View view) {
        Context context = this.$context;
        if (context != null) {
            if (this.$fallback.getDeepLink() != null) {
                String deepLink = this.$fallback.getDeepLink();
                Boolean valueOf = deepLink != null ? Boolean.valueOf(StringsKt.contains$default((CharSequence) deepLink, (CharSequence) DeepLinkConstants.HEALTH_DIRECTORY, false, 2, (Object) null)) : null;
                Intrinsics.checkNotNull(valueOf);
                if (valueOf.booleanValue()) {
                    OmnitureManager.get().trackModule(context, (String) null, "housead", "drvr", (Map<String, Object>) null);
                    new RedirectHandler(false).handleRedirect(context, this.$fallback.getDeepLink(), false);
                }
            }
            OmnitureManager.get().markModule("housead", "drvr", (Map<String, Object>) null);
            new RedirectHandler(false).handleRedirect(context, this.$fallback.getDeepLink(), false);
        }
    }
}
