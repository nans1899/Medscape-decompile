package com.wbmd.adlibrary.utilities;

import androidx.exifinterface.media.ExifInterface;
import com.facebook.appevents.AppEventsConstants;
import com.wbmd.adlibrary.audience.Audience;
import com.wbmd.adlibrary.model.AdRequest;
import com.wbmd.adlibrary.model.AdSettings;
import com.wbmd.wbmdcommons.extensions.StringExtensions;

public class AdSettingsRequestBuilder {
    private static final int DEV_ADS_ENV = 1;
    private static final int ENV_3 = 3;
    private static final int PRODUCTION_ADS_ENV = 0;
    private static final int QA_ADS_ENV = 2;

    public static AdRequest.AdRequestBuilder build(AdSettings adSettings, AdRequest.AdRequestBuilder adRequestBuilder) {
        int adsEnv = adSettings.getAdsEnv();
        if (adsEnv == 0) {
            adRequestBuilder.env(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        } else if (adsEnv == 1) {
            adRequestBuilder.env(AppEventsConstants.EVENT_PARAM_VALUE_YES);
        } else if (adsEnv == 2) {
            adRequestBuilder.env(ExifInterface.GPS_MEASUREMENT_2D);
        } else if (adsEnv == 3) {
            adRequestBuilder.env(ExifInterface.GPS_MEASUREMENT_3D);
        }
        if (!StringExtensions.isNullOrEmpty(adSettings.getContentUrl())) {
            adRequestBuilder.contentUrl(adSettings.getContentUrl());
        }
        if (!StringExtensions.isNullOrEmpty(adSettings.getAppVersion())) {
            adRequestBuilder.mapp(adSettings.getAppVersion());
        }
        if (!StringExtensions.isNullOrEmpty(adSettings.getGender())) {
            adRequestBuilder.mg(adSettings.getGender());
        }
        if (!StringExtensions.isNullOrEmpty(adSettings.getDrugId())) {
            adRequestBuilder.drugId(adSettings.getDrugId());
        }
        if (!StringExtensions.isNullOrEmpty(adSettings.getArticleId())) {
            adRequestBuilder.articleId(adSettings.getArticleId());
        }
        if (adSettings.getExclusionCategories() != null && adSettings.getExclusionCategories().size() > 0) {
            adRequestBuilder.exclCat(adSettings.getExclusionCategories());
        }
        if (adSettings.getSecondaryIds() != null && adSettings.getSecondaryIds().size() > 0) {
            adRequestBuilder.sec(adSettings.getSecondaryIdsCommaDelimeted());
        }
        if (!StringExtensions.isNullOrEmpty(adSettings.getPrimaryId())) {
            adRequestBuilder.pt(adSettings.getPrimaryId());
        }
        if (!StringExtensions.isNullOrEmpty(adSettings.getConditionId())) {
            adRequestBuilder.sc(adSettings.getConditionId());
        }
        if (!StringExtensions.isNullOrEmpty(adSettings.getOs())) {
            adRequestBuilder.os(adSettings.getOs());
        }
        if (adSettings.getAdSession() != 0) {
            adRequestBuilder.ses(adSettings.getAdSession());
        }
        if (!StringExtensions.isNullOrEmpty(adSettings.getIsSponsored())) {
            adRequestBuilder.issponsored(adSettings.getIsSponsored());
        }
        if (!(adSettings.getAudienceInfoResponse() == null || adSettings.getAudienceInfoResponse().getProfile() == null || adSettings.getAudienceInfoResponse().getProfile().getAudiences() == null || adSettings.getAudienceInfoResponse().getProfile().getAudiences().getAudience() == null || adSettings.getAudienceInfoResponse().getProfile().getAudiences().getAudience().size() <= 0)) {
            int size = adSettings.getAudienceInfoResponse().getProfile().getAudiences().getAudience().size();
            int i = 0;
            StringBuilder sb = new StringBuilder();
            for (Audience abbr : adSettings.getAudienceInfoResponse().getProfile().getAudiences().getAudience()) {
                sb.append(abbr.getAbbr());
                i++;
                if (i < size) {
                    sb.append(",");
                }
            }
            String sb2 = sb.toString();
            if (!StringExtensions.isNullOrEmpty(sb2)) {
                adRequestBuilder.audienceInfo(sb2);
            }
        }
        return adRequestBuilder;
    }
}
