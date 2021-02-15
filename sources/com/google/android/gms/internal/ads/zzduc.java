package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzdty;
import com.google.android.gms.internal.ads.zzejz;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public final class zzduc extends zzejz<zzduc, zza> implements zzell {
    private static volatile zzelw<zzduc> zzel;
    /* access modifiers changed from: private */
    public static final zzduc zzhnk;
    private int zzdw;
    private String zzdx = "";
    private int zzhnh;
    private String zzhni = "";
    private zzdty zzhnj;

    /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
    public enum zzb implements zzeke {
        EVENT_TYPE_UNKNOWN(0),
        BLOCKED_IMPRESSION(1);
        
        private static final zzekd<zzb> zzes = null;
        private final int value;

        public final int zzv() {
            return this.value;
        }

        public static zzb zzeh(int i) {
            if (i == 0) {
                return EVENT_TYPE_UNKNOWN;
            }
            if (i != 1) {
                return null;
            }
            return BLOCKED_IMPRESSION;
        }

        public static zzekg zzw() {
            return zzdug.zzeu;
        }

        public final String toString() {
            return HtmlObject.HtmlMarkUp.OPEN_BRACKER + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
        }

        private zzb(int i) {
            this.value = i;
        }

        static {
            zzes = new zzdue();
        }
    }

    private zzduc() {
    }

    /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzduc, zza> implements zzell {
        private zza() {
            super(zzduc.zzhnk);
        }

        public final zza zzb(zzb zzb) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzduc) this.zzijh).zza(zzb);
            return this;
        }

        public final zza zzhf(String str) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzduc) this.zzijh).zzp(str);
            return this;
        }

        public final zza zza(zzdty.zzb zzb) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzduc) this.zzijh).zza((zzdty) ((zzejz) zzb.zzbgt()));
            return this;
        }

        /* synthetic */ zza(zzdud zzdud) {
            this();
        }
    }

    /* access modifiers changed from: private */
    public final void zza(zzb zzb2) {
        this.zzhnh = zzb2.zzv();
        this.zzdw |= 1;
    }

    /* access modifiers changed from: private */
    public final void zzp(String str) {
        str.getClass();
        this.zzdw |= 2;
        this.zzdx = str;
    }

    /* access modifiers changed from: private */
    public final void zza(zzdty zzdty) {
        zzdty.getClass();
        this.zzhnj = zzdty;
        this.zzdw |= 8;
    }

    public static zza zzawh() {
        return (zza) zzhnk.zzbgf();
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzdud.zzdv[i - 1]) {
            case 1:
                return new zzduc();
            case 2:
                return new zza((zzdud) null);
            case 3:
                return zza((zzelj) zzhnk, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဉ\u0003", new Object[]{"zzdw", "zzhnh", zzb.zzw(), "zzdx", "zzhni", "zzhnj"});
            case 4:
                return zzhnk;
            case 5:
                zzelw<zzduc> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzduc.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhnk);
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
        zzduc zzduc = new zzduc();
        zzhnk = zzduc;
        zzejz.zza(zzduc.class, zzduc);
    }
}
