package com.google.android.gms.internal.ads;

import android.net.Uri;
import com.google.android.gms.ads.query.UpdateClickUrlCallback;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzary extends zzars {
    private final /* synthetic */ UpdateClickUrlCallback zzdqf;

    zzary(zzarw zzarw, UpdateClickUrlCallback updateClickUrlCallback) {
        this.zzdqf = updateClickUrlCallback;
    }

    public final void onSuccess(List<Uri> list) {
        this.zzdqf.onSuccess(list.get(0));
    }

    public final void onError(String str) {
        this.zzdqf.onFailure(str);
    }
}
