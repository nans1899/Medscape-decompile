package com.google.android.gms.internal.ads;

import android.net.Uri;
import com.google.android.gms.ads.query.UpdateImpressionUrlsCallback;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzarz extends zzars {
    private final /* synthetic */ UpdateImpressionUrlsCallback zzdqg;

    zzarz(zzarw zzarw, UpdateImpressionUrlsCallback updateImpressionUrlsCallback) {
        this.zzdqg = updateImpressionUrlsCallback;
    }

    public final void onSuccess(List<Uri> list) {
        this.zzdqg.onSuccess(list);
    }

    public final void onError(String str) {
        this.zzdqg.onFailure(str);
    }
}
