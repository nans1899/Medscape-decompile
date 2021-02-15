package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Iterator;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbos extends zzboq {
    private final View view;
    private final zzbgj zzdgy;
    private final Executor zzfoc;
    private final zzdnu zzfpe;
    private final zzbql zzfqb;
    private final zzcep zzfqc;
    private final zzcaf zzfqd;
    private final zzeos<zzczf> zzfqe;
    private zzvn zzfqf;
    private final Context zzvr;

    zzbos(zzbqn zzbqn, Context context, zzdnu zzdnu, View view2, zzbgj zzbgj, zzbql zzbql, zzcep zzcep, zzcaf zzcaf, zzeos<zzczf> zzeos, Executor executor) {
        super(zzbqn);
        this.zzvr = context;
        this.view = view2;
        this.zzdgy = zzbgj;
        this.zzfpe = zzdnu;
        this.zzfqb = zzbql;
        this.zzfqc = zzcep;
        this.zzfqd = zzcaf;
        this.zzfqe = zzeos;
        this.zzfoc = executor;
    }

    public final View zzahx() {
        return this.view;
    }

    public final void zza(ViewGroup viewGroup, zzvn zzvn) {
        zzbgj zzbgj;
        if (viewGroup != null && (zzbgj = this.zzdgy) != null) {
            zzbgj.zza(zzbhy.zzb(zzvn));
            viewGroup.setMinimumHeight(zzvn.heightPixels);
            viewGroup.setMinimumWidth(zzvn.widthPixels);
            this.zzfqf = zzvn;
        }
    }

    public final zzyo getVideoController() {
        try {
            return this.zzfqb.getVideoController();
        } catch (zzdos unused) {
            return null;
        }
    }

    public final zzdnu zzahw() {
        boolean z;
        zzvn zzvn = this.zzfqf;
        if (zzvn != null) {
            return zzdoq.zzf(zzvn);
        }
        if (this.zzeot.zzhee) {
            Iterator<String> it = this.zzeot.zzhdi.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                String next = it.next();
                if (next != null && next.contains("FirstParty")) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                return new zzdnu(this.view.getWidth(), this.view.getHeight(), false);
            }
        }
        return zzdoq.zza(this.zzeot.zzhdq, this.zzfpe);
    }

    public final zzdnu zzaif() {
        return this.zzfpe;
    }

    public final int zzaig() {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcxu)).booleanValue() && this.zzeot.zzhei) {
            if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcxv)).booleanValue()) {
                return 0;
            }
        }
        return this.zzfmw.zzhew.zzeou.zzhel;
    }

    public final void zzkf() {
        this.zzfqd.zzakv();
    }

    public final void zzaih() {
        this.zzfoc.execute(new zzbor(this));
        super.zzaih();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzaii() {
        if (this.zzfqc.zzams() != null) {
            try {
                this.zzfqc.zzams().zza(this.zzfqe.get(), ObjectWrapper.wrap(this.zzvr));
            } catch (RemoteException e) {
                zzayp.zzc("RemoteException when notifyAdLoad is called", e);
            }
        }
    }
}
