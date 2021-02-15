package com.adobe.mobile;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public final class AudienceManager {

    public interface AudienceManagerCallback<T> {
        void call(T t);
    }

    public static HashMap<String, Object> getVisitorProfile() {
        return AudienceManagerWorker.GetVisitorProfile();
    }

    public static String getDpuuid() {
        return AudienceManagerWorker.GetDpuuid();
    }

    public static String getDpid() {
        return AudienceManagerWorker.GetDpid();
    }

    public static String getUuid() {
        FutureTask futureTask = new FutureTask(new Callable<String>() {
            public String call() throws Exception {
                return AudienceManagerWorker.GetUUID();
            }
        });
        StaticMethods.getAudienceExecutor().execute(futureTask);
        try {
            return (String) futureTask.get();
        } catch (Exception e) {
            StaticMethods.logErrorFormat("Audience Manager - Unable to get Uuid (%s)", e.getMessage());
            return "";
        }
    }

    public static void setDpidAndDpuuid(String str, String str2) {
        AudienceManagerWorker.SetDpidAndDpuuid(str, str2);
    }

    public static void signalWithData(Map<String, Object> map, AudienceManagerCallback<Map<String, Object>> audienceManagerCallback) {
        if (StaticMethods.isWearableApp()) {
            StaticMethods.logWarningFormat("Audience Manager - Method signalWithData is not available for Wearable", new Object[0]);
        } else {
            AudienceManagerWorker.SubmitSignal(map, audienceManagerCallback);
        }
    }

    public static void reset() {
        AudienceManagerWorker.Reset();
    }
}
