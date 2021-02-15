package com.wbmd.qxcalculator.model.contentItems.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.db.DBCategory;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Category implements Parcelable {
    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        public Category createFromParcel(Parcel parcel) {
            Category category = new Category();
            category.contentSpecificIdentifier = (String) parcel.readValue(String.class.getClassLoader());
            category.name = (String) parcel.readValue(String.class.getClassLoader());
            category.weight = (Integer) parcel.readValue(Integer.class.getClassLoader());
            category.itemWeight = (Integer) parcel.readValue(Integer.class.getClassLoader());
            category.subCategories = parcel.createTypedArrayList(Category.CREATOR);
            return category;
        }

        public Category[] newArray(int i) {
            return new Category[i];
        }
    };
    public static final String K_MENU_CATEGORY = "menu";
    public String contentSpecificIdentifier;
    public String identifier;
    public Integer itemWeight;
    public String name;
    public List<Category> subCategories;
    public Integer weight;

    public int describeContents() {
        return 0;
    }

    public static ArrayList<Category> categories(List<DBCategory> list) {
        if (list == null) {
            return null;
        }
        ArrayList<Category> arrayList = new ArrayList<>(list.size());
        for (DBCategory category : list) {
            arrayList.add(new Category(category));
        }
        return arrayList;
    }

    public Category(DBCategory dBCategory) {
        if (dBCategory != null) {
            this.identifier = dBCategory.getIdentifier();
            this.contentSpecificIdentifier = dBCategory.getContentSpecificIdentifier();
            this.name = dBCategory.getName();
            this.weight = dBCategory.getWeight();
            this.itemWeight = dBCategory.getItemWeight();
            this.subCategories = categories(dBCategory.getSubCategories());
        }
    }

    public Category() {
        this((DBCategory) null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.contentSpecificIdentifier);
        parcel.writeValue(this.name);
        parcel.writeValue(this.weight);
        parcel.writeValue(this.itemWeight);
        parcel.writeTypedList(this.subCategories);
    }

    public static List<Category> convertJsonToCategories(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToCategory(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static Category convertJsonToCategory(JsonReader jsonReader) throws IOException, ParseException {
        Category category = new Category();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("id")) {
                category.identifier = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("name")) {
                category.name = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("weight")) {
                category.weight = APIParser.readInteger(jsonReader);
            } else if (nextName.equalsIgnoreCase("custom_weight")) {
                category.itemWeight = APIParser.readInteger(jsonReader);
            } else if (nextName.equalsIgnoreCase("subcategories")) {
                category.subCategories = convertJsonToCategories(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return category;
    }

    public boolean isMenuItem() {
        return this.name.toLowerCase().startsWith(K_MENU_CATEGORY);
    }

    public String toString() {
        return "Category: {contentSpecificIdentifier: " + this.contentSpecificIdentifier + "; name: " + this.name + "; weight: " + this.weight + "; subCategories: " + this.subCategories + ";};";
    }
}
