package com.appboy.events;

import com.appboy.enums.CardCategory;
import com.appboy.models.cards.Card;
import com.appboy.support.AppboyLogger;
import com.dd.plist.ASCIIPropertyListParser;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public final class FeedUpdatedEvent {
    private static final String a = AppboyLogger.getAppboyLogTag(FeedUpdatedEvent.class);
    private final List<Card> b;
    private final String c;
    private final boolean d;
    private final long e;

    public FeedUpdatedEvent(List<Card> list, String str, boolean z, long j) {
        this.c = str;
        this.d = z;
        if (list != null) {
            this.b = list;
            this.e = j;
            return;
        }
        throw null;
    }

    public boolean isFromOfflineStorage() {
        return this.d;
    }

    public List<Card> getFeedCards() {
        return getFeedCards(CardCategory.getAllCategories());
    }

    public List<Card> getFeedCards(CardCategory cardCategory) {
        return getFeedCards((EnumSet<CardCategory>) EnumSet.of(cardCategory));
    }

    public List<Card> getFeedCards(EnumSet<CardCategory> enumSet) {
        if (enumSet == null) {
            try {
                AppboyLogger.i(a, "The categories passed to getFeedCards are null, FeedUpdatedEvent is going to return all the cards in cache.");
                enumSet = CardCategory.getAllCategories();
            } catch (Exception e2) {
                String str = a;
                AppboyLogger.w(str, "Unable to get cards with categories[" + enumSet + "]. Ignoring.", e2);
                return null;
            }
        }
        if (enumSet.isEmpty()) {
            AppboyLogger.w(a, "The parameter passed into categories is not valid, Braze is returning an empty card list.Please pass in a non-empty EnumSet of CardCategory for getFeedCards().");
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (Card next : this.b) {
            if (next.isInCategorySet(enumSet) && !next.isExpired()) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public String getUserId() {
        return this.c;
    }

    public int getCardCount() {
        return getCardCount(CardCategory.getAllCategories());
    }

    public int getCardCount(CardCategory cardCategory) {
        return getCardCount((EnumSet<CardCategory>) EnumSet.of(cardCategory));
    }

    public int getCardCount(EnumSet<CardCategory> enumSet) {
        if (enumSet == null) {
            AppboyLogger.i(a, "The categories passed into getCardCount are null, FeedUpdatedEvent is going to return the count of all the cards in cache.");
            return this.b.size();
        } else if (!enumSet.isEmpty()) {
            return getFeedCards(enumSet).size();
        } else {
            AppboyLogger.w(a, "The parameters passed into categories are not valid, Braze is returning 0 in getCardCount().Please pass in a non-empty EnumSet of CardCategory.");
            return 0;
        }
    }

    public int getUnreadCardCount() {
        return getUnreadCardCount(CardCategory.getAllCategories());
    }

    public int getUnreadCardCount(CardCategory cardCategory) {
        return getUnreadCardCount((EnumSet<CardCategory>) EnumSet.of(cardCategory));
    }

    public int getUnreadCardCount(EnumSet<CardCategory> enumSet) {
        if (enumSet == null) {
            AppboyLogger.w(a, "The categories passed to getUnreadCardCount are null, FeedUpdatedEvent is going to return the count of all the unread cards in cache.");
            return getUnreadCardCount(CardCategory.getAllCategories());
        }
        int i = 0;
        if (enumSet.isEmpty()) {
            AppboyLogger.w(a, "The parameters passed into categories are Empty, Braze is returning 0 in getUnreadCardCount().Please pass in a non-empty EnumSet of CardCategory.");
            return 0;
        }
        for (Card next : this.b) {
            if (next.isInCategorySet(enumSet) && !next.getViewed() && !next.isExpired()) {
                i++;
            }
        }
        return i;
    }

    public long lastUpdatedInSecondsFromEpoch() {
        return this.e;
    }

    public String toString() {
        return "FeedUpdatedEvent{" + "mFeedCards=" + this.b + ", mUserId='" + this.c + '\'' + ", mFromOfflineStorage=" + this.d + ", mTimestamp=" + this.e + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
    }
}
