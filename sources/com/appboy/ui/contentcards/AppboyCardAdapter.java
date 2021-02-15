package com.appboy.ui.contentcards;

import android.content.Context;
import android.os.Handler;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.appboy.models.cards.Card;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.contentcards.handlers.IContentCardsViewBindingHandler;
import com.appboy.ui.contentcards.recycler.ItemTouchHelperAdapter;
import com.appboy.ui.contentcards.view.ContentCardViewHolder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AppboyCardAdapter extends RecyclerView.Adapter<ContentCardViewHolder> implements ItemTouchHelperAdapter {
    private static final String TAG = AppboyLogger.getAppboyLogTag(AppboyCardAdapter.class);
    private List<Card> mCardData;
    private final IContentCardsViewBindingHandler mContentCardsViewBindingHandler;
    private final Context mContext;
    private final Handler mHandler;
    private Set<String> mImpressedCardIds = new HashSet();
    private final LinearLayoutManager mLayoutManager;

    public AppboyCardAdapter(Context context, LinearLayoutManager linearLayoutManager, List<Card> list, IContentCardsViewBindingHandler iContentCardsViewBindingHandler) {
        this.mContext = context;
        this.mCardData = list;
        this.mHandler = new Handler();
        this.mLayoutManager = linearLayoutManager;
        this.mContentCardsViewBindingHandler = iContentCardsViewBindingHandler;
        setHasStableIds(true);
    }

    public ContentCardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return this.mContentCardsViewBindingHandler.onCreateViewHolder(this.mContext, this.mCardData, viewGroup, i);
    }

    public void onBindViewHolder(ContentCardViewHolder contentCardViewHolder, int i) {
        this.mContentCardsViewBindingHandler.onBindViewHolder(this.mContext, this.mCardData, contentCardViewHolder, i);
    }

    public int getItemViewType(int i) {
        return this.mContentCardsViewBindingHandler.getItemViewType(this.mContext, this.mCardData, i);
    }

    public int getItemCount() {
        return this.mCardData.size();
    }

    public void onItemDismiss(int i) {
        Card remove = this.mCardData.remove(i);
        remove.setIsDismissed(true);
        notifyItemRemoved(i);
        AppboyContentCardsManager.getInstance().getContentCardsActionListener().onContentCardDismissed(this.mContext, remove);
    }

    public boolean isItemDismissable(int i) {
        if (this.mCardData.isEmpty()) {
            return false;
        }
        return this.mCardData.get(i).getIsDismissible();
    }

    public void onViewAttachedToWindow(ContentCardViewHolder contentCardViewHolder) {
        super.onViewAttachedToWindow(contentCardViewHolder);
        if (!this.mCardData.isEmpty()) {
            int adapterPosition = contentCardViewHolder.getAdapterPosition();
            if (adapterPosition == -1 || !isAdapterPositionOnScreen(adapterPosition)) {
                String str = TAG;
                AppboyLogger.v(str, "The card at position " + adapterPosition + " isn't on screen or does not have a valid adapter position. Not logging impression.");
                return;
            }
            logImpression(this.mCardData.get(adapterPosition));
        }
    }

    public void onViewDetachedFromWindow(ContentCardViewHolder contentCardViewHolder) {
        super.onViewDetachedFromWindow(contentCardViewHolder);
        if (!this.mCardData.isEmpty()) {
            final int adapterPosition = contentCardViewHolder.getAdapterPosition();
            if (adapterPosition == -1 || !isAdapterPositionOnScreen(adapterPosition)) {
                String str = TAG;
                AppboyLogger.v(str, "The card at position " + adapterPosition + " isn't on screen or does not have a valid adapter position. Not marking as read.");
                return;
            }
            this.mCardData.get(adapterPosition).setIndicatorHighlighted(true);
            this.mHandler.post(new Runnable() {
                public void run() {
                    AppboyCardAdapter.this.notifyItemChanged(adapterPosition);
                }
            });
        }
    }

    public long getItemId(int i) {
        return (long) this.mCardData.get(i).getId().hashCode();
    }

    public synchronized void replaceCards(List<Card> list) {
        DiffUtil.DiffResult calculateDiff = DiffUtil.calculateDiff(new CardListDiffCallback(this.mCardData, list));
        this.mCardData.clear();
        this.mCardData.addAll(list);
        calculateDiff.dispatchUpdatesTo((RecyclerView.Adapter) this);
    }

    public void markOnScreenCardsAsRead() {
        if (this.mCardData.isEmpty()) {
            AppboyLogger.d(TAG, "Card list is empty. Not marking on-screen cards as read.");
            return;
        }
        final int findFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition();
        final int findLastVisibleItemPosition = this.mLayoutManager.findLastVisibleItemPosition();
        if (findFirstVisibleItemPosition < 0 || findLastVisibleItemPosition < 0) {
            String str = TAG;
            AppboyLogger.d(str, "Not marking all on-screen cards as read. Either the first or last index is negative. First visible: " + findFirstVisibleItemPosition + " . Last visible: " + findLastVisibleItemPosition);
            return;
        }
        for (int i = findFirstVisibleItemPosition; i <= findLastVisibleItemPosition; i++) {
            this.mCardData.get(i).setIndicatorHighlighted(true);
        }
        this.mHandler.post(new Runnable() {
            public void run() {
                int i = findLastVisibleItemPosition;
                int i2 = findFirstVisibleItemPosition;
                AppboyCardAdapter.this.notifyItemRangeChanged(i2, (i - i2) + 1);
            }
        });
    }

    public List<String> getImpressedCardIds() {
        return new ArrayList(this.mImpressedCardIds);
    }

    public void setImpressedCardIds(List<String> list) {
        this.mImpressedCardIds = new HashSet(list);
    }

    public boolean isControlCardAtPosition(int i) {
        if (i < 0 || i >= this.mCardData.size()) {
            return false;
        }
        return this.mCardData.get(i).isControl();
    }

    /* access modifiers changed from: package-private */
    public boolean isAdapterPositionOnScreen(int i) {
        return Math.min(this.mLayoutManager.findFirstVisibleItemPosition(), this.mLayoutManager.findFirstCompletelyVisibleItemPosition()) <= i && Math.max(this.mLayoutManager.findLastVisibleItemPosition(), this.mLayoutManager.findLastCompletelyVisibleItemPosition()) >= i;
    }

    /* access modifiers changed from: package-private */
    public void logImpression(Card card) {
        if (!this.mImpressedCardIds.contains(card.getId())) {
            card.logImpression();
            this.mImpressedCardIds.add(card.getId());
            String str = TAG;
            AppboyLogger.v(str, "Logged impression for card " + card.getId());
        } else {
            String str2 = TAG;
            AppboyLogger.v(str2, "Already counted impression for card " + card.getId());
        }
        if (!card.getViewed()) {
            card.setViewed(true);
        }
    }

    private class CardListDiffCallback extends DiffUtil.Callback {
        private final List<Card> mNewCards;
        private final List<Card> mOldCards;

        CardListDiffCallback(List<Card> list, List<Card> list2) {
            this.mOldCards = list;
            this.mNewCards = list2;
        }

        public int getOldListSize() {
            return this.mOldCards.size();
        }

        public int getNewListSize() {
            return this.mNewCards.size();
        }

        public boolean areItemsTheSame(int i, int i2) {
            return doItemsShareIds(i, i2);
        }

        public boolean areContentsTheSame(int i, int i2) {
            return doItemsShareIds(i, i2);
        }

        private boolean doItemsShareIds(int i, int i2) {
            return this.mOldCards.get(i).getId().equals(this.mNewCards.get(i2).getId());
        }
    }
}
