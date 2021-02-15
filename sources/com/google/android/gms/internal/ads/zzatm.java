package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzp;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzatm implements Callable<zzatl> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzatn zzdvl;

    zzatm(zzatn zzatn, Context context) {
        this.zzdvl = zzatn;
        this.val$context = context;
    }

    public final /* synthetic */ Object call() throws Exception {
        zzatl zzatl;
        zzatp zzatp = (zzatp) this.zzdvl.zzdvm.get(this.val$context);
        if (zzatp != null) {
            if (!(zzatp.zzdvp + zzaco.zzdam.get().longValue() < zzp.zzky().currentTimeMillis())) {
                zzatl = new zzatk(this.val$context, zzatp.zzdvq).zzvn();
                this.zzdvl.zzdvm.put(this.val$context, new zzatp(this.zzdvl, zzatl));
                return zzatl;
            }
        }
        zzatl = new zzatk(this.val$context).zzvn();
        this.zzdvl.zzdvm.put(this.val$context, new zzatp(this.zzdvl, zzatl));
        return zzatl;
    }
}
