package com.appboy;

import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import com.appboy.configuration.AppboyConfigurationProvider;

public interface IAppboyNotificationFactory {
    Notification createNotification(AppboyConfigurationProvider appboyConfigurationProvider, Context context, Bundle bundle, Bundle bundle2);
}
