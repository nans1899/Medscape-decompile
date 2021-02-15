package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzfi implements Application.ActivityLifecycleCallbacks, View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private static final Handler zzaah = new Handler(Looper.getMainLooper());
    private final Context zzaai;
    private final PowerManager zzaaj;
    private final KeyguardManager zzaak;
    private BroadcastReceiver zzaal;
    private WeakReference<ViewTreeObserver> zzaam;
    private WeakReference<View> zzaan;
    private zzej zzaao;
    private byte zzaap = -1;
    private int zzaaq = -1;
    private long zzaar = -3;
    private Application zzyh;
    private final zzev zzyt;

    public zzfi(Context context, zzev zzev) {
        Context applicationContext = context.getApplicationContext();
        this.zzaai = applicationContext;
        this.zzyt = zzev;
        this.zzaaj = (PowerManager) applicationContext.getSystemService("power");
        this.zzaak = (KeyguardManager) this.zzaai.getSystemService("keyguard");
        Context context2 = this.zzaai;
        if (context2 instanceof Application) {
            this.zzyh = (Application) context2;
            this.zzaao = new zzej((Application) context2, this);
        }
        zze((View) null);
    }

    /* access modifiers changed from: package-private */
    public final void zze(View view) {
        View currentView = getCurrentView();
        if (currentView != null) {
            currentView.removeOnAttachStateChangeListener(this);
            zzg(currentView);
        }
        this.zzaan = new WeakReference<>(view);
        if (view != null) {
            if ((view.getWindowToken() == null && view.getWindowVisibility() == 8) ? false : true) {
                zzf(view);
            }
            view.addOnAttachStateChangeListener(this);
            this.zzaar = -2;
            return;
        }
        this.zzaar = -3;
    }

    private final View getCurrentView() {
        WeakReference<View> weakReference = this.zzaan;
        if (weakReference != null) {
            return (View) weakReference.get();
        }
        return null;
    }

    private final void zzct() {
        zzaah.post(new zzfh(this));
    }

    public final void onViewAttachedToWindow(View view) {
        this.zzaaq = -1;
        zzf(view);
        zzcv();
    }

    public final void onViewDetachedFromWindow(View view) {
        this.zzaaq = -1;
        zzcv();
        zzct();
        zzg(view);
    }

    private final void zza(Activity activity, int i) {
        Window window;
        if (this.zzaan != null && (window = activity.getWindow()) != null) {
            View peekDecorView = window.peekDecorView();
            View currentView = getCurrentView();
            if (currentView != null && peekDecorView != null && currentView.getRootView() == peekDecorView.getRootView()) {
                this.zzaaq = i;
            }
        }
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zza(activity, 0);
        zzcv();
    }

    public final void onActivityStarted(Activity activity) {
        zza(activity, 0);
        zzcv();
    }

    public final void onActivityResumed(Activity activity) {
        zza(activity, 0);
        zzcv();
        zzct();
    }

    public final void onActivityPaused(Activity activity) {
        zza(activity, 4);
        zzcv();
    }

    public final void onActivityStopped(Activity activity) {
        zzcv();
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zzcv();
    }

    public final void onActivityDestroyed(Activity activity) {
        zzcv();
    }

    public final void onGlobalLayout() {
        zzcv();
    }

    public final void onScrollChanged() {
        zzcv();
    }

    public final long zzcu() {
        if (this.zzaar <= -2 && getCurrentView() == null) {
            this.zzaar = -3;
        }
        return this.zzaar;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0064, code lost:
        if (r7 == false) goto L_0x0067;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzcv() {
        /*
            r9 = this;
            java.lang.ref.WeakReference<android.view.View> r0 = r9.zzaan
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            android.view.View r0 = r9.getCurrentView()
            r1 = -1
            r2 = -3
            if (r0 != 0) goto L_0x0013
            r9.zzaar = r2
            r9.zzaap = r1
            return
        L_0x0013:
            int r4 = r0.getVisibility()
            r5 = 1
            r6 = 0
            if (r4 == 0) goto L_0x001d
            r4 = 1
            goto L_0x001e
        L_0x001d:
            r4 = 0
        L_0x001e:
            boolean r7 = r0.isShown()
            if (r7 != 0) goto L_0x0027
            r4 = r4 | 2
            byte r4 = (byte) r4
        L_0x0027:
            android.os.PowerManager r7 = r9.zzaaj
            if (r7 == 0) goto L_0x0034
            boolean r7 = r7.isScreenOn()
            if (r7 != 0) goto L_0x0034
            r4 = r4 | 4
            byte r4 = (byte) r4
        L_0x0034:
            com.google.android.gms.internal.ads.zzev r7 = r9.zzyt
            boolean r7 = r7.zzcb()
            if (r7 != 0) goto L_0x0068
            android.app.KeyguardManager r7 = r9.zzaak
            if (r7 == 0) goto L_0x0067
            boolean r7 = r7.inKeyguardRestrictedInputMode()
            if (r7 == 0) goto L_0x0067
            android.app.Activity r7 = com.google.android.gms.internal.ads.zzfg.zzd(r0)
            if (r7 == 0) goto L_0x0063
            android.view.Window r7 = r7.getWindow()
            if (r7 != 0) goto L_0x0054
            r7 = 0
            goto L_0x0058
        L_0x0054:
            android.view.WindowManager$LayoutParams r7 = r7.getAttributes()
        L_0x0058:
            if (r7 == 0) goto L_0x0063
            int r7 = r7.flags
            r8 = 524288(0x80000, float:7.34684E-40)
            r7 = r7 & r8
            if (r7 == 0) goto L_0x0063
            r7 = 1
            goto L_0x0064
        L_0x0063:
            r7 = 0
        L_0x0064:
            if (r7 == 0) goto L_0x0067
            goto L_0x0068
        L_0x0067:
            r5 = 0
        L_0x0068:
            if (r5 != 0) goto L_0x006d
            r4 = r4 | 8
            byte r4 = (byte) r4
        L_0x006d:
            android.graphics.Rect r5 = new android.graphics.Rect
            r5.<init>()
            boolean r5 = r0.getGlobalVisibleRect(r5)
            if (r5 != 0) goto L_0x007b
            r4 = r4 | 16
            byte r4 = (byte) r4
        L_0x007b:
            android.graphics.Rect r5 = new android.graphics.Rect
            r5.<init>()
            boolean r5 = r0.getLocalVisibleRect(r5)
            if (r5 != 0) goto L_0x0089
            r4 = r4 | 32
            byte r4 = (byte) r4
        L_0x0089:
            int r0 = r0.getWindowVisibility()
            int r5 = r9.zzaaq
            if (r5 == r1) goto L_0x0092
            r0 = r5
        L_0x0092:
            if (r0 == 0) goto L_0x0097
            r0 = r4 | 64
            byte r4 = (byte) r0
        L_0x0097:
            byte r0 = r9.zzaap
            if (r0 == r4) goto L_0x00a9
            r9.zzaap = r4
            if (r4 != 0) goto L_0x00a4
            long r0 = android.os.SystemClock.elapsedRealtime()
            goto L_0x00a7
        L_0x00a4:
            long r0 = (long) r4
            long r0 = r2 - r0
        L_0x00a7:
            r9.zzaar = r0
        L_0x00a9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfi.zzcv():void");
    }

    private final void zzf(View view) {
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            this.zzaam = new WeakReference<>(viewTreeObserver);
            viewTreeObserver.addOnScrollChangedListener(this);
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        if (this.zzaal == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            zzfk zzfk = new zzfk(this);
            this.zzaal = zzfk;
            this.zzaai.registerReceiver(zzfk, intentFilter);
        }
        Application application = this.zzyh;
        if (application != null) {
            try {
                application.registerActivityLifecycleCallbacks(this.zzaao);
            } catch (Exception unused) {
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|(3:4|(1:8)|9)|10|11|(1:13)|15|(3:17|18|19)|21|(3:23|24|26)(1:28)) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x001d */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0027 A[Catch:{ Exception -> 0x002e }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0033 A[SYNTHETIC, Splitter:B:17:0x0033] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x003e A[SYNTHETIC, Splitter:B:23:0x003e] */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzg(android.view.View r4) {
        /*
            r3 = this;
            r0 = 0
            java.lang.ref.WeakReference<android.view.ViewTreeObserver> r1 = r3.zzaam     // Catch:{ Exception -> 0x001d }
            if (r1 == 0) goto L_0x001d
            java.lang.ref.WeakReference<android.view.ViewTreeObserver> r1 = r3.zzaam     // Catch:{ Exception -> 0x001d }
            java.lang.Object r1 = r1.get()     // Catch:{ Exception -> 0x001d }
            android.view.ViewTreeObserver r1 = (android.view.ViewTreeObserver) r1     // Catch:{ Exception -> 0x001d }
            if (r1 == 0) goto L_0x001b
            boolean r2 = r1.isAlive()     // Catch:{ Exception -> 0x001d }
            if (r2 == 0) goto L_0x001b
            r1.removeOnScrollChangedListener(r3)     // Catch:{ Exception -> 0x001d }
            r1.removeGlobalOnLayoutListener(r3)     // Catch:{ Exception -> 0x001d }
        L_0x001b:
            r3.zzaam = r0     // Catch:{ Exception -> 0x001d }
        L_0x001d:
            android.view.ViewTreeObserver r4 = r4.getViewTreeObserver()     // Catch:{ Exception -> 0x002e }
            boolean r1 = r4.isAlive()     // Catch:{ Exception -> 0x002e }
            if (r1 == 0) goto L_0x002f
            r4.removeOnScrollChangedListener(r3)     // Catch:{ Exception -> 0x002e }
            r4.removeGlobalOnLayoutListener(r3)     // Catch:{ Exception -> 0x002e }
            goto L_0x002f
        L_0x002e:
        L_0x002f:
            android.content.BroadcastReceiver r4 = r3.zzaal
            if (r4 == 0) goto L_0x003a
            android.content.Context r1 = r3.zzaai     // Catch:{ Exception -> 0x0038 }
            r1.unregisterReceiver(r4)     // Catch:{ Exception -> 0x0038 }
        L_0x0038:
            r3.zzaal = r0
        L_0x003a:
            android.app.Application r4 = r3.zzyh
            if (r4 == 0) goto L_0x0043
            com.google.android.gms.internal.ads.zzej r0 = r3.zzaao     // Catch:{ Exception -> 0x0043 }
            r4.unregisterActivityLifecycleCallbacks(r0)     // Catch:{ Exception -> 0x0043 }
        L_0x0043:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfi.zzg(android.view.View):void");
    }
}
