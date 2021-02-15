package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdca implements zzdgx<zzdcb> {
    private final zzayb zzbpw;
    private final zzdok zzfsk;
    private final zzdgx<zzdha> zzgvf;
    private final Context zzvr;

    public zzdca(zzddp<zzdha> zzddp, zzdok zzdok, Context context, zzayb zzayb) {
        this.zzgvf = zzddp;
        this.zzfsk = zzdok;
        this.zzvr = context;
        this.zzbpw = zzayb;
    }

    public final zzdzc<zzdcb> zzarj() {
        return zzdyq.zzb(this.zzgvf.zzarj(), new zzdcd(this), (Executor) zzbbz.zzeeu);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdcb zza(zzdha zzdha) {
        boolean z;
        String str;
        int i;
        int i2;
        float f;
        String str2;
        int i3;
        int i4;
        int i5;
        DisplayMetrics displayMetrics;
        zzvn zzvn = this.zzfsk.zzbpf;
        if (zzvn.zzchu != null) {
            str = null;
            boolean z2 = false;
            boolean z3 = false;
            z = false;
            for (zzvn zzvn2 : zzvn.zzchu) {
                if (!zzvn2.zzchv && !z2) {
                    str = zzvn2.zzacy;
                    z2 = true;
                }
                if (zzvn2.zzchv && !z3) {
                    z3 = true;
                    z = true;
                }
                if (z2 && z3) {
                    break;
                }
            }
        } else {
            str = zzvn.zzacy;
            z = zzvn.zzchv;
        }
        Resources resources = this.zzvr.getResources();
        if (resources == null || (displayMetrics = resources.getDisplayMetrics()) == null) {
            str2 = null;
            f = 0.0f;
            i2 = 0;
            i = 0;
        } else {
            float f2 = displayMetrics.density;
            int i6 = displayMetrics.widthPixels;
            i = displayMetrics.heightPixels;
            str2 = this.zzbpw.zzwt().zzxq();
            i2 = i6;
            f = f2;
        }
        StringBuilder sb = new StringBuilder();
        if (zzvn.zzchu != null) {
            boolean z4 = false;
            for (zzvn zzvn3 : zzvn.zzchu) {
                if (zzvn3.zzchv) {
                    z4 = true;
                } else {
                    if (sb.length() != 0) {
                        sb.append("|");
                    }
                    if (zzvn3.width != -1 || f == 0.0f) {
                        i4 = zzvn3.width;
                    } else {
                        i4 = (int) (((float) zzvn3.widthPixels) / f);
                    }
                    sb.append(i4);
                    sb.append("x");
                    if (zzvn3.height == -2) {
                        if (f != 0.0f) {
                            i5 = (int) (((float) zzvn3.heightPixels) / f);
                            sb.append(i5);
                        }
                    }
                    i5 = zzvn3.height;
                    sb.append(i5);
                }
            }
            if (z4) {
                if (sb.length() != 0) {
                    i3 = 0;
                    sb.insert(0, "|");
                } else {
                    i3 = 0;
                }
                sb.insert(i3, "320x50");
            }
        }
        return new zzdcb(zzvn, str, z, sb.toString(), f, i2, i, str2, this.zzfsk.zzgvi);
    }
}
