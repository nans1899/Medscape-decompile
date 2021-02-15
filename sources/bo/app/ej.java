package bo.app;

import android.app.AlarmManager;
import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.support.AppboyLogger;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class ej {
    /* access modifiers changed from: private */
    public static final String a = AppboyLogger.getAppboyLogTag(ej.class);
    /* access modifiers changed from: private */
    public final dt b;
    private final dh c;
    /* access modifiers changed from: private */
    public final ac d;
    /* access modifiers changed from: private */
    public final n e;
    private final bl f;
    private final dk g;
    private final ab h;
    private final ThreadPoolExecutor i;
    private final d j;
    /* access modifiers changed from: private */
    public final q k;
    private final bn l;
    private final bt m;
    private final fy n;
    private final dq o;
    private final bi p;
    private final bh q;
    private final dg r;

    public ej(Context context, l lVar, AppboyConfigurationProvider appboyConfigurationProvider, ad adVar, bg bgVar, bu buVar, boolean z, boolean z2, bw bwVar) {
        de deVar;
        Context context2 = context;
        AppboyConfigurationProvider appboyConfigurationProvider2 = appboyConfigurationProvider;
        String a2 = lVar.a();
        String ceVar = appboyConfigurationProvider.getAppboyApiKey().toString();
        dp dpVar = new dp(context2);
        ax axVar = new ax();
        this.i = new ThreadPoolExecutor(ei.a(), ei.b(), ei.c(), TimeUnit.SECONDS, ei.d(), axVar);
        this.d = new ac(this.i, dpVar);
        this.o = new dq(context2, ceVar);
        if (a2.equals("")) {
            this.b = new dt(context2, buVar, this.o, dpVar);
            this.c = new dh(context2);
            deVar = de.a(context2, (String) null, ceVar);
        } else {
            this.b = new dt(context, a2, ceVar, buVar, this.o, dpVar);
            this.c = new dh(context2, a2, ceVar);
            deVar = de.a(context2, a2, ceVar);
        }
        bo boVar = new bo(context2, appboyConfigurationProvider2, bgVar, this.c);
        this.j = new d();
        r rVar = new r(this.b, boVar, appboyConfigurationProvider2);
        dj djVar = new dj(new ds(context2, a2, ceVar), this.d);
        az azVar = new az(axVar);
        axVar.a(new ay(this.d));
        this.q = new bh(a(context2, a2, ceVar), a(deVar, azVar));
        AlarmManager alarmManager = (AlarmManager) context2.getSystemService(NotificationCompat.CATEGORY_ALARM);
        ac acVar = this.d;
        dq dqVar = this.o;
        bo boVar2 = boVar;
        bn bnVar = r1;
        int sessionTimeoutSeconds = appboyConfigurationProvider.getSessionTimeoutSeconds();
        bn bnVar2 = new bn(context, djVar, acVar, alarmManager, dqVar, sessionTimeoutSeconds, appboyConfigurationProvider.getIsSessionStartBasedTimeoutEnabled());
        this.l = bnVar;
        this.g = new dk(context2, a2);
        this.r = new dg(context2, a2, ceVar);
        da daVar = new da(this.j, e.a(), this.d, adVar, this.i, this.g, this.o, this.r);
        q qVar = new q(context, this.d, new o(), alarmManager, new p(context2), a2);
        this.k = qVar;
        qVar.a(this.d);
        this.k.a(z2);
        AppboyConfigurationProvider appboyConfigurationProvider3 = appboyConfigurationProvider;
        ax axVar2 = axVar;
        n nVar = r1;
        n nVar2 = new n(appboyConfigurationProvider3, this.d, daVar, rVar, axVar2, z);
        this.e = nVar;
        bm bmVar = new bm(context2, this.d, this.o);
        bn bnVar3 = this.l;
        bn bnVar4 = bnVar3;
        String str = ceVar;
        String str2 = a2;
        this.f = new bl(bnVar4, this.e, this.d, boVar2, appboyConfigurationProvider, this.o, this.q, str2, z2, bmVar, dpVar);
        Context context3 = context;
        AppboyConfigurationProvider appboyConfigurationProvider4 = appboyConfigurationProvider2;
        da daVar2 = daVar;
        this.n = new fy(context3, this.f, this.i, this.d, appboyConfigurationProvider, str2, str);
        this.p = new bi(context3, str, this.f, appboyConfigurationProvider, this.o);
        if (!z && (daVar2 instanceof da)) {
            daVar2.a((bq) this.f);
        }
        this.g.a(this.f);
        this.r.a(this.f);
        this.m = new bk(context2, this.f, appboyConfigurationProvider4, this.o);
        bt btVar = this.m;
        n nVar3 = this.e;
        bl blVar = this.f;
        dt dtVar = this.b;
        dh dhVar = this.c;
        dq dqVar2 = this.o;
        fy fyVar = this.n;
        this.h = new ab(context, btVar, nVar3, blVar, dtVar, dhVar, dqVar2, fyVar, fyVar.a(), this.q, this.p, bwVar, adVar);
    }

    public dq a() {
        return this.o;
    }

    public q b() {
        return this.k;
    }

    public ab c() {
        return this.h;
    }

    public bl d() {
        return this.f;
    }

    public n e() {
        return this.e;
    }

    public ac f() {
        return this.d;
    }

    public dt g() {
        return this.b;
    }

    public ThreadPoolExecutor h() {
        return this.i;
    }

    public dk i() {
        return this.g;
    }

    public bt j() {
        return this.m;
    }

    public bh k() {
        return this.q;
    }

    public fy l() {
        return this.n;
    }

    public bi m() {
        return this.p;
    }

    public dg n() {
        return this.r;
    }

    public void o() {
        this.i.execute(new Runnable() {
            /* JADX WARNING: Can't wrap try/catch for region: R(5:3|4|(3:6|7|8)|9|10) */
            /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002e */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r3 = this;
                    bo.app.ej r0 = bo.app.ej.this     // Catch:{ Exception -> 0x0042 }
                    bo.app.dt r0 = r0.b     // Catch:{ Exception -> 0x0042 }
                    monitor-enter(r0)     // Catch:{ Exception -> 0x0042 }
                    bo.app.ej r1 = bo.app.ej.this     // Catch:{ all -> 0x003f }
                    bo.app.dt r1 = r1.b     // Catch:{ all -> 0x003f }
                    boolean r1 = r1.c()     // Catch:{ all -> 0x003f }
                    if (r1 == 0) goto L_0x002e
                    java.lang.String r1 = bo.app.ej.a     // Catch:{ all -> 0x003f }
                    java.lang.String r2 = "User cache was locked, waiting."
                    com.appboy.support.AppboyLogger.i(r1, r2)     // Catch:{ all -> 0x003f }
                    bo.app.ej r1 = bo.app.ej.this     // Catch:{ InterruptedException -> 0x002e }
                    bo.app.dt r1 = r1.b     // Catch:{ InterruptedException -> 0x002e }
                    r1.wait()     // Catch:{ InterruptedException -> 0x002e }
                    java.lang.String r1 = bo.app.ej.a     // Catch:{ InterruptedException -> 0x002e }
                    java.lang.String r2 = "User cache notified."
                    com.appboy.support.AppboyLogger.d(r1, r2)     // Catch:{ InterruptedException -> 0x002e }
                L_0x002e:
                    monitor-exit(r0)     // Catch:{ all -> 0x003f }
                    bo.app.ej r0 = bo.app.ej.this     // Catch:{ Exception -> 0x0042 }
                    bo.app.n r0 = r0.e     // Catch:{ Exception -> 0x0042 }
                    bo.app.ej r1 = bo.app.ej.this     // Catch:{ Exception -> 0x0042 }
                    bo.app.ac r1 = r1.d     // Catch:{ Exception -> 0x0042 }
                    r0.a((bo.app.ac) r1)     // Catch:{ Exception -> 0x0042 }
                    goto L_0x004c
                L_0x003f:
                    r1 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x003f }
                    throw r1     // Catch:{ Exception -> 0x0042 }
                L_0x0042:
                    r0 = move-exception
                    java.lang.String r1 = bo.app.ej.a
                    java.lang.String r2 = "Exception while shutting down dispatch manager. Continuing."
                    com.appboy.support.AppboyLogger.w(r1, r2, r0)
                L_0x004c:
                    bo.app.ej r0 = bo.app.ej.this     // Catch:{ Exception -> 0x0056 }
                    bo.app.q r0 = r0.k     // Catch:{ Exception -> 0x0056 }
                    r0.b()     // Catch:{ Exception -> 0x0056 }
                    goto L_0x0060
                L_0x0056:
                    r0 = move-exception
                    java.lang.String r1 = bo.app.ej.a
                    java.lang.String r2 = "Exception while un-registering data refresh broadcast receivers. Continuing."
                    com.appboy.support.AppboyLogger.w(r1, r2, r0)
                L_0x0060:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: bo.app.ej.AnonymousClass1.run():void");
            }
        });
    }

    private dl a(de deVar, az azVar) {
        return new di(new df(new Cdo(deVar), azVar), this.d);
    }

    private dl a(Context context, String str, String str2) {
        return new di(new df(new dr(context, str, str2), this.i), this.d);
    }
}
