package bo.app;

import android.app.Activity;
import com.appboy.events.IEventSubscriber;
import com.appboy.support.AppboyLogger;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;

public class ac implements ad {
    private static final String a = AppboyLogger.getAppboyLogTag(ac.class);
    private final ConcurrentMap<Activity, ConcurrentMap<Class, CopyOnWriteArraySet<IEventSubscriber>>> b = new ConcurrentHashMap();
    private final ConcurrentMap<Class, CopyOnWriteArraySet<IEventSubscriber>> c = new ConcurrentHashMap();
    private final ConcurrentMap<Class, CopyOnWriteArraySet<IEventSubscriber>> d = new ConcurrentHashMap();
    private final Executor e;
    private final dp f;
    private final Object g = new Object();
    private final Object h = new Object();
    private final Object i = new Object();

    public ac(Executor executor, dp dpVar) {
        this.e = executor;
        this.f = dpVar;
    }

    public <T> boolean a(IEventSubscriber<T> iEventSubscriber, Class<T> cls) {
        boolean a2;
        synchronized (this.h) {
            a2 = a(iEventSubscriber, cls, this.c);
        }
        return a2;
    }

    public <T> boolean b(IEventSubscriber<T> iEventSubscriber, Class<T> cls) {
        boolean a2;
        synchronized (this.i) {
            a2 = a(iEventSubscriber, cls, this.d);
        }
        return a2;
    }

    public <T> boolean c(IEventSubscriber<T> iEventSubscriber, Class<T> cls) {
        boolean a2;
        synchronized (this.h) {
            a2 = a((CopyOnWriteArraySet<IEventSubscriber>) (CopyOnWriteArraySet) this.c.get(cls), iEventSubscriber);
        }
        return a2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00e2, code lost:
        if (r0.isEmpty() == false) goto L_0x00e6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> void a(final T r8, final java.lang.Class<T> r9) {
        /*
            r7 = this;
            bo.app.dp r0 = r7.f
            boolean r0 = r0.a()
            if (r0 == 0) goto L_0x002f
            java.lang.String r0 = a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "SDK is disabled. Not publishing event class: "
            r1.append(r2)
            java.lang.String r9 = r9.getName()
            r1.append(r9)
            java.lang.String r9 = " and message: "
            r1.append(r9)
            java.lang.String r8 = r8.toString()
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            com.appboy.support.AppboyLogger.d(r0, r8)
            return
        L_0x002f:
            java.lang.String r0 = a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r9.getName()
            r1.append(r2)
            java.lang.String r2 = " fired: "
            r1.append(r2)
            java.lang.String r2 = r8.toString()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r2 = 0
            com.appboy.support.AppboyLogger.d((java.lang.String) r0, (java.lang.String) r1, (boolean) r2)
            java.util.concurrent.ConcurrentMap<android.app.Activity, java.util.concurrent.ConcurrentMap<java.lang.Class, java.util.concurrent.CopyOnWriteArraySet<com.appboy.events.IEventSubscriber>>> r0 = r7.b
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x005b:
            boolean r1 = r0.hasNext()
            r3 = 1
            if (r1 == 0) goto L_0x008c
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r4 = r1.getValue()
            java.util.concurrent.ConcurrentMap r4 = (java.util.concurrent.ConcurrentMap) r4
            java.lang.Object r4 = r4.get(r9)
            java.util.concurrent.CopyOnWriteArraySet r4 = (java.util.concurrent.CopyOnWriteArraySet) r4
            if (r4 == 0) goto L_0x005b
            boolean r5 = r4.isEmpty()
            if (r5 != 0) goto L_0x005b
            java.lang.Object r1 = r1.getKey()
            android.app.Activity r1 = (android.app.Activity) r1
            bo.app.ac$1 r2 = new bo.app.ac$1
            r2.<init>(r9, r4, r8)
            r1.runOnUiThread(r2)
            r2 = 1
            goto L_0x005b
        L_0x008c:
            java.util.concurrent.ConcurrentMap<java.lang.Class, java.util.concurrent.CopyOnWriteArraySet<com.appboy.events.IEventSubscriber>> r0 = r7.c
            java.lang.Object r0 = r0.get(r9)
            java.util.concurrent.CopyOnWriteArraySet r0 = (java.util.concurrent.CopyOnWriteArraySet) r0
            if (r0 == 0) goto L_0x00bc
            java.util.concurrent.CopyOnWriteArraySet r1 = r7.a(r9, (java.util.concurrent.CopyOnWriteArraySet<com.appboy.events.IEventSubscriber>) r0)
            java.util.Iterator r1 = r1.iterator()
        L_0x009e:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x00b5
            java.lang.Object r4 = r1.next()
            com.appboy.events.IEventSubscriber r4 = (com.appboy.events.IEventSubscriber) r4
            java.util.concurrent.Executor r5 = r7.e
            bo.app.ac$2 r6 = new bo.app.ac$2
            r6.<init>(r4, r8)
            r5.execute(r6)
            goto L_0x009e
        L_0x00b5:
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x00bc
            r2 = 1
        L_0x00bc:
            java.util.concurrent.ConcurrentMap<java.lang.Class, java.util.concurrent.CopyOnWriteArraySet<com.appboy.events.IEventSubscriber>> r0 = r7.d
            java.lang.Object r0 = r0.get(r9)
            java.util.concurrent.CopyOnWriteArraySet r0 = (java.util.concurrent.CopyOnWriteArraySet) r0
            if (r0 == 0) goto L_0x00e5
            java.util.concurrent.CopyOnWriteArraySet r1 = r7.a(r9, (java.util.concurrent.CopyOnWriteArraySet<com.appboy.events.IEventSubscriber>) r0)
            java.util.Iterator r1 = r1.iterator()
        L_0x00ce:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x00de
            java.lang.Object r4 = r1.next()
            com.appboy.events.IEventSubscriber r4 = (com.appboy.events.IEventSubscriber) r4
            r4.trigger(r8)
            goto L_0x00ce
        L_0x00de:
            boolean r8 = r0.isEmpty()
            if (r8 != 0) goto L_0x00e5
            goto L_0x00e6
        L_0x00e5:
            r3 = r2
        L_0x00e6:
            if (r3 != 0) goto L_0x0126
            java.lang.Class<com.appboy.events.InAppMessageEvent> r8 = com.appboy.events.InAppMessageEvent.class
            boolean r8 = r9.equals(r8)
            if (r8 == 0) goto L_0x0126
            java.lang.String r8 = a
            java.lang.String r9 = "***********************************************************************************************"
            com.appboy.support.AppboyLogger.i(r8, r9)
            java.lang.String r8 = a
            java.lang.String r0 = "**                                       !! WARNING !!                                       **"
            com.appboy.support.AppboyLogger.i(r8, r0)
            java.lang.String r8 = a
            java.lang.String r0 = "**             InAppMessageEvent was published, but no subscribers were found.               **"
            com.appboy.support.AppboyLogger.i(r8, r0)
            java.lang.String r8 = a
            java.lang.String r0 = "**  This is likely an integration error. Please ensure that the AppboyInAppMessageManager is **"
            com.appboy.support.AppboyLogger.i(r8, r0)
            java.lang.String r8 = a
            java.lang.String r0 = "**               registered as early as possible. Additionally, be sure to call              **"
            com.appboy.support.AppboyLogger.i(r8, r0)
            java.lang.String r8 = a
            java.lang.String r0 = "**       AppboyInAppMessageManager.ensureSubscribedToInAppMessageEvents(Context) in your     **"
            com.appboy.support.AppboyLogger.i(r8, r0)
            java.lang.String r8 = a
            java.lang.String r0 = "**          Application onCreate() to avoid losing any in-app messages in the future.        **"
            com.appboy.support.AppboyLogger.i(r8, r0)
            java.lang.String r8 = a
            com.appboy.support.AppboyLogger.i(r8, r9)
        L_0x0126:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: bo.app.ac.a(java.lang.Object, java.lang.Class):void");
    }

    public void a() {
        synchronized (this.h) {
            this.c.clear();
        }
        synchronized (this.i) {
            this.d.clear();
        }
        synchronized (this.g) {
            this.b.clear();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0030, code lost:
        r0 = new java.util.concurrent.CopyOnWriteArraySet();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private <T> boolean a(com.appboy.events.IEventSubscriber<T> r2, java.lang.Class<T> r3, java.util.concurrent.ConcurrentMap<java.lang.Class, java.util.concurrent.CopyOnWriteArraySet<com.appboy.events.IEventSubscriber>> r4) {
        /*
            r1 = this;
            if (r2 != 0) goto L_0x0028
            if (r3 != 0) goto L_0x0007
            java.lang.String r2 = "null eventClass"
            goto L_0x000b
        L_0x0007:
            java.lang.String r2 = r3.getName()
        L_0x000b:
            java.lang.String r3 = a
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = "Error: Attempted to add a null subscriber for eventClass "
            r4.append(r0)
            r4.append(r2)
            java.lang.String r2 = ". This subscriber is being ignored. Please check your calling code to ensure that all potential subscriptions are valid."
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            com.appboy.support.AppboyLogger.e(r3, r2)
            r2 = 0
            return r2
        L_0x0028:
            java.lang.Object r0 = r4.get(r3)
            java.util.concurrent.CopyOnWriteArraySet r0 = (java.util.concurrent.CopyOnWriteArraySet) r0
            if (r0 != 0) goto L_0x003f
            java.util.concurrent.CopyOnWriteArraySet r0 = new java.util.concurrent.CopyOnWriteArraySet
            r0.<init>()
            java.lang.Object r3 = r4.putIfAbsent(r3, r0)
            java.util.concurrent.CopyOnWriteArraySet r3 = (java.util.concurrent.CopyOnWriteArraySet) r3
            if (r3 != 0) goto L_0x003e
            goto L_0x003f
        L_0x003e:
            r0 = r3
        L_0x003f:
            boolean r2 = r0.add(r2)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: bo.app.ac.a(com.appboy.events.IEventSubscriber, java.lang.Class, java.util.concurrent.ConcurrentMap):boolean");
    }

    private <T> boolean a(CopyOnWriteArraySet<IEventSubscriber> copyOnWriteArraySet, IEventSubscriber<T> iEventSubscriber) {
        return (copyOnWriteArraySet == null || iEventSubscriber == null || !copyOnWriteArraySet.remove(iEventSubscriber)) ? false : true;
    }

    /* access modifiers changed from: private */
    public <T> CopyOnWriteArraySet<IEventSubscriber<T>> a(Class<T> cls, CopyOnWriteArraySet<IEventSubscriber> copyOnWriteArraySet) {
        CopyOnWriteArraySet<IEventSubscriber<T>> copyOnWriteArraySet2 = copyOnWriteArraySet;
        String str = a;
        AppboyLogger.d(str, "Triggering " + cls.getName() + " on " + copyOnWriteArraySet.size() + " subscribers.", false);
        return copyOnWriteArraySet2;
    }
}
