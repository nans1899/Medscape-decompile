package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbhy {
    public final int heightPixels;
    private final int type;
    public final int widthPixels;

    public static zzbhy zzb(zzvn zzvn) {
        if (zzvn.zzcht) {
            return new zzbhy(3, 0, 0);
        }
        if (zzvn.zzchv) {
            return new zzbhy(2, 0, 0);
        }
        if (zzvn.zzbrg) {
            return zzacy();
        }
        return zzq(zzvn.widthPixels, zzvn.heightPixels);
    }

    public static zzbhy zzacy() {
        return new zzbhy(0, 0, 0);
    }

    public static zzbhy zzq(int i, int i2) {
        return new zzbhy(1, i, i2);
    }

    public static zzbhy zzacz() {
        return new zzbhy(4, 0, 0);
    }

    public static zzbhy zzada() {
        return new zzbhy(5, 0, 0);
    }

    private zzbhy(int i, int i2, int i3) {
        this.type = i;
        this.widthPixels = i2;
        this.heightPixels = i3;
    }

    public final boolean isFluid() {
        return this.type == 2;
    }

    public final boolean zzadb() {
        return this.type == 3;
    }

    public final boolean zzadc() {
        return this.type == 0;
    }

    public final boolean zzadd() {
        return this.type == 4;
    }

    public final boolean zzade() {
        return this.type == 5;
    }
}
