package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.ads.internal.zzp;
import com.webmd.wbmdcmepulse.omniture.OmnitureData;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbdx {
    private final zzbbx zzdqm;
    private final String zzdrb;
    private final zzabs zzegj;
    private boolean zzegn;
    private final zzabq zzeja;
    private final zzbac zzejb = new zzbah().zza("min_1", Double.MIN_VALUE, 1.0d).zza("1_5", 1.0d, 5.0d).zza("5_10", 5.0d, 10.0d).zza("10_20", 10.0d, 20.0d).zza("20_30", 20.0d, 30.0d).zza("30_max", 30.0d, Double.MAX_VALUE).zzyi();
    private final long[] zzejc;
    private final String[] zzejd;
    private boolean zzeje = false;
    private boolean zzejf = false;
    private boolean zzejg = false;
    private boolean zzejh = false;
    private zzbdf zzeji;
    private boolean zzejj;
    private boolean zzejk;
    private long zzejl = -1;
    private final Context zzvr;

    public zzbdx(Context context, zzbbx zzbbx, String str, zzabs zzabs, zzabq zzabq) {
        this.zzvr = context;
        this.zzdqm = zzbbx;
        this.zzdrb = str;
        this.zzegj = zzabs;
        this.zzeja = zzabq;
        String str2 = (String) zzwm.zzpx().zzd(zzabb.zzcmm);
        if (str2 == null) {
            this.zzejd = new String[0];
            this.zzejc = new long[0];
            return;
        }
        String[] split = TextUtils.split(str2, ",");
        this.zzejd = new String[split.length];
        this.zzejc = new long[split.length];
        for (int i = 0; i < split.length; i++) {
            try {
                this.zzejc[i] = Long.parseLong(split[i]);
            } catch (NumberFormatException e) {
                zzayp.zzd("Unable to parse frame hash target time number.", e);
                this.zzejc[i] = -1;
            }
        }
    }

    public final void zzb(zzbdf zzbdf) {
        zzabj.zza(this.zzegj, this.zzeja, "vpc2");
        this.zzeje = true;
        zzabs zzabs = this.zzegj;
        if (zzabs != null) {
            zzabs.zzh("vpn", zzbdf.zzyx());
        }
        this.zzeji = zzbdf;
    }

    public final void zzfb() {
        if (this.zzeje && !this.zzejf) {
            zzabj.zza(this.zzegj, this.zzeja, "vfr2");
            this.zzejf = true;
        }
    }

    public final void onStop() {
        if (zzadi.zzdcv.get().booleanValue() && !this.zzejj) {
            Bundle bundle = new Bundle();
            bundle.putString("type", "native-player-metrics");
            bundle.putString(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, this.zzdrb);
            bundle.putString(OmnitureData.MODULE_NAME_VIDEO, this.zzeji.zzyx());
            for (zzbae next : this.zzejb.zzyh()) {
                String valueOf = String.valueOf(next.name);
                bundle.putString(valueOf.length() != 0 ? "fps_c_".concat(valueOf) : new String("fps_c_"), Integer.toString(next.count));
                String valueOf2 = String.valueOf(next.name);
                bundle.putString(valueOf2.length() != 0 ? "fps_p_".concat(valueOf2) : new String("fps_p_"), Double.toString(next.zzecl));
            }
            int i = 0;
            while (true) {
                long[] jArr = this.zzejc;
                if (i < jArr.length) {
                    String str = this.zzejd[i];
                    if (str != null) {
                        String valueOf3 = String.valueOf(Long.valueOf(jArr[i]));
                        StringBuilder sb = new StringBuilder(String.valueOf(valueOf3).length() + 3);
                        sb.append("fh_");
                        sb.append(valueOf3);
                        bundle.putString(sb.toString(), str);
                    }
                    i++;
                } else {
                    zzp.zzkr().zza(this.zzvr, this.zzdqm.zzbre, "gmob-apps", bundle, true);
                    this.zzejj = true;
                    return;
                }
            }
        }
    }

    public final void zzc(zzbdf zzbdf) {
        if (this.zzejg && !this.zzejh) {
            if (zzayp.zzxa() && !this.zzejh) {
                zzayp.zzei("VideoMetricsMixin first frame");
            }
            zzabj.zza(this.zzegj, this.zzeja, "vff2");
            this.zzejh = true;
        }
        long nanoTime = zzp.zzky().nanoTime();
        if (this.zzegn && this.zzejk && this.zzejl != -1) {
            this.zzejb.zza(((double) TimeUnit.SECONDS.toNanos(1)) / ((double) (nanoTime - this.zzejl)));
        }
        this.zzejk = this.zzegn;
        this.zzejl = nanoTime;
        long longValue = ((Long) zzwm.zzpx().zzd(zzabb.zzcmn)).longValue();
        long currentPosition = (long) zzbdf.getCurrentPosition();
        int i = 0;
        while (true) {
            String[] strArr = this.zzejd;
            if (i >= strArr.length) {
                return;
            }
            if (strArr[i] != null || longValue <= Math.abs(currentPosition - this.zzejc[i])) {
                zzbdf zzbdf2 = zzbdf;
                i++;
            } else {
                String[] strArr2 = this.zzejd;
                int i2 = 8;
                Bitmap bitmap = zzbdf.getBitmap(8, 8);
                long j = 63;
                int i3 = 0;
                long j2 = 0;
                while (i3 < i2) {
                    int i4 = 0;
                    while (i4 < i2) {
                        int pixel = bitmap.getPixel(i4, i3);
                        j2 |= ((Color.blue(pixel) + Color.red(pixel)) + Color.green(pixel) > 128 ? 1 : 0) << ((int) j);
                        i4++;
                        j--;
                        i2 = 8;
                    }
                    i3++;
                    i2 = 8;
                }
                strArr2[i] = String.format("%016X", new Object[]{Long.valueOf(j2)});
                return;
            }
        }
    }

    public final void zzaac() {
        this.zzegn = true;
        if (this.zzejf && !this.zzejg) {
            zzabj.zza(this.zzegj, this.zzeja, "vfp2");
            this.zzejg = true;
        }
    }

    public final void zzaad() {
        this.zzegn = false;
    }
}
