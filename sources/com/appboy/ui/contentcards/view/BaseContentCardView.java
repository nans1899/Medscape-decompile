package com.appboy.ui.contentcards.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.appboy.models.cards.Card;
import com.appboy.ui.actions.IAction;
import com.appboy.ui.actions.UriAction;
import com.appboy.ui.contentcards.AppboyContentCardsManager;
import com.appboy.ui.widget.BaseCardView;

public abstract class BaseContentCardView<T extends Card> extends BaseCardView<T> {
    public abstract ContentCardViewHolder createViewHolder(ViewGroup viewGroup);

    public BaseContentCardView(Context context) {
        super(context);
    }

    public void bindViewHolder(ContentCardViewHolder contentCardViewHolder, final T t) {
        contentCardViewHolder.setPinnedIconVisible(t.getIsPinned());
        boolean z = true;
        contentCardViewHolder.setUnreadBarVisible(this.mAppboyConfigurationProvider.isContentCardsUnreadVisualIndicatorEnabled() && !t.isIndicatorHighlighted());
        final UriAction uriActionForCard = getUriActionForCard(t);
        contentCardViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BaseContentCardView baseContentCardView = BaseContentCardView.this;
                baseContentCardView.handleCardClick(baseContentCardView.mContext, t, uriActionForCard, BaseContentCardView.this.getClassLogTag());
            }
        });
        if (uriActionForCard == null) {
            z = false;
        }
        contentCardViewHolder.setActionHintVisible(z);
    }

    public void setOptionalCardImage(ImageView imageView, float f, String str, float f2) {
        boolean z;
        if (f != 0.0f) {
            z = true;
        } else {
            f = f2;
            z = false;
        }
        if (imageView != null) {
            setImageViewToUrl(imageView, str, f, z);
        }
    }

    /* access modifiers changed from: protected */
    public boolean isClickHandled(Context context, Card card, IAction iAction) {
        return AppboyContentCardsManager.getInstance().getContentCardsActionListener().onContentCardClicked(context, card, iAction);
    }

    /* access modifiers changed from: protected */
    public void safeSetClipToOutline(ImageView imageView) {
        if (imageView != null) {
            imageView.setClipToOutline(true);
        }
    }
}
