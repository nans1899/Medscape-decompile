package com.adobe.mobile;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import java.util.concurrent.TimeUnit;

final class GoogleApiClientWrapper {
    GoogleApiClientWrapper() {
    }

    protected static void disconnect(GoogleApiClient googleApiClient) {
        try {
            GoogleApiClient.class.getDeclaredMethod("disconnect", new Class[0]).invoke(googleApiClient, new Object[0]);
        } catch (Exception e) {
            StaticMethods.logDebugFormat("Wearable - Unable to call GoogleApiClient.disconnect() method (%s)", e.getLocalizedMessage());
        }
    }

    protected static void connect(GoogleApiClient googleApiClient) {
        try {
            GoogleApiClient.class.getDeclaredMethod("connect", new Class[0]).invoke(googleApiClient, new Object[0]);
        } catch (Exception e) {
            StaticMethods.logDebugFormat("Wearable - Unable to call GoogleApiClient.connect() method (%s)", e.getLocalizedMessage());
        }
    }

    protected static Boolean isConnected(GoogleApiClient googleApiClient) {
        try {
            Object invoke = GoogleApiClient.class.getDeclaredMethod("isConnected", new Class[0]).invoke(googleApiClient, new Object[0]);
            return Boolean.valueOf(invoke instanceof Boolean ? ((Boolean) invoke).booleanValue() : false);
        } catch (Exception e) {
            StaticMethods.logDebugFormat("Wearable - Unable to call GoogleApiClient.isConnected() method (%s)", e.getLocalizedMessage());
            return false;
        }
    }

    protected static ConnectionResult blockingConnect(GoogleApiClient googleApiClient, long j, TimeUnit timeUnit) {
        Class<GoogleApiClient> cls = GoogleApiClient.class;
        try {
            Object invoke = cls.getDeclaredMethod("blockingConnect", new Class[]{Long.TYPE, TimeUnit.class}).invoke(googleApiClient, new Object[]{Long.valueOf(j), timeUnit});
            if (invoke instanceof ConnectionResult) {
                return (ConnectionResult) invoke;
            }
            return null;
        } catch (Exception e) {
            StaticMethods.logDebugFormat("Wearable - Unable to call GoogleApiClient.blockingConnect() method (%s)", e.getLocalizedMessage());
            return null;
        }
    }

    protected static Result await(PendingResult pendingResult) {
        try {
            Object invoke = PendingResult.class.getDeclaredMethod("await", new Class[0]).invoke(pendingResult, new Object[0]);
            if (invoke instanceof Result) {
                return (Result) invoke;
            }
            return null;
        } catch (Exception e) {
            StaticMethods.logDebugFormat("Wearable - Unable to call PendingResult.await() method (%s)", e.getLocalizedMessage());
            return null;
        }
    }
}
