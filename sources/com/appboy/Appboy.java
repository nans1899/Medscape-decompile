package com.appboy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import bo.app.ac;
import bo.app.ad;
import bo.app.ax;
import bo.app.ay;
import bo.app.az;
import bo.app.bf;
import bo.app.bg;
import bo.app.bi;
import bo.app.bl;
import bo.app.bp;
import bo.app.bq;
import bo.app.bu;
import bo.app.bv;
import bo.app.bw;
import bo.app.bz;
import bo.app.ca;
import bo.app.cc;
import bo.app.cf;
import bo.app.cj;
import bo.app.cm;
import bo.app.de;
import bo.app.dg;
import bo.app.dk;
import bo.app.dp;
import bo.app.dq;
import bo.app.du;
import bo.app.ec;
import bo.app.ei;
import bo.app.ej;
import bo.app.fj;
import bo.app.fk;
import bo.app.fo;
import bo.app.fx;
import bo.app.fy;
import bo.app.l;
import bo.app.m;
import bo.app.x;
import com.appboy.configuration.AppboyConfig;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.events.ContentCardsUpdatedEvent;
import com.appboy.events.FeedUpdatedEvent;
import com.appboy.events.IEventSubscriber;
import com.appboy.events.InAppMessageEvent;
import com.appboy.events.SubmitFeedbackFailed;
import com.appboy.events.SubmitFeedbackSucceeded;
import com.appboy.lrucache.AppboyLruImageLoader;
import com.appboy.models.IInAppMessage;
import com.appboy.models.outgoing.AppboyProperties;
import com.appboy.support.AppboyFileUtils;
import com.appboy.support.AppboyLogger;
import com.appboy.support.PermissionUtils;
import com.appboy.support.StringUtils;
import com.appboy.support.ValidationUtils;
import com.facebook.internal.ServerProtocol;
import com.medscape.android.BI.BIParameters;
import java.io.File;
import java.io.FilenameFilter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;

public class Appboy implements IAppboy, IAppboyUnitySupport {
    private static final Object A = new Object();
    private static volatile IAppboyEndpointProvider B;
    private static volatile IAppboyNotificationFactory C;
    /* access modifiers changed from: private */
    public static volatile boolean D = false;
    /* access modifiers changed from: private */
    public static volatile boolean E = false;
    private static volatile boolean F = false;
    private static volatile dp G;
    /* access modifiers changed from: private */
    public static final String l = AppboyLogger.getAppboyLogTag(Appboy.class);
    /* access modifiers changed from: private */
    public static final Set<String> m = new HashSet(Arrays.asList(new String[]{"AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN", "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BRL", "BSD", "BTC", "BTN", "BWP", "BYR", "BZD", "CAD", "CDF", "CHF", "CLF", "CLP", "CNY", "COP", "CRC", "CUC", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP", "DZD", "EEK", "EGP", "ERN", "ETB", "EUR", "FJD", "FKP", "GBP", "GEL", "GGP", "GHS", "GIP", "GMD", "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG", "HUF", "IDR", "ILS", "IMP", "INR", "IQD", "IRR", "ISK", "JEP", "JMD", "JOD", "JPY", "KES", "KGS", "KHR", "KMF", "KPW", "KRW", "KWD", "KYD", "KZT", "LAK", "LBP", "LKR", "LRD", "LSL", "LTL", "LVL", "LYD", "MAD", BIParameters.MDL, "MGA", "MKD", "MMK", "MNT", "MOP", "MRO", "MTL", "MUR", "MVR", "MWK", "MXN", "MYR", "MZN", "NAD", "NGN", "NIO", "NOK", "NPR", "NZD", "OMR", "PAB", "PEN", "PGK", "PHP", "PKR", "PLN", "PYG", "QAR", "RON", "RSD", "RUB", "RWF", "SAR", "SBD", "SCR", "SDG", "SEK", "SGD", "SHP", "SLL", "SOS", "SRD", "STD", "SVC", "SYP", "SZL", "THB", "TJS", "TMT", "TND", "TOP", "TRY", "TTD", "TWD", "TZS", "UAH", "UGX", "USD", "UYU", "UZS", "VEF", "VND", "VUV", "WST", "XAF", "XAG", "XAU", "XCD", "XDR", "XOF", "XPD", "XPF", "XPT", "YER", "ZAR", "ZMK", "ZMW", "ZWL"}));
    private static final Set<String> n = new HashSet(Collections.singletonList("calypso appcrawler"));
    private static final Set<String> o = new HashSet(Arrays.asList(new String[]{"android.permission.ACCESS_NETWORK_STATE", "android.permission.INTERNET"}));
    private static volatile Appboy p = null;
    volatile ad a;
    volatile ej b;
    volatile dk c;
    volatile fy d;
    volatile bi e;
    volatile dq f;
    volatile bl g;
    volatile dg h;
    final AppboyConfigurationProvider i;
    final bu j;
    final az k;
    /* access modifiers changed from: private */
    public final Context q;
    /* access modifiers changed from: private */
    public final ac r;
    /* access modifiers changed from: private */
    public final bw s;
    /* access modifiers changed from: private */
    public volatile AppboyUser t;
    private volatile ThreadPoolExecutor u;
    /* access modifiers changed from: private */
    public final l v;
    /* access modifiers changed from: private */
    public final bg w;
    private final ay x;
    /* access modifiers changed from: private */
    public IAppboyImageLoader y;
    private volatile boolean z = false;

    public static Appboy getInstance(Context context) {
        if (p == null || p.z) {
            synchronized (Appboy.class) {
                if (p != null) {
                    if (p.z) {
                    }
                }
                setOutboundNetworkRequestsOffline(a(context).a());
                p = new Appboy(context);
                Appboy appboy = p;
                return appboy;
            }
        }
        return p;
    }

    Appboy(final Context context) {
        long nanoTime = System.nanoTime();
        AppboyLogger.d(l, "Braze SDK Initializing");
        this.q = context.getApplicationContext();
        bw bwVar = new bw();
        this.s = bwVar;
        AppboyLogger.setTestUserDeviceLoggingManager(bwVar);
        String str = Build.MODEL;
        if (str != null && n.contains(str.toLowerCase(Locale.US))) {
            String str2 = l;
            AppboyLogger.i(str2, "Device build model matches a known crawler. Enabling mock network request mode. Device model: " + str);
            enableMockAppboyNetworkRequestsAndDropEventsMode();
        }
        this.y = new AppboyLruImageLoader(this.q);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(ei.a(), ei.b(), ei.c(), TimeUnit.SECONDS, ei.d(), new ThreadPoolExecutor.DiscardOldestPolicy());
        threadPoolExecutor.execute(new Runnable() {
            public void run() {
                AppboyLogger.checkForSystemLogLevelProperty();
            }
        });
        AppboyConfigurationProvider appboyConfigurationProvider = new AppboyConfigurationProvider(this.q);
        this.i = appboyConfigurationProvider;
        if (!StringUtils.isNullOrBlank(appboyConfigurationProvider.getCustomEndpoint())) {
            a(this.i.getCustomEndpoint());
        }
        this.v = new l(this.q);
        this.w = new bg(this.q);
        this.r = new ac(threadPoolExecutor, G);
        this.j = new bv(this.q, this.i);
        threadPoolExecutor.execute(new Runnable() {
            public void run() {
                if (!Appboy.this.i.isFirebaseCloudMessagingRegistrationEnabled()) {
                    AppboyLogger.i(Appboy.l, "Automatic Firebase Cloud Messaging registration not enabled in configuration. Braze will not register for Firebase Cloud Messaging.");
                } else if (bp.a(Appboy.this.q, Appboy.this.i)) {
                    AppboyLogger.i(Appboy.l, "Firebase Cloud Messaging found. Setting up Firebase Cloud Messaging.");
                    new bp(context).a(Appboy.this.i.getFirebaseCloudMessagingSenderIdKey());
                } else {
                    AppboyLogger.e(Appboy.l, "Firebase Cloud Messaging requirements not met. Braze will not register for Firebase Cloud Messaging.");
                }
                if (!Appboy.this.i.isAdmMessagingRegistrationEnabled()) {
                    AppboyLogger.i(Appboy.l, "Automatic ADM registration not enabled in configuration. Braze will not register for ADM.");
                } else if (bf.a(Appboy.this.q)) {
                    AppboyLogger.i(Appboy.l, "Amazon Device Messaging found. Setting up Amazon Device Messaging");
                    new bf(Appboy.this.q, Appboy.this.j).a();
                } else {
                    AppboyLogger.e(Appboy.l, "ADM manifest requirements not met. Braze will not register for ADM.");
                }
            }
        });
        ax axVar = new ax("Appboy-User-Dependency-Thread");
        ay ayVar = new ay(this.r);
        this.x = ayVar;
        axVar.a(ayVar);
        az azVar = new az(axVar);
        this.k = azVar;
        azVar.submit(new Runnable() {
            public void run() {
                AppboyLogger.v(Appboy.l, "Starting up a new user dependency manager");
                Appboy.this.a(new ej(Appboy.this.q, Appboy.this.v, Appboy.this.i, Appboy.this.r, Appboy.this.w, Appboy.this.j, Appboy.D, Appboy.E, Appboy.this.s));
            }
        });
        threadPoolExecutor.execute(new Runnable() {
            public void run() {
                Appboy.this.g();
            }
        });
        long nanoTime2 = System.nanoTime();
        String str3 = l;
        AppboyLogger.d(str3, "Appboy loaded in " + TimeUnit.MILLISECONDS.convert(nanoTime2 - nanoTime, TimeUnit.NANOSECONDS) + " ms.");
    }

    public void openSession(final Activity activity) {
        if (!i()) {
            this.k.submit(new Runnable() {
                public void run() {
                    try {
                        if (activity == null) {
                            AppboyLogger.w(Appboy.l, "Cannot open session with null activity.");
                        } else {
                            Appboy.this.g.a(activity);
                        }
                    } catch (Exception e) {
                        AppboyLogger.e(Appboy.l, "Failed to open session.", e);
                        Appboy.this.a((Throwable) e);
                    }
                }
            });
        }
    }

    public void closeSession(final Activity activity) {
        if (!i()) {
            this.k.submit(new Runnable() {
                public void run() {
                    try {
                        if (activity == null) {
                            AppboyLogger.w(Appboy.l, "Cannot close session with null activity.");
                            return;
                        }
                        cc b2 = Appboy.this.g.b(activity);
                        if (b2 != null) {
                            String b3 = Appboy.l;
                            AppboyLogger.i(b3, "Closed session with ID: " + b2.a());
                        }
                    } catch (Exception e) {
                        AppboyLogger.w(Appboy.l, "Failed to close session.", e);
                        Appboy.this.a((Throwable) e);
                    }
                }
            });
        }
    }

    public void submitFeedback(final String str, final String str2, final boolean z2) {
        if (!i()) {
            this.k.submit(new Runnable() {
                public void run() {
                    try {
                        Appboy.this.g.a(str, str2, z2);
                    } catch (Exception e) {
                        String b2 = Appboy.l;
                        AppboyLogger.w(b2, "Failed to submit feedback: " + str2, e);
                        Appboy.this.a((Throwable) e);
                    }
                }
            });
        }
    }

    public void logCustomEvent(String str) {
        logCustomEvent(str, (AppboyProperties) null);
    }

    public void logCustomEvent(final String str, final AppboyProperties appboyProperties) {
        if (!i()) {
            this.k.submit(new Runnable() {
                public void run() {
                    String str = str;
                    try {
                        if (!ValidationUtils.isValidLogCustomEventInput(str, Appboy.this.f)) {
                            String b2 = Appboy.l;
                            AppboyLogger.w(b2, "Log custom event input " + str + " was invalid. Not logging custom event to Appboy.");
                            return;
                        }
                        String ensureAppboyFieldLength = ValidationUtils.ensureAppboyFieldLength(str);
                        cf a2 = cf.a(ensureAppboyFieldLength, appboyProperties);
                        if (Appboy.this.g.a((bz) a2)) {
                            Appboy.this.d.a((fk) new fj(ensureAppboyFieldLength, appboyProperties, a2));
                        }
                    } catch (Exception e) {
                        String b3 = Appboy.l;
                        AppboyLogger.w(b3, "Failed to log custom event: " + str, e);
                        Appboy.this.a((Throwable) e);
                    }
                }
            });
        }
    }

    public void logPurchase(String str, String str2, BigDecimal bigDecimal) {
        logPurchase(str, str2, bigDecimal, 1);
    }

    public void logPurchase(String str, String str2, BigDecimal bigDecimal, AppboyProperties appboyProperties) {
        logPurchase(str, str2, bigDecimal, 1, appboyProperties);
    }

    public void logPurchase(String str, String str2, BigDecimal bigDecimal, int i2) {
        logPurchase(str, str2, bigDecimal, i2, (AppboyProperties) null);
    }

    public void logPurchase(String str, String str2, BigDecimal bigDecimal, int i2, AppboyProperties appboyProperties) {
        if (!i()) {
            final String str3 = str;
            final String str4 = str2;
            final BigDecimal bigDecimal2 = bigDecimal;
            final int i3 = i2;
            final AppboyProperties appboyProperties2 = appboyProperties;
            this.k.submit(new Runnable() {
                public void run() {
                    String str = str3;
                    String str2 = str4;
                    if (str2 == null) {
                        try {
                            String b2 = Appboy.l;
                            AppboyLogger.w(b2, "The currencyCode is null. Expected one of " + Appboy.m + ". Not logging in-app purchase to Appboy.");
                        } catch (Exception e2) {
                            String b3 = Appboy.l;
                            AppboyLogger.w(b3, "Failed to log purchase event of " + str, e2);
                            Appboy.this.a((Throwable) e2);
                        }
                    } else {
                        String upperCase = str2.trim().toUpperCase(Locale.US);
                        if (!ValidationUtils.isValidLogPurchaseInput(str, upperCase, bigDecimal2, i3, Appboy.this.f, Appboy.m)) {
                            AppboyLogger.w(Appboy.l, "Log purchase input was invalid. Not logging in-app purchase to Appboy.");
                            return;
                        }
                        String ensureAppboyFieldLength = ValidationUtils.ensureAppboyFieldLength(str);
                        cf a2 = cf.a(ensureAppboyFieldLength, upperCase, bigDecimal2, i3, appboyProperties2);
                        if (Appboy.this.g.a((bz) a2)) {
                            Appboy.this.d.a((fk) new fo(ensureAppboyFieldLength, appboyProperties2, a2));
                        }
                    }
                }
            });
        }
    }

    public void logPushNotificationOpened(final String str) {
        if (!i()) {
            this.k.submit(new Runnable() {
                public void run() {
                    try {
                        if (StringUtils.isNullOrBlank(str)) {
                            AppboyLogger.w(Appboy.l, "Campaign ID cannot be null or blank. Not logging push notification opened.");
                        } else {
                            Appboy.this.g.a((bz) cf.b(str));
                        }
                    } catch (Exception e) {
                        AppboyLogger.w(Appboy.l, "Failed to log opened push.", e);
                        Appboy.this.a((Throwable) e);
                    }
                }
            });
        }
    }

    public void logPushNotificationOpened(final Intent intent) {
        if (!i()) {
            this.k.submit(new Runnable() {
                public void run() {
                    try {
                        String stringExtra = intent.getStringExtra("cid");
                        if (!StringUtils.isNullOrBlank(stringExtra)) {
                            String b2 = Appboy.l;
                            AppboyLogger.i(b2, "Logging push click to Appboy. Campaign Id: " + stringExtra);
                            Appboy.this.logPushNotificationOpened(stringExtra);
                        } else {
                            AppboyLogger.i(Appboy.l, "No campaign Id associated with this notification. Not logging push click to Appboy.");
                        }
                        if (intent.hasExtra(Constants.APPBOY_PUSH_FETCH_TEST_TRIGGERS_KEY) && intent.getStringExtra(Constants.APPBOY_PUSH_FETCH_TEST_TRIGGERS_KEY).equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
                            AppboyLogger.i(Appboy.l, "Push contained key for fetching test triggers, fetching triggers.");
                            Appboy.this.g.a(new cj.a().b());
                        }
                    } catch (Exception e) {
                        AppboyLogger.w(Appboy.l, "Error logging push notification", e);
                    }
                }
            });
        }
    }

    public void logPushDeliveryEvent(final String str) {
        if (!i()) {
            this.k.submit(new Runnable() {
                public void run() {
                    try {
                        if (StringUtils.isNullOrBlank(str)) {
                            AppboyLogger.w(Appboy.l, "Campaign ID cannot be null or blank for push delivery event.");
                        } else {
                            Appboy.this.g.a((bz) cf.j(str));
                        }
                    } catch (Exception e) {
                        AppboyLogger.w(Appboy.l, "Failed to log push delivery event.", e);
                        Appboy.this.a((Throwable) e);
                    }
                }
            });
        }
    }

    public void logPushNotificationActionClicked(final String str, final String str2) {
        if (!i()) {
            this.k.submit(new Runnable() {
                public void run() {
                    try {
                        if (StringUtils.isNullOrBlank(str)) {
                            AppboyLogger.w(Appboy.l, "Campaign ID cannot be null or blank. Not logging push notification action clicked.");
                        } else if (StringUtils.isNullOrBlank(str2)) {
                            AppboyLogger.w(Appboy.l, "Action ID cannot be null or blank");
                        } else {
                            Appboy.this.g.a((bz) cf.c(str, str2));
                        }
                    } catch (Exception e) {
                        AppboyLogger.w(Appboy.l, "Failed to log push notification action clicked.", e);
                        Appboy.this.a((Throwable) e);
                    }
                }
            });
        }
    }

    public void logPushStoryPageClicked(final String str, final String str2) {
        if (!i()) {
            this.k.submit(new Runnable() {
                public void run() {
                    try {
                        if (!ValidationUtils.isValidPushStoryClickInput(str, str2)) {
                            AppboyLogger.w(Appboy.l, "Push story page click input was invalid. Not logging in-app purchase to Appboy.");
                        } else {
                            Appboy.this.g.a((bz) cf.b(str, str2));
                        }
                    } catch (Exception e) {
                        String b2 = Appboy.l;
                        AppboyLogger.w(b2, "Failed to log push story page clicked for page id: " + str2 + " cid: " + str, e);
                        Appboy.this.a((Throwable) e);
                    }
                }
            });
        }
    }

    public void logContentCardsDisplayed() {
        if (!i()) {
            this.k.submit(new Runnable() {
                public void run() {
                    try {
                        Appboy.this.g.a((bz) cf.h());
                    } catch (Exception e) {
                        AppboyLogger.w(Appboy.l, "Failed to log that Content Cards was displayed.", e);
                        Appboy.this.a((Throwable) e);
                    }
                }
            });
        }
    }

    public void logFeedDisplayed() {
        if (!i()) {
            this.k.submit(new Runnable() {
                public void run() {
                    try {
                        Appboy.this.g.a((bz) cf.i());
                    } catch (Exception e) {
                        AppboyLogger.w(Appboy.l, "Failed to log that the feed was displayed.", e);
                        Appboy.this.a((Throwable) e);
                    }
                }
            });
        }
    }

    public void logFeedbackDisplayed() {
        if (!i()) {
            this.k.submit(new Runnable() {
                public void run() {
                    try {
                        Appboy.this.g.a((bz) cf.j());
                    } catch (Exception e) {
                        AppboyLogger.w(Appboy.l, "Failed to log that feedback was displayed.", e);
                        Appboy.this.a((Throwable) e);
                    }
                }
            });
        }
    }

    public void requestFeedRefreshFromCache() {
        if (!i()) {
            this.k.submit(new Runnable() {
                public void run() {
                    try {
                        Appboy.this.r.a(Appboy.this.c.a(), FeedUpdatedEvent.class);
                    } catch (JSONException e) {
                        AppboyLogger.w(Appboy.l, "Failed to retrieve and publish feed from offline cache.", e);
                    }
                }
            });
        }
    }

    public void requestFeedRefresh() {
        if (!i()) {
            this.k.submit(new Runnable() {
                public void run() {
                    try {
                        Appboy.this.g.a(new cj.a().a());
                    } catch (Exception e) {
                        AppboyLogger.w(Appboy.l, "Failed to request refresh of feed.", e);
                        Appboy.this.a((Throwable) e);
                    }
                }
            });
        }
    }

    public void requestContentCardsRefresh(final boolean z2) {
        if (!i()) {
            this.k.submit(new Runnable() {
                public void run() {
                    try {
                        if (z2) {
                            Appboy.this.r.a(Appboy.this.h.a(), ContentCardsUpdatedEvent.class);
                        } else {
                            Appboy.this.g.a(Appboy.this.h.b(), Appboy.this.h.c());
                        }
                    } catch (Exception e) {
                        String b2 = Appboy.l;
                        AppboyLogger.w(b2, "Failed to request Content Cards refresh. Requesting from cache: " + z2, e);
                        Appboy.this.a((Throwable) e);
                    }
                }
            });
        }
    }

    public void requestImmediateDataFlush() {
        if (!i()) {
            this.k.submit(new Runnable() {
                public void run() {
                    try {
                        Appboy.this.g.d();
                    } catch (Exception e) {
                        AppboyLogger.w(Appboy.l, "Failed to request data flush.", e);
                        Appboy.this.a((Throwable) e);
                    }
                }
            });
        }
    }

    public void subscribeToNewInAppMessages(IEventSubscriber<InAppMessageEvent> iEventSubscriber) {
        try {
            this.r.a(iEventSubscriber, InAppMessageEvent.class);
        } catch (Exception e2) {
            AppboyLogger.w(l, "Failed to add subscriber to new in-app messages.", e2);
            a((Throwable) e2);
        }
    }

    public void subscribeToContentCardsUpdates(IEventSubscriber<ContentCardsUpdatedEvent> iEventSubscriber) {
        try {
            this.r.a(iEventSubscriber, ContentCardsUpdatedEvent.class);
        } catch (Exception e2) {
            AppboyLogger.w(l, "Failed to add subscriber for Content Cards updates.", e2);
            a((Throwable) e2);
        }
    }

    public void subscribeToFeedUpdates(IEventSubscriber<FeedUpdatedEvent> iEventSubscriber) {
        try {
            this.r.a(iEventSubscriber, FeedUpdatedEvent.class);
        } catch (Exception e2) {
            AppboyLogger.w(l, "Failed to add subscriber for feed updates.", e2);
            a((Throwable) e2);
        }
    }

    public void subscribeToFeedbackRequestEvents(IEventSubscriber<SubmitFeedbackSucceeded> iEventSubscriber, IEventSubscriber<SubmitFeedbackFailed> iEventSubscriber2) {
        if (iEventSubscriber != null) {
            try {
                this.r.a(iEventSubscriber, SubmitFeedbackSucceeded.class);
            } catch (Exception e2) {
                AppboyLogger.w(l, "Failed to add subscribers for feedback request events.", e2);
                a((Throwable) e2);
                return;
            }
        }
        if (iEventSubscriber2 != null) {
            this.r.a(iEventSubscriber2, SubmitFeedbackFailed.class);
        }
    }

    public <T> void removeSingleSubscription(IEventSubscriber<T> iEventSubscriber, Class<T> cls) {
        try {
            this.r.c(iEventSubscriber, cls);
        } catch (Exception e2) {
            String str = l;
            AppboyLogger.w(str, "Failed to remove " + cls.getName() + " subscriber.", e2);
            a((Throwable) e2);
        }
    }

    public void changeUser(final String str) {
        if (!i()) {
            this.k.submit(new Runnable() {
                public void run() {
                    try {
                        if (StringUtils.isNullOrEmpty(str)) {
                            AppboyLogger.e(Appboy.l, "ArgumentException: userId passed to changeUser was null or empty. The current user will remain the active user.");
                            return;
                        }
                        String userId = Appboy.this.t.getUserId();
                        if (userId.equals(str)) {
                            String b2 = Appboy.l;
                            AppboyLogger.i(b2, "Received request to change current user " + str + " to the same user id. Doing nothing.");
                            return;
                        }
                        if (userId.equals("")) {
                            String b3 = Appboy.l;
                            AppboyLogger.i(b3, "Changing anonymous user to " + str);
                            Appboy.this.v.b(str);
                            Appboy.this.t.a(str);
                        } else {
                            String b4 = Appboy.l;
                            AppboyLogger.i(b4, "Changing current user " + userId + " to new user " + str + ".");
                            Appboy.this.r.a(new FeedUpdatedEvent(new ArrayList(), str, false, du.a()), FeedUpdatedEvent.class);
                        }
                        Appboy.this.g.c();
                        Appboy.this.v.a(str);
                        ej ejVar = Appboy.this.b;
                        Appboy.this.a(new ej(Appboy.this.q, Appboy.this.v, Appboy.this.i, Appboy.this.r, Appboy.this.w, Appboy.this.j, Appboy.D, Appboy.E, Appboy.this.s));
                        Appboy.this.b.g().d();
                        Appboy.this.g.a();
                        Appboy.this.g.a(new cj.a().a());
                        Appboy.this.requestContentCardsRefresh(false);
                        ejVar.o();
                    } catch (Exception e) {
                        String b5 = Appboy.l;
                        AppboyLogger.w(b5, "Failed to set external id to: " + str, e);
                        Appboy.this.a((Throwable) e);
                    }
                }
            });
        }
    }

    public AppboyUser getCurrentUser() {
        try {
            return (AppboyUser) this.k.submit(new Callable<AppboyUser>() {
                /* renamed from: a */
                public AppboyUser call() {
                    return Appboy.this.t;
                }
            }).get();
        } catch (Exception e2) {
            AppboyLogger.w(l, "Failed to retrieve the current user.", e2);
            a((Throwable) e2);
            return null;
        }
    }

    public void registerAppboyPushMessages(String str) {
        if (!i()) {
            try {
                if (StringUtils.isNullOrBlank(str)) {
                    AppboyLogger.w(l, "Push registration ID must not be null or blank. Not registering for push messages from Appboy.");
                    return;
                }
                String str2 = l;
                AppboyLogger.i(str2, "Push token " + str + " registered and immediately being flushed.");
                this.j.a(str);
                requestImmediateDataFlush();
            } catch (Exception e2) {
                AppboyLogger.w(l, "Failed to set the registration ID.", e2);
                a((Throwable) e2);
            }
        }
    }

    public String getAppboyPushMessageRegistrationId() {
        if (i()) {
            return "";
        }
        try {
            return this.j.a();
        } catch (Exception e2) {
            AppboyLogger.w(l, "Failed to get the registration ID.", e2);
            a((Throwable) e2);
            return null;
        }
    }

    public String getInstallTrackingId() {
        if (i()) {
            return "";
        }
        return this.w.a();
    }

    public IAppboyImageLoader getAppboyImageLoader() {
        if (this.y == null) {
            AppboyLogger.d(l, "The Image Loader was null. Creating a new Image Loader and returning it.");
            this.y = new AppboyLruImageLoader(this.q);
        }
        return this.y;
    }

    public void setAppboyImageLoader(IAppboyImageLoader iAppboyImageLoader) {
        if (this.y == null) {
            AppboyLogger.w(l, "The Image Loader cannot be set to null. Doing nothing.");
        } else {
            this.y = iAppboyImageLoader;
        }
    }

    public int getContentCardCount() {
        if (i()) {
            return -1;
        }
        ContentCardsUpdatedEvent f2 = f();
        if (f2 != null) {
            return f2.getCardCount();
        }
        AppboyLogger.w(l, "The ContentCardsUpdatedEvent was null. Returning the default value for the card count.");
        return -1;
    }

    public int getContentCardUnviewedCount() {
        if (i()) {
            return -1;
        }
        ContentCardsUpdatedEvent f2 = f();
        if (f2 != null) {
            return f2.getUnviewedCardCount();
        }
        AppboyLogger.w(l, "The ContentCardsUpdatedEvent was null. Returning the default value for the unviewed card count.");
        return -1;
    }

    public long getContentCardsLastUpdatedInSecondsFromEpoch() {
        if (i()) {
            return -1;
        }
        ContentCardsUpdatedEvent f2 = f();
        if (f2 != null) {
            return f2.getLastUpdatedInSecondsFromEpoch();
        }
        AppboyLogger.w(l, "The ContentCardsUpdatedEvent was null. Returning the default value for the last update timestamp.");
        return -1;
    }

    public void logFeedCardImpression(final String str) {
        if (!i()) {
            this.k.submit(new Runnable() {
                public void run() {
                    try {
                        if (StringUtils.isNullOrBlank(str)) {
                            AppboyLogger.e(Appboy.l, "Card ID cannot be null or blank.");
                            return;
                        }
                        Appboy.this.g.a((bz) cf.c(str));
                        Appboy.this.c.b(str);
                    } catch (Exception e) {
                        String b2 = Appboy.l;
                        AppboyLogger.w(b2, "Failed to log feed card impression. Card id: " + str, e);
                        Appboy.this.a((Throwable) e);
                    }
                }
            });
        }
    }

    public void logFeedCardClick(final String str) {
        if (!i()) {
            this.k.submit(new Runnable() {
                public void run() {
                    try {
                        if (StringUtils.isNullOrBlank(str)) {
                            AppboyLogger.e(Appboy.l, "Card ID cannot be null or blank.");
                        } else {
                            Appboy.this.g.a((bz) cf.d(str));
                        }
                    } catch (Exception e) {
                        String b2 = Appboy.l;
                        AppboyLogger.w(b2, "Failed to log feed card clicked. Card id: " + str, e);
                        Appboy.this.a((Throwable) e);
                    }
                }
            });
        }
    }

    public IInAppMessage deserializeInAppMessageString(String str) {
        return ec.a(str, (bq) this.g);
    }

    public String getDeviceId() {
        return this.w.a();
    }

    public static void setOutboundNetworkRequestsOffline(boolean z2) {
        String str = l;
        StringBuilder sb = new StringBuilder();
        sb.append("Appboy outbound network requests are now ");
        sb.append(z2 ? "disabled" : "enabled");
        AppboyLogger.i(str, sb.toString());
        synchronized (Appboy.class) {
            E = z2;
            if (p != null) {
                p.b(z2);
            }
        }
    }

    public static boolean getOutboundNetworkRequestsOffline() {
        return E;
    }

    public static void setAppboyEndpointProvider(IAppboyEndpointProvider iAppboyEndpointProvider) {
        synchronized (A) {
            B = iAppboyEndpointProvider;
        }
    }

    public static void clearAppboyEndpointProvider() {
        synchronized (A) {
            B = null;
        }
    }

    public static Uri getAppboyApiEndpoint(Uri uri) {
        synchronized (A) {
            if (B != null) {
                try {
                    Uri apiEndpoint = B.getApiEndpoint(uri);
                    if (apiEndpoint != null) {
                        return apiEndpoint;
                    }
                } catch (Exception unused) {
                    AppboyLogger.e(l, "Caught exception trying to get a Braze API endpoint from the AppboyEndpointProvider. Using the original URI");
                }
            }
        }
        return uri;
    }

    public static void setCustomAppboyNotificationFactory(IAppboyNotificationFactory iAppboyNotificationFactory) {
        AppboyLogger.d(l, "Custom Braze notification factory set");
        C = iAppboyNotificationFactory;
    }

    public static IAppboyNotificationFactory getCustomAppboyNotificationFactory() {
        return C;
    }

    public static boolean configure(Context context, AppboyConfig appboyConfig) {
        if (p != null) {
            AppboyLogger.w(l, "Appboy.configure() must be called before the first call to Appboy.getInstance()");
            return false;
        } else if (!F) {
            synchronized (Appboy.class) {
                if (p != null || F) {
                    AppboyLogger.i(l, "Appboy.configure() can only be called once during the lifetime of the singleton.");
                    return false;
                }
                m mVar = new m(context.getApplicationContext());
                if (appboyConfig != null) {
                    F = true;
                    mVar.a(appboyConfig);
                    return true;
                }
                AppboyLogger.i(l, "Appboy.configure() called with a null config; Clearing all configuration values.");
                mVar.a();
                return true;
            }
        } else {
            AppboyLogger.w(l, "Appboy.configure() can only be called once during the lifetime of the singleton.");
            return false;
        }
    }

    public static boolean enableMockAppboyNetworkRequestsAndDropEventsMode() {
        if (p == null) {
            synchronized (Appboy.class) {
                if (p == null) {
                    if (D) {
                        AppboyLogger.i(l, "Appboy network requests already being mocked. Note that events dispatched in this mode are dropped.");
                        return true;
                    }
                    AppboyLogger.i(l, "Appboy network requests will be mocked. Events dispatched in this mode will be dropped.");
                    D = true;
                    return true;
                }
            }
        }
        AppboyLogger.e(l, "Attempt to enable mocking Braze network requests had no effect since getInstance() has already been called.");
        return false;
    }

    public static void disableSdk(Context context) {
        a(context).a(true);
        AppboyLogger.w(l, "Stopping the SDK instance.");
        h();
        AppboyLogger.w(l, "Disabling all network requests");
        setOutboundNetworkRequestsOffline(true);
    }

    public static void enableSdk(Context context) {
        AppboyLogger.w(l, "Setting SDK to enabled.");
        a(context).a(false);
        AppboyLogger.w(l, "Enabling all network requests");
        setOutboundNetworkRequestsOffline(false);
    }

    public static void wipeData(Context context) {
        h();
        try {
            fx.a(context);
            AppboyLruImageLoader.deleteStoredData(context);
        } catch (Exception e2) {
            AppboyLogger.w(l, "Failed to delete data from the internal storage cache.", e2);
        }
        try {
            de.a(context);
        } catch (Exception e3) {
            AppboyLogger.w(l, "Failed to delete Braze database files for the Braze SDK.", e3);
        }
        try {
            File file = new File(context.getApplicationInfo().dataDir, "shared_prefs");
            if (file.exists() && file.isDirectory()) {
                for (File file2 : file.listFiles(new FilenameFilter() {
                    public boolean accept(File file, String str) {
                        return str.startsWith(BuildConfig.APPLICATION_ID);
                    }
                })) {
                    AppboyLogger.v(l, "Deleting shared prefs file at: " + file2.getAbsolutePath());
                    AppboyFileUtils.deleteFileOrDirectory(file2);
                }
            }
        } catch (Exception e4) {
            AppboyLogger.w(l, "Failed to delete shared preference data for the Braze SDK.", e4);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(final ca caVar) {
        if (!i()) {
            this.k.submit(new Runnable() {
                public void run() {
                    try {
                        if (Appboy.this.e != null) {
                            Appboy.this.e.a(caVar);
                        } else {
                            AppboyLogger.d(Appboy.l, "Geofence manager was null. Not requesting geofence refresh.");
                        }
                    } catch (Exception e) {
                        AppboyLogger.w(Appboy.l, "Failed to request geofence refresh.", e);
                        Appboy.this.a((Throwable) e);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void a(final boolean z2) {
        if (!i()) {
            this.k.submit(new Runnable() {
                public void run() {
                    try {
                        if (Appboy.this.e != null) {
                            Appboy.this.e.b(z2);
                        } else {
                            AppboyLogger.d(Appboy.l, "Geofence manager was null. Not requesting geofence refresh.");
                        }
                    } catch (Exception e) {
                        String b2 = Appboy.l;
                        AppboyLogger.w(b2, "Failed to request geofence refresh with rate limit ignore: " + z2, e);
                        Appboy.this.a((Throwable) e);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void a(final String str, final x xVar) {
        if (!i()) {
            this.k.submit(new Runnable() {
                public void run() {
                    try {
                        if (Appboy.this.e != null) {
                            Appboy.this.e.b(str, xVar);
                        } else {
                            AppboyLogger.d(Appboy.l, "Geofence manager was null. Not posting geofence report");
                        }
                    } catch (Exception e) {
                        AppboyLogger.w(Appboy.l, "Failed to post geofence report.", e);
                        Appboy.this.a((Throwable) e);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void a(final String str, final String str2) {
        if (!i()) {
            if (StringUtils.isNullOrBlank(str)) {
                String str3 = l;
                AppboyLogger.w(str3, "Cannot add null or blank card json to storage. Returning. User id: " + str2 + " Serialized json: " + str);
                return;
            }
            this.k.submit(new Runnable() {
                public void run() {
                    try {
                        Appboy.this.h.a(new cm(str), str2);
                    } catch (Exception e) {
                        String b2 = Appboy.l;
                        AppboyLogger.e(b2, "Failed to update ContentCard storage provider with single card update. User id: " + str2 + " Serialized json: " + str, e);
                    }
                }
            });
        }
    }

    private ContentCardsUpdatedEvent f() {
        if (i()) {
            return null;
        }
        try {
            return (ContentCardsUpdatedEvent) this.k.submit(new Callable<ContentCardsUpdatedEvent>() {
                /* renamed from: a */
                public ContentCardsUpdatedEvent call() {
                    return Appboy.this.h.a();
                }
            }).get();
        } catch (Exception e2) {
            AppboyLogger.w(l, "Failed to retrieve the cached ContentCardsUpdatedEvent.", e2);
            a((Throwable) e2);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        if (!i()) {
            this.k.submit(new Runnable() {
                public void run() {
                    try {
                        if (Appboy.this.e != null) {
                            Appboy.this.e.a();
                        } else {
                            AppboyLogger.d(Appboy.l, "Geofence manager was null. Not initializing geofences.");
                        }
                    } catch (Exception e) {
                        AppboyLogger.w(Appboy.l, "Failed to initialize geofences with the geofence manager.", e);
                        Appboy.this.a((Throwable) e);
                    }
                }
            });
        }
    }

    private void b(final boolean z2) {
        this.k.submit(new Runnable() {
            public void run() {
                Appboy.this.g.a(z2);
                Appboy.this.b.b().a(z2);
                if (Appboy.this.y != null) {
                    String b2 = Appboy.l;
                    AppboyLogger.d(b2, "Setting the image loader deny network downloads to " + z2);
                    Appboy.this.y.setOffline(z2);
                }
            }
        });
    }

    private void a(final String str) {
        synchronized (A) {
            setAppboyEndpointProvider(new IAppboyEndpointProvider() {
                public Uri getApiEndpoint(Uri uri) {
                    return uri.buildUpon().encodedAuthority(str).build();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(Throwable th) {
        try {
            this.a.a(th, Throwable.class);
        } catch (Exception e2) {
            AppboyLogger.e(l, "Failed to log throwable.", e2);
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        boolean z2 = true;
        for (String next : o) {
            if (!PermissionUtils.hasPermission(this.q, next)) {
                String str = l;
                AppboyLogger.e(str, "The Braze SDK requires the permission " + next + ". Check your app manifest.");
                z2 = false;
            }
        }
        if (this.i.getAppboyApiKey().toString().equals("")) {
            AppboyLogger.e(l, "The Braze SDK requires a non-empty API key. Check your appboy.xml or AppboyConfig.");
            z2 = false;
        }
        if (!z2) {
            AppboyLogger.e(l, "The Braze SDK is not integrated correctly. Please visit https://www.braze.com/documentation/Android");
        }
    }

    /* access modifiers changed from: private */
    public void a(ej ejVar) {
        this.b = ejVar;
        this.g = ejVar.d();
        this.f = ejVar.a();
        this.d = ejVar.l();
        this.e = ejVar.m();
        this.h = ejVar.n();
        this.t = new AppboyUser(ejVar.g(), this.g, this.v.a(), ejVar.j(), this.f);
        ejVar.c().a(ejVar.f());
        ejVar.e().a();
        this.a = ejVar.f();
        this.x.a(this.a);
        this.u = ejVar.h();
        this.c = ejVar.i();
        this.d = ejVar.l();
        ejVar.k().a(this.u, ejVar.e());
        this.s.a((bq) this.g);
        this.s.a(this.f.k());
    }

    private static void h() {
        try {
            AppboyLogger.i(l, "Shutting down all queued work on the Braze SDK");
            synchronized (Appboy.class) {
                if (p != null) {
                    if (p.k != null) {
                        AppboyLogger.d(l, "Shutting down the user dependency executor");
                        p.k.shutdownNow();
                    }
                    ej ejVar = p.b;
                    if (ejVar != null) {
                        if (ejVar.b() != null) {
                            ejVar.b().a(true);
                        }
                        if (ejVar.k() != null) {
                            ejVar.k().a();
                        }
                        if (ejVar.m() != null) {
                            ejVar.m().b();
                        }
                    }
                    p.z = true;
                }
            }
        } catch (Exception e2) {
            AppboyLogger.w(l, "Failed to shutdown queued work on the Braze SDK.", e2);
        }
    }

    private static boolean i() {
        if (G == null) {
            AppboyLogger.d(l, "SDK enablement provider was null. Returning SDK as enabled.");
            return false;
        }
        boolean a2 = G.a();
        if (a2) {
            AppboyLogger.w(l, "SDK is disabled. Not performing action on SDK.");
        }
        return a2;
    }

    private static dp a(Context context) {
        if (G == null) {
            G = new dp(context);
        }
        return G;
    }
}
