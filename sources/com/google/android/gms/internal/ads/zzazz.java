package com.google.android.gms.internal.ads;

import android.content.Context;
import java.io.File;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzazz extends zzau {
    private final Context zzvr;

    public static zzae zzbk(Context context) {
        zzae zzae = new zzae(new zzav(new File(context.getCacheDir(), "admob_volley"), 20971520), new zzazz(context, new zzbd()));
        zzae.start();
        return zzae;
    }

    private zzazz(Context context, zzar zzar) {
        super(zzar);
        this.zzvr = context;
    }

    public final zzy zzc(zzaa<?> zzaa) throws zzao {
        if (zzaa.zzh() && zzaa.getMethod() == 0) {
            if (Pattern.matches((String) zzwm.zzpx().zzd(zzabb.zzcth), zzaa.getUrl())) {
                zzwm.zzpt();
                if (zzbbg.zzd(this.zzvr, 13400000)) {
                    zzy zzc = new zzaik(this.zzvr).zzc(zzaa);
                    if (zzc != null) {
                        String valueOf = String.valueOf(zzaa.getUrl());
                        zzayp.zzei(valueOf.length() != 0 ? "Got gmscore asset response: ".concat(valueOf) : new String("Got gmscore asset response: "));
                        return zzc;
                    }
                    String valueOf2 = String.valueOf(zzaa.getUrl());
                    zzayp.zzei(valueOf2.length() != 0 ? "Failed to get gmscore asset response: ".concat(valueOf2) : new String("Failed to get gmscore asset response: "));
                }
            }
        }
        return super.zzc(zzaa);
    }
}
