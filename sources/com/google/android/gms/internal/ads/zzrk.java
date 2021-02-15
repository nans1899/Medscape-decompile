package com.google.android.gms.internal.ads;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzrk extends Thread {
    private final Object lock;
    private boolean started;
    private final int zzbsf;
    private final int zzbsh;
    private final boolean zzbsi;
    private boolean zzbtf;
    private boolean zzbtg;
    private final zzrd zzbth;
    private final int zzbti;
    private final int zzbtj;
    private final int zzbtk;
    private final int zzbtl;
    private final int zzbtm;
    private final int zzbtn;
    private final String zzbto;
    private final boolean zzbtp;
    private final boolean zzbtq;

    public zzrk() {
        this(new zzrd());
    }

    private zzrk(zzrd zzrd) {
        this.started = false;
        this.zzbtf = false;
        this.zzbtg = false;
        this.zzbth = zzrd;
        this.lock = new Object();
        this.zzbsf = zzacq.zzdat.get().intValue();
        this.zzbtj = zzacq.zzdaq.get().intValue();
        this.zzbsh = zzacq.zzdau.get().intValue();
        this.zzbtk = zzacq.zzdas.get().intValue();
        this.zzbtl = ((Integer) zzwm.zzpx().zzd(zzabb.zzcnd)).intValue();
        this.zzbtm = ((Integer) zzwm.zzpx().zzd(zzabb.zzcne)).intValue();
        this.zzbtn = ((Integer) zzwm.zzpx().zzd(zzabb.zzcnf)).intValue();
        this.zzbti = zzacq.zzdav.get().intValue();
        this.zzbto = (String) zzwm.zzpx().zzd(zzabb.zzcnh);
        this.zzbtp = ((Boolean) zzwm.zzpx().zzd(zzabb.zzcni)).booleanValue();
        this.zzbsi = ((Boolean) zzwm.zzpx().zzd(zzabb.zzcnj)).booleanValue();
        this.zzbtq = ((Boolean) zzwm.zzpx().zzd(zzabb.zzcnk)).booleanValue();
        setName("ContentFetchTask");
    }

    public final void zzmi() {
        synchronized (this.lock) {
            if (this.started) {
                zzayp.zzef("Content hash thread already started, quiting...");
                return;
            }
            this.started = true;
            start();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        com.google.android.gms.ads.internal.zzp.zzkv().zza(r0, "ContentFetchTask.extractContent");
        com.google.android.gms.internal.ads.zzayp.zzef("Failed getting root view of activity. Content not extracted.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007c, code lost:
        com.google.android.gms.internal.ads.zzayp.zzc("Error in ContentFetchTask", r0);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:30:0x0084 */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x007b A[ExcHandler: InterruptedException (r0v1 'e' java.lang.InterruptedException A[CUSTOM_DECLARE]), Splitter:B:0:0x0000] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0084 A[LOOP:1: B:30:0x0084->B:42:0x0084, LOOP_START, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r4 = this;
        L_0x0000:
            boolean r0 = zzmj()     // Catch:{ InterruptedException -> 0x007b, Exception -> 0x006b }
            if (r0 == 0) goto L_0x005a
            com.google.android.gms.internal.ads.zzrg r0 = com.google.android.gms.ads.internal.zzp.zzku()     // Catch:{ InterruptedException -> 0x007b, Exception -> 0x006b }
            android.app.Activity r0 = r0.getActivity()     // Catch:{ InterruptedException -> 0x007b, Exception -> 0x006b }
            if (r0 != 0) goto L_0x0019
            java.lang.String r0 = "ContentFetchThread: no activity. Sleeping."
            com.google.android.gms.internal.ads.zzayp.zzef(r0)     // Catch:{ InterruptedException -> 0x007b, Exception -> 0x006b }
            r4.zzml()     // Catch:{ InterruptedException -> 0x007b, Exception -> 0x006b }
            goto L_0x0062
        L_0x0019:
            if (r0 == 0) goto L_0x0062
            r1 = 0
            android.view.Window r2 = r0.getWindow()     // Catch:{ Exception -> 0x003d, InterruptedException -> 0x007b }
            if (r2 == 0) goto L_0x004c
            android.view.Window r2 = r0.getWindow()     // Catch:{ Exception -> 0x003d, InterruptedException -> 0x007b }
            android.view.View r2 = r2.getDecorView()     // Catch:{ Exception -> 0x003d, InterruptedException -> 0x007b }
            if (r2 == 0) goto L_0x004c
            android.view.Window r0 = r0.getWindow()     // Catch:{ Exception -> 0x003d, InterruptedException -> 0x007b }
            android.view.View r0 = r0.getDecorView()     // Catch:{ Exception -> 0x003d, InterruptedException -> 0x007b }
            r2 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r0 = r0.findViewById(r2)     // Catch:{ Exception -> 0x003d, InterruptedException -> 0x007b }
            r1 = r0
            goto L_0x004c
        L_0x003d:
            r0 = move-exception
            com.google.android.gms.internal.ads.zzayb r2 = com.google.android.gms.ads.internal.zzp.zzkv()     // Catch:{ InterruptedException -> 0x007b, Exception -> 0x006b }
            java.lang.String r3 = "ContentFetchTask.extractContent"
            r2.zza(r0, r3)     // Catch:{ InterruptedException -> 0x007b, Exception -> 0x006b }
            java.lang.String r0 = "Failed getting root view of activity. Content not extracted."
            com.google.android.gms.internal.ads.zzayp.zzef(r0)     // Catch:{ InterruptedException -> 0x007b, Exception -> 0x006b }
        L_0x004c:
            if (r1 == 0) goto L_0x0062
            if (r1 != 0) goto L_0x0051
            goto L_0x0062
        L_0x0051:
            com.google.android.gms.internal.ads.zzrj r0 = new com.google.android.gms.internal.ads.zzrj     // Catch:{ InterruptedException -> 0x007b, Exception -> 0x006b }
            r0.<init>(r4, r1)     // Catch:{ InterruptedException -> 0x007b, Exception -> 0x006b }
            r1.post(r0)     // Catch:{ InterruptedException -> 0x007b, Exception -> 0x006b }
            goto L_0x0062
        L_0x005a:
            java.lang.String r0 = "ContentFetchTask: sleeping"
            com.google.android.gms.internal.ads.zzayp.zzef(r0)     // Catch:{ InterruptedException -> 0x007b, Exception -> 0x006b }
            r4.zzml()     // Catch:{ InterruptedException -> 0x007b, Exception -> 0x006b }
        L_0x0062:
            int r0 = r4.zzbti     // Catch:{ InterruptedException -> 0x007b, Exception -> 0x006b }
            int r0 = r0 * 1000
            long r0 = (long) r0     // Catch:{ InterruptedException -> 0x007b, Exception -> 0x006b }
            java.lang.Thread.sleep(r0)     // Catch:{ InterruptedException -> 0x007b, Exception -> 0x006b }
            goto L_0x0081
        L_0x006b:
            r0 = move-exception
            java.lang.String r1 = "Error in ContentFetchTask"
            com.google.android.gms.internal.ads.zzayp.zzc(r1, r0)
            com.google.android.gms.internal.ads.zzayb r1 = com.google.android.gms.ads.internal.zzp.zzkv()
            java.lang.String r2 = "ContentFetchTask.run"
            r1.zza(r0, r2)
            goto L_0x0081
        L_0x007b:
            r0 = move-exception
            java.lang.String r1 = "Error in ContentFetchTask"
            com.google.android.gms.internal.ads.zzayp.zzc(r1, r0)
        L_0x0081:
            java.lang.Object r0 = r4.lock
            monitor-enter(r0)
        L_0x0084:
            boolean r1 = r4.zzbtf     // Catch:{ all -> 0x0096 }
            if (r1 == 0) goto L_0x0093
            java.lang.String r1 = "ContentFetchTask: waiting"
            com.google.android.gms.internal.ads.zzayp.zzef(r1)     // Catch:{ InterruptedException -> 0x0084 }
            java.lang.Object r1 = r4.lock     // Catch:{ InterruptedException -> 0x0084 }
            r1.wait()     // Catch:{ InterruptedException -> 0x0084 }
            goto L_0x0084
        L_0x0093:
            monitor-exit(r0)     // Catch:{ all -> 0x0096 }
            goto L_0x0000
        L_0x0096:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0096 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzrk.run():void");
    }

    /* access modifiers changed from: package-private */
    public final void zzj(View view) {
        try {
            zzre zzre = new zzre(this.zzbsf, this.zzbtj, this.zzbsh, this.zzbtk, this.zzbtl, this.zzbtm, this.zzbtn, this.zzbsi);
            Context context = zzp.zzku().getContext();
            if (context != null && !TextUtils.isEmpty(this.zzbto)) {
                String str = (String) view.getTag(context.getResources().getIdentifier((String) zzwm.zzpx().zzd(zzabb.zzcng), "id", context.getPackageName()));
                if (str != null && str.equals(this.zzbto)) {
                    return;
                }
            }
            zzro zza = zza(view, zzre);
            zzre.zzmg();
            if (zza.zzbtx != 0 || zza.zzbty != 0) {
                if (zza.zzbty != 0 || zzre.zzmh() != 0) {
                    if (zza.zzbty != 0 || !this.zzbth.zza(zzre)) {
                        this.zzbth.zzc(zzre);
                    }
                }
            }
        } catch (Exception e) {
            zzayp.zzc("Exception in fetchContentOnUIThread", e);
            zzp.zzkv().zza(e, "ContentFetchTask.fetchContent");
        }
    }

    private static boolean zzmj() {
        boolean z;
        try {
            Context context = zzp.zzku().getContext();
            if (context == null) {
                return false;
            }
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (activityManager == null) {
                return false;
            }
            if (keyguardManager == null) {
                return false;
            }
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return false;
            }
            for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                if (Process.myPid() == next.pid) {
                    if (next.importance != 100 || keyguardManager.inKeyguardRestrictedInputMode()) {
                        return false;
                    }
                    PowerManager powerManager = (PowerManager) context.getSystemService("power");
                    if (powerManager == null) {
                        z = false;
                    } else {
                        z = powerManager.isScreenOn();
                    }
                    if (z) {
                        return true;
                    }
                    return false;
                }
            }
            return false;
        } catch (Throwable th) {
            zzp.zzkv().zza(th, "ContentFetchTask.isInForeground");
            return false;
        }
    }

    private final zzro zza(View view, zzre zzre) {
        boolean z;
        if (view == null) {
            return new zzro(this, 0, 0);
        }
        boolean globalVisibleRect = view.getGlobalVisibleRect(new Rect());
        if ((view instanceof TextView) && !(view instanceof EditText)) {
            CharSequence text = ((TextView) view).getText();
            if (TextUtils.isEmpty(text)) {
                return new zzro(this, 0, 0);
            }
            zzre.zzb(text.toString(), globalVisibleRect, view.getX(), view.getY(), (float) view.getWidth(), (float) view.getHeight());
            return new zzro(this, 1, 0);
        } else if ((view instanceof WebView) && !(view instanceof zzbgj)) {
            WebView webView = (WebView) view;
            if (!PlatformVersion.isAtLeastKitKat()) {
                z = false;
            } else {
                zzre.zzme();
                webView.post(new zzrm(this, zzre, webView, globalVisibleRect));
                z = true;
            }
            if (z) {
                return new zzro(this, 0, 1);
            }
            return new zzro(this, 0, 0);
        } else if (!(view instanceof ViewGroup)) {
            return new zzro(this, 0, 0);
        } else {
            ViewGroup viewGroup = (ViewGroup) view;
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                zzro zza = zza(viewGroup.getChildAt(i3), zzre);
                i += zza.zzbtx;
                i2 += zza.zzbty;
            }
            return new zzro(this, i, i2);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzre zzre, WebView webView, String str, boolean z) {
        zzre.zzmd();
        try {
            if (!TextUtils.isEmpty(str)) {
                String optString = new JSONObject(str).optString("text");
                if (this.zzbtp || TextUtils.isEmpty(webView.getTitle())) {
                    zzre.zza(optString, z, webView.getX(), webView.getY(), (float) webView.getWidth(), (float) webView.getHeight());
                } else {
                    String title = webView.getTitle();
                    StringBuilder sb = new StringBuilder(String.valueOf(title).length() + 1 + String.valueOf(optString).length());
                    sb.append(title);
                    sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                    sb.append(optString);
                    zzre.zza(sb.toString(), z, webView.getX(), webView.getY(), (float) webView.getWidth(), (float) webView.getHeight());
                }
            }
            if (zzre.zzly()) {
                this.zzbth.zzb(zzre);
            }
        } catch (JSONException unused) {
            zzayp.zzef("Json string may be malformed.");
        } catch (Throwable th) {
            zzayp.zzb("Failed to get webview content.", th);
            zzp.zzkv().zza(th, "ContentFetchTask.processWebViewContent");
        }
    }

    public final zzre zzmk() {
        return this.zzbth.zzo(this.zzbtq);
    }

    public final void wakeup() {
        synchronized (this.lock) {
            this.zzbtf = false;
            this.lock.notifyAll();
            zzayp.zzef("ContentFetchThread: wakeup");
        }
    }

    private final void zzml() {
        synchronized (this.lock) {
            this.zzbtf = true;
            StringBuilder sb = new StringBuilder(42);
            sb.append("ContentFetchThread: paused, mPause = ");
            sb.append(true);
            zzayp.zzef(sb.toString());
        }
    }

    public final boolean zzmm() {
        return this.zzbtf;
    }
}
