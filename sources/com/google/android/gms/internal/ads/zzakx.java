package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzakx implements Runnable {
    private final zzaki zzdhp;

    private zzakx(zzaki zzaki) {
        this.zzdhp = zzaki;
    }

    static Runnable zzb(zzaki zzaki) {
        return new zzakx(zzaki);
    }

    public final void run() {
        this.zzdhp.destroy();
    }
}
