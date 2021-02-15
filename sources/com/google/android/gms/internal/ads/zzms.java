package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzms implements Runnable {
    private final /* synthetic */ zzmn zzbek;
    private final /* synthetic */ zzmt zzbem;

    zzms(zzmn zzmn, zzmt zzmt) {
        this.zzbek = zzmn;
        this.zzbem = zzmt;
    }

    public final void run() {
        this.zzbem.release();
        int size = this.zzbek.zzbdu.size();
        for (int i = 0; i < size; i++) {
            ((zznh) this.zzbek.zzbdu.valueAt(i)).disable();
        }
    }
}
