package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzb;
import com.google.android.gms.ads.internal.zzk;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.util.Predicate;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzakk implements zzajz, zzaki {
    /* access modifiers changed from: private */
    public final zzbgj zzdgy;

    public zzakk(Context context, zzbbx zzbbx, zzeg zzeg, zzb zzb) throws zzbgv {
        zzp.zzks();
        zzbgj zza = zzbgr.zza(context, zzbhy.zzacy(), "", false, false, zzeg, (zzacb) null, zzbbx, (zzabs) null, (zzk) null, (zzb) null, zzts.zzna(), (zzsu) null, false, (zzdnv) null, (zzdnw) null);
        this.zzdgy = zza;
        zza.getView().setWillNotDraw(true);
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

    private static void runOnUiThread(Runnable runnable) {
        zzwm.zzpt();
        if (zzbbg.zzyn()) {
            runnable.run();
        } else {
            zzayu.zzeba.post(runnable);
        }
    }

    public final void zzdc(String str) {
        runOnUiThread(new zzakj(this, str));
    }

    public final void zzcz(String str) {
        runOnUiThread(new zzako(this, String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head><body></body></html>", new Object[]{str})));
    }

    public final void zzda(String str) {
        runOnUiThread(new zzakn(this, str));
    }

    public final void zzdb(String str) {
        runOnUiThread(new zzakq(this, str));
    }

    public final void zza(String str, zzahq<? super zzalp> zzahq) {
        this.zzdgy.zza(str, (zzahq<? super zzbgj>) new zzakp(this, zzahq));
    }

    public final void zzb(String str, zzahq<? super zzalp> zzahq) {
        this.zzdgy.zza(str, (Predicate<zzahq<? super zzbgj>>) new zzakm(zzahq));
    }

    public final void zza(zzakh zzakh) {
        zzbhv zzabw = this.zzdgy.zzabw();
        zzakh.getClass();
        zzabw.zza(zzakl.zzb(zzakh));
    }

    public final zzals zzth() {
        return new zzalr(this);
    }

    public final void destroy() {
        this.zzdgy.destroy();
    }

    public final boolean isDestroyed() {
        return this.zzdgy.isDestroyed();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzdh(String str) {
        this.zzdgy.zzdc(str);
    }
}
