package mnetinternal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.WebView;
import com.facebook.places.model.PlaceFields;
import com.medscape.android.settings.Settings;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import net.media.android.bidder.base.MNet;
import net.media.android.bidder.base.common.Constants;
import net.media.android.bidder.base.common.b;
import net.media.android.bidder.base.logging.Logger;
import net.media.android.bidder.base.models.internal.AdTrackerEvent;
import net.media.android.bidder.base.models.internal.AnalyticsEvent;
import net.media.android.bidder.base.models.internal.DeviceInfo;
import net.media.android.bidder.base.models.internal.DisplaySize;
import net.media.android.bidder.base.models.internal.GeoLocation;

public final class ck {
    /* access modifiers changed from: private */
    public static ck a = new ck();
    private Map<String, Object> b = new ConcurrentHashMap();
    private AtomicBoolean c = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public cw<Context> d;
    /* access modifiers changed from: private */
    public String e = "";
    private DeviceInfo f;

    private ck() {
    }

    public static ck a() {
        return a;
    }

    public static void a(Context context) {
        a.d = new cw<>(context);
        a.i();
        x.a().a(AdTrackerEvent.EVENT_CONFIG_FETCH_COMPLETE, new z() {
            public void a(Object obj) {
                ck.a.b();
            }
        });
    }

    /* access modifiers changed from: private */
    public void b(Context context) {
        if (context != null) {
            a("limited_ad_tracking", (Object) Boolean.valueOf(b.a().e()));
            a("limited_ad_tracking", (Object) Boolean.valueOf(cv.c(context)));
            AnalyticsEvent newSdkEvent = AnalyticsEvent.Events.newSdkEvent(Constants.SDK_DATA_EVENT, 4);
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(PlaceFields.PHONE);
            if (telephonyManager != null) {
                a("network_operator", (Object) telephonyManager.getNetworkOperatorName());
                newSdkEvent.addProperty("network_operator", telephonyManager.getNetworkOperatorName());
            }
            String a2 = cx.a(context);
            if (a2 != null) {
                a("connection_type", (Object) a2);
                newSdkEvent.addProperty("connection_type", a2);
            }
            a(newSdkEvent);
            AnalyticsEvent newSdkEvent2 = AnalyticsEvent.Events.newSdkEvent(Constants.SDK_DATA_EVENT, 2);
            a("manufacturer", (Object) Build.MANUFACTURER);
            newSdkEvent2.addProperty("manufacturer", Build.MANUFACTURER);
            a("device_model", (Object) Build.MODEL);
            newSdkEvent2.addProperty("device_model", Build.MODEL);
            a("product", (Object) Build.PRODUCT);
            newSdkEvent2.addProperty("product", Build.PRODUCT);
            a("brand", (Object) Build.BRAND);
            newSdkEvent2.addProperty("brand", Build.BRAND);
            a("os_version", (Object) Build.VERSION.RELEASE);
            newSdkEvent2.addProperty("os_version", Build.VERSION.RELEASE);
            a("os_api_level", (Object) Integer.valueOf(Build.VERSION.SDK_INT));
            newSdkEvent2.addProperty("os_api_level", Integer.valueOf(Build.VERSION.SDK_INT));
            a("os_build", (Object) Build.DISPLAY);
            newSdkEvent2.addProperty("os_build", Build.DISPLAY);
            a("program_total_memory", (Object) Long.valueOf(cv.d()));
            boolean a3 = cv.a();
            a("is_rooted", (Object) Boolean.valueOf(a3));
            newSdkEvent2.addProperty("is_rooted", Boolean.valueOf(a3));
            boolean b2 = cv.b();
            a("is_emulator", (Object) Boolean.valueOf(b2));
            newSdkEvent2.addProperty("is_emulator", Boolean.valueOf(b2));
            long a4 = cv.a(context) / PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            a("device_ram", (Object) Long.valueOf(a4));
            newSdkEvent2.addProperty("device_ram", Long.valueOf(a4));
            long c2 = cv.c() / PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            a("internal_free_space", (Object) Long.valueOf(c2));
            newSdkEvent2.addProperty("internal_free_space", Long.valueOf(c2));
            a("sdk_version", (Object) 24);
            newSdkEvent2.addProperty("sdk_version", 24);
            a("app_package", (Object) context.getPackageName());
            newSdkEvent2.addProperty("app_package", context.getPackageName());
            try {
                int b3 = da.b(context);
                a(Constants.APP_VERSION_CODE, (Object) Integer.valueOf(b3));
                newSdkEvent2.addProperty(Constants.APP_VERSION_CODE, Integer.valueOf(b3));
                String a5 = da.a(context);
                a(Constants.APP_VERSION_NAME, (Object) a5);
                newSdkEvent2.addProperty(Constants.APP_VERSION_NAME, a5);
            } catch (PackageManager.NameNotFoundException e2) {
                Logger.error("##Metadata##", "package not found", e2);
            }
            a(net.media.android.bidder.base.models.internal.Logger.TIMEZONE, (Object) TimeZone.getDefault().getDisplayName());
            a(AnalyticsEvent.Events.newSdkEvent(Constants.SDK_DATA_EVENT, 7).addProperty(net.media.android.bidder.base.models.internal.Logger.TIMEZONE, TimeZone.getDefault().getDisplayName()));
            boolean z = (context.getApplicationInfo().flags & 2) != 0;
            a("app_is_debug_mode", (Object) Boolean.valueOf(z));
            newSdkEvent2.addProperty("app_is_debug_mode", Boolean.valueOf(z));
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager != null) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                a("display_density", (Object) Integer.valueOf(displayMetrics.densityDpi));
                newSdkEvent2.addProperty("display_density", Integer.valueOf(displayMetrics.densityDpi));
                a("pixel_ratio", (Object) Float.valueOf(displayMetrics.density));
                newSdkEvent2.addProperty("pixel_ratio", Float.valueOf(displayMetrics.density));
                DisplaySize a6 = cv.a(windowManager.getDefaultDisplay(), displayMetrics);
                a("display_width", (Object) Integer.valueOf(a6.getWidth()));
                newSdkEvent2.addProperty("display_width", Integer.valueOf(a6.getWidth()));
                a("display_height", (Object) Integer.valueOf(a6.getHeight()));
                newSdkEvent2.addProperty("display_height", Integer.valueOf(a6.getHeight()));
            }
            a(AnalyticsEvent.Events.newSdkEvent(Constants.SDK_DATA_EVENT, 6).addProperty(net.media.android.bidder.base.models.internal.Logger.USER_AGENT, f()));
            if (!b.a().e()) {
                if (Build.VERSION.SDK_INT >= 24) {
                    String language = context.getResources().getConfiguration().getLocales().get(0).getLanguage();
                    a("device_language", (Object) language);
                    newSdkEvent2.addProperty("device_language", language);
                } else {
                    String language2 = context.getResources().getConfiguration().locale.getLanguage();
                    a("device_language", (Object) language2);
                    newSdkEvent2.addProperty("device_language", language2);
                }
            }
            a(newSdkEvent2);
            this.c.set(true);
        }
    }

    private void a(final AnalyticsEvent analyticsEvent) {
        aa.a((Runnable) new ac() {
            public void a() {
                net.media.android.bidder.base.analytics.b.a().a(analyticsEvent);
            }
        });
    }

    private void i() {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            aa.a((Runnable) new ac() {
                public void a() {
                    if (ck.this.d != null && ck.this.d.get() != null) {
                        ck ckVar = ck.this;
                        ckVar.b((Context) ckVar.d.get());
                    }
                }
            });
            return;
        }
        cw<Context> cwVar = this.d;
        if (cwVar != null && cwVar.get() != null) {
            b((Context) this.d.get());
        }
    }

    private void j() {
        Logger.debug("##Metadata##", "metadata is not initialized, you might get invalid data");
    }

    /* access modifiers changed from: package-private */
    public void a(String str, Object obj) {
        if (obj != null) {
            this.b.put(str, obj);
        }
    }

    private Location k() {
        ci.a().a(2);
        if (!this.b.containsKey("location") || this.b.get("location") == null) {
            return null;
        }
        return (Location) this.b.get("location");
    }

    private String a(String str) {
        if (!this.c.get()) {
            j();
        }
        if (!this.b.containsKey(str) || this.b.get(str) == null) {
            return null;
        }
        return (String) this.b.get(str);
    }

    private boolean b(String str) {
        if (!this.c.get()) {
            j();
        }
        return this.b.containsKey(str) && this.b.get(str) != null && ((Boolean) this.b.get(str)).booleanValue();
    }

    private float c(String str) {
        if (!this.c.get()) {
            j();
        }
        if (!this.b.containsKey(str) || this.b.get(str) == null) {
            return 0.0f;
        }
        return ((Float) this.b.get(str)).floatValue();
    }

    private int d(String str) {
        if (!this.c.get()) {
            j();
        }
        if (!this.b.containsKey(str) || this.b.get(str) == null) {
            return 0;
        }
        return ((Integer) this.b.get(str)).intValue();
    }

    private long e(String str) {
        if (!this.c.get()) {
            j();
        }
        if (!this.b.containsKey(str) || this.b.get(str) == null) {
            return 0;
        }
        return ((Long) this.b.get(str)).longValue();
    }

    public DeviceInfo a(boolean z, Location location) {
        if (this.f == null) {
            b(z, location);
        }
        return this.f;
    }

    private void b(boolean z, final Location location) {
        final DeviceInfo.Builder builder = new DeviceInfo.Builder(Boolean.valueOf(z));
        this.d.a(new cz<Context>() {
            public void a() {
            }

            public void a(Context context) {
                ck.this.a(context, builder, location);
            }
        });
        this.f = builder.build();
    }

    /* access modifiers changed from: private */
    public void a(Context context, DeviceInfo.Builder builder, Location location) {
        if (!this.c.get()) {
            builder.setUserAgent(f());
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager != null) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                DisplaySize a2 = cv.a(windowManager.getDefaultDisplay(), displayMetrics);
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                builder.setPixelDensity(displayMetrics.densityDpi).setPixelRatio(displayMetrics.density).setDisplayHeight(a2.getHeight()).setDisplayWidth(a2.getWidth());
            }
            builder.setDeviceLanguage(da.c(context));
            builder.setAdvertId(cv.d(context)).isLocationAllowed(cy.a().b()).setManufacturer(Build.BRAND).setDeviceModel(Build.MODEL).setOs("android").setOsVersion(Build.VERSION.RELEASE).setOsBuild(Build.DISPLAY).setOsApiLevel(Build.VERSION.SDK_INT).setIsRooted(cv.a()).setProgramMemory(cv.d()).setIsEmulator(cv.b());
        } else {
            builder.setUserAgent(f()).setIPv4Address(a("ipv4")).setIPv6Address(a("ipv6")).setPixelDensity(d("display_density")).setDisplayHeight(d("display_height")).setDisplayWidth(d("display_width")).setPixelRatio(c("pixel_ratio")).setCountryCode(a("country_code")).isLocationAllowed(cy.a().b()).setAdvertId(cv.d(context)).setIsLimitedAdTracking(b("limited_ad_tracking")).setCarrier(a("network_operator")).setManufacturer(a("brand")).setDeviceModel(a("device_model")).setOs("android").setDeviceLanguage(a("device_language")).setOsVersion(a("os_version")).setOsBuild(a("os_build")).setIsRooted(b("is_rooted")).setOsApiLevel(d("os_api_level")).setProgramMemory(e("program_total_memory")).setIsEmulator(b("is_emulator"));
        }
        builder.setConnectionType(cx.a(cx.a(context)));
        builder.setDeviceType(cv.e(context) ? 5 : 4);
        if (location == null) {
            location = k();
        }
        if (location != null) {
            builder.setLocation(new GeoLocation.Builder().setLocationSource(1).setLatitude(location.getLatitude()).setLongitude(location.getLongitude()).setAccuracy(Math.round(location.getAccuracy())).setCountry(a("country_code")).setRegion(a(ContentParser.REGION)).setCity(a(Settings.CITY)).setZipCode(a("zip_code")).build());
        }
    }

    public void b() {
        i();
        b(b.a().e(), (Location) null);
    }

    public int c() {
        return d(Constants.APP_VERSION_CODE);
    }

    public String d() {
        return a(Constants.APP_VERSION_NAME);
    }

    public String e() {
        return a("app_package");
    }

    public String f() {
        if (TextUtils.isEmpty(this.e)) {
            this.e = System.getProperty("http.agent");
            new Handler(Looper.getMainLooper()).post(new ac() {
                public void a() {
                    String unused = ck.this.e = new WebView(MNet.getContext()).getSettings().getUserAgentString();
                }
            });
        }
        return this.e;
    }

    public boolean g() {
        aa.a((Runnable) new Runnable() {
            public void run() {
                if (ck.this.d != null && ck.this.d.get() != null) {
                    ck ckVar = ck.this;
                    ckVar.a("limited_ad_tracking", (Object) Boolean.valueOf(cv.c((Context) ckVar.d.get())));
                }
            }
        });
        return b("limited_ad_tracking");
    }
}
