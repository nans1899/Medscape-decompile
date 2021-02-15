package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public final class zzgr extends zzejz<zzgr, zza> implements zzell {
    /* access modifiers changed from: private */
    public static final zzgr zzach;
    private static volatile zzelw<zzgr> zzel;
    private String zzacc = "";
    private String zzacd = "";
    private long zzace;
    private long zzacf;
    private long zzacg;
    private int zzdw;

    private zzgr() {
    }

    /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzgr, zza> implements zzell {
        private zza() {
            super(zzgr.zzach);
        }

        public final zza zzaw(String str) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzgr) this.zzijh).zzau(str);
            return this;
        }

        public final zza zzax(String str) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzgr) this.zzijh).zzav(str);
            return this;
        }

        public final zza zzdj(long j) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzgr) this.zzijh).zzdg(j);
            return this;
        }

        public final zza zzdk(long j) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzgr) this.zzijh).zzdh(j);
            return this;
        }

        public final zza zzdl(long j) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzgr) this.zzijh).zzdi(j);
            return this;
        }

        /* synthetic */ zza(zzgs zzgs) {
            this();
        }
    }

    public final String zzdg() {
        return this.zzacc;
    }

    /* access modifiers changed from: private */
    public final void zzau(String str) {
        str.getClass();
        this.zzdw |= 1;
        this.zzacc = str;
    }

    public final String zzdh() {
        return this.zzacd;
    }

    /* access modifiers changed from: private */
    public final void zzav(String str) {
        str.getClass();
        this.zzdw |= 2;
        this.zzacd = str;
    }

    public final long zzdi() {
        return this.zzace;
    }

    /* access modifiers changed from: private */
    public final void zzdg(long j) {
        this.zzdw |= 4;
        this.zzace = j;
    }

    public final long zzdj() {
        return this.zzacf;
    }

    /* access modifiers changed from: private */
    public final void zzdh(long j) {
        this.zzdw |= 8;
        this.zzacf = j;
    }

    public final long zzdk() {
        return this.zzacg;
    }

    /* access modifiers changed from: private */
    public final void zzdi(long j) {
        this.zzdw |= 16;
        this.zzacg = j;
    }

    public static zzgr zzl(zzeip zzeip) throws zzekj {
        return (zzgr) zzejz.zza(zzach, zzeip);
    }

    public static zzgr zzb(zzeip zzeip, zzejm zzejm) throws zzekj {
        return (zzgr) zzejz.zza(zzach, zzeip, zzejm);
    }

    public static zza zzdl() {
        return (zza) zzach.zzbgf();
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzgs.zzdv[i - 1]) {
            case 1:
                return new zzgr();
            case 2:
                return new zza((zzgs) null);
            case 3:
                return zza((zzelj) zzach, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဃ\u0002\u0004ဃ\u0003\u0005ဃ\u0004", new Object[]{"zzdw", "zzacc", "zzacd", "zzace", "zzacf", "zzacg"});
            case 4:
                return zzach;
            case 5:
                zzelw<zzgr> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzgr.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzach);
                            zzel = zzelw;
                        }
                    }
                }
                return zzelw;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public static zzgr zzdm() {
        return zzach;
    }

    static {
        zzgr zzgr = new zzgr();
        zzach = zzgr;
        zzejz.zza(zzgr.class, zzgr);
    }
}
