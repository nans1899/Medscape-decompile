package com.medscape.android.search;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import com.google.android.material.tabs.TabLayout;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.search.RecentlyViewedSuggestionHelper;
import com.medscape.android.activity.search.SearchMode;
import com.medscape.android.activity.search.SearchModeProvider;
import com.medscape.android.activity.search.SearchResultsFragment;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.landingfeed.model.FeedConstants;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import java.util.HashMap;
import java.util.Map;

public class MedscapeSearchActivity extends BaseActivity implements SearchModeProvider {
    protected boolean isInitialOmnitureCall = true;
    public boolean mCanRecentlyViewOmnitureCallbeMade;
    /* access modifiers changed from: private */
    public boolean mCanTabReselected = true;
    /* access modifiers changed from: private */
    public boolean mCanTypeAHeadOmnitureCallbeMade;
    /* access modifiers changed from: private */
    public int mCurrentTab;
    public boolean mIsLastSearchExternal = false;
    private boolean mIsSearchViewHasFocus;
    private MedscapeException mNoNetworkException;
    /* access modifiers changed from: private */
    public String mOmnitureAction = "search-tab";
    public String mQuery;
    /* access modifiers changed from: private */
    public View mRoot;
    /* access modifiers changed from: private */
    public SearchMode mSearchMode = SearchMode.SEARCH_REFERENCE;
    public SearchView mSearchView;
    /* access modifiers changed from: private */
    public TabLayout mTabLayout;
    SearchAutoCompleteAndHistoryResultsFragment searchAutoCompleteAndHistoryResultsFragment;
    SearchResultsFragment searchResultsFragment;
    public int searchSuggestionType;
    /* access modifiers changed from: private */
    public String voiceQuery;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_search);
        Intent intent = getIntent();
        this.mCurrentTab = getPositionForType(intent.getIntExtra(Constants.EXTRA_MODE, 1));
        if (intent != null && "android.intent.action.SEARCH".equals(intent.getAction())) {
            SearchAutoCompleteAndHistoryResultsFragment searchAutoCompleteAndHistoryResultsFragment2 = (SearchAutoCompleteAndHistoryResultsFragment) getSupportFragmentManager().findFragmentByTag("fragment_search");
            this.searchAutoCompleteAndHistoryResultsFragment = searchAutoCompleteAndHistoryResultsFragment2;
            if (searchAutoCompleteAndHistoryResultsFragment2 == null) {
                getSupportFragmentManager().beginTransaction().replace((int) R.id.content_frame, (Fragment) SearchAutoCompleteAndHistoryResultsFragment.newInstance(), "fragment_search").commit();
            }
        }
        if (intent != null && intent.hasExtra("voice_query")) {
            String stringExtra = intent.getStringExtra("voice_query");
            this.voiceQuery = stringExtra;
            SearchView searchView = this.mSearchView;
            if (searchView != null) {
                searchView.setQuery(stringExtra, true);
            } else {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        MedscapeSearchActivity.this.mSearchView.setQuery(MedscapeSearchActivity.this.voiceQuery, true);
                    }
                }, 1000);
            }
        }
        this.mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        this.mRoot = findViewById(R.id.root_layout);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.mCanTabReselected = true;
        setUpTabLayout();
        addTabChangedListener();
        SearchView searchView2 = this.mSearchView;
        if (searchView2 != null) {
            this.mIsSearchViewHasFocus = searchView2.hasFocus();
        }
        this.mCanTypeAHeadOmnitureCallbeMade = true;
        this.mCanRecentlyViewOmnitureCallbeMade = true;
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        TabLayout tabLayout = this.mTabLayout;
        if (!(tabLayout == null || tabLayout.getTabAt(this.mCurrentTab) == null)) {
            this.mTabLayout.getTabAt(this.mCurrentTab).select();
        }
        setQueryHintForPosition(this.mCurrentTab);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        View actionView;
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem findItem = menu.findItem(R.id.action_search);
        if (findItem == null || (actionView = MenuItemCompat.getActionView(findItem)) == null || !(actionView instanceof SearchView)) {
            return true;
        }
        this.mSearchView = (SearchView) actionView;
        setQueryHintForPosition(this.mCurrentTab);
        this.mSearchView.setMaxWidth(Integer.MAX_VALUE);
        this.mSearchView.onActionViewExpanded();
        setQueryTextListener();
        setCloseButtonListener();
        setOnFocusChangeListener();
        return true;
    }

    private void setOnFocusChangeListener() {
        this.mSearchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            public final void onFocusChange(View view, boolean z) {
                MedscapeSearchActivity.this.lambda$setOnFocusChangeListener$0$MedscapeSearchActivity(view, z);
            }
        });
    }

    public /* synthetic */ void lambda$setOnFocusChangeListener$0$MedscapeSearchActivity(View view, boolean z) {
        this.searchSuggestionType = 0;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void setCloseButtonListener() {
        View findViewById;
        SearchView searchView = this.mSearchView;
        if (searchView != null && (findViewById = searchView.findViewById(R.id.search_close_btn)) != null && (findViewById instanceof ImageView)) {
            ((ImageView) findViewById).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MedscapeSearchActivity.this.clearSearch();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void resetSearchResults() {
        this.mQuery = "";
        searchWithTypeAndQuery(getTypeForPosition(this.mSearchMode.getId()));
        this.mCanTypeAHeadOmnitureCallbeMade = true;
    }

    private void setUpTabLayout() {
        if (this.mTabLayout.getTabCount() == 0) {
            TabLayout tabLayout = this.mTabLayout;
            tabLayout.addTab(tabLayout.newTab());
        }
        TabLayout.Tab tabAt = this.mTabLayout.getTabAt(0);
        if (tabAt != null) {
            tabAt.setText((CharSequence) getResources().getString(R.string.search_tab_reference));
        }
        if (this.mTabLayout.getTabCount() == 1) {
            TabLayout tabLayout2 = this.mTabLayout;
            tabLayout2.addTab(tabLayout2.newTab());
        }
        TabLayout.Tab tabAt2 = this.mTabLayout.getTabAt(1);
        if (tabAt2 != null) {
            tabAt2.setText((CharSequence) getResources().getString(R.string.search_tab_news));
        }
        if (this.mTabLayout.getTabCount() == 2) {
            TabLayout tabLayout3 = this.mTabLayout;
            tabLayout3.addTab(tabLayout3.newTab());
        }
        TabLayout.Tab tabAt3 = this.mTabLayout.getTabAt(2);
        if (tabAt3 != null) {
            tabAt3.setText((CharSequence) getResources().getString(R.string.search_tab_education));
        }
        if (this.mTabLayout.getTabCount() == 3) {
            TabLayout tabLayout4 = this.mTabLayout;
            tabLayout4.addTab(tabLayout4.newTab());
        }
        TabLayout.Tab tabAt4 = this.mTabLayout.getTabAt(3);
        if (tabAt4 != null) {
            tabAt4.setText((CharSequence) getResources().getString(R.string.search_tab_medline));
        }
    }

    private void setQueryTextListener() {
        this.mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String str) {
                MedscapeSearchActivity.this.mQuery = str;
                int access$300 = MedscapeSearchActivity.this.getTypeForPosition(MedscapeSearchActivity.this.mTabLayout.getSelectedTabPosition());
                String unused = MedscapeSearchActivity.this.mOmnitureAction = "search-btn";
                MedscapeSearchActivity.this.searchWithQueryAndSubmit(access$300, 0);
                MedscapeSearchActivity medscapeSearchActivity = MedscapeSearchActivity.this;
                medscapeSearchActivity.sendOmniturePageView(medscapeSearchActivity.mOmnitureAction);
                return false;
            }

            public boolean onQueryTextChange(String str) {
                MedscapeSearchActivity.this.mCanRecentlyViewOmnitureCallbeMade = false;
                if (StringUtil.isNotEmpty(str)) {
                    int access$300 = MedscapeSearchActivity.this.getTypeForPosition(MedscapeSearchActivity.this.mTabLayout.getSelectedTabPosition());
                    MedscapeSearchActivity.this.mQuery = str;
                    if (access$300 == Constants.SEARCH_REFERENCE) {
                        MedscapeSearchActivity.this.searchWithTypeAndQuery(access$300);
                    }
                    if (MedscapeSearchActivity.this.mCanTypeAHeadOmnitureCallbeMade && MedscapeSearchActivity.this.mSearchMode == SearchMode.SEARCH_REFERENCE) {
                        OmnitureManager.get().markModule("search-type", "drgs", (Map<String, Object>) null);
                        MedscapeSearchActivity.this.mPvid = OmnitureManager.get().trackPageView(MedscapeSearchActivity.this, OmnitureManager.get().mSearchChannel, "browse-search", "view", (String) null, (String) null, (Map<String, Object>) null, false, "");
                    }
                    boolean unused = MedscapeSearchActivity.this.mCanTypeAHeadOmnitureCallbeMade = false;
                } else {
                    MedscapeSearchActivity.this.resetSearchResults();
                }
                return false;
            }
        });
    }

    private void addTabChangedListener() {
        TabLayout tabLayout = this.mTabLayout;
        if (tabLayout != null) {
            tabLayout.setOnTabSelectedListener((TabLayout.OnTabSelectedListener) new TabLayout.OnTabSelectedListener() {
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                public void onTabSelected(TabLayout.Tab tab) {
                    int position = tab.getPosition();
                    if (MedscapeSearchActivity.this.mCurrentTab != position) {
                        MedscapeSearchActivity.this.searchSuggestionType = 0;
                    }
                    SearchMode unused = MedscapeSearchActivity.this.mSearchMode = SearchMode.fromId(position);
                    int unused2 = MedscapeSearchActivity.this.mCurrentTab = position;
                    MedscapeSearchActivity.this.setQueryHintForPosition(position);
                    if (MedscapeSearchActivity.this.mSearchMode.equals(SearchMode.SEARCH_REFERENCE) || !StringUtil.isNotEmpty(MedscapeSearchActivity.this.mQuery)) {
                        MedscapeSearchActivity.this.mCanRecentlyViewOmnitureCallbeMade = true;
                        MedscapeSearchActivity medscapeSearchActivity = MedscapeSearchActivity.this;
                        medscapeSearchActivity.searchWithTypeAndQuery(medscapeSearchActivity.getTypeForPosition(position));
                    } else {
                        String unused3 = MedscapeSearchActivity.this.mOmnitureAction = "search-tab";
                        MedscapeSearchActivity medscapeSearchActivity2 = MedscapeSearchActivity.this;
                        medscapeSearchActivity2.searchWithQueryAndSubmit(medscapeSearchActivity2.getTypeForPosition(position), 0);
                        MedscapeSearchActivity medscapeSearchActivity3 = MedscapeSearchActivity.this;
                        medscapeSearchActivity3.sendOmniturePageView(medscapeSearchActivity3.mOmnitureAction);
                    }
                    boolean unused4 = MedscapeSearchActivity.this.mCanTabReselected = false;
                }

                public void onTabReselected(TabLayout.Tab tab) {
                    if (MedscapeSearchActivity.this.mCanTabReselected) {
                        int position = tab.getPosition();
                        SearchMode unused = MedscapeSearchActivity.this.mSearchMode = SearchMode.fromId(position);
                        int unused2 = MedscapeSearchActivity.this.mCurrentTab = position;
                        MedscapeSearchActivity.this.setQueryHintForPosition(position);
                        if ((!MedscapeSearchActivity.this.mSearchMode.equals(SearchMode.SEARCH_REFERENCE) || MedscapeSearchActivity.this.mIsLastSearchExternal) && !StringUtil.isNullOrEmpty(MedscapeSearchActivity.this.mQuery)) {
                            String unused3 = MedscapeSearchActivity.this.mOmnitureAction = "search-tab";
                            if (Util.isOnline(MedscapeSearchActivity.this)) {
                                MedscapeSearchActivity medscapeSearchActivity = MedscapeSearchActivity.this;
                                medscapeSearchActivity.searchWithQueryAndSubmit(medscapeSearchActivity.getTypeForPosition(position), 0);
                            } else {
                                MedscapeSearchActivity.this.showNoNetworkException();
                            }
                            MedscapeSearchActivity medscapeSearchActivity2 = MedscapeSearchActivity.this;
                            medscapeSearchActivity2.sendOmniturePageView(medscapeSearchActivity2.mOmnitureAction);
                        } else {
                            MedscapeSearchActivity.this.mCanRecentlyViewOmnitureCallbeMade = true;
                            MedscapeSearchActivity medscapeSearchActivity3 = MedscapeSearchActivity.this;
                            medscapeSearchActivity3.searchWithTypeAndQuery(medscapeSearchActivity3.getTypeForPosition(position));
                        }
                    }
                    boolean unused4 = MedscapeSearchActivity.this.mCanTabReselected = false;
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void setQueryHintForPosition(int i) {
        SearchView searchView = this.mSearchView;
        if (searchView == null) {
            return;
        }
        if (i == 0) {
            searchView.setQueryHint(getResources().getString(R.string.search_hint_reference));
        } else if (i == 1) {
            searchView.setQueryHint(getResources().getString(R.string.search_hint_news));
        } else if (i == 2) {
            searchView.setQueryHint(getResources().getString(R.string.search_hint_education));
        } else if (i == 3) {
            searchView.setQueryHint(getResources().getString(R.string.search_hint_medline));
        }
    }

    /* access modifiers changed from: private */
    public int getTypeForPosition(int i) {
        if (i == 1) {
            return Constants.SEARCH_NEWS;
        }
        if (i == 2) {
            return Constants.SEARCH_EDUCATION;
        }
        if (i == 3) {
            return Constants.SEARCH_MEDLINE;
        }
        return Constants.SEARCH_REFERENCE;
    }

    private int getPositionForType(int i) {
        if (i == Constants.SEARCH_NEWS) {
            return 1;
        }
        if (i == Constants.SEARCH_EDUCATION) {
            return 2;
        }
        return i == Constants.SEARCH_MEDLINE ? 3 : 0;
    }

    /* access modifiers changed from: private */
    public void searchWithTypeAndQuery(int i) {
        try {
            if (this.mNoNetworkException != null) {
                this.mNoNetworkException.dismissSnackBar();
            }
            this.searchAutoCompleteAndHistoryResultsFragment = SearchAutoCompleteAndHistoryResultsFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.EXTRA_MODE, i);
            bundle.putString(Constants.EXTRA_QUERY, this.mQuery);
            this.searchAutoCompleteAndHistoryResultsFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace((int) R.id.content_frame, (Fragment) this.searchAutoCompleteAndHistoryResultsFragment, "fragment_search").commit();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void searchWithQueryAndSubmit(int i, int i2) {
        try {
            searchClearFocus();
            if (this.mNoNetworkException != null) {
                this.mNoNetworkException.dismissSnackBar();
            }
            if (!this.mSearchMode.equals(SearchMode.SEARCH_REFERENCE) || Util.isOnline(this)) {
                this.searchResultsFragment = SearchResultsFragment.newInstance();
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.EXTRA_MODE, i);
                bundle.putString(Constants.EXTRA_QUERY, this.mQuery);
                bundle.putInt(Constants.EXTRA_SEARCH_SUGGESTION_TYPE, i2);
                this.searchResultsFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace((int) R.id.content_frame, (Fragment) this.searchResultsFragment, Constants.FRAGMENT_TAG_SEARCH_RESULTS).commit();
                return;
            }
            showNoNetworkException();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public SearchMode getCurrentSearchMode() {
        return this.mSearchMode;
    }

    public void onResume() {
        super.onResume();
        if (this.mSearchView != null && this.mIsSearchViewHasFocus) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    MedscapeSearchActivity.this.mSearchView.requestFocusFromTouch();
                }
            }, 100);
        }
        if (StringUtil.isNullOrEmpty(this.mQuery)) {
            resetSearchResults();
        }
    }

    public void onPause() {
        super.onPause();
        if (this.voiceQuery != null) {
            finish();
            return;
        }
        this.mCanTabReselected = true;
        SearchView searchView = this.mSearchView;
        if (searchView != null) {
            this.mIsSearchViewHasFocus = searchView.hasFocus();
            this.mSearchView.clearFocus();
        }
    }

    public void doSearch(String str) {
        this.mSearchView.setQuery(str, true);
    }

    public void sendOmniturePageView(String str) {
        String str2;
        String str3;
        String str4 = OmnitureManager.get().mSearchChannel;
        int id = this.mSearchMode.getId();
        int i = Constants.SEARCH_REFERENCE;
        String str5 = FeedConstants.CME_ITEM;
        String str6 = "";
        if (id == i) {
            str2 = "drgs";
            str3 = "drugs";
        } else {
            if (this.mSearchMode.getId() == Constants.SEARCH_NEWS) {
                str5 = "news";
            } else if (this.mSearchMode.getId() != Constants.SEARCH_EDUCATION) {
                if (this.mSearchMode.getId() == Constants.SEARCH_MEDLINE) {
                    str2 = "med";
                    str3 = RecentlyViewedSuggestionHelper.TYPE_MEDLINE;
                } else {
                    str2 = str6;
                    HashMap hashMap = new HashMap();
                    hashMap.put("wapp.query", this.mQuery);
                    OmnitureManager.get().markModule(str, str2, hashMap);
                    this.mPvid = OmnitureManager.get().trackPageView(this, str4, "search", str6, OmnitureConstants.PAGE_NAME_RESULTS, (String) null, (Map<String, Object>) null, false, "");
                }
            }
            str6 = str2;
            HashMap hashMap2 = new HashMap();
            hashMap2.put("wapp.query", this.mQuery);
            OmnitureManager.get().markModule(str, str2, hashMap2);
            this.mPvid = OmnitureManager.get().trackPageView(this, str4, "search", str6, OmnitureConstants.PAGE_NAME_RESULTS, (String) null, (Map<String, Object>) null, false, "");
        }
        str6 = str3;
        HashMap hashMap22 = new HashMap();
        hashMap22.put("wapp.query", this.mQuery);
        OmnitureManager.get().markModule(str, str2, hashMap22);
        this.mPvid = OmnitureManager.get().trackPageView(this, str4, "search", str6, OmnitureConstants.PAGE_NAME_RESULTS, (String) null, (Map<String, Object>) null, false, "");
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void showNoNetworkException() {
        Util.hideKeyboard(this);
        MedscapeException medscapeException = this.mNoNetworkException;
        if (medscapeException == null) {
            this.mNoNetworkException = new MedscapeException(getResources().getString(R.string.error_connection_required));
        } else {
            medscapeException.dismissSnackBar();
        }
        this.mNoNetworkException.showSnackBar(this.mRoot, -2, getResources().getString(R.string.retry), new View.OnClickListener() {
            public void onClick(View view) {
                if (Util.isOnline(MedscapeSearchActivity.this)) {
                    MedscapeSearchActivity medscapeSearchActivity = MedscapeSearchActivity.this;
                    medscapeSearchActivity.searchWithQueryAndSubmit(medscapeSearchActivity.getTypeForPosition(medscapeSearchActivity.mCurrentTab), MedscapeSearchActivity.this.searchSuggestionType);
                    MedscapeSearchActivity medscapeSearchActivity2 = MedscapeSearchActivity.this;
                    medscapeSearchActivity2.sendOmniturePageView(medscapeSearchActivity2.mOmnitureAction);
                    return;
                }
                MedscapeSearchActivity.this.mRoot.postDelayed(new Runnable() {
                    public final void run() {
                        MedscapeSearchActivity.this.showNoNetworkException();
                    }
                }, 400);
            }
        });
    }

    /* access modifiers changed from: private */
    public void clearSearch() {
        View findViewById = findViewById(R.id.search_src_text);
        if (findViewById != null && (findViewById instanceof EditText)) {
            ((EditText) findViewById).setText("");
        }
    }

    private void searchClearFocus() {
        View findViewById = findViewById(R.id.search_src_text);
        if (findViewById != null && (findViewById instanceof EditText)) {
            findViewById.clearFocus();
        }
    }
}
