package com.appboy.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.appboy.Appboy;
import com.appboy.events.ContentCardsUpdatedEvent;
import com.appboy.events.IEventSubscriber;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.contentcards.AppboyCardAdapter;
import com.appboy.ui.contentcards.AppboyEmptyContentCardsAdapter;
import com.appboy.ui.contentcards.handlers.DefaultContentCardsUpdateHandler;
import com.appboy.ui.contentcards.handlers.DefaultContentCardsViewBindingHandler;
import com.appboy.ui.contentcards.handlers.IContentCardsUpdateHandler;
import com.appboy.ui.contentcards.handlers.IContentCardsViewBindingHandler;
import com.appboy.ui.contentcards.recycler.ContentCardsDividerItemDecoration;
import com.appboy.ui.contentcards.recycler.SimpleItemTouchHelperCallback;
import java.util.ArrayList;

public class AppboyContentCardsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final long AUTO_HIDE_REFRESH_INDICATOR_DELAY_MS = 2500;
    private static final String KNOWN_CARD_IMPRESSIONS_SAVED_INSTANCE_STATE_KEY = "KNOWN_CARD_IMPRESSIONS_SAVED_INSTANCE_STATE_KEY";
    private static final String LAYOUT_MANAGER_SAVED_INSTANCE_STATE_KEY = "LAYOUT_MANAGER_SAVED_INSTANCE_STATE_KEY";
    private static final int MAX_CONTENT_CARDS_TTL_SECONDS = 60;
    private static final long NETWORK_PROBLEM_WARNING_MS = 5000;
    /* access modifiers changed from: private */
    public static final String TAG = AppboyLogger.getAppboyLogTag(AppboyContentCardsFragment.class);
    /* access modifiers changed from: private */
    public AppboyCardAdapter mCardAdapter;
    /* access modifiers changed from: private */
    public SwipeRefreshLayout mContentCardsSwipeLayout;
    private IEventSubscriber<ContentCardsUpdatedEvent> mContentCardsUpdatedSubscriber;
    private IContentCardsUpdateHandler mCustomContentCardUpdateHandler;
    private IContentCardsViewBindingHandler mCustomContentCardsViewBindingHandler;
    private IContentCardsUpdateHandler mDefaultContentCardUpdateHandler = new DefaultContentCardsUpdateHandler();
    private IContentCardsViewBindingHandler mDefaultContentCardsViewBindingHandler = new DefaultContentCardsViewBindingHandler();
    /* access modifiers changed from: private */
    public AppboyEmptyContentCardsAdapter mEmptyContentCardsAdapter;
    /* access modifiers changed from: private */
    public final Handler mMainThreadLooper = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public RecyclerView mRecyclerView;
    /* access modifiers changed from: private */
    public Runnable mShowNetworkUnavailableRunnable;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mShowNetworkUnavailableRunnable = new NetworkUnavailableRunnable(getContext());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.com_appboy_content_cards, viewGroup, false);
        this.mRecyclerView = (RecyclerView) inflate.findViewById(R.id.com_appboy_content_cards_recycler);
        initializeRecyclerView();
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.appboy_content_cards_swipe_container);
        this.mContentCardsSwipeLayout = swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(this);
        this.mContentCardsSwipeLayout.setColorSchemeResources(R.color.com_appboy_content_cards_swipe_refresh_color_1, R.color.com_appboy_content_cards_swipe_refresh_color_2, R.color.com_appboy_content_cards_swipe_refresh_color_3, R.color.com_appboy_content_cards_swipe_refresh_color_4);
        return inflate;
    }

    public void initializeRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        AppboyCardAdapter appboyCardAdapter = new AppboyCardAdapter(getContext(), linearLayoutManager, new ArrayList(), getContentCardsViewBindingHandler());
        this.mCardAdapter = appboyCardAdapter;
        this.mRecyclerView.setAdapter(appboyCardAdapter);
        this.mRecyclerView.setLayoutManager(linearLayoutManager);
        new ItemTouchHelper(new SimpleItemTouchHelperCallback(this.mCardAdapter)).attachToRecyclerView(this.mRecyclerView);
        RecyclerView.ItemAnimator itemAnimator = this.mRecyclerView.getItemAnimator();
        if (itemAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        }
        this.mRecyclerView.addItemDecoration(new ContentCardsDividerItemDecoration(getContext()));
        this.mEmptyContentCardsAdapter = new AppboyEmptyContentCardsAdapter();
    }

    public void onRefresh() {
        Appboy.getInstance(getContext()).requestContentCardsRefresh(false);
        this.mMainThreadLooper.postDelayed(new Runnable() {
            public void run() {
                AppboyContentCardsFragment.this.mContentCardsSwipeLayout.setRefreshing(false);
            }
        }, AUTO_HIDE_REFRESH_INDICATOR_DELAY_MS);
    }

    public void onResume() {
        super.onResume();
        Appboy.getInstance(getContext()).removeSingleSubscription(this.mContentCardsUpdatedSubscriber, ContentCardsUpdatedEvent.class);
        if (this.mContentCardsUpdatedSubscriber == null) {
            this.mContentCardsUpdatedSubscriber = new IEventSubscriber<ContentCardsUpdatedEvent>() {
                public void trigger(ContentCardsUpdatedEvent contentCardsUpdatedEvent) {
                    AppboyContentCardsFragment.this.mMainThreadLooper.post(new ContentCardsUpdateRunnable(contentCardsUpdatedEvent));
                }
            };
        }
        Appboy.getInstance(getContext()).subscribeToContentCardsUpdates(this.mContentCardsUpdatedSubscriber);
        Appboy.getInstance(getContext()).requestContentCardsRefresh(true);
        Appboy.getInstance(getContext()).logContentCardsDisplayed();
    }

    public void onPause() {
        super.onPause();
        Appboy.getInstance(getContext()).removeSingleSubscription(this.mContentCardsUpdatedSubscriber, ContentCardsUpdatedEvent.class);
        this.mMainThreadLooper.removeCallbacks(this.mShowNetworkUnavailableRunnable);
        this.mCardAdapter.markOnScreenCardsAsRead();
    }

    private class ContentCardsUpdateRunnable implements Runnable {
        private final ContentCardsUpdatedEvent mEvent;

        ContentCardsUpdateRunnable(ContentCardsUpdatedEvent contentCardsUpdatedEvent) {
            this.mEvent = contentCardsUpdatedEvent;
        }

        public void run() {
            String access$300 = AppboyContentCardsFragment.TAG;
            AppboyLogger.v(access$300, "Updating Content Cards views in response to ContentCardsUpdatedEvent: " + this.mEvent);
            AppboyContentCardsFragment.this.mCardAdapter.replaceCards(AppboyContentCardsFragment.this.getContentCardUpdateHandler().handleCardUpdate(this.mEvent));
            AppboyContentCardsFragment.this.mMainThreadLooper.removeCallbacks(AppboyContentCardsFragment.this.mShowNetworkUnavailableRunnable);
            if (this.mEvent.isFromOfflineStorage() && (this.mEvent.getLastUpdatedInSecondsFromEpoch() + 60) * 1000 < System.currentTimeMillis()) {
                AppboyLogger.i(AppboyContentCardsFragment.TAG, "ContentCards received was older than the max time to live of 60 seconds, displaying it for now, but requesting an updated view from the server.");
                Appboy.getInstance(AppboyContentCardsFragment.this.getContext()).requestContentCardsRefresh(false);
                if (this.mEvent.isEmpty()) {
                    AppboyContentCardsFragment.this.mContentCardsSwipeLayout.setRefreshing(true);
                    AppboyLogger.d(AppboyContentCardsFragment.TAG, "Old Content Cards was empty, putting up a network spinner and registering the network error message on a delay of 5000ms.");
                    AppboyContentCardsFragment.this.mMainThreadLooper.postDelayed(AppboyContentCardsFragment.this.mShowNetworkUnavailableRunnable, 5000);
                    return;
                }
            }
            if (!this.mEvent.isEmpty()) {
                AppboyContentCardsFragment appboyContentCardsFragment = AppboyContentCardsFragment.this;
                appboyContentCardsFragment.swapRecyclerViewAdapter(appboyContentCardsFragment.mCardAdapter);
            } else {
                AppboyContentCardsFragment appboyContentCardsFragment2 = AppboyContentCardsFragment.this;
                appboyContentCardsFragment2.swapRecyclerViewAdapter(appboyContentCardsFragment2.mEmptyContentCardsAdapter);
            }
            AppboyContentCardsFragment.this.mContentCardsSwipeLayout.setRefreshing(false);
        }
    }

    private class NetworkUnavailableRunnable implements Runnable {
        private final Context mApplicationContext;

        private NetworkUnavailableRunnable(Context context) {
            this.mApplicationContext = context;
        }

        public void run() {
            AppboyLogger.v(AppboyContentCardsFragment.TAG, "Displaying network unavailable toast.");
            Context context = this.mApplicationContext;
            Toast.makeText(context, context.getString(R.string.com_appboy_feed_connection_error_title), 1).show();
            AppboyContentCardsFragment appboyContentCardsFragment = AppboyContentCardsFragment.this;
            appboyContentCardsFragment.swapRecyclerViewAdapter(appboyContentCardsFragment.mEmptyContentCardsAdapter);
            AppboyContentCardsFragment.this.mContentCardsSwipeLayout.setRefreshing(false);
        }
    }

    public IContentCardsUpdateHandler getContentCardUpdateHandler() {
        IContentCardsUpdateHandler iContentCardsUpdateHandler = this.mCustomContentCardUpdateHandler;
        return iContentCardsUpdateHandler != null ? iContentCardsUpdateHandler : this.mDefaultContentCardUpdateHandler;
    }

    public void setContentCardUpdateHandler(IContentCardsUpdateHandler iContentCardsUpdateHandler) {
        this.mCustomContentCardUpdateHandler = iContentCardsUpdateHandler;
    }

    public IContentCardsViewBindingHandler getContentCardsViewBindingHandler() {
        IContentCardsViewBindingHandler iContentCardsViewBindingHandler = this.mCustomContentCardsViewBindingHandler;
        return iContentCardsViewBindingHandler != null ? iContentCardsViewBindingHandler : this.mDefaultContentCardsViewBindingHandler;
    }

    public void setContentCardsViewBindingHandler(IContentCardsViewBindingHandler iContentCardsViewBindingHandler) {
        this.mCustomContentCardsViewBindingHandler = iContentCardsViewBindingHandler;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        RecyclerView recyclerView = this.mRecyclerView;
        if (!(recyclerView == null || recyclerView.getLayoutManager() == null)) {
            bundle.putParcelable(LAYOUT_MANAGER_SAVED_INSTANCE_STATE_KEY, this.mRecyclerView.getLayoutManager().onSaveInstanceState());
        }
        AppboyCardAdapter appboyCardAdapter = this.mCardAdapter;
        if (appboyCardAdapter != null) {
            bundle.putStringArrayList(KNOWN_CARD_IMPRESSIONS_SAVED_INSTANCE_STATE_KEY, (ArrayList) appboyCardAdapter.getImpressedCardIds());
        }
    }

    public void onViewStateRestored(final Bundle bundle) {
        super.onViewStateRestored(bundle);
        if (bundle != null) {
            this.mMainThreadLooper.post(new Runnable() {
                public void run() {
                    Parcelable parcelable = bundle.getParcelable(AppboyContentCardsFragment.LAYOUT_MANAGER_SAVED_INSTANCE_STATE_KEY);
                    RecyclerView.LayoutManager layoutManager = AppboyContentCardsFragment.this.mRecyclerView.getLayoutManager();
                    if (!(parcelable == null || layoutManager == null)) {
                        layoutManager.onRestoreInstanceState(parcelable);
                    }
                    ArrayList<String> stringArrayList = bundle.getStringArrayList(AppboyContentCardsFragment.KNOWN_CARD_IMPRESSIONS_SAVED_INSTANCE_STATE_KEY);
                    if (stringArrayList != null) {
                        AppboyContentCardsFragment.this.mCardAdapter.setImpressedCardIds(stringArrayList);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void swapRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null && recyclerView.getAdapter() != adapter) {
            this.mRecyclerView.setAdapter(adapter);
        }
    }
}
