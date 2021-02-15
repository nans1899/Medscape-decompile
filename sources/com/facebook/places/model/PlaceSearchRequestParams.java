package com.facebook.places.model;

import java.util.HashSet;
import java.util.Set;

public final class PlaceSearchRequestParams {
    private final Set<String> categories;
    private final int distance;
    private final Set<String> fields;
    private final int limit;
    private final String searchText;

    private PlaceSearchRequestParams(Builder builder) {
        this.categories = new HashSet();
        this.fields = new HashSet();
        this.distance = builder.distance;
        this.limit = builder.limit;
        this.searchText = builder.searchText;
        this.categories.addAll(builder.categories);
        this.fields.addAll(builder.fields);
    }

    public int getDistance() {
        return this.distance;
    }

    public int getLimit() {
        return this.limit;
    }

    public String getSearchText() {
        return this.searchText;
    }

    public Set<String> getCategories() {
        return this.categories;
    }

    public Set<String> getFields() {
        return this.fields;
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public final Set<String> categories = new HashSet();
        /* access modifiers changed from: private */
        public int distance;
        /* access modifiers changed from: private */
        public final Set<String> fields = new HashSet();
        /* access modifiers changed from: private */
        public int limit;
        /* access modifiers changed from: private */
        public String searchText;

        public Builder setDistance(int i) {
            this.distance = i;
            return this;
        }

        public Builder setLimit(int i) {
            this.limit = i;
            return this;
        }

        public Builder setSearchText(String str) {
            this.searchText = str;
            return this;
        }

        public Builder addCategory(String str) {
            this.categories.add(str);
            return this;
        }

        public Builder addField(String str) {
            this.fields.add(str);
            return this;
        }

        public PlaceSearchRequestParams build() {
            return new PlaceSearchRequestParams(this);
        }
    }
}
