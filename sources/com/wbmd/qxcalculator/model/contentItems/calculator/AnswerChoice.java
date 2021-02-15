package com.wbmd.qxcalculator.model.contentItems.calculator;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import android.util.JsonWriter;
import com.facebook.internal.AnalyticsEvents;
import com.wbmd.qxcalculator.managers.UserManager;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.contentItems.calculator.Unit;
import com.wbmd.qxcalculator.model.db.DBAnswerChoice;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class AnswerChoice implements Parcelable {
    public static final Parcelable.Creator<AnswerChoice> CREATOR = new Parcelable.Creator<AnswerChoice>() {
        public AnswerChoice createFromParcel(Parcel parcel) {
            AnswerChoice answerChoice = new AnswerChoice();
            answerChoice.identifier = parcel.readString();
            answerChoice.titlePrimary = parcel.readString();
            answerChoice.titleSecondary = (String) parcel.readValue(String.class.getClassLoader());
            answerChoice.answerFactor = Double.valueOf(parcel.readDouble());
            answerChoice.unitIndependent = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            answerChoice.isSelected = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            answerChoice.successMessage = (String) parcel.readValue(String.class.getClassLoader());
            answerChoice.warningMessage = (String) parcel.readValue(String.class.getClassLoader());
            answerChoice.errorMessage = (String) parcel.readValue(String.class.getClassLoader());
            return answerChoice;
        }

        public AnswerChoice[] newArray(int i) {
            return new AnswerChoice[i];
        }
    };
    public Double answerFactor;
    public String errorMessage;
    public String identifier;
    public Boolean isSelected;
    public String successMessage;
    public String titlePrimary;
    public String titleSecondary;
    public Boolean unitIndependent;
    public String warningMessage;

    public int describeContents() {
        return 0;
    }

    public static ArrayList<AnswerChoice> answerChoices(List<DBAnswerChoice> list) {
        if (list == null) {
            return null;
        }
        ArrayList<AnswerChoice> arrayList = new ArrayList<>(list.size());
        for (DBAnswerChoice answerChoice : list) {
            arrayList.add(new AnswerChoice(answerChoice));
        }
        return arrayList;
    }

    public AnswerChoice(DBAnswerChoice dBAnswerChoice) {
        if (dBAnswerChoice != null) {
            this.identifier = dBAnswerChoice.getIdentifier();
            this.titlePrimary = dBAnswerChoice.getTitlePrimary();
            this.titleSecondary = dBAnswerChoice.getTitleSecondary();
            this.answerFactor = dBAnswerChoice.getAnswerFactor();
            this.unitIndependent = dBAnswerChoice.getUnitIndependent();
            this.successMessage = dBAnswerChoice.getSuccessMessage();
            this.warningMessage = dBAnswerChoice.getWarningMessage();
            this.errorMessage = dBAnswerChoice.getErrorMessage();
        }
    }

    public AnswerChoice() {
        this((DBAnswerChoice) null);
    }

    public String getTitlePrimary() {
        Boolean bool;
        if (Unit.UnitType.convertStringToType(UserManager.getInstance().getDbUser().getDefaultUnits()).equals(Unit.UnitType.SI_UNITS) || ((bool = this.unitIndependent) != null && bool.booleanValue())) {
            return this.titlePrimary;
        }
        return this.titleSecondary;
    }

    public String getTitleSecondary() {
        Boolean bool;
        if (Unit.UnitType.convertStringToType(UserManager.getInstance().getDbUser().getDefaultUnits()).equals(Unit.UnitType.SI_UNITS) || ((bool = this.unitIndependent) != null && bool.booleanValue())) {
            return this.titleSecondary;
        }
        return this.titlePrimary;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.identifier);
        parcel.writeString(this.titlePrimary);
        parcel.writeValue(this.titleSecondary);
        parcel.writeDouble(this.answerFactor.doubleValue());
        parcel.writeValue(this.unitIndependent);
        parcel.writeValue(this.isSelected);
        parcel.writeValue(this.successMessage);
        parcel.writeValue(this.warningMessage);
        parcel.writeValue(this.errorMessage);
    }

    public static List<AnswerChoice> convertJsonToAnswerChoices(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToAnswerChoice(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static AnswerChoice convertJsonToAnswerChoice(JsonReader jsonReader) throws IOException, ParseException {
        AnswerChoice answerChoice = new AnswerChoice();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("id")) {
                answerChoice.identifier = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("title_primary")) {
                answerChoice.titlePrimary = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("title_secondary")) {
                answerChoice.titleSecondary = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("answer_factor")) {
                answerChoice.answerFactor = APIParser.readDouble(jsonReader);
            } else if (nextName.equalsIgnoreCase("unit_independent")) {
                answerChoice.unitIndependent = APIParser.readBoolean(jsonReader);
            } else if (nextName.equalsIgnoreCase("success_message")) {
                answerChoice.successMessage = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("warning_message")) {
                answerChoice.warningMessage = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase(AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE)) {
                answerChoice.errorMessage = APIParser.readString(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return answerChoice;
    }

    public static void convertToJson(JsonWriter jsonWriter, List<AnswerChoice> list) throws IOException {
        jsonWriter.beginArray();
        for (AnswerChoice next : list) {
            jsonWriter.beginObject();
            jsonWriter.name("id").value(next.identifier);
            jsonWriter.name("title_primary").value(next.titlePrimary);
            jsonWriter.name("title_secondary").value(next.titleSecondary);
            jsonWriter.name("answer_factor").value(next.answerFactor);
            jsonWriter.endObject();
        }
        jsonWriter.endArray();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AnswerChoice)) {
            return false;
        }
        String str = this.identifier;
        String str2 = ((AnswerChoice) obj).identifier;
        if (str != null) {
            return str.equals(str2);
        }
        if (str2 == null) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "AnswerChoice [id=" + this.identifier + "; titlePrimary=" + this.titlePrimary + ", titleSecondary=" + this.titleSecondary + ", answerFactor=" + this.answerFactor + "]";
    }
}
