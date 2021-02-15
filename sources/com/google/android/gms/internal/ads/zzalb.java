package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzalb implements Runnable {
    private final zzaki zzdhp;

    private zzalb(zzaki zzaki) {
        this.zzdhp = zzaki;
    }

    static Runnable zzb(zzaki zzaki) {
        return new zzalb(zzaki);
    }

    public final void run() {
        this.zzdhp.destroy();
    }
}
