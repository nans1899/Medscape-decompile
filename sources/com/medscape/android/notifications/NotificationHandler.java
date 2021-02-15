package com.medscape.android.notifications;

import android.content.Context;
import android.content.Intent;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.updater.UpdateManager;
import com.medscape.android.welcome.WelcomeActivity;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import java.util.Map;

public class NotificationHandler {
    private Context mCtx = null;

    public NotificationHandler(Context context) {
        this.mCtx = context;
    }

    public void handlePayload(String str) {
        if (this.mCtx.getSharedPreferences(UpdateManager.SETTINGS_PREFS, 0).getFloat("version", -1.0f) != -1.0f) {
            if (AccountProvider.isUserLoggedIn(this.mCtx)) {
                showNotification(str);
            } else {
                handleUserNotLoggedIn();
            }
        }
    }

    private void handleUserNotLoggedIn() {
        Intent intent = new Intent(this.mCtx, WelcomeActivity.class);
        intent.setFlags(268435456);
        this.mCtx.startActivity(intent);
    }

    private void showNotification(String str) {
        OmnitureManager.get().markModule(true, "push", Constants.OMNITURE_MLINK_OPEN, (Map<String, Object>) null);
        Intent intent = new Intent(this.mCtx, NotificationAuthenticationGateActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("payload", str);
        this.mCtx.startActivity(intent);
    }
}
