package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.ViewGroup;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbdn {
    private final Context zzclo;
    private final zzbdu zzegh;
    private final ViewGroup zzehc;
    private zzbdh zzehd;

    public zzbdn(Context context, ViewGroup viewGroup, zzbgj zzbgj) {
        this(context, viewGroup, zzbgj, (zzbdh) null);
    }

    private zzbdn(Context context, ViewGroup viewGroup, zzbdu zzbdu, zzbdh zzbdh) {
        this.zzclo = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.zzehc = viewGroup;
        this.zzegh = zzbdu;
        this.zzehd = null;
    }

    public final void zze(int i, int i2, int i3, int i4) {
        Preconditions.checkMainThread("The underlay may only be modified from the UI thread.");
        zzbdh zzbdh = this.zzehd;
        if (zzbdh != null) {
            zzbdh.zzd(i, i2, i3, i4);
        }
    }

    public final void zza(int i, int i2, int i3, int i4, int i5, boolean z, zzbdv zzbdv) {
        if (this.zzehd == null) {
            zzabj.zza(this.zzegh.zzzw().zzrp(), this.zzegh.zzzt(), "vpr2");
            Context context = this.zzclo;
            zzbdu zzbdu = this.zzegh;
            zzbdh zzbdh = new zzbdh(context, zzbdu, i5, z, zzbdu.zzzw().zzrp(), zzbdv);
            this.zzehd = zzbdh;
            this.zzehc.addView(zzbdh, 0, new ViewGroup.LayoutParams(-1, -1));
            int i6 = i;
            int i7 = i2;
            int i8 = i3;
            int i9 = i4;
            this.zzehd.zzd(i, i2, i3, i4);
            this.zzegh.zzav(false);
        }
    }

    public final zzbdh zzzn() {
        Preconditions.checkMainThread("getAdVideoUnderlay must be called from the UI thread.");
        return this.zzehd;
    }

    public final void onPause() {
        Preconditions.checkMainThread("onPause must be called from the UI thread.");
        zzbdh zzbdh = this.zzehd;
        if (zzbdh != null) {
            zzbdh.pause();
        }
    }

    public final void onDestroy() {
        Preconditions.checkMainThread("onDestroy must be called from the UI thread.");
        zzbdh zzbdh = this.zzehd;
        if (zzbdh != null) {
            zzbdh.destroy();
            this.zzehc.removeView(this.zzehd);
            this.zzehd = null;
        }
    }
}
