package com.medscape.android.consult.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import com.medscape.android.R;
import com.medscape.android.activity.AbstractBreadcrumbNavigableActivity;

public class NotificationSettingsActivity extends AbstractBreadcrumbNavigableActivity {
    private ProgressBar mProgress;
    private View mPushPreferencesView;
    private View mRootView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_notifications);
        setTitle(R.string.title_notifications_settings);
        this.mRootView = findViewById(R.id.rootView);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress);
        this.mProgress = progressBar;
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.medscape_blue), PorterDuff.Mode.MULTIPLY);
        View findViewById = findViewById(R.id.push_preferences_row);
        this.mPushPreferencesView = findViewById;
        findViewById.setClickable(true);
        this.mPushPreferencesView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                NotificationSettingsActivity.this.lambda$onCreate$0$NotificationSettingsActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$onCreate$0$NotificationSettingsActivity(View view) {
        startActivity(new Intent(this, ConsultPushPreferencesActivity.class));
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
}
