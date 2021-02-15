package com.wbmd.qxcalculator.activities.common;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ProgressBar;
import androidx.appcompat.widget.Toolbar;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.fragments.common.WebViewFragment;

public class FragmentContainerActivity extends QxMDActivity {
    public static final String EXTRA_FRAGMENT_TYPE = "EXTRA_FRAGMENT_TYPE";
    public static final String EXTRA_FRAGMENT_TYPE_ACCOUNT_DETAILS = "EXTRA_FRAGMENT_TYPE_ACCOUNT_DETAILS";
    public static final String EXTRA_FRAGMENT_TYPE_APP_INFORMATION = "EXTRA_FRAGMENT_TYPE_APP_INFORMATION";
    public static final String EXTRA_FRAGMENT_TYPE_APP_SETTINGS = "EXTRA_FRAGMENT_TYPE_APP_SETTINGS";
    public static final String EXTRA_FRAGMENT_TYPE_CME_TRACKING = "EXTRA_FRAGMENT_TYPE_CME_TRACKING";
    public static final String EXTRA_FRAGMENT_TYPE_EDIT_DEBUG_GROUPS = "EXTRA_FRAGMENT_TYPE_EDIT_DEBUG_GROUPS";
    public static final String EXTRA_FRAGMENT_TYPE_EDIT_EMAIL = "EXTRA_FRAGMENT_TYPE_EDIT_EMAIL";
    public static final String EXTRA_FRAGMENT_TYPE_EDIT_NAME = "EXTRA_FRAGMENT_TYPE_EDIT_NAME";
    public static final String EXTRA_FRAGMENT_TYPE_EDIT_NPI_NUMBER = "EXTRA_FRAGMENT_TYPE_EDIT_NPI_NUMBER";
    public static final String EXTRA_FRAGMENT_TYPE_EDIT_PASSWORD = "EXTRA_FRAGMENT_TYPE_EDIT_PASSWORD";
    public static final String EXTRA_FRAGMENT_TYPE_EDIT_PROFESSION = "EXTRA_FRAGMENT_TYPE_EDIT_PROFESSION";
    public static final String EXTRA_FRAGMENT_TYPE_EDIT_ZIP_WORK = "EXTRA_FRAGMENT_TYPE_EDIT_ZIP_WORK";
    public static final String EXTRA_FRAGMENT_TYPE_FORGOT_PW = "EXTRA_FRAGMENT_TYPE_FORGOT_PW";
    public static final String EXTRA_FRAGMENT_TYPE_LOGIN = "EXTRA_FRAGMENT_TYPE_LOGIN";
    public static final String EXTRA_FRAGMENT_TYPE_ONBOARDING = "EXTRA_FRAGMENT_TYPE_ONBOARDING";
    public static final String EXTRA_FRAGMENT_TYPE_SETTINGS_DARK_MODE = "EXTRA_FRAGMENT_TYPE_SETTINGS_DARK_MODE";
    public static final String EXTRA_FRAGMENT_TYPE_SETTINGS_NOTIFICATIONS = "EXTRA_FRAGMENT_TYPE_SETTINGS_NOTIFICATIONS";
    public static final String EXTRA_FRAGMENT_TYPE_WEB_VIEW = "EXTRA_FRAGMENT_TYPE_WEB_VIEW";
    public ProgressBar webLoadingProgressBar;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.activity_fragment_container;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        this.webLoadingProgressBar = (ProgressBar) findViewById(R.id.web_loading_progress_bar);
        if (bundle != null && getDefaultFragment() != null) {
            return;
        }
        if (getIntent() == null) {
            finish();
            return;
        }
        String stringExtra = getIntent().getStringExtra("EXTRA_FRAGMENT_TYPE");
        if (stringExtra == null) {
            finish();
            return;
        }
        WebViewFragment webViewFragment = null;
        if (!stringExtra.equals(EXTRA_FRAGMENT_TYPE_CME_TRACKING) && !stringExtra.equals(EXTRA_FRAGMENT_TYPE_SETTINGS_NOTIFICATIONS) && !stringExtra.equals(EXTRA_FRAGMENT_TYPE_SETTINGS_DARK_MODE) && !stringExtra.equals(EXTRA_FRAGMENT_TYPE_ONBOARDING)) {
            if (stringExtra.equals(EXTRA_FRAGMENT_TYPE_WEB_VIEW)) {
                webViewFragment = WebViewFragment.newInstance(getIntent().getStringExtra(WebViewFragment.KEY_URL));
            } else {
                stringExtra.equals(EXTRA_FRAGMENT_TYPE_APP_INFORMATION);
            }
        }
        if (webViewFragment == null) {
            finish();
        } else {
            addFragmentToContainer(webViewFragment, R.id.fragment_container);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public void onToolbarBackPressed() {
        if (getIntent().getStringExtra("EXTRA_FRAGMENT_TYPE").equals(EXTRA_FRAGMENT_TYPE_WEB_VIEW)) {
            super.onBackPressed(true);
        } else {
            super.onToolbarBackPressed();
        }
    }
}
