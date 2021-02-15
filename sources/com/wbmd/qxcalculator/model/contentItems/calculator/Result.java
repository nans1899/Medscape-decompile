package com.wbmd.qxcalculator.model.contentItems.calculator;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import android.util.JsonWriter;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.db.DBResult;
import com.wbmd.qxcalculator.model.db.DBUser;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Result implements Parcelable {
    public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>() {
        public Result createFromParcel(Parcel parcel) {
            Result result = new Result();
            result.identifier = parcel.readString();
            result.orderedId = Long.valueOf(parcel.readLong());
            result.position = Integer.valueOf(parcel.readInt());
            result.type = parcel.readString();
            result.conditionFormula = (String) parcel.readValue(String.class.getClassLoader());
            result.conditionFormulaResult = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            result.title = (String) parcel.readValue(String.class.getClassLoader());
            result.titleFormula = (String) parcel.readValue(String.class.getClassLoader());
            result.titleFormulaResult = (String) parcel.readValue(String.class.getClassLoader());
            result.subTitle = (String) parcel.readValue(String.class.getClassLoader());
            result.subTitleFormula = (String) parcel.readValue(String.class.getClassLoader());
            result.subTitleFormulaResult = (String) parcel.readValue(String.class.getClassLoader());
            result.formula = (String) parcel.readValue(String.class.getClassLoader());
            result.formulaResult = (String) parcel.readValue(String.class.getClassLoader());
            result.answer = (String) parcel.readValue(String.class.getClassLoader());
            result.answerResult = (String) parcel.readValue(String.class.getClassLoader());
            result.answerPrimary = (String) parcel.readValue(String.class.getClassLoader());
            result.answerSecondary = (String) parcel.readValue(String.class.getClassLoader());
            return result;
        }

        public Result[] newArray(int i) {
            return new Result[i];
        }
    };
    public String answer;
    public String answerPrimary;
    public String answerResult;
    public String answerSecondary;
    public String conditionFormula;
    public Boolean conditionFormulaResult;
    public String formula;
    public String formulaResult;
    public String identifier;
    public Long orderedId;
    public Integer position;
    public String subTitle;
    public String subTitleFormula;
    public String subTitleFormulaResult;
    public String title;
    public String titleFormula;
    public String titleFormulaResult;
    public String type;

    public enum ResultType {
        DEFAULT,
        CALCULATION,
        NO_ANSWER,
        NOTE_DEFAULT,
        NOTE_SECTION_TITLE,
        NOTE_WITH_TITLE_SUB_TITLE,
        NOTE_WITH_TITLE_SUB_TITLE_LEFT_JUSTIFIED,
        LINKED_CALCULATOR,
        BAXTER_RX_DOSE,
        CHART_IMAGE,
        UNKNOWN
    }

    public int describeContents() {
        return 0;
    }

    public void initializeResult(DBUser dBUser) {
    }

    public static ArrayList<Result> results(List<DBResult> list) {
        if (list == null) {
            return null;
        }
        ArrayList<Result> arrayList = new ArrayList<>(list.size());
        for (DBResult result : list) {
            arrayList.add(new Result(result));
        }
        return arrayList;
    }

    public Result(DBResult dBResult) {
        if (dBResult != null) {
            this.identifier = dBResult.getIdentifier();
            this.orderedId = dBResult.getOrderedId();
            this.position = dBResult.getPosition();
            this.type = dBResult.getType();
            this.conditionFormula = dBResult.getConditionFormula();
            this.title = dBResult.getTitle();
            this.titleFormula = dBResult.getTitleFormula();
            this.subTitle = dBResult.getSubTitle();
            this.subTitleFormula = dBResult.getSubTitleFormula();
            this.formula = dBResult.getFormula();
            this.answer = dBResult.getAnswer();
            this.answerPrimary = dBResult.getAnswerPrimary();
            this.answerSecondary = dBResult.getAnswerSecondary();
        }
    }

    public ResultType getResultType() {
        if (this.type.equalsIgnoreCase("default")) {
            return ResultType.DEFAULT;
        }
        if (this.type.equalsIgnoreCase("calculation")) {
            return ResultType.CALCULATION;
        }
        if (this.type.equalsIgnoreCase("no_answer")) {
            return ResultType.NO_ANSWER;
        }
        if (this.type.equalsIgnoreCase("note_default")) {
            return ResultType.NOTE_DEFAULT;
        }
        if (this.type.equalsIgnoreCase("note_section_title")) {
            return ResultType.NOTE_SECTION_TITLE;
        }
        if (this.type.equalsIgnoreCase("note_w_title_subtitle")) {
            return ResultType.NOTE_WITH_TITLE_SUB_TITLE;
        }
        if (this.type.equalsIgnoreCase("note_w_title_subtitle_left_justified")) {
            return ResultType.NOTE_WITH_TITLE_SUB_TITLE_LEFT_JUSTIFIED;
        }
        if (this.type.equalsIgnoreCase("linked_result")) {
            return ResultType.LINKED_CALCULATOR;
        }
        if (this.type.equalsIgnoreCase("baxter_rx")) {
            return ResultType.BAXTER_RX_DOSE;
        }
        if (this.type.equalsIgnoreCase("chart_image")) {
            return ResultType.CHART_IMAGE;
        }
        return ResultType.UNKNOWN;
    }

    public Result() {
        this((DBResult) null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.identifier);
        parcel.writeLong(this.orderedId.longValue());
        parcel.writeInt(this.position.intValue());
        parcel.writeString(this.type);
        parcel.writeValue(this.conditionFormula);
        parcel.writeValue(this.conditionFormulaResult);
        parcel.writeValue(this.title);
        parcel.writeValue(this.titleFormula);
        parcel.writeValue(this.titleFormulaResult);
        parcel.writeValue(this.subTitle);
        parcel.writeValue(this.subTitleFormula);
        parcel.writeValue(this.subTitleFormulaResult);
        parcel.writeValue(this.formula);
        parcel.writeValue(this.formulaResult);
        parcel.writeValue(this.answer);
        parcel.writeValue(this.answerResult);
        parcel.writeValue(this.answerPrimary);
        parcel.writeValue(this.answerSecondary);
    }

    public static List<Result> convertJsonToResults(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList(30);
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToResult(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static Result convertJsonToResult(JsonReader jsonReader) throws IOException, ParseException {
        Result result = new Result();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("id")) {
                result.identifier = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("ordered_id")) {
                result.orderedId = APIParser.readLong(jsonReader);
            } else if (nextName.equalsIgnoreCase("position")) {
                result.position = APIParser.readInteger(jsonReader);
            } else if (nextName.equalsIgnoreCase("type")) {
                result.type = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("condition_formula")) {
                result.conditionFormula = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("title")) {
                result.title = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("title_formula")) {
                result.titleFormula = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("sub_title")) {
                result.subTitle = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("sub_title_formula")) {
                result.subTitleFormula = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("formula")) {
                result.formula = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("answer")) {
                result.answer = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("answer_primary")) {
                result.answerPrimary = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("answer_secondary")) {
                result.answerSecondary = APIParser.readString(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return result;
    }

    public static void convertToJson(JsonWriter jsonWriter, List<Result> list) throws IOException {
        jsonWriter.beginArray();
        for (Result next : list) {
            jsonWriter.beginObject();
            jsonWriter.name("ordered_id").value(next.identifier);
            jsonWriter.name("position").value(next.position);
            jsonWriter.name("type").value(next.type);
            jsonWriter.name("condition_formula").value(next.conditionFormula);
            jsonWriter.name("title").value(next.title);
            jsonWriter.name("title_formula").value(next.titleFormula);
            jsonWriter.name("sub_title").value(next.subTitle);
            jsonWriter.name("sub_title_formula").value(next.subTitleFormula);
            jsonWriter.name("formula").value(next.formula);
            jsonWriter.name("answer").value(next.answer);
            jsonWriter.name("answer_primary").value(next.answerPrimary);
            jsonWriter.name("answer_secondary").value(next.answerSecondary);
            jsonWriter.endObject();
        }
        jsonWriter.endArray();
    }

    public String toString() {
        return "Result [contentSpecificIdentifier=" + this.identifier + ", position=" + this.position + ", type=" + this.type + ", title=" + this.title + ", titleFormula=" + this.titleFormula + ", subTitle=" + this.subTitle + ", subTitleFormula=" + this.subTitleFormula + ", formula=" + this.formula + ", answer=" + this.answer + ", answerPrimary=" + this.answerPrimary + ", answerSecondary=" + this.answerSecondary + "]";
    }
}
