package com.medscape.android.activity.search;

import android.content.SearchRecentSuggestionsProvider;

public class RecentlyViewedItemsSuggestionsProvider extends SearchRecentSuggestionsProvider {
    public static final String AUTHORITY = "com.medscape.android.activity.search.RecentlyViewedItemsSuggestionsProvider";
    public static final int MODE = 3;

    public RecentlyViewedItemsSuggestionsProvider() {
        setupSuggestions(AUTHORITY, 3);
    }
}
