package com.medscape.android.reference;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.facebook.appevents.AppEventsConstants;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.activity.AbstractBreadcrumbNavigableActivity;
import com.medscape.android.appboy.AppboyEventsHandler;
import com.medscape.android.update.BackgroundClinicalUpdateService;
import com.medscape.android.util.Util;
import com.medscape.android.util.constants.AppboyConstants;
import java.util.Map;

public class ClinicalReferenceInstallationRequestActivity extends AbstractBreadcrumbNavigableActivity implements View.OnClickListener {
    private static final String TAG = "ClinicalReferenceInstallationRequestActivity";
    static String mInitialInstallAlertText = "You now have access to the full 3500+ Medscape Clinical Reference articles. Internet connection required.\n\nSelect Download to have access to the reference without an Internet connection.\n\nDownload Times:\n10 minutes on Wi-Fi, 20 minutes on 3G.";
    static String mResumeAlertText = "You did not finish downloading the Clinical Reference to give you access to articles without an Internet connection.\n\nWould you like to resume it now?";
    static String mUpdateAlertText = "You now have access to the full 3500+ Medscape Clinical Reference articles. Internet connection required.\n\nSelect Download to have access to the reference without an Internet connection.\n\nDownload Times:\n10 minutes on Wi-Fi, 20 minutes on 3G.";
    static String mkFooterText = "You can download the reference later\nby selecting Update Reference\nfrom the More menu";
    private Button downloadButton;
    private Button skipButton;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.clinical_reference_installtion_layout);
        boolean booleanExtra = getIntent().getBooleanExtra("isResume", false);
        TextView textView = (TextView) findViewById(R.id.TopTextView);
        Button button = (Button) findViewById(R.id.downloadButton);
        this.downloadButton = button;
        if (booleanExtra) {
            textView.setText(mResumeAlertText);
            this.downloadButton.setTextColor(getResources().getColor(17170443));
            this.downloadButton.setText("Resume");
        } else {
            button.setTextColor(getResources().getColor(17170443));
            this.downloadButton.setText(getResources().getText(R.string.button_download_text));
        }
        Button button2 = (Button) findViewById(R.id.skipButton);
        this.skipButton = button2;
        button2.setOnClickListener(this);
        this.downloadButton.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.equals(this.downloadButton)) {
            if (!Util.isSDCardAvailable()) {
                if (!isFinishing()) {
                    showDialog(9);
                }
            } else if (!MedscapeApplication.get().isBackgroundUpdateInProgress()) {
                OmnitureManager.get().trackPageView(this, "other", "install", "downloadckb", (String) null, (String) null, (Map<String, Object>) null);
                startService(new Intent(this, BackgroundClinicalUpdateService.class));
            }
            AppboyEventsHandler.logDailyEvent(this, AppboyConstants.APPBOY_EVENT_CKB_DOWNLOAD, this);
        }
        if (view.equals(this.skipButton)) {
            Settings.singleton(this).saveSetting(Constants.PREF_UPDATE_COMPLETE, "YES");
            Settings.singleton(this).saveSetting(Constants.PREF_CLINICAL_INSTALLTION_FAIL, AppEventsConstants.EVENT_PARAM_VALUE_NO);
            Settings.singleton(this).saveSetting(Constants.PREF_CLINICAL_INSTALLTION_PLIST_TEXT, "");
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        super.setupActionBar();
        setTitle(getResources().getString(R.string.debug_reference_section_header));
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return true;
        }
        finish();
        return true;
    }
}
