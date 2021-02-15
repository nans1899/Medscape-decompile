package com.google.android.gms.vision.clearcut;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public final class zzb {
    private final Object lock = new Object();
    private final long zzcb = Math.round(30000.0d);
    private long zzcc = Long.MIN_VALUE;

    public zzb(double d) {
    }

    public final boolean tryAcquire() {
        synchronized (this.lock) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.zzcc + this.zzcb > currentTimeMillis) {
                return false;
            }
            this.zzcc = currentTimeMillis;
            return true;
        }
    }
}
