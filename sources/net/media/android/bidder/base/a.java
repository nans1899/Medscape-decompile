package net.media.android.bidder.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.lang.ref.WeakReference;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import mnetinternal.aa;
import mnetinternal.ac;
import mnetinternal.da;
import mnetinternal.x;
import mnetinternal.y;
import net.media.android.bidder.base.configs.AdUnitConfig;
import net.media.android.bidder.base.logging.Logger;
import net.media.android.bidder.base.models.internal.ActivityTrackerEvent;
import net.media.android.bidder.base.models.internal.AdTrackerEvent;

public final class a implements Application.ActivityLifecycleCallbacks {
    private static volatile a c;
    /* access modifiers changed from: private */
    public AtomicBoolean a = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public AtomicLong b = new AtomicLong(0);
    /* access modifiers changed from: private */
    public WeakReference<Activity> d;
    /* access modifiers changed from: private */
    public ac e = new ac() {
        public void a() {
            long currentTimeMillis = System.currentTimeMillis() - a.this.b.get();
            if (a.this.a.get() && currentTimeMillis > 10000) {
                Logger.debug("##ActivityLifeCycleListener##", "app is in background");
                a.this.a(AdTrackerEvent.EVENT_APP_IN_BACKGROUND, (String) null);
            }
        }
    };
    /* access modifiers changed from: private */
    public ScheduledFuture f;

    private a() {
    }

    public static a a() {
        a aVar = c;
        if (aVar == null) {
            synchronized (a.class) {
                aVar = c;
                if (aVar == null) {
                    aVar = new a();
                    c = aVar;
                }
            }
        }
        return aVar;
    }

    public Activity b() {
        if (da.a((WeakReference) this.d)) {
            return null;
        }
        return (Activity) this.d.get();
    }

    public void onActivityCreated(final Activity activity, Bundle bundle) {
        aa.a().submit(new ac() {
            public void a() {
                Logger.debug("##ActivityLifeCycleListener##", "onActivityCreated activity: " + activity.getLocalClassName());
                a.this.a(AdTrackerEvent.EVENT_ACTIVITY_CREATED, activity.getLocalClassName());
            }
        });
    }

    public void onActivityStarted(Activity activity) {
        Logger.debug("##ActivityLifeCycleListener##", "onActivityStarted");
    }

    public void onActivityResumed(final Activity activity) {
        aa.a().submit(new ac() {
            public void a() {
                Logger.debug("##ActivityLifeCycleListener##", "onActivityResumed " + activity.getLocalClassName());
                WeakReference unused = a.this.d = new WeakReference(activity);
                a.this.a(AdTrackerEvent.EVENT_ACTIVITY_RESUMED, activity.getLocalClassName());
                long currentTimeMillis = System.currentTimeMillis() - a.this.b.get();
                if (a.this.a.get() && currentTimeMillis > 10000) {
                    Logger.debug("##ActivityLifeCycleListener##", "app was in background, coming to foreground");
                    a.this.a(AdTrackerEvent.EVENT_APP_IN_FOREGROUND, (String) null);
                }
                a.this.a.set(false);
                if (a.this.f != null && !a.this.f.isCancelled() && a.this.f.isDone()) {
                    a.this.f.cancel(false);
                    ScheduledFuture unused2 = a.this.f = null;
                }
            }
        });
    }

    public void onActivityPaused(final Activity activity) {
        aa.a().submit(new ac() {
            public void a() {
                Logger.debug("##ActivityLifeCycleListener##", "onActivityPaused " + activity.getLocalClassName());
                a.this.a(AdTrackerEvent.EVENT_ACTIVITY_PAUSED, activity.getLocalClassName());
                a.this.a.set(true);
                a.this.b.set(System.currentTimeMillis());
                ScheduledFuture unused = a.this.f = aa.a().schedule(a.this.e, 10000, TimeUnit.MILLISECONDS);
            }
        });
    }

    public void onActivityStopped(Activity activity) {
        Logger.debug("##ActivityLifeCycleListener##", "onActivityStopped");
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        Logger.debug("##ActivityLifeCycleListener##", "onActivitySaveInstanceState");
    }

    public void onActivityDestroyed(final Activity activity) {
        aa.a().submit(new ac() {
            public void a() {
                Logger.debug("##ActivityLifeCycleListener##", "onActivityDestroyed " + activity.getLocalClassName());
                a.this.a(AdTrackerEvent.EVENT_ACTIVITY_DESTROYED, activity.getLocalClassName());
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(final String str, final String str2) {
        if (!AdUnitConfig.getInstance().isBidderEnabled()) {
            Logger.debug("##ActivityLifeCycleListener##", "headerbidder disabled!");
        } else {
            x.a().a((y) new y() {
                public String a() {
                    return str;
                }

                public Object b() {
                    return new ActivityTrackerEvent.Builder().setActivityName(str2).setEventType(str).build();
                }
            });
        }
    }
}
