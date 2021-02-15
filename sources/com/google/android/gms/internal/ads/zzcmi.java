package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.internal.zzp;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcmi implements zzbul, zzbuz, zzbyc, zzux {
    private final zzdog zzevd;
    private final zzdnv zzfmx;
    private final zzcmu zzfpk;
    private final zzdow zzggz;
    private final zzcsp zzgha;
    private Boolean zzghb;
    private final boolean zzghc = ((Boolean) zzwm.zzpx().zzd(zzabb.zzcxm)).booleanValue();
    private final Context zzvr;

    public zzcmi(Context context, zzdow zzdow, zzcmu zzcmu, zzdog zzdog, zzdnv zzdnv, zzcsp zzcsp) {
        this.zzvr = context;
        this.zzggz = zzdow;
        this.zzfpk = zzcmu;
        this.zzevd = zzdog;
        this.zzfmx = zzdnv;
        this.zzgha = zzcsp;
    }

    public final void onAdClicked() {
        if (this.zzfmx.zzhek) {
            zza(zzgh("click"));
        }
    }

    public final void onAdImpression() {
        if (zzaoy() || this.zzfmx.zzhek) {
            zza(zzgh("impression"));
        }
    }

    public final void zzajk() {
        if (zzaoy()) {
            zzgh("adapter_impression").zzapc();
        }
    }

    public final void zzajm() {
        if (zzaoy()) {
            zzgh("adapter_shown").zzapc();
        }
    }

    public final void zzl(zzva zzva) {
        if (this.zzghc) {
            zzcmt zzgh = zzgh("ifts");
            zzgh.zzr("reason", "adapter");
            int i = zzva.errorCode;
            String str = zzva.zzcgw;
            if (zzva.zzcgx.equals(MobileAds.ERROR_DOMAIN) && zzva.zzcgy != null && !zzva.zzcgy.zzcgx.equals(MobileAds.ERROR_DOMAIN)) {
                i = zzva.zzcgy.errorCode;
                str = zzva.zzcgy.zzcgw;
            }
            if (i >= 0) {
                zzgh.zzr("arec", String.valueOf(i));
            }
            String zzgu = this.zzggz.zzgu(str);
            if (zzgu != null) {
                zzgh.zzr("areec", zzgu);
            }
            zzgh.zzapc();
        }
    }

    public final void zza(zzccl zzccl) {
        if (this.zzghc) {
            zzcmt zzgh = zzgh("ifts");
            zzgh.zzr("reason", "exception");
            if (!TextUtils.isEmpty(zzccl.getMessage())) {
                zzgh.zzr(NotificationCompat.CATEGORY_MESSAGE, zzccl.getMessage());
            }
            zzgh.zzapc();
        }
    }

    public final void zzajw() {
        if (this.zzghc) {
            zzcmt zzgh = zzgh("ifts");
            zzgh.zzr("reason", "blocked");
            zzgh.zzapc();
        }
    }

    private final boolean zzaoy() {
        if (this.zzghb == null) {
            synchronized (this) {
                if (this.zzghb == null) {
                    zzaaq zzaaq = zzabb.zzcqf;
                    zzp.zzkr();
                    this.zzghb = Boolean.valueOf(zzf((String) zzwm.zzpx().zzd(zzaaq), zzayu.zzbd(this.zzvr)));
                }
            }
        }
        return this.zzghb.booleanValue();
    }

    private static boolean zzf(String str, String str2) {
        if (!(str == null || str2 == null)) {
            try {
                return Pattern.matches(str, str2);
            } catch (RuntimeException e) {
                zzp.zzkv().zza(e, "CsiActionsListener.isPatternMatched");
            }
        }
        return false;
    }

    private final zzcmt zzgh(String str) {
        zzcmt zzd = this.zzfpk.zzapf().zza(this.zzevd.zzhew.zzeou).zzd(this.zzfmx);
        zzd.zzr("action", str);
        if (!this.zzfmx.zzhds.isEmpty()) {
            zzd.zzr("ancn", this.zzfmx.zzhds.get(0));
        }
        if (this.zzfmx.zzhek) {
            zzp.zzkr();
            zzd.zzr("device_connectivity", zzayu.zzbf(this.zzvr) ? "online" : "offline");
            zzd.zzr("event_timestamp", String.valueOf(zzp.zzky().currentTimeMillis()));
            zzd.zzr("offline_ad", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        }
        return zzd;
    }

    private final void zza(zzcmt zzcmt) {
        if (this.zzfmx.zzhek) {
            this.zzgha.zza(new zzcsv(zzp.zzky().currentTimeMillis(), this.zzevd.zzhew.zzeou.zzdtb, zzcmt.zzapd(), zzcsq.zzgne));
            return;
        }
        zzcmt.zzapc();
    }
}
