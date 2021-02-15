package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcim implements zzahq<Object> {
    private final zzeos<zzcif> zzfza;
    private final zzcil zzgbo;
    private final zzafk zzgej;

    public zzcim(zzcep zzcep, zzcei zzcei, zzcil zzcil, zzeos<zzcif> zzeos) {
        this.zzgej = zzcep.zzge(zzcei.getCustomTemplateId());
        this.zzgbo = zzcil;
        this.zzfza = zzeos;
    }

    public final void zzant() {
        if (this.zzgej != null) {
            this.zzgbo.zza("/nativeAdCustomClick", (zzahq<Object>) this);
        }
    }

    public final void zza(Object obj, Map<String, String> map) {
        String str = map.get("asset");
        try {
            this.zzgej.zza(this.zzfza.get(), str);
        } catch (RemoteException e) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40);
            sb.append("Failed to call onCustomClick for asset ");
            sb.append(str);
            sb.append(".");
            zzayp.zzd(sb.toString(), e);
        }
    }
}
