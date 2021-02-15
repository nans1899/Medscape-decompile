package com.appboy.ui.activities;

import android.app.Activity;
import com.appboy.Appboy;
import com.appboy.ui.inappmessage.AppboyInAppMessageManager;

public class AppboyBaseActivity extends Activity {
    public void onStart() {
        super.onStart();
        Appboy.getInstance(this).openSession(this);
    }

    public void onResume() {
        super.onResume();
        AppboyInAppMessageManager.getInstance().registerInAppMessageManager(this);
    }

    public void onPause() {
        super.onPause();
        AppboyInAppMessageManager.getInstance().unregisterInAppMessageManager(this);
    }

    public void onStop() {
        super.onStop();
        Appboy.getInstance(this).closeSession(this);
    }
}
