package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.Executor;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcdx extends zzbqo {
    private final zzbbx zzbpe;
    private final zzeg zzeom;
    private final Executor zzfoc;
    private final zzcep zzfqc;
    private final zzawp zzfrn;
    private final zzczn zzfvj;
    private final zzcei zzfxu;
    private final zzceq zzfyt;
    private final zzcfe zzfyu;
    private final zzcem zzfyv;
    private final zzeos<zzcid> zzfyw;
    private final zzeos<zzcib> zzfyx;
    private final zzeos<zzcik> zzfyy;
    private final zzeos<zzchx> zzfyz;
    private final zzeos<zzcif> zzfza;
    private zzcgc zzfzb;
    private boolean zzfzc;
    private boolean zzfzd = false;
    private final zzced zzfze;
    private final Context zzvr;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzcdx(zzbqn zzbqn, Executor executor, zzcei zzcei, zzceq zzceq, zzcfe zzcfe, zzcem zzcem, zzcep zzcep, zzeos<zzcid> zzeos, zzeos<zzcib> zzeos2, zzeos<zzcik> zzeos3, zzeos<zzchx> zzeos4, zzeos<zzcif> zzeos5, zzawp zzawp, zzeg zzeg, zzbbx zzbbx, Context context, zzced zzced, zzczn zzczn) {
        super(zzbqn);
        this.zzfoc = executor;
        this.zzfxu = zzcei;
        this.zzfyt = zzceq;
        this.zzfyu = zzcfe;
        this.zzfyv = zzcem;
        this.zzfqc = zzcep;
        this.zzfyw = zzeos;
        this.zzfyx = zzeos2;
        this.zzfyy = zzeos3;
        this.zzfyz = zzeos4;
        this.zzfza = zzeos5;
        this.zzfrn = zzawp;
        this.zzeom = zzeg;
        this.zzbpe = zzbbx;
        this.zzvr = context;
        this.zzfze = zzced;
        this.zzfvj = zzczn;
    }

    public final void zzaih() {
        this.zzfoc.execute(new zzcea(this));
        if (this.zzfxu.zzama() != 7) {
            Executor executor = this.zzfoc;
            zzceq zzceq = this.zzfyt;
            zzceq.getClass();
            executor.execute(zzcdz.zza(zzceq));
        }
        super.zzaih();
    }

    public final synchronized void zzfz(String str) {
        this.zzfyt.zzfz(str);
    }

    public final synchronized void zzalp() {
        if (!this.zzfzc) {
            this.zzfyt.zzalp();
        }
    }

    public final synchronized void zzg(Bundle bundle) {
        this.zzfyt.zzg(bundle);
    }

    public final synchronized boolean zzi(Bundle bundle) {
        if (this.zzfzc) {
            return true;
        }
        boolean zzi = this.zzfyt.zzi(bundle);
        this.zzfzc = zzi;
        return zzi;
    }

    public final synchronized void zzh(Bundle bundle) {
        this.zzfyt.zzh(bundle);
    }

    public final synchronized void destroy() {
        this.zzfoc.execute(new zzcec(this));
        super.destroy();
    }

    public final synchronized void zza(zzcgc zzcgc) {
        zzdw zzca;
        this.zzfzb = zzcgc;
        this.zzfyu.zza(zzcgc);
        this.zzfyt.zza(zzcgc.zzahx(), zzcgc.zzand(), zzcgc.zzane(), (View.OnTouchListener) zzcgc, (View.OnClickListener) zzcgc);
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcqx)).booleanValue() && (zzca = this.zzeom.zzca()) != null) {
            zzca.zzb(zzcgc.zzahx());
        }
        if (zzcgc.zzanb() != null) {
            zzcgc.zzanb().zza((zzqu) this.zzfrn);
        }
    }

    public final synchronized void zzb(zzcgc zzcgc) {
        this.zzfyt.zza(zzcgc.zzahx(), zzcgc.zzanc());
        if (zzcgc.zzana() != null) {
            zzcgc.zzana().setClickable(false);
            zzcgc.zzana().removeAllViews();
        }
        if (zzcgc.zzanb() != null) {
            zzcgc.zzanb().zzb(this.zzfrn);
        }
        this.zzfzb = null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0044, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zza(android.view.View r9, android.view.View r10, java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r11, java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r12, boolean r13) {
        /*
            r8 = this;
            monitor-enter(r8)
            com.google.android.gms.internal.ads.zzcfe r0 = r8.zzfyu     // Catch:{ all -> 0x0045 }
            com.google.android.gms.internal.ads.zzcgc r1 = r8.zzfzb     // Catch:{ all -> 0x0045 }
            r0.zzc(r1)     // Catch:{ all -> 0x0045 }
            com.google.android.gms.internal.ads.zzceq r2 = r8.zzfyt     // Catch:{ all -> 0x0045 }
            r3 = r9
            r4 = r10
            r5 = r11
            r6 = r12
            r7 = r13
            r2.zza((android.view.View) r3, (android.view.View) r4, (java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>>) r5, (java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>>) r6, (boolean) r7)     // Catch:{ all -> 0x0045 }
            boolean r9 = r8.zzfzd     // Catch:{ all -> 0x0045 }
            if (r9 == 0) goto L_0x0043
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r9 = com.google.android.gms.internal.ads.zzabb.zzcsa     // Catch:{ all -> 0x0045 }
            com.google.android.gms.internal.ads.zzaax r10 = com.google.android.gms.internal.ads.zzwm.zzpx()     // Catch:{ all -> 0x0045 }
            java.lang.Object r9 = r10.zzd(r9)     // Catch:{ all -> 0x0045 }
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch:{ all -> 0x0045 }
            boolean r9 = r9.booleanValue()     // Catch:{ all -> 0x0045 }
            if (r9 == 0) goto L_0x0043
            com.google.android.gms.internal.ads.zzcei r9 = r8.zzfxu     // Catch:{ all -> 0x0045 }
            com.google.android.gms.internal.ads.zzbgj r9 = r9.zzamf()     // Catch:{ all -> 0x0045 }
            if (r9 != 0) goto L_0x0031
            goto L_0x0043
        L_0x0031:
            com.google.android.gms.internal.ads.zzcei r9 = r8.zzfxu     // Catch:{ all -> 0x0045 }
            com.google.android.gms.internal.ads.zzbgj r9 = r9.zzamf()     // Catch:{ all -> 0x0045 }
            java.lang.String r10 = "onSdkAdUserInteractionClick"
            java.util.HashMap r11 = new java.util.HashMap     // Catch:{ all -> 0x0045 }
            r11.<init>()     // Catch:{ all -> 0x0045 }
            r9.zza((java.lang.String) r10, (java.util.Map<java.lang.String, ?>) r11)     // Catch:{ all -> 0x0045 }
            monitor-exit(r8)
            return
        L_0x0043:
            monitor-exit(r8)
            return
        L_0x0045:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcdx.zza(android.view.View, android.view.View, java.util.Map, java.util.Map, boolean):void");
    }

    public final synchronized void zza(View view, MotionEvent motionEvent, View view2) {
        this.zzfyt.zza(view, motionEvent, view2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0069, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzb(android.view.View r3, java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r4, java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r5, boolean r6) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.zzfzc     // Catch:{ all -> 0x006a }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r2)
            return
        L_0x0007:
            r0 = 1
            if (r6 == 0) goto L_0x001a
            com.google.android.gms.internal.ads.zzcfe r6 = r2.zzfyu     // Catch:{ all -> 0x006a }
            com.google.android.gms.internal.ads.zzcgc r1 = r2.zzfzb     // Catch:{ all -> 0x006a }
            r6.zzd(r1)     // Catch:{ all -> 0x006a }
            com.google.android.gms.internal.ads.zzceq r6 = r2.zzfyt     // Catch:{ all -> 0x006a }
            r6.zzb(r3, r4, r5)     // Catch:{ all -> 0x006a }
            r2.zzfzc = r0     // Catch:{ all -> 0x006a }
            monitor-exit(r2)
            return
        L_0x001a:
            if (r6 != 0) goto L_0x0068
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r6 = com.google.android.gms.internal.ads.zzabb.zzcsh     // Catch:{ all -> 0x006a }
            com.google.android.gms.internal.ads.zzaax r1 = com.google.android.gms.internal.ads.zzwm.zzpx()     // Catch:{ all -> 0x006a }
            java.lang.Object r6 = r1.zzd(r6)     // Catch:{ all -> 0x006a }
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ all -> 0x006a }
            boolean r6 = r6.booleanValue()     // Catch:{ all -> 0x006a }
            if (r6 == 0) goto L_0x0068
            if (r4 == 0) goto L_0x0068
            java.util.Set r6 = r4.entrySet()     // Catch:{ all -> 0x006a }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x006a }
        L_0x0038:
            boolean r1 = r6.hasNext()     // Catch:{ all -> 0x006a }
            if (r1 == 0) goto L_0x0068
            java.lang.Object r1 = r6.next()     // Catch:{ all -> 0x006a }
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch:{ all -> 0x006a }
            java.lang.Object r1 = r1.getValue()     // Catch:{ all -> 0x006a }
            java.lang.ref.WeakReference r1 = (java.lang.ref.WeakReference) r1     // Catch:{ all -> 0x006a }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x006a }
            android.view.View r1 = (android.view.View) r1     // Catch:{ all -> 0x006a }
            if (r1 == 0) goto L_0x0038
            boolean r1 = zzz(r1)     // Catch:{ all -> 0x006a }
            if (r1 == 0) goto L_0x0038
            com.google.android.gms.internal.ads.zzcfe r6 = r2.zzfyu     // Catch:{ all -> 0x006a }
            com.google.android.gms.internal.ads.zzcgc r1 = r2.zzfzb     // Catch:{ all -> 0x006a }
            r6.zzd(r1)     // Catch:{ all -> 0x006a }
            com.google.android.gms.internal.ads.zzceq r6 = r2.zzfyt     // Catch:{ all -> 0x006a }
            r6.zzb(r3, r4, r5)     // Catch:{ all -> 0x006a }
            r2.zzfzc = r0     // Catch:{ all -> 0x006a }
            monitor-exit(r2)
            return
        L_0x0068:
            monitor-exit(r2)
            return
        L_0x006a:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcdx.zzb(android.view.View, java.util.Map, java.util.Map, boolean):void");
    }

    public final synchronized JSONObject zza(View view, Map<String, WeakReference<View>> map, Map<String, WeakReference<View>> map2) {
        return this.zzfyt.zza(view, map, map2);
    }

    public final synchronized void setClickConfirmingView(View view) {
        this.zzfyt.setClickConfirmingView(view);
    }

    public final synchronized void zza(zzafz zzafz) {
        this.zzfyt.zza(zzafz);
    }

    public final synchronized void cancelUnconfirmedClick() {
        this.zzfyt.cancelUnconfirmedClick();
    }

    public final synchronized void zza(zzyd zzyd) {
        this.zzfyt.zza(zzyd);
    }

    public final synchronized void zza(zzxz zzxz) {
        this.zzfyt.zza(zzxz);
    }

    public final synchronized void zzsv() {
        this.zzfyt.zzsv();
    }

    public final synchronized void recordCustomClickGesture() {
        if (this.zzfzb == null) {
            zzayp.zzef("Ad should be associated with an ad view before calling recordCustomClickGesture()");
        } else {
            this.zzfoc.execute(new zzceb(this, this.zzfzb instanceof zzcez));
        }
    }

    public final synchronized boolean isCustomClickGestureEnabled() {
        return this.zzfyt.isCustomClickGestureEnabled();
    }

    public static boolean zzz(View view) {
        return view.isShown() && view.getGlobalVisibleRect(new Rect(), (Point) null);
    }

    public final boolean zzalv() {
        return this.zzfyv.zzamn();
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzi(java.lang.String r12, boolean r13) {
        /*
            r11 = this;
            com.google.android.gms.internal.ads.zzcem r0 = r11.zzfyv
            boolean r0 = r0.zzalw()
            if (r0 != 0) goto L_0x0009
            return
        L_0x0009:
            com.google.android.gms.internal.ads.zzcei r0 = r11.zzfxu
            com.google.android.gms.internal.ads.zzbgj r0 = r0.zzamg()
            com.google.android.gms.internal.ads.zzcei r1 = r11.zzfxu
            com.google.android.gms.internal.ads.zzbgj r1 = r1.zzamf()
            if (r0 != 0) goto L_0x001a
            if (r1 != 0) goto L_0x001a
            return
        L_0x001a:
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L_0x0020
            r4 = 1
            goto L_0x0021
        L_0x0020:
            r4 = 0
        L_0x0021:
            if (r1 == 0) goto L_0x0024
            r2 = 1
        L_0x0024:
            r5 = 0
            if (r4 == 0) goto L_0x0029
        L_0x0027:
            r9 = r5
            goto L_0x0031
        L_0x0029:
            if (r2 == 0) goto L_0x002f
            java.lang.String r5 = "javascript"
            r0 = r1
            goto L_0x0027
        L_0x002f:
            r0 = r5
            r9 = r0
        L_0x0031:
            android.webkit.WebView r4 = r0.getWebView()
            if (r4 != 0) goto L_0x0038
            return
        L_0x0038:
            com.google.android.gms.internal.ads.zzaqv r4 = com.google.android.gms.ads.internal.zzp.zzlg()
            android.content.Context r5 = r11.zzvr
            boolean r4 = r4.zzp(r5)
            if (r4 == 0) goto L_0x0098
            com.google.android.gms.internal.ads.zzbbx r4 = r11.zzbpe
            int r4 = r4.zzeel
            com.google.android.gms.internal.ads.zzbbx r5 = r11.zzbpe
            int r5 = r5.zzeem
            r6 = 23
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r6)
            r7.append(r4)
            java.lang.String r4 = "."
            r7.append(r4)
            r7.append(r5)
            java.lang.String r5 = r7.toString()
            com.google.android.gms.internal.ads.zzaqv r4 = com.google.android.gms.ads.internal.zzp.zzlg()
            android.webkit.WebView r6 = r0.getWebView()
            java.lang.String r7 = ""
            java.lang.String r8 = "javascript"
            r10 = r12
            com.google.android.gms.dynamic.IObjectWrapper r12 = r4.zza(r5, r6, r7, r8, r9, r10)
            if (r12 != 0) goto L_0x0076
            return
        L_0x0076:
            com.google.android.gms.internal.ads.zzcei r4 = r11.zzfxu
            r4.zzas(r12)
            r0.zzap(r12)
            if (r2 == 0) goto L_0x008f
            android.view.View r0 = r1.getView()
            if (r0 == 0) goto L_0x008d
            com.google.android.gms.internal.ads.zzaqv r1 = com.google.android.gms.ads.internal.zzp.zzlg()
            r1.zza(r12, r0)
        L_0x008d:
            r11.zzfzd = r3
        L_0x008f:
            if (r13 == 0) goto L_0x0098
            com.google.android.gms.internal.ads.zzaqv r13 = com.google.android.gms.ads.internal.zzp.zzlg()
            r13.zzab(r12)
        L_0x0098:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcdx.zzi(java.lang.String, boolean):void");
    }

    public final boolean zzalw() {
        return this.zzfyv.zzalw();
    }

    public final void zzaa(View view) {
        IObjectWrapper zzamh = this.zzfxu.zzamh();
        boolean z = this.zzfxu.zzamg() != null;
        if (this.zzfyv.zzalw() && zzamh != null && z && view != null) {
            zzp.zzlg().zza(zzamh, view);
        }
    }

    public final void zzab(View view) {
        IObjectWrapper zzamh = this.zzfxu.zzamh();
        if (this.zzfyv.zzalw() && zzamh != null && view != null) {
            zzp.zzlg().zzb(zzamh, view);
        }
    }

    public final zzced zzalx() {
        return this.zzfze;
    }

    public final synchronized void zza(zzyi zzyi) {
        this.zzfvj.zzb(zzyi);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzbj(boolean z) {
        this.zzfyt.zza(this.zzfzb.zzahx(), this.zzfzb.zzanc(), this.zzfzb.zzand(), z);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzaly() {
        this.zzfyt.destroy();
        this.zzfxu.destroy();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzalz() {
        try {
            int zzama = this.zzfxu.zzama();
            if (zzama != 1) {
                if (zzama != 2) {
                    if (zzama != 3) {
                        if (zzama != 6) {
                            if (zzama != 7) {
                                zzayp.zzfc("Wrong native template id!");
                            } else if (this.zzfqc.zzamt() != null) {
                                this.zzfqc.zzamt().zza(this.zzfyz.get());
                            }
                        } else if (this.zzfqc.zzamr() != null) {
                            zzi("Google", true);
                            this.zzfqc.zzamr().zza(this.zzfyy.get());
                        }
                    } else if (this.zzfqc.zzgd(this.zzfxu.getCustomTemplateId()) != null) {
                        if (this.zzfxu.zzamf() != null) {
                            zzi("Google", true);
                        }
                        this.zzfqc.zzgd(this.zzfxu.getCustomTemplateId()).zza(this.zzfza.get());
                    }
                } else if (this.zzfqc.zzamq() != null) {
                    zzi("Google", true);
                    this.zzfqc.zzamq().zza(this.zzfyx.get());
                }
            } else if (this.zzfqc.zzamp() != null) {
                zzi("Google", true);
                this.zzfqc.zzamp().zza(this.zzfyw.get());
            }
        } catch (RemoteException e) {
            zzayp.zzc("RemoteException when notifyAdLoad is called", e);
        }
    }
}
