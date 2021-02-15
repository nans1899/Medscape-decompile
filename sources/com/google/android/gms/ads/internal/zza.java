package com.google.android.gms.ads.internal;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.internal.ads.zzasr;
import com.google.android.gms.internal.ads.zzawl;
import com.google.android.gms.internal.ads.zzayu;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zza {
    private boolean zzbof;
    private zzawl zzbog;
    private zzasr zzboh = null;
    private final Context zzvr;

    public zza(Context context, zzawl zzawl, zzasr zzasr) {
        this.zzvr = context;
        this.zzbog = zzawl;
        if (0 == 0) {
            this.zzboh = new zzasr();
        }
    }

    private final boolean zzjy() {
        zzawl zzawl = this.zzbog;
        return (zzawl != null && zzawl.zzvq().zzdxh) || this.zzboh.zzdtr;
    }

    public final void recordClick() {
        this.zzbof = true;
    }

    public final boolean zzjz() {
        return !zzjy() || this.zzbof;
    }

    public final void zzbo(String str) {
        if (zzjy()) {
            if (str == null) {
                str = "";
            }
            zzawl zzawl = this.zzbog;
            if (zzawl != null) {
                zzawl.zza(str, (Map<String, String>) null, 3);
            } else if (this.zzboh.zzdtr && this.zzboh.zzdts != null) {
                for (String next : this.zzboh.zzdts) {
                    if (!TextUtils.isEmpty(next)) {
                        String replace = next.replace("{NAVIGATION_URL}", Uri.encode(str));
                        zzp.zzkr();
                        zzayu.zzb(this.zzvr, "", replace);
                    }
                }
            }
        }
    }
}
