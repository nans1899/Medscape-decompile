package bo.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import com.appboy.Constants;
import com.appboy.events.IEventSubscriber;
import com.appboy.receivers.AppboyActionReceiver;
import com.appboy.support.AppboyLogger;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class q {
    /* access modifiers changed from: private */
    public static final String a = AppboyLogger.getAppboyLogTag(q.class);
    /* access modifiers changed from: private */
    public static final int b = ((int) TimeUnit.MINUTES.toMillis(5));
    private final Context c;
    /* access modifiers changed from: private */
    public final s d;
    private final AlarmManager e;
    private final p f;
    private final BroadcastReceiver g;
    private final PendingIntent h;
    /* access modifiers changed from: private */
    public final Random i = new Random();
    /* access modifiers changed from: private */
    public aa j;
    /* access modifiers changed from: private */
    public long k;
    private boolean l;
    /* access modifiers changed from: private */
    public int m = 0;
    private volatile boolean n = false;

    public q(Context context, final ad adVar, s sVar, AlarmManager alarmManager, p pVar, String str) {
        this.c = context;
        this.d = sVar;
        this.e = alarmManager;
        this.f = pVar;
        this.j = aa.NO_SESSION;
        this.k = -1;
        this.h = PendingIntent.getBroadcast(this.c, str.hashCode(), new Intent(Constants.APPBOY_ACTION_RECEIVER_DATA_SYNC_INTENT_ACTION).setClass(context, AppboyActionReceiver.class), 134217728);
        this.g = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                final BroadcastReceiver.PendingResult goAsync = goAsync();
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            q.this.d.a(intent, (ConnectivityManager) context.getSystemService("connectivity"));
                            q.this.c();
                        } catch (Exception e) {
                            AppboyLogger.e(q.a, "Failed to process connectivity event.", e);
                            q.this.a(adVar, (Throwable) e);
                        }
                        goAsync.finish();
                    }
                }).start();
            }
        };
        AppboyLogger.d(a, "Registered broadcast filters");
    }

    public void a(ac acVar) {
        acVar.a(new IEventSubscriber<aq>() {
            /* renamed from: a */
            public void trigger(aq aqVar) {
                aa unused = q.this.j = aa.OPEN_SESSION;
                q.this.c();
            }
        }, aq.class);
        acVar.a(new IEventSubscriber<ar>() {
            /* renamed from: a */
            public void trigger(ar arVar) {
                aa unused = q.this.j = aa.NO_SESSION;
                q.this.c();
            }
        }, ar.class);
        acVar.b(new IEventSubscriber<ag>() {
            /* renamed from: a */
            public void trigger(ag agVar) {
                String f = q.a;
                AppboyLogger.d(f, "Handling network request failure. Previous sleep delay: " + q.this.m);
                int unused = q.this.m = Math.min(q.b, q.a(q.this.i, (int) q.this.k, q.this.m * 3));
                String f2 = q.a;
                AppboyLogger.d(f2, "New flush sleep delay: " + q.this.m + " default interval: " + q.this.k);
                q qVar = q.this;
                qVar.a(qVar.k + ((long) q.this.m));
            }
        }, ag.class);
        acVar.a(new IEventSubscriber<ah>() {
            /* renamed from: a */
            public void trigger(ah ahVar) {
                if (q.this.m != 0) {
                    String f = q.a;
                    AppboyLogger.d(f, "Received successful request flush. Default flush interval reset to " + q.this.k);
                    int unused = q.this.m = 0;
                    q qVar = q.this;
                    qVar.a(qVar.k);
                }
            }
        }, ah.class);
    }

    public synchronized void a(boolean z) {
        this.l = z;
        c();
        if (z) {
            b();
        } else {
            a();
        }
    }

    public synchronized boolean a() {
        if (this.n) {
            AppboyLogger.d(a, "The data sync policy is already running. Ignoring request.");
            return false;
        }
        AppboyLogger.d(a, "Data sync started");
        d();
        a(3000);
        this.n = true;
        return true;
    }

    public synchronized boolean b() {
        if (!this.n) {
            AppboyLogger.d(a, "The data sync policy is not running. Ignoring request.");
            return false;
        }
        AppboyLogger.d(a, "Data sync stopped");
        h();
        e();
        this.n = false;
        return true;
    }

    /* access modifiers changed from: protected */
    public void c() {
        long j2 = this.k;
        if (this.j == aa.NO_SESSION || this.l) {
            this.k = -1;
        } else {
            int i2 = AnonymousClass6.a[this.d.a().ordinal()];
            if (i2 == 1) {
                this.k = -1;
            } else if (i2 == 2) {
                this.k = this.f.a();
            } else if (i2 == 3 || i2 == 4) {
                this.k = this.f.c();
            } else {
                this.k = this.f.b();
            }
        }
        long j3 = this.k;
        if (j2 != j3) {
            a(j3);
            String str = a;
            AppboyLogger.d(str, "Dispatch state has changed from " + j2 + " to " + this.k + ".");
        }
    }

    /* renamed from: bo.app.q$6  reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] a;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                bo.app.z[] r0 = bo.app.z.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                bo.app.z r1 = bo.app.z.NONE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001d }
                bo.app.z r1 = bo.app.z.TWO_G     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0028 }
                bo.app.z r1 = bo.app.z.FOUR_G     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0033 }
                bo.app.z r1 = bo.app.z.WIFI     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x003e }
                bo.app.z r1 = bo.app.z.THREE_G     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: bo.app.q.AnonymousClass6.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public void d() {
        this.c.registerReceiver(this.g, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    /* access modifiers changed from: protected */
    public void e() {
        this.c.unregisterReceiver(this.g);
    }

    private void a(long j2, long j3) {
        this.e.setInexactRepeating(1, j2, j3, this.h);
    }

    private void h() {
        PendingIntent pendingIntent = this.h;
        if (pendingIntent != null) {
            this.e.cancel(pendingIntent);
        }
    }

    /* access modifiers changed from: private */
    public void a(ad adVar, Throwable th) {
        try {
            adVar.a(th, Throwable.class);
        } catch (Exception e2) {
            AppboyLogger.e(a, "Failed to log throwable.", e2);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(long j2) {
        if (this.e == null) {
            AppboyLogger.d(a, "Alarm manager was null. Ignoring request to reset it.");
        } else if (this.k <= 0) {
            AppboyLogger.d(a, "Cancelling alarm because delay value was not positive.");
            h();
        } else {
            a(du.c() + j2, this.k);
        }
    }

    static int a(Random random, int i2, int i3) {
        return random.nextInt(Math.abs(i2 - i3)) + Math.min(i2, i3);
    }
}
