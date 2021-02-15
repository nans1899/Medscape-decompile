package com.webmd.wbmdcmepulse.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.logging.Trace;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.adapters.cmetracker.CMETrackerTabAdapter;
import com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerHeaderFragment;
import com.webmd.wbmdcmepulse.models.CMEPulseException;
import com.webmd.wbmdcmepulse.models.articles.Article;
import com.webmd.wbmdcmepulse.models.cmetracker.CMETrackerResponse;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdcmepulse.models.utils.Extensions;
import com.webmd.wbmdcmepulse.models.utils.Utilities;
import com.webmd.wbmdcmepulse.providers.CMETrackerDataProvider;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CMETrackerActivity extends CmeBaseActivity implements CMETrackerHeaderFragment.OnFragmentInteractionListener {
    private static final int NUMBER_OF_PAST_YEARS_TO_GET = 3;
    /* access modifiers changed from: private */
    public static final String TAG = CMETrackerActivity.class.getSimpleName();
    private Context mContext;
    /* access modifiers changed from: private */
    public View mProgress;
    /* access modifiers changed from: private */
    public ViewPager mViewPager;
    /* access modifiers changed from: private */
    public String parentActivityName;

    public void onFragmentInteraction(Uri uri) {
    }

    public void sendArticleViewedEvent(Article article) {
    }

    /* access modifiers changed from: package-private */
    public void trackOmniturePageView() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        this.parentActivityName = getIntent().getStringExtra(Constants.RETURN_ACTIVITY);
        handleDeepLink();
        setContentView(R.layout.cme_activity_cmetracker);
        this.mProgress = findViewById(R.id.progress_bar);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle((CharSequence) Utilities.getTrackerLabel(this));
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        loadTrackerData();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    public void onBackPressed() {
        if (!isStartingCustomActivity()) {
            super.onBackPressed();
        }
        finish();
    }

    /* access modifiers changed from: private */
    public void loadTrackerData() {
        String str;
        final View findViewById = findViewById(R.id.root_view);
        Calendar instance = Calendar.getInstance();
        instance.add(1, 0);
        String num = Integer.toString(instance.get(1));
        try {
            str = Integer.toString(instance.get(1) - 3);
        } catch (Exception e) {
            Trace.e(TAG, e.getMessage());
            str = num;
        }
        runOnUiThread(new Runnable() {
            public void run() {
                CMETrackerActivity.this.mProgress.setVisibility(0);
            }
        });
        CMETrackerDataProvider.getCMETrackerData(num, this, str, new ICallbackEvent<CMETrackerResponse, String>() {
            public void onError(String str) {
                CMETrackerActivity.this.mProgress.setVisibility(8);
                new CMEPulseException(str).showSnackBar(findViewById, -2, CMETrackerActivity.this.getString(R.string.retry), new View.OnClickListener() {
                    public void onClick(View view) {
                        CMETrackerActivity.this.loadTrackerData();
                    }
                });
            }

            public void onCompleted(CMETrackerResponse cMETrackerResponse) {
                CMETrackerActivity.this.mProgress.setVisibility(8);
                TabLayout tabLayout = (TabLayout) CMETrackerActivity.this.findViewById(R.id.tab_layout);
                if (tabLayout.getTabCount() <= 0) {
                    for (String text : CMETrackerActivity.this.getTabs()) {
                        tabLayout.addTab(tabLayout.newTab().setText((CharSequence) text));
                    }
                }
                tabLayout.setTabGravity(0);
                CMETrackerActivity cMETrackerActivity = CMETrackerActivity.this;
                ViewPager unused = cMETrackerActivity.mViewPager = (ViewPager) cMETrackerActivity.findViewById(R.id.pager);
                CMETrackerActivity.this.mViewPager.setOffscreenPageLimit(0);
                CMETrackerActivity.this.mViewPager.setAdapter(new CMETrackerTabAdapter(CMETrackerActivity.this.getSupportFragmentManager(), tabLayout.getTabCount(), cMETrackerResponse, new ICallbackEvent<Boolean, String>() {
                    public void onError(String str) {
                        if (!Utilities.isNetworkAvailable(CMETrackerActivity.this)) {
                            new CMEPulseException(CMETrackerActivity.this.getString(R.string.error_connection_required)).showSnackBar(findViewById, -2, CMETrackerActivity.this.getString(R.string.retry), new View.OnClickListener() {
                                public void onClick(View view) {
                                    CMETrackerActivity.this.loadTrackerData();
                                }
                            });
                        } else {
                            new CMEPulseException(CMETrackerActivity.this.getString(R.string.error_abim_update_failed)).showSnackBar(findViewById, -2, CMETrackerActivity.this.getString(R.string.retry), new View.OnClickListener() {
                                public void onClick(View view) {
                                    CMETrackerActivity.this.loadTrackerData();
                                }
                            });
                        }
                    }

                    public void onCompleted(Boolean bool) {
                        CMETrackerActivity.this.loadTrackerData();
                    }
                }, CMETrackerActivity.this.mUserProfile));
                CMETrackerActivity.this.mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
                tabLayout.setOnTabSelectedListener((TabLayout.OnTabSelectedListener) new TabLayout.OnTabSelectedListener() {
                    public void onTabReselected(TabLayout.Tab tab) {
                    }

                    public void onTabUnselected(TabLayout.Tab tab) {
                    }

                    public void onTabSelected(TabLayout.Tab tab) {
                        CMETrackerActivity.this.mViewPager.setCurrentItem(tab.getPosition());
                    }
                });
            }
        });
    }

    private void handleDeepLink() {
        if (Extensions.isStringNullOrEmpty(this.parentActivityName)) {
            Uri data = getIntent().getData();
            Log.i(TAG, "handleDeepLink: ");
            if (data != null && !Extensions.isStringNullOrEmpty(data.toString())) {
                if (data.toString().contains("cmetracker") || data.toString().contains("activitytracker")) {
                    try {
                        if (!AccountProvider.isUserLoggedIn(this)) {
                            returnToLandingActivity();
                        } else if (this.mUserProfile == null) {
                            logInUser(new ICallbackEvent<Boolean, CMEPulseException>() {
                                public void onError(CMEPulseException cMEPulseException) {
                                    Trace.d(CMETrackerActivity.TAG, cMEPulseException.getMessage());
                                    CMETrackerActivity.this.returnToLandingActivity();
                                }

                                public void onCompleted(Boolean bool) {
                                    if (bool.booleanValue()) {
                                        Trace.d(CMETrackerActivity.TAG, "Logged in user from keychain");
                                        String unused = CMETrackerActivity.this.parentActivityName = Constants.HOME_ACTIVITY_NAME;
                                        return;
                                    }
                                    Trace.d(CMETrackerActivity.TAG, "Could not in user from keychain");
                                    CMETrackerActivity.this.returnToLandingActivity();
                                }
                            });
                        } else {
                            this.parentActivityName = Constants.HOME_ACTIVITY_NAME;
                        }
                    } catch (Exception e) {
                        Trace.e(TAG, e.getMessage());
                        returnToLandingActivity();
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public List<String> getTabs() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i <= 3; i++) {
            Calendar instance = Calendar.getInstance();
            instance.add(1, i * -1);
            arrayList.add(String.valueOf(instance.get(1)));
        }
        return arrayList;
    }

    private boolean isStartingCustomActivity() {
        if (Extensions.isStringNullOrEmpty(this.parentActivityName)) {
            return false;
        }
        Utilities.goToHomeScreen(this);
        return true;
    }

    public Intent getCMELaunchIntent() {
        return new Intent(this, CmeArticleInfoActivity.class);
    }

    public Intent getCMEWebViewIntent() {
        return new Intent(this, CmeArticleWebActivity.class);
    }

    public Intent getCMEArticleActivityIntent() {
        return new Intent(this, CmeArticleActivity.class);
    }
}
