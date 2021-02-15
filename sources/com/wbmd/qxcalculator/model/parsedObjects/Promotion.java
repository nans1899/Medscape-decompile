package com.wbmd.qxcalculator.model.parsedObjects;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import android.util.JsonToken;
import com.facebook.share.internal.ShareConstants;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.contentItems.common.Filter;
import com.wbmd.qxcalculator.model.contentItems.common.Platform;
import com.wbmd.qxcalculator.model.db.DBPromotion;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Promotion implements Parcelable {
    public static final Parcelable.Creator<Promotion> CREATOR = new Parcelable.Creator<Promotion>() {
        public Promotion createFromParcel(Parcel parcel) {
            Promotion promotion = new Promotion();
            promotion.identifier = (String) parcel.readValue(String.class.getClassLoader());
            promotion.title = (String) parcel.readValue(String.class.getClassLoader());
            promotion.footer = (String) parcel.readValue(String.class.getClassLoader());
            promotion.campaignAdId = (Long) parcel.readValue(Long.class.getClassLoader());
            promotion.platforms = parcel.createTypedArrayList(Platform.CREATOR);
            promotion.filters = parcel.createTypedArrayList(Filter.CREATOR);
            return promotion;
        }

        public Promotion[] newArray(int i) {
            return new Promotion[i];
        }
    };
    public Long campaignAdId;
    public List<Filter> filters;
    public String footer;
    public String identifier;
    public List<Platform> platforms;
    public String title;

    public int describeContents() {
        return 0;
    }

    public static ArrayList<Promotion> promotions(List<DBPromotion> list) {
        if (list == null) {
            return null;
        }
        ArrayList<Promotion> arrayList = new ArrayList<>(list.size());
        for (DBPromotion promotion : list) {
            arrayList.add(new Promotion(promotion));
        }
        return arrayList;
    }

    public Promotion(DBPromotion dBPromotion) {
        if (dBPromotion != null) {
            this.identifier = dBPromotion.getIdentifier();
            this.title = dBPromotion.getTitle();
            this.footer = dBPromotion.getFooter();
            this.campaignAdId = dBPromotion.getCampaignAdId();
            this.platforms = Platform.platforms(dBPromotion.getPlatforms());
            this.filters = Filter.filters(dBPromotion.getFilters());
        }
    }

    public Promotion() {
        this((DBPromotion) null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.identifier);
        parcel.writeValue(this.title);
        parcel.writeValue(this.footer);
        parcel.writeValue(this.campaignAdId);
        parcel.writeTypedList(this.platforms);
        parcel.writeTypedList(this.filters);
    }

    public static List<Promotion> convertJsonToPromotions(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToPromotion(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static Promotion convertJsonToPromotion(JsonReader jsonReader) throws IOException, ParseException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.skipValue();
            return null;
        }
        Promotion promotion = new Promotion();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("id")) {
                promotion.identifier = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("title")) {
                promotion.title = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("footer")) {
                promotion.footer = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("campaign_ad_id")) {
                promotion.campaignAdId = APIParser.readLong(jsonReader);
            } else if (nextName.equalsIgnoreCase("platforms")) {
                promotion.platforms = Platform.convertJsonToPlatforms(jsonReader);
            } else if (nextName.equalsIgnoreCase(ShareConstants.WEB_DIALOG_PARAM_FILTERS)) {
                promotion.filters = Filter.convertJsonToFilters(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return promotion;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Promotion)) {
            return false;
        }
        String str = this.identifier;
        String str2 = ((Promotion) obj).identifier;
        if (str != null) {
            return str.equals(str2);
        }
        if (str2 == null) {
            return true;
        }
        return false;
    }
}
