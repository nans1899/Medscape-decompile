package com.medscape.android.base;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.core.view.MenuItemCompat;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.search.RecentlyViewedItemClickListener;
import com.medscape.android.activity.search.RecentlyViewedItemsAdapter;
import com.medscape.android.activity.search.RecentlyViewedSuggestionHelper;
import com.medscape.android.activity.search.ReferenceItemClickListener;
import com.medscape.android.activity.search.SearchActivity;
import com.medscape.android.activity.search.SearchMode;
import com.medscape.android.activity.search.SearchModeProvider;
import com.medscape.android.activity.search.SearchResultsListAdapter;
import com.medscape.android.activity.search.model.CRData;
import com.medscape.android.task.SearchLocalTask;
import com.medscape.android.util.OldConstants;
import com.medscape.android.util.Util;
import com.medscape.android.view.FilterableSearchView;
import com.medscape.android.view.SuggestibleEditText;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public abstract class SearchableActivity extends BottomNavBaseActivity implements SearchModeProvider, LoaderManager.LoaderCallbacks<Cursor> {
    public static final int LOADER_RECENTLY_VIEWED_ITEMS = 1;
    public static final int MAX_RECENTLY_VIEWED_ITEMS = 5;
    /* access modifiers changed from: private */
    public boolean isMenuCollapsed;
    protected final View.OnClickListener mFilterButtonClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (SearchableActivity.this.mSearchFilterPopupWindow.isShowing()) {
                SearchableActivity.this.mSearchFilterPopupWindow.dismiss();
                return;
            }
            int i = SearchableActivity.this.getResources().getDisplayMetrics().widthPixels;
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            ((ViewGroup.MarginLayoutParams) SearchableActivity.this.mSearchFilterDropDown.findViewById(R.id.arrow).getLayoutParams()).rightMargin = i - (iArr[0] + view.getWidth());
            SearchableActivity.this.mSearchFilterPopupWindow.setWidth(OldConstants.POP_UP_WINDOW_WIDTH);
            SearchableActivity.this.mSearchFilterPopupWindow.setHeight(SearchableActivity.this.mSearchFilterPopupWindow.getHeight());
            SearchableActivity.this.mSearchFilterPopupWindow.showAsDropDown(view);
        }
    };
    protected final MenuItemCompat.OnActionExpandListener mFilterDismissListener = new MenuItemCompat.OnActionExpandListener() {
        public boolean onMenuItemActionExpand(MenuItem menuItem) {
            int i = AnonymousClass7.$SwitchMap$com$medscape$android$activity$search$SearchMode[SearchableActivity.this.mSearchMode.ordinal()];
            SearchableActivity.this.mPvid = OmnitureManager.get().trackPageView(SearchableActivity.this, i != 1 ? i != 2 ? i != 3 ? "other" : "education" : Constants.OMNITURE_CHANNEL_REFERENCE : Constants.OMNITURE_CHANNEL_NEWS, "search", "click", (String) null, (String) null, new HashMap());
            boolean unused = SearchableActivity.this.isMenuCollapsed = false;
            SearchableActivity.this.fetchRecentlyViewedItems();
            return true;
        }

        public boolean onMenuItemActionCollapse(MenuItem menuItem) {
            if (SearchableActivity.this.mSearchFilterPopupWindow != null && SearchableActivity.this.mSearchFilterPopupWindow.isShowing()) {
                SearchableActivity.this.mSearchFilterPopupWindow.dismiss();
            }
            boolean unused = SearchableActivity.this.isMenuCollapsed = true;
            return true;
        }
    };
    protected FilterableSearchView mFilterSearchView;
    /* access modifiers changed from: private */
    public SuggestibleEditText mQueryText;
    private SearchLocalTask.SearchLocalCompleteListener mSearchCompleteListener;
    /* access modifiers changed from: private */
    public MenuItem mSearchMenuItem;
    /* access modifiers changed from: private */
    public SearchMode mSearchMode = SearchMode.NONE;
    private SearchLocalTask mSearchTask;
    /* access modifiers changed from: private */
    public RecentlyViewedItemClickListener recentlyViewedItemClickListener;
    /* access modifiers changed from: private */
    public ReferenceItemClickListener searchItemClickListener;
    private final TextView.OnEditorActionListener suggestibleOnEditorActionListener = new TextView.OnEditorActionListener() {
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i == 3 || (keyEvent.getAction() == 0 && keyEvent.getKeyCode() == 66)) {
                Boolean valueOf = Boolean.valueOf(SearchableActivity.this.mSearchMode == SearchMode.SEARCH_NEWS || SearchableActivity.this.mSearchMode == SearchMode.SEARCH_EDUCATION || SearchableActivity.this.mSearchMode == SearchMode.SEARCH_MEDLINE);
                if (!Util.isOnline(SearchableActivity.this) && !SearchableActivity.this.isFinishing() && valueOf.booleanValue()) {
                    SearchableActivity.this.showDialog(5);
                } else if (textView.length() > 0) {
                    ((SuggestibleEditText) textView).dismissDropDown();
                    ((InputMethodManager) SearchableActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(SearchableActivity.this.getCurrentFocus().getWindowToken(), 2);
                    SearchableActivity.this.startSearchActivity(textView.getText().toString(), SearchableActivity.this.mSearchMode.getId());
                }
            }
            return true;
        }
    };

    /* access modifiers changed from: protected */
    public abstract boolean enableDropDown();

    public int getCheckId() {
        return R.id.check;
    }

    /* access modifiers changed from: protected */
    public abstract int getDefaultFilterSelectionViewId();

    /* access modifiers changed from: protected */
    public abstract int getFilterListResourceId();

    public void onLoaderReset(Loader<Cursor> loader) {
    }

    /* renamed from: com.medscape.android.base.SearchableActivity$7  reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$com$medscape$android$activity$search$SearchMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.medscape.android.activity.search.SearchMode[] r0 = com.medscape.android.activity.search.SearchMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$medscape$android$activity$search$SearchMode = r0
                com.medscape.android.activity.search.SearchMode r1 = com.medscape.android.activity.search.SearchMode.SEARCH_NEWS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$medscape$android$activity$search$SearchMode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.medscape.android.activity.search.SearchMode r1 = com.medscape.android.activity.search.SearchMode.SEARCH_REFERENCE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$medscape$android$activity$search$SearchMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.medscape.android.activity.search.SearchMode r1 = com.medscape.android.activity.search.SearchMode.SEARCH_EDUCATION     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.base.SearchableActivity.AnonymousClass7.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        getWindow().setSoftInputMode(32);
        super.onCreate(bundle);
        this.mSearchCompleteListener = new SearchLocalCompleteListenerImpl();
        this.mSearchFilterDropDown = View.inflate(this, getFilterListResourceId(), (ViewGroup) null);
        this.mSearchFilterPopupWindow = new PopupWindow(this.mSearchFilterDropDown);
        this.mSearchFilterDropDown.post(new Runnable() {
            public void run() {
                SearchableActivity.this.mSearchFilterPopupWindow.setWindowLayoutMode(120, -2);
            }
        });
        this.searchItemClickListener = new ReferenceItemClickListener((Activity) this);
        this.recentlyViewedItemClickListener = new RecentlyViewedItemClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        refreshRecentlyViewed();
    }

    private void refreshRecentlyViewed() {
        MenuItem menuItem = this.mSearchMenuItem;
        if (menuItem != null && MenuItemCompat.isActionViewExpanded(menuItem) && TextUtils.isEmpty(this.mFilterSearchView.getQuery())) {
            fetchRecentlyViewedItems();
        }
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        if (i == 1) {
            return new CursorLoader(this, RecentlyViewedSuggestionHelper.URI_RECENTLY_VIEWED, (String[]) null, (String) null, new String[]{null}, (String) null);
        }
        throw new IllegalArgumentException("unknown loader id: " + i);
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (loader.getId() == 1) {
            applySuggestibleAdapter(cursor);
            return;
        }
        throw new IllegalArgumentException("unknown loader id: " + loader.getId());
    }

    /* access modifiers changed from: private */
    public void fetchRecentlyViewedItems() {
        getSupportLoaderManager().restartLoader(1, (Bundle) null, this);
    }

    private void applySuggestibleAdapter(Cursor cursor) {
        if (TextUtils.isEmpty(this.mQueryText.getText())) {
            RecentlyViewedItemsAdapter recentlyViewedItemsAdapter = new RecentlyViewedItemsAdapter(this, RecentlyViewedSuggestionHelper.filterRecentlyViewedBySearchMode(this, cursor, this.mSearchMode, 5));
            this.mQueryText.setAdapter(recentlyViewedItemsAdapter);
            if (recentlyViewedItemsAdapter.getCount() <= 0 || this.isMenuCollapsed) {
                this.mQueryText.dismissDropDown();
            } else {
                this.mQueryText.showDropDown();
            }
        }
    }

    private void configureFilterDropDown() {
        if (this.mFilterSearchView != null) {
            if (this.mSearchMode == SearchMode.NONE) {
                View findViewById = this.mSearchFilterDropDown.findViewById(getDefaultFilterSelectionViewId());
                findViewById.findViewById(getCheckId()).setVisibility(0);
                this.mSearchMode = SearchMode.fromId(Integer.parseInt(findViewById.getTag().toString()) + 1);
            }
            this.mFilterSearchView.setQueryHint(this.mSearchMode.getHint());
        }
    }

    /* access modifiers changed from: protected */
    public void initSearch(FilterableSearchView filterableSearchView, MenuItem menuItem) {
        this.mSearchMenuItem = menuItem;
        this.mFilterSearchView = filterableSearchView;
        this.mQueryText = (SuggestibleEditText) filterableSearchView.findViewById(R.id.search_src_text);
        configureFilterDropDown();
        this.mFilterSearchView.setOnClickListener(this.mFilterButtonClickListener);
        initSuggestibleDropDown(this.mQueryText);
        this.mQueryText.setOnEditorActionListener(this.suggestibleOnEditorActionListener);
    }

    private void initSuggestibleDropDown(SuggestibleEditText suggestibleEditText) {
        suggestibleEditText.setDropDownWidth(getResources().getDisplayMetrics().widthPixels - ((int) Util.dpToPixel(this, 10)));
        suggestibleEditText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ((InputMethodManager) SearchableActivity.this.getSystemService("input_method")).hideSoftInputFromInputMethod(SearchableActivity.this.getWindow().getDecorView().getWindowToken(), 0);
                if (i != 0 || SearchableActivity.this.mQueryText.getDropDownListHeadersCount() != 1) {
                    if (adapterView.getAdapter() instanceof SearchResultsListAdapter) {
                        SearchableActivity.this.searchItemClickListener.onItemClick(adapterView, view, i, j);
                    } else {
                        SearchableActivity.this.recentlyViewedItemClickListener.onItemClick(adapterView, view, i, j);
                    }
                }
            }
        });
        suggestibleEditText.setOnSearchInputChangedListener(new SuggestibleEditText.OnSearchInputChangedListener() {
            public void onSearchInputChanged(String str) {
                if (MenuItemCompat.isActionViewExpanded(SearchableActivity.this.mSearchMenuItem)) {
                    SearchableActivity.this.updateDropDownContent(str);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateDropDownContent(String str) {
        if (TextUtils.isEmpty(str)) {
            fetchRecentlyViewedItems();
        } else if (enableDropDown()) {
            performSearch();
        } else {
            this.mQueryText.dismissDropDown();
        }
    }

    /* access modifiers changed from: private */
    public void startSearchActivity(String str, int i) {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra(Constants.EXTRA_QUERY, str);
        intent.putExtra(Constants.EXTRA_MODE, i);
        startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public SearchMode getSearchMode() {
        return this.mSearchMode;
    }

    public SearchMode getCurrentSearchMode() {
        return getSearchMode();
    }

    public void onSearchFilterClick(View view) {
        if (enableDropDown()) {
            this.mQueryText.dismissDropDown();
        }
        SearchMode searchMode = this.mSearchMode;
        SearchMode fromId = SearchMode.fromId(Integer.parseInt((String) view.getTag()) + 1);
        this.mSearchMode = fromId;
        if (fromId != searchMode) {
            View view2 = this.mSearchFilterDropDown;
            StringBuilder sb = new StringBuilder();
            sb.append(searchMode.getId() - 1);
            sb.append("");
            view2.findViewWithTag(sb.toString()).findViewById(getCheckId()).setVisibility(8);
            View view3 = this.mSearchFilterDropDown;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.mSearchMode.getId() - 1);
            sb2.append("");
            view3.findViewWithTag(sb2.toString()).findViewById(getCheckId()).setVisibility(0);
            this.mFilterSearchView.setQueryHint(this.mSearchMode.getHint());
        }
        sendFilterSelectionOmniturePing(this.mSearchMode, searchMode);
        initSearch(this.mFilterSearchView, this.mSearchMenuItem);
        updateDropDownContent(this.mQueryText.getText().toString());
        if (enableDropDown()) {
            performSearch();
        }
        this.mSearchFilterPopupWindow.dismiss();
    }

    private void sendFilterSelectionOmniturePing(SearchMode searchMode, SearchMode searchMode2) {
        if (searchMode.equals(SearchMode.SEARCH_NEWS)) {
            OmnitureManager.get().markModule("srchtype", Constants.OMNITURE_CHANNEL_NEWS, (Map<String, Object>) null);
        } else if (searchMode.equals(SearchMode.SEARCH_EDUCATION)) {
            OmnitureManager.get().markModule("srchtype", "edu", (Map<String, Object>) null);
        } else if (searchMode.equals(SearchMode.SEARCH_MEDLINE)) {
            OmnitureManager.get().markModule("srchtype", "med", (Map<String, Object>) null);
        } else if (searchMode.equals(SearchMode.SEARCH_REFERENCE)) {
            if (searchMode2.equals(SearchMode.SEARCH_DRUGS) || searchMode2.equals(SearchMode.SEARCH_CONDITIONS) || searchMode2.equals(SearchMode.SEARCH_PROCEDURES) || searchMode2.equals(SearchMode.SEARCH_CALCULATORS)) {
                OmnitureManager.get().markModule("srchfilter-ref", Constants.CONSULT_ALL, (Map<String, Object>) null);
            } else {
                OmnitureManager.get().markModule("srchtype", "ref", (Map<String, Object>) null);
            }
        } else if (searchMode.equals(SearchMode.SEARCH_DRUGS)) {
            OmnitureManager.get().markModule("srchfilter-ref", AdParameterKeys.DRUG_ID, (Map<String, Object>) null);
        } else if (searchMode.equals(SearchMode.SEARCH_CONDITIONS)) {
            OmnitureManager.get().markModule("srchfilter-ref", "cnd", (Map<String, Object>) null);
        } else if (searchMode.equals(SearchMode.SEARCH_PROCEDURES)) {
            OmnitureManager.get().markModule("srchfilter-ref", "prcd", (Map<String, Object>) null);
        } else if (searchMode.equals(SearchMode.SEARCH_CALCULATORS)) {
            OmnitureManager.get().markModule("srchfilter-ref", Constants.OMNITURE_MLINK_CALC, (Map<String, Object>) null);
        }
    }

    private void performSearch() {
        if (enableDropDown() && !TextUtils.isEmpty(this.mQueryText.getText())) {
            performReferenceSearch(25);
        }
    }

    private void performReferenceSearch(int i) {
        SearchLocalTask searchLocalTask = this.mSearchTask;
        if (searchLocalTask != null) {
            searchLocalTask.cancel(true);
        }
        this.mSearchTask = new SearchLocalTask(this, this.mSearchMode, i, 0, this.mSearchCompleteListener);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(60, 80, (long) 10, TimeUnit.SECONDS, new LinkedBlockingQueue(80));
        if (Build.VERSION.SDK_INT >= 11) {
            this.mSearchTask.executeOnExecutor(threadPoolExecutor, new String[]{this.mQueryText.getText().toString()});
            return;
        }
        this.mSearchTask.execute(new String[]{this.mQueryText.getText().toString()});
    }

    public void onBackPressed() {
        super.onBackPressed();
        SuggestibleEditText suggestibleEditText = this.mQueryText;
        if (suggestibleEditText != null) {
            suggestibleEditText.dismissDropDown();
        }
        if (this.mSearchFilterPopupWindow != null) {
            this.mSearchFilterPopupWindow.dismiss();
        }
    }

    private class SearchLocalCompleteListenerImpl implements SearchLocalTask.SearchLocalCompleteListener {
        final SearchResultsListAdapter adapter;

        private SearchLocalCompleteListenerImpl() {
            this.adapter = new SearchResultsListAdapter(SearchableActivity.this, new ArrayList());
        }

        public void onSearchComplete(List<CRData> list) {
            if (!TextUtils.isEmpty(SearchableActivity.this.mQueryText.getText())) {
                updateAdapter(list);
            }
        }

        public void onNoSearchResults() {
            updateAdapter(new ArrayList());
        }

        private void updateAdapter(List<CRData> list) {
            if (SearchableActivity.this.mQueryText.getAdapter() != this.adapter) {
                SearchableActivity.this.mQueryText.setAdapter(this.adapter);
            }
            this.adapter.refreshList(list);
        }
    }
}
