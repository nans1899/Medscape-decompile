package com.medscape.android.consult.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Html;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.appboy.AppboyEventsHandler;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.consult.fragments.ConsultPostDetailFragment;
import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.util.Util;
import com.medscape.android.util.constants.AppboyConstants;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.wbmd.wbmdcommons.logging.Trace;

public class ConsultPostDetailActivity extends BaseActivity {
    private static final String TAG = ConsultPostDetailActivity.class.getSimpleName();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_fragment_container);
        initializeFragment();
        setUpActionBar();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        AppboyEventsHandler.routeDailyEventsToFirebaseOrBraze(this, AppboyConstants.APPBOY_EVENT_CONSULT_VIEWED);
    }

    private void initializeFragment() {
        FragmentTransaction beginTransaction;
        if (getSupportFragmentManager() != null && (beginTransaction = getSupportFragmentManager().beginTransaction()) != null) {
            ConsultPostDetailFragment newInstance = ConsultPostDetailFragment.newInstance(getIntent().getExtras());
            if (newInstance != null) {
                beginTransaction.add((int) R.id.content_frame, (Fragment) newInstance, Constants.FRAGMENT_TAG_CONSULT_DETAIL);
                beginTransaction.commit();
                return;
            }
            Trace.w(TAG, "No post");
            finish();
        }
    }

    private void setUpActionBar() {
        Bundle extras;
        Parcelable parcelable;
        Intent intent = getIntent();
        if (intent != null && (extras = intent.getExtras()) != null && (parcelable = extras.getParcelable(Constants.EXTRA_CONSULT_POST)) != null && (parcelable instanceof ConsultPost)) {
            ConsultPost consultPost = (ConsultPost) parcelable;
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                String subject = consultPost.getSubject();
                if (StringExtensions.isNullOrEmpty(subject)) {
                    subject = "Consult";
                }
                supportActionBar.setDisplayShowTitleEnabled(true);
                supportActionBar.setTitle((CharSequence) Html.fromHtml("<font color=#ffffff>" + subject + "</font>"));
                supportActionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.medscape_blue)));
                supportActionBar.setDisplayHomeAsUpEnabled(true);
                supportActionBar.setHomeAsUpIndicator((int) R.drawable.ic_arrow_back_white_24dp);
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        onBackPressed();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 4023 && i2 == -1) {
            setUpActionBar();
            initializeFragment();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        Util.hideKeyboard(this);
        finish();
    }
}
