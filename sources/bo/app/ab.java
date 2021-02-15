package bo.app;

import android.content.Context;
import bo.app.cj;
import com.appboy.Appboy;
import com.appboy.AppboyInternal;
import com.appboy.events.IEventSubscriber;
import com.appboy.events.InAppMessageEvent;
import com.appboy.support.AppboyLogger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;

public class ab {
    /* access modifiers changed from: private */
    public static final String c = AppboyLogger.getAppboyLogTag(ab.class);
    AtomicBoolean a = new AtomicBoolean(false);
    long b = 0;
    /* access modifiers changed from: private */
    public final bt d;
    /* access modifiers changed from: private */
    public final t e;
    /* access modifiers changed from: private */
    public final bq f;
    /* access modifiers changed from: private */
    public final Context g;
    /* access modifiers changed from: private */
    public final dt h;
    /* access modifiers changed from: private */
    public final dh i;
    /* access modifiers changed from: private */
    public final dq j;
    /* access modifiers changed from: private */
    public final fu k;
    /* access modifiers changed from: private */
    public final bh l;
    /* access modifiers changed from: private */
    public final bi m;
    /* access modifiers changed from: private */
    public final bw n;
    /* access modifiers changed from: private */
    public final ad o;
    /* access modifiers changed from: private */
    public final fw p;
    /* access modifiers changed from: private */
    public AtomicBoolean q = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public as r;

    public ab(Context context, bt btVar, t tVar, bl blVar, dt dtVar, dh dhVar, dq dqVar, fu fuVar, fw fwVar, bh bhVar, bi biVar, bw bwVar, ad adVar) {
        this.d = btVar;
        this.e = tVar;
        this.f = blVar;
        this.g = context;
        this.h = dtVar;
        this.i = dhVar;
        this.j = dqVar;
        this.k = fuVar;
        this.p = fwVar;
        this.l = bhVar;
        this.m = biVar;
        this.n = bwVar;
        this.o = adVar;
    }

    public void a(ac acVar) {
        acVar.a(b(), ae.class);
        acVar.a(e(), ao.class);
        acVar.a(g(), ap.class);
        acVar.a(j(), as.class);
        acVar.a(h(), an.class);
        acVar.a(a((Semaphore) null), Throwable.class);
        acVar.a(o(), av.class);
        acVar.a(k(), au.class);
        acVar.a(f(), al.class);
        acVar.a(a(), af.class);
        acVar.a(i(), aj.class);
        acVar.a(l(), at.class);
        acVar.a(m(), ak.class);
    }

    /* access modifiers changed from: protected */
    public IEventSubscriber<af> a() {
        return new IEventSubscriber<af>() {
            /* renamed from: a */
            public void trigger(af afVar) {
                cv a2 = afVar.a();
                cj e = a2.e();
                if (e != null && e.c()) {
                    ab.this.j.a(false);
                }
                ch c = a2.c();
                if (c != null) {
                    ab.this.i.b(c, true);
                }
                ck d = a2.d();
                if (d != null) {
                    ab.this.h.b(d, true);
                }
                bx f = a2.f();
                if (f != null) {
                    for (bz b : f.a()) {
                        ab.this.l.b(b);
                    }
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public IEventSubscriber<ae> b() {
        return new IEventSubscriber<ae>() {
            /* renamed from: a */
            public void trigger(ae aeVar) {
                cv a2 = aeVar.a();
                cj e = a2.e();
                if (e != null) {
                    if (e.d()) {
                        ab.this.c();
                        ab.this.d();
                    }
                    if (e.c()) {
                        ab.this.j.a(true);
                    }
                }
                ch c = a2.c();
                if (c != null) {
                    ab.this.i.b(c, false);
                }
                ck d = a2.d();
                if (d != null) {
                    ab.this.h.b(d, false);
                }
                bx f = a2.f();
                if (f != null) {
                    for (bz a3 : f.a()) {
                        ab.this.e.a(a3);
                    }
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public void c() {
        if (this.a.compareAndSet(true, false)) {
            this.k.a((fk) new fn());
        }
    }

    /* access modifiers changed from: protected */
    public void d() {
        if (this.q.compareAndSet(true, false) && this.r.a() != null) {
            this.k.a((fk) new fp(this.r.a(), this.r.b()));
            this.r = null;
        }
    }

    /* access modifiers changed from: protected */
    public IEventSubscriber<ao> e() {
        return new IEventSubscriber<ao>() {
            /* renamed from: a */
            public void trigger(ao aoVar) {
                AppboyLogger.d(ab.c, "Session start event for new session received.");
                ab.this.f.a((bz) cf.k());
                ab.this.d.a();
                ab.this.n();
                AppboyInternal.requestGeofenceRefresh(ab.this.g, false);
                ab.this.h.d();
            }
        };
    }

    /* access modifiers changed from: protected */
    public IEventSubscriber<al> f() {
        return new IEventSubscriber<al>() {
            /* renamed from: a */
            public void trigger(al alVar) {
                ab.this.n();
            }
        };
    }

    /* access modifiers changed from: protected */
    public IEventSubscriber<ap> g() {
        return new IEventSubscriber<ap>() {
            /* renamed from: a */
            public void trigger(ap apVar) {
                ab.this.a(apVar);
                Appboy.getInstance(ab.this.g).requestImmediateDataFlush();
            }
        };
    }

    /* access modifiers changed from: protected */
    public IEventSubscriber<an> h() {
        return new IEventSubscriber<an>() {
            /* renamed from: a */
            public void trigger(an anVar) {
                ab.this.m.a(anVar.a());
                ab.this.n.a(anVar.a());
            }
        };
    }

    /* access modifiers changed from: protected */
    public IEventSubscriber<aj> i() {
        return new IEventSubscriber<aj>() {
            /* renamed from: a */
            public void trigger(aj ajVar) {
                ab.this.m.a(ajVar.a());
            }
        };
    }

    /* access modifiers changed from: protected */
    public IEventSubscriber<as> j() {
        return new IEventSubscriber<as>() {
            /* renamed from: a */
            public void trigger(as asVar) {
                ab.this.q.set(true);
                as unused = ab.this.r = asVar;
                AppboyLogger.i(ab.c, "Requesting trigger update due to trigger-eligible push click event");
                ab.this.f.a(new cj.a().b());
            }
        };
    }

    /* access modifiers changed from: protected */
    public IEventSubscriber<au> k() {
        return new IEventSubscriber<au>() {
            /* renamed from: a */
            public void trigger(au auVar) {
                ab.this.k.a(auVar.a());
                ab.this.c();
                ab.this.d();
            }
        };
    }

    /* access modifiers changed from: protected */
    public IEventSubscriber<at> l() {
        return new IEventSubscriber<at>() {
            /* renamed from: a */
            public void trigger(at atVar) {
                ab.this.k.a(atVar.a());
            }
        };
    }

    /* access modifiers changed from: protected */
    public IEventSubscriber<Throwable> a(final Semaphore semaphore) {
        return new IEventSubscriber<Throwable>() {
            /* renamed from: a */
            public void trigger(Throwable th) {
                Semaphore semaphore;
                try {
                    ab.this.f.a(th);
                    semaphore = semaphore;
                    if (semaphore == null) {
                        return;
                    }
                } catch (Exception e) {
                    AppboyLogger.e(ab.c, "Failed to log error.", e);
                    semaphore = semaphore;
                    if (semaphore == null) {
                        return;
                    }
                } catch (Throwable th2) {
                    Semaphore semaphore2 = semaphore;
                    if (semaphore2 != null) {
                        semaphore2.release();
                    }
                    throw th2;
                }
                semaphore.release();
            }
        };
    }

    /* access modifiers changed from: protected */
    public IEventSubscriber<ak> m() {
        return new IEventSubscriber<ak>() {
            /* renamed from: a */
            public void trigger(ak akVar) {
                ek a2 = akVar.a();
                synchronized (ab.this.p) {
                    if (ab.this.p.a(a2)) {
                        ab.this.o.a(new InAppMessageEvent(akVar.b(), akVar.c()), InAppMessageEvent.class);
                        ab.this.p.a(a2, du.a());
                        ab.this.k.a(du.a());
                    } else {
                        String p = ab.c;
                        AppboyLogger.d(p, "Could not publish in-app message with trigger action id: " + a2.b());
                    }
                }
            }
        };
    }

    /* access modifiers changed from: package-private */
    public void n() {
        if (this.b + 5 < du.a()) {
            this.a.set(true);
            AppboyLogger.d(c, "Requesting trigger refresh.");
            this.f.a(new cj.a().b());
            this.b = du.a();
        }
    }

    /* access modifiers changed from: protected */
    public IEventSubscriber<av> o() {
        return new IEventSubscriber<av>() {
            /* renamed from: a */
            public void trigger(av avVar) {
                try {
                    ab.this.f.a(avVar);
                } catch (Exception e) {
                    AppboyLogger.e(ab.c, "Failed to log the database exception.", e);
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public void a(ap apVar) {
        try {
            cc a2 = apVar.a();
            cf a3 = cf.a(a2.f());
            a3.a(a2.a());
            this.f.a((bz) a3);
        } catch (JSONException unused) {
            AppboyLogger.w(c, "Could not create session end event.");
        }
    }
}
