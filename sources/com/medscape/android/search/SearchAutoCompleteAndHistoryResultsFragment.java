package com.medscape.android.search;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.search.RecentlyViewedClearClickListener;
import com.medscape.android.activity.search.RecentlyViewedItemClickListener;
import com.medscape.android.activity.search.RecentlyViewedItemsAdapter;
import com.medscape.android.activity.search.RecentlyViewedSuggestionHelper;
import com.medscape.android.activity.search.ReferenceItemClickListener;
import com.medscape.android.activity.search.SearchMode;
import com.medscape.android.activity.search.SearchResultsListAdapter;
import com.medscape.android.activity.search.model.CRData;
import com.medscape.android.task.SearchLocalTask;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SearchAutoCompleteAndHistoryResultsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, RecentlyViewedClearClickListener {
    public static final int LOADER_RECENTLY_VIEWED_ITEMS = 1;
    public static final int MAX_AUTOCOMPLETE_ITEMS = 25;
    public static final int MAX_RECENTLY_VIEWED_ITEMS = 5;
    static final String TAG = SearchAutoCompleteAndHistoryResultsFragment.class.getSimpleName();
    /* access modifiers changed from: private */
    public SearchResultsDataAdapter mAdapter;
    private int mCurrentTab = 0;
    private boolean mIsOmniturePageViewFired;
    private View mNoResultsView;
    /* access modifiers changed from: private */
    public View mProgressDialog;
    /* access modifiers changed from: private */
    public View mQueryHintView;
    RecentlyViewedItemClickListener mRecentlyViewedItemClickListener;
    RecyclerView mRecyclerView;
    ReferenceItemClickListener mReferenceItemClickListener;
    private View mRootView;
    private SearchLocalTask.SearchLocalCompleteListener mSearchCompleteListener;
    private SearchMode mSearchMode = SearchMode.SEARCH_REFERENCE;
    /* access modifiers changed from: private */
    public String mSearchQuery = "";
    private SearchLocalTask mSearchTask;
    private int mSearchType = Constants.SEARCH_REFERENCE;

    public void onLoaderReset(Loader<Cursor> loader) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_search, viewGroup, false);
    }

    public static SearchAutoCompleteAndHistoryResultsFragment newInstance() {
        SearchAutoCompleteAndHistoryResultsFragment searchAutoCompleteAndHistoryResultsFragment = new SearchAutoCompleteAndHistoryResultsFragment();
        searchAutoCompleteAndHistoryResultsFragment.mIsOmniturePageViewFired = false;
        return searchAutoCompleteAndHistoryResultsFragment;
    }

    public void onActivityCreated(Bundle bundle) {
        String str;
        super.onActivityCreated(bundle);
        View view = getView();
        this.mRootView = view;
        this.mProgressDialog = view.findViewById(R.id.progressBar);
        this.mNoResultsView = this.mRootView.findViewById(R.id.no_results_msg);
        this.mQueryHintView = this.mRootView.findViewById(R.id.query_hint);
        this.mSearchCompleteListener = new SearchLocalCompleteListenerImpl();
        this.mRecentlyViewedItemClickListener = new RecentlyViewedItemClickListener(getActivity());
        this.mReferenceItemClickListener = new ReferenceItemClickListener((Activity) getActivity());
        setUpRecyclerView();
        Bundle arguments = getArguments();
        int i = -1;
        if (arguments != null) {
            i = arguments.getInt(Constants.EXTRA_MODE, -1);
            str = arguments.getString(Constants.EXTRA_QUERY);
        } else {
            str = "";
        }
        loadFeedForSearchRequest(i, str);
    }

    public void onPause() {
        super.onPause();
        this.mIsOmniturePageViewFired = false;
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) this.mRootView.findViewById(R.id.search_recycler);
        this.mRecyclerView = recyclerView;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
            }
        });
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (this.mAdapter == null) {
            this.mAdapter = new SearchResultsDataAdapter(getActivity(), this.mRecentlyViewedItemClickListener, this.mReferenceItemClickListener);
        }
        this.mAdapter.setmSearchQuery(this.mSearchQuery);
        this.mRecyclerView.setAdapter(this.mAdapter);
    }

    public void clearSearch() {
        this.mSearchQuery = "";
        this.mProgressDialog.setVisibility(8);
        this.mRecyclerView.setVisibility(8);
        this.mQueryHintView.setVisibility(0);
        Util.hideKeyboard(getActivity());
        fetchRecentlyViewedItems();
        this.mAdapter.setmSearchQuery(this.mSearchQuery);
    }

    public void loadFeedForSearchRequest(int i, String str) {
        SearchResultsDataAdapter searchResultsDataAdapter = new SearchResultsDataAdapter(getActivity(), this.mRecentlyViewedItemClickListener, this.mReferenceItemClickListener);
        this.mAdapter = searchResultsDataAdapter;
        this.mRecyclerView.setAdapter(searchResultsDataAdapter);
        refreshCurrentTabForListAdapter(i);
        this.mRecyclerView.setVisibility(8);
        this.mNoResultsView.setVisibility(8);
        this.mSearchQuery = str;
        this.mSearchType = i;
        this.mSearchMode = SearchMode.fromId(i);
        if (StringUtil.isNotEmpty(this.mSearchQuery)) {
            if (isAdded() && getActivity() != null) {
                this.mRecyclerView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.consult_action_bar));
            }
            this.mQueryHintView.setVisibility(8);
            View view = this.mProgressDialog;
            if (view != null) {
                view.setVisibility(0);
            }
            fetchResultsForQuery();
            this.mAdapter.setmSearchQuery(this.mSearchQuery);
            return;
        }
        if (isAdded() && getActivity() != null) {
            this.mRecyclerView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
        }
        this.mQueryHintView.setVisibility(8);
        View view2 = this.mProgressDialog;
        if (view2 != null) {
            view2.setVisibility(0);
        }
        fetchRecentlyViewedItems();
    }

    private void refreshCurrentTabForListAdapter(int i) {
        if (this.mAdapter == null) {
            return;
        }
        if (i == Constants.SEARCH_NEWS) {
            this.mCurrentTab = 0;
            this.mAdapter.refreshCurrentTab(0);
        } else if (i == Constants.SEARCH_REFERENCE) {
            this.mCurrentTab = 1;
            this.mAdapter.refreshCurrentTab(1);
        } else if (i == Constants.SEARCH_EDUCATION) {
            this.mCurrentTab = 2;
            this.mAdapter.refreshCurrentTab(2);
        } else if (i == Constants.SEARCH_MEDLINE) {
            this.mCurrentTab = 2;
            this.mAdapter.refreshCurrentTab(2);
        }
    }

    private void fetchResultsForQuery() {
        if (this.mSearchType == Constants.SEARCH_REFERENCE) {
            performReferenceSearch(25);
        }
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        if (i == 1) {
            return new CursorLoader(getContext(), RecentlyViewedSuggestionHelper.URI_RECENTLY_VIEWED, (String[]) null, (String) null, new String[]{null}, (String) null);
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

    private void fetchRecentlyViewedItems() {
        getActivity().getSupportLoaderManager().restartLoader(1, (Bundle) null, this);
    }

    private void applySuggestibleAdapter(Cursor cursor) {
        if (getActivity() != null && TextUtils.isEmpty(this.mSearchQuery) && isAdded() && getActivity() != null) {
            RecentlyViewedItemsAdapter recentlyViewedItemsAdapter = new RecentlyViewedItemsAdapter(getActivity(), RecentlyViewedSuggestionHelper.filterRecentlyViewedBySearchMode(getContext(), cursor, this.mSearchMode, 5), this);
            boolean z = false;
            if (recentlyViewedItemsAdapter.getCount() > 0) {
                this.mAdapter.setRecentlyViewedItemsAdapter(recentlyViewedItemsAdapter);
                this.mAdapter.notifyDataSetChanged();
                this.mProgressDialog.setVisibility(8);
                this.mRecyclerView.setVisibility(0);
                this.mQueryHintView.setVisibility(8);
            } else {
                this.mProgressDialog.setVisibility(8);
                this.mRecyclerView.setVisibility(8);
                this.mQueryHintView.setVisibility(0);
            }
            if (recentlyViewedItemsAdapter.getCount() > 0) {
                z = true;
            }
            fireRecentlyPageViewCall(z);
        }
    }

    private void performReferenceSearch(int i) {
        SearchLocalTask searchLocalTask = this.mSearchTask;
        if (searchLocalTask != null) {
            searchLocalTask.cancel(true);
        }
        this.mSearchTask = new SearchLocalTask(getContext(), this.mSearchMode, i, 0, this.mSearchCompleteListener);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(60, 80, (long) 10, TimeUnit.SECONDS, new LinkedBlockingQueue(80));
        this.mSearchTask.executeOnExecutor(threadPoolExecutor, new String[]{this.mSearchQuery});
        if (getActivity() != null && (getActivity() instanceof MedscapeSearchActivity)) {
            ((MedscapeSearchActivity) getActivity()).mIsLastSearchExternal = false;
        }
    }

    public void onRecentlyViewedClearClick() {
        this.mProgressDialog.setVisibility(8);
        this.mRecyclerView.setVisibility(8);
        this.mQueryHintView.setVisibility(0);
    }

    private class SearchLocalCompleteListenerImpl implements SearchLocalTask.SearchLocalCompleteListener {
        final SearchResultsListAdapter searchResultsListAdapter;

        private SearchLocalCompleteListenerImpl() {
            this.searchResultsListAdapter = new SearchResultsListAdapter(SearchAutoCompleteAndHistoryResultsFragment.this.getActivity(), new ArrayList());
        }

        public void onSearchComplete(List<CRData> list) {
            if (!TextUtils.isEmpty(SearchAutoCompleteAndHistoryResultsFragment.this.mSearchQuery)) {
                updateAdapter(list);
                SearchAutoCompleteAndHistoryResultsFragment.this.mAdapter.setmSearchQuery(SearchAutoCompleteAndHistoryResultsFragment.this.mSearchQuery);
            }
        }

        public void onNoSearchResults() {
            if (SearchAutoCompleteAndHistoryResultsFragment.this.isAdded()) {
                updateAdapter(new ArrayList());
            }
        }

        private void updateAdapter(List<CRData> list) {
            if (SearchAutoCompleteAndHistoryResultsFragment.this.mAdapter.getSearchResultsListAdapter() != this.searchResultsListAdapter) {
                SearchAutoCompleteAndHistoryResultsFragment.this.mAdapter.setSearchResultsListAdapter(this.searchResultsListAdapter);
            }
            if (list == null) {
                list = new ArrayList<>();
            }
            CRData cRData = new CRData();
            cRData.setExternalSearchDriver(true);
            cRData.setTitle(SearchAutoCompleteAndHistoryResultsFragment.this.mSearchQuery);
            list.add(cRData);
            this.searchResultsListAdapter.refreshList(list);
            SearchAutoCompleteAndHistoryResultsFragment.this.mAdapter.notifyDataSetChanged();
            SearchAutoCompleteAndHistoryResultsFragment.this.mProgressDialog.setVisibility(8);
            SearchAutoCompleteAndHistoryResultsFragment.this.mRecyclerView.setVisibility(0);
            SearchAutoCompleteAndHistoryResultsFragment.this.mQueryHintView.setVisibility(8);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0085  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void fireRecentlyPageViewCall(boolean r12) {
        /*
            r11 = this;
            boolean r0 = r11.mIsOmniturePageViewFired
            if (r0 != 0) goto L_0x00d8
            boolean r0 = r11.isAdded()
            if (r0 == 0) goto L_0x00d8
            androidx.fragment.app.FragmentActivity r0 = r11.getActivity()
            if (r0 == 0) goto L_0x00d8
            androidx.fragment.app.FragmentActivity r0 = r11.getActivity()
            boolean r0 = r0 instanceof com.medscape.android.search.MedscapeSearchActivity
            if (r0 == 0) goto L_0x00d8
            androidx.fragment.app.FragmentActivity r0 = r11.getActivity()
            com.medscape.android.search.MedscapeSearchActivity r0 = (com.medscape.android.search.MedscapeSearchActivity) r0
            boolean r0 = r0.mCanRecentlyViewOmnitureCallbeMade
            if (r0 == 0) goto L_0x00d8
            com.medscape.android.BI.omniture.OmnitureManager r0 = com.medscape.android.BI.omniture.OmnitureManager.get()
            java.lang.String r0 = r0.mSearchChannel
            java.lang.String r4 = "search"
            if (r12 == 0) goto L_0x002f
            java.lang.String r12 = "recently-viewed"
            goto L_0x0031
        L_0x002f:
            java.lang.String r12 = "browse-search/view"
        L_0x0031:
            r6 = r12
            int r12 = r11.mSearchType
            int r1 = com.medscape.android.Constants.SEARCH_REFERENCE
            java.lang.String r2 = "reference and tools"
            java.lang.String r3 = ""
            if (r12 != r1) goto L_0x0043
            java.lang.String r12 = "drugs"
            java.lang.String r3 = "ddtab"
        L_0x0040:
            r5 = r12
            r0 = r2
            goto L_0x006a
        L_0x0043:
            int r12 = r11.mSearchType
            int r1 = com.medscape.android.Constants.SEARCH_NEWS
            if (r12 != r1) goto L_0x0051
            java.lang.String r12 = "news"
            java.lang.String r3 = "newstab"
            java.lang.String r0 = "news and perspectives"
        L_0x004f:
            r5 = r12
            goto L_0x006a
        L_0x0051:
            int r12 = r11.mSearchType
            int r1 = com.medscape.android.Constants.SEARCH_EDUCATION
            if (r12 != r1) goto L_0x005e
            java.lang.String r12 = "cme"
            java.lang.String r3 = "cmetab"
            java.lang.String r0 = "education"
            goto L_0x004f
        L_0x005e:
            int r12 = r11.mSearchType
            int r1 = com.medscape.android.Constants.SEARCH_MEDLINE
            if (r12 != r1) goto L_0x0069
            java.lang.String r12 = "medline"
            java.lang.String r3 = "medlinetab"
            goto L_0x0040
        L_0x0069:
            r5 = r3
        L_0x006a:
            androidx.fragment.app.FragmentActivity r12 = r11.getActivity()
            com.medscape.android.search.MedscapeSearchActivity r12 = (com.medscape.android.search.MedscapeSearchActivity) r12
            boolean r12 = r12.isInitialOmnitureCall
            if (r12 == 0) goto L_0x007f
            androidx.fragment.app.FragmentActivity r12 = r11.getActivity()
            com.medscape.android.search.MedscapeSearchActivity r12 = (com.medscape.android.search.MedscapeSearchActivity) r12
            r1 = 0
            r12.isInitialOmnitureCall = r1
            java.lang.String r3 = "feedsearch"
        L_0x007f:
            boolean r12 = com.medscape.android.util.StringUtil.isNotEmpty(r5)
            if (r12 == 0) goto L_0x00d8
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r4)
            java.lang.String r1 = "/"
            r12.append(r1)
            r12.append(r5)
            r12.append(r1)
            r12.append(r6)
            java.lang.String r12 = r12.toString()
            com.medscape.android.BI.omniture.OmnitureManager r1 = com.medscape.android.BI.omniture.OmnitureManager.get()
            java.lang.String r1 = r1.getmCurrentPageName()
            boolean r12 = r1.contains(r12)
            if (r12 != 0) goto L_0x00d8
            com.medscape.android.BI.omniture.OmnitureManager r12 = com.medscape.android.BI.omniture.OmnitureManager.get()
            r1 = 0
            java.lang.String r2 = "tap"
            r12.markModule(r3, r2, r1)
            com.medscape.android.BI.omniture.OmnitureManager r1 = com.medscape.android.BI.omniture.OmnitureManager.get()
            androidx.fragment.app.FragmentActivity r2 = r11.getActivity()
            r7 = 0
            r8 = 0
            r9 = 0
            java.lang.String r10 = ""
            r3 = r0
            java.lang.String r12 = r1.trackPageView(r2, r3, r4, r5, r6, r7, r8, r9, r10)
            boolean r0 = com.medscape.android.util.StringUtil.isNotEmpty(r12)
            if (r0 == 0) goto L_0x00d8
            androidx.fragment.app.FragmentActivity r0 = r11.getActivity()
            com.medscape.android.base.BaseActivity r0 = (com.medscape.android.base.BaseActivity) r0
            r0.setCurrentPvid(r12)
        L_0x00d8:
            r12 = 1
            r11.mIsOmniturePageViewFired = r12
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.search.SearchAutoCompleteAndHistoryResultsFragment.fireRecentlyPageViewCall(boolean):void");
    }
}
