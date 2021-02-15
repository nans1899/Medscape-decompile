package com.appboy.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public enum CardCategory {
    ADVERTISING,
    ANNOUNCEMENTS,
    NEWS,
    SOCIAL,
    NO_CATEGORY;
    
    private static final Map<String, CardCategory> a = null;

    static {
        a = new HashMap();
        Iterator it = EnumSet.allOf(CardCategory.class).iterator();
        while (it.hasNext()) {
            CardCategory cardCategory = (CardCategory) it.next();
            a.put(cardCategory.toString(), cardCategory);
        }
    }

    public static CardCategory get(String str) {
        return a.get(str.toUpperCase(Locale.US));
    }

    public static EnumSet<CardCategory> getAllCategories() {
        return EnumSet.allOf(CardCategory.class);
    }
}
