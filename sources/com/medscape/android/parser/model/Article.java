package com.medscape.android.parser.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.activity.search.model.SearchSuggestion;
import com.medscape.android.activity.search.model.SearchSuggestionMsg;
import com.medscape.android.util.NumberUtil;
import com.medscape.android.util.Util;
import io.branch.referral.Branch;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class Article implements Comparable<Article>, Parcelable {
    public static final int BRAND_ADVANCE_2_0_CELLTYPE = 4;
    public static final int BRAND_ADVANCE_CELLTYPE = 2;
    public static final int BRAND_PLAY_CELLTYPE = 3;
    public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>() {
        public Article createFromParcel(Parcel parcel) {
            return new Article(parcel);
        }

        public Article[] newArray(int i) {
            return new Article[i];
        }
    };
    public static final int INFORMATION_FROM_INDUSTRY_CELLTYPE = 1;
    public PublisherAdView adView;
    public boolean isAdLoading;
    public String mActivityId;
    public String mArticleId;
    public String mArticleImage;
    public String mCarouselHeaderColor;
    public String mCarouselHeaderTitle;
    public String mCarouselHeaderType;
    public String mCategory;
    public int mCellType;
    public String mConfTag;
    public String mContentTypeDate;
    public String mCredit;
    public Date mDate;
    public String mDescription;
    public boolean mHasLinkUrl;
    public String mInfoTitle;
    public boolean mIsEditorial;
    public boolean mIsInlineAD;
    public String mJobCode;
    public boolean mLegacy;
    public String mLink;
    public boolean mMustShowFullImage;
    public String mOrientationLock;
    public String mPublication;
    public SearchSuggestionMsg mSearchSuggestionMsg;
    public boolean mShareable;
    public boolean mShowsCarouselPlaceholder;
    public String mTcId;
    public String mTitle;

    public int describeContents() {
        return 0;
    }

    public Article() {
        this.mTitle = "";
        this.mLink = "";
        this.mDescription = "";
        this.mCategory = "";
        this.mPublication = "";
        this.mCarouselHeaderType = "";
        this.mCarouselHeaderTitle = "";
        this.mConfTag = "";
        this.mCredit = "";
        this.mInfoTitle = "";
        this.mShareable = true;
        this.mSearchSuggestionMsg = null;
        this.mContentTypeDate = "";
        this.mIsInlineAD = false;
        this.isAdLoading = true;
    }

    public Article(Parcel parcel) {
        this.mTitle = "";
        this.mLink = "";
        this.mDescription = "";
        this.mCategory = "";
        this.mPublication = "";
        this.mCarouselHeaderType = "";
        this.mCarouselHeaderTitle = "";
        this.mConfTag = "";
        this.mCredit = "";
        this.mInfoTitle = "";
        boolean z = true;
        this.mShareable = true;
        this.mSearchSuggestionMsg = null;
        this.mContentTypeDate = "";
        this.mIsInlineAD = false;
        this.isAdLoading = true;
        this.mTitle = parcel.readString();
        this.mLink = parcel.readString();
        this.mDescription = parcel.readString();
        setDate(parcel.readString());
        this.mCategory = parcel.readString();
        this.mArticleId = parcel.readString();
        this.mPublication = parcel.readString();
        this.mCarouselHeaderType = parcel.readString();
        this.mCarouselHeaderTitle = parcel.readString();
        this.mActivityId = parcel.readString();
        this.mTcId = parcel.readString();
        this.mCellType = parcel.readInt();
        this.mConfTag = parcel.readString();
        this.mCredit = parcel.readString();
        this.mLegacy = parcel.readInt() == 1;
        this.mMustShowFullImage = parcel.readInt() == 1;
        this.mArticleImage = parcel.readString();
        this.mInfoTitle = parcel.readString();
        this.mShareable = parcel.readInt() == 1;
        this.mShowsCarouselPlaceholder = parcel.readInt() == 1;
        this.mIsEditorial = parcel.readInt() == 1;
        this.mOrientationLock = parcel.readString();
        this.mJobCode = parcel.readString();
        this.mCarouselHeaderColor = parcel.readString();
        this.mContentTypeDate = parcel.readString();
        this.mHasLinkUrl = parcel.readInt() == 1;
        this.mIsInlineAD = parcel.readInt() != 1 ? false : z;
        this.mSearchSuggestionMsg = (SearchSuggestionMsg) parcel.readValue(SearchSuggestion.class.getClassLoader());
        this.adView = (PublisherAdView) parcel.readValue(PublisherAdView.class.getClassLoader());
    }

    public String getDate() {
        if (this.mDate == null) {
            return "";
        }
        return new SimpleDateFormat("MMMM dd, yyyy", Locale.US).format(this.mDate);
    }

    public void setDate(String str) {
        if (str == null || str.equals("")) {
            this.mDate = null;
            return;
        }
        try {
            this.mDate = new SimpleDateFormat("MMMM dd, yyyy", Locale.US).parse(str.trim());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Article copy() {
        Article article = new Article();
        article.mTitle = this.mTitle;
        article.mLink = this.mLink;
        article.mDescription = this.mDescription;
        article.mDate = this.mDate;
        return article;
    }

    public String toString() {
        return "Title: " + this.mTitle + 10 + "Date: " + getDate() + 10 + "Link: " + this.mLink + 10 + "Description: " + this.mDescription + this.mTcId + this.mActivityId;
    }

    public int hashCode() {
        Date date = this.mDate;
        int i = 0;
        int hashCode = ((date == null ? 0 : date.hashCode()) + 31) * 31;
        String str = this.mDescription;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.mLink;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.mTitle;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode3 + i;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Article)) {
            return false;
        }
        Article article = (Article) obj;
        if ((this.mDate != null || article.mDate != null) && !Util.objectEquals(this.mDate, article.mDate)) {
            return false;
        }
        if ((this.mDescription != null || article.mDescription != null) && !Util.objectEquals(this.mDescription, article.mDescription)) {
            return false;
        }
        if ((this.mLink != null || article.mLink != null) && !Util.objectEquals(this.mLink, article.mLink)) {
            return false;
        }
        if ((this.mTitle != null || article.mTitle != null) && !Util.objectEquals(this.mTitle, article.mTitle)) {
            return false;
        }
        return true;
    }

    public int compareTo(Article article) {
        Date date;
        if (article == null || (date = article.mDate) == null) {
            return 1;
        }
        Date date2 = this.mDate;
        if (date2 == null) {
            return -1;
        }
        return date.compareTo(date2);
    }

    public static Article createFromJSON(JSONObject jSONObject, boolean z) throws JSONException {
        String str;
        DateFormat dateFormat;
        String str2;
        Article article = new Article();
        if (!jSONObject.isNull("ti")) {
            article.mTitle = jSONObject.getString("ti");
        }
        if (!jSONObject.isNull("tiarray")) {
            article.mTitle = jSONObject.getJSONArray("tiarray").getString(Util.selectRandomNumber(jSONObject.getJSONArray("tiarray").length()));
        }
        if (!jSONObject.isNull("de")) {
            article.mDescription = jSONObject.getString("de");
        }
        if (!jSONObject.isNull("ct")) {
            article.mCategory = jSONObject.getString("ct");
        }
        if (!jSONObject.isNull("pu")) {
            article.mPublication = jSONObject.getString("pu");
        }
        if (!jSONObject.isNull("tcid")) {
            article.mTcId = jSONObject.getString("tcid");
        }
        if (!jSONObject.isNull("activityId")) {
            article.mActivityId = jSONObject.getString("activityId");
        }
        if (!jSONObject.isNull("carouselHeaderType")) {
            article.mCarouselHeaderType = jSONObject.getString("carouselHeaderType");
        }
        if (!jSONObject.isNull("carouselHeaderTitle")) {
            article.mCarouselHeaderTitle = jSONObject.getString("carouselHeaderTitle");
        }
        if (!jSONObject.isNull("mo")) {
            String string = jSONObject.getString("mo");
            if (string.length() > 0 && !jSONObject.isNull("da")) {
                String string2 = jSONObject.getString("da");
                if (string2.length() > 0 && !jSONObject.isNull("ye")) {
                    String string3 = jSONObject.getString("ye");
                    if (string3.length() > 0) {
                        try {
                            if (NumberUtil.isNumber(string)) {
                                dateFormat = DateFormat.getDateInstance(3);
                                StringBuilder sb = new StringBuilder(string + "/");
                                sb.append(string2 + "/");
                                sb.append(string3);
                                str2 = sb.toString();
                            } else {
                                dateFormat = DateFormat.getDateInstance(1);
                                StringBuilder sb2 = new StringBuilder(string + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                                sb2.append(string2 + ", ");
                                sb2.append(string3);
                                str2 = sb2.toString();
                            }
                            article.mDate = dateFormat.parse(str2);
                        } catch (Exception unused) {
                        }
                    }
                }
            }
        }
        boolean z2 = false;
        if (!jSONObject.isNull("dom") && !jSONObject.isNull("uri")) {
            if (!jSONObject.isNull("linkurl") && isDemoModeOn()) {
                String string4 = jSONObject.getString("linkurl");
                article.mLink = string4.replace("localfile://", "file://" + MedscapeApplication.get().getPreferences().getString(Constants.PREF_DEMO_ROOT_DIR, "") + "/");
                article.mHasLinkUrl = true;
            } else if (jSONObject.isNull("linkurl") || isDemoModeOn()) {
                String string5 = jSONObject.getString("dom");
                if (string5.length() == 0) {
                    string5 = "https://reference.medscape.com/medline/abstract/";
                }
                String string6 = jSONObject.getString("uri");
                StringBuilder sb3 = new StringBuilder();
                sb3.append(string5);
                if (string5.charAt(string5.length() - 1) == '/' || string6.charAt(0) == '/') {
                    str = "";
                } else {
                    str = "/";
                }
                sb3.append(str);
                sb3.append(string6);
                article.mLink = sb3.toString();
            } else {
                article.mLink = jSONObject.getString("linkurl");
                article.mHasLinkUrl = true;
            }
        }
        if (!jSONObject.isNull("linkurl") && isDemoModeOn()) {
            String string7 = jSONObject.getString("linkurl");
            article.mLink = string7.replace("localfile://", "file://" + MedscapeApplication.get().getPreferences().getString(Constants.PREF_DEMO_ROOT_DIR, "") + "/");
            article.mHasLinkUrl = true;
        } else if (!jSONObject.isNull("linkurl") && !isDemoModeOn()) {
            article.mLink = jSONObject.getString("linkurl");
            article.mHasLinkUrl = true;
        }
        if (!jSONObject.isNull("thumb")) {
            if (isDemoModeOn()) {
                String string8 = jSONObject.getString("thumb");
                article.mArticleImage = string8.replace("localfile://", "file://" + MedscapeApplication.get().getPreferences().getString(Constants.PREF_DEMO_ROOT_DIR, "") + "/");
            } else {
                article.mArticleImage = jSONObject.getString("thumb");
            }
        }
        if (!jSONObject.isNull("celltype")) {
            article.mCellType = jSONObject.getInt("celltype");
        }
        if (!jSONObject.isNull("conftag")) {
            article.mConfTag = jSONObject.getString("conftag");
        }
        if (!jSONObject.isNull(Branch.REFERRAL_CODE_TYPE)) {
            article.mCredit = jSONObject.getString(Branch.REFERRAL_CODE_TYPE);
        }
        article.mLegacy = z;
        if (!jSONObject.isNull("MustShowFullImage")) {
            article.mMustShowFullImage = jSONObject.getInt("MustShowFullImage") == 1;
        }
        if (article.mLegacy || article.mCellType == 1) {
            if (!jSONObject.isNull("shareable") && !jSONObject.getString("shareable").isEmpty() && jSONObject.getInt("shareable") == 1) {
                z2 = true;
            }
            article.mShareable = z2;
            if (!jSONObject.isNull("info-title")) {
                article.mInfoTitle = jSONObject.getString("info-title");
            } else {
                article.mInfoTitle = null;
            }
        }
        if (!jSONObject.isNull("showsCarouselPlaceholder")) {
            article.mShowsCarouselPlaceholder = jSONObject.optBoolean("showsCarouselPlaceholder");
        }
        String str3 = article.mLink;
        if (str3 != null) {
            if (str3.contains("features/slideshow/") && article.mLink.contains("medscape.com")) {
                article.mShowsCarouselPlaceholder = true;
                article.mIsEditorial = true;
            }
            article.mArticleId = Uri.parse(article.mLink).getLastPathSegment();
        }
        if (!jSONObject.isNull("orientationLock")) {
            article.mOrientationLock = jSONObject.optString("orientationLock");
        }
        if (!jSONObject.isNull("jc")) {
            article.mJobCode = jSONObject.optString("jc");
        }
        if (!jSONObject.isNull("carouselHeaderColor")) {
            article.mCarouselHeaderColor = jSONObject.optString("carouselHeaderColor");
        }
        return article;
    }

    public void writeToParcel(Parcel parcel, int i) {
        String str = this.mTitle;
        String str2 = "";
        if (str == null) {
            str = str2;
        }
        parcel.writeString(str);
        String str3 = this.mLink;
        if (str3 == null) {
            str3 = str2;
        }
        parcel.writeString(str3);
        String str4 = this.mDescription;
        if (str4 == null) {
            str4 = str2;
        }
        parcel.writeString(str4);
        parcel.writeString(getDate());
        String str5 = this.mCategory;
        if (str5 == null) {
            str5 = str2;
        }
        parcel.writeString(str5);
        String str6 = this.mArticleId;
        if (str6 == null) {
            str6 = str2;
        }
        parcel.writeString(str6);
        String str7 = this.mPublication;
        if (str7 == null) {
            str7 = str2;
        }
        parcel.writeString(str7);
        String str8 = this.mCarouselHeaderType;
        if (str8 == null) {
            str8 = str2;
        }
        parcel.writeString(str8);
        String str9 = this.mCarouselHeaderTitle;
        if (str9 == null) {
            str9 = str2;
        }
        parcel.writeString(str9);
        String str10 = this.mActivityId;
        if (str10 == null) {
            str10 = str2;
        }
        parcel.writeString(str10);
        String str11 = this.mTcId;
        if (str11 == null) {
            str11 = str2;
        }
        parcel.writeString(str11);
        parcel.writeInt(this.mCellType);
        String str12 = this.mConfTag;
        if (str12 == null) {
            str12 = str2;
        }
        parcel.writeString(str12);
        String str13 = this.mCredit;
        if (str13 == null) {
            str13 = str2;
        }
        parcel.writeString(str13);
        parcel.writeInt(this.mLegacy ? 1 : 0);
        parcel.writeInt(this.mMustShowFullImage ? 1 : 0);
        String str14 = this.mArticleImage;
        if (str14 == null) {
            str14 = str2;
        }
        parcel.writeString(str14);
        String str15 = this.mInfoTitle;
        if (str15 == null) {
            str15 = str2;
        }
        parcel.writeString(str15);
        parcel.writeInt(this.mShareable ? 1 : 0);
        parcel.writeInt(this.mShowsCarouselPlaceholder ? 1 : 0);
        parcel.writeInt(this.mIsEditorial ? 1 : 0);
        String str16 = this.mOrientationLock;
        if (str16 == null) {
            str16 = str2;
        }
        parcel.writeString(str16);
        String str17 = this.mJobCode;
        if (str17 == null) {
            str17 = str2;
        }
        parcel.writeString(str17);
        String str18 = this.mCarouselHeaderColor;
        if (str18 == null) {
            str18 = str2;
        }
        parcel.writeString(str18);
        String str19 = this.mContentTypeDate;
        if (str19 != null) {
            str2 = str19;
        }
        parcel.writeString(str2);
        parcel.writeInt(this.mHasLinkUrl ? 1 : 0);
        parcel.writeInt(this.mIsInlineAD ? 1 : 0);
        parcel.writeValue(this.mSearchSuggestionMsg);
        parcel.writeValue(this.adView);
    }

    public static boolean isDemoModeOn() {
        return MedscapeApplication.get().getPreferences().getBoolean(Constants.PREF_DEMO, false);
    }
}
