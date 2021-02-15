package com.webmd.wbmdcmepulse.models.articles;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class ArticlePage implements Parcelable {
    public static final Parcelable.Creator<ArticlePage> CREATOR = new Parcelable.Creator<ArticlePage>() {
        public ArticlePage createFromParcel(Parcel parcel) {
            return new ArticlePage(parcel);
        }

        public ArticlePage[] newArray(int i) {
            return new ArticlePage[i];
        }
    };
    public List<Section> sections;

    public int describeContents() {
        return 0;
    }

    public ArticlePage() {
        this.sections = new ArrayList();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.sections);
    }

    protected ArticlePage(Parcel parcel) {
        this.sections = parcel.createTypedArrayList(Section.CREATOR);
    }
}
