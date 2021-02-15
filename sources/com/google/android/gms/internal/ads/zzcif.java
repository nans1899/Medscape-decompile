package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.SimpleArrayMap;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcif extends zzaez {
    private final Context zzaai;
    private final zzcei zzfxu;
    /* access modifiers changed from: private */
    public zzcdx zzgaw;
    private zzcfe zzgee;

    public zzcif(Context context, zzcei zzcei, zzcfe zzcfe, zzcdx zzcdx) {
        this.zzaai = context;
        this.zzfxu = zzcei;
        this.zzgee = zzcfe;
        this.zzgaw = zzcdx;
    }

    public final IObjectWrapper zzsk() {
        return null;
    }

    public final String zzcx(String str) {
        return this.zzfxu.zzamk().get(str);
    }

    public final zzaee zzcy(String str) {
        return this.zzfxu.zzami().get(str);
    }

    public final List<String> getAvailableAssetNames() {
        SimpleArrayMap<String, zzadq> zzami = this.zzfxu.zzami();
        SimpleArrayMap<String, String> zzamk = this.zzfxu.zzamk();
        String[] strArr = new String[(zzami.size() + zzamk.size())];
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i2 < zzami.size()) {
            strArr[i3] = zzami.keyAt(i2);
            i2++;
            i3++;
        }
        while (i < zzamk.size()) {
            strArr[i3] = zzamk.keyAt(i);
            i++;
            i3++;
        }
        return Arrays.asList(strArr);
    }

    public final String getCustomTemplateId() {
        return this.zzfxu.getCustomTemplateId();
    }

    public final void performClick(String str) {
        zzcdx zzcdx = this.zzgaw;
        if (zzcdx != null) {
            zzcdx.zzfz(str);
        }
    }

    public final void recordImpression() {
        zzcdx zzcdx = this.zzgaw;
        if (zzcdx != null) {
            zzcdx.zzalp();
        }
    }

    public final zzyo getVideoController() {
        return this.zzfxu.getVideoController();
    }

    public final void destroy() {
        zzcdx zzcdx = this.zzgaw;
        if (zzcdx != null) {
            zzcdx.destroy();
        }
        this.zzgaw = null;
        this.zzgee = null;
    }

    public final IObjectWrapper zzsp() {
        return ObjectWrapper.wrap(this.zzaai);
    }

    public final boolean zzp(IObjectWrapper iObjectWrapper) {
        Object unwrap = ObjectWrapper.unwrap(iObjectWrapper);
        if (!(unwrap instanceof ViewGroup)) {
            return false;
        }
        zzcfe zzcfe = this.zzgee;
        if (!(zzcfe != null && zzcfe.zza((ViewGroup) unwrap))) {
            return false;
        }
        this.zzfxu.zzamf().zza((zzads) new zzcii(this));
        return true;
    }

    public final boolean zzsq() {
        zzcdx zzcdx = this.zzgaw;
        if ((zzcdx == null || zzcdx.zzalw()) && this.zzfxu.zzamg() != null && this.zzfxu.zzamf() == null) {
            return true;
        }
        return false;
    }

    public final boolean zzsr() {
        IObjectWrapper zzamh = this.zzfxu.zzamh();
        if (zzamh != null) {
            zzp.zzlg().zzab(zzamh);
            return true;
        }
        zzayp.zzfe("Trying to start OMID session before creation.");
        return false;
    }

    public final void zzq(IObjectWrapper iObjectWrapper) {
        zzcdx zzcdx;
        Object unwrap = ObjectWrapper.unwrap(iObjectWrapper);
        if ((unwrap instanceof View) && this.zzfxu.zzamh() != null && (zzcdx = this.zzgaw) != null) {
            zzcdx.zzaa((View) unwrap);
        }
    }

    public final void zzss() {
        String zzamj = this.zzfxu.zzamj();
        if ("Google".equals(zzamj)) {
            zzayp.zzfe("Illegal argument specified for omid partner name.");
            return;
        }
        zzcdx zzcdx = this.zzgaw;
        if (zzcdx != null) {
            zzcdx.zzi(zzamj, false);
        }
    }
}
