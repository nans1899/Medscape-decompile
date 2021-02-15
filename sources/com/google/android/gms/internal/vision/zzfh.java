package com.google.android.gms.internal.vision;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzfh extends zzfg {
    private final zzff zzob = new zzff();

    zzfh() {
    }

    public final void zza(Throwable th, Throwable th2) {
        if (th2 == th) {
            throw new IllegalArgumentException("Self suppression is not allowed.", th2);
        } else if (th2 != null) {
            this.zzob.zza(th, true).add(th2);
        } else {
            throw new NullPointerException("The suppressed exception cannot be null.");
        }
    }

    public final void zza(Throwable th) {
        th.printStackTrace();
        List<Throwable> zza = this.zzob.zza(th, false);
        if (zza != null) {
            synchronized (zza) {
                for (Throwable printStackTrace : zza) {
                    System.err.print("Suppressed: ");
                    printStackTrace.printStackTrace();
                }
            }
        }
    }
}
