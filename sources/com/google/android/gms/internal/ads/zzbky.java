package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzb;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzbky {
    private zza zzfly;

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static abstract class zza {
        public abstract zzbft zzaei();

        public abstract zzbde zzaej();

        public abstract zztx zzaek();

        public abstract zzawn zzael();

        public abstract zzarj zzaem();

        public abstract zzams zzaen();

        public abstract zzacg zzaeo();
    }

    public zzbky(zza zza2) {
        this.zzfly = zza2;
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [com.google.android.gms.internal.ads.zzawe, com.google.android.gms.internal.ads.zzawk] */
    public final zzb zzagu() {
        zza zza2 = this.zzfly;
        return new zzb(zza2.zzaei(), zza2.zzaej(), new zzawe(zza2.zzael()), zza2.zzaek(), zza2.zzaem(), zza2.zzaeo());
    }

    public final zzawn zzael() {
        return this.zzfly.zzael();
    }

    public final zzarj zzaem() {
        return this.zzfly.zzaem();
    }

    public final zzams zzaen() {
        return this.zzfly.zzaen();
    }

    public final zzacg zzaeo() {
        return this.zzfly.zzaeo();
    }
}
