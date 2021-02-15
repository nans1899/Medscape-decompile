package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzctd {
    private final List<zzvr> zzadu = Collections.synchronizedList(new ArrayList());
    private zzdnv zzeot = null;
    private final Map<String, zzvr> zzgnt = Collections.synchronizedMap(new HashMap());

    public final void zze(zzdnv zzdnv) {
        String str = zzdnv.zzdjo;
        if (!this.zzgnt.containsKey(str)) {
            Bundle bundle = new Bundle();
            Iterator<String> keys = zzdnv.zzhdu.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    bundle.putString(next, zzdnv.zzhdu.getString(next));
                } catch (JSONException unused) {
                }
            }
            zzvr zzvr = new zzvr(zzdnv.zzhdy, 0, (zzva) null, bundle);
            this.zzadu.add(zzvr);
            this.zzgnt.put(str, zzvr);
        }
    }

    public final void zza(zzdnv zzdnv, long j, zzva zzva) {
        String str = zzdnv.zzdjo;
        if (this.zzgnt.containsKey(str)) {
            if (this.zzeot == null) {
                this.zzeot = zzdnv;
            }
            zzvr zzvr = this.zzgnt.get(str);
            zzvr.zzcic = j;
            zzvr.zzcid = zzva;
        }
    }

    public final zzbty zzaqi() {
        return new zzbty(this.zzeot, "", this);
    }

    public final List<zzvr> getAdapterResponses() {
        return this.zzadu;
    }
}
