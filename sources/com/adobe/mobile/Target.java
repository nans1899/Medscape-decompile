package com.adobe.mobile;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public final class Target {

    public interface TargetCallback<T> {
        void call(T t);
    }

    public static void loadRequest(TargetLocationRequest targetLocationRequest, TargetCallback<String> targetCallback) {
        if (StaticMethods.isWearableApp()) {
            StaticMethods.logWarningFormat("Target - Method loadRequest is not available for Wearable", new Object[0]);
        } else {
            TargetWorker.loadRequest(targetLocationRequest, targetCallback);
        }
    }

    public static void loadRequest(String str, String str2, Map<String, Object> map, Map<String, Object> map2, Map<String, Object> map3, TargetCallback<String> targetCallback) {
        loadRequest(str, str2, map, map2, map3, (Map<String, Object>) null, targetCallback);
    }

    public static void loadRequest(String str, String str2, Map<String, Object> map, Map<String, Object> map2, Map<String, Object> map3, Map<String, Object> map4, TargetCallback<String> targetCallback) {
        if (StaticMethods.isWearableApp()) {
            StaticMethods.logWarningFormat("Target - Method loadRequest is not available for Wearable", new Object[0]);
        } else {
            TargetWorker.loadRequest(str, str2, map, map2, map3, targetCallback);
        }
    }

    public static TargetLocationRequest createRequest(String str, String str2, Map<String, Object> map) {
        return new TargetLocationRequest(str, str2, map);
    }

    public static TargetLocationRequest createOrderConfirmRequest(String str, String str2, String str3, String str4, Map<String, Object> map) {
        return TargetLocationRequest.createRequestWithOrderConfirm(str, str2, str3, str4, map);
    }

    public static String getPcID() {
        FutureTask futureTask = new FutureTask(new Callable<String>() {
            public String call() throws Exception {
                return TargetWorker.getTntId();
            }
        });
        StaticMethods.getAnalyticsExecutor().execute(futureTask);
        try {
            return (String) futureTask.get();
        } catch (Exception e) {
            StaticMethods.logErrorFormat("Target - Unable to get PcID (%s)", e.getMessage());
            return null;
        }
    }

    public static String getSessionID() {
        FutureTask futureTask = new FutureTask(new Callable<String>() {
            public String call() throws Exception {
                return TargetWorker.getSessionId();
            }
        });
        StaticMethods.getAnalyticsExecutor().execute(futureTask);
        try {
            return (String) futureTask.get();
        } catch (Exception e) {
            StaticMethods.logErrorFormat("Target - Unable to get SessionID (%s)", e.getMessage());
            return null;
        }
    }

    public static String getThirdPartyID() {
        FutureTask futureTask = new FutureTask(new Callable<String>() {
            public String call() throws Exception {
                return TargetWorker.getThirdPartyId();
            }
        });
        StaticMethods.getAnalyticsExecutor().execute(futureTask);
        try {
            return (String) futureTask.get();
        } catch (Exception e) {
            StaticMethods.logErrorFormat("Target - Unable to get ThirdPartyID (%s)", e.getMessage());
            return null;
        }
    }

    public static void setThirdPartyID(final String str) {
        StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
            public void run() {
                TargetWorker.setThirdPartyId(str);
            }
        });
    }

    public static void clearCookies() {
        StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
            public void run() {
                TargetWorker.resetExperience();
            }
        });
    }

    public static void setPreviewRestartDeeplink(String str) {
        if (MobileConfig.getInstance().mobileUsingTarget()) {
            TargetPreviewManager.getInstance().setPreviewRestartDeeplink(str);
        }
    }

    public static void prefetchContent(List<TargetPrefetchObject> list, Map<String, Object> map, TargetCallback<Boolean> targetCallback) {
        TargetWorker.prefetchContent(list, map, targetCallback);
    }

    public static void clearPrefetchCache() {
        TargetWorker.clearPrefetchCache();
    }

    public static void loadRequests(List<TargetRequestObject> list, Map<String, Object> map) {
        if (StaticMethods.isWearableApp()) {
            StaticMethods.logWarningFormat("Target - Method loadRequest is not available for Wearable", new Object[0]);
        } else {
            TargetWorker.loadRequests(list, map);
        }
    }

    public static TargetRequestObject createTargetRequestObject(String str, String str2, Map<String, Object> map, Map<String, Object> map2, Map<String, Object> map3, TargetCallback<String> targetCallback) {
        return new TargetRequestObject(str, str2, map, map2, map3, targetCallback);
    }

    public static TargetRequestObject createTargetRequestObject(String str, String str2, Map<String, Object> map, TargetCallback<String> targetCallback) {
        return new TargetRequestObject(str, str2, map, (Map<String, Object>) null, (Map<String, Object>) null, targetCallback);
    }

    public static TargetPrefetchObject createTargetPrefetchObject(String str, Map<String, Object> map, Map<String, Object> map2, Map<String, Object> map3) {
        return new TargetPrefetchObject(str, map, map2, map3);
    }

    public static TargetPrefetchObject createTargetPrefetchObject(String str, Map<String, Object> map) {
        return new TargetPrefetchObject(str, map, (Map<String, Object>) null, (Map<String, Object>) null);
    }

    public static void locationClicked(String str, Map<String, Object> map, Map<String, Object> map2, Map<String, Object> map3, Map<String, Object> map4) {
        TargetWorker.locationClicked(str, map, map2, map3, map4);
    }
}
