package com.webmd.wbmdcmepulse.models.articles;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class Questionnaire implements Parcelable {
    public static final Parcelable.Creator<Questionnaire> CREATOR = new Parcelable.Creator<Questionnaire>() {
        public Questionnaire createFromParcel(Parcel parcel) {
            return new Questionnaire(parcel);
        }

        public Questionnaire[] newArray(int i) {
            return new Questionnaire[i];
        }
    };
    public boolean evaluationCompleted;
    public String expirationDate;
    public String failMessage;
    public String formId;
    public String id;
    public double initialParsedAnswersScore;
    public boolean isGated;
    public boolean isPassed;
    public String passMessage;
    public double passScore;
    Boolean shouldGoToEvaluationScreen;
    public List<ActivityTest> tests;
    public String title;

    public int describeContents() {
        return 0;
    }

    public Questionnaire() {
        this.tests = new ArrayList();
        this.shouldGoToEvaluationScreen = null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.title);
        parcel.writeDouble(this.passScore);
        parcel.writeString(this.passMessage);
        parcel.writeTypedList(this.tests);
        parcel.writeString(this.failMessage);
        parcel.writeByte(this.isGated ? (byte) 1 : 0);
        parcel.writeByte(this.evaluationCompleted ? (byte) 1 : 0);
        parcel.writeString(this.expirationDate);
        parcel.writeString(this.formId);
        parcel.writeByte(this.isPassed ? (byte) 1 : 0);
        parcel.writeDouble(this.initialParsedAnswersScore);
        Boolean bool = this.shouldGoToEvaluationScreen;
        if (bool == null) {
            parcel.writeByte((byte) 2);
        } else {
            parcel.writeByte(bool.booleanValue() ? (byte) 1 : 0);
        }
    }

    protected Questionnaire(Parcel parcel) {
        Boolean bool;
        this.id = parcel.readString();
        this.title = parcel.readString();
        this.passScore = parcel.readDouble();
        this.passMessage = parcel.readString();
        this.tests = parcel.createTypedArrayList(ActivityTest.CREATOR);
        this.failMessage = parcel.readString();
        boolean z = true;
        this.isGated = parcel.readByte() != 0;
        this.evaluationCompleted = parcel.readByte() != 0;
        this.expirationDate = parcel.readString();
        this.formId = parcel.readString();
        this.isPassed = parcel.readByte() != 0;
        this.initialParsedAnswersScore = parcel.readDouble();
        byte readByte = parcel.readByte();
        if (readByte == 2) {
            bool = null;
        } else {
            bool = Boolean.valueOf(readByte == 0 ? false : z);
        }
        this.shouldGoToEvaluationScreen = bool;
    }

    public void checkIfEvalScreen(String str) {
        Boolean bool = this.shouldGoToEvaluationScreen;
        if (bool == null || bool.booleanValue()) {
            this.shouldGoToEvaluationScreen = Boolean.valueOf(str != null && (str.equals(ActivityTest.FORMAT_TYPE_EVAL) || str.equals(ActivityTest.FORMAT_TYPE_MISCELLANEOUS)));
        }
    }

    public boolean getShouldGoToEvaluation() {
        Boolean bool = this.shouldGoToEvaluationScreen;
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }
}
