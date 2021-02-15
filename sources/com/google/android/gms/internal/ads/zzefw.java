package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;
import java.util.List;

@Deprecated
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzefw extends zzejz<zzefw, zza> implements zzell {
    private static volatile zzelw<zzefw> zzel;
    /* access modifiers changed from: private */
    public static final zzefw zzibh;
    private String zzibf = "";
    private zzekk<zzefi> zzibg = zzbgk();

    private zzefw() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzefw, zza> implements zzell {
        private zza() {
            super(zzefw.zzibh);
        }

        /* synthetic */ zza(zzefy zzefy) {
            this();
        }
    }

    public final List<zzefi> zzbdf() {
        return this.zzibg;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzefy.zzdv[i - 1]) {
            case 1:
                return new zzefw();
            case 2:
                return new zza((zzefy) null);
            case 3:
                return zza((zzelj) zzibh, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002\u001b", new Object[]{"zzibf", "zzibg", zzefi.class});
            case 4:
                return zzibh;
            case 5:
                zzelw<zzefw> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzefw.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzibh);
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

    public static zzefw zzbdg() {
        return zzibh;
    }

    static {
        zzefw zzefw = new zzefw();
        zzibh = zzefw;
        zzejz.zza(zzefw.class, zzefw);
    }
}
