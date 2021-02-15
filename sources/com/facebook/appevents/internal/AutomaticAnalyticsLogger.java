package com.facebook.appevents.internal;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.InternalAppEventsLogger;
import com.facebook.internal.FetchedAppGateKeepersManager;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Validate;
import java.math.BigDecimal;
import java.util.Currency;
import org.json.JSONException;
import org.json.JSONObject;

public class AutomaticAnalyticsLogger {
    private static final String TAG = AutomaticAnalyticsLogger.class.getCanonicalName();
    private static final InternalAppEventsLogger internalAppEventsLogger = new InternalAppEventsLogger(FacebookSdk.getApplicationContext());

    public static void logActivateAppEvent() {
        Context applicationContext = FacebookSdk.getApplicationContext();
        String applicationId = FacebookSdk.getApplicationId();
        boolean autoLogAppEventsEnabled = FacebookSdk.getAutoLogAppEventsEnabled();
        Validate.notNull(applicationContext, "context");
        if (!autoLogAppEventsEnabled) {
            return;
        }
        if (applicationContext instanceof Application) {
            AppEventsLogger.activateApp((Application) applicationContext, applicationId);
        } else {
            Log.w(TAG, "Automatic logging of basic events will not happen, because FacebookSdk.getApplicationContext() returns object that is not instance of android.app.Application. Make sure you call FacebookSdk.sdkInitialize() from Application class and pass application context.");
        }
    }

    public static void logActivityTimeSpentEvent(String str, long j) {
        Context applicationContext = FacebookSdk.getApplicationContext();
        String applicationId = FacebookSdk.getApplicationId();
        Validate.notNull(applicationContext, "context");
        FetchedAppSettings queryAppSettings = FetchedAppSettingsManager.queryAppSettings(applicationId, false);
        if (queryAppSettings != null && queryAppSettings.getAutomaticLoggingEnabled() && j > 0) {
            InternalAppEventsLogger internalAppEventsLogger2 = new InternalAppEventsLogger(applicationContext);
            Bundle bundle = new Bundle(1);
            bundle.putCharSequence(Constants.AA_TIME_SPENT_SCREEN_PARAMETER_NAME, str);
            internalAppEventsLogger2.logEvent(Constants.AA_TIME_SPENT_EVENT_NAME, (double) j, bundle);
        }
    }

    public static void logPurchaseInapp(String str, String str2) {
        PurchaseLoggingParameters purchaseLoggingParameters;
        if (isImplicitPurchaseLoggingEnabled() && (purchaseLoggingParameters = getPurchaseLoggingParameters(str, str2)) != null) {
            internalAppEventsLogger.logPurchaseImplicitly(purchaseLoggingParameters.purchaseAmount, purchaseLoggingParameters.currency, purchaseLoggingParameters.param);
        }
    }

    public static void logPurchaseSubs(SubscriptionType subscriptionType, String str, String str2) {
        String str3;
        if (isImplicitPurchaseLoggingEnabled()) {
            boolean gateKeeperForKey = FetchedAppGateKeepersManager.getGateKeeperForKey(FetchedAppGateKeepersManager.APP_EVENTS_IF_AUTO_LOG_SUBS, FacebookSdk.getApplicationId(), false);
            switch (AnonymousClass1.$SwitchMap$com$facebook$appevents$internal$SubscriptionType[subscriptionType.ordinal()]) {
                case 1:
                    if (gateKeeperForKey) {
                        str3 = AppEventsConstants.EVENT_NAME_SUBSCRIBE;
                        break;
                    } else {
                        logPurchaseInapp(str, str2);
                        return;
                    }
                case 2:
                    if (gateKeeperForKey) {
                        str3 = AppEventsConstants.EVENT_NAME_START_TRIAL;
                        break;
                    } else {
                        logPurchaseInapp(str, str2);
                        return;
                    }
                case 3:
                    str3 = "SubscriptionRestore";
                    break;
                case 4:
                    str3 = "SubscriptionCancel";
                    break;
                case 5:
                    str3 = "SubscriptionHeartbeat";
                    break;
                case 6:
                    str3 = "SubscriptionExpire";
                    break;
                default:
                    return;
            }
            PurchaseLoggingParameters purchaseLoggingParameters = getPurchaseLoggingParameters(str, str2);
            if (purchaseLoggingParameters != null) {
                internalAppEventsLogger.logEventImplicitly(str3, purchaseLoggingParameters.purchaseAmount, purchaseLoggingParameters.currency, purchaseLoggingParameters.param);
            }
        }
    }

    /* renamed from: com.facebook.appevents.internal.AutomaticAnalyticsLogger$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$appevents$internal$SubscriptionType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.facebook.appevents.internal.SubscriptionType[] r0 = com.facebook.appevents.internal.SubscriptionType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$appevents$internal$SubscriptionType = r0
                com.facebook.appevents.internal.SubscriptionType r1 = com.facebook.appevents.internal.SubscriptionType.SUBSCRIBE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$facebook$appevents$internal$SubscriptionType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.appevents.internal.SubscriptionType r1 = com.facebook.appevents.internal.SubscriptionType.START_TRIAL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$facebook$appevents$internal$SubscriptionType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.facebook.appevents.internal.SubscriptionType r1 = com.facebook.appevents.internal.SubscriptionType.RESTORE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$facebook$appevents$internal$SubscriptionType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.facebook.appevents.internal.SubscriptionType r1 = com.facebook.appevents.internal.SubscriptionType.CANCEL     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$facebook$appevents$internal$SubscriptionType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.facebook.appevents.internal.SubscriptionType r1 = com.facebook.appevents.internal.SubscriptionType.HEARTBEAT     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$facebook$appevents$internal$SubscriptionType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.facebook.appevents.internal.SubscriptionType r1 = com.facebook.appevents.internal.SubscriptionType.EXPIRE     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.internal.AutomaticAnalyticsLogger.AnonymousClass1.<clinit>():void");
        }
    }

    public static boolean isImplicitPurchaseLoggingEnabled() {
        FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
        return appSettingsWithoutQuery != null && FacebookSdk.getAutoLogAppEventsEnabled() && appSettingsWithoutQuery.getIAPAutomaticLoggingEnabled();
    }

    private static PurchaseLoggingParameters getPurchaseLoggingParameters(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = new JSONObject(str2);
            Bundle bundle = new Bundle(1);
            bundle.putCharSequence(Constants.IAP_PRODUCT_ID, jSONObject.getString("productId"));
            bundle.putCharSequence(Constants.IAP_PURCHASE_TIME, jSONObject.getString("purchaseTime"));
            bundle.putCharSequence(Constants.IAP_PURCHASE_TOKEN, jSONObject.getString("purchaseToken"));
            bundle.putCharSequence(Constants.IAP_PACKAGE_NAME, jSONObject.optString("packageName"));
            bundle.putCharSequence(Constants.IAP_PRODUCT_TITLE, jSONObject2.optString("title"));
            bundle.putCharSequence(Constants.IAP_PRODUCT_DESCRIPTION, jSONObject2.optString("description"));
            String optString = jSONObject2.optString("type");
            bundle.putCharSequence(Constants.IAP_PRODUCT_TYPE, optString);
            if (optString.equals("subs")) {
                bundle.putCharSequence(Constants.IAP_SUBSCRIPTION_AUTORENEWING, Boolean.toString(jSONObject.optBoolean("autoRenewing", false)));
                bundle.putCharSequence(Constants.IAP_SUBSCRIPTION_PERIOD, jSONObject2.optString("subscriptionPeriod"));
                bundle.putCharSequence(Constants.IAP_FREE_TRIAL_PERIOD, jSONObject2.optString("freeTrialPeriod"));
                String optString2 = jSONObject2.optString("introductoryPriceCycles");
                if (!optString2.isEmpty()) {
                    bundle.putCharSequence(Constants.IAP_INTRO_PRICE_AMOUNT_MICROS, jSONObject2.optString("introductoryPriceAmountMicros"));
                    bundle.putCharSequence(Constants.IAP_INTRO_PRICE_CYCLES, optString2);
                }
            }
            return new PurchaseLoggingParameters(new BigDecimal(((double) jSONObject2.getLong("price_amount_micros")) / 1000000.0d), Currency.getInstance(jSONObject2.getString("price_currency_code")), bundle);
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing in-app subscription data.", e);
            return null;
        }
    }

    private static class PurchaseLoggingParameters {
        Currency currency;
        Bundle param;
        BigDecimal purchaseAmount;

        PurchaseLoggingParameters(BigDecimal bigDecimal, Currency currency2, Bundle bundle) {
            this.purchaseAmount = bigDecimal;
            this.currency = currency2;
            this.param = bundle;
        }
    }
}
