package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import java.util.concurrent.Executor;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbnd implements zzqu {
    private final Clock zzbqa;
    private boolean zzbvy = false;
    private zzbgj zzdgy;
    private final zzbms zzfnz;
    private final Executor zzfoc;
    private zzbmw zzfoe = new zzbmw();
    private boolean zzfox = false;

    public zzbnd(Executor executor, zzbms zzbms, Clock clock) {
        this.zzfoc = executor;
        this.zzfnz = zzbms;
        this.zzbqa = clock;
    }

    private final void zzahi() {
        try {
            JSONObject zza = this.zzfnz.zzj(this.zzfoe);
            if (this.zzdgy != null) {
                this.zzfoc.execute(new zzbng(this, zza));
            }
        } catch (JSONException e) {
            zzayp.zza("Failed to call video active view js", e);
        }
    }

    public final void zza(zzqr zzqr) {
        this.zzfoe.zzbro = this.zzfox ? false : zzqr.zzbro;
        this.zzfoe.timestamp = this.zzbqa.elapsedRealtime();
        this.zzfoe.zzfoo = zzqr;
        if (this.zzbvy) {
            zzahi();
        }
    }

    public final void zzg(zzbgj zzbgj) {
        this.zzdgy = zzbgj;
    }

    public final void disable() {
        this.zzbvy = false;
    }

    public final void enable() {
        this.zzbvy = true;
        zzahi();
    }

    public final void zzbf(boolean z) {
        this.zzfox = z;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzi(JSONObject jSONObject) {
        this.zzdgy.zzb("AFMA_updateActiveView", jSONObject);
    }
}
