package com.wbmd.qxcalculator.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceHelper {
    private static final String KEY_CONTRIBUTING_AUTHOR = "SharedPreferenceHelper.KEY_CONTRIBUTING_AUTHOR";
    private static final String KEY_NUM_CALCULATORS = "SharedPreferenceHelper.KEY_NUM_CALCULATORS";
    private static final String KEY_NUM_CATEGORIES = "SharedPreferenceHelper.KEY_NUM_CATEGORIES";
    private static final String KEY_NUM_CATEGORIES_EVENT_SENT = "SharedPreferenceHelper.KEY_NUM_CATEGORIES_EVENT_SENT";
    private static final String KEY_REENTRY_LOGIN = "SharedPreferenceHelper.KEY_REENTRY_LOGIN";
    private static final String KEY_REGISTERED_PN_TOKEN = "SharedPreferenceHelper.KEY_REGISTERED_PN_TOKEN";
    private static final String KEY_SPLASH_PAGE_SPONSOR_CAMPAIGN_AD_ID = "SharedPreferenceHelper.KEY_SPLASH_PAGE_SPONSOR_CAMPAIGN_AD_ID";
    private static final String KEY_SPLASH_PAGE_SPONSOR_ID = "SharedPreferenceHelper.KEY_SPLASH_PAGE_SPONSOR_ID";
    private static final String KEY_SPLASH_PAGE_SPONSOR_IMAGE_HEIGHT_DIP = "SharedPreferenceHelper.KEY_SPLASH_PAGE_SPONSOR_IMAGE_HEIGHT_DIP";
    private static final String KEY_SPLASH_PAGE_SPONSOR_IMAGE_PAGE = "SharedPreferenceHelper.KEY_SPLASH_PAGE_SPONSOR_IMAGE_PAGE";
    private static final String KEY_SPLASH_PAGE_SPONSOR_IMAGE_SCALE = "SharedPreferenceHelper.KEY_SPLASH_PAGE_SPONSOR_IMAGE_SCALE";
    private static final String KEY_SPLASH_PAGE_SPONSOR_IMAGE_WIDTH_DIP = "SharedPreferenceHelper.KEY_SPLASH_PAGE_SPONSOR_IMAGE_WIDTH_DIP";
    private static final String KEY_SPLASH_PAGE_SPONSOR_TRACKER_ID = "SharedPreferenceHelper.KEY_SPLASH_PAGE_SPONSOR_TRACKER_ID";
    private static final String KEY_STORE_NAME = "SharedPreferenceHelper.KEY_STORE_NAME";
    private static SharedPreferenceHelper ourInstance;
    private Context context;
    private int numberOfCalculatorsOpened = 0;
    private int numberOfCategoriesOpened = 0;
    private SharedPreferences sharedPref;

    public static synchronized void initializeInstance(Context context2) {
        synchronized (SharedPreferenceHelper.class) {
            if (ourInstance == null) {
                ourInstance = new SharedPreferenceHelper(context2);
            }
        }
    }

    public static SharedPreferenceHelper getInstance() {
        return ourInstance;
    }

    private SharedPreferenceHelper(Context context2) {
        this.context = context2;
        this.sharedPref = context2.getSharedPreferences(KEY_STORE_NAME, 0);
    }

    public void setContext(Context context2) {
        this.context = context2;
    }

    public String getContributingAuthorString() {
        return this.sharedPref.getString(KEY_CONTRIBUTING_AUTHOR, (String) null);
    }

    public void setContributingAuthorString(String str) {
        SharedPreferences.Editor edit = this.sharedPref.edit();
        edit.putString(KEY_CONTRIBUTING_AUTHOR, str);
        edit.apply();
    }

    public String getSplashPageSponsorImagePath() {
        return this.sharedPref.getString(KEY_SPLASH_PAGE_SPONSOR_IMAGE_PAGE, (String) null);
    }

    public void setSplashPageSponsorImagePath(String str) {
        SharedPreferences.Editor edit = this.sharedPref.edit();
        edit.putString(KEY_SPLASH_PAGE_SPONSOR_IMAGE_PAGE, str);
        edit.apply();
    }

    public Float getSplashPageSponsorImageScale() {
        return Float.valueOf(this.sharedPref.getFloat(KEY_SPLASH_PAGE_SPONSOR_IMAGE_SCALE, 1.0f));
    }

    public void setSplashPageSponsorImageScale(Float f) {
        SharedPreferences.Editor edit = this.sharedPref.edit();
        edit.putFloat(KEY_SPLASH_PAGE_SPONSOR_IMAGE_SCALE, f.floatValue());
        edit.apply();
    }

    public Float getSplashPageSponsorImageWidthDip() {
        return Float.valueOf(this.sharedPref.getFloat(KEY_SPLASH_PAGE_SPONSOR_IMAGE_WIDTH_DIP, 0.0f));
    }

    public void setSplashPageSponsorImageWidthDip(Float f) {
        SharedPreferences.Editor edit = this.sharedPref.edit();
        edit.putFloat(KEY_SPLASH_PAGE_SPONSOR_IMAGE_WIDTH_DIP, f.floatValue());
        edit.apply();
    }

    public Float getSplashPageSponsorImageHeightDip() {
        return Float.valueOf(this.sharedPref.getFloat(KEY_SPLASH_PAGE_SPONSOR_IMAGE_HEIGHT_DIP, 0.0f));
    }

    public void setSplashPageSponsorImageHeightDip(Float f) {
        SharedPreferences.Editor edit = this.sharedPref.edit();
        edit.putFloat(KEY_SPLASH_PAGE_SPONSOR_IMAGE_HEIGHT_DIP, f.floatValue());
        edit.apply();
    }

    public String getSplashScreenTrackerId() {
        return this.sharedPref.getString(KEY_SPLASH_PAGE_SPONSOR_TRACKER_ID, (String) null);
    }

    public void setSplashScreenTrackerId(String str) {
        SharedPreferences.Editor edit = this.sharedPref.edit();
        edit.putString(KEY_SPLASH_PAGE_SPONSOR_TRACKER_ID, str);
        edit.apply();
    }

    public String getSplashScreenSponsorId() {
        return this.sharedPref.getString(KEY_SPLASH_PAGE_SPONSOR_ID, (String) null);
    }

    public void setSplashScreenCampaignAdId(Long l) {
        SharedPreferences.Editor edit = this.sharedPref.edit();
        edit.putLong(KEY_SPLASH_PAGE_SPONSOR_CAMPAIGN_AD_ID, l.longValue());
        edit.apply();
    }

    public Long getSplashScreenCampaignAdId() {
        long j = this.sharedPref.getLong(KEY_SPLASH_PAGE_SPONSOR_CAMPAIGN_AD_ID, 0);
        if (j > 0) {
            return Long.valueOf(j);
        }
        return null;
    }

    public void setSplashScreenSponsorId(String str) {
        SharedPreferences.Editor edit = this.sharedPref.edit();
        edit.putString(KEY_SPLASH_PAGE_SPONSOR_ID, str);
        edit.apply();
    }

    public String getRegisteredPnToken() {
        return this.sharedPref.getString(KEY_REGISTERED_PN_TOKEN, (String) null);
    }

    public void setRegisteredPnToken(String str) {
        SharedPreferences.Editor edit = this.sharedPref.edit();
        edit.putString(KEY_REGISTERED_PN_TOKEN, str);
        edit.apply();
    }

    public void setReentryLogin() {
        SharedPreferences.Editor edit = this.sharedPref.edit();
        edit.putBoolean(KEY_REENTRY_LOGIN, true);
        edit.apply();
    }

    public Boolean getReentryLogin() {
        return Boolean.valueOf(this.sharedPref.getBoolean(KEY_REENTRY_LOGIN, false));
    }

    public void setNumberOfCalculatorsOpened(int i) {
        SharedPreferences.Editor edit = this.sharedPref.edit();
        edit.putInt(KEY_NUM_CALCULATORS, i);
        edit.apply();
        this.numberOfCalculatorsOpened = i;
    }

    public int getNumberOfCalculatorsOpened() {
        return this.sharedPref.getInt(KEY_NUM_CALCULATORS, this.numberOfCalculatorsOpened);
    }

    public void setNumberOfCategoriesOpened(int i) {
        SharedPreferences.Editor edit = this.sharedPref.edit();
        edit.putInt(KEY_NUM_CATEGORIES, i);
        edit.apply();
        this.numberOfCategoriesOpened = i;
    }

    public int getNumberOfCategoriesOpened() {
        return this.sharedPref.getInt(KEY_NUM_CATEGORIES, this.numberOfCategoriesOpened);
    }

    public void setNumCategoriesFirEventSent(boolean z) {
        SharedPreferences.Editor edit = this.sharedPref.edit();
        edit.putBoolean(KEY_NUM_CATEGORIES_EVENT_SENT, z);
        edit.apply();
    }

    public Boolean isNumCategoriesFirEventSent() {
        return Boolean.valueOf(this.sharedPref.getBoolean(KEY_NUM_CATEGORIES_EVENT_SENT, false));
    }
}
