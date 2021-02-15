package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzedi extends zzejz<zzedi, zza> implements zzell {
    private static volatile zzelw<zzedi> zzel;
    /* access modifiers changed from: private */
    public static final zzedi zzhxf;
    private int zzhwq;
    private zzeip zzhwr = zzeip.zzier;
    private zzedm zzhxe;

    private zzedi() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzedi, zza> implements zzell {
        private zza() {
            super(zzedi.zzhxf);
        }

        public final zza zzeu(int i) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzedi) this.zzijh).setVersion(0);
            return this;
        }

        public final zza zzc(zzedm zzedm) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzedi) this.zzijh).zzb(zzedm);
            return this;
        }

        public final zza zzu(zzeip zzeip) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzedi) this.zzijh).zzs(zzeip);
            return this;
        }

        /* synthetic */ zza(zzedj zzedj) {
            this();
        }
    }

    public final int getVersion() {
        return this.zzhwq;
    }

    /* access modifiers changed from: private */
    public final void setVersion(int i) {
        this.zzhwq = i;
    }

    public final zzedm zzazs() {
        zzedm zzedm = this.zzhxe;
        return zzedm == null ? zzedm.zzazz() : zzedm;
    }

    /* access modifiers changed from: private */
    public final void zzb(zzedm zzedm) {
        zzedm.getClass();
        this.zzhxe = zzedm;
    }

    public final zzeip zzazd() {
        return this.zzhwr;
    }

    /* access modifiers changed from: private */
    public final void zzs(zzeip zzeip) {
        zzeip.getClass();
        this.zzhwr = zzeip;
    }

    public static zzedi zzg(zzeip zzeip, zzejm zzejm) throws zzekj {
        return (zzedi) zzejz.zza(zzhxf, zzeip, zzejm);
    }

    public static zza zzazt() {
        return (zza) zzhxf.zzbgf();
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzedj.zzdv[i - 1]) {
            case 1:
                return new zzedi();
            case 2:
                return new zza((zzedj) null);
            case 3:
                return zza((zzelj) zzhxf, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zzhwq", "zzhxe", "zzhwr"});
            case 4:
                return zzhxf;
            case 5:
                zzelw<zzedi> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzedi.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhxf);
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

    public static zzedi zzazu() {
        return zzhxf;
    }

    static {
        zzedi zzedi = new zzedi();
        zzhxf = zzedi;
        zzejz.zza(zzedi.class, zzedi);
    }
}
