package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.internal.ads.zzby;
import com.google.android.gms.internal.ads.zzcf;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Iterator;
import java.util.LinkedList;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public abstract class zzdv implements zzdw {
    protected static volatile zzex zzwg;
    protected MotionEvent zzwl;
    protected LinkedList<MotionEvent> zzwm = new LinkedList<>();
    protected long zzwn = 0;
    protected long zzwo = 0;
    protected long zzwp = 0;
    protected long zzwq = 0;
    protected long zzwr = 0;
    protected long zzws = 0;
    protected long zzwt = 0;
    protected double zzwu;
    private double zzwv;
    private double zzww;
    protected float zzwx;
    protected float zzwy;
    protected float zzwz;
    protected float zzxa;
    private boolean zzxb = false;
    protected boolean zzxc = false;
    protected DisplayMetrics zzxd;

    protected zzdv(Context context) {
        try {
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcre)).booleanValue()) {
                zzcx.zzbo();
            } else {
                zzfe.zzb(zzwg);
            }
            this.zzxd = context.getResources().getDisplayMetrics();
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    public abstract long zza(StackTraceElement[] stackTraceElementArr) throws zzeu;

    /* access modifiers changed from: protected */
    public abstract zzcf.zza.C0009zza zza(Context context, zzby.zza zza);

    /* access modifiers changed from: protected */
    public abstract zzcf.zza.C0009zza zzb(Context context, View view, Activity activity);

    /* access modifiers changed from: protected */
    public abstract zzfd zzb(MotionEvent motionEvent) throws zzeu;

    public void zzb(View view) {
    }

    /* access modifiers changed from: protected */
    public abstract zzcf.zza.C0009zza zzc(Context context, View view, Activity activity);

    public String zzb(Context context) {
        if (!zzfg.isMainThread()) {
            return zza(context, (String) null, zzcw.zznn, (View) null, (Activity) null, (byte[]) null);
        }
        throw new IllegalStateException("The caller must not be called from the UI thread.");
    }

    public String zza(Context context, View view, Activity activity) {
        return zza(context, (String) null, zzcw.zzno, view, activity, (byte[]) null);
    }

    public final String zza(Context context, String str, View view) {
        return zza(context, str, view, (Activity) null);
    }

    public String zza(Context context, String str, View view, Activity activity) {
        return zza(context, str, zzcw.zznp, view, activity, (byte[]) null);
    }

    public void zza(MotionEvent motionEvent) {
        boolean z = false;
        if (this.zzxb) {
            zzbx();
            this.zzxb = false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.zzwu = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            this.zzwv = (double) motionEvent.getRawX();
            this.zzww = (double) motionEvent.getRawY();
        } else if (action == 1 || action == 2) {
            double rawX = (double) motionEvent.getRawX();
            double rawY = (double) motionEvent.getRawY();
            double d = rawX - this.zzwv;
            double d2 = rawY - this.zzww;
            this.zzwu += Math.sqrt((d * d) + (d2 * d2));
            this.zzwv = rawX;
            this.zzww = rawY;
        }
        int action2 = motionEvent.getAction();
        if (action2 == 0) {
            this.zzwx = motionEvent.getX();
            this.zzwy = motionEvent.getY();
            this.zzwz = motionEvent.getRawX();
            this.zzxa = motionEvent.getRawY();
            this.zzwn++;
        } else if (action2 == 1) {
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            this.zzwl = obtain;
            this.zzwm.add(obtain);
            if (this.zzwm.size() > 6) {
                this.zzwm.remove().recycle();
            }
            this.zzwp++;
            this.zzwr = zza(new Throwable().getStackTrace());
        } else if (action2 == 2) {
            this.zzwo += (long) (motionEvent.getHistorySize() + 1);
            try {
                zzfd zzb = zzb(motionEvent);
                if ((zzb == null || zzb.zzzs == null || zzb.zzzv == null) ? false : true) {
                    this.zzws += zzb.zzzs.longValue() + zzb.zzzv.longValue();
                }
                if (!(this.zzxd == null || zzb == null || zzb.zzzt == null || zzb.zzzw == null)) {
                    z = true;
                }
                if (z) {
                    this.zzwt += zzb.zzzt.longValue() + zzb.zzzw.longValue();
                }
            } catch (zzeu unused) {
            }
        } else if (action2 == 3) {
            this.zzwq++;
        }
        this.zzxc = true;
    }

    public void zza(int i, int i2, int i3) {
        if (this.zzwl != null) {
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcqr)).booleanValue()) {
                zzbx();
            } else {
                this.zzwl.recycle();
            }
        }
        DisplayMetrics displayMetrics = this.zzxd;
        if (displayMetrics != null) {
            this.zzwl = MotionEvent.obtain(0, (long) i3, 1, ((float) i) * displayMetrics.density, this.zzxd.density * ((float) i2), 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
        } else {
            this.zzwl = null;
        }
        this.zzxc = false;
    }

    private final void zzbx() {
        this.zzwr = 0;
        this.zzwn = 0;
        this.zzwo = 0;
        this.zzwp = 0;
        this.zzwq = 0;
        this.zzws = 0;
        this.zzwt = 0;
        if (this.zzwm.size() > 0) {
            Iterator it = this.zzwm.iterator();
            while (it.hasNext()) {
                ((MotionEvent) it.next()).recycle();
            }
            this.zzwm.clear();
        } else {
            MotionEvent motionEvent = this.zzwl;
            if (motionEvent != null) {
                motionEvent.recycle();
            }
        }
        this.zzwl = null;
    }

    private final String zza(Context context, String str, int i, View view, Activity activity, byte[] bArr) {
        String str2;
        zzdu zzdu;
        int i2;
        int i3;
        int i4;
        int i5;
        Context context2 = context;
        int i6 = i;
        View view2 = view;
        Activity activity2 = activity;
        long currentTimeMillis = System.currentTimeMillis();
        boolean booleanValue = ((Boolean) zzwm.zzpx().zzd(zzabb.zzcqt)).booleanValue();
        zzcf.zza.C0009zza zza = null;
        if (booleanValue) {
            zzdu = zzwg != null ? zzwg.zzcl() : null;
            str2 = ((Boolean) zzwm.zzpx().zzd(zzabb.zzcre)).booleanValue() ? "be" : "te";
        } else {
            zzdu = null;
            str2 = null;
        }
        try {
            if (i6 == zzcw.zznp) {
                zza = zzb(context2, view2, activity2);
                this.zzxb = true;
                i5 = 1002;
            } else if (i6 == zzcw.zzno) {
                zza = zzc(context2, view2, activity2);
                i5 = 1008;
            } else {
                zza = zza(context2, (zzby.zza) null);
                i5 = 1000;
            }
            if (booleanValue && zzdu != null) {
                zzdu.zza(i5, -1, System.currentTimeMillis() - currentTimeMillis, str2);
            }
        } catch (Exception e) {
            Exception exc = e;
            if (booleanValue && zzdu != null) {
                if (i6 == zzcw.zznp) {
                    i4 = 1003;
                } else if (i6 == zzcw.zzno) {
                    i4 = 1009;
                } else {
                    i4 = i6 == zzcw.zznn ? 1001 : -1;
                }
                zzdu.zza(i4, -1, System.currentTimeMillis() - currentTimeMillis, str2, exc);
            }
        }
        long currentTimeMillis2 = System.currentTimeMillis();
        if (zza != null) {
            try {
                if (((zzcf.zza) ((zzejz) zza.zzbgt())).zzbgh() != 0) {
                    String zzj = zzcx.zzj((zzcf.zza) ((zzejz) zza.zzbgt()), str);
                    if (!booleanValue || zzdu == null) {
                        return zzj;
                    }
                    if (i6 == zzcw.zznp) {
                        i3 = 1006;
                    } else if (i6 == zzcw.zzno) {
                        i3 = 1010;
                    } else {
                        i3 = i6 == zzcw.zznn ? 1004 : -1;
                    }
                    zzdu.zza(i3, -1, System.currentTimeMillis() - currentTimeMillis2, str2);
                    return zzj;
                }
            } catch (Exception e2) {
                Exception exc2 = e2;
                String num = Integer.toString(7);
                if (!booleanValue || zzdu == null) {
                    return num;
                }
                if (i6 == zzcw.zznp) {
                    i2 = 1007;
                } else if (i6 == zzcw.zzno) {
                    i2 = 1011;
                } else {
                    i2 = i6 == zzcw.zznn ? 1005 : -1;
                }
                zzdu.zza(i2, -1, System.currentTimeMillis() - currentTimeMillis2, str2, exc2);
                return num;
            }
        }
        return Integer.toString(5);
    }
}
