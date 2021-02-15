package com.appboy.push;

import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.appboy.IAppboyNotificationFactory;
import com.appboy.configuration.AppboyConfigurationProvider;

public class AppboyNotificationFactory implements IAppboyNotificationFactory {
    private static volatile AppboyNotificationFactory sInstance;

    public static AppboyNotificationFactory getInstance() {
        if (sInstance == null) {
            synchronized (AppboyNotificationFactory.class) {
                if (sInstance == null) {
                    sInstance = new AppboyNotificationFactory();
                }
            }
        }
        return sInstance;
    }

    public NotificationCompat.Builder populateNotificationBuilder(AppboyConfigurationProvider appboyConfigurationProvider, Context context, Bundle bundle, Bundle bundle2) {
        AppboyNotificationUtils.prefetchBitmapsIfNewlyReceivedStoryPush(context, bundle);
        NotificationCompat.Builder autoCancel = new NotificationCompat.Builder(context).setAutoCancel(true);
        AppboyNotificationUtils.setTitleIfPresent(autoCancel, bundle);
        AppboyNotificationUtils.setContentIfPresent(autoCancel, bundle);
        AppboyNotificationUtils.setTickerIfPresent(autoCancel, bundle);
        AppboyNotificationUtils.setSetShowWhen(autoCancel, bundle);
        AppboyNotificationUtils.setContentIntentIfPresent(context, autoCancel, bundle);
        AppboyNotificationUtils.setDeleteIntent(context, autoCancel, bundle);
        AppboyNotificationUtils.setSmallIcon(appboyConfigurationProvider, autoCancel);
        AppboyNotificationUtils.setLargeIconIfPresentAndSupported(context, appboyConfigurationProvider, autoCancel, bundle);
        AppboyNotificationUtils.setSoundIfPresentAndSupported(autoCancel, bundle);
        AppboyNotificationUtils.setSummaryTextIfPresentAndSupported(autoCancel, bundle);
        AppboyNotificationUtils.setPriorityIfPresentAndSupported(autoCancel, bundle);
        AppboyNotificationUtils.setStyleIfSupported(context, autoCancel, bundle, bundle2);
        AppboyNotificationActionUtils.addNotificationActions(context, autoCancel, bundle);
        AppboyNotificationUtils.setAccentColorIfPresentAndSupported(appboyConfigurationProvider, autoCancel, bundle);
        AppboyNotificationUtils.setCategoryIfPresentAndSupported(autoCancel, bundle);
        AppboyNotificationUtils.setVisibilityIfPresentAndSupported(autoCancel, bundle);
        AppboyNotificationUtils.setPublicVersionIfPresentAndSupported(context, appboyConfigurationProvider, autoCancel, bundle);
        AppboyNotificationUtils.setNotificationChannelIfSupported(context, appboyConfigurationProvider, autoCancel, bundle);
        AppboyNotificationUtils.setNotificationBadgeNumberIfPresent(autoCancel, bundle);
        return autoCancel;
    }

    public Notification createNotification(AppboyConfigurationProvider appboyConfigurationProvider, Context context, Bundle bundle, Bundle bundle2) {
        return populateNotificationBuilder(appboyConfigurationProvider, context, bundle, bundle2).build();
    }
}
