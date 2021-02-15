package com.webmd.wbmdcmepulse.models.articles;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class ActivityTest implements Parcelable {
    public static final Parcelable.Creator<ActivityTest> CREATOR = new Parcelable.Creator<ActivityTest>() {
        public ActivityTest createFromParcel(Parcel parcel) {
            return new ActivityTest(parcel);
        }

        public ActivityTest[] newArray(int i) {
            return new ActivityTest[i];
        }
    };
    public static final String FORMAT_TYPE_EVAL = "EVAL";
    public static final String FORMAT_TYPE_INTERNAL = "INTERNAL";
    public static final String FORMAT_TYPE_MISCELLANEOUS = "MISCELLANEOUS";
    public static final String FORMAT_TYPE_NONE = "NO_FORMAT";
    public static final String FORMAT_TYPE_POST = "POST";
    public static final String FORMAT_TYPE_PRE = "pre";
    public static final String PRE_TEST_TYPE_ASSESSMENT = "pre-assessment";
    public static final String PRE_TEST_TYPE_LLA = "lla";
    public static final String PRE_TEST_TYPE_plla = "plla";
    public String formatType;
    public String id;
    public boolean isPassed;
    public boolean isScorable;
    public List<Question> questions;
    public String title;
    public String type;

    public int describeContents() {
        return 0;
    }

    public ActivityTest() {
        this.questions = new ArrayList();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.type);
        parcel.writeTypedList(this.questions);
        parcel.writeString(this.id);
        parcel.writeByte(this.isScorable ? (byte) 1 : 0);
        parcel.writeByte(this.isPassed ? (byte) 1 : 0);
        parcel.writeString(this.title);
        parcel.writeString(this.formatType);
    }

    protected ActivityTest(Parcel parcel) {
        this.type = parcel.readString();
        this.questions = parcel.createTypedArrayList(Question.CREATOR);
        this.id = parcel.readString();
        boolean z = true;
        this.isScorable = parcel.readByte() != 0;
        this.isPassed = parcel.readByte() == 0 ? false : z;
        this.title = parcel.readString();
        this.formatType = parcel.readString();
    }
}
