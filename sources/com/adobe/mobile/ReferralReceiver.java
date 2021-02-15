package com.adobe.mobile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ReferralReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Analytics.processReferrer(context, intent);
    }
}
