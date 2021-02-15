package com.medscape.android.activity.search.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class SearchSuggestionMsg implements Parcelable {
    public static final int AUTO_CORRECT_SEARCH = 3;
    public static final Parcelable.Creator<SearchSuggestionMsg> CREATOR = new Parcelable.Creator<SearchSuggestionMsg>() {
        public SearchSuggestionMsg createFromParcel(Parcel parcel) {
            return new SearchSuggestionMsg(parcel);
        }

        public SearchSuggestionMsg[] newArray(int i) {
            return new SearchSuggestionMsg[i];
        }
    };
    public static final int DEFAULT_SEARCH = 0;
    public static final int DID_YOU_MEAN = 4;
    public static final int DID_YOU_MEAN_NO_RESULTS = 2;
    public static final int NO_RESULTS = 1;
    String autoCorrectedQuery;
    int suggestionMode;
    ArrayList<String> suggestions;
    String userQuery;

    public int describeContents() {
        return 0;
    }

    public SearchSuggestionMsg() {
    }

    protected SearchSuggestionMsg(Parcel parcel) {
        if (parcel.readByte() == 1) {
            ArrayList<String> arrayList = new ArrayList<>();
            this.suggestions = arrayList;
            parcel.readList(arrayList, String.class.getClassLoader());
        } else {
            this.suggestions = null;
        }
        this.userQuery = parcel.readString();
        this.autoCorrectedQuery = parcel.readString();
        this.suggestionMode = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.suggestions == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeList(this.suggestions);
        }
        parcel.writeString(this.userQuery);
        parcel.writeString(this.autoCorrectedQuery);
        parcel.writeInt(this.suggestionMode);
    }

    public ArrayList<String> getSuggestions() {
        return this.suggestions;
    }

    public void setSuggestions(ArrayList<String> arrayList) {
        this.suggestions = arrayList;
    }

    public String getUserQuery() {
        return this.userQuery;
    }

    public void setUserQuery(String str) {
        this.userQuery = str;
    }

    public String getAutoCorrectedQuery() {
        return this.autoCorrectedQuery;
    }

    public void setAutoCorrectedQuery(String str) {
        this.autoCorrectedQuery = str;
    }

    public int getSuggestionMode() {
        return this.suggestionMode;
    }

    public void setSuggestionMode(int i) {
        this.suggestionMode = i;
    }
}
