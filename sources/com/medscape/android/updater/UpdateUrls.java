package com.medscape.android.updater;

import android.os.Bundle;
import com.wbmd.environment.EnvironmentConstants;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;

public class UpdateUrls {
    private static final String AT_HOST = "http://mobld01d-prf-08.portal.webmd.com:8081";
    public static String CHECK_VERSION_URL = "check_version.xml";
    public static String LEGAL_UPDATE_URL = "legal_url";
    public static String REFERENCEPLIST_URL = "reference_plist_url";
    public static String UPDATEPLIST_URL = "update_plist_url";
    public static String VERSION_FILE = "version_file";
    public static String VERSION_FILE_XML = "version_file_xml";
    static Bundle sUpdateUrlBundle = new Bundle();

    static {
        Bundle bundle = new Bundle();
        bundle.putString(CHECK_VERSION_URL, "https://bi.medscape.com/pi/android/medscapeapp/");
        bundle.putString(UPDATEPLIST_URL, "https://bi.medscape.com/pi/android/medscapeapp/updates.plist");
        bundle.putString(REFERENCEPLIST_URL, "https://bi.medscape.com/pi/android/medscapeapp/reference-xml.plist");
        bundle.putString(VERSION_FILE, "ver.txt");
        bundle.putString(LEGAL_UPDATE_URL, "https://bi.medscape.com/pi/android/medscapeapp/");
        bundle.putString(VERSION_FILE_XML, "ver.xml");
        sUpdateUrlBundle.putBundle(EnvironmentConstants.PRODUCTION, bundle);
        Bundle bundle2 = new Bundle();
        bundle2.putString(CHECK_VERSION_URL, "http://img.staging.medscape.com/pi/iphone/medscapeapp/");
        bundle2.putString(UPDATEPLIST_URL, "http://img.staging.medscape.com/pi/iphone/medscapeapp/updates-qa.plist");
        bundle2.putString(REFERENCEPLIST_URL, "http://img.staging.medscape.com/pi/iphone/medscapeapp/reference-qa-xml.plist");
        bundle2.putString(VERSION_FILE, "ver-qa.txt");
        bundle2.putString(LEGAL_UPDATE_URL, "http://184.73.69.209/android-updates/");
        bundle2.putString(VERSION_FILE_XML, "ver-qa.xml");
        sUpdateUrlBundle.putBundle(EnvironmentConstants.QA, bundle2);
        Bundle bundle3 = new Bundle();
        bundle3.putString(CHECK_VERSION_URL, "http://img.staging.medscape.com/pi/iphone/monograph/");
        bundle3.putString(UPDATEPLIST_URL, "http://img.staging.medscape.com/pi/iphone/monograph/updates-dev.plist");
        bundle3.putString(REFERENCEPLIST_URL, "http://img.staging.medscape.com/pi/iphone/monograph/reference-dev-xml.plist");
        bundle3.putString(VERSION_FILE, "ver-dev.txt");
        bundle3.putString(LEGAL_UPDATE_URL, "http://184.73.69.209/android-updates/");
        bundle3.putString(VERSION_FILE_XML, "ver-dev.xml");
        sUpdateUrlBundle.putBundle(EnvironmentConstants.SANDBOX, bundle3);
        Bundle bundle4 = new Bundle();
        bundle4.putString(CHECK_VERSION_URL, "http://img.staging.medscape.com/pi/iphone/bizdev/");
        bundle4.putString(UPDATEPLIST_URL, "http://img.staging.medscape.com/pi/iphone/bizdev/updates.plist");
        bundle4.putString(REFERENCEPLIST_URL, "http://img.staging.medscape.com/pi/iphone/bizdev/reference-xml.plist");
        bundle4.putString(VERSION_FILE, "ver.txt");
        bundle4.putString(LEGAL_UPDATE_URL, "http://img.staging.medscape.com/pi/iphone/bizdev/");
        bundle4.putString(VERSION_FILE_XML, "ver-android.xml");
        sUpdateUrlBundle.putBundle(EnvironmentConstants.BIZDEV, bundle4);
        Bundle bundle5 = new Bundle();
        bundle5.putString(CHECK_VERSION_URL, "http://mobld01d-prf-08.portal.webmd.com:8081/mtest/");
        bundle5.putString(UPDATEPLIST_URL, "http://mobld01d-prf-08.portal.webmd.com:8081/mtest/updates1.plist");
        bundle5.putString(REFERENCEPLIST_URL, "http://mobld01d-prf-08.portal.webmd.com:8081/mtest/reference1.plist");
        bundle5.putString(VERSION_FILE, "ver1.txt");
        bundle5.putString(LEGAL_UPDATE_URL, "http://mobld01d-prf-08.portal.webmd.com:8081/mtest/");
        bundle5.putString(VERSION_FILE_XML, "ver1.xml");
        sUpdateUrlBundle.putBundle(UserProfile.NURSE_PRACTITIONER_ID, bundle5);
        Bundle bundle6 = new Bundle();
        bundle6.putString(CHECK_VERSION_URL, "http://mobld01d-prf-08.portal.webmd.com:8081/mtest/");
        bundle6.putString(UPDATEPLIST_URL, "http://mobld01d-prf-08.portal.webmd.com:8081/mtest/updates2.plist");
        bundle6.putString(REFERENCEPLIST_URL, "http://mobld01d-prf-08.portal.webmd.com:8081/mtest/reference2.plist");
        bundle6.putString(VERSION_FILE, "ver2.txt");
        bundle6.putString(LEGAL_UPDATE_URL, "http://mobld01d-prf-08.portal.webmd.com:8081/mtest/");
        bundle6.putString(VERSION_FILE_XML, "ver2.xml");
        sUpdateUrlBundle.putBundle("6", bundle6);
        Bundle bundle7 = new Bundle();
        bundle7.putString(CHECK_VERSION_URL, "http://mobld01d-prf-08.portal.webmd.com:8081/mtest/");
        bundle7.putString(UPDATEPLIST_URL, "http://mobld01d-prf-08.portal.webmd.com:8081/mtest/updates3.plist");
        bundle7.putString(REFERENCEPLIST_URL, "http://mobld01d-prf-08.portal.webmd.com:8081/mtest/reference3.plist");
        bundle7.putString(VERSION_FILE, "ver3.txt");
        bundle7.putString(LEGAL_UPDATE_URL, "http://mobld01d-prf-08.portal.webmd.com:8081/mtest/");
        bundle7.putString(VERSION_FILE_XML, "ver3.xml");
        sUpdateUrlBundle.putBundle("7", bundle7);
        Bundle bundle8 = new Bundle();
        bundle8.putString(CHECK_VERSION_URL, "http://mobld01d-prf-08.portal.webmd.com:8081/mtest/");
        bundle8.putString(UPDATEPLIST_URL, "http://mobld01d-prf-08.portal.webmd.com:8081/mtest/updates4.plist");
        bundle8.putString(REFERENCEPLIST_URL, "http://mobld01d-prf-08.portal.webmd.com:8081/mtest/reference4.plist");
        bundle8.putString(VERSION_FILE, "ver4.txt");
        bundle8.putString(LEGAL_UPDATE_URL, "http://mobld01d-prf-08.portal.webmd.com:8081/mtest/");
        bundle8.putString(VERSION_FILE_XML, "ver4.xml");
        sUpdateUrlBundle.putBundle(UserProfile.PHARMACIST_ID, bundle8);
        Bundle bundle9 = new Bundle();
        bundle9.putString(CHECK_VERSION_URL, "http://mobld01d-prf-08.portal.webmd.com:8081/mtest/");
        bundle9.putString(UPDATEPLIST_URL, "http://mobld01d-prf-08.portal.webmd.com:8081/mtest/updates5.plist");
        bundle9.putString(REFERENCEPLIST_URL, "http://mobld01d-prf-08.portal.webmd.com:8081/mtest/reference5.plist");
        bundle9.putString(VERSION_FILE, "ver5.txt");
        bundle9.putString(LEGAL_UPDATE_URL, "http://mobld01d-prf-08.portal.webmd.com:8081/mtest/");
        bundle9.putString(VERSION_FILE_XML, "ver5.xml");
        sUpdateUrlBundle.putBundle("9", bundle9);
        Bundle bundle10 = new Bundle();
        bundle10.putString(CHECK_VERSION_URL, "http://mobld01d-prf-08.portal.webmd.com:8081/mtest/");
        bundle10.putString(UPDATEPLIST_URL, "http://mobld01d-prf-08.portal.webmd.com:8081/mtest/updates6.plist");
        bundle10.putString(REFERENCEPLIST_URL, "http://mobld01d-prf-08.portal.webmd.com:8081/mtest/reference6.plist");
        bundle10.putString(VERSION_FILE, "ver6.txt");
        bundle10.putString(LEGAL_UPDATE_URL, "http://mobld01d-prf-08.portal.webmd.com:8081/mtest/");
        bundle10.putString(VERSION_FILE_XML, "ver6.xml");
        sUpdateUrlBundle.putBundle(UserProfile.PHYSICIAN_ID, bundle10);
    }

    public static String getUrlForEnvironment(String str, String str2) {
        return sUpdateUrlBundle.getBundle(str).getString(str2);
    }
}
