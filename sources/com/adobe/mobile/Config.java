package com.adobe.mobile;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public final class Config {
    public static final String ADB_MESSAGE_DEEPLINK_KEY = "adb_deeplink";

    public interface AdobeDataCallback {
        void call(MobileDataEvent mobileDataEvent, Map<String, Object> map);
    }

    public interface ConfigCallback<T> {
        void call(T t);
    }

    public static String getVersion() {
        return "4.17.5-AN";
    }

    public enum ApplicationType {
        APPLICATION_TYPE_HANDHELD(0),
        APPLICATION_TYPE_WEARABLE(1);
        
        private final int value;

        private ApplicationType(int i) {
            this.value = i;
        }

        /* access modifiers changed from: protected */
        public int getValue() {
            return this.value;
        }
    }

    public enum MobileDataEvent {
        MOBILE_EVENT_LIFECYCLE(0),
        MOBILE_EVENT_ACQUISITION_INSTALL(1),
        MOBILE_EVENT_ACQUISITION_LAUNCH(2);
        
        private final int value;

        private MobileDataEvent(int i) {
            this.value = i;
        }

        /* access modifiers changed from: protected */
        public int getValue() {
            return this.value;
        }
    }

    public static void setContext(Context context) {
        setContext(context, ApplicationType.APPLICATION_TYPE_HANDHELD);
    }

    public static void setContext(Context context, ApplicationType applicationType) {
        StaticMethods.setSharedContext(context);
        setApplicationType(applicationType);
        if (applicationType == ApplicationType.APPLICATION_TYPE_WEARABLE) {
            StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
                public void run() {
                    WearableFunctionBridge.syncConfigFromHandheld();
                }
            });
        }
    }

    public static MobilePrivacyStatus getPrivacyStatus() {
        FutureTask futureTask = new FutureTask(new Callable<MobilePrivacyStatus>() {
            public MobilePrivacyStatus call() throws Exception {
                return MobileConfig.getInstance().getPrivacyStatus();
            }
        });
        StaticMethods.getSharedExecutor().execute(futureTask);
        try {
            return (MobilePrivacyStatus) futureTask.get();
        } catch (Exception e) {
            StaticMethods.logErrorFormat("Analytics - Unable to get PrivacyStatus (%s)", e.getMessage());
            return null;
        }
    }

    public static void setPrivacyStatus(final MobilePrivacyStatus mobilePrivacyStatus) {
        StaticMethods.getSharedExecutor().execute(new Runnable() {
            public void run() {
                MobileConfig.getInstance().setPrivacyStatus(mobilePrivacyStatus);
            }
        });
    }

    public static String getUserIdentifier() {
        FutureTask futureTask = new FutureTask(new Callable<String>() {
            public String call() throws Exception {
                return StaticMethods.getVisitorID();
            }
        });
        StaticMethods.getAnalyticsExecutor().execute(futureTask);
        try {
            return (String) futureTask.get();
        } catch (Exception e) {
            StaticMethods.logErrorFormat("Analytics - Unable to get UserIdentifier (%s)", e.getMessage());
            return null;
        }
    }

    public static void setUserIdentifier(final String str) {
        StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
            public void run() {
                if (MobileConfig.getInstance().getPrivacyStatus() != MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_OUT) {
                    StaticMethods.setVisitorID(str);
                }
            }
        });
    }

    public static void setPushIdentifier(final String str) {
        StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
            public void run() {
                if (MobileConfig.getInstance().getPrivacyStatus() != MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_OUT) {
                    StaticMethods.setPushIdentifier(str);
                }
            }
        });
    }

    public static void submitAdvertisingIdentifierTask(final Callable<String> callable) {
        StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
            public void run() {
                try {
                    if (MobileConfig.getInstance().getPrivacyStatus() != MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_OUT && callable != null) {
                        StaticMethods.setAdvertisingIdentifier((String) callable.call());
                    }
                } catch (Exception e) {
                    StaticMethods.logErrorFormat("Config - Error running the task to get Advertising Identifier (%s).", e.getLocalizedMessage());
                }
            }
        });
    }

    public static ApplicationType getApplicationType() {
        return StaticMethods.getApplicationType();
    }

    public static void setApplicationType(ApplicationType applicationType) {
        StaticMethods.setApplicationType(applicationType);
    }

    public static Boolean getDebugLogging() {
        return Boolean.valueOf(StaticMethods.getDebugLogging());
    }

    public static void setDebugLogging(Boolean bool) {
        StaticMethods.setDebugLogging(bool.booleanValue());
    }

    public static BigDecimal getLifetimeValue() {
        FutureTask futureTask = new FutureTask(new Callable<BigDecimal>() {
            public BigDecimal call() throws Exception {
                return AnalyticsTrackLifetimeValueIncrease.getLifetimeValue();
            }
        });
        StaticMethods.getAnalyticsExecutor().execute(futureTask);
        try {
            return (BigDecimal) futureTask.get();
        } catch (Exception e) {
            StaticMethods.logErrorFormat("Analytics - Unable to get lifetime value (%s)", e.getMessage());
            return null;
        }
    }

    public static void collectLifecycleData() {
        if (StaticMethods.isWearableApp()) {
            StaticMethods.logWarningFormat("Analytics - Method collectLifecycleData is not available for Wearable", new Object[0]);
        } else {
            StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
                public void run() {
                    Lifecycle.start((Activity) null, (Map<String, Object>) null);
                }
            });
        }
    }

    public static void collectLifecycleData(final Activity activity) {
        if (StaticMethods.isWearableApp()) {
            StaticMethods.logWarningFormat("Analytics - Method collectLifecycleData is not available for Wearable", new Object[0]);
        } else {
            StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
                public void run() {
                    Lifecycle.start(activity, (Map<String, Object>) null);
                }
            });
        }
    }

    public static void collectLifecycleData(final Activity activity, final Map<String, Object> map) {
        if (StaticMethods.isWearableApp()) {
            StaticMethods.logWarningFormat("Analytics - Method collectLifecycleData is not available for Wearable", new Object[0]);
        } else {
            StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
                public void run() {
                    Lifecycle.start(activity, map);
                }
            });
        }
    }

    public static void pauseCollectingLifecycleData() {
        if (StaticMethods.isWearableApp()) {
            StaticMethods.logWarningFormat("Analytics - Method pauseCollectingLifecycleData is not available for Wearable", new Object[0]);
            return;
        }
        MessageAlert.clearCurrentDialog();
        StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
            public void run() {
                Lifecycle.stop();
            }
        });
    }

    public static void collectPII(Map<String, Object> map) {
        Messages.checkForPIIRequests(map);
    }

    public static void trackAdobeDeepLink(final Uri uri) {
        if (StaticMethods.isWearableApp()) {
            StaticMethods.logWarningFormat("Analytics - Method trackAdobeDeepLink is not available for Wearable", new Object[0]);
        } else {
            StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
                public void run() {
                    Lifecycle.processAdobeDeepLink(uri);
                }
            });
        }
    }

    public static void setSmallIconResourceId(final int i) {
        if (StaticMethods.isWearableApp()) {
            StaticMethods.logWarningFormat("Analytics - Method setSmallIconResourceId is not available for Wearable", new Object[0]);
        } else {
            StaticMethods.getMessagesExecutor().execute(new Runnable() {
                public void run() {
                    Messages.setSmallIconResourceId(i);
                }
            });
        }
    }

    public static void setLargeIconResourceId(final int i) {
        if (StaticMethods.isWearableApp()) {
            StaticMethods.logWarningFormat("Analytics - Method setLargeIconResourceId is not available for Wearable", new Object[0]);
        } else {
            StaticMethods.getMessagesExecutor().execute(new Runnable() {
                public void run() {
                    Messages.setLargeIconResourceId(i);
                }
            });
        }
    }

    public static void overrideConfigStream(InputStream inputStream) {
        MobileConfig.setUserDefinedConfigPath(inputStream);
    }

    public static void registerAdobeDataCallback(final AdobeDataCallback adobeDataCallback) {
        StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
            public void run() {
                MobileConfig.getInstance().setAdobeDataCallback(adobeDataCallback);
            }
        });
    }

    public static void getAllIdentifiersAsync(final ConfigCallback<String> configCallback) {
        if (configCallback != null) {
            StaticMethods.getSharedExecutor().execute(new Runnable() {
                public void run() {
                    configCallback.call(MobileIdentities.getAllIdentifiers());
                }
            });
        }
    }
}
