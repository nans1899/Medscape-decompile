package com.medscape.android.activity.search;

public enum SearchMode {
    NONE(-1, "None"),
    SEARCH_REFERENCE(0, "Search Reference"),
    SEARCH_NEWS(1, "Search News"),
    SEARCH_EDUCATION(2, "Search Education"),
    SEARCH_MEDLINE(3, "Search MEDLINE"),
    SEARCH_DRUGS(5, "Search Drugs"),
    SEARCH_CONDITIONS(6, "Search Conditions"),
    SEARCH_PROCEDURES(7, "Search Procedures"),
    SEARCH_CALCULATORS(8, "Search Calculators"),
    SEARCH_PRICING(9, "Search Pricing & Savings");
    
    private final String hint;
    private final int id;

    private SearchMode(int i, String str) {
        this.id = i;
        this.hint = str;
    }

    public int getId() {
        return this.id;
    }

    public String getHint() {
        return this.hint;
    }

    public static SearchMode fromId(int i) {
        for (SearchMode searchMode : values()) {
            if (searchMode.getId() == i) {
                return searchMode;
            }
        }
        return NONE;
    }
}
