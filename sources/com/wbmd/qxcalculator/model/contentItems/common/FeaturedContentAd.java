package com.wbmd.qxcalculator.model.contentItems.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.facebook.share.internal.ShareConstants;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.db.DBFeaturedContentAd;
import com.wbmd.qxcalculator.model.parsedObjects.Promotion;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class FeaturedContentAd implements Parcelable {
    public static final Parcelable.Creator<FeaturedContentAd> CREATOR = new Parcelable.Creator<FeaturedContentAd>() {
        public FeaturedContentAd createFromParcel(Parcel parcel) {
            FeaturedContentAd featuredContentAd = new FeaturedContentAd();
            featuredContentAd.identifier = (String) parcel.readValue(String.class.getClassLoader());
            featuredContentAd.name = (String) parcel.readValue(String.class.getClassLoader());
            featuredContentAd.description = (String) parcel.readValue(String.class.getClassLoader());
            featuredContentAd.disclaimer = (String) parcel.readValue(String.class.getClassLoader());
            featuredContentAd.footer = (String) parcel.readValue(String.class.getClassLoader());
            featuredContentAd.imageSource = (String) parcel.readValue(String.class.getClassLoader());
            featuredContentAd.backgroundColor = (String) parcel.readValue(String.class.getClassLoader());
            featuredContentAd.displayFrequency = (Double) parcel.readValue(Double.class.getClassLoader());
            featuredContentAd.filters = parcel.createTypedArrayList(Filter.CREATOR);
            featuredContentAd.platforms = parcel.createTypedArrayList(Platform.CREATOR);
            featuredContentAd.promotion = (Promotion) parcel.readValue(Promotion.class.getClassLoader());
            return featuredContentAd;
        }

        public FeaturedContentAd[] newArray(int i) {
            return new FeaturedContentAd[i];
        }
    };
    public String backgroundColor;
    public String description;
    public String disclaimer;
    public Double displayFrequency;
    public List<Filter> filters;
    public String footer;
    public String identifier;
    public String imageSource;
    public String name;
    public List<Platform> platforms;
    public Promotion promotion;

    public int describeContents() {
        return 0;
    }

    public static ArrayList<FeaturedContentAd> featuredContentAds(List<DBFeaturedContentAd> list) {
        if (list == null) {
            return null;
        }
        ArrayList<FeaturedContentAd> arrayList = new ArrayList<>(list.size());
        for (DBFeaturedContentAd featuredContentAd : list) {
            arrayList.add(new FeaturedContentAd(featuredContentAd));
        }
        return arrayList;
    }

    public FeaturedContentAd(DBFeaturedContentAd dBFeaturedContentAd) {
        if (dBFeaturedContentAd != null) {
            this.identifier = dBFeaturedContentAd.getIdentifier();
            this.name = dBFeaturedContentAd.getName();
            this.description = dBFeaturedContentAd.getDescription();
            this.disclaimer = dBFeaturedContentAd.getDisclaimer();
            this.footer = dBFeaturedContentAd.getFooter();
            this.imageSource = dBFeaturedContentAd.getImageSource();
            this.backgroundColor = dBFeaturedContentAd.getBackgroundColor();
            this.displayFrequency = dBFeaturedContentAd.getDisplayFrequency();
            this.filters = Filter.filters(dBFeaturedContentAd.getFilters());
            this.platforms = Platform.platforms(dBFeaturedContentAd.getPlatforms());
            if (dBFeaturedContentAd.getPromotion() != null) {
                this.promotion = new Promotion(dBFeaturedContentAd.getPromotion());
            }
        }
    }

    public FeaturedContentAd() {
        this((DBFeaturedContentAd) null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.identifier);
        parcel.writeValue(this.name);
        parcel.writeValue(this.description);
        parcel.writeValue(this.disclaimer);
        parcel.writeValue(this.footer);
        parcel.writeValue(this.imageSource);
        parcel.writeValue(this.backgroundColor);
        parcel.writeValue(this.displayFrequency);
        parcel.writeTypedList(this.filters);
        parcel.writeTypedList(this.platforms);
        parcel.writeValue(this.promotion);
    }

    public static List<FeaturedContentAd> convertJsonToFeaturedContentAds(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToFeaturedContentAd(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static FeaturedContentAd convertJsonToFeaturedContentAd(JsonReader jsonReader) throws IOException, ParseException {
        FeaturedContentAd featuredContentAd = new FeaturedContentAd();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("id")) {
                featuredContentAd.identifier = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("name")) {
                featuredContentAd.name = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("description")) {
                featuredContentAd.description = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("disclaimer")) {
                featuredContentAd.disclaimer = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("footer")) {
                featuredContentAd.footer = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase(MessengerShareContentUtility.MEDIA_IMAGE)) {
                featuredContentAd.imageSource = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("background_color")) {
                featuredContentAd.backgroundColor = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("display_frequency")) {
                featuredContentAd.displayFrequency = APIParser.readDouble(jsonReader);
            } else if (nextName.equalsIgnoreCase(ShareConstants.WEB_DIALOG_PARAM_FILTERS)) {
                featuredContentAd.filters = Filter.convertJsonToFilters(jsonReader);
            } else if (nextName.equalsIgnoreCase("platforms")) {
                featuredContentAd.platforms = Platform.convertJsonToPlatforms(jsonReader);
            } else if (nextName.equalsIgnoreCase("promotion")) {
                featuredContentAd.promotion = Promotion.convertJsonToPromotion(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return featuredContentAd;
    }
}
