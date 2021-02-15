package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzp;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdel implements Callable {
    private final zzdei zzgwv;

    zzdel(zzdei zzdei) {
        this.zzgwv = zzdei;
    }

    public final Object call() {
        String str;
        String str2;
        String str3;
        zzp.zzkr();
        zzrk zzxd = zzp.zzkv().zzwt().zzxd();
        Bundle bundle = null;
        if (!(zzxd == null || zzxd == null || (zzp.zzkv().zzwt().zzxe() && zzp.zzkv().zzwt().zzxg()))) {
            if (zzxd.zzmm()) {
                zzxd.wakeup();
            }
            zzre zzmk = zzxd.zzmk();
            if (zzmk != null) {
                str2 = zzmk.zzlz();
                str = zzmk.zzma();
                str3 = zzmk.zzmb();
                if (str2 != null) {
                    zzp.zzkv().zzwt().zzej(str2);
                }
                if (str3 != null) {
                    zzp.zzkv().zzwt().zzek(str3);
                }
            } else {
                str2 = zzp.zzkv().zzwt().zzxf();
                str3 = zzp.zzkv().zzwt().zzxh();
                str = null;
            }
            Bundle bundle2 = new Bundle(1);
            if (!zzp.zzkv().zzwt().zzxg()) {
                if (str3 == null || TextUtils.isEmpty(str3)) {
                    bundle2.putString("v_fp_vertical", "no_hash");
                } else {
                    bundle2.putString("v_fp_vertical", str3);
                }
            }
            if (str2 != null && !zzp.zzkv().zzwt().zzxe()) {
                bundle2.putString("fingerprint", str2);
                if (!str2.equals(str)) {
                    bundle2.putString("v_fp", str);
                }
            }
            if (!bundle2.isEmpty()) {
                bundle = bundle2;
            }
        }
        return new zzdej(bundle);
    }
}
