package com.webmd.wbmdcmepulse.models.articles;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class Question implements Parcelable {
    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        public Question createFromParcel(Parcel parcel) {
            return new Question(parcel);
        }

        public Question[] newArray(int i) {
            return new Question[i];
        }
    };
    public static final String DISPLAY_TYPE_RADIO_BUTTON = "RadioButton";
    public List<Answer> answers;
    public String displayRule;
    public String feedback;
    public String formatType;
    public int id;
    public boolean isPassed;
    public boolean isPoll;
    public boolean isRequired;
    public boolean isResponded;
    public boolean isScorable;
    public boolean isSelected;
    public boolean isViewResults;
    public String pageIntro;
    public String questionIntro;
    public String questionText;
    public boolean showAnswersTable;
    public int totalResponse;
    public String userTypeName;

    public int describeContents() {
        return 0;
    }

    public Question() {
        this.answers = new ArrayList();
    }

    public int getCorrectAnswerIndex() {
        for (int i = 0; i < this.answers.size(); i++) {
            if (this.answers.get(i).isCorrect) {
                return i;
            }
        }
        return -1;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.pageIntro);
        parcel.writeString(this.questionText);
        parcel.writeString(this.questionIntro);
        parcel.writeString(this.displayRule);
        parcel.writeString(this.userTypeName);
        parcel.writeString(this.feedback);
        parcel.writeByte(this.isResponded ? (byte) 1 : 0);
        parcel.writeInt(this.totalResponse);
        parcel.writeByte(this.isPassed ? (byte) 1 : 0);
        parcel.writeByte(this.isSelected ? (byte) 1 : 0);
        parcel.writeByte(this.isViewResults ? (byte) 1 : 0);
        parcel.writeByte(this.isRequired ? (byte) 1 : 0);
        parcel.writeByte(this.isScorable ? (byte) 1 : 0);
        parcel.writeString(this.formatType);
        parcel.writeByte(this.isPoll ? (byte) 1 : 0);
        parcel.writeByte(this.showAnswersTable ? (byte) 1 : 0);
        parcel.writeTypedList(this.answers);
    }

    protected Question(Parcel parcel) {
        this.id = parcel.readInt();
        this.pageIntro = parcel.readString();
        this.questionText = parcel.readString();
        this.questionIntro = parcel.readString();
        this.displayRule = parcel.readString();
        this.userTypeName = parcel.readString();
        this.feedback = parcel.readString();
        boolean z = true;
        this.isResponded = parcel.readByte() != 0;
        this.totalResponse = parcel.readInt();
        this.isPassed = parcel.readByte() != 0;
        this.isSelected = parcel.readByte() != 0;
        this.isViewResults = parcel.readByte() != 0;
        this.isRequired = parcel.readByte() != 0;
        this.isScorable = parcel.readByte() != 0;
        this.formatType = parcel.readString();
        this.isPoll = parcel.readByte() != 0;
        this.showAnswersTable = parcel.readByte() == 0 ? false : z;
        this.answers = parcel.createTypedArrayList(Answer.CREATOR);
    }
}
