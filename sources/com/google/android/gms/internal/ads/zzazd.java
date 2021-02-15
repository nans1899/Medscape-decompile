package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Process;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import com.google.android.gms.ads.internal.zzp;
import java.io.InputStream;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzazd {
    public String getDefaultUserAgent(Context context) {
        return "";
    }

    public boolean isAttachedToWindow(View view) {
        return (view.getWindowToken() == null && view.getWindowVisibility() == 8) ? false : true;
    }

    public boolean zza(Activity activity, Configuration configuration) {
        return false;
    }

    public void zzbg(Context context) {
    }

    public void zzg(Activity activity) {
    }

    public int zzxw() {
        return 5;
    }

    public int zzxz() {
        return 1;
    }

    public long zzya() {
        return -1;
    }

    public boolean zza(Context context, WebSettings webSettings) {
        zzbay.zza(context, new zzazc(context, webSettings));
        webSettings.setAllowFileAccessFromFileURLs(false);
        webSettings.setAllowUniversalAccessFromFileURLs(false);
        return true;
    }

    public static boolean zza(zzbgj zzbgj) {
        if (zzbgj == null) {
            return false;
        }
        zzbgj.onPause();
        return true;
    }

    public static boolean zzb(zzbgj zzbgj) {
        if (zzbgj == null) {
            return false;
        }
        zzbgj.onResume();
        return true;
    }

    public zzbgi zza(zzbgj zzbgj, zzts zzts, boolean z) {
        return new zzbhk(zzbgj, zzts, z);
    }

    public ViewGroup.LayoutParams zzxx() {
        return new ViewGroup.LayoutParams(-2, -2);
    }

    public Drawable zza(Context context, Bitmap bitmap, boolean z, float f) {
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    public static boolean zzxy() {
        int myUid = Process.myUid();
        return myUid == 0 || myUid == 1000;
    }

    public CookieManager zzbh(Context context) {
        if (zzxy()) {
            return null;
        }
        try {
            CookieSyncManager.createInstance(context);
            return CookieManager.getInstance();
        } catch (Throwable th) {
            zzayp.zzc("Failed to obtain CookieManager.", th);
            zzp.zzkv().zza(th, "ApiLevelUtil.getCookieManager");
            return null;
        }
    }

    public zzuk zza(Context context, TelephonyManager telephonyManager) {
        return zzuk.ENUM_UNKNOWN;
    }

    public int zza(ContentResolver contentResolver) {
        return Settings.System.getInt(contentResolver, "wifi_on", 0);
    }

    public int zzb(ContentResolver contentResolver) {
        return Settings.System.getInt(contentResolver, "airplane_mode_on", 0);
    }

    public WebResourceResponse zza(String str, String str2, int i, String str3, Map<String, String> map, InputStream inputStream) {
        return new WebResourceResponse(str, str2, inputStream);
    }

    private zzazd() {
    }

    public static zzazd zzdh(int i) {
        if (i >= 28) {
            return new zzazk();
        }
        if (i >= 26) {
            return new zzazl();
        }
        if (i >= 24) {
            return new zzazi();
        }
        if (i >= 21) {
            return new zzazj();
        }
        if (i >= 19) {
            return new zzazg();
        }
        if (i >= 18) {
            return new zzazh();
        }
        if (i >= 17) {
            return new zzaze();
        }
        return new zzazd();
    }
}
