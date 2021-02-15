package bo.app;

import android.app.Activity;
import bo.app.cj;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.models.outgoing.Feedback;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import com.appboy.support.ValidationUtils;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;
import org.json.JSONObject;

public class bl implements bq {
    private static final String a = AppboyLogger.getAppboyLogTag(bl.class);
    private AtomicInteger b = new AtomicInteger(0);
    private AtomicInteger c = new AtomicInteger(0);
    private volatile String d = "";
    private final Object e = new Object();
    private final Object f = new Object();
    private final bn g;
    private final bm h;
    private final t i;
    private final ad j;
    private final br k;
    private final AppboyConfigurationProvider l;
    private final dq m;
    private final bh n;
    private final String o;
    private final dp p;
    private boolean q = false;
    private Class<? extends Activity> r = null;

    public bl(bn bnVar, t tVar, ad adVar, br brVar, AppboyConfigurationProvider appboyConfigurationProvider, dq dqVar, bh bhVar, String str, boolean z, bm bmVar, dp dpVar) {
        this.g = bnVar;
        this.i = tVar;
        this.j = adVar;
        this.k = brVar;
        this.l = appboyConfigurationProvider;
        this.q = z;
        this.o = str;
        this.m = dqVar;
        this.n = bhVar;
        this.h = bmVar;
        this.p = dpVar;
    }

    public cc a() {
        if (this.p.a()) {
            AppboyLogger.w(a, "SDK is disabled. Returning null session.");
            return null;
        }
        cc a2 = this.g.a();
        String str = a;
        AppboyLogger.i(str, "Completed the openSession call. Starting or continuing session " + a2.a());
        return a2;
    }

    public cc a(Activity activity) {
        if (this.p.a()) {
            AppboyLogger.w(a, "SDK is disabled. Returning null session.");
            return null;
        }
        cc a2 = a();
        this.r = activity.getClass();
        this.h.a();
        String str = a;
        AppboyLogger.v(str, "Opened session with activity: " + activity.getLocalClassName());
        return a2;
    }

    public cc b(Activity activity) {
        if (this.p.a()) {
            AppboyLogger.w(a, "SDK is disabled. Returning null session.");
            return null;
        } else if (this.r != null && !activity.getClass().equals(this.r)) {
            return null;
        } else {
            this.h.b();
            String str = a;
            AppboyLogger.v(str, "Closed session with activity: " + activity.getLocalClassName());
            return this.g.b();
        }
    }

    public cd b() {
        return this.g.c();
    }

    public void c() {
        if (this.p.a()) {
            AppboyLogger.w(a, "SDK is disabled. Not force closing session.");
            return;
        }
        this.r = null;
        this.g.e();
    }

    public boolean a(bz bzVar) {
        boolean z = false;
        if (this.p.a()) {
            String str = a;
            AppboyLogger.w(str, "SDK is disabled. Not logging event: " + bzVar);
            return false;
        }
        synchronized (this.e) {
            if (bzVar != null) {
                try {
                    if (this.g.d() || this.g.c() == null) {
                        String str2 = a;
                        AppboyLogger.d(str2, "Not adding session id to event: " + ed.a((JSONObject) bzVar.forJsonPut()));
                    } else {
                        bzVar.a(this.g.c());
                        z = true;
                    }
                    if (!StringUtils.isNullOrEmpty(e())) {
                        bzVar.a(e());
                    } else {
                        String str3 = a;
                        AppboyLogger.d(str3, "Not adding user id to event: " + ed.a((JSONObject) bzVar.forJsonPut()));
                    }
                    if (w.b(bzVar.b())) {
                        AppboyLogger.d(a, "Publishing an internal push body clicked event for any awaiting triggers.");
                        c(bzVar);
                    }
                    this.n.a(bzVar);
                    if (!w.a(bzVar.b()) || z) {
                        this.i.a(bzVar);
                    } else {
                        AppboyLogger.d(a, "Adding push click to dispatcher pending list");
                        this.i.b(bzVar);
                    }
                    if (bzVar.b().equals(w.SESSION_START)) {
                        this.i.a(bzVar.g());
                    }
                    if (!z) {
                        d();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                AppboyLogger.e(a, "Appboy manager received null event.");
                throw new NullPointerException();
            }
        }
        return true;
    }

    public void a(Throwable th) {
        try {
            if (b(th)) {
                AppboyLogger.w(a, "Not logging duplicate error.");
            } else {
                a((bz) cf.a(th, b()));
            }
        } catch (JSONException e2) {
            String str = a;
            AppboyLogger.e(str, "Failed to create error event from " + th + ".", e2);
        } catch (Exception e3) {
            AppboyLogger.e(a, "Failed to log error.", e3);
        }
    }

    public void a(av avVar) {
        try {
            if (b((Throwable) avVar)) {
                AppboyLogger.w(a, "Not logging duplicate database exception.");
            } else {
                a((bz) cf.a(avVar, b()));
            }
        } catch (JSONException e2) {
            String str = a;
            AppboyLogger.e(str, "Failed to create database exception event from " + avVar + ".", e2);
        } catch (Exception e3) {
            AppboyLogger.e(a, "Failed to log error.", e3);
        }
    }

    public void a(String str, String str2, boolean z) {
        if (str == null || !ValidationUtils.isValidEmailAddress(str)) {
            throw new IllegalArgumentException("Reply to email address is invalid");
        } else if (!StringUtils.isNullOrBlank(str2)) {
            a((cv) new cs(this.l.getBaseUrlForRequests(), new Feedback(str2, str, z, this.k.a(), e())));
        } else {
            throw new IllegalArgumentException("Feedback message cannot be null or blank");
        }
    }

    public void d() {
        a(new cj.a());
    }

    public void a(cj.a aVar) {
        if (aVar == null) {
            AppboyLogger.d(a, "Cannot request data sync with null respond with object");
            return;
        }
        dq dqVar = this.m;
        if (dqVar != null && dqVar.l()) {
            aVar.a(new ci(this.m.g()));
        }
        aVar.a(e());
        cj c2 = aVar.c();
        if (c2.c() && (c2.d() || c2.e())) {
            this.m.a(false);
        }
        a((cv) new cr(this.l.getBaseUrlForRequests(), c2));
    }

    public void a(ca caVar) {
        AppboyLogger.d(a, "Posting geofence request for location.");
        a((cv) new ct(this.l.getBaseUrlForRequests(), caVar));
    }

    public void b(bz bzVar) {
        AppboyLogger.d(a, "Posting geofence report for geofence event.");
        a((cv) new cu(this.l.getBaseUrlForRequests(), bzVar));
    }

    public void a(em emVar, fk fkVar) {
        a((cv) new db(this.l.getBaseUrlForRequests(), emVar, fkVar, this, e()));
    }

    public void a(fk fkVar) {
        this.j.a(new at(fkVar), at.class);
    }

    public void a(List<String> list, long j2) {
        a((cv) new dc(this.l.getBaseUrlForRequests(), list, j2, this.o));
    }

    public void a(boolean z) {
        this.q = z;
    }

    public void a(long j2, long j3) {
        this.i.a((cv) new cq(this.l.getBaseUrlForRequests(), j2, j3, this.o));
    }

    public String e() {
        return this.o;
    }

    private boolean b(Throwable th) {
        synchronized (this.f) {
            this.b.getAndIncrement();
            if (this.d.equals(th.getMessage()) && this.c.get() > 3 && this.b.get() < 100) {
                return true;
            }
            if (this.d.equals(th.getMessage())) {
                this.c.getAndIncrement();
            } else {
                this.c.set(0);
            }
            if (this.b.get() >= 100) {
                this.b.set(0);
            }
            this.d = th.getMessage();
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(cv cvVar) {
        if (this.p.a()) {
            AppboyLogger.w(a, "SDK is disabled. Not adding request to dispatch.");
        } else {
            this.i.a(cvVar);
        }
    }

    /* access modifiers changed from: package-private */
    public void c(bz bzVar) {
        JSONObject c2 = bzVar.c();
        if (c2 != null) {
            String optString = c2.optString("cid", (String) null);
            if (bzVar.b().equals(w.PUSH_NOTIFICATION_TRACKING)) {
                this.j.a(new as(optString, bzVar), as.class);
                return;
            }
            return;
        }
        AppboyLogger.w(a, "Event json was null. Not publishing push clicked trigger event.");
    }
}
