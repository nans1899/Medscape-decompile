package com.medscape.android.consult.interfaces;

import com.medscape.android.consult.models.NotificationPreference;
import java.util.ArrayList;

public interface IGetNotificationPreferencesListener {
    void onNotificationPreferencesFailedToReceive();

    void onNotificationPreferencesReceived(ArrayList<NotificationPreference> arrayList);
}
