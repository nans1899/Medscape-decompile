package com.google.android.gms.internal.ads;

import android.content.Context;
import java.io.File;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbj implements zzaz {
    private final /* synthetic */ Context val$appContext;
    private File zzcz = null;

    zzbj(Context context) {
        this.val$appContext = context;
    }

    public final File zzo() {
        if (this.zzcz == null) {
            this.zzcz = new File(this.val$appContext.getCacheDir(), "volley");
        }
        return this.zzcz;
    }
}
