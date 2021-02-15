package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.wrappers.Wrappers;
import com.tapstream.sdk.http.RequestBuilders;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzasf implements zzasj {
    private static final Object lock = new Object();
    private static zzasj zzdqi = null;
    private static zzasj zzdqj = null;
    private final Context zzclo;
    private final Object zzdqk;
    private final WeakHashMap<Thread, Boolean> zzdql;
    private final zzbbx zzdqm;
    private final ExecutorService zzyu;

    public static zzasj zzt(Context context) {
        synchronized (lock) {
            if (zzdqi == null) {
                if (zzadb.zzdcf.get().booleanValue()) {
                    if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcxs)).booleanValue()) {
                        zzdqi = new zzasf(context);
                    }
                }
                zzdqi = new zzasi();
            }
        }
        return zzdqi;
    }

    public static zzasj zzc(Context context, zzbbx zzbbx) {
        synchronized (lock) {
            if (zzdqj == null) {
                if (zzadb.zzdcf.get().booleanValue()) {
                    if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcxs)).booleanValue()) {
                        zzasf zzasf = new zzasf(context, zzbbx);
                        Thread thread = Looper.getMainLooper().getThread();
                        if (thread != null) {
                            synchronized (zzasf.zzdqk) {
                                zzasf.zzdql.put(thread, true);
                            }
                            thread.setUncaughtExceptionHandler(new zzasg(zzasf, thread.getUncaughtExceptionHandler()));
                        }
                        Thread.setDefaultUncaughtExceptionHandler(new zzash(zzasf, Thread.getDefaultUncaughtExceptionHandler()));
                        zzdqj = zzasf;
                    }
                }
                zzdqj = new zzasi();
            }
        }
        return zzdqj;
    }

    private zzasf(Context context) {
        this(context, zzbbx.zzyv());
    }

    private zzasf(Context context, zzbbx zzbbx) {
        this.zzdqk = new Object();
        this.zzdql = new WeakHashMap<>();
        this.zzyu = zzdvg.zzaxc().zzej(zzdvl.zzhov);
        this.zzclo = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.zzdqm = zzbbx;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003b, code lost:
        if (r3 == false) goto L_0x003f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(java.lang.Thread r10, java.lang.Throwable r11) {
        /*
            r9 = this;
            r10 = 1
            r0 = 0
            if (r11 == 0) goto L_0x003e
            r1 = r11
            r2 = 0
            r3 = 0
        L_0x0007:
            if (r1 == 0) goto L_0x0039
            java.lang.StackTraceElement[] r4 = r1.getStackTrace()
            int r5 = r4.length
            r6 = 0
        L_0x000f:
            if (r6 >= r5) goto L_0x0034
            r7 = r4[r6]
            java.lang.String r8 = r7.getClassName()
            boolean r8 = com.google.android.gms.internal.ads.zzbbg.zzey(r8)
            if (r8 == 0) goto L_0x001e
            r2 = 1
        L_0x001e:
            java.lang.Class r8 = r9.getClass()
            java.lang.String r8 = r8.getName()
            java.lang.String r7 = r7.getClassName()
            boolean r7 = r8.equals(r7)
            if (r7 == 0) goto L_0x0031
            r3 = 1
        L_0x0031:
            int r6 = r6 + 1
            goto L_0x000f
        L_0x0034:
            java.lang.Throwable r1 = r1.getCause()
            goto L_0x0007
        L_0x0039:
            if (r2 == 0) goto L_0x003e
            if (r3 != 0) goto L_0x003e
            goto L_0x003f
        L_0x003e:
            r10 = 0
        L_0x003f:
            if (r10 == 0) goto L_0x0048
            r10 = 1065353216(0x3f800000, float:1.0)
            java.lang.String r0 = ""
            r9.zza(r11, r0, r10)
        L_0x0048:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzasf.zza(java.lang.Thread, java.lang.Throwable):void");
    }

    public final void zza(Throwable th, String str) {
        zza(th, str, 1.0f);
    }

    public final void zza(Throwable th, String str, float f) {
        if (zzbbg.zzd(th) != null) {
            String name = th.getClass().getName();
            StringWriter stringWriter = new StringWriter();
            zzehy.zza(th, new PrintWriter(stringWriter));
            String stringWriter2 = stringWriter.toString();
            int i = 0;
            int i2 = 1;
            boolean z = Math.random() < ((double) f);
            if (f > 0.0f) {
                i2 = (int) (1.0f / f);
            }
            if (z) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(zza(name, stringWriter2, str, i2).toString());
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                while (i < size) {
                    Object obj = arrayList2.get(i);
                    i++;
                    this.zzyu.execute(new zzase(new zzbbu(), (String) obj));
                }
            }
        }
    }

    private final Uri.Builder zza(String str, String str2, String str3, int i) {
        boolean z;
        String str4;
        try {
            z = Wrappers.packageManager(this.zzclo).isCallerInstantApp();
        } catch (Throwable th) {
            zzbbq.zzc("Error fetching instant app info", th);
            z = false;
        }
        try {
            str4 = this.zzclo.getPackageName();
        } catch (Throwable unused) {
            zzbbq.zzfe("Cannot obtain package name, proceeding.");
            str4 = "unknown";
        }
        Uri.Builder appendQueryParameter = new Uri.Builder().scheme(RequestBuilders.DEFAULT_SCHEME).path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("is_aia", Boolean.toString(z)).appendQueryParameter("id", "gmob-apps-report-exception").appendQueryParameter(AdParameterKeys.OS, Build.VERSION.RELEASE).appendQueryParameter("api", String.valueOf(Build.VERSION.SDK_INT));
        String str5 = Build.MANUFACTURER;
        String str6 = Build.MODEL;
        if (!str6.startsWith(str5)) {
            StringBuilder sb = new StringBuilder(String.valueOf(str5).length() + 1 + String.valueOf(str6).length());
            sb.append(str5);
            sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb.append(str6);
            str6 = sb.toString();
        }
        Uri.Builder appendQueryParameter2 = appendQueryParameter.appendQueryParameter("device", str6).appendQueryParameter("js", this.zzdqm.zzbre).appendQueryParameter("appid", str4).appendQueryParameter("exceptiontype", str).appendQueryParameter("stacktrace", str2).appendQueryParameter("eids", TextUtils.join(",", zzabb.zzrg())).appendQueryParameter("exceptionkey", str3).appendQueryParameter("cl", "330794610").appendQueryParameter("rc", "dev").appendQueryParameter("session_id", zzwm.zzpy()).appendQueryParameter("sampling_rate", Integer.toString(i)).appendQueryParameter("pb_tm", String.valueOf(zzadb.zzdcd.get()));
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcpz)).booleanValue()) {
            appendQueryParameter2.appendQueryParameter("gmscv", String.valueOf(GoogleApiAvailabilityLight.getInstance().getApkVersion(this.zzclo))).appendQueryParameter("lite", this.zzdqm.zzeeo ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
        return appendQueryParameter2;
    }
}
