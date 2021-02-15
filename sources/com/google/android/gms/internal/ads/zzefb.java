package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzefb extends zzejz<zzefb, zzb> implements zzell {
    private static volatile zzelw<zzefb> zzel;
    /* access modifiers changed from: private */
    public static final zzefb zzhzk;
    private String zzhzh = "";
    private zzeip zzhzi = zzeip.zzier;
    private int zzhzj;

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public enum zza implements zzeke {
        UNKNOWN_KEYMATERIAL(0),
        SYMMETRIC(1),
        ASYMMETRIC_PRIVATE(2),
        ASYMMETRIC_PUBLIC(3),
        REMOTE(4),
        UNRECOGNIZED(-1);
        
        private static final zzekd<zza> zzes = null;
        private final int value;

        public final int zzv() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        public static zza zzfe(int i) {
            if (i == 0) {
                return UNKNOWN_KEYMATERIAL;
            }
            if (i == 1) {
                return SYMMETRIC;
            }
            if (i == 2) {
                return ASYMMETRIC_PRIVATE;
            }
            if (i == 3) {
                return ASYMMETRIC_PUBLIC;
            }
            if (i != 4) {
                return null;
            }
            return REMOTE;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder(HtmlObject.HtmlMarkUp.OPEN_BRACKER);
            sb.append(getClass().getName());
            sb.append('@');
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            if (this != UNRECOGNIZED) {
                sb.append(" number=");
                sb.append(zzv());
            }
            sb.append(" name=");
            sb.append(name());
            sb.append('>');
            return sb.toString();
        }

        private zza(int i) {
            this.value = i;
        }

        static {
            zzes = new zzefd();
        }
    }

    private zzefb() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zzb extends zzejz.zzb<zzefb, zzb> implements zzell {
        private zzb() {
            super(zzefb.zzhzk);
        }

        public final zzb zzhq(String str) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzefb) this.zzijh).zzhp(str);
            return this;
        }

        public final zzb zzaf(zzeip zzeip) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzefb) this.zzijh).zzae(zzeip);
            return this;
        }

        public final zzb zzb(zza zza) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzefb) this.zzijh).zza(zza);
            return this;
        }

        /* synthetic */ zzb(zzefa zzefa) {
            this();
        }
    }

    public final String zzbbt() {
        return this.zzhzh;
    }

    /* access modifiers changed from: private */
    public final void zzhp(String str) {
        str.getClass();
        this.zzhzh = str;
    }

    public final zzeip zzbbu() {
        return this.zzhzi;
    }

    /* access modifiers changed from: private */
    public final void zzae(zzeip zzeip) {
        zzeip.getClass();
        this.zzhzi = zzeip;
    }

    public final zza zzbbv() {
        zza zzfe = zza.zzfe(this.zzhzj);
        return zzfe == null ? zza.UNRECOGNIZED : zzfe;
    }

    /* access modifiers changed from: private */
    public final void zza(zza zza2) {
        this.zzhzj = zza2.zzv();
    }

    public static zzb zzbbw() {
        return (zzb) zzhzk.zzbgf();
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzefa.zzdv[i - 1]) {
            case 1:
                return new zzefb();
            case 2:
                return new zzb((zzefa) null);
            case 3:
                return zza((zzelj) zzhzk, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"zzhzh", "zzhzi", "zzhzj"});
            case 4:
                return zzhzk;
            case 5:
                zzelw<zzefb> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzefb.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhzk);
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

    public static zzefb zzbbx() {
        return zzhzk;
    }

    static {
        zzefb zzefb = new zzefb();
        zzhzk = zzefb;
        zzejz.zza(zzefb.class, zzefb);
    }
}
