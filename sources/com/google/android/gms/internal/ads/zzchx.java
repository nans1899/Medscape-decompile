package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Collections;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzchx extends zzajd implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener, zzadn {
    private boolean zzepb = false;
    private zzyo zzfzs;
    private View zzfzx;
    private zzcdx zzgaw;
    private boolean zzgeb = false;

    public zzchx(zzcdx zzcdx, zzcei zzcei) {
        this.zzfzx = zzcei.zzame();
        this.zzfzs = zzcei.getVideoController();
        this.zzgaw = zzcdx;
        if (zzcei.zzamf() != null) {
            zzcei.zzamf().zza((zzadn) this);
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzajf zzajf) throws RemoteException {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        if (this.zzepb) {
            zzayp.zzfc("Instream ad can not be shown after destroy().");
            zza(zzajf, 2);
        } else if (this.zzfzx == null || this.zzfzs == null) {
            String str = this.zzfzx == null ? "can not get video view." : "can not get video controller.";
            zzayp.zzfc(str.length() != 0 ? "Instream internal error: ".concat(str) : new String("Instream internal error: "));
            zza(zzajf, 0);
        } else if (this.zzgeb) {
            zzayp.zzfc("Instream ad should not be used again.");
            zza(zzajf, 1);
        } else {
            this.zzgeb = true;
            zzanp();
            ((ViewGroup) ObjectWrapper.unwrap(iObjectWrapper)).addView(this.zzfzx, new ViewGroup.LayoutParams(-1, -1));
            zzp.zzlo();
            zzbco.zza(this.zzfzx, (ViewTreeObserver.OnGlobalLayoutListener) this);
            zzp.zzlo();
            zzbco.zza(this.zzfzx, (ViewTreeObserver.OnScrollChangedListener) this);
            zzanq();
            try {
                zzajf.zztc();
            } catch (RemoteException e) {
                zzayp.zze("#007 Could not call remote method.", e);
            }
        }
    }

    public final void zzr(IObjectWrapper iObjectWrapper) throws RemoteException {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zza(iObjectWrapper, (zzajf) new zzchz(this));
    }

    public final zzyo getVideoController() throws RemoteException {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        if (!this.zzepb) {
            return this.zzfzs;
        }
        zzayp.zzfc("getVideoController: Instream ad should not be used after destroyed");
        return null;
    }

    public final zzadz zzsw() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        if (this.zzepb) {
            zzayp.zzfc("getVideoController: Instream ad should not be used after destroyed");
            return null;
        }
        zzcdx zzcdx = this.zzgaw;
        if (zzcdx == null || zzcdx.zzalx() == null) {
            return null;
        }
        return this.zzgaw.zzalx().zzsw();
    }

    public final void destroy() throws RemoteException {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzanp();
        zzcdx zzcdx = this.zzgaw;
        if (zzcdx != null) {
            zzcdx.destroy();
        }
        this.zzgaw = null;
        this.zzfzx = null;
        this.zzfzs = null;
        this.zzepb = true;
    }

    private final void zzanp() {
        View view = this.zzfzx;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.zzfzx);
            }
        }
    }

    public final void onGlobalLayout() {
        zzanq();
    }

    public final void onScrollChanged() {
        zzanq();
    }

    public final void zzsf() {
        zzayu.zzeba.post(new zzcia(this));
    }

    private final void zzanq() {
        View view;
        zzcdx zzcdx = this.zzgaw;
        if (zzcdx != null && (view = this.zzfzx) != null) {
            zzcdx.zzb(view, Collections.emptyMap(), Collections.emptyMap(), zzcdx.zzz(this.zzfzx));
        }
    }

    private static void zza(zzajf zzajf, int i) {
        try {
            zzajf.zzdc(i);
        } catch (RemoteException e) {
            zzayp.zze("#007 Could not call remote method.", e);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzanr() {
        try {
            destroy();
        } catch (RemoteException e) {
            zzayp.zze("#007 Could not call remote method.", e);
        }
    }
}
