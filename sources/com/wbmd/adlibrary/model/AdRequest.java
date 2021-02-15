package com.wbmd.adlibrary.model;

import androidx.exifinterface.media.ExifInterface;
import com.facebook.appevents.AppEventsConstants;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.media.android.bidder.base.models.MNetUser;

public class AdRequest {
    private Map<String, String> mAdCustomParamaters;
    private String mAdType;
    private String mContentUrl;
    private List<String> mExclusionCategories;

    private AdRequest(AdRequestBuilder adRequestBuilder) {
        this.mAdType = adRequestBuilder.adType;
        this.mContentUrl = adRequestBuilder.contentUrl;
        this.mExclusionCategories = adRequestBuilder.exclCat;
        HashMap hashMap = new HashMap();
        this.mAdCustomParamaters = hashMap;
        hashMap.put(AdParameterKeys.SECTION_ID, adRequestBuilder.sectionId);
        this.mAdCustomParamaters.put("pos", adRequestBuilder.pos);
        this.mAdCustomParamaters.put("art", adRequestBuilder.articleId);
        this.mAdCustomParamaters.put(AdParameterKeys.DRUG_ID, adRequestBuilder.drugId);
        this.mAdCustomParamaters.put("env", adRequestBuilder.env);
        this.mAdCustomParamaters.put(AdParameterKeys.GENDER, adRequestBuilder.mg);
        this.mAdCustomParamaters.put("mapp", adRequestBuilder.mapp);
        this.mAdCustomParamaters.put(AdParameterKeys.PRIMARY_ID, adRequestBuilder.pt);
        this.mAdCustomParamaters.put(AdParameterKeys.SECONDARY_IDS, adRequestBuilder.sec);
        this.mAdCustomParamaters.put(AdParameterKeys.AD_SESSION, adRequestBuilder.ses);
        this.mAdCustomParamaters.put(AdParameterKeys.CONDITION, adRequestBuilder.sc);
        this.mAdCustomParamaters.put(AdParameterKeys.OS, adRequestBuilder.os);
        this.mAdCustomParamaters.put("tug", adRequestBuilder.tug);
        this.mAdCustomParamaters.put(AdParameterKeys.PUG_KEY, adRequestBuilder.pug);
        this.mAdCustomParamaters.put(AdParameterKeys.IS_SPONSORED, adRequestBuilder.issponsored);
        this.mAdCustomParamaters.put("dmp", adRequestBuilder.audienceInfo);
    }

    public String getContentUrl() {
        return this.mContentUrl;
    }

    public String getAdType() {
        return this.mAdType;
    }

    public List<String> getExclusionCategories() {
        return this.mExclusionCategories;
    }

    public Map getCustomAdParamaters() {
        return this.mAdCustomParamaters;
    }

    public static class AdRequestBuilder {
        /* access modifiers changed from: private */
        public String adType;
        /* access modifiers changed from: private */
        public String articleId;
        /* access modifiers changed from: private */
        public String audienceInfo;
        /* access modifiers changed from: private */
        public String contentUrl;
        /* access modifiers changed from: private */
        public String drugId;
        /* access modifiers changed from: private */
        public String env;
        /* access modifiers changed from: private */
        public List<String> exclCat;
        /* access modifiers changed from: private */
        public String issponsored;
        /* access modifiers changed from: private */
        public String mapp;
        /* access modifiers changed from: private */
        public String mg;
        /* access modifiers changed from: private */
        public String os;
        /* access modifiers changed from: private */
        public String pos;
        /* access modifiers changed from: private */
        public String pt;
        /* access modifiers changed from: private */
        public String pug;
        /* access modifiers changed from: private */
        public String sc;
        /* access modifiers changed from: private */
        public String sec;
        /* access modifiers changed from: private */
        public String sectionId;
        /* access modifiers changed from: private */
        public String ses;
        /* access modifiers changed from: private */
        public String tug;

        public AdRequestBuilder(String str) {
            this.adType = str;
        }

        public AdRequestBuilder audienceInfo(String str) {
            this.audienceInfo = str;
            return this;
        }

        public AdRequestBuilder contentUrl(String str) {
            this.contentUrl = str;
            return this;
        }

        public AdRequestBuilder sectionId(String str) {
            this.sectionId = str;
            return this;
        }

        public AdRequestBuilder pos(String str) {
            this.pos = str;
            return this;
        }

        public AdRequestBuilder articleId(String str) {
            this.articleId = str;
            return this;
        }

        public AdRequestBuilder drugId(String str) {
            this.drugId = str;
            return this;
        }

        public AdRequestBuilder env(String str) {
            this.env = str;
            return this;
        }

        public AdRequestBuilder mg(String str) {
            if (str.equals("M")) {
                this.mg = AppEventsConstants.EVENT_PARAM_VALUE_YES;
            } else if (str.equals(MNetUser.FEMALE)) {
                this.mg = ExifInterface.GPS_MEASUREMENT_2D;
            }
            return this;
        }

        public AdRequestBuilder mapp(String str) {
            if (str != null) {
                str = str.replace(".", "");
            }
            this.mapp = str;
            return this;
        }

        public AdRequestBuilder pt(String str) {
            this.pt = str;
            return this;
        }

        public AdRequestBuilder sec(String str) {
            this.sec = str;
            return this;
        }

        public AdRequestBuilder exclCat(List<String> list) {
            this.exclCat = list;
            return this;
        }

        public AdRequestBuilder ses(int i) {
            this.ses = String.valueOf(i);
            return this;
        }

        public AdRequestBuilder sc(String str) {
            this.sc = str;
            return this;
        }

        public AdRequestBuilder os(String str) {
            this.os = str;
            return this;
        }

        public AdRequestBuilder tug(String str) {
            this.tug = str;
            return this;
        }

        public AdRequestBuilder pug(String str) {
            this.pug = str;
            return this;
        }

        public AdRequestBuilder issponsored(String str) {
            this.issponsored = str;
            return this;
        }

        public AdRequest build() {
            return new AdRequest(this);
        }
    }
}
