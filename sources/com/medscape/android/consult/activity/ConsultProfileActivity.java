package com.medscape.android.consult.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Html;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.appboy.AppboyEventsHandler;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.consult.fragments.ConsultProfileFragment;
import com.medscape.android.consult.models.ConsultUser;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.constants.AppboyConstants;
import com.medscape.android.util.media.PhotoUtil;
import com.wbmd.wbmdcommons.logging.Trace;
import java.io.File;

public class ConsultProfileActivity extends BaseActivity {
    private static final String TAG = ConsultProfileActivity.class.getSimpleName();

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
            ConsultProfileFragment newInstance = ConsultProfileFragment.newInstance(getIntent().getExtras());
            if (newInstance != null) {
                beginTransaction.add((int) R.id.content_frame, (Fragment) newInstance, Constants.FRAGMENT_TAG_CONSULT_PROFILE);
                beginTransaction.commit();
                return;
            }
            Trace.w(TAG, "No consult user");
            finish();
        }
    }

    private void setUpActionBar() {
        Bundle extras;
        Parcelable parcelable;
        Intent intent = getIntent();
        if (intent != null && (extras = intent.getExtras()) != null && (parcelable = extras.getParcelable(Constants.CONSULT_USER_BUNDLE_KEY)) != null && (parcelable instanceof ConsultUser)) {
            ConsultUser consultUser = (ConsultUser) parcelable;
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle((CharSequence) Html.fromHtml("<font color=#ffffff>" + consultUser.getDisplayName() + "</font>"));
                supportActionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.medscape_blue)));
                supportActionBar.setHomeAsUpIndicator((int) R.drawable.ic_arrow_back_white_24dp);
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        finish();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 4000) {
            handleImageCaptureResult(i2);
        } else if (i == 4001) {
            handleImageChooseResult(i2, intent);
        }
    }

    private void handleImageCaptureResult(int i) {
        Fragment findFragmentByTag;
        if (i == -1) {
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            if (supportFragmentManager != null && (findFragmentByTag = supportFragmentManager.findFragmentByTag(Constants.FRAGMENT_TAG_CONSULT_PROFILE)) != null && (findFragmentByTag instanceof ConsultProfileFragment)) {
                ((ConsultProfileFragment) findFragmentByTag).updateProfileBitmapForCurrentUser((String) null);
                return;
            }
            return;
        }
        PhotoUtil.clearRecentPhotoLocation();
    }

    private void handleImageChooseResult(int i, Intent intent) {
        Fragment findFragmentByTag;
        if (i == -1) {
            String filePathForImageFromGallery = PhotoUtil.getFilePathForImageFromGallery(this, intent);
            if (StringUtil.isNotEmpty(filePathForImageFromGallery)) {
                File file = new File(filePathForImageFromGallery);
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                if (supportFragmentManager != null && (findFragmentByTag = supportFragmentManager.findFragmentByTag(Constants.FRAGMENT_TAG_CONSULT_PROFILE)) != null && (findFragmentByTag instanceof ConsultProfileFragment)) {
                    ((ConsultProfileFragment) findFragmentByTag).updateProfileBitmapForCurrentUser(file.getAbsolutePath());
                    return;
                }
                return;
            }
            PhotoUtil.clearRecentPhotoLocation();
            return;
        }
        PhotoUtil.clearRecentPhotoLocation();
    }
}
