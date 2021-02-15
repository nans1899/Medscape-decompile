package bo.app;

import com.appboy.events.ContentCardsUpdatedEvent;
import com.appboy.events.FeedUpdatedEvent;
import com.appboy.models.IInAppMessage;
import com.appboy.models.response.ResponseError;
import com.appboy.support.AppboyLogger;
import java.net.URI;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class cp implements Runnable {
    private static final String a = AppboyLogger.getAppboyLogTag(cp.class);
    private final cv b;
    private final ad c;
    private final ad d;
    private final Map<String, String> e;
    private final g f;
    private final dk g;
    private final dq h;
    private final dg i;
    private final bq j;

    public cp(cv cvVar, d dVar, g gVar, ad adVar, ad adVar2, dk dkVar, bq bqVar, dq dqVar, dg dgVar) {
        this.b = cvVar;
        this.c = adVar;
        this.d = adVar2;
        Map<String, String> a2 = dVar.a();
        this.e = a2;
        this.b.a(a2);
        this.f = gVar;
        this.g = dkVar;
        this.j = bqVar;
        this.h = dqVar;
        this.i = dgVar;
    }

    public void run() {
        try {
            cl a2 = a();
            if (a2 != null) {
                a(a2);
                this.c.a(new ah(this.b), ah.class);
                this.c.a(new af(this.b), af.class);
                return;
            }
            AppboyLogger.w(a, "Api response was null, failing task.");
            this.b.a(this.d, new ResponseError("An error occurred during request processing, resulting in no valid response being received. Check the error log for more details."));
            this.c.a(new ae(this.b), ae.class);
        } catch (Exception e2) {
            if (e2 instanceof aw) {
                AppboyLogger.d(a, "Experienced network communication exception processing API response. Sending network error event.");
                this.c.a(new ag(this.b), ag.class);
            }
            AppboyLogger.w(a, "Experienced exception processing API response. Failing task.", e2);
        }
    }

    /* renamed from: bo.app.cp$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                bo.app.y[] r0 = bo.app.y.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                bo.app.y r1 = bo.app.y.GET     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001d }
                bo.app.y r1 = bo.app.y.POST     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: bo.app.cp.AnonymousClass1.<clinit>():void");
        }
    }

    private cl a() {
        URI a2 = eb.a(this.b.a());
        int i2 = AnonymousClass1.a[this.b.i().ordinal()];
        if (i2 == 1) {
            return new cl(this.f.a(a2, this.e), this.b, this.j);
        }
        if (i2 != 2) {
            String str = a;
            AppboyLogger.w(str, "Received a request with an unknown Http verb: [" + this.b.i() + "]");
            return null;
        }
        JSONObject g2 = this.b.g();
        if (g2 != null) {
            return new cl(this.f.a(a2, this.e, g2), this.b, this.j);
        }
        AppboyLogger.e(a, "Could not parse request parameters for put request to [%s], canceling request.");
        return null;
    }

    /* access modifiers changed from: package-private */
    public void a(cl clVar) {
        if (!clVar.e()) {
            this.b.a(this.d, clVar);
        } else {
            a(clVar.n());
            this.b.a(this.d, clVar.n());
        }
        b(clVar);
        this.b.a(this.c);
    }

    /* access modifiers changed from: package-private */
    public void b(cl clVar) {
        String e2 = this.j.e();
        if (clVar.a()) {
            try {
                FeedUpdatedEvent a2 = this.g.a(clVar.h(), e2);
                if (a2 != null) {
                    this.d.a(a2, FeedUpdatedEvent.class);
                }
            } catch (JSONException unused) {
                AppboyLogger.w(a, "Unable to update/publish feed.");
            }
        }
        if (clVar.g()) {
            try {
                ContentCardsUpdatedEvent a3 = this.i.a(clVar.m(), e2);
                if (a3 != null) {
                    this.d.a(a3, ContentCardsUpdatedEvent.class);
                }
            } catch (JSONException e3) {
                AppboyLogger.e(a, "Encountered JSON exception while parsing Content Cards update. Unable to publish Content Cards update event.", e3);
            }
        }
        if (clVar.c()) {
            this.h.a(clVar.j());
            this.c.a(new an(clVar.j()), an.class);
        }
        if (clVar.d()) {
            this.c.a(new au(clVar.k()), au.class);
        }
        if (clVar.b()) {
            cv cvVar = this.b;
            if (cvVar instanceof db) {
                db dbVar = (db) cvVar;
                IInAppMessage i2 = clVar.i();
                i2.setExpirationTimestamp(dbVar.k());
                this.c.a(new ak(dbVar.l(), i2, e2), ak.class);
            }
        }
        if (clVar.f()) {
            this.c.a(new aj(clVar.l()), aj.class);
        }
    }

    private void a(ResponseError responseError) {
        String str = a;
        AppboyLogger.e(str, "Received server error from request: " + responseError.getMessage());
    }
}
