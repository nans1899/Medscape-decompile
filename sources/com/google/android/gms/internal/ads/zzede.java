package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzede extends zzejz<zzede, zza> implements zzell {
    private static volatile zzelw<zzede> zzel;
    /* access modifiers changed from: private */
    public static final zzede zzhxa;
    private int zzhwq;
    private zzedi zzhwy;
    private zzeet zzhwz;

    private zzede() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzede, zza> implements zzell {
        private zza() {
            super(zzede.zzhxa);
        }

        public final zza zzet(int i) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzede) this.zzijh).setVersion(i);
            return this;
        }

        public final zza zzb(zzedi zzedi) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzede) this.zzijh).zza(zzedi);
            return this;
        }

        public final zza zzb(zzeet zzeet) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzede) this.zzijh).zza(zzeet);
            return this;
        }

        /* synthetic */ zza(zzedf zzedf) {
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

    public final zzedi zzazl() {
        zzedi zzedi = this.zzhwy;
        return zzedi == null ? zzedi.zzazu() : zzedi;
    }

    /* access modifiers changed from: private */
    public final void zza(zzedi zzedi) {
        zzedi.getClass();
        this.zzhwy = zzedi;
    }

    public final zzeet zzazm() {
        zzeet zzeet = this.zzhwz;
        return zzeet == null ? zzeet.zzbbm() : zzeet;
    }

    /* access modifiers changed from: private */
    public final void zza(zzeet zzeet) {
        zzeet.getClass();
        this.zzhwz = zzeet;
    }

    public static zzede zze(zzeip zzeip, zzejm zzejm) throws zzekj {
        return (zzede) zzejz.zza(zzhxa, zzeip, zzejm);
    }

    public static zza zzazn() {
        return (zza) zzhxa.zzbgf();
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzedf.zzdv[i - 1]) {
            case 1:
                return new zzede();
            case 2:
                return new zza((zzedf) null);
            case 3:
                return zza((zzelj) zzhxa, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\t", new Object[]{"zzhwq", "zzhwy", "zzhwz"});
            case 4:
                return zzhxa;
            case 5:
                zzelw<zzede> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzede.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhxa);
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
        zzede zzede = new zzede();
        zzhxa = zzede;
        zzejz.zza(zzede.class, zzede);
    }
}
