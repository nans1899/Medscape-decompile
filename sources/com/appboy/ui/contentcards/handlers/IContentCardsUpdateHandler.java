package com.appboy.ui.contentcards.handlers;

import com.appboy.events.ContentCardsUpdatedEvent;
import com.appboy.models.cards.Card;
import java.util.List;

public interface IContentCardsUpdateHandler {
    List<Card> handleCardUpdate(ContentCardsUpdatedEvent contentCardsUpdatedEvent);
}
