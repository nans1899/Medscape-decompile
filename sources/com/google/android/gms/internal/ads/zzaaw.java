package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaaw {
    private final Collection<zzaaq<?>> zzclj = new ArrayList();
    private final Collection<zzaaq<String>> zzclk = new ArrayList();
    private final Collection<zzaaq<String>> zzcll = new ArrayList();

    public final void zza(zzaaq zzaaq) {
        this.zzclj.add(zzaaq);
    }

    public final void zzb(zzaaq<String> zzaaq) {
        this.zzclk.add(zzaaq);
    }

    public final void zzc(zzaaq<String> zzaaq) {
        this.zzcll.add(zzaaq);
    }

    public final void zza(SharedPreferences.Editor editor, int i, JSONObject jSONObject) {
        for (zzaaq next : this.zzclj) {
            if (next.getSource() == 1) {
                next.zza(editor, next.zzb(jSONObject));
            }
        }
        if (jSONObject != null) {
            editor.putString("flag_configuration", jSONObject.toString());
        } else {
            zzbbq.zzfc("Flag Json is null.");
        }
    }

    public final List<String> zzrg() {
        ArrayList arrayList = new ArrayList();
        for (zzaaq<String> zzd : this.zzclk) {
            String str = (String) zzwm.zzpx().zzd(zzd);
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(str);
            }
        }
        arrayList.addAll(zzabg.zzrm());
        return arrayList;
    }

    public final List<String> zzrh() {
        List<String> zzrg = zzrg();
        for (zzaaq<String> zzd : this.zzcll) {
            String str = (String) zzwm.zzpx().zzd(zzd);
            if (!TextUtils.isEmpty(str)) {
                zzrg.add(str);
            }
        }
        zzrg.addAll(zzabg.zzrn());
        return zzrg;
    }
}
