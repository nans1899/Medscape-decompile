package com.appboy.ui;

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
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.appboy.Appboy;
import com.appboy.enums.CardCategory;
import com.appboy.events.FeedUpdatedEvent;
import com.appboy.events.IEventSubscriber;
import com.appboy.models.cards.Card;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.adapters.AppboyListAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

public class AppboyFeedFragment extends ListFragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final long AUTO_HIDE_REFRESH_INDICATOR_DELAY_MS = 2500;
    private static final int MAX_FEED_TTL_SECONDS = 60;
    private static final int NETWORK_PROBLEM_WARNING_MS = 5000;
    static final String SAVED_INSTANCE_STATE_KEY_CARD_CATEGORY = "CARD_CATEGORY";
    static final String SAVED_INSTANCE_STATE_KEY_CURRENT_CARD_INDEX_AT_BOTTOM_OF_SCREEN = "CURRENT_CARD_INDEX_AT_BOTTOM_OF_SCREEN";
    static final String SAVED_INSTANCE_STATE_KEY_PREVIOUS_VISIBLE_HEAD_CARD_INDEX = "PREVIOUS_VISIBLE_HEAD_CARD_INDEX";
    static final String SAVED_INSTANCE_STATE_KEY_SKIP_CARD_IMPRESSIONS_RESET = "SKIP_CARD_IMPRESSIONS_RESET";
    /* access modifiers changed from: private */
    public static final String TAG = AppboyLogger.getAppboyLogTag(AppboyFeedFragment.class);
    /* access modifiers changed from: private */
    public AppboyListAdapter mAdapter;
    /* access modifiers changed from: private */
    public EnumSet<CardCategory> mCategories;
    int mCurrentCardIndexAtBottomOfScreen = 0;
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
    int mPreviousVisibleHeadCardIndex = 0;
    /* access modifiers changed from: private */
    public final Runnable mShowNetworkError = new Runnable() {
        public void run() {
            if (AppboyFeedFragment.this.mLoadingSpinner != null) {
                AppboyFeedFragment.this.mLoadingSpinner.setVisibility(8);
            }
            if (AppboyFeedFragment.this.mNetworkErrorLayout != null) {
                AppboyFeedFragment.this.mNetworkErrorLayout.setVisibility(0);
            }
        }
    };
    boolean mSkipCardImpressionsReset = false;
    /* access modifiers changed from: private */
    public boolean mSortEnabled = false;
    /* access modifiers changed from: private */
    public View mTransparentFullBoundsContainerView;

    public void onAttach(Context context) {
        super.onAttach(context);
        if (this.mAdapter == null) {
            this.mAdapter = new AppboyListAdapter(context, R.id.tag, new ArrayList());
            this.mCategories = CardCategory.getAllCategories();
        }
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
        loadFragmentStateFromSavedInstanceState(bundle);
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
                return AppboyFeedFragment.this.mGestureDetector.onTouchEvent(motionEvent);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                AppboyFeedFragment.this.mFeedSwipeLayout.setEnabled(i == 0);
                if (i2 != 0) {
                    int i4 = i - 1;
                    if (i4 > AppboyFeedFragment.this.mPreviousVisibleHeadCardIndex) {
                        AppboyFeedFragment.this.mAdapter.batchSetCardsToRead(AppboyFeedFragment.this.mPreviousVisibleHeadCardIndex, i4);
                    }
                    AppboyFeedFragment.this.mPreviousVisibleHeadCardIndex = i4;
                    AppboyFeedFragment.this.mCurrentCardIndexAtBottomOfScreen = i + i2;
                }
            }
        });
        this.mTransparentFullBoundsContainerView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return view.getVisibility() == 0;
            }
        });
        Appboy.getInstance(getContext()).removeSingleSubscription(this.mFeedUpdatedSubscriber, FeedUpdatedEvent.class);
        this.mFeedUpdatedSubscriber = new IEventSubscriber<FeedUpdatedEvent>() {
            public void trigger(final FeedUpdatedEvent feedUpdatedEvent) {
                FragmentActivity activity = AppboyFeedFragment.this.getActivity();
                if (activity != null) {
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            String access$500 = AppboyFeedFragment.TAG;
                            AppboyLogger.v(access$500, "Updating feed views in response to FeedUpdatedEvent: " + feedUpdatedEvent);
                            AppboyFeedFragment.this.mMainThreadLooper.removeCallbacks(AppboyFeedFragment.this.mShowNetworkError);
                            AppboyFeedFragment.this.mNetworkErrorLayout.setVisibility(8);
                            if (feedUpdatedEvent.getCardCount((EnumSet<CardCategory>) AppboyFeedFragment.this.mCategories) == 0) {
                                listView.setVisibility(8);
                                AppboyFeedFragment.this.mAdapter.clear();
                            } else {
                                AppboyFeedFragment.this.mEmptyFeedLayout.setVisibility(8);
                                AppboyFeedFragment.this.mLoadingSpinner.setVisibility(8);
                                AppboyFeedFragment.this.mTransparentFullBoundsContainerView.setVisibility(8);
                            }
                            if (feedUpdatedEvent.isFromOfflineStorage() && (feedUpdatedEvent.lastUpdatedInSecondsFromEpoch() + 60) * 1000 < System.currentTimeMillis()) {
                                AppboyLogger.i(AppboyFeedFragment.TAG, "Feed received was older than the max time to live of 60 seconds, displaying it for now, but requesting an updated view from the server.");
                                Appboy.getInstance(AppboyFeedFragment.this.getContext()).requestFeedRefresh();
                                if (feedUpdatedEvent.getCardCount((EnumSet<CardCategory>) AppboyFeedFragment.this.mCategories) == 0) {
                                    AppboyLogger.d(AppboyFeedFragment.TAG, "Old feed was empty, putting up a network spinner and registering the network error message with a delay of 5000ms.");
                                    AppboyFeedFragment.this.mEmptyFeedLayout.setVisibility(8);
                                    AppboyFeedFragment.this.mLoadingSpinner.setVisibility(0);
                                    AppboyFeedFragment.this.mTransparentFullBoundsContainerView.setVisibility(0);
                                    AppboyFeedFragment.this.mMainThreadLooper.postDelayed(AppboyFeedFragment.this.mShowNetworkError, CoroutineLiveDataKt.DEFAULT_TIMEOUT);
                                    return;
                                }
                            }
                            if (feedUpdatedEvent.getCardCount((EnumSet<CardCategory>) AppboyFeedFragment.this.mCategories) == 0) {
                                AppboyFeedFragment.this.mLoadingSpinner.setVisibility(8);
                                AppboyFeedFragment.this.mEmptyFeedLayout.setVisibility(0);
                                AppboyFeedFragment.this.mTransparentFullBoundsContainerView.setVisibility(0);
                            } else {
                                if (!AppboyFeedFragment.this.mSortEnabled || feedUpdatedEvent.getCardCount((EnumSet<CardCategory>) AppboyFeedFragment.this.mCategories) == feedUpdatedEvent.getUnreadCardCount((EnumSet<CardCategory>) AppboyFeedFragment.this.mCategories)) {
                                    AppboyFeedFragment.this.mAdapter.replaceFeed(feedUpdatedEvent.getFeedCards((EnumSet<CardCategory>) AppboyFeedFragment.this.mCategories));
                                } else {
                                    AppboyFeedFragment.this.mAdapter.replaceFeed(AppboyFeedFragment.this.sortFeedCards(feedUpdatedEvent.getFeedCards((EnumSet<CardCategory>) AppboyFeedFragment.this.mCategories)));
                                }
                                listView.setVisibility(0);
                            }
                            AppboyFeedFragment.this.mFeedSwipeLayout.setRefreshing(false);
                        }
                    });
                }
            }
        };
        Appboy.getInstance(getContext()).subscribeToFeedUpdates(this.mFeedUpdatedSubscriber);
        listView.setAdapter(this.mAdapter);
        Appboy.getInstance(getContext()).requestFeedRefreshFromCache();
    }

    public List<Card> sortFeedCards(List<Card> list) {
        Collections.sort(list, new Comparator<Card>() {
            public int compare(Card card, Card card2) {
                if (card.isIndicatorHighlighted() == card2.isIndicatorHighlighted()) {
                    return 0;
                }
                return card.isIndicatorHighlighted() ? 1 : -1;
            }
        });
        return list;
    }

    public void onResume() {
        super.onResume();
        Appboy.getInstance(getContext()).logFeedDisplayed();
    }

    public void onDestroyView() {
        super.onDestroyView();
        Appboy.getInstance(getContext()).removeSingleSubscription(this.mFeedUpdatedSubscriber, FeedUpdatedEvent.class);
        setOnScreenCardsToRead();
    }

    public void onPause() {
        super.onPause();
        setOnScreenCardsToRead();
    }

    private void setOnScreenCardsToRead() {
        this.mAdapter.batchSetCardsToRead(this.mPreviousVisibleHeadCardIndex, this.mCurrentCardIndexAtBottomOfScreen);
    }

    public void onDetach() {
        super.onDetach();
        setListAdapter((ListAdapter) null);
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt(SAVED_INSTANCE_STATE_KEY_PREVIOUS_VISIBLE_HEAD_CARD_INDEX, this.mPreviousVisibleHeadCardIndex);
        bundle.putInt(SAVED_INSTANCE_STATE_KEY_CURRENT_CARD_INDEX_AT_BOTTOM_OF_SCREEN, this.mCurrentCardIndexAtBottomOfScreen);
        bundle.putBoolean(SAVED_INSTANCE_STATE_KEY_SKIP_CARD_IMPRESSIONS_RESET, this.mSkipCardImpressionsReset);
        if (this.mCategories == null) {
            this.mCategories = CardCategory.getAllCategories();
        }
        ArrayList arrayList = new ArrayList(this.mCategories.size());
        Iterator it = this.mCategories.iterator();
        while (it.hasNext()) {
            arrayList.add(((CardCategory) it.next()).name());
        }
        bundle.putStringArrayList(SAVED_INSTANCE_STATE_KEY_CARD_CATEGORY, arrayList);
        super.onSaveInstanceState(bundle);
        if (isVisible()) {
            this.mSkipCardImpressionsReset = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void loadFragmentStateFromSavedInstanceState(Bundle bundle) {
        if (bundle != null) {
            if (this.mCategories == null) {
                this.mCategories = CardCategory.getAllCategories();
            }
            this.mPreviousVisibleHeadCardIndex = bundle.getInt(SAVED_INSTANCE_STATE_KEY_PREVIOUS_VISIBLE_HEAD_CARD_INDEX, 0);
            this.mCurrentCardIndexAtBottomOfScreen = bundle.getInt(SAVED_INSTANCE_STATE_KEY_CURRENT_CARD_INDEX_AT_BOTTOM_OF_SCREEN, 0);
            this.mSkipCardImpressionsReset = bundle.getBoolean(SAVED_INSTANCE_STATE_KEY_SKIP_CARD_IMPRESSIONS_RESET, false);
            ArrayList<String> stringArrayList = bundle.getStringArrayList(SAVED_INSTANCE_STATE_KEY_CARD_CATEGORY);
            if (stringArrayList != null) {
                this.mCategories.clear();
                Iterator<String> it = stringArrayList.iterator();
                while (it.hasNext()) {
                    this.mCategories.add(CardCategory.valueOf(it.next()));
                }
            }
        }
    }

    public EnumSet<CardCategory> getCategories() {
        return this.mCategories;
    }

    public boolean getSortEnabled() {
        return this.mSortEnabled;
    }

    public void setSortEnabled(boolean z) {
        this.mSortEnabled = z;
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
        if (Appboy.getInstance(getContext()) != null) {
            Appboy.getInstance(getContext()).requestFeedRefreshFromCache();
        }
    }

    public void onRefresh() {
        Appboy.getInstance(getContext()).requestFeedRefresh();
        this.mMainThreadLooper.postDelayed(new Runnable() {
            public void run() {
                AppboyFeedFragment.this.mFeedSwipeLayout.setRefreshing(false);
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
            AppboyFeedFragment.this.getListView().smoothScrollBy((int) f2, 0);
            return true;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            long eventTime = (motionEvent2.getEventTime() - motionEvent.getEventTime()) * 2;
            AppboyFeedFragment.this.getListView().smoothScrollBy(-((int) ((f2 * ((float) eventTime)) / 1000.0f)), (int) (eventTime * 2));
            return true;
        }
    }
}
