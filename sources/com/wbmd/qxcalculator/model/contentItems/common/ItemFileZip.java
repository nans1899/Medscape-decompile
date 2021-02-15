package com.wbmd.qxcalculator.model.contentItems.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.db.DBItemFileZip;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ItemFileZip implements Parcelable {
    public static final Parcelable.Creator<ItemFileZip> CREATOR = new Parcelable.Creator<ItemFileZip>() {
        public ItemFileZip createFromParcel(Parcel parcel) {
            ItemFileZip itemFileZip = new ItemFileZip();
            itemFileZip.url = parcel.readString();
            itemFileZip.size = (Long) parcel.readValue(Long.class.getClassLoader());
            return itemFileZip;
        }

        public ItemFileZip[] newArray(int i) {
            return new ItemFileZip[i];
        }
    };
    public Long size;
    public String url;

    public int describeContents() {
        return 0;
    }

    public static ArrayList<ItemFileZip> ItemFileZips(List<DBItemFileZip> list) {
        if (list == null) {
            return null;
        }
        ArrayList<ItemFileZip> arrayList = new ArrayList<>(list.size());
        for (DBItemFileZip itemFileZip : list) {
            arrayList.add(new ItemFileZip(itemFileZip));
        }
        return arrayList;
    }

    public ItemFileZip(DBItemFileZip dBItemFileZip) {
        if (dBItemFileZip != null) {
            this.url = dBItemFileZip.getUrl();
            this.size = dBItemFileZip.getSize();
        }
    }

    public ItemFileZip() {
        this((DBItemFileZip) null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.url);
        parcel.writeValue(this.size);
    }

    public static ItemFileZip convertJsonToItemFileZip(JsonReader jsonReader) throws IOException, ParseException {
        ItemFileZip itemFileZip = new ItemFileZip();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("url")) {
                itemFileZip.url = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("size")) {
                itemFileZip.size = APIParser.readLong(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return itemFileZip;
    }

    public String toString() {
        return "ItemFileZip: {url: " + this.url + "; size: " + this.size + ";};";
    }
}
