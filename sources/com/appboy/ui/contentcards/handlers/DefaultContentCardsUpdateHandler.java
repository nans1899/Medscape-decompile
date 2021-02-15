package com.appboy.ui.contentcards.handlers;

import com.appboy.events.ContentCardsUpdatedEvent;
import com.appboy.models.cards.Card;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DefaultContentCardsUpdateHandler implements IContentCardsUpdateHandler {
    public List<Card> handleCardUpdate(ContentCardsUpdatedEvent contentCardsUpdatedEvent) {
        List<Card> allCards = contentCardsUpdatedEvent.getAllCards();
        Collections.sort(allCards, new Comparator<Card>() {
            public int compare(Card card, Card card2) {
                if (card.getIsPinned() && !card2.getIsPinned()) {
                    return -1;
                }
                if (!card.getIsPinned() && card2.getIsPinned()) {
                    return 1;
                }
                if (card.getUpdated() > card2.getUpdated()) {
                    return -1;
                }
                if (card.getUpdated() < card2.getUpdated()) {
                    return 1;
                }
                return 0;
            }
        });
        return allCards;
    }
}
