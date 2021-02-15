package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public class zzaci<T> {
    private final T zzclg;
    private final String zzcn;
    private final int zzczv;

    public static zzaci<Boolean> zzg(String str, boolean z) {
        return new zzaci<>(str, Boolean.valueOf(z), zzack.zzczw);
    }

    public static zzaci<Long> zzb(String str, long j) {
        return new zzaci<>(str, Long.valueOf(j), zzack.zzczx);
    }

    public static zzaci<Double> zzb(String str, double d) {
        return new zzaci<>(str, Double.valueOf(d), zzack.zzczy);
    }

    public static zzaci<String> zzi(String str, String str2) {
        return new zzaci<>(str, str2, zzack.zzczz);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [int, java.lang.Integer] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected zzaci(java.lang.String r1, T r2, java.lang.Integer r3) {
        /*
            r0 = this;
            r0.<init>()
            r0.zzcn = r1
            r0.zzclg = r2
            r0.zzczv = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaci.<init>(java.lang.String, java.lang.Object, int):void");
    }

    public T get() {
        zzadk zzsa = zzadj.zzsa();
        if (zzsa != null) {
            int i = zzach.zzczu[this.zzczv - 1];
            if (i == 1) {
                return zzsa.zzf(this.zzcn, ((Boolean) this.zzclg).booleanValue());
            }
            if (i == 2) {
                return zzsa.getLong(this.zzcn, ((Long) this.zzclg).longValue());
            }
            if (i == 3) {
                return zzsa.zza(this.zzcn, ((Double) this.zzclg).doubleValue());
            }
            if (i == 4) {
                return zzsa.get(this.zzcn, (String) this.zzclg);
            }
            throw new IllegalStateException();
        }
        throw new IllegalStateException("Flag is not initialized.");
    }
}
