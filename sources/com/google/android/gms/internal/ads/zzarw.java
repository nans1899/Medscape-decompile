package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.ads.query.UpdateClickUrlCallback;
import com.google.android.gms.ads.query.UpdateImpressionUrlsCallback;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzarw {
    @Nonnull
    private final View zzaat;
    private final Map<String, WeakReference<View>> zzdqc;
    private final zzaxl zzdqd;

    public zzarw(zzasb zzasb) {
        Map<String, WeakReference<View>> map;
        this.zzaat = zzasb.zzaat;
        this.zzdqc = zzasb.zzdqc;
        zzaxl zzs = zzaru.zzs(zzasb.zzaat.getContext());
        this.zzdqd = zzs;
        if (zzs != null && (map = this.zzdqc) != null && !map.isEmpty()) {
            try {
                this.zzdqd.zza(new zzasa(ObjectWrapper.wrap(this.zzaat).asBinder(), ObjectWrapper.wrap(this.zzdqc).asBinder()));
            } catch (RemoteException unused) {
                zzbbq.zzfc("Failed to call remote method.");
            }
        }
    }

    public final void updateImpressionUrls(List<Uri> list, UpdateImpressionUrlsCallback updateImpressionUrlsCallback) {
        if (this.zzdqd == null) {
            updateImpressionUrlsCallback.onFailure("Failed to get internal reporting info generator.");
        }
        try {
            this.zzdqd.zza(list, ObjectWrapper.wrap(this.zzaat), (zzart) new zzarz(this, updateImpressionUrlsCallback));
        } catch (RemoteException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 16);
            sb.append("Internal error: ");
            sb.append(valueOf);
            updateImpressionUrlsCallback.onFailure(sb.toString());
        }
    }

    public final void updateClickUrl(Uri uri, UpdateClickUrlCallback updateClickUrlCallback) {
        if (this.zzdqd == null) {
            updateClickUrlCallback.onFailure("Failed to get internal reporting info generator.");
        }
        try {
            this.zzdqd.zzb(new ArrayList(Arrays.asList(new Uri[]{uri})), ObjectWrapper.wrap(this.zzaat), new zzary(this, updateClickUrlCallback));
        } catch (RemoteException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 16);
            sb.append("Internal error: ");
            sb.append(valueOf);
            updateClickUrlCallback.onFailure(sb.toString());
        }
    }

    public final void reportTouchEvent(MotionEvent motionEvent) {
        zzaxl zzaxl = this.zzdqd;
        if (zzaxl == null) {
            zzbbq.zzef("Failed to get internal reporting info generator.");
            return;
        }
        try {
            zzaxl.zzan(ObjectWrapper.wrap(motionEvent));
        } catch (RemoteException unused) {
            zzbbq.zzfc("Failed to call remote method.");
        }
    }
}
