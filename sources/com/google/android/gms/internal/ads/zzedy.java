package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzedy extends zzejz<zzedy, zza> implements zzell {
    private static volatile zzelw<zzedy> zzel;
    /* access modifiers changed from: private */
    public static final zzedy zzhxp;
    private int zzhwq;
    private zzeip zzhwr = zzeip.zzier;

    private zzedy() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzedy, zza> implements zzell {
        private zza() {
            super(zzedy.zzhxp);
        }

        public final zza zzex(int i) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzedy) this.zzijh).setVersion(0);
            return this;
        }

        public final zza zzx(zzeip zzeip) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzedy) this.zzijh).zzs(zzeip);
            return this;
        }

        /* synthetic */ zza(zzedz zzedz) {
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

    public final zzeip zzazd() {
        return this.zzhwr;
    }

    /* access modifiers changed from: private */
    public final void zzs(zzeip zzeip) {
        zzeip.getClass();
        this.zzhwr = zzeip;
    }

    public static zzedy zzm(zzeip zzeip, zzejm zzejm) throws zzekj {
        return (zzedy) zzejz.zza(zzhxp, zzeip, zzejm);
    }

    public static zza zzbak() {
        return (zza) zzhxp.zzbgf();
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzedz.zzdv[i - 1]) {
            case 1:
                return new zzedy();
            case 2:
                return new zza((zzedz) null);
            case 3:
                return zza((zzelj) zzhxp, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\n", new Object[]{"zzhwq", "zzhwr"});
            case 4:
                return zzhxp;
            case 5:
                zzelw<zzedy> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzedy.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhxp);
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

    static {
        zzedy zzedy = new zzedy();
        zzhxp = zzedy;
        zzejz.zza(zzedy.class, zzedy);
    }
}
