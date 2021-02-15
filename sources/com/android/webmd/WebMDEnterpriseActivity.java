package com.android.webmd;

import android.app.Activity;
import android.os.Bundle;
import com.android.webmd.validation.DeviceCompare;

public class WebMDEnterpriseActivity extends Activity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DeviceCompare.deviceValidForEnterprise(this);
    }
}
