package com.google.android.gms.common.api.internal;

import com.google.android.gms.signin.internal.zam;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
final class zace implements Runnable {
    private final /* synthetic */ zam zaa;
    private final /* synthetic */ zacc zab;

    zace(zacc zacc, zam zam) {
        this.zab = zacc;
        this.zaa = zam;
    }

    public final void run() {
        this.zab.zab(this.zaa);
    }
}
