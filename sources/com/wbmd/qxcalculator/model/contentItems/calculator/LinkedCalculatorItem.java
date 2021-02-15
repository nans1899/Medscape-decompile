package com.wbmd.qxcalculator.model.contentItems.calculator;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.db.DBLinkedCalculatorItem;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class LinkedCalculatorItem implements Parcelable {
    public static final Parcelable.Creator<LinkedCalculatorItem> CREATOR = new Parcelable.Creator<LinkedCalculatorItem>() {
        public LinkedCalculatorItem createFromParcel(Parcel parcel) {
            LinkedCalculatorItem linkedCalculatorItem = new LinkedCalculatorItem();
            linkedCalculatorItem.identifier = parcel.readString();
            linkedCalculatorItem.calculatorIdentifier = (String) parcel.readValue(String.class.getClassLoader());
            linkedCalculatorItem.resultConvertFormula = (String) parcel.readValue(String.class.getClassLoader());
            linkedCalculatorItem.resultUnits = (String) parcel.readValue(String.class.getClassLoader());
            return linkedCalculatorItem;
        }

        public LinkedCalculatorItem[] newArray(int i) {
            return new LinkedCalculatorItem[i];
        }
    };
    public String calculatorIdentifier;
    public String identifier;
    public String resultConvertFormula;
    public String resultUnits;

    public int describeContents() {
        return 0;
    }

    public static ArrayList<LinkedCalculatorItem> linkedCalculatorItems(List<DBLinkedCalculatorItem> list) {
        if (list == null) {
            return null;
        }
        ArrayList<LinkedCalculatorItem> arrayList = new ArrayList<>(list.size());
        for (DBLinkedCalculatorItem linkedCalculatorItem : list) {
            arrayList.add(new LinkedCalculatorItem(linkedCalculatorItem));
        }
        return arrayList;
    }

    public LinkedCalculatorItem(DBLinkedCalculatorItem dBLinkedCalculatorItem) {
        if (dBLinkedCalculatorItem != null) {
            this.identifier = dBLinkedCalculatorItem.getIdentifier();
            this.calculatorIdentifier = dBLinkedCalculatorItem.getCalculatorIdentifier();
            this.resultConvertFormula = dBLinkedCalculatorItem.getResultConvertFormula();
            this.resultUnits = dBLinkedCalculatorItem.getResultUnits();
        }
    }

    public LinkedCalculatorItem() {
        this((DBLinkedCalculatorItem) null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.identifier);
        parcel.writeValue(this.calculatorIdentifier);
        parcel.writeValue(this.resultConvertFormula);
        parcel.writeValue(this.resultUnits);
    }

    public static List<LinkedCalculatorItem> convertJsonToLinkedCalculatorItems(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToLinkedCalculatorItem(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static LinkedCalculatorItem convertJsonToLinkedCalculatorItem(JsonReader jsonReader) throws IOException, ParseException {
        LinkedCalculatorItem linkedCalculatorItem = new LinkedCalculatorItem();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("id")) {
                linkedCalculatorItem.identifier = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("calculator_id")) {
                linkedCalculatorItem.calculatorIdentifier = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("result_convert_formula")) {
                linkedCalculatorItem.resultConvertFormula = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("result_units")) {
                linkedCalculatorItem.resultUnits = APIParser.readString(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return linkedCalculatorItem;
    }
}
