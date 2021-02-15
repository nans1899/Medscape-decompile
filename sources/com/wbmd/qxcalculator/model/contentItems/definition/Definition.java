package com.wbmd.qxcalculator.model.contentItems.definition;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import com.wbmd.qxcalculator.model.db.DBDefinition;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Definition implements Parcelable {
    public static final Parcelable.Creator<Definition> CREATOR = new Parcelable.Creator<Definition>() {
        public Definition createFromParcel(Parcel parcel) {
            Definition definition = new Definition();
            definition.identifier = parcel.readString();
            definition.definitionSections = parcel.createTypedArrayList(DefinitionSection.CREATOR);
            return definition;
        }

        public Definition[] newArray(int i) {
            return new Definition[i];
        }
    };
    public List<DefinitionSection> definitionSections;
    public String identifier;

    public int describeContents() {
        return 0;
    }

    public static ArrayList<Definition> definitions(List<DBDefinition> list) {
        if (list == null) {
            return null;
        }
        ArrayList<Definition> arrayList = new ArrayList<>(list.size());
        for (DBDefinition definition : list) {
            arrayList.add(new Definition(definition));
        }
        return arrayList;
    }

    public Definition(DBDefinition dBDefinition) {
        if (dBDefinition != null) {
            this.identifier = dBDefinition.getIdentifier();
            this.definitionSections = DefinitionSection.definitionSections(dBDefinition.getDefinitionSections());
        }
    }

    public Definition() {
        this((DBDefinition) null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.identifier);
        parcel.writeTypedList(this.definitionSections);
    }

    public boolean readJsonTag(JsonReader jsonReader, String str) throws IOException, ParseException {
        if (!str.equalsIgnoreCase("sections")) {
            return false;
        }
        this.definitionSections = DefinitionSection.convertJsonToDefinitionSections(jsonReader);
        return true;
    }

    public String toString() {
        return "Definition [identifier=" + this.identifier + ", definitionSections=" + this.definitionSections + "]";
    }
}
