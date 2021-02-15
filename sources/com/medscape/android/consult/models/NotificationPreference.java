package com.medscape.android.consult.models;

import java.io.Serializable;

public class NotificationPreference implements Serializable {
    public static final int DISTRIBUTION_EMAIL = 1;
    public static final int DISTRIBUTION_PUSH = 2;
    public static final String KEY_DISTRIBUTION_TYPE = "DistributionType";
    public static final String KEY_ENABLED = "Enabled";
    public static final String KEY_NOTIFICATION_TYPE = "NotificationType";
    public static final int TYPE_FOLLOWS = 2;
    public static final int TYPE_MENTIONS = 3;
    public static final int TYPE_REPLIES = 1;
    public int mDistribution;
    public boolean mEnabled;
    public int mType;

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof NotificationPreference)) {
            return false;
        }
        NotificationPreference notificationPreference = (NotificationPreference) obj;
        if (this.mType == notificationPreference.mType && this.mDistribution == notificationPreference.mDistribution && this.mEnabled == notificationPreference.mEnabled) {
            return true;
        }
        return false;
    }
}
