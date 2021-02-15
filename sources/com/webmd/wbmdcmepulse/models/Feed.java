package com.webmd.wbmdcmepulse.models;

import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.facebook.appevents.AppEventsConstants;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdcmepulse.models.utils.Extensions;
import io.branch.referral.Branch;
import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONObject;

public class Feed implements Parcelable, Serializable {
    public static final Parcelable.Creator<Feed> CREATOR = new Parcelable.Creator<Feed>() {
        public Feed createFromParcel(Parcel parcel) {
            return new Feed(parcel);
        }

        public Feed[] newArray(int i) {
            return new Feed[i];
        }
    };
    private static final String TAG = Feed.class.getSimpleName();
    private String mActivityExpirationDate;
    private String mActivityId;
    private String mArticleId;
    private int mArticleType;
    private String mCG;
    private String mCT;
    private String mConfTag;
    private String mCredit;
    private String mDOM;
    private String mDate;
    private String mDetails;
    private String mDetailsHTML;
    private boolean mFitImageToFrame;
    private String mInfoTitle;
    private String mJobCode;
    private String mLinkUrl;
    private String mMaxCredits;
    private String mMediaType;
    private String mParId;
    private String mPublication;
    private String mPublicationColor;
    private boolean mShowsShareable;
    private String mTCID;
    private String mThumbUrl;
    private String mTitle;
    private String mTitleColor;
    private int mTitleLineCount;
    private String mUri;
    private String mUrl;

    public int describeContents() {
        return 0;
    }

    public String getLinkUrl() {
        return this.mLinkUrl;
    }

    public void setLinkUrl(String str) {
        this.mLinkUrl = str;
    }

    public String getDate() {
        return this.mDate;
    }

    public void setDate(String str) {
        this.mDate = str;
    }

    protected Feed(Parcel parcel) {
        this.mTitle = parcel.readString();
        this.mTitleLineCount = parcel.readInt();
        this.mDetailsHTML = parcel.readString();
        this.mDetails = parcel.readString();
        this.mCT = parcel.readString();
        this.mCG = parcel.readString();
        this.mPublication = parcel.readString();
        this.mThumbUrl = parcel.readString();
        this.mCredit = parcel.readString();
        this.mMediaType = parcel.readString();
        this.mTitleColor = parcel.readString();
        this.mPublicationColor = parcel.readString();
        this.mInfoTitle = parcel.readString();
        this.mConfTag = parcel.readString();
        this.mTCID = parcel.readString();
        this.mActivityId = parcel.readString();
        this.mParId = parcel.readString();
        this.mArticleType = parcel.readInt();
        boolean z = true;
        this.mShowsShareable = parcel.readByte() != 0;
        this.mFitImageToFrame = parcel.readByte() == 0 ? false : z;
        this.mDOM = parcel.readString();
        this.mUri = parcel.readString();
        this.mJobCode = parcel.readString();
        this.mDate = parcel.readString();
        this.mArticleId = parcel.readString();
        this.mUrl = parcel.readString();
        this.mLinkUrl = parcel.readString();
        this.mMaxCredits = parcel.readString();
        this.mActivityExpirationDate = parcel.readString();
    }

    public Feed() {
    }

    public Feed(Cursor cursor) {
    }

    public Feed(JSONObject jSONObject, Context context) {
        if (jSONObject != null) {
            this.mTitle = jSONObject.optString("ti", "");
            this.mTitleLineCount = -1;
            this.mDetails = jSONObject.optString("de", "");
            this.mDetailsHTML = jSONObject.optString("de", "");
            this.mCT = jSONObject.optString("ct", "");
            this.mCG = jSONObject.optString("cg", "");
            this.mPublication = jSONObject.optString("pu", "");
            this.mThumbUrl = jSONObject.optString("thumb", "");
            this.mCredit = jSONObject.optString(Branch.REFERRAL_CODE_TYPE, "");
            this.mMediaType = jSONObject.optString("media", "");
            this.mTitleColor = jSONObject.optString("ti-color", "");
            this.mPublicationColor = jSONObject.optString("pu-color", "");
            this.mInfoTitle = jSONObject.optString("info-title", "");
            this.mConfTag = jSONObject.optString("conftag", "");
            this.mTCID = jSONObject.optString("tcid", "");
            this.mActivityId = jSONObject.optString("activityId", "");
            this.mParId = jSONObject.optString("parId", "");
            this.mDOM = jSONObject.optString("dom", "");
            this.mUri = jSONObject.optString("uri", "");
            this.mJobCode = jSONObject.optString("jc", "");
            String optString = jSONObject.optString("celltype", "");
            if (!optString.trim().equalsIgnoreCase("")) {
                try {
                    this.mArticleType = Integer.parseInt(optString);
                } catch (NumberFormatException unused) {
                }
            }
            double maxCreditsFromCredits = getMaxCreditsFromCredits(jSONObject);
            if (maxCreditsFromCredits >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                this.mMaxCredits = String.format("%s Credits", new Object[]{Double.valueOf(maxCreditsFromCredits)});
            }
            this.mActivityExpirationDate = jSONObject.optString("activityExpirationDate", "");
            String optString2 = jSONObject.optString("da", "");
            String optString3 = jSONObject.optString("mo", "");
            String optString4 = jSONObject.optString("ye", "");
            String optString5 = jSONObject.optString(OmnitureConstants.OMNITURE_FILTER_DATE, "");
            if (Extensions.isStringNullOrEmpty(optString2) || Extensions.isStringNullOrEmpty(optString3) || Extensions.isStringNullOrEmpty(optString4)) {
                this.mDate = optString5;
            } else {
                this.mDate = String.format("%s/%s/%s", new Object[]{optString2, optString3, optString4});
            }
            if (Extensions.isStringNullOrEmpty(this.mDOM)) {
                this.mDOM = Constants.Config.getDomain(context);
            }
            this.mUrl = String.format("https://%s%s", new Object[]{this.mDOM, this.mUri});
            this.mFitImageToFrame = jSONObject.optBoolean("mustShowFullImage", false);
            if (jSONObject.optString("shareable", AppEventsConstants.EVENT_PARAM_VALUE_YES).equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
                this.mShowsShareable = true;
            } else {
                this.mShowsShareable = true;
            }
        }
    }

    private double getMaxCreditsFromCredits(JSONObject jSONObject) {
        JSONObject optJSONObject;
        JSONArray optJSONArray;
        double d = -1.0d;
        if (!(jSONObject == null || (optJSONObject = jSONObject.optJSONObject("credits")) == null || (optJSONArray = optJSONObject.optJSONArray(Branch.REFERRAL_CODE_TYPE)) == null || optJSONArray.length() <= 0)) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                try {
                    Object obj = optJSONArray.get(i);
                    if (obj instanceof JSONObject) {
                        String optString = ((JSONObject) obj).optString("credits");
                        if (!Extensions.isStringNullOrEmpty(optString)) {
                            double parseDouble = Double.parseDouble(optString);
                            if (parseDouble > d) {
                                d = parseDouble;
                            }
                        }
                    }
                } catch (Exception unused) {
                    Log.d(TAG, "Failed to get credit object");
                }
            }
        }
        return d;
    }

    public String getUrl(Context context) {
        String str = this.mUri;
        if (str != null && str.startsWith("http")) {
            return this.mUri;
        }
        if (Extensions.isStringNullOrEmpty(this.mDOM)) {
            this.mDOM = Constants.Config.getDomain(context);
        }
        String format = String.format("https://%s%s", new Object[]{this.mDOM, this.mUri});
        this.mUrl = format;
        return format;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public int getTitleLineCount() {
        return this.mTitleLineCount;
    }

    public void setTitleLineCount(int i) {
        this.mTitleLineCount = i;
    }

    public String getDetailsHTML() {
        return this.mDetailsHTML;
    }

    public void setDetailsHTML(String str) {
        this.mDetailsHTML = str;
    }

    public String getDetails() {
        return this.mDetails;
    }

    public void setDetails(String str) {
        this.mDetails = str;
    }

    public String getCT() {
        return this.mCT;
    }

    public void setCT(String str) {
        this.mCT = str;
    }

    public String getCG() {
        return this.mCG;
    }

    public void setCG(String str) {
        this.mCG = str;
    }

    public String getPublication() {
        return this.mPublication;
    }

    public void setPublication(String str) {
        this.mPublication = str;
    }

    public String getThumbUrl() {
        if (!Extensions.isStringNullOrEmpty(this.mThumbUrl) && this.mThumbUrl.startsWith("http://")) {
            this.mThumbUrl = this.mThumbUrl.replace("http://", "https://");
        }
        return this.mThumbUrl;
    }

    public void setThumbUrl(String str) {
        this.mThumbUrl = str;
    }

    public String getCredit() {
        return this.mCredit;
    }

    public void setCredit(String str) {
        this.mCredit = str;
    }

    public String getMediaType() {
        return this.mMediaType;
    }

    public void setMediaType(String str) {
        this.mMediaType = str;
    }

    public String getTitleColor() {
        return this.mTitleColor;
    }

    public void setTitleColor(String str) {
        this.mTitleColor = str;
    }

    public String getPublicationColor() {
        return this.mPublicationColor;
    }

    public void setPublicationColor(String str) {
        this.mPublicationColor = str;
    }

    public String getInfoTitle() {
        return this.mInfoTitle;
    }

    public void setInfoTitle(String str) {
        this.mInfoTitle = str;
    }

    public String getConfTag() {
        return this.mConfTag;
    }

    public void setConfTag(String str) {
        this.mConfTag = str;
    }

    public String getTCID() {
        return this.mTCID;
    }

    public void setTCID(String str) {
        this.mTCID = str;
    }

    public String getActivityId() {
        return this.mActivityId;
    }

    public void setActivityId(String str) {
        this.mActivityId = str;
    }

    public String getParId() {
        return this.mParId;
    }

    public void setParId(String str) {
        this.mParId = str;
    }

    public int getArticleType() {
        return this.mArticleType;
    }

    public void setArticleType(int i) {
        this.mArticleType = i;
    }

    public boolean ismShowsShareable() {
        return this.mShowsShareable;
    }

    public void setShowsShareable(boolean z) {
        this.mShowsShareable = z;
    }

    public String getDom() {
        return this.mDOM;
    }

    public void setUri(String str) {
        this.mUri = str;
    }

    public String getUri() {
        return this.mUri;
    }

    public void setJobCode(String str) {
        this.mJobCode = str;
    }

    public String getJobCode() {
        return this.mJobCode;
    }

    public String getArticleId() {
        if (Extensions.isStringNullOrEmpty(this.mArticleId) && !Extensions.isStringNullOrEmpty(this.mUri) && this.mUri.contains("/")) {
            String[] split = this.mUri.split("/");
            this.mArticleId = split[split.length - 1];
        }
        return this.mArticleId;
    }

    public void setArticleId(String str) {
        this.mArticleId = str;
    }

    public String getMaxCredits() {
        return this.mMaxCredits;
    }

    public void setMaxCredits(String str) {
        this.mMaxCredits = str;
    }

    public String getActivityExpirationDate() {
        return this.mActivityExpirationDate;
    }

    public void setActivityExpirationDate(String str) {
        this.mActivityExpirationDate = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mTitle);
        parcel.writeInt(this.mTitleLineCount);
        parcel.writeString(this.mDetailsHTML);
        parcel.writeString(this.mDetails);
        parcel.writeString(this.mCT);
        parcel.writeString(this.mCG);
        parcel.writeString(this.mPublication);
        parcel.writeString(this.mThumbUrl);
        parcel.writeString(this.mCredit);
        parcel.writeString(this.mMediaType);
        parcel.writeString(this.mTitleColor);
        parcel.writeString(this.mPublicationColor);
        parcel.writeString(this.mInfoTitle);
        parcel.writeString(this.mConfTag);
        parcel.writeString(this.mTCID);
        parcel.writeString(this.mActivityId);
        parcel.writeString(this.mParId);
        parcel.writeInt(this.mArticleType);
        parcel.writeByte(this.mShowsShareable ? (byte) 1 : 0);
        parcel.writeByte(this.mFitImageToFrame ? (byte) 1 : 0);
        parcel.writeString(this.mDOM);
        parcel.writeString(this.mUri);
        parcel.writeString(this.mJobCode);
        parcel.writeString(this.mDate);
        parcel.writeString(this.mArticleId);
        parcel.writeString(this.mUrl);
        parcel.writeString(this.mLinkUrl);
        parcel.writeString(this.mMaxCredits);
        parcel.writeString(this.mActivityExpirationDate);
    }
}
