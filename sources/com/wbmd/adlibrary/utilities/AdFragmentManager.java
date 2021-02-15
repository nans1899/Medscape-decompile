package com.wbmd.adlibrary.utilities;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.facebook.appevents.AppEventsConstants;
import com.wbmd.adlibrary.callbacks.IAdStateListener;
import com.wbmd.adlibrary.constants.AdTypes;
import com.wbmd.adlibrary.fragments.AdFragment;
import com.wbmd.adlibrary.model.AdRequest;
import com.wbmd.adlibrary.model.AdSettings;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import java.util.Iterator;

public class AdFragmentManager {
    private static final String AD_FRAGMENT_TAG = "ad_fragment_tag";
    private static final int DEV_ADS_ENV = 1;
    private static final int PRODUCTION_ADS_ENV = 0;
    private static final int QA_ADS_ENV = 2;
    private static final String TAG = AdFragmentManager.class.getSimpleName();
    private static AdFragment adFragment;

    public static void initializeAdFragment(Context context, FragmentManager fragmentManager, int i, AdSettings adSettings, IAdStateListener iAdStateListener) {
        FragmentTransaction beginTransaction;
        if (fragmentManager != null && adSettings.getIsPhone() && (beginTransaction = fragmentManager.beginTransaction()) != null) {
            AdRequest.AdRequestBuilder pos = new AdRequest.AdRequestBuilder(AdTypes.BANNER).sectionId(adSettings.getSectionId()).pos(adSettings.getPos());
            int adsEnv = adSettings.getAdsEnv();
            if (adsEnv == 0) {
                pos.env(AppEventsConstants.EVENT_PARAM_VALUE_NO);
            } else if (adsEnv == 1) {
                pos.env(AppEventsConstants.EVENT_PARAM_VALUE_YES);
            } else if (adsEnv == 2) {
                pos.env(ExifInterface.GPS_MEASUREMENT_2D);
            }
            if (!StringExtensions.isNullOrEmpty(adSettings.getArticleId())) {
                pos.articleId(adSettings.getArticleId());
            }
            if (!StringExtensions.isNullOrEmpty(adSettings.getAppVersion())) {
                pos.mapp(adSettings.getAppVersion());
            }
            if (!StringExtensions.isNullOrEmpty(adSettings.getGender())) {
                pos.mg(adSettings.getGender());
            }
            if (!StringExtensions.isNullOrEmpty(adSettings.getConditionId())) {
                pos.sc(adSettings.getConditionId());
            }
            if (!StringExtensions.isNullOrEmpty(adSettings.getDrugId())) {
                pos.drugId(adSettings.getDrugId());
            }
            if (!StringExtensions.isNullOrEmpty(adSettings.getPrimaryId())) {
                pos.pt(adSettings.getPrimaryId());
            }
            pos.os(Build.VERSION.RELEASE);
            pos.ses(adSettings.getAdSession());
            String lotameAttributesCSV = adSettings.getLotameAttributesCSV();
            if (!StringExtensions.isNullOrEmpty(lotameAttributesCSV)) {
                pos.audienceInfo(lotameAttributesCSV);
            }
            if (adSettings.getSecondaryIds() != null) {
                int size = adSettings.getSecondaryIds().size();
                int i2 = 0;
                StringBuilder sb = new StringBuilder();
                Iterator<String> it = adSettings.getSecondaryIds().iterator();
                while (it.hasNext()) {
                    sb.append(it.next());
                    i2++;
                    if (i2 < size) {
                        sb.append(",");
                    }
                }
                if (!StringExtensions.isNullOrEmpty(sb.toString())) {
                    pos.sec(sb.toString());
                }
            }
            AdFragment newInstance = AdFragment.newInstance(pos.build(), iAdStateListener);
            adFragment = newInstance;
            if (newInstance != null) {
                try {
                    beginTransaction.add(i, (Fragment) newInstance, AD_FRAGMENT_TAG);
                    beginTransaction.commit();
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
            } else {
                Log.w(TAG, "No ad");
            }
        }
    }
}
