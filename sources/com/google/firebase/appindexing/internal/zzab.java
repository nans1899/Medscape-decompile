package com.google.firebase.appindexing.internal;

import android.app.slice.SliceManager;
import android.content.Context;
import android.net.Uri;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzab extends zzaa {
    private final SliceManager zzfx;

    public zzab(Context context) {
        this.zzfx = (SliceManager) context.getSystemService(SliceManager.class);
    }

    public final void grantSlicePermission(String str, Uri uri) {
        this.zzfx.grantSlicePermission(str, uri);
    }
}
