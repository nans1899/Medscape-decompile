package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzhu {
    public static final zzhu zzahz = new zzhu(1.0f, 1.0f);
    public final float zzaia;
    public final float zzaib;
    private final int zzaic;

    public zzhu(float f, float f2) {
        this.zzaia = f;
        this.zzaib = f2;
        this.zzaic = Math.round(f * 1000.0f);
    }

    public final long zzdu(long j) {
        return j * ((long) this.zzaic);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzhu zzhu = (zzhu) obj;
            return this.zzaia == zzhu.zzaia && this.zzaib == zzhu.zzaib;
        }
    }

    public final int hashCode() {
        return ((Float.floatToRawIntBits(this.zzaia) + 527) * 31) + Float.floatToRawIntBits(this.zzaib);
    }
}
