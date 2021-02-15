package com.webmd.webmdrx.activities.search;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.wbmd.wbmdcommons.logging.Trace;
import com.wbmd.wbmdcommons.utils.KeyboardUtil;
import com.wbmd.wbmdcommons.utils.Util;
import com.wbmd.wbmdcommons.utils.ViewUtil;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import com.webmd.wbmdomnituremanager.WBMDOmnitureModule;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.adapters.RecentSearchAdapter;
import com.webmd.webmdrx.fragments.ErrorFragmentDialog;
import com.webmd.webmdrx.intf.IDrugsReceivedListener;
import com.webmd.webmdrx.manager.ApiManager;
import com.webmd.webmdrx.models.DrugSearchResult;
import com.webmd.webmdrx.util.Constants;
import com.webmd.webmdrx.util.StringUtil;
import com.webmd.webmdrx.util.WebMDException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class RxSearchActivity extends AppCompatActivity implements TextWatcher {
    private RecentSearchAdapter mAdapterRecent;
    /* access modifiers changed from: private */
    public DrugSearchListAdapter mAdapterSearch;
    private ImageView mClearSearch;
    /* access modifiers changed from: private */
    public EditText mDrugSearchEditText;
    private FrameLayout mFLRecentHeader;
    /* access modifiers changed from: private */
    public ProgressBar mProgressBar;
    private RecyclerView mRecentRecyclerView;
    private View mRxSearchOverlay;
    private FrameLayout mRxSearchTipLayout;
    /* access modifiers changed from: private */
    public RecyclerView mSearchRecyclerView;
    /* access modifiers changed from: private */
    public TextView mTVNoResults;
    private ArrayList<DrugSearchResult> recentData;
    WeakReference<Activity> weakActivity;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        this.weakActivity = new WeakReference<>(this);
        super.onCreate(bundle);
        checkForDeepLink(getIntent());
        setContentView(R.layout.activity_rx_search);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setSharedElementEnterTransition(enterTransition());
        }
        setLandingMarketText();
        setUpActionBar();
        setUpComponents();
        setUpRecyclerViewSearch();
        setUpRecyclerViewRecent();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        checkForDeepLink(intent);
    }

    private void checkForDeepLink(Intent intent) {
        if (intent.getData() != null) {
            Util.handleRxDeepLink(this, intent.getData());
        }
    }

    private void initFirebaseRemoteConfig() {
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(0);
        final FirebaseRemoteConfig instance = FirebaseRemoteConfig.getInstance();
        instance.setConfigSettings(new FirebaseRemoteConfigSettings.Builder().setDeveloperModeEnabled(false).build());
        instance.setDefaults(R.xml.remote_config_defaults);
        instance.fetch(0).addOnCompleteListener((Activity) this, new OnCompleteListener<Void>() {
            public void onComplete(Task<Void> task) {
                if (task.isSuccessful()) {
                    instance.activateFetched();
                }
                ((TextView) RxSearchActivity.this.findViewById(R.id.tv_market_text)).setText(instance.getString(Constants.FIRE_BASE_LANDING_TEXT));
                progressBar.setVisibility(8);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void setLandingMarketText() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String string = extras.getString(Constants.FIRE_BASE_LANDING_TEXT);
            if (string != null) {
                ((TextView) findViewById(R.id.tv_market_text)).setText(string);
            } else {
                initFirebaseRemoteConfig();
            }
        } else {
            initFirebaseRemoteConfig();
        }
    }

    private void setUpComponents() {
        this.mFLRecentHeader = (FrameLayout) findViewById(R.id.a_search_layout_recent_search_header);
        this.mRxSearchTipLayout = (FrameLayout) findViewById(R.id.rx_search_tip);
        this.mProgressBar = (ProgressBar) findViewById(R.id.a_search_progress_bar);
        this.mTVNoResults = (TextView) findViewById(R.id.a_search_text_view_no_results);
        ImageView imageView = (ImageView) findViewById(R.id.a_search_image_view_clear_search);
        this.mClearSearch = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RxSearchActivity.this.clearSearch();
            }
        });
        ImageView imageView2 = (ImageView) findViewById(R.id.a_search_image_view_clear_recent);
        if (imageView2 != null) {
            imageView2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    RxSearchActivity.this.clearRecentSearches();
                }
            });
        }
        EditText editText = (EditText) findViewById(R.id.rx_edit_text_search);
        this.mDrugSearchEditText = editText;
        if (editText != null) {
            editText.requestFocus();
            KeyboardUtil.showKeyboard(this);
            this.mDrugSearchEditText.addTextChangedListener(this);
            this.mDrugSearchEditText.setOnKeyListener(new View.OnKeyListener() {
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    return keyEvent.getKeyCode() == 66;
                }
            });
            this.mDrugSearchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if (i != 3) {
                        return false;
                    }
                    KeyboardUtil.hideKeyboard(RxSearchActivity.this);
                    return false;
                }
            });
        }
        View findViewById = findViewById(R.id.rx_search_overlay);
        this.mRxSearchOverlay = findViewById;
        ViewUtil.showRxSearchOverLay(findViewById);
    }

    private void setUpRecyclerViewRecent() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.a_search_recycler_view_recent_search);
        this.mRecentRecyclerView = recyclerView;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (i2 > 0) {
                    KeyboardUtil.hideKeyboard(RxSearchActivity.this);
                }
            }
        });
        getRecentSearches();
        RecentSearchAdapter recentSearchAdapter = new RecentSearchAdapter(this, this.recentData);
        this.mAdapterRecent = recentSearchAdapter;
        this.mRecentRecyclerView.setAdapter(recentSearchAdapter);
        this.mAdapterRecent.refresh(this.recentData);
        this.mRecentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (this.recentData.size() > 0) {
            this.mFLRecentHeader.setVisibility(0);
            this.mRecentRecyclerView.setVisibility(0);
            this.mRxSearchTipLayout.setVisibility(8);
            return;
        }
        this.mRxSearchTipLayout.setVisibility(0);
    }

    private void getRecentSearches() {
        DrugSearchResult[] convertRecentSearchJson = com.webmd.webmdrx.util.Util.convertRecentSearchJson(com.webmd.webmdrx.util.Util.getRecentSearches(this));
        ArrayList<DrugSearchResult> arrayList = new ArrayList<>();
        this.recentData = arrayList;
        if (convertRecentSearchJson != null) {
            Collections.addAll(arrayList, convertRecentSearchJson);
        }
    }

    private void setUpRecyclerViewSearch() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.a_search_recycler_view_search_results);
        this.mSearchRecyclerView = recyclerView;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (i2 > 0) {
                    KeyboardUtil.hideKeyboard(RxSearchActivity.this);
                }
            }
        });
        this.mSearchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DrugSearchListAdapter drugSearchListAdapter = new DrugSearchListAdapter(this);
        this.mAdapterSearch = drugSearchListAdapter;
        this.mSearchRecyclerView.setAdapter(drugSearchListAdapter);
    }

    private void setUpActionBar() {
        View findViewById;
        Toolbar toolbar = (Toolbar) findViewById(R.id.a_search_toolbar);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            if (Build.VERSION.SDK_INT < 21 && (findViewById = findViewById(R.id.a_search_shadow_view)) != null) {
                findViewById.setVisibility(0);
            }
        }
        Drawable drawable = AppCompatDrawableManager.get().getDrawable(this, R.drawable.abc_ic_ab_back_material);
        if (drawable != null) {
            drawable.setColorFilter(ContextCompat.getColor(this, R.color.toolbar_icon_gray), PorterDuff.Mode.SRC_ATOP);
        }
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RxSearchActivity.this.onBackPressed();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        KeyboardUtil.hideKeyboard(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        com.webmd.webmdrx.util.Util.showKeyboard(this);
        getRecentSearches();
        RecentSearchAdapter recentSearchAdapter = this.mAdapterRecent;
        if (recentSearchAdapter != null) {
            recentSearchAdapter.refresh(this.recentData);
        }
        sendOmniturePing();
        com.webmd.webmdrx.util.Util.logFirebaseEvent(this, Constants.FIRE_BASE_RX_SEARCH_SCREEN);
    }

    private void sendOmniturePing() {
        Bundle extras = getIntent().getExtras();
        String str = (extras == null || !extras.getBoolean(com.wbmd.wbmdcommons.utils.Constants.EXTRA_IS_DEEP_LINK, false)) ? "wrx-icon-home" : "deep-link";
        String lastSentPage = WBMDOmnitureManager.shared.getLastSentPage();
        String str2 = "rx" + "/search";
        HashMap hashMap = new HashMap();
        hashMap.put("wapp.section", "rx");
        WBMDOmnitureManager.sendPageView(str2, hashMap, new WBMDOmnitureModule(str, (String) null, lastSentPage));
        Trace.d("omni", "Rx search omni = " + str2);
    }

    private void hideRecentSearchViews() {
        this.mRecentRecyclerView.setVisibility(8);
        this.mFLRecentHeader.setVisibility(8);
        this.mRxSearchTipLayout.setVisibility(8);
    }

    private void hideEmptySearchViews() {
        this.mTVNoResults.setVisibility(8);
    }

    private void prepareViewsForSearch() {
        this.mClearSearch.setVisibility(0);
        this.mSearchRecyclerView.setVisibility(8);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        ViewUtil.hideRxSearchOverlay(this.mRxSearchOverlay);
        hideRecentSearchViews();
        hideEmptySearchViews();
        if (charSequence != null && charSequence.length() == 0) {
            if (this.mRecentRecyclerView.getAdapter().getItemCount() > 0) {
                this.mFLRecentHeader.setVisibility(0);
                this.mRecentRecyclerView.setVisibility(0);
                this.mRxSearchTipLayout.setVisibility(8);
            } else {
                this.mRxSearchTipLayout.setVisibility(0);
            }
        }
        ApiManager instance = ApiManager.getInstance();
        String stripSearch = com.webmd.webmdrx.util.Util.stripSearch(this.mDrugSearchEditText.getText().toString());
        if (StringUtil.isNotEmpty(stripSearch)) {
            prepareViewsForSearch();
            if (stripSearch.length() > 1) {
                this.mProgressBar.setVisibility(0);
                instance.fetchSearchResultsForRequest(this, new IDrugsReceivedListener() {
                    public void onDrugsReceived(final String str, final List<DrugSearchResult> list) {
                        String stripSearch = com.webmd.webmdrx.util.Util.stripSearch(RxSearchActivity.this.mDrugSearchEditText.getText().toString());
                        if (StringUtil.isNotEmpty(str) && StringUtil.isNotEmpty(stripSearch) && str.equalsIgnoreCase(stripSearch)) {
                            if (list == null || list.size() <= 0) {
                                RxSearchActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        RxSearchActivity.this.mProgressBar.setVisibility(8);
                                        RxSearchActivity.this.mTVNoResults.setVisibility(0);
                                    }
                                });
                            } else {
                                RxSearchActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        RxSearchActivity.this.mProgressBar.setVisibility(8);
                                        RxSearchActivity.this.mSearchRecyclerView.setVisibility(0);
                                        RxSearchActivity.this.mAdapterSearch.setItemsForRequest(str, list);
                                    }
                                });
                            }
                        }
                    }

                    public void onDrugsRequestFailed(final WebMDException webMDException) {
                        RxSearchActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                RxSearchActivity.this.mProgressBar.setVisibility(8);
                                RxSearchActivity.this.mTVNoResults.setVisibility(0);
                                RxSearchActivity.this.showErrorDialog(webMDException.getMessage());
                            }
                        });
                    }
                }, stripSearch);
                return;
            }
            return;
        }
        if (this.mRecentRecyclerView.getAdapter().getItemCount() > 0) {
            this.mFLRecentHeader.setVisibility(0);
            this.mRecentRecyclerView.setVisibility(0);
        }
        this.mSearchRecyclerView.setVisibility(8);
        this.mProgressBar.setVisibility(8);
        this.mClearSearch.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void showErrorDialog(String str) {
        ErrorFragmentDialog errorFragmentDialog = new ErrorFragmentDialog();
        errorFragmentDialog.setErrorMessage(str);
        try {
            errorFragmentDialog.show(getSupportFragmentManager(), "errorDialog");
        } catch (IllegalStateException unused) {
        }
    }

    private Transition enterTransition() {
        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setDuration(90);
        return changeBounds;
    }

    /* access modifiers changed from: private */
    public void clearSearch() {
        this.mDrugSearchEditText.setText("");
        if (this.mAdapterRecent.getItemCount() == 0) {
            hideRecentSearchViews();
            this.mRxSearchTipLayout.setVisibility(0);
            return;
        }
        this.mRxSearchTipLayout.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void clearRecentSearches() {
        com.webmd.webmdrx.util.Util.clearRecentSearches(this);
        this.recentData.clear();
        this.mAdapterRecent.refresh(this.recentData);
        this.mFLRecentHeader.setVisibility(8);
        this.mRecentRecyclerView.setVisibility(8);
        this.mRxSearchTipLayout.setVisibility(0);
    }
}
