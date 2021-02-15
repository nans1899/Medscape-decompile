package com.appboy.ui.contentcards.handlers;

import android.content.Context;
import android.view.ViewGroup;
import com.appboy.models.cards.Card;
import com.appboy.ui.contentcards.view.ContentCardViewHolder;
import java.util.List;

public interface IContentCardsViewBindingHandler {
    int getItemViewType(Context context, List<Card> list, int i);

    void onBindViewHolder(Context context, List<Card> list, ContentCardViewHolder contentCardViewHolder, int i);

    ContentCardViewHolder onCreateViewHolder(Context context, List<Card> list, ViewGroup viewGroup, int i);
}
