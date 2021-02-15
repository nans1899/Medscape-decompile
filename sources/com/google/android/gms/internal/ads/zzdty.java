package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public final class zzdty extends zzejz<zzdty, zzb> implements zzell {
    private static volatile zzelw<zzdty> zzel;
    private static final zzeki<Integer, zza> zzhmz = new zzdtx();
    /* access modifiers changed from: private */
    public static final zzdty zzhnd;
    private int zzdw;
    private zzekf zzhmy = zzbgi();
    private String zzhna = "";
    private String zzhnb = "";
    private String zzhnc = "";

    /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
    public enum zza implements zzeke {
        BLOCKED_REASON_UNKNOWN(1),
        BLOCKED_REASON_BACKGROUND(2);
        
        private static final zzekd<zza> zzes = null;
        private final int value;

        public final int zzv() {
            return this.value;
        }

        public static zza zzeg(int i) {
            if (i == 1) {
                return BLOCKED_REASON_UNKNOWN;
            }
            if (i != 2) {
                return null;
            }
            return BLOCKED_REASON_BACKGROUND;
        }

        public static zzekg zzw() {
            return zzdua.zzeu;
        }

        public final String toString() {
            return HtmlObject.HtmlMarkUp.OPEN_BRACKER + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
        }

        private zza(int i) {
            this.value = i;
        }

        static {
            zzes = new zzdub();
        }
    }

    private zzdty() {
    }

    /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
    public static final class zzb extends zzejz.zzb<zzdty, zzb> implements zzell {
        private zzb() {
            super(zzdty.zzhnd);
        }

        public final zzb zzb(zza zza) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzdty) this.zzijh).zza(zza);
            return this;
        }

        public final zzb zzhe(String str) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzdty) this.zzijh).zzhd(str);
            return this;
        }

        /* synthetic */ zzb(zzdtx zzdtx) {
            this();
        }
    }

    /* access modifiers changed from: private */
    public final void zza(zza zza2) {
        zza2.getClass();
        zzekf zzekf = this.zzhmy;
        if (!zzekf.zzbeb()) {
            this.zzhmy = zzejz.zza(zzekf);
        }
        this.zzhmy.zzhc(zza2.zzv());
    }

    /* access modifiers changed from: private */
    public final void zzhd(String str) {
        str.getClass();
        this.zzdw |= 1;
        this.zzhna = str;
    }

    public static zzb zzawf() {
        return (zzb) zzhnd.zzbgf();
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzdtz.zzdv[i - 1]) {
            case 1:
                return new zzdty();
            case 2:
                return new zzb((zzdtx) null);
            case 3:
                return zza((zzelj) zzhnd, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\u001e\u0002ဈ\u0000\u0003ဈ\u0001\u0004ဈ\u0002", new Object[]{"zzdw", "zzhmy", zza.zzw(), "zzhna", "zzhnb", "zzhnc"});
            case 4:
                return zzhnd;
            case 5:
                zzelw<zzdty> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzdty.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhnd);
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
        zzdty zzdty = new zzdty();
        zzhnd = zzdty;
        zzejz.zza(zzdty.class, zzdty);
    }
}
