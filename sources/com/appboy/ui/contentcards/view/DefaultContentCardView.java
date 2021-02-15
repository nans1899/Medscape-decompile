package com.appboy.ui.contentcards.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.appboy.models.cards.Card;
import com.appboy.ui.R;

public class DefaultContentCardView extends BaseContentCardView<Card> {
    public void bindViewHolder(ContentCardViewHolder contentCardViewHolder, Card card) {
    }

    public DefaultContentCardView(Context context) {
        super(context);
    }

    public ContentCardViewHolder createViewHolder(ViewGroup viewGroup) {
        return new ContentCardViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.com_appboy_default_content_card, viewGroup, false), false);
    }
}
