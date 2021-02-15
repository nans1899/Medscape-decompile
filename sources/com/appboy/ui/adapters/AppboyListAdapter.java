package com.appboy.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.appboy.models.cards.BannerImageCard;
import com.appboy.models.cards.CaptionedImageCard;
import com.appboy.models.cards.Card;
import com.appboy.models.cards.ShortNewsCard;
import com.appboy.models.cards.TextAnnouncementCard;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.feed.view.BaseFeedCardView;
import com.appboy.ui.widget.BannerImageCardView;
import com.appboy.ui.widget.CaptionedImageCardView;
import com.appboy.ui.widget.DefaultCardView;
import com.appboy.ui.widget.ShortNewsCardView;
import com.appboy.ui.widget.TextAnnouncementCardView;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AppboyListAdapter extends ArrayAdapter<Card> {
    private static final String TAG = AppboyLogger.getAppboyLogTag(AppboyListAdapter.class);
    private final Set<String> mCardIdImpressions = new HashSet();
    private final Context mContext;

    public int getViewTypeCount() {
        return 5;
    }

    public AppboyListAdapter(Context context, int i, List<Card> list) {
        super(context, i, list);
        this.mContext = context;
    }

    public int getItemViewType(int i) {
        Card card = (Card) getItem(i);
        if (card instanceof BannerImageCard) {
            return 1;
        }
        if (card instanceof CaptionedImageCard) {
            return 2;
        }
        if (card instanceof ShortNewsCard) {
            return 3;
        }
        return card instanceof TextAnnouncementCard ? 4 : 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        BaseFeedCardView baseFeedCardView;
        Card card = (Card) getItem(i);
        if (view != null) {
            String str = TAG;
            AppboyLogger.v(str, "Reusing convertView for rendering of item " + i);
            baseFeedCardView = (BaseFeedCardView) view;
        } else if (card instanceof BannerImageCard) {
            baseFeedCardView = new BannerImageCardView(this.mContext);
        } else if (card instanceof CaptionedImageCard) {
            baseFeedCardView = new CaptionedImageCardView(this.mContext);
        } else if (card instanceof ShortNewsCard) {
            baseFeedCardView = new ShortNewsCardView(this.mContext);
        } else if (card instanceof TextAnnouncementCard) {
            baseFeedCardView = new TextAnnouncementCardView(this.mContext);
        } else {
            baseFeedCardView = new DefaultCardView(this.mContext);
        }
        String str2 = TAG;
        AppboyLogger.v(str2, "Using view of type: " + baseFeedCardView.getClass().getName() + " for card at position " + i + ": " + card.toString());
        baseFeedCardView.setCard(card);
        logCardImpression(card);
        return baseFeedCardView;
    }

    public synchronized void replaceFeed(List<Card> list) {
        int i = 0;
        setNotifyOnChange(false);
        if (list == null) {
            clear();
            notifyDataSetChanged();
            return;
        }
        String str = TAG;
        AppboyLogger.d(str, "Replacing existing feed of " + getCount() + " cards with new feed containing " + list.size() + " cards.");
        int size = list.size();
        int i2 = 0;
        while (i < getCount()) {
            Card card = (Card) getItem(i);
            Card card2 = null;
            if (i2 < size) {
                card2 = list.get(i2);
            }
            if (card2 == null || !card2.isEqualToCard(card)) {
                remove(card);
            } else {
                i++;
                i2++;
            }
        }
        super.addAll(list.subList(i2, size));
        notifyDataSetChanged();
    }

    public synchronized void add(Card card) {
        super.add(card);
    }

    public void resetCardImpressionTracker() {
        this.mCardIdImpressions.clear();
    }

    private void logCardImpression(Card card) {
        String id = card.getId();
        if (!this.mCardIdImpressions.contains(id)) {
            this.mCardIdImpressions.add(id);
            card.logImpression();
            String str = TAG;
            AppboyLogger.v(str, "Logged impression for card " + id);
        } else {
            String str2 = TAG;
            AppboyLogger.v(str2, "Already counted impression for card " + id);
        }
        if (!card.getViewed()) {
            card.setViewed(true);
        }
    }

    public void batchSetCardsToRead(int i, int i2) {
        if (getCount() == 0) {
            AppboyLogger.d(TAG, "mAdapter is empty in setting some cards to viewed.");
            return;
        }
        int min = Math.min(getCount(), i2);
        for (int max = Math.max(0, i); max < min; max++) {
            Card card = (Card) getItem(max);
            if (card == null) {
                AppboyLogger.d(TAG, "Card was null in setting some cards to viewed.");
                return;
            }
            if (!card.isIndicatorHighlighted()) {
                card.setIndicatorHighlighted(true);
            }
        }
    }
}
