package net.media.android.bidder.base;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import mnetinternal.aa;
import mnetinternal.ac;
import mnetinternal.ad;
import mnetinternal.af;
import mnetinternal.ai;
import mnetinternal.ak;
import mnetinternal.an;
import mnetinternal.cb;
import mnetinternal.ci;
import mnetinternal.ck;
import mnetinternal.cv;
import mnetinternal.da;
import mnetinternal.q;
import mnetinternal.x;
import mnetinternal.z;
import net.media.android.bidder.base.common.Constants;
import net.media.android.bidder.base.common.ViewContextProvider;
import net.media.android.bidder.base.gson.a;
import net.media.android.bidder.base.logging.Logger;
import net.media.android.bidder.base.models.MNetUser;
import net.media.android.bidder.base.models.internal.AdTrackerEvent;
import net.media.android.bidder.base.models.internal.AnalyticsEvent;

final class b {
    /* access modifiers changed from: private */
    public static String a;
    /* access modifiers changed from: private */
    public static AtomicBoolean c = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public static MNetUser d;
    private static String e;
    /* access modifiers changed from: private */
    public Application b;

    b(Application application, String str, boolean z) {
        a(application, str, z);
    }

    private void a(final Application application, final String str, final boolean z) {
        aa.b();
        aa.a().submit(new ac() {
            public void a() {
                Application unused = b.this.b = application;
                String unused2 = b.a = str;
                cb.a().a("__mn__unique_device_id", (String) null);
                ad.a((Context) application);
                net.media.android.bidder.base.common.b.a(z);
                ck.a((Context) application);
                b.this.a(application);
                a.a();
                net.media.android.bidder.base.logging.b.a(application);
                x.a().a(AdTrackerEvent.EVENT_CONFIG_FETCH_COMPLETE, new z() {
                    public void a(Object obj) {
                        x.a().b(AdTrackerEvent.EVENT_CONFIG_FETCH_COMPLETE, this);
                        an.a();
                        ci.a((Context) application);
                        ViewContextProvider.init(application);
                        application.registerActivityLifecycleCallbacks(a.a());
                        q.a();
                        b.this.a((Context) application);
                        q.a("", ViewContextProvider.getViewContext());
                        b.this.h();
                    }
                });
                net.media.android.bidder.base.configs.a.a((Context) application);
                b.c.set(true);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public String a() {
        return a;
    }

    public Context b() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return c.get();
    }

    /* access modifiers changed from: package-private */
    public void a(String str, String str2, int i, Map<String, Object> map, String str3) {
        final String str4 = str;
        final String str5 = str2;
        final int i2 = i;
        final Map<String, Object> map2 = map;
        final String str6 = str3;
        aa.a().submit(new ac() {
            public void a() {
                MNetUser unused = b.d = new MNetUser.Builder().withId(str4).withGender(str5).withYearOfBirth(i2).withData(map2).withKeyWords(str6).build();
                aa.a((Runnable) new ac() {
                    public void a() {
                        net.media.android.bidder.base.analytics.b.a().a(AnalyticsEvent.Events.newEvent("mnetview_set_mnetuser").addProperty("user", b.d));
                    }
                });
            }
        });
    }

    /* access modifiers changed from: package-private */
    public MNetUser d() {
        return d;
    }

    /* access modifiers changed from: private */
    public void a(final Context context) {
        aa.a((Runnable) new ac() {
            public void a() {
                String uuid = UUID.randomUUID().toString();
                cb.a().a("__mn__visit_id", uuid);
                net.media.android.bidder.base.analytics.b.a().a("__mn__visit_id", uuid);
                net.media.android.bidder.base.analytics.b.a().a(Constants.PUBLISHER_ID, b.a);
                net.media.android.bidder.base.analytics.b.a().a(Constants.APP_PACKAGE, context.getPackageName());
                try {
                    net.media.android.bidder.base.analytics.b.a().a(Constants.APP_VERSION_NAME, da.a(context));
                    net.media.android.bidder.base.analytics.b.a().a(Constants.APP_VERSION_CODE, Integer.valueOf(da.b(context)));
                } catch (PackageManager.NameNotFoundException e) {
                    Logger.error("##MNetImpl##", "package not found", e);
                }
                net.media.android.bidder.base.analytics.b.a().b(AnalyticsEvent.Events.newEvent("user_session").addProperty("customer_id", b.a));
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void a(final SubjectToGDPR subjectToGDPR, final ConsentStatus consentStatus, final String str) {
        aa.a().submit(new ac() {
            public void a() {
                Logger.debug("##MNetImpl##", "manual update gdpr consent");
                net.media.android.bidder.base.common.b.a().a(subjectToGDPR, consentStatus, str);
            }
        });
    }

    /* access modifiers changed from: private */
    public void h() {
        aa.a((Runnable) new ac() {
            public void a() {
                try {
                    net.media.android.bidder.base.analytics.b.a().a(AnalyticsEvent.Events.newEvent("relay_latency").addProperty("latency", Long.valueOf(cv.a(an.b(), 3000))));
                } catch (IOException unused) {
                }
                af.a(new ai.a(an.b()).a(), new ak<Object>() {
                    public void a(Object obj) {
                    }

                    public void a(Throwable th) {
                    }

                    public Class<Object> a() {
                        return Object.class;
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(Application application) {
        PackageManager packageManager = application.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        if (queryIntentActivities != null) {
            for (ResolveInfo next : queryIntentActivities) {
                if (next.activityInfo.packageName.equals(application.getPackageName())) {
                    e = next.activityInfo.name;
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(String str) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(e) && e.contains(str)) {
            return true;
        }
        return false;
    }
}
