package com.adobe.mobile;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.appevents.AppEventsConstants;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public final class Analytics {
    private static final String NO_ANALYTICS_MESSAGE = "Analytics - ADBMobile is not configured correctly to use Analytics.";

    public interface TimedActionBlock<Boolean> {
        Boolean call(long j, long j2, Map<String, Object> map);
    }

    public enum BEACON_PROXIMITY {
        PROXIMITY_IMMEDIATE(1),
        PROXIMITY_NEAR(2),
        PROXIMITY_FAR(3),
        PROXIMITY_UNKNOWN(0);
        
        private final int value;

        private BEACON_PROXIMITY(int i) {
            this.value = i;
        }

        /* access modifiers changed from: protected */
        public int getValue() {
            return this.value;
        }

        public String toString() {
            int i = AnonymousClass15.$SwitchMap$com$adobe$mobile$Analytics$BEACON_PROXIMITY[ordinal()];
            if (i == 1) {
                return AppEventsConstants.EVENT_PARAM_VALUE_YES;
            }
            if (i != 2) {
                return i != 3 ? AppEventsConstants.EVENT_PARAM_VALUE_NO : ExifInterface.GPS_MEASUREMENT_3D;
            }
            return ExifInterface.GPS_MEASUREMENT_2D;
        }
    }

    /* renamed from: com.adobe.mobile.Analytics$15  reason: invalid class name */
    static /* synthetic */ class AnonymousClass15 {
        static final /* synthetic */ int[] $SwitchMap$com$adobe$mobile$Analytics$BEACON_PROXIMITY;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.adobe.mobile.Analytics$BEACON_PROXIMITY[] r0 = com.adobe.mobile.Analytics.BEACON_PROXIMITY.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$adobe$mobile$Analytics$BEACON_PROXIMITY = r0
                com.adobe.mobile.Analytics$BEACON_PROXIMITY r1 = com.adobe.mobile.Analytics.BEACON_PROXIMITY.PROXIMITY_IMMEDIATE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$adobe$mobile$Analytics$BEACON_PROXIMITY     // Catch:{ NoSuchFieldError -> 0x001d }
                com.adobe.mobile.Analytics$BEACON_PROXIMITY r1 = com.adobe.mobile.Analytics.BEACON_PROXIMITY.PROXIMITY_NEAR     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$adobe$mobile$Analytics$BEACON_PROXIMITY     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.adobe.mobile.Analytics$BEACON_PROXIMITY r1 = com.adobe.mobile.Analytics.BEACON_PROXIMITY.PROXIMITY_FAR     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$adobe$mobile$Analytics$BEACON_PROXIMITY     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.adobe.mobile.Analytics$BEACON_PROXIMITY r1 = com.adobe.mobile.Analytics.BEACON_PROXIMITY.PROXIMITY_UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.Analytics.AnonymousClass15.<clinit>():void");
        }
    }

    public static void trackState(final String str, Map<String, Object> map) {
        final HashMap hashMap = map != null ? new HashMap(map) : null;
        StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
            public void run() {
                AnalyticsTrackState.trackState(str, hashMap);
            }
        });
    }

    public static void trackAction(final String str, Map<String, Object> map) {
        final HashMap hashMap = map != null ? new HashMap(map) : null;
        StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
            public void run() {
                AnalyticsTrackAction.trackAction(str, hashMap);
            }
        });
    }

    public static void trackLocation(final Location location, Map<String, Object> map) {
        final HashMap hashMap = map != null ? new HashMap(map) : null;
        StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
            public void run() {
                AnalyticsTrackLocation.trackLocation(location, hashMap);
            }
        });
    }

    public static void trackBeacon(String str, String str2, String str3, BEACON_PROXIMITY beacon_proximity, Map<String, Object> map) {
        final HashMap hashMap = map != null ? new HashMap(map) : null;
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        final BEACON_PROXIMITY beacon_proximity2 = beacon_proximity;
        StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
            public void run() {
                AnalyticsTrackBeacon.trackBeacon(str4, str5, str6, beacon_proximity2, hashMap);
            }
        });
    }

    public static void clearBeacon() {
        StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
            public void run() {
                AnalyticsTrackBeacon.clearBeacon();
            }
        });
    }

    public static void trackLifetimeValueIncrease(final BigDecimal bigDecimal, final HashMap<String, Object> hashMap) {
        StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
            public void run() {
                AnalyticsTrackLifetimeValueIncrease.trackLifetimeValueIncrease(bigDecimal, hashMap);
            }
        });
    }

    public static void trackTimedActionStart(final String str, Map<String, Object> map) {
        final HashMap hashMap = map != null ? new HashMap(map) : null;
        StaticMethods.getTimedActionsExecutor().execute(new Runnable() {
            public void run() {
                AnalyticsTrackTimedAction.sharedInstance().trackTimedActionStart(str, hashMap);
            }
        });
    }

    public static void trackTimedActionUpdate(final String str, Map<String, Object> map) {
        final HashMap hashMap = map != null ? new HashMap(map) : null;
        StaticMethods.getTimedActionsExecutor().execute(new Runnable() {
            public void run() {
                AnalyticsTrackTimedAction.sharedInstance().trackTimedActionUpdate(str, hashMap);
            }
        });
    }

    public static void trackTimedActionEnd(final String str, final TimedActionBlock<Boolean> timedActionBlock) {
        StaticMethods.getTimedActionsExecutor().execute(new Runnable() {
            public void run() {
                AnalyticsTrackTimedAction.sharedInstance().trackTimedActionEnd(str, timedActionBlock);
            }
        });
    }

    public static boolean trackingTimedActionExists(String str) {
        return AnalyticsTrackTimedAction.sharedInstance().trackTimedActionExists(str);
    }

    public static void processReferrer(Context context, final Intent intent) {
        StaticMethods.setSharedContext(context);
        StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
            public void run() {
                ReferrerHandler.processIntent(intent);
            }
        });
    }

    public static String getTrackingIdentifier() {
        FutureTask futureTask = new FutureTask(new Callable<String>() {
            public String call() throws Exception {
                return StaticMethods.getAID();
            }
        });
        StaticMethods.getAnalyticsExecutor().execute(futureTask);
        try {
            return (String) futureTask.get();
        } catch (Exception e) {
            StaticMethods.logErrorFormat("Analytics - Unable to get TrackingIdentifier (%s)", e.getMessage());
            return null;
        }
    }

    public static long getQueueSize() {
        FutureTask futureTask = new FutureTask(new Callable<Long>() {
            public Long call() throws Exception {
                return Long.valueOf(AnalyticsWorker.sharedInstance().getTrackingQueueSize());
            }
        });
        StaticMethods.getAnalyticsExecutor().execute(futureTask);
        try {
            return ((Long) futureTask.get()).longValue();
        } catch (Exception e) {
            StaticMethods.logErrorFormat("Analytics - Unable to get QueueSize (%s)", e.getMessage());
            return 0;
        }
    }

    public static void clearQueue() {
        StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
            public void run() {
                AnalyticsWorker.sharedInstance().clearTrackingQueue();
            }
        });
    }

    public static void sendQueuedHits() {
        StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
            public void run() {
                AnalyticsWorker.sharedInstance().forceKick();
            }
        });
    }
}
