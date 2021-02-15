package com.google.android.gms.internal.ads;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzalr implements zzajz, zzals {
    private final zzalp zzdih;
    private final HashSet<AbstractMap.SimpleEntry<String, zzahq<? super zzalp>>> zzdii = new HashSet<>();

    public zzalr(zzalp zzalp) {
        this.zzdih = zzalp;
    }

    public final void zza(String str, Map map) {
        zzakc.zza((zzajz) this, str, map);
    }

    public final void zza(String str, JSONObject jSONObject) {
        zzakc.zzb(this, str, jSONObject);
    }

    public final void zzb(String str, JSONObject jSONObject) {
        zzakc.zza((zzajz) this, str, jSONObject);
    }

    public final void zzj(String str, String str2) {
        zzakc.zza((zzajz) this, str, str2);
    }

    public final void zzdc(String str) {
        this.zzdih.zzdc(str);
    }

    public final void zza(String str, zzahq<? super zzalp> zzahq) {
        this.zzdih.zza(str, zzahq);
        this.zzdii.add(new AbstractMap.SimpleEntry(str, zzahq));
    }

    public final void zzb(String str, zzahq<? super zzalp> zzahq) {
        this.zzdih.zzb(str, zzahq);
        this.zzdii.remove(new AbstractMap.SimpleEntry(str, zzahq));
    }

    public final void zzto() {
        Iterator<AbstractMap.SimpleEntry<String, zzahq<? super zzalp>>> it = this.zzdii.iterator();
        while (it.hasNext()) {
            AbstractMap.SimpleEntry next = it.next();
            String valueOf = String.valueOf(((zzahq) next.getValue()).toString());
            zzayp.zzei(valueOf.length() != 0 ? "Unregistering eventhandler: ".concat(valueOf) : new String("Unregistering eventhandler: "));
            this.zzdih.zzb((String) next.getKey(), (zzahq) next.getValue());
        }
        this.zzdii.clear();
    }
}
