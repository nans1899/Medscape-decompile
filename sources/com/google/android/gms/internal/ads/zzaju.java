package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzp;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzaju extends zzakd<zzalp> implements zzajz, zzaki {
    private final zzbim zzdgn;
    /* access modifiers changed from: private */
    public zzakh zzdgo;

    public zzaju(Context context, zzbbx zzbbx) throws zzbgv {
        try {
            zzbim zzbim = new zzbim(context, new zzaka(this));
            this.zzdgn = zzbim;
            zzbim.setWillNotDraw(true);
            this.zzdgn.addJavascriptInterface(new zzajx(this), "GoogleJsInterface");
            zzp.zzkr().zza(context, zzbbx.zzbre, this.zzdgn.getSettings());
            super.zzg(this);
        } catch (Throwable th) {
            throw new zzbgv("Init failed.", th);
        }
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

    public final void zzcz(String str) {
        zzda(String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head></html>", new Object[]{str}));
    }

    public final void zzda(String str) {
        zzbbz.zzeet.execute(new zzajt(this, str));
    }

    public final void zzdb(String str) {
        zzbbz.zzeet.execute(new zzajw(this, str));
    }

    public final void zza(zzakh zzakh) {
        this.zzdgo = zzakh;
    }

    public final void destroy() {
        this.zzdgn.destroy();
    }

    public final boolean isDestroyed() {
        return this.zzdgn.isDestroyed();
    }

    public final zzals zzth() {
        return new zzalr(this);
    }

    public final void zzdc(String str) {
        zzbbz.zzeet.execute(new zzajv(this, str));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzdd(String str) {
        this.zzdgn.zzdc(str);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzde(String str) {
        this.zzdgn.loadUrl(str);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzdf(String str) {
        this.zzdgn.loadData(str, "text/html", "UTF-8");
    }
}
