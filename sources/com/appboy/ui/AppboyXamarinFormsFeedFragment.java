package com.appboy.ui;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.core.view.GestureDetectorCompat;
import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.appboy.Appboy;
import com.appboy.enums.CardCategory;
import com.appboy.events.FeedUpdatedEvent;
import com.appboy.events.IEventSubscriber;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.adapters.AppboyListAdapter;
import java.util.ArrayList;
import java.util.EnumSet;

public class AppboyXamarinFormsFeedFragment extends ListFragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final long AUTO_HIDE_REFRESH_INDICATOR_DELAY_MS = 2500;
    private static final int MAX_FEED_TTL_SECONDS = 60;
    private static final int NETWORK_PROBLEM_WARNING_MS = 5000;
    /* access modifiers changed from: private */
    public static final String TAG = AppboyLogger.getAppboyLogTag(AppboyXamarinFormsFeedFragment.class);
    /* access modifiers changed from: private */
    public int currentCardIndexAtBottomOfScreen;
    /* access modifiers changed from: private */
    public AppboyListAdapter mAdapter;
    /* access modifiers changed from: private */
    public Appboy mAppboy;
    /* access modifiers changed from: private */
    public EnumSet<CardCategory> mCategories;
    /* access modifiers changed from: private */
    public LinearLayout mEmptyFeedLayout;
    private RelativeLayout mFeedRootLayout;
    /* access modifiers changed from: private */
    public SwipeRefreshLayout mFeedSwipeLayout;
    private IEventSubscriber<FeedUpdatedEvent> mFeedUpdatedSubscriber;
    /* access modifiers changed from: private */
    public GestureDetectorCompat mGestureDetector;
    /* access modifiers changed from: private */
    public ProgressBar mLoadingSpinner;
    /* access modifiers changed from: private */
    public final Handler mMainThreadLooper = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public LinearLayout mNetworkErrorLayout;
    /* access modifiers changed from: private */
    public final Runnable mShowNetworkError = new Runnable() {
        public void run() {
            if (AppboyXamarinFormsFeedFragment.this.mLoadingSpinner != null) {
                AppboyXamarinFormsFeedFragment.this.mLoadingSpinner.setVisibility(8);
            }
            if (AppboyXamarinFormsFeedFragment.this.mNetworkErrorLayout != null) {
                AppboyXamarinFormsFeedFragment.this.mNetworkErrorLayout.setVisibility(0);
            }
        }
    };
    private boolean mSkipCardImpressionsReset;
    /* access modifiers changed from: private */
    public View mTransparentFullBoundsContainerView;
    /* access modifiers changed from: private */
    public int previousVisibleHeadCardIndex;

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mAppboy = Appboy.getInstance(context);
        if (this.mAdapter == null) {
            this.mAdapter = new AppboyListAdapter(context, R.id.tag, new ArrayList());
            this.mCategories = CardCategory.getAllCategories();
        }
        setRetainInstance(true);
        this.mGestureDetector = new GestureDetectorCompat(context, new FeedGestureListener());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.com_appboy_feed, viewGroup, false);
        this.mNetworkErrorLayout = (LinearLayout) inflate.findViewById(R.id.com_appboy_feed_network_error);
        this.mLoadingSpinner = (ProgressBar) inflate.findViewById(R.id.com_appboy_feed_loading_spinner);
        this.mEmptyFeedLayout = (LinearLayout) inflate.findViewById(R.id.com_appboy_feed_empty_feed);
        this.mFeedRootLayout = (RelativeLayout) inflate.findViewById(R.id.com_appboy_feed_root);
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.appboy_feed_swipe_container);
        this.mFeedSwipeLayout = swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(this);
        this.mFeedSwipeLayout.setEnabled(false);
        this.mFeedSwipeLayout.setColorSchemeResources(R.color.com_appboy_newsfeed_swipe_refresh_color_1, R.color.com_appboy_newsfeed_swipe_refresh_color_2, R.color.com_appboy_newsfeed_swipe_refresh_color_3, R.color.com_appboy_newsfeed_swipe_refresh_color_4);
        this.mTransparentFullBoundsContainerView = inflate.findViewById(R.id.com_appboy_feed_transparent_full_bounds_container_view);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.mSkipCardImpressionsReset) {
            this.mSkipCardImpressionsReset = false;
        } else {
            this.mAdapter.resetCardImpressionTracker();
            AppboyLogger.d(TAG, "Resetting card impressions.");
        }
        LayoutInflater from = LayoutInflater.from(getActivity());
        final ListView listView = getListView();
        listView.addHeaderView(from.inflate(R.layout.com_appboy_feed_header, (ViewGroup) null));
        listView.addFooterView(from.inflate(R.layout.com_appboy_feed_footer, (ViewGroup) null));
        this.mFeedRootLayout.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return AppboyXamarinFormsFeedFragment.this.mGestureDetector.onTouchEvent(motionEvent);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                AppboyXamarinFormsFeedFragment.this.mFeedSwipeLayout.setEnabled(i == 0);
                if (i2 != 0) {
                    int i4 = i - 1;
                    if (i4 > AppboyXamarinFormsFeedFragment.this.previousVisibleHeadCardIndex) {
                        AppboyXamarinFormsFeedFragment.this.mAdapter.batchSetCardsToRead(AppboyXamarinFormsFeedFragment.this.previousVisibleHeadCardIndex, i4);
                    }
                    int unused = AppboyXamarinFormsFeedFragment.this.previousVisibleHeadCardIndex = i4;
                    int unused2 = AppboyXamarinFormsFeedFragment.this.currentCardIndexAtBottomOfScreen = i + i2;
                }
            }
        });
        this.mTransparentFullBoundsContainerView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return view.getVisibility() == 0;
            }
        });
        this.mAppboy.removeSingleSubscription(this.mFeedUpdatedSubscriber, FeedUpdatedEvent.class);
        AnonymousClass5 r4 = new IEventSubscriber<FeedUpdatedEvent>() {
            public void trigger(final FeedUpdatedEvent feedUpdatedEvent) {
                Activity activity = AppboyXamarinFormsFeedFragment.this.getActivity();
                if (activity != null) {
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            String access$700 = AppboyXamarinFormsFeedFragment.TAG;
                            AppboyLogger.d(access$700, "Updating feed views in response to FeedUpdatedEvent: " + feedUpdatedEvent);
                            AppboyXamarinFormsFeedFragment.this.mMainThreadLooper.removeCallbacks(AppboyXamarinFormsFeedFragment.this.mShowNetworkError);
                            AppboyXamarinFormsFeedFragment.this.mNetworkErrorLayout.setVisibility(8);
                            if (feedUpdatedEvent.getCardCount((EnumSet<CardCategory>) AppboyXamarinFormsFeedFragment.this.mCategories) == 0) {
                                listView.setVisibility(8);
                                AppboyXamarinFormsFeedFragment.this.mAdapter.clear();
                            } else {
                                AppboyXamarinFormsFeedFragment.this.mEmptyFeedLayout.setVisibility(8);
                                AppboyXamarinFormsFeedFragment.this.mLoadingSpinner.setVisibility(8);
                                AppboyXamarinFormsFeedFragment.this.mTransparentFullBoundsContainerView.setVisibility(8);
                            }
                            if (feedUpdatedEvent.isFromOfflineStorage() && (feedUpdatedEvent.lastUpdatedInSecondsFromEpoch() + 60) * 1000 < System.currentTimeMillis()) {
                                AppboyLogger.i(AppboyXamarinFormsFeedFragment.TAG, "Feed received was older than the max time to live of 60 seconds, displaying it for now, but requesting an updated view from the server.");
                                AppboyXamarinFormsFeedFragment.this.mAppboy.requestFeedRefresh();
                                if (feedUpdatedEvent.getCardCount((EnumSet<CardCategory>) AppboyXamarinFormsFeedFragment.this.mCategories) == 0) {
                                    AppboyLogger.d(AppboyXamarinFormsFeedFragment.TAG, "Old feed was empty, putting up a network spinner and registering the network error message on a delay of 5000ms.");
                                    AppboyXamarinFormsFeedFragment.this.mEmptyFeedLayout.setVisibility(8);
                                    AppboyXamarinFormsFeedFragment.this.mLoadingSpinner.setVisibility(0);
                                    AppboyXamarinFormsFeedFragment.this.mTransparentFullBoundsContainerView.setVisibility(0);
                                    AppboyXamarinFormsFeedFragment.this.mMainThreadLooper.postDelayed(AppboyXamarinFormsFeedFragment.this.mShowNetworkError, CoroutineLiveDataKt.DEFAULT_TIMEOUT);
                                    return;
                                }
                            }
                            if (feedUpdatedEvent.getCardCount((EnumSet<CardCategory>) AppboyXamarinFormsFeedFragment.this.mCategories) == 0) {
                                AppboyXamarinFormsFeedFragment.this.mLoadingSpinner.setVisibility(8);
                                AppboyXamarinFormsFeedFragment.this.mEmptyFeedLayout.setVisibility(0);
                                AppboyXamarinFormsFeedFragment.this.mTransparentFullBoundsContainerView.setVisibility(0);
                            } else {
                                AppboyXamarinFormsFeedFragment.this.mAdapter.replaceFeed(feedUpdatedEvent.getFeedCards((EnumSet<CardCategory>) AppboyXamarinFormsFeedFragment.this.mCategories));
                                listView.setVisibility(0);
                            }
                            AppboyXamarinFormsFeedFragment.this.mFeedSwipeLayout.setRefreshing(false);
                        }
                    });
                }
            }
        };
        this.mFeedUpdatedSubscriber = r4;
        this.mAppboy.subscribeToFeedUpdates(r4);
        listView.setAdapter(this.mAdapter);
        this.mAppboy.requestFeedRefreshFromCache();
    }

    public void onResume() {
        super.onResume();
        Appboy.getInstance(getActivity()).logFeedDisplayed();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mAppboy.removeSingleSubscription(this.mFeedUpdatedSubscriber, FeedUpdatedEvent.class);
        setOnScreenCardsToRead();
    }

    public void onPause() {
        super.onPause();
        setOnScreenCardsToRead();
    }

    private void setOnScreenCardsToRead() {
        this.mAdapter.batchSetCardsToRead(this.previousVisibleHeadCardIndex, this.currentCardIndexAtBottomOfScreen);
    }

    public void onDetach() {
        super.onDetach();
        setListAdapter((ListAdapter) null);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (isVisible()) {
            this.mSkipCardImpressionsReset = true;
        }
    }

    public EnumSet<CardCategory> getCategories() {
        return this.mCategories;
    }

    public void setCategory(CardCategory cardCategory) {
        setCategories(EnumSet.of(cardCategory));
    }

    public void setCategories(EnumSet<CardCategory> enumSet) {
        if (enumSet == null) {
            AppboyLogger.i(TAG, "The categories passed into setCategories are null, AppboyFeedFragment is going to display all the cards in cache.");
            this.mCategories = CardCategory.getAllCategories();
        } else if (enumSet.isEmpty()) {
            AppboyLogger.w(TAG, "The categories set had no elements and have been ignored. Please pass a valid EnumSet of CardCategory.");
            return;
        } else if (!enumSet.equals(this.mCategories)) {
            this.mCategories = enumSet;
        } else {
            return;
        }
        Appboy appboy = this.mAppboy;
        if (appboy != null) {
            appboy.requestFeedRefreshFromCache();
        }
    }

    public void onRefresh() {
        this.mAppboy.requestFeedRefresh();
        this.mMainThreadLooper.postDelayed(new Runnable() {
            public void run() {
                AppboyXamarinFormsFeedFragment.this.mFeedSwipeLayout.setRefreshing(false);
            }
        }, AUTO_HIDE_REFRESH_INDICATOR_DELAY_MS);
    }

    public class FeedGestureListener extends GestureDetector.SimpleOnGestureListener {
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        public FeedGestureListener() {
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            AppboyXamarinFormsFeedFragment.this.getListView().smoothScrollBy((int) f2, 0);
            return true;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            long eventTime = (motionEvent2.getEventTime() - motionEvent.getEventTime()) * 2;
            AppboyXamarinFormsFeedFragment.this.getListView().smoothScrollBy(-((int) ((f2 * ((float) eventTime)) / 1000.0f)), (int) (eventTime * 2));
            return true;
        }
    }
}
