package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.os.Process;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import androidx.browser.customtabs.CustomTabsIntent;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.ads.impl.R;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.common.net.HttpHeaders;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzayu {
    public static final zzdvo zzeba = new zzayo(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public String zzbij;
    private AtomicReference<Pattern> zzeay = new AtomicReference<>((Object) null);
    private AtomicReference<Pattern> zzeaz = new AtomicReference<>((Object) null);
    /* access modifiers changed from: private */
    public final Object zzebb = new Object();
    private boolean zzebc = false;
    private boolean zzebd = false;
    /* access modifiers changed from: private */
    public boolean zzyq = true;

    public final void zza(Context context, String str, boolean z, HttpURLConnection httpURLConnection) {
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setReadTimeout(60000);
        httpURLConnection.setRequestProperty("User-Agent", zzs(context, str));
        httpURLConnection.setUseCaches(false);
    }

    public static boolean zzr(Context context, String str) {
        Context zzab = zzatx.zzab(context);
        return Wrappers.packageManager(zzab).checkPermission(str, zzab.getPackageName()) == 0;
    }

    public static void zzb(Context context, String str, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str2);
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            new zzbax(context, str, (String) obj).zzwz();
        }
    }

    public static String zza(InputStreamReader inputStreamReader) throws IOException {
        StringBuilder sb = new StringBuilder(8192);
        char[] cArr = new char[2048];
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (read == -1) {
                return sb.toString();
            }
            sb.append(cArr, 0, read);
        }
    }

    public final boolean zzap(Context context) {
        if (this.zzebc) {
            return false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        context.getApplicationContext().registerReceiver(new zzaza(this, (zzayw) null), intentFilter);
        this.zzebc = true;
        return true;
    }

    public final boolean zzaq(Context context) {
        if (this.zzebd) {
            return false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.google.android.ads.intent.DEBUG_LOGGING_ENABLEMENT_CHANGED");
        context.getApplicationContext().registerReceiver(new zzazb(this, (zzayw) null), intentFilter);
        this.zzebd = true;
        return true;
    }

    public final void zza(Context context, String str, WebSettings webSettings) {
        webSettings.setUserAgentString(zzs(context, str));
    }

    private static String zzxr() {
        StringBuilder sb = new StringBuilder(256);
        sb.append("Mozilla/5.0 (Linux; U; Android");
        if (Build.VERSION.RELEASE != null) {
            sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb.append(Build.VERSION.RELEASE);
        }
        sb.append("; ");
        sb.append(Locale.getDefault());
        if (Build.DEVICE != null) {
            sb.append("; ");
            sb.append(Build.DEVICE);
            if (Build.DISPLAY != null) {
                sb.append(" Build/");
                sb.append(Build.DISPLAY);
            }
        }
        sb.append(") AppleWebKit/533 Version/4.0 Safari/533");
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:47:0x003b, code lost:
        continue;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x001d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x0045 */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0025 A[Catch:{ Exception -> 0x00b3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0057 A[Catch:{ Exception -> 0x00b3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x005c A[Catch:{ Exception -> 0x00b3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a4 A[Catch:{ Exception -> 0x00b3 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzs(android.content.Context r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.zzebb
            monitor-enter(r0)
            java.lang.String r1 = r4.zzbij     // Catch:{ all -> 0x00cd }
            if (r1 == 0) goto L_0x000b
            java.lang.String r5 = r4.zzbij     // Catch:{ all -> 0x00cd }
            monitor-exit(r0)     // Catch:{ all -> 0x00cd }
            return r5
        L_0x000b:
            if (r6 != 0) goto L_0x0013
            java.lang.String r5 = zzxr()     // Catch:{ all -> 0x00cd }
            monitor-exit(r0)     // Catch:{ all -> 0x00cd }
            return r5
        L_0x0013:
            com.google.android.gms.internal.ads.zzazd r1 = com.google.android.gms.ads.internal.zzp.zzkt()     // Catch:{ Exception -> 0x001d }
            java.lang.String r1 = r1.getDefaultUserAgent(r5)     // Catch:{ Exception -> 0x001d }
            r4.zzbij = r1     // Catch:{ Exception -> 0x001d }
        L_0x001d:
            java.lang.String r1 = r4.zzbij     // Catch:{ all -> 0x00cd }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x00cd }
            if (r1 == 0) goto L_0x006b
            com.google.android.gms.internal.ads.zzwm.zzpt()     // Catch:{ all -> 0x00cd }
            boolean r1 = com.google.android.gms.internal.ads.zzbbg.zzyn()     // Catch:{ all -> 0x00cd }
            if (r1 != 0) goto L_0x0065
            r1 = 0
            r4.zzbij = r1     // Catch:{ all -> 0x00cd }
            com.google.android.gms.internal.ads.zzdvo r1 = zzeba     // Catch:{ all -> 0x00cd }
            com.google.android.gms.internal.ads.zzayz r2 = new com.google.android.gms.internal.ads.zzayz     // Catch:{ all -> 0x00cd }
            r2.<init>(r4, r5)     // Catch:{ all -> 0x00cd }
            r1.post(r2)     // Catch:{ all -> 0x00cd }
        L_0x003b:
            java.lang.String r1 = r4.zzbij     // Catch:{ all -> 0x00cd }
            if (r1 != 0) goto L_0x006b
            java.lang.Object r1 = r4.zzebb     // Catch:{ InterruptedException -> 0x0045 }
            r1.wait()     // Catch:{ InterruptedException -> 0x0045 }
            goto L_0x003b
        L_0x0045:
            java.lang.String r1 = zzxr()     // Catch:{ all -> 0x00cd }
            r4.zzbij = r1     // Catch:{ all -> 0x00cd }
            java.lang.String r2 = "Interrupted, use default user agent: "
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x00cd }
            int r3 = r1.length()     // Catch:{ all -> 0x00cd }
            if (r3 == 0) goto L_0x005c
            java.lang.String r1 = r2.concat(r1)     // Catch:{ all -> 0x00cd }
            goto L_0x0061
        L_0x005c:
            java.lang.String r1 = new java.lang.String     // Catch:{ all -> 0x00cd }
            r1.<init>(r2)     // Catch:{ all -> 0x00cd }
        L_0x0061:
            com.google.android.gms.internal.ads.zzayp.zzfe(r1)     // Catch:{ all -> 0x00cd }
            goto L_0x003b
        L_0x0065:
            java.lang.String r1 = zzar(r5)     // Catch:{ all -> 0x00cd }
            r4.zzbij = r1     // Catch:{ all -> 0x00cd }
        L_0x006b:
            java.lang.String r1 = r4.zzbij     // Catch:{ all -> 0x00cd }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x00cd }
            java.lang.String r2 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x00cd }
            int r2 = r2.length()     // Catch:{ all -> 0x00cd }
            int r2 = r2 + 10
            java.lang.String r3 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x00cd }
            int r3 = r3.length()     // Catch:{ all -> 0x00cd }
            int r2 = r2 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00cd }
            r3.<init>(r2)     // Catch:{ all -> 0x00cd }
            r3.append(r1)     // Catch:{ all -> 0x00cd }
            java.lang.String r1 = " (Mobile; "
            r3.append(r1)     // Catch:{ all -> 0x00cd }
            r3.append(r6)     // Catch:{ all -> 0x00cd }
            java.lang.String r6 = r3.toString()     // Catch:{ all -> 0x00cd }
            r4.zzbij = r6     // Catch:{ all -> 0x00cd }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r5 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r5)     // Catch:{ Exception -> 0x00b3 }
            boolean r5 = r5.isCallerInstantApp()     // Catch:{ Exception -> 0x00b3 }
            if (r5 == 0) goto L_0x00bd
            java.lang.String r5 = r4.zzbij     // Catch:{ Exception -> 0x00b3 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ Exception -> 0x00b3 }
            java.lang.String r6 = ";aia"
            java.lang.String r5 = r5.concat(r6)     // Catch:{ Exception -> 0x00b3 }
            r4.zzbij = r5     // Catch:{ Exception -> 0x00b3 }
            goto L_0x00bd
        L_0x00b3:
            r5 = move-exception
            com.google.android.gms.internal.ads.zzayb r6 = com.google.android.gms.ads.internal.zzp.zzkv()     // Catch:{ all -> 0x00cd }
            java.lang.String r1 = "AdUtil.getUserAgent"
            r6.zza(r5, r1)     // Catch:{ all -> 0x00cd }
        L_0x00bd:
            java.lang.String r5 = r4.zzbij     // Catch:{ all -> 0x00cd }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x00cd }
            java.lang.String r6 = ")"
            java.lang.String r5 = r5.concat(r6)     // Catch:{ all -> 0x00cd }
            r4.zzbij = r5     // Catch:{ all -> 0x00cd }
            monitor-exit(r0)     // Catch:{ all -> 0x00cd }
            return r5
        L_0x00cd:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00cd }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzayu.zzs(android.content.Context, java.lang.String):java.lang.String");
    }

    protected static String zzar(Context context) {
        try {
            return new WebView(context).getSettings().getUserAgentString();
        } catch (Throwable unused) {
            return zzxr();
        }
    }

    public final JSONObject zzj(Map<String, ?> map) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            for (String next : map.keySet()) {
                zza(jSONObject, next, (Object) map.get(next));
            }
            return jSONObject;
        } catch (ClassCastException e) {
            String valueOf = String.valueOf(e.getMessage());
            throw new JSONException(valueOf.length() != 0 ? "Could not convert map to JSON: ".concat(valueOf) : new String("Could not convert map to JSON: "));
        }
    }

    public final JSONObject zza(Bundle bundle, JSONObject jSONObject) {
        if (bundle != null) {
            try {
                return zzd(bundle);
            } catch (JSONException e) {
                zzayp.zzc("Error converting Bundle to JSON", e);
            }
        }
        return null;
    }

    public final JSONObject zzd(Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            zza(jSONObject, str, bundle.get(str));
        }
        return jSONObject;
    }

    private final JSONArray zza(Collection<?> collection) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (Object zza : collection) {
            zza(jSONArray, (Object) zza);
        }
        return jSONArray;
    }

    private final void zza(JSONArray jSONArray, Object obj) throws JSONException {
        if (obj instanceof Bundle) {
            jSONArray.put(zzd((Bundle) obj));
        } else if (obj instanceof Map) {
            jSONArray.put(zzj((Map<String, ?>) (Map) obj));
        } else if (obj instanceof Collection) {
            jSONArray.put(zza((Collection<?>) (Collection) obj));
        } else if (obj instanceof Object[]) {
            JSONArray jSONArray2 = new JSONArray();
            for (Object zza : (Object[]) obj) {
                zza(jSONArray2, zza);
            }
            jSONArray.put(jSONArray2);
        } else {
            jSONArray.put(obj);
        }
    }

    private final void zza(JSONObject jSONObject, String str, Object obj) throws JSONException {
        if (obj instanceof Bundle) {
            jSONObject.put(str, zzd((Bundle) obj));
        } else if (obj instanceof Map) {
            jSONObject.put(str, zzj((Map<String, ?>) (Map) obj));
        } else if (obj instanceof Collection) {
            if (str == null) {
                str = "null";
            }
            jSONObject.put(str, zza((Collection<?>) (Collection) obj));
        } else if (obj instanceof Object[]) {
            jSONObject.put(str, zza((Collection<?>) Arrays.asList((Object[]) obj)));
        } else {
            jSONObject.put(str, obj);
        }
    }

    private static int zza(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        char c2 = 'A';
        if (c < 'A' || c > 'F') {
            c2 = 'a';
            if (c < 'a' || c > 'f') {
                throw new IllegalArgumentException("Invalid Hex.");
            }
        }
        return (c - c2) + 10;
    }

    public final zzdzc<Map<String, String>> zzi(Uri uri) {
        return zzdyq.zzb(zzdyq.zzaf(null), new zzayx(this, uri), (Executor) zzbbz.zzeep);
    }

    public static Map<String, String> zzj(Uri uri) {
        if (!zzacs.zzdaz.get().booleanValue()) {
            if (uri == null) {
                return null;
            }
            HashMap hashMap = new HashMap();
            zzp.zzkt();
            for (String next : uri.getQueryParameterNames()) {
                hashMap.put(next, uri.getQueryParameter(next));
            }
            return hashMap;
        } else if (uri == null) {
            return null;
        } else {
            HashMap hashMap2 = new HashMap(20);
            String encodedQuery = uri.getEncodedQuery();
            if (encodedQuery == null) {
                return hashMap2;
            }
            int length = encodedQuery.length();
            StringBuilder sb = new StringBuilder(100);
            StringBuilder sb2 = new StringBuilder(length);
            StringBuilder sb3 = sb;
            int i = 0;
            while (i < length) {
                char charAt = encodedQuery.charAt(i);
                if (charAt == '%') {
                    byte[] bArr = new byte[((length - i) / 3)];
                    int i2 = 0;
                    while (i < length - 2 && charAt == '%') {
                        try {
                            bArr[i2] = (byte) ((zza(encodedQuery.charAt(i + 1)) << 4) + zza(encodedQuery.charAt(i + 2)));
                            i2++;
                        } catch (IllegalArgumentException unused) {
                        }
                        i += 3;
                        if (i < length) {
                            charAt = encodedQuery.charAt(i);
                        }
                    }
                    try {
                        sb3.append(new String(bArr, 0, i2, "UTF-8"));
                    } catch (UnsupportedEncodingException unused2) {
                    }
                    if (charAt != '%') {
                    }
                } else if (charAt == '&') {
                    if (sb.length() > 0 && sb3 != sb) {
                        hashMap2.put(sb.toString(), sb2.toString());
                        sb.setLength(0);
                        sb2.setLength(0);
                    }
                    sb3 = sb;
                } else if (charAt == '+') {
                    sb3.append(' ');
                } else if (charAt != '=') {
                    sb3.append(charAt);
                } else if (sb3 != sb2) {
                    sb3 = sb2;
                } else {
                    sb3.append(charAt);
                }
                i++;
            }
            if (sb.length() > 0 && sb3 != sb) {
                hashMap2.put(sb.toString(), sb2.toString());
            }
            return hashMap2;
        }
    }

    public static String zzxs() {
        return UUID.randomUUID().toString();
    }

    public static int zzen(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 22);
            sb.append("Could not parse value:");
            sb.append(valueOf);
            zzayp.zzfe(sb.toString());
            return 0;
        }
    }

    public static String zzxt() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (str2.startsWith(str)) {
            return str2;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(str2).length());
        sb.append(str);
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(str2);
        return sb.toString();
    }

    private static int[] zzxu() {
        return new int[]{0, 0};
    }

    public static int[] zzd(Activity activity) {
        View findViewById;
        Window window = activity.getWindow();
        if (window == null || (findViewById = window.findViewById(16908290)) == null) {
            return zzxu();
        }
        return new int[]{findViewById.getWidth(), findViewById.getHeight()};
    }

    public final int[] zze(Activity activity) {
        int[] zzd = zzd(activity);
        return new int[]{zzwm.zzpt().zzb((Context) activity, zzd[0]), zzwm.zzpt().zzb((Context) activity, zzd[1])};
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0009, code lost:
        r0 = r0.findViewById(16908290);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int[] zzf(android.app.Activity r7) {
        /*
            r6 = this;
            android.view.Window r0 = r7.getWindow()
            r1 = 2
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L_0x0021
            r4 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r0 = r0.findViewById(r4)
            if (r0 == 0) goto L_0x0021
            int[] r4 = new int[r1]
            int r5 = r0.getTop()
            r4[r3] = r5
            int r0 = r0.getBottom()
            r4[r2] = r0
            goto L_0x0025
        L_0x0021:
            int[] r4 = zzxu()
        L_0x0025:
            int[] r0 = new int[r1]
            com.google.android.gms.internal.ads.zzbbg r1 = com.google.android.gms.internal.ads.zzwm.zzpt()
            r5 = r4[r3]
            int r1 = r1.zzb((android.content.Context) r7, (int) r5)
            r0[r3] = r1
            com.google.android.gms.internal.ads.zzbbg r1 = com.google.android.gms.internal.ads.zzwm.zzpt()
            r3 = r4[r2]
            int r7 = r1.zzb((android.content.Context) r7, (int) r3)
            r0[r2] = r7
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzayu.zzf(android.app.Activity):int[]");
    }

    public static boolean zzeo(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|webp))$)");
    }

    public static DisplayMetrics zza(WindowManager windowManager) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static AlertDialog.Builder zzas(Context context) {
        return new AlertDialog.Builder(context);
    }

    public static zzaam zzat(Context context) {
        return new zzaam(context);
    }

    public static Bitmap zzm(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap createBitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return createBitmap;
    }

    public static PopupWindow zza(View view, int i, int i2, boolean z) {
        return new PopupWindow(view, i, i2, false);
    }

    public static String zzau(Context context) {
        List<ActivityManager.RunningTaskInfo> runningTasks;
        ActivityManager.RunningTaskInfo runningTaskInfo;
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (!(activityManager == null || (runningTasks = activityManager.getRunningTasks(1)) == null || runningTasks.isEmpty() || (runningTaskInfo = runningTasks.get(0)) == null || runningTaskInfo.topActivity == null)) {
                return runningTaskInfo.topActivity.getClassName();
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static boolean zzav(Context context) {
        boolean z;
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (activityManager != null) {
                if (keyguardManager != null) {
                    List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
                    if (runningAppProcesses == null) {
                        return false;
                    }
                    for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                        if (Process.myPid() == next.pid) {
                            if (next.importance != 100 || keyguardManager.inKeyguardRestrictedInputMode()) {
                                return true;
                            }
                            PowerManager powerManager = (PowerManager) context.getSystemService("power");
                            if (powerManager == null) {
                                z = false;
                            } else {
                                z = powerManager.isScreenOn();
                            }
                            if (z) {
                                return false;
                            }
                            return true;
                        }
                    }
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    public final void zza(Context context, String str, String str2, Bundle bundle, boolean z) {
        zzp.zzkr();
        bundle.putString("device", zzxt());
        bundle.putString("eids", TextUtils.join(",", zzabb.zzrg()));
        zzwm.zzpt();
        zzbbg.zza(context, str, str2, bundle, true, new zzayy(this, context, str));
    }

    public static void zzc(Runnable runnable) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            runnable.run();
        } else {
            zzbbz.zzeep.execute(runnable);
        }
    }

    public static Bitmap zzn(View view) {
        if (view == null) {
            return null;
        }
        Bitmap zzp = zzp(view);
        return zzp == null ? zzo(view) : zzp;
    }

    private static Bitmap zzo(View view) {
        try {
            int width = view.getWidth();
            int height = view.getHeight();
            if (width != 0) {
                if (height != 0) {
                    Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.RGB_565);
                    Canvas canvas = new Canvas(createBitmap);
                    view.layout(0, 0, width, height);
                    view.draw(canvas);
                    return createBitmap;
                }
            }
            zzayp.zzfe("Width or height of view is zero");
            return null;
        } catch (RuntimeException e) {
            zzayp.zzc("Fail to capture the webview", e);
            return null;
        }
    }

    private static Bitmap zzp(View view) {
        Bitmap bitmap = null;
        try {
            boolean isDrawingCacheEnabled = view.isDrawingCacheEnabled();
            view.setDrawingCacheEnabled(true);
            Bitmap drawingCache = view.getDrawingCache();
            if (drawingCache != null) {
                bitmap = Bitmap.createBitmap(drawingCache);
            }
            view.setDrawingCacheEnabled(isDrawingCacheEnabled);
        } catch (RuntimeException e) {
            zzayp.zzc("Fail to capture the web view", e);
        }
        return bitmap;
    }

    public static void zza(Context context, Intent intent) {
        try {
            context.startActivity(intent);
        } catch (Throwable unused) {
            intent.addFlags(268435456);
            context.startActivity(intent);
        }
    }

    public static int zzaw(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null) {
            return 0;
        }
        return applicationInfo.targetSdkVersion;
    }

    public final boolean zza(View view, Context context) {
        Context applicationContext = context.getApplicationContext();
        return zza(view, applicationContext != null ? (PowerManager) applicationContext.getSystemService("power") : null, zzax(context));
    }

    private static KeyguardManager zzax(Context context) {
        Object systemService = context.getSystemService("keyguard");
        if (systemService == null || !(systemService instanceof KeyguardManager)) {
            return null;
        }
        return (KeyguardManager) systemService;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0062 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(android.view.View r4, android.os.PowerManager r5, android.app.KeyguardManager r6) {
        /*
            r3 = this;
            com.google.android.gms.internal.ads.zzayu r0 = com.google.android.gms.ads.internal.zzp.zzkr()
            boolean r0 = r0.zzyq
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x001d
            if (r6 != 0) goto L_0x000e
            r6 = 0
            goto L_0x0012
        L_0x000e:
            boolean r6 = r6.inKeyguardRestrictedInputMode()
        L_0x0012:
            if (r6 == 0) goto L_0x001d
            boolean r6 = zzq(r4)
            if (r6 == 0) goto L_0x001b
            goto L_0x001d
        L_0x001b:
            r6 = 0
            goto L_0x001e
        L_0x001d:
            r6 = 1
        L_0x001e:
            int r0 = r4.getVisibility()
            if (r0 != 0) goto L_0x0063
            boolean r0 = r4.isShown()
            if (r0 == 0) goto L_0x0063
            if (r5 == 0) goto L_0x0035
            boolean r5 = r5.isScreenOn()
            if (r5 == 0) goto L_0x0033
            goto L_0x0035
        L_0x0033:
            r5 = 0
            goto L_0x0036
        L_0x0035:
            r5 = 1
        L_0x0036:
            if (r5 == 0) goto L_0x0063
            if (r6 == 0) goto L_0x0063
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r5 = com.google.android.gms.internal.ads.zzabb.zzcpt
            com.google.android.gms.internal.ads.zzaax r6 = com.google.android.gms.internal.ads.zzwm.zzpx()
            java.lang.Object r5 = r6.zzd(r5)
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L_0x0062
            android.graphics.Rect r5 = new android.graphics.Rect
            r5.<init>()
            boolean r5 = r4.getLocalVisibleRect(r5)
            if (r5 != 0) goto L_0x0062
            android.graphics.Rect r5 = new android.graphics.Rect
            r5.<init>()
            boolean r4 = r4.getGlobalVisibleRect(r5)
            if (r4 == 0) goto L_0x0063
        L_0x0062:
            return r1
        L_0x0063:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzayu.zza(android.view.View, android.os.PowerManager, android.app.KeyguardManager):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x0016 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean zzq(android.view.View r2) {
        /*
            android.view.View r2 = r2.getRootView()
            r0 = 0
            if (r2 == 0) goto L_0x0012
            android.content.Context r2 = r2.getContext()
            boolean r1 = r2 instanceof android.app.Activity
            if (r1 == 0) goto L_0x0012
            android.app.Activity r2 = (android.app.Activity) r2
            goto L_0x0013
        L_0x0012:
            r2 = r0
        L_0x0013:
            r1 = 0
            if (r2 != 0) goto L_0x0017
            return r1
        L_0x0017:
            android.view.Window r2 = r2.getWindow()
            if (r2 != 0) goto L_0x001e
            goto L_0x0022
        L_0x001e:
            android.view.WindowManager$LayoutParams r0 = r2.getAttributes()
        L_0x0022:
            if (r0 == 0) goto L_0x002d
            int r2 = r0.flags
            r0 = 524288(0x80000, float:7.34684E-40)
            r2 = r2 & r0
            if (r2 == 0) goto L_0x002d
            r2 = 1
            return r2
        L_0x002d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzayu.zzq(android.view.View):boolean");
    }

    public static boolean zzay(Context context) {
        KeyguardManager zzax;
        if (context == null || !PlatformVersion.isAtLeastJellyBean() || (zzax = zzax(context)) == null || !zzax.isKeyguardLocked()) {
            return false;
        }
        return true;
    }

    public static int zzr(View view) {
        if (view == null) {
            return -1;
        }
        ViewParent parent = view.getParent();
        while (parent != null && !(parent instanceof AdapterView)) {
            parent = parent.getParent();
        }
        if (parent == null) {
            return -1;
        }
        return ((AdapterView) parent).getPositionForView(view);
    }

    public static boolean zzs(View view) {
        if (view == null) {
            return false;
        }
        ViewParent parent = view.getParent();
        while (parent != null && !(parent instanceof ScrollView)) {
            parent = parent.getParent();
        }
        if (parent == null) {
            return false;
        }
        return true;
    }

    public static boolean zzaz(Context context) {
        try {
            context.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi");
            return false;
        } catch (ClassNotFoundException unused) {
            return true;
        } catch (Throwable th) {
            zzayp.zzc("Error loading class.", th);
            zzp.zzkv().zza(th, "AdUtil.isLiteSdk");
            return false;
        }
    }

    public static void zza(Context context, Uri uri) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            Bundle bundle = new Bundle();
            intent.putExtras(bundle);
            zzb(context, intent);
            bundle.putString("com.android.browser.application_id", context.getPackageName());
            context.startActivity(intent);
            String uri2 = uri.toString();
            StringBuilder sb = new StringBuilder(String.valueOf(uri2).length() + 26);
            sb.append("Opening ");
            sb.append(uri2);
            sb.append(" in a new browser.");
            zzayp.zzef(sb.toString());
        } catch (ActivityNotFoundException e) {
            zzayp.zzc("No browser is found.", e);
        }
    }

    public static void zzb(Context context, Intent intent) {
        if (intent != null && PlatformVersion.isAtLeastJellyBeanMR2()) {
            Bundle extras = intent.getExtras() != null ? intent.getExtras() : new Bundle();
            extras.putBinder(CustomTabsIntent.EXTRA_SESSION, (IBinder) null);
            extras.putString("com.android.browser.application_id", context.getPackageName());
            intent.putExtras(extras);
        }
    }

    public static void zzc(Context context, String str, String str2) {
        try {
            FileOutputStream openFileOutput = context.openFileOutput(str, 0);
            openFileOutput.write(str2.getBytes("UTF-8"));
            openFileOutput.close();
        } catch (Exception e) {
            zzayp.zzc("Error writing to file in internal storage.", e);
        }
    }

    public static String zzt(Context context, String str) {
        try {
            return new String(IOUtils.readInputStreamFully(context.openFileInput(str), true), "UTF-8");
        } catch (IOException unused) {
            zzayp.zzef("Error reading from internal storage.");
            return "";
        }
    }

    public final boolean zzep(String str) {
        return zza(str, this.zzeay, (String) zzwm.zzpx().zzd(zzabb.zzcnm));
    }

    public final boolean zzeq(String str) {
        return zza(str, this.zzeaz, (String) zzwm.zzpx().zzd(zzabb.zzcnn));
    }

    private static boolean zza(String str, AtomicReference<Pattern> atomicReference, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            Pattern pattern = atomicReference.get();
            if (pattern == null || !str2.equals(pattern.pattern())) {
                pattern = Pattern.compile(str2);
                atomicReference.set(pattern);
            }
            return pattern.matcher(str).matches();
        } catch (PatternSyntaxException unused) {
            return false;
        }
    }

    public static String zzxv() {
        Resources resources = zzp.zzkv().getResources();
        return resources != null ? resources.getString(R.string.s7) : "Test Ad";
    }

    public static WebResourceResponse zzd(Context context, String str, String str2) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("User-Agent", zzp.zzkr().zzs(context, str));
            hashMap.put(HttpHeaders.CACHE_CONTROL, "max-stale=3600");
            String str3 = (String) new zzbag(context).zzc(str2, hashMap).get(60, TimeUnit.SECONDS);
            if (str3 != null) {
                return new WebResourceResponse("application/javascript", "UTF-8", new ByteArrayInputStream(str3.getBytes("UTF-8")));
            }
            return null;
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
            zzayp.zzd("Could not fetch MRAID JS.", e);
            return null;
        }
    }

    public static WebResourceResponse zzd(HttpURLConnection httpURLConnection) throws IOException {
        String str;
        zzp.zzkr();
        String contentType = httpURLConnection.getContentType();
        String str2 = "";
        if (TextUtils.isEmpty(contentType)) {
            str = str2;
        } else {
            str = contentType.split(";")[0].trim();
        }
        zzp.zzkr();
        String contentType2 = httpURLConnection.getContentType();
        if (!TextUtils.isEmpty(contentType2)) {
            String[] split = contentType2.split(";");
            if (split.length != 1) {
                int i = 1;
                while (true) {
                    if (i >= split.length) {
                        break;
                    }
                    if (split[i].trim().startsWith("charset")) {
                        String[] split2 = split[i].trim().split("=");
                        if (split2.length > 1) {
                            str2 = split2[1].trim();
                            break;
                        }
                    }
                    i++;
                }
            }
        }
        String str3 = str2;
        Map headerFields = httpURLConnection.getHeaderFields();
        HashMap hashMap = new HashMap(headerFields.size());
        for (Map.Entry entry : headerFields.entrySet()) {
            if (!(entry.getKey() == null || entry.getValue() == null || ((List) entry.getValue()).size() <= 0)) {
                hashMap.put((String) entry.getKey(), (String) ((List) entry.getValue()).get(0));
            }
        }
        return zzp.zzkt().zza(str, str3, httpURLConnection.getResponseCode(), httpURLConnection.getResponseMessage(), hashMap, httpURLConnection.getInputStream());
    }

    public static void zza(Context context, Throwable th) {
        if (context != null) {
            boolean z = false;
            try {
                z = zzadb.zzdcc.get().booleanValue();
            } catch (IllegalStateException unused) {
            }
            if (z) {
                CrashUtils.addDynamiteErrorToDropBox(context, th);
            }
        }
    }

    public static String zzba(Context context) {
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcwd)).booleanValue()) {
            return "";
        }
        return context.getSharedPreferences("mobileads_consent", 0).getString("consent_string", "");
    }

    public static Bundle zzbb(Context context) {
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcwe)).booleanValue()) {
            return null;
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Bundle bundle = new Bundle();
        if (defaultSharedPreferences.contains("IABConsent_CMPPresent")) {
            bundle.putBoolean("IABConsent_CMPPresent", defaultSharedPreferences.getBoolean("IABConsent_CMPPresent", false));
        }
        String[] strArr = {"IABConsent_SubjectToGDPR", "IABConsent_ConsentString", "IABConsent_ParsedPurposeConsents", "IABConsent_ParsedVendorConsents"};
        for (int i = 0; i < 4; i++) {
            String str = strArr[i];
            if (defaultSharedPreferences.contains(str)) {
                bundle.putString(str, defaultSharedPreferences.getString(str, (String) null));
            }
        }
        return bundle;
    }

    public static boolean zzbc(Context context) {
        Window window;
        if (!(!(context instanceof Activity) || (window = ((Activity) context).getWindow()) == null || window.getDecorView() == null)) {
            Rect rect = new Rect();
            Rect rect2 = new Rect();
            window.getDecorView().getGlobalVisibleRect(rect, (Point) null);
            window.getDecorView().getWindowVisibleDisplayFrame(rect2);
            if (rect.bottom == 0 || rect2.bottom == 0 || rect.top != rect2.top) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean zzer(String str) {
        if (!zzbbk.isEnabled()) {
            return false;
        }
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcty)).booleanValue()) {
            return false;
        }
        String str2 = (String) zzwm.zzpx().zzd(zzabb.zzcua);
        if (!str2.isEmpty()) {
            for (String equals : str2.split(";")) {
                if (equals.equals(str)) {
                    return false;
                }
            }
        }
        String str3 = (String) zzwm.zzpx().zzd(zzabb.zzctz);
        if (str3.isEmpty()) {
            return true;
        }
        for (String equals2 : str3.split(";")) {
            if (equals2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static String zzbd(Context context) {
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        return zze(zzh(context));
    }

    public static boolean zzbe(Context context) {
        Bundle zzh = zzh(context);
        return TextUtils.isEmpty(zze(zzh)) && !TextUtils.isEmpty(zzh.getString("com.google.android.gms.ads.INTEGRATION_MANAGER"));
    }

    private static String zze(Bundle bundle) {
        if (bundle == null) {
            return "";
        }
        String string = bundle.getString("com.google.android.gms.ads.APPLICATION_ID");
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        if (string.matches("^ca-app-pub-[0-9]{16}~[0-9]{10}$") || string.matches("^/\\d+~.+$")) {
            return string;
        }
        return "";
    }

    private static Bundle zzh(Context context) {
        try {
            return Wrappers.packageManager(context).getApplicationInfo(context.getPackageName(), 128).metaData;
        } catch (PackageManager.NameNotFoundException | NullPointerException e) {
            zzayp.zza("Error getting metadata", e);
            return null;
        }
    }

    public static boolean zzbf(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
