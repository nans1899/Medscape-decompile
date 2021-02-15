package mnetinternal;

import android.content.Context;
import android.location.Location;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import net.media.android.bidder.base.logging.Logger;
import net.media.android.bidder.base.logging.MNetLog;

public final class ad {
    private static volatile ad a;
    /* access modifiers changed from: private */
    public Object b;
    /* access modifiers changed from: private */
    public AtomicBoolean c = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public Class d;
    /* access modifiers changed from: private */
    public Class e;
    /* access modifiers changed from: private */
    public Class f;
    /* access modifiers changed from: private */
    public Class g;
    /* access modifiers changed from: private */
    public Method h;
    private Class i;
    private Object j;
    private AtomicBoolean k = new AtomicBoolean(false);

    public interface a {
        void a(Location location);
    }

    private ad(Context context) {
        b(context);
    }

    public static void a(Context context) {
        if (a == null) {
            synchronized (ad.class) {
                if (a == null) {
                    a = new ad(context);
                }
            }
        }
    }

    public static void a(a aVar) {
        if (a.k.get()) {
            a.c(aVar);
        } else {
            a.b(aVar);
        }
    }

    public static boolean a() {
        return a.c();
    }

    public static boolean b() {
        return a.c.get();
    }

    private void b(final Context context) {
        aa.a((Runnable) new ac() {
            public void a() {
                try {
                    Class<?> cls = Class.forName("com.google.android.gms.common.api.GoogleApiClient");
                    Class<?> cls2 = Class.forName("com.google.android.gms.common.api.GoogleApiClient$Builder");
                    Object newInstance = cls2.getConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
                    Class unused = ad.this.g = Class.forName("com.google.android.gms.common.api.Api");
                    Method unused2 = ad.this.h = cls.getMethod("hasConnectedApi", new Class[]{ad.this.g});
                    Class<?> cls3 = Class.forName("com.google.android.gms.location.LocationServices");
                    Object newInstance2 = Array.newInstance(Class.forName("com.google.android.gms.common.api.Scope"), 0);
                    cls2.getMethod("addApiIfAvailable", new Class[]{ad.this.g, newInstance2.getClass()}).invoke(newInstance, new Object[]{cls3.getField("API").get((Object) null), newInstance2});
                    Class unused3 = ad.this.e = cls3;
                    Object unused4 = ad.this.b = cls2.getMethod("build", new Class[0]).invoke(newInstance, new Object[0]);
                    Logger.debug("##GMSHelper", "GClient built");
                    Object invoke = cls.getMethod("blockingConnect", new Class[]{Long.TYPE, TimeUnit.class}).invoke(ad.this.b, new Object[]{3000L, TimeUnit.MILLISECONDS});
                    Logger.debug("##GMSHelper", "GMS blocking connect");
                    if (((Boolean) Class.forName("com.google.android.gms.common.ConnectionResult").getMethod("isSuccess", new Class[0]).invoke(invoke, new Object[0])).booleanValue()) {
                        ad.this.c.set(true);
                        Logger.debug("##GMSHelper", "GMS initialized");
                    } else {
                        Logger.debug("##GMSHelper", "GMS not initialized");
                    }
                    Class unused5 = ad.this.d = cls;
                    Class unused6 = ad.this.f = Class.forName("com.google.android.gms.location.FusedLocationProviderApi");
                } catch (Exception unused7) {
                    ad.this.c(context);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void c(Context context) {
        try {
            this.i = Class.forName("com.google.android.gms.location.FusedLocationProviderClient");
            this.j = Class.forName("com.google.android.gms.location.LocationServices").getMethod("getFusedLocationProviderClient", new Class[]{Context.class}).invoke((Object) null, new Object[]{context});
            this.k.set(true);
            this.c.set(true);
        } catch (Exception unused) {
            MNetLog.info("Google Play services not enabled");
        }
    }

    private void b(a aVar) {
        Class cls;
        if (!this.c.get() || (cls = this.e) == null) {
            aVar.a((Location) null);
            return;
        }
        try {
            Object obj = cls.getField("FusedLocationApi").get((Object) null);
            aVar.a((Location) this.f.getMethod("getLastLocation", new Class[]{this.d}).invoke(obj, new Object[]{this.b}));
        } catch (Exception e2) {
            Logger.notify("##GMSHelper", "Error getting location from fused location provider API", e2);
            aVar.a((Location) null);
        }
    }

    private void c(final a aVar) {
        Class cls;
        if (!this.c.get() || (cls = this.i) == null) {
            aVar.a((Location) null);
            return;
        }
        try {
            final Object invoke = cls.getMethod("getLastLocation", new Class[0]).invoke(this.j, new Object[0]);
            Class<?> cls2 = Class.forName("com.google.android.gms.tasks.OnCompleteListener");
            Object newProxyInstance = Proxy.newProxyInstance(cls2.getClassLoader(), new Class[]{cls2}, new InvocationHandler() {
                public Object invoke(Object obj, Method method, Object[] objArr) {
                    if (!method.getName().equalsIgnoreCase("onComplete")) {
                        return null;
                    }
                    try {
                        aVar.a((Location) invoke.getClass().getMethod("getResult", new Class[0]).invoke(objArr[0], new Object[0]));
                        return null;
                    } catch (Exception unused) {
                        return null;
                    }
                }
            });
            invoke.getClass().getMethod("addOnCompleteListener", new Class[]{Executor.class, cls2}).invoke(invoke, new Object[]{aa.a(), newProxyInstance});
        } catch (Exception e2) {
            Logger.notify("##GMSHelper", "Error getting location from fused location provider client", e2);
            aVar.a((Location) null);
        }
    }

    private boolean c() {
        Class cls;
        if (!this.k.get()) {
            if (this.c.get() && (cls = this.e) != null) {
                try {
                    return ((Boolean) this.h.invoke(this.b, new Object[]{cls.getField("API").get((Object) null)})).booleanValue();
                } catch (Exception e2) {
                    Logger.error("##GMSHelper", "LocationServices connection check error", e2);
                }
            }
            return false;
        } else if (!this.c.get() || this.i == null) {
            return false;
        } else {
            return true;
        }
    }
}
