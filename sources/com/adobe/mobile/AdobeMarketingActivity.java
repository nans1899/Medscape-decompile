package com.adobe.mobile;

import android.app.Activity;
import android.os.Bundle;

public abstract class AdobeMarketingActivity extends Activity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Config.setContext(getApplicationContext());
    }

    public void onPause() {
        super.onPause();
        Config.pauseCollectingLifecycleData();
    }

    public void onResume() {
        super.onResume();
        Config.collectLifecycleData(this);
    }
}
