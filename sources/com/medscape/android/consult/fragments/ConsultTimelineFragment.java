package com.medscape.android.consult.fragments;

import android.animation.Animator;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.ads.AdRequestHelper;
import com.medscape.android.ads.OnAdListener;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.consult.activity.ConsultPostActivity;
import com.medscape.android.consult.activity.ConsultTimelineActivity;
import com.medscape.android.consult.adapters.ConsultTimelineListAdapter;
import com.medscape.android.consult.interfaces.IFeedReceivedListener;
import com.medscape.android.consult.interfaces.ILoadMoreListener;
import com.medscape.android.consult.interfaces.IMedStudentNotificationListener;
import com.medscape.android.consult.interfaces.IPostReceivedListener;
import com.medscape.android.consult.managers.ConsultDataManager;
import com.medscape.android.consult.models.ConsultFeed;
import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.consult.util.ConsultConstants;
import com.medscape.android.consult.viewmodels.ConsultSponsoredAdsViewModel;
import com.medscape.android.drugs.details.util.AdHelper;
import com.medscape.android.provider.SharedPreferenceProvider;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.StringUtil;
import com.tonicartos.superslim.LayoutManager;
import com.webmd.wbmdomnituremanager.WBMDOmniturePaginationHandler;
import com.webmd.wbmdomnituremanager.WBMDPaginationListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ConsultTimelineFragment extends Fragment implements ILoadMoreListener, IFeedReceivedListener, OnAdListener {
    private static final int CIRCULAR_REVEAL_DURATION = 100;
    static final String TAG = ConsultTimelineFragment.class.getSimpleName();
    private String consultTitle;
    /* access modifiers changed from: private */
    public ConsultTimelineListAdapter mAdapter;
    private boolean mCanLoad = false;
    private boolean mClearListForSectionChange = false;
    /* access modifiers changed from: private */
    public int mConsultFeedType = Constants.CONSULT_FEEDTYPE_ALL;
    private ConsultTimeLineChanged mConsultTimeLineChangedReceiver;
    /* access modifiers changed from: private */
    public String mCurrentTag = null;
    /* access modifiers changed from: private */
    public View mFab;
    /* access modifiers changed from: private */
    public ConsultFeed mFeed;
    private boolean mIsRevealAnimated;
    /* access modifiers changed from: private */
    public boolean mLoadOnResume = true;
    /* access modifiers changed from: private */
    public MedscapeException mMedscapeException;
    private View mNoPostsView;
    /* access modifiers changed from: private */
    public View mProgressDialog;
    public RecyclerView mRecyclerView;
    /* access modifiers changed from: private */
    public View mRevealView;
    private View mRootView;
    private boolean mShowExceptionIfFailedtoLoad = false;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private IMedStudentNotificationListener medStudentNotificationListener;
    /* access modifiers changed from: private */
    public String pvid = "";
    private ConsultSponsoredAdsViewModel sponsoredAdsViewModel;

    private String getLastPausePrefName() {
        return "pref_last_pause4";
    }

    public void isAdExpandedByUser(boolean z) {
    }

    public void onAdAvailable() {
    }

    public void onAdNotAvilable() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getActivity() != null) {
            this.sponsoredAdsViewModel = (ConsultSponsoredAdsViewModel) ViewModelProviders.of(getActivity()).get(ConsultSponsoredAdsViewModel.class);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.consult_timeline, viewGroup, false);
        this.mRootView = inflate;
        this.mProgressDialog = inflate.findViewById(R.id.progressBar);
        this.mNoPostsView = this.mRootView.findViewById(R.id.no_post_msg);
        resetLastPause();
        setRefreshLayout(this.mRootView);
        setUpRecyclerView();
        setUpFab();
        handleArguments();
        setObserver();
        return this.mRootView;
    }

    public static ConsultTimelineFragment newInstance() {
        return new ConsultTimelineFragment();
    }

    private void setObserver() {
        ConsultSponsoredAdsViewModel consultSponsoredAdsViewModel = this.sponsoredAdsViewModel;
        if (consultSponsoredAdsViewModel != null) {
            consultSponsoredAdsViewModel.getPostID().observe(this, new Observer<String>() {
                public void onChanged(String str) {
                    ConsultTimelineFragment.this.requestSponsoredPost(str);
                }
            });
        }
    }

    private void handleArguments() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string = arguments.getString(Constants.EXTRA_CONSULT_CLICKED_TAG);
            if (StringUtil.isNotEmpty(string)) {
                this.mConsultFeedType = Constants.CONSULT_FEEDTYPE_TAG;
                this.mCurrentTag = string;
                return;
            }
            this.mConsultFeedType = arguments.getInt(Constants.EXTRA_CONSULT_TIMELINE_FEED_TYPE);
            this.mCurrentTag = arguments.getString(Constants.EXTRA_CONSULT_DEEPLINK_ACTION);
        }
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) this.mRootView.findViewById(R.id.lvFeeds);
        this.mRecyclerView = recyclerView;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
            }
        });
        final LayoutManager layoutManager = new LayoutManager((Context) getActivity());
        this.mRecyclerView.setLayoutManager(layoutManager);
        this.mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                if (ConsultTimelineFragment.this.shouldLoadNextSetOfData(layoutManager, i2)) {
                    ConsultTimelineFragment.this.mAdapter.saveSponsoredPost();
                    ConsultTimelineFragment.this.loadNextSetOfData();
                }
            }
        });
        if (this.mAdapter == null) {
            this.mAdapter = new ConsultTimelineListAdapter(getActivity(), this, this.mConsultFeedType, this.medStudentNotificationListener);
        }
        this.mRecyclerView.setAdapter(this.mAdapter);
    }

    private void setUpFab() {
        View findViewById = this.mRootView.findViewById(R.id.fab);
        this.mFab = findViewById;
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ConsultTimelineFragment.this.createNewPost();
                    ConsultTimelineFragment.this.getActivity().overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.no_animation);
                }
            });
        }
    }

    private Transition enterTransition() {
        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setDuration(100);
        return changeBounds;
    }

    private Transition returnTransition() {
        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setInterpolator(new DecelerateInterpolator());
        changeBounds.setDuration(100);
        return changeBounds;
    }

    private void initializeCircularRevealLayout() {
        View findViewById = this.mRootView.findViewById(R.id.reveal_layout);
        this.mRevealView = findViewById;
        findViewById.setVisibility(4);
        this.mFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ConsultTimelineFragment.this.mFab.setClickable(false);
                ConsultTimelineFragment.this.animateCircularReveal();
                OmnitureManager.get().markModule("consult-newpost", Constants.OMNITURE_MLINK_OPEN, (Map<String, Object>) null);
                Intent intent = new Intent(ConsultTimelineFragment.this.getActivity(), ConsultPostActivity.class);
                intent.putExtra(Constants.EXTRA_CONSULT_TIMELINE_FEED_TYPE, ConsultTimelineFragment.this.mConsultFeedType);
                if (StringUtil.isNotEmpty(ConsultTimelineFragment.this.mCurrentTag)) {
                    intent.putExtra(Constants.EXTRA_CONSULT_TIMELINE_FEED_TAG, ConsultTimelineFragment.this.mCurrentTag);
                }
                ConsultTimelineFragment.this.startActivityForResult(intent, Constants.REQUEST_CODE_UPLOAD_POST_FAB, ActivityOptionsCompat.makeSceneTransitionAnimation(ConsultTimelineFragment.this.getActivity(), ConsultTimelineFragment.this.mFab, "fab_expand").toBundle());
                ConsultTimelineFragment.this.mFab.postDelayed(new Runnable() {
                    public void run() {
                        ConsultTimelineFragment.this.mFab.setClickable(true);
                        ConsultTimelineFragment.this.mRevealView.setVisibility(4);
                    }
                }, 200);
            }
        });
    }

    /* access modifiers changed from: private */
    public void animateCircularReveal() {
        Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(this.mRevealView, (int) (this.mFab.getX() + ((float) (this.mFab.getWidth() / 2))), (int) (this.mFab.getY() + ((float) (this.mFab.getHeight() / 2))), 0.0f, (float) Math.max(this.mRevealView.getWidth(), this.mRevealView.getHeight()));
        createCircularReveal.setDuration(100);
        this.mRevealView.setVisibility(0);
        createCircularReveal.start();
        this.mIsRevealAnimated = true;
    }

    private void animateCircularHide() {
        Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(this.mRevealView, (int) (this.mFab.getX() + ((float) (this.mFab.getWidth() / 2))), (int) (this.mFab.getY() + ((float) (this.mFab.getHeight() / 2))), (float) Math.max(this.mRevealView.getWidth(), this.mRevealView.getHeight()), 0.0f);
        createCircularReveal.setDuration(100);
        this.mRevealView.setVisibility(0);
        createCircularReveal.start();
        this.mIsRevealAnimated = false;
    }

    public void createNewPost() {
        OmnitureManager.get().markModule("consult-newpost", Constants.OMNITURE_MLINK_OPEN, (Map<String, Object>) null);
        Intent intent = new Intent(getActivity(), ConsultPostActivity.class);
        intent.putExtra(Constants.EXTRA_CONSULT_TIMELINE_FEED_TYPE, this.mConsultFeedType);
        if (StringUtil.isNotEmpty(this.mCurrentTag)) {
            intent.putExtra(Constants.EXTRA_CONSULT_TIMELINE_FEED_TAG, this.mCurrentTag);
        }
        getActivity().startActivityForResult(intent, Constants.REQUEST_CODE_UPLOAD_POST_FAB);
    }

    /* access modifiers changed from: private */
    public boolean shouldLoadNextSetOfData(LayoutManager layoutManager, int i) {
        ConsultFeed consultFeed;
        int childCount = layoutManager.getChildCount();
        int itemCount = layoutManager.getItemCount();
        if (itemCount > 0) {
            int findFirstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
            if (this.mCanLoad && i > 0 && (consultFeed = this.mFeed) != null && consultFeed.getFeedItems() != null) {
                return childCount + findFirstVisibleItemPosition >= itemCount && this.mFeed.getFeedItems().size() < this.mFeed.getTotalItems();
            }
        }
    }

    /* access modifiers changed from: private */
    public void loadNextSetOfData() {
        this.mCanLoad = false;
        loadFeed(false);
    }

    private void setRefreshLayout(View view) {
        this.mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.feedRefresh);
        if (isAdded() && getActivity() != null) {
            int color = ContextCompat.getColor(getActivity(), R.color.medscape_blue);
            this.mSwipeRefreshLayout.setColorSchemeColors(color, color, color, color);
        }
        this.mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                if (ConsultTimelineFragment.this.mMedscapeException != null) {
                    ConsultTimelineFragment.this.mMedscapeException.dismissSnackBar();
                }
                ConsultTimelineFragment.this.loadFeed(true);
                ConsultTimelineFragment.this.requestAd();
                String unused = ConsultTimelineFragment.this.pvid = "";
                ConsultTimelineFragment consultTimelineFragment = ConsultTimelineFragment.this;
                consultTimelineFragment.sendOmnitureForTimeline(consultTimelineFragment.mCurrentTag);
            }
        });
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        new WBMDOmniturePaginationHandler((Activity) getActivity(), this.mRecyclerView, 1.0d, (WBMDPaginationListener) new WBMDPaginationListener() {
            public void onDebugOptions(int i) {
            }

            public void sendOmniture(int i, int i2) {
                HashMap hashMap = new HashMap();
                hashMap.put("wapp.pagination", Integer.toString(i2));
                OmnitureManager omnitureManager = OmnitureManager.get();
                omnitureManager.markModule("app-swipe", i + "", hashMap);
                ConsultTimelineFragment consultTimelineFragment = ConsultTimelineFragment.this;
                consultTimelineFragment.sendOmnitureForTimeline(consultTimelineFragment.mCurrentTag);
            }
        });
    }

    public void onResume() {
        super.onResume();
        if (this.mConsultTimeLineChangedReceiver == null) {
            this.mConsultTimeLineChangedReceiver = new ConsultTimeLineChanged();
        }
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.mConsultTimeLineChangedReceiver, new IntentFilter(Constants.CONSULT_TIMELINE_CHANGED_UPDATEACTION));
        if (System.currentTimeMillis() - SharedPreferenceProvider.get().get(getLastPausePrefName(), System.currentTimeMillis()) > TimeUnit.MINUTES.toMillis(30)) {
            resetLastPause();
            this.mSwipeRefreshLayout.setRefreshing(true);
            loadFeed(true);
            this.mRecyclerView.scrollToPosition(0);
        } else if (this.mLoadOnResume) {
            requestAd();
            loadFeed(false);
        }
        if (this.mIsRevealAnimated && Build.VERSION.SDK_INT >= 21 && this.mFab != null && this.mRevealView != null) {
            animateCircularHide();
            this.mFab.postDelayed(new Runnable() {
                public void run() {
                    ConsultTimelineFragment.this.mFab.setVisibility(0);
                    ConsultTimelineFragment.this.mRevealView.setVisibility(4);
                }
            }, 90);
        }
        sendOmnitureForTimeline(this.mCurrentTag);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mRecyclerView.destroyDrawingCache();
        if (this.mConsultTimeLineChangedReceiver != null) {
            LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.mConsultTimeLineChangedReceiver);
        }
    }

    /* access modifiers changed from: private */
    public void sendOmnitureForTimeline(String str) {
        switch (this.mConsultFeedType) {
            case 3000:
                this.pvid = OmnitureManager.get().trackPageView(getActivity(), "consult", "consult", "timeline", Constants.CONSULT_MY_NETWORK, (String) null, (Map<String, Object>) null, false, this.pvid);
                break;
            case Constants.CONSULT_FEEDTYPE_FOLLOWED_POSTS:
                this.pvid = OmnitureManager.get().trackPageView(getActivity(), "consult", "consult", "timeline", "followedposts", (String) null, (Map<String, Object>) null, false, this.pvid);
                break;
            case 3002:
                this.pvid = OmnitureManager.get().trackPageView(getActivity(), "consult", "consult", "timeline", Constants.CONSULT_MY_POSTS, (String) null, (Map<String, Object>) null, false, this.pvid);
                break;
            case Constants.CONSULT_FEEDTYPE_TAG:
                this.pvid = OmnitureManager.get().trackPageView(getActivity(), "consult", "consult", "timeline", str, (String) null, (Map<String, Object>) null, false, this.pvid);
                break;
            case Constants.CONSULT_FEEDTYPE_ALL:
                this.pvid = OmnitureManager.get().trackPageView(getActivity(), "consult", "consult", "timeline", "allposts", (String) null, (Map<String, Object>) null, false, this.pvid);
                break;
            case Constants.CONSULT_FEEDTYPE_TOP_POSTS:
                this.pvid = OmnitureManager.get().trackPageView(getActivity(), "consult", "consult", Constants.CONSULT_TOP_POSTS, str, (String) null, (Map<String, Object>) null, false, this.pvid);
                break;
            case 3007:
                this.pvid = OmnitureManager.get().trackPageView(getActivity(), "consult", "consult", "timeline", "featured", (String) null, (Map<String, Object>) null, false, this.pvid);
                break;
            default:
                this.pvid = OmnitureManager.get().trackPageView(getActivity(), "consult", "consult", "timeline", "allposts", (String) null, (Map<String, Object>) null, false, this.pvid);
                break;
        }
        if (isAdded() && getActivity() != null) {
            ((BaseActivity) getActivity()).setCurrentPvid(this.pvid);
        }
    }

    public void loadFeedForNewSection(int i, String str, String str2) {
        this.mShowExceptionIfFailedtoLoad = true;
        this.mClearListForSectionChange = true;
        MedscapeException medscapeException = this.mMedscapeException;
        if (medscapeException != null) {
            medscapeException.dismissSnackBar();
        }
        this.mCurrentTag = str;
        this.mRecyclerView.setVisibility(8);
        this.mNoPostsView.setVisibility(8);
        View view = this.mProgressDialog;
        if (view != null) {
            view.setVisibility(0);
        }
        this.mFeed = null;
        this.mConsultFeedType = i;
        this.pvid = "";
        loadFeedsBasedOnType(str, str2);
        sendOmnitureForTimeline(str);
    }

    public void loadFeedForNewSection(String str) {
        this.mShowExceptionIfFailedtoLoad = true;
        this.mClearListForSectionChange = true;
        MedscapeException medscapeException = this.mMedscapeException;
        if (medscapeException != null) {
            medscapeException.dismissSnackBar();
        }
        this.mRecyclerView.setVisibility(8);
        this.mNoPostsView.setVisibility(8);
        View view = this.mProgressDialog;
        if (view != null) {
            view.setVisibility(0);
        }
        this.mFeed = null;
        loadFeedsBasedOnType(this.mCurrentTag, str);
        sendOmnitureForTimeline(this.mCurrentTag);
    }

    public void loadFeed(boolean z) {
        if (z) {
            this.mFeed = null;
        }
        loadFeedsBasedOnType(this.mCurrentTag, getActivity() != null ? ((ConsultTimelineActivity) getActivity()).getDisplayFilter() : ConsultConstants.PHYSICIAN_FEED_FILTER);
    }

    private void loadFeedsBasedOnType(String str, String str2) {
        switch (this.mConsultFeedType) {
            case 3000:
                ConsultDataManager.getInstance(getActivity(), getActivity()).getMyNetworkPostswithCurrentFeed(this.mFeed, this);
                return;
            case Constants.CONSULT_FEEDTYPE_FOLLOWED_POSTS:
                ConsultDataManager.getInstance(getActivity(), getActivity()).getFollowedPostsWithCurrentFeed(this.mFeed, this);
                return;
            case 3002:
                ConsultDataManager.getInstance(getActivity(), getActivity()).getPostswithCurrentFeedForUser(this.mFeed, this);
                return;
            case Constants.CONSULT_FEEDTYPE_TAG:
            case 3007:
                ConsultDataManager.getInstance(getActivity(), getActivity()).getPostsForTag(str, str2, this.mFeed, this.mConsultFeedType, this);
                return;
            case Constants.CONSULT_FEEDTYPE_ALL:
                ConsultDataManager.getInstance(getActivity(), getActivity()).getAllPostsWithCurrentFeed(this.mFeed, str2, this);
                return;
            case Constants.CONSULT_FEEDTYPE_TOP_POSTS:
                ConsultDataManager.getInstance(getActivity(), getActivity()).getTopPostsWithCurrentFeed(this.mFeed, this);
                return;
            default:
                this.mConsultFeedType = Constants.CONSULT_FEEDTYPE_ALL;
                ConsultDataManager.getInstance(getActivity(), getActivity()).getAllPostsWithCurrentFeed(this.mFeed, str2, this);
                return;
        }
    }

    public void setConsultTitle(String str) {
        this.consultTitle = str;
    }

    public void requestAd() {
        if (shouldRequestAd(this.mConsultFeedType, this.consultTitle)) {
            new AdRequestHelper().makeSponsoredPostADCall(getActivity(), new AdHelper().getConsultScreenMap(getActivity()), this);
            ConsultTimelineListAdapter consultTimelineListAdapter = this.mAdapter;
            if (consultTimelineListAdapter != null) {
                consultTimelineListAdapter.resetSponsoredPost();
            }
        }
    }

    public boolean shouldRequestAd(int i, String str) {
        if (str == null || !str.equals("Partners")) {
            return i == 3007 || i == 3003 || i == 3004;
        }
        return false;
    }

    public void requestSponsoredPost(String str) {
        ConsultDataManager.getInstance(getActivity(), getActivity()).getSponsoredAdPost(str, new IPostReceivedListener() {
            public void onPostRequestFailed(MedscapeException medscapeException) {
            }

            public void onPostReceived(ConsultPost consultPost) {
                if (ConsultTimelineFragment.this.mAdapter == null) {
                    return;
                }
                if (ConsultTimelineFragment.this.mSwipeRefreshLayout.isRefreshing() || ConsultTimelineFragment.this.mRecyclerView.getVisibility() != 0) {
                    ConsultTimelineFragment.this.mAdapter.mSponsoredPost = consultPost;
                    return;
                }
                ConsultTimelineFragment.this.mAdapter.addSponsoredPost(consultPost);
                ConsultTimelineFragment.this.mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void addItemsToAdapter() {
        if (this.mAdapter == null) {
            ConsultTimelineListAdapter consultTimelineListAdapter = new ConsultTimelineListAdapter(getActivity(), this, this.mConsultFeedType, this.medStudentNotificationListener);
            this.mAdapter = consultTimelineListAdapter;
            if (this.mClearListForSectionChange) {
                this.mClearListForSectionChange = false;
                this.mRecyclerView.setAdapter(consultTimelineListAdapter);
            }
        }
        View view = this.mProgressDialog;
        if (view != null) {
            view.setVisibility(8);
        }
        ConsultFeed consultFeed = this.mFeed;
        if (consultFeed == null || consultFeed.getFeedItems() == null || this.mFeed.getFeedItems().size() == 0) {
            this.mAdapter.setItemsAndRefresh(this.mFeed);
            this.mNoPostsView.setVisibility(0);
            return;
        }
        this.mAdapter.setItemsForFeed(this.mFeed);
    }

    public void onFeedReceived(ConsultFeed consultFeed, int i, String str) {
        if (this.mConsultFeedType == i) {
            this.mShowExceptionIfFailedtoLoad = false;
            SwipeRefreshLayout swipeRefreshLayout = this.mSwipeRefreshLayout;
            if (swipeRefreshLayout != null) {
                swipeRefreshLayout.setRefreshing(false);
            }
            this.mRecyclerView.setVisibility(0);
            this.mNoPostsView.setVisibility(8);
            if (consultFeed != null) {
                this.mCanLoad = true;
                this.mFeed = consultFeed;
                this.mLoadOnResume = false;
                if (this.mClearListForSectionChange) {
                    this.mAdapter = null;
                }
                addItemsToAdapter();
                return;
            }
            onFailedToReceiveFeed(new MedscapeException(getString(R.string.consult_error_request_failed)), i, str);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onFailedToReceiveFeed(com.medscape.android.util.MedscapeException r3, int r4, java.lang.String r5) {
        /*
            r2 = this;
            int r5 = r2.mConsultFeedType
            if (r5 != r4) goto L_0x00cb
            androidx.swiperefreshlayout.widget.SwipeRefreshLayout r4 = r2.mSwipeRefreshLayout
            if (r4 == 0) goto L_0x001a
            androidx.fragment.app.FragmentActivity r4 = r2.getActivity()
            if (r4 == 0) goto L_0x001a
            androidx.fragment.app.FragmentActivity r4 = r2.getActivity()
            com.medscape.android.consult.fragments.-$$Lambda$ConsultTimelineFragment$ph4BXRnNZNfDjED38wz0lu3FNZo r5 = new com.medscape.android.consult.fragments.-$$Lambda$ConsultTimelineFragment$ph4BXRnNZNfDjED38wz0lu3FNZo
            r5.<init>()
            r4.runOnUiThread(r5)
        L_0x001a:
            r2.mMedscapeException = r3     // Catch:{ Exception -> 0x0076 }
            java.lang.String r3 = r3.getMessage()     // Catch:{ Exception -> 0x0076 }
            r4 = 2131952444(0x7f13033c, float:1.954133E38)
            java.lang.String r4 = r2.getString(r4)     // Catch:{ Exception -> 0x0076 }
            boolean r3 = r3.equals(r4)     // Catch:{ Exception -> 0x0076 }
            com.medscape.android.util.MedscapeException r4 = r2.mMedscapeException     // Catch:{ Exception -> 0x0076 }
            java.lang.String r4 = r4.getMessage()     // Catch:{ Exception -> 0x0076 }
            java.lang.String r5 = "Authentication"
            boolean r4 = r4.contains(r5)     // Catch:{ Exception -> 0x0076 }
            if (r3 != 0) goto L_0x0047
            if (r4 != 0) goto L_0x0047
            com.medscape.android.util.MedscapeException r4 = r2.mMedscapeException     // Catch:{ Exception -> 0x0076 }
            r5 = 2131952056(0x7f1301b8, float:1.9540544E38)
            java.lang.String r5 = r2.getString(r5)     // Catch:{ Exception -> 0x0076 }
            r4.setMessage(r5)     // Catch:{ Exception -> 0x0076 }
        L_0x0047:
            boolean r4 = r2.mShowExceptionIfFailedtoLoad     // Catch:{ Exception -> 0x0076 }
            r5 = -2
            if (r4 != 0) goto L_0x005e
            if (r3 == 0) goto L_0x004f
            goto L_0x005e
        L_0x004f:
            com.medscape.android.util.MedscapeException r3 = r2.mMedscapeException     // Catch:{ Exception -> 0x0076 }
            android.view.View r4 = r2.mRootView     // Catch:{ Exception -> 0x0076 }
            java.lang.String r0 = "OK"
            com.medscape.android.consult.fragments.ConsultTimelineFragment$11 r1 = new com.medscape.android.consult.fragments.ConsultTimelineFragment$11     // Catch:{ Exception -> 0x0076 }
            r1.<init>()     // Catch:{ Exception -> 0x0076 }
            r3.showSnackBar(r4, r5, r0, r1)     // Catch:{ Exception -> 0x0076 }
            goto L_0x007d
        L_0x005e:
            com.medscape.android.util.MedscapeException r3 = r2.mMedscapeException     // Catch:{ Exception -> 0x0076 }
            android.view.View r4 = r2.mRootView     // Catch:{ Exception -> 0x0076 }
            android.content.res.Resources r0 = r2.getResources()     // Catch:{ Exception -> 0x0076 }
            r1 = 2131953198(0x7f13062e, float:1.954286E38)
            java.lang.String r0 = r0.getString(r1)     // Catch:{ Exception -> 0x0076 }
            com.medscape.android.consult.fragments.ConsultTimelineFragment$10 r1 = new com.medscape.android.consult.fragments.ConsultTimelineFragment$10     // Catch:{ Exception -> 0x0076 }
            r1.<init>()     // Catch:{ Exception -> 0x0076 }
            r3.showSnackBar(r4, r5, r0, r1)     // Catch:{ Exception -> 0x0076 }
            goto L_0x007d
        L_0x0076:
            java.lang.String r3 = TAG
            java.lang.String r4 = "Failed to show failure alert"
            com.wbmd.wbmdcommons.logging.Trace.w(r3, r4)
        L_0x007d:
            androidx.fragment.app.FragmentActivity r3 = r2.getActivity()
            if (r3 == 0) goto L_0x0099
            androidx.fragment.app.FragmentActivity r3 = r2.getActivity()
            boolean r3 = r3.isFinishing()
            if (r3 != 0) goto L_0x0099
            androidx.fragment.app.FragmentActivity r3 = r2.getActivity()
            com.medscape.android.consult.fragments.ConsultTimelineFragment$12 r4 = new com.medscape.android.consult.fragments.ConsultTimelineFragment$12
            r4.<init>()
            r3.runOnUiThread(r4)
        L_0x0099:
            androidx.recyclerview.widget.RecyclerView r3 = r2.mRecyclerView
            if (r3 == 0) goto L_0x00ae
            com.medscape.android.util.MedscapeException r3 = r2.mMedscapeException
            if (r3 != 0) goto L_0x00ae
            android.os.Handler r3 = new android.os.Handler
            r3.<init>()
            com.medscape.android.consult.fragments.-$$Lambda$ConsultTimelineFragment$cLBAgNhxW1ExEesWOFe-ZBlif-M r4 = new com.medscape.android.consult.fragments.-$$Lambda$ConsultTimelineFragment$cLBAgNhxW1ExEesWOFe-ZBlif-M
            r4.<init>()
            r3.post(r4)
        L_0x00ae:
            com.medscape.android.consult.adapters.ConsultTimelineListAdapter r3 = r2.mAdapter
            if (r3 == 0) goto L_0x00cb
            com.medscape.android.util.MedscapeException r4 = r2.mMedscapeException
            if (r4 != 0) goto L_0x00cb
            com.medscape.android.consult.models.ConsultFeed r4 = r2.mFeed
            if (r4 == 0) goto L_0x00c0
            java.lang.String r4 = "consult_list_error"
            r3.updateListWithProfileItems(r4)
            goto L_0x00cb
        L_0x00c0:
            boolean r4 = r2.mClearListForSectionChange
            if (r4 == 0) goto L_0x00cb
            r4 = 0
            r2.mClearListForSectionChange = r4
            r4 = 0
            r3.setItemsAndRefresh(r4)
        L_0x00cb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.consult.fragments.ConsultTimelineFragment.onFailedToReceiveFeed(com.medscape.android.util.MedscapeException, int, java.lang.String):void");
    }

    public /* synthetic */ void lambda$onFailedToReceiveFeed$0$ConsultTimelineFragment() {
        this.mSwipeRefreshLayout.setRefreshing(false);
    }

    public /* synthetic */ void lambda$onFailedToReceiveFeed$1$ConsultTimelineFragment() {
        this.mRecyclerView.setVisibility(0);
    }

    public void onMoreRequested() {
        MedscapeException medscapeException = this.mMedscapeException;
        if (medscapeException != null) {
            medscapeException.dismissSnackBar();
        }
        ConsultTimelineListAdapter consultTimelineListAdapter = this.mAdapter;
        if (consultTimelineListAdapter != null) {
            consultTimelineListAdapter.updateListWithProfileItems(Constants.CONSULT_LIST_LOADING);
            this.mAdapter.saveSponsoredPost();
        }
        loadNextSetOfData();
    }

    public String getCurrentPvid() {
        if (getActivity() == null || !isAdded() || !(getActivity() instanceof BaseActivity)) {
            return null;
        }
        return ((BaseActivity) getActivity()).getCurrentPvid();
    }

    private class ConsultTimeLineChanged extends BroadcastReceiver {
        private ConsultTimeLineChanged() {
        }

        public void onReceive(Context context, Intent intent) {
            if (ConsultTimelineFragment.this.mProgressDialog != null) {
                ConsultTimelineFragment.this.mProgressDialog.setVisibility(0);
            }
            ConsultFeed unused = ConsultTimelineFragment.this.mFeed = null;
            ConsultTimelineFragment.this.mAdapter.setItemsAndRefresh((ConsultFeed) null);
            boolean unused2 = ConsultTimelineFragment.this.mLoadOnResume = true;
        }
    }

    public void updateFeedWithNewPost(Intent intent) {
        if (intent != null) {
            ConsultPost consultPost = (ConsultPost) intent.getParcelableExtra(Constants.EXTRA_CONSULT_POST_UPLOADED);
            ConsultFeed consultFeed = this.mFeed;
            if (consultFeed != null) {
                consultFeed.addConsultFeedItemToPosition(consultPost, 0);
                this.mAdapter.setItemsAndRefresh(this.mFeed);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        ConsultTimelineFragment.this.mRecyclerView.smoothScrollToPosition(0);
                    }
                }, 200);
            }
        }
    }

    public void setMedStudentNotificationListener(IMedStudentNotificationListener iMedStudentNotificationListener) {
        this.medStudentNotificationListener = iMedStudentNotificationListener;
    }

    public void onPause() {
        super.onPause();
        resetLastPause();
    }

    private void resetLastPause() {
        SharedPreferenceProvider.get().save(getLastPausePrefName(), Long.valueOf(System.currentTimeMillis()));
    }
}
