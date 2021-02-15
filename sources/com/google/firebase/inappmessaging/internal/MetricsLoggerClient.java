package com.google.firebase.inappmessaging.internal;

import android.os.Bundle;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.inappmessaging.CampaignAnalytics;
import com.google.firebase.inappmessaging.ClientAppInfo;
import com.google.firebase.inappmessaging.DismissType;
import com.google.firebase.inappmessaging.EventType;
import com.google.firebase.inappmessaging.FirebaseInAppMessagingDisplayCallbacks;
import com.google.firebase.inappmessaging.RenderErrorReason;
import com.google.firebase.inappmessaging.internal.time.Clock;
import com.google.firebase.inappmessaging.model.Action;
import com.google.firebase.inappmessaging.model.BannerMessage;
import com.google.firebase.inappmessaging.model.CardMessage;
import com.google.firebase.inappmessaging.model.ImageOnlyMessage;
import com.google.firebase.inappmessaging.model.InAppMessage;
import com.google.firebase.inappmessaging.model.ModalMessage;
import com.google.firebase.messaging.Constants;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class MetricsLoggerClient {
    private static final Map<FirebaseInAppMessagingDisplayCallbacks.InAppMessagingDismissType, DismissType> dismissTransform = new HashMap();
    private static final Map<FirebaseInAppMessagingDisplayCallbacks.InAppMessagingErrorReason, RenderErrorReason> errorTransform = new HashMap();
    private final AnalyticsConnector analyticsConnector;
    private final Clock clock;
    private final DeveloperListenerManager developerListenerManager;
    private final EngagementMetricsLoggerInterface engagementMetricsLogger;
    private final FirebaseApp firebaseApp;
    private final FirebaseInstanceId firebaseInstanceId;

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public interface EngagementMetricsLoggerInterface {
        void logEvent(byte[] bArr);
    }

    static {
        errorTransform.put(FirebaseInAppMessagingDisplayCallbacks.InAppMessagingErrorReason.UNSPECIFIED_RENDER_ERROR, RenderErrorReason.UNSPECIFIED_RENDER_ERROR);
        errorTransform.put(FirebaseInAppMessagingDisplayCallbacks.InAppMessagingErrorReason.IMAGE_FETCH_ERROR, RenderErrorReason.IMAGE_FETCH_ERROR);
        errorTransform.put(FirebaseInAppMessagingDisplayCallbacks.InAppMessagingErrorReason.IMAGE_DISPLAY_ERROR, RenderErrorReason.IMAGE_DISPLAY_ERROR);
        errorTransform.put(FirebaseInAppMessagingDisplayCallbacks.InAppMessagingErrorReason.IMAGE_UNSUPPORTED_FORMAT, RenderErrorReason.IMAGE_UNSUPPORTED_FORMAT);
        dismissTransform.put(FirebaseInAppMessagingDisplayCallbacks.InAppMessagingDismissType.AUTO, DismissType.AUTO);
        dismissTransform.put(FirebaseInAppMessagingDisplayCallbacks.InAppMessagingDismissType.CLICK, DismissType.CLICK);
        dismissTransform.put(FirebaseInAppMessagingDisplayCallbacks.InAppMessagingDismissType.SWIPE, DismissType.SWIPE);
        dismissTransform.put(FirebaseInAppMessagingDisplayCallbacks.InAppMessagingDismissType.UNKNOWN_DISMISS_TYPE, DismissType.UNKNOWN_DISMISS_TYPE);
    }

    public MetricsLoggerClient(EngagementMetricsLoggerInterface engagementMetricsLoggerInterface, AnalyticsConnector analyticsConnector2, FirebaseApp firebaseApp2, FirebaseInstanceId firebaseInstanceId2, Clock clock2, DeveloperListenerManager developerListenerManager2) {
        this.engagementMetricsLogger = engagementMetricsLoggerInterface;
        this.analyticsConnector = analyticsConnector2;
        this.firebaseApp = firebaseApp2;
        this.firebaseInstanceId = firebaseInstanceId2;
        this.clock = clock2;
        this.developerListenerManager = developerListenerManager2;
    }

    public void logImpression(InAppMessage inAppMessage) {
        if (!isTestCampaign(inAppMessage)) {
            this.engagementMetricsLogger.logEvent(createEventEntry(inAppMessage, EventType.IMPRESSION_EVENT_TYPE).toByteArray());
            logEventAsync(inAppMessage, "fiam_impression", impressionCountsAsConversion(inAppMessage));
        }
        this.developerListenerManager.impressionDetected(inAppMessage);
    }

    public void logMessageClick(InAppMessage inAppMessage, Action action) {
        if (!isTestCampaign(inAppMessage)) {
            this.engagementMetricsLogger.logEvent(createEventEntry(inAppMessage, EventType.CLICK_EVENT_TYPE).toByteArray());
            logEventAsync(inAppMessage, "fiam_action", true);
        }
        this.developerListenerManager.messageClicked(inAppMessage, action);
    }

    public void logRenderError(InAppMessage inAppMessage, FirebaseInAppMessagingDisplayCallbacks.InAppMessagingErrorReason inAppMessagingErrorReason) {
        if (!isTestCampaign(inAppMessage)) {
            this.engagementMetricsLogger.logEvent(createRenderErrorEntry(inAppMessage, errorTransform.get(inAppMessagingErrorReason)).toByteArray());
        }
        this.developerListenerManager.displayErrorEncountered(inAppMessage, inAppMessagingErrorReason);
    }

    public void logDismiss(InAppMessage inAppMessage, FirebaseInAppMessagingDisplayCallbacks.InAppMessagingDismissType inAppMessagingDismissType) {
        if (!isTestCampaign(inAppMessage)) {
            this.engagementMetricsLogger.logEvent(createDismissEntry(inAppMessage, dismissTransform.get(inAppMessagingDismissType)).toByteArray());
            logEventAsync(inAppMessage, "fiam_dismiss", false);
        }
    }

    private CampaignAnalytics createEventEntry(InAppMessage inAppMessage, EventType eventType) {
        return (CampaignAnalytics) createCampaignAnalyticsBuilder(inAppMessage).setEventType(eventType).build();
    }

    private CampaignAnalytics createDismissEntry(InAppMessage inAppMessage, DismissType dismissType) {
        return (CampaignAnalytics) createCampaignAnalyticsBuilder(inAppMessage).setDismissType(dismissType).build();
    }

    private CampaignAnalytics createRenderErrorEntry(InAppMessage inAppMessage, RenderErrorReason renderErrorReason) {
        return (CampaignAnalytics) createCampaignAnalyticsBuilder(inAppMessage).setRenderErrorReason(renderErrorReason).build();
    }

    private CampaignAnalytics.Builder createCampaignAnalyticsBuilder(InAppMessage inAppMessage) {
        return CampaignAnalytics.newBuilder().setFiamSdkVersion("19.0.5").setProjectNumber(this.firebaseApp.getOptions().getGcmSenderId()).setCampaignId(inAppMessage.getCampaignMetadata().getCampaignId()).setClientApp(createClientAppInfo()).setClientTimestampMillis(this.clock.now());
    }

    private ClientAppInfo createClientAppInfo() {
        return (ClientAppInfo) ClientAppInfo.newBuilder().setGoogleAppId(this.firebaseApp.getOptions().getApplicationId()).setFirebaseInstanceId(this.firebaseInstanceId.getId()).build();
    }

    private void logEventAsync(InAppMessage inAppMessage, String str, boolean z) {
        String campaignId = inAppMessage.getCampaignMetadata().getCampaignId();
        Bundle collectAnalyticsParams = collectAnalyticsParams(inAppMessage.getCampaignMetadata().getCampaignName(), campaignId);
        Logging.logd("Sending event=" + str + " params=" + collectAnalyticsParams);
        AnalyticsConnector analyticsConnector2 = this.analyticsConnector;
        if (analyticsConnector2 != null) {
            analyticsConnector2.logEvent("fiam", str, collectAnalyticsParams);
            if (z) {
                AnalyticsConnector analyticsConnector3 = this.analyticsConnector;
                analyticsConnector3.setUserProperty("fiam", "_ln", "fiam:" + campaignId);
                return;
            }
            return;
        }
        Logging.logw("Unable to log event: analytics library is missing");
    }

    /* access modifiers changed from: package-private */
    public Bundle collectAnalyticsParams(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("_nmid", str2);
        bundle.putString(Constants.ScionAnalytics.PARAM_MESSAGE_NAME, str);
        try {
            bundle.putInt(Constants.ScionAnalytics.PARAM_MESSAGE_DEVICE_TIME, (int) (this.clock.now() / 1000));
        } catch (NumberFormatException e) {
            Logging.logw("Error while parsing use_device_time in FIAM event: " + e.getMessage());
        }
        return bundle;
    }

    /* renamed from: com.google.firebase.inappmessaging.internal.MetricsLoggerClient$1  reason: invalid class name */
    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$inappmessaging$model$MessageType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.google.firebase.inappmessaging.model.MessageType[] r0 = com.google.firebase.inappmessaging.model.MessageType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$firebase$inappmessaging$model$MessageType = r0
                com.google.firebase.inappmessaging.model.MessageType r1 = com.google.firebase.inappmessaging.model.MessageType.CARD     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$firebase$inappmessaging$model$MessageType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.inappmessaging.model.MessageType r1 = com.google.firebase.inappmessaging.model.MessageType.MODAL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$firebase$inappmessaging$model$MessageType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.firebase.inappmessaging.model.MessageType r1 = com.google.firebase.inappmessaging.model.MessageType.BANNER     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$firebase$inappmessaging$model$MessageType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.firebase.inappmessaging.model.MessageType r1 = com.google.firebase.inappmessaging.model.MessageType.IMAGE_ONLY     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.inappmessaging.internal.MetricsLoggerClient.AnonymousClass1.<clinit>():void");
        }
    }

    private boolean impressionCountsAsConversion(InAppMessage inAppMessage) {
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$inappmessaging$model$MessageType[inAppMessage.getMessageType().ordinal()];
        if (i == 1) {
            CardMessage cardMessage = (CardMessage) inAppMessage;
            boolean z = !isValidAction(cardMessage.getPrimaryAction());
            boolean z2 = !isValidAction(cardMessage.getSecondaryAction());
            if (!z || !z2) {
                return false;
            }
            return true;
        } else if (i == 2) {
            return !isValidAction(((ModalMessage) inAppMessage).getAction());
        } else {
            if (i == 3) {
                return !isValidAction(((BannerMessage) inAppMessage).getAction());
            }
            if (i == 4) {
                return !isValidAction(((ImageOnlyMessage) inAppMessage).getAction());
            }
            Logging.loge("Unable to determine if impression should be counted as conversion.");
            return false;
        }
    }

    private boolean isTestCampaign(InAppMessage inAppMessage) {
        return inAppMessage.getCampaignMetadata().getIsTestMessage();
    }

    private boolean isValidAction(@Nullable Action action) {
        return (action == null || action.getActionUrl() == null || action.getActionUrl().isEmpty()) ? false : true;
    }
}
