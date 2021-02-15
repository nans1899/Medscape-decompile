package mnetinternal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.mnet.gson.e;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import mnetinternal.m;
import net.media.android.bidder.base.MNet;
import net.media.android.bidder.base.common.Constants;
import net.media.android.bidder.base.logging.Logger;
import net.media.android.bidder.base.models.internal.AnalyticsEvent;
import net.media.android.bidder.base.models.internal.App;

final class cl extends ch {
    private static final Object b = new Object();
    private m<App> c;
    /* access modifiers changed from: private */
    public final List<App> d = Collections.synchronizedList(new ArrayList());
    /* access modifiers changed from: private */
    public BroadcastReceiver e;

    /* access modifiers changed from: package-private */
    public void a(int i) {
    }

    cl(Context context) {
        super(context, 5);
        this.c = new m<>(new File(context.getFilesDir().getAbsolutePath() + "package_cache"), new a());
    }

    /* access modifiers changed from: package-private */
    public void a() {
        d();
        e();
        a(true);
    }

    /* renamed from: mnetinternal.cl$1  reason: invalid class name */
    class AnonymousClass1 implements cz<Context> {
        final /* synthetic */ cl a;

        public void a() {
        }

        public void a(Context context) {
            context.unregisterReceiver(this.a.e);
        }
    }

    private void d() {
        this.e = new b(this, (AnonymousClass1) null);
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme(Constants.APP_PACKAGE);
        this.a.a(new cz<Context>() {
            public void a() {
            }

            public void a(Context context) {
                context.registerReceiver(cl.this.e, intentFilter);
            }
        });
    }

    private void e() {
        if (b()) {
            if (this.c.a() > 0) {
                f();
            } else {
                this.a.a(new cz<Context>() {
                    public void a() {
                    }

                    public void a(Context context) {
                        PackageManager packageManager = context.getPackageManager();
                        for (ApplicationInfo next : packageManager.getInstalledApplications(128)) {
                            if ((next.flags & 1) == 0) {
                                String charSequence = next.loadLabel(packageManager).toString();
                                String str = "";
                                try {
                                    str = packageManager.getPackageInfo(next.packageName, 0).versionName;
                                } catch (PackageManager.NameNotFoundException e) {
                                    Logger.error("##PackageTracker##", e.getMessage());
                                }
                                synchronized (cl.this.d) {
                                    cl.this.d.add(new App(charSequence, next.packageName, str));
                                }
                            }
                        }
                        cl.this.h();
                        cl.this.g();
                    }
                });
            }
        }
    }

    private void f() {
        if (this.d.size() == 0) {
            ArrayList arrayList = new ArrayList();
            synchronized (b) {
                if (this.c.a() > 0) {
                    while (this.c.b() != null) {
                        arrayList.add(this.c.b());
                        this.c.c();
                    }
                }
            }
            synchronized (this.d) {
                this.d.addAll(arrayList);
            }
            g();
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        ArrayList<App> arrayList;
        if (this.d.size() != 0) {
            synchronized (this.d) {
                arrayList = new ArrayList<>(this.d);
            }
            synchronized (b) {
                while (this.c.b() != null) {
                    this.c.c();
                }
                for (App a2 : arrayList) {
                    this.c.a(a2);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void h() {
        if (!net.media.android.bidder.base.common.b.a().e()) {
            Logger.debug("##PackageTracker##", "pushing package updates");
            synchronized (this.d) {
                net.media.android.bidder.base.analytics.b.a().a(AnalyticsEvent.Events.newSdkEvent(Constants.SDK_DATA_EVENT, 2).addProperty("local_apps", net.media.android.bidder.base.gson.a.b().b((Object) this.d)));
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        Logger.debug("##PackageTracker##", "called removeFromList for package " + str);
        synchronized (this.d) {
            Iterator<App> it = this.d.iterator();
            while (true) {
                if (it.hasNext()) {
                    App next = it.next();
                    if (next != null && next.getPackageName().equalsIgnoreCase(str)) {
                        Logger.debug("##PackageTracker##", "removing app with package name " + next.getPackageName());
                        it.remove();
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        h();
        g();
    }

    private static class a implements m.a<App> {
        private e a = new e();

        a() {
        }

        /* renamed from: b */
        public App a(byte[] bArr) {
            return (App) this.a.a((Reader) new InputStreamReader(new ByteArrayInputStream(bArr), "UTF-8"), App.class);
        }

        public void a(App app, OutputStream outputStream) {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            this.a.a((Object) app, (Appendable) outputStreamWriter);
            outputStreamWriter.close();
        }
    }

    private final class b extends ab {
        private b() {
        }

        /* synthetic */ b(cl clVar, AnonymousClass1 r2) {
            this();
        }

        public void a(Context context, Intent intent) {
            Logger.debug("##PackageTracker##", "app added/removed");
            if (!MNet.a()) {
                Logger.debug("##PackageTracker##", "SDK not initialized yet, abort.");
            } else if (intent != null && intent.getData() != null) {
                String encodedSchemeSpecificPart = intent.getData().getEncodedSchemeSpecificPart();
                if ("android.intent.action.PACKAGE_REMOVED".equals(intent.getAction())) {
                    cl.this.a(encodedSchemeSpecificPart);
                    if (!net.media.android.bidder.base.common.b.a().e()) {
                        net.media.android.bidder.base.analytics.b.a().a(AnalyticsEvent.Events.newEvent("package_removed").addProperty(Constants.APP_PACKAGE, encodedSchemeSpecificPart));
                    }
                } else if ("android.intent.action.PACKAGE_ADDED".equals(intent.getAction())) {
                    PackageManager packageManager = context.getPackageManager();
                    try {
                        PackageInfo packageInfo = packageManager.getPackageInfo(encodedSchemeSpecificPart, 0);
                        String charSequence = packageManager.getApplicationLabel(packageInfo.applicationInfo).toString();
                        String str = packageInfo.versionName;
                        synchronized (cl.this.d) {
                            cl.this.d.add(new App(charSequence, encodedSchemeSpecificPart, str));
                        }
                        cl.this.h();
                        cl.this.g();
                        if (!net.media.android.bidder.base.common.b.a().e()) {
                            net.media.android.bidder.base.analytics.b.a().a(AnalyticsEvent.Events.newEvent("package_added").addProperty(Constants.APP_PACKAGE, encodedSchemeSpecificPart));
                        }
                    } catch (PackageManager.NameNotFoundException e) {
                        Logger.error("##PackageTracker##", e.getLocalizedMessage());
                    }
                }
            }
        }
    }
}
