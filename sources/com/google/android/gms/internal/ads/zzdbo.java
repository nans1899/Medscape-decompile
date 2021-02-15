package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdbo implements zzdgx<zzdbp> {
    private final zzdok zzfsk;
    private final zzdzb zzguy;
    private final View zzguz;
    private final Context zzvr;

    public zzdbo(zzdzb zzdzb, Context context, zzdok zzdok, ViewGroup viewGroup) {
        this.zzguy = zzdzb;
        this.zzvr = context;
        this.zzfsk = zzdok;
        this.zzguz = viewGroup;
    }

    public final zzdzc<zzdbp> zzarj() {
        return this.zzguy.zze(new zzdbr(this));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdbp zzark() throws Exception {
        Context context = this.zzvr;
        zzvn zzvn = this.zzfsk.zzbpf;
        ArrayList arrayList = new ArrayList();
        View view = this.zzguz;
        while (view != null) {
            ViewParent parent = view.getParent();
            if (parent == null) {
                break;
            }
            int i = -1;
            if (parent instanceof ViewGroup) {
                i = ((ViewGroup) parent).indexOfChild(view);
            }
            Bundle bundle = new Bundle();
            bundle.putString("type", parent.getClass().getName());
            bundle.putInt("index_of_child", i);
            arrayList.add(bundle);
            if (!(parent instanceof View)) {
                break;
            }
            view = (View) parent;
        }
        return new zzdbp(context, zzvn, arrayList);
    }
}
