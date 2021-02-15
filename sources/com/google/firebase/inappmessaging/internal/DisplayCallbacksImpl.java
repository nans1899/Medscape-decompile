package com.google.firebase.inappmessaging.internal;

import android.text.TextUtils;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.inappmessaging.FirebaseInAppMessagingDisplayCallbacks;
import com.google.firebase.inappmessaging.internal.time.Clock;
import com.google.firebase.inappmessaging.model.Action;
import com.google.firebase.inappmessaging.model.InAppMessage;
import com.google.firebase.inappmessaging.model.RateLimit;
import com.google.internal.firebase.inappmessaging.v1.sdkserving.CampaignImpression;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Scheduler;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class DisplayCallbacksImpl implements FirebaseInAppMessagingDisplayCallbacks {
    private static final String MESSAGE_CLICK = "message click to metrics logger";
    /* access modifiers changed from: private */
    public static boolean wasImpressed;
    private final RateLimit appForegroundRateLimit;
    private final CampaignCacheClient campaignCacheClient;
    private final Clock clock;
    private final DataCollectionHelper dataCollectionHelper;
    private final ImpressionStorageClient impressionStorageClient;
    private final InAppMessage inAppMessage;
    private final MetricsLoggerClient metricsLoggerClient;
    private final RateLimiterClient rateLimiterClient;
    private final Schedulers schedulers;
    private final String triggeringEvent;

    DisplayCallbacksImpl(ImpressionStorageClient impressionStorageClient2, Clock clock2, Schedulers schedulers2, RateLimiterClient rateLimiterClient2, CampaignCacheClient campaignCacheClient2, RateLimit rateLimit, MetricsLoggerClient metricsLoggerClient2, DataCollectionHelper dataCollectionHelper2, InAppMessage inAppMessage2, String str) {
        this.impressionStorageClient = impressionStorageClient2;
        this.clock = clock2;
        this.schedulers = schedulers2;
        this.rateLimiterClient = rateLimiterClient2;
        this.campaignCacheClient = campaignCacheClient2;
        this.appForegroundRateLimit = rateLimit;
        this.metricsLoggerClient = metricsLoggerClient2;
        this.dataCollectionHelper = dataCollectionHelper2;
        this.inAppMessage = inAppMessage2;
        this.triggeringEvent = str;
        wasImpressed = false;
    }

    public Task<Void> impressionDetected() {
        if (!shouldLog() || wasImpressed) {
            logActionNotTaken("message impression to metrics logger");
            return new TaskCompletionSource().getTask();
        }
        Logging.logd("Attempting to record: " + "message impression to metrics logger");
        return maybeToTask(logToImpressionStore().andThen((CompletableSource) Completable.fromAction(DisplayCallbacksImpl$$Lambda$1.lambdaFactory$(this))).andThen((CompletableSource) updateWasImpressed()).toMaybe(), this.schedulers.io());
    }

    private Completable updateWasImpressed() {
        return Completable.fromAction(DisplayCallbacksImpl$$Lambda$2.lambdaFactory$());
    }

    public Task<Void> messageDismissed(FirebaseInAppMessagingDisplayCallbacks.InAppMessagingDismissType inAppMessagingDismissType) {
        if (shouldLog()) {
            Logging.logd("Attempting to record: " + "message dismissal to metrics logger");
            return logImpressionIfNeeded(Completable.fromAction(DisplayCallbacksImpl$$Lambda$3.lambdaFactory$(this, inAppMessagingDismissType)));
        }
        logActionNotTaken("message dismissal to metrics logger");
        return new TaskCompletionSource().getTask();
    }

    @Deprecated
    public Task<Void> messageClicked() {
        return messageClicked(this.inAppMessage.getAction());
    }

    public Task<Void> messageClicked(Action action) {
        if (!shouldLog()) {
            logActionNotTaken(MESSAGE_CLICK);
            return new TaskCompletionSource().getTask();
        } else if (action.getActionUrl() == null) {
            return messageDismissed(FirebaseInAppMessagingDisplayCallbacks.InAppMessagingDismissType.CLICK);
        } else {
            return logMessageClick(action);
        }
    }

    private Task<Void> logMessageClick(Action action) {
        Logging.logd("Attempting to record: message click to metrics logger");
        return logImpressionIfNeeded(Completable.fromAction(DisplayCallbacksImpl$$Lambda$4.lambdaFactory$(this, action)));
    }

    private boolean actionMatches(Action action, Action action2) {
        if (action == null) {
            return action2 == null || TextUtils.isEmpty(action2.getActionUrl());
        }
        return action.getActionUrl().equals(action2.getActionUrl());
    }

    public Task<Void> displayErrorEncountered(FirebaseInAppMessagingDisplayCallbacks.InAppMessagingErrorReason inAppMessagingErrorReason) {
        if (shouldLog()) {
            Logging.logd("Attempting to record: " + "render error to metrics logger");
            return maybeToTask(logToImpressionStore().andThen((CompletableSource) Completable.fromAction(DisplayCallbacksImpl$$Lambda$5.lambdaFactory$(this, inAppMessagingErrorReason))).andThen((CompletableSource) updateWasImpressed()).toMaybe(), this.schedulers.io());
        }
        logActionNotTaken("render error to metrics logger");
        return new TaskCompletionSource().getTask();
    }

    private boolean shouldLog() {
        return this.dataCollectionHelper.isAutomaticDataCollectionEnabled();
    }

    private Task<Void> logImpressionIfNeeded(Completable completable) {
        if (!wasImpressed) {
            impressionDetected();
        }
        return maybeToTask(completable.toMaybe(), this.schedulers.io());
    }

    private void logActionNotTaken(String str, Maybe<String> maybe) {
        if (maybe != null) {
            Logging.logd(String.format("Not recording: %s. Reason: %s", new Object[]{str, maybe}));
        } else if (this.inAppMessage.getCampaignMetadata().getIsTestMessage()) {
            Logging.logd(String.format("Not recording: %s. Reason: Message is test message", new Object[]{str}));
        } else if (!this.dataCollectionHelper.isAutomaticDataCollectionEnabled()) {
            Logging.logd(String.format("Not recording: %s. Reason: Data collection is disabled", new Object[]{str}));
        } else {
            Logging.logd(String.format("Not recording: %s", new Object[]{str}));
        }
    }

    private void logActionNotTaken(String str) {
        logActionNotTaken(str, (Maybe<String>) null);
    }

    private Completable logToImpressionStore() {
        String campaignId = this.inAppMessage.getCampaignMetadata().getCampaignId();
        Logging.logd("Attempting to record message impression in impression store for id: " + campaignId);
        Completable doOnComplete = this.impressionStorageClient.storeImpression((CampaignImpression) CampaignImpression.newBuilder().setImpressionTimestampMillis(this.clock.now()).setCampaignId(campaignId).build()).doOnError(DisplayCallbacksImpl$$Lambda$6.lambdaFactory$()).doOnComplete(DisplayCallbacksImpl$$Lambda$7.lambdaFactory$());
        return InAppMessageStreamManager.isAppForegroundEvent(this.triggeringEvent) ? this.rateLimiterClient.increment(this.appForegroundRateLimit).doOnError(DisplayCallbacksImpl$$Lambda$8.lambdaFactory$()).doOnComplete(DisplayCallbacksImpl$$Lambda$9.lambdaFactory$()).onErrorComplete().andThen((CompletableSource) doOnComplete) : doOnComplete;
    }

    private static <T> Task<T> maybeToTask(Maybe<T> maybe, Scheduler scheduler) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        taskCompletionSource.getClass();
        maybe.doOnSuccess(DisplayCallbacksImpl$$Lambda$10.lambdaFactory$(taskCompletionSource)).switchIfEmpty(Maybe.fromCallable(DisplayCallbacksImpl$$Lambda$11.lambdaFactory$(taskCompletionSource))).onErrorResumeNext(DisplayCallbacksImpl$$Lambda$12.lambdaFactory$(taskCompletionSource)).subscribeOn(scheduler).subscribe();
        return taskCompletionSource.getTask();
    }

    static /* synthetic */ MaybeSource lambda$maybeToTask$10(TaskCompletionSource taskCompletionSource, Throwable th) throws Exception {
        if (th instanceof Exception) {
            taskCompletionSource.setException((Exception) th);
        } else {
            taskCompletionSource.setException(new RuntimeException(th));
        }
        return Maybe.empty();
    }
}
