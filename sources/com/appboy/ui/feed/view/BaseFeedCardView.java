package com.appboy.ui.feed.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.ViewSwitcher;
import com.appboy.models.cards.Card;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.R;
import com.appboy.ui.actions.IAction;
import com.appboy.ui.feed.AppboyFeedManager;
import com.appboy.ui.feed.AppboyImageSwitcher;
import com.appboy.ui.widget.BaseCardView;
import java.util.Observable;
import java.util.Observer;

public abstract class BaseFeedCardView<T extends Card> extends BaseCardView<T> implements Observer {
    private static final String TAG = AppboyLogger.getAppboyLogTag(BaseCardView.class);

    /* access modifiers changed from: protected */
    public abstract int getLayoutResource();

    /* access modifiers changed from: protected */
    public abstract void onSetCard(T t);

    public BaseFeedCardView(Context context) {
        super(context);
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(getLayoutResource(), this);
        this.mImageSwitcher = (AppboyImageSwitcher) findViewById(R.id.com_appboy_newsfeed_item_read_indicator_image_switcher);
        if (this.mImageSwitcher != null) {
            this.mImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                public View makeView() {
                    return new ImageView(BaseFeedCardView.this.mContext.getApplicationContext());
                }
            });
        }
        if (!isUnreadIndicatorEnabled() && this.mImageSwitcher != null) {
            this.mImageSwitcher.setVisibility(8);
        }
    }

    public View getProperViewFromInflatedStub(int i) {
        ((ViewStub) findViewById(i)).inflate();
        return findViewById(R.id.com_appboy_stubbed_feed_image_view);
    }

    public void update(Observable observable, Object obj) {
        setCardViewedIndicator(this.mImageSwitcher, getCard());
    }

    public void setCard(T t) {
        this.mCard = t;
        onSetCard(t);
        t.addObserver(this);
        setCardViewedIndicator(this.mImageSwitcher, getCard());
    }

    public Card getCard() {
        return this.mCard;
    }

    /* access modifiers changed from: protected */
    public boolean isClickHandled(Context context, Card card, IAction iAction) {
        return AppboyFeedManager.getInstance().getFeedCardClickActionListener().onFeedCardClicked(context, card, iAction);
    }
}
