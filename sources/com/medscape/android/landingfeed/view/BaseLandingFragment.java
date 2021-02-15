package com.medscape.android.landingfeed.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.ib.clickstream.Impression;
import com.medscape.android.R;
import com.medscape.android.analytics.ClickStreamManager;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.db.DatabaseHelper;
import com.medscape.android.db.FeedMaster;
import com.medscape.android.homescreen.views.HomeScreenActivity;
import com.medscape.android.landingfeed.model.ActiveTime;
import com.medscape.android.landingfeed.model.FeedDataItem;
import com.medscape.android.landingfeed.repository.NetworkState;
import com.medscape.android.landingfeed.repository.Status;
import com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel;
import com.medscape.android.landingfeed.views.FeedPagedListAdapter;
import com.wbmd.wbmdcommons.utils.ChronicleIDUtil;
import com.webmd.wbmdcmepulse.live_events.model.LiveEventItem;
import com.webmd.wbmdcmepulse.live_events.util.LiveEventsCacheManager;
import com.webmd.wbmdcmepulse.live_events.util.LiveEventsLoadFinish;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010&\u001a\u00020'H\u0005J\b\u0010(\u001a\u00020\u0013H\u0002J\b\u0010)\u001a\u00020'H\u0002J\b\u0010*\u001a\u00020'H\u0002J\u0010\u0010+\u001a\u00020'2\u0006\u0010,\u001a\u00020\u001fH\u0002J\u000e\u0010-\u001a\u00020'2\u0006\u0010,\u001a\u00020\u001fJ\u0012\u0010.\u001a\u00020'2\b\u0010/\u001a\u0004\u0018\u000100H\u0016J\u0012\u00101\u001a\u00020'2\b\u0010/\u001a\u0004\u0018\u000100H\u0016J&\u00102\u001a\u0004\u0018\u00010!2\u0006\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u0001062\b\u0010/\u001a\u0004\u0018\u000100H\u0016J \u00107\u001a\u00020'2\u0016\u00108\u001a\u0012\u0012\u0004\u0012\u00020:09j\b\u0012\u0004\u0012\u00020:`;H\u0016J\b\u0010<\u001a\u00020'H\u0016J\b\u0010=\u001a\u00020'H\u0016J\u000e\u0010>\u001a\u00020'2\u0006\u0010,\u001a\u00020\u001fJ\b\u0010?\u001a\u00020'H\u0002J\u0012\u0010@\u001a\u00020'2\b\u0010A\u001a\u0004\u0018\u00010BH\u0002J\u0010\u0010C\u001a\u00020\u001c2\u0006\u0010D\u001a\u00020\u001cH\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R*\u0010\u001a\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001c0\u001bj\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001c`\u001dX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u00020!X.¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%¨\u0006E"}, d2 = {"Lcom/medscape/android/landingfeed/view/BaseLandingFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/webmd/wbmdcmepulse/live_events/util/LiveEventsLoadFinish;", "()V", "feedType", "", "getFeedType", "()I", "setFeedType", "(I)V", "feedView", "Landroidx/recyclerview/widget/RecyclerView;", "getFeedView", "()Landroidx/recyclerview/widget/RecyclerView;", "setFeedView", "(Landroidx/recyclerview/widget/RecyclerView;)V", "feedViewModel", "Lcom/medscape/android/landingfeed/viewmodel/LandingFeedViewModel;", "forceRefresh", "", "isScrollTop", "linearLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "liveEventsCacheManager", "Lcom/webmd/wbmdcmepulse/live_events/util/LiveEventsCacheManager;", "liveEventsLoaded", "mActiveTimeMap", "Ljava/util/HashMap;", "Lcom/medscape/android/landingfeed/model/ActiveTime;", "Lkotlin/collections/HashMap;", "mContext", "Landroidx/fragment/app/FragmentActivity;", "rootView", "Landroid/view/View;", "getRootView", "()Landroid/view/View;", "setRootView", "(Landroid/view/View;)V", "addItemsToActiveTimeMap", "", "checkCurrentSpeciality", "initAdapter", "initSwipeToRefresh", "loadCMEFeeds", "activity", "loadFeed", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreate", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onLiveEventsLoaded", "liveEvents", "Ljava/util/ArrayList;", "Lcom/webmd/wbmdcmepulse/live_events/model/LiveEventItem;", "Lkotlin/collections/ArrayList;", "onPause", "onResume", "oneTimeLoadFeed", "sendPageView", "setShimmerEffect", "networkState", "Lcom/medscape/android/landingfeed/repository/NetworkState;", "updateActiveItemsTime", "item", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: BaseLandingFragment.kt */
public abstract class BaseLandingFragment extends Fragment implements LiveEventsLoadFinish {
    private HashMap _$_findViewCache;
    private int feedType = 1;
    private RecyclerView feedView;
    /* access modifiers changed from: private */
    public LandingFeedViewModel feedViewModel;
    /* access modifiers changed from: private */
    public boolean forceRefresh;
    /* access modifiers changed from: private */
    public boolean isScrollTop = true;
    private LinearLayoutManager linearLayoutManager;
    /* access modifiers changed from: private */
    public LiveEventsCacheManager liveEventsCacheManager = new LiveEventsCacheManager();
    private boolean liveEventsLoaded;
    /* access modifiers changed from: private */
    public HashMap<Integer, ActiveTime> mActiveTimeMap = new HashMap<>();
    private FragmentActivity mContext;
    public View rootView;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final View getRootView() {
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        return view;
    }

    public final void setRootView(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.rootView = view;
    }

    public final int getFeedType() {
        return this.feedType;
    }

    public final void setFeedType(int i) {
        this.feedType = i;
    }

    public final RecyclerView getFeedView() {
        return this.feedView;
    }

    public final void setFeedView(RecyclerView recyclerView) {
        this.feedView = recyclerView;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        this.mContext = activity;
        if (activity != null) {
            LandingFeedViewModel landingFeedViewModel = (LandingFeedViewModel) ViewModelProviders.of(activity).get(LandingFeedViewModel.class);
            this.feedViewModel = landingFeedViewModel;
            if (landingFeedViewModel != null) {
                landingFeedViewModel.setIgnoreFirstError(activity instanceof HomeScreenActivity);
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.feed_fragment, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflater.inflate(R.layou…agment, container, false)");
        this.rootView = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        this.feedView = (RecyclerView) inflate.findViewById(R.id.landing_feed_view);
        LandingFeedViewModel landingFeedViewModel = this.feedViewModel;
        if (landingFeedViewModel != null) {
            landingFeedViewModel.setOnLiveEventsFinished(this);
        }
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        return view;
    }

    public void onResume() {
        super.onResume();
        this.mActiveTimeMap = new HashMap<>();
        LandingFeedViewModel landingFeedViewModel = this.feedViewModel;
        Boolean valueOf = landingFeedViewModel != null ? Boolean.valueOf(landingFeedViewModel.onResume(getActivity())) : null;
        if (valueOf != null && valueOf.booleanValue()) {
            RecyclerView recyclerView = this.feedView;
            if (recyclerView != null) {
                recyclerView.scrollToPosition(0);
            }
            LandingFeedViewModel landingFeedViewModel2 = this.feedViewModel;
            if (landingFeedViewModel2 != null) {
                landingFeedViewModel2.setMPvid("");
            }
        }
        sendPageView();
        addItemsToActiveTimeMap();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        LandingFeedViewModel landingFeedViewModel = this.feedViewModel;
        if (landingFeedViewModel != null) {
            landingFeedViewModel.resetLastPause();
        }
        LandingFeedViewModel landingFeedViewModel2 = this.feedViewModel;
        if (landingFeedViewModel2 != null) {
            landingFeedViewModel2.setCurrentSpeciality(getActivity());
        }
        ((ShimmerFrameLayout) _$_findCachedViewById(R.id.shimmerLayout)).startShimmer();
        if (this.feedType == 3) {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                Intrinsics.checkNotNullExpressionValue(activity, "it");
                loadCMEFeeds(activity);
            }
            this.liveEventsLoaded = true;
        }
        initAdapter();
    }

    private final void initAdapter() {
        RecyclerView recyclerView;
        LandingFeedViewModel landingFeedViewModel;
        FeedPagedListAdapter feedAdapter;
        LandingFeedViewModel landingFeedViewModel2;
        FragmentActivity fragmentActivity = this.mContext;
        if (!(fragmentActivity == null || (landingFeedViewModel2 = this.feedViewModel) == null)) {
            landingFeedViewModel2.setFeedAdapter(new FeedPagedListAdapter(fragmentActivity));
        }
        RecyclerView recyclerView2 = this.feedView;
        if (recyclerView2 != null) {
            recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
            LandingFeedViewModel landingFeedViewModel3 = this.feedViewModel;
            recyclerView2.setAdapter(landingFeedViewModel3 != null ? landingFeedViewModel3.getFeedAdapter() : null);
            RecyclerView.LayoutManager layoutManager = recyclerView2.getLayoutManager();
            if (layoutManager != null) {
                this.linearLayoutManager = (LinearLayoutManager) layoutManager;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
            }
        }
        LandingFeedViewModel landingFeedViewModel4 = this.feedViewModel;
        if (!(landingFeedViewModel4 == null || (feedAdapter = landingFeedViewModel4.getFeedAdapter()) == null)) {
            feedAdapter.submitList((PagedList) null);
        }
        FragmentActivity activity = getActivity();
        if (!(activity == null || (recyclerView = this.feedView) == null || (landingFeedViewModel = this.feedViewModel) == null)) {
            Intrinsics.checkNotNullExpressionValue(activity, "it");
            landingFeedViewModel.attachOmniturePaginationHandler(activity, recyclerView);
        }
        RecyclerView recyclerView3 = this.feedView;
        if (recyclerView3 != null) {
            recyclerView3.addOnScrollListener(new BaseLandingFragment$initAdapter$4(this));
        }
    }

    public final void loadFeed(FragmentActivity fragmentActivity) {
        LiveData<PagedList<FeedDataItem>> feedPostItems;
        LiveData<NetworkState> refreshState;
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        LandingFeedViewModel landingFeedViewModel = this.feedViewModel;
        if (landingFeedViewModel != null) {
            landingFeedViewModel.loadFeed(fragmentActivity, this.feedType);
        }
        LandingFeedViewModel landingFeedViewModel2 = this.feedViewModel;
        if (!(landingFeedViewModel2 == null || (refreshState = landingFeedViewModel2.getRefreshState()) == null)) {
            refreshState.removeObservers(this);
        }
        LandingFeedViewModel landingFeedViewModel3 = this.feedViewModel;
        if (!(landingFeedViewModel3 == null || (feedPostItems = landingFeedViewModel3.getFeedPostItems()) == null)) {
            feedPostItems.observe(this, new BaseLandingFragment$loadFeed$1(this));
        }
        LandingFeedViewModel landingFeedViewModel4 = this.feedViewModel;
        if (landingFeedViewModel4 != null) {
            landingFeedViewModel4.getNetworkState().observe(this, new BaseLandingFragment$loadFeed$$inlined$let$lambda$1(landingFeedViewModel4, this, fragmentActivity));
        }
    }

    /* access modifiers changed from: private */
    public final void sendPageView() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            LandingFeedViewModel landingFeedViewModel = this.feedViewModel;
            if (landingFeedViewModel != null) {
                Intrinsics.checkNotNullExpressionValue(activity, "it");
                landingFeedViewModel.sendScreenViewPing(activity);
            }
            if (activity instanceof BaseActivity) {
                BaseActivity baseActivity = (BaseActivity) activity;
                LandingFeedViewModel landingFeedViewModel2 = this.feedViewModel;
                baseActivity.mPvid = landingFeedViewModel2 != null ? landingFeedViewModel2.getMPvid() : null;
            }
        }
    }

    /* access modifiers changed from: private */
    public final void setShimmerEffect(NetworkState networkState) {
        ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) _$_findCachedViewById(R.id.shimmerLayout);
        Intrinsics.checkNotNullExpressionValue(shimmerFrameLayout, "shimmerLayout");
        if (shimmerFrameLayout.getVisibility() == 0 && networkState != null) {
            if (networkState.getStatus() == Status.SUCCESS || networkState.getStatus() == Status.FAILED) {
                new Handler().postDelayed(new BaseLandingFragment$setShimmerEffect$$inlined$let$lambda$1(this), 300);
            } else {
                ((ShimmerFrameLayout) _$_findCachedViewById(R.id.shimmerLayout)).startShimmer();
            }
            if (networkState.getStatus() == Status.SUCCESS) {
                ShimmerFrameLayout shimmerFrameLayout2 = (ShimmerFrameLayout) _$_findCachedViewById(R.id.shimmerLayout);
                Intrinsics.checkNotNullExpressionValue(shimmerFrameLayout2, "shimmerLayout");
                shimmerFrameLayout2.setVisibility(8);
                SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) _$_findCachedViewById(R.id.swipe_refresh);
                Intrinsics.checkNotNullExpressionValue(swipeRefreshLayout, "swipe_refresh");
                swipeRefreshLayout.setVisibility(0);
            }
        }
    }

    public final void oneTimeLoadFeed(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        if (this.feedType != 3) {
            LandingFeedViewModel landingFeedViewModel = this.feedViewModel;
            if (landingFeedViewModel != null && !landingFeedViewModel.getItemsLoaded()) {
                loadFeed(fragmentActivity);
            }
        } else if (checkCurrentSpeciality()) {
            loadCMEFeeds(fragmentActivity);
        } else if (this.liveEventsLoaded) {
            this.liveEventsLoaded = false;
        } else {
            LandingFeedViewModel landingFeedViewModel2 = this.feedViewModel;
            if (landingFeedViewModel2 != null && !landingFeedViewModel2.getItemsLoaded()) {
                loadFeed(fragmentActivity);
            }
        }
    }

    private final boolean checkCurrentSpeciality() {
        LandingFeedViewModel landingFeedViewModel = this.feedViewModel;
        String str = null;
        String currentSpeciality = landingFeedViewModel != null ? landingFeedViewModel.getCurrentSpeciality(getContext()) : null;
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        String[] strArr = new String[2];
        strArr[0] = currentSpeciality;
        strArr[1] = this.feedType == 2 ? ExifInterface.GPS_MEASUREMENT_2D : AppEventsConstants.EVENT_PARAM_VALUE_YES;
        String specialtyNameOrDefaultBySpecialtyId = FeedMaster.getSpecialtyNameOrDefaultBySpecialtyId(databaseHelper, strArr);
        LandingFeedViewModel landingFeedViewModel2 = this.feedViewModel;
        if (landingFeedViewModel2 != null) {
            str = landingFeedViewModel2.getCurrentSpecialityName();
        }
        return !Intrinsics.areEqual((Object) specialtyNameOrDefaultBySpecialtyId, (Object) str);
    }

    /* access modifiers changed from: private */
    public final void loadCMEFeeds(FragmentActivity fragmentActivity) {
        LandingFeedViewModel landingFeedViewModel = this.feedViewModel;
        if (landingFeedViewModel != null) {
            landingFeedViewModel.loadLiveEvents(fragmentActivity);
        }
        loadFeed(fragmentActivity);
    }

    /* access modifiers changed from: private */
    public final void initSwipeToRefresh() {
        LiveData<NetworkState> refreshState;
        LandingFeedViewModel landingFeedViewModel = this.feedViewModel;
        if (!(landingFeedViewModel == null || (refreshState = landingFeedViewModel.getRefreshState()) == null)) {
            refreshState.observe(this, new BaseLandingFragment$initSwipeToRefresh$1(this));
        }
        ((SwipeRefreshLayout) _$_findCachedViewById(R.id.swipe_refresh)).setOnRefreshListener(new BaseLandingFragment$initSwipeToRefresh$2(this));
    }

    public void onLiveEventsLoaded(ArrayList<LiveEventItem> arrayList) {
        LandingFeedViewModel landingFeedViewModel;
        FeedPagedListAdapter feedAdapter;
        Intrinsics.checkNotNullParameter(arrayList, "liveEvents");
        LandingFeedViewModel landingFeedViewModel2 = this.feedViewModel;
        if (landingFeedViewModel2 != null) {
            landingFeedViewModel2.setLiveEventsLoaded(true);
        }
        this.liveEventsCacheManager.setLiveEvents(arrayList);
        LandingFeedViewModel landingFeedViewModel3 = this.feedViewModel;
        if (landingFeedViewModel3 != null && landingFeedViewModel3.getItemsLoaded() && (landingFeedViewModel = this.feedViewModel) != null && (feedAdapter = landingFeedViewModel.getFeedAdapter()) != null) {
            feedAdapter.addLiveEvents(arrayList);
        }
    }

    public void onPause() {
        String str;
        super.onPause();
        LandingFeedViewModel landingFeedViewModel = this.feedViewModel;
        if (landingFeedViewModel != null) {
            landingFeedViewModel.onPause();
        }
        try {
            if (!this.mActiveTimeMap.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                Iterator it = this.mActiveTimeMap.entrySet().iterator();
                while (true) {
                    str = null;
                    if (!it.hasNext()) {
                        break;
                    }
                    Map.Entry entry = (Map.Entry) it.next();
                    if (((ActiveTime) entry.getValue()).getActive()) {
                        ((ActiveTime) entry.getValue()).setMilliseconds(((ActiveTime) entry.getValue()).getMilliseconds() + (System.currentTimeMillis() - ((ActiveTime) entry.getValue()).getCurrentTime()));
                        ((ActiveTime) entry.getValue()).setActive(false);
                    }
                    int i = this.feedType;
                    if (i == 1) {
                        str = "home_screen_cards";
                    } else if (i == 2) {
                        str = "news_screen_cards";
                    } else if (i == 3) {
                        str = "cme_screen_cards";
                    }
                    arrayList.add(new Impression(((ActiveTime) entry.getValue()).getItemId(), String.valueOf(((ActiveTime) entry.getValue()).getMilliseconds()), str, ((ActiveTime) entry.getValue()).getContentType()));
                }
                this.mActiveTimeMap.clear();
                Context context = getContext();
                if (context != null) {
                    ChronicleIDUtil chronicleIDUtil = new ChronicleIDUtil();
                    LandingFeedViewModel landingFeedViewModel2 = this.feedViewModel;
                    String generateAssetId = chronicleIDUtil.generateAssetId("", "", landingFeedViewModel2 != null ? landingFeedViewModel2.getOmniturePage() : null);
                    ClickStreamManager clickStreamManager = ClickStreamManager.INSTANCE;
                    Intrinsics.checkNotNullExpressionValue(context, "it");
                    Object[] array = arrayList.toArray(new Impression[0]);
                    if (array != null) {
                        Impression[] impressionArr = (Impression[]) array;
                        LandingFeedViewModel landingFeedViewModel3 = this.feedViewModel;
                        if (landingFeedViewModel3 != null) {
                            str = landingFeedViewModel3.getMPvid();
                        }
                        clickStreamManager.sendEvent(context, impressionArr, (String) null, (HashMap<String, String>) null, (String[]) null, str, generateAssetId);
                        return;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                }
            }
        } catch (Throwable th) {
            FirebaseCrashlytics.getInstance().recordException(th);
            th.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0134, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x007b A[Catch:{ all -> 0x0135 }] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0056 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void addItemsToActiveTimeMap() {
        /*
            r16 = this;
            r1 = r16
            monitor-enter(r16)
            androidx.recyclerview.widget.LinearLayoutManager r0 = r1.linearLayoutManager     // Catch:{ all -> 0x0135 }
            if (r0 == 0) goto L_0x0140
            androidx.recyclerview.widget.LinearLayoutManager r0 = r1.linearLayoutManager     // Catch:{ all -> 0x0135 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch:{ all -> 0x0135 }
            int r0 = r0.findFirstVisibleItemPosition()     // Catch:{ all -> 0x0135 }
            androidx.recyclerview.widget.LinearLayoutManager r2 = r1.linearLayoutManager     // Catch:{ all -> 0x0135 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)     // Catch:{ all -> 0x0135 }
            int r2 = r2.findLastVisibleItemPosition()     // Catch:{ all -> 0x0135 }
            r3 = -1
            if (r0 == r3) goto L_0x0133
            if (r2 != r3) goto L_0x0020
            goto L_0x0133
        L_0x0020:
            r3 = 1
            if (r2 <= 0) goto L_0x004a
            if (r2 <= r0) goto L_0x004a
            int r4 = r1.feedType     // Catch:{ all -> 0x0135 }
            if (r4 != r3) goto L_0x004a
            androidx.fragment.app.FragmentActivity r4 = r16.getActivity()     // Catch:{ all -> 0x0135 }
            if (r4 == 0) goto L_0x004a
            androidx.fragment.app.FragmentActivity r4 = r16.getActivity()     // Catch:{ all -> 0x0135 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)     // Catch:{ all -> 0x0135 }
            androidx.lifecycle.ViewModelProvider r4 = androidx.lifecycle.ViewModelProviders.of((androidx.fragment.app.FragmentActivity) r4)     // Catch:{ all -> 0x0135 }
            java.lang.Class<com.medscape.android.homescreen.viewmodel.HomeScreenViewModel> r5 = com.medscape.android.homescreen.viewmodel.HomeScreenViewModel.class
            androidx.lifecycle.ViewModel r4 = r4.get(r5)     // Catch:{ all -> 0x0135 }
            com.medscape.android.homescreen.viewmodel.HomeScreenViewModel r4 = (com.medscape.android.homescreen.viewmodel.HomeScreenViewModel) r4     // Catch:{ all -> 0x0135 }
            boolean r4 = r4.isExpanded()     // Catch:{ all -> 0x0135 }
            if (r4 == 0) goto L_0x004a
            int r2 = r2 + -1
        L_0x004a:
            java.util.HashMap<java.lang.Integer, com.medscape.android.landingfeed.model.ActiveTime> r4 = r1.mActiveTimeMap     // Catch:{ all -> 0x0135 }
            java.util.Map r4 = (java.util.Map) r4     // Catch:{ all -> 0x0135 }
            java.util.Set r4 = r4.entrySet()     // Catch:{ all -> 0x0135 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x0135 }
        L_0x0056:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x0135 }
            if (r5 == 0) goto L_0x0091
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x0135 }
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5     // Catch:{ all -> 0x0135 }
            if (r0 > r2) goto L_0x0078
            r6 = r0
        L_0x0065:
            java.lang.Object r7 = r5.getKey()     // Catch:{ all -> 0x0135 }
            java.lang.Number r7 = (java.lang.Number) r7     // Catch:{ all -> 0x0135 }
            int r7 = r7.intValue()     // Catch:{ all -> 0x0135 }
            if (r7 != r6) goto L_0x0073
            r6 = 0
            goto L_0x0079
        L_0x0073:
            if (r6 == r2) goto L_0x0078
            int r6 = r6 + 1
            goto L_0x0065
        L_0x0078:
            r6 = 1
        L_0x0079:
            if (r6 == 0) goto L_0x0056
            java.util.HashMap<java.lang.Integer, com.medscape.android.landingfeed.model.ActiveTime> r6 = r1.mActiveTimeMap     // Catch:{ all -> 0x0135 }
            java.util.Map r6 = (java.util.Map) r6     // Catch:{ all -> 0x0135 }
            java.lang.Object r7 = r5.getKey()     // Catch:{ all -> 0x0135 }
            java.lang.Object r5 = r5.getValue()     // Catch:{ all -> 0x0135 }
            com.medscape.android.landingfeed.model.ActiveTime r5 = (com.medscape.android.landingfeed.model.ActiveTime) r5     // Catch:{ all -> 0x0135 }
            com.medscape.android.landingfeed.model.ActiveTime r5 = r1.updateActiveItemsTime(r5)     // Catch:{ all -> 0x0135 }
            r6.put(r7, r5)     // Catch:{ all -> 0x0135 }
            goto L_0x0056
        L_0x0091:
            com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel r4 = r1.feedViewModel     // Catch:{ all -> 0x0135 }
            if (r4 == 0) goto L_0x0140
            androidx.lifecycle.LiveData r4 = r4.getFeedPostItems()     // Catch:{ all -> 0x0135 }
            if (r4 == 0) goto L_0x0140
            java.lang.Object r4 = r4.getValue()     // Catch:{ all -> 0x0135 }
            androidx.paging.PagedList r4 = (androidx.paging.PagedList) r4     // Catch:{ all -> 0x0135 }
            if (r4 == 0) goto L_0x0140
            if (r0 > r2) goto L_0x0140
        L_0x00a5:
            int r5 = r4.size()     // Catch:{ all -> 0x0135 }
            if (r0 < r5) goto L_0x00ad
            monitor-exit(r16)
            return
        L_0x00ad:
            java.util.HashMap<java.lang.Integer, com.medscape.android.landingfeed.model.ActiveTime> r5 = r1.mActiveTimeMap     // Catch:{ all -> 0x0135 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x0135 }
            boolean r5 = r5.containsKey(r6)     // Catch:{ all -> 0x0135 }
            if (r5 != 0) goto L_0x0100
            java.lang.Object r5 = r4.get(r0)     // Catch:{ all -> 0x0135 }
            com.medscape.android.landingfeed.model.FeedDataItem r5 = (com.medscape.android.landingfeed.model.FeedDataItem) r5     // Catch:{ all -> 0x0135 }
            if (r5 == 0) goto L_0x00fe
            java.lang.String r7 = r5.getContentId()     // Catch:{ all -> 0x0135 }
            if (r7 == 0) goto L_0x00fe
            java.util.HashMap<java.lang.Integer, com.medscape.android.landingfeed.model.ActiveTime> r6 = r1.mActiveTimeMap     // Catch:{ all -> 0x0135 }
            r14 = r6
            java.util.Map r14 = (java.util.Map) r14     // Catch:{ all -> 0x0135 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x0135 }
            com.medscape.android.landingfeed.model.ActiveTime r13 = new com.medscape.android.landingfeed.model.ActiveTime     // Catch:{ all -> 0x0135 }
            java.lang.String r5 = r5.getType()     // Catch:{ all -> 0x0135 }
            if (r5 == 0) goto L_0x00ec
            if (r5 == 0) goto L_0x00e4
            java.lang.String r5 = r5.toLowerCase()     // Catch:{ all -> 0x0135 }
            java.lang.String r6 = "(this as java.lang.String).toLowerCase()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)     // Catch:{ all -> 0x0135 }
            goto L_0x00ed
        L_0x00e4:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ all -> 0x0135 }
            java.lang.String r2 = "null cannot be cast to non-null type java.lang.String"
            r0.<init>(r2)     // Catch:{ all -> 0x0135 }
            throw r0     // Catch:{ all -> 0x0135 }
        L_0x00ec:
            r5 = 0
        L_0x00ed:
            r8 = r5
            long r9 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0135 }
            r11 = 0
            r5 = 1
            r6 = r13
            r3 = r13
            r13 = r5
            r6.<init>(r7, r8, r9, r11, r13)     // Catch:{ all -> 0x0135 }
            r14.put(r15, r3)     // Catch:{ all -> 0x0135 }
        L_0x00fe:
            r5 = 1
            goto L_0x0124
        L_0x0100:
            java.util.HashMap<java.lang.Integer, com.medscape.android.landingfeed.model.ActiveTime> r3 = r1.mActiveTimeMap     // Catch:{ all -> 0x0135 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x0135 }
            java.lang.Object r3 = r3.get(r5)     // Catch:{ all -> 0x0135 }
            if (r3 == 0) goto L_0x012b
            com.medscape.android.landingfeed.model.ActiveTime r3 = (com.medscape.android.landingfeed.model.ActiveTime) r3     // Catch:{ all -> 0x0135 }
            r5 = 1
            r3.setActive(r5)     // Catch:{ all -> 0x0135 }
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0135 }
            r3.setCurrentTime(r6)     // Catch:{ all -> 0x0135 }
            java.util.HashMap<java.lang.Integer, com.medscape.android.landingfeed.model.ActiveTime> r6 = r1.mActiveTimeMap     // Catch:{ all -> 0x0135 }
            java.util.Map r6 = (java.util.Map) r6     // Catch:{ all -> 0x0135 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x0135 }
            r6.put(r7, r3)     // Catch:{ all -> 0x0135 }
        L_0x0124:
            if (r0 == r2) goto L_0x0140
            int r0 = r0 + 1
            r3 = 1
            goto L_0x00a5
        L_0x012b:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ all -> 0x0135 }
            java.lang.String r2 = "null cannot be cast to non-null type com.medscape.android.landingfeed.model.ActiveTime"
            r0.<init>(r2)     // Catch:{ all -> 0x0135 }
            throw r0     // Catch:{ all -> 0x0135 }
        L_0x0133:
            monitor-exit(r16)
            return
        L_0x0135:
            r0 = move-exception
            com.google.firebase.crashlytics.FirebaseCrashlytics r2 = com.google.firebase.crashlytics.FirebaseCrashlytics.getInstance()     // Catch:{ all -> 0x0142 }
            r2.recordException(r0)     // Catch:{ all -> 0x0142 }
            r0.printStackTrace()     // Catch:{ all -> 0x0142 }
        L_0x0140:
            monitor-exit(r16)
            return
        L_0x0142:
            r0 = move-exception
            monitor-exit(r16)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.landingfeed.view.BaseLandingFragment.addItemsToActiveTimeMap():void");
    }

    /* access modifiers changed from: private */
    public final ActiveTime updateActiveItemsTime(ActiveTime activeTime) {
        if (!activeTime.getActive()) {
            return activeTime;
        }
        return new ActiveTime(activeTime.getItemId(), activeTime.getContentType(), System.currentTimeMillis(), activeTime.getMilliseconds() + (System.currentTimeMillis() - activeTime.getCurrentTime()), false);
    }
}
