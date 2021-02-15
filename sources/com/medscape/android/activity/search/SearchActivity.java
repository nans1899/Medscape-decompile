package com.medscape.android.activity.search;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.AbstractBreadcrumbNavigableActivity;
import com.medscape.android.activity.formulary.BrandModels;
import com.medscape.android.activity.formulary.FormularyFinder;
import com.medscape.android.activity.formulary.FormularyMyPlanPage;
import com.medscape.android.activity.formulary.SelectBrandActivity;
import com.medscape.android.activity.search.ReferenceItemClickListener;
import com.medscape.android.activity.search.model.CRData;
import com.medscape.android.drugs.helper.SearchHelper;
import com.medscape.android.drugs.parser.DrugMonographParser;
import com.medscape.android.interfaces.RefreshableAdapter;
import com.medscape.android.parser.model.Article;
import com.medscape.android.task.SearchExternalTask;
import com.medscape.android.task.SearchLocalTask;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.OldConstants;
import com.medscape.android.util.Util;
import com.medscape.android.util.ViewHelper;
import com.medscape.android.webmdrx.RxLauncher;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SearchActivity extends AbstractBreadcrumbNavigableActivity implements ReferenceItemClickListener.OpenFormularyListener, ReferenceItemClickListener.OpenRxListener {
    String TAG;
    /* access modifiers changed from: private */
    public boolean didResultsReturnAtLeastOnce = false;
    /* access modifiers changed from: private */
    public RefreshableAdapter mAdapter;
    FormularyFinder mFormularyFinder;
    boolean mIsFormulary;
    boolean mIsRx;
    ReferenceItemClickListener mItemClickListener;
    /* access modifiers changed from: private */
    public PullToRefreshListView mListView;
    /* access modifiers changed from: private */
    public MedscapeException mNoNetworkException;
    /* access modifiers changed from: private */
    public String mQuery;
    /* access modifiers changed from: private */
    public View mRoot;
    /* access modifiers changed from: private */
    public SearchMode mSearchMode;
    /* access modifiers changed from: private */
    public ProgressBar progress;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.refreshable_list);
        this.TAG = "Search Activity";
        this.mRoot = findViewById(R.id.root);
        this.progress = (ProgressBar) findViewById(R.id.progress);
        PullToRefreshListView pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.list);
        this.mListView = pullToRefreshListView;
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        this.mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
            }

            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                SearchActivity.this.performLocalOrExternalSearch();
            }
        });
        this.mSearchMode = SearchMode.fromId(getIntent().getIntExtra(Constants.EXTRA_MODE, -1));
        this.mQuery = getIntent().getStringExtra(Constants.EXTRA_QUERY);
        this.mIsFormulary = getIntent().getBooleanExtra("isFormulary", false);
        this.mIsRx = getIntent().getBooleanExtra("isRx", false);
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 2);
        performLocalOrExternalSearch();
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        super.setupActionBar();
        int intExtra = getIntent().getIntExtra(Constants.EXTRA_MODE, -1);
        int i = R.drawable.ref_logo;
        if (intExtra == 1) {
            i = R.drawable.news_logo;
        } else if (intExtra == 4) {
            i = R.drawable.home_logo;
        }
        Drawable drawable = ContextCompat.getDrawable(this, i);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.white));
        getSupportActionBar().setLogo(drawable);
        getSupportActionBar().setDisplayOptions(1, 9);
    }

    /* renamed from: com.medscape.android.activity.search.SearchActivity$7  reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
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
            throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.search.SearchActivity.AnonymousClass7.<clinit>():void");
        }
    }

    private String convertSearchModeToQueryString() {
        int i = AnonymousClass7.$SwitchMap$com$medscape$android$activity$search$SearchMode[this.mSearchMode.ordinal()];
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
        return "profmedline";
    }

    /* access modifiers changed from: private */
    public void performLocalOrExternalSearch() {
        MedscapeException medscapeException = this.mNoNetworkException;
        if (medscapeException != null) {
            medscapeException.dismissSnackBar();
            this.mNoNetworkException = null;
        }
        if (this.mSearchMode == SearchMode.SEARCH_NEWS || this.mSearchMode == SearchMode.SEARCH_EDUCATION || this.mSearchMode == SearchMode.SEARCH_MEDLINE) {
            if (this.mAdapter == null) {
                PullToRefreshListView pullToRefreshListView = this.mListView;
                SearchArticleAdapter searchArticleAdapter = new SearchArticleAdapter(this, new ArrayList());
                this.mAdapter = searchArticleAdapter;
                pullToRefreshListView.setAdapter(searchArticleAdapter);
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
                        if (r3 == 0) goto L_0x001b
                        return
                    L_0x001b:
                        java.lang.Object r3 = r24.getTag()
                        java.lang.String r4 = "suggestions"
                        boolean r3 = r4.equals(r3)
                        if (r3 == 0) goto L_0x0034
                        com.medscape.android.activity.search.SearchActivity r1 = com.medscape.android.activity.search.SearchActivity.this
                        java.lang.String r2 = r2.mLink
                        java.lang.String unused = r1.mQuery = r2
                        com.medscape.android.activity.search.SearchActivity r1 = com.medscape.android.activity.search.SearchActivity.this
                        r1.performLocalOrExternalSearch()
                        return
                    L_0x0034:
                        int[] r3 = com.medscape.android.activity.search.SearchActivity.AnonymousClass7.$SwitchMap$com$medscape$android$activity$search$SearchMode
                        com.medscape.android.activity.search.SearchActivity r4 = com.medscape.android.activity.search.SearchActivity.this
                        com.medscape.android.activity.search.SearchMode r4 = r4.mSearchMode
                        int r4 = r4.ordinal()
                        r3 = r3[r4]
                        r4 = 2
                        if (r3 == r4) goto L_0x0061
                        r4 = 4
                        if (r3 == r4) goto L_0x004f
                        com.medscape.android.activity.search.SearchActivity r3 = com.medscape.android.activity.search.SearchActivity.this
                        r4 = 0
                        com.medscape.android.activity.cme.CMEHelper.launchCMEArticle(r3, r2, r4, r4)
                        goto L_0x007c
                    L_0x004f:
                        com.medscape.android.activity.webviews.WebviewUtil$Companion r5 = com.medscape.android.activity.webviews.WebviewUtil.Companion
                        com.medscape.android.activity.search.SearchActivity r6 = com.medscape.android.activity.search.SearchActivity.this
                        java.lang.String r7 = r2.mLink
                        java.lang.String r8 = r2.mTitle
                        java.lang.String r9 = "wv-launch-srch"
                        java.lang.String r10 = "medln"
                        java.lang.String r11 = "other"
                        r5.launchMedline(r6, r7, r8, r9, r10, r11)
                        goto L_0x007c
                    L_0x0061:
                        com.medscape.android.activity.webviews.WebviewUtil$Companion r12 = com.medscape.android.activity.webviews.WebviewUtil.Companion
                        com.medscape.android.activity.search.SearchActivity r13 = com.medscape.android.activity.search.SearchActivity.this
                        java.lang.String r14 = r2.mLink
                        java.lang.String r15 = r2.mTitle
                        java.lang.String r19 = r2.getDate()
                        java.lang.String r2 = r2.mArticleImage
                        java.lang.String r16 = "wv-launch-srch"
                        java.lang.String r17 = "news"
                        java.lang.String r18 = "other"
                        java.lang.String r21 = ""
                        r20 = r2
                        r12.launchNews(r13, r14, r15, r16, r17, r18, r19, r20, r21)
                    L_0x007c:
                        java.util.HashMap r2 = new java.util.HashMap
                        r2.<init>()
                        com.medscape.android.activity.search.SearchActivity r3 = com.medscape.android.activity.search.SearchActivity.this
                        com.medscape.android.activity.search.SearchMode r3 = r3.mSearchMode
                        com.medscape.android.activity.search.SearchMode r4 = com.medscape.android.activity.search.SearchMode.SEARCH_NEWS
                        if (r3 != r4) goto L_0x0096
                        com.medscape.android.activity.search.SearchActivity r3 = com.medscape.android.activity.search.SearchActivity.this
                        java.lang.String r3 = r3.mQuery
                        java.lang.String r4 = "wapp.querytext"
                        r2.put(r4, r3)
                    L_0x0096:
                        com.medscape.android.activity.search.SearchActivity r3 = com.medscape.android.activity.search.SearchActivity.this
                        com.medscape.android.activity.search.SearchMode r3 = r3.mSearchMode
                        com.medscape.android.activity.search.SearchMode r4 = com.medscape.android.activity.search.SearchMode.SEARCH_EDUCATION
                        if (r3 == r4) goto L_0x00c4
                        com.medscape.android.activity.search.SearchActivity r3 = com.medscape.android.activity.search.SearchActivity.this
                        com.medscape.android.activity.search.SearchMode r3 = r3.mSearchMode
                        com.medscape.android.activity.search.SearchMode r4 = com.medscape.android.activity.search.SearchMode.SEARCH_MEDLINE
                        if (r3 == r4) goto L_0x00c4
                        com.medscape.android.BI.omniture.OmnitureManager r3 = com.medscape.android.BI.omniture.OmnitureManager.get()
                        java.lang.StringBuilder r4 = new java.lang.StringBuilder
                        r4.<init>()
                        java.lang.String r5 = ""
                        r4.append(r5)
                        r4.append(r1)
                        java.lang.String r1 = r4.toString()
                        java.lang.String r4 = "srch-results"
                        r3.markModule(r4, r1, r2)
                    L_0x00c4:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.search.SearchActivity.AnonymousClass2.onItemClick(android.widget.AdapterView, android.view.View, int, long):void");
                }
            });
            new SearchExternalTask(this, convertSearchModeToQueryString(), this.mQuery, 50, this.mAdapter.getCount() / 50, new SearchExternalTask.SearchExternalCompleteListener() {
                /* JADX WARNING: Removed duplicated region for block: B:36:0x00d1  */
                /* JADX WARNING: Removed duplicated region for block: B:40:0x00de  */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onSearchComplete(org.json.JSONObject r12) {
                    /*
                        r11 = this;
                        com.medscape.android.activity.search.SearchActivity r0 = com.medscape.android.activity.search.SearchActivity.this
                        com.medscape.android.util.MedscapeException r0 = r0.mNoNetworkException
                        if (r0 == 0) goto L_0x0017
                        com.medscape.android.activity.search.SearchActivity r0 = com.medscape.android.activity.search.SearchActivity.this
                        com.medscape.android.util.MedscapeException r0 = r0.mNoNetworkException
                        r0.dismissSnackBar()
                        com.medscape.android.activity.search.SearchActivity r0 = com.medscape.android.activity.search.SearchActivity.this
                        r1 = 0
                        com.medscape.android.util.MedscapeException unused = r0.mNoNetworkException = r1
                    L_0x0017:
                        com.medscape.android.activity.search.SearchActivity r0 = com.medscape.android.activity.search.SearchActivity.this
                        android.widget.ProgressBar r0 = r0.progress
                        r1 = 8
                        r0.setVisibility(r1)
                        if (r12 != 0) goto L_0x0034
                        com.medscape.android.activity.search.SearchActivity r12 = com.medscape.android.activity.search.SearchActivity.this
                        com.medscape.android.interfaces.RefreshableAdapter r12 = r12.mAdapter
                        int r12 = r12.getCount()
                        if (r12 != 0) goto L_0x0033
                        r11.showNoResults()
                    L_0x0033:
                        return
                    L_0x0034:
                        r0 = 0
                        java.lang.String r1 = "search_results"
                        org.json.JSONArray r1 = r12.optJSONArray(r1)     // Catch:{ Exception -> 0x00b2 }
                        java.lang.String r2 = "Spelling"
                        java.lang.String r3 = "collection"
                        java.lang.String r3 = r12.optString(r3)     // Catch:{ Exception -> 0x00b2 }
                        boolean r2 = r2.equalsIgnoreCase(r3)     // Catch:{ Exception -> 0x00b2 }
                        if (r2 != 0) goto L_0x0050
                        java.lang.String r3 = "total"
                        int r12 = r12.optInt(r3)     // Catch:{ Exception -> 0x00b2 }
                        goto L_0x0051
                    L_0x0050:
                        r12 = 0
                    L_0x0051:
                        if (r1 == 0) goto L_0x009e
                        int r3 = r1.length()     // Catch:{ Exception -> 0x00ad }
                        if (r3 <= 0) goto L_0x009e
                        if (r2 != 0) goto L_0x009e
                        r2 = 25
                        if (r12 >= r2) goto L_0x006d
                        com.medscape.android.activity.search.SearchActivity r2 = com.medscape.android.activity.search.SearchActivity.this     // Catch:{ Exception -> 0x00ad }
                        com.medscape.android.interfaces.RefreshableAdapter r2 = r2.mAdapter     // Catch:{ Exception -> 0x00ad }
                        java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Exception -> 0x00ad }
                        r3.<init>()     // Catch:{ Exception -> 0x00ad }
                        r2.refreshList(r3)     // Catch:{ Exception -> 0x00ad }
                    L_0x006d:
                        int r2 = r1.length()     // Catch:{ Exception -> 0x00ad }
                        r3 = 0
                    L_0x0072:
                        if (r3 >= r2) goto L_0x00a1
                        org.json.JSONObject r4 = r1.getJSONObject(r3)     // Catch:{ Exception -> 0x00ad }
                        java.lang.String r5 = "sp"
                        boolean r4 = r4.has(r5)     // Catch:{ Exception -> 0x00ad }
                        if (r4 != 0) goto L_0x009a
                        com.medscape.android.activity.search.SearchActivity r4 = com.medscape.android.activity.search.SearchActivity.this     // Catch:{ Exception -> 0x00ad }
                        r5 = 1
                        boolean unused = r4.didResultsReturnAtLeastOnce = r5     // Catch:{ Exception -> 0x00ad }
                        com.medscape.android.activity.search.SearchActivity r4 = com.medscape.android.activity.search.SearchActivity.this     // Catch:{ Exception -> 0x00ad }
                        com.medscape.android.interfaces.RefreshableAdapter r4 = r4.mAdapter     // Catch:{ Exception -> 0x00ad }
                        org.json.JSONObject r5 = r1.getJSONObject(r3)     // Catch:{ Exception -> 0x00ad }
                        com.medscape.android.parser.model.Article r5 = com.medscape.android.parser.model.Article.createFromJSON(r5, r0)     // Catch:{ Exception -> 0x00ad }
                        r4.addToList(r5)     // Catch:{ Exception -> 0x00ad }
                        int r3 = r3 + 1
                        goto L_0x0072
                    L_0x009a:
                        r11.showNoResults()     // Catch:{ Exception -> 0x00ad }
                        goto L_0x00a1
                    L_0x009e:
                        r11.showNoResults()     // Catch:{ Exception -> 0x00ad }
                    L_0x00a1:
                        com.medscape.android.activity.search.SearchActivity r0 = com.medscape.android.activity.search.SearchActivity.this     // Catch:{ Exception -> 0x00ad }
                        com.medscape.android.interfaces.RefreshableAdapter r0 = r0.mAdapter     // Catch:{ Exception -> 0x00ad }
                        android.widget.BaseAdapter r0 = (android.widget.BaseAdapter) r0     // Catch:{ Exception -> 0x00ad }
                        r0.notifyDataSetChanged()     // Catch:{ Exception -> 0x00ad }
                        goto L_0x00b7
                    L_0x00ad:
                        r0 = move-exception
                        r10 = r0
                        r0 = r12
                        r12 = r10
                        goto L_0x00b3
                    L_0x00b2:
                        r12 = move-exception
                    L_0x00b3:
                        r12.printStackTrace()
                        r12 = r0
                    L_0x00b7:
                        com.medscape.android.activity.search.SearchActivity r0 = com.medscape.android.activity.search.SearchActivity.this
                        com.handmark.pulltorefresh.library.PullToRefreshListView r0 = r0.mListView
                        r0.onRefreshComplete()
                        int[] r0 = com.medscape.android.activity.search.SearchActivity.AnonymousClass7.$SwitchMap$com$medscape$android$activity$search$SearchMode
                        com.medscape.android.activity.search.SearchActivity r1 = com.medscape.android.activity.search.SearchActivity.this
                        com.medscape.android.activity.search.SearchMode r1 = r1.mSearchMode
                        int r1 = r1.ordinal()
                        r0 = r0[r1]
                        r1 = 2
                        if (r0 == r1) goto L_0x00de
                        r1 = 4
                        if (r0 == r1) goto L_0x00d9
                        java.lang.String r0 = "education"
                        java.lang.String r1 = "app_education-search"
                        goto L_0x00e2
                    L_0x00d9:
                        java.lang.String r0 = "other"
                        java.lang.String r1 = "app_medline-search"
                        goto L_0x00e2
                    L_0x00de:
                        java.lang.String r0 = "news"
                        java.lang.String r1 = "app_news-search"
                    L_0x00e2:
                        r4 = r0
                        java.util.HashMap r9 = new java.util.HashMap
                        r9.<init>()
                        com.medscape.android.activity.search.SearchActivity r0 = com.medscape.android.activity.search.SearchActivity.this
                        java.lang.String r0 = r0.mQuery
                        java.lang.String r2 = "wapp.query"
                        r9.put(r2, r0)
                        java.lang.String r0 = "wapp.filter"
                        r9.put(r0, r1)
                        java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
                        java.lang.String r0 = "wapp.results"
                        r9.put(r0, r12)
                        com.medscape.android.activity.search.SearchActivity r12 = com.medscape.android.activity.search.SearchActivity.this
                        com.medscape.android.BI.omniture.OmnitureManager r2 = com.medscape.android.BI.omniture.OmnitureManager.get()
                        com.medscape.android.activity.search.SearchActivity r3 = com.medscape.android.activity.search.SearchActivity.this
                        r7 = 0
                        r8 = 0
                        java.lang.String r5 = "browse-search"
                        java.lang.String r6 = "view"
                        java.lang.String r0 = r2.trackPageView(r3, r4, r5, r6, r7, r8, r9)
                        r12.mPvid = r0
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.search.SearchActivity.AnonymousClass3.onSearchComplete(org.json.JSONObject):void");
                }

                public void onNoInternet() {
                    SearchActivity.this.progress.setVisibility(8);
                    String string = SearchActivity.this.getResources().getString(R.string.retry);
                    MedscapeException unused = SearchActivity.this.mNoNetworkException = new MedscapeException(SearchActivity.this.getResources().getString(R.string.error_connection_required));
                    SearchActivity.this.mNoNetworkException.showSnackBar(SearchActivity.this.mRoot, -2, string, new View.OnClickListener() {
                        public void onClick(View view) {
                            SearchActivity.this.performLocalOrExternalSearch();
                        }
                    });
                }

                private void showNoResults() {
                    if (SearchActivity.this.mNoNetworkException != null) {
                        SearchActivity.this.mNoNetworkException.dismissSnackBar();
                        MedscapeException unused = SearchActivity.this.mNoNetworkException = null;
                    }
                    if (!SearchActivity.this.didResultsReturnAtLeastOnce) {
                        Article article = new Article();
                        article.mCategory = "spc";
                        article.mTitle = SearchActivity.this.getResources().getString(R.string.no_results, new Object[]{SearchActivity.this.mQuery});
                        SearchActivity.this.mAdapter.refreshList(new ArrayList());
                        SearchActivity.this.mAdapter.addToList(article);
                    }
                    SearchActivity.this.mListView.onRefreshComplete();
                    SearchActivity.this.mListView.setMode(PullToRefreshBase.Mode.DISABLED);
                    SearchActivity.this.progress.setVisibility(8);
                }
            }).execute(new Void[0]);
            this.progress.setVisibility(0);
            return;
        }
        if (this.mAdapter == null) {
            PullToRefreshListView pullToRefreshListView2 = this.mListView;
            SearchResultsListAdapter searchResultsListAdapter = new SearchResultsListAdapter(this, new ArrayList());
            this.mAdapter = searchResultsListAdapter;
            pullToRefreshListView2.setAdapter(searchResultsListAdapter);
        }
        ReferenceItemClickListener referenceItemClickListener = new ReferenceItemClickListener((Activity) this);
        this.mItemClickListener = referenceItemClickListener;
        if (this.mIsFormulary) {
            referenceItemClickListener.setFormularyListener(this);
        }
        if (this.mIsRx) {
            this.mItemClickListener.setRxListener(this);
        }
        this.mListView.setOnItemClickListener(this.mItemClickListener);
        SearchLocalTask searchLocalTask = new SearchLocalTask(this, this.mSearchMode, 25, this.mAdapter.getCount(), new SearchLocalTask.SearchLocalCompleteListener() {
            public void onSearchComplete(List<CRData> list) {
                SearchActivity.this.progress.setVisibility(8);
                for (CRData next : list) {
                    if (!SearchHelper.TYPE_DRUG_CLASS.equals(next.getType()) || (!SearchActivity.this.mIsRx && !SearchActivity.this.mIsFormulary)) {
                        SearchActivity.this.mAdapter.addToList(next);
                    }
                }
                ((BaseAdapter) SearchActivity.this.mAdapter).notifyDataSetChanged();
                SearchActivity.this.mListView.onRefreshComplete();
                HashMap hashMap = new HashMap();
                hashMap.put("wapp.query", SearchActivity.this.mQuery);
                hashMap.put("wapp.filter", "app_reference-search");
                hashMap.put("wapp.results", Integer.valueOf(SearchActivity.this.mAdapter != null ? SearchActivity.this.mAdapter.getCount() : 0));
                if (SearchActivity.this.mSearchMode == SearchMode.SEARCH_CALCULATORS) {
                    OmnitureManager.get().markModule("search-btn", "ref", (Map<String, Object>) null);
                    SearchActivity.this.mPvid = OmnitureManager.get().trackPageView(SearchActivity.this, Constants.OMNITURE_CHANNEL_REFERENCE, "calcs/search", OmnitureConstants.PAGE_NAME_RESULTS, (String) null, (String) null, hashMap);
                } else if (SearchActivity.this.mSearchMode == SearchMode.SEARCH_DRUGS) {
                    OmnitureManager.get().markModule("search-btn", "drgs", (Map<String, Object>) null);
                    SearchActivity.this.mPvid = OmnitureManager.get().trackPageView(SearchActivity.this, Constants.OMNITURE_CHANNEL_REFERENCE, "drugs/search", OmnitureConstants.PAGE_NAME_RESULTS, (String) null, (String) null, hashMap);
                } else {
                    SearchActivity.this.mPvid = OmnitureManager.get().trackPageView(SearchActivity.this, Constants.OMNITURE_CHANNEL_REFERENCE, "browse-search", "view", (String) null, (String) null, hashMap);
                }
            }

            public void onNoSearchResults() {
                SearchActivity.this.progress.setVisibility(8);
                if (SearchActivity.this.mAdapter.isEmpty()) {
                    ArrayList arrayList = new ArrayList();
                    CRData cRData = new CRData();
                    cRData.setTitle(SearchActivity.this.getResources().getString(R.string.text_view_search_result_not_found_text));
                    cRData.setType("NR");
                    arrayList.add(cRData);
                    SearchActivity.this.mAdapter.refreshList(arrayList);
                    SearchActivity.this.mListView.setMode(PullToRefreshBase.Mode.DISABLED);
                    ((BaseAdapter) SearchActivity.this.mAdapter).notifyDataSetChanged();
                }
                SearchActivity.this.mListView.onRefreshComplete();
            }
        });
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(60, 80, (long) 10, TimeUnit.SECONDS, new LinkedBlockingQueue(80));
        if (Build.VERSION.SDK_INT >= 11) {
            searchLocalTask.executeOnExecutor(threadPoolExecutor, new String[]{this.mQuery});
        } else {
            searchLocalTask.execute(new String[]{this.mQuery});
        }
        this.progress.setVisibility(0);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        finish();
        if (!this.mIsFormulary) {
            return true;
        }
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        return true;
    }

    public void onBackPressed() {
        super.onBackPressed();
        if (this.mIsFormulary) {
            overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }
    }

    public void openFormulary(final int i) {
        this.mItemClickListener.setClickability(false);
        if (!Util.isOnline(this)) {
            MedscapeException medscapeException = new MedscapeException(getResources().getString(R.string.error_connection_required));
            this.mNoNetworkException = medscapeException;
            medscapeException.showSnackBar(this.mRoot, -2, getResources().getString(R.string.retry), new View.OnClickListener() {
                public void onClick(View view) {
                    SearchActivity.this.openFormulary(i);
                    SearchActivity.this.mNoNetworkException.dismissSnackBar();
                }
            });
            this.mItemClickListener.setClickability(true);
            return;
        }
        FormularyFinder formularyFinder = new FormularyFinder(this);
        this.mFormularyFinder = formularyFinder;
        formularyFinder.setCallBack(new FormularyFinder.Callbacks() {
            public void onFormularyDownloaded(boolean z) {
                if (!z || SearchActivity.this.mFormularyFinder.getBrandModelList() == null || SearchActivity.this.mFormularyFinder.getBrandModelList().size() <= 0) {
                    Toast.makeText(OldConstants.mContext, OldConstants.mContext.getString(R.string.no_formulary_available), 0).show();
                    if (SearchActivity.this.mNoNetworkException != null) {
                        SearchActivity.this.mNoNetworkException.dismissSnackBar();
                    }
                } else {
                    SearchActivity.this.showFormulary(i);
                }
                SearchActivity.this.mItemClickListener.setClickability(true);
            }
        });
        this.mFormularyFinder.checkForFormularies(i);
    }

    /* access modifiers changed from: private */
    public void showFormulary(int i) {
        BrandModels brandModels = new BrandModels();
        brandModels.setBrandModels(this.mFormularyFinder.getBrandModelList());
        Bundle bundle = new Bundle();
        bundle.putInt(FormularyMyPlanPage.CONTENT_ID, i);
        bundle.putString("TITLE", getHeaderName(i));
        bundle.putSerializable("BRAND_MODELS", brandModels);
        Intent intent = new Intent(this, SelectBrandActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_none);
    }

    private String getHeaderName(int i) {
        try {
            return DrugMonographParser.parse(i).getHeader().getGc();
        } catch (Exception e) {
            Log.w(this.TAG, "getHeaderName: failed to get header", e);
            return null;
        }
    }

    public void openRxPrescription(String str, String str2, String str3) {
        if (ViewHelper.findById((Activity) this, 16908301) != null) {
            ViewHelper.findById((Activity) this, 16908301).setVisibility(0);
        }
        RxLauncher.Companion.launchRxDrug(str, str2, str3, this);
    }
}
