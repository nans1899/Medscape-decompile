package com.appboy.ui.contentcards.listeners;

import android.content.Context;
import com.appboy.models.cards.Card;
import com.appboy.ui.actions.IAction;

public class AppboyContentCardsActionListener implements IContentCardsActionListener {
    public boolean onContentCardClicked(Context context, Card card, IAction iAction) {
        return false;
    }

    public void onContentCardDismissed(Context context, Card card) {
    }
}
