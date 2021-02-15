package com.medscape.android.activity.search.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchSuggestion implements Parcelable {
    public static final Parcelable.Creator<SearchSuggestion> CREATOR = new Parcelable.Creator<SearchSuggestion>() {
        public SearchSuggestion createFromParcel(Parcel parcel) {
            return new SearchSuggestion(parcel);
        }

        public SearchSuggestion[] newArray(int i) {
            return new SearchSuggestion[i];
        }
    };
    private int hits;
    private String query;

    public int describeContents() {
        return 0;
    }

    public SearchSuggestion() {
    }

    public String getQuery() {
        return this.query;
    }

    public void setQuery(String str) {
        this.query = str;
    }

    public int getHits() {
        return this.hits;
    }

    public void setHits(int i) {
        this.hits = i;
    }

    protected SearchSuggestion(Parcel parcel) {
        this.query = parcel.readString();
        this.hits = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.query);
        parcel.writeInt(this.hits);
    }
}
