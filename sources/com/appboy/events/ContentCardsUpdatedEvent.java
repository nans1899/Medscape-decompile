package com.appboy.events;

import com.appboy.models.cards.Card;
import com.dd.plist.ASCIIPropertyListParser;
import java.util.List;

public class ContentCardsUpdatedEvent {
    private final List<Card> a;
    private final String b;
    private final long c;
    private final boolean d;

    public ContentCardsUpdatedEvent(List<Card> list, String str, long j, boolean z) {
        this.b = str;
        this.a = list;
        this.c = j;
        this.d = z;
    }

    public boolean isFromOfflineStorage() {
        return this.d;
    }

    public List<Card> getAllCards() {
        return this.a;
    }

    public String getUserId() {
        return this.b;
    }

    public int getCardCount() {
        return this.a.size();
    }

    public int getUnviewedCardCount() {
        int i = 0;
        for (Card viewed : this.a) {
            if (!viewed.getViewed()) {
                i++;
            }
        }
        return i;
    }

    public boolean isEmpty() {
        return this.a.isEmpty();
    }

    public long getLastUpdatedInSecondsFromEpoch() {
        return this.c;
    }

    public String toString() {
        return "ContentCardsUpdatedEvent{mContentCards=" + this.a + ", mUserId='" + this.b + '\'' + ", mTimestamp=" + this.c + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
    }
}
