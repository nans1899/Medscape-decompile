package com.appboy;

import android.app.Activity;
import android.content.Intent;
import com.appboy.events.ContentCardsUpdatedEvent;
import com.appboy.events.FeedUpdatedEvent;
import com.appboy.events.IEventSubscriber;
import com.appboy.events.InAppMessageEvent;
import com.appboy.events.SubmitFeedbackFailed;
import com.appboy.events.SubmitFeedbackSucceeded;
import com.appboy.models.outgoing.AppboyProperties;
import java.math.BigDecimal;

public interface IAppboy {
    void changeUser(String str);

    void closeSession(Activity activity);

    IAppboyImageLoader getAppboyImageLoader();

    String getAppboyPushMessageRegistrationId();

    int getContentCardCount();

    int getContentCardUnviewedCount();

    long getContentCardsLastUpdatedInSecondsFromEpoch();

    AppboyUser getCurrentUser();

    String getInstallTrackingId();

    void logContentCardsDisplayed();

    void logCustomEvent(String str);

    void logCustomEvent(String str, AppboyProperties appboyProperties);

    @Deprecated
    void logFeedCardClick(String str);

    @Deprecated
    void logFeedCardImpression(String str);

    void logFeedDisplayed();

    void logFeedbackDisplayed();

    void logPurchase(String str, String str2, BigDecimal bigDecimal);

    void logPurchase(String str, String str2, BigDecimal bigDecimal, int i);

    void logPurchase(String str, String str2, BigDecimal bigDecimal, int i, AppboyProperties appboyProperties);

    void logPurchase(String str, String str2, BigDecimal bigDecimal, AppboyProperties appboyProperties);

    void logPushDeliveryEvent(String str);

    void logPushNotificationActionClicked(String str, String str2);

    void logPushNotificationOpened(Intent intent);

    void logPushNotificationOpened(String str);

    void logPushStoryPageClicked(String str, String str2);

    void openSession(Activity activity);

    void registerAppboyPushMessages(String str);

    <T> void removeSingleSubscription(IEventSubscriber<T> iEventSubscriber, Class<T> cls);

    void requestContentCardsRefresh(boolean z);

    void requestFeedRefresh();

    void requestFeedRefreshFromCache();

    void requestImmediateDataFlush();

    void setAppboyImageLoader(IAppboyImageLoader iAppboyImageLoader);

    void submitFeedback(String str, String str2, boolean z);

    void subscribeToContentCardsUpdates(IEventSubscriber<ContentCardsUpdatedEvent> iEventSubscriber);

    void subscribeToFeedUpdates(IEventSubscriber<FeedUpdatedEvent> iEventSubscriber);

    void subscribeToFeedbackRequestEvents(IEventSubscriber<SubmitFeedbackSucceeded> iEventSubscriber, IEventSubscriber<SubmitFeedbackFailed> iEventSubscriber2);

    void subscribeToNewInAppMessages(IEventSubscriber<InAppMessageEvent> iEventSubscriber);
}
