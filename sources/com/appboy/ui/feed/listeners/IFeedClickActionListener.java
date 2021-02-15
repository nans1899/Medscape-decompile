package com.appboy.ui.feed.listeners;

import android.content.Context;
import com.appboy.models.cards.Card;
import com.appboy.ui.actions.IAction;

public interface IFeedClickActionListener {
    boolean onFeedCardClicked(Context context, Card card, IAction iAction);
}
