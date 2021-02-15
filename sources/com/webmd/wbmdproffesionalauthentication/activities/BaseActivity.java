package com.webmd.wbmdproffesionalauthentication.activities;

import android.app.ActionBar;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.ib.foreceup.util.Util;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.webmd.wbmdproffesionalauthentication.R;

public abstract class BaseActivity extends AppCompatActivity {
    private BroadcastReceiver mForceupReceiver;
    protected String mPvid;

    /* access modifiers changed from: protected */
    public void setTitleEmpty() {
        setTitle(StringExtensions.EMPTY);
    }

    public void setCustomTitle(String str) {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(str);
        }
        androidx.appcompat.app.ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle((CharSequence) str);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.app_accent_dark));
        if (getResources().getBoolean(R.bool.isPhone)) {
            setRequestedOrientation(1);
        }
        this.mForceupReceiver = Util.registerForceupReceiver(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.mForceupReceiver == null) {
            this.mForceupReceiver = Util.registerForceupReceiver(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        Util.unregisterForceupReceiver(this, this.mForceupReceiver);
        this.mForceupReceiver = null;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Util.unregisterForceupReceiver(this, this.mForceupReceiver);
        super.onDestroy();
    }
}
