package com.medscape.android.activity.search;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import androidx.fragment.app.Fragment;
import com.google.android.gms.ads.AdSize;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.search.model.CRData;
import com.medscape.android.activity.search.model.SearchSuggestion;
import com.medscape.android.activity.search.model.SearchSuggestionMsg;
import com.medscape.android.ads.AdsConstants;
import com.medscape.android.ads.DFPAdAction;
import com.medscape.android.ads.DFPReferenceAdListener;
import com.medscape.android.ads.INativeDFPAdLoadListener;
import com.medscape.android.ads.NativeAdAction;
import com.medscape.android.ads.NativeDFPAD;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.interfaces.RefreshableAdapter;
import com.medscape.android.landingfeed.model.FeedConstants;
import com.medscape.android.parser.model.Article;
import com.medscape.android.search.MedscapeSearchActivity;
import com.medscape.android.task.SearchExternalTask;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SearchResultsFragment extends Fragment {
    ArrayList<Article> articlesList = new ArrayList<>();
    /* access modifiers changed from: private */
    public boolean didResultsReturnAtLeastOnce = false;
    /* access modifiers changed from: private */
    public int inlineADpos;
    /* access modifiers changed from: private */
    public RefreshableAdapter mAdapter;
    /* access modifiers changed from: private */
    public int mCurrentPage = -1;
    /* access modifiers changed from: private */
    public int mCurrentResultsCount = 0;
    /* access modifiers changed from: private */
    public PullToRefreshListView mListView;
    /* access modifiers changed from: private */
    public int mMaxResults = -1;
    /* access modifiers changed from: private */
    public String mOriginalQuery;
    /* access modifiers changed from: private */
    public String mQuery;
    private int mResultsSet = 50;
    private View mRoot;
    private View mRootView;
    /* access modifiers changed from: private */
    public SearchMode mSearchMode;
    public int mSearchSuggestionType;
    /* access modifiers changed from: private */
    public int mSharethroughInLineADPos = 4;
    /* access modifiers changed from: private */
    public ProgressBar progress;
    String pvid = "";
    ArrayList<CRData> referenceList = new ArrayList<>();

    static /* synthetic */ int access$1208(SearchResultsFragment searchResultsFragment) {
        int i = searchResultsFragment.inlineADpos;
        searchResultsFragment.inlineADpos = i + 1;
        return i;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.refreshable_list, viewGroup, false);
    }

    public static SearchResultsFragment newInstance() {
        return new SearchResultsFragment();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mRootView = getView();
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mRoot = this.mRootView.findViewById(R.id.root);
            this.progress = (ProgressBar) this.mRootView.findViewById(R.id.progress);
            PullToRefreshListView pullToRefreshListView = (PullToRefreshListView) this.mRootView.findViewById(R.id.list);
            this.mListView = pullToRefreshListView;
            pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
            this.mListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
                public void onLastItemVisible() {
                    SearchResultsFragment.this.performExternalSearch();
                }
            });
            this.mSearchMode = SearchMode.fromId(arguments.getInt(Constants.EXTRA_MODE, -1));
            this.mQuery = arguments.getString(Constants.EXTRA_QUERY);
            this.mSearchSuggestionType = arguments.getInt(Constants.EXTRA_SEARCH_SUGGESTION_TYPE, 0);
            ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 2);
            resetResultsCount();
            performExternalSearch();
        }
    }

    /* renamed from: com.medscape.android.activity.search.SearchResultsFragment$5  reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$medscape$android$activity$search$SearchMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.medscape.android.activity.search.SearchMode[] r0 = com.medscape.android.activity.search.SearchMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$medscape$android$activity$search$SearchMode = r0
                com.medscape.android.activity.search.SearchMode r1 = com.medscape.android.activity.search.SearchMode.SEARCH_REFERENCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$medscape$android$activity$search$SearchMode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.medscape.android.activity.search.SearchMode r1 = com.medscape.android.activity.search.SearchMode.SEARCH_NEWS     // Catch:{ NoSuchFieldError -> 0x001d }
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
                int[] r0 = $SwitchMap$com$medscape$android$activity$search$SearchMode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.medscape.android.activity.search.SearchMode r1 = com.medscape.android.activity.search.SearchMode.SEARCH_MEDLINE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.search.SearchResultsFragment.AnonymousClass5.<clinit>():void");
        }
    }

    private String convertSearchModeToQueryString() {
        int i = AnonymousClass5.$SwitchMap$com$medscape$android$activity$search$SearchMode[this.mSearchMode.ordinal()];
        if (i == 1) {
            return "profreference";
        }
        if (i == 2) {
            return "profnews";
        }
        if (i == 3) {
            return "profeducation";
        }
        if (i != 4) {
            return null;
        }
        return "profnlmmedline";
    }

    /* access modifiers changed from: private */
    public void performExternalSearch() {
        if (areAllResultsbeLoaded()) {
            stopPullToRefresh();
            return;
        }
        this.mListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        this.mListView.setPullToRefreshOverScrollEnabled(true);
        this.mCurrentPage++;
        this.mListView.setVisibility(0);
        if (this.mSearchMode == SearchMode.SEARCH_NEWS || this.mSearchMode == SearchMode.SEARCH_EDUCATION || this.mSearchMode == SearchMode.SEARCH_MEDLINE) {
            if (this.mAdapter == null) {
                PullToRefreshListView pullToRefreshListView = this.mListView;
                SearchArticleListAdapter searchArticleListAdapter = new SearchArticleListAdapter(getActivity(), new ArrayList(), this.mSearchMode.getId());
                this.mAdapter = searchArticleListAdapter;
                pullToRefreshListView.setAdapter(searchArticleListAdapter);
            }
            this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                /* JADX WARNING: type inference failed for: r23v0, types: [android.widget.AdapterView<?>, android.widget.AdapterView] */
                /* JADX WARNING: Unknown variable types count: 1 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onItemClick(android.widget.AdapterView<?> r23, android.view.View r24, int r25, long r26) {
                    /*
                        r22 = this;
                        r0 = r22
                        r1 = r25
                        android.widget.Adapter r2 = r23.getAdapter()
                        java.lang.Object r2 = r2.getItem(r1)
                        com.medscape.android.parser.model.Article r2 = (com.medscape.android.parser.model.Article) r2
                        java.lang.Object r3 = r24.getTag()
                        java.lang.String r4 = "noresults"
                        boolean r3 = r4.equals(r3)
                        if (r3 != 0) goto L_0x00f2
                        java.lang.String r3 = r2.mCategory
                        java.lang.String r4 = "nr"
                        boolean r3 = r3.equalsIgnoreCase(r4)
                        if (r3 != 0) goto L_0x00f2
                        com.medscape.android.activity.search.model.SearchSuggestionMsg r3 = r2.mSearchSuggestionMsg
                        if (r3 == 0) goto L_0x002a
                        goto L_0x00f2
                    L_0x002a:
                        com.medscape.android.activity.search.SearchResultsFragment r3 = com.medscape.android.activity.search.SearchResultsFragment.this
                        boolean r3 = r3.isAdded()
                        if (r3 == 0) goto L_0x00f2
                        com.medscape.android.activity.search.SearchResultsFragment r3 = com.medscape.android.activity.search.SearchResultsFragment.this
                        androidx.fragment.app.FragmentActivity r3 = r3.getActivity()
                        if (r3 != 0) goto L_0x003c
                        goto L_0x00f2
                    L_0x003c:
                        java.lang.Object r3 = r24.getTag()
                        java.lang.String r4 = "suggestions"
                        boolean r3 = r4.equals(r3)
                        if (r3 == 0) goto L_0x0056
                        com.medscape.android.activity.search.SearchResultsFragment r1 = com.medscape.android.activity.search.SearchResultsFragment.this
                        androidx.fragment.app.FragmentActivity r1 = r1.getActivity()
                        com.medscape.android.search.MedscapeSearchActivity r1 = (com.medscape.android.search.MedscapeSearchActivity) r1
                        java.lang.String r2 = r2.mLink
                        r1.doSearch(r2)
                        return
                    L_0x0056:
                        int[] r3 = com.medscape.android.activity.search.SearchResultsFragment.AnonymousClass5.$SwitchMap$com$medscape$android$activity$search$SearchMode
                        com.medscape.android.activity.search.SearchResultsFragment r4 = com.medscape.android.activity.search.SearchResultsFragment.this
                        com.medscape.android.activity.search.SearchMode r4 = r4.mSearchMode
                        int r4 = r4.ordinal()
                        r3 = r3[r4]
                        r4 = 2
                        if (r3 == r4) goto L_0x008b
                        r4 = 4
                        if (r3 == r4) goto L_0x0075
                        com.medscape.android.activity.search.SearchResultsFragment r3 = com.medscape.android.activity.search.SearchResultsFragment.this
                        androidx.fragment.app.FragmentActivity r3 = r3.getActivity()
                        r4 = 0
                        com.medscape.android.activity.cme.CMEHelper.launchCMEArticle(r3, r2, r4, r4)
                        goto L_0x00aa
                    L_0x0075:
                        com.medscape.android.activity.webviews.WebviewUtil$Companion r5 = com.medscape.android.activity.webviews.WebviewUtil.Companion
                        com.medscape.android.activity.search.SearchResultsFragment r3 = com.medscape.android.activity.search.SearchResultsFragment.this
                        androidx.fragment.app.FragmentActivity r6 = r3.getActivity()
                        java.lang.String r7 = r2.mLink
                        java.lang.String r8 = r2.mTitle
                        java.lang.String r9 = "wv-launch-srch"
                        java.lang.String r10 = "medln"
                        java.lang.String r11 = "other"
                        r5.launchMedline(r6, r7, r8, r9, r10, r11)
                        goto L_0x00aa
                    L_0x008b:
                        com.medscape.android.activity.webviews.WebviewUtil$Companion r12 = com.medscape.android.activity.webviews.WebviewUtil.Companion
                        com.medscape.android.activity.search.SearchResultsFragment r3 = com.medscape.android.activity.search.SearchResultsFragment.this
                        androidx.fragment.app.FragmentActivity r13 = r3.getActivity()
                        java.lang.String r14 = r2.mLink
                        java.lang.String r15 = r2.mTitle
                        java.lang.String r19 = r2.getDate()
                        java.lang.String r2 = r2.mArticleImage
                        java.lang.String r16 = "wv-launch-srch"
                        java.lang.String r17 = "news"
                        java.lang.String r18 = "news and perspectives"
                        java.lang.String r21 = ""
                        r20 = r2
                        r12.launchNews(r13, r14, r15, r16, r17, r18, r19, r20, r21)
                    L_0x00aa:
                        java.util.HashMap r2 = new java.util.HashMap
                        r2.<init>()
                        com.medscape.android.activity.search.SearchResultsFragment r3 = com.medscape.android.activity.search.SearchResultsFragment.this
                        com.medscape.android.activity.search.SearchMode r3 = r3.mSearchMode
                        com.medscape.android.activity.search.SearchMode r4 = com.medscape.android.activity.search.SearchMode.SEARCH_NEWS
                        if (r3 != r4) goto L_0x00c4
                        com.medscape.android.activity.search.SearchResultsFragment r3 = com.medscape.android.activity.search.SearchResultsFragment.this
                        java.lang.String r3 = r3.mQuery
                        java.lang.String r4 = "wapp.querytext"
                        r2.put(r4, r3)
                    L_0x00c4:
                        com.medscape.android.activity.search.SearchResultsFragment r3 = com.medscape.android.activity.search.SearchResultsFragment.this
                        com.medscape.android.activity.search.SearchMode r3 = r3.mSearchMode
                        com.medscape.android.activity.search.SearchMode r4 = com.medscape.android.activity.search.SearchMode.SEARCH_MEDLINE
                        if (r3 == r4) goto L_0x00f2
                        com.medscape.android.activity.search.SearchResultsFragment r3 = com.medscape.android.activity.search.SearchResultsFragment.this
                        com.medscape.android.activity.search.SearchMode r3 = r3.mSearchMode
                        com.medscape.android.activity.search.SearchMode r4 = com.medscape.android.activity.search.SearchMode.SEARCH_EDUCATION
                        if (r3 == r4) goto L_0x00f2
                        com.medscape.android.BI.omniture.OmnitureManager r3 = com.medscape.android.BI.omniture.OmnitureManager.get()
                        java.lang.StringBuilder r4 = new java.lang.StringBuilder
                        r4.<init>()
                        java.lang.String r5 = ""
                        r4.append(r5)
                        r4.append(r1)
                        java.lang.String r1 = r4.toString()
                        java.lang.String r4 = "search-results"
                        r3.markModule(r4, r1, r2)
                    L_0x00f2:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.search.SearchResultsFragment.AnonymousClass2.onItemClick(android.widget.AdapterView, android.view.View, int, long):void");
                }
            });
        } else {
            if (this.mAdapter == null) {
                PullToRefreshListView pullToRefreshListView2 = this.mListView;
                SearchResultsListAdapter searchResultsListAdapter = new SearchResultsListAdapter(getActivity(), new ArrayList());
                this.mAdapter = searchResultsListAdapter;
                pullToRefreshListView2.setAdapter(searchResultsListAdapter);
            }
            ListView listView = (ListView) this.mListView.getRefreshableView();
            listView.setDivider((Drawable) null);
            listView.setDividerHeight(0);
            ReferenceItemClickListener referenceItemClickListener = new ReferenceItemClickListener((Activity) getActivity());
            referenceItemClickListener.mQueryText = this.mQuery;
            this.mListView.setOnItemClickListener(referenceItemClickListener);
        }
        new SearchExternalTask(getActivity(), convertSearchModeToQueryString(), this.mQuery, this.mResultsSet, this.mCurrentPage, new SearchExternalTask.SearchExternalCompleteListener() {
            /* JADX WARNING: Removed duplicated region for block: B:101:0x0296 A[Catch:{ Exception -> 0x02a7 }] */
            /* JADX WARNING: Removed duplicated region for block: B:107:0x02b6  */
            /* JADX WARNING: Removed duplicated region for block: B:113:? A[RETURN, SYNTHETIC] */
            /* JADX WARNING: Removed duplicated region for block: B:24:0x00ad A[Catch:{ Exception -> 0x02a7 }] */
            /* JADX WARNING: Removed duplicated region for block: B:38:0x0115 A[Catch:{ Exception -> 0x02a7 }] */
            /* JADX WARNING: Removed duplicated region for block: B:49:0x0138 A[Catch:{ Exception -> 0x02a7 }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onSearchComplete(org.json.JSONObject r7) {
                /*
                    r6 = this;
                    com.medscape.android.activity.search.SearchResultsFragment r0 = com.medscape.android.activity.search.SearchResultsFragment.this
                    android.widget.ProgressBar r0 = r0.progress
                    r1 = 8
                    r0.setVisibility(r1)
                    r0 = 0
                    if (r7 != 0) goto L_0x001a
                    com.medscape.android.activity.search.SearchResultsFragment r7 = com.medscape.android.activity.search.SearchResultsFragment.this
                    int r7 = r7.mCurrentResultsCount
                    if (r7 != 0) goto L_0x0019
                    r6.showNoResults(r0)
                L_0x0019:
                    return
                L_0x001a:
                    com.medscape.android.activity.search.SearchResultsFragment r1 = com.medscape.android.activity.search.SearchResultsFragment.this
                    com.handmark.pulltorefresh.library.PullToRefreshListView r1 = r1.mListView
                    r1.onRefreshComplete()
                    int r1 = android.os.Build.VERSION.SDK_INT
                    r2 = 22
                    if (r1 > r2) goto L_0x0034
                    com.medscape.android.activity.search.SearchResultsFragment r1 = com.medscape.android.activity.search.SearchResultsFragment.this
                    com.handmark.pulltorefresh.library.PullToRefreshListView r1 = r1.mListView
                    com.handmark.pulltorefresh.library.PullToRefreshBase$Mode r2 = com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.DISABLED
                    r1.setMode(r2)
                L_0x0034:
                    com.medscape.android.activity.search.SearchResultsFragment r1 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    int r1 = r1.mCurrentPage     // Catch:{ Exception -> 0x02a7 }
                    if (r1 != 0) goto L_0x0045
                    com.medscape.android.activity.search.SearchResultsFragment r1 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    int r2 = com.medscape.android.activity.search.SearchParser.parseMaxResults(r7)     // Catch:{ Exception -> 0x02a7 }
                    int unused = r1.mMaxResults = r2     // Catch:{ Exception -> 0x02a7 }
                L_0x0045:
                    com.medscape.android.activity.search.SearchResultsFragment r1 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ Exception -> 0x02a7 }
                    r2.<init>()     // Catch:{ Exception -> 0x02a7 }
                    r1.referenceList = r2     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r1 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ Exception -> 0x02a7 }
                    r2.<init>()     // Catch:{ Exception -> 0x02a7 }
                    r1.articlesList = r2     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r1 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r2 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.interfaces.RefreshableAdapter r2 = r2.mAdapter     // Catch:{ Exception -> 0x02a7 }
                    int r2 = r2.getCount()     // Catch:{ Exception -> 0x02a7 }
                    int unused = r1.mCurrentResultsCount = r2     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r1 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchMode r1 = r1.mSearchMode     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchMode r2 = com.medscape.android.activity.search.SearchMode.SEARCH_REFERENCE     // Catch:{ Exception -> 0x02a7 }
                    r3 = 0
                    if (r1 != r2) goto L_0x008a
                    com.medscape.android.activity.search.SearchResultsFragment r1 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    java.util.ArrayList r2 = com.medscape.android.activity.search.SearchParser.parseReferencefromJSON(r7)     // Catch:{ Exception -> 0x02a7 }
                    r1.referenceList = r2     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r1 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    java.util.ArrayList<com.medscape.android.activity.search.model.CRData> r1 = r1.referenceList     // Catch:{ Exception -> 0x02a7 }
                    if (r1 != 0) goto L_0x0081
                L_0x007f:
                    r1 = 0
                    goto L_0x00a7
                L_0x0081:
                    com.medscape.android.activity.search.SearchResultsFragment r1 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    java.util.ArrayList<com.medscape.android.activity.search.model.CRData> r1 = r1.referenceList     // Catch:{ Exception -> 0x02a7 }
                    int r1 = r1.size()     // Catch:{ Exception -> 0x02a7 }
                    goto L_0x00a7
                L_0x008a:
                    com.medscape.android.activity.search.SearchResultsFragment r1 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r2 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    androidx.fragment.app.FragmentActivity r2 = r2.getActivity()     // Catch:{ Exception -> 0x02a7 }
                    java.util.ArrayList r2 = com.medscape.android.activity.search.SearchParser.parseArticles(r7, r2)     // Catch:{ Exception -> 0x02a7 }
                    r1.articlesList = r2     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r1 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    java.util.ArrayList<com.medscape.android.parser.model.Article> r1 = r1.articlesList     // Catch:{ Exception -> 0x02a7 }
                    if (r1 != 0) goto L_0x009f
                    goto L_0x007f
                L_0x009f:
                    com.medscape.android.activity.search.SearchResultsFragment r1 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    java.util.ArrayList<com.medscape.android.parser.model.Article> r1 = r1.articlesList     // Catch:{ Exception -> 0x02a7 }
                    int r1 = r1.size()     // Catch:{ Exception -> 0x02a7 }
                L_0x00a7:
                    com.medscape.android.activity.search.SearchResultsFragment r2 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    int r2 = r2.mSearchSuggestionType     // Catch:{ Exception -> 0x02a7 }
                    if (r2 != 0) goto L_0x0115
                    com.medscape.android.activity.search.SearchResultsFragment r2 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    int r2 = r2.mCurrentResultsCount     // Catch:{ Exception -> 0x02a7 }
                    if (r2 != 0) goto L_0x0135
                    com.medscape.android.activity.search.SearchResultsFragment r2 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    java.lang.String r2 = r2.mQuery     // Catch:{ Exception -> 0x02a7 }
                    java.lang.String r4 = "beast cancer"
                    boolean r2 = r2.equalsIgnoreCase(r4)     // Catch:{ Exception -> 0x02a7 }
                    r4 = 11
                    if (r1 < r4) goto L_0x00c7
                    if (r2 == 0) goto L_0x0135
                L_0x00c7:
                    com.medscape.android.activity.search.model.SearchSuggestion r4 = new com.medscape.android.activity.search.model.SearchSuggestion     // Catch:{ Exception -> 0x02a7 }
                    r4.<init>()     // Catch:{ Exception -> 0x02a7 }
                    if (r2 != 0) goto L_0x00d9
                    com.medscape.android.activity.search.SearchResultsFragment r2 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    java.util.ArrayList r7 = com.medscape.android.activity.search.SearchParser.parseSearchSuggestions(r7)     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.model.SearchSuggestion r4 = r2.getBestSearchSuggestion(r1, r7)     // Catch:{ Exception -> 0x02a7 }
                    goto L_0x00de
                L_0x00d9:
                    java.lang.String r7 = "breast cancer"
                    r4.setQuery(r7)     // Catch:{ Exception -> 0x02a7 }
                L_0x00de:
                    if (r4 == 0) goto L_0x010f
                    com.medscape.android.activity.search.SearchResultsFragment r7 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r1 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    java.lang.String r1 = r1.mQuery     // Catch:{ Exception -> 0x02a7 }
                    java.lang.String unused = r7.mOriginalQuery = r1     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r7 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    java.lang.String r1 = r4.getQuery()     // Catch:{ Exception -> 0x02a7 }
                    java.lang.String unused = r7.mQuery = r1     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r7 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    r1 = 3
                    r7.mSearchSuggestionType = r1     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r7 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    r7.resetResultsCount()     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r7 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    r7.performExternalSearch()     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r7 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r1 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    java.lang.String r1 = r1.mQuery     // Catch:{ Exception -> 0x02a7 }
                    r7.sendAutoCorrectOmniturePageView(r1)     // Catch:{ Exception -> 0x02a7 }
                    return
                L_0x010f:
                    if (r1 != 0) goto L_0x0135
                    r6.showNoResults(r0)     // Catch:{ Exception -> 0x02a7 }
                    goto L_0x0135
                L_0x0115:
                    com.medscape.android.activity.search.SearchResultsFragment r2 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    int r2 = r2.mSearchSuggestionType     // Catch:{ Exception -> 0x02a7 }
                    r4 = 4
                    if (r2 != r4) goto L_0x0135
                    java.util.ArrayList r7 = com.medscape.android.activity.search.SearchParser.parseSearchSuggestions(r7)     // Catch:{ Exception -> 0x02a7 }
                    if (r1 != 0) goto L_0x0126
                    r6.showNoResults(r7)     // Catch:{ Exception -> 0x02a7 }
                    goto L_0x0135
                L_0x0126:
                    if (r7 == 0) goto L_0x0135
                    int r2 = r7.size()     // Catch:{ Exception -> 0x02a7 }
                    if (r2 <= 0) goto L_0x0135
                    com.medscape.android.activity.search.SearchResultsFragment r2 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    java.util.ArrayList r7 = r2.getSuggestionString(r7)     // Catch:{ Exception -> 0x02a7 }
                    goto L_0x0136
                L_0x0135:
                    r7 = r0
                L_0x0136:
                    if (r1 <= 0) goto L_0x028e
                    com.medscape.android.activity.search.SearchResultsFragment r1 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    boolean r1 = r1.isAdded()     // Catch:{ Exception -> 0x02a7 }
                    if (r1 == 0) goto L_0x0178
                    com.medscape.android.activity.search.SearchResultsFragment r1 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    android.content.Context r1 = r1.getContext()     // Catch:{ Exception -> 0x02a7 }
                    if (r1 == 0) goto L_0x0178
                    com.medscape.android.activity.search.SearchResultsFragment r1 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    android.content.Context r1 = r1.getContext()     // Catch:{ Exception -> 0x02a7 }
                    boolean r1 = com.medscape.android.util.Util.isOnline(r1)     // Catch:{ Exception -> 0x02a7 }
                    if (r1 == 0) goto L_0x0178
                    com.medscape.android.activity.search.SearchResultsFragment r1 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchMode r1 = r1.mSearchMode     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchMode r2 = com.medscape.android.activity.search.SearchMode.SEARCH_EDUCATION     // Catch:{ Exception -> 0x02a7 }
                    if (r1 == r2) goto L_0x0178
                    com.medscape.android.activity.search.SearchResultsFragment r1 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    int r1 = r1.mCurrentResultsCount     // Catch:{ Exception -> 0x02a7 }
                    if (r1 != 0) goto L_0x0178
                    com.medscape.android.activity.search.SearchResultsFragment r1 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    android.content.Context r1 = r1.getContext()     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.capabilities.CapabilitiesManager r1 = com.medscape.android.capabilities.CapabilitiesManager.getInstance(r1)     // Catch:{ Exception -> 0x02a7 }
                    boolean r1 = r1.isSharethroughFeatureAvailable()     // Catch:{ Exception -> 0x02a7 }
                    if (r1 == 0) goto L_0x0178
                    r1 = 1
                    goto L_0x0179
                L_0x0178:
                    r1 = 0
                L_0x0179:
                    com.medscape.android.activity.search.SearchResultsFragment r2 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r4 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    int r4 = r4.mSharethroughInLineADPos     // Catch:{ Exception -> 0x02a7 }
                    int unused = r2.inlineADpos = r4     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r2 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchMode r2 = r2.mSearchMode     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchMode r4 = com.medscape.android.activity.search.SearchMode.SEARCH_REFERENCE     // Catch:{ Exception -> 0x02a7 }
                    if (r2 != r4) goto L_0x01f2
                    com.medscape.android.activity.search.SearchResultsFragment r2 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    int r2 = r2.mCurrentResultsCount     // Catch:{ Exception -> 0x02a7 }
                    if (r2 != 0) goto L_0x01d4
                    com.medscape.android.activity.search.SearchResultsFragment r2 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    boolean r2 = r2.canAddSearchHeader(r7)     // Catch:{ Exception -> 0x02a7 }
                    if (r2 == 0) goto L_0x01bc
                    com.medscape.android.activity.search.model.CRData r2 = new com.medscape.android.activity.search.model.CRData     // Catch:{ Exception -> 0x02a7 }
                    r2.<init>()     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r4 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r5 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    int r5 = r5.mSearchSuggestionType     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.model.SearchSuggestionMsg r7 = r4.buildSuggestionMsg(r5, r7)     // Catch:{ Exception -> 0x02a7 }
                    r2.setSearchSuggestionMsg(r7)     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r7 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    java.util.ArrayList<com.medscape.android.activity.search.model.CRData> r7 = r7.referenceList     // Catch:{ Exception -> 0x02a7 }
                    r7.add(r3, r2)     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r7 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment.access$1208(r7)     // Catch:{ Exception -> 0x02a7 }
                L_0x01bc:
                    if (r1 == 0) goto L_0x01c5
                    com.medscape.android.activity.search.SearchResultsFragment r7 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    r7.makeShareThroughADCall()     // Catch:{ Exception -> 0x02a7 }
                    goto L_0x028e
                L_0x01c5:
                    com.medscape.android.activity.search.SearchResultsFragment r7 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.interfaces.RefreshableAdapter r7 = r7.mAdapter     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r1 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    java.util.ArrayList<com.medscape.android.activity.search.model.CRData> r1 = r1.referenceList     // Catch:{ Exception -> 0x02a7 }
                    r7.refreshList(r1)     // Catch:{ Exception -> 0x02a7 }
                    goto L_0x028e
                L_0x01d4:
                    com.medscape.android.activity.search.SearchResultsFragment r7 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    java.util.ArrayList<com.medscape.android.activity.search.model.CRData> r7 = r7.referenceList     // Catch:{ Exception -> 0x02a7 }
                    java.util.Iterator r7 = r7.iterator()     // Catch:{ Exception -> 0x02a7 }
                L_0x01dc:
                    boolean r1 = r7.hasNext()     // Catch:{ Exception -> 0x02a7 }
                    if (r1 == 0) goto L_0x028e
                    java.lang.Object r1 = r7.next()     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.model.CRData r1 = (com.medscape.android.activity.search.model.CRData) r1     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r2 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.interfaces.RefreshableAdapter r2 = r2.mAdapter     // Catch:{ Exception -> 0x02a7 }
                    r2.addToList(r1)     // Catch:{ Exception -> 0x02a7 }
                    goto L_0x01dc
                L_0x01f2:
                    com.medscape.android.activity.search.SearchResultsFragment r2 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchMode r2 = r2.mSearchMode     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchMode r4 = com.medscape.android.activity.search.SearchMode.SEARCH_EDUCATION     // Catch:{ Exception -> 0x02a7 }
                    if (r2 != r4) goto L_0x022d
                    com.medscape.android.activity.search.SearchResultsFragment r2 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    boolean r2 = r2.isAdded()     // Catch:{ Exception -> 0x02a7 }
                    if (r2 == 0) goto L_0x0227
                    com.medscape.android.activity.search.SearchResultsFragment r2 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    androidx.fragment.app.FragmentActivity r2 = r2.getActivity()     // Catch:{ Exception -> 0x02a7 }
                    if (r2 == 0) goto L_0x0227
                    com.medscape.android.activity.search.SearchResultsFragment r2 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    androidx.fragment.app.FragmentActivity r2 = r2.getActivity()     // Catch:{ Exception -> 0x02a7 }
                    boolean r2 = r2 instanceof com.medscape.android.base.BaseActivity     // Catch:{ Exception -> 0x02a7 }
                    if (r2 == 0) goto L_0x0227
                    com.medscape.android.activity.search.SearchResultsFragment r2 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r4 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    androidx.fragment.app.FragmentActivity r4 = r4.getActivity()     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.base.BaseActivity r4 = (com.medscape.android.base.BaseActivity) r4     // Catch:{ Exception -> 0x02a7 }
                    java.lang.String r4 = r4.getCurrentPvid()     // Catch:{ Exception -> 0x02a7 }
                    r2.pvid = r4     // Catch:{ Exception -> 0x02a7 }
                    goto L_0x022d
                L_0x0227:
                    com.medscape.android.activity.search.SearchResultsFragment r2 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    java.lang.String r4 = ""
                    r2.pvid = r4     // Catch:{ Exception -> 0x02a7 }
                L_0x022d:
                    com.medscape.android.activity.search.SearchResultsFragment r2 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    int r2 = r2.mCurrentResultsCount     // Catch:{ Exception -> 0x02a7 }
                    if (r2 != 0) goto L_0x0270
                    com.medscape.android.activity.search.SearchResultsFragment r2 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    boolean r2 = r2.canAddSearchHeader(r7)     // Catch:{ Exception -> 0x02a7 }
                    if (r2 == 0) goto L_0x025a
                    com.medscape.android.parser.model.Article r2 = new com.medscape.android.parser.model.Article     // Catch:{ Exception -> 0x02a7 }
                    r2.<init>()     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r4 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r5 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    int r5 = r5.mSearchSuggestionType     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.model.SearchSuggestionMsg r7 = r4.buildSuggestionMsg(r5, r7)     // Catch:{ Exception -> 0x02a7 }
                    r2.mSearchSuggestionMsg = r7     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r7 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    java.util.ArrayList<com.medscape.android.parser.model.Article> r7 = r7.articlesList     // Catch:{ Exception -> 0x02a7 }
                    r7.add(r3, r2)     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r7 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment.access$1208(r7)     // Catch:{ Exception -> 0x02a7 }
                L_0x025a:
                    if (r1 == 0) goto L_0x0262
                    com.medscape.android.activity.search.SearchResultsFragment r7 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    r7.makeShareThroughADCall()     // Catch:{ Exception -> 0x02a7 }
                    goto L_0x028e
                L_0x0262:
                    com.medscape.android.activity.search.SearchResultsFragment r7 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.interfaces.RefreshableAdapter r7 = r7.mAdapter     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r1 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    java.util.ArrayList<com.medscape.android.parser.model.Article> r1 = r1.articlesList     // Catch:{ Exception -> 0x02a7 }
                    r7.refreshList(r1)     // Catch:{ Exception -> 0x02a7 }
                    goto L_0x028e
                L_0x0270:
                    com.medscape.android.activity.search.SearchResultsFragment r7 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    java.util.ArrayList<com.medscape.android.parser.model.Article> r7 = r7.articlesList     // Catch:{ Exception -> 0x02a7 }
                    java.util.Iterator r7 = r7.iterator()     // Catch:{ Exception -> 0x02a7 }
                L_0x0278:
                    boolean r1 = r7.hasNext()     // Catch:{ Exception -> 0x02a7 }
                    if (r1 == 0) goto L_0x028e
                    java.lang.Object r1 = r7.next()     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.parser.model.Article r1 = (com.medscape.android.parser.model.Article) r1     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.activity.search.SearchResultsFragment r2 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.interfaces.RefreshableAdapter r2 = r2.mAdapter     // Catch:{ Exception -> 0x02a7 }
                    r2.addToList(r1)     // Catch:{ Exception -> 0x02a7 }
                    goto L_0x0278
                L_0x028e:
                    com.medscape.android.activity.search.SearchResultsFragment r7 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    boolean r7 = r7.areAllResultsbeLoaded()     // Catch:{ Exception -> 0x02a7 }
                    if (r7 == 0) goto L_0x029b
                    com.medscape.android.activity.search.SearchResultsFragment r7 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    r7.stopPullToRefresh()     // Catch:{ Exception -> 0x02a7 }
                L_0x029b:
                    com.medscape.android.activity.search.SearchResultsFragment r7 = com.medscape.android.activity.search.SearchResultsFragment.this     // Catch:{ Exception -> 0x02a7 }
                    com.medscape.android.interfaces.RefreshableAdapter r7 = r7.mAdapter     // Catch:{ Exception -> 0x02a7 }
                    android.widget.BaseAdapter r7 = (android.widget.BaseAdapter) r7     // Catch:{ Exception -> 0x02a7 }
                    r7.notifyDataSetChanged()     // Catch:{ Exception -> 0x02a7 }
                    goto L_0x02ae
                L_0x02a7:
                    r7 = move-exception
                    r6.showNoResults(r0)
                    r7.printStackTrace()
                L_0x02ae:
                    com.medscape.android.activity.search.SearchResultsFragment r7 = com.medscape.android.activity.search.SearchResultsFragment.this
                    int r7 = r7.mCurrentPage
                    if (r7 <= 0) goto L_0x02bb
                    com.medscape.android.activity.search.SearchResultsFragment r7 = com.medscape.android.activity.search.SearchResultsFragment.this
                    r7.sendMoreOmniturePageView()
                L_0x02bb:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.search.SearchResultsFragment.AnonymousClass3.onSearchComplete(org.json.JSONObject):void");
            }

            public void onNoInternet() {
                SearchResultsFragment.this.progress.setVisibility(8);
                Util.hideKeyboard(SearchResultsFragment.this.getActivity());
                if (SearchResultsFragment.this.isAdded() && SearchResultsFragment.this.getActivity() != null && (SearchResultsFragment.this.getActivity() instanceof MedscapeSearchActivity)) {
                    ((MedscapeSearchActivity) SearchResultsFragment.this.getActivity()).showNoNetworkException();
                }
            }

            private void showNoResults(ArrayList<SearchSuggestion> arrayList) {
                SearchSuggestionMsg searchSuggestionMsg = new SearchSuggestionMsg();
                searchSuggestionMsg.setUserQuery(SearchResultsFragment.this.mQuery);
                if (arrayList == null || arrayList.size() <= 0) {
                    searchSuggestionMsg.setSuggestionMode(1);
                } else {
                    searchSuggestionMsg.setSuggestionMode(2);
                    searchSuggestionMsg.setSuggestions(SearchResultsFragment.this.getSuggestionString(arrayList));
                }
                if (SearchResultsFragment.this.mSearchMode == SearchMode.SEARCH_REFERENCE) {
                    CRData cRData = new CRData();
                    cRData.setSearchSuggestionMsg(searchSuggestionMsg);
                    SearchResultsFragment.this.mAdapter.refreshList(new ArrayList());
                    SearchResultsFragment.this.mAdapter.addToList(cRData);
                } else if (!SearchResultsFragment.this.didResultsReturnAtLeastOnce) {
                    Article article = new Article();
                    article.mSearchSuggestionMsg = searchSuggestionMsg;
                    SearchResultsFragment.this.mAdapter.refreshList(new ArrayList());
                    SearchResultsFragment.this.mAdapter.addToList(article);
                }
                SearchResultsFragment.this.mListView.onRefreshComplete();
                SearchResultsFragment.this.mListView.setMode(PullToRefreshBase.Mode.DISABLED);
                SearchResultsFragment.this.progress.setVisibility(8);
            }
        }).execute(new Void[0]);
        this.progress.setVisibility(0);
        if (getActivity() != null && (getActivity() instanceof MedscapeSearchActivity)) {
            ((MedscapeSearchActivity) getActivity()).mIsLastSearchExternal = true;
        }
    }

    /* access modifiers changed from: private */
    public SearchSuggestionMsg buildSuggestionMsg(int i, ArrayList<String> arrayList) {
        SearchSuggestionMsg searchSuggestionMsg = new SearchSuggestionMsg();
        searchSuggestionMsg.setUserQuery(this.mOriginalQuery);
        searchSuggestionMsg.setAutoCorrectedQuery(this.mQuery);
        searchSuggestionMsg.setSuggestionMode(i);
        searchSuggestionMsg.setSuggestions(arrayList);
        return searchSuggestionMsg;
    }

    /* access modifiers changed from: private */
    public void resetResultsCount() {
        this.mCurrentResultsCount = 0;
        this.mMaxResults = -1;
        this.mCurrentPage = -1;
    }

    /* access modifiers changed from: private */
    public void stopPullToRefresh() {
        this.mListView.setMode(PullToRefreshBase.Mode.DISABLED);
        this.mListView.onRefreshComplete();
        this.mListView.setPullToRefreshOverScrollEnabled(false);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r3.mCurrentPage;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean areAllResultsbeLoaded() {
        /*
            r3 = this;
            int r0 = r3.mCurrentResultsCount
            int r1 = r3.mMaxResults
            if (r0 == r1) goto L_0x001e
            int r0 = r3.mCurrentPage
            if (r0 <= 0) goto L_0x0010
            int r2 = r3.mResultsSet
            int r0 = r0 * r2
            if (r0 > r1) goto L_0x001e
        L_0x0010:
            int r0 = r3.mCurrentPage
            r1 = -1
            if (r0 == r1) goto L_0x001c
            int r0 = r3.mResultsSet
            int r1 = r3.mMaxResults
            if (r0 <= r1) goto L_0x001c
            goto L_0x001e
        L_0x001c:
            r0 = 0
            goto L_0x001f
        L_0x001e:
            r0 = 1
        L_0x001f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.search.SearchResultsFragment.areAllResultsbeLoaded():boolean");
    }

    /* access modifiers changed from: private */
    public void sendMoreOmniturePageView() {
        String str = OmnitureManager.get().mSearchChannel;
        int i = AnonymousClass5.$SwitchMap$com$medscape$android$activity$search$SearchMode[this.mSearchMode.ordinal()];
        String str2 = i != 1 ? i != 2 ? i != 4 ? FeedConstants.CME_ITEM : RecentlyViewedSuggestionHelper.TYPE_MEDLINE : "news" : "drugs";
        OmnitureManager.get().markModule("search-more", String.valueOf(this.mCurrentPage), (Map<String, Object>) null);
        this.pvid = OmnitureManager.get().trackPageView(getActivity(), str, "search", str2, OmnitureConstants.PAGE_NAME_RESULTS, (String) null, (Map<String, Object>) null, false, this.pvid);
        if (isAdded() && getActivity() != null) {
            ((BaseActivity) getActivity()).setCurrentPvid(this.pvid);
        }
    }

    /* access modifiers changed from: private */
    public void sendAutoCorrectOmniturePageView(String str) {
        String str2 = OmnitureManager.get().mSearchChannel;
        int i = AnonymousClass5.$SwitchMap$com$medscape$android$activity$search$SearchMode[this.mSearchMode.ordinal()];
        String str3 = RecentlyViewedSuggestionHelper.TYPE_MEDLINE;
        String str4 = "news";
        if (i == 1) {
            str3 = "drgs";
            str4 = "drugs";
        } else if (i != 2) {
            if (i != 4) {
                str3 = FeedConstants.CME_ITEM;
            }
            str4 = str3;
        } else {
            str3 = str4;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("wapp.querytext", str);
        OmnitureManager.get().markModule("search-autocorrect", str3, hashMap);
        this.pvid = OmnitureManager.get().trackPageView(getActivity(), str2, "search", str4, OmnitureConstants.PAGE_NAME_RESULTS, (String) null, (Map<String, Object>) null, false, this.pvid);
        if (isAdded() && getActivity() != null) {
            ((BaseActivity) getActivity()).setCurrentPvid(this.pvid);
        }
    }

    public SearchSuggestion getBestSearchSuggestion(int i, ArrayList<SearchSuggestion> arrayList) {
        SearchSuggestion searchSuggestion = null;
        if (arrayList != null && arrayList.size() > 0) {
            Iterator<SearchSuggestion> it = arrayList.iterator();
            while (it.hasNext()) {
                SearchSuggestion next = it.next();
                if (searchSuggestion == null) {
                    if (next.getHits() <= i) {
                    }
                } else if (searchSuggestion.getHits() >= next.getHits()) {
                }
                searchSuggestion = next;
            }
        }
        return searchSuggestion;
    }

    public ArrayList<String> getSuggestionString(ArrayList<SearchSuggestion> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        if (arrayList != null && arrayList.size() > 0) {
            Iterator<SearchSuggestion> it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add(it.next().getQuery());
            }
        }
        return arrayList2;
    }

    public boolean canAddSearchHeader(ArrayList<String> arrayList) {
        int i = this.mSearchSuggestionType;
        return i == 3 || (i == 4 && arrayList != null && arrayList.size() > 0);
    }

    /* access modifiers changed from: private */
    public void makeShareThroughADCall() {
        if (isAdded() && getContext() != null) {
            NativeAdAction nativeAdAction = new NativeAdAction(getContext(), DFPReferenceAdListener.AD_UNIT_ID, (View) null);
            nativeAdAction.prepADWithCombinedRequests(new INativeDFPAdLoadListener() {
                public void onAdLoaded(NativeDFPAD nativeDFPAD) {
                    if (SearchResultsFragment.this.isAdded() && SearchResultsFragment.this.getActivity() != null) {
                        SearchResultsFragment.this.mAdapter.setInlineAD(nativeDFPAD);
                        if (SearchResultsFragment.this.mSearchMode == SearchMode.SEARCH_REFERENCE) {
                            CRData cRData = new CRData();
                            cRData.setInlineAD(true);
                            if (SearchResultsFragment.this.referenceList.size() > SearchResultsFragment.this.inlineADpos) {
                                SearchResultsFragment.this.referenceList.add(SearchResultsFragment.this.inlineADpos, cRData);
                            } else {
                                SearchResultsFragment.this.referenceList.add(cRData);
                            }
                            SearchResultsFragment.this.mAdapter.refreshList(SearchResultsFragment.this.referenceList);
                            return;
                        }
                        Article article = new Article();
                        article.mIsInlineAD = true;
                        if (SearchResultsFragment.this.articlesList.size() > SearchResultsFragment.this.inlineADpos) {
                            SearchResultsFragment.this.articlesList.add(SearchResultsFragment.this.inlineADpos, article);
                        } else {
                            SearchResultsFragment.this.articlesList.add(article);
                        }
                        SearchResultsFragment.this.mAdapter.refreshList(SearchResultsFragment.this.articlesList);
                    }
                }

                public void onAdFailedToLoad(int i) {
                    if (SearchResultsFragment.this.isAdded() && SearchResultsFragment.this.getActivity() != null) {
                        SearchResultsFragment.this.mAdapter.removeInlineAD();
                        if (SearchResultsFragment.this.mSearchMode == SearchMode.SEARCH_REFERENCE) {
                            SearchResultsFragment.this.mAdapter.refreshList(SearchResultsFragment.this.referenceList);
                        } else {
                            SearchResultsFragment.this.mAdapter.refreshList(SearchResultsFragment.this.articlesList);
                        }
                    }
                }
            }, new AdSize[]{DFPAdAction.ADSIZE_320x80, DFPAdAction.ADSIZE_320x95, DFPAdAction.ADSIZE_1x3});
            nativeAdAction.isSharethrough = true;
            HashMap hashMap = new HashMap();
            hashMap.put("pc", "search-health");
            hashMap.put("pos", getResources().getString(R.string.sharethrough_ad_pos));
            hashMap.put(AdsConstants.KW, StringUtil.isNotEmpty(this.mOriginalQuery) ? this.mOriginalQuery : this.mQuery);
            if (getActivity() instanceof BaseActivity) {
                this.pvid = ((BaseActivity) getActivity()).getCurrentPvid();
            }
            hashMap.put("pvid", this.pvid);
            nativeAdAction.makeADRequestWithoutBidding(hashMap);
        }
    }
}
