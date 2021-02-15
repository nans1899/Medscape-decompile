package com.wbmd.qxcalculator.model.contentItems.calculator;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import android.util.JsonWriter;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.db.DBUnit;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Unit implements Parcelable {
    public static final Parcelable.Creator<Unit> CREATOR = new Parcelable.Creator<Unit>() {
        public Unit createFromParcel(Parcel parcel) {
            Unit unit = new Unit();
            unit.identifier = parcel.readString();
            unit.title = parcel.readString();
            unit.type = parcel.readString();
            unit.initialValue = (Double) parcel.readValue(Double.class.getClassLoader());
            unit.unitFactor = (Double) parcel.readValue(Double.class.getClassLoader());
            unit.minValue = (Double) parcel.readValue(Double.class.getClassLoader());
            unit.maxValue = (Double) parcel.readValue(Double.class.getClassLoader());
            unit.minValueMessage = (String) parcel.readValue(String.class.getClassLoader());
            unit.maxValueMessage = (String) parcel.readValue(String.class.getClassLoader());
            return unit;
        }

        public Unit[] newArray(int i) {
            return new Unit[i];
        }
    };
    public String identifier;
    public Double initialValue;
    public Double maxValue;
    public String maxValueMessage;
    public Double minValue;
    public String minValueMessage;
    public String title;
    public String type;
    public Double unitFactor;

    public int describeContents() {
        return 0;
    }

    public enum UnitType {
        SI_UNITS,
        US_UNITS;
        
        private static final String K_SI_UNITS = "Unit.kSiUnits";
        private static final String K_US_UNITS = "Unit.kUsUnits";

        public String convertToString(Context context) {
            if (equals(SI_UNITS)) {
                return context.getResources().getString(R.string.units_si);
            }
            if (equals(US_UNITS)) {
                return context.getResources().getString(R.string.units_us);
            }
            return context.getResources().getString(R.string.units_other);
        }

        public static String convertTypeToString(UnitType unitType) {
            return unitType == SI_UNITS ? K_SI_UNITS : K_US_UNITS;
        }

        public static UnitType convertStringToType(String str) {
            if (str == null) {
                return US_UNITS;
            }
            if (str.equalsIgnoreCase(K_SI_UNITS)) {
                return SI_UNITS;
            }
            return US_UNITS;
        }

        public static int convertUnitTypeToInt(UnitType unitType) {
            return unitType == SI_UNITS ? 0 : 1;
        }

        public static int convertUnitStringToInt(String str) {
            return str.equalsIgnoreCase(K_SI_UNITS) ? 0 : 1;
        }

        public static String convertIntToUnitString(int i) {
            if (i == 0) {
                return convertTypeToString(SI_UNITS);
            }
            return convertTypeToString(US_UNITS);
        }

        public static boolean isLegacyDefaultUnitValue(String str) {
            return str.equals(K_SI_UNITS) || str.equals(K_US_UNITS);
        }
    }

    public static ArrayList<Unit> units(List<DBUnit> list) {
        if (list == null) {
            return null;
        }
        ArrayList<Unit> arrayList = new ArrayList<>(list.size());
        for (DBUnit unit : list) {
            arrayList.add(new Unit(unit));
        }
        return arrayList;
    }

    public Unit(DBUnit dBUnit) {
        if (dBUnit != null) {
            this.identifier = dBUnit.getIdentifier();
            this.title = dBUnit.getTitle();
            this.type = dBUnit.getType();
            this.initialValue = dBUnit.getInitialValue();
            this.unitFactor = dBUnit.getUnitFactor();
            this.minValue = dBUnit.getMinValue();
            this.maxValue = dBUnit.getMaxValue();
            this.minValueMessage = dBUnit.getMinValueMessage();
            this.maxValueMessage = dBUnit.getMaxValueMessage();
        }
    }

    public Unit() {
        this((DBUnit) null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.identifier);
        parcel.writeString(this.title);
        parcel.writeString(this.type);
        parcel.writeValue(this.initialValue);
        parcel.writeValue(this.unitFactor);
        parcel.writeValue(this.minValue);
        parcel.writeValue(this.maxValue);
        parcel.writeValue(this.minValueMessage);
        parcel.writeValue(this.maxValueMessage);
    }

    public static List<Unit> convertJsonToUnits(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToUnit(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static Unit convertJsonToUnit(JsonReader jsonReader) throws IOException, ParseException {
        Unit unit = new Unit();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("id")) {
                unit.identifier = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("title")) {
                unit.title = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("type")) {
                unit.type = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("initial_value")) {
                unit.initialValue = APIParser.readDouble(jsonReader);
            } else if (nextName.equalsIgnoreCase("unit_factor")) {
                unit.unitFactor = APIParser.readDouble(jsonReader);
            } else if (nextName.equalsIgnoreCase("min_value")) {
                unit.minValue = APIParser.readDouble(jsonReader);
            } else if (nextName.equalsIgnoreCase("max_value")) {
                unit.maxValue = APIParser.readDouble(jsonReader);
            } else if (nextName.equalsIgnoreCase("min_value_msg")) {
                unit.minValueMessage = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("max_value_msg")) {
                unit.maxValueMessage = APIParser.readString(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return unit;
    }

    public static void convertToJson(JsonWriter jsonWriter, List<Unit> list) throws IOException {
        jsonWriter.beginArray();
        for (Unit next : list) {
            jsonWriter.beginObject();
            jsonWriter.name("id").value(next.identifier);
            jsonWriter.name("title").value(next.title);
            jsonWriter.name("type").value(next.type);
            jsonWriter.name("initial_value").value(next.initialValue);
            jsonWriter.name("unit_factor").value(next.unitFactor);
            jsonWriter.name("min_value").value(next.minValue);
            jsonWriter.name("max_value").value(next.maxValue);
            jsonWriter.name("min_value_msg").value(next.minValueMessage);
            jsonWriter.name("max_value_msg").value(next.maxValueMessage);
            jsonWriter.endObject();
        }
        jsonWriter.endArray();
    }

    public String toString() {
        return "Unit [id=" + this.identifier + "; title=" + this.title + ", type=" + this.type + ", initialValue=" + this.initialValue + ", unitFactor=" + this.unitFactor + ", minValue=" + this.minValue + ", maxValue=" + this.maxValue + ", minValueMessage=" + this.minValueMessage + ", maxValueMessage=" + this.maxValueMessage + "]";
    }
}
