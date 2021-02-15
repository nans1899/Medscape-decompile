package com.appboy.ui.contentcards.listeners;

import android.content.Context;
import com.appboy.models.cards.Card;
import com.appboy.ui.actions.IAction;

public interface IContentCardsActionListener {
    boolean onContentCardClicked(Context context, Card card, IAction iAction);

    void onContentCardDismissed(Context context, Card card);
}
