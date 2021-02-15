package io.branch.referral.util;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import io.branch.referral.BranchUtil;
import io.branch.referral.Defines;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContentMetadata implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public ContentMetadata createFromParcel(Parcel parcel) {
            return new ContentMetadata(parcel);
        }

        public ContentMetadata[] newArray(int i) {
            return new ContentMetadata[i];
        }
    };
    public String addressCity;
    public String addressCountry;
    public String addressPostalCode;
    public String addressRegion;
    public String addressStreet;
    public CONDITION condition;
    BranchContentSchema contentSchema;
    public CurrencyType currencyType;
    private final HashMap<String, String> customMetadata;
    private final ArrayList<String> imageCaptions;
    public Double latitude;
    public Double longitude;
    public Double price;
    public String productBrand;
    public ProductCategory productCategory;
    public String productName;
    public String productVariant;
    public Double quantity;
    public Double rating;
    public Double ratingAverage;
    public Integer ratingCount;
    public Double ratingMax;
    public String sku;

    public int describeContents() {
        return 0;
    }

    public enum CONDITION {
        OTHER,
        NEW,
        GOOD,
        FAIR,
        POOR,
        USED,
        REFURBISHED,
        EXCELLENT;

        public static CONDITION getValue(String str) {
            if (!TextUtils.isEmpty(str)) {
                for (CONDITION condition : values()) {
                    if (condition.name().equalsIgnoreCase(str)) {
                        return condition;
                    }
                }
            }
            return null;
        }
    }

    public ContentMetadata() {
        this.imageCaptions = new ArrayList<>();
        this.customMetadata = new HashMap<>();
    }

    public ContentMetadata addImageCaptions(String... strArr) {
        Collections.addAll(this.imageCaptions, strArr);
        return this;
    }

    public ContentMetadata addCustomMetadata(String str, String str2) {
        this.customMetadata.put(str, str2);
        return this;
    }

    public ContentMetadata setContentSchema(BranchContentSchema branchContentSchema) {
        this.contentSchema = branchContentSchema;
        return this;
    }

    public ContentMetadata setQuantity(Double d) {
        this.quantity = d;
        return this;
    }

    public ContentMetadata setAddress(String str, String str2, String str3, String str4, String str5) {
        this.addressStreet = str;
        this.addressCity = str2;
        this.addressRegion = str3;
        this.addressCountry = str4;
        this.addressPostalCode = str5;
        return this;
    }

    public ContentMetadata setLocation(Double d, Double d2) {
        this.latitude = d;
        this.longitude = d2;
        return this;
    }

    public ContentMetadata setRating(Double d, Double d2, Double d3, Integer num) {
        this.rating = d;
        this.ratingAverage = d2;
        this.ratingMax = d3;
        this.ratingCount = num;
        return this;
    }

    public ContentMetadata setRating(Double d, Double d2, Integer num) {
        this.ratingAverage = d;
        this.ratingMax = d2;
        this.ratingCount = num;
        return this;
    }

    public ContentMetadata setPrice(Double d, CurrencyType currencyType2) {
        this.price = d;
        this.currencyType = currencyType2;
        return this;
    }

    public ContentMetadata setProductBrand(String str) {
        this.productBrand = str;
        return this;
    }

    public ContentMetadata setProductCategory(ProductCategory productCategory2) {
        this.productCategory = productCategory2;
        return this;
    }

    public ContentMetadata setProductCondition(CONDITION condition2) {
        this.condition = condition2;
        return this;
    }

    public ContentMetadata setProductName(String str) {
        this.productName = str;
        return this;
    }

    public ContentMetadata setProductVariant(String str) {
        this.productVariant = str;
        return this;
    }

    public ContentMetadata setSku(String str) {
        this.sku = str;
        return this;
    }

    public ArrayList<String> getImageCaptions() {
        return this.imageCaptions;
    }

    public HashMap<String, String> getCustomMetadata() {
        return this.customMetadata;
    }

    public JSONObject convertToJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.contentSchema != null) {
                jSONObject.put(Defines.Jsonkey.ContentSchema.getKey(), this.contentSchema.name());
            }
            if (this.quantity != null) {
                jSONObject.put(Defines.Jsonkey.Quantity.getKey(), this.quantity);
            }
            if (this.price != null) {
                jSONObject.put(Defines.Jsonkey.Price.getKey(), this.price);
            }
            if (this.currencyType != null) {
                jSONObject.put(Defines.Jsonkey.PriceCurrency.getKey(), this.currencyType.toString());
            }
            if (!TextUtils.isEmpty(this.sku)) {
                jSONObject.put(Defines.Jsonkey.SKU.getKey(), this.sku);
            }
            if (!TextUtils.isEmpty(this.productName)) {
                jSONObject.put(Defines.Jsonkey.ProductName.getKey(), this.productName);
            }
            if (!TextUtils.isEmpty(this.productBrand)) {
                jSONObject.put(Defines.Jsonkey.ProductBrand.getKey(), this.productBrand);
            }
            if (this.productCategory != null) {
                jSONObject.put(Defines.Jsonkey.ProductCategory.getKey(), this.productCategory.getName());
            }
            if (this.condition != null) {
                jSONObject.put(Defines.Jsonkey.Condition.getKey(), this.condition.name());
            }
            if (!TextUtils.isEmpty(this.productVariant)) {
                jSONObject.put(Defines.Jsonkey.ProductVariant.getKey(), this.productVariant);
            }
            if (this.rating != null) {
                jSONObject.put(Defines.Jsonkey.Rating.getKey(), this.rating);
            }
            if (this.ratingAverage != null) {
                jSONObject.put(Defines.Jsonkey.RatingAverage.getKey(), this.ratingAverage);
            }
            if (this.ratingCount != null) {
                jSONObject.put(Defines.Jsonkey.RatingCount.getKey(), this.ratingCount);
            }
            if (this.ratingMax != null) {
                jSONObject.put(Defines.Jsonkey.RatingMax.getKey(), this.ratingMax);
            }
            if (!TextUtils.isEmpty(this.addressStreet)) {
                jSONObject.put(Defines.Jsonkey.AddressStreet.getKey(), this.addressStreet);
            }
            if (!TextUtils.isEmpty(this.addressCity)) {
                jSONObject.put(Defines.Jsonkey.AddressCity.getKey(), this.addressCity);
            }
            if (!TextUtils.isEmpty(this.addressRegion)) {
                jSONObject.put(Defines.Jsonkey.AddressRegion.getKey(), this.addressRegion);
            }
            if (!TextUtils.isEmpty(this.addressCountry)) {
                jSONObject.put(Defines.Jsonkey.AddressCountry.getKey(), this.addressCountry);
            }
            if (!TextUtils.isEmpty(this.addressPostalCode)) {
                jSONObject.put(Defines.Jsonkey.AddressPostalCode.getKey(), this.addressPostalCode);
            }
            if (this.latitude != null) {
                jSONObject.put(Defines.Jsonkey.Latitude.getKey(), this.latitude);
            }
            if (this.longitude != null) {
                jSONObject.put(Defines.Jsonkey.Longitude.getKey(), this.longitude);
            }
            if (this.imageCaptions.size() > 0) {
                JSONArray jSONArray = new JSONArray();
                jSONObject.put(Defines.Jsonkey.ImageCaptions.getKey(), jSONArray);
                Iterator<String> it = this.imageCaptions.iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next());
                }
            }
            if (this.customMetadata.size() > 0) {
                for (String next : this.customMetadata.keySet()) {
                    jSONObject.put(next, this.customMetadata.get(next));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static ContentMetadata createFromJson(BranchUtil.JsonReader jsonReader) {
        ContentMetadata contentMetadata = new ContentMetadata();
        contentMetadata.contentSchema = BranchContentSchema.getValue(jsonReader.readOutString(Defines.Jsonkey.ContentSchema.getKey()));
        contentMetadata.quantity = jsonReader.readOutDouble(Defines.Jsonkey.Quantity.getKey(), (Double) null);
        contentMetadata.price = jsonReader.readOutDouble(Defines.Jsonkey.Price.getKey(), (Double) null);
        contentMetadata.currencyType = CurrencyType.getValue(jsonReader.readOutString(Defines.Jsonkey.PriceCurrency.getKey()));
        contentMetadata.sku = jsonReader.readOutString(Defines.Jsonkey.SKU.getKey());
        contentMetadata.productName = jsonReader.readOutString(Defines.Jsonkey.ProductName.getKey());
        contentMetadata.productBrand = jsonReader.readOutString(Defines.Jsonkey.ProductBrand.getKey());
        contentMetadata.productCategory = ProductCategory.getValue(jsonReader.readOutString(Defines.Jsonkey.ProductCategory.getKey()));
        contentMetadata.condition = CONDITION.getValue(jsonReader.readOutString(Defines.Jsonkey.Condition.getKey()));
        contentMetadata.productVariant = jsonReader.readOutString(Defines.Jsonkey.ProductVariant.getKey());
        contentMetadata.rating = jsonReader.readOutDouble(Defines.Jsonkey.Rating.getKey(), (Double) null);
        contentMetadata.ratingAverage = jsonReader.readOutDouble(Defines.Jsonkey.RatingAverage.getKey(), (Double) null);
        contentMetadata.ratingCount = jsonReader.readOutInt(Defines.Jsonkey.RatingCount.getKey(), (Integer) null);
        contentMetadata.ratingMax = jsonReader.readOutDouble(Defines.Jsonkey.RatingMax.getKey(), (Double) null);
        contentMetadata.addressStreet = jsonReader.readOutString(Defines.Jsonkey.AddressStreet.getKey());
        contentMetadata.addressCity = jsonReader.readOutString(Defines.Jsonkey.AddressCity.getKey());
        contentMetadata.addressRegion = jsonReader.readOutString(Defines.Jsonkey.AddressRegion.getKey());
        contentMetadata.addressCountry = jsonReader.readOutString(Defines.Jsonkey.AddressCountry.getKey());
        contentMetadata.addressPostalCode = jsonReader.readOutString(Defines.Jsonkey.AddressPostalCode.getKey());
        contentMetadata.latitude = jsonReader.readOutDouble(Defines.Jsonkey.Latitude.getKey(), (Double) null);
        contentMetadata.longitude = jsonReader.readOutDouble(Defines.Jsonkey.Longitude.getKey(), (Double) null);
        JSONArray readOutJsonArray = jsonReader.readOutJsonArray(Defines.Jsonkey.ImageCaptions.getKey());
        if (readOutJsonArray != null) {
            for (int i = 0; i < readOutJsonArray.length(); i++) {
                contentMetadata.imageCaptions.add(readOutJsonArray.optString(i));
            }
        }
        try {
            JSONObject jsonObject = jsonReader.getJsonObject();
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                contentMetadata.customMetadata.put(next, jsonObject.optString(next));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentMetadata;
    }

    public void writeToParcel(Parcel parcel, int i) {
        BranchContentSchema branchContentSchema = this.contentSchema;
        String str = "";
        parcel.writeString(branchContentSchema != null ? branchContentSchema.name() : str);
        parcel.writeSerializable(this.quantity);
        parcel.writeSerializable(this.price);
        CurrencyType currencyType2 = this.currencyType;
        parcel.writeString(currencyType2 != null ? currencyType2.name() : str);
        parcel.writeString(this.sku);
        parcel.writeString(this.productName);
        parcel.writeString(this.productBrand);
        ProductCategory productCategory2 = this.productCategory;
        parcel.writeString(productCategory2 != null ? productCategory2.getName() : str);
        CONDITION condition2 = this.condition;
        if (condition2 != null) {
            str = condition2.name();
        }
        parcel.writeString(str);
        parcel.writeString(this.productVariant);
        parcel.writeSerializable(this.rating);
        parcel.writeSerializable(this.ratingAverage);
        parcel.writeSerializable(this.ratingCount);
        parcel.writeSerializable(this.ratingMax);
        parcel.writeString(this.addressStreet);
        parcel.writeString(this.addressCity);
        parcel.writeString(this.addressRegion);
        parcel.writeString(this.addressCountry);
        parcel.writeString(this.addressPostalCode);
        parcel.writeSerializable(this.latitude);
        parcel.writeSerializable(this.longitude);
        parcel.writeSerializable(this.imageCaptions);
        parcel.writeSerializable(this.customMetadata);
    }

    private ContentMetadata(Parcel parcel) {
        this();
        this.contentSchema = BranchContentSchema.getValue(parcel.readString());
        this.quantity = (Double) parcel.readSerializable();
        this.price = (Double) parcel.readSerializable();
        this.currencyType = CurrencyType.getValue(parcel.readString());
        this.sku = parcel.readString();
        this.productName = parcel.readString();
        this.productBrand = parcel.readString();
        this.productCategory = ProductCategory.getValue(parcel.readString());
        this.condition = CONDITION.getValue(parcel.readString());
        this.productVariant = parcel.readString();
        this.rating = (Double) parcel.readSerializable();
        this.ratingAverage = (Double) parcel.readSerializable();
        this.ratingCount = (Integer) parcel.readSerializable();
        this.ratingMax = (Double) parcel.readSerializable();
        this.addressStreet = parcel.readString();
        this.addressCity = parcel.readString();
        this.addressRegion = parcel.readString();
        this.addressCountry = parcel.readString();
        this.addressPostalCode = parcel.readString();
        this.latitude = (Double) parcel.readSerializable();
        this.longitude = (Double) parcel.readSerializable();
        this.imageCaptions.addAll((ArrayList) parcel.readSerializable());
        this.customMetadata.putAll((HashMap) parcel.readSerializable());
    }
}
