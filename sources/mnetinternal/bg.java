package mnetinternal;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public final class bg {
    private static volatile bg a;
    private int b;
    /* access modifiers changed from: private */
    public int c;
    /* access modifiers changed from: private */
    public bh d;
    private String e;
    /* access modifiers changed from: private */
    public AtomicLong f = new AtomicLong(0);
    /* access modifiers changed from: private */
    public AtomicLong g = new AtomicLong(0);
    private ScheduledExecutorService h = Executors.newSingleThreadScheduledExecutor();
    private ExecutorService i = Executors.newSingleThreadExecutor();
    /* access modifiers changed from: private */
    public Map<String, bd> j = Collections.synchronizedMap(new WeakHashMap());
    private ViewTreeObserver.OnGlobalLayoutListener k = new ViewTreeObserver.OnGlobalLayoutListener() {
        public void onGlobalLayout() {
            bg.this.f.set(new Date().getTime());
            bg.this.d();
        }
    };
    private ViewTreeObserver.OnScrollChangedListener l = new ViewTreeObserver.OnScrollChangedListener() {
        public void onScrollChanged() {
            bg.this.f.set(new Date().getTime());
        }
    };

    private bg(Application application, int i2, int i3) {
        try {
            this.b = i2;
            this.c = i3;
            this.e = bf.b(application);
            b(application);
            e();
        } catch (Exception e2) {
            bi.a("##Parser", (Throwable) e2);
        }
    }

    public static void a(Application application) {
        a(application, 200, 400);
    }

    private static void a(Application application, int i2, int i3) {
        if (a == null) {
            synchronized (bg.class) {
                if (a == null) {
                    a = new bg(application, i2, i3);
                }
            }
        }
    }

    public static void a(boolean z) {
        bi.a(z);
    }

    /* access modifiers changed from: private */
    public void d() {
        this.i.submit(new ac() {
            public void a() {
                for (bo next : bp.a()) {
                    bd a2 = next.a(bg.this.d.a(), false);
                    if (a2 != null && a2.b()) {
                        bi.a("##Parser", "Parsing with " + next.getClass().getCanonicalName() + " for activity : " + bg.this.d.a().getLocalClassName());
                        bi.a("##Parser", "Crawler link : " + a2.d() + " for activity : " + bg.this.d.a().getLocalClassName());
                        Map d = bg.this.j;
                        bg bgVar = bg.this;
                        d.put(bgVar.c(bgVar.d.a()), a2);
                        return;
                    }
                }
            }
        });
    }

    private void b(Application application) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            public void onActivityStarted(Activity activity) {
            }

            public void onActivityStopped(Activity activity) {
            }

            public void onActivityResumed(Activity activity) {
                try {
                    bh unused = bg.this.d = new bh(activity);
                    long time = new Date().getTime();
                    bg.this.f.set(time);
                    bg.this.g.set(time);
                    if (bg.this.j.get(bg.this.c(activity)) == null) {
                        bg.this.d();
                    }
                    bg.this.b(activity);
                } catch (Exception e) {
                    bi.a("##Parser", (Throwable) e);
                }
            }

            public void onActivityPaused(Activity activity) {
                try {
                    bg.this.a(activity);
                    bd bdVar = (bd) bg.this.j.get(bg.this.c(activity));
                    if (bdVar != null) {
                        bdVar.f();
                    }
                } catch (Exception e) {
                    bi.a("##Parser", (Throwable) e);
                }
            }

            public void onActivityDestroyed(Activity activity) {
                try {
                    bg.this.j.remove(bg.this.c(activity));
                } catch (Exception e) {
                    bi.a("##Parser", (Throwable) e);
                }
            }
        });
    }

    private void e() {
        this.h.scheduleAtFixedRate(new ac() {
            public void a() {
                if (bg.this.g.get() != bg.this.f.get() && new Date().getTime() - bg.this.f.get() >= ((long) bg.this.c)) {
                    bg.this.g.set(bg.this.f.get());
                    bg.this.d();
                }
            }
        }, 0, (long) this.b, TimeUnit.MILLISECONDS);
    }

    /* access modifiers changed from: private */
    public void a(Activity activity) {
        if (Build.VERSION.SDK_INT >= 16) {
            activity.getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(this.k);
        }
        activity.getWindow().getDecorView().getViewTreeObserver().removeOnScrollChangedListener(this.l);
    }

    /* access modifiers changed from: private */
    public void b(Activity activity) {
        activity.getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(this.k);
        activity.getWindow().getDecorView().getViewTreeObserver().addOnScrollChangedListener(this.l);
    }

    public static String a() {
        if (a == null || a.d == null) {
            return null;
        }
        bi.a("##Parser", "getContent: " + a.d.a().getLocalClassName());
        bd bdVar = a.j.get(a.c(a.d.a()));
        if (bdVar == null) {
            return null;
        }
        if (bdVar.e() == a.d) {
            return bdVar.a();
        }
        a.d();
        return a.j.get(a.c(a.d.a())).a();
    }

    public static String b() {
        if (a == null) {
            return null;
        }
        if (a.d == null) {
            return a.e;
        }
        bd bdVar = a.j.get(a.c(a.d.a()));
        if (bdVar == null) {
            return bf.a(a.d.a(), (List<bk>) null);
        }
        if (bdVar.e() == a.d) {
            return bdVar.d();
        }
        a.d();
        return a.j.get(a.c(a.d.a())).d();
    }

    public static String c() {
        if (a == null) {
            return null;
        }
        if (a.d == null) {
            return a.e;
        }
        bd bdVar = a.j.get(a.c(a.d.a()));
        if (bdVar == null) {
            return bf.a(a.d.a());
        }
        if (bdVar.e() == a.d) {
            return bdVar.c();
        }
        a.d();
        return a.j.get(a.c(a.d.a())).c();
    }

    /* access modifiers changed from: private */
    public String c(Activity activity) {
        return activity.getLocalClassName() + "@" + Integer.toHexString(activity.hashCode());
    }
}
