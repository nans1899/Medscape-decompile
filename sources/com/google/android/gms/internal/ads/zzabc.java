package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzabc implements zzadk {
    private final /* synthetic */ zzaax zzcys;

    zzabc(zzaax zzaax) {
        this.zzcys = zzaax;
    }

    public final Boolean zzf(String str, boolean z) {
        return Boolean.valueOf(this.zzcys.zzcln.getBoolean(str, z));
    }

    public final Long getLong(String str, long j) {
        try {
            return Long.valueOf(this.zzcys.zzcln.getLong(str, j));
        } catch (ClassCastException unused) {
            return Long.valueOf((long) this.zzcys.zzcln.getInt(str, (int) j));
        }
    }

    public final Double zza(String str, double d) {
        return Double.valueOf((double) this.zzcys.zzcln.getFloat(str, (float) d));
    }

    public final String get(String str, String str2) {
        return this.zzcys.zzcln.getString(str, str2);
    }
}
