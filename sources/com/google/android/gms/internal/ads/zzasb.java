package com.google.android.gms.internal.ads;

import android.view.View;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzasb {
    /* access modifiers changed from: private */
    @Nonnull
    public View zzaat;
    /* access modifiers changed from: private */
    public final Map<String, WeakReference<View>> zzdqc = new HashMap();

    public final zzasb zzk(View view) {
        this.zzaat = view;
        return this;
    }

    public final zzasb zzh(Map<String, View> map) {
        this.zzdqc.clear();
        for (Map.Entry next : map.entrySet()) {
            View view = (View) next.getValue();
            if (view != null) {
                this.zzdqc.put((String) next.getKey(), new WeakReference(view));
            }
        }
        return this;
    }
}
