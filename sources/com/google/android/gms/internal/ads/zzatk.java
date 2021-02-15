package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzatk {
    private float zzbsa;
    private int zzdoc;
    private int zzdod;
    private int zzdul;
    private boolean zzdum;
    private boolean zzdun;
    private int zzduo;
    private int zzdup;
    private int zzduq;
    private String zzdur;
    private int zzdus;
    private int zzdut;
    private int zzduu;
    private boolean zzduv;
    private int zzduw;
    private double zzdux;
    private boolean zzduy;
    private String zzduz;
    private String zzdva;
    private boolean zzdvb;
    private boolean zzdvc;
    private String zzdvd;
    private boolean zzdve;
    private final boolean zzdvf;
    private boolean zzdvg;
    private String zzdvh;
    private String zzdvi;
    private String zzdvj;
    private boolean zzdvk;

    public zzatk(Context context) {
        DisplayMetrics displayMetrics;
        PackageManager packageManager = context.getPackageManager();
        zzu(context);
        zzv(context);
        zzw(context);
        Locale locale = Locale.getDefault();
        boolean z = true;
        this.zzdvb = zza(packageManager, "geo:0,0?q=donuts") != null;
        this.zzdvc = zza(packageManager, "http://www.google.com") == null ? false : z;
        this.zzdvd = locale.getCountry();
        zzwm.zzpt();
        this.zzdve = zzbbg.zzym();
        this.zzdvf = DeviceProperties.isLatchsky(context);
        this.zzdvg = DeviceProperties.isSidewinder(context);
        this.zzdvh = locale.getLanguage();
        this.zzdvi = zza(context, packageManager);
        this.zzdvj = zzx(context);
        Resources resources = context.getResources();
        if (resources != null && (displayMetrics = resources.getDisplayMetrics()) != null) {
            this.zzbsa = displayMetrics.density;
            this.zzdoc = displayMetrics.widthPixels;
            this.zzdod = displayMetrics.heightPixels;
        }
    }

    public zzatk(Context context, zzatl zzatl) {
        zzu(context);
        zzv(context);
        zzw(context);
        this.zzduz = Build.FINGERPRINT;
        this.zzdva = Build.DEVICE;
        this.zzdvk = PlatformVersion.isAtLeastIceCreamSandwichMR1() && zzacc.zzk(context);
        this.zzdvb = zzatl.zzdvb;
        this.zzdvc = zzatl.zzdvc;
        this.zzdvd = zzatl.zzdvd;
        this.zzdve = zzatl.zzdve;
        this.zzdvf = zzatl.zzdvf;
        this.zzdvg = zzatl.zzdvg;
        this.zzdvh = zzatl.zzdvh;
        this.zzdvi = zzatl.zzdvi;
        this.zzdvj = zzatl.zzdvj;
        this.zzbsa = zzatl.zzbsa;
        this.zzdoc = zzatl.zzdoc;
        this.zzdod = zzatl.zzdod;
    }

    private final void zzu(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (audioManager != null) {
            try {
                this.zzdul = audioManager.getMode();
                this.zzdum = audioManager.isMusicActive();
                this.zzdun = audioManager.isSpeakerphoneOn();
                this.zzduo = audioManager.getStreamVolume(3);
                this.zzdup = audioManager.getRingerMode();
                this.zzduq = audioManager.getStreamVolume(2);
                return;
            } catch (Throwable th) {
                zzp.zzkv().zza(th, "DeviceInfo.gatherAudioInfo");
            }
        }
        this.zzdul = -2;
        this.zzdum = false;
        this.zzdun = false;
        this.zzduo = 0;
        this.zzdup = 2;
        this.zzduq = 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0050  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzv(android.content.Context r6) {
        /*
            r5 = this;
            java.lang.String r0 = "phone"
            java.lang.Object r0 = r6.getSystemService(r0)
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0
            java.lang.String r1 = "connectivity"
            java.lang.Object r1 = r6.getSystemService(r1)
            android.net.ConnectivityManager r1 = (android.net.ConnectivityManager) r1
            java.lang.String r2 = r0.getNetworkOperator()
            r5.zzdur = r2
            boolean r2 = com.google.android.gms.common.util.PlatformVersion.isAtLeastR()
            r3 = 0
            if (r2 == 0) goto L_0x0031
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r2 = com.google.android.gms.internal.ads.zzabb.zzcyr
            com.google.android.gms.internal.ads.zzaax r4 = com.google.android.gms.internal.ads.zzwm.zzpx()
            java.lang.Object r2 = r4.zzd(r2)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L_0x0031
            r2 = 0
            goto L_0x0035
        L_0x0031:
            int r2 = r0.getNetworkType()
        L_0x0035:
            r5.zzdut = r2
            int r0 = r0.getPhoneType()
            r5.zzduu = r0
            r0 = -2
            r5.zzdus = r0
            r5.zzduv = r3
            r0 = -1
            r5.zzduw = r0
            com.google.android.gms.ads.internal.zzp.zzkr()
            java.lang.String r2 = "android.permission.ACCESS_NETWORK_STATE"
            boolean r6 = com.google.android.gms.internal.ads.zzayu.zzr(r6, r2)
            if (r6 == 0) goto L_0x006f
            android.net.NetworkInfo r6 = r1.getActiveNetworkInfo()
            if (r6 == 0) goto L_0x0067
            int r0 = r6.getType()
            r5.zzdus = r0
            android.net.NetworkInfo$DetailedState r6 = r6.getDetailedState()
            int r6 = r6.ordinal()
            r5.zzduw = r6
            goto L_0x0069
        L_0x0067:
            r5.zzdus = r0
        L_0x0069:
            boolean r6 = r1.isActiveNetworkMetered()
            r5.zzduv = r6
        L_0x006f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzatk.zzv(android.content.Context):void");
    }

    private final void zzw(Context context) {
        Intent registerReceiver = context.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        boolean z = false;
        if (registerReceiver != null) {
            int intExtra = registerReceiver.getIntExtra("status", -1);
            this.zzdux = (double) (((float) registerReceiver.getIntExtra(FirebaseAnalytics.Param.LEVEL, -1)) / ((float) registerReceiver.getIntExtra("scale", -1)));
            if (intExtra == 2 || intExtra == 5) {
                z = true;
            }
            this.zzduy = z;
            return;
        }
        this.zzdux = -1.0d;
        this.zzduy = false;
    }

    private static String zzx(Context context) {
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo("com.android.vending", 128);
            if (packageInfo != null) {
                int i = packageInfo.versionCode;
                String str = packageInfo.packageName;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 12);
                sb.append(i);
                sb.append(".");
                sb.append(str);
                return sb.toString();
            }
        } catch (Exception unused) {
        }
        return null;
    }

    private static String zza(Context context, PackageManager packageManager) {
        ActivityInfo activityInfo;
        ResolveInfo zza = zza(packageManager, "market://details?id=com.google.android.gms.ads");
        if (zza == null || (activityInfo = zza.activityInfo) == null) {
            return null;
        }
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(activityInfo.packageName, 0);
            if (packageInfo != null) {
                int i = packageInfo.versionCode;
                String str = activityInfo.packageName;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 12);
                sb.append(i);
                sb.append(".");
                sb.append(str);
                return sb.toString();
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return null;
    }

    private static ResolveInfo zza(PackageManager packageManager, String str) {
        try {
            return packageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)), 65536);
        } catch (Throwable th) {
            zzp.zzkv().zza(th, "DeviceInfo.getResolveInfo");
            return null;
        }
    }

    public final zzatl zzvn() {
        return new zzatl(this.zzdul, this.zzdvb, this.zzdvc, this.zzdur, this.zzdvd, this.zzdve, this.zzdvf, this.zzdvg, this.zzdum, this.zzdun, this.zzdvh, this.zzdvi, this.zzdvj, this.zzduo, this.zzdus, this.zzdut, this.zzduu, this.zzdup, this.zzduq, this.zzbsa, this.zzdoc, this.zzdod, this.zzdux, this.zzduy, this.zzduv, this.zzduw, this.zzduz, this.zzdvk, this.zzdva);
    }
}
