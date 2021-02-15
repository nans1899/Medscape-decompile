package com.wbmd.wbmddatacompliance.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.wbmd.wbmddatacompliance.R;
import com.wbmd.wbmddatacompliance.gdpr.GDPRStateManager;
import com.wbmd.wbmddatacompliance.utils.Constants;

public class AcceptActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = AcceptActivity.class.getSimpleName();
    private View mButtonAccept;
    /* access modifiers changed from: private */
    public String mPrivacyUrl;
    private TextView mTextViewFirstParagraph;
    private TextView mTextViewSecondParagraph;

    public void onBackPressed() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i(TAG, "onCreate - AcceptActivity");
        if (isProfessionalApplication()) {
            setContentView(R.layout.activity_accept_professional);
        } else {
            setContentView(R.layout.activity_accept_consumer);
        }
        this.mPrivacyUrl = getPrivacyPolicy();
        View findViewById = findViewById(R.id.button_accept);
        this.mButtonAccept = findViewById;
        findViewById.setOnClickListener(this);
        this.mTextViewFirstParagraph = (TextView) findViewById(R.id.text_view_first_paragraph);
        this.mTextViewSecondParagraph = (TextView) findViewById(R.id.text_view_second_paragraph);
        setUpFirstParagraphsPrivacyPolicyLink(this.mTextViewFirstParagraph);
        setUpSecondParagraph(this.mTextViewSecondParagraph);
        sendPageViewBroadcast();
    }

    private String getPrivacyPolicy() {
        return isConsumerApplication() ? "https://www.webmd.com/about-webmd-policies/gdpr-privacy-policy" : "https://www.medscape.com/public/cookies";
    }

    public void onClick(View view) {
        if (view.getId() == R.id.button_accept) {
            Log.d(TAG, "accept button clicked");
            GDPRStateManager.onPromptAccepted(getApplicationContext());
            sendAcceptActionBroadcast();
            finish();
        }
    }

    private void setUpFirstParagraphsPrivacyPolicyLink(TextView textView) {
        String charSequence = textView.getText().toString();
        SpannableString spannableString = new SpannableString(charSequence);
        if (isProfessionalApplication()) {
            spannableString.setSpan(new ClickableSpan() {
                public void onClick(View view) {
                    AcceptActivity acceptActivity = AcceptActivity.this;
                    acceptActivity.launchWebActivity(Constants.COOKIE_PRIVACY_TITLE, acceptActivity.mPrivacyUrl);
                }
            }, charSequence.length() - 14, charSequence.length() - 1, 33);
        } else {
            spannableString.setSpan(new ClickableSpan() {
                public void onClick(View view) {
                    AcceptActivity acceptActivity = AcceptActivity.this;
                    acceptActivity.launchWebActivity(Constants.PRIVACY_TITLE, acceptActivity.mPrivacyUrl);
                }
            }, charSequence.length() - 21, charSequence.length() - 1, 33);
        }
        textView.setText(spannableString);
        textView.setClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void setUpSecondParagraph(TextView textView) {
        String charSequence = textView.getText().toString();
        SpannableString spannableString = new SpannableString(charSequence);
        if (isProfessionalApplication()) {
            spannableString.setSpan(new ClickableSpan() {
                public void onClick(View view) {
                    AcceptActivity acceptActivity = AcceptActivity.this;
                    acceptActivity.launchWebActivity(Constants.COOKIE_PRIVACY_TITLE, acceptActivity.mPrivacyUrl);
                }
            }, charSequence.length() - 14, charSequence.length() - 1, 33);
        } else {
            spannableString.setSpan(new ClickableSpan() {
                public void onClick(View view) {
                    AcceptActivity acceptActivity = AcceptActivity.this;
                    acceptActivity.launchWebActivity(Constants.PRIVACY_TITLE, acceptActivity.mPrivacyUrl);
                }
            }, 46, 66, 33);
        }
        spannableString.setSpan(new StyleSpan(1), 12, 21, 33);
        textView.setText(spannableString);
        textView.setClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /* access modifiers changed from: private */
    public void launchWebActivity(String str, String str2) {
        Intent intent = new Intent(getApplicationContext(), DetailsWebViewActivity.class);
        intent.putExtra(Constants.EXTRA_URL_KEY, str2);
        intent.putExtra(Constants.EXTRA_TITLE_KEY, str);
        startActivity(intent);
    }

    private void sendAcceptActionBroadcast() {
        Intent intent = new Intent();
        intent.setAction(Constants.BROADCAST_ACCEPT_ACTION);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void sendPageViewBroadcast() {
        Intent intent = new Intent();
        intent.setAction(Constants.BROADCAST_ACTIVITY_VIEW);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = getIntent().getStringExtra(com.wbmd.wbmddatacompliance.utils.Constants.EXTRA_APPLICATION_TYPE);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isProfessionalApplication() {
        /*
            r2 = this;
            android.content.Intent r0 = r2.getIntent()
            if (r0 == 0) goto L_0x001c
            android.content.Intent r0 = r2.getIntent()
            java.lang.String r1 = "extra_application_type"
            java.lang.String r0 = r0.getStringExtra(r1)
            if (r0 == 0) goto L_0x001c
            java.lang.String r1 = "professional"
            boolean r0 = r0.equalsIgnoreCase(r1)
            if (r0 == 0) goto L_0x001c
            r0 = 1
            return r0
        L_0x001c:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.wbmddatacompliance.activities.AcceptActivity.isProfessionalApplication():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = getIntent().getStringExtra(com.wbmd.wbmddatacompliance.utils.Constants.EXTRA_APPLICATION_TYPE);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isConsumerApplication() {
        /*
            r2 = this;
            android.content.Intent r0 = r2.getIntent()
            if (r0 == 0) goto L_0x001c
            android.content.Intent r0 = r2.getIntent()
            java.lang.String r1 = "extra_application_type"
            java.lang.String r0 = r0.getStringExtra(r1)
            if (r0 == 0) goto L_0x001c
            java.lang.String r1 = "consumer"
            boolean r0 = r0.equalsIgnoreCase(r1)
            if (r0 == 0) goto L_0x001c
            r0 = 1
            return r0
        L_0x001c:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.wbmddatacompliance.activities.AcceptActivity.isConsumerApplication():boolean");
    }
}
